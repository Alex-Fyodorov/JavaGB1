package exceptions.hw3.validators;

import exceptions.hw3.exceptions.QualityDataException;
import exceptions.hw3.exceptions.QuantityDataInputException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements Validating {

    int FIELDS_COUNT = 6;

    @Override
    public List<String> validate(List<String> inputData) throws QuantityDataInputException, QualityDataException {
        if (inputData.size() != FIELDS_COUNT) {
            throw new QuantityDataInputException(
                    "Количество данных не соответствует необходимому", inputData.size() - FIELDS_COUNT);
        }
        List<String> result = new ArrayList<>();
        result.add(validName(inputData.get(0), 0, "фамилия"));
        result.add(validName(inputData.get(1), 1, "имя"));
        result.add(validName(inputData.get(2), 2, "отчество"));
        result.add(validBirthday(inputData.get(3), 3));
        result.add(validPhone(inputData.get(4), 4));
        result.add(validSex(inputData.get(5), 5));
        return result;
    }

    private String validPhone(String str, int index) throws QualityDataException {
        long phone;
        try {
            phone = Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new QualityDataException("Номер телефона не соответствует формату", index, "телефон");
        }
        return str;
    }

    private String validSex(String str, int index) throws QualityDataException {
        if (!str.equals("м") && !str.equals("ж")) {
            throw new QualityDataException("Запись пола не соответствует формату", index, "пол");
        }
        return str;
    }

    private String validBirthday(String str, int index) throws QualityDataException {
        List<String> listStr = new ArrayList<>(Arrays.asList(str.strip().split("\\.")));
        if (listStr.size() != 3) {
            throw new QualityDataException("Запись дня рождения не соответствует формату", index, "день рождения");
        }
        int inputDate;
        int month;
        int day;
        try {
            inputDate = Integer.parseInt(listStr.get(2) + listStr.get(1) + listStr.get(0));
            month = Integer.parseInt(listStr.get(1));
            day = Integer.parseInt(listStr.get(0));
        } catch (NumberFormatException e) {
            throw new QualityDataException("Запись дня рождения не соответствует формату", index, "день рождения");
        }
        int currentDate = 0;
        try {
            currentDate = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (inputDate > currentDate || (month < 1 || month > 12) || (day < 1 || day > 31)) {
            throw new QualityDataException("Запись дня рождения не соответствует формату", index, "день рождения");
        }
        return str;
    }

    private String validName(String str, int index, String field) throws QualityDataException {
        boolean foundRus = checkWordByAlphabet("[а-яА-Я]+", str);
        boolean foundEng = checkWordByAlphabet("[a-zA-Z]+", str);
        if (!foundEng && !foundRus) {
            throw new QualityDataException("Запись имени не соответствует формату", index, field);
        }
        return str;
    }

    private boolean checkWordByAlphabet(String alphabet, String str) {
        Pattern pattern = Pattern.compile(alphabet);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}

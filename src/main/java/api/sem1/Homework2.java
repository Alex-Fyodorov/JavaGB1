package api.sem1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Homework2 {
    private static File log;
    private static FileWriter fileWriter;

    public static void main(String[] args) {
        //parseJson();
        crateSql();
        //sort(new int[]{9, 4, 8, 3, 1});
        //System.out.println(calculate('+', 3, 2));
    }

    /**
     * Внутри класса Answer напишите метод answer,
     * который распарсит json и, используя StringBuilder, создаст строки вида:
     * Студент [фамилия] получил [оценка] по предмету [предмет].
     */
    public static void parseJson() {
        String JSON = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
        String ELEMENT1 = "Студент ";
        String ELEMENT2 = " получил ";
        String ELEMENT3 = " по предмету ";
        answer(JSON, ELEMENT1, ELEMENT2, ELEMENT3);
    }

    public static void answer(String JSON, String ELEMENT1, String ELEMENT2, String ELEMENT3) {
        String[] array = JSON.split("},");
        for (String str : array) {
            String[] subArr = str.replace("\"", "").split(",");
            String name = subArr[0].replace("{фамилия:", "")
                    .replace("[", "");
            String score = subArr[1].replace("оценка:", "");
            String theme = subArr[2].replace("предмет:", "")
                    .replace("}]", "");
            StringBuilder sb = new StringBuilder(ELEMENT1);
            sb.append(name).append(ELEMENT2).append(score).append(ELEMENT3).append(theme);
            System.out.println(sb);
        }
    }

    /**
     * Дана строка sql-запроса:
     * select * from students where
     * Сформируйте часть WHERE этого запроса, используя StringBuilder.
     * Пример данных для фильтрации приведены ниже в виде json-строки.
     * Если значение null, то параметр не должен попадать в запрос.
     * {"name": "Ivanov", "country": "Russia", "city": "Moscow", "age": "null"}
     */
    public static void crateSql() {
        String QUERY = "select * from students where ";
        String PARAMS = "{\"name\":\"Ivanov\", \"country\":\"Russia\", " +
                "\"city\":\"Moscow\", \"age\":\"null\"}";
        System.out.println(answer(QUERY, PARAMS));
    }

    public static StringBuilder answer(String QUERY, String PARAMS) {
        StringBuilder sb = new StringBuilder(QUERY);
        String[] newParams = PARAMS.substring(1, PARAMS.length() - 1)
                .replace("\"", "").split(", ");
        for (String str : newParams) {
            String[] currentParam = str.split(":");
            if (!currentParam[1].equals("null")) {
                sb.append(currentParam[0])
                        .append("='")
                        .append(currentParam[1])
                        .append("' and ");
            }
        }
        sb.replace(sb.length() - 4, sb.length() - 1, "");
        System.out.println(sb);
        return sb;
    }

    /**
     * Реализуйте алгоритм сортировки пузырьком числового массива,
     * результат после каждой итерации запишите в лог-файл.     *
     * Напишите свой код в методе sort класса BubbleSort.
     * Метод sort принимает на вход один параметр:     *
     * int[] arr - числовой массив     *
     * После каждой итерации, ваш код должен делать запись в лог-файл
     * 'log.txt' в формате год-месяц-день час:минуты {массив на данной итерации}.
     */
    public static void sort(int[] mas) {
        log = new File("log.txt");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            fileWriter = new FileWriter(log);
            int buf, count;
            for (int i = 0; i < mas.length; i++) {
                count = 0;
                for (int j = 0; j < mas.length - i - 1; j++) {
                    if (mas[j] > mas[j + 1]) {
                        buf = mas[j];
                        mas[j] = mas[j + 1];
                        mas[j + 1] = buf;
                        count++;
                    }
                }
                fileWriter.append(format.format(date));
                fileWriter.append(" ");
                fileWriter.append(Arrays.toString(mas));
                fileWriter.append("\n");
                if (count == 0) break;
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Возьмите код от калькулятора с прошлого урока.
     * К этому калькулятору нужно добавить логирование.
     * Логи запишите в файл log.txt в формате:
     * "гггг-мм-дд чч:мм User entered the first operand = {первое число}"
     * "гггг-мм-дд чч:мм User entered the operation = {оператор}"
     * "гггг-мм-дд чч:мм User entered the second operand = {второе число}"
     * "гггг-мм-дд чч:мм Result is {результат}"
     */
    public static int calculate(char op, int a, int b) {
        int result = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (FileWriter fw = new FileWriter(new File("log.txt"))) {
            fw.append(formatter.format(LocalDateTime.now())).append(" ")
                    .append("User entered the first operand = ")
                    .append(Integer.toString(a)).append("\n");
            fw.append(formatter.format(LocalDateTime.now())).append(" ")
                    .append("User entered the operation = ").append(op).append("\n");
            fw.append(formatter.format(LocalDateTime.now())).append(" ")
                    .append("User entered the second operand = ")
                    .append(Integer.toString(b)).append("\n");
            switch (op) {
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
                case '*':
                    result = a * b;
                    break;
                case '/':
                    result = a / b;
                    break;
                default:
                    System.out.printf("Некорректный оператор: %c", op);
            }
            fw.append(formatter.format(LocalDateTime.now())).append(" ")
                    .append("Result is ").append(Integer.toString(result));
            fw.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return result;
    }
}

package exceptions.hw3.controllers;

import exceptions.hw3.exceptions.QualityDataException;
import exceptions.hw3.exceptions.QuantityDataInputException;
import exceptions.hw3.savers.Saving;
import exceptions.hw3.validators.Validating;
import exceptions.hw3.views.Viewing;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Controller {
    private final Viewing view;
    private final Validating validator;
    private final Saving saver;

    public void start() {
        List<String> inputData = view.createNote();
        String response = saver.saveData(validateInputData(inputData));
        view.printMessage(response);
    }

    private List<String> validateInputData(List<String> inputData) {
        List<String> result = null;
        boolean flag = true;
        while (flag) {
            try {
                result = validator.validate(inputData);
                flag = false;
            } catch (QuantityDataInputException e) {
                String quantity;
                if (e.getQuantity() > 0) quantity = "больше";
                else quantity = "меньше";
                view.printMessage(String.format("Количество введённых данных %s необходимого.", quantity));
                inputData = view.createNote();
            } catch (QualityDataException e) {
                inputData = view.editData(inputData, e.getIndex(), e.getField());
            }
        }
        return result;
    }
}

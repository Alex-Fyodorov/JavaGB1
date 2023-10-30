package exceptions.hw3.validators;

import exceptions.hw3.exceptions.QualityDataException;
import exceptions.hw3.exceptions.QuantityDataInputException;

import java.util.List;

public interface Validating {
    List<String> validate(List<String> inputData) throws QuantityDataInputException, QualityDataException;
}

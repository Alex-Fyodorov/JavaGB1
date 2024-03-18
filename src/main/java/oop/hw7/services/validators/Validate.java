package oop.hw7.services.validators;

import java.util.List;

public interface Validate {

    /**
     * Метод проверяет обработанные данные, полученные от пользователя на валидность.
     * @param method Номер метода.
     * @param list Обработанные данные, подготовленные для калькулятора.
     * @return true или false.
     */
    boolean validate(int method, List<?> list);
}

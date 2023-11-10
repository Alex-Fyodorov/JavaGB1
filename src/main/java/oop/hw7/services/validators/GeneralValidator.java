package oop.hw7.services.validators;

import java.util.List;

public class GeneralValidator implements Validate {

    /**
     * Метод проверяет обработанные данные, полученные от пользователя на валидность.
     * @param method Номер метода.
     * @param list Обработанные данные, подготовленные для калькулятора.
     * @return true или false.
     */
    @Override
    public boolean validate(int method, List<?> list) {
        if (method == 5 && (list.get(0) == null || list.get(1) == null)) {
            return false;
        }
        if (method < 5 && method > 0 && (
                list.get(0) == null
                        || list.get(1) == null
                        || list.get(2) == null
                        || list.get(3) == null
        )) {
            return false;
        }
        return true;
    }
}

package alex5.model;

public interface Data {
    /** sum() - Функция суммирует число numberA и numberB
     */
    String sum(Number numberA, Number numberB);
    /** subtraction() - Функция вычитает число numberA и numberB
     */
    String subtraction(Number numberA, Number numberB);
    /** division() - Функция делит число numberA на numberB
     */
    String division(Number numberA, Number numberB);
    /** multiplication() - Функции умножает число numberA на numberB
     */
    String multiplication(Number numberA, Number numberB);
    /** dateTime() - Функция показывает дату и время
     */
    String dateTime();
    /** writeHistory() - Функция записывает результаты вычислений в ArrayList
     */
    void writeHistory(String result);
    /** readHistory() - Функция читает результаты вычислений из ArrayList
     */
    void readHistory();
}

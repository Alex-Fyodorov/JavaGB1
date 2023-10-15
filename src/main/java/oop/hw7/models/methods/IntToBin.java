package oop.hw7.models.methods;

import oop.hw7.models.Request;

public class IntToBin implements ToBinMethod{

    /**
     * Принимает число в виде дроби, делит его на целую и дробную части и передаёт
     * их в другие методы для перевода в бинарную форму.
     * @param numX Числитель.
     * @param numY Знаменатель.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    @Override
    public String toBin(Integer numX, Integer numY, Request request) {
        double number = numX.doubleValue()/numY;
        int intNum = (int) number;
        double fractionNum = number - (double) intNum;
        return request.getNumX() + " = " + ((double) intToBin(intNum) + fractionToBin(fractionNum));
    }

    /**
     * Принимает целое число и переводит его в бинарный вид.
     * @param num Входящее число.
     * @return Число в бинарном представлении.
     */
    private long intToBin(int num) {
        int numAbs = Math.abs(num);
        long index = 1;
        long result = 0;
        while (numAbs > 0) {
            result += (numAbs % 2) * index;
            numAbs /= 2;
            index *= 10;
        }
        return (num / Math.abs(num)) * result;
    }

    /**
     * Принимает число от -1 до 1 и переводит его в бинарный вид.
     * @param num Входящее число.
     * @return Число в бинарном представлении.
     */
    private double fractionToBin(double num) {
        double numAbs = Math.abs(num);
        double index = 0.1;
        double result = 0d;
        while (numAbs != 0 || index > 0.000000000001) {
            numAbs *= 2;
            if (numAbs >= 1) {
                numAbs -= 1;
                result += index;
            }
            index /= 10;
        }
        return (num / Math.abs(num)) * result;
    }
}

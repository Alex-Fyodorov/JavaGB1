package oop.sem7.dop.calculator;

import javax.swing.*;

public class LogCalculator implements Calculable {
    private Logable logger;
    private Calculable calculator;

    public LogCalculator(Calculable calculator, Logable logger) {
        this.logger = logger;
        this.calculator = calculator;
    }

    @Override
    public Calculable sum(int arg) {
        logger.log(String.format("Сложение старого значения %d с новым %d",
                calculator.getResult(), arg));
        return calculator.sum(arg);
    }

    @Override
    public Calculable multi(int arg) {
        logger.log(String.format("Умножение старого значения %d на новое %d",
                calculator.getResult(), arg));
        return calculator.multi(arg);
    }

    @Override
    public int getResult() {
        return calculator.getResult();
    }
}

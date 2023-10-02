package oop.sem3.task1;

public enum Month {
    JAN(1),
    FEB(2),
    MAR(3),
    APR(4),
    MAY(5),
    JUN(6),
    JUL(7),
    AUG(8),
    SEP(9),
    OCT(10),
    NOV(11),
    DEC(12);

    private int numberMonth;

    Month(int numberMonth) {
        this.numberMonth = numberMonth;
    }

    Month() {
    }

    public int getNumberMonth() {
        return numberMonth;
    }
}

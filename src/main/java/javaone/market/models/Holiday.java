package javaone.market.models;

import java.time.LocalDate;

public enum Holiday {
    NEW_YEAR (LocalDate.of(2001, 1, 1), null),
    DEFENDER_OF_THE_FATHERLAND_DAY (LocalDate.of(2001, 2, 23), Sex.MALE),
    INTERNATIONAL_WOMENS_DAY (LocalDate.of(2001, 3, 8), Sex.FEMALE),
    SPRING_AND_LABOR_DAY (LocalDate.of(2001, 5, 1), null),
    VICTORY_DAY (LocalDate.of(2001, 5, 9), null),
    DAY_OF_RUSSIA (LocalDate.of(2001, 6, 12), null),
    NATIONAL_UNITY_DAY (LocalDate.of(2001, 11, 4), null);

    private final LocalDate date;
    private final Sex sex;

    Holiday(LocalDate date, Sex sex) {
        this.date = date;
        this.sex = sex;
    }

    public LocalDate getDate() {
        return date;
    }

    public Sex getSex() {
        return sex;
    }
}

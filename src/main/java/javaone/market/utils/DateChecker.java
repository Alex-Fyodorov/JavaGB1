package javaone.market.utils;

import javaone.market.models.Holiday;
import javaone.market.models.User;

import java.time.LocalDate;

public class DateChecker {

    public float checkDateOfOrder(LocalDate date, User user) {
        float ratio = 1f;
        int day = getDayOfYear(date);
        if (day >= getDayOfHoliday(Holiday.NEW_YEAR) && day <= getDayOfHoliday(Holiday.NEW_YEAR) + 8) {
            ratio -= 0.2f;
        } else if (day == getDayOfHoliday(Holiday.DEFENDER_OF_THE_FATHERLAND_DAY) &&
                Holiday.DEFENDER_OF_THE_FATHERLAND_DAY.getSex().equals(user.getSex())) {
            ratio -= 0.15f;
        } else if (day == getDayOfHoliday(Holiday.INTERNATIONAL_WOMENS_DAY) &&
                Holiday.INTERNATIONAL_WOMENS_DAY.getSex().equals(user.getSex())) {
            ratio -= 0.15f;
        } else  if (day == getDayOfHoliday(Holiday.SPRING_AND_LABOR_DAY) ||
                day == getDayOfHoliday(Holiday.VICTORY_DAY) ||
                day == getDayOfHoliday(Holiday.DAY_OF_RUSSIA) ||
                day == getDayOfHoliday(Holiday.NATIONAL_UNITY_DAY)) {
            ratio -= 0.1f;
        }
        if (day == getDayOfYear(user.getBirthDate())) {
            ratio -= 0.15f;
        }
        return ratio;
    }

    private int getDayOfHoliday(Holiday holiday) {
        return holiday.getDate().getDayOfYear();
    }

    // Поправка на високосный год.
    private int getDayOfYear(LocalDate date) {
        if (date.getYear() % 4 == 0 && date.getDayOfYear() > 59) {
            return date.getDayOfYear() - 1;
        }
        return date.getDayOfYear();
    }
}

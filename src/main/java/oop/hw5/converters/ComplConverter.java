package oop.hw5.converters;

import oop.hw5.models.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplConverter implements Convertering<Double> {

    @Override
    public List<Double> requestToNumbers(Request request) {
        List<Double> list = new ArrayList<>();
        for (String number : Arrays.asList(request.getNumX(), request.getNumY())) {
            List<Double> response = stringToNumbers(number);
            if (!response.isEmpty()) {
                list.add(response.get(0));
                list.add(response.get(1));
            } else {
                list.add(null);
                list.add(null);
            }
        }
        return list;
    }

    private List<Double> stringToNumbers(String str) {
        List<Double> list = new ArrayList<>();
        str = str.strip();
        str = str.substring(0, str.length() - 2);
        int index = 0;
        StringBuilder sb = new StringBuilder();
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            index = 1;
            sb.append(str.charAt(0));
        }
        for (int i = index; i < str.length(); i++) {
            if ((str.charAt(i) == '-' || str.charAt(i) == '+')) {
                list.add(isDouble(sb.toString()));
                sb = new StringBuilder();
            }
            sb.append(str.charAt(i));
        }
        list.add(isDouble(sb.toString()));
        return list;
    }

    private Double isDouble(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

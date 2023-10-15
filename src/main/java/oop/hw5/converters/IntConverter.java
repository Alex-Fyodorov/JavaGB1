package oop.hw5.converters;

import oop.hw5.models.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntConverter implements Convertering<Integer> {

    @Override
    public List<Integer> requestToNumbers(Request request) {
        List<Integer> list = new ArrayList<>();
        for (String number : Arrays.asList(request.getNumX(), request.getNumY())) {
            List<Integer> response = stringToNumbers(number);
            if (response != null && !response.isEmpty()) {
                list.add(response.get(0));
                list.add(response.get(1));
            } else {
                list.add(null);
                list.add(null);
            }
        }
        return list;
    }

    private List<Integer> stringToNumbers(String str) {
        if (str == null) return null;
        if (str.contains("/")) return forFraction(str);
        if (isInteger(str.strip())) return forInteger(str.strip());
        if (isDouble(str.strip())) return forDouble(str.strip());
        return null;
    }

    private List<Integer> forFraction(String str) {
        List<Integer> list = new ArrayList<>();
        int index = str.indexOf("/");
        if (index == 0 || index == str.length() - 1) {
            return list;
        }
        String a = str.substring(0, index).strip();
        String b = str.substring(index + 1).strip();
        if (isInteger(a) && isInteger(b)) {
            list.add(Integer.parseInt(a));
            list.add(Integer.parseInt(b));
            return list;
        }
        return list;
    }

    private List<Integer> forInteger(String str) {
        List<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(str));
        list.add(1);
        return list;
    }

    private List<Integer> forDouble(String str) {
        List<Integer> list = new ArrayList<>();
        int index = 1;
        double num = Double.parseDouble(str);
        while (num * index != (int) (num * index)) {
            index *= 10;
        }
        list.add((int) (num * index));
        list.add(index);
        return list;
    }

    private boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

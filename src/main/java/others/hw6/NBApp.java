package others.hw6;

import java.util.*;

public class NBApp {
    public static void main(String[] args) {
        NoteBookSet noteBookSet = new NoteBookSet();
        toFillSet(noteBookSet, 1000);
        Scanner scanner = new Scanner(System.in);
        noteBookSet.printSize();
        System.out.println();
        while (true) {
            System.out.println("Введите:");
            System.out.println("1 - выбрать ноутбук.");
            System.out.println("2 - выход.");
            String in = scanner.nextLine();
            if (in.equals("1")) {
                System.out.println(noteBookSet.filterAll(createParams()));
                System.out.println("Нажмите \"y\", чтобы продолжить.");
                if (!scanner.nextLine().equals("y")) {
                    System.out.println("Всего хорошего! Заходите в наш магазин снова. :)");
                    break;
                }
            }
            if (in.equals("2")) {
                System.out.println("Всего хорошего! Заходите в наш магазин снова. :)");
                break;
            }
        }
    }

    public static void toFillSet(NoteBookSet noteBookSet, int num) {
        Random random = new Random();
        List<?> brands = returnList(1);
        List<?> colors = returnList(2);
        List<?> opSystems = returnList(3);
        List<?> rams = returnList(4);
        List<?> ssds = returnList(5);
        for (int i = 0; i < num; i++) {
            String brand = (String) brands.get(random.nextInt(brands.size()));
            String color = (String) colors.get(random.nextInt(colors.size()));
            String os = (String) opSystems.get(random.nextInt(opSystems.size()));
            Integer ram = (Integer) rams.get(random.nextInt(rams.size()));
            Integer ssd = (Integer) ssds.get(random.nextInt(ssds.size()));
            noteBookSet.add(new NoteBook(brand, ram, ssd, os, color));
        }
    }

    public static Map<String, String> createParams() {
        Map<String, String> params = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i < 6; i++) {
            String str;
            List<?> list;
            do {
                System.out.println(returnString(i));
                System.out.println("0 - Пропустить");
                list = returnList(i);
                for (int j = 0; j < list.size(); j++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(j + 1).append(" - ").append(list.get(j));
                    System.out.println(sb);
                }
                str = scanner.nextLine();
            } while (!isNumeric(str)
                    || 0 > Integer.parseInt(str)
                    || Integer.parseInt(str) > list.size());
            if (Integer.parseInt(str) != 0) {
                params.put(returnKey(i), list.get(Integer.parseInt(str) - 1).toString());
            }
        }
        return params;
    }

    public static List<?> returnList(int number) {
        return switch (number) {
            case 1 -> new ArrayList<>(List.of("Xiaomi", "Apple", "Samsung", "Philips", "Lenovo"));
            case 2 -> new ArrayList<>(List.of("Black", "White", "Grey", "Red", "Blue"));
            case 3 -> new ArrayList<>(List.of("Windows", "macOS", "Linux", "Android"));
            case 4 -> new ArrayList<>(List.of(4, 8, 12, 16, 24));
            case 5 -> new ArrayList<>(List.of(128, 256, 512, 1024));
            default -> null;
        };
    }

    public static String returnString(int number) {
        return switch (number) {
            case 1 -> "Выберите бренд ноутбука:";
            case 2 -> "Выберите цвет ноутбука:";
            case 3 -> "Выберите операционную систему:";
            case 4 -> "Введите минимальный объём оперативной памяти (8-24):";
            case 5 -> "Введите минимальный размер жёсткого диска (128 - 1024):";
            default -> null;
        };
    }

    public static String returnKey(int number) {
        return switch (number) {
            case 1 -> "brand";
            case 2 -> "color";
            case 3 -> "os";
            case 4 -> "ram";
            case 5 -> "hdCapacity";
            default -> null;
        };
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

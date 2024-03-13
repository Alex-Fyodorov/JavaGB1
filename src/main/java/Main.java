import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while (!str.equals("END")){
            System.out.println(str);
            str = scanner.nextLine();
        }
    }
}

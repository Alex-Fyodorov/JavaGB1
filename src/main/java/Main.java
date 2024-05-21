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

// java -cp target/JavaGB1-1.0-SNAPSHOT.jar javatwo.hw6.MontyHallProblem


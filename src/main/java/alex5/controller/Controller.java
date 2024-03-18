package alex5.controller;

import alex5.service.Operations;
import alex5.view.Text;

import java.util.Scanner;

public class Controller {

    /** menu() - Функция запускает меню с выбором
     *  вариантов вычислений с целыми, дробными или комплексными числами
     */
    public void menu() {

        Scanner cs = new Scanner(System.in);
        Operations operations = new Operations();

        boolean flag = true;
        while (flag) {
            Text.welcome();

            int num = cs.nextInt();


            switch (num) {
                case 1:
                    operations.subMenu();
                    break;
                case 2:
                    operations.subMenuComplex();
                    break;
                case 3:
                    operations.functions.readHistory();
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    Text.noThatNumber();
                    break;
            }

        }

    }
}

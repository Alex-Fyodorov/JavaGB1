package oop.alex5.service;

import oop.alex5.model.Functions;
import oop.alex5.view.CalculationView;
import oop.alex5.view.Text;

import java.util.Scanner;


public class Operations {
    public Functions functions = new Functions();

     /*static*/ CalculationView print = new CalculationView();



    /** subMenu() - Функция предоставляет выбрать операцию с числами
     /* (сложение, вычитание, умножение, деление)
     */
    public void subMenu() {
        Scanner cs = new Scanner(System.in);
        Text.chooseOperation();
        Text.allOperations();

        int oper = cs.nextInt();
        cs.nextLine();
        switch (oper) {
            case 1:

                Text.sumAB();
                Text.inputNumberA();
                double numA = cs.nextInt();
                Text.inputNumberB();
                double numB = cs.nextInt();
                functions.writeHistory(functions.dateTime() + functions.sum(numA, numB));
                print.printResult(functions.sum(numA, numB));
                Text.loadedToHistory();
                break;
            case 2:

                Text.subtractionAB();
                Text.inputNumberA();
                numA = cs.nextInt();
                Text.inputNumberB();
                numB = cs.nextInt();
                functions.writeHistory(functions.dateTime() + functions.subtraction(numA, numB));
                print.printResult(functions.subtraction(numA, numB));
                Text.loadedToHistory();
                break;
            case 3:

                Text.multiplicationAB();
                Text.inputNumberA();
                numA = cs.nextInt();
                Text.inputNumberB();
                numB = cs.nextInt();
                functions.writeHistory(functions.dateTime() + functions.multiplication(numA, numB));
                print.printResult(functions.multiplication(numA, numB));
                Text.loadedToHistory();
                break;
            case 4:

                Text.divisionAB();
                Text.inputNumberA();
                numA = cs.nextInt();
                Text.inputNumberB();
                numB = cs.nextInt();
                functions.writeHistory(functions.dateTime() + functions.division(numA, numB));
                print.printResult(functions.division(numA, numB));
                Text.loadedToHistory();
                break;
            default:
                Text.noThatNumber();
                break;
        }

    }
    /** subMenu() - Функция предоставляет выбрать операцию с комплексными числами
     /* (сложение, вычитание, умножение, деление)
     */
    public void subMenuComplex() {
        Scanner cs = new Scanner(System.in);
        Text.chooseComplexOperation();
        Text.allOperations();

        int oper = cs.nextInt();

        switch (oper) {
            case 1:

                Text.complexSumAB();
                Text.inputNumberA();
                int numA = cs.nextInt();
                Text.inputNumberB();
                int numB = cs.nextInt();

                Text.inputNumberA2();
                int numA2 = cs.nextInt();
                Text.inputNumberB2();
                int numB2 = cs.nextInt();

                functions.writeHistory(functions.dateTime() + functions.sumComplex(numA, numB, numA2, numB2));
                print.printResult(functions.sumComplex(numA, numB, numA2, numB2));
                Text.loadedToHistory();
                break;
            case 2:

                Text.complexSubtractionAB();
                Text.inputNumberA();
                numA = cs.nextInt();
                Text.inputNumberB();
                numB = cs.nextInt();

                Text.inputNumberA2();
                numA2 = cs.nextInt();
                Text.inputNumberB2();
                numB2 = cs.nextInt();

                functions.writeHistory(functions.dateTime() + functions.subtractionComplex(numA, numB, numA2, numB2));
                print.printResult(functions.subtractionComplex(numA, numB, numA2, numB2));
                Text.loadedToHistory();
                break;
            case 3:

                Text.complexMultiplicationAB();
                Text.inputNumberA();
                numA = cs.nextInt();
                Text.inputNumberB();
                numB = cs.nextInt();

                Text.inputNumberA2();
                numA2 = cs.nextInt();
                Text.inputNumberB2();
                numB2 = cs.nextInt();

                functions.writeHistory(functions.dateTime() + functions.multiplicationComplex(numA, numB, numA2, numB2));
                print.printResult(functions.multiplicationComplex(numA, numB, numA2, numB2));
                Text.loadedToHistory();
                break;
            case 4:

                Text.complexDivisionAB();
                Text.inputNumberA();
                numA = cs.nextInt();
                Text.inputNumberB();
                numB = cs.nextInt();

                Text.inputNumberA2();
                numA2 = cs.nextInt();
                Text.inputNumberB2();
                numB2 = cs.nextInt();

                functions.writeHistory(functions.dateTime() + functions.divisionComplex(numA, numB, numA2, numB2));
                print.printResult(functions.divisionComplex(numA, numB, numA2, numB2));
                Text.loadedToHistory();
                break;
            default:
                Text.noThatNumber();
                break;
        }

    }
}

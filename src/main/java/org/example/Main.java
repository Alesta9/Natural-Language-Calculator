package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String[] numbers = {
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a calculation: ");
        String calculationString = scanner.nextLine();

        String[] expressions = calculationString.toLowerCase().split(" ");

        for (int i = 0; i < expressions.length; i++) {
            if (expressions[i].equals("over")) {
                String beforeOver = expressions[i-1];
                String afterOver = expressions[i+1];
                double calculatedDecimalNum = calculateDecimalNum(getNumber(beforeOver),getNumber(afterOver));
                expressions[i-1] = String.valueOf(calculatedDecimalNum);
                expressions = removeElement(expressions,i);
                expressions = removeElement(expressions,i);
            }
        }


        ArrayList<Object> mixedArray = new ArrayList<>();

        for(int i = 0;i < expressions.length ;i++) {
            if(i % 2 == 1) {
                mixedArray.add(getOperator(expressions[i]));
            }else {
                if(expressions[i].contains(".")) {
                    mixedArray.add(Double.parseDouble(expressions[i]));
                }else {
                    mixedArray.add(getNumber(expressions[i]));
                }
            }
        }


        mixedArray = extractMultiplyOrDivide(mixedArray);

        System.out.println(calculateResult(mixedArray));


    }
    public static String[] removeElement(String[] array, int index) {
        if (index < 0 || index >= array.length) {
            return array;
        }

        String[] newArray = new String[array.length - 1];

        System.arraycopy(array, 0, newArray, 0, index);

        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);

        return newArray;
    }
    public static double calculateDecimalNum(double num1,double num2) {
        return num1 / num2;
    }


    public static ArrayList<Object> extractMultiplyOrDivide(ArrayList<Object> mixedArray) {
        while(expressionsHasMultiplyOrDivide(mixedArray)) {


            for(int i = 2;i < mixedArray.size(); i++) {

                char leftOperator = (char) mixedArray.get(i-1);
                if(mixedArray.size() == 3) {
                    double newNumber = calculate((double) mixedArray.get(i-2),(double) mixedArray.get(i),leftOperator);
                    mixedArray = fixArray(mixedArray,i-2, newNumber);
                    break;
                } else {
                    if (i + 1 < mixedArray.size()) {
                        char rightOperator = (char) mixedArray.get(i + 1);

                        if (leftOperator == '*' || leftOperator == '/') {

                            double newNumber = calculate((double) mixedArray.get(i - 2), (double) mixedArray.get(i), leftOperator);

                            mixedArray = fixArray(mixedArray, i - 2, newNumber);
                            break;
                        } else if (rightOperator == '*' || rightOperator == '/') {

                            double newNumber = calculate((double) mixedArray.get(i), (double) mixedArray.get(i + 2), rightOperator);
                            mixedArray = fixArray(mixedArray, i, newNumber);
                            break;
                        }
                    }
                    if (leftOperator == '*' || leftOperator == '/') {
                        double newNumber = calculate((double) mixedArray.get(i), (double) mixedArray.get(i + 2), leftOperator);
                        mixedArray = fixArray(mixedArray, i - 2, newNumber);
                        break;
                    }
                }
                i++;
            }
        }
        return mixedArray;
    }

    public static double calculateResult(ArrayList<Object> mixedArray) {

        double total = (double) mixedArray.get(0);

        for(int i = 0 ; i < mixedArray.size() ; i++) {

            if(i+1 < mixedArray.size()) {

                if((char) mixedArray.get(i+1) == '+') {
                    total += (double) mixedArray.get(i+2);
                } else {
                    total -= (double) mixedArray.get(i+2);
                }
            }
            i++;
        }
        return total;
    }
    public static ArrayList<Object> fixArray(ArrayList<Object> array,int removeIndex,Double newElement) {

        array.remove(removeIndex);
        array.remove(removeIndex);
        array.remove(removeIndex);

        array.add(removeIndex, newElement);


        return array;

    }

    public static boolean expressionsHasMultiplyOrDivide(ArrayList<Object> mixedArray) {
        for(int i = 0 ; i < mixedArray.size() ; i++) {

            if(String.valueOf(mixedArray.get(i)).equals("*") || String.valueOf(mixedArray.get(i)).equals("/"))  {

                return true;
            }
        }
        return false;
    }

    public static double calculate(double number1,double number2,char operator) {
        if(operator == '+') {
            return number1 + number2;
        } else if (operator == '-') {
            return number1 - number2;
        } else if (operator == '*') {
            return number1 * number2;
        } else if (operator == '/') {
            return number1 / number2;
        }
        return 0;
    }
    public static char getOperator(String operator) {

        if(operator.equals("add") || operator.equals("plus")) {
            return '+';
        }else if (operator.equals("subtract") || operator.equals("minus") || operator.equals("less")){
            return '-';
        }else if (operator.equals("multiplied-by") || operator.equals("times")) {
            return '*';
        }else if (operator.equals("divided-by") || operator.equals("overCONFIDENTIAL")){
            return '/';
        } else {
            return 'N';
        }

    }

    public static double getNumber(String number) {
        for(int i = 0;i < numbers.length;i++) {
            if(numbers[i].equals(number)) {
                return i+1;
            }
        }
        return 0;
    }



}
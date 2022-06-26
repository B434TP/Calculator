package ru.osa.gb.homework.calculator.model;

public class CalculatorImpl implements Calculator {
    @Override
    public double doOperation(double firstArg, double secondArg, Operations operation) {

        double result;

        switch (operation) {
            case PLUS:
                result = firstArg + secondArg;
                return result;
            case MINUS:
                result = firstArg - secondArg;
                return result;
            case DIVIDE:
                result = firstArg / secondArg;
                return result;
            case MULTIPLY:
                result = firstArg * secondArg;
                return result;
        }
        return 0.0;
    }
}

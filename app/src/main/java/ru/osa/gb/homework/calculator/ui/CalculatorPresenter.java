package ru.osa.gb.homework.calculator.ui;

import android.util.Log;

import java.text.DecimalFormat;

import ru.osa.gb.homework.calculator.model.Calculator;
import ru.osa.gb.homework.calculator.model.Operations;

public class CalculatorPresenter {

    private Double firstArg;
    private Double secondArg;
    private Operations lastOperation;
    private Double onDisplay;
    private Boolean dotIsPressed;
    private Boolean operationIsPressed;
    private CalculatorView calculatorView;
    private Calculator calculator;


    public CalculatorPresenter(CalculatorView calculatorView, Calculator calculator) {
        this.calculatorView = calculatorView;
        this.calculator = calculator;
        initCalc();
    }

    private void initCalc() {
        firstArg = null;
        secondArg = null;
        dotIsPressed = false;
        operationIsPressed = false;
        onDisplay = 0.0;
        sh();
    }

    private void sh() {
        calculatorView.showOnDisplay(onDisplay);
        Log.d("onDisplay", String.valueOf(onDisplay));

    }

    public void onDigitPress(DigitKeys key) {

        if (!operationIsPressed) {
            if (firstArg == null) {
                firstArg = getIntFromKey(key);
            } else
                firstArg = 10 * firstArg + getIntFromKey(key);
            onDisplay = firstArg;
        } else {
            if (secondArg == null) {
                secondArg = getIntFromKey(key);
            } else
                secondArg = 10 * secondArg + getIntFromKey(key);
            onDisplay = secondArg;
        }
        sh();

    }


    public void onOperationKeyPress(Operations key) {
        onResultKeyPress();
        lastOperation = key;
        operationIsPressed = true;
        Log.d("KeyPress", key.toString());
    }

    public void onResultKeyPress() {
        Log.d("KeyPress", "RESULT");
        if (firstArg != null && secondArg != null) {
            onDisplay = calculator.doOperation(firstArg, secondArg, lastOperation);
            sh();
            firstArg = onDisplay;
            secondArg = null;
            dotIsPressed = false;
            operationIsPressed = false;
        }
    }

    public void onDotKeyPress() {
        dotIsPressed = true;
        Log.d("KeyPress", "DOT");
    }

    public void onCAKeyPress() {
        initCalc();
        Log.d("KeyPress", "CA");
    }

    public void onBackSpaceKeyPress() {
        Log.d("KeyPress", "BACKSPACE");
        String val = String.valueOf(onDisplay);
    }


    private double getIntFromKey(DigitKeys key) {
        Log.d("KeyPress", key.toString());
        switch (key) {
            case KEY1:
                return 1;
            case KEY2:
                return 2;
            case KEY3:
                return 3;
            case KEY4:
                return 4;
            case KEY5:
                return 5;
            case KEY6:
                return 6;
            case KEY7:
                return 7;
            case KEY8:
                return 8;
            case KEY9:
                return 9;
            case KEY0:
                return 0;
        }
        return 0.0;

    }


}

package ru.osa.gb.homework.calculator.ui;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import ru.osa.gb.homework.calculator.model.Calculator;
import ru.osa.gb.homework.calculator.model.Operations;

public class CalculatorPresenter implements Parcelable {

    private Double firstArg;
    private Double secondArg;
    private Operations lastOperation;
    private String textOnDisplay;
    private Boolean dotIsPressed;
    private int afterDotCount;
    private Boolean operationIsPressed;
    private CalculatorView calculatorView;
    private Calculator calculator;

    public void setCalculatorView(CalculatorView calculatorView) {
        this.calculatorView = calculatorView;
    }

    public CalculatorPresenter(CalculatorView calculatorView, Calculator calculator) {
        this.calculatorView = calculatorView;
        this.calculator = calculator;
        initCalc();
    }

    protected CalculatorPresenter(Parcel in) {
        if (in.readByte() == 0) {
            firstArg = null;
        } else {
            firstArg = in.readDouble();
        }
        if (in.readByte() == 0) {
            secondArg = null;
        } else {
            secondArg = in.readDouble();
        }
        textOnDisplay = in.readString();
        byte tmpDotIsPressed = in.readByte();
        dotIsPressed = tmpDotIsPressed == 0 ? null : tmpDotIsPressed == 1;
        afterDotCount = in.readInt();
        byte tmpOperationIsPressed = in.readByte();
        operationIsPressed = tmpOperationIsPressed == 0 ? null : tmpOperationIsPressed == 1;
    }

    public static final Creator<CalculatorPresenter> CREATOR = new Creator<CalculatorPresenter>() {
        @Override
        public CalculatorPresenter createFromParcel(Parcel in) {
            return new CalculatorPresenter(in);
        }

        @Override
        public CalculatorPresenter[] newArray(int size) {
            return new CalculatorPresenter[size];
        }
    };

    private void initCalc() {
        firstArg = null;
        secondArg = null;
        dotIsPressed = false;
        afterDotCount = 0;
        operationIsPressed = false;
        textOnDisplay = "";
        sh();
    }

    public void sh() {
        textOnDisplay = calculatorView.showOnDisplay(textOnDisplay);
        Log.d("onDisplay", String.valueOf(textOnDisplay));

    }

    public void onDigitPress(DigitKeys key) {

        if (!operationIsPressed) {
            if (firstArg == null) {
                if (!dotIsPressed) {
                    firstArg = (double) getNumberFromKey(key);
                } else {
                    firstArg = (double) getNumberFromKey(key) / 10;
                }
            } else if (!dotIsPressed) {
                firstArg = 10 * firstArg + getNumberFromKey(key);
            } else {
                firstArg = putDigitAfterDot(firstArg, getNumberFromKey(key));
            }
            textOnDisplay = String.valueOf(firstArg);
        } else {
            if (secondArg == null) {
                secondArg = (double) getNumberFromKey(key);
            } else
                secondArg = 10 * secondArg + getNumberFromKey(key);
            textOnDisplay = String.valueOf(secondArg);
        }
        sh();

    }


    public void onOperationKeyPress(Operations key) {
        onResultKeyPress();
        lastOperation = key;
        operationIsPressed = true;
        dotIsPressed = false;
        afterDotCount = 0;
        Log.d("KeyPress", key.toString());
    }

    public void onResultKeyPress() {
        Log.d("KeyPress", "RESULT");
        if (firstArg != null && secondArg != null) {
            firstArg  = calculator.doOperation(firstArg, secondArg, lastOperation);
            Log.d("KeyPressRes-FirstArg", String.valueOf(firstArg));
            Log.d("KeyPressRes-SecondArg", String.valueOf(secondArg));

            textOnDisplay = String.valueOf(firstArg);
            sh();
            secondArg = null;
            dotIsPressed = false;
            afterDotCount = 0;
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
//        textOnDisplay = ;
    }

    private double putDigitAfterDot(Double in, int digit) {
        if (in == null) {
            return (double) digit;
        }

        String strIn = String.valueOf(in);
        Log.d("DOT-in", strIn);

        if (in * 10 == in) {
            strIn = strIn.substring(0, strIn.length()-1);
        }

        String strOut = strIn + digit;
        double out;
        out = Double.valueOf(strOut);

        Log.d("DOT-out", strOut);
        return out;
    }


    private int getNumberFromKey(DigitKeys key) {
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
        return 0;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (firstArg == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(firstArg);
        }
        if (secondArg == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(secondArg);
        }
        parcel.writeString(textOnDisplay);
        parcel.writeByte((byte) (dotIsPressed == null ? 0 : dotIsPressed ? 1 : 2));
        parcel.writeInt(afterDotCount);
        parcel.writeByte((byte) (operationIsPressed == null ? 0 : operationIsPressed ? 1 : 2));
    }
}

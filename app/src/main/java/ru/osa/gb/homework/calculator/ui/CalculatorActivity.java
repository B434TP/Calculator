package ru.osa.gb.homework.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import ru.osa.gb.homework.calculator.R;
import ru.osa.gb.homework.calculator.model.CalculatorImpl;
import ru.osa.gb.homework.calculator.model.Operations;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView displayView;
    private CalculatorPresenter calculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        displayView = findViewById(R.id.display);
        calculatorPresenter = new CalculatorPresenter(this, new CalculatorImpl());

        setAllListeners();

    }

    private void setAllListeners() {
        findViewById(R.id.key1).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY1));
        findViewById(R.id.key2).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY2));
        findViewById(R.id.key3).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY3));
        findViewById(R.id.key4).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY4));
        findViewById(R.id.key5).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY5));
        findViewById(R.id.key6).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY6));
        findViewById(R.id.key7).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY7));
        findViewById(R.id.key8).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY8));
        findViewById(R.id.key9).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY9));
        findViewById(R.id.key0).setOnClickListener(view -> calculatorPresenter.onDigitPress(DigitKeys.KEY0));

        findViewById(R.id.keyPlus).setOnClickListener(view -> calculatorPresenter.onOperationKeyPress(Operations.PLUS));
        findViewById(R.id.keyMinus).setOnClickListener(view -> calculatorPresenter.onOperationKeyPress(Operations.MINUS));
        findViewById(R.id.keyDivide).setOnClickListener(view -> calculatorPresenter.onOperationKeyPress(Operations.DIVIDE));
        findViewById(R.id.keyMultiply).setOnClickListener(view -> calculatorPresenter.onOperationKeyPress(Operations.MULTIPLY));

        findViewById(R.id.keyDot).setOnClickListener(view -> calculatorPresenter.onDotKeyPress());
        findViewById(R.id.keyClearAll).setOnClickListener(view -> calculatorPresenter.onCAKeyPress());
        findViewById(R.id.keyBackSpace).setOnClickListener(view -> calculatorPresenter.onBackSpaceKeyPress());

        findViewById(R.id.keyResult).setOnClickListener(view -> calculatorPresenter.onResultKeyPress());
    }

    @Override
    public void showOnDisplay(double result) {
        displayView.setText(String.valueOf(result));
    }
}
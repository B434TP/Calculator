package ru.osa.gb.homework.calculator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import ru.osa.gb.homework.calculator.R;
import ru.osa.gb.homework.calculator.model.CalculatorImpl;
import ru.osa.gb.homework.calculator.model.Operations;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView displayView;
    private CalculatorPresenter calculatorPresenter;
    private final int DISPLAY_LENGTH = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        displayView = findViewById(R.id.display);


        if (savedInstanceState != null) {
            calculatorPresenter = savedInstanceState.getParcelable("calculatorPresenter");
            calculatorPresenter.setCalculatorView(this);
            calculatorPresenter.sh();
        } else {
            calculatorPresenter = new CalculatorPresenter(this, new CalculatorImpl());
        }

        setAllListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("calculatorPresenter", calculatorPresenter);
        Log.d("Act", "onSaveInstanceState: done");
        Log.d("Act", outState.getParcelable("calculatorPresenter").toString());
        super.onSaveInstanceState(outState);

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
    public String showOnDisplay(String result) {
        if (result.length() > DISPLAY_LENGTH) {
            result = result.substring(0, DISPLAY_LENGTH);
        }
        displayView.setText(result);
        return result;
    }
}
package com.poketusk.wincalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

enum OPERATION {
    NONE,
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION
}

public class MainActivity extends AppCompatActivity {

    public String result = "0";
    public String firstNum ="0";
    public String secondNum = "0";
    public boolean isCalculated = false;
    public boolean startedTypingSecNum = false;
    public char operationSign = ' ';
    public OPERATION operation = OPERATION.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void update() {
        String finalText;
        finalText = String.valueOf(firstNum);
        if(operation != OPERATION.NONE) {
            finalText += operationSign;
            finalText += "\n";
            finalText += String.valueOf(secondNum);
        }
        if(isCalculated) {
            finalText += "=\n";
            finalText += result;
        }
        TextView screenViev =findViewById(R.id.textView);
        screenViev.setText(finalText);
    }

    public  void restart()
    {
        firstNum = "0";
        secondNum = "0";
        isCalculated = false;
        operation = OPERATION.NONE;
        startedTypingSecNum = false;
    }

    public void calculate()
    {
        switch(operation) {
            case ADDITION:
                result = String.valueOf(new BigDecimal(firstNum).add(new BigDecimal(secondNum)));
                isCalculated = true;
                break;
            case SUBTRACTION:
                result = String.valueOf(new BigDecimal(firstNum).subtract(new BigDecimal(secondNum)));
                isCalculated = true;
                break;
            case MULTIPLICATION:
                result = String.valueOf(new BigDecimal(firstNum).multiply(new BigDecimal(secondNum)));
                isCalculated = true;
                break;
            case DIVISION:
                if(new BigDecimal(secondNum).compareTo(new BigDecimal(0))!=0) {
                    result = String.valueOf(new BigDecimal(firstNum).divide(new BigDecimal(secondNum), 10, RoundingMode.HALF_UP));
                }
                else {
                    result = "ERROR";
                }
                isCalculated = true;
                break;
        }
        update();
    }

    public void addNumberAtTheEnd(int number) {
        if(!isCalculated) {
            if (operation == OPERATION.NONE) {
                if (firstNum.equals("0")) {
                    firstNum = String.valueOf(number);
                } else {
                    firstNum += String.valueOf(number);
                }
            } else {
                if (!startedTypingSecNum) {
                    startedTypingSecNum = true;
                }
                if (secondNum.equals("0")) {
                    secondNum = String.valueOf(number);
                } else {
                    secondNum += String.valueOf(number);
                }
            }
        }
        else
        {
            restart();
            firstNum = String.valueOf(number);
        }
        update();

    }
        void nextCalculation(OPERATION newOperation) {
            calculate();
            String newFNum = result;
            restart();
            firstNum = newFNum;
            operation = newOperation;
            update();
        }
    //methods used, when a button is pressed

    public void num0(View view) {
        addNumberAtTheEnd(0);
    }

    public void num1(View view) {
        addNumberAtTheEnd(1);
    }

    public void num2(View view) {
        addNumberAtTheEnd(2);
    }

    public void num3(View view) {
        addNumberAtTheEnd(3);
    }

    public void num4(View view) {
        addNumberAtTheEnd(4);
    }

    public void num5(View view) {
        addNumberAtTheEnd(5);
    }

    public void num6(View view) {
        addNumberAtTheEnd(6);
    }

    public void num7(View view) {
        addNumberAtTheEnd(7);
    }

    public void num8(View view) {
        addNumberAtTheEnd(8);
    }

    public void num9(View view) {
        addNumberAtTheEnd(9);
    }

    public void equal(View view) {
        calculate();
    }

    public void changeSign(View view) {
        if(operation == OPERATION.NONE) {
            if(!firstNum.equals("0")) {
                if (firstNum.charAt(0) == '-') {
                    firstNum = firstNum.substring(1);
                } else {
                    firstNum = '-' + firstNum;
                }
            }
        }
        else{
            if(!secondNum.equals("0")) {
                if (secondNum.charAt(0) == '-') {
                    secondNum = secondNum.substring(1);
                } else {
                    secondNum = '-' + secondNum;
                }
            }
        }
        update();
    }

    public void decPoint(View view) {
        if(operation == OPERATION.NONE) {
            firstNum+='.';
        }
        else {
            secondNum+='.';
        }
        update();
    }

    public void plus(View view) {
        operationSign = '+';
        if(startedTypingSecNum){
            nextCalculation(OPERATION.ADDITION);
        }
        else {
            operation = OPERATION.ADDITION;
        }
        update();
    }

    public void minus(View view) {
        operationSign = '-';
        if(startedTypingSecNum){
            nextCalculation(OPERATION.SUBTRACTION);
        }
        else {
            operation = OPERATION.SUBTRACTION;
        }
        update();
    }

    public void multiply(View view) {
        operationSign = '×';
        if(startedTypingSecNum){
            nextCalculation(OPERATION.MULTIPLICATION);
        }
        else {
            operation = OPERATION.MULTIPLICATION;
        }
        update();
    }

    public void divide(View view) {
        operationSign = '÷';
        if(startedTypingSecNum){
            nextCalculation(OPERATION.DIVISION);
        }
        else {
            operation = OPERATION.DIVISION;
        }
        update();
    }

    public void clearE(View view) {
        if(operation == OPERATION.NONE) {
            firstNum = "0";
        }
        else {
            secondNum = "0";
        }
        isCalculated = false;
        update();
    }

    public void clear(View view) {
        restart();
        update();
    }

    public void delete(View view) {
        if(!isCalculated) {
            if (operation == OPERATION.NONE) {
                if ((firstNum.length() == 2 && firstNum.charAt(0) == '-') || firstNum.length() == 1) {
                    firstNum = "0";
                } else {
                    firstNum = firstNum.substring(0, firstNum.length() - 1);
                }
            } else {
                if ((secondNum.length() == 2 && secondNum.charAt(0) == '-') || secondNum.length() == 1) {
                    secondNum = "0";
                } else {
                    secondNum = secondNum.substring(0, secondNum.length() - 1);
                }
            }
        }
        update();
    }
}
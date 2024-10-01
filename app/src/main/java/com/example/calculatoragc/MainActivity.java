package com.example.calculatoragc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Variables de la calculadora
    Integer number1 = 0;
    Integer number2 = 0;
    String simbol = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos las salidas de pantalla
        TextView tvOperation = findViewById(R.id.tvOperation);
        TextView tvResult = findViewById(R.id.tvResult);

        // Obtenemos Los números
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn0 = findViewById(R.id.btn0);

        // Obtenemos los operadores
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnSplit = findViewById(R.id.btnSplit);
        Button btnClean = findViewById(R.id.btnClean);
        Button btnEqual = findViewById(R.id.btnEqual);

        // Ajustamos la funcionalidad de los botones numéricos
        btn1.setOnClickListener(view -> appendNumber(tvResult, "1"));
        btn2.setOnClickListener(view -> appendNumber(tvResult, "2"));
        btn3.setOnClickListener(view -> appendNumber(tvResult, "3"));
        btn4.setOnClickListener(view -> appendNumber(tvResult, "4"));
        btn5.setOnClickListener(view -> appendNumber(tvResult, "5"));
        btn6.setOnClickListener(view -> appendNumber(tvResult, "6"));
        btn7.setOnClickListener(view -> appendNumber(tvResult, "7"));
        btn8.setOnClickListener(view -> appendNumber(tvResult, "8"));
        btn9.setOnClickListener(view -> appendNumber(tvResult, "9"));
        btn0.setOnClickListener(view -> appendNumber(tvResult, "0"));

        // Botón de limpiar
        btnClean.setOnClickListener(view -> resetCalculator(tvResult, tvOperation));

        // Botón de sumar
        btnAdd.setOnClickListener(view -> handleOperator(tvResult, tvOperation, "+"));

        // Botón de restar
        btnSubtract.setOnClickListener(view -> handleOperator(tvResult, tvOperation, "-"));

        // Botón de multiplicar
        btnMultiply.setOnClickListener(view -> handleOperator(tvResult, tvOperation, "*"));

        // Botón de dividir
        btnSplit.setOnClickListener(view -> handleOperator(tvResult, tvOperation, "/"));

        // Botón de igual
        btnEqual.setOnClickListener(view -> handleEqual(tvResult, tvOperation));
    }

    // Método para añadir un número a la pantalla
    private void appendNumber(TextView tvResult, String number) {
        if (tvResult.getText().charAt(0) == '0') {
            tvResult.setText(number);  // Si es 0, lo reemplazamos
        } else {
            tvResult.setText(tvResult.getText().toString() + number);  // Si no, lo añadimos
        }
    }

    // Método para reiniciar la calculadora
    private void resetCalculator(TextView tvResult, TextView tvOperation) {
        tvResult.setText("0");
        tvOperation.setText("");
        number1 = 0;
        number2 = 0;
        simbol = "";
    }

    // Método para manejar operadores
    private void handleOperator(TextView tvResult, TextView tvOperation, String operator) {
        if (number1 == 0) {
            number1 = Integer.valueOf(tvResult.getText().toString());
        } else if (!simbol.equals("")) {
            number2 = Integer.valueOf(tvResult.getText().toString());
            number1 = operation(number1, number2, simbol);
            tvResult.setText(String.valueOf(number1));  // Mostramos el resultado intermedio
        }
        simbol = operator;  // Guardamos el operador actual
        tvOperation.setText(number1 + " " + simbol + " ");
        tvResult.setText("0");
    }

    // Método para manejar la operación de igual
    private void handleEqual(TextView tvResult, TextView tvOperation) {
        if (!simbol.equals("")) {
            number2 = Integer.valueOf(tvResult.getText().toString());
            int result = operation(number1, number2, simbol);
            tvResult.setText(String.valueOf(result));
            tvOperation.setText(number1 + " " + simbol + " " + number2 + " = ");
            number1 = result;
            simbol = "";  // Limpiamos el operador
        }
    }

    // Ajustamos que cuando se inice la app este los campos vacios
    @Override
    protected void onStart() {
        super.onStart();
        // Obtenemos las salidas de pantalla
        TextView tvOperation = findViewById(R.id.tvOperation);
        TextView tvResult = findViewById(R.id.tvResult);

        tvOperation.setText("");
        tvResult.setText("0");
    }

    // Método para realizar la operación
    private int operation(Integer number1, Integer number2, String simbol) {
        switch (simbol) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                if (number2 == 0) {
                    Toast.makeText(MainActivity.this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
                    return number1;  // Retornamos el número1 sin modificar
                }
                return number1 / number2;
            default:
                Log.e("Operation Error", "Operador desconocido");
                return 0;
        }
    }
}

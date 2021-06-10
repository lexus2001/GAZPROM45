package com.example.gazprom45;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity2 extends AppCompatActivity {
    String[] gaz = { "Достоевского 70A", "Гагарина 84А"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText price = (EditText) findViewById(R.id.PrIce);
        EditText tf = (EditText) findViewById(R.id.tarif);
        EditText rs = (EditText) findViewById(R.id.rashod);
        EditText ps = (EditText) findViewById(R.id.ysluga);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerADRES);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gaz);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                if(item.equals("Достоевского 70A")) {
                    ps.setText("Сжиженный газ");
                    tf.setText("47,25 рублей");
                    price.setText("378 рублей");
                } else if (item.equals("Гагарина 84А")) {
                    ps.setText("Природный газ");
                    tf.setText("25,16 рублей");
                    price.setText("226,44 рублей");
                }
                 if (item.equals("Достоевского 70A")) {
                    rs.setText("8 килограмм");
                }
            else if (item.equals("Гагарина 84А")) {
                rs.setText("9 килограмм");
            }





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        };
    }
    public void buttonZayvka(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(intent);
    }
    }
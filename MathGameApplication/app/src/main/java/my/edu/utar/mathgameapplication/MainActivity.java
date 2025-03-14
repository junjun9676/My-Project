package my.edu.utar.mathgameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button compareNumberBtn = findViewById(R.id.button_compare_numbers);
        Button orderNumberBtn = findViewById(R.id.button_order_numbers);
        Button composeNumberBtn = findViewById(R.id.button_compose_numbers);

        compareNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_compare_numbers.class);
                startActivity(intent);
            }
        });

        orderNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_order_numbers.class);
                startActivity(intent);
            }
        });

        composeNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_compose_numbers.class);
                startActivity(intent);
            }
        });
    }
}
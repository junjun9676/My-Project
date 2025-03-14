package my.edu.utar.mathgameapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class activity_compare_numbers extends AppCompatActivity {
    private TextView num1TextView;
    private TextView num2TextView;
    private Button greatBtn;
    private Button lessBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_numbers);

        Toolbar toolbar = findViewById(R.id.toolbarShow);
        setSupportActionBar(toolbar);

        num1TextView = findViewById(R.id.number1TextView);
        num2TextView = findViewById(R.id.number2TextView);
        Button greatBtn = findViewById(R.id.greaterButton);
        Button lessBtn = findViewById(R.id.lessButton);

        // Generate random numbers
        generateNewQuestion();

        // Greater button click listener
        greatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(num1TextView.getText().toString()) > Integer.parseInt(num2TextView.getText().toString())) {
                    Toast.makeText(activity_compare_numbers.this, "You are correct! Great job!", Toast.LENGTH_SHORT).show();
                    // Refresh question
                    generateNewQuestion();
                } else {
                    Toast.makeText(activity_compare_numbers.this, "Wrong! " + num2TextView.getText() + " is greater.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Less button click listener
        lessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(num1TextView.getText().toString()) < Integer.parseInt(num2TextView.getText().toString())) {
                    Toast.makeText(activity_compare_numbers.this, "You are correct! Great job!", Toast.LENGTH_SHORT).show();
                    // Refresh question
                    generateNewQuestion();
                } else {
                    Toast.makeText(activity_compare_numbers.this, "Wrong! " + num1TextView.getText() + " is smaller.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void generateNewQuestion(){
        Random random = new Random();
        int num1 = random.nextInt(100);
        int num2 = random.nextInt(100);

        while (num1 == num2) {
            num2 = random.nextInt(100);
        }

        num1TextView.setText(String.valueOf(num1));
        num2TextView.setText(String.valueOf(num2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homeItem) {
            startActivity(new Intent(activity_compare_numbers.this, MainActivity.class));
            return true;
        }

        if (id == R.id.helpItem) {
            showHelpDialog();
            return true;
        }

        if (id == R.id.feedbackItem) {
            startActivity(new Intent(activity_compare_numbers.this, FeedbackActivity.class));
            return true;
        }



        // Add more cases for other menu items if needed
        return super.onOptionsItemSelected(item);
    }

    private void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Instructions");
        builder.setMessage("Compare Number\n" +
                "\n" +
                "How to Play:\n" +
                "\n" +
                "In this game, you'll be presented with two numbers. Your task is simple: quickly decide which number is greater and which is lesser. Tap on the number you believe is greater. Keep playing to sharpen your skills in comparing numbers!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
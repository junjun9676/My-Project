package my.edu.utar.mathgameapplication;

import static android.widget.Toast.makeText;


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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;


public class activity_compose_numbers extends AppCompatActivity {

    private TextView targetNumberTextView;
    private EditText num1EditText;
    private EditText num2EditText;
    private Button submitButton;

    private Button resetButton;


    private int targetNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_numbers);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        targetNumberTextView = findViewById(R.id.targetNumberTextView);
        num1EditText = findViewById(R.id.num1EditText);
        num2EditText = findViewById(R.id.num2EditText);
        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);
        FloatingActionButton fab =  findViewById(R.id.next);


        // Generate a random target number
        generateTargetNumber();

        // Set the target number TextView
        targetNumberTextView.setText(String.valueOf(targetNumber));

        // Set onClickListener for Submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetField();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
                resetField();
            }
        });
    }

    private void resetField() {
        num1EditText.setText("");
        num2EditText.setText("");
    }

    private void generateTargetNumber() {
        Random random = new Random();
        targetNumber = random.nextInt(100); // Random number between 0 and 99
    }

    private void checkAnswer() {
        // Get the user input from EditText fields
        String num1Str = num1EditText.getText().toString();
        String num2Str = num2EditText.getText().toString();

        // Check if both EditText fields are not empty
        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            int num1 = Integer.parseInt(num1Str);
            int num2 = Integer.parseInt(num2Str);

            // Check if the sum of the user's input matches the target number
            if (num1 + num2 == targetNumber) {
               // makeText(this, "", Toast.LENGTH_SHORT).show();
                makeText(activity_compose_numbers.this, "Correct! Great job!", Toast.LENGTH_SHORT).show();

            } else {
                makeText(activity_compose_numbers.this, "Wrong! Try again.", Toast.LENGTH_SHORT).show();
                resetField();
            }
        } else {
            makeText(activity_compose_numbers.this, "Please enter both numbers.", Toast.LENGTH_SHORT).show();
        }


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
                startActivity(new Intent(activity_compose_numbers.this, MainActivity.class));
                return true;
            }

            if (id == R.id.helpItem) {
                showHelpDialog();
                return true;
            }

            if (id == R.id.feedbackItem) {
                startActivity(new Intent(activity_compose_numbers.this, FeedbackActivity.class));
                return true;
            }



            // Add more cases for other menu items if needed
            return super.onOptionsItemSelected(item);
        }

        public void nextQuestion(){

            generateTargetNumber();

            // Set the target number TextView
            targetNumberTextView.setText(String.valueOf(targetNumber));
        }
    private void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Instructions");
        builder.setMessage("Compose the given number by adding two smaller numbers together.\n" +
                "\n" +
                "How to Play:\n" +
                "\n" +
                "You will be given a random target number.\n" +
                "Your task is to compose this number by adding two smaller numbers together.\n" +
                "Enter a number in each circle. These numbers must be smaller than the target number.\n" +
                "Once you've entered both numbers, click on the \"Submit\" button.\n" +
                "If the sum of the two entered numbers matches the target number, you win!\n" +
                "If the sum does not match, try again until you get it right.");
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
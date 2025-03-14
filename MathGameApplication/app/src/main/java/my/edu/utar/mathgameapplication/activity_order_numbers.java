package my.edu.utar.mathgameapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class activity_order_numbers extends AppCompatActivity {

    private TextView[] numberTextViews;
    private TextView orderTypeTextView, resultTextView;

    private Button checkOrderButton;

    private FloatingActionButton nextButton;
    private ArrayList<Integer> numbers;
    private boolean ascendingOrder = true;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_numbers);

        Toolbar toolbar2 = findViewById(R.id.toolbarON);
        setSupportActionBar(toolbar2);

        numberTextViews = new TextView[]{
                findViewById(R.id.num1),
                findViewById(R.id.num2),
                findViewById(R.id.num3),
                findViewById(R.id.num4),
                findViewById(R.id.num5)
        };

        orderTypeTextView = findViewById(R.id.orderTypeTextView);
        checkOrderButton = findViewById(R.id.checkOrderButton);
        resultTextView = findViewById(R.id.resultTextView);
        nextButton = findViewById(R.id.nextButton);

        generateNumbers();
        displayNumbers();

        orderTypeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ascendingOrder = !ascendingOrder;
                if (ascendingOrder) {
                    orderTypeTextView.setText("Ascending Order");
                } else {
                    orderTypeTextView.setText("Descending Order");
                }
            }
        });

        for (final TextView textView : numberTextViews) {
            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDragAndDrop(null, shadowBuilder, v, 0);
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            textView.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    int action = event.getAction();
                    switch (action) {
                        case DragEvent.ACTION_DROP:
                            View view = (View) event.getLocalState();
                            TextView droppedTextView = (TextView) view;
                            int droppedIndex = -1;
                            for (int i = 0; i < numberTextViews.length; i++) {
                                if (numberTextViews[i] == droppedTextView) {
                                    droppedIndex = i;
                                    break;
                                }
                            }
                            if (droppedIndex != -1) {
                                TextView targetTextView = textView;
                                int targetIndex = -1;
                                for (int i = 0; i < numberTextViews.length; i++) {
                                    if (numberTextViews[i] == targetTextView) {
                                        targetIndex = i;
                                        break;
                                    }
                                }
                                if (targetIndex != -1) {
                                    Collections.swap(numbers, droppedIndex, targetIndex);
                                    displayNumbers();
                                    //checkOrder();
                                }
                            }
                            break;
                    }
                    return true;
                }
            });
        }

        checkOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOrder();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNewQuestion();
                resultTextView.setText("");
            }
        });

    }
        private void generateNumbers () {
            numbers = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < numberTextViews.length; i++) {
                numbers.add(random.nextInt(100));
            }
        }

        private void displayNumbers () {
            for (int i = 0; i < numberTextViews.length; i++) {
                numberTextViews[i].setText(String.valueOf(numbers.get(i)));
            }
        }

        private void checkOrder () {
            boolean correctOrder = true;
            for (int i = 1; i < numbers.size(); i++) {
                if (ascendingOrder && numbers.get(i) < numbers.get(i - 1)) {
                    correctOrder = false;
                    break;
                } else if (!ascendingOrder && numbers.get(i) > numbers.get(i - 1)) {
                    correctOrder = false;
                    break;
                }
            }
            if (correctOrder) {
                resultTextView.setText("Correct Order!");
            } else {
                resultTextView.setText("Incorrect Order. Try Again!");
            }
        }


    private void generateNewQuestion(){
        generateNumbers();
        displayNumbers();

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
            startActivity(new Intent(activity_order_numbers.this, MainActivity.class));
            return true;
        }

        if (id == R.id.helpItem) {
            showHelpDialog();
            return true;
        }

        if (id == R.id.feedbackItem) {
            startActivity(new Intent(activity_order_numbers.this, FeedbackActivity.class));
            return true;
        }



        // Add more cases for other menu items if needed
        return super.onOptionsItemSelected(item);
    }

    private void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Instructions");
        builder.setMessage("Order the given number to ascending order.\n" +
                "\n" +
                "How to Play:\n" +
                "\n" +
                "Drag each number to its correct position to arrange them in ascending order. Complete the sequence by placing each number in its proper location.\n" +
                "Once you've arranged, click on the \"Submit\" button.\n" +
                "If the sequence is correct, you win!\n" +
                "If the sequence is incorrect, try again until you get it right.");
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




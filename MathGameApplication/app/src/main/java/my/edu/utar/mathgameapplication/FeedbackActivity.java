package my.edu.utar.mathgameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    private EditText feedbackEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackEditText = findViewById(R.id.feedbackEditText);
        Button submitbutton = findViewById(R.id.submitButton);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = feedbackEditText.getText().toString().trim();
                if(!TextUtils.isEmpty(feedback)){
                    Toast.makeText(FeedbackActivity.this,"Feedback submitted!",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(FeedbackActivity.this,"Please enter your feedback",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
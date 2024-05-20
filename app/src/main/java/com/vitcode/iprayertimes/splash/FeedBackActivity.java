package com.vitcode.iprayertimes.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.databinding.ActivityFeedBackBinding;

public class FeedBackActivity extends AppCompatActivity {
    ActivityFeedBackBinding binding;
    String selectedOption = "Issues";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        binding.feedBackIMG.setOnClickListener(view -> {
            onBackPressed();
        });
        //default
        binding.radioB1.setChecked(true);

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                selectedOption = selectedRadioButton.getText().toString();
            }
        });



        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentClickToNext();
            }
        });

    }
    private void IntentClickToNext() {
        if (!FeedBackActivity.this.binding.edtFeedback.getText().toString().isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String[] emails_in_to = {"exadesignstudioads@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, emails_in_to);
            String subject = selectedOption;
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, binding.edtFeedback.getText().toString());
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(intent);
            binding.edtFeedback.setText("");
            return;
        }
        FeedBackActivity.this.binding.edtFeedback.setError("Please Enter Message !!!");
    }
}
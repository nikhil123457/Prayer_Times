package com.vitcode.iprayertimes.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.databinding.ActivityContactBinding;

public class ContactActivity extends AppCompatActivity {

    ActivityContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.COntactBackIMG.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentClickToNext();
            }
        });
    }

    private void IntentClickToNext() {
        if (!ContactActivity.this.binding.edtMessageCont.getText().toString().isEmpty() && !ContactActivity.this.binding.edtNameCont.getText().toString().isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String[] emails_in_to = {"appsviha@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, emails_in_to);
            String subject = binding.edtNameCont.getText().toString();
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, binding.edtMessageCont.getText().toString());
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(intent);
            return;
        }
        ContactActivity.this.binding.edtMessageCont.setError("Please Enter Message !!!");

    }


}
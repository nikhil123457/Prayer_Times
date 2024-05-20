package com.vitcode.iprayertimes.abcd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vitcode.iprayertimes.R;

public class TextFragment extends Fragment {

    private TextClickListener listener;
    private EditText EDItxt1;
    private ImageView IMgTxt1;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TextClickListener) {
            listener = (TextClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement TextClickListener");
        }
    }


    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_text, container, false);

        textView1 = rootView.findViewById(R.id.textView1);
        textView2 = rootView.findViewById(R.id.textView2);
        textView3 = rootView.findViewById(R.id.textView3);
        textView4 = rootView.findViewById(R.id.textView4);
        textView5 = rootView.findViewById(R.id.textView5);
        textView6 = rootView.findViewById(R.id.textView6);
        textView7 = rootView.findViewById(R.id.textView7);
        textView8 = rootView.findViewById(R.id.textView8);

        textView1.setOnClickListener(v -> {
            String text = textView1.getText().toString();
            listener.onTextClicked(text);
        });

        textView2.setOnClickListener(v -> {
            String text = textView2.getText().toString();
            listener.onTextClicked(text);
        });
        textView3.setOnClickListener(v -> {
            String text = textView3.getText().toString();
            listener.onTextClicked(text);
        });
        textView4.setOnClickListener(v -> {
            String text = textView4.getText().toString();
            listener.onTextClicked(text);
        });
        textView5.setOnClickListener(v -> {
            String text = textView5.getText().toString();
            listener.onTextClicked(text);
        });
        textView6.setOnClickListener(v -> {
            String text = textView6.getText().toString();
            listener.onTextClicked(text);
        });
        textView7.setOnClickListener(v -> {
            String text = textView7.getText().toString();
            listener.onTextClicked(text);
        });
        textView8.setOnClickListener(v -> {
            String text = textView8.getText().toString();
            listener.onTextClicked(text);
        });

        EDItxt1 = rootView.findViewById(R.id.EDItxt1);
        IMgTxt1 = rootView.findViewById(R.id.IMgTxt1);

        IMgTxt1.setOnClickListener(v -> {
            String text = EDItxt1.getText().toString();
            listener.onTextClicked(text);
        });


        return rootView;
    }

    public interface TextClickListener {
        void onTextClicked(String text);
    }

}
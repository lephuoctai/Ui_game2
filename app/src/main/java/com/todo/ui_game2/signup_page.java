package com.todo.ui_game2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class signup_page extends AppCompatActivity {
    public void paintText(TextView view, String src, ArrayList<String> text){
        SpannableString spannableString = new SpannableString(src);
        int color = ContextCompat.getColor(this, R.color.access);

        for(String t : text){
            int start = src.indexOf(t);
            int end = start + t.length();

            spannableString.setSpan(new ForegroundColorSpan(color), start, end, spannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        view.setText(spannableString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            return insets;
        });

        String src = "By clicking continue, you agree to our \nTerms of Service  and  Privacy Policy.";
        TextView terms = findViewById(R.id.terms);
        ArrayList<String> text = new ArrayList<>();
        text.add("Terms of Service");
        text.add("Privacy Policy");

        paintText(terms, src, text);

        String src2 = "Already have an account? Log in >";
        TextView login_have_account = findViewById(R.id.login_have_account);
        ArrayList<String> textLogin = new ArrayList<>();
        text.add("Log in >");

        paintText(login_have_account, src2, textLogin);

        ImageView again = findViewById(R.id.btn_signup);
        again.setOnClickListener(v -> {
            Intent intent = new Intent(login_have_account.getContext(), signup_page_2.class);
            startActivity(intent);
        });

    }
}
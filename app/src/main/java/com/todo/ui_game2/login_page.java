package com.todo.ui_game2;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class login_page extends AppCompatActivity {
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
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String src = "New to GamerPal? Sign Up >";
        TextView terms = findViewById(R.id.login);
        ArrayList<String> text = new ArrayList<>();
        text.add("Sign Up >");

        paintText(terms, src, text);

        Button btn_signup_with_google = findViewById(R.id.btn_signup_with_google);
        btn_signup_with_google.setOnClickListener(v -> {
            Intent intent = new Intent(this, login_gg.class);
            startActivity(intent);
        });

        ImageButton btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(v -> {
            Intent intent = new Intent(this, loading_page.class);
            startActivity(intent);
        });
    }
}
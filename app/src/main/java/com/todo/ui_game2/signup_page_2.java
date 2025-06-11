package com.todo.ui_game2;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class signup_page_2 extends AppCompatActivity {
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
        setContentView(R.layout.activity_signup_page2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String src = "Didn’t receive a code? Send again.";
        TextView send_again = findViewById(R.id.send_again);
        ArrayList<String> text = new ArrayList<>();
        text.add("Send again.");

        paintText(send_again, src, text);

    }
}
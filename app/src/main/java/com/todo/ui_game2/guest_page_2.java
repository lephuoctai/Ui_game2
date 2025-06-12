package com.todo.ui_game2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class guest_page_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guest_page2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_skip = findViewById(R.id.btn_skip_intro_2);
        ImageButton btn_next = findViewById(R.id.btn_next_2);

        btn_next.setOnClickListener(v -> {
            Intent intent = new Intent(this, guest_page_3.class);
            startActivity(intent);
        });

        btn_skip.setOnClickListener(v -> {
            Intent intent = new Intent(this, loading_page.class);
            startActivity(intent);
        });
    }
}
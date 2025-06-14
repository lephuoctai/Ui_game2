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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btn_login = findViewById(R.id.btn_login);
        ImageButton btn_signup = findViewById(R.id.btn_signup);
        Button btn_signup_with_google = findViewById(R.id.btn_signup_with_google);
        Button btn_continue_as_guest = findViewById(R.id.btn_continue_as_guest);

        btn_signup.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, signup_page.class);
            startActivity(intent);
        });

        btn_login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, login_page.class);
            startActivity(intent);

        });

        btn_signup_with_google.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, login_gg.class);
            startActivity(intent);
        });

        btn_continue_as_guest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, guest_page.class);
            startActivity(intent);
        });
    }
}
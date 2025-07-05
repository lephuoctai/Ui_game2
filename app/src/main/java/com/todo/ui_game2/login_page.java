package com.todo.ui_game2;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class login_page extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;

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
        EditText email = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        // paint text
        String src = "New to GamerPal? Sign Up >";
        TextView terms = findViewById(R.id.login);
        ArrayList<String> text = new ArrayList<>();
        text.add("Sign Up >");

        paintText(terms, src, text);

        ImageButton btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(v -> {
            //Setup Firebase Auth
            mAuth = FirebaseAuth.getInstance();

            String TextEmail = email.getText().toString();
            String TextPassword = password.getText().toString();

            mAuth.signInWithEmailAndPassword(TextEmail, TextPassword).addOnCompleteListener(t -> {
                Task<AuthResult> task = t;
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String getEmail = user.getEmail();
                    Toast.makeText(this, getEmail + "\nđăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(2000);
                        Intent intent = new Intent(this, loading_page.class);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(this, "Đăng nhập thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });


    }



}
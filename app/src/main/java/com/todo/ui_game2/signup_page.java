package com.todo.ui_game2;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class signup_page extends AppCompatActivity {
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
        textLogin.add("Log in >");

        paintText(login_have_account, src2, textLogin);

        ImageButton btn_signup = findViewById(R.id.btn_signup);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        btn_signup.setOnClickListener(v -> {
            //Setup Firebase Auth
            mAuth = FirebaseAuth.getInstance();

            String TextEmail = email.getText().toString();
            String TextPassword = password.getText().toString();
            //Test case
//            TextEmail = "lephuoctai@gmail.com";
//            TextPassword = "Taile@123";

            mAuth.createUserWithEmailAndPassword(TextEmail, TextPassword).addOnCompleteListener(t -> {
                Task<AuthResult> task = t;
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String getEmail = user.getEmail();
                    Toast.makeText(this, getEmail + "\nđăng ký thành công!", Toast.LENGTH_SHORT).show();
                    new android.os.Handler().postDelayed(() -> {
                        Intent intent = new Intent(this, loading_page.class);
                        startActivity(intent);
                    }, 2000); // delay 2s

                } else {
                    Toast.makeText(this, " Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        });

    }
}
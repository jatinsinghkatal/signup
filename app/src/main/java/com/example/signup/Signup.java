package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    EditText username, pwd;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        signup = findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String userpwd = pwd.getText().toString();

                // Check if username is empty
                if (email.isEmpty()) {
                    Toast.makeText(Signup.this, "Username cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validate password
                if (!isValidPassword(userpwd)) {
                    Toast.makeText(Signup.this, "Invalid Password", Toast.LENGTH_LONG).show();
                    return;
                }

                // Create Intent and pass data to Signin activity
                Intent intent = new Intent(Signup.this, Signin.class);
                intent.putExtra("email", email);
                intent.putExtra("userpwd", userpwd);
                startActivity(intent);
            }
        });
    }

    // Regex patterns for password validation
    Pattern lowercase = Pattern.compile(".*[a-z].*");
    Pattern uppercase = Pattern.compile(".*[A-Z].*");
    Pattern number = Pattern.compile(".*[0-9].*");
    Pattern special = Pattern.compile(".*[@#$%^&*(){},.;/].*");

    // Validate password
    private boolean isValidPassword(String userpwd) {
        if (userpwd.length() < 8) {
            return false; // Password should be at least 8 characters long
        }
        if (!lowercase.matcher(userpwd).matches()) {
            return false; // Password should have at least one lowercase letter
        }
        if (!uppercase.matcher(userpwd).matches()) {
            return false; // Password should have at least one uppercase letter
        }
        if (!number.matcher(userpwd).matches()) {
            return false; // Password should have at least one digit
        }
        if (!special.matcher(userpwd).matches()) {
            return false; // Password should have at least one special character
        }
        return true; // If all conditions are met
    }
}

package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    EditText eusername, epwd;
    Button login;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Initialize views
        eusername = findViewById(R.id.username);
        epwd = findViewById(R.id.password);
        login = findViewById(R.id.login);

        // Retrieve the registered email and password from Intent
        String regemail = getIntent().getStringExtra("email");
        String regpwd = getIntent().getStringExtra("userpwd");

        // Set onClickListener for the login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newemail = eusername.getText().toString();
                String newpwd = epwd.getText().toString();

                // Check if the entered email and password match the registered ones
                if (regemail.equals(newemail) && regpwd.equals(newpwd)) {
                    // Login successful
                    Toast.makeText(Signin.this, "Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Signin.this, Welcome.class);
                    startActivity(intent);
                    eusername.setText(""); // Clear username field
                    epwd.setText(""); // Clear password field
                } else {
                    // Login failed
                    count++;
                    Toast.makeText(Signin.this, "Login Failed: Attempt " + count, Toast.LENGTH_LONG).show();

                    // Disable login button after 3 failed attempts
                    if (count == 3) {
                        login.setEnabled(false);
                        Toast.makeText(Signin.this, "Account Locked After 3 Failed Attempts", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}

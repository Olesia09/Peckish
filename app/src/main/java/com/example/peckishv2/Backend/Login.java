package com.example.peckishv2.Backend;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peckishv2.R;

public class Login extends AppCompatActivity {
    EditText uEmail, uPassword, uUsername, signIn_username, signIn_password;
    ImageButton uRegisterBtn, uSignInBtn, uSubmit, signIn_submit;
    Dialog userPrompt, signIn;
    String newUsername, newPassword, newEmail, signIn_username_input, singIn_password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // set views
        uEmail=findViewById(R.id.email);
        uPassword=findViewById(R.id.password);
        uRegisterBtn=findViewById(R.id.register_btn);
        uSignInBtn=findViewById(R.id.sign_in_btn);

        //register action
        uRegisterBtn.setOnClickListener(v -> {
            newEmail = uEmail.getText().toString();
            newPassword = uPassword.getText().toString();
            checkEmail_Password(newPassword, newEmail);
        });

        //sign in action
        uSignInBtn.setOnClickListener(v -> signIn.show());

        //username prompt dialog
        userPrompt = new Dialog(this);
        userPrompt.setContentView(R.layout.dialog_username);
        userPrompt.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_box));
        userPrompt.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        uUsername = userPrompt.findViewById(R.id.user_username);
        uSubmit = userPrompt.findViewById(R.id.submit_btn);

        uSubmit.setOnClickListener(v -> {
            newUsername = uUsername.getText().toString();
            checkUsername(newUsername);
        });

        //sign in dialog
        signIn = new Dialog(this);
        signIn.setContentView(R.layout.dialog_sign_in);
        signIn.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_box));
        signIn.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        signIn_username = signIn.findViewById(R.id.sign_in_username);
        signIn_password = signIn.findViewById(R.id.sign_in_password);
        signIn_submit = signIn.findViewById(R.id.sign_in_submit_btn);

        signIn_submit.setOnClickListener(v -> {
            signIn_username_input = signIn_username.getText().toString();
            singIn_password_input = signIn_password.getText().toString();
            signIn.dismiss();
        });
    }

    private void checkEmail_Password(String password, String email) {
        if (!email.contains("@"))
        {
            error(uEmail, "Invalid Email");
        }
        else if (password.isEmpty() || password.length()<7)
        {
            error(uPassword, "Password must have at least 7 characters");
        }
        else
        {
            userPrompt.show();
       }
    }
    private void checkUsername(String username) {
        if(username.isEmpty()){
            error(uUsername, "Invalid Username");
        }
        else if (username.length()<2)
        {
            error(uUsername, "Username must have at least 2 characters");
        }
        else
        {
            userPrompt.dismiss();
            startActivity(new Intent(Login.this, LoadingScreen.class));
        }
    }
    private void error(EditText userInput, String text){
        userInput.setError(text);
        userInput.requestFocus();
    }
}
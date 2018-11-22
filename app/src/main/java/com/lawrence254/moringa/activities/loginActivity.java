package com.lawrence254.moringa.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lawrence254.moringa.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.AuthenticationInterfaceTry;

public class loginActivity extends AppCompatActivity implements View.OnClickListener, AuthenticationInterfaceTry {

    private FirebaseAuth fAuth;
    @BindView(R.id.loginButton) Button loginButton;
    @BindView(R.id.emailEditText)TextView mEmail;
    @BindView(R.id.passwordEditText)TextView mPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        fAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser= fAuth.getCurrentUser();



        if (firebaseUser != null){
            Intent intent = new Intent(this,homeActivity.class);
            startActivity(intent);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            String email = mEmail.getText().toString();
            String pass = mPass.getText().toString();
            if (Objects.equals(email, "") && Objects.equals(pass, "")){
                Toast.makeText(this, "All Fields Must Be Filled", Toast.LENGTH_LONG).show();
            }
            else if (Objects.equals(email, "")){
                Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show();
            }else if (Objects.equals(pass, "")) {
                Toast.makeText(this, "A password is required", Toast.LENGTH_LONG).show();
            }
            else{
                login(email,pass);
            }
    }


    @Override
    public void login(String username, String password) {
        fAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(loginActivity.this,homeActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(loginActivity.this, "Authentication Failed, Please Try again or create account", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }

}

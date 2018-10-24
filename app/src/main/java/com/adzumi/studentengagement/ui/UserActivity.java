package com.adzumi.studentengagement.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adzumi.studentengagement.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.signup) Button mSignup;
    @BindView(R.id.signin) Button mSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        mSignup.setOnClickListener(this);
        mSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view == mSignup){
            Intent intent =  new Intent(UserActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
        if (view == mSignin) {
            Intent intent =  new Intent(UserActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }
}

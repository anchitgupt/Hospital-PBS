package com.ateam.hospital.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ateam.hospital.Controller.User;
import com.ateam.hospital.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "LoginActivity";


    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    TextInputEditText mTextInputUserName;
    TextInputEditText mTextInputUserPassword;
    Button mButtonSignin;
    TextView mTextViewForgotPassword;

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
        user =  FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            //TODO intent to send the data
            Toast.makeText(this, "User Not Exist", Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "onStart: User Exist" );
            Intent intent = new Intent(this, BillActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mTextInputUserName      =  findViewById(R.id.textInputEditTextUser);
        mTextInputUserPassword  =  findViewById(R.id.textInputEditTextPassword);
        mButtonSignin           =  findViewById(R.id.submit);
        mTextViewForgotPassword =  findViewById(R.id.textviewForgotPassword);

        mButtonSignin.setOnClickListener(this);
        mTextViewForgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String userName   =  mTextInputUserName.getText().toString().trim();
        String userPassword =  mTextInputUserPassword.getText().toString().trim();
        User user = new User();
        user.setUsername(userName);
        user.setPassword(userPassword);
        if (view == mButtonSignin) {

            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPassword)){
                Toast.makeText(this, "Enter the field Values", Toast.LENGTH_SHORT).show();
            } else {
                boolean status = user.signIn();
                if (status){
                    Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, BillActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (view == mTextViewForgotPassword) {
            if (TextUtils.isEmpty(userName)){
                Toast.makeText(this, "Enter the field Values", Toast.LENGTH_SHORT).show();
            } else {
                user.forgotPassword();
            }
        }

    }

}

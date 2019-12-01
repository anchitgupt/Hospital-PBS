package com.ateam.hospital.Controller;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-24.
 * Under the MIT License
 */
public class User {
    private String username;
    private String password;

    private FirebaseAuth firebaseAuth;
    FirebaseUser user;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean signIn(){
        firebaseAuth = FirebaseAuth.getInstance();
        final boolean[] flag = {true};
        Log.e("Test2", "signIn: "+ username);
        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                           flag[0] = true;
                        Log.e("Test", "onSuccess: "+username);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                flag[0] = false;
                Log.e("User: ", "onFailure: " +e.getMessage());
            }
        });
        return flag[0];
    }

    public boolean forgotPassword(){
        final boolean[] flag = {true};

        firebaseAuth.sendPasswordResetEmail(username).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                flag[0] = true;
                Log.e("Test", "onSuccess: ");
            }
        });

        return flag[0];
    }
}

package com.pret.lalala.pret;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText inputEmail;
    TextInputEditText inputPassword;
    MaterialButton signIn;
    String email;
    String password;
    String keys;
    DatabaseReference ref;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.emailEditText);
        inputPassword = findViewById(R.id.passEditText);
        signIn = findViewById(R.id.sign_in);

        mAuth = FirebaseAuth.getInstance();

        ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://pret-app-35b58.firebaseio.com/users");

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputEmail.getText().toString().isEmpty()) {
                    email = "lala";
                } else {
                    email = inputEmail.getText().toString();
                }

                if (inputPassword.getText().toString().isEmpty()) {
                    password = "lele";
                } else {
                    password = inputPassword.getText().toString();
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent signInIntent = new Intent(
                                            LoginActivity.this,
                                            MainActivity.class
                                    );
                                    startActivity(signInIntent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this,
                                            "Email dan Kata sandi yang anda masukan salah",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                ref.orderByChild("email").equalTo(inputEmail.getText().toString()).
                        addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                    keys = datas.getKey();
                                    Log.d("UserID", keys);
                                    getSharedPreferences("PREFERENCE_CURRENT_USER_ID", MODE_PRIVATE).edit()
                                            .putString("currentUserId", keys).apply();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                getSharedPreferences("PREFERENCE_LOGGEDIN", MODE_PRIVATE).edit()
                        .putBoolean("isLoggedIn", true).apply();
                getSharedPreferences("PREFERENCE_CURRENT_USER", MODE_PRIVATE).edit()
                        .putString("currentUser", inputEmail.getText().toString()).apply();
            }
        });
    }
}
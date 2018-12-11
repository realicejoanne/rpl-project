package com.pret.lalala.pret;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pret.lalala.pret.Model.User;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText inputEmail, inputNama, inputUname, inputPassword, inputNoHp;
    private MaterialButton btnSignUp;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.emailEditText);
        inputNama = findViewById(R.id.namaUserEditText);
        inputUname = findViewById(R.id.unameEditText);
        inputPassword = findViewById(R.id.passEditText);
        inputNoHp = findViewById(R.id.phoneEditText);

        btnSignUp = findViewById(R.id.btn_daftar);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukan alamat Email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Masukan Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Kata sandi terlalu pendek, minimal 6 karakter", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

                mDatabase = FirebaseDatabase.getInstance().getReference();

                //Getting Values
                String namaUser = inputNama.getText().toString();
                String emailUser = inputEmail.getText().toString();
                String unameUser = inputUname.getText().toString();
                String phoneUser = inputNoHp.getText().toString();

                //Creating new user node
                String userId = mDatabase.push().getKey();

                //Creating user Object
                User user = new User(namaUser, emailUser, unameUser, userId, phoneUser);

                //Pushing user to 'users node using userID
                mDatabase.child("users").child(userId).setValue(user);
                finish();
            }
        });


    }
}

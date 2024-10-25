package com.ntt.mediatn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.ntt.mediatn.Model.User;

public class LoginActivity extends AppCompatActivity {
    TextView tvReg, tvLogin;
    CardView cardViewLogin, cardViewReg;
    private boolean passwordShowing = false;

    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = auth.getCurrentUser();

        tvReg = findViewById(R.id.tv_reg);
        tvLogin = findViewById(R.id.tv_log);
        cardViewLogin = findViewById(R.id.cardview_login);
        cardViewReg = findViewById(R.id.cardview_reg);

// Swp cardview
        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewLogin.setVisibility(View.GONE);
                cardViewReg.setVisibility(View.VISIBLE);
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewLogin.setVisibility(View.VISIBLE);
                cardViewReg.setVisibility(View.GONE);
            }
        });
//---------------------------------------------
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final TextView login_button = findViewById(R.id.login_button);

        final EditText reg_username = findViewById(R.id.reg_username);
        final EditText reg_email = findViewById(R.id.reg_email);
        final EditText reg_job = findViewById(R.id.reg_job);
        final EditText reg_password = findViewById(R.id.reg_password);
        final EditText reg_reppassword = findViewById(R.id.reg_reppassword);
        final ImageView reg_passwordIcon = findViewById(R.id.reg_passwordIcon);
        final ImageView reg_reppasswordIcon = findViewById(R.id.reg_reppasswordIcon);
        final Button register_button = findViewById(R.id.register_button);
//Show pass
        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordShowing) {
                    passwordShowing = false;
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    passwordShowing = true;
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                password.setSelection(password.length());
            }
        });
        reg_passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordShowing) {
                    passwordShowing = false;
                    reg_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    passwordShowing = true;
                    reg_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                reg_password.setSelection(reg_password.length());
            }
        });
        reg_reppasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordShowing) {
                    passwordShowing = false;
                    reg_reppassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    passwordShowing = true;
                    reg_reppassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                reg_reppassword.setSelection(reg_reppassword.length());
            }
        });
// Login
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                String lEmail = email.getText().toString(), lPassword = password.getText().toString();
                auth.signInWithEmailAndPassword(lEmail, lPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });

            }
        });

//  Regester
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                final String getEmailtxt = reg_email.getText().toString();
//                Intent intent = new Intent(LoginActivity.this, OTPVerification_Activity.class);
//                intent.putExtra("Email", getEmailtxt);
//                startActivity(intent);
                String rEmail = reg_email.getText().toString(), rPassword = reg_password.getText().toString();
                auth.createUserWithEmailAndPassword(rEmail, rPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(reg_username.getText().toString(),reg_job.getText().toString(), rEmail, rPassword);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(user);
                                    Toast.makeText(LoginActivity.this, "Đã lưu người dùng", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser!= null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
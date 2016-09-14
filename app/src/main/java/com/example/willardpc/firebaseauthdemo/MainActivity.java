package com.example.willardpc.firebaseauthdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);


        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    //
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //EMPTY EMAIL.
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            //STOPS THE FUNCTION FROM EXECUTING FURTHER
            return;
        }

        if (TextUtils.isEmpty(password)){
            //EMPTY PASSWORD
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            //STOPS THE FUNCTION FROM EXECUTING FURTHER
            return;
        }
        //IF VALIDATIONS ARE OK
        //SHOW THE PROGRESSBAR

        progressDialog.setMessage("Registering new user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        })

    }

    //
    @Override
    public void onClick(View view) {
        if (view == buttonRegister){
            registerUser();
        }

        if (view == textViewSignin){
            //WILL OPEN LOGIN ACTIVITY HERE
        }
    }
}

package com.example.iss;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DataBaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    EditText editTextCNP,editTextTelefon,editTextNume,editTextAdresa;
    Button mButtonRegister;
    TextView mTextViewLogin;
    Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DataBaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        editTextAdresa=(EditText)findViewById(R.id.edittext_adresa);
        editTextCNP=(EditText)findViewById(R.id.edittext_cnp);
        editTextTelefon=(EditText)findViewById(R.id.edittext_telefon);
        editTextNume=(EditText)findViewById(R.id.edittext_nume);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);
        mSpinner =(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.userType,R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();
                String userType =mSpinner.getSelectedItem().toString();
                String cnp=editTextCNP.getText().toString().trim();
                String adresa=editTextAdresa.getText().toString().trim();
                String nume=editTextNume.getText().toString().trim();
                String telefon=editTextTelefon.getText().toString().trim();

                if(mTextUsername.length()==0)
                    mTextUsername.setError("");
                else if(mTextPassword.length()==0)
                    mTextPassword.setError("");
                else if(mTextCnfPassword.length()==0)
                    mTextCnfPassword.setError("");
                else if(editTextAdresa.length()==0)
                    editTextAdresa.setError("");
                else if(editTextNume.length()==0)
                    editTextNume.setError("");

                else if(pwd.equals(cnf_pwd)){
                    long val = db.addUser(userName,pwd,userType,cnp,adresa,nume,telefon);
                    if(val > 0){
                        Toast.makeText(RegisterActivity.this,"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Registeration Error",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


package com.project.ticketsystem.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.project.ticketsystem.Database.databaseTable.user;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;

public class RegisterPage extends AppCompatActivity {
    private EditText Rfullname, Remail, Rpassword, Rcomfirmpassword;
    private Button Rregister;
    public ProgressDialog pd;
    public Database db;
    public TextView toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Rfullname = findViewById(R.id.ret_fllname);
        Remail = findViewById(R.id.ret_email);
        Rpassword = findViewById(R.id.ret_password);
        Rcomfirmpassword = findViewById(R.id.ret_comfirmpassword);
        Rregister = findViewById(R.id.rbtn_Register);
        toLogin = findViewById(R.id.rtv_tologin);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(RegisterPage.this, Login.class);
                startActivity(in);
                finish();
            }
        });

        pd = new ProgressDialog(this);
        pd = new ProgressDialog(this);
        pd.setMessage("Please Wait....");
        pd.setCancelable(false);
      /*  Rcomfirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass=Rpassword.getText().toString();
                if(s.length()>0 && pass.length()>0){
                    if(Rcomfirmpassword.equals(pass)){

                    }else{
                        Rcomfirmpassword.setError("Password Not Match");
                    }
                }
            }
        });*/
        Rregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataProcess();
            }
        });

    }

    public void saveDataProcess() {

        db = Database.getINSTANCE(RegisterPage.this);
        if (Rfullname.getText().toString().equalsIgnoreCase("")) {
            Rfullname.setError("Please Enter Full Name");
        } else if (Remail.getText().toString().equalsIgnoreCase("")
        ) {
            Remail.setError("Please enter Email");
        } else if (!Remail.getText().toString().contains("@gmail.com")){
            Remail.setError("Something was wrong!");
        } else if (Rpassword.getText().toString().equalsIgnoreCase("")) {
            Rpassword.setError("Please Enter Password");
        } else if (Rcomfirmpassword.getText().toString().equalsIgnoreCase("")
        ) {
            Rcomfirmpassword.setError("Please Enter Comfirm Password");
        } else if (!Rcomfirmpassword.getText().toString().equals(Rpassword.getText().toString())) {
            Rcomfirmpassword.setError("Does not match with the Password you entered!");
        } else {
            pd.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pd.dismiss();
                    user u = new user();
                    u.setName(Rfullname.getText().toString());
                    u.setEmail(Remail.getText().toString());
                    u.setPassword(Rpassword.getText().toString());
                    u.setImg("Noupload");
                    db.userDao().addUser(u);
                    Rfullname.setText("");
                    Remail.setText("");
                    Rpassword.setText("");
                    Rcomfirmpassword.setText("");
                    Toast.makeText(RegisterPage.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    Intent r=new Intent(RegisterPage.this,Login.class);
                    finish();
                    startActivity(r);


                }
            }, 2000);

        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i=new Intent(RegisterPage.this,Login.class);
            i.putExtra("key","backregister");
            finish();
            startActivity(i);
        }

        return true;
    }

    private void AlertBoxCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterPage.this);
        builder.setTitle("Alert!");
        builder.setMessage("Do you want to exit?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);

                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

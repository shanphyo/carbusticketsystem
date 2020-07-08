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

import java.util.List;

public class Login extends AppCompatActivity {
    public EditText lusername, lpassword;
    public Button lsignin;
    public Database db;
    public TextView toSignUp;
    ProgressDialog pd1;
    public TextView forgotpas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        pd1 = new ProgressDialog(Login.this);
        pd1.setMessage("Please Wait...");

        lusername = findViewById(R.id.let_username);
        lpassword = findViewById(R.id.let_password);
        lsignin = findViewById(R.id.lbtn_Login);
        toSignUp = findViewById(R.id.ltv_toregister);
        forgotpas=findViewById(R.id.login_forgot);
        forgotpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Login.this,resetPassword.class);
                startActivity(in);
            }
        });
        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login.this, RegisterPage.class);
                startActivity(in);
                finish();
            }
        });
        lsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
    }

    private void checkUser() {
        if (lusername.getText().toString().equalsIgnoreCase("admin") && lpassword.getText().toString().equalsIgnoreCase("1234")) {
            pd1.show();
            pd1.setCancelable(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pd1.dismiss();
                    Intent in = new Intent(Login.this, AdminPage.class);
                    startActivity(in);
                    finish();
                }
            },2000);
        } else {
            String user1 = lusername.getText().toString();
            String pass = lpassword.getText().toString();
            db = Database.getINSTANCE(Login.this);
            final List<user> list = db.userDao().getSpecialUser(user1, pass);
            if (list.isEmpty()) {
                Toast.makeText(Login.this, "User Does Not Exist", Toast.LENGTH_SHORT).show();
            } else {

                pd1.show();
                pd1.setCancelable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd1.dismiss();
                        Intent in = new Intent(Login.this, ChooseClassActivity.class);
                        in.putExtra("key", "loginuser");
                        in.putExtra("ename", list.get(0).getName());
                        in.putExtra("eusername", list.get(0).getEmail());
                        in.putExtra("skey", list.get(0).getSyskey() + "");
                        in.putExtra("img", list.get(0).getImg());
                        startActivity(in);
                        finish();
                    }
                }, 2000);
            }

        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertBoxCustom();
        }

        return true;
    }

    private void AlertBoxCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
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

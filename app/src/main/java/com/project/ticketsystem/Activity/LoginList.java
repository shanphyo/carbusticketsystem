package com.project.ticketsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.ticketsystem.Adapter.LoginCustomAdapter;
import com.project.ticketsystem.Adapter.RecyclerDeligate;
import com.project.ticketsystem.Database.databaseTable.user;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;

import java.util.List;

public class LoginList extends AppCompatActivity implements RecyclerDeligate {
    public Database db;
    public LoginCustomAdapter adapter;
    RecyclerView rv;
    TextView backArrow;
    public TextView btnsearch,searcharrow;
    public EditText searchbox;
    public LinearLayout firstlayout,secondlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_list);
        db=Database.getINSTANCE(LoginList.this);
        backArrow=findViewById(R.id.back_arrowlogin);
        btnsearch=findViewById(R.id.btn_searchlogin);
        searcharrow=findViewById(R.id.search_arrowlogin);
        searchbox=findViewById(R.id.et_loginsearch);
        firstlayout=findViewById(R.id.loginfirstlayout);
        secondlayout=findViewById(R.id.loginesecondlayout);
        rv=findViewById(R.id.ll_rv);
        List<user>list=db.userDao().getUser();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        adapter=new LoginCustomAdapter(this,list,this);
        rv.setAdapter(adapter);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstlayout.setVisibility(View.GONE);
                secondlayout.setVisibility(View.VISIBLE);
            }
        });
        searcharrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondlayout.setVisibility(View.GONE);
                firstlayout.setVisibility(View.VISIBLE);
            }
        });
        searchbox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<user>list=null;
                String t=searchbox.getText().toString();
                if(!t.equalsIgnoreCase("")) {
                    String a = "%" + t + "%";
                    list = db.userDao().getsearchlogin(a);
                }else{
                    list=db.userDao().getUser();
                }
                adapter.AddAppendData(list);
            }

        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginList.this, AdminPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onRecyclerClick(int position) {
        db= Database.getINSTANCE(LoginList.this);
        db.userDao().deleteuser(position);
        List<user>list=db.userDao().getUser();
        adapter.AddAppendData(list);
    }

    @Override
    public void onRecyclerClick1(int position, String type, int syskey, String name, String time, String price) {

    }

    @Override
    public void onRecyclerClick2(int position) {

    }

}

package com.project.ticketsystem.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.ticketsystem.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView map=findViewById(R.id.img_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitute=16.806989;
                double longitude=96.161562;
                String label="Ticket System";
                String uriBegin="geo:"+latitute+","+longitude;
                String query=latitute+","+longitude+"("+label+")";
                String encodeQuery= Uri.encode(query);
                String uriString=uriBegin+"?q="+encodeQuery+"&z=16";
                Uri uri=Uri.parse(uriString);
                Intent in=new Intent(android.content.Intent.ACTION_VIEW,uri);
                startActivity(in);
            }
        });
    }
}

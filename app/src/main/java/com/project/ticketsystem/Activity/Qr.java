package com.project.ticketsystem.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;
import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.fileupload;
import com.project.ticketsystem.Shared.util;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Qr extends AppCompatActivity {
    String TAG = "GenerateQRCode";
    ImageView qrImage;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String inputValue;
    String key, name, nrc, expressname, sdate, stime, seat,from,to;
    Button btn_home,saveQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        qrImage = findViewById(R.id.img_qr);
        saveQr=findViewById(R.id.btn_saveqr);
        saveQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileupload.saveImageGallery(bitmap);
                Toast.makeText(Qr.this, "Save Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        btn_home = findViewById(R.id.btn_go_to_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Qr.this, HomePage.class);
                intent.putExtra("key","qrtohomepage");
                finish();
                startActivity(intent);

            }
        });

        key = getIntent().getStringExtra("key");
        if (key.equalsIgnoreCase("qr")) {
            name = getIntent().getStringExtra("name");
            nrc = getIntent().getStringExtra("nrc");
            expressname = getIntent().getStringExtra("expressname");
            sdate = getIntent().getStringExtra("sdate");
            stime = getIntent().getStringExtra("stime");
            seat = getIntent().getStringExtra("seat");
            from=getIntent().getStringExtra("from");
            to=getIntent().getStringExtra("to");
        }
        qrGenerator();
    }

    public void qrGenerator() {
        inputValue = "                       " +expressname+"\n"+
                "      Name              : " + name + "\n" +
                "      National Card: " + nrc + "\n" +
                "      Route              : "+from+" - "+to+"\n"+
                "      Seat No           :" + seat + "\n" +
                "      Date                 : " + util.stringToDate(sdate) + "\n" +
                "      Depature time:  " + util.twentyfourToTwelve(stime);
        if (inputValue.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    inputValue, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                qrImage.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Qr.this);
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

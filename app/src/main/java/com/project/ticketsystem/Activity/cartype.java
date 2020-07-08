package com.project.ticketsystem.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.project.ticketsystem.Database.databaseTable.car;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.fileupload;

import java.io.IOException;
import java.util.List;

public class cartype extends AppCompatActivity {
    public ImageView imageuplod;
    private int GALLERY = 1, CAMERA = 2;
    private EditText name, phno, noseat;
    private Spinner type;
    private Button savebtn;
    private Database db;
    public String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartype);

        imageuplod = findViewById(R.id.ctiv_profile);
        name = findViewById(R.id.ctet_name);
        phno = findViewById(R.id.ctet_phone);
        type = findViewById(R.id.ctsp_choosetype);
        noseat = findViewById(R.id.ctet_noseat);
        savebtn = findViewById(R.id.ctbtn_save);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageuplod.getDrawable() == null) {
                    Toast.makeText(cartype.this, "Please choose the car photo!", Toast.LENGTH_SHORT).show();
                }else if(imageuplod.getDrawable().equals("")){
                    Toast.makeText(cartype.this, "Please choose the car photo!", Toast.LENGTH_SHORT).show();
                }

                else if (name.getText().length() == 0) {
                    name.setError("Please fill all the information!");
                } else if (phno.getText().length() == 0) {
                    phno.setError("Please fill all the information!");
                } else if (noseat.getText().length() == 0) {
                    noseat.setError("Please fill all the information!");
                } else {
                    saveDataProcess();
                }
            }
        });
        requestMultiplePermissions();
        //for imageupload in on create
        imageuplod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        //=====upload

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.choosecar , R.layout.spinner_item);
        adapter4.setDropDownViewResource(R.layout.spinner_dropdown_item);
        type.setAdapter(adapter4);

    }

    //======oncreate
    private void saveDataProcess() {
        db = Database.getINSTANCE(cartype.this);
        String cartype = "";
        if (type.getSelectedItem().toString().equalsIgnoreCase("First Class - (2+1)")) {
            cartype = "(2+1)";
        } else {
            cartype = "(2+2)";
        }
        car c = new car();
        String t=null;
        if(path!=null){
             t = path;
        }else{
            t="nologo";

        }

        c.setName(name.getText().toString());
        c.setPhNo(phno.getText().toString());
        c.setType(cartype);
        c.setNoOfSeat(noseat.getText().toString());
        c.setImage(t);
        db.carDao().addCar(c);
        Intent in = new Intent(com.project.ticketsystem.Activity.cartype.this, cartypelist.class);
        finish();
        startActivity(in);

    }

    //===upload
    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    path = fileupload.saveImage(bitmap);

                    Toast.makeText(cartype.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageuplod.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(cartype.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageuplod.setImageBitmap(thumbnail);
            fileupload.saveImage(thumbnail);
            Toast.makeText(cartype.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }

    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
    //====upload
}

package com.project.ticketsystem.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.project.ticketsystem.Database.databaseTable.user;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.fileupload;

import java.io.IOException;
import java.util.List;

public class EditProfile extends AppCompatActivity {
    public ImageView etprofile;
    public EditText etfullname,etusername,etoldpassword,etnewpassword;
    public CheckBox etcp;
    public Button Updatep;
    private int GALLERY = 1, CAMERA = 2;
    private Database db;
    public  String path;
    String key,sname,susername,skey,img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        etprofile=findViewById(R.id.epiv_profile);
        etfullname=findViewById(R.id.eptv_name);
        etusername=findViewById(R.id.eptv_username);
        etoldpassword=findViewById(R.id.eptv_oldpassword);
        etnewpassword=findViewById(R.id.eptv_newpassword);
        etcp=findViewById(R.id.epr_changepass);
        Updatep=findViewById(R.id.epbtn_save);
        requestMultiplePermissions();
        key=getIntent().getStringExtra("key");
        if(key.equalsIgnoreCase("editprofile")){
            sname=getIntent().getStringExtra("epname");
            susername=getIntent().getStringExtra("eusername");
            skey=getIntent().getStringExtra("skey");
            img=getIntent().getStringExtra("img");
            if(img.equalsIgnoreCase("Noupload")){

            }else{
                etprofile.setImageURI(Uri.parse(img));
            }
            etfullname.setText(sname);
            etusername.setText(susername);
        }
      etcp.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
              etoldpassword.setVisibility(View.VISIBLE);
              etnewpassword.setVisibility(View.VISIBLE);
              etcp.setVisibility(View.GONE);
          }
      });
        Updatep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=Database.getINSTANCE(EditProfile.this);
                if(etoldpassword.getText().length()==0){
                    Updatedata();

                }else{
                    List<user> list=db.userDao().getSpecialUser(susername,etoldpassword.getText().toString());
                    if(list.isEmpty()){
                        etoldpassword.setError("password is incorrect");
                    }else{
                        Updatedataandpassword();
                    }

                }
            }
        });





        etprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

    }
    public void Updatedataandpassword(){
        db=Database.getINSTANCE(EditProfile.this);
        if(path==null){
            path=img;
        }
        db.userDao().updatedataandpassword(etfullname.getText().toString(),etnewpassword.getText().toString(),path,skey);
       Intent in=new Intent(EditProfile.this,ChooseClassActivity.class);
       in.putExtra("key","update");
       in.putExtra("skey",skey);
       finish();
       startActivity(in);
        Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();

    }
    public void Updatedata(){
        if(path==null){
            path=img;
        }
        db=Database.getINSTANCE(EditProfile.this);
        db.userDao().updatedata(etfullname.getText().toString(),path,skey);
        Intent in=new Intent(EditProfile.this,ChooseClassActivity.class);
        in.putExtra("key","update");
        in.putExtra("skey",skey);
        finish();
        startActivity(in);
        Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();
    }



    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
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

                    Toast.makeText(EditProfile.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    etprofile.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(EditProfile.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            etprofile.setImageBitmap(thumbnail);
            fileupload.saveImage(thumbnail);
            Toast.makeText(EditProfile.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
    private void  requestMultiplePermissions() {
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
}

package com.example.sakshi.runtimecamerapermission;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaring and associating buttons with their ids
        Button checkpermission = (Button)findViewById(R.id.check);
        Button askpermission = (Button)findViewById(R.id.ask);
        //on click listener for checkpermission button
        checkpermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPermissionGranted()){
                    //prints toast "Permission Granted" if permission is granted i.e., if isPermissionGranted is true
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }else{
                    //prints Toast "Permission Denied. Request Permission First!" if permission is denied
                    Toast.makeText(MainActivity.this, "Permission Denied. Request Permission First!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //onclick listener for askpermission button
        askpermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isPermissionGranted()){
                    //calls method requestPermission if permission is denied i.e., if isPermissionGranted is false
                    requestPermission();
                }else{
                    //prints this toast if permission is not granted
                    Toast.makeText(MainActivity.this, "Permissions are already granted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void requestPermission() {
        //requests for camera permission
        //ActivityCompat.requestPermissions(activity, stringof permissions required, request code);
        ActivityCompat.requestPermissions(this, new String[]{CAMERA},101);
    }

    private boolean isPermissionGranted() {

        boolean isGranted=false;
        //checks for permission
        int result = ActivityCompat.checkSelfPermission(getApplicationContext(),CAMERA);
        if(result== PackageManager.PERMISSION_GRANTED){
            //set isGranted to true if permission is granted
            isGranted=true;
        }
        //return value
        return  isGranted;
    }
}

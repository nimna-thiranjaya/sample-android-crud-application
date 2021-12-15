package com.example.samplecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samplecrud.Database.DBHelper;
import com.example.samplecrud.Database.UserMaster;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView edName,edAge, edUsreName,edPassword;
    Button btnselectall,btnadd,btnupdate,btndelete,btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.boxName);
        edAge= findViewById(R.id.boxAge);
        edUsreName = findViewById(R.id.boxUname);
        edUsreName.setFocusable(false);
        edPassword = findViewById(R.id.boxPassword);
        btnadd = findViewById(R.id.addbtn);
        btnselectall = findViewById(R.id.seletcAllbtn);
        btnupdate = findViewById(R.id.updatebtn);
        btndelete = findViewById(R.id.deletebtn);
        btnsignup = findViewById(R.id.signinbtn);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInfo(v);
            }
        });

        btnselectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAll(v);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateinfo(v);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteusr(v);
            }
        });

    }



    public void addInfo(View view){
        String name = edName.getText().toString();
        String age = edAge.getText().toString();
        String username = edUsreName.getText().toString();
        String password = edPassword.getText().toString();

        DBHelper dbHelper = new DBHelper(this);

        if(name.isEmpty() || username.isEmpty() || password.isEmpty() || age.isEmpty()){
            Toast.makeText(this, "Enter values", Toast.LENGTH_SHORT).show();
        }else {
            dbHelper.addInfo(name,age,username,password);
            Toast.makeText(this, "added successful", Toast.LENGTH_SHORT).show();
            edName.setText("");
            edAge.setText("");
            edUsreName.setText("");
            edPassword.setText("");
        }
    }

    public void viewAll(View view){
        DBHelper dbHelper = new DBHelper(this);
        List info = dbHelper.readAllInfo();

        String[] infoArray = (String[]) info.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("User Details");

        builder.setItems(infoArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String data = infoArray[i];

                String[] x = data.split(",");

                edName.setText(x[0]);
                edAge.setText(x[1]);
                edUsreName.setText(x[2]);
                edPassword.setText(x[3]);

            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void updateinfo(View view){
        DBHelper dbHelper = new DBHelper(this);

        String name = edName.getText().toString();
        String age = edAge.getText().toString();
        String username = edUsreName.getText().toString();
        String password = edPassword.getText().toString();

        if(name.isEmpty() || age.isEmpty() || username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Enter values", Toast.LENGTH_SHORT).show();
        }else {
            dbHelper.updateInfo(view,name, age, username, password);
            edName.setText("");
            edAge.setText("");
            edUsreName.setText("");
            edPassword.setText("");
        }
    }
     public void deleteusr(View view){
        DBHelper dbHelper = new DBHelper(this);
        String username = edUsreName.getText().toString();

        if(username.isEmpty()) {
            Toast.makeText(this, "Enter user name", Toast.LENGTH_SHORT).show();
        }else {
            dbHelper.deleteInfo(view,username);
            edName.setText("");
            edAge.setText("");
            edUsreName.setText("");
            edPassword.setText("");
        }
     }

}
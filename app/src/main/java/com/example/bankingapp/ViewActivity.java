package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {
    String sname,semail,sphone,samount,sid;
    Button btnTransfer;
    TextView displayName,displayEmail,displayPhone,displayAmount;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        db=new DatabaseHandler(ViewActivity.this);

        btnTransfer=(Button) findViewById(R.id.transfer);
        modifyUserInterface();


    }
    private void modifyUserInterface(){
        Intent intent=getIntent();
        sname=intent.getStringExtra("name");
        semail=intent.getStringExtra("email");
        sphone=intent.getStringExtra("phone");
        samount=intent.getStringExtra("amount");
        sid=intent.getStringExtra("id");
        displayName=(TextView) findViewById(R.id.nameBox);
        displayEmail=(TextView) findViewById(R.id.emailBox);
        displayPhone=(TextView) findViewById(R.id.phoneBox);
        displayAmount=(TextView) findViewById(R.id.amtBox);

        displayName.setText("Name: "+sname);
        displayEmail.setText("Email: "+semail);
        displayPhone.setText("Phone no.: "+sphone);
        displayAmount.setText("Amount: "+samount);

        btnTransfer.setOnClickListener(ViewActivity.this);


    }

    @Override
    public void onClick(View v) {
        Intent intentt=new Intent(ViewActivity.this,TransferActivity.class);
        intentt.putExtra("amt",samount);
        intentt.putExtra("id",sid);
        startActivity(intentt);
    }
}
package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class TransferActivity extends AppCompatActivity implements View.OnClickListener {
    EditText tname,tAmount;
    DatabaseHandler db;
    Button btnOkay,batnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        tname=(EditText) findViewById(R.id.tId);
        tAmount=(EditText) findViewById(R.id.tAmount);
        db=new DatabaseHandler(TransferActivity.this);
        btnOkay=findViewById(R.id.button);
        btnOkay.setOnClickListener(TransferActivity.this);
        batnBack=(Button) findViewById(R.id.button1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                int id=Integer.parseInt(tname.getText().toString());
                double amount=Double.parseDouble(tAmount.getText().toString());
                UserD toBeModified=db.returnMartialObjectByID(id);
                Intent intent=getIntent();
                String amountOfCurrentUser=intent.getStringExtra("amt");
                String sid=intent.getStringExtra("id");
                double amtUser=Double.parseDouble(amountOfCurrentUser);
                if(amount>amtUser){
                    Toast.makeText(TransferActivity.this, "Your account does not have enough balance!", Toast.LENGTH_SHORT).show();
                }
                else{
                    double newAmountOfCurr=amtUser-amount;
                    double newOfSelected=toBeModified.getAmount()+amount;
                    db.modifyUser(id,toBeModified.getName(),newOfSelected,toBeModified.getEmail(),toBeModified.getPhone());
                    UserD tUser= db.returnMartialObjectByID(Integer.parseInt(sid));
                    db.modifyUser(Integer.parseInt(sid),tUser.getName(),newAmountOfCurr,tUser.getEmail(),tUser.getPhone());
                    Toast.makeText(TransferActivity.this, "Your money has been successfully transmitted !", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button1:
                Intent intennt=new Intent(TransferActivity.this,MainActivity.class);
                startActivity(intennt);
                break;

        }

    }
}
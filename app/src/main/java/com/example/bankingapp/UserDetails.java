package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserDetails extends AppCompatActivity implements View.OnClickListener {
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        db=new DatabaseHandler(UserDetails.this);

        UserD user1=new UserD(0,"Rama",7456, "rama123@gmail.com", 8595854);
        UserD user2=new UserD(0,"Shyam",7456, "shyam123@gmail.com",895);
        UserD user3=new UserD(0,"Preeti",7456, "preeti123@gmail.com",677);
        UserD user4=new UserD(0,"Sweta",7456, "sweta123@gmail.com",7655);
        UserD user5=new UserD(0,"Rakhi",7456, "rakhi123@gmail.com",6544);
        UserD user6=new UserD(0,"Jennifer",3453,"jennifer123@gmail.com",5326227);
        UserD user7=new UserD(0,"Joe",5433,"joe123@gmail.com",5326227);
        UserD user8=new UserD(0,"Jack",2000,"jack123@gmail.com",5326227);
        UserD user9=new UserD(0,"Billu",4353,"billu123@gmail.com",5326227);
        UserD user10=new UserD(0,"Harsh",98531,"harsh123@gmail.com",5326227);
        UserD user11=new UserD(0,"Mala",35441,"mala123@gmail.com",53264545);
        UserD user12=new UserD(0,"Pihu",54559,"pihu123@gmail.com",5326227);

        db.addMartialArt(user1);
        db.addMartialArt(user2);
        db.addMartialArt(user3);
        db.addMartialArt(user4);
        db.addMartialArt(user5);
        db.addMartialArt(user6);
        db.addMartialArt(user7);
        db.addMartialArt(user8);
        db.addMartialArt(user9);
        db.addMartialArt(user10);
        db.addMartialArt(user11);
        db.addMartialArt(user12);

        modifyUserInterface();
    }

    private void modifyUserInterface(){
        ArrayList<UserD> array=db.getAllObjects();
        if(array.size()>0){
            ScrollView scrollView=new ScrollView(UserDetails.this);
            GridLayout gridLayout=new GridLayout(UserDetails.this);
            gridLayout.setRowCount(array.size());
            gridLayout.setColumnCount(6);

            TextView[] idTextView=new TextView[array.size()];
            TextView[][] edtNamePricesAndColors=new TextView[array.size()][4];
            Button[] modifyButton=new Button[array.size()];
            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);
            int screenWidth=screenSize.x;
            int index=0;

            for(UserD martialArt: array){
                idTextView[index]=new TextView(UserDetails.this);
                idTextView[index].setGravity(Gravity.CENTER);
                idTextView[index].setText(martialArt.getId()+"");
                //idTextView[index].setId(martialArt.getId());

                edtNamePricesAndColors[index][0]=new TextView(UserDetails.this);
                edtNamePricesAndColors[index][1]=new TextView(UserDetails.this);
                edtNamePricesAndColors[index][2]=new TextView(UserDetails.this);
                edtNamePricesAndColors[index][3]=new TextView(UserDetails.this);

                edtNamePricesAndColors[index][0].setText(martialArt.getName());
                edtNamePricesAndColors[index][1].setText(martialArt.getEmail());
                edtNamePricesAndColors[index][2].setText(martialArt.getPhone()+"");
                edtNamePricesAndColors[index][2].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamePricesAndColors[index][3].setText(martialArt.getAmount()+"");
                edtNamePricesAndColors[index][3].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamePricesAndColors[index][0].setId(martialArt.getId()+10);
                edtNamePricesAndColors[index][1].setId(martialArt.getId()+20);
                edtNamePricesAndColors[index][2].setId(martialArt.getId()+30);
                edtNamePricesAndColors[index][3].setId(martialArt.getId()+40);

                modifyButton[index]=new Button(UserDetails.this);
                modifyButton[index].setText("View");
                modifyButton[index].setId(martialArt.getId());
                modifyButton[index].setOnClickListener(UserDetails.this);

                gridLayout.addView(idTextView[index], (int) (screenWidth * 0.07), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePricesAndColors[index][0],(int)(screenWidth*0.15),ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePricesAndColors[index][1], (int)(screenWidth*0.20),ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePricesAndColors[index][2],(int)(screenWidth*0.20),ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePricesAndColors[index][3],(int)(screenWidth*0.15),ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(modifyButton[index], (int) (screenWidth*0.25),ViewGroup.LayoutParams.WRAP_CONTENT);

                index++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);




        }




    }

    @Override
    public void onClick(View v) {
        int bankingUserId= v.getId();

        TextView textName=(TextView)  findViewById(bankingUserId+10);
        TextView textEmail=(TextView) findViewById(bankingUserId+20);
        TextView textPhone=(TextView) findViewById(bankingUserId+30);
        TextView textAmount=(TextView)findViewById(bankingUserId+40);

        String txtName=textName.getText().toString();
        String txtEmail=textEmail.getText().toString();
        String txtPhone=textPhone.getText().toString();
        String txtAmt=textAmount.getText().toString();


        Intent intent=new Intent(UserDetails.this,ViewActivity.class);

        intent.putExtra("name",txtName);
        intent.putExtra("email",txtEmail);
        intent.putExtra("phone",txtPhone);
        intent.putExtra("amount",txtAmt);
        intent.putExtra("id",bankingUserId+"");
        startActivity(intent);
    }
}
package com.example.sumuppro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv;
    Button btnPhoto,btnSaveDialog,btnSave,btnSurvey;
    Bitmap bitmap;
    SharedPreferences sp;
    Dialog d;
    EditText etResName,etCheffName , etAvgPrice;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView)findViewById(R.id.iv);
        etResName=(EditText)findViewById(R.id.etResName);
        etCheffName=(EditText)findViewById(R.id.etCheffName);
        etAvgPrice = (EditText)findViewById(R.id.etAvgPrice);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        btnPhoto=(Button)findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(this);
        sp=getSharedPreferences("details1",0);
        btnSave=(Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnSurvey = (Button) findViewById(R.id.btnSurvey);
        btnSurvey.setOnClickListener(this);
        //----------------------------------
        Intent intent=getIntent();
        String ChefRate = intent.getExtras().getString("Chef_rate");
        String ServiceRate = intent.getExtras().getString("Slider_service_rate");
        String PriceRate = intent.getExtras().getString("Switch_price_rate");
        tvDisplay.setText("Your rate for the Chef " + ChefRate + " Your service rate " + ServiceRate + "Your Price rate " + PriceRate);
    }

    public void createLoginDialog()
    {
        d= new Dialog(this);
        d.setContentView(R.layout.save_dialog);
        d.setTitle("Save");
        d.setCancelable(true);
        btnSaveDialog=(Button)d.findViewById(R.id.btnSaveDialog);
        btnSaveDialog.setOnClickListener(this);
        d.show();
    }


    @Override
    public void onClick(View view) {
        if(view==btnPhoto)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,0);
        }
        else if(view==btnSave)
        {
            createLoginDialog();
        }
        else if(view==btnSaveDialog)
        {
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("Restuarnt Name",etResName.getText().toString());
            editor.putString("Cheff Name",etCheffName.getText().toString());
            editor.putString("Avarge Price",etAvgPrice.getText().toString());
            editor.commit();
            Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
            d.dismiss();
        }
        else if(view == btnSurvey){
            Intent intent=new Intent(this,survey_activity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0)
        {
            if(resultCode==RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                iv.setImageBitmap(bitmap);
            }
        }
    }

}
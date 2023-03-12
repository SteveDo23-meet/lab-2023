package com.example.sumuppro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

public class survey_activity extends AppCompatActivity implements View.OnClickListener {
    EditText etRateCheff;
    Slider Slider;
    Switch simpleSwitch;
    Button btnSubmit,btnSubmitDialog;
    SharedPreferences sp;
    Dialog d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        etRateCheff = (EditText) findViewById(R.id.etRateCheff);
        Slider = (Slider)findViewById(R.id.slider);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
        simpleSwitch.setChecked(true);
        sp=getSharedPreferences("details1",0);

    }
    public void createLoginDialog()
    {
        d= new Dialog(this);
        d.setContentView(R.layout.survey_dialog);
        d.setTitle("Submit");
        d.setCancelable(true);
        btnSubmitDialog=(Button)d.findViewById(R.id.btnSubmitDialog);
        btnSubmitDialog.setOnClickListener(this);
        d.show();
    }
//    public int getResult(boolean Switch) {
//        int result = 0;
//        if (Switch)
//        {
//            result = 100;
//        }
//        else if (!(Switch))
//        {
//            result = 20;
//        }
//        return result;
//    }

    @Override
    public void onClick(View view) {
        if(view==btnSubmit)
        {
            createLoginDialog();
        }
        else if (view == btnSubmitDialog)
        {
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("Chef_rate",etRateCheff.getText().toString());
            editor.putString("Slider_service_rate",Slider.toString());
            editor.putString("Switch_price_rate",simpleSwitch.toString());
            editor.commit();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
            d.dismiss();
        }
    }
}
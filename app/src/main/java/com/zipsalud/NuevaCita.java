package com.zipsalud;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class NuevaCita extends AppCompatActivity {

    TextView tv, tvh;
    Calendar mCurrentDate;
    int day, month, year, minute, hour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_cita);
        tv = (TextView) findViewById(R.id.tvFcha);
        tvh = (TextView) findViewById(R.id.tvHra);
        mCurrentDate = Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);
        hour = mCurrentDate.get(Calendar.HOUR_OF_DAY);
        minute = mCurrentDate.get(Calendar.MINUTE);

        month = month+1;

        tv.setText(day+"/"+month+"/"+year);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(NuevaCita.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        tv.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        tvh.setText(hour+":"+minute);
        tvh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(NuevaCita.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvh.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,false);
                tpd.show();
            }
        });
    }
    public void citas(View view){
        Intent i = new Intent(this,Citas.class);
        startActivity(i);

    }
}

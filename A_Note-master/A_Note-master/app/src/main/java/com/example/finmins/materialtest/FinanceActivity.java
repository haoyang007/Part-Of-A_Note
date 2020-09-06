package com.example.finmins.materialtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FinanceActivity extends AppCompatActivity {
    private Button submit;
    // 日期
    private TextView dateView;
    private TextView currAmount;
    private Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        submit = findViewById(R.id.addButton);
        dateView = findViewById(R.id.addDate);
        currAmount = findViewById(R.id.currAmount);
        Button cancelBtn = findViewById(R.id.cancelButton);
        Button history = findViewById(R.id.history);
        dateView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final int year = Calendar.getInstance().get(Calendar.YEAR);
                final int month = Calendar.getInstance().get(Calendar.MONTH);
                final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                @SuppressLint("ResourceType") DatePickerDialog datepicker = new DatePickerDialog(FinanceActivity.this, 2, new DatePickerDialog.OnDateSetListener() {
                    // 绑定监听器
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String dateStr = year+"-"+month+"-"+day;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            date = sdf.parse(dateStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        // 添加时间
                        dateView.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                    }
                }, year, month, day);
                datepicker.show();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FinanceActivity.this,HistoricalbillsActivity.class);
                startActivity(intent);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}

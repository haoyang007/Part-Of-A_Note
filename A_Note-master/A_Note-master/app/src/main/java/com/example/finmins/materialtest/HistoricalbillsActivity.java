package com.example.finmins.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finmins.materialtest.Model.HistoricalViewModel;

import java.util.Map;

public class HistoricalbillsActivity extends AppCompatActivity {
//    private HistoricalViewModel historicalViewModel;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historicalbills);

    }

}

package com.example.finmins.materialtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.daimajia.swipe.util.Attributes;
import com.example.finmins.materialtest.Model.HistoricalViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchBillsActivity extends AppCompatActivity {
//    private  final String TAG = "SearchBillsActivity.this";
    private HistoricalViewModel historicalViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Bills> billsList = new ArrayList<Bills>(); //账单列表
    private BillsAdapter billsAdapter;  // Bills适配器
    private RecyclerView recyclerView;  //RecycleView
    private ImageButton searchButton;   //确认搜索按钮
    private String searchStr ;          //搜索内容
    private EditText searchEdit;        //搜索框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bills);
        searchButton = findViewById(R.id.searchInSearch); //搜索按钮
        searchEdit = findViewById(R.id.editTextInSearch); //搜索框
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayoutInSearch);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh();
                swipeRefreshLayout.setRefreshing(false);
            }});
        //点击搜索图标
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取搜索内容
                searchStr = searchEdit.getText().toString();
                //初始化搜查的账单数据
                Refresh();
            }
        });

    }
    //将查询到的数据读取到listView中
    private void initBills(){
//       从数据库中获取账单列表bills
        List<Bills> bills = new ArrayList<Bills>();
        Bills bill1 = new Bills(new Date(),"生活支出",1000.00);
        bills.add(bill1);
        Bills bill2 = new Bills(new Date(),"学习支出",800.00);
        bills.add(bill2);
        Bills bill3 = new Bills(new Date(),"其他支出",1200.00);
        bills.add(bill3);
        Bills bill4 = new Bills(new Date(),"娱乐支出",600.00);
        bills.add(bill4);
        for(Bills bill : bills){
            //对每个事件进行字符搜索
            if(isValid(bill)){
                billsList.add(bill);
            }
        }
    }

    private boolean isValid(Bills bill){
        return bill.getDate().toString().contains(searchStr)
                ||bill.getType().contains(searchStr)
                ||String.valueOf(bill.getAmount()).contains(searchStr);
    }
    public void initEvents() {

    }
    //刷新账单列表
    public void Refresh(){
        billsList.clear();
        recyclerView=findViewById(R.id.recycler_viewInSearch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        billsAdapter = new BillsAdapter(this, billsList,historicalViewModel);
        recyclerView.setAdapter(billsAdapter);
        initBills();
        billsAdapter.notifyDataSetChanged();
    }
}

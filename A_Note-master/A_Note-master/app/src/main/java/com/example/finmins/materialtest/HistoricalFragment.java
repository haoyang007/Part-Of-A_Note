package com.example.finmins.materialtest;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finmins.materialtest.Model.HistoricalViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HistoricalFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;  //界面视图
    private HistoricalViewModel mViewModel;
    private List<Bills> billsList = new ArrayList<Bills>(); //账单列表
    private BillsAdapter adapter;   //账单属性适配器
    private LinearLayoutManager linearLayoutManager;   //item线性布局
    private TextView dateView;   //账单时间View
    private TextView amountView; //账单金额View
    private ImageView searchBills; //搜索账单View
    public HistoricalFragment(){

    }

    public static HistoricalFragment newInstance() {
        return new HistoricalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       view =  inflater.inflate(R.layout.fragment_historicalbills, container, false);
       return view;
    }

    /** 活动运行后调用 */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(HistoricalViewModel.class);
        // TODO: Use the ViewModel
        initControl();
        initData();
        Log.d(TAG, "passWay: billAmount");
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        //        imageButton = findViewById(R.id.searchBills);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent searchIntent = new Intent(HistoricalbillsActivity.this,SearchBillsActivity.class);
//                startActivity(searchIntent);
//            }
//        });
//        historicalViewModel = new ViewModelProvider(this).get(HistoricalViewModel.class);
//        Intent intent = getIntent();
        // 添加点击事件（排序）
        /**将账单进行排序（时间、金额）*/
        dateView = getView().findViewById(R.id.billDate);
        amountView = getView().findViewById(R.id.billAmount);
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(billsList!=null){

                    Collections.sort(billsList, new Comparator<Bills>() {
                        @Override
                        public int compare(Bills b1, Bills b2) {
                            if (b1.getDate().getTime() > b2.getDate().getTime()){
                                return 1;
                            } else if (b1.getDate().getTime() < b2.getDate().getTime()){
                                return -1;
                            }else{
                                return 0;
                            }
                        }
                    });
                }
                initControl();
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
        amountView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (billsList != null){
                    Collections.sort(billsList,new Comparator<Bills>() {
                        @Override
                        public int compare(Bills b1, Bills b2) {
                            //按金额排序
                            if(b1.getAmount() > b2.getAmount()) {
                                return 1;
                            }else if(b1.getAmount() < b2.getAmount()) {
                                return -1;
                            }else {
                                return 0;
                            }
                        }
                    });
                }
                initControl();
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//                Log.d(TAG, "Amount1："+billsList.get(0).getAmount());
            }
        });
        /*
           搜索账单
        */
        searchBills = getView().findViewById(R.id.searchBills);
        searchBills.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent searchIntent = new Intent(getActivity(),SearchBillsActivity.class);
                startActivity(searchIntent);
            }
        });

    }
        /** 从数据库中读取数据 */
        private void initData(){
            for(int i=1;i<=5;i++){
                Date date = new Date();
                long time = date.getTime();
                time += 1000*60*60*24*i;
//                Log.d(TAG, "Time: "+time);
                date.setTime(time);
                Bills bill1 = new Bills(date,"生活支出",1000.00);
                billsList.add(bill1);
                Bills bill2 = new Bills(date,"学习支出",800.00);
                billsList.add(bill2);
                Bills bill3 = new Bills(date,"其他支出",1200.00);
                billsList.add(bill3);
                Bills bill4 = new Bills(date,"娱乐支出",600.00);
                billsList.add(bill4);
            }
        }
        /** 初始化元素 */
        private void initControl(){
            adapter = new BillsAdapter(getActivity(),billsList,mViewModel);
            recyclerView = view.findViewById(R.id.billsRecyclerView);
            linearLayoutManager = new LinearLayoutManager(getActivity());
            mViewModel = new ViewModelProvider(this).get(HistoricalViewModel.class);
        }
//        private void init(){
//            initControl();
//        }
}

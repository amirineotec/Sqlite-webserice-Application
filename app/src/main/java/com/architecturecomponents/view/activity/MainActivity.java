package com.architecturecomponents.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.architecturecomponents.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void dataBaseHelper(View view) {
        Intent intent = new Intent(view.getContext(), DatabaseHelperActivity.class);
        startActivity(intent);
    }

    public void roomSqlite(View view) {
        Intent intent = new Intent(view.getContext(), RoomActivity.class);
        startActivity(intent);
    }

    public void retrofit(View view) {
        Intent intent = new Intent(view.getContext(), RetrofitActivity.class);
        startActivity(intent);
    }

    public void asyncTask(View view) {
        Intent intent = new Intent(view.getContext(), AsyncTaskActivity.class);
        startActivity(intent);
    }

}

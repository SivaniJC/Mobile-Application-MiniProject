package com.example.food_app_sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.food_app_sample.Adapters.MainAdapter;
import com.example.food_app_sample.Models.MainModel;
import com.example.food_app_sample.databinding.ActivityMainBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.idle, "idle","15","Steam rice cake"));
        list.add(new MainModel(R.drawable.dosa, "dosa","60","Ragi dasa"));
        list.add(new MainModel(R.drawable.vada, "vada","12","crispy vada"));
        list.add(new MainModel(R.drawable.pesarattu, "pesarattu","70","crispy dosa"));
        list.add(new MainModel(R.drawable.pongal, "pongal","50","kara Pongal"));
        list.add(new MainModel(R.drawable.puttu, "puttu","42","steamed rice with a layer of coconut"));
        list.add(new MainModel(R.drawable.appam, "appam","30","Type of pancake"));
        MainAdapter adapter = new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
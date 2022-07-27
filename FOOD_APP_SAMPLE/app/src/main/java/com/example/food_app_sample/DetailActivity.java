package com.example.food_app_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.food_app_sample.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final DBHelper helper = new DBHelper(this);
        if (getIntent().getIntExtra("type", 0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");
            binding.detailImages.setImageResource(image);
            binding.pricelbl.setText(String.format("%d", price));
            binding.textView.setText(name);
            binding.detailDescription.setText(description);
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),binding.PhoneBox.getText().toString(), price,image,name,
                            description,Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }});
        } else {
            int id=getIntent().getIntExtra("id",0);
            Cursor cursor=helper.getOrderById(id);
            int image=cursor.getInt(4);
            binding.detailImages.setImageResource(image);
            binding.pricelbl.setText(String.format("%d", cursor.getInt(3)));
            binding.textView.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(5));

            binding.nameBox.setText(cursor.getString(1));
            binding.PhoneBox.setText(cursor.getString(2));
            binding.insertButton.setText("Update Now");
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated= helper.updateOrder(
                            binding.nameBox.getText().toString(),binding.PhoneBox.getText().toString(),
                            Integer.parseInt(binding.pricelbl.getText().toString()),image,
                            binding.detailDescription.getText().toString(),
                            binding.textView.getText().toString(),1,id
                            );
                    if(isUpdated)
                        Toast.makeText(DetailActivity.this,"Updated.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this,"Failed.", Toast.LENGTH_SHORT).show();
                }}); } }
}
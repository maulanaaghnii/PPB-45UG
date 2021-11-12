package com.example.recyclerviewrestaurantmenu;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailFoodActivity extends AppCompatActivity {
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        ImageView imgFood = findViewById(R.id.imgFood);
        TextView tvFoodName = findViewById(R.id.tvFoodName);
        TextView tvFoodDetail = findViewById(R.id.tvFoodDetail);
        TextView tvFoodPrice = findViewById(R.id.tvFoodPrice);

        Food food = getIntent().getParcelableExtra(ITEM_EXTRA);
        if(food != null){
            Glide.with(this)
                    .load(food.getPhoto())
                    .into(imgFood);
            tvFoodName.setText(food.getName());
            tvFoodPrice.setText(food.getPrice());
            tvFoodDetail.setText(food.getDetail());
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Food");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}

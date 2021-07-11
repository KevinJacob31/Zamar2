package com.soarhigh.zamar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soarhigh.zamar.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    ArrayList<POJOSlide> pojoSlideArrayList;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        binding.myrecyclerview.setLayoutManager(layoutManager);
        setContentView(binding.getRoot());

    }

    public void prepare_gallery_with_photos()
    {
        pojoSlideArrayList = new ArrayList<>();

        pojoSlideArrayList.add(new POJOSlide("Oneplus 5","https://www.youtube.com/watch?v=sE80h3_bv0c",""));

        CustomAdapterVideo customAdapterVideo= new CustomAdapterVideo(pojoSlideArrayList,MainActivity.this);
        binding.myrecyclerview.setAdapter(customAdapterVideo);






    }



}
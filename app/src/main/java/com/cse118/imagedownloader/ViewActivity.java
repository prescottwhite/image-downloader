package com.cse118.imagedownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<Image> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ImageDatabase db = new ImageDatabase(this);

        recyclerView = findViewById(R.id.AV_RecyclerView_imageList);

        images = db.getImages();

        ImageAdapter adapter = new ImageAdapter(images);

        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

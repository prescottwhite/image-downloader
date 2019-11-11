package com.cse118.imagedownloader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cse118.imagedownloader.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

package com.cse118.imagedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final ListView lv = findViewById(R.id.listView);
        final List<String> books_list = new ArrayList<>();
        ImageDatabase db = new ImageDatabase(this);
        final ListIterator<Image> iterator = db.getImages().listIterator();
        showBooks(lv, books_list, iterator);

    }

    public void showBooks(ListView lv, List<String> images_list, ListIterator<Image> iterator) {
        Image image;
        String title;

        final ArrayAdapter<String> arrayAdapter;

        while (iterator.hasNext()) {
            image = iterator.next();
            title = image.getTitle();

            images_list.add("Title: " + title);
        }

        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, images_list);
        lv.setAdapter(arrayAdapter);
    }
}

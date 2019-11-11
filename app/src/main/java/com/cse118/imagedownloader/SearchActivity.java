package com.cse118.imagedownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private Button mSearchButton;
    private EditText mSearchText;

    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private ArrayList<Image> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchText = findViewById(R.id.AS_editText_searchTitle);

        mSearchButton = findViewById(R.id.AS_button_search);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = mSearchText.getText().toString();
                ImageDatabase db = new ImageDatabase(getBaseContext());

                images = db.searchImages(query);
                getSearchResults(images, query);
            }
        });
    }

    public void getSearchResults(ArrayList<Image> images, String query) {
        recyclerView = findViewById(R.id.AS_RecyclerView_searchList);
        adapter = new ImageAdapter(images);
        if (adapter != null && recyclerView != null && !query.isEmpty()) {
            recyclerView.setAdapter(adapter);
            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }
}

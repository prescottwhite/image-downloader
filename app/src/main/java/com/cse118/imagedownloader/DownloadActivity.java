package com.cse118.imagedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadActivity extends AppCompatActivity {

    private EditText mURLText;
    private EditText mTitleText;
    private Button mDownloadButton;

    private String urlString;
    private String titleString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mURLText = findViewById(R.id.AD_editText_url);
        mTitleText = findViewById(R.id.AD_editText_title);
        mDownloadButton = findViewById(R.id.AD_button_download);

        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlString = mURLText.getText().toString();
                titleString = mTitleText.getText().toString();

                Bitmap bitmap = null;

                try {
                    bitmap = new GetBitmapTask().execute(urlString).get();
                }
                catch (Exception e) {
                    bitmap = null;
                }

                if (bitmap != null) {
                    addBitmapToSQL(titleString, bitmap);
                    finish();
                }
                else {
                    Toast errorToast = Toast.makeText(getBaseContext(), R.string.AD_toast_error, Toast.LENGTH_LONG);
                    errorToast.show();
                }
            }
        });
    }

    private class GetBitmapTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                urlConnection.disconnect();
                return bitmap;
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

        }
    }

    private void addBitmapToSQL(String title, Bitmap bitmap) {
        ImageDatabase db = new ImageDatabase(this);
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteOutStream);
        byte[] byteFromBitmap = byteOutStream.toByteArray();

        db.addBlob(title, byteFromBitmap);
    }
}

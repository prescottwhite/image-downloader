package com.cse118.imagedownloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ImageAdapter extends
        RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    ImageDatabase db;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        db = new ImageDatabase(context);

        LayoutInflater inflater = LayoutInflater.from(context);

        View imageView = inflater.inflate(R.layout.image_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(imageView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Image image = mImages.get(position);

        TextView textViewID = holder.mIDTextView;
        textViewID.setText(Integer.toString(image.getId()));

        ImageView imageView = holder.mImageView;
        byte[] byteArray = image.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView.setImageBitmap(bitmap);

        TextView textViewTitle = holder.mTitleTextView;
        textViewTitle.setText(image.getTitle());

        ImageButton buttonDel = holder.mDeleteButton;
        buttonDel.setEnabled(true);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteImage(image.getId());
                mImages.clear();
                mImages.addAll(db.getImages());
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mImages.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView mIDTextView;
        public ImageView mImageView;
        public TextView mTitleTextView;
        public ImageButton mDeleteButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            mIDTextView = itemView.findViewById(R.id.IR_TextView_id);
            mImageView = itemView.findViewById(R.id.IR_ImageView_image);
            mTitleTextView = itemView.findViewById(R.id.IR_TextView_title);
            mDeleteButton = itemView.findViewById(R.id.IR_Button_delete);
        }
    }

    private List<Image> mImages;

    public ImageAdapter(List<Image> images) {
        mImages = images;
    }
}
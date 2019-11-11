package com.cse118.imagedownloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


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
                mImages.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mImages.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mIDTextView;
        public ImageView mImageView;
        public TextView mTitleTextView;
        public ImageButton mDeleteButton;

        public ViewHolder(View itemView) {

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
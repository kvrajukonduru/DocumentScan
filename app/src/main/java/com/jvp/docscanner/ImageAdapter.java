package com.jvp.docscanner;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devliving.online.cvscanner.util.Util;

/**
 * Created by user on 10/16/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

  private List<ImageObject> imageObjects = new ArrayList<>();

  @Override
  public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_row, parent,false);
    return new ImageViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ImageViewHolder holder, int position) {
    if (position < imageObjects.size()) {
      ImageObject imageObject = imageObjects.get(position);

      Log.d("ADAPTER", "position: " + position + ", uri: " + imageObject.imageURI);

      Picasso.get().load(imageObject.imageURI).into(holder.imageView);
      holder.textView.setText(imageObject.name);
      Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(imageObject.date);
      DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
      holder.dateView.setText("Last Modified: "+df.format(cal.getTime()));
    }
  }

  /**
   * Returns the total number of items in the data set held by the adapter.
   *
   * @return The total number of items in this adapter.
   */
  @Override
  public int getItemCount() {
    return imageObjects.size();
  }

  public void add(ImageObject imageObject) {
    int pos = imageObjects.size();
    imageObjects.add(imageObject);
    notifyItemInserted(pos);
    Log.d("ADAPTER", "added image");
  }

  public void clear() {
    if (imageObjects.size() > 0) {
      List<String> paths = new ArrayList(imageObjects);
      imageObjects.clear();
      notifyDataSetChanged();

      for (String path : paths) {
        Utility.deleteFilePermanently(path);
      }
    }

    Log.d("ADAPTER", "cleared all images");
  }
}

class ImageViewHolder extends RecyclerView.ViewHolder {

  ImageView imageView;
  TextView textView;
  TextView dateView;

  public ImageViewHolder(View itemView) {
    super(itemView);
   imageView = itemView.findViewById(R.id.iv_base);
    textView = itemView.findViewById(R.id.tv_title);
    dateView = itemView.findViewById(R.id.tv_date);
  }
}



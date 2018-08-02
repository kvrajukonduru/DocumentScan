package com.jvp.docscanner;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class ImageDialogFragment extends DialogFragment {

  public static ImageDialogFragment newInstance(Uri uri){
    ImageDialogFragment imageDialogFragment = new ImageDialogFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable("info",uri);
    imageDialogFragment.setArguments(bundle);
    return imageDialogFragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_dialog_image, container);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Uri uri = (Uri) getArguments().get("info");

    ImageView imageView = view.findViewById(R.id.iv_image_preview);
    Picasso.get().load(uri).into(imageView);
  }

  @Override
  public void onStart() {
    super.onStart();
    getDialog().getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    getDialog().getWindow().setGravity(Gravity.TOP);
    getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(),R.color.white)));
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    return dialog;
  }

}

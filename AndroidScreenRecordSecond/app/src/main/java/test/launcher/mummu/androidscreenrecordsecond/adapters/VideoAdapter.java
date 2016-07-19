package test.launcher.mummu.androidscreenrecordsecond.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import test.launcher.mummu.androidscreenrecordsecond.R;

/**
 * Created by muhammed on 7/19/2016.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<String> list;

    public VideoAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cardview, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.mThumbImage.setImageResource(R.drawable.test);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Context getContext() {
        return context;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView mThumbImage;
        protected ImageView mDeleteImage;
        protected ImageView mShareImage;

        public CustomViewHolder(View view) {
            super(view);
            mThumbImage = (ImageView) view.findViewById(R.id.thumbImage);
            mDeleteImage = (ImageView) view.findViewById(R.id.deleteImageView);
            mShareImage = (ImageView) view.findViewById(R.id.shareImageView);
        }
    }
}

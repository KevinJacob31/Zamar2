package com.soarhigh.zamar;

import android.content.Context;
import android.media.MediaPlayer;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.soarhigh.zamar.databinding.EntitySlideBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapterVideo extends RecyclerView.Adapter<CustomAdapterVideo.MyViewHolder> {

    ArrayList<POJOSlide> pojoSlideArrayList;
    Context context;

    public CustomAdapterVideo(ArrayList<POJOSlide> pojoSlideArrayList, Context context) {

        this.pojoSlideArrayList = pojoSlideArrayList;
        this.context = context;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        EntitySlideBinding binding;

        MyViewHolder(EntitySlideBinding b) {
            super(b.getRoot());
            binding = b;


        }

    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(context);
        return new MyViewHolder(EntitySlideBinding.inflate(li));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomAdapterVideo.MyViewHolder myViewHolder, int i) {

        myViewHolder.binding.vidname.setText(pojoSlideArrayList.get(i).name + "");

        pojoSlideArrayList.get(i).slide_no = (i + 1) + "/" + pojoSlideArrayList.size() + "";
        myViewHolder.binding.vidnum.setText(pojoSlideArrayList.get(i).slide_no + "");


        //======== image before video loading ==
        String image_url = pojoSlideArrayList.get(i).img_thumb + "";
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round);
        Glide.with(context).load(image_url).apply(options).into(myViewHolder.binding.vidimage);


        try {
            final String urlpath = pojoSlideArrayList.get(i).vid_url;
            myViewHolder.binding.vid.setVideoPath(urlpath);

            myViewHolder.binding.vid.requestFocus();
            myViewHolder.binding.vid.start();

            myViewHolder.binding.vid.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.d("inside", "onPrepared");
                    myViewHolder.binding.vid.seekTo(0);
                    mp.setLooping(true);
                    mp.setVolume(1, 1); //full volume , 0 for mute
                    mp.start();
                    myViewHolder.binding.vidimage.setVisibility(View.GONE);


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return pojoSlideArrayList.size();


    }
}
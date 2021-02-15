package com.example.videostreamingtask2;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.M)
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder>implements View.OnScrollChangeListener {
    Context mcontext;
    ArrayList<MediaSource> mediaSourceslist;
    private SimpleExoPlayer simpleExoPlayer;
    private BandwidthMeter bandwidthMeter;
    private ExtractorsFactory extractorsFactory;
    private AdaptiveTrackSelection.Factory trackSelectionFactory;
    private TrackSelector trackSelector;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    public VideoListAdapter(ArrayList<MediaSource> mediaSourceslist, Context context) {
        this.mediaSourceslist=mediaSourceslist;
        mcontext=context;
    }

    @NonNull
    @Override
    public VideoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListAdapter.ViewHolder holder, int position) {
        MediaSource source=mediaSourceslist.get(position);
        bandwidthMeter = new DefaultBandwidthMeter();
            extractorsFactory = new DefaultExtractorsFactory();
            trackSelectionFactory = new AdaptiveTrackSelection.Factory();
            trackSelector = new DefaultTrackSelector();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(mcontext, trackSelector);
            simpleExoPlayer.seekTo(currentWindow, playbackPosition);
            holder.playerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.setPlayWhenReady(true);
            simpleExoPlayer.prepare(source, true, true);
       /* if(holder.getAdapterPosition()!=position)
        {

        }
        System.out.println("Adapter Position:-"+holder.getLayoutPosition());*/

    }


    @Override
    public int getItemCount() {
        return mediaSourceslist.size();
    }

    @Override
    public void onScrollChange(View view, int i, int i1, int i2, int i3) {
        System.out.println("Adapter Position:-"+i1);

    }

   /* @Override
    public void onScrollChange(View view, int i, int i1, int i2, int i3) {
        System.out.println("Adapter Position:-"+i);

    }
*/


    public class ViewHolder extends RecyclerView.ViewHolder {

        private PlayerView playerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.idExoPlayerVIew);
           // initializePlayer(playerView);

        }
    }
    private void initializePlayer(PlayerView playerView) {
        if (simpleExoPlayer == null) {
            bandwidthMeter = new DefaultBandwidthMeter();
            extractorsFactory = new DefaultExtractorsFactory();
            trackSelectionFactory = new AdaptiveTrackSelection.Factory();
            trackSelector = new DefaultTrackSelector();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(mcontext, trackSelector);
            simpleExoPlayer.setPlayWhenReady(true);
            simpleExoPlayer.seekTo(currentWindow, playbackPosition);
        }
    }

    private void releasePlayer(PlayerView playerView) {
        if (simpleExoPlayer != null) {
            playerView.setVisibility(View.GONE);
            playbackPosition = simpleExoPlayer.getCurrentPosition();
            currentWindow = simpleExoPlayer.getCurrentWindowIndex();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    private void pausePlayer(SimpleExoPlayer player){
        player.setPlayWhenReady(false);
        player.getPlaybackState();
    }
    private void startPlayer(SimpleExoPlayer player){
        player.setPlayWhenReady(true);
        player.getPlaybackState();
    }


}

package com.example.bakingrceipesapp.fragments;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bakingrceipesapp.R;
import com.example.bakingrceipesapp.recipeAPI.Recipe;
import com.example.bakingrceipesapp.recipeAPI.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailsFragment extends Fragment {

    private Recipe r;
    private Step s;
    private int stepID;
    private int position;
    private ArrayList<Step> steps;
    private SimpleExoPlayer exoPlayer;
    
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.previous)
    Button previous;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.mExoPlayer)
    SimpleExoPlayerView exoPlayerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_details_fragment,container,false);
        ButterKnife.bind(this, rootView);

        String Extra = getActivity().getIntent().getStringExtra(Recipe.class.getName());
        Gson gson = new Gson();
        r = gson.fromJson(Extra, Recipe.class);
        steps = (ArrayList<Step>) r.getSteps();
        stepID = getActivity().getIntent().getIntExtra("position",position);
        Log.d("Log",position+"");
        s = steps.get(stepID);
        getActivity().setTitle(r.getName());

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stepID-1>=0) {
                    s = steps.get(--stepID);
                    description.setText(s.getDescription());
                    prepareMedia(s);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stepID+1<steps.size()){
                    s = steps.get(++stepID);
                    description.setText(s.getDescription());
                    prepareMedia(s);
                }
            }
        });

        description.setText(s.getDescription());
        initializePlayer();
        prepareMedia(s);

        return rootView;
    }
    
    private void initializePlayer() {

        if (exoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            exoPlayerView.setPlayer(exoPlayer);
        }
    }

    private void prepareMedia(Step s){
        Uri mediaUri = null;
        if(!s.getVideoURL().equals("")) { 
            mediaUri = Uri.parse(s.getVideoURL());
        }
        else if(!s.getThumbnailURL().equals("")){
            mediaUri = Uri.parse(s.getThumbnailURL());
        }
        else {
            releasePlayer();
            initializePlayer();
            exoPlayerView.setDefaultArtwork(BitmapFactory.
                    decodeResource(getResources(),R.drawable.ic_no_video));
        }
        String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                new DefaultDataSourceFactory(getContext(), userAgent),
                new DefaultExtractorsFactory(), null, null);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    private void releasePlayer(){
        exoPlayer.stop();
        exoPlayer.release();
        exoPlayer = null;
    }

    public void setStepID(int position){
        this.position = position;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("recipe", r);
        outState.putInt("stepId",stepID);
        super.onSaveInstanceState(outState);
    }
}

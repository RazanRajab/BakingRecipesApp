package com.example.bakingrceipesapp.fragments;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailsFragment extends Fragment {

    private Recipe r;
    private Step s;
    private int stepID;
    private ArrayList<Step> steps;
    private SimpleExoPlayer exoPlayer;

    @BindView(R.id.thumbnail)
    ImageView thumbnail;
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
        stepID = getActivity().getIntent().getIntExtra("position",0);
        s = steps.get(stepID);
        getActivity().setTitle(r.getName());

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stepID-1>=0) {
                    s = steps.get(--stepID);
                    populateUI(s);
                    prepareMedia(s.getVideoURL());
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stepID+1<steps.size()){
                    s = steps.get(++stepID);
                    populateUI(s);
                    prepareMedia(s.getVideoURL());
                }
            }
        });

        populateUI(s);
        initializePlayer();
        prepareMedia(s.getVideoURL());

        return rootView;
    }

    private void populateUI(Step s) {
        if(!s.getThumbnailURL().equals("")){
            Picasso.with(getContext())
                    .load(s.getThumbnailURL())
                    .placeholder(R.drawable.ic_cake).error(R.drawable.ic_broken_image)
                    .into(thumbnail);
        }
        description.setText(s.getDescription());
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

    private void prepareMedia(String Url){
        if(!s.getVideoURL().equals("")) {
            Uri mediaUri = Uri.parse(s.getVideoURL());
            String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                    new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(), null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
        else {
            releasePlayer();
            initializePlayer();
            exoPlayerView.setDefaultArtwork(BitmapFactory.
                    decodeResource(getResources(),R.drawable.ic_no_video));
        }
    }

    private void releasePlayer(){
        exoPlayer.stop();
        exoPlayer.release();
        exoPlayer = null;
    }
}

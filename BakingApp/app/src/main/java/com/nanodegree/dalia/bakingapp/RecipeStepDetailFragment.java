package com.nanodegree.dalia.bakingapp;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.Models.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepDetailFragment extends Fragment {
    @BindView(R.id.player_view) SimpleExoPlayerView simpleExoPlayerView;
    @BindView(R.id.step_instructions) TextView instructions;

    private SimpleExoPlayer player;
    private Handler mainHandler;
    private TrackSelection.Factory videoTrackSelectionFactory;
    private TrackSelector trackSelector;
    private LoadControl loadControl;
    private DataSource.Factory dataSourceFactory;
    private MediaSource videoSource;
    private Uri uri;
    private String userAgent;
    private static final DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

    public RecipeStepDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);

        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if(bundle != null){

            if(bundle.get("recipe") != null){
                Recipe recipe = (Recipe) bundle.get("recipe");

            }
            if(bundle.get("step") != null){
                Step step = (Step) bundle.get("step");

                Log.d("Step", "Step desc: " + step.getDescription());

                createPlayer();
                attachPlayerView();
                preparePlayer(step.getVideoURL());

                instructions.setText(step.getDescription());
            }


        }

        return view;
    }

    public void createPlayer(){
        mainHandler = new Handler();
        videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        loadControl = new DefaultLoadControl();
        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
    }

    public void attachPlayerView(){
        simpleExoPlayerView.setPlayer(player);
    }

    public void preparePlayer(String videoURL){
        uriParse(videoURL);
        dataSourceFactory = buildDataSourceFactory(bandwidthMeter);
        videoSource = new DashMediaSource(uri, buildDataSourceFactory(null),
                new DefaultDashChunkSource.Factory(dataSourceFactory), mainHandler, null);
        player.prepare(videoSource);
    }

    public void uriParse(String videoURL){
        uri = Uri.parse(videoURL);
    }

    private DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter){
        return new DefaultDataSourceFactory(getContext(), bandwidthMeter, buildHttpDataSourceFactory(bandwidthMeter));
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter){
        return new DefaultHttpDataSourceFactory(userAgent, bandwidthMeter);
    }

}

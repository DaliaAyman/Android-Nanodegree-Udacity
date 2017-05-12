package com.nanodegree.dalia.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.Models.Step;
import com.nanodegree.dalia.bakingapp.Utilities.MediaSessionCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepDetailFragment extends Fragment implements ExoPlayer.EventListener{

    @BindView(R.id.player_view) SimpleExoPlayerView simpleExoPlayerView;
    @BindView(R.id.step_image) ImageView image;
    @BindView(R.id.step_instructions) TextView instructions;
    @Nullable @BindView(R.id.decrementStep) Button decrementStepBtn;
    @Nullable @BindView(R.id.incrementStep) Button incrementStepBtn;

    private static final String TAG = RecipeStepDetailFragment.class.getSimpleName();

    private SimpleExoPlayer player;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;

    List<Step> recipeSteps;
    Step currentStep;

    public RecipeStepDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeMediaSession();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);

        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if(bundle != null){

            if(bundle.get("recipe") != null){
                Recipe recipe = (Recipe) bundle.get("recipe");

                recipeSteps = recipe.getStepsList();
            }
            if(bundle.get("step") != null){
                currentStep = (Step) bundle.get("step");

                Log.d("Step", "Step desc: " + currentStep.getDescription());

                updateFragmentData(currentStep);

                bindNavigationButtons();
            }


        }

        return view;
    }

    private void updateFragmentData(Step step){

        instructions.setText(step.getDescription());

        if(step.getVideoURL() != null && step.getVideoURL().length() != 0){
            createPlayer(Uri.parse(step.getVideoURL()));
        }else{
            simpleExoPlayerView.setVisibility(View.GONE);
        }

        if(step.getThumbnailURL() != null && step.getThumbnailURL().length() != 0){
            Glide.with(getContext()).load(step.getThumbnailURL()).into(image);
        }else{
            image.setVisibility(View.GONE);
        }
    }

    private void bindNavigationButtons(){
        if(recipeSteps != null){
            if(currentStep.isLastStep(recipeSteps)){
                incrementStepBtn.setVisibility(View.INVISIBLE);
            }
            if(currentStep.isFirstStep(recipeSteps)){
                decrementStepBtn.setVisibility(View.INVISIBLE);
            }

            incrementStepBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentStep.incrementStep(recipeSteps) != null) {
                        currentStep = currentStep.incrementStep(recipeSteps);
                        updateFragmentData(currentStep);
                    }
                    if(currentStep.isLastStep(recipeSteps)){
                        incrementStepBtn.setVisibility(View.INVISIBLE);
                    }else{
                        decrementStepBtn.setVisibility(View.VISIBLE);
                    }
                }
            });

            decrementStepBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentStep.decrementStep(recipeSteps) != null) {
                        currentStep = currentStep.decrementStep(recipeSteps);
                        updateFragmentData(currentStep);
                    }
                    if(currentStep.isFirstStep(recipeSteps)){
                        decrementStepBtn.setVisibility(View.INVISIBLE);
                    }else{
                        incrementStepBtn.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public void createPlayer(Uri uri){
        if(player == null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(player);
            String userAgent = Util.getUserAgent(getContext(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(uri,
                    new DefaultDataSourceFactory(getContext(),userAgent), new DefaultExtractorsFactory(), null, null);
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
        }
    }

    private void initializeMediaSession() {
        mediaSession = new MediaSessionCompat(getActivity(),TAG);
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS|MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mediaSession.setMediaButtonReceiver(null);

        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY|
                        PlaybackStateCompat.ACTION_PAUSE|
                        PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MediaSessionCallback(player));
        mediaSession.setActive(true);
    }

    private void releasePlayer(){
        if(player != null){
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        mediaSession.setActive(false);
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if(playbackState == ExoPlayer.STATE_READY && playWhenReady){
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, player.getCurrentPosition(), 1f);

        }else if(playbackState == ExoPlayer.STATE_READY){
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, player.getCurrentPosition(), 1f);
        }
        mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }
}

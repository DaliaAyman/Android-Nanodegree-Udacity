package com.nanodegree.dalia.bakingapp.Utilities;

import android.support.v4.media.session.MediaSessionCompat;

import com.google.android.exoplayer2.SimpleExoPlayer;

/**
 * Created by Dalia on 5/12/2017.
 */

public class MediaSessionCallback extends MediaSessionCompat.Callback {
    private SimpleExoPlayer player;

    public MediaSessionCallback(SimpleExoPlayer player) {
        this.player = player;
    }

    @Override
    public void onPlay() {
        player.setPlayWhenReady(true);
    }

    @Override
    public void onPause() {
        player.setPlayWhenReady(false);
    }

    @Override
    public void onSkipToPrevious() {
        player.seekTo(0);
    }
}

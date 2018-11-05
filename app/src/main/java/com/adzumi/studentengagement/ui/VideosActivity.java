package com.adzumi.studentengagement.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.adzumi.studentengagement.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
public class VideosActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyCFyIgIAKp5KxXs-wLklpzQCT89NYqgZAM";
    public static final String VIDEO_ID = "GTe57jQX5Eg";

    @BindView(R.id.youtube_view) YouTubePlayerView youTubePlayerView;

//    @BindView(R.id.my_toolbar) Toolbar myToolbar;
//    @BindView(R.id.bottom_navigation) BottomNavigationView mBottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        ButterKnife.bind(this);

        youTubePlayerView.initialize(API_KEY, this);

//        setSupportActionBar(myToolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.logo_appbar);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        mBottom_navigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                switch (item.getItemId())
//                {
//                    case R.id.action_home:
//                        Intent home = new Intent(VideosActivity.this, MainActivity.class);
//                        startActivity(home);
//                        finish();
//                        break;
//                    case R.id.action_videos:
//                        Intent events = new Intent(VideosActivity.this, VideosActivity.class);
//                        startActivity(events);
//                        finish();
//                        break;
//                    case R.id.action_favorites:
//                        Toast.makeText(VideosActivity.this, "Favorites Add Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.action_search:
//                        Toast.makeText(VideosActivity.this, "Search Add Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//
//                }
//            }
//        });
//        BottomNavigationViewHelper.disableShiftMode(mBottom_navigation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        /** add listeners to YouTubePlayer instance **/
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!b) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };
}

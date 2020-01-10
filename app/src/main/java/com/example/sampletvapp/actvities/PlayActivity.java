package com.example.sampletvapp.actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.sampletvapp.R;
import com.example.sampletvapp.manager.DataManager;
import com.example.sampletvapp.model.DataItem;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;

import java.util.HashMap;

public class PlayActivity extends AppCompatActivity {

    // TODO :: Fix the play functionality

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initData();

    }

    private DataItem dataItem;
    PlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    VideoView videoView;

    private void initData() {
        // videoView = findViewById(R.id.videoView);
        dataItem = DataManager.getInstance().getSelectedDataItem();
        final String videoUrl = dataItem.getUrl();
        String accessToken = "Bearer " + DataManager.getInstance().getToken().getAccessToken();
        String Authorization = "Authorization";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Authorization, accessToken);
        exoPlayerView = findViewById(R.id.videoView);

        try {


            Uri hlsUri = Uri.parse(videoUrl);
            //DefaultHttpDataSourceFactory dataSourceFactory =new DefaultHttpDataSourceFactory
            // (Util.getUserAgent((this,"exo-pplayer")));
            DataSource.Factory dataSourceFactory =
                    new DefaultHttpDataSourceFactory(Util.getUserAgent(this,
                            "app-name"));

            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
/*            MediaSource mediaSource= new ExtractorMediaSource(videoUri,dataSourceFactory,
                    extractorsFactory,null,null);
            HlsMediaSource hlsMediaSource =
                    new HlsMediaSource.Factory(dataSourceFactory)
                            .setAllowChunklessPreparation(true)
                            .createMediaSource(hlsUri);*/
            HlsMediaSource hlsMediaSource =
                    new HlsMediaSource.Factory(
                            dataType -> {
                                HttpDataSource dataSource =
                                        new DefaultHttpDataSource("Exo-player");
                                if (dataType == C.DATA_TYPE_MEDIA) {
                                    // The data source will be used for fetching media segments. We
                                    // set a custom authentication request header.
                                    dataSource.setRequestProperty(accessToken, Authorization);
                                }
                                return dataSource;
                            })
                            .createMediaSource(hlsUri);


            SimpleExoPlayer player = new SimpleExoPlayer.Builder(this).build();
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(hlsMediaSource);
            exoPlayer.setPlayWhenReady(true);

        } catch (Exception e) {
            Log.d("PlaterActivity", "exoplayer error " + e.toString());
        }


    /*    try {
            Log.d("tag", "hellooo its me ");
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController;
            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            Log.d("tag", "hellooo its me too ");
            //parse Uri as URI


            Uri vidroUri = Uri.parse(videoUrl);
            Log.d("tag", "your banana link " + vidroUri);

            //set media controller to video view
            videoView.setMediaController(mediaController);
            //set video uri
            videoView.setVideoURI(vidroUri,hashMap);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                    videoView.start();
                }
            });


        } catch (Exception e) {
            //If anything goes wrong while streaming
            Log.d("playerActivity", "ggwp " + e.toString());

            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }*/
    }
}

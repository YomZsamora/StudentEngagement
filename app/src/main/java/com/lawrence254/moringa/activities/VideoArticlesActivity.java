package com.lawrence254.moringa.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lawrence254.moringa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapters.CustomListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.VideoDetails;

public class VideoArticlesActivity extends AppCompatActivity {

    ArrayList<VideoDetails> videoDetailsArrayList;
    CustomListAdapter customListAdapter;
    String searchName;
    String TAG="VideoArticlesActivity";
    String URL="https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCnkYPrm1GvxDzGlT0S2zn4w&maxResults=25&key=AIzaSyCFyIgIAKp5KxXs-wLklpzQCT89NYqgZAM";

    @BindView(R.id.videoList)
    ListView lvVideo;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_articles);
        ButterKnife.bind(this);

        videoDetailsArrayList = new ArrayList<>();
        customListAdapter = new CustomListAdapter(VideoArticlesActivity.this,videoDetailsArrayList);
        showVideo();

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_appbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mBottom_navigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        Intent home = new Intent(VideoArticlesActivity.this, homeActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.action_videos:
                        Intent events = new Intent(VideoArticlesActivity.this, VideoArticlesActivity.class);
                        startActivity(events);
                        finish();
                        break;
                    case R.id.action_favorites:
                        Toast.makeText(VideoArticlesActivity.this, "Favorites Add Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_search:
                        Toast.makeText(VideoArticlesActivity.this, "Search Add Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_library:
                        Toast.makeText(VideoArticlesActivity.this, "Library Add Clicked", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        com.lawrence254.moringa.activities.BottomNavigationViewHelper.disableShiftMode(mBottom_navigation);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }



    private void showVideo() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for(int i=0;i<13;i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonVideoId = jsonObject1.getJSONObject("id");
                        JSONObject jsonsnippet = jsonObject1.getJSONObject("snippet");
                        JSONObject jsonObjectdefault = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");

                        String videoid = jsonVideoId.getString("videoId");
                        VideoDetails videoDetails = new VideoDetails();

                        Log.e(TAG," New Video Id " + videoid);
                        videoDetails.setVideoId(videoid);
                        videoDetails.setURL(jsonObjectdefault.getString("url"));
                        videoDetails.setVideoName(jsonsnippet.getString("title"));
                        videoDetails.setVideoDesc(jsonsnippet.getString("description"));

                        videoDetailsArrayList.add(videoDetails);
                    }
                    lvVideo.setAdapter(customListAdapter);
                    customListAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }
}

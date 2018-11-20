package com.lawrence254.moringa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lawrence254.moringa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import fragments.articleFragment;
import fragments.profileFragment;
import fragments.videoFragment;

public class homeActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar) Toolbar myToolbar;
    @BindView(R.id.bottom_navigation) BottomNavigationView mBottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_appbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadFragment(new articleFragment());

        mBottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        Intent home = new Intent(homeActivity.this, homeActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.action_videos:
                        Intent events = new Intent(homeActivity.this, VideoArticlesActivity.class);
                        startActivity(events);
                        finish();
                        break;
                    case R.id.action_favorites:
                        Toast.makeText(homeActivity.this, "Favorites Add Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_search:
                        Toast.makeText(homeActivity.this, "Search Add Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_library:
                        Toast.makeText(homeActivity.this, "Library Add Clicked", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
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



    private void loadFragment(Fragment fragment) {
//        Load the fragments
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
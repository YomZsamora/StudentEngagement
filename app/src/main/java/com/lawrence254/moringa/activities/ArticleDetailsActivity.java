package com.lawrence254.moringa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.lawrence254.moringa.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Article;

public class ArticleDetailsActivity extends AppCompatActivity {

    @BindView(R.id.webV)WebView mWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        ButterKnife.bind(this);

        Intent intent = Objects.requireNonNull(ArticleDetailsActivity.this).getIntent();
        String articleurl = intent.getStringExtra("art");

        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWeb.loadUrl(articleurl);

        Toast.makeText(this, "The article is: "+articleurl, Toast.LENGTH_SHORT).show();

    }
}

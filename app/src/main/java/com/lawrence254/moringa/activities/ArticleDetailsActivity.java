package com.lawrence254.moringa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lawrence254.moringa.R;

import java.util.Objects;

import models.Article;

public class ArticleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        Intent intent = Objects.requireNonNull(ArticleDetailsActivity.this).getIntent();
        String article = intent.getStringExtra("art");

        Toast.makeText(this, "The article is: "+article, Toast.LENGTH_SHORT).show();

    }
}

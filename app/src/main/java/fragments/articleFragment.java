package fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lawrence254.moringa.R;
import adapters.ArticlesAdapter;
import models.Article;
import service.ArticlesService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class articleFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.articleRecycler)RecyclerView mRecyclerView;
    @BindView(R.id.imLikes)ImageView mLike;
    private ArticlesAdapter mAdapter;
    public ArrayList<Article> mArticles = new ArrayList<>();


    public articleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this,root);

        mLike.setOnClickListener(this);

        getArticles();
        return root;
    }
    private void getArticles() {
        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Student Engagement");
        progress.setMessage("Fetching Articles...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        final ArticlesService articlesSercice = new ArticlesService();

        progress.show();
        ArticlesService.getArticle(new Callback(){


            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                progress.dismiss();
                mArticles = articlesSercice.processArticles(response);
                getActivity().runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        mAdapter = new ArticlesAdapter(getContext(),mArticles);
                        mRecyclerView.setAdapter(mAdapter);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });

            }
        });
    }
//  Try to save a favourited item into shared preferences as an arraylist in order to save everything. Might fail spectacularly!!!!.
    @Override
    public void onClick(View v) {
        SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String article = gson.toJson(mArticles);
        Log.e("SHAREDPREF", "STORED ITEM: "+article);
        editor.putString("Article",article);
        editor.commit();
        mLike.setImageResource(R.drawable.heart);
    }
}
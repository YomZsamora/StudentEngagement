package fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lawrence254.moringa.R;
import adapters.ArticlesAdapter;
import models.Article;
import service.ArticlesService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class articleFragment extends Fragment {
    @BindView(R.id.articleRecycler)RecyclerView mRecyclerView;
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

}
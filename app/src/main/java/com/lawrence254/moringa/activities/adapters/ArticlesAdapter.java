package com.lawrence254.moringa.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lawrence254.moringa.R;
import com.lawrence254.moringa.activities.models.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>{
    @NonNull

private ArrayList<Article> mArticles = new ArrayList<>();
    private Context mContext;

    public ArticlesAdapter(Context context, ArrayList<Article> article){
        mContext = context;
        mArticles = article;
    }

    public ArticlesAdapter.ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_cards, parent, false);
        ArticlesAdapter.ArticlesViewHolder viewHolder = new ArticlesAdapter.ArticlesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ArticlesViewHolder holder, int position) {
        holder.bindArticles(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
    public class ArticlesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.articlesImage)ImageView mArticlesImage;
        @BindView(R.id.headline)TextView mHeadline;
        @BindView(R.id.description) TextView mDescription;
        @BindView(R.id.txtSource)TextView mSource;
        private Context mContext;

        public ArticlesViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
        }
        public void bindArticles(Article article){
            Picasso.get()
                    .load(article.getImageUrl())
                    .placeholder(R.drawable.moringa)
                    .error(R.drawable.download)
                    .into(mArticlesImage);
            mHeadline.setText(article.getTitle());
//            mSource.setText(article.getId());
            mDescription.setText(article.getBody());

        }
    }
}
package adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lawrence254.moringa.R;
import models.Article;

import com.lawrence254.moringa.activities.ArticleDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>{
    @NonNull

private ArrayList<Article> mArticles = new ArrayList<>();
    private Context mContext;
    private GestureDetectorCompat mGDetector;

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
        @BindView(R.id.tvTag)TextView mTag;
        @BindView(R.id.tvDate)TextView mDate;
        @BindView(R.id.tvComment)TextView mComment;
        @BindView(R.id.tvlike) TextView mLike;
        @BindView(R.id.imLikes)ImageView like;
        private Context mContext;

        public ArticlesViewHolder(View itemView){
            super(itemView);

            ButterKnife.bind(this,itemView);
            mGDetector = new GestureDetectorCompat(this,this);
            mContext = itemView.getContext();
        }
        public void bindArticles(Article article){
            final String art = article.getUrl();
            String author = article.getAuthor();
//TODO: Solve issue with saving whole response. Need to save only selected item:
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (){

                    }else {
                        SharedPreferences sharedPref = mContext.getSharedPreferences("ARTICLES", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        Gson gson = new Gson();
                        String article = gson.toJson(mArticles.get(getAdapterPosition()));
                        Log.e("SHAREDPREF", "STORED ITEM: " + article);
                        editor.putString("Article", article);
                        editor.commit();
                        like.setImageResource(R.drawable.heart);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(mContext, ArticleDetailsActivity.class);
                    intent.putExtra("art",art);
//                    mContext. startActivity(intent);
                }
            });
            Picasso.get()
                    .load(article.getImageUrl())
                    .placeholder(R.drawable.moringa)
                    .error(R.drawable.download)
                    .into(mArticlesImage);
            mHeadline.setText(article.getTitle());
            mSource.setText(article.getSource());
            mTag.setText(article.getTag());
            mDate.setText(article.getDate());
            mComment.setText(String.valueOf(article.getComments()));
            mLike.setText(String.valueOf(article.getLikes()));
            mDescription.setText(article.getDescription());

        }
    }
}
package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lawrence254.moringa.R;

import java.util.ArrayList;

import models.VideoDetails;
import service.AppController;

public class CustomListAdapter extends BaseAdapter {
    Activity activity;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private LayoutInflater inflater;
    ArrayList<VideoDetails> singletons;

    public CustomListAdapter(Activity activity, ArrayList<VideoDetails> singletons) {
        this.activity = activity;
        this.singletons = singletons;
    }

    public int getCount() {
        return this.singletons.size();
    }

    public Object getItem(int i) {
        return this.singletons.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.activity.getLayoutInflater();
            // getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.articles_videos, null);
        }
        if (this.imageLoader == null) {
            this.imageLoader = AppController.getInstance().getImageLoader();
        }
        NetworkImageView networkImageView = (NetworkImageView) convertView.findViewById(R.id.youtubeImageView);
        final TextView imgtitle = (TextView) convertView.findViewById(R.id.youtubeTitleTextView);
        final TextView imgdesc = (TextView) convertView.findViewById(R.id.descriptionTextView);
//        final TextView tvURL=(TextView)convertView.findViewById(R.id.tvUrlTextView);
//        final  TextView tvVideoID=(TextView)convertView.findViewById(R.id.idTextView);

//        ((LinearLayout) convertView.findViewById(R.id.youtube_fragment)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), VideosActivity.class);
////                intent.putExtra("videoId",tvVideoID.getText().toString());
//                view.getContext().startActivity(intent);
//
//             }
//        });

        VideoDetails singleton = (VideoDetails) this.singletons.get(i);
        networkImageView.setImageUrl(singleton.getURL(), this.imageLoader);
//        tvVideoID.setText(singleton.getVideoId());
        imgtitle.setText(singleton.getVideoName());
        imgdesc.setText(singleton.getVideoDesc());
        return convertView;
    }
}


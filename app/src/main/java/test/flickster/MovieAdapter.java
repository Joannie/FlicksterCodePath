package test.flickster;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import test.flickster.models.Movie;

/**
 * Created by joanniehuang on 2017/2/14.
 */

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private Movie data;
    private static final String TAG = "MovieAdapter";

    public MovieAdapter(Context mContext, Movie mData) {
        this.context = mContext;
        this.data = mData;
    }



    @Override
    public int getCount() {
        if (data != null){
            return data.getResults().size();

        }else{
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        int type = getItemViewType(position);
        int orientation = context.getResources().getConfiguration().orientation;

        if (view == null){

            if(type == 0){
                view = LayoutInflater.from(context).inflate(R.layout.list_item_withtext,
                        parent, false);
            }else if(type == 1 && orientation == Configuration.ORIENTATION_PORTRAIT){
                view = LayoutInflater.from(context).inflate(R.layout.list_item_fullimage,
                        parent, false);
            }else{
                view = LayoutInflater.from(context).inflate(R.layout.list_item_withtext,
                        parent, false);
            }

            holder = new ViewHolder(view);
            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }

        /*Set different layouts and landscape*/
        if (type == 0 ){
            if(orientation == Configuration.ORIENTATION_PORTRAIT){
                holder.title.setText(data.getResults().get(position).getOriginalTitle());
                holder.overview.setText(data.getResults().get(position).getOverview());
                Picasso.with(context).load(data.getResults().get(position).getPostPath())
                        .transform(new RoundedCornersTransformation(10, 10))
                        .into(holder.imagePoster);


            }else{
                holder.title.setText(data.getResults().get(position).getOriginalTitle());
                holder.overview.setText(data.getResults().get(position).getOverview());
                Picasso.with(context).load(data.getResults().get(position).getPostPath())
                        .transform(new RoundedCornersTransformation(10, 10))
                        .into(holder.imagePoster);
            }

            holder.videoPlayIcon.setVisibility(View.GONE);

        }else{
            if(orientation == Configuration.ORIENTATION_PORTRAIT){
                Picasso.with(context).load(data.getResults().get(position).getBackdropPath())
                        .transform(new RoundedCornersTransformation(10, 10))
                        .into(holder.imagePoster);
            }else{
                holder.title.setText(data.getResults().get(position).getOriginalTitle());
                holder.overview.setText(data.getResults().get(position).getOverview());
                Picasso.with(context).load(data.getResults().get(position).getBackdropPath())
                        .transform(new RoundedCornersTransformation(10, 10))
                        .into(holder.imagePoster);
            }

        }

        holder.imagePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("title", data.getResults().get(position).getOriginalTitle());
                intent.putExtra("overview", data.getResults().get(position).getOverview());
                intent.putExtra("backdropPath", data.getResults().get(position).getBackdropPath());
                intent.putExtra("voteRate", data.getResults().get(position).getVoteAverage());
                intent.putExtra("movieID", data.getResults().get(position).getMovieID());
                intent.putExtra("releaseDate", data.getResults().get(position).getReleaseDate());

                context.startActivity(intent);
            }
        });


        return view;
    }

    //For showing two different layout in the same list
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if(data.getResults().get(position).getVoteAverage() <= 5.0f){
            return 0;
        }else{
            return 1;
        }
    }

    static class ViewHolder{

        @Nullable @BindView(R.id.image_poster) ImageView imagePoster;
        @Nullable @BindView(R.id.text_title) TextView title;
        @Nullable @BindView(R.id.text_overview) TextView overview;
        @Nullable @BindView(R.id.videoPreviewPlayButton) ImageView videoPlayIcon;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }


}


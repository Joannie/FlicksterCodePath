package test.flickster;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.ButterKnife;

public class MovieDetailsActivity extends YouTubeBaseActivity {

    private String apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private String youtube_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        TextView title = (TextView) findViewById(R.id.text_title);
        title.setText(getIntent().getStringExtra("title"));

        TextView releaseDate = (TextView) findViewById(R.id.release_date);
        releaseDate.setText("Release Date: " + getIntent().getStringExtra("releaseDate"));

        TextView overview = (TextView) findViewById(R.id.text_overview);
        overview.setText(getIntent().getStringExtra("overview"));

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating((float)getIntent().getDoubleExtra("voteRate", 0.0));

        int movieID = getIntent().getIntExtra("movieID", 0);

        youtube_url = String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=%s&language=en-US"
                ,Integer.toString(movieID), apiKey);


        Log.d("DEBUG", Double.toString(getIntent().getDoubleExtra("voteRate", 0.0)));

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player) ;
        youTubePlayerView.initialize(youtube_url,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideo("FkAndUJpyX0");
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

        ButterKnife.bind(this);
    }
}

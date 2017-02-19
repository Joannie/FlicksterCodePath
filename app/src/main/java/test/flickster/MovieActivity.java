package test.flickster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import test.flickster.models.Movie;

public class MovieActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private MovieAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String jsonData = response.body().string();
                    Log.d("DEBUG", jsonData.toString());
                    JSONObject jsonObject = new JSONObject(jsonData);
                    JSONArray movieResultArray = jsonObject.getJSONArray("results");
                    Log.d("DEBUG", movieResultArray.toString());
                    movies = Movie.FromJSonArray(movieResultArray);

                    MovieActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setList();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void setList(){
        listView = (ListView) findViewById(R.id.movieListView);
        adapter = new MovieAdapter(this, movies);
        listView.setAdapter(adapter);
    }
}

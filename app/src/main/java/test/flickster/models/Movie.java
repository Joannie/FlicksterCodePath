package test.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by joanniehuang on 2017/2/13.
 */

public class Movie {

    String postPath;
    String backdropPath;
    String originalTitle;
    String overview;
    String releaseDate;


    int movieID;
    double voteAverage;
    static ArrayList<Movie> results;

    public Movie(JSONObject jsonObject) throws JSONException{
        this.postPath = jsonObject.getString("poster_path");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.voteAverage = jsonObject.getDouble("vote_average");
        this.movieID = jsonObject.getInt("id");
        this.releaseDate = jsonObject.getString("release_date");

    }

    public static ArrayList<Movie> FromJSonArray(JSONArray array){
        results = new ArrayList<>();

        for(int i = 0; i < array.length(); i++){
            try {
                results.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String getPostPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", postPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", backdropPath);
    }


    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getMovieID() { return movieID;}

    public String getReleaseDate() {
        return releaseDate;
    }
}

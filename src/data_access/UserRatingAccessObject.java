package data_access;
import data_store.UserDBInterface;
import data_store.UserMovie;
import entity.Movie;
import entity.UserFactory;
import org.json.JSONObject;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.search.SearchUserRatingsDataAccessInterface;
import use_case.update_rating.UpdateRatingDataAccessInterface;
import utility.ApiInterface;
import utility.OMDBCaller;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRatingAccessObject implements SearchUserRatingsDataAccessInterface, GetRatingsDataAccessInterface,
        RemoveRatingDataAccessInterface, UpdateRatingDataAccessInterface {

    public HashMap<String, Integer> getUserRatingsHashmap(String user, List<Movie> movies){
        return new HashMap<String, Integer>();
    }

    @Override
    public HashMap<Movie, Integer> getRatings(String user){
        ApiInterface APIcaller = new OMDBCaller();
        HashMap<Movie, Integer> ratings = new HashMap<Movie, Integer>();
        ratings.put(APIcaller.getMovie("tt1375666"), 5); // Inception
        ratings.put(APIcaller.getMovie("tt10648342"), 2); // Thor: Love and Thunder
        ratings.put(APIcaller.getMovie("tt2935510"), 3); // Ad Astra
        return ratings;
    }
    @Override
    public void removeRating(String username, String movie_id){
        HashMap<Movie, Integer> rating = getRatings(username);
        rating.remove(movie_id);


    }
    @Override
    public void updateRating(String username, String movie_id, int newRating){
        HashMap<Movie, Integer> rating = getRatings(username);
        ApiInterface APIcaller = new OMDBCaller();
        if (rating.containsKey(movie_id)) {
            rating.put(APIcaller.getMovie(movie_id), newRating);
        } else {;
            rating.put(APIcaller.getMovie(movie_id), newRating);
        }
    }

    @Override
    public int getUserRating(String username, String movie_id){

        HashMap<Movie, Integer> rating = getRatings(username);
        if (rating.containsKey(movie_id)){
            return rating.get(movie_id);
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean userRatingExists(String username, String movie_id){
        HashMap<Movie, Integer> ratings = getRatings(username);
        if (ratings.containsKey(movie_id)){
            return true;
        }
        else{
            return false;
        }
    }
}

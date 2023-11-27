package data_access;
import entity.Movie;
import org.json.JSONObject;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.search.SearchUserRatingsDataAccessInterface;
import use_case.update_rating.UpdateRatingDataAccessInterface;
import utility.ApiInterface;
import utility.OMDBCaller;

import javax.print.attribute.HashAttributeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRatingAccessObject implements SearchUserRatingsDataAccessInterface, GetRatingsDataAccessInterface,
        RemoveRatingDataAccessInterface, UpdateRatingDataAccessInterface {

    public UserRatingAccessObject() {

    }
    public void removeRating(String username, String move_id){}
    public void updateRating(String username, String move_id, int newRating){}
    public int getUserRating(String user, String movieID){
        return 1;
    }
    public boolean userRatingExists(String user, String movieID){
        return false;
    }

    public HashMap<String, Integer> getUserRatingsHashmap(String user, List<Movie> movies){
        return new HashMap<String, Integer>();
    }

    public List<Movie> getRatings(String user){
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie("tt1229238", "Mission: Impossible - Ghost Protocol", "https://m.media-amazon.com/images/M/MV5BMTY4MTUxMjQ5OV5BMl5BanBnXkFtZTcwNTUyMzg5Ng@@._V1_SX300.jpg", 2011);
        movie.setUserRating(5);
        movieList.add(movie);

        movie = new Movie("tt2935510", "Ad Astra", "https://m.media-amazon.com/images/M/MV5BZDE0ZjNjMTktYmIwMC00ODZjLWIwNGMtODM3MWZhMGM1NWFhXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_SX300.jpg", 2019);
        movie.setUserRating(3);
        movieList.add(movie);

        movie = new Movie("tt2911666", "John Wick", "https://m.media-amazon.com/images/M/MV5BMTU2NjA1ODgzMF5BMl5BanBnXkFtZTgwMTM2MTI4MjE@._V1_SX300.jpg", 2014);
        movie.setUserRating(5);
        movieList.add(movie);

        movie = new Movie("tt0468569", "The Dark Knight", "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg", 2008);
        movie.setUserRating(4);
        movieList.add(movie);

        return movieList;
    }
}

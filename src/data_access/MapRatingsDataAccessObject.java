package data_access;

import data_store.UserDBInterface;
import entity.Movie;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.update_rating.UpdateRatingDataAccessInterface;
import use_case.remove_rating.RemoveRatingDataAccessInterface;

import java.util.List;
import java.util.HashMap;

public class MapRatingsDataAccessObject implements GetRatingsDataAccessInterface, UpdateRatingDataAccessInterface, RemoveRatingDataAccessInterface {
    private final UserDBInterface watchlistDBMap;

    public MapRatingsDataAccessObject(UserDBInterface watchlistDBMap) {
        this.watchlistDBMap = watchlistDBMap;
    }

    @Override
    public List<Movie> getRatings(String user){
       return null;
    }

    @Override
    public void updateRating(String user, String movie, int rating){
        watchlistDBMap.addRating(user, movie, rating);
    }

    @Override
    public void removeRating(String username, String move_id) {

    }

    @Override
    public boolean userRatingExists(String username, String move_id) {
        return false;
    }
}

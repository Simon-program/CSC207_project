package use_case.get_ratings;

import entity.Movie;

import java.util.HashMap;
import java.util.List;

public interface GetRatingsDataAccessInterface {
    public List<Movie> getRatings(String user);
}

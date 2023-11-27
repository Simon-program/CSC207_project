package use_case.get_ratings;

import entity.Movie;

import java.util.HashMap;
import java.util.List;

public class GetRatingsOutputData {
    private final List<Movie> movieList;

    public GetRatingsOutputData(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {return movieList;}
}

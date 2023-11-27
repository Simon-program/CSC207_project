package use_case.get_watchlist;

import entity.Movie;
import entity.Watchlist;

import java.util.HashMap;
import java.util.List;

public class GetWatchlistOutputData {
    private final List<Movie> movieList;

    public GetWatchlistOutputData(List<Movie> movieList) {
        this.movieList = movieList;
    }
    public List<Movie> getMovieList() {
        return movieList;
    }
}

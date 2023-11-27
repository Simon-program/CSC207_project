package interface_adapters.get_watchlist;

import entity.Movie;
import entity.Watchlist;

import java.util.HashMap;
import java.util.List;

public class GetWatchlistState {
    private List<Movie> movieList;

    public GetWatchlistState(GetWatchlistState copy) {
        this.movieList = copy.movieList;
    }

    GetWatchlistState() {}

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}

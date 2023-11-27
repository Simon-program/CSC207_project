package interface_adapters.get_ratings;

import entity.Movie;

import java.util.HashMap;
import java.util.List;

public class GetRatingsState {

    private List<Movie> movieList;

    public GetRatingsState(GetRatingsState copy) {
        this.movieList = copy.movieList;
    }

    GetRatingsState() {}

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }


}

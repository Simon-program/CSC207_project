package use_case.remove_from_watchlist;

import entity.Movie;
import entity.Watchlist;

import java.util.List;

public interface RemoveFromWatchlistDataAccessInterface {

    public List<Movie> getWatchlist(String user);

    public void removeMovie(String user, String movieID);

}

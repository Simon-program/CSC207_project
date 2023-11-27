package data_access;
import data_store.UserDBInterface;
import entity.Movie;
import entity.Watchlist;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.remove_from_watchlist.RemoveFromWatchlistDataAccessInterface;
import data_store.MovieDBInterface;

import java.util.List;

public class MapWatchlistDataAccessObject implements GetWatchlistDataAccessInterface, AddToWatchlistDataAccessInterface, RemoveFromWatchlistDataAccessInterface {
    private final UserDBInterface watchlistDBMap;

    public MapWatchlistDataAccessObject(UserDBInterface watchlistDBMap) {
        this.watchlistDBMap = watchlistDBMap;
    }

    @Override
    public List<Movie> getWatchlist(String username) {
        return watchlistDBMap.getWatchlist(username);
    }
    @Override
    public void removeMovie(String username, String movieID) {
        watchlistDBMap.removeFromWatchlist(username, movieID);
    }
    @Override
    public void addMovie(String username, String movieID) {
        watchlistDBMap.addToWatchlist(username, movieID);
    }
}

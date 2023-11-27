package data_access;

// Entities
import entity.Movie;
import entity.Watchlist;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.remove_from_watchlist.RemoveFromWatchlistDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class WatchlistAccessObject implements GetWatchlistDataAccessInterface, AddToWatchlistDataAccessInterface, RemoveFromWatchlistDataAccessInterface {
    @Override
    public List<Movie> getWatchlist(String user) {
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie("tt1229238", "Mission: Impossible - Ghost Protocol", "https://m.media-amazon.com/images/M/MV5BMTY4MTUxMjQ5OV5BMl5BanBnXkFtZTcwNTUyMzg5Ng@@._V1_SX300.jpg", 2011);
        movie.setUserRating(5);
        movieList.add(movie);

//        movie = new Movie("tt2935510", "Ad Astra", "https://m.media-amazon.com/images/M/MV5BZDE0ZjNjMTktYmIwMC00ODZjLWIwNGMtODM3MWZhMGM1NWFhXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_SX300.jpg", 2019);
//        movie.setUserRating(3);
//        movieList.add(movie);

        movie = new Movie("tt2911666", "John Wick", "https://m.media-amazon.com/images/M/MV5BMTU2NjA1ODgzMF5BMl5BanBnXkFtZTgwMTM2MTI4MjE@._V1_SX300.jpg", 2014);
        movie.setUserRating(5);
        movieList.add(movie);

        movie = new Movie("tt0468569", "The Dark Knight", "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg", 2008);
        movie.setUserRating(4);
        movieList.add(movie);

        return movieList;
    }

    @Override
    public void removeMovie(String user, String movieID) {

    }

    @Override
    public void addMovie(String user, String movieID) {

    }
}
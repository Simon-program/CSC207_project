package data_store;

import data_store.MovieStore;

import java.util.HashMap;

public interface MovieDBInterface {

    public void addMovie(MovieStore movie);

    public void removeMovie(String movieID);

    public MovieStore getMovie(String movieID);

    public HashMap<String, MovieStore> getMovies();

    public void setMovies(HashMap<String, MovieStore> movies);
}

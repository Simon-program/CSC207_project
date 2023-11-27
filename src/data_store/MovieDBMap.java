package data_store;
import data_store.MovieDBInterface;

import java.util.HashMap;

// Class which accesses the movie hashmap
public class MovieDBMap implements MovieDBInterface{

    private HashMap<String, MovieStore> movies;

    public MovieDBMap(){
        this.movies = new HashMap<>();
    }

    @Override
    public HashMap<String, MovieStore> getMovies(){return movies;}

    @Override
    public void setMovies(HashMap<String, MovieStore> movies){this.movies = movies;}

    @Override
    public void addMovie(MovieStore movie){
        movies.put(movie.getMovieID(), movie);
    }

    @Override
    public void removeMovie(String movieID){
        movies.remove(movieID);
    }

    @Override
    public MovieStore getMovie(String movieID){
        return movies.get(movieID);
    }
}

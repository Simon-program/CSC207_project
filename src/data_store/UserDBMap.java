package data_store;

import entity.Movie;
import entity.User;
import entity.UserFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class UserDBMap implements UserDBInterface {

    private HashMap<String, UserStore> users;
    private MovieDBInterface movieDB;

    private String userDBFilename = "userDB.ser";

    private String movieDBFilename = "movieDB.ser";

    private UserFactory userFactory;

    public UserDBMap(UserFactory userFactory) {
        this.userFactory = userFactory;
        load();
    }

    public void save(){

        // Save userDB to disk
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userDBFilename))) {
            oos.writeObject(users);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Save movieDB to disk
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(movieDBFilename))) {
            oos.writeObject(movieDB.getMovies());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(){

        // Load userDB from disk
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userDBFilename))) {
            users = (HashMap<String, UserStore>) ois.readObject();
        } catch (Exception e) {
            users = new HashMap<>();
        }

        // Load movieDB from disk
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(movieDBFilename))) {
            movieDB = new MovieDBMap();
            movieDB.setMovies((HashMap<String, MovieStore>) ois.readObject());
        } catch (Exception e) {
            movieDB = new MovieDBMap();
        }
    }

    @Override
    public User getUser(String username) {
        UserStore user = users.get(username);
        if (user == null) {
            return null;
        }
        return userFactory.create(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    @Override
    public boolean addUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new UserStore(username, password));
        return true;
    }



    @Override
    public List<Movie> getWatchlist(String username) {
        Collection<UserMovieStore> movieList = users.get(username).getUserMovies().values();
        List<Movie> returnList = new ArrayList<>();
        for (UserMovieStore userMovie : movieList) {
            if (!userMovie.isInWatchlist()){
                continue;
            }
            MovieStore movieStore = movieDB.getMovie(userMovie.getMovieID());
            Movie movie = new Movie(movieStore.getMovieID(), movieStore.getTitle(), movieStore.getPosterPath(), movieStore.getYear());
            movie.setInWatchlist(userMovie.isInWatchlist());
            movie.setUserRating(userMovie.getRating());
            returnList.add(movie);
        }
        return returnList;
    }

    @Override
    public void addToWatchlist(String username, String movieID) {
        HashMap<String, UserMovieStore> userMovies = users.get(username).getUserMovies();
        if (userMovies.containsKey(movieID)) {
            userMovies.get(movieID).setInWatchlist(true);
        } else {
            UserMovieStore movie = new UserMovieStore(movieID);
            movie.setInWatchlist(true);
            userMovies.put(movie.getMovieID(), movie);
        }
    }

    @Override
    public void removeFromWatchlist(String username, String movieID) {
        HashMap<String, UserMovieStore> userMovies = users.get(username).getUserMovies();
        if (userMovies.containsKey(movieID)) {
            UserMovieStore userMovie = userMovies.get(movieID);
            if(userMovie.getRating() == 0){
                userMovies.remove(movieID);
            } else {
                userMovie.setInWatchlist(false);
            }
        }
    }

    @Override
    public List<Movie> getRatings(String username) {
        Collection<UserMovieStore> movieList = users.get(username).getUserMovies().values();
        List<Movie> returnList = new ArrayList<>();
        for (UserMovieStore userMovie : movieList) {
            if (userMovie.getRating() == 0){
                continue;
            }
            MovieStore movieStore = movieDB.getMovie(userMovie.getMovieID());
            Movie movie = new Movie(movieStore.getMovieID(), movieStore.getTitle(), movieStore.getPosterPath(), movieStore.getYear());
            movie.setInWatchlist(userMovie.isInWatchlist());
            movie.setUserRating(userMovie.getRating());
            returnList.add(movie);
        }
        return returnList;
    }

    @Override
    public void addRating(String username, String movieID, int rating) {
        HashMap<String, UserMovieStore> userMovies = users.get(username).getUserMovies();
        if (userMovies.containsKey(movieID)) {
            userMovies.get(movieID).setRating(rating);
        } else {
            UserMovieStore movie = new UserMovieStore(movieID);
            movie.setRating(rating);
            userMovies.put(movie.getMovieID(), movie);
        }
    }

    @Override
    public void removeRating(String username, String movieID) {
        HashMap<String, UserMovieStore> userMovies = users.get(username).getUserMovies();
        if (userMovies.containsKey(movieID)) {
            UserMovieStore userMovie = userMovies.get(movieID);
            if(!userMovie.isInWatchlist()){
                userMovies.remove(movieID);
            } else {
                userMovie.setRating(0);
            }
        }
    }
}

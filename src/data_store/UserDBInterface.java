package data_store;
import entity.Movie;
import entity.User;

import java.util.List;

public interface UserDBInterface {

    public void save();

    public void load();
    public User getUser(String username);

    public boolean addUser(String username, String password);

    public boolean userExists(String username);

    public List<Movie> getWatchlist (String username);

    public void addToWatchlist (String username, String movieID);

    public void removeFromWatchlist (String username, String movieID);

    public List<Movie> getRatings (String username);

    public void addRating (String username, String movie, int rating);

    public void removeRating (String username, String movie);



}

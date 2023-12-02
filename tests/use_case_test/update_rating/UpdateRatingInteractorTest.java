package use_case_test.update_rating;

import entity.Movie;
import interface_adapters.update_rating.UpdateRatingPresenter;
import interface_adapters.update_rating.UpdateRatingState;
import use_case.movie_info.*;
import use_case.remove_rating.*;
import use_case.update_rating.*;
import interface_adapters.update_rating.UpdateRatingViewModel;
import interface_adapters.update_rating.UpdateRatingController;
import org.junit.Test;
import utility.ApiInterface;
import utility.OMDBCaller;
import entity.UserRating;
import data_access.UserRatingAccessObject;

import java.io.File;

import static org.junit.Assert.*;

public class UpdateRatingInteractorTest {
    //Tests if a rating is properly updated. A userRating is first initialized to have a rating of 3.
    //The interactor is called and updates the database, the new rating should be updated to 3.
    @Test
    public void testUpadateRating() {
        String imdb = "tt0345950";
        String username = "Alex";
        int r = 5;
        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject("./userRatingsTest.csv");
        ratingAccessObject.updateRating(username, imdb, r);
        UpdateRatingOutputBoundary presenter = new UpdateRatingOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateRatingOutputData rating) {

                int x = ratingAccessObject.getUserRating(username, imdb).getRating();
                assertEquals(x, rating.getNewRating());
            }
        };

        UpdateRatingInputData inputData = new UpdateRatingInputData(3, username, imdb);
        UpdateRatingInputBoundary interactor = new UpdateRatingInteractor(ratingAccessObject, presenter);
        interactor.execute(inputData); // Will send output data to presenter to be checked
    }

}

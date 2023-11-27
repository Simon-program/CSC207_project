package use_case.get_watchlist;

import data_access.UserRatingAccessObject;
import entity.Movie;
import entity.Watchlist;
import utility.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetWatchlistInteractor implements GetWatchlistInputBoundary {
    private final GetWatchlistDataAccessInterface getWatchlistDataAccessObject;
    private final UserRatingAccessObject ratingAccessObject;
    private final ApiInterface apiInterface;
    private final GetWatchlistOutputBoundary getWatchlistPresenter;

    public GetWatchlistInteractor(GetWatchlistDataAccessInterface getWatchlistDataAccessObject, UserRatingAccessObject ratingAccessObject, ApiInterface apiInterface, GetWatchlistOutputBoundary getWatchlistPresenter) {
        this.getWatchlistDataAccessObject = getWatchlistDataAccessObject;
        this.ratingAccessObject = ratingAccessObject;
        this.apiInterface = apiInterface;
        this.getWatchlistPresenter = getWatchlistPresenter;
    }

    @Override
    public void execute(GetWatchlistInputData getWatchlistInputData) {
        List<Movie> watchlist = getWatchlistDataAccessObject.getWatchlist(getWatchlistInputData.getUser());

        GetWatchlistOutputData getWatchlistOutputData = new GetWatchlistOutputData(watchlist);

        getWatchlistPresenter.prepareGetWatchlistView(getWatchlistOutputData);
    }
}

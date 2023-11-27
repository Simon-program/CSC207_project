package interface_adapters.get_watchlist;

import entity.Movie;
import entity.Watchlist;
import interface_adapters.ViewManagerModel;
import use_case.get_watchlist.GetWatchlistOutputBoundary;
import use_case.get_watchlist.GetWatchlistOutputData;

import java.util.HashMap;
import java.util.List;

public class GetWatchlistPresenter implements GetWatchlistOutputBoundary {

    private final GetWatchlistViewModel getWatchlistViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetWatchlistPresenter(GetWatchlistViewModel getWatchlistViewModel, ViewManagerModel viewManagerModel) {
        this.getWatchlistViewModel = getWatchlistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareGetWatchlistView(GetWatchlistOutputData getWatchlistOutputData) {
        GetWatchlistState state = new GetWatchlistState();

        List<Movie> movieList = getWatchlistOutputData.getMovieList();


        state.setMovieList(movieList);

        getWatchlistViewModel.setState(state);
        viewManagerModel.firePropertyChanged();

        viewManagerModel.setActiveView(getWatchlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        viewManagerModel.firePropertyChanged();
    }
}

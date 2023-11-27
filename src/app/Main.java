package app;

import data_access.*;
import data_store.UserDBInterface;
import data_store.UserDBMap;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.get_ratings.GetRatingsViewModel;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.movie_info.MovieInfoViewModel;
import interface_adapters.signup.SignupViewModel;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.remove_from_watchlist.RemoveFromWatchlistDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import utility.ApiInterface;
import utility.OMDBCaller;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {
    private static ApiInterface api;
    private static FileUserDataAccessObject fileUserDataAccessObject;

    private static MapUserDataAccessObject mapUserDataAccessObject;

    private static SignupUserDataAccessInterface signupUserDAO;
    private static LoginUserDataAccessInterface loginUserDAO;

    private static WatchlistAccessObject testWatchlistDataAccessObject;

    private static MapWatchlistDataAccessObject mapWatchlistDataAccessObject;

    private static GetWatchlistDataAccessInterface getWatchlistDAO;

    private static RemoveFromWatchlistDataAccessInterface removeFromWatchlistDAO;

    private static AddToWatchlistDataAccessInterface addToWatchlistDAO;

    private static UserDBInterface userDBMap;


    public static void main(String[] args) {
        appInit();
        initUI();
    }

    public static void initUI() {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        GetWatchlistViewModel getWatchlistViewModel = new GetWatchlistViewModel();
        GetRatingsViewModel getRatingsViewModel = new GetRatingsViewModel();
        MovieInfoViewModel movieInfoViewModel = new MovieInfoViewModel();


        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject();
        GetWatchlistDataAccessInterface watchlistAccessObject = new WatchlistAccessObject();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, signupUserDAO);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, loginUserDAO);
        views.add(loginView, loginView.viewName);

        WatchlistView watchlistView = GetWatchlistUseCaseFactory.create(api, getWatchlistViewModel, viewManagerModel, getWatchlistDAO, ratingAccessObject, movieInfoViewModel);
        RatingsView ratingsView = GetRatingsUseCaseFactory.create(api, getRatingsViewModel, viewManagerModel, ratingAccessObject, getWatchlistDAO, movieInfoViewModel);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, watchlistView, ratingsView);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                appShutdown();
            }
        });

        application.pack();
        application.setVisible(true);
    }

    public static void appInit() {
        UserFactory userFactory = new CommonUserFactory();
        userDBMap = new UserDBMap(userFactory);

        //Movie API initializer
        api = new OMDBCaller();

        //create File User DAO
        try {
            fileUserDataAccessObject = new FileUserDataAccessObject("./users.csv", userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //create Map user DAO
        mapUserDataAccessObject = new MapUserDataAccessObject(userDBMap, userFactory);

        //assign File User DAOs
//        loginUserDAO = fileUserDataAccessObject;
//        signupUserDAO = fileUserDataAccessObject;

        //assign Map User DAOs
        loginUserDAO = mapUserDataAccessObject;
        signupUserDAO = mapUserDataAccessObject;

        //create test Watchlist DAO
        testWatchlistDataAccessObject = new WatchlistAccessObject();

        //create Map watchlist DAO
        mapWatchlistDataAccessObject = new MapWatchlistDataAccessObject(userDBMap);

        //assign test Watchlist DAO
        getWatchlistDAO = testWatchlistDataAccessObject;
        removeFromWatchlistDAO = testWatchlistDataAccessObject;
        addToWatchlistDAO = testWatchlistDataAccessObject;

        //assign Map watchlist DAO
//        getWatchlistDAO = mapWatchlistDataAccessObject;
//        removeFromWatchlistDAO = mapWatchlistDataAccessObject;
//        addToWatchlistDAO = mapWatchlistDataAccessObject;
    }

    public static void appShutdown() {
        userDBMap.save();
        System.out.println("Shutting down");
    }
}
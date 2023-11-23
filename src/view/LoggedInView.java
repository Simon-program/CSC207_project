package view;

import interface_adapters.logged_in.LoggedInState;
import interface_adapters.logged_in.LoggedInViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    JLabel username;
    JTabbedPane tabbedPane;
    //create new watchlist tab
    JPanel mywatchlist;

    //create new ratings tab
    JPanel myratings;

    //create new search tab
    JPanel moviesearch;

    //final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        tabbedPane = new JTabbedPane();
        tabbedPane.setSize(400,400);
        //create new watchlist tab
        mywatchlist = new JPanel();
        mywatchlist.setLayout(new BorderLayout());

        JPanel searchpanel = new JPanel(new BorderLayout());
        JButton search = new JButton("Search");

        searchpanel.add(new TextField("", 30));
        searchpanel.add(search, BorderLayout.AFTER_LINE_ENDS);

        mywatchlist.add(searchpanel, BorderLayout.PAGE_START);

        //create new ratings tab
        myratings = new JPanel();

        //create new search tab
        moviesearch = new JPanel();

        tabbedPane.addTab("My Watch List", mywatchlist);
        tabbedPane.addTab("My Ratings", myratings);
        tabbedPane.addTab("Movie Search", moviesearch);

        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("Tab: " + tabbedPane.getSelectedIndex());
                // Prints the string 3 times if there are 3 tabs etc
            }
        });

        add(tabbedPane);
        setSize(400, 400);
        setVisible(true);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //LoggedInState state = (LoggedInState) evt.getNewValue();
        //username.setText(state.getUsername());
    }
}
//Data_Access
package data_access;
import use_case.search.SearchUserDataAccessInterface;
import entity.User;

import java.util.*;


public class UserAccessObject implements SearchUserDataAccessInterface {

    public UserAccessObject() {

    }

    private final Map<String, User> accounts = new HashMap<>();


    public String getCurrUser() {
        return "";
    }
}

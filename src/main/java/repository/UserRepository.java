package repository;

import model.User;

import java.util.ArrayList;

public class UserRepository {

    private final ArrayList<User> userList = new ArrayList<>();
    private int lastUserID;


    // ---------------------------------------------------------------------------------- //

    public UserRepository() {
        addUser(new User(32345679, new ArrayList<>(), new ArrayList<>()));
        addUser(new User(52345678, new ArrayList<>(), new ArrayList<>()));
        addUser(new User(82335578, new ArrayList<>(), new ArrayList<>()));

    }

    //just for test purposes
    public UserRepository(User user1, User user2, User user3){
        addUser(user1);
        addUser(user2);
        addUser(user3);
    }

    // ---------------------------------------------------------------------------------- //

    public User getUser(int userId){
        User chosenUser = null;
        for(User user : getUserList()){
            if(userId == user.getUserID()){
                chosenUser = user;
            }
        }
        return chosenUser;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void deleteUser(int userId){
        for(User user : userList)
            if(userId == user.getUserID()){
                userList.remove(user);
                break;
            }
    }

    public void addUser(User user){
        user.setUserID(getNewUserID());
        userList.add(user);
    }

    public int getNewUserID(){
       lastUserID++;
       return lastUserID;
    }

    public void updateUser(){

    }

    public String userInfo(User user){
        return user.getUserID() + ", " + user.getUserMobileNumber();
    }








}

package dataObjects;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.educationapplication.observer.Observer;
import com.example.educationapplication.util.views.LoginView;
import com.example.educationapplication.util.views.MainActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class UserDto implements Observer {

    private String userId;
    private String userFirstName;
    private String userName;
    private String userLastName;
    private String userEmail;
    private String userDesc;

    private static List<MessageDto> directMessages;


    public UserDto(){
        userId = "";
        userFirstName = "";
        this.userLastName = "";
        this.userName = "";
        this.userEmail = "";
        this.userDesc = "";
        if(directMessages == null)
            directMessages = new ArrayList<>();
    }
    public UserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail,
                   String userDesc){
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userDesc = userDesc;
        if(directMessages == null)
            directMessages = new ArrayList<>();
    }

    public UserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail){
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userEmail = userEmail;
        if(directMessages == null)
            directMessages = new ArrayList<>();
    }


    public String getUserId(){
        return userId;
    }

    public String getUserFirstName(){
        return userFirstName;
    }

    public String getUserLastName(){
        return userLastName;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserDesc(){
        return userDesc;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setUserFirstName(String userFirstName){
        if(userFirstName!=null&&!userFirstName.equals("")){
            this.userFirstName = userFirstName;
        }
    }

    public void setUserLastName(String userLastName){
        if(userLastName!=null&&!userLastName.equals("")){
            this.userLastName = userLastName;
        }
    }

    public void setUserEmail(String userEmail){
        this.userEmail=userEmail;
    }

    public void setUserName(String userName){ this.userName = userName; }

    public void setUserDesc(String userDesc){
         this.userDesc = userDesc;
    }

    public void addMessage(String message, int courseID, int quizID){
        MessageDto m = new MessageDto(userId, courseID, quizID, message);
        directMessages.add(m);
    }

    public void setDirectMessages(List<MessageDto> messages){
        this.directMessages = messages;
    }

    public List<MessageDto> getDirectMessages(){
        return directMessages;
    }

    @Override
    public void update(String msg){
        addMessage(msg, 0,0);
        System.out.println("here");
    }

}

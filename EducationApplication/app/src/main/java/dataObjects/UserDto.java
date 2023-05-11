package dataObjects;

public abstract class UserDto {

    private String userId;
    private String userFirstName;
    private String userName;
    private String userLastName;
    private String userEmail;
    private String userDesc;


    public UserDto(){
        userId = "";
        userFirstName = "";
        this.userLastName = "";
        this.userName = "";
        this.userEmail = "";
        this.userDesc = "";
    }
    public UserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail,
                   String userDesc){
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userDesc = userDesc;
    }

    public UserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail){
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userEmail = userEmail;
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



}

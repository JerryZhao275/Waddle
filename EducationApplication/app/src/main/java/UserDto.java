
public abstract class UserDto {
    private Integer userId;
    private String userFirstName;
    private String userName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userDesc;
    private boolean isAdmin;

    public UserDto(Integer userId, String userFirstName, String userLastName, String userName, String userEmail,
                   String userPassword, String userDesc, String userType){
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userDesc = userDesc;
        if(userType == null){
            isAdmin = true;
        }
    }

    public UserDto(Integer userId, String userFirstName, String userLastName, String userName, String userEmail,
                   String userPassword, String userType){
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        if(userType == null){
            isAdmin = true;
        }
    }

    public Integer getUserId(){
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

    public void setUserFirstName(String userFirstName){
        if(userFirstName!=null&&!userFirstName.equals("")){
            this.userFirstName = userFirstName;
        }
    }

    public void setUserLastName(String userLastName){
        if(userLastName!=null&&!userLastName.equals("")){
            this.userFirstName = userFirstName;
        }
    }

    public String setUserEmail(){
        return userEmail;
    }

    public String setUserDesc(){
        return userDesc;
    }
}
package dataObjects;

public abstract class LoginUserDto {
    private String loginUserId;
    private String loginUserName;
    private String loginUserEmail;
    private String password;
    private boolean isLoggedIn;

    public LoginUserDto(String loginUserId, String loginUserName, String loginUserEmail, String password){
        this.loginUserId = loginUserId;
        this.loginUserName = loginUserName;
        this.loginUserEmail = loginUserEmail;
        this.password = password;
    }

    public String getLoginUserId(){
        return loginUserId;
    }
    public String getLoginUserName(){
        return loginUserName;
    }
    public String getLoginUserEmail(){
        return loginUserEmail;
    }
    public String getPassword(){
        return password;
    }

    public void setIsLoggedIn(boolean isLoggedIn){
        this.isLoggedIn = isLoggedIn;
    }
    public void setLoginUserEmail(String email){
         this.loginUserEmail = email;
    }
    public void setLoginUserName(String userName){
        this.loginUserName = userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public boolean isUserLoggedIn(){
        return this.isLoggedIn;
    }


}

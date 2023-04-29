package dataObjects;

public class AdminLoginDto extends LoginUserDto{
    public AdminLoginDto(String loginUserId, String loginUserName, String loginUserEmail, String password) {
        super(loginUserId, loginUserName, loginUserEmail, password);
    }
}

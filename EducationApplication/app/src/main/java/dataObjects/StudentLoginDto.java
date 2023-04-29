package dataObjects;

public class StudentLoginDto extends LoginUserDto{
    public StudentLoginDto(String loginUserId, String loginUserName, String loginUserEmail, String password) {
        super(loginUserId, loginUserName, loginUserEmail, password);
    }
}

package dataObjects;

public class TeacherLoginDto extends LoginUserDto{
    public TeacherLoginDto(String loginUserId, String loginUserName, String loginUserEmail, String password) {
        super(loginUserId, loginUserName, loginUserEmail, password);
    }
}

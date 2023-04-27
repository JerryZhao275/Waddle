public class AdminUserDto extends UserDto{
    public AdminUserDto(Integer userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, String userDesc) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, userDesc, null);
    }
    public AdminUserDto(Integer userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, null);
    }
}

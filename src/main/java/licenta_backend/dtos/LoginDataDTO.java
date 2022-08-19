package licenta_backend.dtos;

public class LoginDataDTO {

    private String username;
    private String password;

    public LoginDataDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDataDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
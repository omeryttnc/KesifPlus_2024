package enums;

public enum USERINFO {
    BUYER("peummonnemane-5141@yopmail.com","e*y7G2xhsTVAi5u")
    ;
    private String email;
    private String password;

    USERINFO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

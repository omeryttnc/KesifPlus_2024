package enums;

public enum USERCREDENTIAL {
    SELLER("seller_urban2@yopmail.com","Seller2/"),
    BUYER("buyer_urban@mailsac.com", "VHt*zzt*wQNu6XS");
//    BUYER("urbanicfarm2@yopmail.com", "Urbanicfarm2/");

    private final String getUsername;
    private final String getPassword;

    USERCREDENTIAL(String username, String password) {
        this.getUsername = username;
        this.getPassword = password;
    }
    public String getUsername(){
        return getUsername;
    }

    public String getPassword(){
        return getPassword;
    }
}

package enums;

public enum URLOGIN {
    HOMEPAGE("https://test.urbanicfarm.com/"),

    LOGIN_URL("https://test.hypnotes.net/login");



    private final String getLink;

  URLOGIN(String getLink) {
        this.getLink = getLink;
    }

    public String getLink() {

        return getLink;
    }

}

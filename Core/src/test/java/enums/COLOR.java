package enums;

public enum COLOR {
    GREEN("rgba(7, 188, 12, 1)",""),
    RED("rgba(231, 76, 60, 1)","")
    ;

    private String rgba;
    private String hex;

    COLOR(String rgba, String hex) {
        this.rgba = rgba;
        this.hex = hex;
    }

    public String getRgba() {
        return rgba;
    }

    public String getHex() {
        return hex;
    }


}

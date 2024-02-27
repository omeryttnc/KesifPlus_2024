package utility;

import java.util.Random;

public class FakerData {
    private String email;
    private String firstName;
    public FakerData(){
        StringBuilder str = new StringBuilder();
        String alp = "abcdefghijklmnoprstuvyxwz";
        for (int i = 0; i < 10; i++) {
            str.append(alp.charAt(new Random().nextInt(20)));
        }

        this.email = str.toString()+"@gmail.com";
        this.firstName = str.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }
}

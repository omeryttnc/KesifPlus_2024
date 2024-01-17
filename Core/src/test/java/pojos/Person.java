package pojos;

public record Person(String email,String password) {
   public void login(){
        System.out.println("email : " + email+ " password : " + password);
    }
}

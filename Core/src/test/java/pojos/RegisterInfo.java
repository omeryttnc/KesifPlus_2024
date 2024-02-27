package pojos;

public record RegisterInfo(String firstName, String lastName, String email, String plainPassword,
                           String confirmPassword) {
}

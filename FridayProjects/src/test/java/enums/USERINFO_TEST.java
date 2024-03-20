package enums;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.KesifPlus.api.ApiUtilities.response;
import static io.restassured.RestAssured.given;

public enum USERINFO_TEST {
    SELLER("seller_urban2@yopmail.com","Seller2/"),
    BUYER("buyer_urban@mailsac.com", "VHt*zzt*wQNu6XS");
//    BUYER("urbanicfarm2@yopmail.com", "Urbanicfarm2/");

    private String email;
    private String password;
    private String token;

    USERINFO_TEST(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        Map<String, String> map = new HashMap<>();

        RestAssured.baseURI = "https://test.urbanicfarm.com";
        RestAssured.basePath = "/api";

        map.put("email", this.email);
        map.put("password", this.password);
//        System.out.println("map = " + map);

        response = given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("/public/login");

//        System.out.println("Response body: " + response.asString());

        JsonPath jsonPath = response.jsonPath();
        boolean success = jsonPath.getBoolean("success");
        Assert.assertTrue(success);
        String token = jsonPath.getString("token");
        Assert.assertNotNull(token);
        return token;
    }

    public String loginForToken() {
        return getToken();
    }
}

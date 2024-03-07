package enums;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import manifold.ext.rt.api.This;
import org.junit.Assert;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;

import java.util.HashMap;
import java.util.Map;

import static com.KesifPlus.HooksImp.driver;
import static com.KesifPlus.api.ApiUtilities.response;
import static io.restassured.RestAssured.given;

public enum USERINFO {
    BUYER("peummonnemane-5141@yopmail.com", "e*y7G2xhsTVAi5u"),
    CANLI_SATICI("test_seller@mailsac.com", "e976S2rhtySNTdk");
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

    public String getToken() {
        Map<String, String> map = new HashMap<>();

        RestAssured.baseURI = "https://bdd26cb8-2c6c-417f-8bd3-70caab0a5060.mock.pstmn.io";
        map.put("email", this.email);
        map.put("password", this.password);

        response = given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("/api/public/login");
        JsonPath jsonPath = response.jsonPath();
        boolean success = jsonPath.getBoolean("success");
        Assert.assertTrue(success);
        String token = jsonPath.getString("token");
        Assert.assertNotNull(token);
        return token;

    }

    public void loginWithToken(String partialUrl) {
        driver.get("https://urbanicfarm.com/");
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
//        SessionStorage sessionStorage = ((WebStorage) driver).getSessionStorage();
        String token= getToken();

        localStorage.setItem("e3e1601fca9c429344c15527cd542142",token);
        localStorage.setItem("a27c6fac85ae1295535e42c9d3e3f305",token.split("\\.")[1]);
        driver.get("https://urbanicfarm.com/"+partialUrl);
    }
}

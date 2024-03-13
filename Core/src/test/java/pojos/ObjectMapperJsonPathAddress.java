package pojos;

import io.cucumber.java.sl.In;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.util.List;

import static com.KesifPlus.api.ApiUtilities.response;
import static io.restassured.RestAssured.given;

public class ObjectMapperJsonPathAddress {
    private boolean success;
    private List<Integer> allId;
    private int statusCode;
    private List<String> allCities;
    private String token;

    public ObjectMapperJsonPathAddress(String token) {
        this.token = token;
        response = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .when()
                .post("https://test.urbanicfarm.com/api/account/address/getAddress");
        JsonPath jsonPath = response.jsonPath();

        this.success = jsonPath.getBoolean("success");
        this.allId=jsonPath.getList("addresses.id");
        this.statusCode=response.statusCode();
        this.allCities = jsonPath.getList("addresses.city");
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Integer> getAllId() {
        return allId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<String> getAllCities() {
        return allCities;
    }
}

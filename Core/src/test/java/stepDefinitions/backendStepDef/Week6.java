package stepDefinitions.backendStepDef;

import com.KesifPlus.pojo.CreateAddress;
import enums.USERINFO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class Week6 extends TestCase {
    Response response;
    Map<String, String> map = new HashMap<>();

    public void testLogin_1_body_doldurma() {
        RestAssured.baseURI = "https://bdd26cb8-2c6c-417f-8bd3-70caab0a5060.mock.pstmn.io";
        map.put("email", "test_seller@mailsac.com");
        map.put("password", "e976S2rhtySNTdk");
        //given()
        //when()
        //then()
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("/api/public/login")
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("email", is("test_seller@mailsac.com"))
                .body("token", notNullValue());
    }

    public void testLogin_2_param_kullanimi() {
        RestAssured.baseURI = "https://bdd26cb8-2c6c-417f-8bd3-70caab0a5060.mock.pstmn.io";

        given()
                .contentType(ContentType.URLENC)
                .param("email", "test_seller@mailsac.com")
                .param("password", "e976S2rhtySNTdk")
                .when()
                .post("/api/public/login")
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("email", is("test_seller@mailsac.com"))
                .body("token", notNullValue());
    }

    public void testLogin_3_log() {
        RestAssured.baseURI = "https://bdd26cb8-2c6c-417f-8bd3-70caab0a5060.mock.pstmn.io";
        map.put("email", "test_seller@mailsac.com");
        map.put("password", "e976S2rhtySNTdk");
        //given()
        //when()
        //then()
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("/api/public/login")
                .then()
                .assertThat()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .body("success", Matchers.equalTo(true))
                .body("email", is("test_seller@mailsac.com"))
                .body("token", notNullValue())
//                .log()
//                .all();
//                .body();
        ;
    }

    public void testLogin_4_JsonPathKullanimi() {
        RestAssured.baseURI = "https://bdd26cb8-2c6c-417f-8bd3-70caab0a5060.mock.pstmn.io";
        map.put("email", "test_seller@mailsac.com");
        map.put("password", "e976S2rhtySNTdk");

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

    }
    public void testLogin_5_EnumKullanimi() {
        String token = USERINFO.CANLI_SATICI.getToken();

    }

    public void testCreateAddress(){
        RestAssured.baseURI = "https://bdd26cb8-2c6c-417f-8bd3-70caab0a5060.mock.pstmn.io";
        String token = USERINFO.CANLI_SATICI.getToken();
        CreateAddress createAddress = new CreateAddress(
                true,
                false,
                "title",
                "address",
                "city",
                "2700",
                "gd",
                false);

        response = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .body(createAddress)
                .when()
                .post("/api/account/address/addAddress");

        int actualStatusCode = response.statusCode();
        Assert.assertEquals(200,actualStatusCode);

        int anInt = response.jsonPath().getInt("address.id");
        Assert.assertNotNull(anInt);

        boolean aBoolean = response.jsonPath().getBoolean("address.success");
        Assert.assertTrue(aBoolean);
    }


}

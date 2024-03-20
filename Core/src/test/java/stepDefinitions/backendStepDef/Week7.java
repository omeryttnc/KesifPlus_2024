package stepDefinitions.backendStepDef;

import com.KesifPlus.api.ApiUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.USERINFO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Assert;
import pojos.GetAddress;
import pojos.ObjectMapperJsonPathAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class Week7 extends TestCase {
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE3MTAwMDc3MjMsImV4cCI6MTc0MTExMTcyMywicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9TRUxMRVIiXSwiZW1haWwiOiJzZWxsZXJfdXJiYW4yQHlvcG1haWwuY29tIiwiaWQiOjIzODcsImZpcnN0TmFtZSI6Im11c3RhZmEiLCJwaWN0dXJlIjpudWxsLCJnb29nbGVJZCI6bnVsbCwiZmFjZWJvb2tJZCI6bnVsbCwibGlua2VkaW5JZCI6bnVsbCwiZ2l0aHViSWQiOm51bGwsImFwcGxlSWQiOm51bGwsImlzVmVyaWZpZWQiOmZhbHNlLCJwaG9uZSI6Im51bGwiLCJkZXZpY2VUb2tlbiI6IjAiLCJjcmVhdGVkIjp7ImRhdGUiOiIyMDI0LTAxLTIzIDE0OjA3OjQxLjAwMDAwMCIsInRpbWV6b25lX3R5cGUiOjMsInRpbWV6b25lIjoiVVRDIn0sInBheXBhbEFjY291bnRJZCI6bnVsbCwibGF0IjozNy4zMDk5NDM4LCJsbmciOi0xMjIuMDA5ODg5NiwiYnJvd3Nlck5vdGlmaWNhdGlvbnMiOmZhbHNlLCJhbGxvd0Fub255bW91c0NoYXQiOnRydWUsInppcENvZGUiOiI0ODk3MCIsImRlbGl2ZXJ5VHlwZSI6WyJTRUxMRVJfREVMSVZFUlkiLCJCVVlFUl9QSUNLVVBfRlJPTV9CUkFOQ0giLCJCVVlFUl9QSUNLVVAiXSwibWF4RGVsaXZlcnlSYW5nZSI6MTAwMCwiZnJlZURlbGl2ZXJ5UmFuZ2UiOjEwMCwibWluRnJlZURlbGl2ZXJ5T3JkZXIiOjUsInBlck1pbGVDb3N0IjoxLCJhdmFpbGFiaWxpdHkiOiIwODowMC0xNzowMCIsImVzdGltYXRlZERlbGl2ZXJ5VGltZSI6WyI0MzIwXzEwMDAiXSwiYXZhaWxhYmlsaXR5QnJhbmNoIjoiMDg6MDAtMTc6MDAiLCJtaWRkbGVOYW1lIjoibnVsbCIsImxhc3ROYW1lIjoiQVRBS0FZIiwiZmxleGlibGVEZWxpdmVyeU9wdGlvbnMiOltdfQ.A9ummy9_nY51wPYAKnX8ATiylo64glvDsWHYpOeYnSXFWrXoCL6JRkrKJE427AGrp0WjZ4uLzbiEeDMUotZH50p6_jT0uYWISf2psoh4dEABd3L49VGjtCpueRTS2XW4n5VoURNUNCu9aJKgZmRhKmPk7IjC4XlGymfRjmAUiq72Nm4uJiXgJIRelA55QsJ4MJZXZGcO8Lkemo2qAactI8-eXj5jDQNuUv_V-EVLW0K3RaELn2sRFSBcogAIMs8Mo58obvKQaJnwnXv-Ob0K3hB0BgogR5CrItUvvIRr5Nx257Wr8npxOZuqXhQSuZuhBmu_R4D16MKtyXLoURIVrw";

    public void test1() {
        Map<String, String> map = new HashMap<>();
        // way 1 Rest assured uzerinden tanimlarsiniz
//        RestAssured.baseURI = "https://test.urbanicfarm.com";
//        RestAssured.basePath = "/api";
        map.put("email", "seller_urban2@yopmail.com");
        map.put("password", "Seller2/");

        RequestSpecification requestSpecification = RestAssured.given();
        // given uzerine girilmesi gerekn degerler request specification a eklenir
        requestSpecification.contentType(ContentType.JSON);
        // way 2 requestSpec uzerinden tanimlarsiniz
        requestSpecification.baseUri("https://test.urbanicfarm.com");
        requestSpecification.basePath("/api");

        // butun isteklerede yapacagimiz assertionlari ekleriz
        ResponseSpecification responseSpecification = RestAssured.expect();
        responseSpecification.statusCode(200);
        responseSpecification.body("success", equalTo(true));
        responseSpecification.time(Matchers.lessThan(2000L));


        given()
                .spec(requestSpecification)
                .body(map)
                .when()
                .post("/public/login")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    public void test2_jsonPath_1() {

        // given
        // when

        Response response;
        JsonPath jsonPath;
        response = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .when()
                .post("https://test.urbanicfarm.com/api/account/address/getAddress");
        response.prettyPrint();
        jsonPath = response.jsonPath();

        List<Integer> idList = jsonPath.getList("addresses.id");
        System.out.println("idList = " + idList);

        List<String> titleList = jsonPath.getList("addresses.title");
        System.out.println("titleList = " + titleList);

        List<String> createdDate = jsonPath.getList("addresses.created.date");
        System.out.println("createdDate = " + createdDate);

    }

    public void test2_objectMapper_1() {

        // given
        // when

        Response response;
        GetAddress getAddress;
        ObjectMapper objectMapper = new ObjectMapper();
        response = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .when()
                .post("https://test.urbanicfarm.com/api/account/address/getAddress");
        response.prettyPrint();
        try {
            getAddress = objectMapper.readValue(response.asString(), GetAddress.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        getAddress.getAddresses().stream()
                .forEach(t -> System.out.println(t.getId()));

        System.out.println("getAddress.getSuccess() = " + getAddress.getSuccess());

        for (int i = 0; i < getAddress.getAddresses().size(); i++) {
            System.out.println("getAddress.getAddresses().get(i).getTitle() = " + getAddress.getAddresses().get(i).getTitle());
        }


    }

    public void test2_objectMapper_jsonPath() {

        ObjectMapperJsonPathAddress address = new ObjectMapperJsonPathAddress(token);
        System.out.println("address.isSuccess() = " + address.isSuccess());
        System.out.println("address.getStatusCode() = " + address.getStatusCode());
        System.out.println("address.getAllId() = " + address.getAllId());

    }

    public void test3() {
        ApiUtilities apiUtilities= new ApiUtilities(USERINFO.BUYER.getEmail(), USERINFO.BUYER.getPassword());
//        System.out.println("apiUtilities.getGetAddress().getAllCities() = " + apiUtilities.getGetAddress().getAllCities());
    }
}

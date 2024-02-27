package com.KesifPlus.api;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtilities {
    static ResponseSpecification responseSpecification;
    static RequestSpecification requestSpecification;
    static Response response;

    public static void changePassword(String oldPassword, String newPassword, String token) {
        Map<String, String> map = new HashMap<>();
        map.put("old_password", oldPassword);
        map.put("new_password", newPassword);

        response = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .body(map)
                .when()
                .post("https://test.urbanicfarm.com/api/account/change/password");
    }

}


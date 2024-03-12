package com.KesifPlus.api;

import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static com.KesifPlus.api.ApiUtilities.response;
import static io.restassured.RestAssured.given;

public class ForgotPassword {
    public static void changePassword(String oldPassword, String newPassword, String token) {
        Map<String, String> map = new HashMap<>();
        map.put("old_password", oldPassword);
        map.put("new_password", newPassword);

        response  = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .body(map)
                .when()
                .post("https://test.urbanicfarm.com/api/account/change/password");
    }
}

package stepDefinitions.backendStepDef;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utility.FakerData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BackEndCalisma {
    Response response;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE3MDkwNjY1ODEsImV4cCI6MTc0MDE3MDU4MSwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9TRUxMRVIiXSwiZW1haWwiOiJwZXVtbW9ubmVtYW5lLTUxNDFAeW9wbWFpbC5jb20iLCJpZCI6MjQwNSwiZmlyc3ROYW1lIjoicnIiLCJwaWN0dXJlIjoiL3VwbG9hZHMvdXNlci8yNDA1LzY1Y2ZmMjE2MmVkOWIucG5nIiwiZ29vZ2xlSWQiOm51bGwsImZhY2Vib29rSWQiOm51bGwsImxpbmtlZGluSWQiOm51bGwsImdpdGh1YklkIjpudWxsLCJhcHBsZUlkIjpudWxsLCJpc1ZlcmlmaWVkIjpmYWxzZSwicGhvbmUiOiIrMSAoMjM0KSA1NjctNjU0NSIsImRldmljZVRva2VuIjoiMCIsImNyZWF0ZWQiOnsiZGF0ZSI6IjIwMjQtMDEtMzEgMjI6Mzc6MzAuMDAwMDAwIiwidGltZXpvbmVfdHlwZSI6MywidGltZXpvbmUiOiJVVEMifSwicGF5cGFsQWNjb3VudElkIjpudWxsLCJsYXQiOjM3LjM0LCJsbmciOi0xMjEuODksImJyb3dzZXJOb3RpZmljYXRpb25zIjpmYWxzZSwiYWxsb3dBbm9ueW1vdXNDaGF0Ijp0cnVlLCJ6aXBDb2RlIjpudWxsLCJkZWxpdmVyeVR5cGUiOlsiU0VMTEVSX0ZMRVhJQkxFIl0sIm1heERlbGl2ZXJ5UmFuZ2UiOjMzLCJmcmVlRGVsaXZlcnlSYW5nZSI6MTAuMDEsIm1pbkZyZWVEZWxpdmVyeU9yZGVyIjozNCwicGVyTWlsZUNvc3QiOjUsImF2YWlsYWJpbGl0eSI6IiIsImVzdGltYXRlZERlbGl2ZXJ5VGltZSI6W10sImF2YWlsYWJpbGl0eUJyYW5jaCI6IjA3OjAxLTE3OjAwIiwibWlkZGxlTmFtZSI6ImVydCIsImxhc3ROYW1lIjoiZXJ0IiwiZmxleGlibGVEZWxpdmVyeU9wdGlvbnMiOlt7ImlkIjo1MTcsImRlbGl2ZXJ5QnkiOjMwNjYsIm9yZGVyQmVnaW4iOjQ0NDUsIm9yZGVyRW5kIjo4ODI2fV19.HowdR9nf7OOjumt2VGp7jMlZ3kUF2u31BnhDQ6KUknBdmgVgbRbUpTsSI-lfKAyvuTKWIg1FNhMT1ocHh-dvbJhv8mBk5_kN7mfcUVhhjK1aKCYaZ8ereTi603qphEFlrVVJatmkUkPzOj5Wf1aKACcA9qXWsBJOS4Yiu2EQtnhdNO58DCc2bORUQA5Xx2xs9cd2Tdaaa4kax6ZXaVcYYqEqL0flstR1qYXvBPllrTfrSzRF-ObuhJ1xpB_EBqkybXPXpYdsGH9W9aYP8Tf5fblEdWpi2O0KGsmmwGNcDM3k_OBvhwhMiECUVTnz8SQNB4WAy3aMj1Bb6cZzBnM-pw";

    @Test
    public void registerTest_mapKullanarakBodyDoldurma() {
        // Given -> hazirlik
        // gonderilecek data turu ->Content-type
        // header
        // cookies
        // token
        // body
        // When  -> eylem gerceklestirecegiz
        // post get delete patch put
        // Then  -> assert yapacagiz
// 1. way map kullanimi
        Map<String, String> map = new HashMap<>();
        map.put("firstName", "omer");
        map.put("lastName", "ali");
        map.put("email", "omer@sdasd.asdas");
        map.put("plainPassword", "EPUcJ5xLpC6nynr");
        map.put("confirmPassword", "EPUcJ5xLpC6nynr");


        response = given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("https://test.urbanicfarm.com/api/public/register");

        response.prettyPrint();
        System.out.println("***********************************");
        response.prettyPeek();

    }


    @Test
    public void registerTest_classKullanarakBodyDoldurma() {
        // Given -> hazirlik
        // gonderilecek data turu ->Content-type
        // header
        // cookies
        // token
        // body
        // When  -> eylem gerceklestirecegiz
        // post get delete patch put
        // Then  -> assert yapacagiz


        RegisterInfo registerInfo = new RegisterInfo(
                "omer",
                "ali",
                "asd@asd.asd22",
                "EPUcJ5xLpC6nynr",
                "EPUcJ5xLpC6nynr"
        );
        response = given()
                .contentType(ContentType.JSON)
                .body(registerInfo)
                .when()
                .post("https://test.urbanicfarm.com/api/public/register");

        response.prettyPrint();
        System.out.println("***********************************");
        response.prettyPeek();

    }


    @Test
    public void registerTest_recordKullanarakBodyDoldurma() {
        // Given -> hazirlik
        // gonderilecek data turu ->Content-type
        // header
        // cookies
        // token
        // body
        // When  -> eylem gerceklestirecegiz
        // post get delete patch put
        // Then  -> assert yapacagiz


        RegisterInfoRecor registerInfo = new RegisterInfoRecor(
                "omer",
                "ali",
                "asd@asd.asd22",
                "EPUcJ5xLpC6nynr",
                "EPUcJ5xLpC6nynr"
        );
        response = given()
                .contentType(ContentType.JSON)
                .body(registerInfo)
                .when()
                .post("https://test.urbanicfarm.com/api/public/register");

        response.prettyPrint();
        System.out.println("***********************************");
        response.prettyPeek();

    }

    //2 .way record class
    private record RegisterInfoRecor(String firstName, String lastName, String email, String plainPassword,
                                     String confirmPassword) {
    }

    // 3. way lombok kullanimi
//    @Getter
//    @AllArgsConstructor

    // 4. way normal class kullanimi
    private class RegisterInfo {
        private String firstName;
        private String lastName;
        private String email;
        private String plainPassword;
        private String confirmPassword;

        public RegisterInfo(String firstName, String lastName, String email, String plainPassword, String confirmPassword) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.plainPassword = plainPassword;
            this.confirmPassword = confirmPassword;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getPlainPassword() {
            return plainPassword;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }
    }

    @Test
    public void name2() {
//        StringBuilder str = new StringBuilder();
//        String alp = "abcdefghijklmnoprstuvyxwz";
//        for (int i = 0; i < 10; i++) {
//            str.append(alp.charAt(new Random().nextInt(20)));
//        }
//
//        System.out.println("str = " + str);
        FakerData fakerData = new FakerData();
        System.out.println("fakerData.getEmail() = " + fakerData.getEmail());
    }


    public void changePassword(String oldPassword,String newPassword,String token) {
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

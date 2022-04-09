package tests;

//this is a static import, if static import is not used then we need to use RestAssured class name for
//accessing its methods.
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestsExamples {

    @Test
    public void test_1(){
        //here get can be called using RestAssured.get() but the import should not be a static
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void test_2(){
        //when you send the api request the endpoint url gets appended to the baseURI and goes to the server,
        // Now it is inbuilt in Rest Assured library so don't have to do this appending manually
        baseURI ="https://reqres.in/api";

        //We need to use given() method of RestAssured class to get a reference for RequestSpecification.
       /* given()
                .get("/users?page=2")
                .then().statusCode(200);
        */

        given()
                .get("/users?page=2")
                .then().statusCode(200).body("data[1].id",equalTo(8))
                .log().all();
    }
}

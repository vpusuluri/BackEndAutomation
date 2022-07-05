package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

import static io.restassured.matcher.RestAssuredMatchers.*;
import org.testng.annotations.Test;

import RequestBody.AddPlaceBody;
import RequestBody.requestBodyMethod;

public class PostHttpMethodExample {

    //http://makeseleniumeasy.com/2019/11/19/rest-assured-tutorial-8-bdd-style-in-rest-assured/
    @Test(enabled=false)
    public void postMethodWithoutBDDApproach() 
    {
        String requestBody = " {\n" +
                "\"firstName\": \"Elon\",\n" +
                "\"lastName\": \"Musk\",\n" +
                "\"salary\": 3000,\n" +
                "\"email\": \"elonmusk@abc.com\"\n" +
                "}";

        RequestSpecification request = RestAssured.given();
        request.baseUri("http://localhost:8088");
        request.body(requestBody);
        request.contentType(ContentType.JSON);

        Response response = request.post("/employees");

        // Let's print response body.
        String resString = response.asString();
        System.out.println("Response Details : " + resString);

        /*
         * To perform validation on response like status code or value, we need to get
         * ValidatableResponse type of response using then() method of Response
         * interface. ValidatableResponse is also an interface.
         */
        ValidatableResponse validatableResponse = response.then();
        // It will check if status code is 201
        validatableResponse.statusCode(201);
        // It will check if status line is as expected
        validatableResponse.body("id", Matchers.notNullValue());
        validatableResponse.body("firstName", Matchers.equalTo("Elon"));
        validatableResponse.body("lastName", Matchers.equalTo("Musk"));
        validatableResponse.body("salary", Matchers.equalTo(3000));
        validatableResponse.body("email", Matchers.equalTo("elonmusk@abc.com"));
    }

    //https://jsonpath.herokuapp.com/
    @Test(enabled=false)
    public void postMethodWithBDDApproach() 
    {
        String requestBody = " {\n" +
                "\"firstName\": \"Elon1\",\n" +
                "\"lastName\": \"Musk1\",\n" +
                "\"salary\": 3000,\n" +
                "\"email\": \"elonmusk@abc.com\"\n" +
                "}";

        RestAssured.given()
                .baseUri("http://localhost:8088")
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when()
                .post("/employees")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .assertThat()
                .body("id", notNullValue())
                .body("firstName", equalTo("Elon1"))
                .body("lastName", equalTo("Musk1"))
                .body("salary", equalTo(3000))
                .body("email", equalTo("elonmusk@abc.com"));
        
    }
    
    
    @SuppressWarnings("unchecked")
	@Test(enabled=false)
    public void requestPayloadUsingJSONObject()
    {
    	
    	JSONObject jso = new JSONObject();
    	//jso.put("id",notNullValue());
    	jso.put("firstName","Ajit");
    	jso.put("lastName","Kumar");
    	jso.put("salary","20000");
    	jso.put("email","kumar@gmail.com");

    	RestAssured.given().
    	baseUri("http://localhost:8088").
    	body(jso.toJSONString()).
    	contentType(ContentType.JSON).
    	when().
    	post("/employees").
    	then().
    	log().
    	all().
    	assertThat().
    	statusCode(201).
    	assertThat().
    	body("firstName",equalTo("Ajit"));
    	
    }
    
    @SuppressWarnings("unchecked")
   	@Test(enabled=false)
       public void requestPayloadUsingJSONFile()
       {
       	
    	File payload = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\PostBody.json");

       	RestAssured.given().
       	baseUri("http://localhost:8088").
       	body(payload).
       	contentType(ContentType.JSON).
       	when().
       	post("/employees").
       	then().
       	log().
       	all().
       	assertThat().
       	statusCode(201).
       	assertThat().
       	body("firstName",equalTo("Vijay"));
       	
       }
    
    @SuppressWarnings("unchecked")
   	@Test(enabled=false)
       public void requestPayloadUsingClassMethod()
       {
       	
    	//File payload = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\PostBody.json");

       	RestAssured.given().
       	baseUri("http://localhost:8088").
       	body(requestBodyMethod.getBody()).
       	contentType(ContentType.JSON).
       	when().
       	post("/employees").
       	then().
       	log().
       	all().
       	assertThat().
       	statusCode(201).
       	assertThat().
       	body("firstName",equalTo("Saritha"));
       	
       }
    
    @SuppressWarnings("unchecked")
	@Test
    public void requestPayloadUsingDSHashMap()
    {
    	
    	HashMap<String,Object> dataBody = new HashMap<String,Object>();
    	dataBody.put("firstName","smith");
    	dataBody.put("lastName","Henry");
    	dataBody.put("salary","70000");
    	dataBody.put("email","smith@gmail.com");

    	RestAssured.given().
    	baseUri("http://localhost:8088").
    	body(dataBody).
    	contentType(ContentType.JSON).
    	when().
    	post("/employees").
    	then().
    	log().
    	all().
    	assertThat().
    	statusCode(201).
    	assertThat().
    	body("firstName",equalTo("smith"));
    	
    }
    
}


package RestClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class JSONSchemaValidation 
{
	 @Test
	 public void testGet()
	 {
		 baseURI = "https://reqres.in/api";
		 
		 given().
		   get("/users?page=2"). 
		  then(). 
		  assertThat().body(matchesJsonSchemaInClasspath("schema.json")).
		  statusCode(200);
		 
	 }

}

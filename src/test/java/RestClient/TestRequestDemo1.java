package RestClient;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
public class TestRequestDemo1 
{
    @Test
	public void TestDemo1() 
	{
		 given()
		   .when()
		     .get("http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=fbb1cbf58e6b63e0709cadf6bf12b845")
		 .then()
		     .statusCode(200)
		     .statusLine("HTTP/1.1 200 OK")
		     .header("Content-Type", "application/json; charset=utf-8");
		     
		     

	}

}

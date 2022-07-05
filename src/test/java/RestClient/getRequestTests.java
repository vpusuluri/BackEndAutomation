package RestClient;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
public class getRequestTests 
{
	 @Test
	 public void test_1()
	 {
		 given().
		    get("https://reqres.in/api/users?page=2").
		 then().
		    statusCode(200).
		    body("data.id[1]",equalTo(8)).
		    body("data.first_name",hasItems("Michael","Lindsay"));
		    
	 }

}

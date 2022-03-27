package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ReadAllProducts {
	@Test
	public void readAllProducts() {

		Response response = given()
				// .log().all()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8").auth().preemptive()
				.basic("demo@techfios.com", "abc123").when()
				// .log().all()
				.get("/read.php").then()
				// .log().all()
				.extract().response();

		int actualStatusCode = response.statusCode();
		System.out.println("ActualStatusCode: " + actualStatusCode);
		Assert.assertEquals(actualStatusCode, 200);

		String actualHeader = response.getHeader("Content-Type");
		System.out.println("ActualHeader: " + actualHeader);
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");

		String actualResponseBody = response.getBody().asString();
		System.out.println("ActualResponseBody: " + actualResponseBody);

		JsonPath jp = new JsonPath(actualResponseBody);
		String firstProductId = jp.get("records[0].id");
		System.out.println("FirstProductId: " + firstProductId);

		if (firstProductId != null) {
			System.out.println("Records are not null");
		} else {
			System.out.println("Records are null");
		}

	}

}

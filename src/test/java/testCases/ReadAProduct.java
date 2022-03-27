package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ReadAProduct {
	@Test
	public void readAProduct() {

		Response response = given()
				// .log().all()
				.baseUri("https://techfios.com/api-prod/api/product").header("Content-Type", "application/json").auth()
				.preemptive().basic("demo@techfios.com", "abc123").queryParam("id", "3745").when()
				// .log().all()
				.get("/read_one.php").then()
				// .log().all()
				.extract().response();

		int actualResponse = response.statusCode();
		System.out.println("ActualResponse :" + actualResponse);
		int expectedResponse = 200;
		Assert.assertEquals(actualResponse, expectedResponse);

		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader :" + actualHeader);
		String expectedHeader = "application/json";
		Assert.assertEquals(actualHeader, expectedHeader);

		String actualResponseBody = response.getBody().asString();
		System.out.println("actualResponseBody :" + actualResponseBody);

		JsonPath jp = new JsonPath(actualResponseBody);
		String actualidValue = jp.get("id");
		System.out.println("ActualidValue: " + actualidValue);
		Assert.assertEquals(actualidValue, "3745");

		String actualNameValue = jp.get("name");
		System.out.println("ActualNameValue: " + actualNameValue);
		Assert.assertEquals(actualNameValue, "MD's Amazing Pillow 2.0");

	}

}

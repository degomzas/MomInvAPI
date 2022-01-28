package loans;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class LoansAPI {

    public Response addClient(String IdNum, String Name, String Surname, String AccountNumber, String BankName) {
        //Creating first JSON Object
        JSONObject requestBody = new JSONObject();
        requestBody.put("idNumber", IdNum);
        requestBody.put("name", Name);
        requestBody.put("surname", Surname);
        //Creating Second JSON Object
        JSONObject requestBody2 = new JSONObject();
        requestBody2.put("accountNumber", AccountNumber);
        requestBody2.put("bankName", BankName);
        // merging of first and second json objects
        requestBody.put("bankAccount", requestBody2);

        //Sending the request body to the loans endpoint
        return given().auth()
                .preemptive()
                .basic("admin", "password").spec(reqSpec()).body(requestBody).post("loans");
    }

    //Defining basic headers and Uri for all requests
    private static RequestSpecification reqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://assessment-api-loan.herokuapp.com/")
                .addHeader("accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addFilter(new RequestLoggingFilter()) //For logging the request made to the endpoint
                .addFilter(new ResponseLoggingFilter())//For logging the response
                .build();
    }
}

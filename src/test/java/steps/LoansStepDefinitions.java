package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import loans.LoansAPI;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;

public class LoansStepDefinitions {
    private LoansAPI loansAPI;
    Response response;
    ResponseSpecification SUCCESS = new ResponseSpecBuilder().expectStatusCode(200).build();


    @When("I add a new client with the following details {} {} {} {} {}")
    public void iAddANewClientWithTheFollowingDetailsIdNumberNameSurnameAccountNumberBankName(String IdNum, String Name, String Surname, String AccountNumber, String BankName) {
        loansAPI = new LoansAPI();
        response = loansAPI.addClient(IdNum,Name,Surname,AccountNumber,BankName);
    }

    //Asserting the response contains the expected values
    @Then("a new applicant is added to the application system")
    public void aNewApplicantIsAdded() {
        response.then().assertThat()
                .spec(SUCCESS)
                .body("warnings", Matchers.hasSize(0))
                .body("errors", Matchers.hasSize(0))
                .body("validationStatus", CoreMatchers.equalTo(true));
    }

    //Asserting the response contains the expected values
    @Then("a {} is returned")
    public void aWarningIsReturned(String Warning) {
        response.then().assertThat()
                .spec(SUCCESS)
                .body("errors", Matchers.hasSize(0))
                .body("warnings", CoreMatchers.hasItem(Warning))
                 ;
    }

    //Asserting the response contains the expected values
    @Then("an {} is returned")
    public void anErrorIsReturned(String Error) {
        response.then().assertThat()
                .spec(SUCCESS)
                .body("validationStatus", CoreMatchers.equalTo(false))
                .body("errors", CoreMatchers.hasItem(Error))
        ;
    }
}

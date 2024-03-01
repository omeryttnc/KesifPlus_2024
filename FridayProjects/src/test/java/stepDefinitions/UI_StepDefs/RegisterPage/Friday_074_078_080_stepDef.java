package stepDefinitions.UI_StepDefs.RegisterPage;

import io.cucumber.java.en.And;
import pages.CommonPage;

public class Friday_074_078_080_stepDef extends CommonPage {
    @And("Click the button {string}")
    public void clickTheButton(String btnTitle) {
        clickButton(btnTitle);
    }

    @And("Fill the event form with valid data")
    public void fillTheEventFormWithValidData() {
        getMyEventsPage().fillEventForm();
    }

    @And("Verify required fields")
    public void verifyRequiredFields() {
        getMyEventsPage().verifyFields();
    }

    @And("Verify that the success message and event is available")
    public void verifyThatTheSuccessMessageAndEventIsAvailable() {
        getMyEventsPage().verifyMessage("Event created");
        getMyEventsPage().verifyNewEvent();
    }

    @And("Click the Register button for the created event")
    public void clickTheRegisterButtonForTheCreatedEvent() {
        getEventsPage().clickRegisterBtn();
    }

    @And("Verify that approving the terms box is required")
    public void verifyThatApprovingTheTermsBoxIsRequired() {
        getEventsPage().verifyApproveTerms();
    }

    @And("Verify that the number of attendees is required")
    public void verifyThatTheNumberOfAttendeesIsRequired() {
        getEventsPage().verifyNumberOfAttendees();
    }

    @And("Click the approve button for event registration")
    public void clickTheApproveButtonForEventRegistration() {
        getEventsPage().clickApproveButton();
    }

    @And("Verify that the approve message and registration is available")
    public void verifyThatTheApproveMessageAndRegistrationIsAvailable() {
        getMyEventsPage().verifyMessage("You have now registered to this event");
        getEventsPage().verifyEventIsRegistered();
    }

    @And("Verify that delete button is functional")
    public void verifyThatDeleteButtonIsFunctional() {
        getMyEventsPage().deleteEventAndVerify();
    }
}

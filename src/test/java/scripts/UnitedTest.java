package scripts;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Waiter;

public class UnitedTest extends Base{

    @Test(priority = 1, description = "TASK-1 - Main Menu")
    public void testMainMenu(){
        driver.get("https://www.united.com/en/us");

        for(WebElement menu : unitedHomePage.mainMenu){
            Assert.assertTrue(menu.isDisplayed());
        }
    }

    @Test(priority = 2, description = "TASK-2 - Book Travel Menu")
    public void testBookTravelMenu(){
        driver.get("https://www.united.com/en/us");

        for(WebElement menu : unitedHomePage.travelMenu){
            Assert.assertTrue(menu.isDisplayed());
        }
    }

    @Test(priority = 3, description = "TASK-3 - Round-Trip/One-Way Radio Buttons")
    public void testTripRadioButtons(){
        driver.get("https://www.united.com/en/us");

        WebElement roundTrip = unitedHomePage.travelTypeRadioButtons.get(0);
        WebElement roundTripLabel = unitedHomePage.travelTypeLabels.get(0);
        WebElement oneWay = unitedHomePage.travelTypeRadioButtons.get(1);
        WebElement oneWayLabel = unitedHomePage.travelTypeLabels.get(1);

        Assert.assertTrue(unitedHomePage.travelTypeLabels.get(0).isDisplayed());
        Assert.assertTrue(roundTrip.isEnabled() && roundTrip.isSelected());
        Assert.assertTrue(unitedHomePage.travelTypeLabels.get(1).isDisplayed());
        Assert.assertTrue(oneWay.isEnabled() && !oneWay.isSelected());

        oneWay.click();
        Waiter.pause(5);
        Assert.assertTrue(oneWay.isSelected() && !roundTrip.isSelected());
    }

    @Test(priority = 4, description = "TASK-4 - Checkboxes")
    public void testCheckboxes(){
        driver.get("https://www.united.com/en/us");

        Assert.assertTrue(unitedHomePage.bookWithMilesLabel.isDisplayed());
        Assert.assertTrue(unitedHomePage.bookWithMiles.isEnabled());
        Assert.assertFalse(unitedHomePage.bookWithMiles.isSelected());
        Assert.assertTrue(unitedHomePage.flexibleDatesLabel.isDisplayed());
        Assert.assertTrue(unitedHomePage.flexibleDates.isEnabled());
        Assert.assertFalse(unitedHomePage.flexibleDates.isSelected());

        unitedHomePage.bookWithMilesLabel.click();
        unitedHomePage.flexibleDatesLabel.click();
        Assert.assertTrue(unitedHomePage.bookWithMiles.isSelected());
        Assert.assertTrue(unitedHomePage.flexibleDates.isSelected());

        unitedHomePage.bookWithMilesLabel.click();
        unitedHomePage.flexibleDatesLabel.click();
        Assert.assertFalse(unitedHomePage.bookWithMiles.isSelected());
        Assert.assertFalse(unitedHomePage.flexibleDates.isSelected());
    }

    @Test(priority = 5, description = "TASK-5 - Validating One-Way Ticket Search")
    public void testOneWay(){
        driver.get("https://www.united.com/en/us");

        unitedHomePage.travelTypeRadioButtons.get(1).click();
        actions.click(unitedHomePage.fromInputBox).keyDown(Keys.COMMAND).sendKeys("a").perform();
        actions.keyUp(Keys.COMMAND).perform();
        unitedHomePage.fromInputBox.sendKeys("Chicago, IL, US (ORD)");
        unitedHomePage.toInputBox.sendKeys("Miami, FL, US (MIA)");
        unitedHomePage.dateInputBox.click();

        for (int i = 0; i < 4; i++) {
            unitedHomePage.nextButton.click();
            Waiter.pause(2);
        }

        unitedHomePage.jan30.click();

        unitedHomePage.travelersInputBox.click();
        Waiter.pause(3);
        unitedHomePage.plusButton.click();

        unitedHomePage.typeDropDown.click();
        unitedHomePage.businessOrFirst.click();

        unitedHomePage.findFlightButton.click();
        Waiter.pause(3);

        Assert.assertEquals(unitedHomePage.departHeader.getText(), "Depart: Chicago, IL, US to Miami, FL, US");
    }
}

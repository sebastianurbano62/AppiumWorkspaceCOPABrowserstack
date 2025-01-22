package androidTest;

import androidPages.*;
import dataSet.DataBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;


public class PersonalReminders {
    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();
        RatingModalCheck modal = new RatingModalCheck(driver);

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        MenuFragment menuFragment = new MenuFragment(driver);
        TIFValidations tif = new TIFValidations(driver);
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);
        personalRemindersValidations pr = new personalRemindersValidations(driver);
        ReadData data = new ReadData(driver);

        String PNR = data.extractData(1, 1, "PersonalReminders"),
                LastName = data.extractData(1, 2, "PersonalReminders");




        String direct2 = "abajo";

        try {
            /**Crea el reporte **/
            report.createTestReport("Personal Reminders", "Realiza las validaciones de Personal Reminders");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            Thread.sleep(1000);
            /**PASO 2. Realiza las validaciones modal To Create List **/
            pr.modalToCreateList(report, PNR, LastName);
            /**PASO 3. Click en My trips icon**/
            menuFragment.clickMyTripsIcon();
            /**PASO 4. Click en primer vuelo**/
            tif.clickFirstTripAdded();
            /**PASO 5. Identifica elemento webelement**/
            WebElement PanelTripDetails = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /**PASO 6. Swipe para llegar a personal reminders**/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /**PASO 7. Realiza las validaciones de Personal List Page**/
            pr.personalListPageValidations(report);
            /**PASO 8. Realiza las validaciones de Edit And Delete Lists**/
            pr.editAndDeleteListsValidations(report);


            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

package iOS.iOSTest;

import dataSet.DataBase;
import iOS.iOSPages.*;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class tripPreparationiOS {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        LoginiOS login = new LoginiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        tripPreparationValidationsiOS tripPrep = new tripPreparationValidationsiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);

        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

        //Reserva ida y vuelta
        String PNR = data.extractData(1, 1, "TripPreparation"), LastName = data.extractData(1, 2, "TripPreparation");
        //Reserva Miami - Bogotá ida y vuelta comprada con una cuenta cm de tier presidential en dónde se muestra el self kiosk y copa club
        String KIOSK_PNR = data.extractData(2, 1, "TripPreparation"), KIOSK_LastName = data.extractData(2, 2, "TripPreparation");

        String direct1 = "arriba";
        String direct2 = "abajo";
        String tripDomestic = "David", tripInternationalM = "Miami", tripInternationalB = "Bogota";

        try {

            /**Crea el reporte **/
            report.createTestReport("Trip Preparation", "Realiza las validaciones de Trip Preparation");
            /** PASO 1. Se invoca la clase **/
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            booking.swipeSuperSmall(Panel, driver, direct2);
            /**PASO 2. Realiza las validaciones tripPeparationValidateSections **/
            tripPrep.tripPeparationValidateSections(report, PNR, LastName);
            /**PASO 3. Realiza las validaciones tripPreparationValidateDescriptionNavTab **/
            tripPrep.tripPeparationValidateDescriptionNavTab(report);
            /**PASO 4. Realiza las validaciones tripPreparationEnhacement**/
            tripPrep.tripPreparationEnhancementsValidations(report, KIOSK_PNR, KIOSK_LastName);
            /**PASO 5. Realiza las validaciones sectionOnBoard **/
            tripPrep.sectionOnBoardValidations(report);
            /**PASO 6. Realiza las validaciones de navigationTab **/
            tripPrep.navigationTabsValidations(report);
            /**PASO 7. Elimina el vuelo y termina el flujo **/
            menuFragment.deleteTrip();

            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

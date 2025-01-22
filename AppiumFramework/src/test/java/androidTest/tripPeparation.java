package androidTest;

import androidPages.*;
import dataSet.DataBase;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class tripPeparation {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        RatingModalCheck modal = new RatingModalCheck(driver);
        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);
        tripPreparationValidations tripPrep = new tripPreparationValidations(driver);
        ReadData data = new ReadData(driver);

        //Reserva ida y vuelta
        String PNR = data.extractData(1, 1, "TripPreparation"),
                LastName = data.extractData(1, 2, "TripPreparation");
        //Reserva Miami - Bogotá ida y vuelta comprada con una cuenta cm de tier presidential en dónde se muestra el self kiosk y copa club
        String KIOSK_PNR = data.extractData(2, 1, "TripPreparation"),
                KIOSK_LastName = data.extractData(2, 2, "TripPreparation");


        try {
            /**Crea el reporte **/
            report.createTestReport("Trip Preparation", "Realiza las validaciones de Trip Preparation");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            Thread.sleep(1000);
            /**PASO 2. Realiza las validaciones tripPeparationValidateSections **/
            tripPrep.tripPeparationValidateSections(report, PNR, LastName);
            Thread.sleep(5000);
            /**PASO 3. Realiza las validaciones tripPreparationValidateDescriptionNavTab **/
            tripPrep.tripPeparationValidateDescriptionNavTab(report);
            /**PASO 4. Realiza las validaciones tripPreparationEnhacement**/
            tripPrep.tripPeparationEnhacementValidations(report, KIOSK_PNR, KIOSK_LastName);
            /**PASO 5. Realiza las validaciones sectionOnBoard **/
            tripPrep.sectionOnBoardValidations(report);
            /**PASO 6. Realiza las validaciones de navigationTab **/
            tripPrep.navigationTabsValidations(report);


            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

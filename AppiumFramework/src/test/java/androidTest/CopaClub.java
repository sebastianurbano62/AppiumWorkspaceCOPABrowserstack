package androidTest;

import androidPages.*;
import dataSet.DataBase;
import io.appium.java_client.AppiumDriver;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class CopaClub {
    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        //WebDriver proxyDriver = DriverFactoryAndroid.createDriverWithConditionCheck(driver);

        DataBase db = new DataBase();

        RatingModalCheck modal = new RatingModalCheck(driver);

        //Se crean las instancias de cada flujo automatizado.
        CopaClubValidations copa = new CopaClubValidations(driver);
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        ReadData data = new ReadData(driver);

        String PNRUSD = data.extractData(1, 1, "CopaClub");
        String LastNameUSD = data.extractData(1, 2, "CopaClub");
        String PNRMXN = data.extractData(2, 1, "CopaClub");
        String LastNameMXN = data.extractData(2, 2, "CopaClub");

        try {

            /**Crea el reporte **/
            report.createTestReport("Validaciones", "Realiza las validaciones del Módulo CopaClub");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            Thread.sleep(1000);
            /** PASO 2. Realiza las validaciones CopaClubPass2PaxUSDVisa **/
//            modal.closeRatingModalIfPresent();
//            Thread.sleep(5000);
//            copa.CopaClubPass2PaxUSDVisa(report, PNRUSD, LastNameUSD);
            /** PASO 3. Realiza las validaciones CopaClubPass2PaxMXNVisa **/
            modal.closeRatingModalIfPresent();
            Thread.sleep(5000);
            copa.CopaClubPass2PaxUSDVisa(report, PNRMXN, LastNameMXN);


            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

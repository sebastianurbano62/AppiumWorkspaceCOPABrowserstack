package iOS.iOSTest;

import dataSet.DataBase;
import iOS.iOSPages.*;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumDriver;

public class ContactUsFligthStatusiOS {
    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        //WebDriver proxyDriver = DriverFactory.createDriverWithConditionCheck(driver);

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        ContactUsValidationsiOS contact = new ContactUsValidationsiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);


        try {
            /**Crea el reporte **/
            report.createTestReport("Contact us", "Realiza las validaciones de Contact us");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            //driver.findElement(AppiumBy.accessibilityId("dialogCloseButton")).click();
            /** PASO 2. Validaciones MainScreen **/
            contact.MainScreenValidations(report);
            /** PASO 3. Validaciones Country Select **/
            contact.CountrySelectionValidations(report);
            /** PASO 4. Validaciones New Click to Call Validations **/
            contact.NewClicktoCallValidations(report);
            /** Paso 5. Regresa al Home y termina las validaciones **/
            menuFragment.clickHomeIcon();

            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

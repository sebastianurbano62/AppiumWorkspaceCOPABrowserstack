package iOS.iOSTest;

import dataSet.DataBase;
import iOS.iOSPages.*;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class OnBoardingLandingHomeiOS {
    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        OnBoardingLandingHomeValidationsiOS onboarding = new OnBoardingLandingHomeValidationsiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);

        //Reserva en ventana de check in
        String PNRC = data.extractData(1, 1, "OnboardingLandingHome"), LastNameC = data.extractData(1, 2, "OnboardingLandingHome");
        //Reserva normal
        String PNR = data.extractData(2, 1, "OnboardingLandingHome"), LastName = data.extractData(2, 2, "OnboardingLandingHome");
        Double user = data.extractUserCM(1, 1, "ConnectMiles");
        String direct2 = "abajo", direct1 = "arriba";

        try {
            /**Crea el reporte **/
            report.createTestReport("Validaciones On Boarding Landing Home", "Realiza las validaciones de On Boarding landing Home");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. Realiza las validaciones de OnBoarding **/
            //onboarding.OnBoardingValidations(report);
            /** PASO 3. Realiza las validacones de LandingPage **/
            //onboarding.LandingPageValidations(report);
            /**PASO 4. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 5. Realiza Swipes para ver boton de entretenimiento **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 6. Realiza las validaciones del Botón de Entertaiment **/
            onboarding.EntertaimentValidations(report);
            /** PASO 7. Realizar swipe hacia arriba**/
            booking.swipeValidateStopover(Panel, driver, direct1);
            booking.swipeValidateStopover(Panel, driver, direct1);
            /** PASO 8. Realiza las validaciones de Home Base Layout**/
            onboarding.HomeBaseLayoutValidations(report);
            /** PASO 9. Realizar swipe hacia arriba**/
            booking.swipeValidateStopover(Panel, driver, direct1);
            Thread.sleep(3000);
            /** PASO 10. Realiza las validaciones de MENU**/
            onboarding.MenusValidations(report, String.format("%.0f", user));
            /**PASO 11. Realiza validaciones de Card Status**/
            onboarding.CardStatusValidations(report, PNRC, LastNameC, PNR, LastName);
            /** PASO 12. Realiza validaciones de Card Carusel y cierra sesión**/
            onboarding.CardCaruselValidations(report, PNR, LastName);
            logout.logoutUser();
            /** PASO 13. Realiza validaciones de Home Alerts**/
            onboarding.HomeAlertsValidations(report, String.format("%.0f", user));
            /** PASO 14. Cierra sesión **/
            logout.logoutUser();





            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

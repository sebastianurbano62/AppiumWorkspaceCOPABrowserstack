package androidTest;

import androidPages.*;
import dataSet.DataBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class OnBoardingLandingHome {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        RatingModalCheck modal = new RatingModalCheck(driver);

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        OnBoardingLandingHomeValidations OBLH = new OnBoardingLandingHomeValidations(driver);
        Report report = new Report(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        ReadData data = new ReadData(driver);

        //Reserva con check in
        String PNRC = data.extractData(1, 1, "OnboardingLandingHome"), LastNameC = data.extractData(1, 2, "OnboardingLandingHome");
        //Reserva normal
        String PNR = data.extractData(2, 1, "OnboardingLandingHome"), LastName = data.extractData(2, 2, "OnboardingLandingHome");
        String direct1 = "arriba";
        String direct2 = "abajo";

        try {
            /**Crea el reporte **/
            report.createTestReport("Validaciones", "Realiza las validaciones de On Boarding landing Home");
            /** PASO 1. Realiza las validaciones **/
            Thread.sleep(5000);
            OBLH.onBoardingValidations(report);
            Thread.sleep(5000);
            OBLH.landingPageValidationsAndEntertainmentValidations(report, driver);
            /** Paso 2. Regresa al Home y termina las validaciones **/
            menuFragment.clickHomeIcon();
            /** PASO 3. Realiza las validaciones de Home Base Layout**/
            OBLH.HomeBaseLayoutValidations(report);
            /** PASO 4. Realizar swipe hacia arriba**/
            Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            booking.swipeValidateStopover(Panel, driver, direct1);
            Thread.sleep(3000);
            /** PASO 5. Realiza las validaciones de MENU**/
            OBLH.MenusValidations(report);
            /**PASO 6. Realiza validaciones de Card Status**/
            OBLH.CardStatusValidations(report, PNRC, LastNameC, PNR, LastName);
            /** PASO 7. Realiza validaciones de Card Carusel**/
            OBLH.CardCaruselValidations(report, PNR, LastName);
            /** PASO 8. Realiza validaciones de Home Alerts**/
            OBLH.HomeAlertsValidations(report);

            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

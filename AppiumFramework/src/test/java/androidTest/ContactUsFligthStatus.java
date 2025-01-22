package androidTest;

import androidPages.*;
import dataSet.DataBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class ContactUsFligthStatus {

    public static void main(String[] args) throws Exception {
        AppiumDriver driver = null;

        try {
            // Inicializa el driver
            Driver.initializeDriver("1"); // Ajusta el ID del dispositivo según tu configuración
            // Obtiene el driver
            driver = Driver.getDriver();
//            driver = Driver.getAndroidDriver();

        DataBase db = new DataBase();

        RatingModalCheck modal = new RatingModalCheck(driver);

        //Se crean las instancias de cada flujo automatizado.
        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        ContactUsValidations contactUs = new ContactUsValidations(driver);
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        String direct2 = "abajo";

            try {
                /**Crea el reporte **/
                report.createTestReport("Validaciones de ContactUS", "MainScreen validaciones");
                /** PASO 1. Se invoca la clase **/
                Thread.sleep(1000);
                firstSteps.skipFirstSteps();
                Thread.sleep(2000);
                /** PASO 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
                modal.closeRatingModalIfPresent();
                WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
                /** PASO 3. Swipe para ver el ícono de mis viajes **/
                booking.swipeSmall(Panel, driver, direct2);
                booking.swipeSmall(Panel, driver, direct2);
                /** PASO 4. Validaciones de MainScreen **/
                contactUs.MainScreenValidations(report);
                /** PASO 5. Validaciones de CountrySelection **/
                contactUs.countrySelectionValidations(report);
                /** PASO 6. Validaciones de newClickToCallButton **/
                contactUs.newClickToCallButton(report);
                /** Paso 7. Regresa al Home y termina las validaciones **/
                menuFragment.clickHomeIcon();

                report.cerrar();

            } catch (Throwable e) {
                // Manejar cualquier excepción
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (Throwable e) {
            System.out.println("El error es "+e+"\n");
            throw new RuntimeException(e);
        }
    }
}
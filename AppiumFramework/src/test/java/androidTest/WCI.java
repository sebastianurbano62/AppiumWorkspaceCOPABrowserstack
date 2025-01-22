package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class WCI {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = Driver.getAndroidDriver();

        RatingModalCheck modal = new RatingModalCheck(driver);

        /** Se crean las instancias de cada flujo automatizado. **/
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        OriginDestinationVal valOriginDest;

        ReadData data = new ReadData(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";
        String PNR = data.extractData(1, 1, "WCI");
        String LastName = data.extractData(1, 2, "WCI");

        try {
            /** Crea el reporte **/
            report.createTestReport("Validaciones WCI", "Search reservation for Check-In Modal, Check-In WebView integration, Revisiones generales");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            Thread.sleep(1000);
            /** identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
            booking.swipeSmall(Panel, driver, direct2);
            /** PASO 2. CLick al botón de check in **/
//            menuFragment.clickCheckinIcon();
            menuFragment.ClickAddTripHome();
            /** PASO 3. Valida que se muestre los títulos de check in y la opción de cancelar **/
//            wci.validateCheckinTitles(report);
            /** PASO 4. Valida que esté presente la opción cancelar en checkin **/
//            wci.validateCheckinCancelOption(report);
            /** PASO 5. Realiza las validaciones cuando los datos son erróneos en wci **/
            wci.validateWrongDataCheckin(PNR, LastName, report);
            /** PASO 6. Realiza las validaciones cuando los datos son correctos en wci **/
            wci.validateCorrectDataCheckin(PNR, LastName, report);
            /** PASO 7. Flujo eliminar mis vuelos **/
            menuFragment.deleteTrip();
            /** PASO 8. Click en inicio **/
            menuFragment.clickHomeIcon();
            Thread.sleep(1000);
            /** PASO 9. Swipe para ver el ícono de mis viajes **/
            //booking.swipeSuperSmall(Panel, driver, direct2);
            /** PASO 10. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 11. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 12. Valida los elementos en la pantalla agregar un viaje **/
            wci.validateAddTripTitles(report);
            /** PASO 13. Llena el campo de PNR y apellido, luego busca la reservación **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 14. Valida que se muestre el mensaje de viaje agregado EL MENSAJE CAMBIÓ Y SÓLO APARECE SEGUNDOS **/
            //wci.validateAddedTripMessage(report);
            /** PASO 15. Valida los elementos en trip details **/
            wci.validateTripDetails(report);
            wci.clickBackFlightDetailsCheckIn();
            /** PASO 16. Click al ícono inicio **/
            menuFragment.clickHomeIcon();
            Thread.sleep(1000);
            /** PASO 17. Hace un pequeño swipe y valida que se muestre la opción de check-in **/
            //booking.swipeSmall(Panel, driver, direct2);
            wci.validateHomeCheckIn(report);
            //System.out.println("Botón de Check-in mostrado correctamente");
            //report.testPassed("Valida botón check-in", true);
            /** PASO 18. Elimina el vuelo **/
            menuFragment.deleteTrip();
            /** PASO 19. Regresa a inicio **/
            menuFragment.clickHomeIcon();
            /** PASO 20. Validaciones de RetrieveFFN **/
            //wci.validateRetrieveFFN(user, LastName_CM, report);
            /** PASO 21. Regresa a inicio y finaliza **/
            //menuFragment.clickHomeIcon();


        }catch (Throwable e) {
            throw new RuntimeException(e);
        }
        /** cierra el reporte **/
        report.cerrar();
    }
}

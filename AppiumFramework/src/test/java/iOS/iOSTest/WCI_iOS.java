package iOS.iOSTest;

import iOS.iOSPages.*;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import iOS.objectsiOS.OriginDestinationValiOS;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class WCI_iOS {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = DriveriOS.getiOSDriver();

        /** Se crean las instancias de cada flujo automatizado. **/
        ReportiOS report = new ReportiOS(driver);
        FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);

        String PNR = data.extractData(1, 1, "WCI"), LastName = data.extractData(1, 2, "WCI");
        String user = "241326052", LastName_CM= "FRONT";
        String direct1 = "arriba";
        String direct2 = "abajo";
        try {
            /** Crea el reporte **/
            report.createTestReport("Validaciones WCI", "Search reservation for Check-In Modal, Check-In WebView integration, Revisiones generales");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            booking.swipeSuperSmall(Panel, driver, direct2);
            booking.swipeSuperSmall(Panel, driver, direct2);
            /** PASO 2. CLick al botón de agregar un viaje en el home **/
            menuFragment.clickCheckinIcon();
            //menuFragment.clickAddTripHome(); DESCOMENTAR AL TENER DE VUELTA EL NEW HOME
            /** PASO 5. Realiza las validaciones cuando los datos son erróneos en wci **/
            wci.validateWrongDataCheckin(PNR, LastName, report);
            /** PASO 6. Realiza las validaciones cuando los datos son correctos en wci **/
            wci.validateCorrectDataCheckin(PNR, LastName, report);
            /** PASO 7. Flujo eliminar mis vuelos **/
            menuFragment.deleteTrip();
            /** PASO 8. Click en inicio **/
            menuFragment.clickHomeIcon();
            Thread.sleep(1000);
            modal.closeRatingModalIfPresent();
            /** PASO 9. Swipe para ver el ícono de mis viajes **/
            //booking.swipeSmall(Panel, driver, direct2);
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
            /** PASO 14. Valida que se muestre el mensaje de viaje agregado **/
            //wci.validateAddedTripMessage(report);      // LA MANERA DE MOSTRAR EL MENSAJE EN IOS CAMBIÓ Y AHORA NO SE PUEDE INTERACTUAR CON ÉL, POR ENDE
                                                         // NO SE PUEDE VALIDAR
            /** PASO 15. Valida los elementos en trip details **/
            wci.validateTripDetails(report);
            /** PASO 16. Click atrás y al ícono inicio **/
            tif.clickBackFlightDetails();
            menuFragment.clickHomeIcon();
            Thread.sleep(1000);
            modal.closeRatingModalIfPresent();
            /** PASO 17. Hace un pequeño swipe y valida que se muestre la opción de check-in **/
            //booking.swipeSmall(Panel, driver, direct2);
            wci.validateHomeCheckIn(report);
            /** PASO 18. Elimina el vuelo **/
            menuFragment.deleteTrip();
            /** PASO 19. Regresa a inicio y finaliza **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();
            /** PASO 20. Validaciones de RetrieveFFN **/
            //wci.validateRetrieveFFN(user, LastName_CM, report);
            /** PASO 22. Elimina el vuelo y finaliza **/
            //menuFragment.deleteTrip();

        }catch (Throwable e) {
            throw new RuntimeException(e);
        }
        /** cierra el reporte **/
        report.cerrar();
    }
}

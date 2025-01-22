package iOS.iOSTest;

import iOS.iOSPages.*;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CopaClubiOS {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        //Se crean las instancias de cada flujo automatizado.
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        CopaClubValidationsiOS cc = new CopaClubValidationsiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";
        String PNR = data.extractData(1, 1, "CopaClub");
        String LastName = data.extractData(1, 2, "CopaClub");

        try {
            /** Crea el reporte **/
            report.createTestReport("Copa Club Validaciones", "Comprar un Copa ClubPass para una reserva PTY-BOG OW con 2 pax seleccionando la T1 con emitida en USD con Visa");
            /** PASO 1. Se invoca la clase **/
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(1500);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Click al viaje agregado buscado por nombre destino del vuelo **/
            tif.clickFirstTripAdded();
            Thread.sleep(5000);
            /** PASO 7. Identifica el elemento al cual hará el swipe **/
            WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 8. Envía los elemento para ejecutar el Swipe para ver el copa club en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            /** PASO 9. Reliza las validaciones de copa club **/
            cc.CopaClubPass2Pax(report, PNR, LastName);

            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

package iOS.iOSTest;

import iOS.iOSPages.*;
import io.appium.java_client.AppiumDriver;
import iOS.objectsiOS.OriginDestinationValiOS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class TIF_T37_iOS {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        //Se crean las instancias de cada flujo automatizado.
        ReportiOS report = new ReportiOS(driver);
        FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);

        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        LoginiOS login = new LoginiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";
        //Reserva limpia sin tif llenado
        String PNR = data.extractData(18, 1, "TIF");
        String LastName = data.extractData(18, 2, "TIF");
        String adult = data.extractData(18, 3, "TIF"),
                child = data.extractData(18, 4, "TIF"),
                infant = data.extractData(18, 5, "TIF");

        try {
            /** Crea el reporte **/
            report.createTestReport("Comportamiento de los campos", "email y Redress Number, Know Traveler Number");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            /** PASO 3. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 4. Swipe para ver el ícono de mis viajes **/
            booking.swipeSmall(Panel, driver, direct2);
            /** PASO 5. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 6. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 7. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 8. Click al viaje agregado buscado por nombre destino del vuelo **/
            //booking.clickTextViewXpath(tripInternationalM);
            tif.clickFirstTripAdded();
            /** PASO 9. Identifica el elemento al cual hará el swipe **/
            WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 10. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 11. Click al pasajero adulto para llenar los campos **/
            tif.clickEditInformation(adult);
            /** PASO 12. Llena los campos del pasajero adulto y luego refresca con un swipe **/
            tif.fillFieldsT37AndValidations("adult", report);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct1);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct1);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct1);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct1);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct1);
            Thread.sleep(6000);
            /** PASO 16. hace un swipe para tener a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 17. Click al pasajero child para validar los campos **/
            tif.clickEditInformation(child);
            /** PASO 18. Llena y valida los campos en el pasajero child **/
            tif.fillFieldsT37AndValidations("child", report);
            /** PASO 19. Regresa atrás y borra el vuelo **/
            tif.clickBack();
            Thread.sleep(100);
            tif.clickBackFlightDetails();
            menuFragment.deleteTrip();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

package iOS.iOSTest;

import iOS.iOSPages.*;
import iOS.objectsiOS.OriginDestinationValiOS;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class TIF_T1_iOS {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        //Se crean las instancias de cada flujo automatizado.
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
        ReadDataiOS data = new ReadDataiOS(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";
        String PNR = data.extractData(1, 1, "TIF");
        String LastName = data.extractData(1, 2, "TIF");
        String PNR2 = data.extractData(1, 7, "TIF");
        String LastName2 = data.extractData(1, 8, "TIF");
        String adult = data.extractData(1, 3, "TIF"),
                child = data.extractData(1, 4, "TIF"),
                infant = data.extractData(1, 5, "TIF"),
                adult2 = data.extractData(1, 9, "TIF");

        try {
            /** Crea el reporte **/
            report.createTestReport("Validación de campos REGULAR APIS", "Para un vuelo doméstico sin loguearse");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            /** PASO 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 3. Swipe para ver el ícono de mis viajes **/
            booking.swipeSmall(Panel, driver, direct2);
            /** PASO 4. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(1500);
            /** PASO 5. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 6. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 7. Click al viaje agregado buscado por nombre destino del vuelo **/
            //booking.clickTextViewXpath(tripDomestic);
            tif.clickFirstTripAdded();
            /** PASO 8. Identifica el elemento al cual hará el swipe **/
            WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 9. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            Thread.sleep(2000);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            /** PASO 10. Click al pasajero adulto para validar los campos **/
            tif.clickEditInformation(adult);
            /** PASO 11. Valida los campos en el pasajero adulto **/
            tif.scriptT1_T6Validations("no login","adult", report);
            /** PASO 12. Regresa atrás y hace un swipe para tener a los pasajeros en pantalla **/
            tif.clickBack();
            /** PASO 13. Click al pasajero child para validar los campos **/
            tif.clickEditInformation(infant);
            /** PASO 14. Valida los campos en el pasajero child **/
            tif.scriptT1_T6Validations("no login","infant", report);
            /** PASO 15. Regresa atrás para volver a flight details y volver a my trips **/
            tif.clickBack();
            Thread.sleep(100);
            tif.clickBackFlightDetails();
            /** PASO 16. Click al pasajero infant para validar los campos **/
            menuFragment.deleteTrip();
            /** PASO 17. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 18. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 19. Click al viaje agregado buscado por nombre destino del vuelo **/
            tif.clickFirstTripAdded();
            /** PASO 20. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            Thread.sleep(2000);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            /** PASO 17. Valida los campos en el pasajero infante **/
            tif.scriptT1_T6Validations("no login","child", report);
            /** PASO 18. Regresa atrás, elimina el vuelo, regresa a inicio, cierra el reporte y termina el flujo **/
            tif.clickBack();
            Thread.sleep(100);
            tif.clickBackFlightDetails();
            menuFragment.deleteTrip();
            menuFragment.clickHomeIcon();
            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

package iOS.iOSTest;

import iOS.iOSPages.*;
import io.appium.java_client.AppiumDriver;
import iOS.objectsiOS.OriginDestinationValiOS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class TIF_T47_T54_iOS {

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
        String PNR = data.extractData(26, 1, "TIF");
        String LastName = data.extractData(26, 2, "TIF");
        String adult = data.extractData(26, 3, "TIF"),
                child = data.extractData(26, 4, "TIF"),
                infant = data.extractData(26, 5, "TIF");

        String PNRDomestic = data.extractData(1, 1, "TIF");
        String LastNameDomestic = data.extractData(1, 2, "TIF");
        String adultDomestic = data.extractData(1, 3, "TIF");
        try {
            /** Crea el reporte **/
            report.createTestReport("Update TIF", "Agregar Emergency Contact after Check In");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            /** PASO 2. Inicia sesión y regresa a inicio **/
            //login.loginUser(user, password, false);
            //menuFragment.clickHomeIcon();
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
            /** PASO 11. Click al pasajero adulto para validar los campos **/
            tif.clickEditInformation(adult);
            /** PASO 12. Validaciones **/
            tif.validateT47_T54(report, "no login", "adult");
            /** PASO 14. Click al pasajero adulto para llenar los campos **/
            tif.clickEditInformation(adult);
            /** PASO 15. Llena los campos del pasajero adulto **/
            tif.fillFieldsUsaNationality("no login");
            /** PASO 17. Click al pasajero infante para validar **/
            tif.clickEditInformation(infant);
            /** PASO 18. Valida que no se muestre los campos de contacto emergencia en infante **/
            tif.validateT47_T54(report, "no login", "infant");
            /** PASO 19. regresa y Elimina el vuelo **/
            tif.clickBack();
            Thread.sleep(100);
            tif.clickBackFlightDetails();
            menuFragment.deleteTrip();
            /** PASO 20. Va al home y agrega el vuelo doméstico para validar **/
            menuFragment.clickHomeIcon();
            //booking.swipeSmall(Panel, driver, direct2);
            /** PASO 21. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 22. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 23. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNRDomestic, LastNameDomestic);
            /** PASO 24. Click al viaje agregado buscado por nombre destino del vuelo **/
            tif.clickFirstTripAdded();
            /** PASO 25. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 26. Click al pasajero adulto para validar que no se muestren los campos de contacto de emergencia **/
            tif.clickEditInformation(adultDomestic);
            tif.clickFirstTripAdded();
            Thread.sleep(4000);
            /** PASO 27. Valida que no se muestren los campos contacto de emergencia en el vuelo doméstico **/
            tif.validateT47_T54(report, "no login", "domestic");
            /** PASO 28. regresa, Elimina el vuelo y termina las validaciones **/
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

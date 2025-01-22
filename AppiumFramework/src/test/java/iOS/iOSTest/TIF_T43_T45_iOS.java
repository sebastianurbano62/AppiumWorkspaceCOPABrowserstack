package iOS.iOSTest;

import iOS.iOSPages.*;
import io.appium.java_client.AppiumDriver;
import iOS.objectsiOS.OriginDestinationValiOS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class TIF_T43_T45_iOS {

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
        String PNR = data.extractData(22, 1, "TIF");
        String LastName = data.extractData(22, 2, "TIF");
        String adult = data.extractData(22, 3, "TIF"),
                child = data.extractData(22, 4, "TIF"),
                infant = data.extractData(22, 5, "TIF");

        double user = data.extractUserCM(1, 1, "ConnectMiles");
        String password = data.extractData(1, 2, "ConnectMiles");
        try {
            /** Crea el reporte **/
            report.createTestReport("Quitar opción de save to profile", "Si el usuario no ha hecho cambios y Agregar logo de TSA Pre Check en KTN");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            /** PASO 2. Inicia sesión y regresa a inicio **/
            login.loginUser(String.format("%.0f", user), password, true);
            menuFragment.clickHomeIcon();
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
            /** PASO 12. Llena los campos del tif **/
            tif.fillFieldsUsaNationality("login");
            /** PASO 14. Click al pasajero adulto para validar los campos **/
            tif.clickEditInformation(adult);
            tif.validateT43_T45(report, "t43");
            Thread.sleep(1000);
            /** PASO 16. Click al pasajero adulto para llenar los campos **/
            tif.clickEditInformation(adult);
            tif.validateT43_T45(report, "t44");
            Thread.sleep(1000);
            /** PASO 18. Click al pasajero adulto para llenar los campos **/
            tif.clickEditInformation(adult);
            tif.validateT43_T45(report, "t45");
            /** PASO 19. regresa, Elimina el vuelo, cierra sesión termina las validaciones **/
            Thread.sleep(100);
            tif.clickBackFlightDetails();
            menuFragment.deleteTrip();
            logout.logoutUser();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

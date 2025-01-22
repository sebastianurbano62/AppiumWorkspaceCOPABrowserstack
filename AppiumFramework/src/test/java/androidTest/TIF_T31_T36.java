package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.Report;
public class TIF_T31_T36 {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        //Se crean las instancias de cada flujo automatizado.
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        Login login = new Login(driver);
        Logout logout = new Logout(driver);
        OriginDestinationVal valOriginDest;

        ReadData data = new ReadData(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";


        String PNR = data.extractData(16, 1, "TIF");
        String LastName = data.extractData(16, 2, "TIF");
        String PNR2 = data.extractData(14, 7, "TIF");
        String LastName2 = data.extractData(14, 8, "TIF");
        String adult = data.extractData(16, 3, "TIF"),
                child = data.extractData(16, 4, "TIF"),
                infant = data.extractData(16, 5, "TIF");
        String tripInternationalM = data.extractData(16, 6, "TIF");


        try {
            /** Crea el reporte **/
            report.createTestReport("Validaciónes de Agregar In Transit", "Para un vuelo internacional USA");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            Thread.sleep(500);
            /** PASO 3. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 4. Swipe para ver el ícono de mis viajes **/
            //booking.swipeSmall(Panel, driver, direct2);
            /** PASO 5. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 6. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 7. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 8. Click al viaje agregado buscado por nombre destino del vuelo **/
            booking.clickTextViewXpath(tripInternationalM);
            Thread.sleep(3500);
            //tif.clickFirstTripAdded();
            /** PASO 9. Identifica el elemento al cual hará el swipe **/
            WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 10. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 11. Click al pasajero adulto para llenar los campos **/
            tif.clickEditInformation(adult);
            Thread.sleep(4000);
            /** PASO 12. Llena los campos del pasajero adulto **/
            tif.fillFieldsAllPanamaWhereAYSCheckInTransitAndValidate("no login", "adult", report);
            /** PASO 13. Regresa atrás **/
            tif.clickBack();
            /** PASO 14. Click al pasajero child para validar los campos **/
            tif.clickEditInformation(infant);
            Thread.sleep(4000);
            /** PASO 15. Llena y valida los campos en el pasajero infant **/
            tif.fillFieldsAllPanamaWhereAYSCheckInTransitAndValidate("no login", "infant", report);
            /** PASO 16. Regresa atrás **/
            tif.clickBack();
            Thread.sleep(100);
            tif.clickBackFlightDetails();
            /** PASO 17. Click en eliminar la reserva **/
            menuFragment.deleteTrip();
            /** PASO 18. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 19. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR2, LastName2);
            /** PASO 20. Click al viaje agregado buscado por nombre destino del vuelo **/
            booking.clickTextViewXpath(tripInternationalM);
            Thread.sleep(5000);
            /** PASO 21. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 22. Click al pasajero infant para validar los campos **/
            tif.clickEditInformation(child);
            Thread.sleep(7000);
            /** PASO 23. Llena y valida los campos en el pasajero child y regresa al home **/
            tif.fillFieldsAllPanamaWhereAYSCheckInTransitAndValidate("no login", "child", report);
            tif.clickBack();
            tif.clickBackFlightDetails();
            /** PASO 24. Elimina el vuelo y termina las validaciones **/
            menuFragment.deleteTrip();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.Report;
public class TIF_T25 {

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
        OriginDestinationVal valOriginDest;

        ReadData data = new ReadData(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";


        String PNR = data.extractData(13, 1, "TIF");
        String LastName = data.extractData(13, 2, "TIF");
        String PNR2 = data.extractData(13, 7, "TIF");
        String LastName2 = data.extractData(13, 8, "TIF");
        String adult = data.extractData(13, 3, "TIF"),
                child = data.extractData(13, 4, "TIF"),
                infant = data.extractData(13, 5, "TIF");
        String tripInternationalM = data.extractData(13, 6, "TIF");

        try {
            /** Crea el reporte **/
            report.createTestReport("Validación de campos REGULAR APIS Y WHERE ARE YOU STAYING", "Para un vuelo internacional USA sin loguearse");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            Thread.sleep(1000);
            /** PASO 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 3. Swipe para ver el ícono de mis viajes **/
            booking.swipeSmall(Panel, driver, direct2);
            /** PASO 4. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 5. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 6. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 7. Click al viaje agregado buscado por nombre destino del vuelo **/
            booking.clickTextViewXpath(tripInternationalM);
            Thread.sleep(5000);
            //tif.clickFirstTripAdded();
            /** PASO 8. Identifica el elemento al cual hará el swipe **/
            WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 9. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 10. Click al pasajero adulto para llenar los campos **/
            tif.clickEditInformation(adult);
            Thread.sleep(4000);
            /** PASO 12. Llena los campos del pasajero adulto **/
            tif.fillFieldsAllPanamaAndWhereAreYouStaying("no login");
            /** PASO 13. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/

            /** PASO 14. Click al pasajero adulto para validar los campos **/
            tif.clickEditInformation(adult);
            Thread.sleep(4000);
            /** PASO 15. Valida los campos en el pasajero adulto **/
            tif.regularApisWhereAYSAndPersonalInformationValidations("adult","no login", report);
            /** PASO 16. Regresa atrás **/
            tif.clickBack();
            /** PASO 17. Click al pasajero infant para validar los campos **/
            tif.clickEditInformation(infant);
            Thread.sleep(4000);
            /** PASO 18. Valida los campos en el pasajero infant **/
            tif.regularApisWhereAYSAndPersonalInformationValidations("infant","no login", report);
            /** PASO 19. Regresa atrás **/
            tif.clickBack();
            Thread.sleep(100);
            tif.clickBackFlightDetails();
            /** PASO 20. Click en eliminar la reserva **/
            menuFragment.deleteTrip();
            /** PASO 21. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 22. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR2, LastName2);
            /** PASO 23. Click al viaje agregado buscado por nombre destino del vuelo **/
            booking.clickTextViewXpath(tripInternationalM);
            Thread.sleep(5000);
            /** PASO 24. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 25. Click al pasajero child para validar los campos **/
            tif.clickEditInformation(child);
            /** PASO 26. Valida los campos en el pasajero child **/
            tif.regularApisWhereAYSAndPersonalInformationValidations("child","no login", report);
            tif.clickBack();
            tif.clickBackFlightDetails();
            /** PASO 27. Elimina el vuelo, regresa a inicio y termina el flujo **/
            menuFragment.deleteTrip();
            menuFragment.clickHomeIcon();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

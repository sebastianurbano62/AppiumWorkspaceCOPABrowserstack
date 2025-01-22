package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.Report;

public class TIF_T46 {

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

        String PNR = data.extractData(24, 1, "TIF");
        String LastName = data.extractData(24, 2, "TIF");
        String adult = data.extractData(24, 3, "TIF"),
                child = data.extractData(24, 4, "TIF"),
                infant = data.extractData(24, 5, "TIF");
        String tripInternationalM = data.extractData(24, 6, "TIF");
        double user = data.extractUserCM(1, 1, "ConnectMiles");
        String password = data.extractData(1, 2, "ConnectMiles");

        try {
            /** Crea el reporte **/
            report.createTestReport("Mensaje de error", "relacionado al FFN, Email y Guardado en Update TIF");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            //Thread.sleep(1000);
            /** PASO 2. Inicia sesión y regresa a inicio **/
            login.loginUser(String.format("%.0f", user), password, false);
            menuFragment.clickHomeIcon();
            /** PASO 3. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 4. Swipe para ver el ícono de mis viajes **/
            //booking.swipeSmall(Panel, driver, direct2);
            /** PASO 5. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(5000);
            /** PASO 6. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(3000);
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
            /** PASO 12. Llena y valida el mensaje de error **/
            tif.validateT46(report);
            Thread.sleep(1000);
            /** PASO 19. Regresa al home, Elimina el vuelo, cierra sesión termina las validaciones **/
            tif.clickBack();
            tif.clickBackFlightDetails();
            menuFragment.deleteTrip();
            logout.logoutUser();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import objects.OriginDestinationVal;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.GeneratedUtils;
import utils.Report;

import java.io.FileInputStream;

public class TIF_T2_T6 {

    public static void main(String[] args) throws Throwable {
        // Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        // Se crean las instancias de cada flujo automatizado.
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        Login login = new Login(driver);
        Logout logout = new Logout(driver);

        ReadData data = new ReadData(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";
        String PNR = data.extractData(2, 1, "TIF");
        String LastName = data.extractData(2, 2, "TIF");
        String PNR2 = data.extractData(2, 7, "TIF");
        String LastName2 = data.extractData(2, 8, "TIF");
        String adult = data.extractData(2, 3, "TIF"),
                child = data.extractData(2, 4, "TIF"),
                infant = data.extractData(2, 5, "TIF");
        String tripDomestic = data.extractData(2, 6, "TIF");
        double user = data.extractUserCM(1, 1, "ConnectMiles");
        String password = data.extractData(1, 2, "ConnectMiles");
        int index = 1;

        try {
            /** Crea el reporte **/
            report.createTestReport("Validación de campos REGULAR APIS", "Para un vuelo internacional no USA logueado");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            //Thread.sleep(1000);
            while (index != 6) { // Loop necesario para ejecutar con cada cuenta connect miles
                /** PASO 2. Inicia sesión y regresa a inicio **/
                login.loginUser(String.format("%.0f", user), password, false);
                Thread.sleep(2000);
                menuFragment.clickHomeIcon();
                /** PASO 3. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
                WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
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
                booking.clickTextViewXpath(tripDomestic);
                Thread.sleep(5000);
                //tif.clickFirstTripAdded();
                /** PASO 9. Identifica el elemento al cual hará el swipe **/
                WebElement PanelTripDetails = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
                /** PASO 10. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                /** PASO 11. Click al pasajero adulto para validar los campos **/
                tif.clickEditInformation(adult);
                Thread.sleep(4000);
                /** PASO 12. Valida los campos en el pasajero adulto **/
                tif.scriptT1_T6Validations("login","adult", report);
                /** PASO 13. Regresa atrás **/
                tif.clickBack();
                /** PASO 14. Click al pasajero child para validar los campos **/
                tif.clickEditInformation(infant);
                Thread.sleep(4000);
                /** PASO 15. Valida los campos en el pasajero adulto **/
                tif.scriptT1_T6Validations("login","infant", report);
                /** PASO 16. Regresa atrás y hace un swipe para tener a los pasajeros en pantalla **/
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
                booking.clickTextViewXpath(tripDomestic);
                Thread.sleep(5000);
                /** PASO 21. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                /** PASO 22. Click al pasajero infant para validar los campos **/
                tif.clickEditInformation(child);
                Thread.sleep(4000);
                /** PASO 23. Valida los campos en el pasajero infante **/
                tif.scriptT1_T6Validations("login","child", report);
                tif.clickBack();
                tif.clickBackFlightDetails();
                /** PASO 24. Elimina el vuelo, cierra sesión termina las validaciones **/
                menuFragment.deleteTrip();
                logout.logoutUser();
                /** PASO 25. Click en cancelar en la pantalla de inicio de sesión para quedar en el home **/
                tif.clickCancel();
                /** PASO 26. Aumenta el valor del index para repetir las validaciones **/
                index++;
                /** PASO 27. Switch creado para cambiar de index y hacer las validaciones para las otras 4 cuentas connect miles **/
                switch (index) {
                    case 2:
                        user = data.extractUserCM(2, 1, "ConnectMiles");
                        password = data.extractData(2, 2, "ConnectMiles");
                        break;
                    case 3:
                        user = data.extractUserCM(3, 1, "ConnectMiles");
                        password = data.extractData(3, 2, "ConnectMiles");
                        break;
                    case 4:
                        user = data.extractUserCM(4, 1, "ConnectMiles");
                        password = data.extractData(4, 2, "ConnectMiles");
                        break;
                    case 5:
                        user = data.extractUserCM(5, 1, "ConnectMiles");
                        password = data.extractData(5, 2, "ConnectMiles");
                        break;
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

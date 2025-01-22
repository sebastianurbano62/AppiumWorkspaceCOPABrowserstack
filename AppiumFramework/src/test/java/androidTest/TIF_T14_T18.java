package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.Report;

import java.io.FileInputStream;

public class TIF_T14_T18 {

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
        String PNR = data.extractData(8, 1, "TIF");
        String LastName = data.extractData(8, 2, "TIF");
        String PNR2 = data.extractData(8, 7, "TIF");
        String LastName2 = data.extractData(8, 8, "TIF");
        String adult = data.extractData(8, 3, "TIF"),
                child = data.extractData(8, 4, "TIF"),
                infant = data.extractData(8, 5, "TIF");
        String tripInternationalM = data.extractData(10, 6, "TIF");
        double user = data.extractUserCM(1, 1, "ConnectMiles");
        String password = data.extractData(1, 2, "ConnectMiles");
        int index = 1;

        try {
            /** Crea el reporte **/
            report.createTestReport("Validación de campos REGULAR APIS", "Para un vuelo internacional USA logueado");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            //Thread.sleep(1000);
            while (index != 6) { // Loop necesario para ejecutar con cada cuenta connect miles
                /** PASO 2. Inicia sesión y regresa a inicio **/
                login.loginUser(String.format("%.0f", user), password, false);
                menuFragment.clickHomeIcon();
                /** PASO 3. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
                WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
                /** PASO 4. Swipe para ver el ícono de mis viajes **/
                booking.swipeSmall(Panel, driver, direct2);
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
                Thread.sleep(5000);
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
                if (index == 1) {  //Estos pasos se hacen únicamente en el primer usuario CM ya que en los otros se usa
                    //el mismo vuelo y no es necesario hacer el llenado
                    /** PASO 12. Llena los campos del pasajero adulto **/
                    tif.fillFieldsUsaNationality("login");
                    /** PASO 13. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/

                    /** PASO 14. Click al pasajero adulto para validar los campos **/
                    tif.clickEditInformation(adult);
                    Thread.sleep(4000);
                }
                /** PASO 15. Valida los campos en el pasajero adulto **/
                tif.regularApisValidations("login", report);
                /** PASO 16. Regresa atrás y hace un swipe para tener a los pasajeros en pantalla **/
                tif.clickBack();
                /** PASO 17. Click al pasajero infante para validar los campos **/
                tif.clickEditInformation(infant);
                Thread.sleep(4000);
                /** PASO 18. Valida los campos en el pasajero infant **/
                tif.regularApisValidations("login", report);
                /** PASO 19. Regresa atrás y hace un swipe para tener a los pasajeros en pantalla **/
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
                Thread.sleep(7000);
                /** PASO 26. Valida los campos en el pasajero child **/
                tif.regularApisValidations("login", report);
                tif.clickBack();
                tif.clickBackFlightDetails();
                /** PASO 27. Elimina el vuelo, cierra sesión termina las validaciones **/
                menuFragment.deleteTrip();
                logout.logoutUser();
                /** PASO 28. Click en cancelar en la pantalla de inicio de sesión para quedar en el home **/
                //tif.clickCancel();
                /** PASO 29. Aumenta el valor del index para repetir las validaciones **/
                index++;
                /** PASO 30. Switch creado para cambiar de index y hacer las validaciones para las otras 4 cuentas connect miles **/
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

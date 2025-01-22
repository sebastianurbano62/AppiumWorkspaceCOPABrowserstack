package iOS.iOSTest;

import iOS.iOSPages.*;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class TIF_T2_T6_iOS {

    public static void main(String[] args) throws Throwable {
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
        String PNR = data.extractData(2, 1, "TIF");
        String LastName = data.extractData(2, 2, "TIF");
        String adult = data.extractData(2, 3, "TIF"),
                child = data.extractData(2, 4, "TIF"),
                infant = data.extractData(2, 5, "TIF");

        double user = data.extractUserCM(1, 1, "ConnectMiles");
        String password = data.extractData(1, 2, "ConnectMiles");
        int index = 1;

        try {
            /** Crea el reporte **/
            report.createTestReport("Validación de campos REGULAR APIS", "Para un vuelo doméstico logueado");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            while (index != 6) { // Loop necesario para ejecutar con cada cuenta connect miles
                /** PASO 2. Inicia sesión y regresa a inicio **/
                login.loginUser(String.format("%.0f", user), password, true);
                Thread.sleep(1000);
                menuFragment.clickHomeIcon();
                /** PASO 3. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
                WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
                /** PASO 4. Swipe para ver el ícono de mis viajes **/
                if(index==1){booking.swipeSmall(Panel, driver, direct2);}
                /** PASO 5. Click al ícono de mis viajes **/
                menuFragment.clickMyTripsIcon();
                Thread.sleep(1500);
                /** PASO 6. Click al "+" agregar un viaje **/
                wci.clickAddTrip();
                Thread.sleep(1000);
                /** PASO 7. Llena el campo de PNR, el campo apellido y busca la reserva **/
                wci.writePNRandLastNameMyTrips(PNR, LastName);
                /** PASO 8. Click al viaje agregado buscado por nombre destino del vuelo **/
                //booking.clickTextViewXpath(tripDomestic);
                tif.clickFirstTripAdded();
                /** PASO 9. Identifica el elemento al cual hará el swipe **/
                WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
                /** PASO 10. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
                Thread.sleep(1000);
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                /** PASO 11. Click al pasajero adulto para validar los campos **/
                tif.clickEditInformation(adult);
                /** PASO 12. Valida los campos en el pasajero adulto **/
                tif.scriptT1_T6Validations("login","adult", report);
                /** PASO 13. Regresa atrás y hace un swipe para tener a los pasajeros en pantalla **/
                tif.clickBack();
                /** PASO 14. Click al pasajero child para validar los campos **/
                tif.clickEditInformation(child);
                /** PASO 15. Valida los campos en el pasajero adulto **/
                tif.scriptT1_T6Validations("login","child", report);
                /** PASO 16. Regresa atrás y hace un swipe para tener a los pasajeros en pantalla **/
                tif.clickBack();
                /** PASO 17. Click al pasajero infant para validar los campos **/
                tif.clickEditInformation(infant);
                /** PASO 18. Valida los campos en el pasajero infante **/
                tif.scriptT1_T6Validations("login","infant", report);
                /** PASO 19. Regresa atrás, elimina el vuelo, regresa a inicio, cierra el reporte y termina el flujo **/
                tif.clickBack();
                Thread.sleep(100);
                tif.clickBackFlightDetails();
                menuFragment.deleteTrip();
                menuFragment.clickHomeIcon();
                logout.logoutUser();
                /** PASO 20. Click en cancelar en la pantalla de inicio de sesión para quedar en el home **/
                //tif.clickCancel();
                /** PASO 21. Aumenta el valor del index para repetir las validaciones **/
                index++;
                /** PASO 22. Switch creado para cambiar de index y hacer las validaciones para las otras 4 cuentas connect miles **/
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
            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

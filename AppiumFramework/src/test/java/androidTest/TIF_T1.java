package androidTest;

import androidPages.*;
import dataSet.DataBase;
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

public class TIF_T1 {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);
        OriginDestinationVal valOriginDest;
        ReadData data = new ReadData(driver);

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
        String tripDomestic = data.extractData(1, 6, "TIF");

        try {
            /**Crea el reporte **/
            report.createTestReport("Validación de campos REGULAR APIS", "Para un vuelo doméstico sin loguearse");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            //Thread.sleep(1000);
            /**PASO 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 3. Swipe para ver el ícono de mis viajes **/
            booking.swipeSuperSmall(Panel, driver, direct2);
            /** PASO 4. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 5. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 6. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 7. Click al viaje agregado buscado por nombre destino del vuelo **/
            booking.clickTextViewXpath(tripDomestic);
            Thread.sleep(5000);
            //tif.clickFirstTripAdded();
            /** PASO 8. Identifica el elemento al cual hará el swipe **/
            WebElement PanelTripDetails = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 9. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 10. Click al pasajero adulto para validar los campos **/
            tif.clickEditInformation(adult);
            Thread.sleep(3500);
            /** PASO 11. Valida los campos en el pasajero adulto **/
            tif.scriptT1_T6Validations("no login","adult", report);
            //report.testPassed("Validar campos", true);
            /** PASO 12. Regresa atrás y hace un swipe para tener a los pasajeros en pantalla **/
            tif.clickBack();
            //booking.swipeSmall(PanelTripDetails, driver, direct1);
            /** PASO 13. Click al pasajero child para validar los campos **/
            tif.clickEditInformation(infant);
            Thread.sleep(3500);
            /** PASO 14. Valida los campos en el pasajero child **/
            tif.scriptT1_T6Validations("no login","infant", report);
            //report.testPassed("Validar campos", true);
            /** PASO 15. Regresa atrás y hace un swipe para tener a los pasajeros en pantalla **/
            tif.clickBack();
            Thread.sleep(100);
            tif.clickBackFlightDetails();
            /** PASO 16. Click en eliminar la reserva **/
            menuFragment.deleteTrip();
            /** PASO 17. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 18. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR2, LastName2);
            /** PASO 19. Click al viaje agregado buscado por nombre destino del vuelo **/
            booking.clickTextViewXpath(tripDomestic);
            Thread.sleep(5000);
            /** PASO 20. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 21. Click al pasajero infant para validar los campos **/
            tif.clickEditInformation(child);
            Thread.sleep(3500);
            /** PASO 22. Valida los campos en el pasajero infante **/
            tif.scriptT1_T6Validations("no login","child", report);
            tif.clickBack();
            tif.clickBackFlightDetails();
            /** PASO 23. Elimina el vuelo, regresa a inicio, cierra el reporte y termina el flujo **/
            menuFragment.deleteTrip();
            menuFragment.clickHomeIcon();
            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

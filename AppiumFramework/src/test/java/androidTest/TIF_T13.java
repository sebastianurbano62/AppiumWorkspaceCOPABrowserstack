package androidTest;

import androidPages.*;
import dataSet.TestData;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.Report;

import java.io.FileInputStream;
import java.sql.ResultSet;

import java.sql.*;

public class TIF_T13 {

   /* ResultSet dataTable;
    @BeforeClass
    public void beforeClass(){
        TestData data = new TestData();
        dataTable = data.getScenarioReservasJMeter( 1);
    } */

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

        /*ResultSet dataTable;
        TestData data = new TestData();
        dataTable = data.getScenarioReservasJMeter( 1);*/

        ReadData data = new ReadData(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";

        String PNR = data.extractData(7, 1, "TIF");
        String LastName = data.extractData(7, 2, "TIF");
        String PNR2 = data.extractData(7, 7, "TIF");
        String LastName2 = data.extractData(7, 8, "TIF");
        String adult = data.extractData(7, 3, "TIF"),
                child = data.extractData(7, 4, "TIF"),
                infant = data.extractData(7, 5, "TIF");
        String tripInternationalM = data.extractData(7, 6, "TIF");


        try {
            /** Crea el reporte **/
            report.createTestReport("Validación de campos REGULAR APIS", "Para un vuelo internacional USA sin loguearse");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            //Thread.sleep(1000);
            /** PASO 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 3. Swipe para ver el ícono de mis viajes **/
            //booking.swipeSuperSmall(Panel, driver, direct2);
            /** PASO 4. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(5000);
            /** PASO 5. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(3000);
            /** PASO 6. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            //wci.writePNRandLastNameMyTrips(dataTable.getString("Codigo_PNR"), dataTable.getString("Apellidoa_PNR"));
            /** PASO 7. Click al viaje agregado buscado por nombre destino del vuelo **/
            booking.clickTextViewXpath(tripInternationalM);
            Thread.sleep(3500);
            //tif.clickFirstTripAdded();
            /** PASO 8. Identifica el elemento al cual hará el swipe **/
            WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 9. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            /** PASO 10. Click al pasajero adulto para llenar los campos **/
            tif.clickEditInformation(adult);
            /** PASO 12. Llena los campos del pasajero adulto **/
            tif.fillFieldsUsaNationality("no login");
            /** PASO 14. Click al pasajero adulto para validar los campos **/
            tif.clickEditInformation(adult);
            /** PASO 15. Valida los campos en el pasajero adulto **/
            tif.regularApisValidations("no login", report);
            /** PASO 16. Regresa atrás para tener a los pasajeros en pantalla **/
            tif.clickBack();
            /** PASO 17. Click al pasajero infant para validar los campos **/
            tif.clickEditInformation(infant);
            /** PASO 18. Valida los campos en el pasajero infant **/
            tif.regularApisValidations("no login", report);
            /** PASO 19. Regresa atrás para tener a los pasajeros en pantalla **/
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
            /** PASO 26. Valida los campos en el pasajero infante y regresa a my trips**/
            tif.regularApisValidations("no login", report);
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


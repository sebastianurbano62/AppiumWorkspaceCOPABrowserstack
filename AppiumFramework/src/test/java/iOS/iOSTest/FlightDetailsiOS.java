package iOS.iOSTest;

import dataSet.DataBase;
import iOS.iOSPages.*;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FlightDetailsiOS {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
        BookingiOS booking = new BookingiOS(driver);
        FlightDetailsValidationsiOS flight = new FlightDetailsValidationsiOS(driver);

        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

        String PNR = "AXMFTL", LastName = "MOL"; //Reserva normal ida y vuelta
        String PNRD = "APTQJO", LastNameD = "ORT"; //ida y vuelta, botón checkin y boarding pass a la vez se hace con 3 pasajeros y sólo lleno el tif de uno o dos e igual con asientos
        String PNRC = "AXMSRP", LastNameC = "ESP"; //ida y vuelta, botón de checkin
        String PNRB = "APP0H5", LastNameB = "PER"; //ida y vuelta, botón boarding pass
        String PNRCC = "APP5NP", LastNameCC = "MEM"; //ida y vuelta, Reserva comprada con  status connectmiles
        String PNR_DAYSFORCI = "AXMIVU", LastNameDAYSFORCI = "PIN"; //RESERVA ida y vuelta donde se muestre cuanto falta para el checkin, debe ser 24 a 72 horas antes de que abra ventana de checkin
        String PNR_BAG = "AXM20H", LastName_BAG = "PAE"; // RESERVA ida y vuelta checkeada que tenga 24 a 3 horas antes de que salga el vuelo
        String PNR_SHOW = "AVXBIU", LastName_SHOW = "COR"; //Reserva donde se valida el copa show pass
        String direct2 = "abajo";

        try {
            /**Crea el reporte **/
            report.createTestReport("FlightDetails", "Realiza las validaciones de FlightDetails");
            /** PASO 1. Se invoca la clase **/
            Thread.sleep(6000);
            booking.swipeSuperSmall(Panel, driver, direct2);
            /** PASO 2. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(1000);
            /**PASO 3. Realiza las validaciones de travel Itinerary **/
            //flight.travelItineraryValidations(report, PNRC, LastNameC);
            /**PASO 4. Realiza las validaciones de flight Details Checkin Validations **/
            //flight.flightDetailsCheckinValidations(report, PNRC, LastNameC, PNRB, LastNameB, PNRD, LastNameD);
            /**PASO 5. Realiza las validaciones de flight Details Checkin Validations **/
            //Thread.sleep(10000); //temporal para entrar a flight details ya que no se ejecutan los pasos anteriores
            //flight.passangerInformationValidations(report, PNRCC, LastNameCC);
            /**PASO 6. Realiza las validaciones de Pull To Refresh**/
            //flight.pullToRefreshValidations(report, PNR, LastName);
            /**PASO 7. Realiza las validaciones de Seats Card**/
            //flight.seatsCardValidations(report, PNR, LastName);
            /**PASO 8. Realiza las validaciones de Header**/
            //flight.HeaderValidations(report, PNR, LastName);
            /**PASO 9. Realiza las validaciones de Card Status**/
            //flight.CardStatusValidations(report, PNR, LastName, PNR_DAYSFORCI, LastNameDAYSFORCI, PNRC, LastNameC, PNR_BAG, LastName_BAG);
            /**PASO 10. Realiza las validaciones de Copa Show Pass**/
            //flight.CopaShowPassValidations(report, PNR_SHOW, LastName_SHOW);
            /**PASO 11. Realiza las validaciones de Quick Actions**/
            //flight.QuickActionsValidations(report, PNRC, LastNameC);
            /**PASO 12. Realiza las validaciones de Flight Information**/
            flight.FlightInformationValidations(report, PNR, LastName);


            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

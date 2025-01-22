package androidTest;

import androidPages.*;
import dataSet.DataBase;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.Report;
public class FlightDetails {

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
        FlightDetailsValidations flight = new FlightDetailsValidations(driver);
        ReadData data = new ReadData(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";

        String PNR = "AXMFTL", LastName = "MOL"; //Reserva normal ida y vuelta
        String PNRD = "APTQJO", LastNameD = "ORT"; //botón checkin y boarding pass a la vez se hace con 3 pasajeros y sólo lleno el tif de uno o dos e igual con asientos
        String PNRC = "A0GRN0", LastNameC = "IGL"; //ida y vuelta, botón de checkin
        String PNRB = "A0G05S", LastNameB = "HER"; //ida y vuelta, botón boarding pass.
        String PNRCC = "APP5NP", LastNameCC = "MEM"; //Reserva comprada con  status connectmiles
        String PNR_DAYSFORC = "AXMIVU", LastNameDAYSFORC = "PIN"; //RESERVA ida y vuelta donde se muestre cuanto falta para el checkin, debe ser 24 a 72 horas antes de que abra ventana de checkin
        String PNR_BAG = "AXM20H", LastName_BAG = "PAE"; // RESERVA ida y vuelta checkeada que tenga 24 a 3 horas antes de que salga el vuelo
        String PNR_SHOW = "AVXBIU", LastName_SHOW = "COR";
        String PNRW = "", LastNameW = "";


        try {
            /**Crea el reporte **/
            report.createTestReport("FlightDetails", "Realiza las validaciones de FlightDetails");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            Thread.sleep(1000);
            /** PASO 2. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /**PASO 3. Realiza las validaciones de travel Itinerary **/
            //flight.travelItineraryValidations(report, PNRC, LastNameC);
            /**PASO 4. Realiza las validaciones de flight Details Check-in**/
            //flight.flightDetailsCheckinValidations(report, PNRC, LastNameC, PNRB, LastNameB, PNRD, LastNameD);
            /**PASO 5. Realiza las validaciones de flight Details Check-in**/
            //flight.passangerInformationValidations(report, PNRCC, LastNameCC);
            /**PASO 6. Realiza las validaciones de Pull To Refresh**/
            //flight.pullToRefreshValidations(report, PNR, LastName);
            /**PASO 7. Realiza las validaciones de Seats Card**/
            //flight.seatsCardValidations(report, PNR, LastName);
            /**PASO 8. Realiza las validaciones de header**/
            //flight.HeaderValidations(report, PNR, LastName);
            /**PASO 9. Realiza las validaciones de Card Status**/
            //flight.CardStatusValidations(report, PNR, LastName, PNR_DAYSFORC, LastNameDAYSFORC, PNRC, LastNameC, PNR_BAG, LastName_BAG);
            /**PASO 10. Realiza las validaciones de Copa Show Pass**/
            //flight.CopaShowPassValidations(report, PNR_SHOW, LastName_SHOW);
            /**PASO 11. Realiza las validaciones de Quick Actions**/
            //flight.QuickActionsValidations(report, PNRC, LastNameC);
            /**PASO 12. Realiza las validaciones de Flight Information**/
            //flight.FlightInformationValidations(report, PNR, LastName);
            /**PASO 13. Realiza las validaciones de Flight Pull To Refresh**/
            flight.FlightPullToRefreshValidations(report, PNR, LastName);
            /**PASO 14. Realiza las validaciones de Manage Reservation**/
            flight.ManageReservationsValidations(report, PNRB, LastNameB);
            /**PASO 15. Realiza las validaciones de Buy Chairs**/
            flight.BuyChairsValidations(report, PNR, LastName);
            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

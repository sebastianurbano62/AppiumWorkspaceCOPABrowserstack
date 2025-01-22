package androidTest;

import androidPages.*;
import dataSet.DataBase;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class TripHubAddTrip {
    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        DataBase db = new DataBase();

        RatingModalCheck modal = new RatingModalCheck(driver);

        //Se crean las instancias de cada flujo automatizado.
        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        Checkin wci = new Checkin(driver);
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);
        TripHubValidations triphub = new TripHubValidations(driver);
        ReadData data = new ReadData(driver);

        String direct2 = "abajo";

        String PNR = data.extractData(1, 1, "TripHubAddTrip"),
                LastName = data.extractData(1, 2, "TripHubAddTrip");
        //viaje ya realizado para validar
        String PNR_PT = data.extractData(2, 1, "TripHubAddTrip"),
                LastName_PT = data.extractData(2, 2, "TripHubAddTrip");
        //Reserva con modal de correo sin correo en el modal
        String PNR_E = data.extractData(3, 1, "TripHubAddTrip"),
                LastName_E = data.extractData(3, 2, "TripHubAddTrip");
        //Reserva con correo no confirmado desde share
        String PNR_conemailasignado = data.extractData(4, 1, "TripHubAddTrip"),
                LastName_conemailasignado = data.extractData(4, 2, "TripHubAddTrip");
        //Reserva de grupo
        String PNRW = data.extractData(5, 1, "TripHubAddTrip"),
                LastNameW = data.extractData(5, 2, "TripHubAddTrip");


        try {
            /**Crea el reporte **/
            report.createTestReport("Trip Hub y Add Trip", "Realiza las validaciones de Trip Hub y Add Trip");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            Thread.sleep(1000);
            /**PASO 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
            /** PASO 3. Swipe para ver el ícono de mis viajes **/
//            booking.swipeSuperSmall(Panel, driver, direct2);
            /** PASO 4. Click al ícono de mis viajes **/
//            menuFragment.clickMyTripsIcon();
//            Thread.sleep(2000);
            /** PASO 5. Realiza las validaciones de empty state **/
//            triphub.emptyStateValidations(report, "con pasttrip", PNR_PT, LastName_PT);
            /** PASO 6. Click al "+" agregar un viaje **/
//            wci.clickAddTrip();
//            Thread.sleep(1000);
            /** PASO 7. Llena el campo de PNR, el campo apellido y busca la reserva **/
//            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 8. Realiza las validaciones de Upcoming Trips **/
//            triphub.upcomingTripsValidations(report);
            /** PASO 9. Elimina el vuelo **/
//            menuFragment.deleteTrip();
            /** PASO 10. Realiza las validaciones de Past Trips **/
//            triphub.pastTripsValidations(report);
//            /** PASO 11. Realiza las validaciones de Past Trips Details **/
//            triphub.pastTripsDetailsValidations(report);
//            menuFragment.deleteTrip();
//            /** PASO 12. Realiza las validaciones de Home Page Validations**/
//            triphub.HomePageValidations(PNR, LastName, report);
//            Thread.sleep(10000);
//            /** PASO 13. Realiza las validaciones de Remove/Rename **/
//            triphub.Remove_RenameValidations(report, PNR, LastName);
//            modal.closeRatingModalIfPresent();
//            /** PASO 14. Realiza las validaciones de " Email Capture Modal design**/
//            triphub.EmailCaptureModalValidations(report, PNR_E, LastName_E, PNR_conemailasignado, LastName_conemailasignado);
//            /** PASO 15. Realiza las validaciones de Email Capture Modal Errors **/
//            triphub.EmailCaptureModalErrorsValidations(report, PNR_E, LastName_E);
//            /** PASO 16. Realiza las validaciones de Add Calendar **/
//            triphub.AddCalendarValidations(report, PNR, LastName);
//            menuFragment.deleteTrip();
            /** PASO 17. Realiza las validaciones de Who Page For Groups **/
            triphub.WhoPageForGroupsValidations(report, PNRW, LastNameW);
//            /** PASO 18. Realiza las validaciones de Limiting Native Loader **/
//            triphub.LimitingNativeLoaderValidations(report, PNR, LastName);


             report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

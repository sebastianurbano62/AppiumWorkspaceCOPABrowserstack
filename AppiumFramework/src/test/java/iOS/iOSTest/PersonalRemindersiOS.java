package iOS.iOSTest;

import dataSet.DataBase;
import iOS.iOSPages.*;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PersonalRemindersiOS {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        PersonalRemindersValidationsiOS pr =  new PersonalRemindersValidationsiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);

        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

        String PNR = data.extractData(1, 1, "PersonalReminders"), LastName = data.extractData(1, 2, "PersonalReminders");
        Double user = data.extractUserCM(1, 1, "ConnectMiles");
        String direct1 = "arriba";
        String direct2 = "abajo";
        String tripDomestic = "David", tripInternationalM = "Miami", tripInternationalB = "Bogota";

        try {

            /**Crea el reporte **/
            report.createTestReport("Personal Reminders", "Realiza las validaciones de Personal Reminders");
            /** PASO 1. Se invoca la clase **/
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
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
            /**PASO 7. Click al viaje agregado buscado por nombre destino del vuelo **/
            tif.clickFirstTripAdded();
            /** PASO 8. Identifica el elemento al cual hará el swipe **/
            WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 9. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            /** PASO 10. Entra en la sescción de personal reminders **/
            pr.clickPersonalReminders();
            /** PASO 11. Realiza las validaciones de modal to create list y cierra sesión **/
            pr.modalToCreateList(report, String.format("%.0f", user));
            /** PASO 13. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(1000);
            /**PASO 14. Click al viaje agregado buscado por nombre destino del vuelo **/
            tif.clickFirstTripAdded();
            /** PASO 15. Identifica el elemento al cual hará el swipe **/
            PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 16. Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla **/
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            /** PASO 17. Entra en la sescción de personal reminders **/
            pr.clickPersonalReminders();
            /** PASO 18. Realiza las validaciones de personal list page **/
            pr.personalListPage(report);
            /** PASO 19. Realiza las validaciones de edit y delete list **/
            pr.editAndDeleteListsValidations(report);
            /** PASO 20. regresa y elimina el vuelo **/
            tif.clickBackFlightDetails();
            tif.clickBackFlightDetails();
            menuFragment.deleteTrip();





            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

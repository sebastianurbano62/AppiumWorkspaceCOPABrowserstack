package androidTest;

import androidPages.*;
import androidPages.MenuFragment;
import androidPages.ReadData;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class IBEActivateStopoverForMulticityAndRoundTrip {
    public static void main(String[] args) throws Exception {
        /**  Se crea la instancia para el driver de Appium  **/
        AppiumDriver driver = Driver.getAndroidDriver();

        RatingModalCheck modal = new RatingModalCheck(driver);

        /** Se crean las instancias de cada flujo automatizado. **/
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        //Login login = new Login(driver);
        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        ReadData data = new ReadData(driver);
        OriginDestinationVal valOriginDest;
        String stopover1 = "En mi ida";
        String stopover2 = "En mi regreso";
        String origin = "origen";
        String destiny = "destino";
        String direct1 = "arriba";
        String direct2 = "abajo";
        String day1 = data.extractData(1, 0, "IBE"), day2 = data.extractData(1, 1, "IBE"), day3 = data.extractData(1, 2, "IBE");

        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "activar stopover para multicity y round-trip");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            /** PASO 2. Click al botón de Reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Click al Dropdown Tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 4. Click al Tipo de viaje Ida y vuelta **/
            booking.clickTextViewXpath("Ida y vuelta");
            /** PASO 5. Llena origen y destino **/
            booking.selectOriginDestination("BOG", "MIA");
            /** PASO 6. Valida T80 **/
            booking.validateCheckStopOver(report);
            /** PASO 7. Click al Dropdown Tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 8. Click al Tipo de viaje Multicuidad **/
            booking.clickTextViewXpath("Multiciudad / Stopover");
            /** PASO 9. Llena origen y destino **/
            booking.selectOriginDestination("PTY", "MIA");
            /** PASO 10. Valida T81 **/
            booking.validateCheckStopOver(report);
            /** PASO 11. Click al Stopover **/
            booking.clickCheckStopOver();
            /** PASO 12. Valida T83 **/
            booking.validateStopOver(stopover1, report);
            booking.validateStopOver(stopover2, report);
            /** PASO 13. Valida T84 **/
            booking.validatePanamaStopoverDefault(destiny, report);
            /** identifica el elemento en el cual se hará swipe **/
            WebElement Panel = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/action_bar_root"));
            Thread.sleep(2000);
            /** envía el elemento al cual se hará el swipe, el driver y la dirección a dónde se hará el swipe **/
            //booking.swipeSmall(Panel, driver, direct2);
            /** Valida el segundo segmento **/
            booking.validatePanamaStopoverDefault(origin, report);
            /** swipe hacia arriba para cambiar el stopover **/
            booking.swipeValidateStopover(Panel, driver, direct1);
            /** click en stopover en mi regreso **/
            booking.clickStopOverOnGoOrReturn(stopover2);
            /** Swipe hacia abajo para validar el segundo segmento **/
            booking.swipeSmall(Panel, driver, direct2);
            /** valida el segundo segmento **/
            booking.validatePanamaStopoverDefault(destiny, report);
            /** swipe hacia abajo para validar el tercer segmento **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** valida el tercer segmento **/
            booking.validatePanamaStopoverDefault("tercer", report);
            /** Swipes hacia arriba del todo **/
            booking.swipeValidateStopover(Panel, driver, direct1);
            booking.swipeValidateStopover(Panel, driver, direct1);
            /** PASO 14. Click toggle "?" en stopover **/
            booking.clickToggleStopover();
            /** PASO 15. Valida T85 **/
            booking.validateToggleStopover(report);
            /** PASO 16. Cierra el toggle de stopover **/
            booking.closeToggleStopover();

            //Validaciones T87
            /** PASO 17. Click al Stopover **/
            booking.clickStopOverOnGoOrReturn(stopover1);
            /** llena primer segmento **/
            booking.selectOriginDestinationStopOver("BOG", "MIA", origin);
            /** click en el primer calendario **/
            booking.enterCalendarMulticityWithStopover("primero");
            /** Selecciona el día del calendario **/
            booking.selectCalendarDayStopover(day1, "primero", "no");
            /** swipe al segundo segmento **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** llena el segundo segmento **/
            booking.selectOriginDestinationStopOver("BOG", "MIA", destiny);
            /** click al segundo calendario **/
            booking.enterCalendarMulticityWithStopover("segundo");
            /** Selecciona el día del calendario **/
            booking.selectCalendarDayStopover(day2, "segundo", "no");
            /** swipe al tercer tramo **/
            //booking.swipeValidateStopover(Panel, driver, direct2);
            /** click al tercer calendario **/
            booking.enterCalendarMulticityWithStopover("tercero");
            /** Selecciona el día del calendario **/
            booking.selectCalendarDayStopover(day3, "tercero", "no");
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** click en el botón buscar vuelo **/
            booking.clickFindFlightStopover();
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

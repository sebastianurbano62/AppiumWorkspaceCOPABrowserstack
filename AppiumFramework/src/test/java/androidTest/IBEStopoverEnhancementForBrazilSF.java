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

public class IBEStopoverEnhancementForBrazilSF {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = Driver.getAndroidDriver();

        RatingModalCheck modal = new RatingModalCheck(driver);

        /** Se crean las instancias de cada flujo automatizado. **/
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        //Login login = new Login(driver);
        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        ReadData data = new ReadData(driver);

        String origin = "origen";
        String destiny = "destino";
        String direct1 = "arriba";
        String direct2 = "abajo";
        String day1 = data.extractData(5, 0, "IBE"), day2 = data.extractData(5, 1, "IBE");

        try {
            /** CREA EL REPORTE **/
            report.createTestReport("IBE Integration", "Redemption Stopover Enhancement For Brazil SF");
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
            booking.selectOriginDestination("GIG", "BOG");
            /** PASO 6. Click toggle "?" en stopover **/
            booking.clickToggleStopover();
            /** PASO 7. Valida T89 **/
            booking.validateToggleStopover(report);
            /** PASO 8. Cierra el toggle de stopover **/
            booking.closeToggleStopover();
            /** PASO 9. Click al Dropdown Tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /**PASO 10. Click al Tipo de viaje Multicuidad **/
            booking.clickTextViewXpath("Multiciudad / Stopover");
            /** PASO 11. Click toggle "?" en stopover **/
            booking.clickToggleStopover();
            /** PASO 12. Valida T89 **/
            booking.validateToggleStopover(report);
            /** PASO 13. Cierra el toggle de stopover **/
            booking.closeToggleStopover();
            /** PASO 14. Click al Stopover **/
            booking.clickCheckStopOver();
            /** PASO 15. llena primer segmento **/
            booking.selectOriginDestinationStopOver("GIG", "BOG", origin);
            /** PASO 16. llena el calendario del segundo segmento **/
            booking.enterCalendarMulticityWithStopover("primero");
            booking.selectCalendarDayStopover(day1, "primero", "no");
            Thread.sleep(1000);
            /** PASO 17. identifica el elemento en el cual se hará swipe **/
            WebElement Panel = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/action_bar_root"));
            /** PASO 18. swipe hacia abajo para llenar el calendario del segundo segmento **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 19. llena el calendario del segundo segmento **/
            booking.enterCalendarMulticityWithStopover("segundo");
            booking.selectCalendarDayStopover(day2, "segundo", "si");
            /** PASO 20. Valida T90 **/
            booking.validateDatesNoApplyForStopOver(report);
            /** PASO 21. Click en cambiar fechas **/
            booking.clickAdjustDatesStopOver();
            /** PASO 22. Cierra el calendario **/
            booking.clickCloseCalendar();
            /** PASO 23. Swipe hacia arribab **/
            Thread.sleep(1000);
            booking.swipeValidateStopover(Panel, driver, direct1);
            booking.swipeValidateStopover(Panel, driver, direct1);
            //Validaciones T91
            /** PASO 24. Click al Dropdown Tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 25. Click al Tipo de viaje Multicuidad **/
            booking.clickTextViewXpath("Ida y vuelta");
            /** PASO 26. Llena origen y destino **/
            booking.selectOriginDestination("GIG", "BOG");
            /** PASO 27. Click al check añadir stopover **/
            booking.clickCheckStopOver();
            /** PASO 28. Swipe hacia abajo **/
            Thread.sleep(1000);
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 29. Valida que este presente el selector de la cantidad de noches de stopover en Brazil **/
            booking.validateDaySelectorOnBrazilStopOver(report);

        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();

    }
}

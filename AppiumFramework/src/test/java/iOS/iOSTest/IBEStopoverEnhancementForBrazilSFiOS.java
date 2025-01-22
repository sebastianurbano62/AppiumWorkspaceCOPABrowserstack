package iOS.iOSTest;

import iOS.iOSPages.BookingiOS;
import iOS.iOSPages.FirstStepsiOS;
import iOS.iOSPages.MenuFragmentiOS;
import iOS.iOSPages.ReadDataiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class IBEStopoverEnhancementForBrazilSFiOS {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = DriveriOS.getiOSDriver();

        /** Se crean las instancias de cada flujo automatizado. **/
        ReportiOS report = new ReportiOS(driver);
        FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);

        String origin = "origen";
        String destiny = "destino";
        String direct1 = "arriba";
        String direct2 = "abajo";
        String day1 = data.extractData(5, 3, "IBE"),
                day2 = data.extractData(5, 4, "IBE");

        try {
            /** CREA EL REPORTE **/
            report.createTestReport("IBE Integration", "Redemption Stopover Enhancement For Brazil SF");
            /** PASO 1. Se invoca la clase **/
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            //firstSteps.skipFirstSteps();
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
            /** PASO 10. Click al Tipo de viaje Multicuidad **/
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
            booking.selectOriginDestinationWithStopOverBrazil("GIG", "BOG", origin);
            /** PASO 16. llena el calendario del primer segmento **/
            booking.enterCalendarMulticityWithStopoverOnMyGo("primero");
            booking.selectCalendarDayStopover(day1, "primero");
            Thread.sleep(1000);
            /** PASO 17. identifica el elemento en el cual se hará swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 18. swipe hacia abajo para llenar el calendario del segundo segmento **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 19. llena el calendario del segundo segmento **/
            booking.enterCalendarMulticityWithStopoverOnMyGo("segundo");
            booking.selectCalendarDayStopover(day2, "segundo");
            /** PASO 20. Valida T90 **/
            booking.validateDatesNoApplyForStopOver(report);
            /** PASO 21. Click en cambiar fechas **/
            booking.clickAdjustDatesStopOver();
            /** PASO 22. Cierra el calendario **/
            booking.clickCloseCalendar();
            /** PASO 23. Swipe hacia arriba **/
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

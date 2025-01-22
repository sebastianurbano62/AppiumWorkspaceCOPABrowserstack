package iOS.iOSTest;

import iOS.iOSPages.BookingiOS;
import iOS.iOSPages.MenuFragmentiOS;
import iOS.iOSPages.ReadDataiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class IBEActivateStopoverForMulticityAndRoundTripiOS {
    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = iOS.utilsiOS.DriveriOS.getiOSDriver();

        /** Se crean las instancias de cada flujo automatizado. **/
        iOS.utilsiOS.ReportiOS report = new iOS.utilsiOS.ReportiOS(driver);
        iOS.iOSPages.FirstStepsiOS firstSteps = new iOS.iOSPages.FirstStepsiOS(driver, report);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);

        String stopover1 = "En mi ida";
        String stopover2 = "En mi regreso";
        String origin = "origen";
        String destiny = "destino";
        String direct1 = "arriba";
        String direct2 = "abajo";
        String dia1 = data.extractData(1, 3, "IBE"),
                dia2 = data.extractData(1, 4, "IBE"),
                dia3 = data.extractData(1, 5, "IBE");

        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "activar stopover para multicity y round-trip");
            /**PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. Click al botón de Reserva **/
            menuFragment.clickBookingIcon();
            Thread.sleep(1500);
            /** PASO 3. Click al Dropdown Tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 4. Click al Tipo de viaje Ida y vuelta **/
            booking.clickTypeOfTrip("Ida y vuelta");
            /** PASO 5. Llena origen y destino **/
            booking.selectOriginDestination("BOG", "MIA");
            /** PASO 6. Valida T80 **/
            booking.validateCheckStopOver(report);
            /** PASO 7. Click al Dropdown Tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 8. Click al Tipo de viaje Multicuidad **/
            booking.clickTypeOfTrip("Multiciudad / Stopover");
            /** PASO 9. Llena origen y destino **/
            booking.selectOriginDestinationMulticity("PTY", "MIA");
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
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            Thread.sleep(2000);
            /** envía el elemento al cual se hará el swipe, el driver y la dirección a dónde se hará el swipe **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** Valida el segundo segmento **/
            booking.validatePanamaStopoverDefault(origin, report);
            /** swipe hacia arriba para cambiar el stopover **/
            booking.swipeValidateStopover(Panel, driver, direct1);
            booking.swipeValidateStopover(Panel, driver, direct1);
            /** click en stopover en mi regreso **/
            booking.clickStopOverOnGoOrReturn(stopover2);
            /** Swipe hacia abajo para validar el segundo segmento **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** valida el segundo segmento **/
            booking.validatePanamaStopoverDefaultOnReturn(destiny, report);
            /** swipe hacia abajo para validar el tercer segmento **/
            booking.swipeSmall(Panel, driver, direct2);
            /** valida el tercer segmento **/
            booking.validatePanamaStopoverDefaultOnReturn(origin, report);
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
            /** PASO 17. Click al Stopover en mi ida **/
            booking.clickStopOverOnGoOrReturn(stopover1);
            /** llena primer segmento **/
            booking.selectOriginDestinationStopOver("BOG", "MIA", origin);
            /** click en el primer calendario **/
            booking.enterCalendarMulticityWithStopoverOnMyGo("primero");
            /** selecciona el dia del vuelo **/
            booking.selectCalendarDayStopover(dia1, "primero");
            /** swipe al segundo segmento **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            //booking.swipeSmall(Panel, driver, direct2);
            /** llena el segundo segmento **/
            booking.selectOriginDestinationStopOver("BOG", "MIA", destiny);
            /** click al segundo calendario **/
            booking.enterCalendarMulticityWithStopoverOnMyGo("segundo");
            /** selecciona el dia del vuelo **/
            booking.selectCalendarDayStopover(dia2, "segundo");
            /** pausa por carga **/
            Thread.sleep(1000);
            /** swipe al tercer segmento **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** click al tercer calendario **/
            booking.enterCalendarMulticityWithStopoverOnMyGo("tercero");
            /** selecciona el dia del vuelo **/
            booking.selectCalendarDayStopover(dia3, "tercero");
            /** swipe para buscar vuelo **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** click en el botón buscar vuelo **/
            booking.clickFindNOSingleFlight();
            Thread.sleep(20000);
            report.testPassed("Valida que se pueda realizar una búsqueda con Stopover", true);
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

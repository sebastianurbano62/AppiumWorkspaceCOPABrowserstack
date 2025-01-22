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

public class IBEUserNavigationInWebViewiOS {

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
        String day1 = data.extractData(6, 3, "IBE"),
                day2 = data.extractData(6, 4, "IBE");

        try {
            /** CREA EL REPORTE **/
            report.createTestReport("IBE Integration", "User Navigation In Web View");
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
            booking.selectOriginDestination("BOG", "MIA");
            /** PASO 6. llenado del calendario **/
            booking.enterCalendarRoundTripWithOriginDestinyAdded();
            booking.selectCalendarDays(day1, day2);
            Thread.sleep(1000);
            /** PASO 7. identifica el elemento en el cual se hará swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 8. swipe hacia abajo para visualizar el botón buscar vuelo **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 9. Click al botón buscar vuelo **/
            booking.clickFindSingleFlight();
            /** PASO 10. Pausa por búsqueda de vuelo **/
            Thread.sleep(30000);
            /** PASO 11. Click a la X para salir del web view **/
            booking.clickXOnWebView();
            /** PASO 12. Valida T95 **/
            booking.validateClosePageOnWebView(report);
            /** PASO 13. Click en sí para salir del web view **/
            booking.clickYesOnWebView();
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

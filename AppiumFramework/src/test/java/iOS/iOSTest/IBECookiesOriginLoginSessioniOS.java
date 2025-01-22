package iOS.iOSTest;

import iOS.iOSPages.*;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class IBECookiesOriginLoginSessioniOS {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = DriveriOS.getiOSDriver();

        /** Se crean las instancias de cada flujo automatizado. **/
        ReportiOS report = new ReportiOS(driver);
        FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        LoginiOS login = new LoginiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);

        String direct2 = "abajo";
        String day1 = data.extractData(3, 3, "IBE"),
                day2 = data.extractData(3, 4, "IBE");

        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Cookies origin login session");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. Realiza el login en el app **/
            login.loginUser("121195865", "Copa2022", false);
            /** PASO 3. CLick al botón de reserva **/
            modal.closeRatingModalIfPresent();
            menuFragment.clickBookingIcon();
            /** PASO 4. Llena origen y destino **/
            booking.selectOriginDestination("BOG", "MIA");
            /** PASO 5. Entra al calendario **/
            booking.enterCalendarRoundTripWithOriginDestinyAdded();
            /** PASO 6. Selecciona las fechas de calendario **/
            booking.selectCalendarDays(day1, day2);
            Thread.sleep(1000);
            /** PASO 7. identifica el elemento en el cual se hará swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 8. swipe hacia abajo para visualizar el botón buscar vuelo **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 9. Click al botón buscar vuelo **/
            booking.clickFindSingleFlight();
            /** PASO 10. Pausa por búsqueda de vuelo **/
            Thread.sleep(35000);
            /** PASO 11. Click al ícono logeado del usuario CM en el webview **/
            booking.clickLogedIconCMWebView();
            /** PASO 12. Valida T98 **/
            booking.validateAccountCMWebView("121195865", report);
            /** PASO 13. Click cerrar sesión en web view **/
            booking.clickCloseSessionCMWebView();
            /** PASO 14. Pausa por carga **/
            Thread.sleep(20000);
            /** PASO 15. Click al ícono deslogeado del usuario CM en el webview **/
            booking.clickUnlogedIconCMWebView();
            /** PASO 16. Escribe el usuario CM en el web view **/
            login.loginUserWebView("121196086", "Copa2022");
            /** PASO 17. Click a la X para salir del web view **/
            booking.clickXOnWebView();
            /** PASO 18. Click en sí para salir del web view **/
            booking.clickYesOnWebView();
            Thread.sleep(1000);
            /** PASO 19. Click en el ícono de cuenta **/
            modal.closeRatingModalIfPresent();
            booking.clickAccountIcon();
            /** PASO 20. Pausa por carga **/
            Thread.sleep(6000);
            /** PASO 21. Valida T99 **/
            booking.validateAccountCMWebView("121 195 865", report);
            //report.testPassed("Valida que se cerró también la sesión en el app", true);
            logout.logoutUser();
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

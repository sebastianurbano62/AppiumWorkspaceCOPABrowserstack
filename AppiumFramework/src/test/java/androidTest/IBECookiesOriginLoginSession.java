package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class IBECookiesOriginLoginSession {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = Driver.getAndroidDriver();

        RatingModalCheck modal = new RatingModalCheck(driver);

        /** Se crean las instancias de cada flujo automatizado. **/
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        Login login = new Login(driver);
        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        ReadData data = new ReadData(driver);
        String direct2 = "abajo";
        String day1 = data.extractData(3, 0, "IBE"), day2 = data.extractData(3, 1, "IBE");

      try{
          /** Crea el reporte **/
          report.createTestReport("IBE Integration", "Cookies origin login session");
        /** PASO 1. Se invoca la clase **/
        firstSteps.skipFirstSteps();
        modal.closeRatingModalIfPresent();
        /** PASO 2. Realiza el login en el app **/
        login.loginUser("121196086", "Copa2022", false);
        /** PASO 3. CLick al botón de reserva **/
        menuFragment.clickBookingIcon();
        /** PASO 4. Llena origen y destino **/
        Thread.sleep(2000);
        booking.selectOriginDestination("BOG", "MIA");
        /** PASO 5. Entra al calendario **/
        booking.enterCalendar();
        /** PASO 5. FALTA EL LLENADO DEL CALENDARIO **/
        booking.selectCalendarDays(day1, day2);
        Thread.sleep(1000);
        /** PASO 6. identifica el elemento en el cual se hará swipe **/
        WebElement Panel = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/action_bar_root"));
        /** PASO 7. swipe hacia abajo para visualizar el botón buscar vuelo **/
        booking.swipeValidateStopover(Panel, driver, direct2);
        /** PASO 8. Click al botón buscar vuelo **/
        booking.clickFindFlight();
        /** PASO 9. Pausa por búsqueda de vuelo **/
        Thread.sleep(35000);
        /** PASO 10. Click al ícono logeado del usuario CM en el webview **/
        booking.clickLogedIconCMWebView();
        Thread.sleep(1000);
        /** PASO 11. Valida T98 **/
        booking.validateAccountCMWebView("121196086", report);
        /** PASO 12. Click cerrar sesión en web view **/
        booking.clickCloseSessionCMWebView();
        /** PASO 13. Pausa por carga **/
        Thread.sleep(25000);
        /** PASO 14. Click al ícono deslogeado del usuario CM en el webview **/
        booking.clickUnlogedIconCMWebView();
        /** PASO 15. Pausa y Oculta el teclado **/
        Thread.sleep(3000);
        driver.navigate().back();
        /** PASO 16. Escribe el usuario CM en el web view **/
        login.loginUserWebView("121196086", "Copa2022");
        /** PASO 17. Click a la X para salir del web view **/
        booking.clickXOnWebView();
        /** PASO 18. Click en sí para salir del web view **/
        booking.clickYesOnWebView();
        /** PASO 20. Click en el ícono de cuenta **/
        booking.clickAccountIcon();
        Thread.sleep(10000);
        /*booking.clickAccountIcon();
        Thread.sleep(1500);*/
        report.testPassed("Valida que se deslogueo del app al cerrar sesión en el WebView", true);
        /** PASO 21. Pausa por carga **/
        //Thread.sleep(6000);
        /** PASO 22. Valida T99 **/
        //booking.validateAccountCMWebView("230 015 732",report);

      }catch (Throwable e){
          throw new RuntimeException(e);
      }
      report.cerrar();
    }
}

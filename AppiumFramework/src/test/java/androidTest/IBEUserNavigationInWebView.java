package androidTest;

import androidPages.*;
import androidPages.MenuFragment;
import androidPages.ReadData;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class IBEUserNavigationInWebView {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = Driver.getAndroidDriver();

        RatingModalCheck modal = new RatingModalCheck(driver);

        /**Se crean las instancias de cada flujo automatizado. **/
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
        String day1 = data.extractData(6, 0, "IBE"), day2 = data.extractData(6, 1, "IBE");

        try {
            /** CREA EL REPORTE **/
            report.createTestReport("IBE Integration", "User Navigation In Web View");
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
            /** PASO 6. llenado del calendario **/
            booking.enterCalendar();
            booking.selectCalendarDays(day1, day2);
            Thread.sleep(1000);
            /** PASO 7. identifica el elemento en el cual se hará swipe **/
            WebElement Panel = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/action_bar_root"));
            /** PASO 8. swipe hacia abajo para visualizar el botón buscar vuelo **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 9. Click al botón buscar vuelo **/
            booking.clickFindFlight();
            /** PASO 10. Pausa por búsqueda de vuelo **/
            Thread.sleep(30000);
            //PAUSA PARA LLENAR EL CALENDARIO MANUALMENTE
            Thread.sleep(15000);
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

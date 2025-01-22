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

public class IBECatalogPromocodeServiceIntegration {

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
        String day1 = data.extractData(2, 0, "IBE"), day2 = data.extractData(2, 1, "IBE");

        try{
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Catalog promocode service Integration");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            /** PASO 2. Click al botón de Reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Llena origen y destino **/
            booking.selectOriginDestination("BOG", "MIA");
            /** PASO 4. Click en el calendario **/
            booking.enterCalendar();
            /** PASO 5. Selecciona los días de vuelo **/
            booking.selectCalendarDays(day1, day2);
            Thread.sleep(1000);
            /** PASO 6. identifica el elemento en el cual se hará swipe **/
            WebElement Panel = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/action_bar_root"));
            /** PASO 7. swipe hacia abajo para visualizar el promcode **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 8. Click en agregar código promocional **/
            booking.clickPromCode();
            /** PASO 9. Escribe el promcode **/
            booking.writePromCode("NCASW08");
            /** PASO 10. Click a agregar el promcode **/
            booking.addPromCode();
            Thread.sleep(5000);
            /** PASO 11. swipe hacia abajo para seleccionar el botón buscar vuelo **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 12. Click al botón de buscar vuelo y valida T92 **/
            booking.clickFindFlight();
            /* PAUSA PARA LLENAR EL CALENDARIO MANUALMENTE Y CLICKEAR EN BUSCAR VUELO
            Thread.sleep(20000); */
            /** PASO 13. Pausa necesaria mientras busca el vuelo **/
            Thread.sleep(35000);
            report.testPassed("Validar que funciona las busquedas con PromCode", true);
            /** PASO 14. Click a la X para salir del web view **/
            booking.clickXOnWebView();
            /** PASO 15. Click en sí para salir del web view **/
            booking.clickYesOnWebView();
            /** PASO 16. Click en eliminar promcode **/
            booking.eliminatePromcode();
            /** PASO 17. Click en agregar código promocional **/
            booking.clickPromCode();
            /** PASO 18. Valida T93 look and field de promcode **/
            booking.validateLookAndFieldPromCode(report);
            /** PASO 19. Cierra promcode **/
            booking.closePromcode();

        }catch (Throwable e){
        throw new RuntimeException(e);
        }
        report.cerrar();

    }
}

package iOS.iOSTest;

import iOS.iOSPages.BookingiOS;
import iOS.iOSPages.FirstStepsiOS;
import iOS.iOSPages.MenuFragmentiOS;
import iOS.iOSPages.ReadDataiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class IBECatalogPromocodeServiceIntegrationiOS {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = DriveriOS.getiOSDriver();

        /** Se crean las instancias de cada flujo automatizado. **/
        ReportiOS report = new ReportiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);

        String origin = "origen";
        String destiny = "destino";
        String direct1 = "arriba";
        String direct2 = "abajo";

        String day1 = data.extractData(2, 3, "IBE"),
                day2 = data.extractData(2, 4, "IBE"),
                promCode1 = data.extractData(2, 6, "IBE");

        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Catalog promocode service Integration");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. Click al botón de Reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Llena origen y destino **/
            booking.selectOriginDestination("BOG", "MIA");
            /** PASO 4. Click en el calendario **/
            booking.enterCalendarRoundTripWithOriginDestinyAdded();
            /** PASO 5. Selecciona los días de vuelo **/
            booking.selectCalendarDays(day1, day2);
            Thread.sleep(1000);
            /** PASO 6. identifica el elemento en el cual se hará swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 7. swipe hacia abajo para visualizar el promcode **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 8. Click en agregar código promocional **/
            booking.clickPromCode();
            /** PASO 9. Escribe el promcode **/
            booking.writePromCode(promCode1);
            /** PASO 10. Click a agregar el promcode **/
            booking.addPromCode();
            Thread.sleep(2000);
            /** PASO 11. Entra al calendario para seleccionar las fechas **/
            booking.enterCalendarRoundTripWithOriginDestinyAdded();
            /** PASO 12. Selecciona las fechas del calendario **/
            booking.selectCalendarDays(day1, day2);
            /** PASO 13. swipe hacia abajo para seleccionar el botón buscar vuelo **/
            booking.swipeValidateStopover(Panel, driver, direct2);
            /** PASO 14. Click al botón de buscar vuelo y valida T92 **/
            booking.clickFindSingleFlight();
            /** PASO 15. Pausa necesaria mientras busca el vuelo **/
            Thread.sleep(30000);
            report.testPassed("Valida que se pueda realizar la búsqueda con código promocional", true);
            /** PASO 16. Click a la X para salir del web view **/
            booking.clickXOnWebView();
            /** PASO 17. Click en sí para salir del web view **/
            booking.clickYesOnWebView();
            /** PASO 18. Click en eliminar promcode **/
            booking.eliminatePromcode();
            /** PASO 19. Click en agregar código promocional **/
            booking.clickPromCode();
            /** PASO 20. Valida T93 look and field de promcode **/
            booking.validateLookAndFieldPromCode(report);
            /** PASO 21. Cierra promcode **/
            booking.closePromcode();
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

package iOS.iOSTest;

import iOS.iOSPages.BookingiOS;
import iOS.iOSPages.FirstStepsiOS;
import iOS.iOSPages.ReadDataiOS;
import iOS.objectsiOS.OriginDestinationValiOS;
import iOS.iOSPages.MenuFragmentiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Report;

public class IBERedemptionBookingPanelCheckBoxiOS {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = DriveriOS.getiOSDriver();

        /** Se crean las instancias de cada flujo automatizado. **/
        ReportiOS report = new ReportiOS(driver);
        FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        OriginDestinationValiOS valOriginDest;
        ReadDataiOS data = new ReadDataiOS(driver);

        String promCode1 = data.extractData(2, 6, "IBE");
        String promCode2 = data.extractData(3, 6, "IBE");
        String direct2 = "abajo";

        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Redemption Booking Panel CheckBox");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. Click al botón de Reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Click al Dropdown Tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 4. Click al Tipo de viaje Multiciudad **/
            booking.clickTextViewXpath("Multiciudad / Stopover");
            /** PASO 5. Valida T73 **/
            booking.validateCheckBoxPriceInMilesDisabled(report);
            /** PASO 6. Click al Dropdown Tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 7. Click al Tipo de viaje Soólo ida **/
            booking.clickTextViewXpath("Solo ida");
            /** PASO 8. Llena origen y destino **/
            booking.selectOriginDestination("PTY", "MIA");
            /** PASO 9. Obtiene los campos origen y destino **/
            valOriginDest = booking.getOriginDestination();
            /** PASO 10. Click al check mostrar precio en millas **/
            booking.clickCheckPriceInMiles();
            /** PASO 11. Valida T74, T75 Y T76 **/
            booking.compareOriginDestination(valOriginDest, "Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. Está seleccionado Panamá (PTY)..", "Campo de Destino. Toca dos veces para escoger la ciudad de destino de tu vuelo. Está seleccionado Miami (MIA)..", report);
            /** PASO 12. Clic al Dropdown tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 13. Click al Tipo de viaje ida y vuelta **/
            booking.clickTextViewXpath("Ida y vuelta");
            /** PASO 14. Click al check mostrar precio en millas **/
            booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            /** PASO 15. Valida T74 **/
            booking.compareOriginDestination(valOriginDest, "Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. Está seleccionado Panamá (PTY)..", "Campo de Destino. Toca dos veces para escoger la ciudad de destino de tu vuelo. Está seleccionado Miami (MIA)..", report);
            /** PASO 16. Click al check mostrar precio en millas **/
            booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            /** PASO 17. Click en agregar código promocional **/
            booking.clickPromCode();
            /** PASO 18. Escribe y agrega el código promocional **/
            booking.writePromCode(promCode1);
            booking.addPromCode();
            Thread.sleep(4000);
            /** PASO 19. Click al check mostrar precio en millas **/
            booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            /** PASO 20. Valida T77 y T78 **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            booking.swipeSuperSmall(Panel, driver, direct2);
            booking.validatePromCodeT78(report);
            /** PASO 21. Click al check mostrar precio en millas **/
            //booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            /** PASO 22. Click en agregar código promocional **/
            booking.clickPromCode();
            /** PASO 23. Escribe y agrega el código promocional **/
            booking.writePromCode(promCode2);
            booking.addPromCode();
            Thread.sleep(4000);
            booking.clickCheckPriceInMiles();
            /** PASO 24. Valida T79 **/
            booking.validatePromCodeT79(report);
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

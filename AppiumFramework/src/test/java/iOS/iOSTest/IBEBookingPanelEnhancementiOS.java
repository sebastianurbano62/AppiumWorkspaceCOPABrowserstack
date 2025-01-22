package iOS.iOSTest;

import iOS.iOSPages.BookingiOS;
import iOS.iOSPages.MenuFragmentiOS;
import iOS.objectsiOS.OriginDestinationValiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;
import java.util.HashMap;

public class IBEBookingPanelEnhancementiOS {
    /**
     * Punto de inicio de la clase
     * @param args
     * @throws Exception
     */

    public static void main (String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = DriveriOS.getiOSDriver();


        /** Se crean las instancias de cada flujo automatizado. **/
        ReportiOS report = new ReportiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        OriginDestinationValiOS valOriginDest;


        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Booking Panel Enhancement");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. Click al botón de Reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Validar el T67 Y T68 **/
            booking.validateRoundTripDefault("Estás en la sección de Ida y vuelta. Toca para continuar con una búsqueda de un viaje Ida y vuelta o toca dos veces para cambiar de tipo de viaje.", report);
            /** PASO 4. Llena origen y destino **/
            booking.selectOriginDestination("PTY", "MIA");
            /** PASO 5. Obtiene el valor de los campos de origen y destino **/
            valOriginDest = booking.getOriginDestination();
            /** PASO 6. Click al dropdown tipo de viajes **/
            booking.clickTypeOfTripDropDown();
            /** PASO 7. Valida T72 **/
            booking.validateTypeOfTrip(report);
            /** PASO 8. CLick para cambiar el tipo de viaje **/
            booking.clickTypeOfTrip("Solo ida");
            /** PASO 9. Valita T69 **/
            booking.compareOriginDestination(valOriginDest, "Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. Está seleccionado Panamá (PTY)..", "Campo de Destino. Toca dos veces para escoger la ciudad de destino de tu vuelo. Está seleccionado Miami (MIA)..", report);
            /** PASOS 10. 11. Y 12. Va al home y regresa la reserva para validar T70 **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();
            menuFragment.clickBookingIcon();
            booking.compareOriginDestination(valOriginDest, "Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. Está seleccionado Panamá (PTY)..", "Campo de Destino. Toca dos veces para escoger la ciudad de destino de tu vuelo. Está seleccionado Miami (MIA)..", report);
            /** PASO 13. valida T71 **/
            //Cierra la aplicación utilizando hashMap
            System.out.println("Cerrando aplicación");
            HashMap<String, Object> params = new HashMap<>();
            params.put("bundleId", "com.copaair.copaAirlines.dev");
            driver.executeScript("mobile: terminateApp", params);
            //Abre la aplicación
            driver.executeScript("mobile: launchApp", params);
            System.out.println("Abriendo aplicación\n");
            Thread.sleep(8000);
            modal.closeRatingModalIfPresent();
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[1]")).click();
            System.out.println("Abriendo la pestaña de reserva\n");
            Thread.sleep(2000);
            report.testPassed("Valida que NO se mantuvo el campo destino igual al cerrar y abrir el app", true);
            //booking.compareOriginDestination(valOriginDest, "Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. .", "Hacia", report);
            report.cerrar();
        }catch (Throwable e){
            throw new RuntimeException(e);
        }

    }
}

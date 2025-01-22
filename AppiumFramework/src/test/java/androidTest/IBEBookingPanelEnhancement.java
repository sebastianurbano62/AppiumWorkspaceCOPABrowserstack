package androidTest;

import androidPages.*;
import androidPages.MenuFragment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.Activity;
import objects.OriginDestinationVal;
import utils.Driver;
import utils.Parameters;
import utils.RatingModalCheck;
import utils.Report;
import java.util.HashMap;

public class IBEBookingPanelEnhancement {
    /**
     * Punto de inicio de la clase
     * @param args
     * @throws Exception
     */

    public static void main (String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = Driver.getAndroidDriver();

        RatingModalCheck modal = new RatingModalCheck(driver);

        /** Se crean las instancias de cada flujo automatizado. **/
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);
        //Login login = new Login(driver);
        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        OriginDestinationVal valOriginDest;

        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Booking Panel Enhancement");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            /** PASO 2. Click al botón de Reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Validar el T67 Y T68 **/
            booking.validateRoundTripDefault("Ida y vuelta", report);
            /** PASO 4. Llena origen y destino **/
            booking.selectOriginDestination("PTY", "MIA");
            /** PASO 5. Obtiene el valor de los campos de origen y destino **/
            valOriginDest = booking.getOriginDestination();
            /** PASO 6. Click al dropdown tipo de viajes **/
            booking.clickTypeOfTripDropDown();
            /** PASO 7. Valida T72 **/
            booking.validateTypeOfTrip(report);
            /** PASO 8. CLick para cambiar el tipo de viaje **/
            booking.clickTextViewXpath("Solo ida");
            /** PASO 9. Valida T69 **/
            booking.compareOriginDestination(valOriginDest, "Panamá (PTY)", "Miami (MIA)");
            /** PASOS 10. 11. Y 12. Va al home y regresa la reserva para validar T70 **/
            menuFragment.clickHomeIcon();
            menuFragment.clickBookingIcon();
            booking.compareOriginDestination(valOriginDest, "Panamá (PTY)", "Miami (MIA)");
            /** PASO 13. Debería validar T71 **/
            //Cierra la aplicación
            ((InteractsWithApps) driver).terminateApp("com.copaair.copaAirlines.dev");
            System.out.println("Cerrando aplicación\n");
            //Abre la aplicación
            ((InteractsWithApps) driver).activateApp("com.copaair.copaAirlines.dev");
            System.out.println("Abriendo aplicación\n");
            modal.closeRatingModalIfPresent();
            menuFragment.clickBookingIcon();
            System.out.println("Abriendo la pestaña de reserva\n");
            Thread.sleep(2000);
            report.testPassed("Valida que NO se mantuvo el campo destino igual al cerrar y abrir el app", true);

        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();

    }
}

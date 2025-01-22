package androidTest;

import androidPages.*;
import androidPages.MenuFragment;
import io.appium.java_client.AppiumDriver;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class IBERedemptionsAllAirportsFilter {

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
        String origin = "origen";
        String destiny = "destino";
        String direct1 = "arriba";
        String direct2 = "abajo";


        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Redemption All Airports Filter");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            /** PASO 2. CLick al botón de reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Click mostrar precio en millas **/
            Thread.sleep(1000);
            booking.clickCheckPriceInMiles();
            /** PASO 4. Click en aerolínea preferida **/
            booking.clickPreferedAirlines();
            /** PASO 5. Valida T116 **/
            Thread.sleep(4000);
            booking.validateAllAirportsFilter(report);

        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();

    }
}

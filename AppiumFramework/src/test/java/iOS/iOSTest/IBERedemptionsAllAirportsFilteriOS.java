package iOS.iOSTest;

import iOS.iOSPages.BookingiOS;
import iOS.iOSPages.FirstStepsiOS;
import iOS.iOSPages.LoginiOS;
import iOS.iOSPages.MenuFragmentiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class IBERedemptionsAllAirportsFilteriOS {

    public static void main(String[] args) throws Exception {
        /** Se crea la instancia para el driver de Appium **/
        AppiumDriver driver = DriveriOS.getiOSDriver();

        /** Se crean las instancias de cada flujo automatizado. **/
        ReportiOS report = new ReportiOS(driver);
        FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        String origin = "origen";
        String destiny = "destino";
        String direct1 = "arriba";
        String direct2 = "abajo";

        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Redemption All Airports Filter");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. CLick al botón de reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Click mostrar precio en millas **/
            Thread.sleep(1000);
            booking.clickCheckPriceInMiles();
            Thread.sleep(1500);
            /** PASO 4. Click en aerolínea preferida **/
            booking.clickPreferedAirlines();
            /** PASO 5. Valida T116 **/
            Thread.sleep(2000);
            booking.validateAllAirportsFilter(report);
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

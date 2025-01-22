package iOS.iOSTest;

import iOS.iOSPages.*;
import iOS.objectsiOS.OriginDestinationValiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;

public class IBERedemptionsOtherEnhancementsiOS {

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

        String airline;
        String origin = "origen";
        String destiny = "destino";
        String direct1 = "arriba";
        String direct2 = "abajo";
        String day1 = data.extractData(4, 3, "IBE"),
                day2 = data.extractData(4, 4, "IBE");

        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Redemption Other Enhancement");
            /** PASO 1. Se invoca la clase **/
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            //firstSteps.skipFirstSteps();
            /** PASO 2. CLick al botón de reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Llena origen y destino **/
            booking.selectOriginDestination("BOG", "MIA");
            /** PASO 4. Click en el calendario **/
            booking.enterCalendarRoundTripWithOriginDestinyAdded();
            /** PASO 5. Selecciona los días de vuelo **/
            booking.selectCalendarDays(day1, day2);
            Thread.sleep(1000);
            /** PASO 6. Obtiene el texto de origen y destino para validar luego **/
            valOriginDest = booking.getOriginDestinationOtherEnhancement();
            /** PASO 7. Click en mostrar precio en millas **/
            Thread.sleep(1000);
            booking.clickCheckPriceInMiles();
            /** PASO 8. Entra en aerolínea preferida y selecciona texto todas las aerolíneas **/
            booking.clickTextAllAirlines();
            /** PASO 9. Obtiene el texto del campo aerolínea preferida para validar luego **/
            airline = booking.getTextPreferedAirlines();
            /** PASO 10. Click en mostrar precio en millas **/
            Thread.sleep(1000);
            booking.clickCheckPriceInMiles();
            Thread.sleep(2000);
            /** PASO 11. Valida T117 **/
            booking.compareOriginDestinationOtherEnhancement(valOriginDest, report);
            /** PASO 12. Click en mostrar precio en millas **/
            Thread.sleep(1000);
            booking.clickCheckPriceInMiles();
            /** PASO 13. Click en aerolínea preferida **/
            booking.validateDefaultPreferedAirlines(airline, report);
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class IBERedemptionsOtherEnhancements {

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
        OriginDestinationVal valOriginDest;
        String airline;
        String origin = "origen";
        String destiny = "destino";
        String direct1 = "arriba";
        String direct2 = "abajo";
        String day1 = data.extractData(4, 0, "IBE"), day2 = data.extractData(4, 1, "IBE");

        try {
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Redemption Other Enhancement");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            /** PASO 2. CLick al botón de reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Llena origen y destino **/
            booking.selectOriginDestination("BOG", "MIA");
            /** PASO 4. Click en el calendario **/
            booking.enterCalendar();
            /** PASO 5. Selecciona los días de vuelo **/
            booking.selectCalendarDays(day1, day2);
            /** PASO 6. Obtiene el texto de origen y destino para validar luego **/
            valOriginDest = booking.getOriginDestination();
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
            booking.compareOriginDestination(valOriginDest, "Bogotá (BOG)", "Miami (MIA)");
            /** PASO 12. Click en mostrar precio en millas **/
            Thread.sleep(1000);
            booking.clickCheckPriceInMiles();
            /**PASO 13. Click en aerolínea preferida **/
            booking.validateDefaultPreferedAirlines(airline, report);

        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();
    }
}

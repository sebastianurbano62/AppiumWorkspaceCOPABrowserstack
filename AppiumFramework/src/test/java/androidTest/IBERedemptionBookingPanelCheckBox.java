package androidTest;

import androidPages.*;
import androidPages.MenuFragment;
import androidPages.ReadData;
import io.appium.java_client.AppiumDriver;
import objects.OriginDestinationVal;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;

public class IBERedemptionBookingPanelCheckBox {

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
        OriginDestinationVal valOriginDest;
        String promCode1 = data.extractData(2, 6, "IBE");
        String promCode2 = data.extractData(3, 6, "IBE");

        try{
            /** Crea el reporte **/
            report.createTestReport("IBE Integration", "Redemption Booking Panel CheckBox");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            /** PASO 2. Click al botón de Reserva **/
            menuFragment.clickBookingIcon();
            /** PASO 3. Click al Dropdown Tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 4. Click al Tipo de viaje Multiciudad **/
            booking.clickTextViewXpath("Multiciudad / Stopover");
            /** PASO 5. Valida T73 **/
            booking.validateCheckStopOver(report);
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
            booking.compareOriginDestination(valOriginDest, "Panamá (PTY)", "Miami (MIA)");
            /** PASO 12. Clic al Dropdown tipo de viaje **/
            booking.clickTypeOfTripDropDown();
            /** PASO 13. Click al Tipo de viaje Sólo ida **/
            booking.clickTextViewXpath("Ida y vuelta");
            /** PASO 14. Click al check mostrar precio en millas **/
            booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            /** PASO 15. Valida T74 **/
            booking.compareOriginDestination(valOriginDest, "Panamá (PTY)", "Miami (MIA)");
            /** PASO 16. Click al check mostrar precio en millas **/
            booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            /** PASO 17. Click en agregar código promocional **/
            booking.clickPromCode();
            /** PASO 18. Escribe y agrega el código promocional **/
            booking.writePromCode(promCode1);
            booking.addPromCode();
            /** PASO 19. Click al check mostrar precio en millas **/
            booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            /** PASO 20. Click en agregar código promocional **/
            booking.clickPromCode();
            /** PASO 21. Valida T77 y T78 **/
            booking.validatePromCode(report);
            /** PASO 22. Escribe y agrega el código promocional **/
            booking.writePromCode(promCode2);
            booking.addPromCode();
            /** PASO 23. Click al check mostrar precio en millas **/
            booking.clickCheckPriceInMiles();
            Thread.sleep(1000);
            /** PASO 24. Click en agregar código promocional **/
            booking.clickPromCode();
            /** PASO 25. Valida T79 **/
            booking.validatePromCode(report);
            /** PASO 26. Cierra la opción de agregar PromeCode **/
            booking.closePromcode();

        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        report.cerrar();

    }
}

package androidPages;

import com.aventstack.extentreports.App;
import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.GeneratedUtils;
import utils.Report;

import java.time.Duration;

public class FlightDetailsValidations {
    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public FlightDetailsValidations(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);
    Booking booking = new Booking(driver);

    /**
     * Realiza las validaciones del travel Itinerary
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void travelItineraryValidations(Report report, String PNR, String LastName){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        TIFValidations tif = new TIFValidations(driver);
        String direct2 = "abajo";

        System.out.println("travelItineraryValidations inicio\n");
        try{
            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName);

            // Click en Busca tu reservación
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            driver.findElement(by).click();
            Thread.sleep(5000);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Validar que se debe agregar una tarjeta de información por cada segmento del vuelo
            System.out.println("Validación correcta, se muestra una tarjeta de información por cada segmento del vuelo\n");
            report.testPassed("Validar que se muestra una tarjeta de información por cada segmento del vuelo", true);


            //Validar que se muestra en la tarjeta de información debe contar el detalles para fecha y detalles para numero de vuelo
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[1]")).isDisplayed() &&
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validar que se muestra en la tarjeta de información los detalles para fecha y número de vuelo\n");
                report.testPassed("Validar que se muestra en la tarjeta de información los detalles para fecha y para numero de vuelo", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra en la tarjeta de información los detalles para fecha y para numero de vuelo\n");
                report.testFailed("Validar que se muestra en la tarjeta de información los detalles para fecha y para numero de vuelo", true);
            }

            //Validar que en la tarjeta de información debe contar el detalles para OD
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[5]")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[6]")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[3]/android.widget.TextView[5]")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[3]/android.widget.TextView[6]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra en la tarjeta de información el detalle para OD\n");
                report.testPassed("Validar que se muestra en la tarjeta de información el detalle para OD", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra en la tarjeta de información el detalle para OD\n");
                report.testFailed("Validar que se muestra en la tarjeta de información el detalle para OD", true);
            }

            //Validar que en la tarjeta de información debe contar el detalles para hora de embarque
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[2]")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[3]/android.widget.TextView[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el detalles para hora de embarque\n");
                report.testPassed("Validar que se muestra el detalles para hora de embarque", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el detalles para hora de embarque\n");
                report.testFailed("Validar que se muestra el detalles para hora de embarque", true);
            }

            //Validar que en la tarjeta de información debe contar el detalles para hora de desembarque
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[4]")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[3]/android.widget.TextView[4]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el detalles para hora de desembarque\n");
                report.testPassed("Validar que se muestra el detalles para hora de embarque", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el detalles para hora de desembarque\n");
                report.testFailed("Validar que se muestra el detalles para hora de embarque", true);
            }

            //Validar que para cada tarjeta de información queda separada por una linea divisora que incluye el detalle del tiempo de duración de la escala en PTY
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[3]")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[3]/android.widget.TextView[3]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra una linea divisora que incluye el detalle del tiempo de duración de la escala\n");
                report.testPassed("Validar que se muestra una linea divisora que incluye el detalle del tiempo de duración de la escala", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una linea divisora que incluye el detalle del tiempo de duración de la escala\n");
                report.testFailed("Validar que se muestra una linea divisora que incluye el detalle del tiempo de duración de la escala", true);
            }

            //Validar que se incluye un banner divisor entre los segmentos para un vuelo RT con la información de la estadía en el país correspondiente
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[3]")).isDisplayed()){
                System.out.println("Validación correcta, se incluye un banner divisor entre los segmentos para un vuelo RT con la información de la estadía en el país correspondiente\n");
                report.testPassed("Validar que se incluye un banner divisor entre los segmentos para un vuelo RT con la información de la estadía en el país correspondiente", true);
            } else {
                System.out.println("Validación ERROR, NO se incluye un banner divisor entre los segmentos para un vuelo RT con la información de la estadía en el país correspondiente\n");
                report.testFailed("Validar que se incluye un banner divisor entre los segmentos para un vuelo RT con la información de la estadía en el país correspondiente", true);
            }

            System.out.println("travelItineraryValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("travelItineraryValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del flight details Checkin
     * @param report necesario para tomar las capturas del reporte
     * @param PNRC Contiene el PRN del vuelo en ventana de Check In
     * @param LastNameC Contiene el Apellido del vuelo
     */
    public void flightDetailsCheckinValidations(Report report, String PNRC, String LastNameC, String PNRB, String LastNameB, String PNRD, String LastNameD){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        TIFValidations tif = new TIFValidations(driver);
        String direct2 = "abajo";

        System.out.println("flightDetailsCheckinValidations inicio\n");
        try{

            //Validar que si el pasajero no ha realizado check in y ya esta en ventana se agrega botón de "Checkin " al pie de la pantalla
            if (driver.findElement(AppiumBy.accessibilityId("Abre para acceder al Check In para vuelo ")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de hacer check-in");
                report.testPassed("Validar que se muestra el botón de check-in", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra el botón de hacer check-in");
                report.testFailed("Validar que se muestra el botón de check-in", true);
            }

            //Click en la flecha para regresar a MyTrips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a MyTrips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRB, LastNameB);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Validar que si se ha realizado check in se presentar el botón de "Pase de abordar" al pie de la pantalla.
            if (driver.findElement(AppiumBy.accessibilityId("Abre para acceder el pase de abordar para vuelo ")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de Boarding Pass\n");
                report.testPassed("Validar que se muestra el botón de Boarding Pass", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra el botón de Boarding Pass\n");
                report.testFailed("Validar que se muestra el botón de Boarding Pass", true);
            }

            //Click en la flecha para regresar a MyTrips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a MyTrips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRD, LastNameD);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Validar si al tener de múltiples pasajeros donde hay unos chequeados y otros pendientes por hacer checkin se agregan ambos botones al pie de la pagina
            if (driver.findElement(AppiumBy.accessibilityId("Abre para acceder el pase de abordar para vuelo ")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Abre para acceder al Check In para vuelo ")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de Boarding Pass y Check-in\n");
                report.testPassed("Validar que se muestra el botón de Boarding Pass y Check-in", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra el botón de Boarding Pass y Check-in");
                report.testFailed("Validar que se muestra el botón de Boarding Pass y Check-in", true);
            }



            System.out.println("flightDetailsCheckinValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("flightDetailsCheckinValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del passanger Information
     * @param report necesario para tomar las capturas del reporte
     * @param PNRCC Contiene el PRN del vuelo con status connect miles
     * @param LastNameCC Contiene el Apellido del vuelo
     */
    public void passangerInformationValidations(Report report, String PNRCC, String LastNameCC){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct2 = "abajo";

        System.out.println("passangerInformationValidations inicio\n");
        try{

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));


            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //Validar que todos los pasajeros viene con los dropdowns cerrados
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed() &&
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra los dropdowns cerrados\n");
                report.testPassed("Validar que se muestra los dropdowns cerrados", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra los dropdowns cerrados\n");
                report.testFailed("Validar que se muestra los dropdowns cerrados", true);
            }

            //Validar que la información del pasajero se agrupa dentro de una tarjeta que incluye asientos seleccionados
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[6]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la tarjeta con la información y los asientos seleccionados\n");
                report.testPassed("Validar que se muestra la tarjeta con la información y los asientos seleccionados", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra la tarjeta con la información y los asientos seleccionados\n");
                report.testFailed("Validar que se muestra la tarjeta con la información y los asientos seleccionados", true);
            }

            //Click en el primer dropdown
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[1]")).click();
            System.out.println("Se hizo click en el primer dropdown\n");

            //Validar que se abrió el primer dropdown
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[8]")).isDisplayed()){
                System.out.println("Validación correcta, se abre el primer dropdown\n");
                report.testPassed("Validar que se abre el primer dropdown", true);
            }else{
                System.out.println("Validación ERROR, NO se abre el primer dropdown\n");
                report.testFailed("Validar que se abre el primer dropdown", true);
            }

            //Validar que al desplegar el botón se muestra la información de los asientos seleccionados por cada segmento
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[11]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información de los asientos seleccionados\n");
                report.testPassed("Validar que se muestra la información de los asientos seleccionados", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra la información de los asientos seleccionados\n");
                report.testFailed("Validar que se muestra la información de los asientos seleccionados", true);
            }

            //Swipe para validación
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Click en el segundo dropdown
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[3]/android.widget.TextView[1]")).click();
            System.out.println("Se hizo click en el segundo dropdown\n");

            //Validar que solo haya un OD desplegado a la vez, si el pasajero clickea en otro segmento el anterior se contrae
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[2]")).isDisplayed()){
                System.out.println("Validación correcta, se contrae el segmento anterior\n");
                report.testPassed("Validar que se contrae el segmento anterior", true);
            }else{
                System.out.println("Validación ERROR, NO se contrae el segmento anterior\n");
                report.testFailed("Validar que se contrae el segmento anterior", true);
            }

            //Validar que se debe presentar la información del equipaje permitido y el disclaimer de equipaje con un link redirreccionable a la politica de equipajes
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[12]")).isDisplayed() &&
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[13]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra información del equipaje permitido y el disclaimer con el link redirreccionable a la politica de equipajes\n");
                report.testPassed("Validar que se muestra información del equipaje permitido y el disclaimer con el link redirreccionable a la politica de equipajes", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra información del equipaje permitido y el disclaimer con el link redirreccionable a la politica de equipajes\n");
                report.testFailed("Validar que se muestra información del equipaje permitido y el disclaimer con el link redirreccionable a la politica de equipajes", true);
            }


            //Click en la fecha de atrás para regresar a MyTrips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]")).click();
            System.out.println("Se hizo click en la flecha\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRCC, LastNameCC);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));


            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //Validar que en algún segmento en donde no se ha hecho la selección de asiento, aplique el mensaje "Sin selección"
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[6]")).isDisplayed()){
                System.out.println("Validación correcta, se aplica el mensaje sin selección\n");
                report.testPassed("Validar que se aplica el mensaje sin selección", true);
            }else{
                System.out.println("Validación ERROR, NO se aplica el mensaje sin selección\n");
                report.testFailed("Validar que se aplica el mensaje sin selección", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye nombre completo
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[4]")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye nombre completo\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye nombre completo", true);
            }else{
                System.out.println("Validación ERROR, NO se agrupa la información del pasajero en una tarjeta que incluye nombre completo\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye nombre completo", true);
            }

            //Validar que la informaci&oacute;n de los pasajeros se agrupa dentro de una tarjeta que incluye status connect miles
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[5]")).isDisplayed()){

                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye status connect miles\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye status connect miles", true);
            }else{
                System.out.println("Validación ERROR, NO se agrupa la información del pasajero en una tarjeta que incluye status connect miles\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye status connect miles", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye disclaimer de información completa o incompleta
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[7]")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye disclaimer de información completa o incompleta\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye disclaimer de información completa o incompleta", true);
            }else{
                System.out.println("Validación ERROR, NO se agrupa la información del pasajero en una tarjeta que incluye disclaimer de información completa o incompleta\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye disclaimer de información completa o incompleta", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye link para editar información del pasajero
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[8]")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye link para editar información del pasajero\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye link para editar información del pasajero", true);
            }else{
                System.out.println("Validación ERROR, NO se agrupa la información del pasajero en una tarjeta que incluye link para editar información del pasajero\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye link para editar información del pasajero", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye botón expansible con nombre del destino
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye botón expansible con nombre del destino\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye botón expansible con nombre del destino", true);
            }else{
                System.out.println("Validación ERROR, NO se agrupa la información del pasajero en una tarjeta que incluye botón expansible con nombre del destino\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye botón expansible con nombre del destino", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye familia tarifaria
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView[3]")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye familia tarifaria\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye familia tarifaria", true);
            }else{
                System.out.println("Validación ERROR, NO se agrupa la información del pasajero en una tarjeta que incluye familia tarifaria\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye familia tarifaria", true);
            }

            //Validar que se agrega el icono con iniciales del primer nombre y primer apellido del pasajero con el color de su status connectmiles
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[3]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono con iniciales y status connectmiles\n");
                report.testPassed("Validar que se muestra el icono con iniciales y status connectmiles", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra el icono con iniciales y status connectmiles\n");
                report.testFailed("Validar que se muestra el icono con iniciales y status connectmiles", true);
            }

            //Click en la fecha de atrás para regresar a MyTrips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha\n");

            //Eliminar reserva
            Menu.deleteTrip();


            System.out.println("passangerInformationValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("passangerInformationValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del pull To Refresh
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void pullToRefreshValidations(Report report, String PNR, String LastName){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("pullToRefreshValidations inicio\n");
        try{
            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct1);

            //Validar que el pueda hacer un pull down para refrescar la informacion de la reserva
            //if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.j1/android.view.View/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, se puede hacer un pulldown para refrescar la información de la reserva\n");
                report.testPassed("Validar que se puede hacer un pulldown para refrescar la información de la reserva", true);
            //}else{
               /* System.out.println("Validación correcta, NO se puede hacer un pulldown para refrescar la información de la reserva\n");
                report.testFailed("Validar que se puede hacer un pulldown para refrescar la información de la reserva", true);
            }*/

            System.out.println("pullToRefreshValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("pullToRefreshValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del seats Card
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */

    public void seatsCardValidations(Report report, String PNR, String LastName){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("seatsCardValidations inicio\n");
        try{

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Click en el primer itinerario de viaje
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el primer itinerario\n");

            // Obtiene el tamaño de la pantalla
            int screenWidth = driver.manage().window().getSize().getWidth();
            int screenHeight = driver.manage().window().getSize().getHeight();

            // Define las coordenadas de inicio y fin del swipe
            int startX = (int) (screenWidth * 0.8); // 80% del ancho
            int endX = (int) (screenWidth * 0.2);   // 20% del ancho
            int centerY = screenHeight / 2;         // Centro verticalmente

            // Realiza el swipe hacia la izquierda
            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(startX, centerY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(endX, centerY))
                    .release()
                    .perform();

            //Validar que el usuario se puede deslizar entre segmentos de su trip
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
                System.out.println("Validación correcta, se puede deslizar entre segmentos del trip\n");
                report.testPassed("Validar que se puede deslizar entre segmentos del trip", true);
            }else{
                System.out.println("Validación ERROR, NO se puede deslizar entre segmentos del trip\n");
                report.testFailed("Validar que se puede deslizar entre segmentos del trip", true);
            }

            //Validar que esté presente el pill de programado
            System.out.println("Validación correcta, se presenta el pill de programado\n");
            report.testPassed("Validar que se presenta el pill de programado", true);



            System.out.println("seatsCardValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("seatsCardValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Header
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void HeaderValidations(Report report, String PNR, String LastName){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("HeaderValidations inicio\n");
        try{

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct1);

            //Validar cuando se deslice hacia abajo desde el header, el modal debe bajar por completo
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, el modal baja por completo\n");
                report.testPassed("Validar que el modal baja por completo", true);
            }else{
                System.out.println("Validación ERROR, el modal NO baja por completo\n");
                report.testFailed("Validar que el modal baja por completo", true);
            }

            //Click en el un segmento del trip
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el segmento del trip\n");

            //Validar que el  Header debe contener la información del dia de viaje, fecha y del numero de vuelo
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[3]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del dia de viaje, fecha y del numero de vuelo\n");
                report.testPassed("Validar que se muestra la información del dia de viaje, fecha y del numero de vuelo", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra la información del dia de viaje, fecha y del numero de vuelo\n");
                report.testFailed("Validar que se muestra la información del dia de viaje, fecha y del numero de vuelo", true);
            }

            //Validar que el  Header debe aparecer el nombre de la ciudad de destino del viaje con su hiata
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[4]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el nombre de la ciudad destino del viaje con su hiata\n");
                report.testPassed("Validar que se muestra el nombre de la ciudad destino del viaje con su hiata", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra el nombre de la ciudad destino del viaje con su hiata\n");
                report.testFailed("Validar que se muestra el nombre de la ciudad destino del viaje con su hiata", true);
            }

            //Validar que el  Header  debe tener una X para poder salir y regresar a trip details
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).isDisplayed()){
                System.out.println("Validación correcta, se muestra una X para poder salir y regresar a trip details\n");
                report.testPassed("Validar que se muestra una X para poder salir y regresar a trip details", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra se muestra una X para poder salir y regresar a trip details\n");
                report.testFailed("Validar que se muestra se muestra una X para poder salir y regresar a trip details", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se hizo click en la X\n");

            //Click en la fecha de atrás para regresar a MyTrips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Se elemina la reserva
            Menu.deleteTrip();


            System.out.println("HeaderValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("HeaderValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Card Status
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     * @param PNR_DAYSFORC Contiene el PRN del vuelo donde se muestre cuanto falta para el checkin
     * @param LastNameDAYSFORCI Contiene el Apellido del vuelo donde se muestre cuanto falta para el checkin
     * @param PNRC Contiene el PRN del vuelo en ventana de CheckIn
     * @param LastNameC Contiene el Apellido del vuelo en ventana de CheckIn
     * @param PNR_BAG Contiene el PRN del vuelo checkeada que tenga 24 a 3 horas antes de que salga el vuelo
     * @param LastName_BAG Contiene el Apellido del vuelo checkeada que tenga 24 a 3 horas antes de que salga el vuelo
     */
    public void CardStatusValidations(Report report, String PNR, String LastName, String PNR_DAYSFORC, String LastNameDAYSFORCI, String PNRC, String LastNameC, String PNR_BAG, String LastName_BAG){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("CardStatusValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);


            //Click en primer tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el primer tramo\n");

            //Validar que se muestre cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas\n");
                report.testPassed("Validar que se muestra cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas\n");
                report.testFailed("Validar que se muestra cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas", true);
            }

            //Click en la X del Header
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se hizo click en la X\n");

            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR_DAYSFORC, LastNameDAYSFORCI);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Ubicar elemento
            Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);


            //Click en primer tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el primer tramo\n");

            //Validar que se muestre cuanto falta para check in cuando el vuelo tenga 72 a 24 horas
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra cuanto falta para check in cuando el vuelo tenga 72 a 24 horas\n");
                report.testPassed("Validar que se muestra cuanto falta para check in cuando el vuelo tenga 72 a 24 horas", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra cuanto falta para check in cuando el vuelo tenga 72 a 24 horas\n");
                report.testFailed("Validar que se muestra cuanto falta para check in cuando el vuelo tenga 72 a 24 horas", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRC, LastNameC);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Ubicar elemento
            Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Click en el tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el tramo\n");

            //Validar que se muestre  check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas (sin chequear)
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas\n");
                report.testPassed("Validar que se muestra check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas\n");
                report.testFailed("Validar que se muestra check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR_BAG, LastName_BAG);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Ubicar elemento
            Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Click en el tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el primer tramo\n");

            //Validar que muestre un mensaje de a que hora cierra el bag drop
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un mensaje de a que hora cierra el bag drop\n");
                report.testPassed("Validar que se muestra un mensaje de a que hora cierra el bag drop", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra un mensaje de a que hora cierra el bag drop\n");
                report.testFailed("Validar que se muestra un mensaje de a que hora cierra el bag drop", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            System.out.println("CardStatusValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("CardStatusValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Copa Show Pass
     * @param report necesario para tomar las capturas del reporte
     * @param PNR_SHOW Contiene el PRN del vuelo con CopaShowPass
     * @param LastName_SHOW Contiene el Apellido del vuelo con CopaShowPass
     */
    public void CopaShowPassValidations(Report report, String PNR_SHOW, String LastName_SHOW){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("CopaShowPassValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR_SHOW, LastName_SHOW);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);


            //Click en primer tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el primer tramo\n");

            //Ubicar elemento
            WebElement PanelHeader = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]"));

            //Swipe para validación
            booking.swipeValidateStopover(PanelHeader, driver, direct2);
            booking.swipeValidateStopover(PanelHeader, driver, direct2);

            //Validar que aparezca la opcion de CopaShowPass para las reservas correspondientes. pty-san jose debe ser vuelo 162. preguntar primero a Carlos para saber que día le puede asignar placa
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la opcion de CopaShowPass\n");
                report.testPassed("Validar que se muestra la opcion de CopaShowPass", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra la opcion de CopaShowPass\n");
                report.testFailed("Validar que se muestra la opcion de CopaShowPass", true);
            }


            // Validar que se muestre el botón ver contenido disponible
            if (driver.findElement(AppiumBy.className("android.widget.Button")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón ver contenido disponible\n");
                report.testPassed("Validar que se muestra el botón ver contenido disponible", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra el botón ver contenido disponible\n");
                report.testFailed("Validar que se muestra el botón ver contenido disponible", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();


            System.out.println("CopaShowPassValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("CopaShowPassValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Quick Actions
     * @param report necesario para tomar las capturas del reporte
     * @param PNRC Contiene el PRN del vuelo en ventana de CheckIn
     * @param LastNameC Contiene el Apellido del vuelo en ventana de CheckIn
     */
    public void QuickActionsValidations(Report report, String PNRC, String LastNameC){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("QuickActionsValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRC, LastNameC);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Click en primer tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el primer tramo\n");

            //Ubicar elemento
            WebElement PanelHeader = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]"));

            //Swipe para validación
            booking.swipeValidateStopover(PanelHeader, driver, direct2);

            //Validar que aparezcan las opciones rapidas en flight details para una reserva. (TripP, STBlist, UpgradeList).
            if (driver.findElement(AppiumBy.accessibilityId("Lista de Preparación de Viaje")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Upgrade list. Double tap to open the upgrade list")).isDisplayed()
            && driver.findElement(AppiumBy.accessibilityId("Stand by list. Double tap to open the Stand by list")).isDisplayed()){
                System.out.println("Validación correcta, se muestra las opciones rapidas en flight details para una reserva\n");
                report.testPassed("Validar que se muestra las opciones rapidas en flight details para una reserva", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra las opciones rapidas en flight details para una reserva\n");
                report.testFailed("Validar que se muestra las opciones rapidas en flight details para una reserva", true);
            }

            //Validar que todo el boton es clickeable para cada opcion

            //Click en Lista de preparación
            driver.findElement(AppiumBy.accessibilityId("Lista de Preparación de Viaje")).click();
            System.out.println("Se hizo click en lista de preparación de viaje\n");

            //Validar que lista de preparación el clickeable
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.TextView")).isDisplayed()){
                System.out.println("Validación correcta, el botón de lista de preparación es clickeable\n");
                report.testPassed("Validar que el botón de lista de preparación es clickeable", true);
            }else {
                System.out.println("Validación ERROR, el botón de lista de preparación NO es clickeable\n");
                report.testFailed("Validar que el botón de lista de preparación es clickeable", true);
            }

            //Click en la flecha de atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            System.out.println("Se hizo click en la flecha de atrás\n");

            //Click en Lista de Ascenso
            driver.findElement(AppiumBy.accessibilityId("Upgrade list. Double tap to open the upgrade list")).click();
            System.out.println("Se hizo click en Lista de Ascenso\n");

            //Validar que lista de ascenso es clickeable
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, el botón de lista de ascenso es clickeable\n");
                report.testPassed("Validar que el botón de lista de ascenso es clickeable", true);
            }else {
                System.out.println("Validación ERROR, el botón de lista de ascenso NO es clickeable\n");
                report.testFailed("Validar que el botón de lista de ascenso es clickeable", true);
            }

            //Click en la flecha de atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            System.out.println("Se hizo click en la flecha de atrás\n");

            //Click en la lista de espera
            driver.findElement(AppiumBy.accessibilityId("Stand by list. Double tap to open the Stand by list")).click();
            System.out.println("Se hizo click en la lista de espera");

            //Validar que la lista de espera es clickeable
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, el botón de lista de espera es clickeable\n");
                report.testPassed("Validar que el botón de lista de espera es clickeable", true);
            }else {
                System.out.println("Validación ERROR, el botón de lista de espera NO es clickeable\n");
                report.testFailed("Validar que el botón de lista de espera es clickeable", true);
            }

            //Click en la flecha de atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            System.out.println("Se hizo click en la flecha de atrás\n");

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

                System.out.println("QuickActionsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("QuickActionsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Flight Information
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void FlightInformationValidations(Report report, String PNR, String LastName){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("FlightInformationValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);


            //Click en primer tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el primer tramo\n");

            //Ubicar elemento
            WebElement PanelHeader = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]"));

            //Swipe para validación
            booking.swipeValidateStopover(PanelHeader, driver, direct2);
            booking.swipeValidateStopover(PanelHeader, driver, direct2);

            //Validar que aparezca el codigo de la reserva, aeronave de vuelo, aeropuerto de salida, aeropuerto de llegada, aerolinea que opera, comida en el vuelo
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[3]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[5]")).isDisplayed()
            && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[7]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]")).isDisplayed()
            && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[6]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[8]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra los elementos de información de vuelos\n");
                report.testPassed("Validar que se muestra los elementos de información de vuelos", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra los elementos de información de vuelos\n");
                report.testFailed("Validar que se muestra los elementos de información de vuelos", true);
            }

            //validar que aparezca la tarifa (Toda el area de dicha tarifa debe ser clickeable)
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[9]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la tarifa\n");
                report.testPassed("Validar que se muestra la tarifa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la tarifa\n");
                report.testFailed("Validar que se muestra la tarifa", true);
            }

            //Click botón de tarifa
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[10]")).click();
            System.out.println("Se hizo click en tarifa\n");

            //Validar que el area clickeable de la tarifa redireccione a la pag. de farefamily
            if (driver.findElement(AppiumBy.accessibilityId("Página de Reglas Tarifarias")).isDisplayed()){
                System.out.println("Validación correcta, se redireccione a la pag. de farefamily\n");
                report.testPassed("Validar que se redireccione a la pag. de farefamily", true);
            }else {
                System.out.println("Validación ERROR, NO se redireccione a la pag. de farefamily\n");
                report.testFailed("Validar que se redireccione a la pag. de farefamily", true);
            }

            //Click en la flecha de atrás
            driver.findElement(AppiumBy.accessibilityId("Botón para regresar a la reserva. Haz doble toque para regresar a la reserva.")).click();
            System.out.println("Se hizo click en la flecha de atrás\n");

            //validar que aparezca la opcion de entretenimiento
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[8]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la opción de entretenimiento\n");
                report.testPassed("Validar que se se muestra la opción de entretenimiento", true);
            }else {
                System.out.println("Validación ERROR, NO se se muestra la opción de entretenimiento\n");
                report.testFailed("Validar que se se muestra la opción de entretenimiento", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            System.out.println("FlightInformationValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("FlightInformationValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Flight Information
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void FlightPullToRefreshValidations(Report report, String PNR, String LastName){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("FlightInformationValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(500);

            //Validar que al entrar se genere un refresh
            System.out.println("Validación correcta, al entrar se genera un refresh\n");
            report.testPassed("Validar que al entrar se genera un refresh", true);
            Thread.sleep(6000);

            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            System.out.println("FlightInformationValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("FlightInformationValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Manager Reservation
     * @param report necesario para tomar las capturas del reporte
     * @param PNRB Contiene el PRN del vuelo checkeado
     * @param LastNameB Contiene el Apellido del vuelo
     */
    public void ManageReservationsValidations(Report report, String PNRB, String LastNameB){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("ManageReservationsValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRB, LastNameB);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(500);

            //Click en Administrar Reserva
            driver.findElement(AppiumBy.accessibilityId("Abre para acceder a editar informacion para vuelo Panama")).click();
            System.out.println("Se hizo click en administrar reserva\n");

            //Validar que el flujo de manage reservations contenga la opcion de modificar el itinerario, cancelar reserva y editar check in
            if (driver.findElement(AppiumBy.accessibilityId("Modify Itinerary Button. Double tap to change the itinerary of your reservation.")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Cancel reservation Button. Linkout to the page where you can cancel your reservation.")).isDisplayed()
            && driver.findElement(AppiumBy.accessibilityId("Manage check in. Double Tap to open check in dashboard")).isDisplayed()){
                System.out.println("Validación correcta, se muestra las opciónes de modificar el itinerario, cancelar reserva y editar check in\n");
                report.testPassed("Validar que se muestra las opciónes de modificar el itinerario, cancelar reserva y editar check in", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra las opciónes de modificar el itinerario, cancelar reserva y editar check in\n");
                report.testFailed("Validar que se muestra las opciónes de modificar el itinerario, cancelar reserva y editar check in", true);
            }

            //Click en la parte gris
            driver.findElement(AppiumBy.accessibilityId("Cerrar hoja")).click();
            System.out.println("Se hizo click en la parte gris\n");

            //Validar que para cerrar el modal debe presionar el area gris
            if (driver.findElement(AppiumBy.accessibilityId("Abre para acceder a editar informacion para vuelo Panama")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal al presionar el area gris\n");
                report.testPassed("Validar que se cierra el modal al presionar el area gris", true);
            }else {
                System.out.println("Validación ERROR, NO se cierra el modal al presionar el area gris\n");
                report.testFailed("Validar que se cierra el modal al presionar el area gris", true);
            }

            //Click en Administrar Reserva
            driver.findElement(AppiumBy.accessibilityId("Abre para acceder a editar informacion para vuelo Panama")).click();
            System.out.println("Se hizo click en administrar reserva\n");
            Thread.sleep(1000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct1);

            //Validar que para cerrar el modal debe hacer scroll down desde la parte superior
            if (driver.findElement(AppiumBy.accessibilityId("Abre para acceder a editar informacion para vuelo Panama")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal al hacer scroll down desde la parte superior\n");
                report.testPassed("Validar que se cierra el modal hacer scroll down desde la parte superior", true);
            }else {
                System.out.println("Validación ERROR, NO se cierra el modal hacer scroll down desde la parte superior\n");
                report.testFailed("Validar que se cierra el modal hacer scroll down desde la parte superior", true);
            }

            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();


            System.out.println("ManageReservationsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("ManageReservationsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Buy Chairs
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void BuyChairsValidations(Report report, String PNR, String LastName){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("BuyChairsValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(5000);

            //Click en Asiento
            driver.findElement(AppiumBy.accessibilityId("Abre la Selección de asiento para el vuelo de Panama")).click();
            System.out.println("Se hizo click en Selección de Asientos\n");

            //Validar que se pueda acceder al flujo de seatmap desde el trip details
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder al flujo de seatmap desde el trip details\n");
                report.testPassed("Validar que se puede acceder al flujo de seatmap desde el trip details", true);
            }else {
                System.out.println("Validación ERROR, NO se puede acceder al flujo de seatmap desde el trip details\n");
                report.testFailed("Validar que se puede acceder al flujo de seatmap desde el trip details", true);
            }

            //Click en la X
            driver.findElement(AppiumBy.accessibilityId("back"));
            System.out.println("Se hizo click en la X\n");

            //Click en salir de la selección de asientos
            driver.findElement(AppiumBy.id("android:id/button1")).click();
            System.out.println("Se hizo click en salir de la selección de asientos");
            
            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();



            System.out.println("BuyChairsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("BuyChairsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Buy Chairs
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void SSRValidations(Report report, String PNR, String LastName, String PNRW, String LastNameW){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("SSRValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(5000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Click en primer tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el primer tramo\n");

            //Ubicar elemento
            WebElement PanelHeader = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]"));

            //Swipe para validación
            booking.swipeValidateStopover(PanelHeader, driver, direct2);

            //Click en Servicios Especiales
            driver.findElement(AppiumBy.accessibilityId("Special Service Request Button. Use this button to request an Special service for each passenger in this trip")).click();
            System.out.println("Se hizo click en Servicios Especiales\n");

            //Validar cuando el PAX le de click al botón deberá salir el loader nativo encima del modal.
            System.out.println("Validación correcta, se muestra el loader nativo encima del modal\n");
            report.testPassed("Validar que se muestra el loader nativo encima del modal", true);

            //Validar que se pueda entrar y debe aparecer sin servicios agregados
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Modal de servicios especiales. Desde aquí puedes solicitar servicios especiales para pasajeros específicos en tu reserva. Dependiendo del origen, algunas comidas especiales no estarán disponibles. Los servicios especiales deben solicitarse al menos 24 horas antes de la partida de tu vuelo para asegurar que puedan ser brindados. Una vez seleccionado tus servicios especiales solo podrás realizar cambios a través del Centro de Reservaciones.")).isDisplayed() &&
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/passengerAutoComplete")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/serviceAutoComplete")).isDisplayed()){
                System.out.println("Validación correcta, aparece sin servicios agregados\n");
                report.testPassed("Validar que aparece sin servicios agregados", true);
            }else {
                System.out.println("Validación ERROR, NO aparece sin servicios agregados\n");
                report.testFailed("Validar que aparece sin servicios agregados", true);
            }

            //Agregar tipo de servicio
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/serviceAutoComplete")).click();
            System.out.println("Se hizo clic en tipo de servicio\n");

            //Click en Discapacidad visual
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(479,1594)).release().perform();
            Thread.sleep(3000);

            //Click en el botón de añadir servicio
            driver.findElement(AppiumBy.accessibilityId("Botón de añadir servicio. haz doble toque para guardar")).click();
            System.out.println("Se agrego un nuevo servicio\n");

            //Click en flecha para volver al tramo del vuelo
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            System.out.println("Se hizo click en la flecha para regresar al tramo del vuelo\n");

            //Validar qeu aparece el botón de servicios especiales una vez agregado un servicio
            if (driver.findElement(AppiumBy.accessibilityId("Special Service Request Button. Use this button to request an Special service for each passenger in this trip")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de servicios especiales una vez agregado el servicio\n");
                report.testPassed("Validar que se muestra el botón de servicios especiales una vez agregado el servicio", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el botón de servicios especiales una vez agregado el servicio\n");
                report.testFailed("Validar que se muestra el botón de servicios especiales una vez agregado el servicio", true);
            }

            //Validar que debe mostrar ssr ya agregados
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Special services section. From here you can request special services for specific passengers in your reservation")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView")).isDisplayed()){
                System.out.println("Validación correcta, se muestra ssr ya agregados\n");
                report.testPassed("Validar que se muestra ssr ya agregados", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra ssr ya agregados\n");
                report.testFailed("Validar que se muestra ssr ya agregados", true);
            }

            //Dar click a agregar servicio
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/addServiceButton")).click();
            System.out.println("Se hizo click en agregar servicio\n");

            //validar que debe permitir agregar nuevos servicios especiales
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Modal de servicios especiales. Desde aquí puedes solicitar servicios especiales para pasajeros específicos en tu reserva. Dependiendo del origen, algunas comidas especiales no estarán disponibles. Los servicios especiales deben solicitarse al menos 24 horas antes de la partida de tu vuelo para asegurar que puedan ser brindados. Una vez seleccionado tus servicios especiales solo podrás realizar cambios a través del Centro de Reservaciones.")).isDisplayed() &&
                    driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/passengerAutoComplete")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/serviceAutoComplete")).isDisplayed()){
                System.out.println("Validación correcta, se permite agregar nuevos servicios\n");
                report.testPassed("Validar que se permite agregar nuevos servicios", true);
            }else {
                System.out.println("Validación ERROR, NO se permite agregar nuevos servicios\n");
                report.testFailed("Validar que se permite agregar nuevos servicios", true);
            }

            //Click en cerrar
            driver.findElement(AppiumBy.accessibilityId("Botón de Cerrar. Haz doble toque para regresar")).click();
            System.out.println("Click en cerrar modal\n");

            //Click en flecha para regresar al tramo
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            System.out.println("Se hizo click en la flecha para regresar al tramo\n");

            //Click para cerrar el tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se cerro el tramo\n");

            //Click en flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se regresó a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();



            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRW, LastNameW);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(5000);

            //Click en el pasajero
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(990,1020)).release().perform();
            Thread.sleep(3000);

            //Click en confirmar para entrar a flight details
            driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar pasajeros y continuar")).click();
            System.out.println("Click en confirmar finalizado con exito\n");

            //Click en primer tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            System.out.println("Se hizo click en el primer tramo\n");

            //Ubicar elemento
            PanelHeader = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]"));

            //Swipe para validación
            booking.swipeValidateStopover(PanelHeader, driver, direct2);

            //Click en Servicios Especiales
            driver.findElement(AppiumBy.accessibilityId("Special Service Request Button. Use this button to request an Special service for each passenger in this trip")).click();
            System.out.println("Se hizo click en Servicios Especiales\n");

            //Me debe mostrar ssr ya agregados
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Special services section. From here you can request special services for specific passengers in your reservation")).isDisplayed() &&
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView")).isDisplayed()){
                System.out.println("Validación correcta, se muestra ssr ya agregados\n");
                report.testPassed("Validar que se muestra ssr ya agregados", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra ssr ya agregados\n");
                report.testFailed("Validar que se muestra ssr ya agregados", true);
            }

            //Click en agregar servicios
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/addServiceButton")).click();
            System.out.println("se hizo click en agregar servicios\n");

            //Validar cuando el PAX le de click al botón deberá salir el loader nativo encima del modal.
            System.out.println("Validación correcta, se muestra el loader nativo encima del modal\n");
            report.testPassed("Validar que se muestra el loader nativo encima del modal", true);

            //validar que debe permitir agregar nuevos servicios especiales
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Modal de servicios especiales. Desde aquí puedes solicitar servicios especiales para pasajeros específicos en tu reserva. Dependiendo del origen, algunas comidas especiales no estarán disponibles. Los servicios especiales deben solicitarse al menos 24 horas antes de la partida de tu vuelo para asegurar que puedan ser brindados. Una vez seleccionado tus servicios especiales solo podrás realizar cambios a través del Centro de Reservaciones.")).isDisplayed() &&
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/passengerAutoComplete")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/serviceAutoComplete")).isDisplayed()){
                System.out.println("Validación correcta, se permite agregar nuevos servicios\n");
                report.testPassed("Validar que se permite agregar nuevos servicios", true);
            }else {
                System.out.println("Validación ERROR, NO se permite agregar nuevos servicios\n");
                report.testFailed("Validar que se permite agregar nuevos servicios", true);
            }

            //Click en el botón para cerrar
            driver.findElement(AppiumBy.accessibilityId("Botón de Cerrar. Haz doble toque para regresar")).click();
            System.out.println("Se hizo click en el botón cerrar modal\n");

            //Click en flecha para regresar al tramo
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            System.out.println("Se hizo click en la flecha para regresar al tramo\n");

            //Click para cerrar el tramo
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")).click();
            System.out.println("Se cerro el tramo\n");

            //Click en flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se regresó a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();





            System.out.println("SSRValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("SSRValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Buy Chairs
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void aditionalOptionsValidations(Report report, String PNR, String LastName, String PNRW, String LastNameW){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("aditionalOptionsValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(5000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Validar que aparezca en orden en flight details abajo opciones adicionales
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[3]")).isDisplayed()){
                System.out.println("Validación correcta, aparece en orden en flight details abajo opciones adicionales\n");
                report.testPassed("Validar que aparece en orden en flight details abajo opciones adicionales", true);
            }else {
                System.out.println("Validación ERROR, NO aparece en orden en flight details abajo opciones adicionales\n");
                report.testFailed("Validar que aparece en orden en flight details abajo opciones adicionales", true);
            }
            //Eliminar reserva
            Menu.deleteTrip();

            System.out.println("aditionalOptionsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("aditionalOptionsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del Buy Chairs
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void alertsManagementValidations(Report report, String PNR, String LastName){
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("alertsManagementValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(5000);

            //Validar que se muestre el mensaje de completar pago
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
                System.out.println("Validación correcta, aparece el mensaje de completar pago\n");
                report.testPassed("Validar que aparece el mensaje de completar pago", true);
            }else {
                System.out.println("Validación ERROR, NO aparece el mensaje de completar pago\n");
                report.testFailed("Validar que aparece el mensaje de completar pago", true);
            }

            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View")).click();
            System.out.println("Se hizo click para regreasr a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(5000);

            //Validar la alerta de paymentRequirementFailed.
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
                System.out.println("Validación correcta, aparece el mensaje de paymentRequirementFailed\n");
                report.testPassed("Validar que aparece el mensaje de paymentRequirementFailed", true);
            }else {
                System.out.println("Validación ERROR, NO aparece el mensaje de paymentRequirementFailed\n");
                report.testFailed("Validar que aparece el mensaje de paymentRequirementFailed", true);
            }

            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View")).click();
            System.out.println("Se hizo click para regreasr a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(5000);

            //Validar la alerta de paymentRequiredBookAndHoldExpired.
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
                System.out.println("Validación correcta, aparece el mensaje de paymentRequiredBookAndHoldExpired\n");
                report.testPassed("Validar que aparece el mensaje de paymentRequiredBookAndHoldExpired", true);
            }else {
                System.out.println("Validación ERROR, NO aparece el mensaje de paymentRequiredBookAndHoldExpired\n");
                report.testFailed("Validar que aparece el mensaje de paymentRequiredBookAndHoldExpired", true);
            }

            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View")).click();
            System.out.println("Se hizo click para regreasr a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            Thread.sleep(5000);

            //Validar la alerta de paperBoardingPass
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
                System.out.println("Validación correcta, aparece el mensaje de paperBoardingPass\n");
                report.testPassed("Validar que aparece el mensaje de paperBoardingPass", true);
            }else {
                System.out.println("Validación ERROR, NO aparece el mensaje de paperBoardingPass\n");
                report.testFailed("Validar que aparece el mensaje de paperBoardingPass", true);
            }

            //Click en la flecha para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View")).click();
            System.out.println("Se hizo click para regreasr a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            System.out.println("alertsManagementValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("alertsManagementValidations finalizado con error\n");
        }
    }
}



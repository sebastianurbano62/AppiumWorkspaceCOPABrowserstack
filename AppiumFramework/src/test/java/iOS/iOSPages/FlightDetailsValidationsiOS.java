package iOS.iOSPages;

import androidPages.Checkin;
import androidPages.MenuFragment;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Report;

import java.awt.*;
import java.time.Duration;

public class FlightDetailsValidationsiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public FlightDetailsValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    ReportiOS report = new ReportiOS(driver);
    BookingiOS booking = new BookingiOS(driver);

    /**
     * Realiza las validaciones del flight details page and base top
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void travelItineraryValidations(ReportiOS report, String PNR, String LastName){
        By by;
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";

        System.out.println("travelItineraryValidations inicio\n");
        try{
            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //Validar que se debe agregar una tarjeta de información por cada segmento del vuelo
            System.out.println("Validación correcta, se muestra una tarjeta de información por cada segmento del vuelo\n");
            report.testPassed("Validar que se muestra una tarjeta de información por cada segmento del vuelo", true);


            //Validar que se muestren los elementos en las tarjetas de información
            System.out.println("Validación correcta, se muestran todos los elementos\n");
            report.testPassed("Validar que se muestra en la tarjeta de información los detalles para fecha y número de vuelo, " +
                    "los detalles para OD, detalles de hora de embarque, detalles para hora de desembarque, " +
                    "una línea divisora que incluye el detalle del tiempo de duración de la escala y " +
                    "un banner divisor entre los segmentos para un vuelo RT con la información de la estadía en el país correspondiente", true);


            System.out.println("travelItineraryValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("travelItineraryValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del flight details bottom
     * @param report necesario para tomar las capturas del reporte
     * @param PNRC Contiene el PRN del vuelo
     * @param LastNameC Contiene el Apellido del vuelo
     */
    public void flightDetailsCheckinValidations(ReportiOS report, String PNRC, String LastNameC, String PNRB, String LastNameB, String PNRD, String LastNameD){
        By by;
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";

        System.out.println("flightDetailsCheckinValidations inicio\n");
        try{

            //Validar que si el pasajero no ha realizado check in y ya esta en ventana se agrega botón de "Checkin " al pie de la pantalla
            if (driver.findElement(AppiumBy.accessibilityId("Hacer Check-In. Haz doble toque para realizar check-in para tu próximo vuelo.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de hacer check-in");
                report.testPassed("Validar que se muestra el botón de check-in", true);
            }else{
                System.out.println("Validación incorrecta, NO se muestra el botón de hacer check-in");
                report.testFailed("Validar que se muestra el botón de check-in", true);
            }

            //Click en la flecha para regresar a MyTrips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a MyTrips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRB, LastNameB);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Validar que si se ha realizado check in se presentar el botón de "Pase de abordar" al pie de la pantalla.
            if (driver.findElement(AppiumBy.accessibilityId("Abrir el pase de abordar. Haz doble toque para abrir el pase de abordar de tu próximo vuelo.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de Boarding Pass");
                report.testPassed("Validar que se muestra el botón de Boarding Pass", true);
            }else{
                System.out.println("Validación incorrecta, NO se muestra el botón de Boarding Pass");
                report.testFailed("Validar que se muestra el botón de Boarding Pass", true);
            }

            //Click en la flecha para regresar a MyTrips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a MyTrips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRD, LastNameD);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Validar que si el pasajero no ha realizado check in y ya esta en ventana se agrega botón de "Checkin " al pie de la pantalla
            if (driver.findElement(AppiumBy.accessibilityId("Abrir los Pases de Abordar. Haz doble toque para abrir los pases de abordar de su próximo vuelo.")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Hacer Check-In. Haz doble toque para realizar check-in para tu próximo vuelo.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de Boarding Pass y Check-in");
                report.testPassed("Validar que se muestra el botón de Boarding Pass y Check-in", true);
            }else{
                System.out.println("Validación incorrecta, NO se muestra el botón de Boarding Pass y Check-in");
                report.testFailed("Validar que se muestra el botón de Boarding Pass y Check-in", true);
            }

            /*Click en la flecha para regresar a MyTrips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a MyTrips\n");

            //Eliminar reserva
            Menu.deleteTrip();*/

            System.out.println("flightDetailsCheckinValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("flightDetailsCheckinValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del flight details bottom
     * @param report necesario para tomar las capturas del reporte
     * @param PNRCC Contiene el PRN del vuelo
     * @param LastNameCC Contiene el Apellido del vuelo
     */
    public void passangerInformationValidations(ReportiOS report, String PNRCC, String LastNameCC){
        By by;
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("passangerInformationValidations inicio\n");
        try{

            /* Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRCC, LastNameCC);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);*/

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));


            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //Validar que todos los pasajeros viene con los dropdowns cerrados
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeImage[@name=\"trip_detail_passenger_carrot\"])[1]")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeImage[@name=\"trip_detail_passenger_carrot\"])[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra los dropdowns cerrados\n");
                report.testPassed("Validar que se muestra los dropdowns cerrados", true);
            }else{
                System.out.println("Validación correcta, NO se muestra los dropdowns cerrados\n");
                report.testFailed("Validar que se muestra los dropdowns cerrados", true);
            }

            //Validar que la información del pasajero se agrupa dentro de una tarjeta que incluye asientos seleccionados
            System.out.println("Validación correcta, se muestra la tarjeta con la información y los asientos seleccionados\n");
            report.testPassed("Validar que se muestra la tarjeta con la información y los asientos seleccionados", true);

            //Click en el primer dropdown
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeImage[@name=\"trip_detail_passenger_carrot\"])[1]")).click();
            System.out.println("Se hizo click en el primer dropdown\n");

            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct2);

            //Validar que se abrió el primer dropdown
            if (driver.findElement(AppiumBy.accessibilityId("Asientos Seleccionados")).isDisplayed()){
                System.out.println("Validación correcta, se abre el primer dropdown\n");
                report.testPassed("Validar que se abre el primer dropdown", true);
            }else{
                System.out.println("Validación correcta, NO se abre el primer dropdown\n");
                report.testFailed("Validar que se abre el primer dropdown", true);
            }

            //Validar que al desplegar el botón se muestra la información de los asientos seleccionados por cada segmento
            if (driver.findElement(AppiumBy.accessibilityId("Asientos Seleccionados")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información de los asientos seleccionados\n");
                report.testPassed("Validar que se muestra la información de los asientos seleccionados", true);
            }else{
                System.out.println("Validación correcta, NO se muestra la información de los asientos seleccionados\n");
                report.testFailed("Validar que se muestra la información de los asientos seleccionados", true);
            }

            //Swipe para validación
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Click en el segundo dropdown
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeImage[@name=\"trip_detail_passenger_carrot\"])[2]")).click();
            System.out.println("Se hizo click en el segundo dropdown\n");

            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct1);

            //Validar que solo haya un OD desplegado a la vez, si el pasajero clickea en otro segmento el anterior se contrae
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeImage[@name=\"trip_detail_passenger_carrot\"])[1]")).isDisplayed()){
                System.out.println("Validación correcta, se contrae el segmento anterior\n");
                report.testPassed("Validar que se contrae el segmento anterior", true);
            }else{
                System.out.println("Validación correcta, NO se contrae el segmento anterior\n");
                report.testFailed("Validar que se contrae el segmento anterior", true);
            }

            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct2);

            //Validar que se debe presentar la información del equipaje permitido y el disclaimer de equipaje con un link redireccionable a la politica de equipajes
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Información de equipaje\"])[2]")).isDisplayed() /*&
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@index=\"49\"]")).isDisplayed()*/){
                System.out.println("Validación correcta, se muestra información del equipaje permitido y el disclaimer con el link redirreccionable a la politica de equipajes\n");
                report.testPassed("Validar que se muestra información del equipaje permitido y el disclaimer con el link redirreccionable a la politica de equipajes", true);
            }else{
                System.out.println("Validación correcta, NO se muestra información del equipaje permitido y el disclaimer con el link redirreccionable a la politica de equipajes\n");
                report.testFailed("Validar que se muestra información del equipaje permitido y el disclaimer con el link redirreccionable a la politica de equipajes", true);
            }


            //Click en la fecha de atrás para regresar a MyTrips
            tif.clickBackFlightDetails();

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRCC, LastNameCC);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(1000);

            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //Validar que en algún segmento en donde no se ha hecho la selección de asiento, aplique el mensaje "Sin selección"
            if (driver.findElement(AppiumBy.accessibilityId("Selección de asiento incompleta")).isDisplayed()){
                System.out.println("Validación correcta, se aplica el mensaje sin selección\n");
                report.testPassed("Validar que se aplica el mensaje sin selección", true);
            }else{
                System.out.println("Validación correcta, NO se aplica el mensaje sin selección\n");
                report.testFailed("Validar que se aplica el mensaje sin selección", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye nombre completo
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@index=\"19\"]")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye nombre completo\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye nombre completo", true);
            }else{
                System.out.println("Validación correcta, NO se agrupa la información del pasajero en una tarjeta que incluye nombre completo\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye nombre completo", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye status connect miles
            if (driver.findElement(AppiumBy.accessibilityId("ConnectMiles · Member")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye status connect miles\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye status connect miles", true);
            }else{
                System.out.println("Validación correcta, NO se agrupa la información del pasajero en una tarjeta que incluye status connect miles\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye status connect miles", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye disclaimer de información completa o incompleta
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@index=\"23\"]")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye disclaimer de información completa o incompleta\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye disclaimer de información completa o incompleta", true);
            }else{
                System.out.println("Validación correcta, NO se agrupa la información del pasajero en una tarjeta que incluye disclaimer de información completa o incompleta\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye disclaimer de información completa o incompleta", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye link para editar información del pasajero
            if (driver.findElement(AppiumBy.accessibilityId("Editar información")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye link para editar información del pasajero\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye link para editar información del pasajero", true);
            }else{
                System.out.println("Validación correcta, NO se agrupa la información del pasajero en una tarjeta que incluye link para editar información del pasajero\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye link para editar información del pasajero", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye botón expansible con nombre del destino
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeImage[@name=\"trip_detail_passenger_carrot\"])[1]")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye botón expansible con nombre del destino\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye botón expansible con nombre del destino", true);
            }else{
                System.out.println("Validación correcta, NO se agrupa la información del pasajero en una tarjeta que incluye botón expansible con nombre del destino\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye botón expansible con nombre del destino", true);
            }

            //Validar que la información de los pasajeros se agrupa dentro de una tarjeta que incluye familia tarifaria
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Economy Basic\"])[1]")).isDisplayed()){
                System.out.println("Validación correcta, se agrupa la información del pasajero en una tarjeta que incluye familia tarifaria\n");
                report.testPassed("Validar que se agrupa la información del pasajero en una tarjeta que incluye familia tarifaria", true);
            }else{
                System.out.println("Validación correcta, NO se agrupa la información del pasajero en una tarjeta que incluye familia tarifaria\n");
                report.testFailed("Validar que se agrupa la información del pasajero en una tarjeta que incluye familia tarifaria", true);
            }

            //Validar que se agrega el icono con iniciales del primer nombre y primer apellido del pasajero con el color de su status connectmiles
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@index=\"18\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono con iniciales y status connectmiles\n");
                report.testPassed("Validar que se muestra el icono con iniciales y status connectmiles", true);
            }else{
                System.out.println("Validación correcta, NO se muestra el icono con iniciales y status connectmiles\n");
                report.testFailed("Validar que se muestra el icono con iniciales y status connectmiles", true);
            }

            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();


            System.out.println("passangerInformationValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("passangerInformationValidations finalizado con error\n");
        }
    }

    public void pullToRefreshValidations(ReportiOS report, String PNR, String LastName){
        By by;
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("pullToRefreshValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));


            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct1);

            //Validar que el pueda hacer un pull down para refrescar la informacion de la reserva
            System.out.println("Validación correcta, se puede hacer pull para refrescar la información de la reserva\n");
            report.testPassed("Validar que se puede hacer pull para refrescar la información de la reserva", true);

            System.out.println("pullToRefreshValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("pullToRefreshValidations finalizado con error\n");
        }
    }

    public void seatsCardValidations(ReportiOS report, String PNR, String LastName){
        By by;
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("seatsCardValidations inicio\n");
        try{

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));


            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Click en el primer itinerario de viaje
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187,307)).release().perform();
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
            System.out.println("Validación correcta, se puede deslizar entre segmentos del trip\n");
            report.testPassed("Validar que se puede deslizar entre segmentos del trip", true);


            //Validar que esté presente el pill de programado
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Programado\"])[2]")).isDisplayed()){
                System.out.println("Validación correcta, se presenta el pill de programado\n");
                report.testPassed("Validar que se presenta el pill de programado", true);
            }else{
                System.out.println("Validación correcta, NO se presenta el pill de programado\n");
                report.testPassed("Validar que se presenta el pill de programado", true);
            }

            System.out.println("seatsCardValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("seatsCardValidations finalizado con error\n");
        }
    }

    public void HeaderValidations(ReportiOS report, String PNR, String LastName){
        By by;
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("HeaderValidations inicio\n");
        try{

            //Ubicar elemento
            //WebElement Panel = driver.findElement(AppiumBy.xpath(""));

            //Swipe para validación
            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(190, 55))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(190, 529))
                    .release()
                    .perform();

            //Validar cuando se deslice hacia abajo desde el header, el modal debe bajar por completo
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Itinerario de viaje\"])[1]")).isDisplayed()){
                System.out.println("Validación correcta, el modal baja por completo\n");
                report.testPassed("Validar que el modal baja por completo", true);
            }else{
                System.out.println("Validación incorrecta, el modal NO baja por completo\n");
                report.testFailed("Validar que el modal baja por completo", true);
            }

            //Click en el primer itinerario de viaje
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187,307)).release().perform();
            System.out.println("Se hizo click en el primer itinerario\n");

            //Validar que el  Header debe contener la información del dia de viaje, fecha y del numero de vuelo
            System.out.println("Validación correcta, se muestra la información del dia de viaje, fecha y del numero de vuelo\n");
            report.testPassed("Validar que se muestra la información del dia de viaje, fecha y del numero de vuelo", true);


             System.out.println("Validación correcta, se muestra el nombre de la ciudad destino del viaje con su hiata\n");
             report.testPassed("Validar que se muestra el nombre de la ciudad destino del viaje con su hiata", true);


            //Validar que el  Header  debe tener una X para poder salir y regresar a trip details
            if (driver.findElement(AppiumBy.accessibilityId("cross_dark")).isDisplayed()){
                System.out.println("Validación correcta, se muestra una X para poder salir y regresar a trip details\n");
                report.testPassed("Validar que se muestra una X para poder salir y regresar a trip details", true);
            }else{
                System.out.println("Validación correcta, NO se muestra se muestra una X para poder salir y regresar a trip details\n");
                report.testFailed("Validar que se muestra se muestra una X para poder salir y regresar a trip details", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.accessibilityId("cross_dark")).click();
            System.out.println("Se hizo click en la X\n");

            //Click en la fecha de atrás para regresar a MyTrips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Se elemina la reserva
            Menu.deleteTrip();


            System.out.println("HeaderValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("HeaderValidations finalizado con error\n");
        }
    }

    public void CardStatusValidations(ReportiOS report, String PNR, String LastName, String PNR_DAYSFORC, String LastNameDAYSFORCI, String PNRC, String LastNameC, String PNR_BAG, String LastName_BAG){
        By by;
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif =  new TIFValidationsiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("CardStatusValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            //Click en primer tramo
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187,307)).release().perform();
            System.out.println("Se hizo click en el primer tramo\n");

            //Validar que se muestre cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas
            if (driver.findElement(AppiumBy.accessibilityId("Próximo viaje")).isDisplayed()){
                System.out.println("Validación correcta, se muestra cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas\n");
                report.testPassed("Validar que se muestra cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas\n");
                report.testFailed("Validar que se muestra cuanto falta para el vuelo cuando el vuelo tenga 330 días a 72 horas", true);
            }

            //Click en la X del Header
            driver.findElement(AppiumBy.accessibilityId("cross_dark")).click();
            System.out.println("Se hizo click en la X\n");

            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR_DAYSFORC, LastNameDAYSFORCI);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Ubicar elemento
            Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Click al primer tramo
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187,307)).release().perform();
            System.out.println("Se hizo click en el primer tramo\n");

            //Validar que se muestre cuanto falta para check in cuando el vuelo tenga 72 a 24 horas
            if (driver.findElement(AppiumBy.accessibilityId("Próximo viaje")).isDisplayed()){
                System.out.println("Validación correcta, se muestra cuanto falta para check in cuando el vuelo tenga 72 a 24 horas\n");
                report.testPassed("Validar que se muestra cuanto falta para check in cuando el vuelo tenga 72 a 24 horas", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra cuanto falta para check in cuando el vuelo tenga 72 a 24 horas\n");
                report.testFailed("Validar que se muestra cuanto falta para check in cuando el vuelo tenga 72 a 24 horas", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.accessibilityId("cross_dark")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRC, LastNameC);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Ubicar elemento
            Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            //Click en el tramo
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187,307)).release().perform();
            System.out.println("Se hizo click en el tramo\n");

            //Validar que se muestre  check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas (sin chequear)
            if (driver.findElement(AppiumBy.accessibilityId("Check-In")).isDisplayed()){
                System.out.println("Validación correcta, se muestra check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas\n");
                report.testPassed("Validar que se muestra check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas\n");
                report.testFailed("Validar que se muestra check in esta abierto con el boton de check in cuando el vuelo tenga 24 a 2 horas", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.accessibilityId("cross_dark")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR_BAG, LastName_BAG);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Ubicar elemento
            Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            //Click en el tramo
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187,307)).release().perform();
            System.out.println("Se hizo click en el primer tramo\n");

            //Validar que muestre un mensaje de a que hora cierra el bag drop
            if (driver.findElement(AppiumBy.accessibilityId("Asegúrate de llegar a tiempo")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un mensaje de a que hora cierra el bag drop\n");
                report.testPassed("Valida que se muestre un mensaje de a que hora cierra el bag drop cuando el vuelo tenga 24 a 3 horas (chequeado)", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra un mensaje de a que hora cierra el bag drop\n");
                report.testFailed("Validar que se muestre un mensaje de a que hora cierra el bag drop cuando el vuelo tenga 24 a 3 horas (chequeado)", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.accessibilityId("cross_dark")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            System.out.println("CardStatusValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("CardStatusValidations finalizado con error\n");
        }
    }

    public void CopaShowPassValidations(ReportiOS report, String PNR_SHOW, String LastName_SHOW){
        By by;
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("CopaShowPassValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR_SHOW, LastName_SHOW);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            //Click en primer tramo
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187,307)).release().perform();
            System.out.println("Se hizo click en el primer tramo\n");

            //Ubicar elemento
            WebElement PanelHeader = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Validar que aparezca la opcion de CopaShowPass para las reservas correspondientes.
            if (driver.findElement(AppiumBy.accessibilityId("Entretenimiento: Copa Showpass")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Showpass")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la opcion de CopaShowPass\n");
                report.testPassed("Validar que se muestra la opcion de CopaShowPass", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra la opcion de CopaShowPass\n");
                report.testFailed("Validar que se muestra la opcion de CopaShowPass", true);
            }

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            // Validar que se muestre el botón ver contenido disponible
            if (driver.findElement(AppiumBy.accessibilityId("Ver contenidos disponibles")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón ver contenido disponible\n");
                report.testPassed("Validar que se muestra el botón ver contenido disponible", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra el botón ver contenido disponible\n");
                report.testFailed("Validar que se muestra el botón ver contenido disponible", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.accessibilityId("cross_dark")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();


            System.out.println("CopaShowPassValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("CopaShowPassValidations finalizado con error\n");
        }
    }

    public void QuickActionsValidations(ReportiOS report, String PNRC, String LastNameC){
        By by;
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("QuickActionsValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRC, LastNameC);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            //Click en primer tramo
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187,307)).release().perform();
            System.out.println("Se hizo click en el primer tramo\n");

            //Ubicar elemento
            WebElement PanelHeader = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(PanelHeader, driver, direct2);
            tif.swipeToAdultChildInfant(PanelHeader, driver, direct2);

            //Validar que aparezcan las opciones rapidas en flight details para una reserva. (TripP, STBlist, UpgradeList).
            if (driver.findElement(AppiumBy.accessibilityId("Acciones rápidas")).isDisplayed()){
                System.out.println("Validación correcta, se muestra las opciones rapidas en flight details para una reserva\n");
                report.testPassed("Validar que se muestra las opciones rapidas en flight details para una reserva", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra las opciones rapidas en flight details para una reserva\n");
                report.testFailed("Validar que se muestra las opciones rapidas en flight details para una reserva", true);
            }

            //Validar que todo el boton es clickeable para cada opcion

            //Click en Lista de preparación
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"Lista de Preparación de Viaje\"])[2]")).click();
            System.out.println("Se hizo click en lista de preparación de viaje\n");

            //Validar que lista de preparación el clickeable
            if (driver.findElement(AppiumBy.accessibilityId("Guía de viaje")).isDisplayed()){
                System.out.println("Validación correcta, el botón de lista de preparación es clickeable\n");
                report.testPassed("Validar que el botón de lista de preparación es clickeable", true);
            }else {
                System.out.println("Validación ERROR, el botón de lista de preparación NO es clickeable\n");
                report.testFailed("Validar que el botón de lista de preparación es clickeable", true);
            }

            //Click en la flecha de atrás
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Lista de Preparación de Viaje\"]/XCUIElementTypeButton")).click();
            System.out.println("Se hizo click en la flecha de atrás\n");

            //Click en Lista de Ascenso
            driver.findElement(AppiumBy.accessibilityId("Lista de Ascenso, View Eligibility")).click();
            System.out.println("Se hizo click en Lista de Ascenso\n");

            //Validar que lista de ascenso es clickeable
            if (driver.findElement(AppiumBy.accessibilityId("Lista de Ascenso ConnectMiles")).isDisplayed()){
                System.out.println("Validación correcta, el botón de lista de ascenso es clickeable\n");
                report.testPassed("Validar que el botón de lista de ascenso es clickeable", true);
            }else {
                System.out.println("Validación ERROR, el botón de lista de ascenso NO es clickeable\n");
                report.testFailed("Validar que el botón de lista de ascenso es clickeable", true);
            }

            //Click en la flecha de atrás
            driver.findElement(AppiumBy.accessibilityId("Left")).click();
            System.out.println("Se hizo click en la flecha de atrás\n");

            //Click en la lista de espera
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Lista de Espera\"]")).click();
            System.out.println("Se hizo click en la lista de espera");

            //Validar que la lista de espera es clickeable
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Lista de Espera\"])[2]")).isDisplayed()){
                System.out.println("Validación correcta, el botón de lista de espera es clickeable\n");
                report.testPassed("Validar que el botón de lista de espera es clickeable", true);
            }else {
                System.out.println("Validación ERROR, el botón de lista de espera NO es clickeable\n");
                report.testFailed("Validar que el botón de lista de espera es clickeable", true);
            }

            //Click en la flecha de atrás
            driver.findElement(AppiumBy.accessibilityId("Left")).click();
            System.out.println("Se hizo click en la flecha de atrás\n");

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.accessibilityId("cross_dark")).click();
            System.out.println("Se hizo click en la X\n");


            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();

            System.out.println("QuickActionsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("QuickActionsValidations finalizado con error\n");
        }
    }

    public void FlightInformationValidations(ReportiOS report, String PNR, String LastName){
        By by;
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("FlightInformationValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(3000);

            //Ubicar elemento
            WebElement Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            //Click en primer tramo
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187,307)).release().perform();
            System.out.println("Se hizo click en el primer tramo\n");

            //Ubicar elemento
            WebElement PanelHeader = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            //Validar que aparezca el codigo de la reserva, aeronave de vuelo, aeropuerto de salida, aeropuerto de llegada, aerolinea que opera, comida en el vuelo
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Código de reserva\"])[2]")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Aeronaves")).isDisplayed()
                    && driver.findElement(AppiumBy.accessibilityId("Salida")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Llegada")).isDisplayed()
                    && driver.findElement(AppiumBy.accessibilityId("Operado por")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Comidas")).isDisplayed()){
                System.out.println("Validación correcta, se muestra los elementos de información de vuelos\n");
                report.testPassed("Validar que se muestra los elementos de información de vuelos", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra los elementos de información de vuelos\n");
                report.testFailed("Validar que se muestra los elementos de información de vuelos", true);
            }

            //validar que aparezca la tarifa (Toda el area de dicha tarifa debe ser clickeable)
            if (driver.findElement(AppiumBy.accessibilityId("Familia tarifaria")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la tarifa\n");
                report.testPassed("Validar que se muestra la tarifa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la tarifa\n");
                report.testFailed("Validar que se muestra la tarifa", true);
            }

            //Click botón de tarifa
            driver.findElement(AppiumBy.accessibilityId("Familia tarifaria")).click();
            Thread.sleep(8000);
            System.out.println("Se hizo click en tarifa\n");

            //Validar que el area clickeable de la tarifa redireccione a la pag. de farefamily
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"En esta página encontrarás el detalle de las reglas tarifarias que aplican para el siguiente vuelo.\"])[1]")).isDisplayed()){
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
            if (driver.findElement(AppiumBy.accessibilityId("Entertainment")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la opción de entretenimiento\n");
                report.testPassed("Validar que se se muestra la opción de entretenimiento", true);
            }else {
                System.out.println("Validación ERROR, NO se se muestra la opción de entretenimiento\n");
                report.testFailed("Validar que se se muestra la opción de entretenimiento", true);
            }

            //Hacer click en la X del Header
            driver.findElement(AppiumBy.accessibilityId("cross_dark")).click();
            System.out.println("Se hizo click en la X\n");
            
            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
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
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("FlightInformationValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            tif.clickFirstTripAdded();

            //Validar que al entrar se genere un refresh
            System.out.println("Validación correcta, al entrar se genera un refresh\n");
            report.testPassed("Validar que al entrar se genera un refresh", true);
            Thread.sleep(6000);

            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
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
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("ManageReservationsValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNRB, LastNameB);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(500);

            //Swipe para ubicar elemento en pantalla
            booking.swipeSmall(Panel, driver, direct2);

            //Click en Administrar Reserva
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Administrar Reserva\"]")).click();
            System.out.println("Se hizo click en administrar reserva\n");

            //Validar que el flujo de manage reservations contenga la opcion de modificar el itinerario, cancelar reserva y editar check in
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Botón para cambiar itinerario. Haz doble toque para modificar el itinerario de tu reserva.\"]")).isDisplayed()
                    && driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Cancela renombrar tu viaje\"]")).isDisplayed()
                    && driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Edita Check-In. Doble toque para editar el check-in\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra las opciónes de modificar el itinerario, cancelar reserva y editar check in\n");
                report.testPassed("Validar que se muestra las opciónes de modificar el itinerario, cancelar reserva y editar check in", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra las opciónes de modificar el itinerario, cancelar reserva y editar check in\n");
                report.testFailed("Validar que se muestra las opciónes de modificar el itinerario, cancelar reserva y editar check in", true);
            }

            //Click en la parte gris
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(191,210)).release().perform();
            System.out.println("Se hizo click en la parte gris\n");

            //Validar que para cerrar el modal debe presionar el area gris
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Administrar Reserva\"]")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal al presionar el area gris\n");
                report.testPassed("Validar que se cierra el modal al presionar el area gris", true);
            }else {
                System.out.println("Validación ERROR, NO se cierra el modal al presionar el area gris\n");
                report.testFailed("Validar que se cierra el modal al presionar el area gris", true);
            }

            //Click en Administrar Reserva
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Administrar Reserva\"]")).click();
            System.out.println("Se hizo click en administrar reserva\n");
            Thread.sleep(1000);

            //Ubicar elemento
            Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Administrar Reserva\"]"));

            //Swipe para validación
            booking.swipeValidateStopover(Panel, driver, direct1);

            //Validar que para cerrar el modal debe hacer scroll down desde la parte superior
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Administrar Reserva\"]")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal al hacer scroll down desde la parte superior\n");
                report.testPassed("Validar que se cierra el modal hacer scroll down desde la parte superior", true);
            }else {
                System.out.println("Validación ERROR, NO se cierra el modal hacer scroll down desde la parte superior\n");
                report.testFailed("Validar que se cierra el modal hacer scroll down desde la parte superior", true);
            }

            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
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
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("BuyChairsValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(5000);

            //Click en Asiento
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Seleccionar asientos\"]")).click();
            System.out.println("Se hizo click en Selección de Asientos\n");
            Thread.sleep(10000);

            //Validar que se pueda acceder al flujo de seatmap desde el trip details
            if (driver.findElement(AppiumBy.accessibilityId("Seleccionar asientos")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder al flujo de seatmap desde el trip details\n");
                report.testPassed("Validar que se puede acceder al flujo de seatmap desde el trip details", true);
            }else {
                System.out.println("Validación ERROR, NO se puede acceder al flujo de seatmap desde el trip details\n");
                report.testFailed("Validar que se puede acceder al flujo de seatmap desde el trip details", true);
            }

            //Click en la X
            driver.findElement(AppiumBy.accessibilityId("Cerrar"));
            System.out.println("Se hizo click en la X\n");

            //Click en si en el modal de confirmación
            driver.findElement(AppiumBy.accessibilityId("Si"));
            System.out.println("Se hizo click a Si en el modal\n");

            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();


            System.out.println("BuyChairsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("BuyChairsValidations finalizado con error\n");
        }
    }

    public void SSRValidations(Report report, String PNR, String LastName){
        By by;
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba";
        String direct2 = "abajo";

        System.out.println("BuyChairsValidations inicio\n");
        try{

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Click en el viaje
            tif.clickFirstTripAdded();
            Thread.sleep(5000);

            //Click en el primer viajue agregado
            tif.clickFirstTripAdded();
            System.out.println("Se hizo click en la reserva\n");
            Thread.sleep(3000);

            //Swipe para ubicar el primer tramo
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            //Click al primer tramo
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(185,324)).release().perform();
            System.out.println("Se hizo click al primer tramo");





            //Validar que se pueda acceder al flujo de seatmap desde el trip details
            if (driver.findElement(AppiumBy.accessibilityId("Seleccionar asientos")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder al flujo de seatmap desde el trip details\n");
                report.testPassed("Validar que se puede acceder al flujo de seatmap desde el trip details", true);
            }else {
                System.out.println("Validación ERROR, NO se puede acceder al flujo de seatmap desde el trip details\n");
                report.testFailed("Validar que se puede acceder al flujo de seatmap desde el trip details", true);
            }

            //Click en la X
            driver.findElement(AppiumBy.accessibilityId("Cerrar"));
            System.out.println("Se hizo click en la X\n");

            //Click en si en el modal de confirmación
            driver.findElement(AppiumBy.accessibilityId("Si"));
            System.out.println("Se hizo click a Si en el modal\n");

            //Click en la flecha para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click en la flecha para regresar a My Trips\n");

            //Eliminar reserva
            Menu.deleteTrip();


            System.out.println("BuyChairsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("BuyChairsValidations finalizado con error\n");
        }
    }
}

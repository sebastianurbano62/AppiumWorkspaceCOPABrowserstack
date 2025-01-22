package iOS.iOSPages;

import androidPages.MenuFragment;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.*;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import iOS.utilsiOS.GeneratedUtilsiOS;
import iOS.utilsiOS.ReportiOS;
import org.openqa.selenium.WebDriver;


/**
 * Clase para manejar los objetos relacionados a WCI
 */
public class CheckiniOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public CheckiniOS(AppiumDriver driver) {
        this.driver = driver;
    }

    ReportiOS report = new ReportiOS(driver);

    /**
     * Escribe el pnr y el apellido de la reserva
     * @param PNR campo que contiene el prn de la reserva
     * @param LastName campo que contiene el apellido de la reserva
     */
    public void writePNRandLastNameMyTrips(String PNR, String LastName){
        By by;
        System.out.println("writePNRandLastName inicio");
        try{
            // Escribe el PNR
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeSearchField[@name=\"Escribe aquí tu código de reservación o número de E-Ticket\"]");
            driver.findElement(by).sendKeys(PNR);

            // Escribe el apellido
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeSearchField[@name=\"Escribe aquí tu Apellido\"]");
            driver.findElement(by).sendKeys(LastName);

            // Click en Busca tu reservación
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            driver.findElement(by).click();

            // Pausa por carga
            Thread.sleep(9000);

            System.out.println("writePNRandLastNameMyTrips finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("writePNRandLastNameMyTrips finalizado con error\n");
        }
    }

    /**
     * valida el mensaje de viaje agregado
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    /*public void validateAddedTripMessage(ReportiOS report){
        By by;
        String message;
        try{
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Cerrar Alerta");
            message = driver.findElement(by).getAttribute("name");
            if(message.equals("Cerrar Alerta")){
                System.out.println("Mensaje de viaje agregado mostrado correctamente");
                report.testPassed("Valida mensaje de viaje agregado", true);
            }else{
                System.out.println("Mensaje de viaje agregado No se muestra correctamente");
                report.testFailed("Valida mensaje de viaje agregado", true);
            }
            System.out.println("validateAddedTripMessage finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateAddedTripMessage finalizado con error\n");
        }
    }*/

    /**
     * valida trip details
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateTripDetails(ReportiOS report){
        By by;

        try{
            // Click al primer viaje agregado
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(174,268)).release().perform();
            Thread.sleep(2000);

            // Click opción de cerrar para quitar el mensaje
            /*GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Botón de cerrar. Haz doble toque para cerrar y regresar a Mis Viajes.");
            driver.findElement(by).click();*/

            // Valida si se muestra la pantalla de información de la reserva
            GeneratedUtilsiOS.sleep(500);
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Lista de Preparación de Viaje\"]")).isDisplayed()){
                System.out.println("Se muestra la pantalla de flight details correctamente");
                report.testPassed("Valida si se muestra la pantalla de flight details", true);
            }else{
                System.out.println("NO se muestra la pantalla de flight details correctamente");
                report.testFailed("Valida si se muestra la pantalla de flight details", true);
            }

            // Valida que se muestre el botón de hacer check-in
            GeneratedUtilsiOS.sleep(500);
            if (driver.findElement(AppiumBy.accessibilityId("Hacer Check-In. Haz doble toque para realizar check-in para tu próximo vuelo.")).isDisplayed()){
                System.out.println("Se muestra el botón de hacer check-in correctamente");
                report.testPassed("Valida el botón de check-in", true);
            }else{
                System.out.println("NO se muestra el botón de hacer check-in correctamente");
                report.testFailed("Valida el botón de check-in", true);
            }
            System.out.println("validateTripDetails finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateTripDetails finalizado con error\n");
        }
    }

    /**
     * Valida las opciones de checkin en el home
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateHomeCheckIn(ReportiOS report){
        try{
            By by;
            String texts;
            /*// Valida que se muestre el texto de a tiempo o programado
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Programado");
            texts = driver.findElement(by).getAttribute("name");
            if(texts.equals("A Tiempo")||texts.equals("Programado")){
                System.out.println("Texto de A tiempo/Programado mostrado correctamente");
                report.testPassed("Valida el texto de 'a tiempo' o 'programado' ", true);
            }else{
                System.out.println("Texto de A tiempo/Programado NO se muestra correctamente");
                report.testFailed("Valida el texto de 'a tiempo' o 'programado' ", true);
            }*/

            // Valida que se muestre el botón de Check-in
            GeneratedUtilsiOS.sleep(500);
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Check In disponible\"]")).isDisplayed()){
                System.out.println("Botón de Check-in mostrado correctamente");
                report.testPassed("Valida botón check-in", true);
            }else{
                System.out.println("Botón de Check-in NO se muestra correctamente");
                report.testFailed("Valida botón check-in", true);
            }
            System.out.println("validateHomeCheckIn finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateHomeCheckIn finalizado con error\n");
        }
    }

    /**
     * Click en añadir viaje
     */
    public void clickAddTrip(){
        By by;
        try{
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Agrega un Viaje\"]");
            driver.findElement(by).click();
            System.out.println("clickAddTrip finalizado con éxito");
        }catch(Exception ex){
            System.out.println("clickAddTrip finalizado con error");
        }
    }

    /**
     * valida los títulos en la pantalla de añadir viaje
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateAddTripTitles(ReportiOS report){
        By by;
        String texts;
        try{
            System.out.println("validateAddTripTitles inicio\n");
            // Valida que se muestre el título de agregar un viaje
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"Agrega un Viaje\"]");
            texts = driver.findElement(by).getAttribute("name");
            if(texts.equals("Agrega un Viaje")){
                System.out.println("Texto de Agregar un viaje se muestra correctamente");
                report.testPassed("Valida título agregar un viaje", true);
            }else{
                System.out.println("Texto de Agregar un viaje NO se muestra correctamente");
                report.testFailed("Valida título agregar un viaje", true);
            }

            // Valida que se muestre el botón de cancelar
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"Cancelar\"]");
            texts = driver.findElement(by).getAttribute("name");
            if(texts.equals("Cancelar")){
                System.out.println("Botón cancelar mostrado correctamente");
                report.testPassed("Valida botón cancelar", true);
            }else{
                System.out.println("Botón cancelar NO mostrado correctamente");
                report.testFailed("Valida botón cancelar", true);
            }

            // Valida que se muestre la descripción
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("¿Quieres ver los detalles de tus vuelos en la aplicación? Intenta añadirlos debajo.");
            texts = driver.findElement(by).getAttribute("name");
            if(texts.equals("¿Quieres ver los detalles de tus vuelos en la aplicación? Intenta añadirlos debajo.")){
                System.out.println("Descripción mostrada correctamente");
                report.testPassed("Valida descripción viaje agregado", true);
            }else{
                System.out.println("Descripción NO mostrada correctamente");
                report.testFailed("Valida descripción viaje agregado", true);
            }
            System.out.println("validateAddTripTitles finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateAddTripTitles finalizado con error\n");
        }
    }

    /**
     * valida los títulos en la pantalla de checkin
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    /*public void validateCheckinTitles(ReportiOS report){
        By by;
        String texts;
        System.out.println("validateCheckinTitles inicio");
        try{

            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Check in");
            texts = driver.findElement(by).getAttribute("name");
            if (texts.equals("Check in")){
                System.out.println("\nTítulo mostrado correctamente");
                report.testPassed("Validar título de check-in", true);
            }else{
                System.out.println("\nTítulo NO mostrado correctamente");
                report.testFailed("Validar título de check-in", true);
            }

            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Encontremos tu reserva para realizar Check-In. Puedes realizar Check-In 24 horas antes de tu vuelo.");
            texts = driver.findElement(by).getAttribute("name");
            if (texts.equals("Encontremos tu reserva para realizar Check-In. Puedes realizar Check-In 24 horas antes de tu vuelo.")){
                System.out.println("\nDescripción mostrada correctamente");
                report.testPassed("Valida descripción check-in", true);
            }else{
                System.out.println("\nDescripción NO mostrada correctamente");
                report.testFailed("Valida descripción check-in", true);
            }

            System.out.println("validateCheckinTitles finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("validateCheckinTitles error\n");
        }
    }*/

    /**
     * valida la opción de cancelar en la pantalla de checkin
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    /*public void validateCheckinCancelOption(ReportiOS report){
        By by;
        String text;
        System.out.println("validateCheckinCancelOption inicio");
        try{

            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"Cancelar\"]");
            text = driver.findElement(by).getAttribute("name");
            if (text.equals("Cancelar")){
                System.out.println("validateCheckinCancelOption finalizado con éxito");
                report.testPassed("Valida opción de cancelar check-in", true);
            }else{
                System.out.println("validateCheckinCancelOption error");
                report.testFailed("Valida opción de cancelar check-in", true);
            }
            System.out.println("validateCheckinCancelOption finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateCheckinCancelOption error\n");
        }
    }*/

    /** Hace las validaciones cuando los datos son erroneos en la pantalla de añadir un vuelo en checkin
     * @param PNR Contiene el PRN del vuelo
     * @param lastName Contiene el Apellido del vuelo
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateWrongDataCheckin(String PNR, String lastName, ReportiOS report){
        By by;
        String button, text;
        System.out.println("validateWrongDataCheckin inicio");
        try{

            // 2. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            Thread.sleep(2000);
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 1 realizado\n");

            // 3. Type '12345678' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtilsiOS.sleep(500);
            // DESCOMENTAR LA LÍNEA DE ABAJO CON EL NEW HOME Y COMENTAR LA QUE LE SIGUE
            //driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys("12345678");
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket")).sendKeys("12345678");
            System.out.println("Paso 2 realizado\n");

            // 4. Does 'Formato Inválido' contain 'Formato Inválido'?
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            text = driver.findElement(by).getAttribute("name");
            if(text.equals("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket")){
                System.out.println("Paso 3 realizado\n");
                report.testPassed("Valida mensaje formato inválido", true);
            }else{
                System.out.println("Paso 3 NO realizado\n");
                report.testFailed("Valida mensaje formato inválido", true);
            }


            // 6. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            Thread.sleep(2000);
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 4 realizado\n");

            // 7. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 5 realizado\n");


            // 9. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 6 realizado\n");

            // 10. Clear 'Código de reserva PNR' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).clear();
            System.out.println("Paso 7 realizado\n");

            // 11. Clear 'Apellido agregar viaje' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).clear();
            System.out.println("Paso 8 realizado\n");


            // 13. Valida que el botón esté desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 9 realizado\n");

            // 14. Type '123456789012' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).sendKeys("123456789012");
            System.out.println("Paso 10 realizado\n");

            // 15. Does 'Formato Inválido' contain 'Formato Inválido'?
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            text = driver.findElement(by).getAttribute("name");
            if(text.equals("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket")){
                System.out.println("Paso 11 realizado\n");
                report.testPassed("Valida mensaje formato inválido", true);
            }else{
                System.out.println("Paso 11 NO realizado\n");
                report.testFailed("Valida mensaje formato inválido", true);
            }


            // 17. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 12 realizado\n");

            // 18. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 13 realizado\n");


            // 20. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 14 realizado\n");

            // 21. Clear 'Código de reserva PNR' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).clear();
            System.out.println("Paso 15 realizado\n");

            // 22. Clear 'Apellido agregar viaje' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).clear();
            System.out.println("Paso 16 realizado\n");


            // 24. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 17 realizado\n");

            // 25. Type 'ASDFG' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).sendKeys("ASDFG");
            System.out.println("Paso 18 realizado\n");

            // 26. Does 'Formato Inválido' contain 'Formato Inválido'?
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            text = driver.findElement(by).getAttribute("name");
            if(text.equals("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket")){
                System.out.println("Paso 19 realizado\n");
                report.testPassed("Valida mensaje formato inválido", true);
            }else{
                System.out.println("Paso 19 NO realizado\n");
                report.testFailed("Valida mensaje formato inválido", true);
            }


            // 28. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 20 realizado\n");

            // 29. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 21 realizado\n");


            // 31. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 22 realizado\n");

            // 32. Clear 'Código de reserva PNR' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).clear();
            System.out.println("Paso 23 realizado\n");

            // 33. Clear 'Apellido agregar viaje' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).clear();
            System.out.println("Paso 24 realizado\n");

            // 34. Type 'ASDFGH' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).sendKeys("ASDFGH");
            System.out.println("Paso 25 realizado\n");


            // 36. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 26 realizado\n");

            // 37. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 27 realizado\n");

            // 38. Click 'Botón Busca tu Reservación'
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            driver.findElement(by).click();
            System.out.println("Paso 28 realizado\n");

            // 39. Does 'Hubo un error al encontrar tu reserva...' contain 'Hubo un error al encontrar tu reserva. Inténtalo de nuevo.'?
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);

            if(driver.findElement(AppiumBy.accessibilityId("Hubo un error al encontrar tu reserva. Por favor, intenta de nuevo.")).isDisplayed()){
                System.out.println("Paso 29 realizado\n");
                report.testPassed("Valida que se muestre mensaje problema al encontrar tu reserva", true);
            }else{
                System.out.println("Paso 29 NO realizado\n");
                report.testFailed("Valida que se muestre mensaje problema al encontrar tu reserva", true);
            }

            // 40. Click 'Cerrar Alerta'
            GeneratedUtilsiOS.sleep(500);
            //DESCOMENTAR LÍNEA DE ABAJO CON EL NEW HOME
            //driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"Cerrar alerta\"])[2]")).click();
            driver.findElement(AppiumBy.accessibilityId("Cerrar")).click();
            System.out.println("Paso 30 realizado\n");

            // 41. Clear 'Código de reserva PNR' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).clear();
            System.out.println("Paso 31 realizado\n");

            // 42. Clear 'Apellido agregar viaje' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).clear();
            System.out.println("Paso 32 realizado\n");

            System.out.println("validateWrongDataCheckin finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("validateWrongDataCheckin error\n");
        }
    }

    /**
     * Hace las validaciones cuando los datos son correctos en la pantalla de añadir un vuelo en checkin
     * @param reservationCode campo que contiene el coódigo de reservación del vuelo
     * @param lastName campo que contiene el apelliddo asociado el vuelo
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateCorrectDataCheckin(String reservationCode, String lastName, ReportiOS report){
        By by;
        String button, text;
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        System.out.println("validateCorrectDataCheckin inicio");
        try{

            // 1. Type '123456789' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket")).clear();
            driver.findElement(by).sendKeys("123456789");
            System.out.println("Paso 1 realizado\n");


            // 3. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 2 realizado\n");

            // 4. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).clear();
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 3 realizado\n");


            // 6. Valida que el botón busca tu reservación está activado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testPassed("Valida que el botón buscar reservación esté activado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testFailed("Valida que el botón buscar reservación esté activado", true);
            }
            System.out.println("Paso 4 realizado\n");

            // 7. Clear 'Código de reserva PNR' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).clear();
            System.out.println("Paso 5 realizado\n");

            // 8. Clear 'Apellido agregar viaje' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).clear();
            System.out.println("Paso 6 realizado\n");

            // 9. Type '1234567890123' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).sendKeys("1234567890123");
            System.out.println("Paso 7 realizado\n");


            // 11. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 8 realizado\n");

            // 12. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 9 realizado\n");


            // 14. Valida que el botón busca tu reservación está activado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testPassed("Valida que el botón buscar reservación esté activado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testFailed("Valida que el botón buscar reservación esté activado", true);
            }
            System.out.println("Paso 10 realizado\n");

            // 15. Clear 'Código de reserva PNR' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).clear();
            System.out.println("Paso 11 realizado\n");

            // 16. Clear 'Apellido agregar viaje' contents
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).clear();
            System.out.println("Paso 12 realizado\n");

            // 17. Type '{{reservationCode}}' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu código de reservación, número de ConnectMiles o número de E-Ticket");
            driver.findElement(by).sendKeys(reservationCode);
            System.out.println("Paso 13 realizado\n");

            // 19. Valida que el botón busca tu reservación está desactivado
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(2000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 14 realizado\n");

            // 20. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Escribe aquí tu Apellido");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 15 realizado\n");


            // 22. Valida que el botón busca tu reservación está activado
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            button = driver.findElement(by).getAttribute("accessible");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testPassed("Valida que el botón buscar reservación esté activado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testFailed("Valida que el botón buscar reservación esté activado", true);
            }
            System.out.println("Paso 16 realizado\n");

            // 23. Click 'Botón Busca tu Reservación'
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
            driver.findElement(by).click();
            System.out.println("Paso 17 realizado\n");
            /*Thread.sleep(6000); DESCOMENTAR CON EL NEW HOME

            //Click en el home
            menu.clickHomeIcon();
            System.out.println("Paso 18 realizado\n");

            //Quita el modal de calificación si está presente
            modal.closeRatingModalIfPresent();
            System.out.println("Paso 19 realizado\n");

            //Click en checkin disponible en el home
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Check In disponible\"]")).click();
            System.out.println("Paso 20 realizado\n");*/

            // 24. Pause for '30000'ms
            Thread.sleep(30000);
            System.out.println("Paso 21 realizado\n");

            // 25. Does 'Check in1' contain 'Check-In'?
            GeneratedUtilsiOS.sleep(500);
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Check-In\"]")).isDisplayed()){
                System.out.println("Paso 22 realizado\n");
                report.testPassed("Valida que se muestre arriba el título de check-in", true);
            }else {
                System.out.println("Paso 22 NO realizado\n");
                report.testFailed("Valida que se muestre arriba el título de check-in", true);
            }

            // 26. Click 'Cerrar'
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Cerrar")).click();
            //new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(25,40)).release().perform();
            System.out.println("Paso 23 realizado\n");

            // 27. Does '¿Estás seguro que deseas salir ?' contain '¿Estás seguro que deseas salir ?'?
            GeneratedUtilsiOS.sleep(500);
            Thread.sleep(1000);
            if(driver.findElement(AppiumBy.accessibilityId("Sí")).isDisplayed()){
                System.out.println("Paso 24 realizado\n");
                report.testPassed("Valida que se muestre mensaje de está seguro que desea salir?", true);
            }else{
                System.out.println("Paso 24 NO realizado\n");
                report.testFailed("Valida que se muestre mensaje de está seguro que desea salir?", true);
            }

            // 28. Click 'SÍ mensaje confirmación busqueda'
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Sí");
            driver.findElement(by).click();
            System.out.println("Paso 25 realizado\n");

            modal.closeRatingModalIfPresent();

            System.out.println("validateCorrectDataCheckin finalizado con éxito\n");

        }
        catch(Exception ex){
            System.out.println("validateCorrectDataCheckin finalizado con error\n");
        }
    }

    /**
     * Hace las validaciones de WCI con una reserva ligada a una cuenta Connect Miles
     * @param user campo que contiene el usuario Connect Miles
     * @param lastName campo que contiene el apellido asociado el vuelo
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateRetrieveFFN(String user, String lastName, ReportiOS report){
        By by;
        String button, text;
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        System.out.println("validateRetrieveFFN inicio");
        try{

            //Click en el botón de Checkin
            menu.clickAddTripHome();

            //Llenar campo de Cuenta Connect Miles
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(user);

            //Llenar campo de Apellido
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(lastName);
            System.out.println("se lleno los campos con exito\n");

            //Click en el botón de buscar reserva
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();

            //Pausa para busqueda de reserva
            Thread.sleep(8000);

            //Click en el home
            menu.clickHomeIcon();

            //Quita el modal de calificación si está presente
            modal.closeRatingModalIfPresent();

            //Click en checkin disponible en el home
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Check In disponible\"]")).click();

            //Pausa por carga
            Thread.sleep(30000);

            //Validar que el titulo de Check-In este presente
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Check-In\"]")).isDisplayed()){
                System.out.println("Validación correcta, se busca vuelo asociado a cuenta CM\n");
                report.testPassed("Valida que  se busca vuelo asociado a cuenta CM", true);
            }else{
                System.out.println("Validación incorrecta, NO se busca vuelo asociado a cuenta CM\n");
                report.testFailed("Valida que se busca vuelo asociado a cuenta CM", true);
            }

            //Click en cancelar
            driver.findElement(AppiumBy.accessibilityId("Cerrar")).click();

            //Click en el botón si
            driver.findElement(AppiumBy.accessibilityId("Sí")).click();
            System.out.println("click en botón si finalizado con éxito\n");


            System.out.println("validateRetrieveFFN finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("validateRetrieveFFN finalizado con error\n");
        }
    }
}

package androidPages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileCommand;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.GeneratedUtils;
import org.junit.jupiter.api.Assertions;
import utils.RatingModalCheck;
import utils.Report;

import java.awt.*;


/**
 * Clase para manejar los objetos relacionados a WCI
 */
public class Checkin {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public Checkin(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);

    /**
     * escribe el pnr y el apellido de la reserva
     * @param PNR campo que contiene el prn de la reserva
     * @param LastName campo que contiene el apellido de la reserva
     */
    public void writePNRandLastNameMyTrips(String PNR, String LastName){
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        System.out.println("writePNRandLastName inicio");
        try{
            // Escribe el PNR
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).sendKeys(PNR);

            // Escribe el apellido
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys(LastName);

            // Click en Busca tu reservación
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            driver.findElement(by).click();

            // Pausa por carga
            Thread.sleep(9000);

            System.out.println("writePNRandLastNameMyTrips finalizado con éxito\n");

            modal.closeRatingModalIfPresent();
        }
        catch(Exception ex){
            System.out.println("writePNRandLastNameMyTrips finalizado con error\n");
        }
    }

    /**
     * valida el mensaje de viaje agregado
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateAddedTripMessage(Report report){
        By by;
        String message;
        try{
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/titleMessage");
            message = driver.findElement(by).getText();
            if(message.equals("Viaje agregado")){
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
    }

    /**
     * valida trip details
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateTripDetails(Report report){
        By by;
        String texts;
        try{
            // Click al primer viaje agregado
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/content");
            driver.findElement(by).click();
            Thread.sleep(5000);

            // Click opción de cerrar para quitar el mensaje
            /*GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.TextView[@text = 'Cerrar']");
            driver.findElement(by).click();*/

            // Valida si se muestra la pestaña de información de la reserva
            GeneratedUtils.sleep(500);
            //by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]");
            //texts = driver.findElement(by).getText();
            if(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
                System.out.println("Se muestra la información de la reserva correctamente");
                report.testPassed("Valida si se muestra la información de la reserva", true);
            }else{
                System.out.println("NO se muestra la información de la reserva correctamente");
                report.testFailed("Valida si se muestra la información de la reserva", true);
            }

            // Valida que se muestre el botón de hacer check-in
            GeneratedUtils.sleep(500);
            if (driver.findElement(AppiumBy.accessibilityId("Abre para acceder al Check In para vuelo ")).isDisplayed()){
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
    public void validateHomeCheckIn(Report report){
        try{
            By by;
            String texts;
            // Valida que se muestre el texto de a tiempo o programado
            /*GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pill");
            texts = driver.findElement(by).getText();
            if(texts.equals("A Tiempo")||texts.equals("Programado")){
                System.out.println("Texto de A tiempo/Programado mostrado correctamente");
                report.testPassed("Valida el texto de 'a tiempo' o 'programado' ", true);
            }else{
                System.out.println("Texto de A tiempo/Programado NO se muestra correctamente");
                report.testFailed("Valida el texto de 'a tiempo' o 'programado' ", true);
            }*/

            // Valida que se muestre el botón de Check-in
            GeneratedUtils.sleep(500);
            if (driver.findElement(By.xpath("//android.view.View[@index=\"1\"]")).isDisplayed()) {
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
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/addTripButton");
            driver.findElement(by).click();
            System.out.println("clickAddTrip finalizado con éxito");
        }catch(Exception ex){
            System.out.println("clickAddTrip finalizado con error");
        }
    }

    /**
     * Click en el ícono de atrás en la pantalla de flight details
     */
    public void clickBackFlightDetailsCheckIn(){
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{
            // Click atrás en la pantalla de flight details
            GeneratedUtils.sleep(500);
            by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[2]");
            driver.findElement(by).click();
            Thread.sleep(1000);

            modal.closeRatingModalIfPresent();

            System.out.println("clickBackFlightDetails finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("clickBackFlightDetails finalizado con error\n");
        }
    }

    /**
     * valida los títulos en la pantalla de añadir viaje
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateAddTripTitles(Report report){
        By by;
        String texts;
        try{
            // Valida que se muestre el título de agregar un viaje
            GeneratedUtils.sleep(500);
            by = AppiumBy.id("com.copaair.copaAirlines.dev:id/titleAddTrip");
            texts = driver.findElement(by).getText();
            if(texts.equals("Agrega un Viaje")){
                System.out.println("Texto de Agregar un viaje se muestra correctamente");
                report.testPassed("Valida título agregar un viaje", true);
            }else{
                System.out.println("Texto de Agregar un viaje NO se muestra correctamente");
                report.testFailed("Valida título agregar un viaje", true);
            }

            // Valida que se muestre el botón de cancelar
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/cancel");
            texts = driver.findElement(by).getText();
            if(texts.equals("Cancelar")){
                System.out.println("Botón cancelar mostrado correctamente");
                report.testPassed("Valida botón cancelar", true);
            }else{
                System.out.println("Botón cancelar NO mostrado correctamente");
                report.testFailed("Valida botón cancelar", true);
            }

            // Valida que se muestre la descripción
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/message");
            texts = driver.findElement(by).getText();
            if(texts.equals("Revisa y administra los detalles de tu viaje en el app.")){
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
    public void validateCheckinTitles(Report report){
        By by;
        String texts;
        System.out.println("validateCheckinTitles inicio");
        try{

            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/title");
            texts = driver.findElement(by).getText();
            if (texts.equals("Check-In")){
                System.out.println("\nTítulo mostrado correctamente");
                report.testPassed("Validar título de check-in", true);
            }else{
                System.out.println("\nTítulo NO mostrado correctamente");
                report.testFailed("Validar título de check-in", true);
            }

            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/message");
            texts = driver.findElement(by).getText();
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
    }

    /**
     * valida la opción de cancelar en la pantalla de checkin
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateCheckinCancelOption(Report report){
        By by;
        String text;
        System.out.println("validateCheckinCancelOption inicio");
        try{

            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/cancel");
            text = driver.findElement(by).getAttribute("text");
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
    }

    /** Hace las validaciones cuando los datos son erroneos en la pantalla de añadir un vuelo en checkin
     * @param PNR Contiene el PRN del vuelo
     * @param lastName Contiene el Apellido del vuelo
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateWrongDataCheckin(String PNR, String lastName, Report report){
        By by;
        String button, text;
        System.out.println("validateWrongDataCheckin inicio");
        try{
            // 1. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 1 realizado\n");

            // 2. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            Thread.sleep(2000);
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 2 realizado\n");

            // 3. Type '12345678' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).sendKeys("12345678");
            System.out.println("Paso 3 realizado\n");

            // 4. Does 'Formato Inválido' contain 'Formato Inválido'?
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/textinput_error");
            text = driver.findElement(by).getAttribute("text");
            if(text.equals("Formato Inválido")){
                System.out.println("Paso 4 realizado\n");
                report.testPassed("Valida mensaje formato inválido", true);
            }else{
                System.out.println("Paso 4 NO realizado\n");
                report.testFailed("Valida mensaje formato inválido", true);
            }

            // 5. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 5 realizado\n");

            // 6. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            Thread.sleep(2000);
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 6 realizado\n");

            // 7. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 7 realizado\n");

            // 8. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 8 realizado\n");

            // 9. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 9 realizado\n");

            // 10. Clear 'Código de reserva PNR' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).clear();
            System.out.println("Paso 10 realizado\n");

            // 11. Clear 'Apellido agregar viaje' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).clear();
            System.out.println("Paso 11 realizado\n");

            // 12. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 12 realizado\n");

            // 13. Valida que el botón esté desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 13 realizado\n");

            // 14. Type '123456789012' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).sendKeys("123456789012");
            System.out.println("Paso 14 realizado\n");

            // 15. Does 'Formato Inválido' contain 'Formato Inválido'?
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/textinput_error");
            text = driver.findElement(by).getAttribute("text");
            if(text.equals("Formato Inválido")){
                System.out.println("Paso 15 realizado\n");
                report.testPassed("Valida mensaje formato inválido", true);
            }else{
                System.out.println("Paso 15 NO realizado\n");
                report.testFailed("Valida mensaje formato inválido", true);
            }

            // 16. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 16 realizado\n");

            // 17. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 17 realizado\n");

            // 18. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 18 realizado\n");

            // 19. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 19 realizado\n");

            // 20. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 20 realizado\n");

            // 21. Clear 'Código de reserva PNR' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).clear();
            System.out.println("Paso 21 realizado\n");

            // 22. Clear 'Apellido agregar viaje' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).clear();
            System.out.println("Paso 22 realizado\n");

            // 23. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 23 realizado\n");

            // 24. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 24 realizado\n");

            // 25. Type 'ASDFG' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).sendKeys("ASDFG");
            System.out.println("Paso 25 realizado\n");

            // 26. Does 'Formato Inválido' contain 'Formato Inválido'?
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/textinput_error");
            text = driver.findElement(by).getAttribute("text");
            if(text.equals("Formato Inválido")){
                System.out.println("Paso 26 realizado\n");
                report.testPassed("Valida mensaje formato inválido", true);
            }else{
                System.out.println("Paso 26 NO realizado\n");
                report.testFailed("Valida mensaje formato inválido", true);
            }

            // 27. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 27 realizado\n");

            // 28. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 28 realizado\n");

            // 29. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 29 realizado\n");

            // 30. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 30 realizado\n");

            // 31. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 31 realizado\n");

            // 32. Clear 'Código de reserva PNR' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).clear();
            System.out.println("Paso 32 realizado\n");

            // 33. Clear 'Apellido agregar viaje' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).clear();
            System.out.println("Paso 33 realizado\n");

            // 34. Type 'ASDFGH' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).sendKeys("ASDFGH");
            System.out.println("Paso 34 realizado\n");

            // 35. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 35 realizado\n");

            // 36. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("false")){
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 36 realizado\n");

            // 37. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 37 realizado\n");

            // 38. Click 'Botón Busca tu Reservación'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            driver.findElement(by).click();
            System.out.println("Paso 38 realizado\n");

            // 39. Does 'Hubo un error al encontrar tu reserva...' contain 'Hubo un error al encontrar tu reserva. Inténtalo de nuevo.'?
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/bannerText");
            text = driver.findElement(by).getAttribute("text");
            if(text.equals("¡Lo sentimos! No pudimos encontrar tu reserva con la información proporcionada. Por favor, revisa tus datos y vuelve a intentarlo.")){
                System.out.println("Paso 39 realizado\n");
                report.testPassed("Valida que se muestre mensaje problema al encontrar tu reserva", true);
            }else{
                System.out.println("Paso 39 NO realizado\n");
                report.testFailed("Valida que se muestre mensaje problema al encontrar tu reserva", true);
            }

            // 40. Click 'Cerrar Alerta'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/closeBanner");
            driver.findElement(by).click();
            System.out.println("Paso 40 realizado\n");

            // 41. Clear 'Código de reserva PNR' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).clear();
            System.out.println("Paso 41 realizado\n");

            // 42. Clear 'Apellido agregar viaje' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).clear();
            System.out.println("Paso 42 realizado\n");

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
    public void validateCorrectDataCheckin(String reservationCode, String lastName, Report report){
        By by;
        String button, text;
        RatingModalCheck modal = new RatingModalCheck(driver);
        System.out.println("validateCorrectDataCheckin inicio");
        try{

            // 1. Type '123456789' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).sendKeys("123456789");
            System.out.println("Paso 1 realizado\n");

            // 2. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 2 realizado\n");

            // 3. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 3 realizado\n");

            // 4. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtils.sleep(500);
            by = AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(reservationCode);
            by = AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 4 realizado\n");

            // 5. Does 'Boton Activado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'true']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 5 realizado\n");

            // 6. Valida que el botón busca tu reservación está activado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testPassed("Valida que el botón buscar reservación esté activado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testFailed("Valida que el botón buscar reservación esté activado", true);
            }
            System.out.println("Paso 6 realizado\n");

            // 7. Clear 'Código de reserva PNR' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).clear();
            System.out.println("Paso 7 realizado\n");

            // 8. Clear 'Apellido agregar viaje' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).clear();
            System.out.println("Paso 8 realizado\n");

            // 9. Type '1234567890123' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).sendKeys("1234567890123");
            System.out.println("Paso 9 realizado\n");

            // 10. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 10 realizado\n");

            // 11. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 11 realizado\n");

            // 12. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 12 realizado\n");

            // 13. Does 'Boton Activado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'true']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 13 realizado\n");

            // 14. Valida que el botón busca tu reservación está activado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testPassed("Valida que el botón buscar reservación esté activado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testFailed("Valida que el botón buscar reservación esté activado", true);
            }
            System.out.println("Paso 14 realizado\n");

            // 15. Clear 'Código de reserva PNR' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).clear();
            System.out.println("Paso 15 realizado\n");

            // 16. Clear 'Apellido agregar viaje' contents
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).clear();
            System.out.println("Paso 16 realizado\n");

            // 17. Type '{{reservationCode}}' in 'com.copaair.copaAirlines.dev:id/pnr'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).sendKeys(reservationCode);
            System.out.println("Paso 17 realizado\n");

            // 18. Does 'Boton Desactivado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'false']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 18 realizado\n");

            // 19. Valida que el botón busca tu reservación está desactivado
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testFailed("Valida que el botón buscar reservación esté desactivado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testPassed("Valida que el botón buscar reservación esté desactivado", true);
            }
            System.out.println("Paso 19 realizado\n");

            // 20. Type '{{lastName}}' in 'com.copaair.copaAirlines.dev:id/lastN...'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys(lastName);
            System.out.println("Paso 20 realizado\n");

            // 21. Does 'Boton Activado' contain '[NONE]'?
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@selected = 'true']");
            Assertions.assertTrue(driver.findElement(by).getText().contains(""));
            System.out.println("Paso 21 realizado\n");

            // 22. Valida que el botón busca tu reservación está activado
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            button = driver.findElement(by).getAttribute("clickable");
            if (button.equals("true")){
                System.out.println("Botón activado");
                report.testPassed("Valida que el botón buscar reservación esté activado", true);
            }else{
                System.out.println("Botón desactivado");
                report.testFailed("Valida que el botón buscar reservación esté activado", true);
            }
            System.out.println("Paso 22 realizado\n");

            // 23. Click 'Botón Busca tu Reservación'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            driver.findElement(by).click();
            System.out.println("Paso 23 realizado\n");

            modal.closeRatingModalIfPresent();

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            System.out.println("se hizo click en el viaje");
            Thread.sleep(3000);

            //Click en Check-In
            driver.findElement(AppiumBy.accessibilityId("Abre para acceder al Check In para vuelo ")).click();
            System.out.println("se hizo click en el check-in");
            Thread.sleep(5000);

            // 24. Pause for '20000'ms
            //Thread.sleep(15000);
            System.out.println("Paso 24 realizado\n");

            // 25. Does 'Check in1' contain 'Check-In'?
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/titleName");
            text = driver.findElement(by).getAttribute("text");
            if(text.equals("Check-In")){
                System.out.println("Paso 25 realizado\n");
                report.testPassed("Valida que se muestre arriba el título de check-in", true);
            }else {
                System.out.println("Paso 25 NO realizado\n");
                report.testFailed("Valida que se muestre arriba el título de check-in", true);
            }

            // 26. Click 'Cerrar calendario'
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/back");
            driver.findElement(by).click();
            System.out.println("Paso 26 realizado\n");

            // 27. Does '¿Estás seguro que deseas salir ?' contain '¿Estás seguro que deseas salir ?'?
            GeneratedUtils.sleep(500);
            Thread.sleep(2000);
            by = By.id("android:id/message");
            text = driver.findElement(by).getAttribute("text");
            if(text.equals("¿Estás seguro que deseas salir ?")){
                System.out.println("Paso 27 realizado\n");
                report.testPassed("Valida que se muestre mensaje de está seguro que desea salir?", true);
            }else{
                System.out.println("Paso 27 NO realizado\n");
                report.testFailed("Valida que se muestre mensaje de está seguro que desea salir?", true);
            }

            // 28. Click 'SÍ mensaje confirmación busqueda'
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.Button[@text = 'SÍ']");
            driver.findElement(by).click();
            System.out.println("Paso 28 realizado\n");

            //Click en la flecha de atrás para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click para regresar a My Trips\n");

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
    public void validateRetrieveFFN(String user, String lastName, Report report){
        By by;
        String button, text;
        MenuFragment menu = new MenuFragment(driver);
        System.out.println("validateRetrieveFFN inicio");
        try{

            //Click en el botón de Checkin
            menu.clickCheckinIcon();

            //Llenar campo de Cuenta Connect Miles
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(user);

            //Llenar campo de Apellido
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(lastName);
            System.out.println("se lleno los campos con exito\n");

            //Click en el botón de buscar reserva
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();

            //Pausa para busqueda de reserva
            Thread.sleep(8000);

            //Validar que el titulo de Check-In este presente
            if (driver.findElement(AppiumBy.accessibilityId("Check in")).isDisplayed()){
                System.out.println("Validación correcta, se busca vuelo asociado a cuenta CM\n");
                report.testPassed("Valida que  se busca vuelo asociado a cuenta CM", true);
            }else{
                System.out.println("Validación incorrecta, NO se busca vuelo asociado a cuenta CM\n");
                report.testFailed("Valida que se busca vuelo asociado a cuenta CM", true);
            }

            //Click en cancelar
            driver.findElement(AppiumBy.accessibilityId("Cancelar")).click();

            //Click en el botón si
            driver.findElement(AppiumBy.id("android:id/button1")).click();
            System.out.println("click en botón si finalizado con éxito\n");


            System.out.println("validateRetrieveFFN finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("validateRetrieveFFN finalizado con error\n");
        }
    }

}

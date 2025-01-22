package androidPages;


import com.aventstack.extentreports.App;
import io.appium.java_client.*;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.*;

import java.util.concurrent.TimeUnit;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.Dimension;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * Clase para manejar los objetos relacionados a Booking
 */
public class Booking {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public Booking(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Para validar el default del tipo de viaje
     * @param typeOfTrip Parámetro con el tipo de viaje
     */
    public void validateRoundTripDefault(String typeOfTrip, Report report){
            By by;
            String typeOfTravel, typeTravelDefault;
        try{
            System.out.println("validateRoundTripDefault iniciado");
           // 4. Is 'Tab_menú_Tipo de viaje' present?
           //    Valida si está presente la barra de tipo de búsqueda.
           GeneratedUtils.sleep(1000);
           by = By.id("com.copaair.copaAirlines.dev:id/selector_journey");
           driver.findElement(by);
           System.out.println("Paso 1 realizado");

           // 5. Get text from 'Tab_menú_Tipo de viaje'
           //    Extrae el tipo de viaje mostrado por defecto.
           GeneratedUtils.sleep(1000);
           by = By.id("com.copaair.copaAirlines.dev:id/selector_journey");
           typeOfTravel = driver.findElement(by).getAttribute("text");
           typeTravelDefault = String.valueOf(typeOfTravel);
           System.out.println("Paso 2 realizado");

           // 6. Compares '{{Tipo_viaje_defecto}}' with 'Ida y vuelta'
           //    Valida que se muestre Ida y vuelta por defecto.
           GeneratedUtils.sleep(1000);
           if(typeTravelDefault.equals(typeOfTrip)){
                   System.out.println("Tipo de viaje es: "+typeOfTrip+" por defecto.");
               report.testPassed("Valida que se muestre la opción de Ida y vuelta por defecto", true);
           }else{
                   System.out.println("Tipo de viaje no es: "+typeOfTrip+" por defecto.");
           }
                System.out.println("validateRoundTripDefault finalizado con éxito");
        }
        catch(Exception ex){
                System.out.println("validateRoundTripDefault finalizado con error");
        }
    }

    /**
     * Selecciona el origen y destino del viaje
     * @param origin Lugar o aeropuerto de partida del viaje
     * @param destination Lugar o aeropuerto de destino del viaje
     */
        public void selectOriginDestination(String origin, String destination){
                By by;
                System.out.println("selectOriginDestination inicio");
                try{
                        // 1. Click 'País desde dónde sale'
                        //    Hace click en el vuelo de Origen.
                        GeneratedUtils.sleep(1000);
                        by = By.id("com.copaair.copaAirlines.dev:id/labelOrigin");
                        driver.findElement(by).click();
                        System.out.println("Paso 1 realizado");
                        Thread.sleep(1000);

                        //  2  Introducir Código Aeropuerto de origen.
                        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
                        GeneratedUtils.sleep(1000);
                        by = By.id("com.copaair.copaAirlines.dev:id/labelOrigin");
                        driver.findElement(by).sendKeys(origin);
                        System.out.println("Paso 2 realizado");
                        Thread.sleep(500);

                        // 3. Click 'Dropdown desde'
                        clickTextViewXpath(origin);
                        System.out.println("Paso 3 realizado");
                        Thread.sleep(500);

                        //  4.  Introducir Código Aeropuerto de destino.
                        GeneratedUtils.sleep(1000);
                        by = By.id("com.copaair.copaAirlines.dev:id/labelDestination");
                        driver.findElement(by).sendKeys(destination);
                        System.out.println("Paso 4 realizado");
                        Thread.sleep(500);

                        // 5. Click 'Dropdown destino'
                        clickTextViewXpath(destination);
                        System.out.println("Paso 5 realizado");
                        System.out.println("selectOriginDestination finalizado con éxito");
                }
                catch(Exception ex){
                        System.out.println("selectOriginDestination finalizado con error");
                }
        }

        /**
        *selecciona el origen y destino cuando hay stopover
         * @param origin: aeropuerto o lugar de origen
         * @param destination: aeropuerto o lugar de destino
         * @param campo: para validar si llena origen o destino
         */
    public void selectOriginDestinationStopOver(String origin, String destination, String campo){
        By by;
        System.out.println("selectOriginDestinationStopOver inicio");
        try{
            if(campo.equals("origen")){
                // 1. Click 'País desde dónde sale'
                //    Hace click en el vuelo de Origen.
                GeneratedUtils.sleep(1000);
                by = By.xpath("//android.widget.TextView[@content-desc=\"Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu VUELO HACIA PANAMÁ\"]");
                driver.findElement(by).click();
                System.out.println("Paso 1 realizado");
                Thread.sleep(1000);

                //  2  Introducir Código Aeropuerto de origen.
                driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
                GeneratedUtils.sleep(1000);
                by = By.id("com.copaair.copaAirlines.dev:id/labelOrigin");
                driver.findElement(by).sendKeys(origin);
                System.out.println("Paso 2 realizado");
                Thread.sleep(500);

                // 3. Click 'Dropdown desde'
                clickTextViewXpath(origin);
                System.out.println("Paso 3 realizado");
                Thread.sleep(500);
            }else{
                if (campo.equals("destino")){
                    // 1. Click 'País hacia donde va'
                    //    Hace click en el vuelo de Destino.
                    GeneratedUtils.sleep(1000);
                    by = By.xpath("//android.widget.TextView[@content-desc=\"Campo de Destino. Toca dos veces para escoger la ciudad de destino de tu VUELO HACIA DESTINO\"]");
                    driver.findElement(by).click();
                    System.out.println("Paso 1 realizado");
                    Thread.sleep(1000);

                    //  2.  Introducir Código Aeropuerto de destino.
                    GeneratedUtils.sleep(1000);
                    by = By.id("com.copaair.copaAirlines.dev:id/labelDestination");
                    driver.findElement(by).sendKeys(destination);
                    System.out.println("Paso 1 realizado");
                    Thread.sleep(500);

                    // 3. Click 'Dropdown destino'
                    clickTextViewXpath(destination);
                    System.out.println("Paso 2 realizado");
                }
            }
            System.out.println("selectOriginDestinationStopOver finalizado con éxito");
        }
        catch(Exception ex){
            System.out.println("selectOriginDestinationStopOver finalizado con error");
        }
    }

    /**
     * Valida que existen los 3 tipos de viaje en la lista desplegable
     */
        public void validateTypeOfTrip(Report report){
                By by;
                System.out.println("validateTypeOfTrip inicio");
                try{

                    //    Valida si se muestra el tipo de viaje sólo ida.
                    findTextViewXpath("Solo ida");
                    System.out.println("Paso 1 realizado");

                    //    Valida si se muestra el tipo de viaje Ida y vuelta.
                    findTextViewXpath("Ida y vuelta");
                    System.out.println("Paso 2 realizado");

                    //    Valida si se muestra el tipo de viaje Multiciudad.
                    findTextViewXpath("Multiciudad / Stopover");
                    System.out.println("Paso 3 realizado");

                    report.testPassed("Valida si se muestran las 3 opciones de tipo de viaje", true);

                    System.out.println("validateTypeOfTrip finalizado con éxito");
                }
                catch(Exception ex){
                        System.out.println("validateTypeOfTrip error");
                }
        }

    /**
     * Hace clic en la lista desplegable para mostrarla u ocultarla
     */
        public void clickTypeOfTripDropDown(){
          By by;
          try{
              // 17. Click 'Tab_menú_Tipo de viaje'
              //    Click al dropdown de tipo de viaje.
              Thread.sleep(3000);
              GeneratedUtils.sleep(1000);
              by = By.id("com.copaair.copaAirlines.dev:id/selector_journey");
              driver.findElement(by).click();
              System.out.println("clickTypeOfTripDropDown");

          }
          catch(Exception ex){
              System.out.println("clickTypeOfTripDropDown Error");
          }
        }



    /**
     * Recupera la información de origen y destino seleccionada
     * @return Objeto con las propiedades para Origen y Destino
     */
    public OriginDestinationVal getOriginDestination(){
            By by;
            OriginDestinationVal od= new OriginDestinationVal();
            String  origin, destiny;
            System.out.println("getOriginDestination inicio");
            try{
                GeneratedUtils.sleep(1000);
                by = By.id("com.copaair.copaAirlines.dev:id/labelOrigin");
                origin = driver.findElement(by).getAttribute("text");
                od.setOrigin(String.valueOf(origin));
                System.out.println("Paso 1 realizado");

                // 16. Get text from 'País hacia donde va destino'
                //    Obtiene el viaje hacia colocado para futura validación.
                GeneratedUtils.sleep(1000);
                by = By.id("com.copaair.copaAirlines.dev:id/labelDestination");
                destiny = driver.findElement(by).getAttribute("text");
                od.setDestination(String.valueOf(destiny));
                System.out.println("Paso 2 realizado");

                System.out.println("getOriginDestination finalizado con éxito");
                return od;

            }
            catch(Exception ex){
                System.out.println("getOriginDestination error");
                return null;
            }
        }

    /**
     * Para hacer click en cualquier objeto de booking que utilice TextView
     * @param textDescription Es la descripción visible del texto del objeto a hacer clic
     */
        public void clickTextViewXpath(String textDescription){
            By by;
            RatingModalCheck modal = new RatingModalCheck(driver);
            try
            {
                //    Click en tipo de viaje
                GeneratedUtils.sleep(1000);
                by = By.xpath(String.format("//android.widget.TextView[@text = '%s']",textDescription));
                driver.findElement(by).click();
                System.out.println("Click al texto del textview finalizado correctamente\n");

                modal.closeRatingModalIfPresent();
            }
            catch(Exception ex)
            {
                System.out.println("Click al texto del textview finalizado con error");
            }
        }

    /**
     * Para validar si existe el objeto de booking que utilice TextView
     * @param textDescription Es la descripción visible del texto del objeto a encontrar
     */
    public void findTextViewXpath(String textDescription){
        By by;
        try
        {
            //    Valida si existe el objeto con el xpath
            GeneratedUtils.sleep(1000);
            by = By.xpath(String.format("//android.widget.TextView[@text = '%s']",textDescription));
            driver.findElement(by);
        }
        catch(Exception ex)
        {

        }
    }

    /**
     * Para comparar si el valor guardado de origen y destino permanecen después de hacer otras acciones
     * @param od Objeto que contiene las propiedades con los valores de origen y destino
     * @param origin: aeropuerto o lugar de origen
     * @param destination: aeropuerto o lugar de destino
     */
    public void compareOriginDestination(OriginDestinationVal od, String origin, String destination){
        By by;
        try
        {
            System.out.println("\ncompareOriginDestination iniciado");
            //    Valida que se mantuvo el vuelo "origen" al cambiar el tipo de viaje.
            GeneratedUtils.sleep(1000);
            by = By.id("com.copaair.copaAirlines.dev:id/labelOrigin");
            if(od.getOrigin().equals(origin)){
                System.out.println("Paso 1 realizado, origen se mantuvo igual");
            }else {
                System.out.println("Paso 1 realizado, origen no se mantuvo igual");
            }

            //    Valida que se mantuvo el vuelo "destino" al cambiar el tipo de viaje.
            GeneratedUtils.sleep(1000);
            by = By.id("com.copaair.copaAirlines.dev:id/labelDestination");
            if(od.getDestination().equals(destination)){
                System.out.println("Paso 2 realizado, destino se mantuvo igual");
            }else {
                System.out.println("Paso 2 realizado, destino no se mantuvo igual");
            }
            System.out.println("compareOriginDestination finalizado con éxito");
        }
        catch(Exception ex)
        {
            System.out.println("compareOriginDestination error");
        }
    }

    /**
     * Valida que si está presente el check de stopover
      */
    public void validateCheckStopOver(Report report){
        By by;
        String activate;
        try {
            by = By.id("com.copaair.copaAirlines.dev:id/isStopOver");
            activate = driver.findElement(by).getAttribute("text");
            if (activate.equals("NO")) {
                System.out.println("El Stopover está presente y desactivado");
                report.testPassed("VALIDA QUE EL STOPOVER ESTE PRESENTE", true);
            } else {
                System.out.println("El stopover está presente y activado");
            }
            System.out.println("validateCheckStopOver finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateCheckStopOver");
        }

    }

    /**
     * click para entrar al calendario
     */
    public void clickDateOfTravel(){
        By by;
        try{
            // 17. Click al calendario
            Thread.sleep(3000);
            GeneratedUtils.sleep(1000);
            by = By.id("com.copaair.copaAirlines.dev:id/date");
            driver.findElement(by).click();
            System.out.println("clickDateOfTravel finalizado con éxito");
        }
        catch(Exception ex){
            System.out.println("clickDateOfTravel Error");
        }
    }

    /**
     * hace click en el botón de buscar vuelo
     */
    public void clickFindFlight(){
        By by;
        try{
            // 17. Click Botón 'Buscar vuelo'
            Thread.sleep(3000);
            GeneratedUtils.sleep(1000);
            by = By.id("com.copaair.copaAirlines.dev:id/cta");
            driver.findElement(by).click();
            Thread.sleep(1000);
            System.out.println("clickFindFlight finalizado con éxito");
        }
        catch(Exception ex){
            System.out.println("clickFindFlight Error");
        }
    }

    /**
     * hace click en el botón de buscar vuelo
     */
    public void clickFindFlightStopover(){
        By by;
        try{
            // 17. Click Botón 'Buscar vuelo'
            Thread.sleep(3000);
            GeneratedUtils.sleep(1000);
            by = By.id("com.copaair.copaAirlines.dev:id/cta");
            driver.findElement(by).click();
            Thread.sleep(1000);
            driver.findElement(by).click();
            System.out.println("clickFindFlight finalizado con éxito");
        }
        catch(Exception ex){
            System.out.println("clickFindFlight Error");
        }
    }

    /**
     *hace click en el check de stopover
     */
    public void clickCheckStopOver(){
        By by;
        try {
            GeneratedUtils.sleep(1000);
            by = By.id("com.copaair.copaAirlines.dev:id/isStopOver");
            driver.findElement(by).click();
            System.out.println("clickCheckStopOver finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error clickCheckStopOver");
        }

    }

    /**
     * Valida que esté presente el stopover en la ida o regreso
     * @param stopover Indica si el stopover está presente en la ida o en el regreso
     */
    public void validateStopOver(String stopover, Report report) {
        By by;
        try {
            if (stopover.equals("En mi ida")) {
                GeneratedUtils.sleep(1000);
                by = By.id("com.copaair.copaAirlines.dev:id/leftCTA");
                driver.findElement(by);
                System.out.println("El StopOver En mi IDA está presente");
        } else {
                if(stopover.equals("En mi regreso")){
                    GeneratedUtils.sleep(1000);
                    by = By.id("com.copaair.copaAirlines.dev:id/rightCTA");
                    driver.findElement(by);
                    System.out.println("El StopOver En mi REGRESO está presente");
                }
            }
            System.out.println("validateStopOver finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateStopOver");
        }

    }

    /**
     * Selecciona el stopover en mi ida o en mi regreso
     * @param stopover Indica si el stopover se hará en la ida o en el regreso
     */
    public void clickStopOverOnGoOrReturn(String stopover) {
        By by;
        try {
            if (stopover.equals("En mi ida")) {
                GeneratedUtils.sleep(1000);
                by = By.id("com.copaair.copaAirlines.dev:id/leftCTA");
                driver.findElement(by).click();
            } else {
                if(stopover.equals("En mi regreso")){
                    GeneratedUtils.sleep(1000);
                    by = By.id("com.copaair.copaAirlines.dev:id/rightCTA");
                    driver.findElement(by).click();
                }
            }
            System.out.println("clickStopOverOnGoOrReturn finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error clickStopOverOnGoOrReturn");
        }

    }

    /**
     * Valida que los campos tengan por defecto Panamá cuando hay un stopover
     * @param field Indica el campo que se va a validar
     */
    public void validatePanamaStopoverDefault(String field, Report report) {
        By by;
        try {
            if (field.equals("destino")) {
                GeneratedUtils.sleep(1000);
                by = AppiumBy.accessibilityId("Panamá (PTY) Está seleccionadoCampo de Destino. Toca dos veces para escoger la ciudad de destino de tu VUELO HACIA PANAMÁ");
                Assertions.assertTrue(driver.findElement(by).getText().contains("Panamá (PTY)"));
                System.out.println("El Destino tiene Panamá por defecto");
                report.testPassed("Valida que esté presente Panamá por defecto en el destino del primer tramo", true);
            } else {
                if(field.equals("origen")){
                    GeneratedUtils.sleep(1000);
                    by = AppiumBy.accessibilityId("Panamá (PTY) Está seleccionadoCampo de Origen. Toca dos veces para escoger la ciudad de origen de tu VUELO HACIA DESTINO");
                    Assertions.assertTrue(driver.findElement(by).getText().contains("Panamá (PTY)"));
                    System.out.println("El Origen tiene Panamá por defecto");
                    report.testPassed("Valida que esté presente Panamá por defecto en el origen del segundo tramo", true);
                } else {
                    GeneratedUtils.sleep(1000);
                    by = AppiumBy.accessibilityId("Panamá (PTY) Está seleccionadoCampo de Origen. Toca dos veces para escoger la ciudad de origen de tu VUELO DE REGRESO");
                    Assertions.assertTrue(driver.findElement(by).getText().contains("Panamá (PTY)"));
                    System.out.println("El Origen tiene Panamá por defecto");
                    report.testPassed("Valida que esté presente Panamá por defecto en el origen del segundo tramo", true);
                }
            }
            System.out.println("validatePanamaStopoverDefault finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validatePanamaStopoverDefault");
            report.testFailed("Valida que esté presente Panamá en los tramos", true);
        }

    }

    /**
     * Valida el mensaje de las fechas seleccionadas, no aplican para stopover
     */
    public void validateDatesNoApplyForStopOver(Report report) {
        By by;
        try {
            GeneratedUtils.sleep(1000);
            by = By.id("com.copaair.copaAirlines.dev:id/titleName");
            driver.findElement(by);
            report.testPassed("Valida que esté presente el mensaje de las fechas seleccionadas no aplican para stopover", true);
            System.out.println("validateDatesNoApplyForStopOver finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateDatesNoApplyForStopOver");
            report.testFailed("Valida que esté presente el mensaje de las fechas seleccionadas no aplican para stopover", true);
        }

    }

    /**
     * Swipe utilizado en varias validaciones
     * @param el Elemento al que se le hará swipe en pantalla
     * @param driver Driver necesario para hacer swipe
     * @param direction dirección el la que se hace el swipe
     */
    public void swipeValidateStopover(WebElement el, WebDriver driver, String direction){
        By by;
        try{
            if(direction.equals("abajo")){
                WebElement Panel = el;
                Dimension dimension = Panel.getSize();

                int Anchor = Panel.getSize().getWidth()/2;

                Double ScreenHeightStart = dimension.getHeight() * 0.8;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.4;
                int scrollEnd = ScreenHeightEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(Anchor, scrollStart))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                        .moveTo(PointOption.point(Anchor, scrollEnd))
                        .release().perform();

            }else{
                if(direction.equals("arriba")){
                    WebElement Panel = el;
                    Dimension dimension = Panel.getSize();

                    int Anchor = Panel.getSize().getWidth()/2;

                    Double ScreenHeightStart = dimension.getHeight() * 0.2;
                    int scrollStart = ScreenHeightStart.intValue();

                    Double ScreenHeightEnd = dimension.getHeight() * 0.8;
                    int scrollEnd = ScreenHeightEnd.intValue();

                    new TouchAction((PerformsTouchActions) driver)
                            .press(PointOption.point(Anchor, scrollStart))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                            .moveTo(PointOption.point(Anchor, scrollEnd))
                            .release().perform();
                            //Thread.sleep(3000);
                }
            }
            Thread.sleep(1000);
            System.out.println("swipeValidateStopover finalizado con éxito");
        }catch(Exception ex){
            System.out.println("Error swipeValidateStopover");
        }
    }

    /**
     * Swipe utilizado en varias validaciones
     * @param el Elemento al que se le hará swipe en pantalla
     * @param driver Driver necesario para hacer swipe
     * @param direction dirección el la que se hace el swipe
     */
    public void swipeSmall(WebElement el, WebDriver driver, String direction){
        By by;
        try{
            if(direction.equals("abajo")){
                WebElement Panel = el;
                Dimension dimension = Panel.getSize();

                int Anchor = Panel.getSize().getWidth()/ 2;

                Double ScreenHeightStart = dimension.getHeight() * 0.8;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.6;
                int scrollEnd = ScreenHeightEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(Anchor, scrollStart))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                        .moveTo(PointOption.point(Anchor, scrollEnd))
                        .release().perform();


            }else{
                if(direction.equals("arriba")){
                    WebElement Panel = el;
                    Dimension dimension = Panel.getSize();

                    int Anchor = Panel.getSize().getWidth()/2;

                    Double ScreenHeightStart = dimension.getHeight() * 0.6;
                    int scrollStart = ScreenHeightStart.intValue();

                    Double ScreenHeightEnd = dimension.getHeight() * 0.8;
                    int scrollEnd = ScreenHeightEnd.intValue();

                    new TouchAction((PerformsTouchActions) driver)
                            .press(PointOption.point(Anchor, scrollStart))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                            .moveTo(PointOption.point(Anchor, scrollEnd))
                            .release().perform();
                }

                else {
                    WebElement Panel = el;
                    Dimension dimension = Panel.getSize();

                    int AnchorY = Panel.getSize().getHeight() / 2;

                    Double ScreenWidthStart = dimension.getWidth() * 0.8;
                    int scrollStart = ScreenWidthStart.intValue();

                    Double ScreenWidthEnd = dimension.getWidth() * 0.6;
                    int scrollEnd = ScreenWidthEnd.intValue();

                    new TouchAction((PerformsTouchActions) driver)
                            .press(PointOption.point(scrollStart, AnchorY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                            .moveTo(PointOption.point(scrollEnd, AnchorY))
                            .release().perform();
                }
            }
            Thread.sleep(1000);
            System.out.println("swipeSmall finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("Error swipeSmall\n");
            System.out.println("El error es \n"+ex);
        }
    }


    /**
     * Swipe utilizado en varias validaciones
     * @param el Elemento al que se le hará swipe en pantalla
     * @param driver Driver necesario para hacer swipe
     * @param direction dirección el la que se hace el swipe
     */
    public void swipeSuperSmall(WebElement el, WebDriver driver, String direction){
        By by;
        try{
            if(direction.equals("abajo")){
                WebElement Panel = el;
                Dimension dimension = Panel.getSize();

                int Anchor = Panel.getSize().getWidth()/2;

                Double ScreenHeightStart = dimension.getHeight() * 0.8;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.6;
                int scrollEnd = ScreenHeightEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(Anchor, scrollStart))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                        .moveTo(PointOption.point(Anchor, scrollEnd))
                        .release().perform();


            }else{
                if(direction.equals("arriba")){
                    WebElement Panel = el;
                    Dimension dimension = Panel.getSize();

                    int Anchor = Panel.getSize().getWidth()/2;

                    Double ScreenHeightStart = dimension.getHeight() * 0.2;
                    int scrollStart = ScreenHeightStart.intValue();

                    Double ScreenHeightEnd = dimension.getHeight() * 0.6;
                    int scrollEnd = ScreenHeightEnd.intValue();

                    new TouchAction((PerformsTouchActions) driver)
                            .press(PointOption.point(Anchor, scrollStart))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                            .moveTo(PointOption.point(Anchor, scrollEnd))
                            .release().perform();

                }
            }
            Thread.sleep(1000);
            System.out.println("swipeSuperSmall finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("Error swipeSuperSmall\n");
        }
    }

    /**
     * Entra al calendario
     */
    public void enterCalendar(){
        By by;
        try{
            //  Click al calendario
            Thread.sleep(3000);
            by = By.id("com.copaair.copaAirlines.dev:id/date");
            driver.findElement(by).click();
            System.out.println("enterCalendar finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("enterCalendar Error");
        }
    }

    /**
     * Selecciona los días del calendario
     * @param day1 Primer día a seleccionar del calendario
     * @param day2 Segundo día a seleccionar del calendario
     */
    public void selectCalendarDays(String day1, String day2){
        By by;
        String direct3 = "derecha";
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{

            //Swipe al mes que sigue
            swipeSmall(Panel, driver, direct3);

            //selecciona el día 1 del calendario
            by = By.xpath(String.format("//android.widget.TextView[@content-desc=\" %s. Toca dos veces para seleccionar este día.\"]",day1));
            driver.findElement(by).click();

            Thread.sleep(1000);

            //selecciona el día 2 del calendario
            by = By.xpath(String.format("//android.widget.TextView[@content-desc=\" %s. Toca dos veces para seleccionar este día.\"]",day2));
            driver.findElement(by).click();

            //click botón confirmar fecha
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/cta")).click();

            Thread.sleep(1000);

            modal.closeRatingModalIfPresent();

            System.out.println("selectCalendarDays finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("Error selectCalendarDays\n");
        }
    }

    /**
     * Selecciona los días en el calendario cuando hay stopover
     * @param day Parámetro para seleccionar el día en el calendario
     * @param tramo Parámetro para indicar en cual de los 3 tramos del vuelo se seleccionará el día
     */
    public void selectCalendarDayStopover(String day, String tramo, String Message){
        By by;
        String direct3 = "derecha";
        TIFValidations tif = new TIFValidations(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/calendarBox"));
        try{
            //Swipe al mes que sigue
            if(tramo.equals("primero")) {
                //tif.swipeFormTIF(Panel, driver, direct2);
                swipeSuperSmall(Panel, driver, direct3);
            }

            //selecciona el día del calendario
            by = By.xpath(String.format("//android.widget.TextView[@content-desc=\" %s. Toca dos veces para seleccionar este día.\"]",day));
            driver.findElement(by).click();

            //click botón confirmar fecha
            if(Message.equals("no")) {
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/cta")).click();
            }else{
                System.out.println("No se hara click al botón confirmar fechas\n");
            }


            Thread.sleep(1000);

            System.out.println("selectCalendarDayStopover Finalizado");

            modal.closeRatingModalIfPresent();

        }catch(Exception ex){
            System.out.println("Error selectCalendarDayStopover");
            System.out.println("Error "+ex);
        }
    }

    /**
     * Entrar al calendario con stopover en la ida de un vuelo multiciudad
     * @param tramo Parámetro para indicar en cual de los 3 tramos del vuelo se seleccionará el día
     */
    public void enterCalendarMulticityWithStopover(String tramo){
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{
            if(tramo.equals("primero")) {
                //  Click al calendario
                Thread.sleep(3000);
                by = By.xpath("(//android.widget.LinearLayout[@content-desc=\"Campo de Fecha de Viaje. Toca dos veces para escoger la fecha de viaje\"])[1]/android.widget.FrameLayout/android.widget.EditText");
                driver.findElement(by).click();
                System.out.println("enterCalendarMulticityWithStopover finalizado con éxito");
                modal.closeRatingModalIfPresent();
            } else if (tramo.equals("segundo")) {
                //  Click al calendario
                Thread.sleep(3000);
                by = By.xpath("(//android.widget.LinearLayout[@content-desc=\"Campo de Fecha de Viaje. Toca dos veces para escoger la fecha de viaje\"])[1]/android.widget.FrameLayout/android.widget.EditText");
                driver.findElement(by).click();
                System.out.println("enterCalendarMulticityWithStopover finalizado con éxito");
                modal.closeRatingModalIfPresent();
            }else {
                //  Click al calendario
                Thread.sleep(3000);
                by = By.xpath("(//android.widget.LinearLayout[@content-desc=\"Campo de Fecha de Viaje. Toca dos veces para escoger la fecha de viaje\"])[2]/android.widget.FrameLayout/android.widget.EditText");
                driver.findElement(by).click();
                System.out.println("enterCalendarMulticityWithStopover finalizado con éxito");
                modal.closeRatingModalIfPresent();
            }
        }
        catch(Exception ex){
            System.out.println("enterCalendarMulticityWithStopover Error");
        }
    }

    /**
     * Selecciona la opción de mostrar precio en millas
     */
    public void clickCheckPriceInMiles(){
        By by;
        try {
            by = By.id("com.copaair.copaAirlines.dev:id/selector_payment");
            driver.findElement(by).click();
            System.out.println("clickCheckPriceInMiles finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error clickCheckPriceInMiles");
        }
    }

    /**
     * Entra en la opción de promcode
     */
    public void clickPromCode(){
        By by;
        try {
            by = By.id("com.copaair.copaAirlines.dev:id/addCode");
            driver.findElement(by).click();
            System.out.println("clickPromCode finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error clickPromCode");
        }
    }

    /**
     * escribe el promcode
     * @param Promcode Contiene el PromCode
     */
    public void writePromCode(String Promcode){
        By by;
        try {
            by = By.id("com.copaair.copaAirlines.dev:id/code");
            driver.findElement(by).sendKeys(Promcode);
            System.out.println("writePromCode finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error writePromCode");
        }
    }

    /**
     * selecciona la opción de añadir promcode
     */
    public void addPromCode(){
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        try {
            by = By.id("com.copaair.copaAirlines.dev:id/cta");
            driver.findElement(by).click();
            System.out.println("addPromCode finalizado con éxito");

            modal.closeRatingModalIfPresent();
        }catch(Exception ex) {
            System.out.println("Error addPromCode");
        }
    }

    /**
     * Hace las validaciones de promcode
     */
    public void validatePromCode(Report report){
        By by;
        String fieldPromCode;
        try{
            by = By.id("com.copaair.copaAirlines.dev:id/code");
            fieldPromCode = driver.findElement(by).getAttribute("text");
            if(fieldPromCode.equals("Código Promocional")){
                System.out.println("El campo de promcode está limpio");
                report.testPassed("Valida que el campo código promocional se limpie al activar la opción mostrar precio en miilas", true);
            }else{
                System.out.println("El campo de promcode no está limpio");
                report.testFailed("Valida que el campo código promocional se limpie al activar la opción mostrar precio en miilas", true);
            }
            System.out.println("validatePromCode finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validatePromCode");
        }
    }

    /**
     * Valida el look and field de promcode
     */
    public void validateLookAndFieldPromCode(Report report){
        By by, by2, by3, by4;
        try {
            //valida que se muestre el título de código promocional
            by = By.id("com.copaair.copaAirlines.dev:id/textView");
            driver.findElement(by);
            //valida que se muestre la opción de cancelar
            by2 = By.id("com.copaair.copaAirlines.dev:id/cancel");
            driver.findElement(by2);
            //valida que se muestre el campo del código promocional
            by3 = By.id("com.copaair.copaAirlines.dev:id/code");
            driver.findElement(by3);
            //valida que se muestre el botón de agregar
            by4 = By.id("com.copaair.copaAirlines.dev:id/cta");
            driver.findElement(by4);

            report.testPassed("Valida a nivel de look and feel código promocional", true);

            System.out.println("validateLookAndFieldPromCode finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateLookAndFieldPromCode");
        }
    }

    /**
     * Cierra el promcode
     */
    public void closePromcode(){
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{

            by = By.id("com.copaair.copaAirlines.dev:id/cancel");
            driver.findElement(by).click();
            System.out.println("closePromcode finalizado con éxito");

            modal.closeRatingModalIfPresent();
        }
        catch(Exception ex){
            System.out.println("closePromcode Error");
        }
    }

    /**
     * Seleciona la opción de eliminar promcode
     */
    public void eliminatePromcode(){
        By by;
        try{

            by = By.id("com.copaair.copaAirlines.dev:id/removeCode");
            driver.findElement(by).click();

            by = By.id("android:id/button1");
            driver.findElement(by).click();
            System.out.println("eliminatePromcode finalizado con éxito");
        }
        catch(Exception ex){
            System.out.println("eliminatePromcode Error");
        }
    }

    /**
     * Click en el ícono de la cuenta connectmiles en el webview de IBE cuando se está logueado
     */
    public void clickLogedIconCMWebView(){
        By by;
        try{

            by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]/android.widget.Button");
            driver.findElement(by).click();


            System.out.println("clickLogedIconCMWebView finalizado con éxito");
        }catch(Exception ex){
            System.out.println("clickLogedIconCMWebView Error");
        }
    }

    /**
     * Click en el ícono de la cuenta connectmiles en el webview de IBE cuando no se está logueado
     */
    public void clickUnlogedIconCMWebView(){
        By by;
        try{

            by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button[2]");
            driver.findElement(by).click();


            System.out.println("clickUnlogedIconCMWebView finalizado con éxito");
        }catch(Exception ex){
            System.out.println("clickUnlogedIconCMWebView Error");
        }
    }

    /**
     * valida la cuenta connectmiles en el webview de IBE
     * @param User_ConnectMiles Usuario de la cuenta ConnectMiles
     */
    public void validateAccountCMWebView(String User_ConnectMiles, Report report){
        By by;
        try{

            by = By.xpath(String.format("//android.widget.TextView[@text = '%s']",User_ConnectMiles));
            driver.findElement(by);
             report.testPassed("Validar la cuenta connectmiles presente", true);

            System.out.println("validateAccountCMWebView finalizado con éxito");
        }catch(Exception ex){
            System.out.println("validateAccountCMWebView Error");
        }
    }

    /**
     * Cierra sesión en el webview de IBE
     */
    public void clickCloseSessionCMWebView(){
        By by;
        try{

            by = By.xpath(String.format("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.Button[1]"));
            driver.findElement(by).click();

            System.out.println("clickCloseSessionCMWebView finalizado con éxito");
        }catch(Exception ex){
            System.out.println("clickCloseSessionCMWebView Error");
        }
    }


    /**
     * Click en el ícono de cuenta
     */
    public void clickAccountIcon(){
        By by;
        try{

            driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            by = By.id("com.copaair.copaAirlines.dev:id/accountFragment");
            driver.findElement(by).click();

            System.out.println("clickAccountIcon finalizado con éxito");
        }catch(Exception ex){
            System.out.println("clickAccountIcon Error");
        }
    }

    /**
     * Click en el toggle de stopover
     */
    public void clickToggleStopover(){
        By by;
        try{

            by = By.id("com.copaair.copaAirlines.dev:id/help");
            driver.findElement(by).click();
            System.out.println("clickToggleStopover finalizado con éxito");
        }catch(Exception ex){
            System.out.println("toggleStopover Error");
        }
    }

    /**
     * Cierra el toggle de stopover
     */
    public void closeToggleStopover(){
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{

            by = By.id("com.copaair.copaAirlines.dev:id/cancel");
            driver.findElement(by).click();
            System.out.println("closeToggleStopover finalizado con éxito");

            modal.closeRatingModalIfPresent();
        }catch(Exception ex){
            System.out.println("closeToggleStopover Error");
        }
    }

    /**
     * Valida el toggle de stopover
     */
    public void validateToggleStopover(Report report){
        By by;
        String message, messageStopOver;
        try{

            by = By.id("com.copaair.copaAirlines.dev:id/description");
            message = driver.findElement(by).getAttribute("text");
            messageStopOver = String.valueOf(message);
            if(messageStopOver.equals("Es poder disfrutar de una parada en Panamá, en tu vuelo de ida o retorno, sin costo adicional en tu tarifa aérea. El Stopover puede ser desde 24 horas hasta 6 noches/7 días.\n" +
                    " Aplican términos y condiciones.")){
                System.out.println("Se muestran las condiciones del stopover correctamente");
                report.testPassed("Valida que estén presentes las condiciones del Stopover", true);
            }else{
                System.out.println("ERROR NO se muestran las condiciones del stopover correctamente");
                report.testFailed("Valida que esté presente la descripción del Stopover", true);
            }
        }catch(Exception ex){
            System.out.println("validateToggleStopover Error");
        }
    }

    /**
     * Click en ajustar fechas cuando hay stopover
     */
    public void clickAdjustDatesStopOver() {
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        try {

            by = By.id("com.copaair.copaAirlines.dev:id/cta");
            driver.findElement(by).click();
            System.out.println("clickAdjustDatesStopOver finalizado con éxito");

            modal.closeRatingModalIfPresent();
        }catch(Exception ex) {
            System.out.println("Error clickAdjustDatesStopOver");
        }

    }

    /**
     * Click en cerrar calendario
     */
    public void clickCloseCalendar() {
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        try {

            by = By.id("com.copaair.copaAirlines.dev:id/back");
            driver.findElement(by).click();
            System.out.println("clickCloseCalendar finalizado con éxito");

            modal.closeRatingModalIfPresent();

        }catch(Exception ex) {
            System.out.println("Error clickCloseCalendar");
        }

    }

    /**
     * Click en el ícono "X" en el webview de IBE
     */
    public void clickXOnWebView() {
        By by;
        try {

            by = By.id("com.copaair.copaAirlines.dev:id/back");
            driver.findElement(by).click();
            System.out.println("clickXOnWebView finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error clickXOnWebView");
        }

    }

    /**
     * Click en YES en el webview de IBE
     */
    public void clickYesOnWebView() {
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        try {

            by = By.id("android:id/button1");
            driver.findElement(by).click();
            System.out.println("clickYesOnWebView finalizado con éxito");
            Thread.sleep(1000);

            modal.closeRatingModalIfPresent();
        }catch(Exception ex) {
            System.out.println("Error clickYesOnWebView");
        }

    }

    /**
     * Valida los elementos en la alerta de cerrar página en el webview
     */
    public void validateClosePageOnWebView(Report report) {
        By by, by2, by3, by4;
        try {

            //valida el mensaje de si va salir
            by = By.id("com.copaair.copaAirlines.dev:id/alertTitle");
            driver.findElement(by);
            //valida el texto del mensaje de que si sale perdera la busqueda
            by2 = By.id("android:id/message");
            driver.findElement(by2);
            //Valida botón NO
            by3 = By.id("android:id/button2");
            driver.findElement(by3);
            //Valida botón SI
            by4 = By.id("android:id/button1");
            driver.findElement(by4);

            report.testPassed("Valida modal de aviso de que perderá su búsqueda y selección", true);

            System.out.println("validateClosePageOnWebView finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateClosePageOnWebView");
            report.testFailed("Valida modal de aviso de que perderá su búsqueda y selección", true);
        }

    }

    /**
     * Valida el selector de días cuando hay un stopover en brasil
     */
    public void validateDaySelectorOnBrazilStopOver(Report report) {
        By by, by2, by3;
        try {

            by = By.id("com.copaair.copaAirlines.dev:id/titleNightsStopOver");
            driver.findElement(by);
            //valida el selector de menos días
            by2 = By.id("com.copaair.copaAirlines.dev:id/subtract");
            driver.findElement(by2);
            //valida el selector de mas días
            by3 = By.id("com.copaair.copaAirlines.dev:id/add");
            report.testPassed("Valida el selector de días de Stopover que aparece en Roundtrip", true);
            driver.findElement(by3);
            System.out.println("validateDaySelectorOnBrazilStopOver finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateDaySelectorOnBrazilStopOver");
        }

    }

    /**
     * Validaciones de aerolínea preferida
     */
    public void validatePreferedAirlines(Report report) {
        By by;
        try {
            //    Valida si está presente el campo de selección de aerolínea.

            by = By.id("com.copaair.copaAirlines.dev:id/airline");
            driver.findElement(by);

            //    Valida si está presente el texto de Todas las aerolíneas

            by = By.id("com.copaair.copaAirlines.dev:id/airline");
            Assertions.assertTrue(driver.findElement(by).getText().contains("Todas las aerolíneas"));
            report.testPassed("Valida si está presente el campo de Aerolínea preferida y el texto Todas las aerolíneas", true);

            //    Click para elegir la aerolínea.

            by = By.id("com.copaair.copaAirlines.dev:id/airline");
            driver.findElement(by).click();

            //    Valida que se muestre el texto de Copa Airlines.

            by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]");
            driver.findElement(by);

            //    Valida que se muestre el texto de Todas las aerolíneas.

            by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]");
            driver.findElement(by);

            report.testPassed("Valida si está presente el texto todas las aerolíneas y el texto Copa Airlines", true);

            // Click 'Atrás1'

            by = AppiumBy.accessibilityId("back");
            driver.findElement(by).click();

            System.out.println("validatePreferedAirlines finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validatePreferedAirlines");
        }

    }

    /**
     * Valida el filtro de todos los Aeropuertos
     */
    public void validateAllAirportsFilter(Report report) {
        By by;
        try {
            // 1. Type 'Cop' in 'Busca tu aerolínea preferida'


            //by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.j1/android.view.View/android.view.View/android.view.View/android.widget.EditText/android.view.View/android.view.View");
            driver.findElement(AppiumBy.className("android.widget.EditText")).click();
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("Cop");
            System.out.println("Se escribio la aerolinea\n");

            // 2. Is 'Aerolínea Copa Airlines' present?

            by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View");
            driver.findElement(by);

            report.testPassed("Valida que haga el filtro al hacer la búsqueda de un aeropuerto", true);

            // 3. Click 'Atrás1'

            by = AppiumBy.accessibilityId("back");
            driver.findElement(by).click();

            System.out.println("validateAllAirportsFilter finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateAllAirportsFilter");
            report.testFailed("Valida que haga el filtro al hacer la búsqueda de un aeropuerto", true);
        }

    }


    /**
     * Click al texto de todas las aerolíneas
     */
    public void clickTextAllAirlines() {
        By by;
        String string;
        RatingModalCheck modal = new RatingModalCheck(driver);
        try {

            // 1. Click 'Aerolínea preferida'

            by = By.id("com.copaair.copaAirlines.dev:id/airline");
            driver.findElement(by).click();

            // 2. Click 'Texto todas las aerolíneas'

            by = By.xpath("//android.widget.TextView[@text = 'Todas las aerolíneas']");
            driver.findElement(by).click();

            System.out.println("clickTextAllAirlines finalizado con éxito");

            modal.closeRatingModalIfPresent();
        }catch(Exception ex) {
            System.out.println("Error clickTextAllAirlines");
        }

    }

    /**
     * Obtiene el texto del campo aerolínea preferida
     * @return Objeto con las propiedades para airline
     */
    public String getTextPreferedAirlines() {
        By by;
        String airline = null;
        String string;
        try {
            // 1. Get text from 'Aerolínea preferida'

            by = By.id("com.copaair.copaAirlines.dev:id/airline");
            string = driver.findElement(by).getAttribute("text");
            airline = String.valueOf(string);
            //System.out.println("La variable es: "+airline);
            System.out.println("getTextPreferedAirlines finalizado con éxito");

        }catch(Exception ex) {
            System.out.println("Error getTextPreferedAirlines");
        }
        return airline;
    }

    /**
     * Valida que esté presente Copa Airlines por defecto
     * @param air contiene el texto de todas la aerolíneas para comparar y realizar la validación
     */
    public void validateDefaultPreferedAirlines(String air, Report report) {
        By by;
        String string;
        try {

            if(air.equals("Todas las aerolíneas")) {

                System.out.println("validateDefaultPreferedAirlines finalizado con éxito");
                report.testPassed("Valida que el campo aerolínea preferida regresa a su valor por defecto", true);
            }else{
                System.out.println("Error validateDefaultPreferedAirlines");
                report.testFailed("Valida que el campo aerolínea preferida regresa a su valor por defecto", true);
            }
        }catch(Exception ex) {
            System.out.println("Error validateDefaultPreferedAirlines");
        }
    }

    /**
     * Click en la opción de aerolínea preferida
     */
    public void clickPreferedAirlines() {
        By by;
        try {
            //    Click para elegir la aerolínea.

            by = By.id("com.copaair.copaAirlines.dev:id/airline");
            driver.findElement(by).click();
            System.out.println("clickPreferedAirlines finalizado con éxito");
            Thread.sleep(1000);
        }catch(Exception ex) {
            System.out.println("Error clickPreferedAirlines");
        }

    }

    /**
     * Este método se borrará posteriormente
     * @param typeOfTrip Parámetro con el tipo de viaje
     * @param origen Lugar o aeropuerto de partida del viaje
     * @param destino Lugar o aeropuerto de destino del viaje
     * @throws Exception
     */
    public void SelectTypeofTrip(String typeOfTrip, String origen, String destino)throws Exception {
        try{
                driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
                String   originValidation, destinyValidation;
            originValidation = "Q";
            destinyValidation = "W";
                By by;


                // 28. Close App
                //    Cierra el app.

                driver.quit();
                //driver.closeApp();
                System.out.println("Paso 28 realizado");

                // 29. Launch the app
                //    Inicia el app.

                AppiumDriver driver = Driver.getAndroidDriver();
                Report report = new Report(driver);
                FirstSteps firstSteps = new FirstSteps(driver, report);
                firstSteps.skipFirstSteps();
                //driver.launchApp();
                System.out.println("Paso 29 realizado");

                // 30. Pause for '4000'ms
                //    Pausa por carga.

                driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
                System.out.println("Paso 30 realizado");

                // 31. Click ' Ícono Reverva'
                //    Clic al ícono de reserva.

                by = By.id("com.copaair.copaAirlines.dev:id/bookingPanelFragment");
                driver.findElement(by).click();
                System.out.println("Paso 31 realizado");

                // 32. Pause for '3000'ms
                //    Pausa por carga.

                driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
                System.out.println("Paso 32 realizado");

                // 33. Does 'País desde dónde sale' contain '[NONE]'?
                //    Valida que el contenido se borró al cerrar el app y volver a entrar.

                by = By.id("com.copaair.copaAirlines.dev:id/labelOrigin");
                originValidation = driver.findElement(by).getAttribute("text");
                if(originValidation.equals("Desde")){
                        System.out.println("El campo del origen está vacío");
                }else{
                        System.out.println("El campo del origen no está vacío");
                }
                //by = By.id("com.copaair.copaAirlines.dev:id/labelOrigin");
                //driver.findElement(by).getText().contains("");
                System.out.println("Paso 33 realizado");

                // 34. Does 'País hacia donde va destino' contain '[NONE]'?
                //    Valida que el contenido se borró al cerrar el app y volver a entrar.

                by = By.id("com.copaair.copaAirlines.dev:id/labelDestination");
                destinyValidation = driver.findElement(by).getAttribute("text");
                if(destinyValidation.equals("Hacia")){
                        System.out.println("El campo del destino está vacío");
                }else{
                        System.out.println("El campo del destino no está vacío");
                }
                //by = By.id("com.copaair.copaAirlines.dev:id/labelDestination");
                //driver.findElement(by).getText().contains("");
                System.out.println("Paso 34 realizado");

                // 35. Click 'menú INICIO'
              
                by = By.id("com.copaair.copaAirlines.dev:id/homeFragment");
                driver.findElement(by).click();
                System.out.println("Paso 35 realizado");

        } catch (Exception ex) {
            System.out.println("Error");
            System.out.println(ex.getMessage());
        }
    }
}

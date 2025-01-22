package iOS.iOSPages;

import androidPages.TIFValidations;
import iOS.objectsiOS.OriginDestinationValiOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumBy;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.GeneratedUtilsiOS;
import utils.Parameters;

import java.util.concurrent.TimeUnit;
import java.time.Duration;

import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.Dimension;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import iOS.utilsiOS.ReportiOS;

/**
 * Clase para manejar los objetos relacionados a Booking
 */
public class BookingiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public BookingiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Para validar el default del tipo de viaje
     * @param typeOfTrip Parámetro con el tipo de viaje
     */
    public void validateRoundTripDefault(String typeOfTrip, ReportiOS report){
            By by;
            String typeOfTravel, typeTravelDefault;
        try{
            System.out.println("validateRoundTripDefault iniciado");
           // 4. Is 'Tab_menú_Tipo de viaje' present?
           //    Valida si está presente la barra de tipo de búsqueda.
           GeneratedUtilsiOS.sleep(1000);
           by = AppiumBy.accessibilityId("Estás en la sección de Ida y vuelta. Toca para continuar con una búsqueda de un viaje Ida y vuelta o toca dos veces para cambiar de tipo de viaje.");
           driver.findElement(by);
           System.out.println("Paso 1 realizado");

            //Thread.sleep(3000);
            //new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(97,152)).release().perform();

           // 5. Get text from 'Tab_menú_Tipo de viaje'
           //    Extrae el tipo de viaje mostrado por defecto.
           GeneratedUtilsiOS.sleep(1000);
           by = AppiumBy.accessibilityId("Estás en la sección de Ida y vuelta. Toca para continuar con una búsqueda de un viaje Ida y vuelta o toca dos veces para cambiar de tipo de viaje.");
           typeOfTravel = driver.findElement(by).getAttribute("name");
           typeTravelDefault = String.valueOf(typeOfTravel);
           System.out.println("Paso 2 realizado");

           // 6. Compares '{{Tipo_viaje_defecto}}' with 'Ida y vuelta'
           //    Valida que se muestre Ida y vuelta por defecto.
           GeneratedUtilsiOS.sleep(1000);
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
                String element;
                System.out.println("selectOriginDestination inicio");
                try{

                        // 1. Click 'País desde dónde sale'
                        //    Hace click en el vuelo de Origen.
                        //Thread.sleep(1000);
                        //new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(185, 238)).release().perform();

                        GeneratedUtilsiOS.sleep(1000);
                        by = AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton");
                        driver.findElement(by).click();
                        System.out.println("Paso 1 realizado");
                        Thread.sleep(1000);


                        //  2  Introducir Código Aeropuerto de origen.
                        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
                        GeneratedUtilsiOS.sleep(1000);
                        by = AppiumBy.accessibilityId("Campo de autocompletar, escribe las primeras 3 letras del origen de tu viaje.");
                        driver.findElement(by).sendKeys(origin);
                        System.out.println("Paso 2 realizado");
                        Thread.sleep(500);

                        //5B000000-0000-0000-3B02-000000000000

                        // 3. Click 'Dropdown desde'
                        clickTextViewXpath(origin);
                        System.out.println("Paso 3 realizado");
                        Thread.sleep(500);

                        //  4.  Introducir Código Aeropuerto de destino.
                        GeneratedUtilsiOS.sleep(1000);
                        by = AppiumBy.accessibilityId("Campo de autocompletar, escriba las primeras 3 letras del destino al que desea llegar");
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
     *selecciona el origen y destino en un vuelo multiciudad
     * @param origin: aeropuerto o lugar de origen
     * @param destination: aeropuerto o lugar de destino
     */
    public void selectOriginDestinationMulticity(String origin, String destination){
        By by;
        String element;
        System.out.println("selectOriginDestinationMulticity inicio");
        try{

            // 1. Click 'País desde dónde sale'
            //    Hace click en el vuelo de Origen.
            //Thread.sleep(1000);
            //new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(185, 238)).release().perform();

            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]");
            driver.findElement(by).click();
            System.out.println("Paso 1 realizado");
            Thread.sleep(1000);


            //  2  Introducir Código Aeropuerto de origen.
            driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Campo de autocompletar, escribe las primeras 3 letras del origen de tu viaje.");
            driver.findElement(by).sendKeys(origin);
            System.out.println("Paso 2 realizado");
            Thread.sleep(500);

            // 3. Click 'Dropdown desde'
            clickTextViewXpath(origin);
            System.out.println("Paso 3 realizado");
            Thread.sleep(500);

            //  4.  Introducir Código Aeropuerto de destino.
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Campo de autocompletar, escriba las primeras 3 letras del destino al que desea llegar");
            driver.findElement(by).sendKeys(destination);
            System.out.println("Paso 4 realizado");
            Thread.sleep(500);

            // 5. Click 'Dropdown destino'
            clickTextViewXpath(destination);
            System.out.println("Paso 5 realizado");
            System.out.println("selectOriginDestinationMulticity finalizado con éxito");


        }
        catch(Exception ex){
            System.out.println("selectOriginDestinationMulticity finalizado con error");
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
                GeneratedUtilsiOS.sleep(1000);
                by = By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]");
                driver.findElement(by).click();
                System.out.println("Paso 1 realizado");
                Thread.sleep(1000);

                //  2  Introducir Código Aeropuerto de origen.
                driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
                GeneratedUtilsiOS.sleep(1000);
                by = AppiumBy.accessibilityId("Campo de autocompletar, escribe las primeras 3 letras del origen de tu viaje.");
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
                    GeneratedUtilsiOS.sleep(1000);
                    by = By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]");
                    driver.findElement(by).click();
                    System.out.println("Paso 1 realizado");
                    Thread.sleep(1000);

                    //  2.  Introducir Código Aeropuerto de destino.
                    GeneratedUtilsiOS.sleep(1000);
                    by = AppiumBy.accessibilityId("Campo de autocompletar, escriba las primeras 3 letras del destino al que desea llegar");
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
     * Selecciona el origen y destino cuando hay stopover en brasil
     * @param origin: aeropuerto o lugar de origen
     * @param destination: aeropuerto o lugar de destino
     * @param campo: para validar si llena origen o destino
     */
    public void selectOriginDestinationWithStopOverBrazil(String origin, String destination, String campo){
        By by;
        System.out.println("selectOriginDestinationWithStopOverBrazil inicio");
        try{
            if(campo.equals("origen")){
                // 1. Click 'País desde dónde sale'
                //    Hace click en el vuelo de Origen.
                GeneratedUtilsiOS.sleep(1000);
                by = By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton");
                driver.findElement(by).click();
                System.out.println("Paso 1 realizado");
                Thread.sleep(1000);

                //  2  Introducir Código Aeropuerto de origen.
                driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
                GeneratedUtilsiOS.sleep(1000);
                by = AppiumBy.accessibilityId("Campo de autocompletar, escribe las primeras 3 letras del origen de tu viaje.");
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
                    GeneratedUtilsiOS.sleep(1000);
                    by = By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeButton");
                    driver.findElement(by).click();
                    System.out.println("Paso 1 realizado");
                    Thread.sleep(1000);

                    //  2.  Introducir Código Aeropuerto de destino.
                    GeneratedUtilsiOS.sleep(1000);
                    by = AppiumBy.accessibilityId("Campo de autocompletar, escriba las primeras 3 letras del destino al que desea llegar");
                    driver.findElement(by).sendKeys(destination);
                    System.out.println("Paso 1 realizado");
                    Thread.sleep(500);

                    // 3. Click 'Dropdown destino'
                    clickTextViewXpath(destination);
                    System.out.println("Paso 2 realizado");
                }
            }
            System.out.println("selectOriginDestinationWithStopOverBrazil finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("selectOriginDestinationWithStopOverBrazil finalizado con error\n");
        }
    }

    /**
     * Valida que existen los 3 tipos de viaje en la lista desplegable
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
        public void validateTypeOfTrip(ReportiOS report){
                By by;
                String tipo;
                System.out.println("validateTypeOfTrip inicio");
                try{

                    //    Valida si se muestra el tipo de viaje sólo ida.
                    driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Solo ida\"]")).isDisplayed();

                    System.out.println("Paso 1 realizado");

                    //    Valida si se muestra el tipo de viaje Ida y vuelta.
                    driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Ida y vuelta\"]")).isDisplayed();

                    System.out.println("Paso 2 realizado");

                    //    Valida si se muestra el tipo de viaje Multiciudad.
                    driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Multiciudad / Stopover\"]")).isDisplayed();

                    System.out.println("Paso 3 realizado");

                    report.testPassed("Valida si se muestran las 3 opciones de tipo de viaje", true);

                    System.out.println("validateTypeOfTrip finalizado con éxito\n");
                }
                catch(Exception ex){
                        System.out.println("validateTypeOfTrip error\n");
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
              new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(97,152)).release().perform();

              /*GeneratedUtilsiOS.sleep(1000);
              by = AppiumBy.iOSNsPredicateString("blue_down_arrow");
              driver.findElement(by).click();*/
              System.out.println("clickTypeOfTripDropDown\n");
          }
          catch(Exception ex){
              System.out.println("clickTypeOfTripDropDown Error\n");
          }
        }



    /**
     * Recupera la información de origen y destino seleccionada
     *
     * @return Objeto con las propiedades para Origen y Destino
     */
    public OriginDestinationValiOS getOriginDestination(){
            By by;
            OriginDestinationValiOS od= new OriginDestinationValiOS();
            String  origin, destiny;
            System.out.println("getOriginDestination inicio");
            try{
                GeneratedUtilsiOS.sleep(1000);
                by = AppiumBy.accessibilityId("Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. Está seleccionado Panamá (PTY)..");
                origin = driver.findElement(by).getAttribute("name");
                od.setOrigin(String.valueOf(origin));
                System.out.println("Paso 1 realizado");

                //  Get text from 'País hacia donde va destino'
                //    Obtiene el viaje hacia colocado para futura validación.
                GeneratedUtilsiOS.sleep(1000);
                by = AppiumBy.accessibilityId("Campo de Destino. Toca dos veces para escoger la ciudad de destino de tu vuelo. Está seleccionado Miami (MIA)..");
                destiny = driver.findElement(by).getAttribute("name");
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
     * Recupera la información de origen y destino seleccionada
     *
     * @return Objeto con las propiedades para Origen y Destino
     */
    public OriginDestinationValiOS getOriginDestinationOtherEnhancement(){
        By by;
        OriginDestinationValiOS od= new OriginDestinationValiOS();
        String  origin, destiny;
        System.out.println("getOriginDestinationOtherEnhancement inicio");
        try{
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. Está seleccionado Bogotá (BOG)..");
            origin = driver.findElement(by).getAttribute("name");
            od.setOrigin(origin);
            System.out.println("Paso 1 realizado");

            //  Get text from 'País hacia donde va destino'
            //    Obtiene el viaje hacia colocado para futura validación.
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Campo de Destino. Toca dos veces para escoger la ciudad de destino de tu vuelo. Está seleccionado Miami (MIA)..");
            destiny = driver.findElement(by).getAttribute("name");
            od.setDestination(destiny);
            System.out.println("Paso 2 realizado");

            System.out.println("getOriginDestinationOtherEnhancement finalizado con éxito");
            return od;

        }
        catch(Exception ex){
            System.out.println("getOriginDestinationOtherEnhancement error");
            return null;
        }
    }

    /**
     * Recupera la información de origen y destino seleccionada cuando están vacíos
     *
     * @return Objeto con las propiedades para Origen y Destino
     */
    public OriginDestinationValiOS getOriginDestinationEmpty(){
        By by;
        OriginDestinationValiOS od= new OriginDestinationValiOS();
        String  origin, destiny;
        System.out.println("getOriginDestinationEmpty inicio");
        try{
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. .");
            origin = driver.findElement(by).getAttribute("name");
            od.setOrigin(String.valueOf(origin));
            System.out.println("Paso 1 realizado");

            //  Get text from 'País hacia donde va destino'
            //    Obtiene el viaje hacia colocado para futura validación.
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Hacia");
            destiny = driver.findElement(by).getAttribute("name");
            od.setDestination(String.valueOf(destiny));
            System.out.println("Paso 2 realizado");

            System.out.println("getOriginDestinationEmpty finalizado con éxito");
            return od;

        }
        catch(Exception ex){
            System.out.println("getOriginDestinationEmpty error");
            return null;
        }
    }

    /**
     * Para hacer click en cualquier objeto de booking que utilice TextView
     * @param textDescription Es la descripción visible del texto del objeto a hacer clic
     */
        public void clickTextViewXpath(String textDescription){
            By by;
            try
            {
                //    Click en tipo de viaje
                GeneratedUtilsiOS.sleep(1000);
                //by = By.xpath(String.format("//XCUIElementTypeStaticText[@name='%s']",textDescription));
                by = AppiumBy.accessibilityId(textDescription);
                driver.findElement(by).click();
                System.out.println("Click al texto del textview finalizado correctamente\n");
            }
            catch(Exception ex)
            {
                System.out.println("Click al texto del textview finalizado con error");
            }
        }

    /**
     * Para seleccionar el tipo de viaje
     * @param textDescription Es la descripción visible del texto del objeto a hacer clic
     */
    public void clickTypeOfTrip(String textDescription){
        By by;
        try
        {
            if(textDescription.equals("Ida y vuelta")){
                driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Ida y vuelta\"]")).click();
                System.out.println("Click al tipo de viaje Ida y vuelta finalizado con éxito\n");
            } else if (textDescription.equals("Solo ida")) {
                driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Solo ida\"]")).click();
                System.out.println("Click al tipo de viaje Sólo Ida finalizado con éxito\n");
            }else {
                driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Multiciudad / Stopover\"]")).click();
                System.out.println("Click al tipo de viaje Sólo Ida finalizado con éxito\n");
            }

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
            GeneratedUtilsiOS.sleep(1000);
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
     */
    public void compareOriginDestination(OriginDestinationValiOS od, String origin, String destination, ReportiOS report){
        By by;
        try
        {
            System.out.println("\ncompareOriginDestination iniciado");
            //    Valida que se mantuvo el vuelo "origen" al cambiar el tipo de viaje.
            System.out.println(od.getDestination());
            System.out.println(destination);
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. Está seleccionado Panamá (PTY)..");
            if(od.getOrigin().equals(origin)){
                System.out.println("Paso 1 realizado, origen se mantuvo igual");
                report.testPassed("Valida que se mantuvo el campo origen igual", true);
            }else {
                System.out.println("Paso 1 realizado, origen no se mantuvo igual");
                report.testPassed("Valida que NO se mantuvo el campo origen igual", true);
            }

            //    Valida que se mantuvo el vuelo "destino" al cambiar el tipo de viaje.
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Campo de Destino. Toca dos veces para escoger la ciudad de destino de tu vuelo. Está seleccionado Miami (MIA)..");
            if(od.getDestination().equals(destination)){
                System.out.println("Paso 2 realizado, destino se mantuvo igual");
                report.testPassed("Valida que se mantuvo el campo destino igual", true);
            }else {
                System.out.println("Paso 2 realizado, destino no se mantuvo igual");
                report.testPassed("Valida que NO se mantuvo el campo destino igual", true);
            }
            System.out.println("compareOriginDestination finalizado con éxito\n");
        }
        catch(Exception ex)
        {
            System.out.println("compareOriginDestination error\n");
        }
    }

    /**
     * Hace la comparación de los campos de origen y destino para las validaciones del escenarios Other Enhancement
     * @param od contiene el origen y el destino
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void compareOriginDestinationOtherEnhancement(OriginDestinationValiOS od, ReportiOS report){
        By by;
        String origen, destino;
        try
        {
            System.out.println("\ncompareOriginDestinationOtherEnhancement iniciado");
            //    Valida que se mantuvo el vuelo "origen" al cambiar el tipo de viaje.
            GeneratedUtilsiOS.sleep(1000);
            origen = driver.findElement(AppiumBy.accessibilityId("Campo de Origen. Toca dos veces para escoger la ciudad de origen de tu vuelo. Está seleccionado Bogotá (BOG)..")).getAttribute("name");
            if(od.getOrigin().equals(origen)){
                System.out.println("Paso 1 realizado, origen se mantuvo igual");
                report.testPassed("Valida que se mantuvo el campo origen igual", true);
            }else {
                System.out.println("Paso 1 realizado, origen no se mantuvo igual");
                report.testPassed("Valida que NO se mantuvo el campo origen igual", true);
            }

            //    Valida que se mantuvo el vuelo "destino" al cambiar el tipo de viaje.
            GeneratedUtilsiOS.sleep(1000);
            destino = driver.findElement(AppiumBy.accessibilityId("Campo de Destino. Toca dos veces para escoger la ciudad de destino de tu vuelo. Está seleccionado Miami (MIA)..")).getAttribute("name");
            if(od.getDestination().equals(destino)){
                System.out.println("Paso 2 realizado, destino se mantuvo igual");
                report.testPassed("Valida que se mantuvo el campo destino igual", true);
            }else {
                System.out.println("Paso 2 realizado, destino no se mantuvo igual");
                report.testPassed("Valida que NO se mantuvo el campo destino igual", true);
            }
            System.out.println("compareOriginDestinationOtherEnhancement finalizado con éxito\n");
        }
        catch(Exception ex)
        {
            System.out.println("error "+ ex);
            System.out.println("compareOriginDestinationOtherEnhancement error\n");
        }
    }

    /**
     * Valida que si está presente el check de stopover
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */

    public void validateCheckStopOver(ReportiOS report){
        By by;
        String activate;
        try {
            by = AppiumBy.className("XCUIElementTypeSwitch");
            activate = driver.findElement(by).getAttribute("name");
            if (activate.equals("Selector de Stopover Panamá Inactivo. Toca dos veces para activar Stopover en Panamá.")) {
                System.out.println("El Stopover está presente y desactivado");
                report.testPassed("Valida que el Stopover esté presente y desactivado", true);
            } else if (activate.equals("Stopover Panamá. Activo. Toca dos veces para remover el stopover de tu búsqueda.")){
                System.out.println("El stopover está presente y activado");
                report.testPassed("Valida que el Stopover esté presente y desactivado", true);
            }
            System.out.println("validateCheckStopOver finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateCheckStopOver");
            report.testFailed("Valida que el Stopover esté presente", true);
        }

    }
    /**
     * Valida que la opción de mostrar precio en millas este deshabilitada
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateCheckBoxPriceInMilesDisabled(ReportiOS report){
        By by;
        String accessible;
        try {
            by = AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]");
            accessible = driver.findElement(by).getAttribute("accessible");
            if (accessible.equals("false")) {
                System.out.println("CheckBox precio en millas desactivado");
                report.testPassed("Valida que el check precio en millas esté desactivado", true);
            } else {
                System.out.println("CheckBox precio en millas activado");
                report.testFailed("Valida que el check precio en millas esté activado\"", true);
            }
            System.out.println("validateCheckBoxPriceInMiles finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateCheckBoxPriceInMiles");
            report.testFailed("Valida que el Stopover esté presente", true);
        }

    }
    /**
     * Valida que la opción de mostrar precio en millas este habilitada
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateCheckBoxPriceInMilesEnabled(ReportiOS report){
        By by;
        String accessible;
        try {
            by = AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]");
            accessible = driver.findElement(by).getAttribute("accessible");
            if (accessible.equals("false")) {
                System.out.println("CheckBox precio en millas desactivado");
                report.testPassed("Valida que el check precio en millas esté desactivado", true);
            } else {
                System.out.println("CheckBox precio en millas activado");
                report.testFailed("Valida que el check precio en millas esté activado\"", true);
            }
            System.out.println("validateCheckBoxPriceInMilesEnabled finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateCheckBoxPriceInMilesEnabled");
            report.testFailed("Valida que el Stopover esté presente", true);
        }

    }

    /**
     * Entrar a Calendario para vuelo de ida y vuelta con origen y destino seleccionados
     */
    public void enterCalendarRoundTripWithOriginDestinyAdded(){
        By by;
        try{
            //  Click al calendario
            Thread.sleep(3000);
            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[5]");
            driver.findElement(by).click();
            System.out.println("enterCalendarRoundTripWithOriginDestinyAdded finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("enterCalendarRoundTripWithOriginDestinyAdded Error");
        }
    }

    /**
     * Entrar al calendario con stopover en la ida de un vuelo multiciudad
     * @param tramo Parámetro para indicar en cual de los 3 tramos del vuelo se seleccionará el día
     */
    public void enterCalendarMulticityWithStopoverOnMyGo(String tramo){
        By by;
        try{
            if(tramo.equals("primero")) {
                //  Click al calendario
                Thread.sleep(3000);
                GeneratedUtilsiOS.sleep(1000);
                by = By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton");
                driver.findElement(by).click();
                System.out.println("enterCalendarMulticityWithStopoverOnMyGo finalizado con éxito");
            } else if (tramo.equals("segundo")) {
                //  Click al calendario
                Thread.sleep(3000);
                GeneratedUtilsiOS.sleep(1000);
                by = By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton");
                driver.findElement(by).click();
                System.out.println("enterCalendarMulticityWithStopoverOnMyGo finalizado con éxito");
            }else {
                //  Click al calendario
                Thread.sleep(3000);
                GeneratedUtilsiOS.sleep(1000);
                by = By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton");
                driver.findElement(by).click();
                System.out.println("enterCalendarMulticityWithStopoverOnMyGo finalizado con éxito");
            }
        }
        catch(Exception ex){
            System.out.println("enterCalendarMulticityWithStopoverOnMyGo Error");
        }
    }

    /**
     * Click para buscar un vuelo
     */
    public void clickFindSingleFlight(){
        By by;
        try{
            // 17. Click Botón 'Buscar vuelo'
            Thread.sleep(500);
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Buscar vuelos");

            driver.findElement(by).click();

            //confirma búsqueda
            //GeneratedUtilsiOS.sleep(1000);
            //by = AppiumBy.accessibilityId("Buscar vuelos. Toca dos veces para realizar la búsqueda de vuelos.");
            //driver.findElement(by).click();

            System.out.println("clickFindFlight finalizado con éxito");
        }
        catch(Exception ex){
            System.out.println("clickFindFlight Error");
        }
    }

    /**
     * Busca vuelo cuando NO es una búsqueda sencilla
     */
    public void clickFindNOSingleFlight(){
        By by;
        try{
            // 17. Click Botón 'Buscar vuelo'
            Thread.sleep(500);
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Buscar vuelos");

            driver.findElement(by).click();

            //confirma búsqueda
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Buscar vuelos. Toca dos veces para realizar la búsqueda de vuelos.");
            driver.findElement(by).click();

            System.out.println("clickFindFlight finalizado con éxito");
        }
        catch(Exception ex){
            System.out.println("clickFindFlight Error");
        }
    }
    /**
     * Hace click en el check de stopover
     */
    public void clickCheckStopOver(){
        By by;
        String activate;
        try {
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.className("XCUIElementTypeSwitch");
            activate = driver.findElement(by).getAttribute("name");
            if (activate.equals("Selector de Stopover Panamá Inactivo. Toca dos veces para activar Stopover en Panamá.")) {
                driver.findElement(by).click();
            } else if (activate.equals("Stopover Panamá. Activo. Toca dos veces para remover el stopover de tu búsqueda.")){
                System.out.println("El stopover está presente y activado");
                driver.findElement(by).click();
            }

            System.out.println("clickCheckStopOver finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error clickCheckStopOver\n");
        }

    }
    /**
     * Valida que esté presente el stopover en la ida o regreso
     * @param stopover Indica si el stopover está presente en la ida o en el regreso
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateStopOver(String stopover, ReportiOS report) {
        By by;
        try {
            if (stopover.equals("En mi ida")) {
                GeneratedUtilsiOS.sleep(1000);
                by = AppiumBy.accessibilityId("Selecciona para hacer la parada en Panamá en el vuelo de ida.");
                driver.findElement(by);
                System.out.println("El StopOver En mi IDA está presente");
                report.testPassed("Valida que el Stopover en mi ida esté presente", true);
        } else {
                if(stopover.equals("En mi regreso")){
                    GeneratedUtilsiOS.sleep(1000);
                    by = AppiumBy.accessibilityId("Selecciona para hacer la parada en Panamá en el vuelo de regreso.");
                    driver.findElement(by);
                    System.out.println("El StopOver En mi REGRESO está presente");
                    report.testPassed("Valida que el Stopover en mi regreso esté presente", true);
                }
            }
            System.out.println("validateStopOver finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validateStopOver");
            report.testFailed("Valida que el Stopover en mi ida y regreso estén presente", true);
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
                GeneratedUtilsiOS.sleep(1000);
                by = AppiumBy.accessibilityId("Selecciona para hacer la parada en Panamá en el vuelo de ida.");
                driver.findElement(by).click();
            } else {
                if(stopover.equals("En mi regreso")){
                    GeneratedUtilsiOS.sleep(1000);
                    by = AppiumBy.accessibilityId("Selecciona para hacer la parada en Panamá en el vuelo de regreso.");
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
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validatePanamaStopoverDefault(String field, ReportiOS report) {
        By by;
        try {
            if (field.equals("destino")) {
                GeneratedUtilsiOS.sleep(1000);
                by = AppiumBy.accessibilityId("Campo de Destino de vuelo 1. Toca dos veces para escoger la ciudad de destino. Está seleccionado Panamá (PTY).");
                Assertions.assertTrue(driver.findElement(by).getAttribute("name").contains("Campo de Destino de vuelo 1. Toca dos veces para escoger la ciudad de destino. Está seleccionado Panamá (PTY)."));
                System.out.println("El Destino tiene Panamá por defecto");
                report.testPassed("Valida que esté presente Panamá por defecto en el destino del primer tramo", true);
            } else {
                if(field.equals("origen")){
                    GeneratedUtilsiOS.sleep(1000);
                    by = AppiumBy.accessibilityId("Campo de Origen de vuelo 2. Toca dos veces para escoger la ciudad de origen. Está seleccionado Panamá (PTY).");
                    Assertions.assertTrue(driver.findElement(by).getAttribute("name").contains("Campo de Origen de vuelo 2. Toca dos veces para escoger la ciudad de origen. Está seleccionado Panamá (PTY)."));
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
     * Valida que los campos al tener stopover activado tengan Panamá por defecto
     * @param field Indica el campo que se va a validar
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validatePanamaStopoverDefaultOnReturn(String field, ReportiOS report) {
        By by;
        try {
            //valida destino panamá en el segundo segmento
            if (field.equals("destino")) {
                GeneratedUtilsiOS.sleep(1000);
                by = AppiumBy.accessibilityId("Campo de Destino de vuelo 2. Toca dos veces para escoger la ciudad de destino. Está seleccionado Panamá (PTY).");
                Assertions.assertTrue(driver.findElement(by).getAttribute("name").contains("Campo de Destino de vuelo 2. Toca dos veces para escoger la ciudad de destino. Está seleccionado Panamá (PTY)."));
                System.out.println("El Destino tiene Panamá por defecto");
                report.testPassed("Valida que esté presente Panamá por defecto en el destino del segundo tramo", true);
            } else {
                //valida origen panamá en el tercer segmento
                if(field.equals("origen")){
                    GeneratedUtilsiOS.sleep(1000);
                    by = AppiumBy.accessibilityId("Campo de Origen de vuelo 3. Toca dos veces para escoger la ciudad de origen. Está seleccionado Panamá (PTY).");
                    Assertions.assertTrue(driver.findElement(by).getAttribute("name").contains("Campo de Origen de vuelo 3. Toca dos veces para escoger la ciudad de origen. Está seleccionado Panamá (PTY)."));
                    System.out.println("El Origen tiene Panamá por defecto");
                    report.testPassed("Valida que esté presente Panamá por defecto en el origen del tercer tramo", true);
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
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateDatesNoApplyForStopOver(ReportiOS report) {
        By by;
        try {
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Las fechas seleccionadas no aplican para Stopover");
            driver.findElement(by);
            report.testPassed("Valida que esté presente el mensaje de las fechas seleccionadas no aplican para stopover", true);
            System.out.println("validateDatesNoApplyForStopOver finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error validateDatesNoApplyForStopOver\n");
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

                int Anchor = Panel.getSize().getHeight()/2;

                Double ScreenHeightStart = dimension.getHeight() * 0.8;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.2;
                int scrollEnd = ScreenHeightEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(Anchor, scrollStart))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(Anchor, scrollEnd))
                        .release().perform();

            }else{
                if(direction.equals("arriba")){
                    WebElement Panel = el;
                    Dimension dimension = Panel.getSize();

                    int Anchor = Panel.getSize().getHeight()/2;

                    Double ScreenHeightStart = dimension.getHeight() * 0.2;
                    int scrollStart = ScreenHeightStart.intValue();

                    Double ScreenHeightEnd = dimension.getHeight() * 0.8;
                    int scrollEnd = ScreenHeightEnd.intValue();

                    new TouchAction((PerformsTouchActions) driver)
                            .press(PointOption.point(Anchor, scrollStart))
                            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                            .moveTo(PointOption.point(Anchor, scrollEnd))
                            .release().perform();
                            //Thread.sleep(3000);
                }
            }
            Thread.sleep(2000);
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

                int Anchor = Panel.getSize().getHeight()/2;

                Double ScreenHeightStart = dimension.getHeight() * 0.8;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.5;
                int scrollEnd = ScreenHeightEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(Anchor, scrollStart))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(Anchor, scrollEnd))
                        .release().perform();


            }else{
                if(direction.equals("arriba")){
                    WebElement Panel = el;
                    Dimension dimension = Panel.getSize();

                    int Anchor = Panel.getSize().getHeight()/2;

                    Double ScreenHeightStart = dimension.getHeight() * 0.2;
                    int scrollStart = ScreenHeightStart.intValue();

                    Double ScreenHeightEnd = dimension.getHeight() * 0.5;
                    int scrollEnd = ScreenHeightEnd.intValue();

                    new TouchAction((PerformsTouchActions) driver)
                            .press(PointOption.point(Anchor, scrollStart))
                            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                            .moveTo(PointOption.point(Anchor, scrollEnd))
                            .release().perform();

                }
            }
            Thread.sleep(1000);
            System.out.println("swipeSmall finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("Error swipeSmall\n");
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

                int Anchor = Panel.getSize().getHeight()/2;

                Double ScreenHeightStart = dimension.getHeight() * 0.8;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.6;
                int scrollEnd = ScreenHeightEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(Anchor, scrollStart))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(Anchor, scrollEnd))
                        .release().perform();


            }else{
                if(direction.equals("arriba")){
                    WebElement Panel = el;
                    Dimension dimension = Panel.getSize();

                    int Anchor = Panel.getSize().getHeight()/2;

                    Double ScreenHeightStart = dimension.getHeight() * 0.2;
                    int scrollStart = ScreenHeightStart.intValue();

                    Double ScreenHeightEnd = dimension.getHeight() * 0.6;
                    int scrollEnd = ScreenHeightEnd.intValue();

                    new TouchAction((PerformsTouchActions) driver)
                            .press(PointOption.point(Anchor, scrollStart))
                            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
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
     * Selecciona los días del calendario
     * @param day1 Primer día a seleccionar del calendario
     * @param day2 Segundo día a seleccionar del calendario
     */
    public void selectCalendarDays(String day1, String day2){
        By by;
        String direct2 = "abajo";
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            //Swipe al mes que sigue
            tif.swipeFormTIF(Panel, driver, direct2);
            swipeSuperSmall(Panel, driver, direct2);
            GeneratedUtilsiOS.sleep(1000);

            //selecciona el día 1 del calendario
            by = By.xpath(String.format("//XCUIElementTypeStaticText[@name='%s. Toca dos veces para seleccionar este día.']",day1));
            driver.findElement(by).click();

            //selecciona el día 2 del calendario
            by = By.xpath(String.format("//XCUIElementTypeStaticText[@name='%s. Toca dos veces para seleccionar este día.']",day2));
            driver.findElement(by).click();

            //click botón confirmar fecha
            driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Confirmar Fechas\"]")).click();

            Thread.sleep(1000);

            System.out.println("selectCalendarDays finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("Error selectCalendarDays");
        }
    }
    /**
     * Selecciona los días en el calendario cuando hay stopover
     * @param day Parámetro para seleccionar el día en el calendario
     * @param tramo Parámetro para indicar en cual de los 3 tramos del vuelo se seleccionará el día
     */
    public void selectCalendarDayStopover(String day, String tramo){
        By by;
        String direct2 = "abajo";
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            //Swipe al mes que sigue
            if(tramo.equals("primero")) {
                tif.swipeFormTIF(Panel, driver, direct2);
                swipeSuperSmall(Panel, driver, direct2);
                GeneratedUtilsiOS.sleep(1000);
            }

            //selecciona el día del calendario
            by = By.xpath(String.format("//XCUIElementTypeStaticText[@name='%s. Toca dos veces para seleccionar este día.']",day));
            driver.findElement(by).click();

            //click botón confirmar fecha
            driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Confirmar Fecha\"]")).click();

            Thread.sleep(1000);

            System.out.println("swipeCalendar selectCalendarDayStopover");
        }catch(Exception ex){
            System.out.println("Error selectCalendarDayStopover");
        }
    }
    /**
     * click al calendario (no utilizado)
     */
    public void clickCalenardar(){
        By by;
        try {
            GeneratedUtilsiOS.sleep(1000);
            by = By.id("com.copaair.copaAirlines.dev:id/selector_payment");
            driver.findElement(by).click();
            System.out.println("clickCheckPriceInMiles finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error clickCheckPriceInMiles");
        }
    }

    /**
     * Selecciona la opción de mostrar precio en millas
     */
    public void clickCheckPriceInMiles(){
        By by;
        try {
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Haz doble toque para mostrar precios en millas");
            driver.findElement(by).click();
            System.out.println("clickCheckPriceInMiles finalizado con éxito\n");
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
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Agregar código promocional");
            driver.findElement(by).click();
            System.out.println("clickPromCode finalizado con éxito\n");
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
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Ingrese código\"`]");
            driver.findElement(by).sendKeys(Promcode);

            System.out.println("writePromCode finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error writePromCode");
        }
    }
    /**
     * selecciona la opción de añadir promcode
     */
    public void addPromCode(){
        By by;
        try {
            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Agregar\"]");
            driver.findElement(by).click();
            System.out.println("addPromCode finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error addPromCode");
        }
    }
    /**
     * Hace las validaciones de promcode T78
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validatePromCodeT78(ReportiOS report){
        By by;
        String fieldPromCode;
        try{
            by = AppiumBy.accessibilityId("Agregar código promocional");
            fieldPromCode = driver.findElement(by).getAttribute("name");
            if(fieldPromCode.equals("Agregar código promocional")){
                System.out.println("El campo de promcode está limpio");
                report.testPassed("Valida que el campo código promocional se limpie al activar la opción mostrar precio en miilas", true);
            }else{
                System.out.println("El campo de promcode no está limpio");
                report.testFailed("Valida que el campo código promocional se limpie al activar la opción mostrar precio en miilas", true);
            }
            System.out.println("validatePromCode finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error validatePromCode");
        }
    }
    /**
     * Hace las validaciones de promcode T79
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validatePromCodeT79(ReportiOS report){
        By by;
        String fieldPromCode;
        try{
            by = AppiumBy.accessibilityId("Agregar código promocional");
            fieldPromCode = driver.findElement(by).getAttribute("name");
            if(fieldPromCode.equals("Agregar código promocional")){
                System.out.println("El campo de promcode está limpio");
                report.testPassed("Valida que el campo código promocional se limpie al desactivar la opción mostrar precio en miilas", true);
            }else{
                System.out.println("El campo de promcode no está limpio");
                report.testFailed("Valida que el campo código promocional se limpie al desactivar la opción mostrar precio en miilas", true);
            }
            System.out.println("validatePromCode finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error validatePromCode");
        }
    }

    /**
     * Valida el look and field de promcode
     */
    public void validateLookAndFieldPromCode(ReportiOS report){
        By by, by2, by3, by4;
        try {
            GeneratedUtilsiOS.sleep(1000);
            //valida que se muestre el título de código promocional
            by = AppiumBy.accessibilityId("Código Promocional");
            driver.findElement(by);
            //valida que se muestre la opción de cancelar
            by2 = By.xpath("//XCUIElementTypeStaticText[@name=\"Cancelar\"]");
            driver.findElement(by2);
            //valida que se muestre el campo del código promocional
            by3 = AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Ingrese código\"`]");
            driver.findElement(by3);
            //valida que se muestre el botón de agregar
            by4 = By.xpath("//XCUIElementTypeButton[@name=\"Agregar\"]");
            driver.findElement(by4);

            report.testPassed("Valida a nivel de look and feel código promocional", true);

            System.out.println("validateLookAndFieldPromCode finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error validateLookAndFieldPromCode");
        }
    }

    /**
     * Cierra el promcode
     */
    public void closePromcode(){
        By by;
        try{

            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"Cancelar\"]");
            driver.findElement(by).click();
            System.out.println("closePromcode finalizado con éxito\n");
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

            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"Eliminar\"]");
            driver.findElement(by).click();

            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Eliminar código");
            driver.findElement(by).click();
            System.out.println("eliminatePromcode finalizado con éxito\n");
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
            GeneratedUtilsiOS.sleep(1000);
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(359,95)).release().perform();

            /*GeneratedUtils.sleep(1000);
            by = By.id("btnMembersLoginBox");
            driver.findElement(by).click();*/
            System.out.println("clickLogedIconCMWebViewfinalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("clickLogedIconCMWebView Error "+ex);
        }
    }

    /**
     * Click en el ícono de la cuenta connectmiles en el webview de IBE cuando no se está logueado
     */
    public void clickUnlogedIconCMWebView(){
        By by;
        try{
            GeneratedUtilsiOS.sleep(1000);
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(359,95)).release().perform();

            /*GeneratedUtils.sleep(1000);
            by = By.id("btnLoginBox");
            driver.findElement(by).click();*/
            System.out.println("clickUnlogedIconCMWebView finalizado con éxito");
        }catch(Exception ex){
            System.out.println("clickUnlogedIconCMWebView Error");
        }
    }

    /**
     * valida la cuenta connectmiles en el webview de IBE
     * @param User_ConnectMiles Usuario de la cuenta ConnectMiles
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateAccountCMWebView(String User_ConnectMiles, ReportiOS report){
        By by;
        try{
            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath(String.format("//XCUIElementTypeStaticText[@name= '%s']",User_ConnectMiles));
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
            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Boton de cerrar la sesión de tu cuenta de ConnectMiles, presiona enter para cerrar la sesión de tu cuenta de ConnectMiles\"]");
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
            GeneratedUtilsiOS.sleep(2000);
            driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            by = AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Account\"]");
            driver.findElement(by).click();

            System.out.println("clickAccountIcon finalizado con éxito\n");
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
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Toca dos veces para conocer más acerca del programa Stopover en Panamá.");
            driver.findElement(by).click();
            System.out.println("clickToggleStopover finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("toggleStopover Error\n");
        }
    }

    /**
     * Cierra el toggle de stopover
     */
    public void closeToggleStopover(){
        By by;
        try{
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Cerrar");
            driver.findElement(by).click();
            System.out.println("closeToggleStopover finalizado con éxito");
        }catch(Exception ex){
            System.out.println("closeToggleStopover Error");
        }
    }

    /**
     * Valida el toggle de stopover
     */
    public void validateToggleStopover(ReportiOS report){
        By by;
        String message, messageStopOver;
        try{
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Panamá Stopover: Es poder disfrutar de una parada en Panamá, en tu vuelo de ida o retorno, sin costo adicional en tu tarifa aérea. El Stopover puede ser desde 24 horas hasta 6 noches/7 días. Aplican términos y condiciones.");
            message = driver.findElement(by).getAttribute("name");
            messageStopOver = String.valueOf(message);
            if(messageStopOver.equals("Panamá Stopover: Es poder disfrutar de una parada en Panamá, en tu vuelo de ida o retorno, sin costo adicional en tu tarifa aérea. El Stopover puede ser desde 24 horas hasta 6 noches/7 días. Aplican términos y condiciones.")){
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
        try {
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Ajustar fechas. Toca dos veces para regresar a la selección de fechas.");
            driver.findElement(by).click();
            System.out.println("clickAdjustDatesStopOver finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error clickAdjustDatesStopOver\n");
        }

    }

    /**
     * Click en cerrar calendario
     */
    public void clickCloseCalendar() {
        By by;
        try {
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Cerrar calendario");
            driver.findElement(by).click();
            System.out.println("clickCloseCalendar finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error clickCloseCalendar\n");
        }

    }

    /**
     * Click en el ícono "X" en el webview de IBE
     */
    public void clickXOnWebView() {
        try {
            GeneratedUtilsiOS.sleep(1000);
            driver.findElement(AppiumBy.accessibilityId("Cerrar")).click();
            System.out.println("clickXOnWebView finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error clickXOnWebView");
        }

    }

    /**
     * Click en YES en el webview de IBE
     */
    public void clickYesOnWebView() {
        By by;
        try {
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Sí");
            driver.findElement(by).click();
            System.out.println("clickYesOnWebView finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error clickYesOnWebView");
        }

    }

    /**
     * Valida los elementos en la alerta de cerrar página en el webview
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateClosePageOnWebView(ReportiOS report) {
        By by, by2, by3, by4;
        try {
            GeneratedUtilsiOS.sleep(1000);
            //valida el mensaje de si va salir
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"¿Deseas salir de esta página?\"]");
            driver.findElement(by);
            //valida el texto del mensaje de que si sale perdera la busqueda
            by2 = AppiumBy.accessibilityId("Esta acción borrará tu búsqueda. Cualquier selección de vuelos que hayas hecho se perderá.");
            driver.findElement(by2);
            //Valida botón NO
            by3 = AppiumBy.accessibilityId("No");
            driver.findElement(by3);
            //Valida botón SI
            by4 = AppiumBy.accessibilityId("Sí");;
            driver.findElement(by4);

            report.testPassed("Valida modal de aviso de que perderá su búsqueda y selección", true);

            System.out.println("validateClosePageOnWebView finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error validateClosePageOnWebView\n");
            report.testFailed("Valida modal de aviso de que perderá su búsqueda y selección", true);
        }

    }
    /**
     * Valida el selector de días cuando hay un stopover en brasil
     */
    public void validateDaySelectorOnBrazilStopOver(ReportiOS report) {
        By by;
        try {
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("¿Cuántas noches deseas estar en Panamá?");
            report.testPassed("Valida el selector de días de Stopover que aparece en Roundtrip", true);
            driver.findElement(by);

            System.out.println("validateDaySelectorOnBrazilStopOver finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("Error validateDaySelectorOnBrazilStopOver\n");
        }

    }

    /**
     * Validaciones de aerolínea preferida
     */
    public void validatePreferedAirlines(ReportiOS report) {
        By by;
        try {
            //    Valida si está presente el campo de selección de aerolínea y si está presente el texto de Copa Airlines
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Selecciona tu aerolínea preferida. Continua con Copa Airlines o haz doble toque para abrir el selector");
            Assertions.assertTrue(driver.findElement(by).getText().contains("Selecciona tu aerolínea preferida. Continua con Copa Airlines o haz doble toque para abrir el selector"));
            report.testPassed("Valida si está presente el campo de Aerolínea preferida y el texto Copa Airlines", true);

            //    Click para elegir la aerolínea.
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[6]");
            driver.findElement(by).click();

            //    Valida que se muestre el texto de Copa Airlines.
            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"Copa Airlines\"]");
            driver.findElement(by);

            //    Valida que se muestre el texto de Todas las aerolíneas.
            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeOther[@name=\"Busca tu aerolínea preferida o escucha todas las opciones.\"]");
            driver.findElement(by);

            report.testPassed("Valida si está presente el texto todas las aerolíneas y el texto Copa Airlines", true);

            // Click 'Atrás1'
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Cerrar");
            driver.findElement(by).click();

            System.out.println("validatePreferedAirlines finalizado con éxito");
        }catch(Exception ex) {
            System.out.println("Error validatePreferedAirlines");
        }

    }

    /**
     * Valida el filtro de todos los Aeropuertos
     */
    public void validateAllAirportsFilter(ReportiOS report) {
        By by;
        try {
            // 1. Type 'Cop' in 'Busca tu aerolínea preferida'
            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeOther[@name=\"Busca tu aerolínea preferida o escucha todas las opciones.\"]");
            driver.findElement(by).sendKeys("Cop");

            // 2. Is 'Aerolínea Copa Airlines' present?
            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"Copa Airlines\"]");
            driver.findElement(by);

            report.testPassed("Valida que haga el filtro al hacer la búsqueda de un aeropuerto", true);

            // 3. Click 'Atrás1'
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Cerrar");
            driver.findElement(by).click();

            System.out.println("validateAllAirportsFilter finalizado con éxito\n");
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
        try {

            // 1. Click 'Aerolínea preferida'
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[6]");
            driver.findElement(by).click();

            // 2. Click 'Texto todas las aerolíneas'
            GeneratedUtilsiOS.sleep(1000);
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"Todas las aerolíneas\"]");
            driver.findElement(by).click();

            System.out.println("clickTextAllAirlines finalizado con éxito\n");
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
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.accessibilityId("Selecciona tu aerolínea preferida. Continua con Copa Airlines o haz doble toque para abrir el selector");
            string = driver.findElement(by).getAttribute("name");
            airline = String.valueOf(string);
            //System.out.println("La variable es: "+airline);
            System.out.println("getTextPreferedAirlines finalizado con éxito\n");

        }catch(Exception ex) {
            System.out.println("Error getTextPreferedAirlines");
        }
        return airline;
    }

    /**
     * Valida que esté presente Copa Airlines por defecto
     * @param air contiene el texto de todas la aerolíneas para comparar y realizar la validación
     */
    public void validateDefaultPreferedAirlines(String air, ReportiOS report) {
        By by;
        String string;
        try {
            GeneratedUtilsiOS.sleep(1000);
            if(air.equals("Selecciona tu aerolínea preferida. Continua con Copa Airlines o haz doble toque para abrir el selector")) {

                System.out.println("validateDefaultPreferedAirlines finalizado con éxito\n");
                report.testPassed("Valida que el campo aerolínea preferida regresa a su valor por defecto", true);
            }else{
                System.out.println("Error validateDefaultPreferedAirlines\n");
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
            GeneratedUtilsiOS.sleep(1000);
            by = AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[6]");
            driver.findElement(by).click();
            System.out.println("clickPreferedAirlines finalizado con éxito\n");
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
                GeneratedUtilsiOS.sleep(1000);
                driver.quit();
                //driver.closeApp();
                System.out.println("Paso 28 realizado");

                // 29. Launch the app
                //    Inicia el app.
                GeneratedUtilsiOS.sleep(1000);
                AppiumDriver driver = DriveriOS.getiOSDriver();
                ReportiOS report = new ReportiOS(driver);
                FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
                firstSteps.skipFirstSteps();
                //driver.launchApp();
                System.out.println("Paso 29 realizado");

                // 30. Pause for '4000'ms
                //    Pausa por carga.
                GeneratedUtilsiOS.sleep(4000);
                driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
                System.out.println("Paso 30 realizado");

                // 31. Click ' Ícono Reverva'
                //    Clic al ícono de reserva.
                GeneratedUtilsiOS.sleep(1000);
                by = By.id("com.copaair.copaAirlines.dev:id/bookingPanelFragment");
                driver.findElement(by).click();
                System.out.println("Paso 31 realizado");

                // 32. Pause for '3000'ms
                //    Pausa por carga.
                GeneratedUtilsiOS.sleep(3000);
                driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
                System.out.println("Paso 32 realizado");

                // 33. Does 'País desde dónde sale' contain '[NONE]'?
                //    Valida que el contenido se borró al cerrar el app y volver a entrar.
                GeneratedUtilsiOS.sleep(1000);
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
                GeneratedUtilsiOS.sleep(1000);
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
                GeneratedUtilsiOS.sleep(1000);
                by = By.id("com.copaair.copaAirlines.dev:id/homeFragment");
                driver.findElement(by).click();
                System.out.println("Paso 35 realizado");

        } catch (Exception ex) {
            System.out.println("Error");
            System.out.println(ex.getMessage());
        }
    }
}

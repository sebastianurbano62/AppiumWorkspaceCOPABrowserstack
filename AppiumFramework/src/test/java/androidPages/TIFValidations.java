package androidPages;

import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import utils.GeneratedUtils;
import utils.Report;

import java.awt.*;
import java.time.Duration;

public class TIFValidations {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public TIFValidations(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);
    Booking booking = new Booking(driver);


    /**
     * Click al primer viaje agreado
     */
    public void clickFirstTripAdded(){
        By by;
        try{
            // Click al primer viaje agregado
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/destination");
            driver.findElement(by).click();
            Thread.sleep(1000);

            System.out.println("clickFirstTripAdded finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("clickFirstTripAdded finalizado con error\n");
        }
    }

    /**
     * Click En editar información en TIF
     */
    public void clickEditInformation(String pasajero){
        By by;
        String opcion = "";
        String nombre1, nombre2, nombre3;
        String direct2 = "abajo";

        Booking booking = new Booking(driver);

        WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));


        nombre1 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@index=\"3\"]")).getAttribute("text");

        nombre2 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@index=\"10\"]")).getAttribute("text");

        if(nombre1.equals(pasajero)){
            opcion = "primero";
        } else if (nombre2.equals(pasajero)) {
            opcion = "segundo";
        }

        try{
            switch (opcion) {
                case "primero": // Click editar información del pasajero adulto
                              GeneratedUtils.sleep(500);
                              by = By.xpath("(//android.widget.TextView[@text=\"Editar información\"])[1]");
                              driver.findElement(by).click();
                              Thread.sleep(7000);
                              break;
                case "segundo": // Click editar información del pasajero adulto
                              GeneratedUtils.sleep(500);
                              by = By.xpath("(//android.widget.TextView[@text=\"Editar información\"])[2]");
                              driver.findElement(by).click();
                              Thread.sleep(7000);
                              break;
                case "tercero": //Swipe para ubicar el elemento
                                //booking.swipeSmall(Panel, driver, direct2);
                                // Click editar información del pasajero adulto
                               GeneratedUtils.sleep(500);
                               by = By.xpath("(//android.widget.TextView[@text=\"Editar información\"])[3]");
                               driver.findElement(by).click();
                               Thread.sleep(7000);
            }

            System.out.println("clickEditInformation finalizado con éxito\n");

        }catch(Exception ex){
            System.out.println("clickEditInformation finalizado con error\n");
        }
    }

    /**
     * Swipe utilizado para visualizar los pasajeros en trip details y también en otras validaciones
     * @param el Elemento al que se le hará swipe en pantalla
     * @param driver Driver necesario para hacer swipe
     * @param direction dirección el la que se hace el swipe
     */
    public void swipeToAdultChildInfant(WebElement el, WebDriver driver, String direction){
        By by;
        try{
            int screenWidth = driver.manage().window().getSize().getWidth();
            int screenHeight = driver.manage().window().getSize().getHeight();

            // Define swipe coordinates
            int startSwipeX = screenWidth / 2; // Middle of the screen horizontally
            int startSwipeY = (int) (screenHeight * 0.8); // 80% down from the top
            int endSwipeX = screenWidth / 2; // Middle of the screen horizontally
            int endSwipeY = (int) (screenHeight * 0.545); // 54.5% up from the bottom

            // Create a TouchAction object to perform the swipe
            TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
            touchAction.press(PointOption.point(startSwipeX, startSwipeY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))) // Adjust as needed
                    .moveTo(PointOption.point(endSwipeX, endSwipeY))
                    .release()
                    .perform();

            Thread.sleep(1000);
            /*if(direction.equals("abajo")){
                WebElement Panel = el;
                Dimension dimension = Panel.getSize();

                int Anchor = Panel.getSize().getWidth()/2;

                Double ScreenHeightStart = dimension.getHeight() * 0.7;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.42;
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

                    Double ScreenHeightStart = dimension.getHeight() * 0.44;
                    int scrollStart = ScreenHeightStart.intValue();

                    Double ScreenHeightEnd = dimension.getHeight() * 0.7;
                    int scrollEnd = ScreenHeightEnd.intValue();

                    new TouchAction((PerformsTouchActions) driver)
                            .press(PointOption.point(Anchor, scrollStart))
                            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                            .moveTo(PointOption.point(Anchor, scrollEnd))
                            .release().perform();
                    //Thread.sleep(3000);
                }
            }
            Thread.sleep(1000);*/
            System.out.println("swipeToAdultChildInfant finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("Error swipeToAdultChildInfant\n");
        }
    }

    /**
     * Swipe utilizado principalmente en los formularios de TIF pero también en otras validaciones
     * @param el Elemento al que se le hará swipe en pantalla
     * @param driver Driver necesario para hacer swipe
     * @param direction dirección el la que se hace el swipe
     */
    public void swipeFormTIF(WebElement el, WebDriver driver, String direction){
        By by;
        try{
            if(direction.equals("abajo")){
                WebElement Panel = el;
                Dimension dimension = Panel.getSize();

                int Anchor = Panel.getSize().getWidth()/2;

                Double ScreenHeightStart = dimension.getHeight() * 0.8;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.55;
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

                    Double ScreenHeightStart = dimension.getHeight() * 0.55;
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
            Thread.sleep(1000);
            System.out.println("swipeFormTIF finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("swipeFormTIF finalizado con error\n");
        }
    }

    /**
     * Click en el ícono de atrás
     */
    public void clickBack(){
        By by;
        try{
            // Click atrás
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/back");
            driver.findElement(by).click();
            Thread.sleep(1000);

            System.out.println("clickBack finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("clickBack finalizado con error\n");
        }
    }

    /**
     * Click en el ícono de atrás en la pantalla de flight details
     */
    public void clickBackFlightDetails(){
        By by;
        try{
            // Click atrás en la pantalla de flight details
            GeneratedUtils.sleep(500);
            by = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]");
            driver.findElement(by).click();
            Thread.sleep(1000);

            System.out.println("clickBackFlightDetails finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("clickBackFlightDetails finalizado con error\n");
        }
    }

    /**
     * Click en cancelar
     */
    public void clickCancel(){
        By by;
        try{
            // Click atrás
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/cancel");
            driver.findElement(by).click();
            Thread.sleep(1000);

            System.out.println("clickCancel finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("clickCancel finalizado con error\n");
        }
    }

    /**
     * Validaciones del T1 al T6
     * @param session contiene texto login o no login para saber si en la ejecución se está logueado en el app
     * @param pasenger Indica si el pasajero es adulto o niño
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void scriptT1_T6Validations(String session, String pasenger, Report report){
        By by;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            if(pasenger.equals("adult") || pasenger.equals("child")) {

                if(session.equals("login")) { //solo hace estas validaciones y swipe cuando esta logueado
                    //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                    GeneratedUtils.sleep(1500);
                    driver.findElement(By.id("com.copaair.copaAirlines.dev:id/selectProfile")).isDisplayed();
                    System.out.println("Se encontró el dropdown de perfiles\n");
                    // Screenshoot para el reporte
                    report.testPassed("Validar campos: Dropdown de perfiles, email, FFP and FFN", true);
                    // Swipe para ubicar los elementos en pantalla cuando se está logueado
                    Thread.sleep(500);
                    booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
                }

                // Valida que esté el campo correo electrónico
                //    Verificación de acceso al campo.
                GeneratedUtils.sleep(1500);
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).isDisplayed();
                System.out.println("PASO 1: Se encontró el campo correo electronico\n");
                //report.testPassed("Validar campos", true);

                // Valida que esté el campo programa de viajero frecuente
                //    Verificación de acceso al campo.
                GeneratedUtils.sleep(1500);
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffp")).isDisplayed();
                System.out.println("PASO 2: Se encontró el campo programa de viajero frecuento\n");
                //report.testPassed("Validar campos", true);

                // Valida que esté el campo número de viajero frecuente
                //    Verificación de acceso al campo.
                GeneratedUtils.sleep(1500);
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffn")).isDisplayed();
                System.out.println("PASO 3: Se encontró el campo número de viajero frecuente\n");

                // Screenshoot para el reporte
                report.testPassed("Validar campos: email, FFP and FFN", true); //Solo es necesario 1 screenshoot ya que son
                                                                                // visibles todos los campos en pantalla
            }else{
                if(session.equals("login")) { //solo hace estas validaciones y swipe cuando esta logueado
                    //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                    GeneratedUtils.sleep(1500);
                    driver.findElement(By.id("com.copaair.copaAirlines.dev:id/selectProfile")).isDisplayed();
                    System.out.println("Se encontró el dropdown de perfiles\n");
                }
                // Valida que esté el campo correo electrónico
                //    Verificación de acceso al campo.
                GeneratedUtils.sleep(1500);
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).isDisplayed();
                System.out.println("PASO 1: Se encontró el campo correo electronico\n");

                // Screenshoot para el reporte
                report.testPassed("Validar campo correo infante", true);
            }

            System.out.println("scriptT1_T6Validations finalizado con éxito\n");
        }catch(Exception ex) {
            System.out.println("scriptT1_T6Validations finalizado con error\n");
            report.testFailed("Validar campos", true);
        }
    }

    /**
     * Validaciones de los campos regular apis dentro de los formularios de TIF
     * @param session contiene texto login o no login para saber si en la ejecución se está logueado en el app
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void regularApisValidations(String session, Report report){
        By by;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            if(session.equals("login")) {
                //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                GeneratedUtils.sleep(2500);
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/selectProfile")).isDisplayed();
                System.out.println("Opción seleccionar perfil validado\n");
                // Screenshoot para el reporte
                report.testPassed("Validar campos: Dropdown de perfiles, regular APIS, FFP, FFN, email, KTN, Redress Number", true);
                // Swipe para ubicar los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            }

            // 1. Valida que esté presente el nombre del pasajero
            //    Validación del campo Nombre.
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/firstName")).isDisplayed();
            System.out.println("Nombre validado\n");

            // 2. Valida que esté presente el apellido del pasajero
            //    Validación del campo Apellido
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/lastName")).isDisplayed();
            System.out.println("Apellido validado\n");

            // 3. Valida que esté presente la fecha de nacimiento
            //    Validación del campo fecha de nacimiento.
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/dateOfBirthField")).isDisplayed();
            System.out.println("Fecha de nacimiento validado\n");

            // 4. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 5. Swipe para ubicar los elementos en pantalla
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            // 6. Valida que esté presente el campo de masculino
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/maleCheck")).isDisplayed();
            System.out.println("masculino validado\n");

            // 7. Valida que esté presente el campo de femenino
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/femaleCheck")).isDisplayed();
            System.out.println("femenino validado\n");

            // 8. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 9. Swipe para ver los elementos en pantalla
            //Thread.sleep(500);
            //booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            // 10. Valida que esté presente el campo de nacinalidad
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/nationalityField")).isDisplayed();
            System.out.println("Nacionalidad validado\n");

            // 11. Valida que esté presente el campo país de residencia
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/countryOfResidenceField")).isDisplayed();
            System.out.println("País residencia validado\n");

            // 12. Valida que esté presente el campo número de pasaporte
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/passportNumberField")).isDisplayed();
            System.out.println("Numero Pasaporte validado\n");

            // 13. Valida que esté presente el campo fecha de vencimiento pasaporte
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/expirationDate")).isDisplayed();
            System.out.println("Fecha vecimiento Pasaporte validado\n");

            // 14. Valida que esté presente el campo país emisor
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/countryOfIssueField")).isDisplayed();
            System.out.println("País emisor validado\n");

            // 15. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 16. Swipe para ver los elementos en pantalla
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);

            // 17. Valida que esté presente el campo correo electrónico
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).isDisplayed();
            System.out.println("Correo electronico validado\n");

            // 18. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            System.out.println("regularApisValidations finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("regularApisValidations finalizado con error\n");
            report.testFailed("Validar campos", true);
        }
    }

    /**
     * Validaciones de regular apis, campos de green card y personal information dentro de los formularios de TIF
     * @param passenger Indica si el pasajero es adulto o niño
     * @param session contiene texto login o no login para saber si en la ejecución se está logueado en el app
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void regularApisGreenCardAndPersonalInformationValidations(String passenger, String session, Report report){
        By by;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            if(session.equals("login")) {
                //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                GeneratedUtils.sleep(2500);
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/selectProfile")).isDisplayed();
                System.out.println("Opción seleccionar perfil validado\n");
                // Screenshoot para el reporte
                report.testPassed("Validar campos: Dropdown de perfiles, regular APIS, FFP, FFN, email, KTN, Redress Number", true);
                // Swipe para ubicar los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            }

            // 1. Valida que esté presente el nombre del pasajero
            //    Validación del campo Nombre.
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/firstName")).isDisplayed();
            System.out.println("Nombre validado\n");

            // 2. Valida que esté presente el apellido del pasajero
            //    Validación del campo Apellido
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/lastName")).isDisplayed();
            System.out.println("Apellido validado\n");

            // 3. Valida que esté presente la fecha de nacimiento
            //    Validación del campo fecha de nacimiento.
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/dateOfBirthField")).isDisplayed();
            System.out.println("Fecha de nacimiento validado\n");

            // 4. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 5. Swipe para ubicar los elementos en pantalla
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            // 6. Valida que esté presente el campo de masculino
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/maleCheck")).isDisplayed();
            System.out.println("masculino validado\n");

            // 7. Valida que esté presente el campo de femenino
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/femaleCheck")).isDisplayed();
            System.out.println("femenino validado\n");

            // 8. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 9. Swipe para ver los elementos en pantalla
            //Thread.sleep(500);
            //booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 10. Valida que esté presente el campo de nacinalidad
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/nationalityField")).isDisplayed();
            System.out.println("Nacionalidad validado\n");

            // 11. Valida que esté presente el campo país de residencia
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/countryOfResidenceField")).isDisplayed();
            System.out.println("País residencia validado\n");

            // 12. Valida que esté presente el campo número de pasaporte
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/passportNumberField")).isDisplayed();
            System.out.println("Numero Pasaporte validado\n");

            // 13. Valida que esté presente el campo fecha de vencimiento pasaporte
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/expirationDate")).isDisplayed();
            System.out.println("Fecha vecimiento Pasaporte validado\n");

            // 14. Valida que esté presente el campo país emisor
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/countryOfIssueField")).isDisplayed();
            System.out.println("País emisor validado\n");

            // 15. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 16. Swipe para ver los elementos en pantalla
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);

            // 17. Valida que esté presente el campo correo electrónico
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).isDisplayed();
            System.out.println("Correo electronico validado\n");

            //Condición requerida ya que estos campos en conjunto sólo serán visibles en pasajeros adultos
            if(passenger.equals("adult")) {

                // 18. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);

                // 19. Valida que este presente el campo de green card
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/cardNumber")).isDisplayed();
                System.out.println("numero green card validado\n");

                // 20. Valida que esté presente el campo vencimiento green card
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/expirationDateCard")).isDisplayed();
                System.out.println("expiración green card validado\n");

                // 21. Screenshoot para el reporte
                report.testPassed("Validar campos: Regular APIS, Extended APIS (Residence Card) FFP, FFN, KTN, Redress Number, email", true);

                // 22. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                // 23. Valida que esté presente el campo programa de viajero frecuente
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffp")).isDisplayed();
                System.out.println("Viajero frecuento validado\n");

                // 24. Valida que esté presente el campo Número de viajero fecuente
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffn")).isDisplayed();
                System.out.println("Numero viajero frecuente validado\n");
            }else{
                //Aquí entra a validar al child ya que al no haberse llenado sus formularios no muestra los mismos
                //campos que el adulto
                if (passenger.equals("child")){
                    // 25. Swipe para ver los elementos en pantalla
                    Thread.sleep(500);
                    swipeFormTIF(PanelTripDetails, driver, direct2);

                    // 26. Valida que esté presente el campo programa de viajero frecuente
                    driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffp")).isDisplayed();
                    System.out.println("Viajero frecuente validado\n");

                    // 27. Valida que esté presente el campo Número de viajero fecuente
                    driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffn")).isDisplayed();
                    System.out.println("numero viajero frecuente validado\n");
                }
            }

            // 28. Valida que esté presente el campo número de viajero conocido
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ktn")).isDisplayed();
            System.out.println("numero viajero conocido validado\n");

            // 29. Valida que esté presente el campo número de desagravio
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/redressNumber")).isDisplayed();
            System.out.println("numero de desagravio validado\n");

            // 30. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, Extended APIS (Residence Card) FFP, FFN, KTN, Redress Number, email", true);

            System.out.println("regularApisGreenCardAndPersonalInformationValidations finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("regularApisGreenCardAndPersonalInformationValidations finalizado con error\n");
            report.testFailed("Validar campos", true);
        }
    }

    /**
     * Validaciones de regular apis, where are you staying y personal information dentro de los formularios de TIF
     * @param passenger Indica si el pasajero es adulto o niño
     * @param session contiene texto login o no login para saber si en la ejecución se está logueado en el app
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void regularApisWhereAYSAndPersonalInformationValidations(String passenger, String session, Report report){
        By by;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            if(session.equals("login")) {
                //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                GeneratedUtils.sleep(2500);
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/selectProfile")).isDisplayed();
                System.out.println("Opción seleccionar perfil validado\n");
                // Screenshoot para el reporte
                report.testPassed("Validar campos: Dropdown de perfiles, regular APIS, FFP, FFN, email, KTN, Redress Number", true);
                // Swipe para ubicar los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            }

            // 1. Valida que esté presente el nombre del pasajero
            //    Validación del campo Nombre.
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/firstName")).isDisplayed();
            System.out.println("Nombre validado\n");

            // 2. Valida que esté presente el apellido del pasajero
            //    Validación del campo Apellido
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/lastName")).isDisplayed();
            System.out.println("Apellido validado\n");

            // 3. Valida que esté presente la fecha de nacimiento
            //    Validación del campo fecha de nacimiento.
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/dateOfBirthField")).isDisplayed();
            System.out.println("Fecha de nacimiento validado\n");

            // 4. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 5. Swipe para ubicar los elementos en pantalla
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            // 6. Valida que esté presente el campo de masculino
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/maleCheck")).isDisplayed();
            System.out.println("masculino validado\n");

            // 7. Valida que esté presente el campo de femenino
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/femaleCheck")).isDisplayed();
            System.out.println("femenino validado\n");

            // 8. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 9. Swipe para ver los elementos en pantalla
            //Thread.sleep(500);
            //booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 10. Valida que esté presente el campo de nacinalidad
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/nationalityField")).isDisplayed();
            System.out.println("Nacionalidad validado\n");

            // 11. Valida que esté presente el campo país de residencia
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/countryOfResidenceField")).isDisplayed();
            System.out.println("País residencia validado\n");

            // 12. Valida que esté presente el campo número de pasaporte
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/passportNumberField")).isDisplayed();
            System.out.println("Numero Pasaporte validado\n");

            // 13. Valida que esté presente el campo fecha de vencimiento pasaporte
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/expirationDate")).isDisplayed();
            System.out.println("Fecha vecimiento Pasaporte validado\n");

            // 14. Valida que esté presente el campo país emisor
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/countryOfIssueField")).isDisplayed();
            System.out.println("País emisor validado\n");

            // 15. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 16. Swipe para ver los elementos en pantalla
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);

            // 17. Valida que esté presente el campo correo electrónico
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).isDisplayed();
            System.out.println("Correo electronico validado\n");

            //Condición requerida ya que estos campos en conjunto sólo serán visibles en pasajeros adultos
            if(passenger.equals("adult")) {

                // 18. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                // 19. Valida que este presente el campo direccion de hospedaje
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/streetAddress")).isDisplayed();
                System.out.println("direccion de hospedaje validado\n");

                // 20. Valida que esté presente el campo ciudad de hospedaje
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/city")).isDisplayed();
                System.out.println("ciudad de hospedaje validado\n");

                // 21. Valida que esté presente el estado de hospedaje
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/state")).isDisplayed();
                System.out.println("estado de hospedaje validado\n");

                // 22. Valida que esté presente el campo código postal de hospedaje
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/zipCode")).isDisplayed();
                System.out.println("código postal de hospedaje validado\n");

                // 23. Screenshoot para el reporte
                report.testPassed("Validar campos", true);

                // 22. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                // 23. Valida que esté presente el campo programa de viajero frecuente
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffp")).isDisplayed();
                System.out.println("viajero frecuente validado\n");

                // 24. Valida que esté presente el campo Número de viajero fecuente
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffn")).isDisplayed();
                System.out.println("Número de viajero fecuente validado\n");
            }else{
                //Aquí entra a validar al child ya que al no haberse llenado sus formularios no muestra los mismos
                //campos que el adulto
                if (passenger.equals("child")){
                    // 25. Swipe para ver los elementos en pantalla
                    Thread.sleep(500);
                    swipeFormTIF(PanelTripDetails, driver, direct2);

                    // 26. Valida que esté presente el campo programa de viajero frecuente
                    driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffp")).isDisplayed();
                    System.out.println("viajero frecuente validado\n");

                    // 27. Valida que esté presente el campo Número de viajero fecuente
                    driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ffn")).isDisplayed();
                    System.out.println("Número de viajero fecuente validado\n");
                }
            }

            // 28. Valida que esté presente el campo número de viajero conocido
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/ktn")).isDisplayed();
            System.out.println("número de viajero conocido validado\n");

            // 29. Valida que esté presente el campo número de desagravio
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/redressNumber")).isDisplayed();
            System.out.println("número de desagravio validado\n");

            // 30. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, Extended APIS (Where are you staying?) FFP, FFN, KTN, Redress Number, email", true);

            System.out.println("regularApisWhereAYSAndPersonalInformationValidations finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("regularApisWhereAYSAndPersonalInformationValidations finalizado con error\n");
            report.testFailed("Validar campos: Regular APIS, Extended APIS (Where are you staying?) FFP, FFN, KTN, Redress Number, email", true);
        }
    }

    /**
     * Llena los campos del TIF con nacionalidad USA
     * @param session contiene texto login o no login para saber si en la ejecución se está logueado en el app
     */
    public void fillFieldsUsaNationality(String session){
        By by;
        String direct2 = "abajo";
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            // 1. Swipe para ubicar los elementos en pantalla
            if(session.equals("login")){
                // Ubica los elementos en pantalla cuando se está logueado}
                Thread.sleep(500);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            }
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).clear();
            System.out.println("Campo país de nacionalidad limpiado\n");

            // 3. Llena el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).sendKeys("Estados Unidos de América");
            System.out.println("Campo país de nacionalidad llenado\n");

            // 4. Limpia el campo país de residencia
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).clear();
            System.out.println("Campo país de residencia limpiado\n");

            // 5. Llena el campo país de residenciaa
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).sendKeys("Estados Unidos de América");
            System.out.println("Campo país de residencia llenado\n");

            // 6. Limpia el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).clear();
            System.out.println("Limpia campo número de pasaporte\n");

            // 7. Llena el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).sendKeys("1234");
            System.out.println("Campo de número de pasaporte llenado\n");

            // 8. Click en la opción fecha de vencimiento
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/expirationDate");
            driver.findElement(by).click();
            System.out.println("Opción fecha de vencimiento clickeada\n");

            // 9. Make a Swipe gesture from ('761','1189') to ('768','768')
            GeneratedUtils.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(761,1189))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(768,768)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("android:id/button1");
            driver.findElement(by).click();
            System.out.println("Aceptar fecha de pasaporte clickeado\n");

            // 11. Swipe y Limpia país emisor
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).clear();
            System.out.println("País Emisor limpiado\n");

            // 12. llena país emisor
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).sendKeys("Estados Unidos de América");
            System.out.println("País Emisor llenado\n");

            // 13. Click en Guardar
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/cta");
            driver.findElement(by).click();
            System.out.println("Click en guardar completado\n");

            // 14. Pausa por carga de guardado
            Thread.sleep(15000);

            System.out.println("fillFieldsUsaNationality finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("fillFieldsUsaNationality finalizado con error\n");
        }
    }

    /** Llena los campos del TIF con únicamente la residencia en USA para desplegar los campos de green card
     * @param session contiene texto login o no login para saber si en la ejecución se está logueado en el app
     */
    public void fillFieldsOnlyUsaResidentAndResidentCard(String session){
        By by;
        String direct2 = "abajo";
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            // 1. Swipe para ubicar los elementos en pantalla
            if(session.equals("login")){
                // Ubica los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
            }
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).clear();
            System.out.println("Campo país de nacionalidad limpiado\n");

            // 3. Llena el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).sendKeys("Panamá");
            System.out.println("Campo país de nacionalidad llenado\n");

            // 4. Limpia el campo país de residencia
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).clear();
            System.out.println("Campo país de residencia limpiado\n");

            // 5. Llena el campo país de residenciaa
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).sendKeys("Estados Unidos de América");
            System.out.println("Campo país de residencia llenado\n");

            // 6. Limpia el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).clear();
            System.out.println("Campo número de pasaporte limpiado\n");

            // 7. Llena el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).sendKeys("1234");
            System.out.println("Campo número de pasaporte llenado\n");

            // 8. Click en la opción fecha de vencimiento
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/expirationDate");
            driver.findElement(by).click();
            System.out.println("Opción fecha de vencimiento clickeado\n");

            // 9. Make a Swipe gesture from ('761','1189') to ('768','768')
            GeneratedUtils.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(761,1189))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(768,768)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("android:id/button1");
            driver.findElement(by).click();
            System.out.println("Opción aceptar fecha de pasaporte clickeado\n");

            // 11. swipe y Limpia país emisor
            Thread.sleep(500);
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).clear();
            System.out.println("Campo País emisor limpiado\n");

            // 12. llena país emisor
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).sendKeys("Panamá");
            System.out.println("Campo País Emisor llenado\n");

            // 13. Swipe y limpia el campo número de green card
            swipeFormTIF(PanelTripDetails, driver, direct2);
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/cardNumber");
            driver.findElement(by).clear();
            System.out.println("Campo número de green card limpiado\n");

            // 14. Llena número de green card
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/cardNumber");
            driver.findElement(by).sendKeys("12345");
            System.out.println("Campo green card llenado\n");

            // 15. Swipe y Click en la opción fecha de vencimiento Green card
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/expirationDateCard");
            driver.findElement(by).click();
            System.out.println("Opción fecha de vencimiento Green card clickeado\n");

            // 16. Make a Swipe gesture from ('761','1189') to ('768','768')
            GeneratedUtils.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(761,1189))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(768,768)).release().perform();

            // 17. Click en aceptar fecha de vencimiento green card
            GeneratedUtils.sleep(500);
            by = By.id("android:id/button1");
            driver.findElement(by).click();
            System.out.println("Opción aceptar fecha de vencimiento green card clickeado\n");

            // 18. Click en Guardar
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.TextView[@text = 'Guardar']");
            driver.findElement(by).click();
            System.out.println("Opción guardar clickeado\n");

            // 19. Pausa por carga de guardado
            Thread.sleep(15000);

            System.out.println("fillFieldsOnlyUsaResidentAndResidentCard finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("fillFieldsOnlyUsaResidentAndResidentCard finalizado con error\n");
        }
    }

    /**
     * Llena los campos del TIF con todo en Panamá para desplegar los campos de where are you staying
     * @param session contiene texto login o no login para saber si en la ejecución se está logueado en el app
     */
    public void fillFieldsAllPanamaAndWhereAreYouStaying(String session){
        By by;
        String direct2 = "abajo";
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            // 1. Swipe para ubicar los elementos en pantalla
            if(session.equals("login")){
                // Ubica los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
            }
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).clear();
            System.out.println("Campo país de nacionalidad limpiado\n");

            // 3. Llena el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).sendKeys("Panamá");
            System.out.println("Campo país de nacionalidad llenado\n");

            // 4. Limpia el campo país de residencia
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).clear();
            System.out.println("Campo país de residencia limpiado\n");

            // 5. Llena el campo país de residenciaa
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).sendKeys("Panamá");
            System.out.println("Campo país de residencia llenado\n");

            // 6. Limpia el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).clear();
            System.out.println("Campo número de pasaporte limpiado\n");

            // 7. Llena el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).sendKeys("1234");
            System.out.println("Campo número de pasaporte llenado\n");

            // 8. Click en la opción fecha de vencimiento
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/expirationDate");
            driver.findElement(by).click();
            System.out.println("Opción fecha de vencimiento clickeado\n");

            // 9. Make a Swipe gesture from ('761','1189') to ('768','768')
            GeneratedUtils.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(761,1189))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(768,768)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("android:id/button1");
            driver.findElement(by).click();
            System.out.println("Opción aceptar fecha de pasaporte clickeado\n");

            // 11. swipe y Limpia país emisor
            Thread.sleep(500);
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).clear();
            System.out.println("Campo País emisor limpiado\n");

            // 12. llena país emisor
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).sendKeys("Panamá");
            System.out.println("Campo País Emisor llenado\n");

            // 13. Swipe y limpia el campo dirección de hospedaje
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/streetAddress");
            driver.findElement(by).clear();
            System.out.println("Campo dirección de hospedaje limpiado\n");

            // 14. Llena la dirección de hospedaje
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/streetAddress");
            driver.findElement(by).sendKeys("3601 CHARLES GREEN");
            System.out.println("Campo dirección de hospedaje llenado\n");

            // 13. limpia el campo ciudad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/city");
            driver.findElement(by).clear();
            System.out.println("Campo ciudad limpiado\n");

            // 14. Llena el campo ciudad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/city");
            driver.findElement(by).sendKeys("Miami");
            System.out.println("Campo ciudad llenado\n");

            // 13. Swipe y limpia el campo estado
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/state");
            driver.findElement(by).clear();
            System.out.println("Campo estado limpiado\n");

            // 14. Llena el campo estado
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/state");
            driver.findElement(by).sendKeys("Florida");
            System.out.println("Campo estado llenado\n");

            // 13. limpia el campo código postal
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/zipCode");
            driver.findElement(by).clear();
            System.out.println("Campo código postal limpiado\n");

            // 14. Llena el campo código postal
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/zipCode");
            driver.findElement(by).sendKeys("94027");
            System.out.println("Campo código postal llenado\n");

            // 24. Click 'Check Utilizar esta dirección ¿Dónde te vas a hospedar?'
            GeneratedUtils.sleep(1795);
            by = By.id("com.copaair.copaAirlines.dev:id/addressForAllTravelersCheck");
            driver.findElement(by).click();
            System.out.println("'Check Utilizar esta dirección ¿Dónde te vas a hospedar?' clickeado\n");

            // 18. Click en Guardar
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.TextView[@text = 'Guardar']");
            driver.findElement(by).click();
            System.out.println("Opción Guardar clickeada\n");

            // 19. Pausa por carga de guardado
            Thread.sleep(15000);

            System.out.println("fillFieldsAllPanamaAndWhereAreYouStaying finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("fillFieldsAllPanamaAndWhereAreYouStaying finalizado con error\n");
        }
    }

    /** Llena los campos del TIF con todo en Panamá, where are you staying con el check in transit y realiza las validaciones
     * @param session contiene texto login o no login para saber si en la ejecución se está logueado en el app
     * @param passenger Indica si el pasajero es adulto o niño
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void fillFieldsAllPanamaWhereAYSCheckInTransitAndValidate(String session, String passenger, Report report){
        By by;
        String direct2 = "abajo", campos;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            // 1. Swipe para ubicar los elementos en pantalla
            if(session.equals("login")){
                // Ubica los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
            }
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).clear();
            System.out.println("Campo país de nacionalidad limpiado\n");

            // 3. Llena el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).sendKeys("Panamá");
            System.out.println("Campo país de nacionalidad llenado\n");

            // 4. Limpia el campo país de residencia
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).clear();
            System.out.println("Campo país de residencia limpiado\n");

            // 5. Llena el campo país de residenciaa
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).sendKeys("Panamá");
            System.out.println("Campo país de residencia llenado\n");

            // 6. Limpia el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).clear();
            System.out.println("Campo número de pasaporte limpiado\n");

            // 7. Llena el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).sendKeys("1234");
            System.out.println("Campo número de pasaporte llenado\n");

            // 8. Click en la opción fecha de vencimiento
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/expirationDate");
            driver.findElement(by).click();
            System.out.println("Opción fecha de vencimiento clickeado\n");


            // 9. Make a Swipe gesture from ('761','1189') to ('768','768')
            GeneratedUtils.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(761,1189))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(768,768)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("android:id/button1");
            driver.findElement(by).click();
            System.out.println("Opción aceptar fecha de pasaporte clickeado\n");


            // 11. swipe y Limpia país emisor
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).clear();
            System.out.println("Campo País emisor limpiado\n");

            // 12. llena país emisor
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).sendKeys("Panamá");
            System.out.println("Campo País Emisor llenado\n");

            if(passenger.equals("adult")) {
                // 13. Valida que este presente 'En tránsito hospedaje' y luego hace click
                GeneratedUtils.sleep(1704);
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/inTransit"));
                by = By.id("com.copaair.copaAirlines.dev:id/inTransit");
                campos = driver.findElement(by).getAttribute("checked");
                if(campos.equals("false")) {
                    driver.findElement(by).click();
                    System.out.println("Campo seleccionado\n");
                }else { System.out.println("Campo ya seleccionado\n");}

                // 15. Screenshoot para el reporte
                report.testPassed("Validaciones Agregar in Transit", true);

                // 14. swipe para ver usar esta direccion en pantalla
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                // 15. Click 'Check Utilizar esta dirección ¿Dónde te vas a hospedar?'
                GeneratedUtils.sleep(1795);
                by = By.id("com.copaair.copaAirlines.dev:id/addressForAllTravelersCheck");
                driver.findElement(by).click();
                System.out.println("'Check Utilizar esta dirección ¿Dónde te vas a hospedar?' clickeado\n");
            } else if (passenger.equals("child") || passenger.equals("infant")) {

                // 16. swipe y valida que esté el check en tránsito esté presente y activado
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
                driver.findElement(By.id("com.copaair.copaAirlines.dev:id/inTransit"));
                System.out.println("'Check en tránsito presente y activado\n");

                // 6. Valida que no se pueda llenar el campo dirección de hospedaje
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/streetAddress");
                campos = driver.findElement(by).getAttribute("enabled");
                if (campos.equals("true")){
                    System.out.println("Validación incorrecta, campo editable");
                }else{
                    System.out.println("Validación correcta, campo no editable");
                    report.testPassed("Validar campos", true);
                }

                // 6. Valida que no se pueda llenar el campo ciudad de hospedaje
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/city");
                campos = driver.findElement(by).getAttribute("enabled");
                if (campos.equals("true")){
                    System.out.println("Validación incorrecta, campo editable");
                }else{
                    System.out.println("Validación correcta, campo no editable");
                    report.testPassed("Validar campos", true);
                }

                // 6. Valida que no se pueda llenar el campo estado de hospedaje
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/state");
                campos = driver.findElement(by).getAttribute("enabled");
                if (campos.equals("true")){
                    System.out.println("Validación incorrecta, campo editable");
                }else{
                    System.out.println("Validación correcta, campo no editable");
                    report.testPassed("Validar campos", true);
                }

                // 6. Valida que no se pueda llenar el campo código postal de hospedaje
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/zipCode");
                campos = driver.findElement(by).getAttribute("enabled");
                if (campos.equals("true")){
                    System.out.println("Validación incorrecta, campo editable");
                }else{
                    System.out.println("Validación correcta, campo no editable\n");
                    report.testPassed("Validar campos", true);
                }

                // 15. Screenshoot para el reporte pass
                report.testPassed("Validaciones Agregar in Transit", true);
            }

            if(passenger.equals("adult") || passenger.equals("child")) {
                // 17. Click en Guardar
                GeneratedUtils.sleep(500);
                by = By.xpath("//android.widget.TextView[@text = 'Guardar']");
                driver.findElement(by).click();
                System.out.println("Opción Guardar clickeada\n");

                // 18. Pausa por carga de guardado
                Thread.sleep(15000);
            }

            System.out.println("fillFieldsAllPanamaWhereAYSCheckInTransitAndValidate finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("fillFieldsAllPanamaWhereAYSCheckInTransitAndValidate finalizado con error\n");
        }
    }

    /**
     * Llena los campos necesarios para el Test 37 y realiza las validaciones
     * @param passenger Indica si el pasajero es adulto o niño
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void fillFieldsT37AndValidations(String passenger, Report report){
        By by;
        String direct2 = "abajo", correo;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{

            if(passenger.equals("adult")){
                // 1. Swipe para ubicar los elementos en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

                // 2. Limpia el campo país de nacionalidad
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/nationality");
                driver.findElement(by).clear();


                // 3. Llena el campo país de nacionalidad
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/nationality");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("Nacionalidad Llenado");

                // 4. Limpia el campo país de residencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).clear();

                // 5. Llena el campo país de residenciaa
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("País de residencia Llenado");

                // 6. Limpia el campo número de pasaporte
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
                driver.findElement(by).clear();

                // 7. Llena el campo número de pasaporte
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
                driver.findElement(by).sendKeys("1234");
                System.out.println("Número de pasaporte Llenado");

                // 8. Click en la opción fecha de vencimiento
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/expirationDate");
                driver.findElement(by).click();

                // 9. Make a Swipe gesture from ('761','1189') to ('768','768')
                GeneratedUtils.sleep(500);
                (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(761,1189))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                        .moveTo(PointOption.point(768,768)).release().perform();

                // 10. Click en aceptar fecha de pasaporte
                GeneratedUtils.sleep(500);
                by = By.id("android:id/button1");
                driver.findElement(by).click();
                System.out.println("Fecha de pasaporte aceptada");

                // 11. swipe y Limpia país emisor
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
                driver.findElement(by).clear();

                // 12. llena país emisor
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("País Emisor Llenado");

                report.testPassed("Campo con el correo original", true);

                // 13. Limpia correo
                Thread.sleep(500);
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/email");
                driver.findElement(by).clear();

                // 14. llena el correo
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/email");
                driver.findElement(by).sendKeys("CORREO@INFOS.COM");
                report.testPassed("Llenado del correo pasajero adulto", true);
                System.out.println("Correo Llenado");

                // 15. Click en utilizar este correo para todos los pasajeros
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/cmEmailForAllUsersCheck");
                driver.findElement(by).click();
                System.out.println("Click en utilizar este correo para todos los pasajeros");


                // 16. Click en Guardar
                GeneratedUtils.sleep(500);
                by = By.xpath("//android.widget.TextView[@text = 'Guardar']");
                driver.findElement(by).click();
                System.out.println("Click en Guardar");

                // 17. Pausa por carga de guardado
                Thread.sleep(15000);

            } else if (passenger.equals("child")) {
                // 18. Swipe para ubicar el correo electrónico en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                // 19. Valida si el campo de correo electrónico contiene el correo puesto en el pasajero adulto
                correo = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).getAttribute("text");
                if (correo.equals("CORREO@INFOS.COM")){
                    System.out.println("Validación correcta, contiene el correo llenado\n");
                    report.testPassed("Valida si se realizó el cambio del correo", true);
                }else {
                    System.out.println("Validación incorrecta, NO contiene el correo llenado\n");
                    report.testFailed("Validar campos", true);
                }
            }

            System.out.println("fillFieldsT37 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("fillFieldsT37 finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del T38 AL T42
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateT38_T42(Report report){
        By by;
        String direct2 = "abajo", direct1 = "arriba", correo1, correo2;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            //    Swipe para ubicar el correo en pantalla
            Thread.sleep(1500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            swipeFormTIF(PanelTripDetails, driver, direct2);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            //booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            //    Screenshot para el reporte
            report.testPassed("Correo por defecto", true);

            //    Obtiene el correo para comparar en pasos posteriores
            correo1 = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).getAttribute("text");

            //    Swipe hacia arriba para cambiar el perfil CM
            swipeFormTIF(PanelTripDetails, driver, direct1);
            swipeFormTIF(PanelTripDetails, driver, direct1);
            swipeFormTIF(PanelTripDetails, driver, direct1);
            booking.swipeSmall(PanelTripDetails, driver, direct1);

            //    Click al dropdown menú de perfiles
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/selectProfile")).click();
            System.out.println("Click al dropdown menú de perfiles\n");

            //    Click a un perfil CM
            GeneratedUtils.sleep(1500);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/selectProfileContentDiv")).click();
            System.out.println("Click a un perfil CM\n");

            //    Swipe para ubicar el correo en pantalla
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);



            //    Obtiene el nuevo correo para comparar en el paso siguiente
            correo2 = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).getAttribute("text");

            if(correo1.equals(correo2)){
                System.out.println("Error, el correo de la cuenta CM no se sobreescribió correctamente\n");
                report.testFailed("Valida que se sobreescribió el correo de la cuenta Connectmiles", true);
            }else{
                System.out.println("Paso correcto, el correo de la cuenta CM se sobreescribió correctamente\n");
                report.testPassed("Valida que se sobreescribió correo de la cuenta Connectmiles", true);
            }

            System.out.println("validateT38_T42 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateT38_T42 finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del T43 al T45
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param test Indica el test que se valida
     */
    public void validateT43_T45(Report report, String test){
        By by;
        String direct2 = "abajo", logo, campo;
        Boolean check;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            //      Validación t43 no se debe mostrar la opción save to profile
            if (test.equals("t43")) {
                //    Click 'Menú de perfiles'
                GeneratedUtils.sleep(1500);
                by = By.id("com.copaair.copaAirlines.dev:id/selectProfile");
                driver.findElement(by).click();
                System.out.println("Click en menú de perfiles\n");

                //    Click 'Perfil Connectmiles'
                GeneratedUtils.sleep(1500);
                by = By.id("com.copaair.copaAirlines.dev:id/selectProfileContentDiv");
                driver.findElement(by).click();
                System.out.println("Click Perfil Connectmiles\n");

                //    Swipe para validar que no se muestre save to profile en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                /*//    Valida que esté NO presente la opción de save to profile
                check = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/cmProfileCheck")).isDisplayed();
                if(check == true){
                    System.out.println("ERROR, opción save to profile se muestra\n");
                }else {
                    System.out.println("Opción save to profile no se muestra, validación correcta\n");
                    report.testFailed("Valida que no esté presente la opción de save to profile", true);
                }*/

                //    Screenshot para el reporte
                report.testPassed("Validación save to profile no esté presente", true);

                //    Click atrás
                GeneratedUtils.sleep(1500);
                by = By.id("com.copaair.copaAirlines.dev:id/back");
                driver.findElement(by).click();
                System.out.println("Click en atrás\n");

            } else if (test.equals("t44")) { //     Validación t44 se debe mostrar la opción save to profile
                //    Click 'Menú de perfiles'
                GeneratedUtils.sleep(1500);
                by = By.id("com.copaair.copaAirlines.dev:id/selectProfile");
                driver.findElement(by).click();
                System.out.println("Click en menú de perfiles\n");

                //    Click 'Perfil Connectmiles'
                GeneratedUtils.sleep(1500);
                by = By.id("com.copaair.copaAirlines.dev:id/selectProfileContentDiv");
                driver.findElement(by).click();
                System.out.println("Click en perfil Connectmiles\n");

                //     Empieza el llenado de los campos del pasajero para visualizar la opción save to profile
                // 1. Swipe para ubicar los elementos en pantalla
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                //Thread.sleep(500);
                //booking.swipeSmall(PanelTripDetails, driver, direct2);
                //swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

                // 2. Limpia el campo país de nacionalidad
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/nationality");
                driver.findElement(by).clear();
                System.out.println("Campo país de nacionalidad limpiado\n");

                // 3. Llena el campo país de nacionalidad
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/nationality");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("Campo país de nacionalidad llenado\n");

                // 4. Limpia el campo país de residencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).clear();
                System.out.println("Campo país de residencia limpiado\n");

                // 5. Llena el campo país de residenciaa
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("Campo país de residencia llenado\n");


                // 6. Limpia el campo número de pasaporte
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
                driver.findElement(by).clear();
                System.out.println("Campo número de pasaporte limpiado\n");

                // 7. Llena el campo número de pasaporte
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
                driver.findElement(by).sendKeys("1234");
                System.out.println("Campo número de pasaporte llenado\n");

                // 8. Click en la opción fecha de vencimiento
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/expirationDate");
                driver.findElement(by).click();
                System.out.println("Opción fecha de vencimiento clickeado\n");


                // 9. Make a Swipe gesture from ('761','1189') to ('768','768')
                GeneratedUtils.sleep(500);
                (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(761,1189))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                        .moveTo(PointOption.point(768,768)).release().perform();

                // 10. Click en aceptar fecha de pasaporte
                GeneratedUtils.sleep(500);
                by = By.id("android:id/button1");
                driver.findElement(by).click();
                System.out.println("Opción aceptar fecha de pasaporte clickeado\n");


                // 11. Swipe y Limpia país emisor
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
                driver.findElement(by).clear();
                System.out.println("Campo País emisor limpiado\n");

                // 12. llena país emisor
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("Campo País Emisor llenado\n");

                //    Swipe para validar que se muestre save to profile en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //    Valida que esté presente la opción de save to profile
                campo = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/cmProfileCheck")).getAttribute("text");
                if(campo.equals("Guardar información actualizada en el perfil de ConnectMiles.")){
                    System.out.println("Opción save to profile se muestra correctamente\n");
                }else {
                    System.out.println("ERROR, opción save to profile no se muestra correctamente\n");
                    report.testFailed("Valida que esté presente la opción de save to profile", true);
                }

                //    Screenshot para el reporte
                report.testPassed("Validación save to profile presente", true);

                //    Click atrás
                GeneratedUtils.sleep(1500);
                by = By.id("com.copaair.copaAirlines.dev:id/back");
                driver.findElement(by).click();
                System.out.println("Click en atrás\n");

            }else {
                //    Click 'Menú de perfiles'
                GeneratedUtils.sleep(1500);
                by = By.id("com.copaair.copaAirlines.dev:id/selectProfile");
                driver.findElement(by).click();
                System.out.println("Click en Menú de perfiles\n");

                //    Click 'Perfil Connectmiles'
                GeneratedUtils.sleep(1500);
                by = By.id("com.copaair.copaAirlines.dev:id/selectProfileContentDiv");
                driver.findElement(by).click();
                System.out.println("Click en Perfil Connectmiles\n");

                //    Swipe para validar que se muestre el logo de TSA PreCheck
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
                //swipeFormTIF(PanelTripDetails, driver, direct2);

                //    Valida que esté presente el logo de TSA PreCheck
                logo = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/tsa")).getAttribute("enabled");
                if(logo.equals("true")){
                    System.out.println("El logo se TSA PreCheck se muestra correctamente\n");
                }else {
                    System.out.println("ERROR, el logo se TSA PreCheck NO se muestra correctamente\n");
                    report.testFailed("Valida que se muestre el logo de TSA PreCheck", true);
                }

                //    Screenshot para el reporte
                report.testPassed("Valida que se muestre el logo de TSA PreCheck", true);

                //    Click atrás
                GeneratedUtils.sleep(1500);
                by = By.id("com.copaair.copaAirlines.dev:id/back");
                driver.findElement(by).click();
                System.out.println("Click en atrás\n");
            }

            System.out.println("validateT43_T45 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validate43_T45 finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del T46
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateT46(Report report){
        By by;
        String direct2 = "abajo", logo, campo;
        Boolean check;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{

            //    Click 'Menú de perfiles'
            GeneratedUtils.sleep(1500);
            by = By.id("com.copaair.copaAirlines.dev:id/selectProfile");
            driver.findElement(by).click();
            System.out.println("Click en Menú de perfiles\n");

            //    Click 'Perfil Connectmiles'
            GeneratedUtils.sleep(1500);
            by = By.id("com.copaair.copaAirlines.dev:id/selectProfileContentDiv");
            driver.findElement(by).click();
            System.out.println("Click en Perfil Connectmiles\n");

            Thread.sleep(3000);

            //  Limpia el campo nombre
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/firstName");
            driver.findElement(by).clear();
            System.out.println("Campo Nombre limpiado\n");

            //  Llena el campo nombre
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/firstName");
            driver.findElement(by).sendKeys("Juan");
            System.out.println("Campo Nombre llenado\n");

            //     Empieza el llenado de los campos del pasajero para visualizar la opción save to profile
            // 1. Swipe para ubicar los elementos en pantalla
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);
            //Thread.sleep(500);
            //booking.swipeSmall(PanelTripDetails, driver, direct2);
            //swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).clear();
            System.out.println("Campo país de nacionalidad limpiado\n");

            // 3. Llena el campo país de nacionalidad
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/nationality");
            driver.findElement(by).sendKeys("Estados Unidos de América");
            System.out.println("Campo país de nacionalidad llenado\n");


            // 4. Limpia el campo país de residencia
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).clear();
            System.out.println("Campo país de residencia limpiado\n");

            // 5. Llena el campo país de residenciaa
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
            driver.findElement(by).sendKeys("Estados Unidos de América");
            System.out.println("Campo país de residencia llenado\n");


            // 6. Limpia el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).clear();
            System.out.println("Campo número de pasaporte limpiado\n");

            // 7. Llena el campo número de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
            driver.findElement(by).sendKeys("1234");
            System.out.println("Campo número de pasaporte llenado\n");


            // 8. Click en la opción fecha de vencimiento
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/expirationDate");
            driver.findElement(by).click();
            System.out.println("Opción fecha de vencimiento clickeado\n");


            // 9. Make a Swipe gesture from ('761','1189') to ('768','768')
            GeneratedUtils.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(761,1189))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(768,768)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtils.sleep(500);
            by = By.id("android:id/button1");
            driver.findElement(by).click();
            System.out.println("Opción aceptar fecha de pasaporte clickeado\n");


            // 11. Swipe y Limpia país emisor
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).clear();
            System.out.println("Campo País emisor limpiado\n");

            // 12. llena país emisor
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
            driver.findElement(by).sendKeys("Estados Unidos de América");
            System.out.println("Campo País Emisor llenado\n");

            // 13. Click en Guardar
            GeneratedUtils.sleep(500);
            by = By.xpath("//android.widget.TextView[@text = 'Guardar']");
            driver.findElement(by).click();
            System.out.println("Opción Guardar clickeada\n");

            Thread.sleep(15000);

            //    Screenshot para el reporte
            report.testPassed("Valida que se muestre el mensaje de error", true);

            System.out.println("validateT46 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validate46 finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del T47 al T54
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param session contiene texto login o no login para saber si en la ejecución se está logueado en el app
     * @param passenger Indica si el pasajero es adulto o infante
     */
    public void validateT47_T54(Report report, String session, String passenger){
        By by;
        String direct2 = "abajo", validation;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            if(passenger.equals("adult")) {
                // 1. Swipe para ubicar los elementos en pantalla
                if (session.equals("login")) {
                    // Ubica los elementos en pantalla cuando se está logueado}
                    Thread.sleep(500);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                }
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

                // 2. Limpia el campo país de nacionalidad
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/nationality");
                driver.findElement(by).clear();
                System.out.println("Campo país de nacionalidad limpiado\n");

                // 3. Llena el campo país de nacionalidad
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/nationality");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("Campo país de nacionalidad llenado\n");


                // 4. Limpia el campo país de residencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).clear();
                System.out.println("Campo país de residencia limpiado\n");

                // 5. Llena el campo país de residenciaa
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("Campo país de residencia llenado\n");


                // 6. Limpia el campo número de pasaporte
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
                driver.findElement(by).clear();
                System.out.println("Campo número de pasaporte limpiado\n");

                // 7. Llena el campo número de pasaporte
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/passportNumber");
                driver.findElement(by).sendKeys("1234");
                System.out.println("Campo número de pasaporte llenado\n");


                // 8. Click en la opción fecha de vencimiento
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/expirationDate");
                driver.findElement(by).click();
                System.out.println("Opción fecha de vencimiento clickeado\n");

                // 9. Make a Swipe gesture from ('761','1189') to ('768','768')
                GeneratedUtils.sleep(500);
                (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(761, 1189))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                        .moveTo(PointOption.point(768, 768)).release().perform();

                // 10. Click en aceptar fecha de pasaporte
                GeneratedUtils.sleep(500);
                by = By.id("android:id/button1");
                driver.findElement(by).click();
                System.out.println("Opción aceptar fecha de pasaporte clickeado\n");

                // 11. swipe y Limpia país emisor
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
                driver.findElement(by).clear();
                System.out.println("Campo País emisor limpiado\n");

                // 12. llena país emisor
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfIssue");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("Campo País Emisor llenado\n");


                //    Swipes para ubicar los campos de contacto de emergencia
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Valida si es seleccionable el botón guardar T48
                validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/cta")).getAttribute("selected");
                if (validation.equals("true")) {
                    System.out.println("Validación correcta, se puede seleccionar el botón de guardar\n");
                    report.testPassed("Valida que el botón de guardar se pueda seleccionar", true);
                } else {
                    System.out.println("Validación error, NO se puede seleccionar el botón de guardar\n");
                    report.testFailed("Valida que el botón de guardar se pueda seleccionar", true);
                }

                //  Llena el campo nombre y apellido de emergencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).sendKeys("Juan");
                System.out.println("Campo nombre y apellido de emergencia llenado\n");

                //  Valida el mensaje de error de campo requerido T47
                validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");
                if (validation.equals("Requerido")) {
                    System.out.println("Validación correcta, se muestra el mensaje de error\n");
                    report.testPassed("Valida que se muestre el mensaje de error Requerido", true);
                } else {
                    System.out.println("Validación error, NO se muestra el mensaje de error\n");
                    report.testFailed("Valida que se muestre el mensaje de error Requerido", true);
                }

                //  Limpia el campo nombre y apellido de emergencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).clear();
                System.out.println("Campo nombre y apellido de emergencia limpiado\n");

                //  Llena y valida que el código de país se pueda buscar por Nombre del país o Código T49
                GeneratedUtils.sleep(500);
                new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(138,2087)).release().perform();
                by = By.id("com.copaair.copaAirlines.dev:id/countryCode");
                driver.findElement(by).sendKeys("+50");
                System.out.println("Campo código de país llenado\n");
                report.testPassed("Valida que el Código de país se pueda buscar por nombre del país y código", true);
                //  Limpia el campo para ahora escribir el país por nombre
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryCode");
                driver.findElement(by).clear();
                System.out.println("Campo código de país limpiado\n");
                //  Escribe el nombre del país
                GeneratedUtils.sleep(500);
                new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(138,2087)).release().perform();
                by = By.id("com.copaair.copaAirlines.dev:id/countryCode");
                driver.findElement(by).sendKeys("Panamá");
                System.out.println("Campo código de país llenado\n");
                report.testPassed("Valida que el Código de país se pueda buscar por nombre del país y código", true);

                //  Limpia el campo de código de país
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryCode");
                driver.findElement(by).clear();
                System.out.println("Campo código de país limpiado\n");

                //  Llena y valida que el nombre y apellido de contacto de emergencia sólo pueda tener MAX 15 caracteres
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).sendKeys("validacioncampo");
                System.out.println("Campo nombre y apellido de contacto de emergencia llenado\n");
                //  Valida que se muestre el mensaje de 15 caracteres permitidos T51
                validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");
                if (validation.equals("Máximo de 15 caracteres permitidos")) {
                    System.out.println("Validación correcta, se muestra el mensaje de 15 carateres permitidos\n");
                    report.testPassed("Valida que se muestre el mensaje de 15 caracteres permitidos", true);
                } else {
                    System.out.println("Validación error, NO se muestra el mensaje de 15 caracteres permitidos\n");
                    report.testFailed("Valida que se muestre el mensaje de 15 caracteres permitidos", true);
                }

                //  Limpia el campo nombre y apellido de emergencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).clear();
                System.out.println("Campo nombre y apellido de contacto de emergencia limpiado\n");

                // Click en el campo código de país
                GeneratedUtils.sleep(500);
                new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(138,2087)).release().perform();
                System.out.println("Click en el campo código de país\n");


                //  Valida que al seleccionar el Nombre del País se muestre su código telefónico T52
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryCode");
                driver.findElement(by).sendKeys("Panam");
                System.out.println("Nombre de País llenado");
                Thread.sleep(1000);
                //  Selecciona el país del dropdwon
                GeneratedUtils.sleep(500);
                by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]");
                driver.findElement(by).click();
                System.out.println("Dropdown seleccionado");
                Thread.sleep(5000);
                //  Valida que el campo contenga el código del país seleccionado
                validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/countryCode")).getAttribute("text");
                if (validation.equals("+507")) {
                    System.out.println("Validación correcta, se muestra el código de país correcto\n");
                    report.testPassed("Valida que se muestre el Código del país seleccionado", true);
                } else {
                    System.out.println("Validación error, NO se muestra el código de país correcto\n");
                    report.testFailed("Valida que se muestre el Código del país seleccionado", true);
                }

                //  Limpia el campo código de país
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryCode");
                driver.findElement(by).clear();
                System.out.println("Campo código de país limpiado");

                // Click en atrás
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/back");
                driver.findElement(by).click();
                System.out.println("Click en atrás");

                // Pausa por carga
                Thread.sleep(1000);
            } else if (passenger.equals("infant")) {
                //    Swipes para ubicar la pantalla y ver que no se muestran los campos de contacto de emergencia
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Screenshot donde no se visualizan los campos de contacto de emergencia en un infante
                report.testPassed("Valida que no se muestran los campos de contacto de emergencia en un infante", true);

            } else {    //  Validaciones del T53
                //    Swipes para ubicar la pantalla y ver que no se muestran los campos de contacto de emergencia
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Screenshot donde no se visualizan los campos de contacto de emergencia en un vuelo doméstico
                report.testPassed("Valida que no se muestran los campos de contacto de emergencia en un vuelo DOMÉSTICO", true);
            }

            System.out.println("validateT47_T54 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validate47_T54 finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del T55 al T56
     * @param adult contiene el texto del nombre del pasajero adulto para ubicarlo en las validaciones
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateT55_T56(String adult, Report report){
        By by;
        Booking booking = new Booking(driver);
        String direct1 = "arriba", direct2 = "abajo", validation, change;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
                //  Swipe para ubicar el campo correo en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Screenshot y extracción el campo correo para verificar luego el cambio
                report.testPassed("Valida el campo correo antes de hacer un cambio", true);
                validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).getAttribute("text");

                //  Limpia el campo de correo
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/email");
                driver.findElement(by).clear();
                System.out.println("Campo correo limpiado");

                //  Llena el campo de correo
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/email");
                driver.findElement(by).sendKeys("cambiodecorreo@gmail.com");
                System.out.println("Campo correo llenado");

                // Click en Guardar
                GeneratedUtils.sleep(500);
                by = By.xpath("//android.widget.TextView[@text = 'Guardar']");
                driver.findElement(by).click();
                System.out.println("Click en Guardar");

                // Pausa por carga de guardado
                Thread.sleep(18000);

                //  Swipe para actualizar
                //swipeToAdultChildInfant(PanelTripDetails, driver, direct1);
                //Thread.sleep(10000);

                //  Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla
                //swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                //swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

                //  Click al pasajero adulto para validar los campos
                Thread.sleep(2000);
                clickEditInformation(adult);
                Thread.sleep(5000);

                //  Swipe para ubicar el campo correo en pantalla
                PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Screenshot, extracción y validación de los cambios en el campo correo
                change = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/email")).getAttribute("text");
                if(validation != change){
                    System.out.println("Validación correcta, información guardada sin problemas");
                    report.testPassed("Valida el cambio realizado en el campo correo", true);
                }else{
                    System.out.println("Validación incorrecta, información no guardada");
                    report.testFailed("Valida el cambio realizado en el campo correo", true);
                }


            System.out.println("validateT55_T56 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateT55_T56 finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del T57
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateT57(Report report){
        By by;
        String direct1 = "arriba", direct2 = "abajo";
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            //  Swipe para ubicar los campos nombre y apellido en pantalla
            Thread.sleep(500);
            //booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            //  Limpia el campo de nombre
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/firstName");
            driver.findElement(by).clear();
            System.out.println("Campo nombre limpiado\n");

            //  Llena el campo con un nombre que tenga acento
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/firstName");
            driver.findElement(by).sendKeys("Saúl");
            System.out.println("Campo nombre llenado\n");


            //  Limpia el campo de apellido
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).clear();
            System.out.println("Campo apellido limpiado\n");

            //  Llena el campo con un apellido que tenga acento
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys("Díaz");
            System.out.println("Campo apellido llenado\n");

            //  Screenshot para validar que perminte agregar correctamente acentos en los campos
            report.testPassed("Valida que permita agregar acentos en el TIF", true);

            System.out.println("validateT57 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateT57 finalizado con error\n");
            report.testFailed("Valida que permita agregar acentos en el TIF", true);
        }
    }

    /**
     * Realiza las validaciones del T58 al T61
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateT58_T61(Report report){
        By by;
        String direct2 = "abajo", validation;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{

                //    Swipes para ubicar los campos de contacto de emergencia
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Limpia el campo de nombre de contacto de emergencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).clear();
                System.out.println("Campo nombre de contacto de emergencia limpiado\n");

                //  Llena y valida que el nombre y apellido de contacto de emergencia sólo pueda tener MAX 15 caracteres
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).sendKeys("validacioncampo");
                System.out.println("Campo nombre de contacto de emergencia llenado\n");
                //  Valida que se muestre el mensaje de 15 caracteres permitidos T51
                validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");
                if (validation.equals("Máximo de 15 caracteres permitidos")) {
                    System.out.println("Validación correcta, se muestra el mensaje de 15 carateres permitidos\n");
                    report.testPassed("Valida que se muestre el mensaje de 15 caracteres permitidos", true);
                } else {
                    System.out.println("Validación error, NO se muestra el mensaje de 15 caracteres permitidos\n");
                    report.testFailed("Valida que se muestre el mensaje de 15 caracteres permitidos", true);
                }

                //  Limpia el campo nombre y apellido de emergencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).clear();
                System.out.println("Campo nombre y apellido de emergencia limpiado\n");

                //  Llena y valida que el nombre y apellido de contacto de emergencia para validar que no admita los caracateres @*+&%
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).sendKeys("@*+&%");
                //  Valida que se muestre el mensaje de 15 caracteres permitidos T51
                validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");
                if (validation.equals("Formato Inválido")) {
                    System.out.println("Validación correcta, se muestra el mensaje de Formato Inválido\n");
                    report.testPassed("Valida que se muestre el mensaje de Formato Inválido", true);
                } else {
                    System.out.println("Validación error, NO se muestra el mensaje de Formato Inválido\n");
                    report.testFailed("Valida que se muestre el mensaje de Formato Inválido", true);
                }

                //  Limpia el campo nombre y apellido de emergencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).clear();
                System.out.println("Campo nombre y apellido de emergencia limpiado\n");

                //  Llena y valida que el nombre y apellido de contacto de emergencia con mas de 15 caracteres para validar que sólo deje agregar 15
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/emergencyContactName");
                driver.findElement(by).sendKeys("validacioncampos");
                //  Valida que se muestre el mensaje de 15 caracteres permitidos T51
                validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");
                if (validation.length() > 15) {
                    System.out.println("Validación correcta, sólo permite ingresar 15 caracteres\n");
                    report.testPassed("Valida que no permita ingresar más de 15 caracteres", true);
                } else {
                    System.out.println("Validación error, permite ingresar más de 15 caracteres\n");
                    report.testFailed("Valida que no permita ingresar más de 15 caracteres", true);
                }

                //  Click atrás
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/back");
                driver.findElement(by).click();
                System.out.println("Click en atrás");


            System.out.println("validateT58_T61 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validate58_61 finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del T62 al T65
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param validation Indica el tipo de validación que se realiza
     */
    public void validateT62_T65(Report report, String validation){
        By by;
        String direct1 = "arriba", direct2 = "abajo", message;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            System.out.println("validateT62_T65 inicio\n");
            if(validation.equals("regular apis")){
                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/cta");
                driver.findElement(by).click();
                System.out.println("Click en Guardar\n");

                //  Swipe para mostrar los elementos a validar en pantalla
                swipeFormTIF(PanelTripDetails, driver, direct2);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");

                //  Valida si el mensaje de error se obtuvo correctamente
                if(message.equals("Campo requerido")){
                    System.out.println("Validación correcta, se muestran los mensajes de error en pantalla\n");
                    report.testPassed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }else{
                    System.out.println("Validación incorrecta, NO se muestran los mensajes de error en pantalla\n");
                    report.testFailed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }

                //  Click atrás
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/back");
                driver.findElement(by).click();
                System.out.println("Click en atrás\n");
                Thread.sleep(2000);

            } else if (validation.equals("green card")) {
                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/cta");
                driver.findElement(by).click();
                System.out.println("Click en Guardar\n");

                //  Swipe para mostrar los elementos a validar en pantalla
                swipeFormTIF(PanelTripDetails, driver, direct2);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");

                //  Valida si el mensaje de error se obtuvo correctamente
                if(message.equals("Campo requerido")){
                    System.out.println("Validación correcta, se muestran los mensajes de error en pantalla\n");
                    report.testPassed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }else{
                    System.out.println("Validación incorrecta, NO se muestran los mensajes de error en pantalla\n");
                    report.testFailed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }

                //  Limpia el campo de país de residencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).clear();
                System.out.println("Campo de país de residencia limpiado\n");

                //  Llena el campo país de residencia para mostrar el extended apis green card
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                System.out.println("Campo de país de residencia llenado\n");

                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/cta");
                driver.findElement(by).click();
                System.out.println("Click en Guardar\n");

                //  Swipe para mostrar los campos de green card
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");

                //  Valida si el mensaje de error se obtuvo correctamente
                if(message.equals("Campo requerido")){
                    System.out.println("Validación correcta, se muestran los mensajes de error en pantalla\n");
                    report.testPassed("Valida que se muestre mensaje de error campo requerido green card", true);
                }else{
                    System.out.println("Validación incorrecta, NO se muestran los mensajes de error en pantalla\n");
                    report.testFailed("Valida que se muestre mensaje de error campo requerido green card", true);
                }

                //  Click atrás
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/back");
                driver.findElement(by).click();
                System.out.println("Click en atrás\n");
                Thread.sleep(2000);

            }else {
                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/cta");
                driver.findElement(by).click();
                System.out.println("Click en Guardar\n");

                //  Swipe para mostrar los elementos a validar en pantalla
                swipeFormTIF(PanelTripDetails, driver, direct2);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");

                //  Valida si el mensaje de error se obtuvo correctamente
                if(message.equals("Campo requerido")){
                    System.out.println("Validación correcta, se muestran los mensajes de error en pantalla\n");
                    report.testPassed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }else{
                    System.out.println("Validación incorrecta, NO se muestran los mensajes de error en pantalla\n");
                    report.testFailed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }

                //  Limpia el campo de país de residencia
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).clear();
                System.out.println("Campo de país de residencia limpiado\n");

                //  Llena el campo país de residencia para mostrar el extended apis where are you staying?
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/countryOfResidence");
                driver.findElement(by).sendKeys("Panamá");
                System.out.println("Campo país de residencia llenado\n");

                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtils.sleep(500);
                by = By.id("com.copaair.copaAirlines.dev:id/cta");
                driver.findElement(by).click();
                System.out.println("Click en Guardar\n");

                //  Swipe para mostrar los campos de where are you staying?
                swipeFormTIF(PanelTripDetails, driver, direct2);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
                booking.swipeSmall(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");

                //  Valida si el mensaje de error se obtuvo correctamente
                if(message.equals("Campo requerido")){
                    System.out.println("Validación correcta, se muestran los mensajes de error en pantalla\n");
                    report.testPassed("Valida que se muestre mensaje de error campo requerido where are you staying?", true);
                }else{
                    System.out.println("Validación incorrecta, NO se muestran los mensajes de error en pantalla\n");
                    report.testFailed("Valida que se muestre mensaje de error campo requerido where are you staying?", true);
                }
            }

            System.out.println("validateT62_T65 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateT62_T65 finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del T66
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void validateT66(Report report){
        By by;
        String direct2 = "abajo", direct1 = "arriba", name, lastname;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{
            //  Swipe para ubicar los elementos en pantalla
            Thread.sleep(2000);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            //  Obtiene el nombre y apellido para validar después
            name = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/firstName")).getText();
            lastname = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/lastName")).getText();
            System.out.println("Elementos nombre y apellido guardados\n");

            //  Captura para ver el nombre y apellido originales
            report.testPassed("Valida como estaban los campos antes de llenar la información", true);

            //  Click en seleccionar perfil connectmiles para cambiarlo
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/selectProfile");
            driver.findElement(by).click();
            System.out.println("Click en seleccionar perfil connectmiles\n");

            //  Click en el pasajero adicional
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/passportInformationTitle");
            driver.findElement(by).click();
            System.out.println("Click en pasajero adicional\n");

            //  Swipe hacia arriba para ubicar los elementos en pantalla
            Thread.sleep(2000);
            swipeFormTIF(PanelTripDetails, driver, direct1);
            swipeFormTIF(PanelTripDetails, driver, direct1);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct1);


            //  Valida si se autopopulo el nombre y apellido
            if (name != driver.findElement(By.id("com.copaair.copaAirlines.dev:id/firstName")).getText() & lastname != driver.findElement(By.id("com.copaair.copaAirlines.dev:id/lastName")).getText()){
                System.out.println("Validación correcta, se cambio el nombre y apellido por el del pasajero adicional\n");
                report.testPassed("Valida que se llenen los campos con la información del pasajero adicional", true);
            }else {
                System.out.println("Validación error, NO se cambio el nombre y apellido por el del pasajero adicional\n");
                report.testFailed("Valida que se llenen los campos con la información del pasajero adicional", true);
            }

            System.out.println("validateT66 finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateT66 finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del CountryPhoneNumbers
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param countryCode contiene texto login o no login para saber si en la ejecución se está logueado en el app
     * @param phoneNumber1 Indica si el pasajero es adulto o infante
     * @param phoneNumber2 Indica si el pasajero es adulto o infante
     * @param phoneNumber3 Indica si el pasajero es adulto o infante
     */
    public void validateCountryPhoneNumbers(Report report, String countryCode, String phoneNumber1, String phoneNumber2, String phoneNumber3){
        By by;
        String direct2 = "abajo", validation;
        Boolean noMessage;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{

            /** Validación para los países **/

            //  Limpia y llena el código de país
            GeneratedUtils.sleep(500);
            //new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(138,1592)).release().perform();
            by = By.id("com.copaair.copaAirlines.dev:id/countryCode");
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(countryCode);
            System.out.println("Campo código de país limpiado y llenado\n");

            //  Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/phoneNumber");
            driver.findElement(by).clear();
            // llena menos del mínimo requerido
            driver.findElement(by).sendKeys(phoneNumber1);
            System.out.println("Campo número de telefono limpiado y llenado\n");

            // Valida que se muestra el mensaje de formato inválido
            validation = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Escribe Número de Teléfono\"]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")).getAttribute("text");
            if(validation.equals("Formato Inválido")){
                System.out.println("Se muestra el mensaje de error con menos del mínimo requerido\n");
                report.testPassed("Valida que se muestra el mensaje de error con menos del mínimo requerido", true);
            }else {
                System.out.println("ERROR, NO se muestra el mensaje de error con menos del mínimo requerido\n");
                report.testFailed("Valida que se muestra el mensaje de error con menos del mínimo requerido", true);
            }

            //  Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/phoneNumber");
            driver.findElement(by).clear();
            // llena el máximo requerido
            driver.findElement(by).sendKeys(phoneNumber2);
            System.out.println("Campo número de télefono limpiado y llenado\n");

            // Valida que se muestra el mensaje de formato inválido
            validation = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Escribe Número de Teléfono\"]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")).getAttribute("text");
            if(validation.equals("Formato Inválido")){
                System.out.println("Se muestra el mensaje de error con más del máximo requerido\n");
                report.testPassed("Valida que se muestra el mensaje de error con más del máximo requerido\n", true);
            }else {
                System.out.println("ERROR, NO se muestra el mensaje de error con más del máximo requerido\n");
                report.testFailed("Valida que se muestra el mensaje de error con más del máximo requerido\n", true);
            }

            // Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/phoneNumber");
            driver.findElement(by).clear();
            // llena la cantidad correcta de números
            driver.findElement(by).sendKeys(phoneNumber3);
            System.out.println("Campo número de télefono limpiado y llenado\n");

            // Valida que no se muestre el mensaje de error
            if(driver.findElements(By.xpath("//android.widget.LinearLayout[@content-desc=\"Escribe Número de Teléfono\"]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")).isEmpty()) {
                System.out.println("Validación correcta, no se muestra el mensaje de error\n");
                report.testPassed("Valida que NO se muestra el mensaje de error\n", true);
            } else {
                System.out.println("Validación incorrecta, se muestra el mensaje de error\n");
                report.testFailed("Valida que NO se muestra el mensaje de error\n", true);
            }

            /* Valida que no se muestre el mensaje de error
            if(driver.findElements(By.xpath("//android.widget.LinearLayout[@content-desc=\"Escribe Número de Teléfono\"]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")).isEmpty()) {
                System.out.println("Validación correcta, no se muestra el mensaje de error\n");
            } else {
                System.out.println("Validación incorrecta, se muestra el mensaje de error\n");
            }*/

            System.out.println("validateCountryPhoneNumbers finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateCountryPhoneNumbers finalizado con error\n");
            System.out.println("el error es \n"+ex);
        }
    }

    public void validateCountryPhoneNumbersMyProfile(Report report, String countryCode, String phoneNumber1, String phoneNumber2, String phoneNumber3){
        By by;
        String direct2 = "abajo", validation;
        Boolean noMessage;
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        try{

            /** Validación para los países **/

            //  Limpia y llena el código de país
            GeneratedUtils.sleep(500);
            //new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(138,1592)).release().perform();
            by = By.id("com.copaair.copaAirlines.dev:id/countryCode");
            driver.findElement(by).clear();
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/countryCode")).click();
            driver.findElement(by).sendKeys(countryCode);
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/group_edit_travel_information")).click();
            System.out.println("Campo código de país limpiado y llenado\n");

            //  Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/phoneNumber");
            driver.findElement(by).clear();
            // llena menos del mínimo requerido
            driver.findElement(by).sendKeys(phoneNumber1);
            System.out.println("Campo número de télefono limpiado y llenado\n");

            // Valida que se muestra el mensaje de formato inválido
            validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");
            if(validation.equals("Formato inválido")){
                System.out.println("Se muestra el mensaje de error con menos del mínimo requerido\n");
                report.testPassed("Valida que se muestra el mensaje de error con menos del mínimo requerido", true);
            }else {
                System.out.println("ERROR, NO se muestra el mensaje de error con menos del mínimo requerido\n");
                report.testFailed("Valida que se muestra el mensaje de error con menos del mínimo requerido", true);
            }

            //  Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/phoneNumber");
            driver.findElement(by).clear();
            // llena el máximo requerido
            driver.findElement(by).sendKeys(phoneNumber2);
            System.out.println("Campo número de télefono limpiado y llenado\n");

            // Valida que se muestra el mensaje de formato inválido
            validation = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).getAttribute("text");
            if(validation.equals("Formato inválido")){
                System.out.println("Se muestra el mensaje de error con más del máximo requerido\n");
                report.testPassed("Valida que se muestra el mensaje de error con más del máximo requerido\n", true);
            }else {
                System.out.println("ERROR, NO se muestra el mensaje de error con más del máximo requerido\n");
                report.testFailed("Valida que se muestra el mensaje de error con más del máximo requerido\n", true);
            }

            // Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/phoneNumber");
            driver.findElement(by).clear();
            // llena la cantidad correcta de números
            driver.findElement(by).sendKeys(phoneNumber3);
            System.out.println("Campo número de télefono limpiado y llenado\n");

            // Valida que no se muestre el mensaje de error
            if(driver.findElements(By.id("com.copaair.copaAirlines.dev:id/textinput_error")).isEmpty()) {
                System.out.println("Validación correcta, no se muestra el mensaje de error\n");
                report.testPassed("Valida que NO se muestra el mensaje de error\n", true);
            } else {
                System.out.println("Validación incorrecta, se muestra el mensaje de error\n");
                report.testFailed("Valida que NO se muestra el mensaje de error\n", true);
            }

            System.out.println("validateCountryPhoneNumbersMyProfile finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateCountryPhoneNumbersMyProfile finalizado con error\n");
            System.out.println("el error es \n"+ex);
        }
    }

}

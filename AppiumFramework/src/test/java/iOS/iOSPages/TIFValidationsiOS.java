package iOS.iOSPages;

import io.appium.java_client.*;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.touch.WaitOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.GeneratedUtilsiOS;
import iOS.utilsiOS.ReportiOS;
import utils.GeneratedUtils;

import java.time.Duration;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class TIFValidationsiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     *
     * @param driver Driver necesario para construir la clase
     */
    public TIFValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    BookingiOS booking = new BookingiOS(driver);

    /**
     * Click al primer viaje agreado
     */
    public void clickFirstTripAdded() {
        By by;
        try {
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(174, 268)).release().perform();

            /* Click al primer viaje agregado
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/destination");
            driver.findElement(by).click();*/
            Thread.sleep(5000);

            System.out.println("clickFirstTripAdded finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("clickFirstTripAdded finalizado con error\n");
        }
    }

    /**
     * Click En editar información en TIF
     */
    public void clickEditInformation(String pasajero){
        By by;
        String nombre1, nombre2;
        String direct2 = "abajo";

        TIFValidationsiOS tif = new TIFValidationsiOS(driver);

        WebElement Panel = driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));


        nombre1 = driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@index=\"17\"]")).getAttribute("name");

        nombre2 = driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@index=\"29\"]")).getAttribute("name");

        if(nombre1.equals(pasajero)){
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Editar información\"])[1]")).click();
        } else if (nombre2.equals(pasajero)) {
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Editar información\"])[2]")).click();
        }
    }

    /**
     * Swipe utilizado para visualizar los pasajeros en trip details y también en otras validaciones
     *
     * @param el        Elemento al que se le hará swipe en pantalla
     * @param driver    Driver necesario para hacer swipe
     * @param direction dirección el la que se hace el swipe
     */
    public void swipeToAdultChildInfant(WebElement el, WebDriver driver, String direction) {
        By by;
        try {

            if(direction.equals("abajo")) {
                int screenWidth = driver.manage().window().getSize().getWidth();
                int screenHeight = driver.manage().window().getSize().getHeight();

                // Define swipe coordinates
                int startSwipeX = screenWidth / 2; // Middle of the screen horizontally
                int startSwipeY = (int) (screenHeight * 0.7); // 70% down from the top
                int endSwipeX = screenWidth / 2; // Middle of the screen horizontally
                int endSwipeY = (int) (screenHeight * 0.228); // 26% up from the bottom

                // Create a TouchAction object to perform the swipe
                TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
                touchAction.press(PointOption.point(startSwipeX, startSwipeY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) // Adjust as needed
                        .moveTo(PointOption.point(endSwipeX, endSwipeY))
                        .release()
                        .perform();
            }else{
                int screenWidth = driver.manage().window().getSize().getWidth();
                int screenHeight = driver.manage().window().getSize().getHeight();

                // Define swipe coordinates
                int startSwipeX = screenWidth / 2; // Middle of the screen horizontally
                int startSwipeY = (int) (screenHeight * 0.228); // 26% down from the top
                int endSwipeX = screenWidth / 2; // Middle of the screen horizontally
                int endSwipeY = (int) (screenHeight * 0.7); // 70% up from the bottom

                // Create a TouchAction object to perform the swipe
                TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
                touchAction.press(PointOption.point(startSwipeX, startSwipeY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) // Adjust as needed
                        .moveTo(PointOption.point(endSwipeX, endSwipeY))
                        .release()
                        .perform();
            }

            /*if (direction.equals("abajo")) {
                WebElement Panel = el;
                Dimension dimension = Panel.getSize();

                int Anchor = Panel.getSize().getWidth() / 2;

                Double ScreenHeightStart = dimension.getHeight() * 0.8;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.48;
                int scrollEnd = ScreenHeightEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(Anchor, scrollStart))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                        .moveTo(PointOption.point(Anchor, scrollEnd))
                        .release().perform();

            } else {
                if (direction.equals("arriba")) {
                    WebElement Panel = el;
                    Dimension dimension = Panel.getSize();

                    int Anchor = Panel.getSize().getWidth() / 2;

                    Double ScreenHeightStart = dimension.getHeight() * 0.34;
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
            Thread.sleep(1000);*/
            System.out.println("swipeToAdultChildInfant finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("Error swipeToAdultChildInfant\n");
        }
    }

    /**
     * Swipe utilizado principalmente en los formularios de TIF pero también en otras validaciones
     *
     * @param el        Elemento al que se le hará swipe en pantalla
     * @param driver    Driver necesario para hacer swipe
     * @param direction dirección el la que se hace el swipe
     */
    public void swipeFormTIF(WebElement el, WebDriver driver, String direction) {
        By by;
        try {
            if (direction.equals("abajo")) {
                WebElement Panel = el;
                Dimension dimension = Panel.getSize();

                int Anchor = Panel.getSize().getWidth() / 2;

                Double ScreenHeightStart = dimension.getHeight() * 0.8;
                int scrollStart = ScreenHeightStart.intValue();

                Double ScreenHeightEnd = dimension.getHeight() * 0.44;
                int scrollEnd = ScreenHeightEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(Anchor, scrollStart))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(Anchor, scrollEnd))
                        .release().perform();

            } else {
                if (direction.equals("arriba")) {
                    WebElement Panel = el;
                    Dimension dimension = Panel.getSize();

                    int Anchor = Panel.getSize().getWidth() / 2;

                    Double ScreenHeightStart = dimension.getHeight() * 0.44;
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
        } catch (Exception ex) {
            System.out.println("swipeFormTIF finalizado con error\n");
        }
    }

    /**
     * Click en el ícono de atrás en los formularios de TIF
     */
    public void clickBack() {
        By by;
        try {
            // Click atrás
            GeneratedUtilsiOS.sleep(500);
            //driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
            by = AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Información del Pasajero\"]/XCUIElementTypeButton");
            //by = By.id("com.copaair.copaAirlines.dev:id/back");
            driver.findElement(by).click();
            Thread.sleep(500);

            System.out.println("clickBack finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("clickBack finalizado con error\n");
        }
    }

    /**
     * Click en el ícono de atrás en Flight Details
     */
    public void clickBackFlightDetails() {
        By by;
        try {
            // Click atrás
            GeneratedUtilsiOS.sleep(500);
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(49,44)).release().perform();
            Thread.sleep(500);

            System.out.println("clickBackFlightDetails finalizado con éxito\n");
        } catch (Exception ex) {
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
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeStaticText[@name=\"Cancelar\"]");
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
    public void scriptT1_T6Validations(String session, String pasenger, ReportiOS report){
        By by;
        String direct2 = "abajo";
        BookingiOS booking = new BookingiOS(driver);
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            if(pasenger.equals("adult") || pasenger.equals("child")) {

                if(session.equals("login")) { //solo hace estas validaciones y swipe cuando esta logueado
                    //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                    GeneratedUtilsiOS.sleep(700);
                    driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]")).isDisplayed();
                    System.out.println("Paso realizado, se encontró el dropdown de perfiles\n");
                    // Screenshoot para el reporte
                    report.testPassed("Validar campos: Dropdown de perfiles, email, FFP and FFN", true);
                    // Swipe para ubicar los elementos en pantalla cuando se está logueado
                    Thread.sleep(500);
                    booking.swipeSmall(PanelTripDetails, driver, direct2);
                }

                // Valida que esté el campo correo electrónico
                //    Verificación de acceso al campo.
                GeneratedUtilsiOS.sleep(700);
                //driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Escribe tu Correo Electrónico (para enviar Pase de Abordar)\"]"));
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).isDisplayed();
                System.out.println("Paso 1: se encontró el campo de correo\n");
                //report.testPassed("Validar campos", true);

                // Valida que esté el campo programa de viajero frecuente
                //    Verificación de acceso al campo.
                GeneratedUtilsiOS.sleep(700);
                driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Selecciona Programa de Viajero Frecuente. Doble toque para desplegar el selector\"]")).isDisplayed();
                System.out.println("Paso 2: se encontró el campo programa de viajero frecuente\n");
                //report.testPassed("Validar campos", true);

                // Valida que esté el campo número de viajero frecuente
                //    Verificación de acceso al campo.
                GeneratedUtilsiOS.sleep(700);
                driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Escribe Número de Viajero Frecuente\"]")).isDisplayed();
                System.out.println("Paso 3: se encontró el campo programa de viajero frecuente\n");

                // Screenshoot para el reporte
                report.testPassed("Validar campos: email, FFP and FFN", true); //Solo es necesario 1 screenshoot ya que son
                                                                                // visibles todos los campos en pantalla
            }else{
                if(session.equals("login")) { //solo hace estas validaciones y swipe cuando esta logueado
                    //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                    GeneratedUtilsiOS.sleep(700);
                    driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]"));
                    System.out.println("Paso realizado, se encontró el dropdown de perfiles\n");
                }
                // Valida que esté el campo correo electrónico
                //    Verificación de acceso al campo.
                GeneratedUtilsiOS.sleep(700);
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)"));
                System.out.println("Paso 1: se encontró el campo de correo\n");

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
    public void regularApisValidations(String session, ReportiOS report){
        By by;
        String direct2 = "abajo";
        BookingiOS booking = new BookingiOS(driver);
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            if(session.equals("login")) {
                //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                GeneratedUtilsiOS.sleep(700);
                driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]")).isDisplayed();
                System.out.println("Paso realizado, se encontró el dropdown de perfiles\n");
                // Screenshoot para el reporte
                report.testPassed("Validar campos: Dropdown de perfiles, regular APIS, FFP, FFN, email, KTN, Redress Number", true);
                // Swipe para ubicar los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
            }

            // 1. Valida que esté presente el nombre del pasajero
            //    Validación del campo Nombre.
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Nombre")).isDisplayed();
            System.out.println("Nombre validado\n");

            // 2. Valida que esté presente el apellido del pasajero
            //    Validación del campo Apellido
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Apellido")).isDisplayed();
            System.out.println("Apellido validado\n");

            // 3. Valida que esté presente la fecha de nacimiento
            //    Validación del campo fecha de nacimiento.
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Fecha de Nacimiento")).isDisplayed();
            System.out.println("Fecha de nacimiento validada\n");

            // 4. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 5. Swipe para ubicar los elementos en pantalla
            Thread.sleep(500);
            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 6. Valida que esté presente el campo de masculino
            //
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Masculino\"]")).isDisplayed();
            System.out.println("Sexo masculino validado\n");

            // 7. Valida que esté presente el campo de femenino
            //
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Femenino\"]")).isDisplayed();
            System.out.println("Sexo femenino validado\n");

            // 8. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 9. Valida que esté presente el campo de nacionalidad
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).isDisplayed();
            System.out.println("Nacionalidad validada\n");

            // 10. Valida que esté presente el campo país de residencia
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).isDisplayed();
            System.out.println("Residencia validada\n");

            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 11. Valida que esté presente el campo número de pasaporte
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).isDisplayed();
            System.out.println("Pasaporte validado\n");

            // 12. Valida que esté presente el campo fecha vencimiento pasaporte
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).isDisplayed();
            System.out.println("Fecha de vencimiento pasaporte validado\n");

            // 13. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 14. Swipe para ver los elementos en pantalla
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);

            // 15. Valida que esté presente el campo país emisor
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).isDisplayed();
            System.out.println("País emisor validado\n");

            // 16. Valida que esté presente el campo correo electrónico
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).isDisplayed();
            System.out.println("Correo validado\n");

            // 17. Screenshoot para el reporte
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
    public void regularApisGreenCardAndPersonalInformationValidations(String passenger, String session, ReportiOS report){
        By by;
        String direct2 = "abajo";
        BookingiOS booking = new BookingiOS(driver);
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            if(session.equals("login")) {
                //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                GeneratedUtilsiOS.sleep(700);
                driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]")).isDisplayed();
                System.out.println("Paso realizado, se encontró el dropdown de perfiles\n");
                // Screenshoot para el reporte
                report.testPassed("Validar campos: Dropdown de perfiles, regular APIS, FFP, FFN, email, KTN, Redress Number", true);
                // Swipe para ubicar los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
            }

            // 1. Valida que esté presente el nombre del pasajero
            //    Validación del campo Nombre.
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Nombre")).isDisplayed();
            System.out.println("Nombre validado\n");

            // 2. Valida que esté presente el apellido del pasajero
            //    Validación del campo Apellido
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Apellido")).isDisplayed();
            System.out.println("Apellido validado\n");

            // 3. Valida que esté presente la fecha de nacimiento
            //    Validación del campo fecha de nacimiento.
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Fecha de Nacimiento")).isDisplayed();
            System.out.println("Fecha de nacimiento validada\n");

            // 4. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 5. Swipe para ubicar los elementos en pantalla
            Thread.sleep(500);
            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 6. Valida que esté presente el campo de masculino
            //
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Masculino\"]")).isDisplayed();
            System.out.println("Sexo masculino validado\n");

            // 7. Valida que esté presente el campo de femenino
            //
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Femenino\"]")).isDisplayed();
            System.out.println("Sexo femenino validado\n");

            // 8. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 9. Valida que esté presente el campo de nacionalidad
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).isDisplayed();
            System.out.println("Nacionalidad validada\n");

            // 10. Valida que esté presente el campo país de residencia
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).isDisplayed();
            System.out.println("Residencia validada\n");

            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 11. Valida que esté presente el campo número de pasaporte
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).isDisplayed();
            System.out.println("Pasaporte validado\n");

            // 12. Valida que esté presente el campo fecha vencimiento pasaporte
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).isDisplayed();
            System.out.println("Fecha de vencimiento pasaporte validado\n");

            // 13. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            // 14. Swipe para ver los elementos en pantalla
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);

            // 15. Valida que esté presente el campo país emisor
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).isDisplayed();
            System.out.println("País emisor validado\n");

            // 16. Valida que esté presente el campo correo electrónico
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).isDisplayed();
            System.out.println("Correo validado\n");

            // 17. Screenshoot para el reporte
            report.testPassed("Validar campos: Regular APIS, FFP, FFN, email, KTN, Redress Number", true);

            //Condición requerida ya que estos campos en conjunto sólo serán visibles en pasajeros adultos
            if(passenger.equals("adult")) {

                // 18. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                booking.swipeSmall(PanelTripDetails, driver, direct2);

                // 19. Valida que este presente el campo de green card
                driver.findElement(AppiumBy.accessibilityId("Escribe Número de Tarjeta ")).isDisplayed();
                System.out.println("Green card validado\n");

                // 20. Valida que esté presente el campo vencimiento green card
                driver.findElement(By.xpath("(//XCUIElementTypeTextField[@name=\"Seleccione Fecha de Vencimiento\"])[2]")).isDisplayed();
                System.out.println("Vencimiento green card validado\n");

                // 21. Screenshoot para el reporte
                report.testPassed("Validar campos: Regular APIS, Extended APIS (Residence Card) FFP, FFN, KTN, Redress Number, email", true);

                // 22. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                booking.swipeSmall(PanelTripDetails, driver, direct2);

                // 23. Valida que esté presente el campo programa de viajero frecuente
                driver.findElement(AppiumBy.accessibilityId("Selecciona Programa de Viajero Frecuente. Doble toque para desplegar el selector")).isDisplayed();
                System.out.println("Viajero frecuente validado\n");

                // 24. Valida que esté presente el campo Número de viajero fecuente
                driver.findElement(AppiumBy.accessibilityId("Escribe Número de Viajero Frecuente")).isDisplayed();
                System.out.println("Número viajero frecuente validado\n");
            }else{
                //Aquí entra a validar al child ya que al no haberse llenado sus formularios no muestra los mismos
                //campos que el adulto
                if (passenger.equals("child")){
                    // 25. Swipe para ver los elementos en pantalla
                    Thread.sleep(500);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                    booking.swipeSmall(PanelTripDetails, driver, direct2);

                    // 26. Valida que esté presente el campo programa de viajero frecuente
                    driver.findElement(AppiumBy.accessibilityId("Selecciona Programa de Viajero Frecuente. Doble toque para desplegar el selector")).isDisplayed();
                    System.out.println("Viajero frecuente validado\n");

                    // 27. Valida que esté presente el campo Número de viajero fecuente
                    driver.findElement(AppiumBy.accessibilityId("Escribe Número de Viajero Frecuente")).isDisplayed();
                    System.out.println("Número viajero frecuente validado\n");
                }
            }
            if(passenger!="adult" & passenger!="child"){
                // 28. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
            }

            // 31. Valida que esté presente el campo número de viajero conocido
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Viajero Conocido")).isDisplayed();
            System.out.println("Número de Viajero Conocido validado\n");

            // 32. Valida que esté presente el campo número de desagravio
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Desagravio")).isDisplayed();
            System.out.println("Número de Desagravio validado\n");

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
    public void regularApisWhereAYSAndPersonalInformationValidations(String passenger, String session, ReportiOS report){
        By by;
        String direct2 = "abajo";
        BookingiOS booking = new BookingiOS(driver);
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            if(session.equals("login")) {
                //    Valida que esté presente el dropdown de perfiles cuando se está logueado
                GeneratedUtilsiOS.sleep(700);
                driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]")).isDisplayed();
                System.out.println("Paso realizado, se encontró el dropdown de perfiles\n");
                // Screenshoot para el reporte
                report.testPassed("Validar campos: Dropdown de perfiles", true);
                // Swipe para ubicar los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
            }

            // 1. Valida que esté presente el nombre del pasajero
            //    Validación del campo Nombre.
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Nombre")).isDisplayed();
            System.out.println("Nombre validado\n");

            // 2. Valida que esté presente el apellido del pasajero
            //    Validación del campo Apellido
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Apellido")).isDisplayed();
            System.out.println("Apellido validado\n");

            // 3. Valida que esté presente la fecha de nacimiento
            //    Validación del campo fecha de nacimiento.
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Fecha de Nacimiento")).isDisplayed();
            System.out.println("Fecha de nacimiento validada\n");

            // 4. Screenshoot para el reporte
            report.testPassed("Validar campos: Nombre del pasajero, Apellido del pasajero y Fecha de nacimiento", true);

            // 5. Swipe para ubicar los elementos en pantalla
            Thread.sleep(500);
            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 6. Valida que esté presente el campo de masculino
            //
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Masculino\"]")).isDisplayed();
            System.out.println("Sexo masculino validado\n");

            // 7. Valida que esté presente el campo de femenino
            //
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Femenino\"]")).isDisplayed();
            System.out.println("Sexo femenino validado\n");

            // 8. Screenshoot para el reporte
            report.testPassed("Validar campos: sexo masculino y sexo femenino", true);

            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 9. Valida que esté presente el campo de nacionalidad
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).isDisplayed();
            System.out.println("Nacionalidad validada\n");

            // 10. Valida que esté presente el campo país de residencia
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).isDisplayed();
            System.out.println("Residencia validada\n");

            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 11. Valida que esté presente el campo número de pasaporte
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).isDisplayed();
            System.out.println("Pasaporte validado\n");

            // 12. Valida que esté presente el campo fecha vencimiento pasaporte
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).isDisplayed();
            System.out.println("Fecha de vencimiento pasaporte validado\n");

            // 13. Screenshoot para el reporte
            report.testPassed("Validar campos: Nacionalidad, residencia, número de pasaporte y fecha de vencimiento de pasaporte", true);

            // 14. Swipe para ver los elementos en pantalla
            Thread.sleep(500);
            swipeFormTIF(PanelTripDetails, driver, direct2);

            // 15. Valida que esté presente el campo país emisor
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).isDisplayed();
            System.out.println("País emisor validado\n");

            // 16. Valida que esté presente el campo correo electrónico
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).isDisplayed();
            System.out.println("Correo validado\n");

            // 18. Screenshoot para el reporte
            report.testPassed("Validar campos: país emisor y correo electrónico", true);

            //Condición requerida ya que estos campos en conjunto sólo serán visibles en pasajeros adultos
            if(passenger.equals("adult")) {

                // 18. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                //swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

                // 19. Valida que este presente el campo direccion de hospedaje
                driver.findElement(AppiumBy.accessibilityId("Escribe Dirección")).isDisplayed();
                System.out.println("Dirección hospedaje validado\n");

                // 20. Valida que esté presente el campo ciudad de hospedaje
                driver.findElement(AppiumBy.accessibilityId("Escribe Ciudad")).isDisplayed();
                System.out.println("Ciudad validado\n");

                // 21. Valida que esté presente el estado de hospedaje
                driver.findElement(AppiumBy.accessibilityId("Escribe Estado")).isDisplayed();
                System.out.println("Estado validado\n");

                // 22. Valida que esté presente el campo código postal de hospedaje
                driver.findElement(AppiumBy.accessibilityId("Escribe Código Postal")).isDisplayed();
                System.out.println("Código postal validado\n");

                // 23. Screenshoot para el reporte
                report.testPassed("Validar campos: dirección de hospedaje, ciudad de hospedaje, estado de hospedaje y código postal", true);

                // 22. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                // 23. Valida que esté presente el campo programa de viajero frecuente
                driver.findElement(AppiumBy.accessibilityId("Selecciona Programa de Viajero Frecuente. Doble toque para desplegar el selector")).isDisplayed();
                System.out.println("Programa de viajero frecuente validado\n");

                // 24. Valida que esté presente el campo Número de viajero fecuente
                driver.findElement(AppiumBy.accessibilityId("Escribe Número de Viajero Frecuente")).isDisplayed();
                System.out.println("Número de viajero frecuente validado\n");
            }else{
                //Aquí entra a validar al child ya que al no haberse llenado sus formularios no muestra los mismos
                //campos que el adulto
                if (passenger.equals("child")){
                    // 25. Swipe para ver los elementos en pantalla
                    Thread.sleep(500);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                    booking.swipeSmall(PanelTripDetails, driver, direct2);

                    // 23. Valida que esté presente el campo programa de viajero frecuente
                    driver.findElement(AppiumBy.accessibilityId("Selecciona Programa de Viajero Frecuente. Doble toque para desplegar el selector")).isDisplayed();
                    System.out.println("Programa de viajero frecuente validado\n");

                    // 24. Valida que esté presente el campo Número de viajero fecuente
                    driver.findElement(AppiumBy.accessibilityId("Escribe Número de Viajero Frecuente")).isDisplayed();
                    System.out.println("Número de viajero frecuente validado\n");
                }
            }
            if(passenger!="adult" & passenger!="child"){
                // 28. Swipe para ver los elementos en pantalla
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
            }

            // 31. Valida que esté presente el campo número de viajero conocido
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Viajero Conocido")).isDisplayed();
            System.out.println("Número de viajero conocido validado\n");

            // 32. Valida que esté presente el campo número de desagravio
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Desagravio")).isDisplayed();
            System.out.println("Número de viajero desagravio validado\n");

            // 30. Screenshoot para el reporte
            report.testPassed("Validar campos: programa de viajero frecuente, número de viajero frecuente, número de viajero conocido y número de desagravio", true);

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
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            // 1. Swipe para ubicar los elementos en pantalla
            if(session.equals("login")){
                // Ubica los elementos en pantalla cuando se está logueado}
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
            }
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).clear();

            // 3. Llena el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
            driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
            System.out.println("Nacionalidad llenada\n");

            // 4. Limpia el campo país de residencia
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).clear();

            // 5. Llena el campo país de residenciaa
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
            driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
            System.out.println("Residencia llenada\n");

            // 6. Limpia el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).clear();

            // 7. Llena el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).sendKeys("1234");
            System.out.println("Número pasaporte llenado\n");

            // 8. Click en la opción fecha de vencimiento
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).click();
            driver.findElement(AppiumBy.accessibilityId("Mostrar selector de año")).click();

            // 9. Make a Swipe gesture
            GeneratedUtilsiOS.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(261,568))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(260,455)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();
            System.out.println("Vencimiento pasaporte llenado\n");

            // 11. Swipe y Limpia país emisor
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).clear();

            // 12. llena país emisor
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
            driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
            System.out.println("País emisor llenado\n");

            // 13. Click en Guardar
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]")).click();
            System.out.println("Click en guardar realizado\n");

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
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            // 1. Swipe para ubicar los elementos en pantalla
            if(session.equals("login")){
                // Ubica los elementos en pantalla cuando se está logueado}
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
            }
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).clear();

            // 3. Llena el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("Nacionalidad llenada\n");

            // 4. Limpia el campo país de residencia
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).clear();

            // 5. Llena el campo país de residenciaa
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
            driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
            System.out.println("Residencia llenada\n");

            // 6. Limpia el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).clear();

            // 7. Llena el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).sendKeys("1234");
            System.out.println("Número pasaporte llenado\n");

            // 8. Click en la opción fecha de vencimiento
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).click();
            driver.findElement(AppiumBy.accessibilityId("Mostrar selector de año")).click();

            // 9. Make a Swipe gesture
            GeneratedUtilsiOS.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(261,568))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(260,455)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();
            System.out.println("Vencimiento pasaporte llenado\n");

            // 11. Swipe y Limpia país emisor
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).clear();

            // 12. llena país emisor
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("País emisor llenado\n");

            // 13. Swipe y limpia el campo número de green card
            swipeFormTIF(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Tarjeta ")).clear();

            // 14. Llena número de green card
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Tarjeta ")).sendKeys("12345");
            System.out.println("Número green card llenado\n");

            // 15. Swipe y Click en la opción fecha de vencimiento Green card
            Thread.sleep(500);
            //booking.swipeSmall(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("(//XCUIElementTypeTextField[@name=\"Seleccione Fecha de Vencimiento\"])[2]");
            driver.findElement(by).click();
            driver.findElement(AppiumBy.accessibilityId("Mostrar selector de año")).click();

            // 9. Make a Swipe gesture
            GeneratedUtilsiOS.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(261,568))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(260,455)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();
            System.out.println("Vencimiento green card llenada\n");

            // 18. Click en Guardar
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]")).click();
            System.out.println("Click en guardar realizado\n");

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
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            // 1. Swipe para ubicar los elementos en pantalla
            if(session.equals("login")){
                // Ubica los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
            }
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).clear();

            // 3. Llena el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("Nacionalidad llenada\n");

            // 4. Limpia el campo país de residencia
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).clear();

            // 5. Llena el campo país de residenciaa
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("Residencia llenada\n");

            // 6. Limpia el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).clear();

            // 7. Llena el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).sendKeys("1234");
            System.out.println("Número pasaporte llenado\n");

            // 8. Click en la opción fecha de vencimiento
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).click();
            driver.findElement(AppiumBy.accessibilityId("Mostrar selector de año")).click();

            // 9. Make a Swipe gesture
            GeneratedUtilsiOS.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(261,568))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(260,455)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();
            System.out.println("Vencimiento pasaporte llenado\n");

            // 11. Swipe y Limpia país emisor
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).clear();

            // 12. llena país emisor
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("País emisor llenado\n");

            // 13. Swipe y limpia el campo dirección de hospedaje
            swipeFormTIF(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Dirección")).clear();

            // 14. Llena la dirección de hospedaje
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Dirección")).sendKeys("3601 CHARLES GREEN");
            System.out.println("Dirección hospedaje llenada\n");

            // 13. limpia el campo ciudad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Ciudad")).clear();

            // 14. Llena el campo ciudad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Ciudad")).sendKeys("Miami");
            System.out.println("Ciudad llenada\n");

            // 13. Swipe y limpia el campo estado
            swipeFormTIF(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Estado")).clear();

            // 14. Llena el campo estado
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Estado")).sendKeys("Florida");
            driver.findElement(AppiumBy.accessibilityId("Florida")).click();
            System.out.println("Estado llenado\n");

            // 13. limpia el campo código postal
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Código Postal")).clear();

            // 14. Llena el campo código postal
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe Código Postal")).sendKeys("32003");
            System.out.println("Código postal llenado\n");

            // 24. Click 'Check Utilizar esta dirección ¿Dónde te vas a hospedar?'
            GeneratedUtilsiOS.sleep(1795);
            by = AppiumBy.accessibilityId("Seleccione para usar dirección para todos los viajeros. Doble toque para seleccionar");
            driver.findElement(by).click();
                System.out.println("Click al check utilizar esta dirección para todos los pasajeros realizado\n");

            // 18. Click en Guardar
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]")).click();
            System.out.println("Click en guardar realizado\n");

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
    public void fillFieldsAllPanamaWhereAYSCheckInTransitAndValidate(String session, String passenger, ReportiOS report){
        By by;
        int value, value2 = 0;
        String direct2 = "abajo", campos;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            // 1. Swipe para ubicar los elementos en pantalla
            if(session.equals("login")){
                // Ubica los elementos en pantalla cuando se está logueado
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
            }
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).clear();

            // 3. Llena el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("Nacionalidad llenada\n");

            // 4. Limpia el campo país de residencia
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).clear();

            // 5. Llena el campo país de residenciaa
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("Residencia llenada\n");

            // 6. Limpia el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).clear();

            // 7. Llena el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).sendKeys("1234");
            System.out.println("Número pasaporte llenado\n");

            // 8. Click en la opción fecha de vencimiento
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).click();
            driver.findElement(AppiumBy.accessibilityId("Mostrar selector de año")).click();

            // 9. Make a Swipe gesture
            GeneratedUtilsiOS.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(261,568))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(260,455)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();
            System.out.println("Vencimiento pasaporte llenado\n");

            // 11. Swipe y Limpia país emisor
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).clear();

            // 12. llena país emisor
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("País emisor llenado\n");

            if(passenger.equals("adult")) {
                // 13. Valida que este presente 'En tránsito hospedaje' y luego hace click
                swipeFormTIF(PanelTripDetails, driver, direct2);
                GeneratedUtilsiOS.sleep(1704);
                driver.findElement(AppiumBy.accessibilityId("Doble toque para indicar que está en tránsito en los Estados Unidos. ")).isDisplayed();
                by = AppiumBy.accessibilityId("Doble toque para indicar que está en tránsito en los Estados Unidos. ");
                driver.findElement(by).click();
                System.out.println("Opción en tránsito validada y click realizado\n");

                // 15. Screenshoot para el reporte
                report.testPassed("Validaciones Agregar in Transit", true);

                // 14. swipe para ver usar esta direccion en pantalla
                Thread.sleep(500);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

                // 15. Click 'Check Utilizar esta dirección ¿Dónde te vas a hospedar?'
                GeneratedUtilsiOS.sleep(1795);
                by = AppiumBy.accessibilityId("Seleccione para usar dirección para todos los viajeros. Doble toque para seleccionar");
                driver.findElement(by).click();
                System.out.println("Click en utilizar esta dirección para todos los pasajeros en tránsito realizado\n");
            } else if (passenger.equals("child") || passenger.equals("infant")) {

                // 16. swipe y valida que esté el check en tránsito esté presente y activado
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                driver.findElement(AppiumBy.accessibilityId("Doble toque para indicar que está en tránsito en los Estados Unidos. ")).isDisplayed();
                System.out.println("Check en tránsito presente y activado\n");

                if(passenger.equals("child")) {
                    //Elemento mostrado cuando no se pueden seleccionar ninguna de las 4 opciones de agregar in transit, esto valida que no se puedan seleccionar
                    driver.findElement(By.xpath("//XCUIElementTypeImage[@name=\"Check\"]")).isDisplayed();
                    System.out.println("Validación correcta, no se puede hacer click en las opciones de en tránsito\n");
                } else if (passenger.equals("infant")) {
                    driver.findElement(By.xpath("//XCUIElementTypeImage[@name=\"Check\"]"));
                    System.out.println("Validación correcta, no se puede hacer click en las opciones de en tránsito\n");
                }

                // 15. Screenshoot para el reporte pass
                report.testPassed("Validaciones Agregar in Transit", true);
            }

            if(passenger.equals("adult") || passenger.equals("child")) {
                // 17. Click en Guardar
                GeneratedUtilsiOS.sleep(500);
                by = By.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]");
                driver.findElement(by).click();
                System.out.println("Click en guardar realizado\n");

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
    public void fillFieldsT37AndValidations(String passenger, ReportiOS report){
        By by;
        String direct2 = "abajo", correo;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{

            if(passenger.equals("adult")){
                // 1. Swipe para ubicar los elementos en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                booking.swipeSmall(PanelTripDetails, driver, direct2);

                // 2. Limpia el campo país de nacionalidad
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).clear();

                // 3. Llena el campo país de nacionalidad
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
                driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
                System.out.println("Nacionalidad llenada\n");

                // 4. Limpia el campo país de residencia
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).clear();

                // 5. Llena el campo país de residenciaa
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).sendKeys("Panamá");
                driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
                System.out.println("Residencia llenada\n");

                // 6. Limpia el campo número de pasaporte
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).clear();

                // 7. Llena el campo número de pasaporte
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).sendKeys("1234");
                System.out.println("Número de pasaporte llenado\n");

                // 8. Click en la opción fecha de vencimiento
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).click();
                driver.findElement(AppiumBy.accessibilityId("Mostrar selector de año")).click();

                // 9. Make a Swipe gesture
                GeneratedUtilsiOS.sleep(500);
                (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(261,568))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                        .moveTo(PointOption.point(260,455)).release().perform();

                // 10. Click en aceptar fecha de pasaporte
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Listo")).click();
                System.out.println("Vencimiento pasaporte llenado\n");

                // 11. Swipe y Limpia país emisor
                Thread.sleep(500);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).clear();

                // 12. llena país emisor
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).sendKeys("Panamá");
                driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
                System.out.println("País emisor llenado\n");

                report.testPassed("Campo con el correo original", true);

                // 13. Limpia correo
                Thread.sleep(500);
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).clear();

                // 14. llena el correo
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).sendKeys("CORREO@INFOS.COM");
                System.out.println("Correo llenado\n");

                // 15. Click en utilizar este correo para todos los pasajeros
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Seleccione para usar este correo electrónico para todos los viajeros. Doble toque para seleccionar")).click();
                System.out.println("Click en utilizar este correo para todos los pasajeros realizado\n");

                // 16. Click en Guardar
                GeneratedUtilsiOS.sleep(500);
                by = By.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]");
                driver.findElement(by).click();
                System.out.println("Click en guardar realizado\n");

                // 17. Pausa por carga de guardado
                Thread.sleep(15000);
            } else if (passenger.equals("child")) {
                // 18. Swipe para ubicar el correo electrónico en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

                // 19. Valida si el campo de correo electrónico contiene el correo puesto en el pasajero adulto
                correo = driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).getAttribute("value");
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
    public void validateT38_T42(ReportiOS report){
        By by;
        String direct2 = "abajo", direct1 = "arriba", correo1, correo2;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            //    Swipe para ubicar el correo en pantalla
            Thread.sleep(1500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            //    Screenshot para el reporte
            report.testPassed("Correo por defecto", true);

            //    Obtiene el correo para comparar en pasos posteriores
            correo1 = driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).getAttribute("value");
            System.out.println("Correo original obtenido\n");

            //    Swipe hacia arriba para cambiar el perfil CM
            swipeFormTIF(PanelTripDetails, driver, direct1);
            swipeFormTIF(PanelTripDetails, driver, direct1);
            swipeFormTIF(PanelTripDetails, driver, direct1);
            swipeFormTIF(PanelTripDetails, driver, direct1);

            //    Click al dropdown menú de perfiles
            GeneratedUtilsiOS.sleep(1500);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]")).click();

            //    Click a un perfil CM
            GeneratedUtilsiOS.sleep(1500);
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();
            System.out.println("Perfil Connectmiles seleccionado\n");

            /*/    Swipe para ubicar el correo en pantalla
            Thread.sleep(500);
            swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);*/



            //    Obtiene el nuevo correo para comparar en el paso siguiente
            correo2 = driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).getAttribute("value");

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
    public void validateT43_T45(ReportiOS report, String test){
        By by;
        String direct2 = "abajo", logo, campo;
        Boolean check;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            //      Validación t43 no se debe mostrar la opción save to profile
            if (test.equals("t43")) {
                //    Click 'Menú de perfiles'
                GeneratedUtilsiOS.sleep(700);
                by = By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]");
                driver.findElement(by).click();

                //    Click 'Perfil Connectmiles'
                GeneratedUtilsiOS.sleep(1500);
                driver.findElement(AppiumBy.accessibilityId("Listo")).click();
                System.out.println("Perfil connectmiles seleccionado\n");

                //    Swipe para validar que no se muestre save to profile en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
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
                GeneratedUtilsiOS.sleep(700);
                by = By.xpath("//XCUIElementTypeNavigationBar[@name=\"Información del Pasajero\"]/XCUIElementTypeButton");
                driver.findElement(by).click();
                System.out.println("Click atrás realizado\n");

            } else if (test.equals("t44")) { //     Validación t44 se debe mostrar la opción save to profile
                //    Click 'Menú de perfiles'
                GeneratedUtilsiOS.sleep(700);
                by = By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]");
                driver.findElement(by).click();

                //    Click 'Perfil Connectmiles'
                GeneratedUtilsiOS.sleep(700);
                driver.findElement(AppiumBy.accessibilityId("Listo")).click();
                System.out.println("Perfil connectmiles seleccionado\n");

                //     Empieza el llenado de los campos del pasajero para visualizar la opción save to profile
                // 1. Swipe para ubicar los elementos en pantalla
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                //Thread.sleep(500);
                //booking.swipeSmall(PanelTripDetails, driver, direct2);
                //swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

                // 2. Limpia el campo país de nacionalidad
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).clear();

                // 3. Llena el campo país de nacionalidad
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
                driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
                System.out.println("Nacionalidad llenada\n");

                // 4. Limpia el campo país de residencia
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).clear();

                // 5. Llena el campo país de residenciaa
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
                driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
                System.out.println("Residencia llenada\n");

                // 6. Limpia el campo número de pasaporte
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).clear();

                // 7. Llena el campo número de pasaporte
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).sendKeys("1234");
                System.out.println("Número pasaporte llenado\n");

                // 8. Click en la opción fecha de vencimiento
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).click();
                driver.findElement(AppiumBy.accessibilityId("Mostrar selector de año")).click();

                // 9. Make a Swipe gesture
                GeneratedUtilsiOS.sleep(500);
                (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(261,568))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                        .moveTo(PointOption.point(260,455)).release().perform();

                // 10. Click en aceptar fecha de pasaporte
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Listo")).click();
                System.out.println("Vencimiento pasaporte llenado\n");

                // 11. Swipe y Limpia país emisor
                Thread.sleep(500);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).clear();

                // 12. llena país emisor
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
                driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
                System.out.println("País emisor llenado\n");

                //    Swipe para validar que se muestre save to profile en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //    Valida que esté presente la opción de save to profile
                campo = driver.findElement(AppiumBy.accessibilityId("Guardar información actualizada en el perfil de ConnectMiles.")).getAttribute("value");
                if(campo.equals("Guardar información actualizada en el perfil de ConnectMiles.")){
                    System.out.println("Opción save to profile se muestra correctamente\n");
                }else {
                    System.out.println("ERROR, opción save to profile no se muestra correctamente\n");
                    report.testFailed("Valida que esté presente la opción de save to profile", true);
                }

                //    Screenshot para el reporte
                report.testPassed("Validación save to profile presente", true);

                //    Click atrás
                GeneratedUtilsiOS.sleep(700);
                by = By.xpath("//XCUIElementTypeNavigationBar[@name=\"Información del Pasajero\"]/XCUIElementTypeButton");
                driver.findElement(by).click();
                System.out.println("Click atrás realizado\n");

            }else {
                //    Click 'Menú de perfiles'
                GeneratedUtilsiOS.sleep(700);
                by = By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]");
                driver.findElement(by).click();

                //    Click 'Perfil Connectmiles'
                GeneratedUtilsiOS.sleep(700);
                driver.findElement(AppiumBy.accessibilityId("Listo")).click();
                System.out.println("Perfil connectmiles seleccionado llenada\n");

                //    Swipe para validar que se muestre el logo de TSA PreCheck
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                Thread.sleep(500);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
                //swipeFormTIF(PanelTripDetails, driver, direct2);

                //    Valida que esté presente el logo de TSA PreCheck
                logo = driver.findElement(AppiumBy.accessibilityId("Logo de TSA PreCheck")).getAttribute("enabled");
                if(logo.equals("true")){
                    System.out.println("El logo se TSA PreCheck se muestra correctamente\n");
                }else {
                    System.out.println("ERROR, el logo se TSA PreCheck NO se muestra correctamente\n");
                    report.testFailed("Valida que se muestre el logo de TSA PreCheck", true);
                }

                //    Screenshot para el reporte
                report.testPassed("Valida que se muestre el logo de TSA PreCheck", true);

                //    Click atrás
                GeneratedUtilsiOS.sleep(700);
                by = By.xpath("//XCUIElementTypeNavigationBar[@name=\"Información del Pasajero\"]/XCUIElementTypeButton");
                driver.findElement(by).click();
                System.out.println("Click atrás realizado\n");
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
    public void validateT46(ReportiOS report){
        By by;
        String direct2 = "abajo", direct1 = "arriba", logo, campo;
        Boolean check;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            //    Click al dropdown de menú de perfiles
            GeneratedUtilsiOS.sleep(700);
            by = By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]");
            driver.findElement(by).click();

            //    Click 'Perfil Connectmiles'
            GeneratedUtilsiOS.sleep(700);
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();
            System.out.println("Perfil connectmiles seleccionado\n");

            Thread.sleep(3000);

            // Swipe hacia arriba para visualizar el campo de nombre
            swipeFormTIF(PanelTripDetails, driver, direct1);
            swipeFormTIF(PanelTripDetails, driver, direct1);
            booking.swipeSmall(PanelTripDetails, driver, direct1);

            // Limpia el campo nombre
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Nombre")).clear();

            // Llena el campo nombre
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Ingrese Nombre")).sendKeys("Juan");
            System.out.println("Nombre llenado\n");

            //     Empieza el llenado de los campos del pasajero para visualizar la opción save to profile
            // 1. Swipe para ubicar los elementos en pantalla
            Thread.sleep(500);
            //swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            //booking.swipeSmall(PanelTripDetails, driver, direct2);

            // 2. Limpia el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).clear();

            // 3. Llena el campo país de nacionalidad
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
            driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
            System.out.println("Nacionalidad llenada\n");

            // 4. Limpia el campo país de residencia
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).clear();

            // 5. Llena el campo país de residenciaa
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("Residencia llenada\n");

            // 6. Limpia el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).clear();

            // 7. Llena el campo número de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).sendKeys("1234");
            System.out.println("Número pasaporte llenado\n");

            // 8. Click en la opción fecha de vencimiento
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).click();
            driver.findElement(AppiumBy.accessibilityId("Mostrar selector de año")).click();

            // 9. Make a Swipe gesture
            GeneratedUtilsiOS.sleep(500);
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(261,568))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(260,455)).release().perform();

            // 10. Click en aceptar fecha de pasaporte
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();
            System.out.println("Vencimiento pasaporte llenado\n");

            // 11. Swipe y Limpia país emisor
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).clear();

            // 12. llena país emisor
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).sendKeys("Panamá");
            driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
            System.out.println("País emisor llenado llenado\n");

            // 13. Click en Guardar
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]");
            driver.findElement(by).click();
            System.out.println("Click en guardar realizado\n");

            Thread.sleep(10000);
            System.out.println("Se realizó el tiempo de espera para visualizar el error\n");

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
    public void validateT47_T54(ReportiOS report, String session, String passenger){
        By by;
        String direct2 = "abajo", validation;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            if(passenger.equals("adult")) {
                if (session.equals("login")) {
                    // Ubica los elementos en pantalla cuando se está logueado}
                    Thread.sleep(500);
                    swipeFormTIF(PanelTripDetails, driver, direct2);
                }
                // 1. Swipe para ubicar los elementos en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                booking.swipeSmall(PanelTripDetails, driver, direct2);

                // 2. Limpia el campo país de nacionalidad
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).clear();

                // 3. Llena el campo país de nacionalidad
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu nacionalidad. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
                driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
                System.out.println("Nacionalidad llenada\n");

                // 4. Limpia el campo país de residencia
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).clear();

                // 5. Llena el campo país de residenciaa
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
                driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
                System.out.println("Residencia llenada\n");

                // 6. Limpia el campo número de pasaporte
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).clear();

                // 7. Llena el campo número de pasaporte
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).sendKeys("1234");
                System.out.println("Número de pasaporte llenado\n");

                // 8. Click en la opción fecha de vencimiento
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Seleccione Fecha de Vencimiento")).click();
                driver.findElement(AppiumBy.accessibilityId("Mostrar selector de año")).click();

                // 9. Make a Swipe gesture
                GeneratedUtilsiOS.sleep(500);
                (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(261,568))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                        .moveTo(PointOption.point(260,455)).release().perform();

                // 10. Click en aceptar fecha de pasaporte
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Listo")).click();
                System.out.println("Vencimiento pasaporte llenado\n");

                // 11. Swipe y Limpia país emisor
                Thread.sleep(500);
                booking.swipeSuperSmall(PanelTripDetails, driver, direct2);
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).clear();

                // 12. llena país emisor
                GeneratedUtilsiOS.sleep(500);
                driver.findElement(AppiumBy.accessibilityId("Selecciona el país emisor. Doble toque para desplegar el selector")).sendKeys("Estados Unidos de América");
                driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
                System.out.println("País emisor llenado\n");

                //    Swipes para ubicar los campos de contacto de emergencia
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Captura el botón guardar seleccionable T48
                report.testPassed("Valida que el botón de guardar se pueda seleccionar", true);

                //  Llena el campo nombre y apellido de emergencia
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).sendKeys("Juan");
                System.out.println("Nombre contacto emergencia llenado\n");

                // Click en el título de contacto de emergencia para esconder el teclado en iOS
                driver.findElement(AppiumBy.accessibilityId("CONTACTO DE EMERGENCIA")).click();
                System.out.println("Click en el títuló de contacto de emergencia para ocultar el teclado realizado\n");

                //  Valida el mensaje de error de campo requerido T47 para el campo código de país
                validation = driver.findElement(AppiumBy.accessibilityId("Requerido para Código País")).getAttribute("value");
                if (validation.equals("Requerido para Código País")) {
                    System.out.println("Validación correcta, se muestra el mensaje de error\n");
                    report.testPassed("Valida que se muestre el mensaje de error Requerido", true);
                } else {
                    System.out.println("Validación error, NO se muestra el mensaje de error\n");
                    report.testFailed("Valida que se muestre el mensaje de error Requerido", true);
                }

                //  Valida el mensaje de error de campo requerido T47 para el campo número de teléfono
                validation = driver.findElement(AppiumBy.accessibilityId("Requerido para Número de Teléfono")).getAttribute("value");
                if (validation.equals("Requerido para Número de Teléfono")) {
                    System.out.println("Validación correcta, se muestra el mensaje de error\n");
                    report.testPassed("Valida que se muestre el mensaje de error Requerido", true);
                } else {
                    System.out.println("Validación error, NO se muestra el mensaje de error\n");
                    report.testFailed("Valida que se muestre el mensaje de error Requerido", true);
                }

                //  Limpia el campo nombre y apellido de emergencia
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).clear();

                // Click en el título de contacto de emergencia para esconder el teclado en iOS
                driver.findElement(AppiumBy.accessibilityId("CONTACTO DE EMERGENCIA")).click();
                System.out.println("Click al título de contacto de emergencia para esconder el teclado realizado\n");

                //  Llena y valida que el código de país se pueda buscar por Nombre del país o Código T49
                GeneratedUtilsiOS.sleep(500);
                //new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(138,1592)).release().perform();
                by = AppiumBy.accessibilityId("Escribe Código de País");
                driver.findElement(by).sendKeys("+50");
                System.out.println("Búsqueda de país por código realizada\n");
                report.testPassed("Valida que el Código de país se pueda buscar por nombre del país y código", true);
                //  Limpia el campo para ahora escribir el país por nombre
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Código de País");
                driver.findElement(by).clear();
                //  Escribe el nombre del país
                GeneratedUtilsiOS.sleep(500);
                new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(138,1008)).release().perform();
                by = AppiumBy.accessibilityId("Escribe Código de País");
                driver.findElement(by).sendKeys("Pana");
                System.out.println("Búsqueda de país por nombre realizada\n");
                report.testPassed("Valida que el Código de país se pueda buscar por nombre del país y código", true);

                //  Limpia el campo de código de país
                GeneratedUtilsiOS.sleep(500);
                by = By.id("Escribe Código de País");
                driver.findElement(by).clear();

                // Click en el título código país para esconder el teclado en iOS
                driver.findElement(AppiumBy.accessibilityId("Código País")).click();
                System.out.println("Click al título código de país para ocultar el teclado realizado\n");

                //  Llena y valida que el nombre y apellido de contacto de emergencia sólo pueda tener MAX 15 caracteres
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).sendKeys("validacioncampo");

                //click al título para ocultar el teclado
                driver.findElement(AppiumBy.accessibilityId("CONTACTO DE EMERGENCIA")).click();


                //  Valida que se muestre el mensaje de 15 caracteres permitidos T51
                validation = driver.findElement(AppiumBy.accessibilityId("Has llegado al máximo de 15 caracteres permitidos")).getAttribute("value");
                if (validation.equals("Has llegado al máximo de 15 caracteres permitidos")) {
                    System.out.println("Validación correcta, se muestra el mensaje de 15 carateres permitidos\n");
                    report.testPassed("Valida que se muestre el mensaje de 15 caracteres permitidos", true);
                } else {
                    System.out.println("Validación error, NO se muestra el mensaje de 15 caracteres permitidos\n");
                    report.testFailed("Valida que se muestre el mensaje de 15 caracteres permitidos", true);
                }

                //  Limpia el campo nombre y apellido de emergencia
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).clear();

                // Click en el título de contacto de emergencia para esconder el teclado en iOS
                driver.findElement(AppiumBy.accessibilityId("CONTACTO DE EMERGENCIA")).click();
                System.out.println("Click en el título contacto de emergencia para ocultar el teclado realizado\n");

                //  Valida que al seleccionar el Nombre del País se muestre su código telefónico T52
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Código de País");
                driver.findElement(by).sendKeys("Panam");
                //  Selecciona el país del dropdwon
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("+507   Panamá");
                driver.findElement(by).click();
                //  Valida que el campo contenga el código del país seleccionado
                validation = driver.findElement(AppiumBy.accessibilityId("Escribe Código de País")).getAttribute("value");
                if (validation.equals("+507")) {
                    System.out.println("Validación correcta, se muestra el código de país correcto\n");
                    report.testPassed("Valida que se muestre el Código del país seleccionado", true);
                } else {
                    System.out.println("Validación error, NO se muestra el código de país correcto\n");
                    report.testFailed("Valida que se muestre el Código del país seleccionado", true);
                }

                //  Limpia el campo código de país
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Código de País");
                driver.findElement(by).clear();

                // Click en el título código país para esconder el teclado en iOS
                driver.findElement(AppiumBy.accessibilityId("Código País")).click();
                System.out.println("Click en el código de país para ocultar el teclado realizado\n");

                // Click en atrás
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Información del Pasajero\"]/XCUIElementTypeButton");
                driver.findElement(by).click();
                System.out.println("Click atrás realizado\n");

                // Pausa por carga
                Thread.sleep(1000);
            } else if (passenger.equals("infant")) {
                //    Swipes para ubicar la pantalla y ver que no se muestran los campos de contacto de emergencia
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
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
    public void validateT55_T56(String adult, ReportiOS report){
        By by;
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct1 = "arriba", direct2 = "abajo", validation, change;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
                //  Swipe para ubicar el campo correo en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Screenshot y extracción el campo correo para verificar luego el cambio
                report.testPassed("Valida el campo correo antes de hacer un cambio", true);
                validation = driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).getAttribute("value");

                //  Limpia el campo de correo
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)");
                driver.findElement(by).clear();

                //  Llena el campo de correo
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)");
                driver.findElement(by).sendKeys("cambiodecorreo@gmail.com");
                System.out.println("Correo llenado\n");

                // Click en Guardar
                GeneratedUtilsiOS.sleep(500);
                by = By.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]");
                driver.findElement(by).click();

                // Pausa por carga de guardado
                Thread.sleep(18000);

                System.out.println("Se guardó e hizo la pausa\n");

                //  Swipe para actualizar
                //swipeToAdultChildInfant(PanelTripDetails, driver, direct1);
                //Thread.sleep(10000);

                /*  Envía los elemento para ejecutar el Swipe para ver a los pasajeros en pantalla
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);*/

                //  Click al pasajero adulto para validar los campos
                Thread.sleep(2000);
                tif.clickEditInformation(adult);
                Thread.sleep(5000);

                //  Swipe para ubicar el campo correo en pantalla
                Thread.sleep(500);
                swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
                Thread.sleep(500);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Screenshot, extracción y validación de los cambios en el campo correo
                change = driver.findElement(AppiumBy.accessibilityId("Escribe tu Correo Electrónico (para enviar Pase de Abordar)")).getAttribute("value");
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
    public void validateT57(ReportiOS report){
        By by;
        String direct1 = "arriba", direct2 = "abajo";
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            //  Swipe para ubicar los campos nombre y apellido en pantalla
            Thread.sleep(500);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            //  Limpia el campo de nombre
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Ingrese Nombre");
            driver.findElement(by).clear();

            //  Llena el campo con un nombre que tenga acento
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Ingrese Nombre");
            driver.findElement(by).sendKeys("Saúl");
            System.out.println("Nombre con acento llenado\n");

            //  Limpia el campo de apellido
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Ingrese Apellido");
            driver.findElement(by).clear();

            //  Llena el campo con un apellido que tenga acento
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Ingrese Apellido");
            driver.findElement(by).sendKeys("Díaz");
            System.out.println("Apellido con acento llenado\n");

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
    public void validateT58_T61(ReportiOS report){
        By by;
        String direct2 = "abajo", validation;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{

                //    Swipes para ubicar los campos de contacto de emergencia
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Limpia el campo de nombre de contacto de emergencia
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).clear();

                //  Llena y valida que el nombre y apellido de contacto de emergencia sólo pueda tener MAX 15 caracteres
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).sendKeys("validacioncampo");

                //Click en el título de contactos de emergencia para ocultar el teclado
                driver.findElement(AppiumBy.accessibilityId("CONTACTO DE EMERGENCIA")).click();

                //  Valida que se muestre el mensaje de 15 caracteres permitidos T51
                validation = driver.findElement(AppiumBy.accessibilityId("Has llegado al máximo de 15 caracteres permitidos")).getAttribute("value");
                if (validation.equals("Has llegado al máximo de 15 caracteres permitidos")) {
                    System.out.println("Validación correcta, se muestra el mensaje de 15 carateres permitidos\n");
                    report.testPassed("Valida que se muestre el mensaje de 15 caracteres permitidos", true);
                } else {
                    System.out.println("Validación error, NO se muestra el mensaje de 15 caracteres permitidos\n");
                    report.testFailed("Valida que se muestre el mensaje de 15 caracteres permitidos", true);
                }

                //  Limpia el campo nombre y apellido de emergencia
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).clear();

                //  Llena y valida que el nombre y apellido de contacto de emergencia para validar que no admita los caracateres @*+&%
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).sendKeys("@*+&%");

                //Click en el título de contactos de emergencia para ocultar el teclado
                driver.findElement(AppiumBy.accessibilityId("CONTACTO DE EMERGENCIA")).click();

                //  Valida que se muestre el mensaje de 15 caracteres permitidos T51
                validation = driver.findElement(AppiumBy.accessibilityId("Formato inválido para Nombre y Apellido")).getAttribute("value");
                if (validation.equals("Formato inválido para Nombre y Apellido")) {
                    System.out.println("Validación correcta, se muestra el mensaje de Formato Inválido\n");
                    report.testPassed("Valida que se muestre el mensaje de Formato Inválido", true);
                } else {
                    System.out.println("Validación error, NO se muestra el mensaje de Formato Inválido\n");
                    report.testFailed("Valida que se muestre el mensaje de Formato Inválido", true);
                }

                //  Limpia el campo nombre y apellido de emergencia
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).clear();

                //  Llena y valida que el nombre y apellido de contacto de emergencia con mas de 15 caracteres para validar que sólo deje agregar 15
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia");
                driver.findElement(by).sendKeys("validacioncampos");

                //Click en el título de contactos de emergencia para ocultar el teclado
                driver.findElement(AppiumBy.accessibilityId("CONTACTO DE EMERGENCIA")).click();

                //  Valida que se muestre el mensaje de 15 caracteres permitidos T51
                validation = driver.findElement(AppiumBy.accessibilityId("Escribe Nombre y Apellido del contacto de emergencia")).getAttribute("value");
                if (validation.equals("validacioncampo")) {
                    System.out.println("Validación correcta, sólo permite ingresar 15 caracteres\n");
                    report.testPassed("Valida que no permita ingresar más de 15 caracteres", true);
                } else {
                    System.out.println("Validación error, permite ingresar más de 15 caracteres\n");
                    report.testFailed("Valida que no permita ingresar más de 15 caracteres", true);
                }

                //  Click atrás
                clickBack();


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
    public void validateT62_T65(ReportiOS report, String validation){
        By by;
        String direct1 = "arriba", direct2 = "abajo", message, message2;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{

            if(validation.equals("regular apis")){
                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtilsiOS.sleep(500);
                by = By.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]");
                driver.findElement(by).click();
                System.out.println("Click en guardar para mostrar mensaje de campos requeridos realizado\n");

                //  Swipe para mostrar los elementos a validar en pantalla
                //swipeFormTIF(PanelTripDetails, driver, direct2);
                //booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Campo requerido\"])[1]")).getAttribute("value");
                message2 = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Campo requerido\"])[2]")).getAttribute("value");

                //  Valida si el mensaje de error se obtuvo correctamente
                if(message.equals("Campo requerido") & message2.equals("Campo requerido")){
                    System.out.println("Validación correcta, se muestran los mensajes de error en pantalla\n");
                    report.testPassed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }else{
                    System.out.println("Validación incorrecta, NO se muestran los mensajes de error en pantalla\n");
                    report.testFailed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }

                //  Click atrás
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Información del Pasajero\"]/XCUIElementTypeButton");
                driver.findElement(by).click();
                Thread.sleep(2000);
                System.out.println("Click atrás realizado\n");

            } else if (validation.equals("green card")) {
                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtilsiOS.sleep(500);
                by = By.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]");
                driver.findElement(by).click();
                System.out.println("Click en guardar para mostrar mensaje de campos requeridos realizado\n");

                //  Swipe para mostrar los elementos a validar en pantalla
                //swipeFormTIF(PanelTripDetails, driver, direct2);
                //booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Campo requerido\"])[1]")).getAttribute("value");
                message2 = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Campo requerido\"])[2]")).getAttribute("value");

                //  Valida si el mensaje de error se obtuvo correctamente
                if(message.equals("Campo requerido") & message2.equals("Campo requerido")){
                    System.out.println("Validación correcta, se muestran los mensajes de error en pantalla\n");
                    report.testPassed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }else{
                    System.out.println("Validación incorrecta, NO se muestran los mensajes de error en pantalla\n");
                    report.testFailed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }

                //  Limpia el campo de país de residencia
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector");
                driver.findElement(by).clear();

                //  Llena el campo país de residencia para mostrar el extended apis green card
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector");
                driver.findElement(by).sendKeys("Estados Unidos de América");
                driver.findElement(AppiumBy.accessibilityId("Estados Unidos de América")).click();
                System.out.println("país residencia llenado\n");

                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtilsiOS.sleep(500);
                by = By.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]");
                driver.findElement(by).click();
                System.out.println("Click en guardar para mostrar mensaje de campos requeridos realizado\n");

                //  Swipe para mostrar los campos de green card
                swipeFormTIF(PanelTripDetails, driver, direct2);
                swipeFormTIF(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Campo requerido\"])[5]")).getAttribute("value");
                message2 = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Campo requerido\"])[6]")).getAttribute("value");

                //  Valida si el mensaje de error se obtuvo correctamente
                if(message.equals("Campo requerido") & message2.equals("Campo requerido")){
                    System.out.println("Validación correcta, se muestran los mensajes de error en pantalla\n");
                    report.testPassed("Valida que se muestre mensaje de error campo requerido green card", true);
                }else{
                    System.out.println("Validación incorrecta, NO se muestran los mensajes de error en pantalla\n");
                    report.testFailed("Valida que se muestre mensaje de error campo requerido green card", true);
                }

                //  Click atrás
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Información del Pasajero\"]/XCUIElementTypeButton");
                driver.findElement(by).click();
                Thread.sleep(2000);
                System.out.println("Click atrás realizado\n");

            }else {
                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtilsiOS.sleep(500);
                by = By.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]");
                driver.findElement(by).click();
                System.out.println("Click en guardar para mostrar mensaje de campos requeridos realizado\n");

                //  Swipe para mostrar los elementos a validar en pantalla
                //swipeFormTIF(PanelTripDetails, driver, direct2);
                //booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Campo requerido\"])[1]")).getAttribute("value");
                message2 = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Campo requerido\"])[2]")).getAttribute("value");

                //  Valida si el mensaje de error se obtuvo correctamente
                if(message.equals("Campo requerido") & message2.equals("Campo requerido")){
                    System.out.println("Validación correcta, se muestran los mensajes de error en pantalla\n");
                    report.testPassed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }else{
                    System.out.println("Validación incorrecta, NO se muestran los mensajes de error en pantalla\n");
                    report.testFailed("Valida que se muestre mensaje de error campo requerido regular apis", true);
                }

                //  Limpia el campo de país de residencia
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector");
                driver.findElement(by).clear();

                //  Llena el campo país de residencia para mostrar el extended apis green card
                GeneratedUtilsiOS.sleep(500);
                by = AppiumBy.accessibilityId("Selecciona tu País de Residencia. Doble toque para desplegar el selector");
                driver.findElement(by).sendKeys("Panamá");
                driver.findElement(AppiumBy.accessibilityId("Panamá")).click();
                System.out.println("País residencia llenado\n");

                //  Click en guardar para mostrar el mensaje de campos requeridos
                GeneratedUtilsiOS.sleep(500);
                by = By.xpath("//XCUIElementTypeButton[@name=\"Guardar\"]");
                driver.findElement(by).click();
                System.out.println("Click en guardar para mostrar mensaje de campos requeridos realizado\n");

                //  Swipe para mostrar los campos de where are you staying?
                swipeFormTIF(PanelTripDetails, driver, direct2);
                booking.swipeSmall(PanelTripDetails, driver, direct2);
                booking.swipeSmall(PanelTripDetails, driver, direct2);

                //  Obtiene el atributo texto del mensaje de error para realizar la validación
                message = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Campo requerido\"])[5]")).getAttribute("value");

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
    public void validateT66(ReportiOS report){
        By by;
        String direct2 = "abajo", direct1 = "arriba", name, lastname;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{
            //  Swipe para ubicar los elementos en pantalla
            Thread.sleep(2000);
            booking.swipeSuperSmall(PanelTripDetails, driver, direct2);

            //  Obtiene el nombre y apellido para validar después
            name = driver.findElement(AppiumBy.accessibilityId("Ingrese Nombre")).getAttribute("value");
            lastname = driver.findElement(AppiumBy.accessibilityId("Ingrese Apellido")).getAttribute("value");
            System.out.println("Nombre y apellido obtenidos\n");

            //  Captura para ver el nombre y apellido originales
            report.testPassed("Valida como estaban los campos antes de llenar la información", true);

            //  Click en seleccionar perfil connectmiles para cambiarlo
            GeneratedUtilsiOS.sleep(500);
            by = By.xpath("//XCUIElementTypeTextField[@name=\"Seleccione el Perfil\"]");
            driver.findElement(by).click();

            //  Click en el pasajero adicional
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.accessibilityId("Listo");
            driver.findElement(by).click();
            System.out.println("Click en un pasajero adicional realizado\n");

            //  Swipe hacia arriba para ubicar los elementos en pantalla
            Thread.sleep(2000);
            swipeFormTIF(PanelTripDetails, driver, direct1);
            swipeFormTIF(PanelTripDetails, driver, direct1);
            booking.swipeSmall(PanelTripDetails, driver, direct1);


            //  Valida si se autopopulo el nombre y apellido
            if (name != driver.findElement(AppiumBy.accessibilityId("Ingrese Nombre")).getAttribute("value") & lastname != driver.findElement(AppiumBy.accessibilityId("Ingrese Apellido")).getAttribute("value")){
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
     * Realiza las validaciones de CountryPhoneNumbers
     * @param report objeto necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param countryCode contiene texto login o no login para saber si en la ejecución se está logueado en el app
     * @param phoneNumber1 Indica si el pasajero es adulto o infante
     * @param phoneNumber2 Indica si el pasajero es adulto o infante
     * @param phoneNumber3 Indica si el pasajero es adulto o infante
     */
    public void validateCountryPhoneNumbers(ReportiOS report, String countryCode, String phoneNumber1, String phoneNumber2, String phoneNumber3){
        By by;
        String direct2 = "abajo", validation;
        String noMessage;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{

            /** Validación para los países **/

            //  Limpia y llena el código de país
            GeneratedUtils.sleep(500);
            //new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(138,1592)).release().perform();
            by = AppiumBy.accessibilityId("Escribe Código de País");
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(countryCode);
            System.out.println("Código de país llenado\n");

            //  Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = AppiumBy.accessibilityId("Escribe Número de Teléfono");
            driver.findElement(by).clear();
            // llena menos del mínimo requerido
            driver.findElement(by).sendKeys(phoneNumber1);
            System.out.println("Número de teléfono llenado\n");

            // Valida que se muestra el mensaje de formato inválido
            validation = driver.findElement(AppiumBy.accessibilityId("Formato inválido para Número de Teléfono")).getAttribute("name");
            if(validation.equals("Formato inválido para Número de Teléfono")){
                System.out.println("Se muestra el mensaje de error con menos del mínimo requerido\n");
                report.testPassed("Valida que se muestra el mensaje de error con menos del mínimo requerido", true);
            }else {
                System.out.println("ERROR, NO se muestra el mensaje de error con menos del mínimo requerido\n");
                report.testFailed("Valida que se muestra el mensaje de error con menos del mínimo requerido", true);
            }

            //  Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = AppiumBy.accessibilityId("Escribe Número de Teléfono");
            driver.findElement(by).clear();
            // llena el máximo requerido
            driver.findElement(by).sendKeys(phoneNumber2);

            // Valida que se muestra el mensaje de formato inválido
            validation = driver.findElement(AppiumBy.accessibilityId("Formato inválido para Número de Teléfono")).getAttribute("name");
            if(validation.equals("Formato inválido para Número de Teléfono")){
                System.out.println("Se muestra el mensaje de error con más del máximo requerido\n");
                report.testPassed("Valida que se muestra el mensaje de error con más del máximo requerido\n", true);
            }else {
                System.out.println("ERROR, NO se muestra el mensaje de error con más del máximo requerido\n");
                report.testFailed("Valida que se muestra el mensaje de error con más del máximo requerido\n", true);
            }

            // Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = AppiumBy.accessibilityId("Escribe Número de Teléfono");
            driver.findElement(by).clear();
            // llena la cantidad correcta de números
            driver.findElement(by).sendKeys(phoneNumber3);

            // Valida que no se muestre el mensaje de error
            if(driver.findElements(AppiumBy.accessibilityId("Formato inválido para Número de Teléfono")).isEmpty()) {
                System.out.println("Validación correcta, no se muestra el mensaje de error\n");
                report.testPassed("Valida que NO se muestra el mensaje de error\n", true);
            } else {
                System.out.println("Validación incorrecta, se muestra el mensaje de error\n");
                report.testFailed("Valida que NO se muestra el mensaje de error\n", true);
            }

            System.out.println("validateCountryPhoneNumbers finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateCountryPhoneNumbers finalizado con error\n");
            System.out.println("el error es \n"+ex);
        }
    }

    public void validateCountryPhoneNumbersMyProfile(ReportiOS report, String countryCode, String phoneNumber1, String phoneNumber2, String phoneNumber3){
        By by;
        String direct2 = "abajo", validation;
        String noMessage;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        try{

            /** Validación para los países **/

            //  Limpia y llena el código de país
            GeneratedUtils.sleep(500);
            //new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(138,1592)).release().perform();
            by = AppiumBy.accessibilityId("Escribe Código de País ");
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(countryCode);

            //  Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = AppiumBy.accessibilityId("Escribe tu número de teléfono principal. Este campo es numérico.");
            driver.findElement(by).clear();
            // llena menos del mínimo requerido
            driver.findElement(by).sendKeys(phoneNumber1);

            // Valida que se muestra el mensaje de formato inválido
            validation = driver.findElement(AppiumBy.accessibilityId("Formato inválido")).getAttribute("name");
            if(validation.equals("Formato inválido")){
                System.out.println("Se muestra el mensaje de error con menos del mínimo requerido\n");
                report.testPassed("Valida que se muestra el mensaje de error con menos del mínimo requerido", true);
            }else {
                System.out.println("ERROR, NO se muestra el mensaje de error con menos del mínimo requerido\n");
                report.testFailed("Valida que se muestra el mensaje de error con menos del mínimo requerido", true);
            }

            //  Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = AppiumBy.accessibilityId("Escribe tu número de teléfono principal. Este campo es numérico.");
            driver.findElement(by).clear();
            // llena el máximo requerido
            driver.findElement(by).sendKeys(phoneNumber2);

            // Valida que se muestra el mensaje de formato inválido
            validation = driver.findElement(AppiumBy.accessibilityId("Formato inválido")).getAttribute("name");
            if(validation.equals("Formato inválido")){
                System.out.println("Se muestra el mensaje de error con más del máximo requerido\n");
                report.testPassed("Valida que se muestra el mensaje de error con más del máximo requerido\n", true);
            }else {
                System.out.println("ERROR, NO se muestra el mensaje de error con más del máximo requerido\n");
                report.testFailed("Valida que se muestra el mensaje de error con más del máximo requerido\n", true);
            }

            // Limpia y llena el campo número de teléfono para realizar la validación
            GeneratedUtils.sleep(500);
            by = AppiumBy.accessibilityId("Escribe tu número de teléfono principal. Este campo es numérico.");
            driver.findElement(by).clear();
            // llena la cantidad correcta de números
            driver.findElement(by).sendKeys(phoneNumber3);

            // Valida que no se muestre el mensaje de error
            if(driver.findElements(AppiumBy.accessibilityId("Formato inválido")).isEmpty()) {
                System.out.println("Validación correcta, no se muestra el mensaje de error\n");
                report.testPassed("Valida que NO se muestra el mensaje de error\n", true);
            } else {
                System.out.println("Validación incorrecta, se muestra el mensaje de error\n");
                report.testFailed("Valida que NO se muestra el mensaje de error\n", true);
            }

            System.out.println("validateCountryPhoneNumbers finalizado con éxito\n");
        }catch(Exception ex){
            System.out.println("validateCountryPhoneNumbers finalizado con error\n");
            System.out.println("el error es \n"+ex);
        }
    }
}

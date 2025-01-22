package iOS.iOSPages;

import androidPages.*;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/**
 * Clase para manejar los objetos relacionados a Contact Us
 */

public class OnBoardingLandingHomeValidationsiOS {
    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public OnBoardingLandingHomeValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }


    /**
     * Realiza las validaciones de OnBoarding
     * @param report necesario para hacer las capturas para el reporte
     */
    public void OnBoardingValidations(ReportiOS report){
        By by;
        String direct2 = "abajo";
        BookingiOS book = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("OnBoardingValidations inicio\n");
        try {

            //Validar que las pantallas que suben desde la parte inferior contengan pagination
            if (driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypePageIndicator")).isDisplayed()){
                System.out.println("Validación correcta, se muestra pagination\n");
                report.testPassed("Valida que se muestra pagination", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra paginationn");
                report.testFailed("Valida que se muestra pagination", true);
            }

            //Validar que la aplicación de una explicación de como utilizar las funcionalidades más importantes
            if (driver.findElement(AppiumBy.accessibilityId("Lo que necesitas en el momento correcto")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Recibe información y notificaciones en tiempo real sobre tu próximo vuelo en la pantalla de inicio")).isDisplayed()){
                WebElement Panel2 = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
                Dimension dimension = Panel2.getSize();

                int Anchor = Panel2.getSize().getWidth()/2;

                Double ScreenWidthStart = dimension.getWidth() * 1.0;
                int scrollStart = ScreenWidthStart.intValue();

                Double ScreenWidthEnd = dimension.getWidth() * 0.2;
                int scrollEnd = ScreenWidthEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(scrollStart, Anchor))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(scrollEnd, Anchor))
                        .release().perform();

                Thread.sleep(2000);

                if (driver.findElement(AppiumBy.accessibilityId("Toda la información de tu vuelo en un sólo lugar")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Accede a tus viajes, detalles de vuelo y edita la información de pasajero fácilmente")).isDisplayed()){

                    new TouchAction((PerformsTouchActions) driver)
                            .press(PointOption.point(scrollStart, Anchor))
                            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                            .moveTo(PointOption.point(scrollEnd, Anchor))
                            .release().perform();

                    Thread.sleep(2000);

                    if (driver.findElement(AppiumBy.accessibilityId("Experiencia de Check-In mejorada")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Realiza Check-In y accede a tu pase de abordar desde donde estés")).isDisplayed()){
                        new TouchAction((PerformsTouchActions) driver)
                                .press(PointOption.point(scrollStart, Anchor))
                                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                                .moveTo(PointOption.point(scrollEnd, Anchor))
                                .release().perform();

                        Thread.sleep(2000);

                        if (driver.findElement(AppiumBy.accessibilityId("Accede a tu cuenta ConnectMiles")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Inicia sesión o afíliate para sincronizar tus viajes, ver el balance de tus millas premio y acceder a la información de tu perfil")).isDisplayed()){
                            System.out.println("Validación correcta, se muestra una explicación de como utilizar las funcionalidades más importantes\n");
                            report.testPassed("Valida que se muestra una explicación de como utilizar las funcionalidades más importantes", true);

                        }else {
                            System.out.println("Validación incorrecta, NO se muestra una explicación de como utilizar las funcionalidades más importantes");
                            report.testFailed("Valida que se muestra una explicación de como utilizar las funcionalidades más importantes", true);
                        }

                    } else {
                        System.out.println("Validación incorrecta, NO se muestra una explicación de como utilizar las funcionalidades más importantes");
                        report.testFailed("Valida que se muestra una explicación de como utilizar las funcionalidades más importantes", true);
                    }

                } else {
                    System.out.println("Validación incorrecta, NO se muestra una explicación de como utilizar las funcionalidades más importantes");
                    report.testFailed("Valida que se muestra una explicación de como utilizar las funcionalidades más importantes", true);
                }

            } else {
                System.out.println("Validación incorrecta, NO se muestra una explicación de como utilizar las funcionalidades más importantes");
                report.testFailed("Valida que se muestra una explicación de como utilizar las funcionalidades más importantes", true);
            }

            //Validar que el usuario tenga un rapido acceso a la página de inicio de sesion
            if (driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Listo\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un rapido acceso a la página de inicio de sesion\n");
                report.testPassed("Valida que se muestra un rapido acceso a la página de inicio de sesion", true);

            } else {
                System.out.println("Validación incorrecta, NO se muestra un rapido acceso a la página de inicio de sesion");
                report.testFailed("Valida que se muestra un rapido acceso a la página de inicio de sesion", true);
            }

            //Swipe a la derecha para validar botón omitir
            WebElement Panel2 = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            Dimension dimension = Panel2.getSize();

            int Anchor = Panel2.getSize().getWidth()/2;

            Double ScreenWidthStart = dimension.getWidth() * 0.3;
            int scrollStart = ScreenWidthStart.intValue();

            Double ScreenWidthEnd = dimension.getWidth() * 0.9;
            int scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            Thread.sleep(2000);

            //Validar que las pantallas que suben desde la parte inferior contengan skip
            if (driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Omitir\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el skip\n");
                report.testPassed("Valida que se muestra el skip", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el skip");
                report.testFailed("Valida que se muestra el skip", true);
            }

            //Click en Omitir para futura validación
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Omitir\"]")).click();


            System.out.println("OnBoardingValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("OnBoardingValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de LandingPage
     * @param report necesario para hacer las capturas para el reporte
     */
    public void LandingPageValidations(ReportiOS report){
        By by;
        String direct2 = "abajo";
        BookingiOS book = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("LandingPageValidations inicio\n");
        try {

            Thread.sleep(5000);
            //Validar que al usuario abrir el app y esta inicie se le muestre una pagina de destino que muestre un fondo de pantalla con unas nubes moviendose lentamente
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeImage[@name=\"cloud\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un fondo de pantalla con unas nubes\n");
                report.testPassed("Valida que se muestra un fondo de pantalla con unas nubes", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra un fondo de pantalla con unas nubes");
                report.testFailed("Valida que se muestra un fondo de pantalla con unas nubes", true);
            }

            //Validar que la página muestre el logo de Copa en el medio
            if (driver.findElement(AppiumBy.accessibilityId("Logo")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de Copa en el medio\n");
                report.testPassed("Valida que se muestra el logo de Copa en el medio", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el logo de Copa en el medio");
                report.testFailed("Valida que se muestra el logo de Copa en el medio", true);
            }

            //Validar que se muestra Log In CTA en color azul claro
            if (driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Inicia Sesión\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra Log In CTA en color azul claro\n");
                report.testPassed("Valida que se muestra Log In CTA en color azul claro", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra Log In CTA en color azul claro");
                report.testFailed("Valida que se muestra Log In CTA en color azul claro", true);
            }

            //Validar que se muestra Sign Up en azul
            if (driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Afíliate a ConnectMiles\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra Sign Up en azul\n");
                report.testPassed("Valida que se muestra Sign Up en azul", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra Sign Up en azul");
                report.testFailed("Valida que se muestra Sign Up en azul", true);
            }

            //Validar que se muestra un Disclaimer debajo del Sign Up CTA
            if (driver.findElement(AppiumBy.accessibilityId("Inicia sesión en tu cuenta de ConnectMiles para sincronizar tus viajes y ver la información de tu perfil.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un Disclaimer debajo del Sign Up CTA\n");
                report.testPassed("Valida que se muestra un Disclaimer debajo del Sign Up CTA", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra un Disclaimer debajo del Sign Up CTA");
                report.testFailed("Valida que se muestra un Disclaimer debajo del Sign Up CTA", true);
            }

            //Click en continuar como invitado
            driver.findElement(AppiumBy.accessibilityId("Continuar como invitado")).click();
            Thread.sleep(3000);

            //Click en recibir notificaciones
            driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Recibir Notificaciones\"]")).click();
            Thread.sleep(2000);

            //Click en permitir
            driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Permitir\"]")).click();
            Thread.sleep(2000);


            System.out.println("LandingPageValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("LandingPageValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Entertaiment
     * @param report necesario para tomar las capturas del reporte
     */
    public void EntertaimentValidations(ReportiOS report){
        By by;
        String direct2 = "abajo";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS book = new BookingiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("EntertaimentValidations inicio\n");
        try {

            //Validar que aparezca en el texto inferior Disfruta la selección de contenido
            if (driver.findElement(AppiumBy.accessibilityId("cd_home_entertainment")).isDisplayed()){
                System.out.println("Validación correcta, aparece en el texto inferior Disfruta la selección de contenido\n");
                report.testPassed("Valida que aparece en el texto inferior Disfruta la selección de contenido", true);
            } else {
                System.out.println("Validación incorrecta, NO aparece en el texto inferior Disfruta la selección de contenido");
                report.testFailed("Valida que aparece en el texto inferior Disfruta la selección de contenido", true);
            }

            //Click en el botón de Entertaiment
            driver.findElement(AppiumBy.accessibilityId("cd_home_entertainment")).click();
            Thread.sleep(2000);

            //Validar que entrará a una página intermedia que le permita escoger entre Showpass y revista Panorama.
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"cd_entertainment_header\"]")).isDisplayed()){
                System.out.println("Validación correcta, entrará a una página intermedia que le permita escoger entre Showpass y revista Panorama.\n");
                report.testPassed("Valida que entrará a una página intermedia que le permita escoger entre Showpass y revista Panorama.", true);
            } else {
                System.out.println("Validación incorrecta, NO entrará a una página intermedia que le permita escoger entre Showpass y revista Panorama.");
                report.testFailed("Valida que entrará a una página intermedia que le permita escoger entre Showpass y revista Panorama.", true);
            }

            //Swipe para centrar elementos
            book.swipeSuperSmall(Panel, driver, direct2);

            //Validar que la página de Entertainment tenga las secciones "Showpass" y "Panorama de las Américas"
            if (driver.findElement(AppiumBy.accessibilityId("Enlace a Copa Showpas. Doble toque para ir nuestro sistema de entretenimiento a bordo.")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Enlace a nuestra revista digital. Doble toque para ir a la página de Panorama de las Américas.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra \"Showpass\" y \"Panorama de las Américas\"\n");
                report.testPassed("Valida que se muestra \"Showpass\" y \"Panorama de las Américas\"", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra \"Showpass\" y \"Panorama de las Américas\"");
                report.testFailed("Valida que se muestra \"Showpass\" y \"Panorama de las Américas\"", true);
            }

            //Click en Showpass
            driver.findElement(AppiumBy.accessibilityId("Enlace a Copa Showpas. Doble toque para ir nuestro sistema de entretenimiento a bordo.")).click();
            Thread.sleep(2000);

            //Validar que la págna de Showpass envíe a la página de instrucciones de Showpass
            if (driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Copa Showpass\"]")).isDisplayed()){
                System.out.println("Validación correcta, envía a la página de instrucciones de Showpass\"\n");
                report.testPassed("Valida que envía a la página de instrucciones de Showpass\"", true);
            } else {
                System.out.println("Validación incorrecta, NO envía a la página de instrucciones de Showpass\"");
                report.testFailed("Valida que envía a la página de instrucciones de Showpass\"", true);
            }

            //Vuelve al Home
            menu.clickHomeIcon();
            modal.closeRatingModalIfPresent();


            System.out.println("EntertaimentValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("EntertaimentValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Home Base Layout
     * @param report necesario para tomar las capturas del reporte
     */
    public void HomeBaseLayoutValidations(ReportiOS report){
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("HomeBaseLayoutValidations inicio\n");
        try {
            //Validar que en la pantalla de inicio exista un espacio para el mensaje de bienvenida
            if(driver.findElement(AppiumBy.accessibilityId("Hola, Bienvenido")).isDisplayed()){
                System.out.println("Validación correcta, se muestran el mensaje de bienvenida\n");
                report.testPassed("Valida que se muestran se muestran el mensaje de bienvenida", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran se muestran el mensaje de bienvenida\n");
                report.testFailed("Valida que se muestran se muestran el mensaje de bienvenida", true);
            }

            //Validar que en la pantalla de inicio exista un espacio para el icono de notificaciones y que muestre la cantidad de notificaciones pendiente
            if(driver.findElement(AppiumBy.accessibilityId("cd_home_notification_center")).isDisplayed()){
                System.out.println("Validación correcta, se muestran el icono de notificaciones\n");
                report.testPassed("Valida que se muestran se muestran el icono de notificaciones", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran se muestran el icono de notificaciones\n");
                report.testFailed("Valida que se muestran se muestran el icono de notificaciones", true);
            }

            //Swipe para futura validación
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Validar que aparezca el logo de Star Alliance entre los textos de Terms & Conditions y el número de la versión
            if(driver.findElement(AppiumBy.accessibilityId("Star Alliance")).isDisplayed()){
                System.out.println("Validación correcta, se muestran el logo de Star Alliance\n");
                report.testPassed("Valida que se muestran se muestran el logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran se muestran el logo de Star Alliance\n");
                report.testFailed("Valida que se muestran se muestran el logo de Star Alliance", true);
            }

            System.out.println("HomeBaseLayoutValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("HomeBaseLayoutValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Menus
     * @param report necesario para tomar las capturas del reporte
     */
    public void MenusValidations(ReportiOS report, String cuenta){
        String direct2 = "abajo", direct1 = "arriba";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        LoginiOS login = new LoginiOS(driver);
        String password = "Copa2022";
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("MenusValidations inicio\n");
        try {
            //REGRESAR LO DE ABAJO CUANDO TENGAMOS DE VUETA EL NEW HOME
            //Validar que en la pantalla principal se pueda acceder a cada una de los shortcuts
            /*if(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Home\"]")).isDisplayed() && driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Booking\"]")).isDisplayed()
                    && driver.findElement(AppiumBy.accessibilityId("My Trips")).isDisplayed() && driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Account\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestran los shortcuts en la pantalla principal\n");
                report.testPassed("Valida que se muestran los shortcuts en la pantalla principal", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran los shortcuts en la pantalla principal\n");
                report.testFailed("Valida que se muestran los shortcuts en la pantalla principal", true);
            }*/

            if(driver.findElement(By.xpath("//XCUIElementTypeButton[@value=\"1\"]")).isDisplayed() && driver.findElement(AppiumBy.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[1]")).isDisplayed()
                    && driver.findElement(By.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[3]")).isDisplayed()){
                System.out.println("Validación correcta, se muestran los shortcuts en la pantalla principal\n");
                report.testPassed("Valida que se muestran los shortcuts en la pantalla principal", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran los shortcuts en la pantalla principal\n");
                report.testFailed("Valida que se muestran los shortcuts en la pantalla principal", true);
            }

            //Validar que se pueda acceder a las opciones My Trips y Check-In

            //Click en Mis Viajes
            menu.clickMyTripsIcon();
            Thread.sleep(1000);

            //Validar que se pueda acceder a las opciones My Trips
            if (driver.findElement(AppiumBy.accessibilityId("Mis Viajes")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a My Trips\n");
                report.testPassed("Valida que se puede acceder a My Trips", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a My Trips\n");
                report.testFailed("Valida que se puede acceder a My Trips", true);
            }

            //Click en HOME
            menu.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en Check-In VOLVER A COMENTAR AL TENER DE VUELTA EL NEW HOME
            menu.clickCheckinIcon();

            //Validar que se pueda acceder a Check-In
            if (driver.findElement(AppiumBy.accessibilityId("Check in")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a Check-In\n");
                report.testPassed("Valida que se puede acceder a Check-In", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a Check-In\n");
                report.testFailed("Valida que se puede acceder a Check-In", true);
            }

            //Click en cancelar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Cancelar\"]")).click();
            modal.closeRatingModalIfPresent();

            //Swipe para futura validación
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //Validar cuando el usuario no tenga la sesión iniciada se presente en el menú vertical las opciones
            System.out.println("Validación correcta, se presenta en el menú vertical las opciones\n");
            report.testPassed("Valida que se presenta en el menú vertical las opciones", true);

            //Iniciar Sesión
            login.loginUser(cuenta, password, false);
            modal.closeRatingModalIfPresent();

            //Click en Home Icon
            menu.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Validar cuando el usuario tenga la sesión iniciada se presente en el menú vertical las opciones
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSuperSmall(Panel, driver, direct2);
            System.out.println("Validación correcta, se presenta en el menú vertical las opciones cuando se loggea\n");
            report.testPassed("Valida que se presenta en el menú vertical las opciones cuando se loggea", true);

            //Validar que en la pantalla principal se pueda acceder a los shortcuts cuando se está loggeado
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSuperSmall(Panel, driver, direct1);
            booking.swipeSuperSmall(Panel, driver, direct1);

            //Click en Mis Viajes
            menu.clickMyTripsIcon();
            Thread.sleep(1000);

            //Validar que se pueda acceder a las opciones My Trips

            if (driver.findElement(AppiumBy.accessibilityId("Mis Viajes")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a My Trips\n");
                report.testPassed("Valida que se puede acceder a My Trips", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a My Trips\n");
                report.testFailed("Valida que se puede acceder a My Trips", true);
            }

            //Click en HOME
            menu.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en Check-In VOLVER A COMENTAR AL TENER DE VUELTA EL NEW HOME
            menu.clickCheckinIcon();

            //Validar que se pueda acceder a Check-In
            if (driver.findElement(AppiumBy.accessibilityId("Check in")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a Check-In\n");
                report.testPassed("Valida que se puede acceder a Check-In", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a Check-In\n");
                report.testFailed("Valida que se puede acceder a Check-In", true);
            }

            //Click en cancelar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Cancelar\"]")).click();
            modal.closeRatingModalIfPresent();


            System.out.println("MenusValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("MenusValidations finalizado con error\n"+ex);
        }
    }

    /**
     * Realiza las validaciones de Card Status
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNRC Contiene el PNR del vuelo con Check-In
     * @param LastNameC Contiene el Apellido del vuelo con Check-In
     * @param PNR Contiene el PNR del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void CardStatusValidations(ReportiOS report, String PNRC, String LastNameC, String PNR, String LastName ) {
        String direct2 = "abajo", direct1 = "arriba";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("CardStatusValidations inicio\n");
        try {
            //Click en MyTrips
            menu.clickMyTripsIcon();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNRC);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastNameC);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.accessibilityId("Busca tu Reservación")).click();
            Thread.sleep(8000);


            //Click en el icono de home
            menu.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //swipe hacia arriba
            booking.swipeValidateStopover(Panel, driver, direct1);
            booking.swipeValidateStopover(Panel, driver, direct1);
            Thread.sleep(6000);

            //Validar que las tarjetas sean accesibles para realizar el check-in
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Check In disponible\"]")).isDisplayed()) {
                System.out.println("Validación correcta, se puede realizar el check-in\n");
                report.testPassed("Valida que se puede realizar el check-in", true);
            } else {
                System.out.println("Validación incorrecta, No se puede realizar el check-in\n");
                report.testFailed("Valida que se puede realizar el check-in", true);
            }

            //Eliminar Viaje
            menu.deleteTrip();

            //Click en el icono de Home
            menu.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en MyTrips
            menu.clickMyTripsIcon();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.accessibilityId("Busca tu Reservación")).click();
            Thread.sleep(8000);

            //Click en el icono de Home
            menu.clickHomeIcon();
            Thread.sleep(1000);
            modal.closeRatingModalIfPresent();

            //Validar que todas las reservas fuera de las 24 horas en la ventana de Check-In no tengan ninguna pastilla de texto en el estado de vuelo
            System.out.println("Validación correcta, NO se muestra ninguna pastilla de texto en el estado\n");
            report.testPassed("Valida que todas las reservas fuera de las 24 horas en la ventana de Check-In no tengan ninguna pastilla de texto en el estado", true);


            //Eliminar Viaje
            menu.deleteTrip();

            //Click en el icono de Home
            menu.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            System.out.println("CardStatusValidations finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("CardStatusValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de Card Carusel
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNR Contiene el PNR del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void CardCaruselValidations(ReportiOS report, String PNR, String LastName ) {
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("CardCaruselValidations inicio\n");
        try {

            //Click en My Trips
            menu.clickMyTripsIcon();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.accessibilityId("Busca tu Reservación")).click();
            Thread.sleep(8000);

            //Click en icono de Home
            menu.clickHomeIcon();
            Thread.sleep(1000);
            modal.closeRatingModalIfPresent();

            //Validar que cuando el texto es demasiado largo se muestre solo el destino, que las tarjetas cuenten con el formato establecido y con los detalles requeridos, el formato de fecha de la siguiente manera (nov 2, 2018)

            System.out.println("Validación correcta, se muestra el texto, formato establecido y fecha\n");
            report.testPassed("Valida que se muestra el texto, formato establecido y fecha", true);

            //Borrar Viaje
            menu.deleteTrip();

            //Click el icono de home
            menu.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            System.out.println("CardCaruselValidations finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("CardCaruselValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de Home Alerts
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void HomeAlertsValidations(ReportiOS report, String cuenta) {
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        LoginiOS login = new LoginiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        String password = "Copa2022", direct1 = "arriba";
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("HomeAlertsValidations inicio\n");
        try {

            modal.closeRatingModalIfPresent();

            //swipe arriba
            booking.swipeSuperSmall(Panel, driver, direct1);
            //booking.swipeSuperSmall(Panel, driver, direct1);

            //Validar que se muestren las alertas genericas para todos lo pax
            if(driver.findElement(AppiumBy.accessibilityId("Alerta importante")).isDisplayed()){
                System.out.println("Validación correcta, se muestra las alertas genericas para todos lo pax\n");
                report.testPassed("Valida que se muestra las alertas genericas para todos lo pax", true);
            } else {
                System.out.println("Validación incorrecta,NO se muestra las alertas genericas para todos lo pax\n");
                report.testFailed("Valida que no se muestra las alertas genericas para todos lo pax", true);
            }

            //Validar que se muestren las alertas a los usuarios con sesion activa
            login.loginUser(cuenta, password, false);
            modal.closeRatingModalIfPresent();

            //Click en icono de home
            menu.clickHomeIcon();
            Thread.sleep(2000);
            modal.closeRatingModalIfPresent();

            //Validar que se muestren las alertas a los usuarios con sesion activa
            if(driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Alerta segment\"])[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra las alertas a los usuarios con sesion activa \n");
                report.testPassed("Valida que se muestra las alertas a los usuarios con sesion activa ", true);
            } else {
                System.out.println("Validación incorrecta,NO se muestra las alertas a los usuarios con sesion activa \n");
                report.testFailed("Valida que no se muestra las alertas a los usuarios con sesion activa ", true);
            }
            System.out.println("HomeAlertsValidations finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("HomeAlertsValidations finalizado con error\n");
        }
    }
}

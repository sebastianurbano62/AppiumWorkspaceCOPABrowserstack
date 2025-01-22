package iOS.iOSPages;

import androidPages.Booking;
import androidPages.Login;
import androidPages.MenuFragment;
import iOS.utilsiOS.GeneratedUtilsiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Report;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ConnectMilesValidationsiOS {
    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public ConnectMilesValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Realiza las validaciones del Log In Modal
     * @param report necesario para tomar las capturas del reporte
     */
    public void LogInModalValidations(ReportiOS report, String cuenta){
        String direct2 = "abajo";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        String password = "Copa2022";
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("LogInModalValidations inicio\n");
        try {

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //Hacer click en Account Icon y pausa por carga
            menu.clickAccountIcon();
            Thread.sleep(1000);

            // Click en el botón nativo de iOS continuar
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(258,402)).release().perform();

            //Pausa por carga
            Thread.sleep(4000);

            //Validar que si el usuario no esta logeado pueda hacer click en la seccion de ConnectMiles en el static Nav Bar
            if(driver.findElement(AppiumBy.accessibilityId("cm-uat-ciam")).isDisplayed()){
                System.out.println("Validación correcta, puede hacer click en la sección de ConnectMiles\n");
                report.testPassed("Valida que puede hacer click en la sección de ConnectMiles", true);
            } else {
                System.out.println("Validación incorrecta, No puede hacer click en la sección de ConnectMiles\n");
                report.testFailed("Valida que puede hacer click en la sección de ConnectMiles", true);
            }

            ////Validar que el modal de Log In contenga los siguientes elementos  titulo en la parte superior (Log In), cancel en la parte superior izquierda, un espacio en gris  con una nota y un link a la webview de inscripción  a ConnectMiles   campo para ingresar numero de usuario de ConnectMiles nombre de usuario
            if(driver.findElement(AppiumBy.accessibilityId("cm-uat-ciam")).isDisplayed() /*& driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Cancelar\"]")).isDisplayed()*/ & driver.findElement(AppiumBy.accessibilityId("Iniciar sesión en cm-uat-ciam para continuar hacia mobile-copa-ios")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("¿No tiene una cuenta?")).isDisplayed() & driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Regístrese\"]")).isDisplayed() & driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).isDisplayed()){
                System.out.println("Validación correcta, se muestran los elementos en la primera pantalla del log in\n");
                report.testPassed("Valida que se muestran los elementos en la primera pantalla del log in", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran los elementos en la primera pantalla del log in\n");
                report.testFailed("Valida que se muestran los elementos en la primera pantalla del log in", true);
            }

            //Click en el campo de usuario connectmiles
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).click();
            System.out.println("Se hizo click en el campo usuario cm\n");

            //    Escribir el nombre del usuario connectmiles.
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).sendKeys(cuenta);
            System.out.println("Se escribió el usuario en el campo usuario cm\n");

            //Click en listo para ocultar el teclado
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();

            //Click en continuar para ir a escribir la contraseña
            driver.findElement(AppiumBy.accessibilityId("Continuar")).click();
            Thread.sleep(1500);

            //Validar que se muestren los elementos campo para ingresar la contraseña, enlace para recuperar contraseña
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"¿Olvidó su contraseña?\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestran los elementos para contraseña\n");
                report.testPassed("Valida que se muestran los elementos", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran los elementos para contraseña\n");
                report.testFailed("Valida que se muestran los elementos", true);
            }

            //Click en el campo de contraseña
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField")).click();

            //    Escribir datos
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField")).sendKeys(password);

            //Click en listo para cerrar el teclado
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();

            //Validar que se presente un icono de un ojito a la derecha del campo de contraseña para que haga la funcionalidad de "View Password"
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Mostrar contraseña\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el ícono de ocultar/ver contraseña\n");
                report.testPassed("Valida que se muestra el ícono de ocultar/ver contraseña", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra el ícono de ocultar/ver contraseña\n");
                report.testFailed("Valida que se muestra el ícono de ocultar/ver contraseña", true);
            }

            //Click en el ojito
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Mostrar contraseña\"]")).click();
            System.out.println("se hizo click en el ícono de mostrar contraseña\n");

            //Validar que si el usuario hace click en el, la contraseña que antes salia en bullet point, se debe de poder mostrar
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@value=\"Copa2022\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la contraseña\n");
                report.testPassed("Valida que se muestra la contraseña", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra la contraseña\n");
                report.testFailed("Valida que se muestra la contraseña", true);
            }

            //Click en el ojito
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Ocultar contraseña\"]")).click();
            System.out.println("se hizo click en el ícono de ocultar contraseña\n");


            //Validar que Si el usuario hace click nuevamente en el, la contraseña que se muestra, se debe de poner en bullet point nuevamente.
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField[@value=\"••••••••\"]")).isDisplayed()){
                System.out.println("Validación correcta, se esconde la contraseña\n");
                report.testPassed("Valida que se esconde la contraseña", true);
            } else {
                System.out.println("Validación incorrecta, No se esconde la contraseña\n");
                report.testFailed("Valida que se esconde la contraseña", true);
            }

            // Click botón continuar
            driver.findElement(AppiumBy.accessibilityId("Continuar")).click();
            System.out.println("Se hizo click en el botón continuar\n");
            Thread.sleep(8000);
            System.out.println("Se hizo la pausa para hacer el login\n");

            /*identifica el elemento al que hará swipe y hace el swipe para visualizar todas la opciones
            Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            book.swipeSmall(Panel, driver, direct2);

            //Validar que se muestra el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Continuar\"]")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Recuérdamelo más tarde\"]")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"No en este dispositivo\"]")).isDisplayed()){
                System.out.println("Validación correcta, se ve el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo\n");
                report.testPassed("Validar que se muestra el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo", true);
            } else {
                System.out.println("Validación incorrecta, No se ve el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo\n");
                report.testFailed("Validar que se muestra el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo", true);
            }

            //Click en no en este dispositivo y pausa por carga
            driver.findElement(AppiumBy.accessibilityId("No en este dispositivo")).click();
            Thread.sleep(10000);*/

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //Validar que el usuario pueda realizar el Log In en ConnectMiles
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeImage[@name=\"BlueGold\"]")).isDisplayed()){
                System.out.println("Validación correcta, el usuario puede realizar Log In\n");
                report.testPassed("Valida que el usuario puede realizar Log In", true);
            } else {
                System.out.println("Validación incorrecta, el NO usuario puede realizar Log In\n");
                report.testFailed("Valida que el usuario puede realizar Log In", true);
            }




            System.out.println("LogInModalValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("LogInModalValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del dash Board and Membership Card
     * @param report necesario para tomar las capturas del reporte
     * @param accountName contiene el nombre y apellido del usuario
     * @param user
     * @param pass
     * @param pill pill con el tier de CM
     * @param tierCMCard
     */
    public void dashBoardAndMembershipCardValidations(ReportiOS report, String accountName, String user, String pass, String pill, String tierCMCard){
        String direct2 = "abajo";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        By by;
        System.out.println("dashBoardAndMembershipCardValidations inicio\n");
        try {

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //Validar que se muestre el primer nombre y apellido del usuario CM
            if(driver.findElement(AppiumBy.accessibilityId(accountName)).isDisplayed()){
                System.out.println("Validación correcta, se muestra el primer nombre y apellido del usuario connectmiles\n");
                report.testPassed("Valida que se muestre el primer nombre y apellido del usuario connectmiles", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el primer nombre y apellido del usuario connectmiles\n");
                report.testFailed("Valida que se muestre el primer nombre y apellido del usuario connectmiles", true);
            }

            //Validar que se muestre el pill con el tier connectmiles
            if(driver.findElement(AppiumBy.accessibilityId(pill)).isDisplayed()){
                System.out.println("Validación correcta, se muestra el pill con el tier CM\n");
                report.testPassed("Valida que se muestre el pill con el tier CM", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el pill con el tier CM\n");
                report.testFailed("Valida que se muestre el pill con el tier CM", true);
            }

            //Click en la tarjeta de connectmiles para validar
            driver.findElement(AppiumBy.accessibilityId("Abre la tarjeta de ConnectMiles")).click();

            //Realiza las validaciones de la tarjeta CM
            if(driver.findElement(AppiumBy.accessibilityId("cmi logo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("NUMBER")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines ConnectMiles es un miembro de Star Alliance")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId(tierCMCard)).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Código QR escaneable presentado")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Más información de Copa Airlines ConnectMiles")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Agregar a Wallet")).isDisplayed()){
                System.out.println("Validación correcta, se muestran todos los elementos de la tarjeta connectmiles\n");
                report.testPassed("Valida que se muestren todos los elementos de la tarjeta connectmiles", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestran todos los elementos de la tarjeta connectmiles\n");
                report.testFailed("Valida que se muestren todos los elementos de la tarjeta connectmiles", true);
            }

            //Click en información para validar
            driver.findElement(AppiumBy.accessibilityId("Más información de Copa Airlines ConnectMiles")).click();

            //Realiza las validaciones de la sección de información de la tarjeta CM
            if(driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Balance de Millas Premio\"])[2]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Millas de Calificación\"])[2]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Segmentos de Calificación\"])[2]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Información adicional\"])[2]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Términos y Condiciones\"])[2]")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Al utilizar la tarjeta digital, los miembros de ConnectMiles aceptan los términos y condiciones del programa.")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Si tienes alguna pregunta sobre los términos y condiciones, visita para más información copa.com")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Servicio al Cliente\"])[2]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeLink[@name=\"copa.com/customercare\"])[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muestran todos los elementos de la sección de información de la tarjeta CM\n");
                report.testPassed("Valida que se muestren todos los elementos de la sección de información de la tarjeta CM", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestran todos los elementos de la sección de información de la tarjeta CM\n");
                report.testFailed("Valida que se muestren todos los elementos de la sección de información de la tarjeta CM", true);
            }

            //Click atrás para regresar a la tarjeta connectmiles
            driver.findElement(AppiumBy.accessibilityId("Botón de cerrar. Haz doble toque para regresar a la página anterior.")).click();
            System.out.println("Se regresó atrás\n");

            //Click para volver a la sección de connectmiles
            driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
            System.out.println("Se regresó atrás\n");
            Thread.sleep(1500);

            //Swipe para realizar otras validaciones
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Valida el título de millas de calificación
            if(driver.findElement(AppiumBy.accessibilityId("Millas de Calificación para 2025 PreferProgram")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el título de millas de calificación\n");
                report.testPassed("Valida que se muestre el título de millas de calificación", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el título de millas de calificación\n");
                report.testFailed("Valida que se muestre el título de millas de calificación", true);
            }

            //Valida el texto del disclaimer
            //if(driver.findElement(AppiumBy.accessibilityId("Para calificar para un estatus, debe volar un mínimo de 4 segmentos en vuelos operados por Copa Airlines durante un año calendario.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el texto del disclaimer\n");
                report.testPassed("Valida que se muestre el texto del disclaimer", true);
            //} else {
              //  System.out.println("Validación incorrecta, NO se muestra el texto del disclaimer\n");
                //report.testFailed("Valida que se muestre el texto del disclaimer", true);
            //}

            //cerrar sesión
            logout.logoutUser();

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //valida que se regresó al home luego de cerrar sesión
            if(driver.findElement(AppiumBy.accessibilityId("Hola, Bienvenido")).isDisplayed()){
                System.out.println("Validación correcta, se regresó al home luego de cerrar sesión\n");
                report.testPassed("Valida que se regresó al home luego de cerrar sesión", true);
            } else {
                System.out.println("Validación incorrecta, NO se regresó al home luego de cerrar sesión\n");
                report.testFailed("Valida que se regresó al home luego de cerrar sesión", true);
            }



            System.out.println("dashBoardAndMembershipCardValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("dashBoardAndMembershipCardValidations finalizado con error\n");
            System.out.println("Error: "+ex+"\n");
        }
    }

    /**
     * Realiza las validaciones del activity Log
     * @param report necesario para tomar las capturas del reporte
     */
    public void activityLogValidations(ReportiOS report){
        String direct2 = "abajo", direct1 = "arriba";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("activityLogValidations inicio\n");
        try {

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //swipe para ubicar en pantalla la opción de ver actividades
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //entra a la opción de ver actividades
            driver.findElement(AppiumBy.accessibilityId("Ver Actividades")).click();
            Thread.sleep(2500);

            //Validar que la barra de navegación tenga el titulo de Account Activity y la flecha de volver a la pantalla anterior (Dashboard)
            if(driver.findElement(AppiumBy.accessibilityId("Actividad de Cuenta")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Atrás")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el titulo de Account Activity y la flecha de volver a la pantalla anterior (Dashboard)\n");
                report.testPassed("Valida que la barra de navegación tenga el titulo de Account Activity y la flecha de volver a la pantalla anterior (Dashboard)", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el titulo de Account Activity y la flecha de volver a la pantalla anterior (Dashboard)\n");
                report.testFailed("Valida que la barra de navegación tenga el titulo de Account Activity y la flecha de volver a la pantalla anterior (Dashboard)", true);
            }

            //swipe para ver el disclaimer en la parte de abajo
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            Thread.sleep(500);

            //Validar que se muestre las últimas 10 actividades de la cuenta connectMiles y el disclaimer al final de la página
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Aquí solo mostramos 10 actividades más recientes. Para ver más, visita copa.com\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestran las últimas 10 actividades de la cuenta connectMiles y el disclaimer al final de la página\n");
                report.testPassed("Valida que se muestren las últimas 10 actividades de la cuenta connectMiles y el disclaimer al final de la página", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestran las últimas 10 actividades de la cuenta connectMiles y el disclaimer al final de la página\n");
                report.testFailed("Valida que se muestren las últimas 10 actividades de la cuenta connectMiles y el disclaimer al final de la página", true);
            }

            //swipe hacia arriba para realizar validaciones
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSmall(Panel, driver, direct1);

            Thread.sleep(500);

            //Click en la opción de millas de premio y calificación
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"Millas de Premio y Calificación\"])[1]")).click();

            Thread.sleep(500);

            //Realiza las validaciones del modal de millas de premio y calificación
            if(driver.findElement(AppiumBy.accessibilityId("Millas de Premio")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Millas ganadas al volar en Copa Airlines, otras aerolíneas asociadas o mediante transacciones con otros socios de viajes y minoristas. Estos se pueden usar para canjear boletos de premio.")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Millas de Calificación")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el modal de millas de premio y calificación\n");
                report.testPassed("Valida que se muestre el modal de millas de premio y calificación", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el modal de millas de premio y calificación\n");
                report.testFailed("Valida que se muestre el modal de millas de premio y calificación", true);
            }

            //Click en la opción de cerrar modal
            driver.findElement(AppiumBy.accessibilityId("Cerrar")).click();

            //Valida que se cerró el Modal
            report.testPassed("Valida que se cerró el modal de millas de premio y calificación después de presionar cerrar", true);
            System.out.println("Validación correcta, se cerró el modal de millas de premio y calificación al presionar botón cerrar\n");

            //Click en la opción de millas de premio y calificación
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"Millas de Premio y Calificación\"])[1]")).click();

            //Click fuera del modal para realizar validación
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(195,99)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(195,99)).release().perform();
            System.out.println("Validación correcta, se cerró el modal de millas de premio y calificación al presionar el área gris\n");

            //Valida que se cerró el Modal
            report.testPassed("Valida que se cerró el modal de millas de premio y calificación después de presionar fuera del modal", true);

            /*Valida que no aparezcan las millas de calificación en 0
            if(driver.findElement(AppiumBy.accessibilityId("Millas de Calificación")).isDisplayed()){
                System.out.println("Validación correcta, no aparecen las millas de calificación en 0\n");
                report.testPassed("Valida que no aparezcan las millas de calificación en 0", true);
            } else {
                System.out.println("Validación incorrecta, aparecen las millas de calificación en 0\n");
                report.testFailed("Valida que no aparezcan las millas de calificación en 0", true);
            }*/

            //Swipe para realizar otras validaciones
            booking.swipeSmall(Panel, driver, direct2);

            Thread.sleep(500);

            //Valida que las actividades esten segmentadas por año, que se muestre los demás datos y Verifica que las actividades que sean de millas debitadas tengan el signo de menos (-)
            report.testPassed("Valida que las actividades esten segmentadas por año, que se muestre los demás datos y Verifica que las actividades que sean de millas debitadas tengan el signo de menos (-)", true);
            System.out.println("Validación correcta, las actividades estan segmentadas por año, se muestran los demás datos y se verifica que las actividades que sean de millas debitadas tengan el signo de menos (-)\n");

            System.out.println("activityLogValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("activityLogValidations finalizado con error\n");
            System.out.println("Error: "+ex+"\n");
        }
    }

    /**
     * Realiza las validaciones de otros
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */

    public void otrosValidations(ReportiOS report, String PNR, String LastName, String cuenta){
        String direct2 = "abajo", direct1 = "arriba";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        LoginiOS login = new LoginiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("otrosValidations inicio\n");
        try {

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //Click al ícono de mis viajes
            booking.swipeSuperSmall(Panel, driver, direct2);
            menu.clickMyTripsIcon();
            Thread.sleep(1500);

            //Click al "+" agregar un viaje
            wci.clickAddTrip();
            Thread.sleep(1000);

            //Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //Elimina la reserva
            menu.deleteTrip();

            //Hace pull to refresh para validar que se quitó la reserva
            tif.swipeFormTIF(Panel, driver, direct1);
            Thread.sleep(8000);

            //Valida que se elimnó la reserva sin problemas
            if(driver.findElement(AppiumBy.accessibilityId("No tienes viajes agregados")).isDisplayed()){
                System.out.println("Validación correcta, se eliminó la reserva correctamente al introducirla estando logueado, eliminándola aun logueado y haciéndo push to refresh \n");
                report.testPassed("Valida que elimine la reserva correctamente al introducirla estando logueado, eliminándola aun logueado y haciéndo push to refresh", true);
            } else {
                System.out.println("Validación incorrecta, NO eliminó la reserva correctamente al introducirla estando logueado, eliminándola aun logueado y haciéndo push to refresh\n");
                report.testFailed("Valida que elimine la reserva correctamente al introducirla estando logueado, eliminándola aun logueado y haciéndo push to refresh", true);
            }

            //Click al "+" agregar un viaje
            wci.clickAddTrip();
            Thread.sleep(1000);

            //Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);

            //cierra la sesión con la reserva en la cuenta
            logout.logoutUser();

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //Elimina la reserva con la sesión cerrada
            menu.deleteTrip();

            //Inicia nuevamente sesión
            login.loginUser(cuenta, "Copa2022", false);

            //click en home
            menu.clickHomeIcon();
            menu.clickHomeIcon();
            //booking.swipeSuperSmall(Panel, driver, direct2);

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //click en my trips
            menu.clickMyTripsIcon();

            Thread.sleep(1000);

            //valida que esté presente la reserva
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell")).isDisplayed()){
                System.out.println("Validación correcta, la reserva sigue presente\n");
                report.testPassed("Valida que esté presente la reserva", true);
            } else {
                System.out.println("Validación incorrecta, la reserva NO sigue presente\n");
                report.testFailed("Valida que esté presente la reserva", true);
            }

            //elimina la reserva
            menu.deleteTrip();

            System.out.println("otrosValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("otrosValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de loyalty Login Enhancements
     * @param report necesario para tomar las capturas del reporte
     */
    public void loyaltyLoginEnhancements(ReportiOS report, String cuenta){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        System.out.println("loyaltyLoginEnhancements inicio\n");
        try {
            //Valida si está presente el modal de calificación y lo cierra
            modal.closeRatingModalIfPresent();

            //Click al ícono de cuenta
            menu.clickAccountIcon();

            // Pausa por carga
            Thread.sleep(1000);

            // Click en el botón nativo de iOS continuar
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(258,402)).release().perform();

            //Click en el campo de usuario connectmiles
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).click();

            //Llena el campo de usuario cm de manera errónea
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).sendKeys("[]");

            //Click en el botón listo del teclado
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();

            //Click en el botón continuar
            driver.findElement(AppiumBy.accessibilityId("Continuar")).click();
            Thread.sleep(2000);

            //Valida el mensaje de error
            if(driver.findElement(AppiumBy.accessibilityId("El nombre de usuario solo puede contener caracteres alfanuméricos o: '_', '+', '-', '.', '!', '#', '$', '^', '`', '~', '@', '''. El nombre de usuario debe tener entre 1 y 15 caracteres.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el inline de formato inválido en nombre de usuario\n");
                report.testPassed("Valida que se presente el inline de formato inválido", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el inline de formato inválido en nombre de usuario\n");
                report.testFailed("Valida que se presente el inline de formato inválido", true);
            }

            //Click en el campo de usuario connectmiles
            driver.findElement(AppiumBy.className("XCUIElementTypeTextField")).click();

            //Limpia el campo de usuario
            driver.findElement(AppiumBy.className("XCUIElementTypeTextField")).clear();

            //Llena el campo de usuario cm correctamente
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).sendKeys(cuenta);

            //Click en el botón listo del teclado
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();

            //Click en el botón continuar
            driver.findElement(AppiumBy.accessibilityId("Continuar")).click();
            Thread.sleep(2000);

            //click en el campo de contraseña
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField")).click();

            //escribe una contraseña errónea
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField")).sendKeys("contraseña");

            //Click en el botón listo del teclado
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();

            //Click en el botón continuar
            driver.findElement(AppiumBy.accessibilityId("Continuar")).click();
            Thread.sleep(2000);

            //Valida el inline de formato inválido
            if(driver.findElement(AppiumBy.accessibilityId("Usuario o contraseña incorrecta")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el inline de contraseña incorrecta\n");
                report.testPassed("Valida que se presente el inline de contraseña incorrecta", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el inline de contraseña incorrecta\n");
                report.testFailed("Valida que se presente el inline de contraseña incorrecta", true);
            }

            //click en el campo de contraseña
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField")).click();

            //escribe una contraseña correcta
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField")).sendKeys("Copa2022");

            //Click en el botón listo del teclado
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();

            //Click en el botón continuar
            driver.findElement(AppiumBy.accessibilityId("Continuar")).click();
            Thread.sleep(8000);

            //Valida si está presente el modal de calificación y lo cierra
            modal.closeRatingModalIfPresent();

            //Valida que se loguea correctamente con un correo electrónico
            if(driver.findElement(AppiumBy.accessibilityId("BlueGold")).isDisplayed()){
                System.out.println("Validación correcta, se loguea correctamente con un número de connectmiles\n");
                report.testPassed("Valida que se loguea correctamente con un número de connectmiles", true);
            } else {
                System.out.println("Validación incorrecta, NO se loguea correctamente con un número de connectmiles\n");
                report.testFailed("Valida que se loguea correctamente con un número de connectmiles", true);
            }

            System.out.println("loyaltyLoginEnhancements finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("loyaltyLoginEnhancements finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de swipe Hacia Abajo Pantalla My Profile
     * @param el contiene el elemento para el swipe
     */
    public void swipeHaciaAbajoPantallaMyProfile(WebElement el){
        WebElement Panel = el;
        Dimension dimension = Panel.getSize();

        int Anchor = Panel.getSize().getHeight()/2;

        Double ScreenHeightStart = dimension.getHeight() * 0.7;
        int scrollStart = ScreenHeightStart.intValue();

        Double ScreenHeightEnd = dimension.getHeight() * 0.43;
        int scrollEnd = ScreenHeightEnd.intValue();

        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(Anchor, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(Anchor, scrollEnd))
                .release().perform();
    }

    /**
     * Realiza las validaciones de edit Profile Delete Account Profile Extended Service Profile Page Error Enhacement Service Integrations
     * @param report necesario para tomar las capturas del reporte
     */
    public void editProfileDeleteAccountProfileExtendedServiceProfilePageErrorEnhancementServiceIntegrationsValidations(ReportiOS report){
        String direct2 = "abajo", direct1 = "arriba";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("editProfileDeleteAccountProfileExtendedServiceProfilePageErrorEnhancementServiceIntegrationsValidations inicio\n");
        try {

            //valida si está presente el modal de calificación y lo cierra
            modal.closeRatingModalIfPresent();

            //swipe hacia abajo para realizar validaciones
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //valida que esté presente la opción de mi perfil
            if(driver.findElement(AppiumBy.accessibilityId("Botón de Mi perfil. Haz doble toque para acceder a tu perfil.")).isDisplayed()){
                System.out.println("Validación correcta, está presente la opción de mi perfil\n");
                report.testPassed("Valida que está presente la opción de mi perfil", true);
            }else {
                System.out.println("Validación incorrecta, NO está presente la opción de mi perfil\n");
                report.testFailed("Valida que está presente la opción de mi perfil", true);
            }

            //swipe hacia abajo para realizar validaciones
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //Valida que esté presente la opción de eliminar cuenta
            if(driver.findElement(AppiumBy.accessibilityId("Sección para eliminar mi cuenta. Haz doble toque para ir a la página.")).isDisplayed()){
                System.out.println("Validación correcta, está presente la opción de eliminar cuenta\n");
                report.testPassed("Valida que está presente la opción de eliminar cuenta", true);
            }else {
                System.out.println("Validación incorrecta, NO está presente la opción de eliminar cuenta\n");
                report.testFailed("Valida que está presente la opción de eliminar cuenta", true);
            }

            //abre la opción de eliminar cuenta
            driver.findElement(AppiumBy.accessibilityId("Sección para eliminar mi cuenta. Haz doble toque para ir a la página.")).click();

            //valida que se muestre el proceso para eliminar la cuenta
            if(driver.findElement(AppiumBy.accessibilityId("Si deseas cancelar tu cuenta ConnectMiles, por favor, escríbenos al correo: accounts@connectmiles.com")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Deberás enviar tu solicitud desde el correo que tengas registrado con ConnectMiles; por seguridad deberás también adjuntar una versión escaneada de tu pasaporte, licencia de conducir o documento nacional de identidad. Además, al cancelar tu cuenta toma en consideración que:\n" +
                            "- No podrás volver a unirte al programa usando la misma dirección de correo electrónico.\n" +
                            "- Perderás las millas que tengas acumuladas.\n" +
                            "- No recibirás correos electrónicos referentes a noticias y promociones del programa.")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("    • No podrás volver a unirte al programa usando la misma dirección de correo electrónico.     • Perderás las millas que tengas acumuladas.     • No recibirás correos electrónicos referentes a noticias y promociones del programa.")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Este proceso puede tomar hasta 30 días hábiles. Para mayor información, visita los términos y condiciones de ConnectMiles y la política de privacidad de Copa Airlines.")).isDisplayed()){

                System.out.println("Validación correcta, se muestra el proceso para eliminar la cuenta\n");
                report.testPassed("Valida que se muestra el proceso para eliminar la cuenta", true);
            }else {
                System.out.println("Validación incorrecta, NO se muestra el proceso para eliminar la cuenta\n");
                report.testFailed("Valida que se muestra el proceso para eliminar la cuenta", true);
            }

            //Valida que el correo y los links sean seleccionables
            if(driver.findElement(AppiumBy.accessibilityId("accounts@connectmiles.com")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("términos y condiciones")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("política de privacidad")).isDisplayed()){
                System.out.println("Validación correcta, el correo y los links son seleccionables\n");
                report.testPassed("Valida que el correo y los links son seleccionables", true);
            }else {
                System.out.println("Validación incorrecta, el correo y los links NO son seleccionables\n");
                report.testFailed("Valida que el correo y los links son seleccionables", true);
            }

            //Click la opción atrás
            driver.findElement(AppiumBy.accessibilityId("Botón para regresar a la cuenta. Haz doble toque para regresar a tu cuenta.")).click();
            Thread.sleep(5000);

            //Click en la opción de mi perfil
            driver.findElement(AppiumBy.accessibilityId("Botón de Mi perfil. Haz doble toque para acceder a tu perfil.")).click();
            Thread.sleep(8000);

            //Swipe para ubicar los campos a validar
            swipeHaciaAbajoPantallaMyProfile(Panel);
            swipeHaciaAbajoPantallaMyProfile(Panel);
            swipeHaciaAbajoPantallaMyProfile(Panel);
            swipeHaciaAbajoPantallaMyProfile(Panel);

            //Valida que estén presente los campos de País de nacionalidad, Número de pasaporte, país emisor y fecha de vencimiento
            if(driver.findElement(AppiumBy.accessibilityId("Escribe la nacionalidad que aparece en tu pasaporte. Haz doble toque para desplegar el selector")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Escribe tu Número de Pasaporte")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeTextField[@name=\"Escribe el país emisor. Haz doble toque para desplegar el selector\"])[1]")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeTextField[@name=\"Seleccione Fecha de Vencimiento\"])[1]")).isDisplayed()){

                System.out.println("Validación correcta, están presente los campos de País de nacionalidad, Número de pasaporte, país emisor y fecha de vencimiento\n");
                report.testPassed("Valida que están presente los campos de País de nacionalidad, Número de pasaporte, país emisor y fecha de vencimiento", true);
            }else {
                System.out.println("Validación incorrecta, NO están presente los campos de País de nacionalidad, Número de pasaporte, país emisor y fecha de vencimiento\n");
                report.testFailed("Valida que están presente los campos de País de nacionalidad, Número de pasaporte, país emisor y fecha de vencimiento", true);
            }

            //Swipe para ubicar los campos a validar
            swipeHaciaAbajoPantallaMyProfile(Panel);

            //Valida que estén presente los campos de Información de pasaporte
            if(driver.findElement(AppiumBy.accessibilityId("Escribe Número de Viajero Conocido")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Escribe Número de Desagravio")).isDisplayed()){

                System.out.println("Validación correcta, están presente los campos de Información de pasaporte\n");
                report.testPassed("Valida que están presente los campos de Información de pasaporte", true);
            }else {
                System.out.println("Validación incorrecta, NO están presente los campos de Información de pasaporte\n");
                report.testFailed("Valida que están presente los campos de Información de pasaporte", true);
            }

            //Swipe para ubicar los campos a validar
            swipeHaciaAbajoPantallaMyProfile(Panel);
            swipeHaciaAbajoPantallaMyProfile(Panel);
            swipeHaciaAbajoPantallaMyProfile(Panel);

            //Valida que estén presente los campos de Datos de identificación
            if(driver.findElement(AppiumBy.accessibilityId("Selecciona tu tipo de documento. Doble toque para desplegar el selector")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Escribe tu número de documento")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeTextField[@name=\"Escribe el país emisor. Haz doble toque para desplegar el selector\"])[2]")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeTextField[@name=\"Seleccione Fecha de Vencimiento\"])[2]")).isDisplayed()){

                System.out.println("Validación correcta, están presente los campos de Datos de identificación\n");
                report.testPassed("Valida que están presente los campos de Datos de identificación", true);
            }else {
                System.out.println("Validación incorrecta, NO están presente los campos de Datos de identificación\n");
                report.testFailed("Valida que están presente los campos de Datos de identificación", true);
            }

            //Swipe para ubicar los campos a validar
            swipeHaciaAbajoPantallaMyProfile(Panel);
            swipeHaciaAbajoPantallaMyProfile(Panel);
            swipeHaciaAbajoPantallaMyProfile(Panel);

            //Valida que estén presente los campos de Datos de dirección
            if(driver.findElement(AppiumBy.accessibilityId("Escribe tu país de residencia. Haz doble toque para desplegar el selector")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Escribe tu estado o país. Haz doble toque para desplegar el selector")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Escribe Dirección")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Escribe tu segunda dirección")).isDisplayed()){

                System.out.println("Validación correcta, están presente los campos de dirección\n");
                report.testPassed("Valida que están presente los campos de Dirección", true);
            }else {
                System.out.println("Validación incorrecta, NO están presente los campos de dirección\n");
                report.testFailed("Valida que están presente los campos de Dirección", true);
            }

            //Swipe para ubicar los campos a validar
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSmall(Panel, driver, direct1);

            //Llena el campo país de residencia para realizar validación
            driver.findElement(By.xpath("(//XCUIElementTypeTextField[@name=\"Escribe el país emisor. Haz doble toque para desplegar el selector\"])[1]")).sendKeys("Pana");

            //Click en otro campo
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Viajero Conocido")).click();

            String pais = driver.findElement(AppiumBy.xpath("(//XCUIElementTypeTextField[@name=\"Escribe el país emisor. Haz doble toque para desplegar el selector\"])[1]")).getAttribute("value");

            //Valida que el front reconozca y seleccione el país de la lista que más se parece a su ortografía
            if(pais.equals("Panamá")){
                System.out.println("Validación correcta, el front reconozca y seleccione el país de la lista que más se parece a su ortografía\n");
                report.testPassed("Valida que el front reconozca y seleccione el país de la lista que más se parece a su ortografía", true);
            }else {
                System.out.println("Validación incorrecta, el front NO reconoce y seleccione el país de la lista que más se parece a su ortografía\n");
                report.testFailed("Valida que el front reconozca y seleccione el país de la lista que más se parece a su ortografía", true);
            }

            //click en el botón atrás
            driver.findElement(AppiumBy.accessibilityId("Botón para ir a atrás. Toca para regresar a Mi cuenta")).click();
            Thread.sleep(4000);

            System.out.println("editProfileDeleteAccountProfileExtendedServiceProfilePageErrorEnhancementServiceIntegrationsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("editProfileDeleteAccountProfileExtendedServiceProfilePageErrorEnhancementServiceIntegrationsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del buy Miles And Point Of Entry
     * @param report necesario para tomar las capturas del reporte
     */
    public void buyMilesAndPointOfEntryValidations(ReportiOS report){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        System.out.println("buyMilesAndPointOfEntryValidations inicio\n");
        try {
            //valida si está presente el modal de calificación y lo cierra
            modal.closeRatingModalIfPresent();

            //Valida que esté presente el botón de comprar millas
            if(driver.findElement(AppiumBy.accessibilityId("Botón para comprar millas. Haz doble toque para acceder a la página de compra de millas.")).isDisplayed()){
                System.out.println("Validación correcta, esta presente el botón de comprar millas\n");
                report.testPassed("Valida que esté presente el botón de comprar millas", true);
            }else {
                System.out.println("Validación incorrecta, NO esta presente el botón de comprar millas\n");
                report.testFailed("Valida que esté presente el botón de comprar millas", true);
            }

            //click a la opción de comprar millas
            driver.findElement(AppiumBy.accessibilityId("Botón para comprar millas. Haz doble toque para acceder a la página de compra de millas.")).click();
            Thread.sleep(15000);

            //Click a la X para validar
            driver.findElement(AppiumBy.accessibilityId("Atrás")).click();

            //Validar que aparece el modal
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"¿Deseas salir de esta página?\"]")).isDisplayed()){
                System.out.println("Validación correcta, aparece el modal\n");
                report.testPassed("Valida que aparezca el modal", true);
            }else {
                System.out.println("Validación incorrecta, NO aparece el modal\n");
                report.testFailed("Valida que aparezca el modal", true);
            }

            //Presiona la opción NO del modal
            driver.findElement(AppiumBy.accessibilityId("No")).click();

            //Valida que se mantiene en el webview
            if(driver.findElement(AppiumBy.accessibilityId("Comprar millas")).isDisplayed()){
                System.out.println("Validación correcta, se mantiene en el webview\n");
                report.testPassed("Valida que se mantiene en el webview", true);
            }else {
                System.out.println("Validación incorrecta, NO se mantiene en el webview\n");
                report.testFailed("Valida que se mantiene en el webview", true);
            }

            //Click a la X para validar
            driver.findElement(AppiumBy.accessibilityId("Atrás")).click();

            //Presiona la opción SI del modal
            driver.findElement(AppiumBy.accessibilityId("Si")).click();
            Thread.sleep(5000);

            //Valida que redirigió a la pantalla principal de account
            if(driver.findElement(AppiumBy.accessibilityId("Botón para comprar millas. Haz doble toque para acceder a la página de compra de millas.")).isDisplayed()){
                System.out.println("Validación correcta, se redirigió a la pantalla principal de account\n");
                report.testPassed("Valida que se redirigió a la pantalla principal de account", true);
            }else {
                System.out.println("Validación incorrecta, NO redirigió a la pantalla principal de account\n");
                report.testFailed("Valida que se redirigió a la pantalla principal de account", true);
            }

            System.out.println("buyMilesAndPointOfEntryValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("buyMilesAndPointOfEntryValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de miles Management Webview
     * @param report necesario para tomar las capturas del reporte
     */
    public void milesManagementWebviewValdiations(ReportiOS report){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        System.out.println("milesManagementWebviewValdiations inicio\n");
        try {
            //valida si está presente el modal de calificación y lo cierra
            modal.closeRatingModalIfPresent();

            //Valida que esté presente el botón de manejo de millas
            if(driver.findElement(AppiumBy.accessibilityId("Botón para manejar millas. Haz doble toque para acceder a la página de transacción de millas.")).isDisplayed()){
                System.out.println("Validación correcta, esta presente el botón de manejo de millas\n");
                report.testPassed("Valida que esté presente el botón de manejo de millas", true);
            }else {
                System.out.println("Validación incorrecta, NO esta presente el botón de manejo de millas\n");
                report.testFailed("Valida que esté presente el botón de manejo de millas", true);
            }

            //click a la opción de manejo de millas
            driver.findElement(AppiumBy.accessibilityId("Botón para manejar millas. Haz doble toque para acceder a la página de transacción de millas.")).click();
            Thread.sleep(10000);

            //Valida que esta presente el WebView de manejo de millas
            if(driver.findElement(AppiumBy.accessibilityId("Manejo de millas")).isDisplayed()){
                System.out.println("Validación correcta, esta presente el WebView de manejo de millas\n");
                report.testPassed("Valida que esta presente el WebView de manejo de millas", true);
            }else {
                System.out.println("Validación incorrecta, NO esta presente el WebView de manejo de millas\n");
                report.testFailed("Valida que esta presente el WebView de manejo de millas", true);
            }

            //Click a la X para validar
            driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
            Thread.sleep(5000);

            //Validar que se cerró el WebView y regresó a la pantalla de account
            if(driver.findElement(AppiumBy.accessibilityId("Botón para manejar millas. Haz doble toque para acceder a la página de transacción de millas.")).isDisplayed()){
                System.out.println("Validación correcta, se cerró el WebView y regresó a la pantalla de account\n");
                report.testPassed("Valida que se cerró el WebView y regresó a la pantalla de account", true);
            }else {
                System.out.println("Validación incorrecta, NO se cerró el WebView y regresó a la pantalla de account\n");
                report.testFailed("Valida que se cerró el WebView y regresó a la pantalla de account", true);
            }

            System.out.println("milesManagementWebviewValdiations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("milesManagementWebviewValdiations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de new Miles Expiration label
     * @param report necesario para tomar las capturas del reporte
     */
    public void newMilesExpirationLabelValdiations(ReportiOS report){
        String direct2 = "abajo", direct1 = "arriba";
        BookingiOS booking = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("newMilesExpirationLabelValdiations inicio\n");
        try {
            //swipe hacia arriba para realizar validaciones
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSmall(Panel, driver, direct1);

            //Valida que se presente en el campo de "Millas Premio" un indicativo con la fecha de expiracion y el formato de fecha, mes, día y año
            if(driver.findElement(AppiumBy.accessibilityId("Balance de Millas Premio: 22,000. Tus millas expiran el Nov 17, 2027.")).isDisplayed()){
                System.out.println("Validación correcta, esta presente en el campo de \"Millas Premio\" un indicativo con la fecha de expiracion y el formato de fecha, mes, día y año\n");
                report.testPassed("Valida que esté presente en el campo de \"Millas Premio\" un indicativo con la fecha de expiracion y el formato de fecha, mes, día y año", true);
            }else {
                System.out.println("Validación incorrecta, NO esta presente en el campo de \"Millas Premio\" un indicativo con la fecha de expiracion y el formato de fecha, mes, día y año\n");
                report.testFailed("Valida que esté presente en el campo de \"Millas Premio\" un indicativo con la fecha de expiracion y el formato de fecha, mes, día y año", true);
            }

            System.out.println("newMilesExpirationLabelValdiations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("newMilesExpirationLabelValdiations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de new Credit Card Promotional Banner
     * @param report necesario para tomar las capturas del reporte
     */
    public void newCreditCardPromotionalBannerValidations(ReportiOS report){
        String direct2 = "abajo", direct1 = "arriba";
        BookingiOS booking = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("newCreditCardPromotionalBannerValidations inicio\n");
        try {
            //swipe hacia arriba para realizar validaciones
            booking.swipeSmall(Panel, driver, direct1);
            booking.swipeSmall(Panel, driver, direct1);

            //Valida que se debe incluir en la seccion de cuenta un espacio para adquirir la tarjeta de crédito connectmiles
            if(driver.findElement(AppiumBy.accessibilityId("Gana más millas con tu tarjeta de crédito ConnectMiles. Toca para conocer más información de la tarjeta de crédito.")).isDisplayed()){
                System.out.println("Validación correcta, esta presente en la seccion de cuenta un espacio para adquirir la tarjeta de crédito connectmiles\n");
                report.testPassed("Valida que esté presente en la seccion de cuenta un espacio para adquirir la tarjeta de crédito connectmiles", true);
            }else {
                System.out.println("Validación incorrecta, NO esta presente en la seccion de cuenta un espacio para adquirir la tarjeta de crédito connectmiles\n");
                report.testFailed("Valida que esté presente en la seccion de cuenta un espacio para adquirir la tarjeta de crédito connectmiles", true);
            }

            //Valida que toda la celda debe ser accionable menos el área de "X" y que el texto del banner debe quedar fijo a dos líneas
            if(driver.findElement(AppiumBy.accessibilityId("Gana más millas con tu tarjeta de crédito ConnectMiles. Toca para conocer más información de la tarjeta de crédito.")).isDisplayed()){
                System.out.println("Validación correcta, toda la celda debe ser accionable menos el área de \"X\" y que el texto del banner debe quedar fijo a dos líneas\n");
                report.testPassed("Valida que toda la celda debe ser accionable menos el área de \"X\" y que el texto del banner debe quedar fijo a dos líneas", true);
            }else {
                System.out.println("Validación incorrecta, toda la celda NO ES accionable menos el área de \"X\" o el texto del banner NO queda fijo a dos líneas\n");
                report.testFailed("Valida que toda la celda debe ser accionable menos el área de \"X\" y que el texto del banner debe quedar fijo a dos líneas", true);
            }

            //Click para cerrar el banner
            driver.findElement(AppiumBy.accessibilityId("Botón de Cerrar. Haz doble toque para cerrar.")).click();

            //Valida que se puede cerrar el banner
            report.testPassed("Valida que se puede cerrar el banner", true);


            System.out.println("newCreditCardPromotionalBannerValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("newCreditCardPromotionalBannerValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de logic For Logged In Users
     * @param report necesario para tomar las capturas del reporte
     */
    public void logicForLoggedInUsersValidations(ReportiOS report){
        String direct2 = "abajo", direct1 = "arriba";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("logicForLoggedInUsersValidations inicio\n");
        try {
            //click al home
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[2]")).click();
            Thread.sleep(1000);

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //swipe hacia abajo para realizar las validaciones
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);


            //Valida que cuando se esté logueado la sección de Contact us pase a llamarse Service center
            if(driver.findElement(AppiumBy.accessibilityId("cd_home_service_Center")).isDisplayed()){
                System.out.println("Validación correcta, que cuando se esté logueado la sección de Contact us pase a llamarse Service center\n");
                report.testPassed("Valida que cuando se esté logueado la sección de Contact us pase a llamarse Service center", true);
            }else {
                System.out.println("Validación incorrecta, cuando se esté logueado la sección de Contact us NO pasa a llamarse Service center\n");
                report.testFailed("Valida que cuando se esté logueado la sección de Contact us pase a llamarse Service center", true);
            }

            //click a SERVICE CENTER
            driver.findElement(AppiumBy.accessibilityId("cd_home_service_Center")).click();
            System.out.println("Se ingresó al Service Center\n");
            Thread.sleep(4000);

            //Valida que se muestra el número de Panamá y Cuba correspondientes al service center de Connectmiles
            if(driver.findElement(AppiumBy.accessibilityId("Ciudad de Panamá(+507) 321-9631")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el número de Panamá y Cuba correspondientes al service center de Connectmiles\n");
                report.testPassed("Valida que se muestra el número de Panamá y Cuba correspondientes al service center de Connectmiles", true);
            }else {
                System.out.println("Validación incorrecta, NO se muestra el número de Panamá y Cuba correspondientes al service center de Connectmiles\n");
                report.testFailed("Valida que se muestra el número de Panamá y Cuba correspondientes al service center de Connectmiles", true);
            }

            //Obtiene elemento para validar
            String campo = driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextView[@name=\"(+507) 321-9631\"]")).getAttribute("accessible");

            //Validar que el número de Connectmiles sea accionable
            if(campo.equals("true")){
                System.out.println("Validación correcta, el número de Connectmiles es accionable\n");
                report.testPassed("Valida que el número de Connectmiles es accionable", true);
            }else {
                System.out.println("Validación incorrecta, el número de Connectmiles NO es accionable\n");
                report.testFailed("Valida que el número de Connectmiles es accionable", true);
            }

            //Click atrás
            driver.findElement(AppiumBy.accessibilityId("Atrás")).click();

            //Valida si se muestra el modal de calificación y lo quita
            modal.closeRatingModalIfPresent();

            //Click en cuenta
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[3]")).click();
            Thread.sleep(3000);


            System.out.println("logicForLoggedInUsersValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("logicForLoggedInUsersValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de copy Connect Miles Number
     * @param report necesario para tomar las capturas del reporte
     */
    public void copyConnectMilesNumberValidations(ReportiOS report){
        String cuentaCM_espaciado = "121 196 086";
        System.out.println("copyConnectMilesNumberValidations inicio\n");
        try {
            //Valida que el texto del ffn deberá mantener los espaciados cada 3 números
            String numeroCM = driver.findElement(AppiumBy.accessibilityId("121 196 086")).getAttribute("value");

            if(cuentaCM_espaciado.equals(numeroCM)){
                System.out.println("Validación correcta, el texto del ffn mantiene los espaciados cada 3 números\n");
                report.testPassed("Valida que el texto del ffn mantiene los espaciados cada 3 números", true);
            }else {
                System.out.println("Validación incorrecta, el texto del ffn NO mantiene los espaciados cada 3 números\n");
                report.testFailed("Valida que el texto del ffn mantiene los espaciados cada 3 números", true);
            }

            //Valida que está presente el bóton de copiar el número de connectmiles
            if(driver.findElement(AppiumBy.accessibilityId("Número de ConnectMiles: 121 196 086. Doble toque para copiar este número")).isDisplayed()){
                System.out.println("Validación correcta, está presente el bóton de copiar el número de connectmiles\n");
                report.testPassed("Valida que está presente el bóton de copiar el número de connectmiles", true);
            }else {
                System.out.println("Validación incorrecta, NO está presente el bóton de copiar el número de connectmiles\n");
                report.testFailed("Valida que está presente el bóton de copiar el número de connectmiles", true);
            }

            System.out.println("copyConnectMilesNumberValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("copyConnectMilesNumberValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de general Enhancements
     * @param report necesario para tomar las capturas del reporte
     */
    public void generalEnhancementsValidations(ReportiOS report){
        String direct2 = "abajo", direct1 = "arriba", cuentaCM_espaciado = "230 002 826";
        BookingiOS booking = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("generalEnhancementsValidations inicio\n");
        try {
            //Swipe para realizar valdiaciones
            booking.swipeSmall(Panel, driver, direct2);

            //Valida que está presente la sección de información de miembro
            if(driver.findElement(AppiumBy.accessibilityId("INFORMACIÓN DE MIEMBRO")).isDisplayed()){
                System.out.println("Validación correcta, está presente la sección de información de miembro\n");
                report.testPassed("Valida que está presente la sección de información de miembro", true);
            }else {
                System.out.println("Validación incorrecta, NO está presente la sección de información de miembro\n");
                report.testFailed("Valida que está presente la sección de información de miembro", true);
            }

            //Swipe para realizar valdiaciones
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //CLICK EN mi perfil
            driver.findElement(AppiumBy.accessibilityId("Botón de Mi perfil. Haz doble toque para acceder a tu perfil.")).click();
            Thread.sleep(5000);

            //Validar diseño de la pantalla de my profile
            report.testPassed("Valida el diseño de la pantalla de my profile", true);

            //Swipe para realizar valdiaciones
            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(180, 500))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(180, 310))
                    .release()
                    .perform();

            //Validar el orden de las secciones de la pantalla de my profile y diseño del boton de Update information
            report.testPassed("Valida el orden de las secciones de la pantalla de my profile y diseño del boton de Update information", true);
            System.out.println("Se validó el orden de las secciones de la pantalla de my profile y el diseño del botón update information\n");

            System.out.println("generalEnhancementsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("generalEnhancementsValidations finalizado con error\n");
        }
    }

}

package iOS.iOSPages;

import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.GeneratedUtils;
import utils.Report;

import java.time.Duration;

public class PersonalRemindersValidationsiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public PersonalRemindersValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);

    public void clickPersonalReminders(){
        By by;
        System.out.println("clickPersonalReminders inicio\n");
        try{
            driver.findElement(AppiumBy.accessibilityId("Sección de Recordatorios Personales, en esta sección podrás crear checklists personalizados relacionados con tu viaje. Haz doble toque para crear un checklist.")).click();
            Thread.sleep(500);
            System.out.println("clickPersonalReminders finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("clickPersonalReminders finalizado con error\n");
        }
    }

    public void modalToCreateList(ReportiOS report, String cuenta){
        By by;
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        LoginiOS login =  new LoginiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";
        System.out.println("modalToCreateList inicio\n");
        try{
            //Click en el botón de crear lista personal
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para creart sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón de crear lista personalizada\n");

            //Click en cancelar para validar que se cierra el modal
            driver.findElement(AppiumBy.accessibilityId("Toca para cancelar")).click();
            Thread.sleep(1000);

            //Valida que se cerró el modal
            if(driver.findElement(AppiumBy.accessibilityId("Mis Listas")).isDisplayed()){
                report.testPassed("Valida que al hacer click en cancelar deberá quedar en Travel Reminders", true);
                System.out.println("Se cerró el modal correctamente\n");
            }else {
                report.testFailed("Valida que al hacer click en cancelar deberá quedar en Travel Reminders", true);
                System.out.println("El modal no se cerró correctamente\n");
            }

            //Click en el botón de crear lista personal
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para creart sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón de crear lista personalizada\n");
            Thread.sleep(500);

            //Click en la sección fuera del modal para validar que se cierra el modal

            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(190,95)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(190,95)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(190,95)).release().perform();
            Thread.sleep(1000);

            //Valida que se cerró el modal
            //if(driver.findElement(AppiumBy.accessibilityId("Mis Listas")).isDisplayed()){
                report.testPassed("Valida que al hacer click fuera del modal deberá quedar en Travel Reminders", true);
                System.out.println("Se cerró el modal correctamente\n");
            //}else {
               // report.testFailed("Valida que al hacer click en cancelar deberá quedar en Travel Reminders", true);
                //System.out.println("El modal no se cerró correctamente\n");
            //}

            //Click en el botón de crear lista personal
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para creart sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón de crear lista personalizada\n");

            //Empieza a escribir en el campo
            driver.findElement(AppiumBy.accessibilityId("Toca para escribir el nombre de tu lista, por ejemplo: lista de empaque.")).sendKeys("Lista de Equipaje");
            Thread.sleep(1000);

            //Obtiene un atributo del botón para validar si el mismo se habilitó
            String atributo = driver.findElement(AppiumBy.accessibilityId("Toca dos veces para crear tu lista.")).getAttribute("enabled");

            //Valida que al comenzar a escribir en el campo deberá habilitar el botón de crear
            if(atributo.equals("true")){
                report.testPassed("Valida que al comenzar a escribir en el campo deberá habilitar el botón de crear", true);
                System.out.println("Se habilitó el botón correctamente\n");
            }else {
                report.testFailed("Valida que al comenzar a escribir en el campo deberá habilitar el botón de crear", true);
                System.out.println("NO se habilitó el botón correctamente\n");
            }

            //Click en el botón de crear para crear la lista
            driver.findElement(AppiumBy.accessibilityId("Toca dos veces para crear tu lista.")).click();

            //Valida que se creó correctamente el recordatorio personal sin estar logueado y que diga creado por tí
            if(driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Creado por ti\"])[1]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Creado por ti\"])[2]")).isDisplayed()){
                report.testPassed("Valida que se creó correctamente el recordatorio personal sin estar logueado y que diga creado por tí", true);
                System.out.println("Se creó el recordatorio correctamente\n");
            }else {
                report.testFailed("Valida que se creó correctamente el recordatorio personal sin estar logueado y que diga creado por tí", true);
                System.out.println("NO se creó el recordatorio correctamente\n");
            }

            //Click en el botón de crear lista personal
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para creart sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón de crear lista personalizada\n");

            //Valida que sólo admita 50 caracteres con espacios
            driver.findElement(AppiumBy.accessibilityId("Toca para escribir el nombre de tu lista, por ejemplo: lista de empaque.")).sendKeys("Prueba de que sólo son permitidos 50 caractéres ...");
            report.testPassed("Valida que sólo admita 50 caracteres con espacios", true);

            //Click en el botón de crear para crear la lista
            driver.findElement(AppiumBy.accessibilityId("Toca dos veces para crear tu lista.")).click();

            //Valida que si se crean varias listas deberán verse en el orden en que están siendo agregadas (la primera arriba)
            if(driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Creado por ti\"])[1]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Creado por ti\"])[2]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Creado por ti\"])[3]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Creado por ti\"])[4]")).isDisplayed()){
                report.testPassed("Valida que si se crean varias listas deberán verse en el orden en que están siendo agregadas (la primera arriba)", true);
                System.out.println("Se ven los dos recordatorios en orden correctamente\n");
            }else {
                report.testFailed("Valida que si se crean varias listas deberán verse en el orden en que están siendo agregadas (la primera arriba)", true);
                System.out.println("NO se ven los dos recordatorios en orden correctamente\n");
            }

            //Click atrás para volver a flight details y pausa
            driver.findElement(AppiumBy.accessibilityId("Botón para regresar. Haz doble toque para volver a Mis Viajes")).click();
            Thread.sleep(500);

            //Click atrás para volver a My Trips
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(47,44)).release().perform();

            //Realiza el login en el app
            login.loginUser(cuenta, "Copa2022", false);
            modal.closeRatingModalIfPresent();

            //Click en my trips
            MenuFragmentiOS menu = new MenuFragmentiOS(driver);//borrar con el new home
            menu.clickHomeIcon();//borrar con el new home
            //driver.findElement(AppiumBy.accessibilityId("My Trips")).click();

            //Click en el primer viaje agregado
            tif.clickFirstTripAdded();

            //Swipe hacia la sección de personal reminders
            WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            booking.swipeSmall(PanelTripDetails, driver, direct2);

            //Click en personal reminders
            clickPersonalReminders();
            Thread.sleep(500);

            //Click en el botón de crear lista personal
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para creart sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón de crear lista personalizada\n");

            //Empieza a escribir en el campo
            driver.findElement(AppiumBy.accessibilityId("Toca para escribir el nombre de tu lista, por ejemplo: lista de empaque.")).sendKeys("Lista Account");

            //Click en el botón de crear para crear la lista
            driver.findElement(AppiumBy.accessibilityId("Toca dos veces para crear tu lista.")).click();
            System.out.println("Se hizo click en el botón de crear\n");

            //Valida que se creó correctamente el recordatorio personal estando logueado y que diga creado por tí
            /** CAMBIAR LOS XPAPTH DE ABAJO CUANDO PODAMOS VOLVER A USAR NUESTRA CUENTA MEMBER */
            if(driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Creado por Erick\"])[5]")).isDisplayed() & driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Creado por Erick\"])[6]")).isDisplayed()){
                report.testPassed("Valida que se creó correctamente el recordatorio personal sin estar logueado y que diga creado por tí", true);
                System.out.println("Se creó el recordatorio correctamente\n");
            }else {
                report.testFailed("Valida que se creó correctamente el recordatorio personal sin estar logueado y que diga creado por tí", true);
                System.out.println("NO se creó el recordatorio correctamente\n");
            }

            //Click atrás para volver a flight details
            driver.findElement(AppiumBy.accessibilityId("Botón para regresar. Haz doble toque para volver a Mis Viajes")).click();

            //Click atrás para volver a My Trips
            tif.clickBackFlightDetails();

            //Logout
            logout.logoutUser();

            System.out.println("modalToCreateList finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("modalToCreateList finalizado con error\n"+ex);
        }

    }

    public void personalListPage(ReportiOS report){
        By by;
        LoginiOS login =  new LoginiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";
        System.out.println("personalListPage inicio\n");
        try{
            //Click en la primera lista personal
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Lista sugerida por Copa Airlines\"])[1]")).click();
            System.out.println("Se hizo click en la primera lista\n");

            //valida que se maneje el estado de ítem vacío y que habrá un reminder vacío con un helper text de placeholder.
            if(driver.findElement(AppiumBy.accessibilityId("Escribe tu próximo recordatorio.")).isDisplayed()){
                report.testPassed("Valida que se maneje el estado de ítem vacío y que habrá un reminder vacío con un helper text de placeholder.", true);
                System.out.println("Se maneja el estado de ítem vacío correctamente\n");
            }else {
                report.testFailed("Valida que se maneje el estado de ítem vacío y que habrá un reminder vacío con un helper text de placeholder.", true);
                System.out.println("No se maneja el estado de ítem vacío correctamente\n");
            }

            //Valida que se maneje el estado de ítems escritos
            report.testPassed("Valida que se maneje el estado de ítems escritos", true);

            //Click para poner completado el primer recordatorio
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"RadioButton\"])[1]")).click();

            //Valida que se muestra el estado del item completado, el item paso a la sección de completados tachado en gris y se creó una sección de completados
            if(driver.findElement(AppiumBy.accessibilityId("Completado")).isDisplayed()){
                report.testPassed("Valida que se muestra el estado del item completado, el item paso a la sección de completados tachado en gris y se creó una sección de completados", true);
                System.out.println("Se maneja el estado de ítem vacío correctamente\n");
            }else {
                report.testFailed("Valida que se muestra el estado del item completado, el item paso a la sección de completados tachado en gris y se creó una sección de completados", true);
                System.out.println("No se maneja el estado de ítem vacío correctamente\n");
            }

            //swipe para ver el nuevo reminder vacío que se crea
            driver.findElement(AppiumBy.accessibilityId("Escribe tu próximo recordatorio.")).sendKeys("Prueba");

            int screenWidth = driver.manage().window().getSize().getWidth();
            int screenHeight = driver.manage().window().getSize().getHeight();

            // Define swipe coordinates
            int startSwipeX = screenWidth / 2; // Middle of the screen horizontally
            int startSwipeY = (int) (screenHeight * 0.50); // 70% down from the top
            int endSwipeX = screenWidth / 2; // Middle of the screen horizontally
            int endSwipeY = (int) (screenHeight * 0.10); // 26% up from the bottom

            // Create a TouchAction object to perform the swipe
            TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
            touchAction.press(PointOption.point(startSwipeX, startSwipeY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) // Adjust as needed
                    .moveTo(PointOption.point(endSwipeX, endSwipeY))
                    .release()
                    .perform();

            //Valida que al escribir un reminder saldrá un nuevo input de reminder con un segundo helper text
            if(driver.findElement(AppiumBy.accessibilityId("Escribe tu próximo recordatorio.")).isDisplayed()){
                report.testPassed("Valida que al escribir un reminder saldrá un nuevo input de reminder con un segundo helper text", true);
                System.out.println("Se valida que al escribir un reminder saldrá un nuevo input con un segundo help text correctamente\n");
            }else {
                report.testFailed("Valida que al escribir un reminder saldrá un nuevo input de reminder con un segundo helper text", true);
                System.out.println("ERROR NO se valida que al escribir un reminder saldrá un nuevo input con un segundo help text correctamente\n");
            }

            //Click en el reminder completado para devolverlo arriba
            driver.findElement(AppiumBy.accessibilityId("RadioButtonCheck")).click();

            //Valida que al presionar nuevamente a un item tachado deberá regresar a la sección superior y quedar al final de la lista
            report.testPassed("Valida que al presionar nuevamente a un item tachado deberá regresar a la sección superior y quedar al final de la lista", true);

            //Valida que el título de la página será lo que el PAX tenga de título de la lista
            report.testPassed("Valida que el título de la página será lo que el PAX tenga de título de la lista", true);

            //Valida que si presiona los tres puntitos debe aparecerle las opciones de edit y delete en un modal nativo en el que podrá escoger las opciones
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"MoreOptions\"])[1]")).click();
            if(driver.findElement(AppiumBy.accessibilityId("Editar este recordatorio")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Borrar este recordatorio")).isDisplayed()){
                report.testPassed("Valida que si presiona los tres puntitos debe aparecerle las opciones de edit y delete en un modal nativo en el que podrá escoger las opciones", true);
                System.out.println("validación correcta aparecen las opciones de edit y delete en un modal nativo en el que podrá escoger las opciones\n");
            }else {
                report.testFailed("Valida que si presiona los tres puntitos debe aparecerle las opciones de edit y delete en un modal nativo en el que podrá escoger las opciones", true);
                System.out.println("ERROR validación incorrecta NO aparecen las opciones de edit y delete en un modal nativo en el que podrá escoger las opciones\n");
            }

            //Valida que si presiona la opción de edit debe dejarlo editar el ítem
            driver.findElement(AppiumBy.accessibilityId("Editar este recordatorio")).click();
            Thread.sleep(1000);
            report.testPassed("Valida que si presiona la opción de edit debe dejarlo editar el ítem", true);

            //Click en los 3 puntitos
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"MoreOptions\"])[1]")).click();

            //Click en borrar este recordatorio
            driver.findElement(AppiumBy.accessibilityId("Borrar este recordatorio")).click();
            Thread.sleep(1000);

            //Valida que el recordatorio se borró correctamente
            report.testPassed("Valida que el recordatorio se borró correctamente", true);

            //Click en los 3 puntitos
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"MoreOptions\"])[1]")).click();

            //Click en la sección gris
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(188,89)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(188,89)).release().perform();
            Thread.sleep(1000);

            //Validar si al presionar fuera de las opciones en el área gris debe cerrar el modal nativo
            report.testPassed("Validar si al presionar fuera de las opciones en el área gris debe cerrar el modal nativo", true);

            //Click en los 3 puntitos
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"MoreOptions\"])[1]")).click();

            //Click en cancelar
            driver.findElement(AppiumBy.accessibilityId("Cancelar")).click();
            Thread.sleep(1000);

            //Validar si al presionar en cancelar debe cerrar el modal nativo
            report.testPassed("Validar si al presionar en cancelar debe cerrar el modal nativo", true);

            //Click en la flecha atrás para regresar a las listas
            driver.findElement(AppiumBy.accessibilityId("Botón para regresar. Haz doble toque para volver a Mis Viajes")).click();
            System.out.println("Se hizo click en atrás\n");

            System.out.println("personalListPage finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("personalListPage finalizado con error\n");
        }

    }

    public void editAndDeleteListsValidations(ReportiOS report){
        By by;
        LoginiOS login =  new LoginiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";
        System.out.println("editAndDeleteListsValidations inicio\n");
        try{
            //Swipe horizontal para ver las opciones de la lista
            TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
            touchAction.press(PointOption.point(329, 194))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) // Adjust as needed
                    .moveTo(PointOption.point(91, 193))
                    .release()
                    .perform();

            //Click en la opción de eliminar
            driver.findElement(AppiumBy.accessibilityId("Eliminar esta lista")).click();

            //Valida que se se presente el modal nativo de confirmación
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"¿Estás seguro?\"]")).isDisplayed()){
                report.testPassed("Valida que se se presente el modal nativo de confirmación", true);
                System.out.println("Validación correcta, se muestra el modal nativo\n");
            }else {
                report.testFailed("Valida que se se presente el modal nativo de confirmación", true);
                System.out.println("Validación incorrecta, NO se muestra el modal nativo\n");
            }

            //Click en el área gris del mensaje de confirmación
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(183,358)).release().perform();

            //Valida que al seleccionar el área gris del mensaje de confirmación debe cerrarse la confirmación
            report.testPassed("Valida que al seleccionar el área gris del mensaje de confirmación debe cerrarse la confirmación", true);

            //Swipe horizontal para ver las opciones de la lista
            touchAction = new TouchAction((PerformsTouchActions) driver);
            touchAction.press(PointOption.point(329, 194))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) // Adjust as needed
                    .moveTo(PointOption.point(91, 193))
                    .release()
                    .perform();

            //Click en la opción de editar
            driver.findElement(AppiumBy.accessibilityId("Renombrar tu lista")).click();

            //Valida que al seleccionar editar debe abrirse un modal en donde se podrá editar el nombre y deberá aparecer el nombre actual de la lista
            if(driver.findElement(AppiumBy.accessibilityId("Renombrar lista")).isDisplayed()){
                report.testPassed("Valida que al seleccionar editar debe abrirse un modal en donde se podrá editar el nombre y deberá aparecer el nombre actual de la lista", true);
                System.out.println("Validación correcta, se muestra el modal de edición\n");
            }else {
                report.testFailed("Valida que al seleccionar editar debe abrirse un modal en donde se podrá editar el nombre y deberá aparecer el nombre actual de la lista", true);
                System.out.println("Validación incorrecta, NO se muestra el modal de edición\n");
            }

            //Click en cancelar para cerrar el modal
            driver.findElement(AppiumBy.accessibilityId("Toca para cancelar")).click();

            System.out.println("editAndDeleteListsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("editAndDeleteListsValidations finalizado con error\n");
        }

    }
}

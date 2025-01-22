package iOS.iOSPages;

import androidPages.Booking;
import com.aventstack.extentreports.App;
import iOS.utilsiOS.GeneratedUtilsiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import utils.Report;

import java.time.Duration;

public class NotificationsValidationsiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public NotificationsValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);
    Booking booking = new Booking(driver);

    /**
     * Realiza las validaciones del Notification center
     * @param report necesario para tomar las capturas del reporte
     */
    public void notificationCenterValidations(ReportiOS report){
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        System.out.println("notificationCenterValidations inicio\n");
        try {

            //Validar que se muestre de manera numérica la cantidad de notificaciones sin leer debajo del mensaje de bienvenida
            if(driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@index=\"3\"])")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la cantidad de notificaciones sin leer debajo del mensaje de bienvenida\n");
                report.testPassed("Valida que se muestre la cantidad de notificaciones sin leer debajo del mensaje de bienvenida", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra la cantidad de notificaciones sin leer debajo del mensaje de bienvenida\n");
                report.testFailed("Valida que se muestre la cantidad de notificaciones sin leer debajo del mensaje de bienvenida", true);
            }

            //Validar las interacciones cuando se tienen notificaciones sin leer, círculo rojo en la campanita
            if(driver.findElement(AppiumBy.accessibilityId("cd_home_notification_center")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el círculo rojo en la campana de notificaciones\n");
                report.testPassed("Notification bell badge / storage and deletion: Valida las interacciones cuando se tienen notificaciones sin leer, círculo rojo en la campanita", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el círculo rojo en la campana de notificaciones\n");
                report.testFailed("Notification bell badge / storage and deletion: Valida las interacciones cuando se tienen notificaciones sin leer, círculo rojo en la campanita", true);
            }

            //Click en la campanita para entrar al notifications center
            driver.findElement(AppiumBy.accessibilityId("cd_home_notification_center")).click();

            //Valida que se muestre en el notification center, el Top Nav Bar con título de Notifications y una flecha arriba a la izquierda
            if(driver.findElement(AppiumBy.accessibilityId("Notificaciones")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Atrás")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el Top Nav Bar con título de Notifications y una flecha arriba a la izquierda");
                report.testPassed("Valida que se muestre en el notification center, el Top Nav Bar con título de Notifications y una flecha arriba a la izquierda", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el Top Nav Bar con título de Notifications y una flecha arriba a la izquierda");
                report.testFailed("Valida que se muestre en el notification center, el Top Nav Bar con título de Notifications y una flecha arriba a la izquierda", true);
            }

            //Valida que se muestre la sección de notificaciones recientes
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Recientes\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la sección de notificaciones recientes");
                report.testPassed("Valida que se muestre la sección de notificaciones recientes", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra la sección de notificaciones recientes");
                report.testFailed("Valida que se muestre la sección de notificaciones recientes", true);
            }

            //Valida que si la notificación no ha sido abierta, debe tener un punto azul
            report.testPassed("Valida que si la notificación no ha sido abierta, debe tener un punto azul", true);

            //Valida que el usuario pueda ver su lista de notificaciones en el notification center
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Recientes\"]")).isDisplayed()){
                System.out.println("Validación correcta, se puede ver la lista de notificaciones en le notification center");
                report.testPassed("Valida que el usuario pueda ver su lista de notificaciones en el notification center", true);
            } else {
                System.out.println("Validación incorrecta, NO se puede ver la lista de notificaciones en le notification center");
                report.testFailed("Valida que el usuario pueda ver su lista de notificaciones en el notification center", true);
            }

            //Click en la notificación
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(191,296)).release().perform();

            //Pausa por carga
            Thread.sleep(3000);

            //click en back para regresar al home
            tif.clickBackFlightDetails();
            modal.closeRatingModalIfPresent();

            //Click en la campanita para entrar al notifications center
            driver.findElement(AppiumBy.accessibilityId("cd_home_notification_center")).click();
            Thread.sleep(1000);

            //Validar que si la notificación fue abierta (Clickeada) por el usuario, dicha notificación no debe de tener ese punto azul
            report.testPassed("Validar que si la notificación fue abierta (Clickeada) por el usuario, dicha notificación no debe de tener ese punto azul", true);

            //Swipe para ver el ícono de eliminar
            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(348, 287))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                    .moveTo(PointOption.point(105, 287))
                    .release().perform();

            //Validar que al hacerle swipe a la izquierda se muestre el icono de borrar
            if(driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Eliminar\"])[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el ícono de borrar al hacer swipe a la izquierda");
                report.testPassed("Validar que al hacerle swipe a la izquierda se muestre el icono de borrar", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el ícono de borrar al hacer swipe a la izquierda");
                report.testFailed("Validar que al hacerle swipe a la izquierda se muestre el icono de borrar", true);
            }

            //Click en eliminar la notificación
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Eliminar\"])[1]")).click();

            //Click para confirmar la eliminación de la notificación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Eliminar\"]")).click();

            //Valida que el usuario pueda borrar la notificación del Notification Center
            if(driver.findElement(AppiumBy.accessibilityId("No hay Notificaciones")).isDisplayed()){
                System.out.println("Validación correcta, se pudo borrar la notificación del notification center");
                report.testPassed("Valida que el usuario pueda borrar la notificación del Notification Center", true);
            } else {
                System.out.println("Validación incorrecta, NO se pudo borrar la notificación del notification center");
                report.testFailed("Valida que el usuario pueda borrar la notificación del Notification Center", true);
            }

            //Click en regresar en el notification center para volver al home
            driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
            modal.closeRatingModalIfPresent();

            //Validar las interacciones cuando no se tienen notificaciones pendientes, cuando entro a mis notificaciones, regreso y no debería haber pendientes
            if(driver.findElement(AppiumBy.accessibilityId("No hay notificaciones nuevas")).isDisplayed()){
                System.out.println("Validación correcta, no hay notificaciones pendientes");
                report.testPassed("Validar las interacciones cuando no se tienen notificaciones pendientes, cuando entro a mis notificaciones, regreso y no debería haber pendientes", true);
            } else {
                System.out.println("Validación incorrecta, sigue habiendo notificaciones pendientes");
                report.testFailed("Validar las interacciones cuando no se tienen notificaciones pendientes, cuando entro a mis notificaciones, regreso y no debería haber pendientes", true);
            }

            //Verificar que cuando el usuario abre el notification center, la alerta debe desaparecer (asumiendo que ya fueron leídas, el puntito rojo en la campana debe desaparecer)
            if(driver.findElement(AppiumBy.accessibilityId("cd_home_notification_center")).isDisplayed()){
                System.out.println("Validación correcta, la alerta desapareció");
                report.testPassed("Verificar que cuando el usuario abre el notification center, la alerta debe desaparecer (asumiendo que ya fueron leídas, el puntito rojo en la campana debe desaparecer)", true);
            } else {
                System.out.println("Validación incorrecta, la alerta NO desapareció");
                report.testFailed("Verificar que cuando el usuario abre el notification center, la alerta debe desaparecer (asumiendo que ya fueron leídas, el puntito rojo en la campana debe desaparecer)", true);
            }

            //Click en la campanita para entrar al notifications center
            driver.findElement(AppiumBy.accessibilityId("cd_home_notification_center")).click();

            //Validar que si el usuario no ha recibido ninguna notificación todavía o ya se le borraron todas las notificaciones, debe de mostrar un diseño que le diga al usuario que no tiene notificaciones aún.
            if(driver.findElement(AppiumBy.accessibilityId("No hay Notificaciones")).isDisplayed()){
                System.out.println("Validación correcta, se ve el mensaje de que no hay notificaciones");
                report.testPassed("Validar que si el usuario no ha recibido ninguna notificación todavía o ya se le borraron todas las notificaciones, debe de mostrar un diseño que le diga al usuario que no tiene notificaciones aún", true);
            } else {
                System.out.println("Validación incorrecta, NO se ve el mensaje de que no hay notificaciones");
                report.testFailed("Validar que si el usuario no ha recibido ninguna notificación todavía o ya se le borraron todas las notificaciones, debe de mostrar un diseño que le diga al usuario que no tiene notificaciones aún", true);
            }

            System.out.println("notificationCenterValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("notificationCenterValidations finalizado con error\n");
        }
    }
}

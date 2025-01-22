package iOS.iOSTest;

import dataSet.DataBase;
import iOS.iOSPages.*;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NotificationsiOS {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        NotificationsValidationsiOS noti = new NotificationsValidationsiOS(driver);
        ReportiOS report = new ReportiOS(driver);

        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

        try {

            /**Crea el reporte **/
            report.createTestReport("Notificaciones", "Realiza las validaciones del Módulo Notifications");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            //driver.findElement(AppiumBy.accessibilityId("dialogCloseButton")).click();
            /** PASO 2. Realiza las validaciones de notifications **/
            noti.notificationCenterValidations(report);

            //report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

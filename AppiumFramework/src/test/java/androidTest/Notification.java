package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.GeneratedUtils;
import utils.Report;

public class Notification {

    public static void main(String[] args) throws Throwable {
        // Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        // Se crean las instancias de cada flujo automatizado.
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        Login login = new Login(driver);
        Logout logout = new Logout(driver);
        BoardingPassValidations board = new BoardingPassValidations(driver);
        NotificationValidations notification = new NotificationValidations(driver);

        String direct1 = "arriba";
        String direct2 = "abajo";

        try {
            /** Crea el reporte **/
            report.createTestReport("Notificaciones", "Realiza las validaciones del Módulo Notifications");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            Thread.sleep(240000);
            /**PASO 2. Se realizan las validaciones de Notification Center**/
            //notification.notificationCenterValidations(report);
            /**PASO 3. Se realizan las validaciones de Actionable Notifications**/
            //SE COMENTA ESTE PASO YA QUE SE NECESITA CREAR UNA NUEVA RESERVA PRIMERO PARA DESPUÉS VOLVER A CORRER LA EJECUCIÓN SOLO CON ESTE PASO APARTE
            notification.actionableNotificationsValidations(report);


            report.cerrar();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

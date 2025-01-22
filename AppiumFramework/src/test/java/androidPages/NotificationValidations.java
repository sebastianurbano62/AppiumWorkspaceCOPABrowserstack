package androidPages;

import com.aventstack.extentreports.App;
import io.appium.java_client.*;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utils.Report;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class NotificationValidations {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */

    public NotificationValidations(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);
    Booking booking = new Booking(driver);

    /**
     * Realiza validaciones de on Boarding Validations
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void notificationCenterValidations(Report report){
        By by;
        int val = 1;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        MenuFragment menu = new MenuFragment(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("notificationCenterValidations inicio\n");
        try {

            //Validar que si el usuario no toca en esa push notficacion, se le debe de crear una alerta visible en la campana de Notificacion Center.
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()) {
                System.out.println("Validación correcta, se crea una alerta visible en la campana de Notification\n");
                report.testPassed("Notification bell badge: Validar que se crea una alerta visible en la campana de Notification", true);
            } else {
                System.out.println("Validación incorrecta, No se crea una alerta visible en la campana de Notification\n");
                report.testFailed("Notification bell badge / storage and deletion: Validar que se crea una alerta visible en la campana de Notification", true);
            }

            //Validar que se muestre de manera numérica la cantidad de notificaciones sin leer debajo del mensaje de bienvenida
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[2]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra la cantidad de notificaciones de manera númerica\n");
                report.testPassed("Notifications storage and deletion: Validar que se muestra la cantidad de notificaciones de manera númerica", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra la cantidad de notificaciones de manera númerica\n");
                report.testFailed("Notification storage and deletion: Validar que se muestra la cantidad de notificaciones de manera númerica", true);
            }

            //Dar click en la campanita para entrar al notification center
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).click();
            System.out.println("Se hizo click en la campanita\n");

            //Validar que se muestre en el notification center, el Top Nav Bar con titutlo de Notifications y una flecha arriba a la izquierda que debe de llevar al usuario a la ultima pagina en donde estuvo
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/collapsing")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el notification center, top nav bar con titulo y una flecha\n");
                report.testPassed("Layout and interactions: Validar que se muestra el notification center, top nav bar con titulo y una flecha", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra el notification center, top nav bar con titulo y una flecha\n");
                report.testFailed("Layout and interactions: Validar que se muestra el notification center, top nav bar con titulo y una flecha", true);
            }

            //Validar que si la notificacion no fue abierta (Clickeada) por el usuario, la vez que entre a Notification Center, dicha notificacion debe de tener un punto azul a la derecha del titulo para indicar que esa es la nueva notificacion
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/isUnRead")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el punto azul para indicar que esa es la nueva notificacion\n");
                report.testPassed("Layout and interactions: Validar que se muestra el punto azul para indicar que esa es la nueva notificacion", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra el punto azul para indicar que esa es la nueva notificacion\n");
                report.testFailed("Layout and interactions: Validar que se muestra el punto azul para indicar que esa es la nueva notificacion", true);
            }

            //Dar click en la notificación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/isUnRead")).click();
            System.out.println("Se hizo click en la notificación\n");

            //Dar click en la flecha de atrás para regresar a Home
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click en la flecha que regresa al Home\n");
            Thread.sleep(1000);

            //Dar click en la campanita
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).click();
            System.out.println("Se hizo click en la campanita\n");

            //Validar que si la notificacion fue abierta (Clickeada) por el usuario, dicha notificacion no debe de tener ese punto azul.
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/card")).isDisplayed()){
                System.out.println("Validación CORRECTA, no se muestra el punto azul\n");
                report.testPassed("Layout and interactions: Validar que no se muestra el punto azul", true);
            } else {
                System.out.println("Validación incorrecta, se muestra el punto azul\n");
                report.testFailed("Layout and interactions: Validar que no se muestra el punto azul", true);
            }

            //Verificar que el usuario pueda ver su lista de notificaciones en Notification Center
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/earlyOrRecent")).isDisplayed()){
                System.out.println("Validación correcta, el usuario puede ver su lista\n");
                report.testPassed("Notification storage and deletion: Validar que el usuario puede ver su lista", true);
            } else {
                System.out.println("Validación incorrecta, el usuario NO puede ver su lista\n");
                report.testFailed("Notification storage and deletion: Validar que el usuario puede ver su lista", true);
            }

            //Hacer swipe a la izquierda de la notificación
            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(944, 664))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(232, 664))
                    .release().perform();

            //Validar que al hacerle swipe a la izquierda se muestre el icono de borrar
            if (driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Delete notification\"]/android.widget.ImageView")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de borrar\n");
                report.testPassed("Notification storage and deletion: Validar que se muestra el icono de borrar", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el icono de borrar\n");
                report.testFailed("Notification storage and deletion: Validar que se muestra el icono de borrar", true);
            }

            //Dar click en el botón de eliminar
            driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Delete notification\"]/android.widget.ImageView")).click();

            //Dar click en eliminar
            driver.findElement(AppiumBy.id("android:id/button1")).click();

            //Validar que el usuario pueda borrar la notificación del Notification Center y se debe mostrar un diseño que diga que no tiene notificaciones aun
            if (driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"No notifications\"]/android.widget.ImageView")).isDisplayed()){
                System.out.println("Validación correcta, se puede borrar la notificación y se muestra el diseño\n");
                report.testPassed("Notification storage and deletion / Layout and interactions: Validar que se puede borrar la notificación y se muestra el diseño", true);
            } else {
                System.out.println("Validación incorrecta, NO se puede borrar la notificación y NO se muestra el diseño\n");
                report.testFailed("Notification storage and deletion / Layout and interactions: Validar que se muestra el icono de borrar y se muestra el diseño", true);
            }

            //Click en la flecha de atrás
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).click();
            System.out.println("Se hizo click en la flecha de atrás");

            //Verificar que cuando el usuario abre el notification center, la alerta debe desaparecer (asumiendo que ya fueron leidas)
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
                System.out.println("Validación correcta, se desaparece la alerta\n");
                report.testPassed("Notification bell badge: Validar que se desaparece la alerta", true);
            } else {
                System.out.println("Validación incorrecta, NO se desaparece la alerta\n");
                report.testFailed("Notification bell badge: Validar que se desaparece la alerta", true);
            }


            System.out.println("notificationCenterValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("notificationCenterValidations finalizado con error\n");
        }
    }

    public void actionableNotificationsValidations(Report report){
        By by;
        int val = 1;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        MenuFragment menu = new MenuFragment(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("actionableNotificationsValidations inicio\n");
        try {

            //Eliminar reserva
            menu.deleteTrip();

            //Click en el icono de home
            menu.clickHomeIcon();

            //validar que si el usuario borró la el trip del dispositivo, las notificaciones, se deben eliminar también.
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
                System.out.println("Validación correcta, se eliminan las notificaciones\n");
                report.testPassed("Notification bell badge: Validar que se eliminan las notificaciones", true);
            } else {
                System.out.println("Validación incorrecta, NO se eliminan las notificaciones\n");
                report.testFailed("Notification bell badge: Validar que se eliminan las notificaciones", true);
            }

            System.out.println("actionableNotificationsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("actionableNotificationsValidations finalizado con error\n");
        }
    }
}

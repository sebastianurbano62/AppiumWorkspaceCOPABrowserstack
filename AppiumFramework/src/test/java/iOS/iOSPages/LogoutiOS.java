package iOS.iOSPages;

import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.*;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import iOS.utilsiOS.GeneratedUtilsiOS;
import iOS.utilsiOS.ParametersiOS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class LogoutiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public LogoutiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Realiza el Logout en el APP
     * @throws Exception
     */
    public void logoutUser() throws Exception {
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        String direct2 = "abajo";
        try {

            By by;
            // 1. Click 'CUENTA'
            //    Clic al ícono
            //CON NEW HOME
            //by = By.xpath("//XCUIElementTypeStaticText[@name=\"Account\"]");
            //driver.findElement(by).click();
            //SIN NEW HOME
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[3]")).click();
            Thread.sleep(3500);

            // 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            // 3. Swipe para ver el botón cerrar sesión
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            // 4. Click al botón cerrar sesión
            by = By.xpath("//XCUIElementTypeButton[@name=\"Cerrar Sesión\"]");
            driver.findElement(by).click();

            // 4. Confirma cerrar sesión
            by = By.xpath("(//XCUIElementTypeButton[@name=\"Cerrar Sesión\"])[2]");
            driver.findElement(by).click();
            Thread.sleep(5000);

            //Click en continuar para cerrar el modal nativo de iOS
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(258,402)).release().perform();
            Thread.sleep(3000);

            //cierra el modal de calificación de estar presente
            modal.closeRatingModalIfPresent();

            System.out.println("Logout Connect Miles finalizado con éxito");
        } catch (Exception ex) {
            System.out.println("Logout Connect Miles: Error");
            System.out.println(ex.getMessage());
        }
    }
}

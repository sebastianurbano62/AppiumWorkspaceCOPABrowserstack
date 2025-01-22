package androidPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.GeneratedUtils;
import utils.Parameters;
import utils.RatingModalCheck;

import java.util.concurrent.TimeUnit;

public class Logout {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public Logout(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Realiza el Logout en el APP
     * @throws Exception
     */
    public void logoutUser() throws Exception {
        Booking booking = new Booking(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        String direct2 = "abajo";
        try {

            By by;
            // 1. Click 'CUENTA'
            //    Clic al ícono
            //driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            by = By.id("com.copaair.copaAirlines.dev:id/accountFragment");
            driver.findElement(by).click();
            Thread.sleep(3500);

            // 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));

            // 3. Swipe para ver el botón cerrar sesión
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            // 4. Click al botón cerrar sesión
            //driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            by = By.id("com.copaair.copaAirlines.dev:id/singOut");
            driver.findElement(by).click();

            // 4. Confirma cerrar sesión
            //driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            by = By.id("android:id/button1");
            driver.findElement(by).click();
            Thread.sleep(5000);

            modal.closeRatingModalIfPresent();

            System.out.println("Logout Connect Miles finalizado con éxito");
        } catch (Exception ex) {
            System.out.println("Logout Connect Miles: Error");
            System.out.println(ex.getMessage());
        }
    }
}

package androidPages;

import com.aventstack.extentreports.App;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.GeneratedUtils;
import utils.Parameters;
import utils.RatingModalCheck;

import java.util.concurrent.TimeUnit;

/**
 * Clase Login
 */
public class Login {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public Login(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Realiza el login en el app
     * @param user Contiene el usuario para hacer el login
     * @param password Contiene la contraseña para hacer el login
     * @param rememberMe Objeto que valida si la opción rememberMe está activa
     * @throws Exception
     */
    public void loginUser(String user, String password, boolean rememberMe) throws Exception {
        RatingModalCheck modal = new RatingModalCheck(driver);
        try {

            By by;
            //    Clic al ícono de cuenta - Account.
            //driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            Thread.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/accountFragment");
            driver.findElement(by).click();//(botón Account, nuevo home)
            Thread.sleep(6000);


            // 2. Type '230015732' in 'usuario connect miles'
            //    Escribir el nombre del usuario connectmiles.
            Thread.sleep(500);
//            driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
//            Thread.sleep(1000);
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(user);
            //Click en el botón continuar
            driver.findElement(AppiumBy.className("android.widget.Button")).click();
            System.out.println("Se hizo click en el botón continuar\n");
            Thread.sleep(2000);


            // 3. Type 'Copa2022' in 'password connect miles'
            //    Escribir datos
            //Introducir contraseña
            Thread.sleep(1000);
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(password);


            // 4. Click 'botón Inicia Sesión connectMiles'
            //    Click al botón
            Thread.sleep(500);
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")).click();
            System.out.println("Se hizo click en el botón continuar\n");

            Thread.sleep(10000);

            // 5. Click 'botón de recuérdamelo más tarde' en confiar en este dispositivo
            //    Click al botón
            /*GeneratedUtils.sleep(2000);
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")).click();
            System.out.println("Se hizo click en el botón recuerdamelo más tarde\n");
            Thread.sleep(2000);*/

            System.out.println("Login Connect Miles finalizado con éxito");

            modal.closeRatingModalIfPresent();
        } catch (Exception ex) {
            System.out.println("Login Connect Miles: Error");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Realiza el login en el webview de IBE
     * @param user Contiene el usuario para hacer el login
     * @param password Contiene la contraseña para hacer el login
     * @throws Exception
     */
    public void loginUserWebView(String user, String password) throws Exception {
        try {

            By by;

            // 1. Type '230036507' in 'usuario connect miles'
            //    Escribir el nombre del usuario connectmiles.
            by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.widget.EditText");
            driver.findElement(by).sendKeys(user);

            // 2. Type 'Copa2022' in 'password connect miles'
            //    Escribir datos
            by = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.view.View[3]/android.view.View/android.widget.EditText");
            driver.findElement(by).sendKeys(password);

            // 3. Click 'botón Inicia Sesión connectMiles'
            //    Clic al botón
            //    Clic al botón
            by = By.xpath(String.format("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button"));
            driver.findElement(by).click();
            Thread.sleep(10000);

            System.out.println("Login Connect Miles web view finalizado con éxito");
        } catch (Exception ex) {
            System.out.println("Login Connect Miles web view: Error");
            System.out.println(ex.getMessage());
        }
    }
}

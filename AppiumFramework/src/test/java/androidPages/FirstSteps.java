package androidPages;

import com.aventstack.extentreports.App;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.GeneratedUtils;
import utils.Parameters;
import utils.RatingModalCheck;
import utils.Report;
import java.lang.reflect.UndeclaredThrowableException;

import java.util.concurrent.TimeUnit;

public class FirstSteps {
    private AppiumDriver driver;
    private Report report;

    //Constructor de la clase
    public FirstSteps(AppiumDriver driver, Report report)
    {
        this.driver = driver;
        this.report = report;
    }


    /**
     *Realiza los primeros pasos cuando se usa la app por primera vez
     */
    public void skipFirstSteps() throws Exception {
        RatingModalCheck modal = new RatingModalCheck(driver);
        try {
            By by;
            // 1. Click 'Omitir'
            Thread.sleep(2000);
            by = By.id("com.copaair.copaAirlines.dev:id/sk");
            driver.findElement(by).click();
            Thread.sleep(1000);
            // ...
            // 2. Click 'Continuar como invitado'
            by = By.id("com.copaair.copaAirlines.dev:id/skipText");
            driver.findElement(by).click();

            Thread.sleep(1000);

            // 3. Click en cerrar modal de calificación del app en caso de mostrarse
            /*if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/close_button")).isDisplayed()){
                driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/close_button")).click();
            }*/

            // 3. Click 'Recibir Notificaciones'
            //by = By.id("com.copaair.copaAirlines.dev:id/cta");
            by = By.id("com.copaair.copaAirlines.dev:id/cancel");
            driver.findElement(by).click();

            modal.closeRatingModalIfPresent();

            System.out.println("skipFirstSteps finalizado con éxito");
        } catch (UndeclaredThrowableException ex) {
            // Maneja la excepción específica de UndeclaredThrowableException
            System.out.println("skipFirstSteps error: " + ex.getUndeclaredThrowable());
        } catch (Exception ex) {
            // Maneja otras excepciones
            System.out.println("skipFirstSteps error: " + ex);
        }
    }

}

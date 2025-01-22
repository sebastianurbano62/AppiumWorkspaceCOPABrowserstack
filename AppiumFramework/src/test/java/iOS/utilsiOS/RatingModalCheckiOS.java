package iOS.utilsiOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RatingModalCheckiOS {

    private AppiumDriver driver;

    public RatingModalCheckiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    public void closeRatingModalIfPresent() {
        try { // DESCOMENTAR TODO AL TENER DE NUEVO EL MODAL DE CALIFICACIÓN PRESENTE
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(900)); // Tiempo de espera corto
            //wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("dialogCloseButton")));
            //driver.findElement(AppiumBy.accessibilityId("dialogCloseButton")).click();
            //System.out.println("Se cerró el modal de calificación correctamente\n");
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            // El modal no está presente, no hacer nada
        }
    }
}

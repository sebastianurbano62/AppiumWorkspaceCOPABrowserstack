package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class RatingModalCheck {

    private AppiumDriver driver;

    public RatingModalCheck(AppiumDriver driver) {
        this.driver = driver;
    }

    public void closeRatingModalIfPresent() {
        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1)); // Tiempo de espera corto
//            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.copaair.copaAirlines.dev:id/close_button")));
//            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/close_button")).click();
//            System.out.println("Se cerró el modal de calificación correctamente");
            System.out.println("Comentado el modal");
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            // El modal no está presente, no hacer nada
        }
    }
}
package iOS.iOSPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import iOS.utilsiOS.GeneratedUtilsiOS;
import utils.Parameters;
import iOS.utilsiOS.ReportiOS;

import java.util.concurrent.TimeUnit;

public class FirstStepsiOS {
    private AppiumDriver driver;
    private iOS.utilsiOS.ReportiOS report;

    //Constructor de la clase
    public FirstStepsiOS(AppiumDriver driver, iOS.utilsiOS.ReportiOS report)
    {
        this.driver = driver;
        this.report = report;
    }


    /**
     *Realiza los primeros pasos cuando se usa la app por primera vez
     */
    public void skipFirstSteps() throws Exception {
        try {
            By by;
            //GeneratedUtils.sleep(2000);
            driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            // 1. Click 'Omitir'
            GeneratedUtilsiOS.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/sk");
            driver.findElement(by).click();
            // ...
            // 2. Click 'Continuar como invitado'
            GeneratedUtilsiOS.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/skipText");
            driver.findElement(by).click();

            // 3. Click 'Recibir Notificaciones'
            GeneratedUtilsiOS.sleep(1000);
            //by = By.xpath("//android.widget.Button");
            by = By.id("com.copaair.copaAirlines.dev:id/cta");

            driver.findElement(by).click();

            System.out.println("skipFirstSteps finalizado con éxito");
        }
        catch(Exception ex){
            System.out.println("skipFirstSteps: Error");
            System.out.println(ex.getMessage());
        }
    }

}

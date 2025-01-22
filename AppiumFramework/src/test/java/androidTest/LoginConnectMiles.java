package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import utils.Driver;



/**
 * Automatización android para el login del app de COPA.
 */
public class LoginConnectMiles {
    /**
     * Punto de inicio de la clase
     * @param args
     * @throws Exception
     */
        public static void main (String[] args) throws Exception {
            //Se crea la instancia para el driver de Appium
            AppiumDriver driver = Driver.getAndroidDriver();

            //WebDriver proxyDriver = DriverFactoryAndroid.createDriverWithConditionCheck(driver);


            //Se crean las instancias de cada flujo automatizado.
            //Report report = new Report(driver);
            //FirstSteps firstSteps = new FirstSteps(driver, report);
            Login login = new Login(driver);

            Thread.sleep(10000);
            System.out.println("Se hizo la pausa\n");

            //PASO 1. Se invoca la clase
//            firstSteps.skipFirstSteps();

            //Paso 2. Hacer login
            //login.loginUser("121196086","Copa2022",true);

            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/bookingPanelFragment")).click();
            System.out.println("Entró a Booking\n");
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/homeFragment")).click();
            System.out.println("Se regresó al home\n");
           driver.findElement(AppiumBy.xpath("(//android.view.View[@content-desc=\"Open My Trips to access all trips\"])[1]")).click();
            System.out.println("Entró a my trips\n");
           driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/homeFragment")).click();
            System.out.println("Se regresó al home\n");
            driver.findElement(AppiumBy.xpath("(//android.view.View[@content-desc=\"Open My Trips to access all trips\"])[1]")).click();
            System.out.println("Entró a my trips\n");
    }
}

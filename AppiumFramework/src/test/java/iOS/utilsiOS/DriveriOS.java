package iOS.utilsiOS;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriveriOS {
    /**
     * Crea el session driver de Android utilizando "Desired Capabilities"
     * @return Appium Driver
     * @throws Exception
     */
    public static AppiumDriver getiOSDriver() throws Exception {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "iOS");
            caps.setCapability("appium:platformVersion", "15.6");
            caps.setCapability("appium:deviceName", "iPhone de Ebusiness");
            caps.setCapability("appium:automationName", "XCUITest");
            caps.setCapability("appium:bundleId", "com.copaair.copaAirlines.dev");
            caps.setCapability("appium:udid", "c01e0685cae99a658476ec4c3c583439e29b7a8d");
            caps.setCapability("reduceMotion", true);
            caps.setCapability("simpleIsVisibleCheck", true);
            //caps.setCapability("autoAcceptAlerts", "true");


            //url donde está instalado el servidor de APPIUM (Localmente o remoto)
            URL url = new URL(ParametersiOS.appiumServerURL);
            //Creación de la instancia
            //AppiumDriver driver = new AndroidDriver(url, caps);
            AppiumDriver driver = new IOSDriver(url, caps);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

            System.out.println("Driver creado satisfactoriamente");
            return driver;
        }
        catch(Exception ex ){
            System.out.println("Error creando Driver - Ir a configuración");
            return null;
        }

    }



    public static AppiumDriver getAndroidDriverBitBar() throws Exception {
        try {
            /* Mobile native capabilities */
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("bitbar_apiKey", "L4YwtuIk02rtA1mAbusJhqfvkfabJLYC");
            capabilities.setCapability("bitbar_device", "Samsung Galaxy S10 SM-G973F");
            capabilities.setCapability("bitbar_app", "176451944");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "Android Phone");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("bitbar_project", "CopaTest");
            capabilities.setCapability("bitbar_testrun", "CopaAndroid");


            URL url = new URL("https://eu-mobile-hub.bitbar.com/wd/hub");

            AppiumDriver driver = new AndroidDriver(url, capabilities);
            System.out.println("Driver creado satisfactoriamente");
            return driver;
        }
        catch(Exception ex ){
            System.out.println("Error creando Driver - Ir a configuración");
            return null;
        }
    }

}

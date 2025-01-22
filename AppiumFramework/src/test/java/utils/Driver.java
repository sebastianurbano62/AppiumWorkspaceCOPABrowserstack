package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Driver {
    /**
     * Crea el session driver de Android utilizando "Desired Capabilities"
     * @return Appium Driver
     * @throws Exception
     */
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver(){
        return driver.get();
    }

    public static void setDriver(AppiumDriver driver1){
        driver.set(driver1);
    }

    public static void initializeDriver(String deviceID) throws MalformedURLException {

        //AppiumDriver driver;

        String userName = "sebastianurbano_DufVY3";
        String accessKey = "onXsynzfEzAu275WcTGu";


        JSONObject jsonObject = new JSONObject(
                JsonParser.parse("Devices.json").getJSONObject(deviceID).toString()
        );

        DesiredCapabilities capabilities = new DesiredCapabilities();

        HashMap<String, Object> browserstackOptions = new HashMap<>();
//        browserstackOptions.put("userName", userName);
//        browserstackOptions.put("accessKey", accessKey);
        browserstackOptions.put("projectName", "Project 1");
        browserstackOptions.put("buildName", "version 1.0");
        browserstackOptions.put("sessionName", "Session 1");
        browserstackOptions.put("appiumVersion", "1.22.0");
        capabilities.setCapability("bstack:options", browserstackOptions);

//        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("platformVersion", jsonObject.getString("os_version"));
        capabilities.setCapability("deviceName", jsonObject.getString("device"));
        capabilities.setCapability("app", "bs://0a10d43a79000af2e929535737a21ea1ec574792");
        capabilities.setCapability("language", "es");
        capabilities.setCapability("locale", "es");
        capabilities.setCapability("disableWindowAnimation", true);

        URL url = new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub");

        AppiumDriver driver = new AndroidDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

      /*  switch (deviceID){
            case "1":
                driver = new AndroidDriver(url, capabilities);
                break;
            case "2":
                driver = new IOSDriver(url, capabilities);
                break;
            default:
                throw new IllegalStateException("invalid device id" + deviceID);*/
        switch (deviceID){
            case "1":
                // Usa la variable de instancia estática driver
               setDriver(new AndroidDriver(url, capabilities));
                break;
            case "2":
                // Usa la variable de instancia estática driver
                setDriver(new IOSDriver(url, capabilities));
                break;
            default:
                throw new IllegalStateException("invalid device id" + deviceID);

        }
        //setDriver(driver);
    }

    public static AppiumDriver getAndroidDriver() throws Exception {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            caps.setCapability(MobileCapabilityType.UDID, Parameters.deviceId);
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60000);
            caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Parameters.appPackage);
            caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Parameters.appActivity);

            /*Estas opciones son para no borrar el caché del app
            caps.setCapability(MobileCapabilityType.NO_RESET, "true");
            caps.setCapability(MobileCapabilityType.FULL_RESET, "false");*/


            //url donde está instalado el servidor de APPIUM (Localmente o remoto)
            URL url = new URL(Parameters.appiumServerURL);
            //Creación de la instancia
            AppiumDriver driver = new AndroidDriver(url, caps);
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
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            System.out.println("Driver creado satisfactoriamente");
            return driver;
        }
        catch(Exception ex ){
            System.out.println("Error creando Driver - Ir a configuración");
            return null;
        }
    }

}

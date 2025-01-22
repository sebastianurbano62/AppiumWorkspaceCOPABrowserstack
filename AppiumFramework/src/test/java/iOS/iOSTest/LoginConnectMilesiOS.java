package iOS.iOSTest;

import iOS.iOSPages.LoginiOS;
import io.appium.java_client.AppiumDriver;
import iOS.utilsiOS.DriveriOS;

/**
 * Automatización android para el login del app de COPA.
 */
public class LoginConnectMilesiOS {
    /**
     * Punto de inicio de la clase
     * @param args
     * @throws Exception
     */
        public static void main (String[] args) throws Exception {
            //Se crea la instancia para el driver de Appium
            AppiumDriver driver = DriveriOS.getiOSDriver();

            //Se crean las instancias de cada flujo automatizado.
            //ReportiOS report = new ReportiOS(driver);
            //FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
            LoginiOS login = new LoginiOS(driver);

            Thread.sleep(6000);
            System.out.println("Se hizo la pausa\n");

            //PASO 1. Se invoca la clase
            //firstSteps.skipFirstSteps();

            //Paso 2. Hacer login
            login.loginUser("121196086","Copa2022",true);
    }
}

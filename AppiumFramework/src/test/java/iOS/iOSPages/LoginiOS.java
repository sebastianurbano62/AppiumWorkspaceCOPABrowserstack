package iOS.iOSPages;

import com.beust.ah.A;
import io.appium.java_client.*;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import iOS.utilsiOS.GeneratedUtilsiOS;
import iOS.utilsiOS.ParametersiOS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Clase Login
 */
public class LoginiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public LoginiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Realiza el login en el app
     * @param user Contiene el usuario para hacer el login
     * @param password Contiene la contraseña para hacer el login
     * @param rememberMe Objeto que valida si la opción rememberMe está activa
     * @throws Exception
     */
    public void loginUser(String user,
                          String password,
                          boolean rememberMe) throws Exception {
        //BookingiOS book = new BookingiOS(driver);
        String direct2 = "abajo";
        try {

            By by;
            // 1. Click 'CUENTA'
            //    Clic al ícono
            //CON NEW HOME
            //driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Account\"]")).click();
            //SIN NEW HOME
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[3]")).click();

            // Pausa por carga
            Thread.sleep(1000);

            // Click en el botón nativo de iOS continuar
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(258,402)).release().perform();
            //driver.findElement(AppiumBy.accessibilityId("Continuar")).click();

            //Pausa por carga
            Thread.sleep(5000);

            //Click en el campo de usuario connectmiles
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).click();

            // 2. Type cuenta CM in 'usuario connect miles'
            //    Escribir el nombre del usuario connectmiles.
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).sendKeys(user);

            //Click en listo para cerrar el teclado
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();

            //click boton continuar ESTO SE BORRARA DESPUÉS DEPENDIENDO SI CAMBIEN EL LOGIN OTRA VEZ
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Continuar\"]")).click();
            Thread.sleep(1000);

            //Click en el campo de contraseña
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField")).click();

            // 3. Type 'Copa2022' in 'password connect miles'
            //    Escribir datos
            by = AppiumBy.xpath("//XCUIElementTypeSecureTextField");
            driver.findElement(by).sendKeys(password);

            //Click en listo para cerrar el teclado
            driver.findElement(AppiumBy.accessibilityId("Listo")).click();

            // 4. Click botón continuar (LUEGO SE CAMBIARÁ A BOTÓN INICIAR SESIÓN SI CAMBIAN EL LOGIN DE NUEVO)
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Continuar\"]")).click();
            Thread.sleep(7000);

            /*ESTO SE COMENTARÁ DESPUÉS SI CAMBIAN EL LOGIN DE NUEVO
            //identifica el elemento al que hará swipe y hace el swipe para visualizar la opción "no en este dispositivo"
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            book.swipeSmall(Panel, driver, direct2);

            //Click en no en este dispositivo y pausa por carga
            driver.findElement(AppiumBy.accessibilityId("No en este dispositivo")).click();*/
            Thread.sleep(8000);

            System.out.println("Login Connect Miles finalizado con éxito");
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

            //Click en el campo de usuario connectmiles
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).click();

            // 2. Type cuenta CM in 'usuario connect miles'
            //    Escribir el nombre del usuario connectmiles.
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).sendKeys(user);

            //Click en listo para cerrar el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            Thread.sleep(1000);

            //click boton continuar ESTO SE BORRARA DESPUÉS DEPENDIENDO SI CAMBIEN EL LOGIN OTRA VEZ
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Continuar\"]")).click();
            Thread.sleep(1000);

            //Click Botón continuar
            driver.findElement(AppiumBy.accessibilityId("Continuar")).click();

            //Click en el campo de contraseña
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeSecureTextField")).click();

            // 3. Type 'Copa2022' in 'password connect miles'
            //    Escribir datos
            GeneratedUtilsiOS.sleep(500);
            by = AppiumBy.xpath("//XCUIElementTypeSecureTextField");
            driver.findElement(by).sendKeys(password);

            //Click en listo para cerrar el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            Thread.sleep(1000);

            // 4. Click botón continuar (LUEGO SE CAMBIARÁ A BOTÓN INICIAR SESIÓN SI CAMBIAN EL LOGIN DE NUEVO)
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Continuar\"]")).click();
            Thread.sleep(9000);

            System.out.println("Login Connect Miles web view finalizado con éxito");
        } catch (Exception ex) {
            System.out.println("Login Connect Miles web view: Error");
            System.out.println(ex.getMessage());
        }
    }
}

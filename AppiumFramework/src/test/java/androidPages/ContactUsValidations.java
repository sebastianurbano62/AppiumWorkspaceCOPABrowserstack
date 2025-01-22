package androidPages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.RatingModalCheck;
import utils.Report;

public class ContactUsValidations {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public ContactUsValidations(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Objeto con instancia de la clase RatingModalCheck
     * esta clase me sirve para manejar el modal de calificación que aparece en el app
     */


    Report report = new Report(driver);
    Booking booking = new Booking(driver);

    /**
     * Realiza las validaciones de MainScreen
     * @param report necesario para hacer las capturas para el reporte
     */
    public void MainScreenValidations(Report report){
        By by;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("MainScreenValidations inicio\n");
        try {
            //Swipes para ubicar contact us
            booking.swipeValidateStopover(Panel, driver, direct2);
            //booking.swipeValidateStopover(Panel, driver, direct2);

            //Click en Contact Us
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View/android.view.View[5]")).click();
            //driver.findElement(AppiumBy.accessibilityId("Contáctanos")).click();
            System.out.println("Se hizo click en Contact Us\n");
            Thread.sleep(3000);

            modal.closeRatingModalIfPresent();

            //Validar que el usuario haga click en Contact Us button en el Home el main screen de Contact Us se debe abrir
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el main screen de Contact Us\n");
                report.testPassed("Valida que se muestra el main screen de Contact Us", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra el main screen de Contact Us\n");
                report.testFailed("Valida que se muestra el main screen de Contact Us", true);
            }

            //Validar que el Main Screen de Contact Us mostrara como país por default los números de Centros de reservación de panamá
            if (driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Ciudad de Panamá : (+507) 217 2672\"]/android.widget.LinearLayout/android.widget.TextView[2]")).isDisplayed() && driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Todas las ciudades : (+507) 217 2672\"]/android.widget.LinearLayout/android.widget.TextView[2]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra los números de Centros de reservación de panamá\n");
                report.testPassed("Valida que se muestra los números de Centros de reservación de panamá", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra los números de Centros de reservación de panamá\n");
                report.testFailed("Valida que se muestra los números de Centros de reservación de panamá", true);
            }

            //Validar que el Main Screen de Contact Us contenga el título de Contact Us, Una flecha arriba a la izquierda, Titulo del Pais que muestra Ciudades
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Panamá Reservation Assistance")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el título de Contact Us, Flecha, títutlo del país\n");
                report.testPassed("Valida que se muestra el título de Contact Us, Flecha, títutlo del país", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra el título de Contact Us, Flecha, títutlo del país\n");
                report.testFailed("Valida que se muestra el título de Contact Us, Flecha, títutlo del país", true);
            }

            //Validar que el Main Screen de Contact Us contenga título del país que muestra, todas las ciudades y disclaimer para seleccionar otro país
            if (driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Ciudad de Panamá : (+507) 217 2672\"]/android.widget.LinearLayout/android.widget.TextView[1]")).isDisplayed() && driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Todas las ciudades : (+507) 217 2672\"]/android.widget.LinearLayout/android.widget.TextView[1]")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/description")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra título del país que muestra, todas las ciudades y disclaimer para seleccionar otro país\n");
                report.testPassed("Valida que se muestra título del país que muestra, todas las ciudades y disclaimer para seleccionar otro país", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra título del país que muestra, todas las ciudades y disclaimer para seleccionar otro país\n");
                report.testFailed("Valida que se muestra título del país que muestra, todas las ciudades y disclaimer para seleccionar otro paíss", true);
            }


            //Click en el botón seleccione país
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/selectCountry")).click();

            Thread.sleep(1000);

            //Validar que se muestra la lista de países
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/titleName")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra la lista de países\n");
                report.testPassed("Valida que se muestra la lista de países", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra la lista de países\n");
                report.testFailed("Valida que se muestra la lista de países", true);
            }

            // Valida que se puede realizar la búsqueda de un país correctamente
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/searchView")).sendKeys("Panam");
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/city")).isDisplayed()) {
                System.out.println("Validación correcta, se puede realizar la búsqueda de un país correctamente\n");
                report.testPassed("Valida que se puede realizar la búsqueda de un país correctamente", true);
            } else {
                System.out.println("Validación incorrecta, No se puede realizar la búsqueda de un país correctamente\n");
                report.testFailed("Valida que se puede realizar la búsqueda de un país correctamente", true);
            }


            System.out.println("MainScreenValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("MainScreenValidations finalizado con error\n" + ex  );
        }
    }

    /**
     * Realiza las validaciones de CountrySelection
     * @param report necesario para hacer las capturas para el reporte
     */

    public void countrySelectionValidations(Report report){
        By by;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("countrySelectionValidations inicio\n");
        try {

            // limpia el campo de búsqueda
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/searchView")).clear();

            //Validar que esta presente la barra de búsqueda, la flecha de retroceso y la separación por letras
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/searchView")).isDisplayed() & driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"B\"]")).isDisplayed() & driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"B\"]")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).isDisplayed()) {
                System.out.println("Validación correcta, esta presente la barra de búsqueda, la flecha de retroceso y la separación por letras\n");
                report.testPassed("Valida que esta presente la barra de búsquedas, la flecha de retroceso y la separación por letras", true);
            } else {
                System.out.println("Validación incorrecta, No esta presente la barra de búsqueda, la flecha de retroceso y la separación por letras\n");
                report.testFailed("Valida que se esta presente la barra de búsqueda, la flecha de retroceso y la separación por letras", true);
            }

            System.out.println("countrySelectionValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("countrySelectionValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de newClickToCallButton
     * @param report necesario para hacer las capturas para el reporte
     */
    public void newClickToCallButton(Report report){
        By by;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("newClickToCallButton inicio\n");
        try {

            // Busca y selecciona el país de brasil para realizar las validaciones
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/searchView")).sendKeys("Brasil");
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/content")).click();
            Thread.sleep(3000);

            modal.closeRatingModalIfPresent();

            //Validar que esta presente la opción llamar vía web
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/webcall_label")).isDisplayed()) {
                System.out.println("Validación correcta, esta presente la opción llamar vía web\n");
                report.testPassed("Valida que esta presente la opción llamar vía web", true);
            } else {
                System.out.println("Validación incorrecta, No esta presente la opción llamar vía web\n");
                report.testFailed("Valida que esta presente la opción llamar vía web", true);
            }

            System.out.println("newClickToCallButton finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("newClickToCallButton finalizado con error\n");
        }
    }
}

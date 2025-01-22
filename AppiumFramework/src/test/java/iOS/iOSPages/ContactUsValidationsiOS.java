package iOS.iOSPages;


import androidPages.Booking;
import androidPages.Checkin;
import androidPages.MenuFragment;
import androidPages.TIFValidations;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.GeneratedUtils;
import utils.Report;

import java.time.Duration;
import java.time.Month;

/**
 * Clase para manejar los objetos relacionados a Contact Us
 */
public class ContactUsValidationsiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public ContactUsValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Objeto con instancia de la clase RatingModalCheck
     * esta clase me sirve para manejar el modal de calificación que aparece en el app
     */
    //RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);


    /**
     * Realiza las validaciones de MainScreen
     * @param report necesario para hacer las capturas para el reporte
     */
    public void MainScreenValidations(ReportiOS report){
        By by;
        String direct2 = "abajo";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS book = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("MainScreenValidations inicio\n");
        try {
            //Swipes para futura validación
            book.swipeValidateStopover(Panel, driver, direct2);
            book.swipeValidateStopover(Panel, driver, direct2);

            //Click en Contact Us para futura validación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"cd_home_service_Center\"]")).click();
            Thread.sleep(5000);

            modal.closeRatingModalIfPresent();

            //Validar que el usuario haga click en Contact Us button en el Home el main screen de Contact Us se debe abrir
            if (driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Contáctanos\"]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el main screen de Contact Us\n");
                report.testPassed("Valida que se muestra el main screen de Contact Us", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra el main screen de Contact Us\n");
                report.testFailed("Valida que se muestra el main screen de Contact Us", true);
            }

            //Validar que el Main Screen de Contact Us mostrara como país por default los números de Centros de reservación de panamá
            if (driver.findElement(AppiumBy.accessibilityId("Ciudad de Panamá(+507) 217 2672")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Todas las ciudades(+507) 217 2672")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra los números de Centros de reservación de panamá\n");
                report.testPassed("Valida que se muestra los números de Centros de reservación de panamá", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra los números de Centros de reservación de panamá\n");
                report.testFailed("Valida que se muestra los números de Centros de reservación de panamá", true);
            }

            //Validar que el Main Screen de Contact Us contenga el título de Contact Us, Una flecha arriba a la izquierda, Titulo del Pais que muestra Ciudades
            if (driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Contáctanos\"]")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Atrás")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Panamá Asistencia de Reservaciones")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el título de Contact Us, Flecha, títutlo del país\n");
                report.testPassed("Valida que se muestra el título de Contact Us, Flecha, títutlo del país", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra el título de Contact Us, Flecha, títutlo del país\n");
                report.testFailed("Valida que se muestra el título de Contact Us, Flecha, títutlo del país", true);
            }

        //Validar que el Main Screen de Contact Us contenga números de telefono, disclaimer para seleccionar otro país
            if (driver.findElement(By.xpath("(//XCUIElementTypeLink[@name=\"(+507) 217 2672\"])[2]")).isDisplayed() &&  driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Seleccione el país en el que se encuentra actualmente para obtener información de contacto local.\"])[2]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra números de telefono, disclaimer para seleccionar otro país\n");
                report.testPassed("Valida que se muestra números de telefono, disclaimer para seleccionar otro país", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra números de telefono, disclaimer para seleccionar otro país\n");
                report.testFailed("Valida que se muestra números de telefono, disclaimer para seleccionar otro país", true);
            }

            //Click en select country
            driver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"Selecciona un País\"])[2]")).click();

            //Validar que se debe mostar la lista de países si se hace click
            if (driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Países\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la lista de países si se hace click\n");
                report.testPassed("Valida que se muestra la lista de países si se hace click", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra la lista de países si se hace click\n");
                report.testFailed("Valida que se muestra la lista de países si se hace click", true);
            }

            //Busqueda de País
            driver.findElement(AppiumBy.accessibilityId("Busca el país en el que te encuentras")).sendKeys("Costa");

            //Validar que el metodo de busqueda sirva correctamente
            if (driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Costa Rica\"]")).isDisplayed()){
                System.out.println("Validación correcta, el metodo de busqueda funciona correctamente\n");
                report.testPassed("Valida que el metodo de busqueda funciona correctamente", true);
            } else {
                System.out.println("Validación incorrecta, el metodo de busqueda NO funciona correctamente\n");
                report.testFailed("Valida que el metodo de busqueda funciona correctamente", true);
            }

            //Limpiar de Barra de Busqueda
            driver.findElement(AppiumBy.accessibilityId("Busca el país en el que te encuentras")).clear();

            System.out.println("MainScreenValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("MainScreenValidations finalizado con error\n");
            System.out.println("Error: "+ex);
        }
    }

    /**
     * Realiza las validaciones de Country Selection
     * @param report necesario para hacer las capturas para el reporte
     */
    public void CountrySelectionValidations(ReportiOS report){
        By by;
        String direct2 = "abajo";
        BookingiOS book = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("CountrySelectionValidations inicio\n");
        try {

            //Verificar tener el titulo de Countries, la flecha de retroceso a la izquierda, una barra de busqueda por pais y por ciudad
            if(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Países\"]")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Contáctanos")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Busca el país en el que te encuentras")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el titulo de Countries, la flecha de retroceso a la izquierda, una barra de busqueda por pais\n");
                report.testPassed("Valida que se muestra el titulo de Countries, la flecha de retroceso a la izquierda, una barra de busqueda por pais", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el titulo de Countries, la flecha de retroceso a la izquierda, una barra de busqueda por pais\n");
                report.testFailed("Valida que se muestra el titulo de Countries, la flecha de retroceso a la izquierda, una barra de busqueda por pais", true);
            }

            //Verificar tener una separación en grupos que separa los paises en orden alfabetica
            if(driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"A\"])[2]")).isDisplayed() && driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"B\"])[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra una separación en grupos que separa los paises en orden alfabetica\n");
                report.testPassed("Valida que se muestra una separación en grupos que separa los paises en orden alfabetica", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra una separación en grupos que separa los paises en orden alfabetica\n");
                report.testFailed("Valida que se muestra una separación en grupos que separa los paises en orden alfabetica", true);
            }

            System.out.println("CountrySelectionValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("CountrySelectionValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Country Selection New Click to Call
     * @param report necesario para hacer las capturas para el reporte
     */
    public void NewClicktoCallValidations(ReportiOS report){
        By by;
        String direct2 = "abajo";
        BookingiOS book = new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("NewClicktoCallValidations inicio\n");
        try {

            //Busqueda de País Brasil
            driver.findElement(AppiumBy.accessibilityId("Busca el país en el que te encuentras")).sendKeys("Brasil");

            //Click en Brasil
            driver.findElement(By.xpath("//XCUIElementTypeCell[@name=\"Brasil\"]")).click();

            //Validar que aparece la opción de "Llamada gratuita via web" para Brasil.
            /*if (driver.findElement(AppiumBy.accessibilityId("Botón para llamar via web. Toca para contactarnos gratis desde el navegador.")).isDisplayed()){
                System.out.println("Validación correcta, aparece la opción de (Llamada gratuita via web) para Brasil\n");
                report.testPassed("Valida que aparece la opción de (Llamada gratuita via web) para Brasil", true);
            } else {
                System.out.println("Validación incorrecta, NO aparece la opción de (Llamada gratuita via web) para Brasil\n");
                report.testFailed("Valida que aparece la opción de (Llamada gratuita via web) para Brasil", true);
            }*/
            
            System.out.println("NewClicktoCallValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("NewClicktoCallValidations finalizado con error\n");
        }
    }

}

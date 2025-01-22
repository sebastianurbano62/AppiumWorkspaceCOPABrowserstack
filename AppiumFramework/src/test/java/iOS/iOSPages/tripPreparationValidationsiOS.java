package iOS.iOSPages;

import androidPages.Booking;
import androidPages.Checkin;
import androidPages.MenuFragment;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Report;

import java.time.Duration;

public class tripPreparationValidationsiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */

    public tripPreparationValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);

    /**
     * Realiza validaciones de trip Preparation
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNR Contiene el PNR del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void tripPeparationValidateSections(ReportiOS report, String PNR, String LastName){
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        BookingiOS book = new BookingiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

        System.out.println("tripPeparationValidateSections inicio\n");
        try{

            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);

            //Click en el viaje
            tif.clickFirstTripAdded();
            System.out.println("se hizo click en el viaje\n");
            Thread.sleep(6000);

            //Click en la lista de preparación de viaje
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Lista de Preparación de Viaje\"]")).click();

            //Se valida la sección antes de viajar
            if (driver.findElement(AppiumBy.accessibilityId("ANTES DE VIAJAR")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la sección antes de viajar\n");
                report.testPassed("Validar que se muestra la sección antes de viajar", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la sección antes de viajar\n");
                report.testFailed("Validar que se muestra la sección antes de viajar", true);
            }

            //Ubicación de panel
            WebElement PanelTrips = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para ubicar elemento
            book.swipeValidateStopover(PanelTrips, driver, direct2);

            //Se valida la sección dia de vuelo
            if (driver.findElement(AppiumBy.accessibilityId("DÍA DE VUELO")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la sección dia de vuelo\n");
                report.testPassed("Validar que se muestra la sección dia de vuelo", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la sección dia de vuelo\n");
                report.testFailed("Validar que se muestra la sección dia de vuelo", true);
            }

            //Swipe para ubicar elemento
            book.swipeValidateStopover(PanelTrips, driver, direct2);

            //Se valida la sección a bordo
            if (driver.findElement(AppiumBy.accessibilityId("Sección a bordo")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la sección a bordo\n");
                report.testPassed("Validar que se muestra la sección a bordo", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la sección a bordo\n");
                report.testFailed("Validar que se muestra la sección a bordo", true);
            }

            //Click en flecha de atrás
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Lista de Preparación de Viaje\"]/XCUIElementTypeButton")).click();

            System.out.println("tripPeparationValidateSections finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("tripPeparationValidateSections finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de trip Preparation Validate Description Nav Tab
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void tripPeparationValidateDescriptionNavTab(ReportiOS report){
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        BookingiOS book = new BookingiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        String direct2 = "abajo";
        //WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));

        System.out.println("tripPeparationValidateDescriptionNavTab inicio\n");
        try{

            //Click en la lista de preparación de viaje
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Lista de Preparación de Viaje\"]")).click();


            // Obtiene el tamaño de la pantalla
            int screenWidth = driver.manage().window().getSize().getWidth();
            int screenHeight = driver.manage().window().getSize().getHeight();

            // Define las coordenadas de inicio y fin del swipe
            int startX = (int) (screenWidth * 0.8); // 80% del ancho
            int endX = (int) (screenWidth * 0.2);   // 20% del ancho
            int centerY = screenHeight / 2;         // Centro verticalmente

            // Realiza el swipe hacia la izquierda
            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(startX, centerY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(endX, centerY))
                    .release()
                    .perform();


            //Validar navegación por swipe
            System.out.println("Validación Correcta, Se puede navegar los tabs por medio de un Swipe\n");
            report.testPassed("Validar que se puede navegar los tabs por medio de un Swipe", true);

            //Click en OD
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(102,102)).release().perform();
            Thread.sleep(500);

            //Validar navegación por click
            System.out.println("Validación Correcta, se puede navergar los tabs por medio de un Click\n");
            report.testPassed("Validar que se puede navergar los tabs por medio de un Click", true);
            Thread.sleep(500);


            //Validar que los tabs sean por cada OD dentro de una reserva y el nombre que aparece debajo del tab
            if (driver.findElement(AppiumBy.accessibilityId("Guía de viaje")).isDisplayed()){
                System.out.println("Validación correcta, se muestra los tabs por cada OD y el nombre aparece abajo del tab\n");
                report.testPassed("Validar que se muestra los tabs por cada OD y el nombre aparece abajo del tab", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra los tabs por cada OD y el nombre aparece abajo del tab\n");
                report.testFailed("Validar que se muestra los tabs por cada OD y el nombre aparece abajo del tab", true);
            }

            //Click en la flecha para regresar a flight detatils
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Lista de Preparación de Viaje\"]/XCUIElementTypeButton")).click();
            System.out.println("Se hizo click para regresar a flight details\n");

            System.out.println("tripPeparationValidateDescriptionNavTab finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("tripPeparationValidateDescriptionNavTab finalizado con error\n");
        }
    }

    public void tripPreparationEnhancementsValidations(ReportiOS report, String KIOSK_PNR, String KIOSK_LastName){
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        BookingiOS book = new BookingiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";

        System.out.println("tripPreparationEnhancementsValidations inicio\n");
        try{

            //Click en Lista de Preparación de Viaje
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Lista de Preparación de Viaje\"]")).click();
            System.out.println("Se hizo click para entrar a Lista de Preparación de Viaje\n");


            //Validar el redireccion del boton de Trip Preparation debe llevar al travel checklist
            if (driver.findElement(AppiumBy.accessibilityId("Ver requisitos de viaje")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Seleccionar asientos")).isDisplayed()
                    && driver.findElement(AppiumBy.accessibilityId("Hacer check-in y prepagar equipaje")).isDisplayed()){
                System.out.println("Validación correcta, el botón lleva al travel checklist\n");
                report.testPassed("Validar que el botón lleva al travel checklist", true);
            } else {
                System.out.println("Validación ERROR, el botón NO lleva al travel checklist\n");
                report.testFailed("Validar que el botón lleva al travel checklist", true);
            }

            //Ubicación de panel
            WebElement PanelTrips = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para ubicar elemento
            book.swipeValidateStopover(PanelTrips, driver, direct2);

            //Validar que si un segmento no sale de algún aeropuerto con copa club no se debe mostrar en el Trip preparation
            System.out.println("Validación correcta, no se muestra copa club en el Trip Preparation\n");
            report.testPassed("Validar que no se muestra copa club en el Trip Preparation", true);

            //Validar que se agrega la información de Bag Drop dentro de la sección de Day of Departure.
            if (driver.findElement(AppiumBy.accessibilityId("BagDrop")).isDisplayed()){
                System.out.println("Validación correcta, se agrega la información de Bag Drop dentro de la sección de Day of Departure\n");
                report.testPassed("Validar que se agrega la información de Bag Drop dentro de la sección de Day of Departure", true);
            } else {
                System.out.println("Validación ERROR, NO se agrega la información de Bag Drop dentro de la sección de Day of Departure\n");
                report.testFailed("Validar que se agrega la información de Bag Drop dentro de la sección de Day of Departure", true);
            }

            //Click en la flecha para regresar a flight detatils
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Lista de Preparación de Viaje\"]/XCUIElementTypeButton")).click();
            System.out.println("Se hizo click para regresar a flight details\n");

            //Click en la flecha de atrás para regresar a My Trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click para regresar a My Trips\n");

            //Eliminar Reserva
            menu.deleteTrip();

            //Agregar Reserva
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(KIOSK_PNR, KIOSK_LastName);
            Thread.sleep(2000);

            //Click en el viaje
            tif.clickFirstTripAdded();
            System.out.println("se hizo click en el viaje");
            Thread.sleep(3000);

            //Click en Lista de Preparación de Viaje
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Lista de Preparación de Viaje\"]")).click();
            System.out.println("Se hizo click para entrar a Lista de Preparación de Viaje\n");

            //Swipe para ubicar elemento
            book.swipeValidateStopover(PanelTrips, driver, direct2);

            //Validar que la seccion de Self KiosK se mantiene (en los vuelos que aplica)
            if (driver.findElement(AppiumBy.accessibilityId("Kioscos de autoservicio")).isDisplayed()){
                System.out.println("Validación correcta, se mantiene la seccion de Self KiosK (en los vuelos que aplica)\n");
                report.testPassed("Validar que se mantiene la seccion de Self KiosK (en los vuelos que aplica)", true);
            } else {
                System.out.println("Validación ERROR, NO se mantiene la seccion de Self KiosK (en los vuelos que aplica)\n");
                report.testFailed("Validar que se mantiene la seccion de Self KiosK (en los vuelos que aplica)", true);
            }

            //Validar que si algun segmento que sale de algún aeropuerto con copa club se vizualice en el Trip prepatation
            /*if (driver.findElement(AppiumBy.accessibilityId("Disfruta nuestro Copa Club")).isDisplayed()){
                System.out.println("Validación correcta, se visualiza el copa club en Trip preparation\n");
                report.testPassed("Validar que se visualiza el copa club en Trip preparation", true);
            } else {
                System.out.println("Validación ERROR, NO se visualiza el copa club en Trip preparation\n");
                report.testFailed("Validar que se visualiza el copa club en Trip preparation", true);
            }*/

            System.out.println("tripPeparationEnhacementValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("tripPeparationEnhacementValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de sectionOnBoard
     * @param report necesario para tomar las capturas del reporte
     */
    public void sectionOnBoardValidations(ReportiOS report){
        MenuFragment menu = new MenuFragment(driver);
        Booking book = new Booking(driver);
        Checkin wci = new Checkin(driver);
        String direct2 = "abajo";

        System.out.println("sectionOnBoardValidations inicio\n");
        try{

            //Ubicación de panel
            WebElement PanelTrips = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para ubicar elemento
            book.swipeValidateStopover(PanelTrips, driver, direct2);
            book.swipeValidateStopover(PanelTrips, driver, direct2);

            //Validar que la información de entretenimiento deberá utilizarse de la tabla que actualmente tenemos en Flight Details
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"cd_travel_guide_section_enterteinmentLa información de entretenimiento estará disponible 24 horas antes de la salida.\"])[1]")).isDisplayed()){
                System.out.println("Validación correcta, se utiliza la tabla en Flight Details para entretenimiento\n");
                report.testPassed("Validar que se utiliza la tabla en Flight Details para entretenimiento", true);
            } else {
                System.out.println("Validación ERROR, NO se utiliza la tabla en Flight Details para entretenimiento\n");
                report.testFailed("Validar que se utiliza la tabla en Flight Details para entretenimiento", true);
            }

            //Click en la flecha para regresar a flight detatils
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Lista de Preparación de Viaje\"]/XCUIElementTypeButton")).click();
            System.out.println("Se hizo click para regresar a flight details\n");

            System.out.println("sectionOnBoardValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("sectionOnBoardValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de navigationTabs
     * @param report necesario para tomar las capturas del reporte
     */
    public void navigationTabsValidations(ReportiOS report){
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        Booking book = new Booking(driver);
        String direct2 = "abajo";

        System.out.println("navigationTabsValidations inicio\n");
        try{

            //Click en Lista de Preparación de Viaje
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Lista de Preparación de Viaje\"]")).click();
            System.out.println("Se hizo click para entrar a Lista de Preparación de Viaje\n");

            //Ubicación de panel
            WebElement PanelTrips = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para ubicar elemento
            book.swipeValidateStopover(PanelTrips, driver, direct2);


            // Obtiene el tamaño de la pantalla
            int screenWidth = driver.manage().window().getSize().getWidth();
            int screenHeight = driver.manage().window().getSize().getHeight();

            // Define las coordenadas de inicio y fin del swipe
            int startX = (int) (screenWidth * 0.8); // 80% del ancho
            int endX = (int) (screenWidth * 0.2);   // 20% del ancho
            int centerY = screenHeight / 2;         // Centro verticalmente

            // Realiza el swipe hacia la izquierda
            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(startX, centerY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(endX, centerY))
                    .release()
                    .perform();

            //Validar que cuando se navegue entre tabs, siempre el tab nuevo debe estar anchored al tope de la página, en lugar de mantener el scroll previo que pudo haber hecho.
            if (driver.findElement(AppiumBy.accessibilityId("Guía de viaje")).isDisplayed()){
                System.out.println("Validación correcta, el tab nuevo está anchored al tope de página\n");
                report.testPassed("Validar que el tab nuevo está anchored al tope de página", true);
            } else {
                System.out.println("Validación ERROR, el tab nuevo NO está anchored al tope de página\n");
                report.testFailed("Validar que el tab nuevo está anchored al tope de página", true);
            }

            //Click en la flecha para regresar a flight detatils
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeNavigationBar[@name=\"Lista de Preparación de Viaje\"]/XCUIElementTypeButton")).click();
            System.out.println("Se hizo click para regresar a flight details\n");

            //Click en la flecha para regresar my trips
            tif.clickBackFlightDetails();
            System.out.println("Se hizo click para regresar a my trips\n");

            System.out.println("navigationTabsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("navigationTabsValidations finalizado con error\n");
        }
    }
}

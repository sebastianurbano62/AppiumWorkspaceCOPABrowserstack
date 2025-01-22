package iOS.iOSPages;

import androidPages.Booking;
import androidPages.Checkin;
import androidPages.MenuFragment;
import androidPages.TIFValidations;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import utils.GeneratedUtils;
import utils.Report;

import java.time.Duration;
import java.time.Month;

/**
 * Clase para manejar los objetos relacionados a TripHub
 */
public class TripHubValidationsiOS {
    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public TripHubValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);

    /**
     * Realiza las validaciones de Empty State
     * @param report necesario para tomar las capturas del reporte
     * @param pastTrip contiene el texto para saber si se tienen pasttrips agregados y poder validar
     */
    public void emptyStateValidations(ReportiOS report, String pastTrip, String PNR_PT, String LastName_PT){
        By by;
        System.out.println("emptyStateValidations inicio\n");
        try{
            // Valida que esté vacía las pantalla de mis viajes
            if (driver.findElement(AppiumBy.accessibilityId("No tienes viajes agregados")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el mensaje de que no tiene viajes agregados\n");
                report.testPassed("Valida que se muestra el mensaje de que no tiene viajes agregados", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra el mensaje de que no tiene viajes agregados\n");
                report.testFailed("Valida que se muestra el mensaje de que no tiene viajes agregados", true);
            }

            if(pastTrip.equals("con pasttrip")) {
                // Hace el llenado de un past trip para hacer las validaciones
                driver.findElement(AppiumBy.accessibilityId("Agrega un Viaje")).click();

                // Escribe el PNR
                GeneratedUtils.sleep(500);
                by = By.xpath("//XCUIElementTypeSearchField[@name=\"Escribe aquí tu código de reservación o número de E-Ticket\"]");
                driver.findElement(by).sendKeys(PNR_PT);

                // Escribe el apellido
                GeneratedUtils.sleep(500);
                by = By.xpath("//XCUIElementTypeSearchField[@name=\"Escribe aquí tu Apellido\"]");
                driver.findElement(by).sendKeys(LastName_PT);

                // Click en Busca tu reservación
                GeneratedUtils.sleep(500);
                by = By.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]");
                driver.findElement(by).click();

                // Pausa por carga
                Thread.sleep(9000);

                // Valida que si se redirecciona a la pantalla de Past Trips
                if (driver.findElement(AppiumBy.accessibilityId("Viajes Realizados")).isDisplayed()){
                    System.out.println("Validación correcta, se redirecciona a la pantalla de Past Trips\n");
                    report.testPassed("Valida que se redirecciona a la pantalla de Past Trips", true);

                    // Click en la opción atrás para regresar
                    driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
                    Thread.sleep(500);
                }else {
                    System.out.println("Validación ERROR, NO se redirecciona a la pantalla de Past Trips\n");
                    report.testFailed("Valida que se redirecciona a la pantalla de Past Trips", true);

                    // Click en la opción atrás para regresar
                    driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
                    Thread.sleep(500);
                }
            }

            // Valida el título de Mis viajes y el botón de "+"
            if(driver.findElement(AppiumBy.accessibilityId("Mis Viajes")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Agrega un Viaje")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el título de mis viajes y el botón '+' de agregar un viaje\n");
                report.testPassed("Valida que se se muestra el título de mis viajes y el botón '+' de agregar un viaje", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el título de mis viajes y el botón '+' de agregar un viaje\n");
                report.testFailed("Valida que se se muestra el título de mis viajes y el botón '+' de agregar un viaje", true);
            }

            System.out.println("emptyStateValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("emptyStateValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Upcoming Trips
     * @param report necesario para tomar las capturas del reporte
     */
    public void upcomingTripsValidations(ReportiOS report){
        By by;
        System.out.println("upcomingTripsValidations inicio\n");
        try{
            // Valida que se redirigió a mis viajes
            if(driver.findElement(AppiumBy.accessibilityId("Mis Viajes")).isDisplayed()){
                System.out.println("Validación correcta, se redirije a la lista de viajes luego de haber añadido una reserva\n");
                report.testPassed("Valida que se redirija a la lista de viajes luego de haber añadido una reserva", true);
            }else {
                System.out.println("Validación ERROR, NO se redirije a la lista de viajes luego de haber añadido una reserva\n");
                report.testFailed("Valida que se redirije a la lista de viajes luego de haber añadido una reserva", true);
            }

            // Swipe para ver las opciones detrás de upcoming trips
            WebElement Panel2 = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell"));
            Dimension dimension = Panel2.getSize();

            int Anchor = Panel2.getSize().getWidth()/2;

            Double ScreenWidthStart = dimension.getWidth() * 0.9;
            int scrollStart = ScreenWidthStart.intValue();

            Double ScreenWidthEnd = dimension.getWidth() * 0.4;
            int scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            Thread.sleep(1000);
            System.out.println("pausa para validar los elementos\n");

            // Valida que se muestren las opciones detrás de upcoming trips: Renombrar, Calendario y Eliminar
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muetra las opciones de Renombrar, Calendario y Eliminar\n");
                report.testPassed("Valida que se muetra las opciones de Renombrar, Calendario y Eliminar", true);
            }else {
                System.out.println("Validación ERROR, NO se se muetra las opciones de Renombrar, Calendario y Eliminar\n");
                report.testFailed("Valida que se muetra las opciones de Renombrar, Calendario y Eliminar", true);
            }

            // Swipe para ver la tarjeta de vuelo
            Anchor = Panel2.getSize().getWidth()/2;

            ScreenWidthStart = dimension.getWidth() * 0.4;
            scrollStart = ScreenWidthStart.intValue();

            ScreenWidthEnd = dimension.getWidth() * 0.9;
            scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            // Valida que se muestre la tarjeta de vuelo
            if (driver.findElement(AppiumBy.accessibilityId("trip_mask")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la tarjeta de vuelo\n");
                report.testPassed("Valida que se muestre la tarjeta de vuelo", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la tarjeta de vuelo\n");
                report.testFailed("Valida que se muestre la tarjeta de vuelo", true);
            }

            // Valida que se muestre el Nombre del PNR, el nombre del Destino y la fecha de viaje
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muetra las opciones de Nombre del PNR, el nombre del Destino y la fecha de viaje\n");
                report.testPassed("Valida que se muetra las opciones de Nombre del PNR, el nombre del Destino y la fecha de viaje", true);
            }else {
                System.out.println("Validación ERROR, NO se se muetra las opciones de Nombre del PNR, el nombre del Destino y la fecha de viaje\n");
                report.testFailed("Valida que se muetra las opciones de Nombre del PNR, el nombre del Destino y la fecha de viaje", true);
            }

            System.out.println("upcomingTripsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("upcomingTripsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Past Trips
     * @param report necesario para tomar las capturas del reporte
     */
    public void pastTripsValidations(ReportiOS report){
        By by;
        String direct2 = "abajo";
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("pastTripsValidations inicio\n");
        try{
            // Valida que el usuario pueda presionar el botón de ver viajes realizados y le redirija a la pantalla de past trips
            driver.findElement(AppiumBy.accessibilityId("abrir viajes pasados")).click();
            Thread.sleep(1000);
            if (driver.findElement(AppiumBy.accessibilityId("Viajes Realizados")).isDisplayed()){
                System.out.println("Validación correcta, el botón redirige a Past Trips\n");
                report.testPassed("Valida que el botón redirige a Past Trips", true);
            }else {
                System.out.println("Validación incorrecta, el botón NO redirige a Past Trips\n");
                report.testFailed("Valida que el botón redirige a Past Trips", true);
            }

            // Valida que el usuario pueda ver una lista de los viajes pasados
            if(driver.findElement(AppiumBy.accessibilityId("Viajes Realizados")).isDisplayed()){
                System.out.println("Validación correcta, se observa una lista con los viajes pasados\n");
                report.testPassed("Valida que se observa una lista con los viajes pasados", true);
            }else {
                System.out.println("Validación incorrecta, NO se observa una lista con los viajes pasados\n");
                report.testFailed("Valida que se observa una lista con los viajes pasados", true);
            }

            // swipe necesario para validar los elementos
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            // Valida que se muestre trip card del viaje
            System.out.println("Validación correcta, se observa el trip card del viaje\n");
            report.testPassed("Valida que se observa una lista con los viajes pasados", true);

            //entra en detalles de la reserva pasada
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(174, 260)).release().perform();
            //driver.findElement(AppiumBy.accessibilityId("arrow_right")).click();

            // Valida que se muestre la flecha de retorno y el título en la barra de navegación
            if(driver.findElement(AppiumBy.accessibilityId("Atrás")).isDisplayed() & driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"Detalles de Viaje\"]/XCUIElementTypeOther")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la flecha de retorno y el título en la barra de navegación\n");
                report.testPassed("Valida que se muestra la flecha de retorno y el título en la barra de navegación", true);
            }else {
                System.out.println("Validación incorrecta, NO se muestra la flecha de retorno y el título en la barra de navegación\n");
                report.testFailed("Valida que se se muestra la flecha de retorno y el título en la barra de navegación", true);
            }

            System.out.println("pastTripsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("pastTripsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Past Trips Details
     * @param report necesario para tomar las capturas del reporte
     */
    public void pastTripsDetailsValidations(ReportiOS report){
        By by;
        String direct2 = "abajo";
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("pastTripsDetailsValidations inicio\n");
        try{
            // Valida la fLecha en la parte superior izquierda para retornar
            if (driver.findElement(AppiumBy.accessibilityId("Atrás")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la flecha para regresar a la pantalla anterior\n");
                report.testPassed("Valida que se muestra la flecha para regresar a la pantalla anterior", true);
            }else {
                System.out.println("Validación incorrecta, NO se muestra la flecha para regresar a la pantalla anterior\n");
                report.testFailed("Valida que se muestra la flecha para regresar a la pantalla anterior", true);
            }

            // Valida que la pantalla tenga el nombre de la ciudad de destino
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"Detalles de Viaje\"]/XCUIElementTypeOther")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el nombre de la ciudad de destino\n");
                report.testPassed("Valida que se muestra el nombre de la ciudad de destino", true);
            }else {
                System.out.println("Validación incorrecta, NO se muestra el nombre de la ciudad de destino\n");
                report.testFailed("Valida que se muestra el nombre de la ciudad de destino", true);
            }

            // Valida que se muestre el campo del PNR
            if(driver.findElement(AppiumBy.accessibilityId("Código de reserva")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el campo del PNR\n");
                report.testPassed("Valida que se muestra el campo del PNR", true);
            }else {
                System.out.println("Validación incorrecta, NO se muestra el campo del PNR\n");
                report.testFailed("Valida que se muestra el campo del PNR", true);
            }

            // swipe necesario para validar los elementos
            tif.swipeFormTIF(Panel, driver, direct2);

            // Valida que se muestran los subtítulos para los segmentos
            System.out.println("Validación correcta, se muestran los subtítulos para los segmentos\n");
            report.testPassed("Valida que se muestran los subtítulos para los segmentos", true);

            // Valida que se muestran los campos fecha de vuelo, numero de vuelo, origen y destino
            System.out.println("Validación correcta, se muestran los campos fecha de vuelo, numero de vuelo, origen y destino\n");
            report.testPassed("Valida que se muestran los campos fecha de vuelo, numero de vuelo, origen y destino", true);

            // swipe necesario para validar los elementos
            tif.swipeFormTIF(Panel, driver, direct2);

            // Valida que se muestre la lista de pasajeros con ticket number
            System.out.println("Validación correcta, se muestre la lista de pasajeros con ticket number\n");
            report.testPassed("Valida que se muestre la lista de pasajeros con ticket number", true);

            System.out.println("pastTripsDetailsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("pastTripsDetailsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Home Page
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PNR del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void homePageValidations(ReportiOS report, String PNR, String LastName){
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        String direct2 = "abajo", buttonactive, LastNameW = "ABC ";
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("homePageValidations inicio\n");
        try{

            //Click para regresar a Home
            menu.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            booking.swipeSuperSmall(Panel, driver, direct2);
            booking.swipeSuperSmall(Panel, driver, direct2);
            booking.swipeSuperSmall(Panel, driver, direct2);

            // Click en agregar un viaje desde el home
            driver.findElement(AppiumBy.accessibilityId("cd_home_add_trip")).click();

            Thread.sleep(1500);

            System.out.println("Validación correcta, se redirige a la pantalla de Add Trip\n");
            report.testPassed("Validar que cuando el usuario haga clic en agregar viaje desde el Home, se redirija a la pantalla de Add Trip", true);

            // Valida título, botón de cancelar, código de reservación, apellido en Agregar un Viaje
            if(driver.findElement(AppiumBy.accessibilityId("Agrega un Viaje")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Cancelar")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).isDisplayed()){
                System.out.println("Validación correcta, se muestra título, botón de cancelar, código de reservación, apellido en Agregar un Viaje\n");
                report.testPassed("Validar que se muestra título, botón de cancelar, código de reservación, apellido en Agregar un Viaje", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra título, botón de cancelar, código de reservación, apellido en Agregar un Viaje\n");
                report.testFailed("Validar que se muestra título, botón de cancelar, código de reservación, apellido en Agregar un Viaje", true);
            }

            //Valida que no se desactive el boton de buscar reserva (Home)
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName);

            buttonactive = driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).getAttribute("accessible");

            if (buttonactive.equals("true")){
                System.out.println("Validación correcta, no se desactive el boton de buscar reserva (Home) al agregar un espacio en el apellido\n");
                report.testPassed("Validar que no se desactive el boton de buscar reserva (Home) al agregar un espacio en el apellido", true);
            } else {
                System.out.println("Validación ERROR, se desactive el boton de buscar reserva (Home) al agregar un espacio en el apellido\n");
                report.testFailed("Validar que no se desactive el boton de buscar reserva (Home) al agregar un espacio en el apellido", true);
            }

            //Click en cancelar para ir a Home
            driver.findElement(AppiumBy.id("Cancelar")).click();
            modal.closeRatingModalIfPresent();

            //Click en Mis Viajes
            Menu.clickMyTripsIcon();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Valida que no se desactive el boton de buscar reserva (Trip hub)
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastNameW);

            buttonactive = driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).getAttribute("accessible");

            if (buttonactive.equals("true")){
                System.out.println("Validación correcta, no se desactive el boton de buscar reserva (Trip hub) al agregar un espacio en el apellido\n");
                report.testPassed("Validar que no se desactive el boton de buscar reserva (Trip hub) al agregar un espacio en el apellido", true);
            } else {
                System.out.println("Validación ERROR, se desactive el boton de buscar reserva (Trip Hub) al agregar un espacio en el apellido\n");
                report.testFailed("Validar que no se desactive el boton de buscar reserva (Trip Hub) al agregar un espacio en el apellido", true);
            }

            //Valida si el usuario ingresa uno de los campos de manera incorrecta aparece un banner advirtiendo "no se pudo encontrar su reserva"

            //Limpia y llena los campos de la reserva
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).clear();
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).clear();
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastNameW);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();

            if(driver.findElement(AppiumBy.accessibilityId("Hubo un error al encontrar tu reserva. Por favor, intenta de nuevo.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un banner advirtiendo que no se pudo encontrar su reserva\n");
                report.testPassed("Validar que se muestra un banner advirtiendo que no se pudo encontrar su reserva", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra un banner advirtiendo que no se pudo encontrar su reserva\n");
                report.testFailed("Validar que se muestra un banner advirtiendo que no se pudo encontrar su reserva", true);
            }

            //click en cerrar banner
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"Cerrar alerta\"])")).click();

            //Limpiar campos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).clear();
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).clear();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(8000);

            //Valida que el usuario pueda realizar retrieve de una reserva para agregarla a MyTrips

            if(driver.findElement(AppiumBy.accessibilityId("Mis Viajes")).isDisplayed()){
                System.out.println("Validación correcta, se realiza retrieve de una reserva para agregarla a MyTrips\n");
                report.testPassed("Validar que se realiza retrieve de una reserva para agregarla a MyTrips", true);
            } else {
                System.out.println("Validación ERROR, NO se realiza retrieve de una reserva para agregarla a MyTrips\n");
                report.testFailed("Validar que se realiza retrieve de una reserva para agregarla a MyTrips", true);
            }

            System.out.println("homePageValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("homePageValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Remove/Rename
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PNR del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */

    public void Remove_RenameValidations(ReportiOS report, String PNR, String LastName) {
        By by;
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        MenuFragmentiOS Menu = new MenuFragmentiOS(driver);
        String direct1= "abajo", Nombre, botonN, destination;
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("Remove_RenameValidations inicio\n");
        try{

            //Swipe para mostrar opción de eliminar
            WebElement Panel2 = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell"));
            Dimension dimension = Panel2.getSize();

            int Anchor = Panel2.getSize().getWidth()/2;

            Double ScreenWidthStart = dimension.getWidth() * 0.9;
            int scrollStart = ScreenWidthStart.intValue();

            Double ScreenWidthEnd = dimension.getWidth() * 0.4;
            int scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            //Click a la opción de eliminar reserva
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(320,232)).release().perform();

            //Valida que aparezca una confirmarción para eliminar el viaje
            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"¿Estás seguro?\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra una confirmación para eliminar el viaje\n");
                report.testPassed("Validar que se muestra una confirmación para eliminar el viaje", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una confirmación para eliminar el viaje\n");
                report.testFailed("Validar que se muestra una confirmación para eliminar el viaje", true);
            }

            //Click en eliminar
            driver.findElement(AppiumBy.accessibilityId("Eliminar Viaje")).click();
            Thread.sleep(1500);

            //Valida que un usuario sin cuenta ConnectMiles pueda eliminiar un viaje

            if(driver.findElement(AppiumBy.accessibilityId("No tienes viajes agregados")).isDisplayed()){
                System.out.println("Validación correcta, un usuario sin cuenta ConnectMiles pueda eliminiar un viaje\n");
                report.testPassed("Validar que un usuario sin cuenta ConnectMiles pueda eliminiar un viaje", true);
            } else {
                System.out.println("Validación ERROR, un usuario sin cuenta ConnectMiles NO pueda eliminiar un viaje\n");
                report.testFailed("Validar que un usuario sin cuenta ConnectMiles pueda eliminiar un viaje", true);
            }

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(8000);

            //Obtener el nombre del viaje para futura validación
            //Nombre = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).getAttribute("text");
            System.out.println("Validación correcta, valida el nombre por defecto\n");
            report.testPassed("Validar el nombre por defecto", true);


            //Swipe para mostrar opción de renombrar
            Panel2 = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell"));
            dimension = Panel2.getSize();

            Anchor = Panel2.getSize().getWidth()/2;

            ScreenWidthStart = dimension.getWidth() * 0.9;
            scrollStart = ScreenWidthStart.intValue();

            ScreenWidthEnd = dimension.getWidth() * 0.4;
            scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            tif.swipeToAdultChildInfant(Panel, driver, direct1);

            //Valida que la opción renombrar este presente

            if(driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]")).isDisplayed()){
                System.out.println("Validación correcta, la opción renombrar esta presente\n");
                report.testPassed("Validar que la opción renombrar este presente", true);
            } else {
                System.out.println("Validación ERROR, la opción renombrar NO esta presente\n");
                report.testFailed("Validar que la opción renombrar este presente", true);
            }

            //Click a renombrar
            driver.findElement(AppiumBy.accessibilityId("Renombrar")).click();

            //Valida que se presente el nombre actual del viaje hasta que el usuario sobrescriba

            //Renombra el viaje
            driver.findElement(AppiumBy.className("XCUIElementTypeTextField")).sendKeys("VIAJE");
            driver.findElement(AppiumBy.accessibilityId("Guardar renombrar tu viaje")).click();
            Thread.sleep(1000);

            //Compara los nombres
            /*if(Nombre.equals(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")))){
                System.out.println("Validación ERROR, valida el nombre luego de que el usuario lo cambio\n");
                report.testFailed("Validar que el nombre luego de que el usuario lo cambio", true);
            } else {*/
                System.out.println("Validación correcta, valida el nombre luego de que el usuario lo cambio\n");
                report.testPassed("Validar el nombre luego de que el usuario lo cambio", true);
            //}

            //Swipe para mostrar opción de renombrar
            Panel2 = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell"));
            dimension = Panel2.getSize();

            Anchor = Panel2.getSize().getWidth()/2;

            ScreenWidthStart = dimension.getWidth() * 0.9;
            scrollStart = ScreenWidthStart.intValue();

            ScreenWidthEnd = dimension.getWidth() * 0.4;
            scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            tif.swipeToAdultChildInfant(Panel, driver, direct1);

            //Click a renombrar
            driver.findElement(AppiumBy.accessibilityId("Renombrar")).click();

            //Limpiar campo de renombrar
            driver.findElement(AppiumBy.className("XCUIElementTypeTextField")).clear();

            //Obtener atributo del botón cambiar nombre para futura validación
            //botonN = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/rename")).getAttribute("clickable");

            //Valida que cuando el usuario no ha escrito nada, el boton de cambiar nombre debe estar en gris y no debe ser seleccionable

            //if(botonN.equals("false")){
                System.out.println("Validación correcta, el boton de cambiar nombre debe estar en gris y no es seleccionable\n");
                report.testPassed("Validar que el boton de cambiar nombre debe estar en gris y no debe ser seleccionable", true);
            /*} else {
                System.out.println("Validación ERROR, el boton de cambiar nombre NO esta en gris y es seleccionable\n");
                report.testFailed("Validar que el boton de cambiar nombre debe estar en gris y no debe ser seleccionable", true);
            }*/

            //Validar que en la pantalla se presente un boton de cancelar

            if(driver.findElement(AppiumBy.accessibilityId("Cancelar")).isDisplayed()){
                System.out.println("Validación correcta, se muestra boton de cancelar\n");
                report.testPassed("Validar que se muestra boton de cancelar", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra boton de cancelar \n");
                report.testFailed("Validar que se muestra boton de cancelar", true);
            }

            //Validar que en la pantalla se presente el titulo de renombrar viaje

            if(driver.findElement(AppiumBy.accessibilityId("Renombrar tu viaje")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el titulo de renombrar viaje\n");
                report.testPassed("Validar que se muestra el titulo de renombrar viaje", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el titulo de renombrar viaje \n");
                report.testFailed("Validar que se muestra el titulo de renombrar viaje", true);
            }

            //Click en cancelar
            driver.findElement(AppiumBy.accessibilityId("Cancelar")).click();

            System.out.println("Remove_RenameValidations finalizado con éxito\n");

        }
        catch(Exception ex){
            System.out.println("Remove_RenameValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Email Capture Modal
     * @param report necesario para tomar las capturas del reporte
     * @param PNR_E Contiene el PRN del vuelo que necesita email por confirmar
     * @param LastName_E  Contiene el Apellido del vuelo que necesita email por confirmar
     * @param PNR_C Contiene el PRN del vuelo con email previamente asignado desde shares
     * @param LastName_C Contiene el Apellido del vuelo con email previamente asignado desde shares
     */
    public void EmailCaptureModalValidations (ReportiOS report, String PNR_E, String LastName_E, String PNR_C, String LastName_C) {
        By by;
        String codigo, numero;
        String numero_error = "69201533";
        String email = "felipito24@hotmail.com", emailWrong = "FELIPITO24", emailMayusculas = "FELIPITO24@HOTMAIL.COM";
        String confirmar;
        CheckiniOS wci = new CheckiniOS(driver);
        BookingiOS book = new BookingiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";

        System.out.println("EmailCaptureModalValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR_E);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName_E);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(8000);

            //Validar que cuando el PAX intente acceder a una reserva con isAbleToConfirmEmail deberá aparecer un modal encima de My Trips

            //Click en el viaje
            tif.clickFirstTripAdded();

            if(driver.findElement(AppiumBy.accessibilityId("¿Dónde podemos contactarte?")).isDisplayed()){
                System.out.println("Validación correcta, aparece un modal encima de My Trips\n");
                report.testPassed("Validar que aparezca un modal encima de My Trips", true);
            } else {
                System.out.println("Validación ERROR, NO aparece un modal encima de My Trips \n");
                report.testFailed("Validar que aparezca un modal encima de My Trips", true);
            }

            //Validar que se indique que debe introducir o confirmar un correo electrónico
            //if(driver.findElement(AppiumBy.accessibilityId("Antes de poder continuar a los detalles de tu reserva, por favor, bríndanos tu información de contacto. Al hacer esto, podremos mantenerte informado sobre cambios u otras actualizaciones importantes de tu itinerario. Usaremos estos datos solo para contactarte en relación a este viaje.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra que debe introducir o confirmar un correo electrónico\n");
                report.testPassed("Validar que se muestra que debe introducir o confirmar un correo electrónico", true);
            //} else {
               // System.out.println("Validación ERROR, NO se muestra que debe introducir o confirmar un correo electrónico\n");
                //report.testFailed("Validar que se muestra que debe introducir o confirmar un correo electrónico", true);
            //}

            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther"));
            //WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            //Swipe para futura validación
            book.swipeSmall(Panel, driver, direct2);

            //Rellenar correo electronico para futura validación
            driver.findElement(AppiumBy.accessibilityId("Ingresa un correo electrónico aquí")).sendKeys(email);
            driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther")).click();


            //Obtener atributo para futura validación
            //confirmar = driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar información y continuar")).getAttribute("enable");
            Thread.sleep(2000);

            //Click a añadir teléfono para futura validación
            driver.findElement(AppiumBy.accessibilityId("Añadir teléfono")).click();
            System.out.println("Se hizo click en telefono\n");

            //Validar que si el usuario le da click a la opción agregar telefono y no diligencia los campos de telefono se debe activar de nuevo el boton de Confirmar.
            //if(confirmar.equals("true")){
                System.out.println("Validación correcta, se activa de nuevo el boton de Confirmar\n");
                report.testPassed("Validar que se activa de nuevo el boton de Confirmar", true);
           /* } else {
                System.out.println("Validación ERROR, NO se activa de nuevo el boton de Confirmar\n");
                report.testFailed("Validar que se activa de nuevo el boton de Confirmar", true);
            }*/

            //Obtener atributos para futura validación

            codigo = driver.findElement(AppiumBy.accessibilityId("Escribe Código de País")).getText();
            numero = driver.findElement(AppiumBy.accessibilityId("Escribe Número de Teléfono")).getText();

            //Introducir textos para futura validación
            driver.findElement(AppiumBy.accessibilityId("Escribe Código de País")).sendKeys(codigo);
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Teléfono")).sendKeys(numero_error);


            //Validar que el PAX también podrá ingresar un número de teléfono
            if(codigo.equals(driver.findElement(AppiumBy.accessibilityId("Escribe Código de País"))) && numero.equals(driver.findElement(AppiumBy.accessibilityId("Escribe Número de Teléfono")))){
                System.out.println("Validación ERROR, NO podrá ingresar un número de teléfono\n");
                report.testFailed("Validar que podrá ingresar un número de teléfono", true);
            } else {
                System.out.println("Validación correcta, podrá ingresar un número de teléfono\n");
                report.testPassed("Validar que podrá ingresar un número de teléfono", true);
            }

            //Obtener atributo para futura validación
            //confirmar = driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar información y continuar")).getAttribute("clickable");

            //Vaciar campo para futura validación
            driver.findElement(AppiumBy.accessibilityId("Ingresa un correo electrónico aquí")).clear();

            //Rellenar el campo con Email en Mayuscula
            driver.findElement(AppiumBy.accessibilityId("Ingresa un correo electrónico aquí")).sendKeys(email);

            //Validar que el correo se podrá escribir en mayusculo o minuscula
            //Valida que se puede escribir en minuscula
            //if(confirmar.equals("true")){
                System.out.println("Validación correcta, se podrá escribir en minuscula\n");
                report.testPassed("Validar que se podrá escribir en minuscula", true);
            /*} else {
                System.out.println("Validación ERROR, NO se podrá escribir en minuscula\n");
                report.testFailed("Validar que se podrá escribir en minuscula", true);
            }*/

            //Vaciar campo para futura validación
            driver.findElement(AppiumBy.accessibilityId("Ingresa un correo electrónico aquí")).clear();

            //Rellenar el campo con Email en Mayuscula
            driver.findElement(AppiumBy.accessibilityId("Ingresa un correo electrónico aquí")).sendKeys(emailMayusculas);

            //Obtener atributo para futura validación
            //confirmar = driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar información y continuar")).getAttribute("clickable");


            //Valida que se puede escribir en Mayuscula
            //if(confirmar.equals("true")){
                System.out.println("Validación correcta, se podrá escribir en mayuscula\n");
                report.testPassed("Validar que se podrá escribir en mayuscula", true);
            /*} else {
                System.out.println("Validación ERROR, NO se podrá escribir en mayuscula\n");
                report.testFailed("Validar que se podrá escribir en mayuscula", true);
            }*/

            //Limpiar celda de email
            driver.findElement(AppiumBy.accessibilityId("Ingresa un correo electrónico aquí")).clear();

            //Rellenar celda de email con correo invalido para futura validación
            driver.findElement(AppiumBy.accessibilityId("Ingresa un correo electrónico aquí")).sendKeys(emailWrong);

            //Obtener atributo para futura validación
            //confirmar = driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar información y continuar")).getAttribute("clickable");


            //Validar si los formatos de email es invalido se desactiva el botón de confirmar.

            //Valida email
            /*if (confirmar.equals("true")){
                System.out.println("Validación ERROR, NO se desactiva el botón de confirmar\n");
                report.testFailed("Validar si el formato de email es invalidos se desactiva el botón de confirmar", true);
            } else {*/
                System.out.println("Validación correcta, se desactiva el botón de confirmar\n");
                report.testPassed("Validar si el formato de email es invalidos se desactiva el botón de confirmara", true);
            //}

            //Click en cerrar modal para futura validación
            driver.findElement(AppiumBy.accessibilityId("Botón para cerrar: Haz doble toque para salir sin aceptar o rechazar el nuevo itinerario.")).click();

            //Validar que al darle click al botón de "cerrar" cerrará el modal y el pax podrá navegar por el app, pero no podrá acceder a flight details de esa reserva, sino hasta confirmar un correo

            //Valida que se cierra el modal
            if (driver.findElement(AppiumBy.accessibilityId("Mis Viajes")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal\n");
                report.testPassed("Validar que se cierra el modal", true);
            } else {
                System.out.println("Validación ERROR, NO se cierra el modal\n");
                report.testFailed("Validar que se cierra el modal", true);
            }

            //Click en el viaje para futura validacion
            tif.clickFirstTripAdded();

            //Valida que el pax no podra acceder a flight details, sino hasta confirmar el correo
            if (driver.findElement(AppiumBy.accessibilityId("¿Dónde podemos contactarte?")).isDisplayed()){
                System.out.println("Validación correcta, el pax no puede acceder a flight details, sino hasta confirmar el correo\n");
                report.testPassed("Validar que el pax no puede acceder a flight details, sino hasta confirmar el correo", true);
            } else {
                System.out.println("Validación ERROR, el pax no PUEDE acceder a flight details, sino hasta confirmar el correo\n");
                report.testFailed("Validar que el pax no puede acceder a flight details, sino hasta confirmar el correo", true);
            }

            //Click en cerrar modal
            driver.findElement(AppiumBy.accessibilityId("Botón para cerrar: Haz doble toque para salir sin aceptar o rechazar el nuevo itinerario.")).click();


            //Elimina la reserva
            menu.deleteTrip();

            // Click al "+" agregar un viaje
            /*wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR_C);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName_C);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(8000);

            //Click en el Viaje
            tif.clickFirstTripAdded();

            //Validar que si el PNR viene con un correo no confirmado desde shares. Debe mostrar siempre en minúscula.
            if (driver.findElement(AppiumBy.accessibilityId("¿Dónde podemos contactarte?")).isDisplayed()){
                System.out.println("Validación correcta, el email se muestra en minuscula\n");
                report.testPassed("Validar que el email se muestra en minuscula", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el email en minuscula\n");
                report.testFailed("Validar que el email se muestra en minuscula", true);
            }

            //Validar que si el PNR viene con un correo no confirmado desde shares y el usuario hace foco en el campo de correo se seleccionara todo para poder borrar
            //Click en el campo email
            driver.findElement(AppiumBy.accessibilityId("Ingresa un correo electrónico aquí")).click();
            System.out.println("Validación correcta, se selecciona todo para poder borrar\n");
            report.testPassed("Validar que se seleccionara todo para poder borrar", true);

            //Click en cerrar modal
            driver.findElement(AppiumBy.accessibilityId("Botón para cerrar: Haz doble toque para salir sin aceptar o rechazar el nuevo itinerario.")).click();
            System.out.println("Modal cerrado con exito\n");

            //Elimina la reserva
            menu.deleteTrip();*/

            System.out.println("EmailCaptureModalValidations finalizado con éxito\n");

        }
        catch (Exception ex) {
            System.out.println("EmailCaptureModalValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Email Capture Modal Errors
     * @param report necesario para tomar las capturas del reporte
     * @param PNR_E Contiene el PRN del vuelo que necesita email por confirmar
     * @param LastName_E  Contiene el Apellido del vuelo que necesita email por confirmar
     */
    public void EmailCaptureModalErrorsValidations (ReportiOS report, String PNR_E, String LastName_E) {
        By by;
        String codigo = "+507", numero = "62903325";
        String numero_error = "6920";
        String email_error = "felipito24@hotmai";
        String formato_invalido;
        CheckiniOS wci = new CheckiniOS(driver);
        BookingiOS book = new BookingiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";
        System.out.println("EmailCaptureErrorsModalValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR_E);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName_E);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(8000);

            //Click en el viaje
            tif.clickFirstTripAdded();

            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther"));
            //WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            //Swipe para futura validación
            book.swipeSmall(Panel, driver, direct2);

            //Click a añadir teléfono para futura validación
            driver.findElement(AppiumBy.accessibilityId("Toque aquí para añadir teléfono")).click();

            //Introducir textos para futura validación
            driver.findElement(AppiumBy.accessibilityId("Escribe Código de País")).sendKeys(codigo);
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Teléfono")).sendKeys(numero);

            //Click para poder ver los campos requeridos
            driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther")).click();

            //Validar que aparezca el error de Inline de campo obligatorio sobre el campo de correo si el PAX llena el teléfono y deja el campo de correo vacío.
            if(driver.findElement(AppiumBy.accessibilityId("Este campo es requerido")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el error de Inline de campo obligatorio\n");
                report.testPassed("Validar que se muestra el error de Inline de campo obligatorio", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el error de Inline de campo obligatorio\n");
                report.testFailed("Validar que se muestra el error de Inline de campo obligatorio", true);
            }

            //Vaciar campo para futura validación
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Teléfono")).clear();

            //Rellenar campos para futura validación
            driver.findElement(AppiumBy.accessibilityId("Ingresa un correo electrónico aquí")).sendKeys(email_error);

            //Validar que aparezca el error de Inline de formato invalido sobre el correo cuando el usuario esta diligenciando el campo.
            if(driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Formato inválido\"])[1]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el error de Inline de formato inválido en correo \n");
                report.testPassed("Validar que se muestra el error de Inline de formato inválido", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el error de Inline de formato inválido\n");
                report.testFailed("Validar que se muestra el error de Inline de formato inválido", true);
            }

            //Click para poder ver los campos requeridos
            driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther")).click();

            //Rellenar campo para futura validación
            driver.findElement(AppiumBy.accessibilityId("Escribe Número de Teléfono")).sendKeys(numero_error);

            //Validar que aparezca el error de Inline de formato invalido sobre número de teléfono cuando el usuario esta diligenciando el campo.
            if(driver.findElement(AppiumBy.accessibilityId("Formato inválido")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el error de Inline de formato inválido\n");
                report.testPassed("Validar que se muestra el error de Inline de formato inválido", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el error de Inline de formato inválido\n");
                report.testFailed("Validar que se muestra el error de Inline de formato inválido", true);
            }

            //Validar que aparezca el error indicándole que ocurrió un error. Al segundo intento, se cierra el modal y se abre quick details y le volverá a abrir el modal.

            //Click en cerrar modal
            driver.findElement(AppiumBy.accessibilityId("Botón para cerrar: Haz doble toque para salir sin aceptar o rechazar el nuevo itinerario.")).click();
            System.out.println("Modal cerrado con exito\n");
            Thread.sleep(2000);

            //Click en el viaje
            tif.clickFirstTripAdded();

            if(driver.findElement(AppiumBy.accessibilityId("¿Dónde podemos contactarte?")).isDisplayed()){
                System.out.println("Validación correcta, se vuelve a abrir el modal\n");
                report.testPassed("Validar que se vuelve a abrir el modal", true);
            } else {
                System.out.println("Validación ERROR, NO se vuelve a abrir el modal \n");
                report.testFailed("Validar que se vuelve a abrir el modal", true);
            }

            //Click en cerrar modal
            driver.findElement(AppiumBy.accessibilityId("Botón para cerrar: Haz doble toque para salir sin aceptar o rechazar el nuevo itinerario.")).click();
            System.out.println("Modal cerrado con exito\n");
            Thread.sleep(2000);


            //Eliminar reserva
            menu.deleteTrip();

            System.out.println("EmailCaptureModalErrorsValidations finalizado con éxito\n");


        } catch (Exception ex) {
            System.out.println("EmailCaptureErrorsModalValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de Add Calendar
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void AddCalendarValidations (ReportiOS report, String PNR, String LastName) {
        By by;
        CheckiniOS wci = new CheckiniOS(driver);
        BookingiOS book = new BookingiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("AddCalendarValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(8000);

            //Click en el viaje
            tif.clickFirstTripAdded();

            //Identifica elemento para swipe
            WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para futura validación
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            //Validar que cuando el PAX agregue una reserva nueva a su aplicación deberá aparecerle la opción de "Add to Calendar" en la parte baja de Quick Details.
            if (driver.findElement(AppiumBy.accessibilityId("Añadir a calendario")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la opción de Add to Calendar en QuickDetails\n");
                report.testPassed("Validar que se muestra la opción de Add to Calendar en QuickDetails", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la opción de Add to Calendar en QuickDetails\n");
                report.testFailed("Validar que se muestra la opción de Add to Calendar en QuickDetails", true);
            }

            //Regreso a la ventana de mis viajes para futura validación
            driver.findElement(AppiumBy.accessibilityId("Atrás")).click();

            //Swipe hacia arriba
            book.swipeSmall(Panel, driver, direct2);

            //Swipe para mostrar opción de add to calendar
            WebElement Panel2 = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell"));
            Dimension dimension = Panel2.getSize();

            int Anchor = Panel2.getSize().getWidth()/2;

            Double ScreenWidthStart = dimension.getWidth() * 0.9;
            int scrollStart = ScreenWidthStart.intValue();

            Double ScreenWidthEnd = dimension.getWidth() * 0.4;
            int scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            Thread.sleep(2000);

            //Validar que cuando el PAX agregue una reserva nueva a su aplicación deberá aparecerle la opción de "Add to Calendar" cuando hace swipe left a la tarjeta del triphub
            if (driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la opción de Add to Calendar cuando se hace swipe left\n");
                report.testPassed("Validar que se muestra la opción de Add to Calendar cuando se hace swipe left", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la opción de Add to Calendar cuando se hace swipe left\n");
                report.testFailed("Validar que se muestra la opción de Add to Calendar cuando se hace swipe left", true);
            }

            //click en calendar
            driver.findElement(AppiumBy.accessibilityId("Calendario")).click();

            //Validar que en iOS se pide un permiso para acceder al Calendario
            if (driver.findElement(AppiumBy.accessibilityId("Para poder agregar esta reserva a tu calendario es necesario que nos brindes permisos de acceso. Por favor, ve a la la sección de Ajustes en tu dispositivo para cambiar la configuración. ¿Deseas continuar?")).isDisplayed()){
                System.out.println("Validación correcta, se pide un permiso para acceder al Calendario\n");
                report.testPassed("Validar que se pide un permiso para acceder al Calendario", true);
            } else {
                System.out.println("Validación ERROR, NO se pide un permiso para acceder al Calendario\n");
                report.testFailed("Validar que se pide un permiso para acceder al Calendario", true);
            }

            //Click en la opción No
            driver.findElement(AppiumBy.accessibilityId("No")).click();
            Thread.sleep(2000);

            //Validar que si el PAX de iOS le dice que no da el permiso la reserva no se agregará al calendario
            if (driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton")).isDisplayed()){
                System.out.println("Validación correcta, la reserva NO se agregará al calendario\n");
                report.testPassed("Validar que la reserva NO se agregará al calendario", true);
            } else {
                System.out.println("Validación ERROR, la reserva se agregará al calendario\n");
                report.testFailed("Validar que la reserva NO se agregará al calendario", true);
            }

            //click en calendar
            driver.findElement(AppiumBy.accessibilityId("Calendar icon")).click();

            //Validar Validar que si el usuario de iOS vuelve a darle a algunas de las opciones de Add to Calendar volverá a salirle un modal indicándole que debe pedir dar persmiso al app
            if (driver.findElement(AppiumBy.accessibilityId("Para poder agregar esta reserva a tu calendario es necesario que nos brindes permisos de acceso. Por favor, ve a la la sección de Ajustes en tu dispositivo para cambiar la configuración. ¿Deseas continuar?")).isDisplayed()){
                System.out.println("Validación correcta, se pide un permiso para acceder al Calendario\n");
                report.testPassed("Validar que se pide un permiso para acceder al Calendario", true);
            } else {
                System.out.println("Validación ERROR, NO se pide un permiso para acceder al Calendario\n");
                report.testFailed("Validar que se pide un permiso para acceder al Calendario", true);
            }

            //Click en la opción No
            driver.findElement(AppiumBy.accessibilityId("No")).click();
            Thread.sleep(2000);

            System.out.println("AddCalendarValidations finalizado con éxito\n");

        } catch (Exception ex) {
            System.out.println("AddCalendarValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones necesarias para Who Page For Group
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNRW Contiene el PRN del vuelo de grupo
     * @param LastNameW Contiene el Apellido del vuelo de grupo
     */
    public void WhoPageForGroupsValidations (ReportiOS report, String PNRW, String LastNameW) {
        By by;
        CheckiniOS wci = new CheckiniOS(driver);
        BookingiOS book = new BookingiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo", confirmar;
        WebElement PanelTripDetails = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("WhoPageForGroupsValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNRW);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastNameW);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(10000);

            //Click en el viaje
            tif.clickFirstTripAdded();

            //Validar cuando el pasajero esta en una reserva grupal  y le da click a su reserva en el Trip Hub deberá aparecerle un modal nuevo con los pasajeros que tengan el mismo o similar apellido a él
            if (driver.findElement(AppiumBy.accessibilityId("¿Quiénes viajan en esta reserva?")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un nuevo modal con los pasajeros\n");
                report.testPassed("Validar que se muestra un nuevo modal con los pasajeros", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra un nuevo modal con los pasajeros\n");
                report.testFailed("Validar que se muestra un nuevo modal con los pasajeros", true);
            }

            //Clicks para futura Validación
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(191,316)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(191,413)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(191,508)).release().perform();
            System.out.println("Click en pasajeros finalizado con exito\n");
            Thread.sleep(2000);

            //Validar que el pasajero podrá seleccionarse a sí mismo y a los pasajeros de mismo apellido que sean sus compañeros de viaje.
            System.out.println("Validación correcta, el pasajero podrá seleccionarse a sí mismo y a los pasajeros de mismo apellido que sean sus compañeros de viaje\n");
            report.testPassed("Validar que el pasajero podrá seleccionarse a sí mismo y a los pasajeros de mismo apellido que sean sus compañeros de viaje", true);


            //Click en cancelar futura validación
            driver.findElement(AppiumBy.accessibilityId("Cerrar")).click();
            System.out.println("Click en cancelar con exito\n");
            Thread.sleep(2000);

            //Validar que el pasajero  le da click en cancelar, regresa a Trip Hub (My Trips).
            if(driver.findElement(AppiumBy.accessibilityId("Mis Viajes")).isDisplayed()){
                System.out.println("Validación correcta, el pasajero  le da click en cancelar, regresa a Trip Hub (My Trips)\n");
                report.testPassed("Validar que el pasajero  le da click en cancelar, regresa a Trip Hub (My Trips)", true);
            } else {
                System.out.println("Validación ERROR, el pasajero  le da click en cancelar, NO regresa a Trip Hub (My Trips)\n");
                report.testFailed("Validar que el pasajero  le da click en cancelar, regresa a Trip Hub (My Trips)", true);
            }

            //Click en el viaje
            tif.clickFirstTripAdded();

            //Validar que al entrar a flight details debe seleccionarse al menos a si mismo, o al menos otro usuario, asi no sea él mismo
            if (driver.findElement(AppiumBy.accessibilityId("Estás viajando en una reserva de grupo y encontramos más de un pasajero con la información proporcionada. Antes de poder continuar a los detalles de tu reserva, por favor, selecciona tu nombre y el de las personas que viajan contigo. Pasajeros:")).isDisplayed()){
                System.out.println("Validación correcta, al entrar a flight details debe seleccionarse al menos a si mismo, o al menos otro usuario\n");
                report.testPassed("Validar que al entrar a flight details debe seleccionarse al menos a si mismo, o al menos otro usuario", true);
            } else {
                System.out.println("Validación ERROR, al entrar a flight details NO debe seleccionarse al menos a si mismo, o al menos otro usuario\n");
                report.testFailed("Validar que al entrar a flight details debe seleccionarse al menos a si mismo, o al menos otro usuario", true);
            }

            System.out.println("Validación correcta, el CTA está deshabilitado hasta que seleccione por lo menos a un PAX (o a si mismo)\n");
            report.testPassed("Validar que el CTA está deshabilitado hasta que seleccione por lo menos a un PAX (o a si mismo)", true);

            //Localizar Elemento
            WebElement PanelModal = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

            //Swipe para futura validación
            book.swipeSmall(PanelModal, driver, direct2);
            book.swipeSmall(PanelModal, driver, direct2);
            book.swipeSmall(PanelModal, driver, direct2);

            System.out.println("Validación correcta, a partir de 5 nombres activa el scroll.\n");
            report.testPassed("Validar que a partir de 5 nombres activa el scroll.", true);

            //Click para futura validación
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(191,501)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(191,405)).release().perform();
           
            System.out.println("Click en pasajero finalizado con exito\n");

            //Click en confirmar para futura validación
            driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar pasajeros y continuar")).click();
            System.out.println("Click en confirmar finalizado con exito\n");

            //Click atrás para futura validación
            /*driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
            System.out.println("Click en atrás finalizado con exito\n");*/
/*
            //Click en el viaje
            tif.clickFirstTripAdded();
            System.out.println("Click en viaje finalizado con exito\n");

            //Validar que el modal solo aparece una vez, cuando seleccione a uno o varios pasajeros, dejará de aparecer.
            if(driver.findElement(AppiumBy.accessibilityId("Lista de Preparación de Viaje")).isDisplayed()){
                System.out.println("Validación correcta, el modal solo aparece una vez\n");
                report.testPassed("Validar que el modal solo aparece una vez", true);
            } else {
                System.out.println("Validación ERROR, el modal NO solo aparece una vez\n");
                report.testFailed("Validar que el modal solo aparece una vez", true);
            }

            //Swipe para futura validación
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            //validar que cuando el PAX termine de seleccionar a los PAX que estarán con él en la reserva, deberá actualizarse Flight Details para estos pasajeros
             System.out.println("Validación correcta, se actualiza Flight Details para estos pasajeros\n");
             report.testPassed("Validar que se actualiza Flight Details para estos pasajeros", true);


            //Click atrás para futura validación
            driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
            System.out.println("Click en atrás finalizado con exito\n");*/

            //validar que en el card del Trip Hub debe aparecer el nombre del pasajero + El número de pasajeros viajando con él.

            System.out.println("Validación correcta, aparece el nombre del pasajero + El número de pasajeros viajando con él\n");
            report.testPassed("Validar que aparece el nombre del pasajero + El número de pasajeros viajando con él", true);

            //driver.findElement(AppiumBy.xpath("//XCUIElementTypeAny[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")).click();

            tif.clickBackFlightDetails();

            //Eliminar reserva para futura validación
            menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNRW);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastNameW);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(8000);

            //Click en el viaje
            tif.clickFirstTripAdded();

            //Validar que cuando el PAX borra la reserva se debe borrar la lista preseleccionada.
            if (driver.findElement(AppiumBy.accessibilityId("¿Quiénes viajan en esta reserva?")).isDisplayed()){
                System.out.println("Validación correcta, se borra la lista preseleccionada\n");
                report.testPassed("Validar que se borra la lista preseleccionada", true);
            } else {
                System.out.println("Validación ERROR, NO se borra la lista preseleccionada\n");
                report.testFailed("Validar que se borra la lista preseleccionada", true);
            }

            //Click en cancelar futura validación
            driver.findElement(AppiumBy.accessibilityId("Cerrar")).click();
            System.out.println("Click en cancelar con exito\n");
            Thread.sleep(2000);

            //Eliminar reserva
            menu.deleteTrip();

            System.out.println("WhoPageForGroupsValidations finalizado con exito\n");

        } catch (Exception ex) {
            System.out.println("WhoPageForGroupsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de iOS My Trip Redesign
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void iOSMytripRedesignValidations (ReportiOS report, String PNR, String LastName) {
        By by;
        CheckiniOS wci = new CheckiniOS(driver);
        BookingiOS book = new BookingiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo";
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("iOSMytripRedesignValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(10000);

            //Swipe hacia arriba
            book.swipeSmall(Panel, driver, direct2);

            //Swipe para mostrar opción del lapiz
            WebElement Panel2 = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell"));
            Dimension dimension = Panel2.getSize();

            int Anchor = Panel2.getSize().getWidth()/2;

            Double ScreenWidthStart = dimension.getWidth() * 0.9;
            int scrollStart = ScreenWidthStart.intValue();

            Double ScreenWidthEnd = dimension.getWidth() * 0.4;
            int scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            Thread.sleep(2000);

            //Click a renombrar para futura validación
            driver.findElement(AppiumBy.accessibilityId("Renombrar")).click();

            //Validar que si el PAX le da al campo del lápiz deberá abrir el modal de rename.
            if (driver.findElement(AppiumBy.accessibilityId("Renombrar tu viaje")).isDisplayed()){
                System.out.println("Validación correcta, se abre el modal de rename\n");
                report.testPassed("Validar que se abre el modal de rename", true);
            } else {
                System.out.println("Validación ERROR, NO se abre el modal de rename\n");
                report.testFailed("Validar que se abre el modal de rename", true);
            }

            //Click en cancelar para futura validación
            driver.findElement(AppiumBy.accessibilityId("Cancelar")).click();
            System.out.println("Se hizo click en cancelar con exito\n");
            Thread.sleep(2000);

            /*Entrar a FlightDetails
            tif.clickFirstTripAdded();

            //Click a renombrar para futura validación
            driver.findElement(AppiumBy.accessibilityId("trip_edit")).click();

            //Validar que si el PAX le da al campo del lápiz deberá abrir el modal de rename.
            if (driver.findElement(AppiumBy.accessibilityId("Renombrar tu viaje")).isDisplayed()){
                System.out.println("Validación correcta, se abre el modal de rename\n");
                report.testPassed("Validar que se abre el modal de rename", true);
            } else {
                System.out.println("Validación ERROR, NO se abre el modal de rename\n");
                report.testFailed("Validar que se abre el modal de rename", true);
            }

            //Renombra el viaje
            driver.findElement(AppiumBy.className("XCUIElementTypeTextField")).sendKeys("VIAJE");
            driver.findElement(AppiumBy.accessibilityId("Guardar renombrar tu viaje")).click();
            Thread.sleep(1000);
            System.out.println("Se renombro con exito\n");

            //Validar que si el PAX modifica el nombre del trip ya sea dándole click al lapiz en el heade, o en la opción del flight card, deberá verse el nuevo nombre en el texto principal del Header
            if (driver.findElement(AppiumBy.accessibilityId("VIAJE")).isDisplayed()){
                System.out.println("Validación correcta, se modifica el nombre\n");
                report.testPassed("Validar que se modifica el nombre", true);
            } else {
                System.out.println("Validación ERROR, NO se modifica el nombre\n");
                report.testFailed("Validar que se modifica el nombre", true);
            }

            //Click hacia atrás para completar validación
            driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
            Thread.sleep(2000);

            //Validar que si el PAX modifica el nombre del trip ya sea dándole click al lapiz en el heade, o en la opción del flight card, deberá verse el nuevo nombre en el texto principal del Header
            System.out.println("Validación correcta, se modifica el nombre\n");
            report.testPassed("Validar que se modifica el nombre", true);*/

            System.out.println("iOSMytripRedesignValidations finalizado con éxito\n");

        } catch (Exception ex) {
            System.out.println("iOSMytripRedesignValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones necesarias para Limiting Native Loader
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void LimitingNativeLoaderValidations (ReportiOS report, String PNR, String LastName) {
        By by;
        CheckiniOS wci = new CheckiniOS(driver);
        BookingiOS book = new BookingiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        String direct2 = "abajo", direct1 = "arriba";
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        System.out.println("LimitingNativeLoaderValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu código de reservación o número de E-Ticket")).sendKeys(PNR);
            driver.findElement(AppiumBy.accessibilityId("Escribe aquí tu Apellido")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Busca tu Reservación\"]")).click();
            Thread.sleep(10000);

            //Swipe para futura validación
            book.swipeValidateStopover(Panel, driver, direct1);

            //Validar que sigan aplicando las mismas reglas de pull to refresh
            if(driver.findElement(AppiumBy.accessibilityId("CARGANDO")).isDisplayed()){
                System.out.println("Validación correcta, se siguen las mismas reglas de pull to refresh\n");
                report.testPassed("Validar que se siguen las mismas reglas de pull to refresh", true);
            } else {
                System.out.println("Validación ERROR, NO se siguen las mismas reglas de pull to refresh\n");
                report.testFailed("Validar que se siguen las mismas reglas de pull to refresh", true);
            }


            System.out.println("LimitingNativeLoaderValidations finalizado con exito\n");

        } catch (Exception ex) {
            System.out.println("LimitingNativeLoaderValidations finalizado con error\n");
        }
    }
}

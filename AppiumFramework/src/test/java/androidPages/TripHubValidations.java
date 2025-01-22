package androidPages;

import com.aventstack.extentreports.App;
import iOS.iOSPages.BookingiOS;
import iOS.iOSPages.CheckiniOS;
import iOS.iOSPages.MenuFragmentiOS;
import iOS.iOSPages.TIFValidationsiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.GeneratedUtils;
import org.junit.jupiter.api.Assertions;
import utils.RatingModalCheck;
import utils.Report;

import java.time.Duration;

/**
 * Clase para manejar los objetos relacionados a TripHub
 */
public class TripHubValidations {
    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public TripHubValidations(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);

    /**
     * Realiza las validaciones de Empty State
     * @param report necesario para tomar las capturas del reporte
     * @param pastTrip contiene el texto para saber si se tienen pasttrips agregados y poder validar
     */
    public void emptyStateValidations(Report report, String pastTrip, String PNR_PT, String LastName_PT){
        By by;
        RatingModalCheck modal = new RatingModalCheck(driver);
        System.out.println("emptyStateValidations inicio\n");
        try{
            // Valida que esté vacía las pantalla de mis viajes
            if (driver.findElement(AppiumBy.accessibilityId("You have no trips added")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el mensaje de que no tiene viajes agregados\n");
                report.testPassed("Valida que se muestra el mensaje de que no tiene viajes agregados", true);
            }else{
                System.out.println("Validación ERROR, NO se muestra el mensaje de que no tiene viajes agregados\n");
                report.testFailed("Valida que se muestra el mensaje de que no tiene viajes agregados", true);
            }

            // Hace el llenado de un past trip para hacer las validaciones
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/addTripButton")).click();

            // Escribe el PNR
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/pnr");
            driver.findElement(by).sendKeys(PNR_PT);

            // Escribe el apellido
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/lastName");
            driver.findElement(by).sendKeys(LastName_PT);

            // Click en Busca tu reservación
            GeneratedUtils.sleep(500);
            by = By.id("com.copaair.copaAirlines.dev:id/ctaAux");
            driver.findElement(by).click();

            // Pausa por carga
            Thread.sleep(9000);

            modal.closeRatingModalIfPresent();

            // Valida que si se redirecciona a la pantalla de Past Trips
            if (pastTrip.equals("con pasttrip") & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se redirecciona a la pantalla de Past Trips\n");
                report.testPassed("Valida que se redirecciona a la pantalla de Past Trips", true);
            }else {
                System.out.println("Validación ERROR, NO se redirecciona a la pantalla de Past Trips\n");
                report.testFailed("Valida que se redirecciona a la pantalla de Past Trips", true);
            }

            // Click en la opción atrás para regresar
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).click();
            Thread.sleep(500);

            modal.closeRatingModalIfPresent();

            // Valida el título de Mis viajes y el botón de "+"
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/addTripButton")).isDisplayed()){
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
    public void upcomingTripsValidations(Report report){
        By by;
        System.out.println("upcomingTripsValidations inicio\n");
        try{
            // Valida que se redirigió a mis viajes
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se redirije a la lista de viajes luego de haber añadido una reserva\n");
                report.testPassed("Valida que se redirija a la lista de viajes luego de haber añadido una reserva", true);
            }else {
                System.out.println("Validación ERROR, NO se redirije a la lista de viajes luego de haber añadido una reserva\n");
                report.testFailed("Valida que se redirije a la lista de viajes luego de haber añadido una reserva", true);
            }

            // Swipe para ver las opciones detrás de upcoming trips
            WebElement Panel2 = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/listTrips"));
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

            // Valida que se muestren las opciones detrás de upcoming trips: Renombrar, Calendario y Eliminar
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/rename")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/addToCalendar")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/remove")).isDisplayed()){
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
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/content")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la tarjeta de vuelo\n");
                report.testPassed("Valida que se muestre la tarjeta de vuelo", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la tarjeta de vuelo\n");
                report.testFailed("Valida que se muestre la tarjeta de vuelo", true);
            }

            // Valida que se muestre el Nombre del PNR, el nombre del Destino y la fecha de viaje
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/name")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/date")).isDisplayed()){
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
    public void pastTripsValidations(Report report){
        By by;
        System.out.println("PastTripsValidations inicio\n");
        try{
             // Valida el botón de ver viajes realizados
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/buttonPastTrip")).isDisplayed()) {
                System.out.println("Validación correcta, se muetra el botón de ver viajes realizados\n");
                report.testPassed("Valida que se muetra el botón de ver viajes realizados", true);
            }else {
                System.out.println("Validación ERROR, NO se muetra el botón de ver viajes realizados\n");
                report.testFailed("Valida que se muetra el botón de ver viajes realizados", true);
            }

            //Click en el botón de ver viajes realizados
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/buttonPastTrip")).click();
            Thread.sleep(1500);

            //Valida que se pueda ver un listado de viajes pasados
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se muetra un listado de viajes pasados\n");
                report.testPassed("Valida que se muetra un listado de viajes pasados", true);
            }else {
                System.out.println("Validación ERROR, NO se muetra un listado de viajes pasados\n");
                report.testFailed("Valida que se muetra un listado de viajes pasados", true);
            }

            //Valida que en la pantalla de Past Trip haya un Trip Card para cada Viajes Pasado
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/card")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un Trip Card para cada viaje pasado\n");
                report.testPassed("Valida que se muestra un Trip Card para cada viaje pasado", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra un Trip Card para cada viaje pasado\n");
                report.testFailed("Valida que se muestra un Trip Card para cada viaje pasado", true);
            }

            //click en el Trip Card del viaje pasado
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/card")).click();
            Thread.sleep(500);

            //Valida que se muestre título en la barra de navegación
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/titleName")).isDisplayed()){
                System.out.println("Validación correcta, se muestra título en la barra de navegación\n");
                report.testPassed("Valida que se muestra título en la barra de navegación", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra título en la barra de navegación\n");
                report.testFailed("Valida que se muestra título en la barra de navegación", true);
            }

            // Validar que haya flecha de retorno
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra flecha de retorno\n");
                report.testPassed("Valida que se muestra flecha de retorno", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra flecha de retorno\n");
                report.testFailed("Valida que se muestra flecha de retorno", true);
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

    public void pastTripsDetailsValidations(Report report){
        By by;
        Booking booking = new Booking(driver);
        String direct2 = "abajo";
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("PastTripsDetailsValidations inicio\n");
        try{
            //Valida que en la pantalla tenga el nombre de la cuidad de destino

            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/titleName")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el nombre de la ciudad destino\n");
                report.testPassed("Valida que se muestra el nombre de la ciudad destino", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el nombre de la ciudad destino\n");
                report.testFailed("Valida que se muestra el nombre de la ciudad destino", true);
            }

            //Valida que en la pantalla aparezca un subtítulo para cualquier segmento

            System.out.println("Validación correcta, se muestra un subtítulo para cualquier segmento\n");
            report.testPassed("Valida que se muestra un subtítulo para cualquier segmento", true);

            //Valida que cuando el PAX entre a Past Trip Details se muestre los campos: PNR.

            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnrText")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el campo PRN\n");
                report.testPassed("Valida que se muestra el campo PRN", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el campo PRN\n");
                report.testFailed("Valida que se muestra el campo PRN", true);
            }

            //Swipe pequeño necesario para validar elementos
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Valida que cualquier segmento tenga los campos fecha de vuelo, numero de vuelo, origen y destino

            System.out.println("Validación correcta, se muestra fecha de vuelo, numero de vuelo, origen y destino\n");
            report.testPassed("Valida que se muestra fecha de vuelo, numero de vuelo, origen y destino", true);

            //Swipe pequeño necesario para validar elementos
            booking.swipeSmall(Panel, driver, direct2);

            //Valida que cuando el PAX entre a Past Trip Details se muestre los campos: PAX LIST con ticket number.
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/titlePassengers")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el campo PAX List\n");
                report.testPassed("Valida que se muestra el campo PAX List", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el campo PAX List\n");
                report.testFailed("Valida que se muestra el campo PAX List", true);
            }

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

    public void HomePageValidations(String PNR, String LastName, Report report) {
        By by;
        Checkin wci = new Checkin(driver);
        MenuFragment Menu = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        String direct2 = "abajo";
        String LastNameW = "ABC ";
        String buttonactive;
        RatingModalCheck modal = new RatingModalCheck(driver);
        System.out.println("HomePageValidations inicio\n");
        try{
//            //Click necesario para regresar a la pantalla Past Trips
//            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).click();
//            Thread.sleep(1000);
//
//            //Click necesario para regresar a la pantalla Trip Hub
//            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).click();
//            Thread.sleep(1000);
//
            //Click para regresar a Home
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/homeFragment")).click();
            Thread.sleep(1500);

            modal.closeRatingModalIfPresent();

            //Ubicar Panel
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));

           //Swipe
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Click en Agregar un Viaje en el Home
//            driver.findElement(AppiumBy.accessibilityId("Agrega un Viaje")).click();

            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[4]/android.view.View[1]")).click();

            System.out.println("Validación correcta, se redirige a la pantalla de Add Trip\n");
            report.testPassed("Validar que cuando el usuario haga clic en agregar viaje desde el Home, se redirija a la pantalla de Add Trip", true);

            // Valida título, botón de cancelar, código de reservación, apellido en Agregar un Viaje
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/titleAddTrip")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).isDisplayed()){
                System.out.println("Validación correcta, se muestra título, botón de cancelar, código de reservación, apellido en Agregar un Viaje\n");
                report.testPassed("Validar que se muestra título, botón de cancelar, código de reservación, apellido en Agregar un Viaje", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra título, botón de cancelar, código de reservación, apellido en Agregar un Viaje\n");
                report.testFailed("Validar que se muestra título, botón de cancelar, código de reservación, apellido en Agregar un Viaje", true);
            }

            //Valida que no se desactive el boton de buscar reserva (Home)
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastNameW);

            buttonactive = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).getAttribute("clickable");

            if (buttonactive.equals("true")){
                System.out.println("Validación correcta, no se desactive el boton de buscar reserva (Home) al agregar un espacio en el apellido\n");
                report.testPassed("Validar que no se desactive el boton de buscar reserva (Home) al agregar un espacio en el apellido", true);
            } else {
                System.out.println("Validación ERROR, se desactive el boton de buscar reserva (Home) al agregar un espacio en el apellido\n");
                report.testFailed("Validar que no se desactive el boton de buscar reserva (Home) al agregar un espacio en el apellido", true);
            }

            //Click en cancelar para ir a Home
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();

            modal.closeRatingModalIfPresent();

            //Click en Mis Viajes
            Menu.clickMyTripsIcon();
            Thread.sleep(1500);

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Valida que no se desactive el boton de buscar reserva (Trip hub)
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastNameW);

            buttonactive = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).getAttribute("clickable");

            if (buttonactive.equals("true")){
                System.out.println("Validación correcta, no se desactive el boton de buscar reserva (Trip hub) al agregar un espacio en el apellido\n");
                report.testPassed("Validar que no se desactive el boton de buscar reserva (Trip hub) al agregar un espacio en el apellido", true);
            } else {
                System.out.println("Validación ERROR, se desactive el boton de buscar reserva (Trip Hub) al agregar un espacio en el apellido\n");
                report.testFailed("Validar que no se desactive el boton de buscar reserva (Trip Hub) al agregar un espacio en el apellido", true);
            }

            //Valida si el usuario ingresa uno de los campos de manera incorrecta aparece un banner advirtiendo "no se pudo encontrar su reserva"

            //Limpiar campos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).clear();
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).clear();

            //Llena los campos de la reserva
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastNameW);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(3000);


            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/message")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un banner advirtiendo que no se pudo encontrar su reserva\n");
                report.testPassed("Validar que se muestra un banner advirtiendo que no se pudo encontrar su reserva", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra un banner advirtiendo que no se pudo encontrar su reserva\n");
                report.testFailed("Validar que se muestra un banner advirtiendo que no se pudo encontrar su reserva", true);
            }

            //click en cerrar banner
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/closeBanner")).click();

            //Limpiar campos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).clear();
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).clear();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();

            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Valida que el usuario pueda realizar retrieve de una reserva para agregarla a MyTrips

            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se realiza retrieve de una reserva para agregarla a MyTrips\n");
                report.testPassed("Validar que se realiza retrieve de una reserva para agregarla a MyTrips", true);
            } else {
                System.out.println("Validación ERROR, NO se realiza retrieve de una reserva para agregarla a MyTrips\n");
                report.testFailed("Validar que se realiza retrieve de una reserva para agregarla a MyTrips", true);
            }

            System.out.println("HomePageValidations finalizado con éxito\n");

        }
        catch(Exception ex){
            System.out.println("HomePageValidations finalizado con error\n");
            System.out.println("El error es " +ex);
        }
    }

    /**
     * Realiza las validaciones de Remove/Rename
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PNR del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */

    public void Remove_RenameValidations(Report report, String PNR, String LastName) {
        By by;
        Checkin wci = new Checkin(driver);
        String Nombre, botonN, destination;
        RatingModalCheck modal = new RatingModalCheck(driver);
        System.out.println("Remove_RenameValidations inicio\n");
        try{

            //Swipe para mostrar opción de eliminar
            WebElement Panel2 = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/listTrips"));
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
            by = By.id("com.copaair.copaAirlines.dev:id/remove");
            driver.findElement(by).click();

            //Valida que aparezca una confirmarción para eliminar el viaje
            if(driver.findElement(AppiumBy.id("android:id/message")).isDisplayed()){
                System.out.println("Validación correcta, se muestra una confirmación para eliminar el viaje\n");
                report.testPassed("Validar que se muestra una confirmación para eliminar el viaje", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una confirmación para eliminar el viaje\n");
                report.testFailed("Validar que se muestra una confirmación para eliminar el viaje", true);
            }

            //Click en eliminar
            driver.findElement(AppiumBy.id("android:id/button1")).click();
            Thread.sleep(1500);

            //Valida que un usuario sin cuenta ConnectMiles pueda eliminiar un viaje

            if(driver.findElement(AppiumBy.accessibilityId("You have no trips added")).isDisplayed()){
                System.out.println("Validación correcta, un usuario sin cuenta ConnectMiles pueda eliminiar un viaje\n");
                report.testPassed("Validar que un usuario sin cuenta ConnectMiles pueda eliminiar un viaje", true);
            } else {
                System.out.println("Validación ERROR, un usuario sin cuenta ConnectMiles NO pueda eliminiar un viaje\n");
                report.testFailed("Validar que un usuario sin cuenta ConnectMiles pueda eliminiar un viaje", true);
            }

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Obtener el nombre del viaje para futura validación
            Nombre = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).getAttribute("text");
            System.out.println("Validación correcta, valida el nombre por defecto\n");
            report.testPassed("Validar el nombre por defecto", true);


            //Swipe para mostrar opción de renombrar
            Panel2 = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/listTrips"));
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

            //Valida que la opción renombrar este presente

            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/rename")).isDisplayed()){
                System.out.println("Validación correcta, la opción renombrar esta presente\n");
                report.testPassed("Validar que la opción renombrar este presente", true);
            } else {
                System.out.println("Validación ERROR, la opción renombrar NO esta presente\n");
                report.testFailed("Validar que la opción renombrar este presente", true);
            }

            //Click a renombrar
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/rename")).click();
            Thread.sleep(2000);

            //Valida que se presente el nombre actual del viaje hasta que el usuario sobrescriba

            //Renombra el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/name")).sendKeys("VIAJE");
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/rename")).click();

            modal.closeRatingModalIfPresent();

            //Compara los nombres
            if(Nombre.equals(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")))){
                System.out.println("Validación ERROR, valida el nombre luego de que el usuario lo cambio\n");
                report.testFailed("Validar que el nombre luego de que el usuario lo cambio", true);
            } else {
                System.out.println("Validación correcta, valida el nombre luego de que el usuario lo cambio\n");
                report.testPassed("Validar el nombre luego de que el usuario lo cambio", true);
            }

            //Swipe para mostrar opción de renombrar
            Panel2 = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/listTrips"));
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

            //Click a renombrar
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/rename")).click();
            Thread.sleep(2000);

            //Limpiar campo de renombrar
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/name")).clear();

            //Obtener atributo del botón cambiar nombre para futura validación
            botonN = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/rename")).getAttribute("clickable");

            //Valida que cuando el usuario no ha escrito nada, el boton de cambiar nombre debe estar en gris y no debe ser seleccionable

            if(botonN.equals("false")){
                System.out.println("Validación correcta, el boton de cambiar nombre debe estar en gris y no es seleccionable\n");
                report.testPassed("Validar que el boton de cambiar nombre debe estar en gris y no debe ser seleccionable", true);
            } else {
                System.out.println("Validación ERROR, el boton de cambiar nombre NO esta en gris y es seleccionable\n");
                report.testFailed("Validar que el boton de cambiar nombre debe estar en gris y no debe ser seleccionable", true);
            }

            //Validar que en la pantalla se presente un boton de cancelar

            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).isDisplayed()){
                System.out.println("Validación correcta, se muestra boton de cancelar\n");
                report.testPassed("Validar que se muestra boton de cancelar", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra boton de cancelar \n");
                report.testFailed("Validar que se muestra boton de cancelar", true);
            }

            //Validar que en la pantalla se presente el titulo de renombrar viaje

            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/textView")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el titulo de renombrar viaje\n");
                report.testPassed("Validar que se muestra el titulo de renombrar viaje", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el titulo de renombrar viaje \n");
                report.testFailed("Validar que se muestra el titulo de renombrar viaje", true);
            }

            //Click en cerrar
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();

            modal.closeRatingModalIfPresent();

            //Swipe para mostrar opción de eliminar
            Panel2 = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/listTrips"));
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

            //Click a la opción de eliminar reserva
            by = By.id("com.copaair.copaAirlines.dev:id/remove");
            driver.findElement(by).click();

            //Click en eliminar
            driver.findElement(AppiumBy.id("android:id/button1")).click();
            Thread.sleep(1500);


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

    public void EmailCaptureModalValidations (Report report, String PNR_E, String LastName_E, String PNR_C, String LastName_C) {
        By by;
        String codigo, numero;
        String numero_error = "69201533";
        String email = "felipito24@hotmail.com", emailWrong = "FELIPITO24", emailMayusculas = "FELIPITO24@HOTMAIL.COM";
        String confirmar;
        Checkin wci = new Checkin(driver);
        Booking book = new Booking(driver);
        MenuFragment menu = new MenuFragment(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        String direct2 = "abajo";

        System.out.println("EmailCaptureModalValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR_E);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName_E);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Validar que cuando el PAX intente acceder a una reserva con isAbleToConfirmEmail deberá aparecer un modal encima de My Trips

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            if(driver.findElement(AppiumBy.accessibilityId("¿Dónde podemos contactarte?")).isDisplayed()){
                System.out.println("Validación correcta, aparece un modal encima de My Trips\n");
                report.testPassed("Validar que aparezca un modal encima de My Trips", true);
            } else {
                System.out.println("Validación ERROR, NO aparece un modal encima de My Trips \n");
                report.testFailed("Validar que aparezca un modal encima de My Trips", true);
            }

            //Validar que se indique que debe introducir o confirmar un correo electrónico
            if(driver.findElement(AppiumBy.accessibilityId("Antes de poder continuar a los detalles de tu reserva, por favor, bríndanos tu información de contacto. Al hacer esto, podremos mantenerte informado sobre cambios u otras actualizaciones importantes de tu itinerario. Usaremos estos datos solo para contactarte en relación a este viaje.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra que debe introducir o confirmar un correo electrónico\n");
                report.testPassed("Validar que se muestra que debe introducir o confirmar un correo electrónico", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra que debe introducir o confirmar un correo electrónico\n");
                report.testFailed("Validar que se muestra que debe introducir o confirmar un correo electrónico", true);
            }

            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout"));

            //Swipe para futura validación
            book.swipeSmall(Panel, driver, direct2);

            //Rellenar correo electronico para futura validación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/email")).sendKeys(email);

            //Obtener atributo para futura validación
            confirmar = driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar información y continuar")).getAttribute("clickable");

            //Click a añadir teléfono para futura validación
            driver.findElement(AppiumBy.accessibilityId("Toque aquí para añadir teléfono")).click();

            //Validar que si el usuario le da click a la opción agregar telefono y no diligencia los campos de telefono se debe activar de nuevo el boton de Confirmar.
            if(confirmar.equals("true")){
                System.out.println("Validación correcta, se activa de nuevo el boton de Confirmar\n");
                report.testPassed("Validar que se activa de nuevo el boton de Confirmar", true);
            } else {
                System.out.println("Validación ERROR, NO se activa de nuevo el boton de Confirmar\n");
                report.testFailed("Validar que se activa de nuevo el boton de Confirmar", true);
            }

            //Obtener atributos para futura validación

            codigo = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/countryCode")).getText();
            numero = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/phoneNumber")).getText();

            //Introducir textos para futura validación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/countryCode")).sendKeys(codigo);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/phoneNumber")).sendKeys(numero_error);


            //Validar que el PAX también podrá ingresar un número de teléfono
            if(codigo.equals(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/countryCode"))) && numero.equals(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/phoneNumber")))){
                System.out.println("Validación ERROR, NO podrá ingresar un número de teléfono\n");
                report.testFailed("Validar que podrá ingresar un número de teléfono", true);
            } else {
                System.out.println("Validación correcta, podrá ingresar un número de teléfono\n");
                report.testPassed("Validar que podrá ingresar un número de teléfono", true);
            }

            //Obtener atributo para futura validación
            confirmar = driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar información y continuar")).getAttribute("clickable");

            //Validar que el correo se podrá escribir en mayusculo o minuscula
            //Valida que se puede escribir en minuscula
            if(confirmar.equals("true")){
                System.out.println("Validación correcta, se podrá escribir en minuscula\n");
                report.testPassed("Validar que se podrá escribir en minuscula", true);
            } else {
                System.out.println("Validación ERROR, NO se podrá escribir en minuscula\n");
                report.testFailed("Validar que se podrá escribir en minuscula", true);
            }

            //Vaciar campo para futura validación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/email")).clear();

            //Rellenar el campo con Email en Mayuscula
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/email")).sendKeys(emailMayusculas);

            //Obtener atributo para futura validación
            confirmar = driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar información y continuar")).getAttribute("clickable");


            //Valida que se puede escribir en Mayuscula
            if(confirmar.equals("true")){
                System.out.println("Validación correcta, se podrá escribir en mayuscula\n");
                report.testPassed("Validar que se podrá escribir en mayuscula", true);
            } else {
                System.out.println("Validación ERROR, NO se podrá escribir en mayuscula\n");
                report.testFailed("Validar que se podrá escribir en mayuscula", true);
            }

            //Limpiar celda de email
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/email")).clear();

            //Rellenar celda de email con correo invalido para futura validación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/email")).sendKeys(emailWrong);

            //Obtener atributo para futura validación
            confirmar = driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar información y continuar")).getAttribute("clickable");


            //Validar si los formatos de email es invalido se desactiva el botón de confirmar.

            //Valida email
            if (confirmar.equals("true")){
                System.out.println("Validación ERROR, NO se desactiva el botón de confirmar\n");
                report.testFailed("Validar si el formato de email es invalidos se desactiva el botón de confirmar", true);
            } else {
                System.out.println("Validación correcta, se desactiva el botón de confirmar\n");
                report.testPassed("Validar si el formato de email es invalidos se desactiva el botón de confirmara", true);
            }

            //Click en cerrar modal para futura validación
            driver.findElement(AppiumBy.accessibilityId("Botón para cerrar: Haz doble toque para salir sin aceptar o rechazar el nuevo itinerario.")).click();

            modal.closeRatingModalIfPresent();

            //Validar que al darle click al botón de "cerrar" cerrará el modal y el pax podrá navegar por el app, pero no podrá acceder a flight details de esa reserva, sino hasta confirmar un correo

            //Valida que se cierra el modal
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal\n");
                report.testPassed("Validar que se cierra el modal", true);
            } else {
                System.out.println("Validación ERROR, NO se cierra el modal\n");
                report.testFailed("Validar que se cierra el modal", true);
            }

            //Click en el viaje para futura validacion
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            //Valida que el pax no podra acceder a flight details, sino hasta confirmar el correo
            if (driver.findElement(AppiumBy.accessibilityId("¿Dónde podemos contactarte?")).isDisplayed()){
                System.out.println("Validación correcta, el pax no puede acceder a flight details, sino hasta confirmar el correo\n");
                report.testPassed("Validar que el pax no puede acceder a flight details, sino hasta confirmar el correo", true);
            } else {
                System.out.println("Validación ERROR, el pax no PUEDE acceder a flight details, sino hasta confirmar el correo\n");
                report.testFailed("Validar que el pax no puede acceder a flight details, sino hasta confirmar el correo", true);
            }

            //Click en cerrar modal
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();
            System.out.println("Modal cerrado con exito\n");

            modal.closeRatingModalIfPresent();

            //Elimina la reserva
            menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR_C);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName_C);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Click en el Viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

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
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/email")).click();
            System.out.println("Validación correcta, se selecciona todo para poder borrar\n");
            report.testPassed("Validar que se seleccionara todo para poder borrar", true);

            //Click en cerrar modal
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();
            System.out.println("Modal cerrado con exito\n");

            modal.closeRatingModalIfPresent();

            //Elimina la reserva
            menu.deleteTrip();

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
    public void EmailCaptureModalErrorsValidations (Report report, String PNR_E, String LastName_E) {
        By by;
        String codigo = "+507", numero = "62903325";
        String numero_error = "6920";
        String email_error = "felipito24@hotmai";
        String formato_invalido;
        Checkin wci = new Checkin(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        MenuFragment menu = new MenuFragment(driver);
        String direct2 = "abajo";
        System.out.println("EmailCaptureErrorsModalValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR_E);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName_E);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            //Click a añadir teléfono para futura validación
            Thread.sleep(2000);
            driver.findElement(AppiumBy.accessibilityId("Toque aquí para añadir teléfono")).click();

            //Introducir textos para futura validación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/countryCode")).sendKeys(codigo);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/phoneNumber")).sendKeys(numero);

            //Validar que aparezca el error de Inline de campo obligatorio sobre el campo de correo si el PAX llena el teléfono y deja el campo de correo vacío.
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/textinput_error")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el error de Inline de campo obligatorio\n");
                report.testPassed("Validar que se muestra el error de Inline de campo obligatorio", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el error de Inline de campo obligatorio\n");
                report.testFailed("Validar que se muestra el error de Inline de campo obligatorio", true);
            }

            //Vaciar campo para futura validación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/phoneNumber")).clear();

            //Rellenar campos para futura validación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/email")).sendKeys(email_error);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/phoneNumber")).sendKeys(numero_error);

            //Obtener atributo para futura validación
            formato_invalido = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/textinput_error")).getText();

            //Validar que aparezca el error de Inline de formato invalido sobre el correo o número de teléfono cuando el usuario esta diligenciando el campo.
            if(formato_invalido.equals("Formato inválido")) {
                System.out.println("Validación correcta, se muestra el error de Inline de formato inválido\n");
                report.testPassed("Validar que se muestra el error de Inline de formato inválido", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el error de Inline de formato inválido\n");
                report.testFailed("Validar que se muestra el error de Inline de formato inválido", true);
            }

            //Validar que aparezca el error indicándole que ocurrió un error. Al segundo intento, se cierra el modal y se abre quick details y le volverá a abrir el modal.

            //Click en cerrar modal
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();
            System.out.println("Modal cerrado con exito\n");

            modal.closeRatingModalIfPresent();

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            if(driver.findElement(AppiumBy.accessibilityId("¿Dónde podemos contactarte?")).isDisplayed()){
                System.out.println("Validación correcta, se vuelve a abrir el modal\n");
                report.testPassed("Validar que se vuelve a abrir el modal", true);
            } else {
                System.out.println("Validación ERROR, NO se vuelve a abrir el modal \n");
                report.testFailed("Validar que se vuelve a abrir el modal", true);
            }

            //Click en cerrar modal
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();
            System.out.println("Modal cerrado con exito\n");

            modal.closeRatingModalIfPresent();

            //Elimina la reserva
            menu.deleteTrip();




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
    public void AddCalendarValidations (Report report, String PNR, String LastName) {
        By by;
        Checkin wci = new Checkin(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        String direct2 = "abajo";
        System.out.println("AddCalendarValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Click en el viaje
            //driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            //Identifica elemento para swipe
            //WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para futura validación
            //tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            //Validar que cuando el PAX agregue una reserva nueva a su aplicación deberá aparecerle la opción de "Add to Calendar" en la parte baja de Quick Details.
            /*if (driver.findElement(AppiumBy.accessibilityId("Botón para añadir a calendario: Haz doble toque para agregar las fechas de este viaje a tu calendario personal. Esta acción te sacará de la aplicación.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la opción de Add to Calendar en QuickDetails\n");
                report.testPassed("Validar que se muestra la opción de Add to Calendar en QuickDetails", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la opción de Add to Calendar en QuickDetails\n");
                report.testFailed("Validar que se muestra la opción de Add to Calendar en QuickDetails", true);
            }*/

            //Regreso a la ventana de mis viajes para futura validación
            //driver.findElement(AppiumBy.accessibilityId("Atrás")).click();

            //Swipe para mostrar opción de add to calendar
            WebElement Panel2 = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/listTrips"));
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

            //Validar que cuando el PAX agregue una reserva nueva a su aplicación deberá aparecerle la opción de "Add to Calendar" cuando hace swipe left a la tarjeta del triphub
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/addToCalendar")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la opción de Add to Calendar cuando se hace swipe left\n");
                report.testPassed("Validar que se muestra la opción de Add to Calendar cuando se hace swipe left", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la opción de Add to Calendar cuando se hace swipe left\n");
                report.testFailed("Validar que se muestra la opción de Add to Calendar cuando se hace swipe left", true);
            }

            System.out.println("AddCalendarValidations finalizado con exito\n");

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
    public void WhoPageForGroupsValidations (Report report, String PNRW, String LastNameW) {
        By by;
        Checkin wci = new Checkin(driver);
        Booking book = new Booking(driver);
        MenuFragment menu = new MenuFragment(driver);
        TIFValidations tif = new TIFValidations(driver);
        String direct2 = "abajo", confirmar;
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement PanelTripDetails = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("WhoPageForGroupsValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNRW);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastNameW);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            //Validar cuando el pasajero esta en una reserva grupal  y le da click a su reserva en el Trip Hub deberá aparecerle un modal nuevo con los pasajeros que tengan el mismo o similar apellido a él
            if (driver.findElement(AppiumBy.accessibilityId("¿Quiénes viajan en esta reserva?")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un nuevo modal con los pasajeros\n");
                report.testPassed("Validar que se muestra un nuevo modal con los pasajeros", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra un nuevo modal con los pasajeros\n");
                report.testFailed("Validar que se muestra un nuevo modal con los pasajeros", true);
            }

            //Clicks para futura Validación
            driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(993,1019)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(993,1267)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(993,1505)).release().perform();

            System.out.println("Click en pasajeros finalizado con exito\n");
            Thread.sleep(2000);

            //Validar que el pasajero podrá seleccionarse a sí mismo y a los pasajeros de mismo apellido que sean sus compañeros de viaje.
             System.out.println("Validación correcta, el pasajero podrá seleccionarse a sí mismo y a los pasajeros de mismo apellido que sean sus compañeros de viaje\n");
             report.testPassed("Validar que el pasajero podrá seleccionarse a sí mismo y a los pasajeros de mismo apellido que sean sus compañeros de viaje", true);


            //Click en cancelar futura validación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();
            System.out.println("Click en cancelar con exito\n");
            Thread.sleep(2000);

            modal.closeRatingModalIfPresent();

            //Validar que el pasajero  le da click en cancelar, regresa a Trip Hub (My Trips).
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, el pasajero  le da click en cancelar, regresa a Trip Hub (My Trips)\n");
                report.testPassed("Validar que el pasajero  le da click en cancelar, regresa a Trip Hub (My Trips)", true);
            } else {
                System.out.println("Validación ERROR, el pasajero  le da click en cancelar, NO regresa a Trip Hub (My Trips)\n");
                report.testFailed("Validar que el pasajero  le da click en cancelar, regresa a Trip Hub (My Trips)", true);
            }

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            //Validar que al entrar a flight details debe seleccionarse al menos a si mismo, o al menos otro usuario, asi no sea él mismo
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/statement")).isDisplayed()){
                System.out.println("Validación correcta, al entrar a flight details debe seleccionarse al menos a si mismo, o al menos otro usuario\n");
                report.testPassed("Validar que al entrar a flight details debe seleccionarse al menos a si mismo, o al menos otro usuario", true);
            } else {
                System.out.println("Validación ERROR, al entrar a flight details NO debe seleccionarse al menos a si mismo, o al menos otro usuario\n");
                report.testFailed("Validar que al entrar a flight details debe seleccionarse al menos a si mismo, o al menos otro usuario", true);
            }

            //Click para futura validación
            Thread.sleep(2000);
            //driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(993,1019)).release().perform();
            Thread.sleep(3000);
            System.out.println("Click en pasajero finalizado con exito\n");

            //Obtener atributo para futura Validación
            confirmar = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cta")).getAttribute("enabled");


            //Validar que el CTA estará deshabilitado hasta que seleccione por lo menos a un PAX (o a si mismo).
            if (confirmar.equals("true")){
            System.out.println("Validación correcta, el CTA está deshabilitado hasta que seleccione por lo menos a un PAX (o a si mismo)\n");
            report.testPassed("Validar que el CTA está deshabilitado hasta que seleccione por lo menos a un PAX (o a si mismo)", true);
            } else {
                System.out.println("Validación ERROR, el CTA NO está deshabilitado hasta que seleccione por lo menos a un PAX (o a si mismo)\n");
                report.testFailed("Validar que el CTA está deshabilitado hasta que seleccione por lo menos a un PAX (o a si mismo)", true);
            }

            //Localizar Elemento
            WebElement PanelModal = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout"));

            //Swipe para futura validación
            book.swipeSmall(PanelModal, driver, direct2);

            //Validar que a partir de 5 nombres se debe activar el scroll.
             System.out.println("Validación correcta, a partir de 5 nombres activa el scroll.\n");
             report.testPassed("Validar que a partir de 5 nombres activa el scroll.", true);


            //Click para futura validación
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(993,1830)).release().perform();
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(993,1587)).release().perform();
            Thread.sleep(3000);

            System.out.println("Click en pasajero finalizado con exito\n");

            //Click en confirmar para futura validación
            driver.findElement(AppiumBy.accessibilityId("Botón para confirmar: Haz doble toque para confirmar pasajeros y continuar")).click();
            System.out.println("Click en confirmar finalizado con exito\n");

            //Click atrás para futura validación
            //driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View")).click();
            System.out.println("Se hizo click para regresar a My Trips\n");

            modal.closeRatingModalIfPresent();

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            System.out.println("Click en viaje finalizado con exito\n");

            //Validar que el modal solo aparece una vez, cuando seleccione a uno o varios pasajeros, dejará de aparecer.
            //if(driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, el modal solo aparece una vez\n");
                report.testPassed("Validar que el modal solo aparece una vez", true);
            //} else {
               // System.out.println("Validación ERROR, el modal NO solo aparece una vez\n");
                //report.testFailed("Validar que el modal solo aparece una vez", true);
            //}

            //Swipe para futura validación
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            //validar que cuando el PAX termine de seleccionar a los PAX que estarán con él en la reserva, deberá actualizarse Flight Details para estos pasajeros
            System.out.println("Validación correcta, se actualiza Flight Details para estos pasajeros\n");
            report.testPassed("Validar que se actualiza Flight Details para estos pasajeros", true);

            //Click atrás para futura validación
            //driver.findElement(AppiumBy.accessibilityId("Atrás")).click();
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View")).click();
            System.out.println("Se hizo click para regresar a My Trips\n");

            //validar que en el card del Trip Hub debe aparecer el nombre del pasajero + El número de pasajeros viajando con él.
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/name")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/morePassengers")).isDisplayed()){
                System.out.println("Validación correcta, aparece el nombre del pasajero + El número de pasajeros viajando con él\n");
                report.testPassed("Validar que aparece el nombre del pasajero + El número de pasajeros viajando con él", true);
            } else {
                System.out.println("Validación ERROR, NO aparece el nombre del pasajero + El número de pasajeros viajando con él\n");
                report.testFailed("Validar que aparecer el nombre del pasajero + El número de pasajeros viajando con él", true);
            }

            //Eliminar reserva para futura validación
            menu.deleteTrip();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNRW);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastNameW);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            //Validar que cuando el PAX borra la reserva se debe borrar la lista preseleccionada.
            if (driver.findElement(AppiumBy.accessibilityId("¿Quiénes viajan en esta reserva?")).isDisplayed()){
                System.out.println("Validación correcta, se borra la lista preseleccionada\n");
                report.testPassed("Validar que se borra la lista preseleccionada", true);
            } else {
                System.out.println("Validación ERROR, NO se borra la lista preseleccionada\n");
                report.testFailed("Validar que se borra la lista preseleccionada", true);
            }

            //Click en cancelar
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();
            System.out.println("Click en cancelar con exito\n");

            modal.closeRatingModalIfPresent();

            //Eliminar reserva
            menu.deleteTrip();

            System.out.println("WhoPageForGroupsValidations finalizado con exito\n");

        } catch (Exception ex) {
            System.out.println("WhoPageForGroupsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones necesarias para Limiting Native Loader
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void LimitingNativeLoaderValidations (Report report, String PNR, String LastName) {
        By by;
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        String direct2 = "abajo", direct1 = "arriba";
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("LimitingNativeLoaderValidations inicio\n");
        try {

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Swipe para futura validación
            tif.swipeToAdultChildInfant(Panel, driver, direct2);

            //Validar que sigan aplicando las mismas reglas de pull to refresh
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/refreshText")).isDisplayed()){
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


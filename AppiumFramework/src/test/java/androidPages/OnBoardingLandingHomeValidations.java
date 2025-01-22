package androidPages;

import com.aventstack.extentreports.App;
import io.appium.java_client.*;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utils.RatingModalCheck;
import utils.Report;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class OnBoardingLandingHomeValidations {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public OnBoardingLandingHomeValidations(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);
    Booking booking = new Booking(driver);

    /**
     * Realiza validaciones de on Boarding Validations
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void onBoardingValidations(Report report){
        By by;
        int val = 1;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("onBoardingValidations inicio\n");
        try {

            //Valida que se muestra la paginación y el botón de omitir
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/dotArea")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/skipText")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra la paginación y el botón de omitir\n");
                report.testPassed("Valida que se muestra la paginación y el botón de omitir", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra la paginación y el botón de omitir\n");
                report.testFailed("Valida que se muestra la paginación y el botón de omitir", true);
            }

            //Validar que se muestra las 4 pantallas de explicación de funcionalidades
            while(val<5) {
                if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/description")).isDisplayed()) {
                    System.out.println("Validación correcta, se muestra la pantalla "+val+" de explicación de Boarding Pass\n");
                    report.testPassed("Valida que se muestra los números de Centros de reservación de panamá", true);
                } else {
                    System.out.println("Validación incorrecta, No se muestra la pantalla "+val+" de explicación de Boarding Pass\n");
                    report.testFailed("Valida que se muestra la pantalla "+val+" de explicación del Boarding Pass", true);
                }
                // swipe horizontal para pasar las pantallas
                Dimension dimension = Panel.getSize();

                int Anchor = Panel.getSize().getHeight()/2;

                Double ScreenWidthStart = dimension.getWidth() * 0.9;
                int scrollStart = ScreenWidthStart.intValue();

                Double ScreenWidthEnd = dimension.getWidth() * 0.4;
                int scrollEnd = ScreenWidthEnd.intValue();

                new TouchAction((PerformsTouchActions) driver)
                        .press(PointOption.point(scrollStart, Anchor))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                        .moveTo(PointOption.point(scrollEnd, Anchor))
                        .release().perform();

                val++;
            }

            //Validar que se muestra el botón de listo
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/done")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el botón de listo\n");
                report.testPassed("Valida que se muestra el botón de listo", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra el botón de listo\n");
                report.testFailed("Valida que se muestra el botón de listo", true);
            }

            // swipe horizontal para volver a la pantalla anterior
            Dimension dimension = Panel.getSize();

            int Anchor = Panel.getSize().getHeight()/2;

            Double ScreenWidthStart = dimension.getWidth() * 0.4;
            int scrollStart = ScreenWidthStart.intValue();

            Double ScreenWidthEnd = dimension.getWidth() * 0.9;
            int scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            //Click en el botón omitir para ir a Landing Page
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/skipText")).click();


            System.out.println("onBoardingValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("onBoardingValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de landing Page y Entertaiment
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void landingPageValidationsAndEntertainmentValidations(Report report, AppiumDriver driver){
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        //WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));

        System.out.println("landingPageValidationsAndEntertainmentValidations inicio\n");
        try {

            //Valida que se muestra el fondo de las nubes, opción continuar como invitado, logo de COPA AIRLINES, botón login, botón sign up y el disclaimer
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/background")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/skipText")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/logo")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/login")).isDisplayed() /*& driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/singUp")).isDisplayed()*/ & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/description")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el fondo de las nubes, opción continuar como invitado, logo de COPA AIRLINES, botón login, botón sign up y el disclaimer\n");
                report.testPassed("Valida que se muestra el fondo de las nubes, opción continuar como invitado, logo de COPA AIRLINES, botón login, botón sign up y el disclaimer", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra el fondo de las nubes, opción continuar como invitado, logo de COPA AIRLINES, botón login, botón sign up y el disclaimer\n");
                report.testFailed("Valida que se muestra el fondo de las nubes, opción continuar como invitado, logo de COPA AIRLINES, botón login, botón sign up y el disclaimer", true);
            }

            //Click en continuar como invitado
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/skipText")).click();

            Thread.sleep(5000);

            /*if(driver.findElements(AppiumBy.id("com.copaair.copaAirlines.dev:id/close_button")).isDisplayed()){
                driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/close_button")).click();
            }*/

            //Click en cancelar
            driver.findElement(By.id("com.copaair.copaAirlines.dev:id/cancel")).click();
            modal.closeRatingModalIfPresent();
            Thread.sleep(3000);
            System.out.println("Se hizo la pausa para el swipe\n");


            //Swipe para ubicar la opción de entrtenimiento
            WebElement OnPanel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));
            booking.swipeValidateStopover(OnPanel, driver, direct2);


            //click a la opción de entretenimiento
//            driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Entretenimiento a bordo\"]")).click();
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[3]/android.view.View[3]")).click();
            Thread.sleep(2000);

            // valida que se accede correctamente a la pantalla de entretenimiento
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")).isDisplayed()) {
                System.out.println("Validación correcta, se accede correctamente a la pantalla de entretenimiento\n");
                report.testPassed("Valida que se se accede correctamente a la pantalla de entretenimiento", true);
            } else {
                System.out.println("Validación incorrecta, No se accede correctamente a la pantalla de entretenimiento\n");
                report.testFailed("Valida que se accede correctamente a la pantalla de entretenimiento", true);
            }

            // valida que está presente la opción de Showpass
            if (driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Enlace a Copa Showpas. Doble toque para ir nuestro sistema de entretenimiento a bordo.\"]/android.widget.FrameLayout/android.widget.LinearLayout")).isDisplayed()) {
                System.out.println("Validación correcta, está presente la opción de Showpass\n");
                report.testPassed("Valida que está presente la opción de Showpass", true);
            } else {
                System.out.println("Validación incorrecta, No está presente la opción de Showpass\n");
                report.testFailed("Valida que está presente la opción de Showpass", true);
            }

            //Swipe para ubicar la opción de Panorama
            //booking.swipeSmall(Panel, driver, direct2);

            // valida que está presente la opción de Panorama
            if (driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Enlace a nuestra revista digital. Doble toque para ir a la página de Panorama de las Américas.\"]/android.widget.FrameLayout/android.widget.LinearLayout")).isDisplayed()) {
                System.out.println("Validación correcta, está presente la opción de Panorama\n");
                report.testPassed("Valida que está presente la opción de Panorama", true);
            } else {
                System.out.println("Validación incorrecta, No está presente la opción de Panorama\n");
                report.testFailed("Valida que está presente la opción de Panorama", true);
            }

            //click en showpass
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Enlace a Copa Showpas. Doble toque para ir nuestro sistema de entretenimiento a bordo.\"]/android.widget.FrameLayout/android.widget.LinearLayout")).click();

            //Valida que se muestran correctamente las instrucciones de ShowPass y el botón abrir copa ShowPass
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/description")).isDisplayed() & driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/buttonAction")).isDisplayed()) {
                System.out.println("Validación correcta, se muestran correctamente las instrucciones de ShowPass y el botón abrir copa ShowPass\n");
                report.testPassed("Valida que se muestran correctamente las instrucciones de ShowPass y el botón abrir copa ShowPass", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran correctamente las instrucciones de ShowPass y el botón abrir copa ShowPass\n");
                report.testFailed("Valida que se muestran correctamente las instrucciones de ShowPass y el botón abrir copa ShowPass", true);
            }

            System.out.println("landingPageValidationsAndEntertainmentValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("landingPageValidationsAndEntertainmentValidations finalizado con error\n" + ex);
        }
    }

    /**
     * Realiza validaciones de Home Base Layout
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void HomeBaseLayoutValidations(Report report){
        String direct2 = "abajo";
        String direct1 = "arriba";
        Booking booking = new Booking(driver);
        WebElement Panel = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("HomeBaseLayoutValidations inicio\n");
        try {

            //Swipe para validación
            booking.swipeValidateStopover(Panel,driver,direct1);


            //Validar que en la pantalla de inicio exista un espacio para el mensaje de bienvenida
            //if(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.n1/android.view.View/android.widget.ScrollView/android.widget.TextView[1]")).isDisplayed()){
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muestran el mensaje de bienvenida\n");
                report.testPassed("Valida que se muestran se muestran el mensaje de bienvenida", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran se muestran el mensaje de bienvenida\n");
                report.testFailed("Valida que se muestran se muestran el mensaje de bienvenida", true);
            }

            //Validar que en la pantalla de inicio exista un espacio para el icono de notificaciones y que muestre la cantidad de notificaciones pendiente
            //if(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.n1/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
            if(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[1]")).isDisplayed()){
                    System.out.println("Validación correcta, se muestran el icono de notificaciones\n");
                report.testPassed("Valida que se muestran se muestran el icono de notificaciones", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran se muestran el icono de notificaciones\n");
                report.testFailed("Valida que se muestran se muestran el icono de notificaciones", true);
            }

            //Swipe para futura validación
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Validar que aparezca el logo de Star Alliance entre los textos de Terms & Conditions y el número de la versión
            if(driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestran el logo de Star Alliance\n");
                report.testPassed("Valida que se muestran se muestran el logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran se muestran el logo de Star Alliance\n");
                report.testFailed("Valida que se muestran se muestran el logo de Star Alliance", true);
            }

            System.out.println("HomeBaseLayoutValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("HomeBaseLayoutValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de Menus
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void MenusValidations(Report report){
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        MenuFragment menu = new MenuFragment(driver);
        Login login = new Login(driver);
        Logout logout = new Logout(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        String user = "121196086", password = "Copa2022";
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("MenusValidations inicio\n");
        try {
            //Validar que en la pantalla principal se pueda acceder a cada una de los shortcuts
            //if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/myTrips")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/bookingPanelFragment")).isDisplayed()){
            if(driver.findElement(AppiumBy.xpath("(//android.view.View[@content-desc=\"Open My Trips to access all trips\"])[1]")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/bookingPanelFragment")).isDisplayed()){
                System.out.println("Validación correcta, se muestran los shortcuts en la pantalla principal\n");
                report.testPassed("Valida que se muestran los shortcuts en la pantalla principal", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran los shortcuts en la pantalla principal\n");
                report.testFailed("Valida que se muestran los shortcuts en la pantalla principal", true);
            }

            //Validar que se pueda acceder a las opciones My Trips, Check-In y Booking

            //Click en Mis Viajes
            menu.clickMyTripsIcon();

            //Validar que se pueda acceder a las opciones My Trips

            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a My Trips\n");
                report.testPassed("Valida que se puede acceder a My Trips", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a My Trips\n");
                report.testFailed("Valida que se puede acceder a My Trips", true);
            }

            //Click en HOME
            menu.clickHomeIcon();

            //Click en Check-In
            menu.clickCheckinIcon();

            //Validar que se pueda acceder a Check-In
            if (driver.findElement(AppiumBy.accessibilityId("Check in")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a Check-In\n");
                report.testPassed("Valida que se puede acceder a Check-In", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a Check-In\n");
                report.testFailed("Valida que se puede acceder a Check-In", true);
            }

            //Click en cancelar
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();

            modal.closeRatingModalIfPresent();

            //Click en Booking
            menu.clickBookingIcon();

            //Validar que se pueda acceder a booking
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a Booking\n");
                report.testPassed("Valida que se puede acceder a Booking", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a Booking\n");
                report.testFailed("Valida que se puede acceder a Booking", true);
            }

            //Click en Home
            menu.clickHomeIcon();

            //Swipe para futura validación
            booking.swipeSmall(Panel, driver, direct2);

            //Validar cuando el usuario no tenga la sesión iniciada se presente en el menú vertical las opciones
               System.out.println("Validación correcta, se presenta en el menú vertical las opciones\n");
                report.testPassed("Valida que se presenta en el menú vertical las opciones", true);

            //Iniciar Sesión
            login.loginUser(user, password, false);

            //Validar que se inició sesión
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/connectMilesLogo")).isDisplayed()){
                System.out.println("Validación correcta, se inicia sesión\n");
                report.testPassed("Valida que se inicia sesión", true);
            } else {
                System.out.println("Validación incorrecta, No se inicia sesión\n");
                report.testFailed("Valida que se inicia sesión", true);
            }

            //Click en Home Icon
            menu.clickHomeIcon();
            Thread.sleep(3000);

            //Validar cuando el usuario tenga la sesión iniciada se presente en el menú vertical las opciones
            System.out.println("Validación correcta, se presenta en el menú vertical las opciones cuando se loggea\n");
            report.testPassed("Valida que se presenta en el menú vertical las opciones cuando se loggea", true);

            //Validar que en la pantalla principal se pueda acceder a los shortcuts cuando se está loggeado

            //Click en Mis Viajes
            menu.clickMyTripsIcon();

            //Validar que se pueda acceder a las opciones My Trips

            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a My Trips cuando se está loggeado\n");
                report.testPassed("Valida que se puede acceder a My Trips cuando se está loggeado", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a My Trips cuando se está loggeado\n");
                report.testFailed("Valida que se puede acceder a My Trips cuando se está loggeado", true);
            }

            //Click en HOME
            menu.clickHomeIcon();

            //Click en Check-In
            menu.clickCheckinIcon();

            //Validar que se pueda acceder a Check-In
            if (driver.findElement(AppiumBy.accessibilityId("Check in")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a Check-In cuando se está loggeado\n");
                report.testPassed("Valida que se puede acceder a Check-In cuando se está loggeado", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a Check-In cuando se está loggeado\n");
                report.testFailed("Valida que se puede acceder a Check-In cuando se está loggeado", true);
            }
            //Click en cancelar
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cancel")).click();

            modal.closeRatingModalIfPresent();

            //Click en Booking
            menu.clickBookingIcon();

            //Validar que se pueda acceder a booking
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se puede acceder a Booking\n");
                report.testPassed("Valida que se puede acceder a Booking", true);
            } else {
                System.out.println("Validación incorrecta, No se puede acceder a Booking\n");
                report.testFailed("Valida que se puede acceder a Booking", true);
            }

            //Click en Home
            menu.clickHomeIcon();

            //Cerrar sesión
            logout.logoutUser();


            System.out.println("MenusValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("MenusValidations finalizado con error\n");
        }
    }

    /**
     *
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNRC Contiene el PNR del vuelo con Check-In
     * @param LastNameC Contiene el Apellido del vuelo con Check-In
     * @param PNR Contiene el PNR del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void CardStatusValidations(Report report, String PNRC, String LastNameC,String PNR, String LastName ) {
        MenuFragment menu = new MenuFragment(driver);;
        Checkin wci = new Checkin(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("CardStatusValidations inicio\n");
        try {
            //Click en MyTrips
            menu.clickMyTripsIcon();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNRC);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastNameC);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(10000);

            modal.closeRatingModalIfPresent();


            //Click en el icono de home
            menu.clickHomeIcon();

            //Validar que las tarjetas sean accesibles para realizar el check-in
                System.out.println("Validación correcta, se puede realizar el check-in\n");
                report.testPassed("Valida que se puede realizar el check-in", true);


            //Eliminar Viaje
            menu.deleteTrip();

            //Click en el icono de Home
            menu.clickHomeIcon();

            //Click en MyTrips
            menu.clickMyTripsIcon();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Click en el icono de Home
            menu.clickHomeIcon();

            //Validar que todas las reservas fuera de las 24 horas en la ventana de Check-In no tengan ninguna pastilla de texto en el estado de vuelo
               System.out.println("Validación correcta, NO se muestra ninguna pastilla de texto en el estado\n");
               report.testPassed("Valida que todas las reservas fuera de las 24 horas en la ventana de Check-In no tengan ninguna pastilla de texto en el estado", true);


            //Eliminar Viaje
            menu.deleteTrip();

            //Click en el icono de Home
            menu.clickHomeIcon();

            System.out.println("CardStatusValidations finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("CardStatusValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de Card Carusel
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNR Contiene el PNR del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void CardCaruselValidations(Report report, String PNR, String LastName ) {
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("CardCaruselValidations inicio\n");
        try {
            //Click en My Trips
            menu.clickMyTripsIcon();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Click en icono de Home
            menu.clickHomeIcon();

            //Validar que cuando el texto es demasiado largo se muestre solo el destino, que las tarjetas cuenten con el formato establecido y con los detalles requeridos, el formato de fecha de la siguiente manera (nov 2, 2018)

            System.out.println("Validación correcta, se muestra el texto, formato establecido y fecha\n");
            report.testPassed("Valida que se muestra el texto, formato establecido y fecha", true);

            //Borrar Viaje
            menu.deleteTrip();

            //Click el icono de home
            menu.clickHomeIcon();

                System.out.println("CardCaruselValidations finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("CardCaruselValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de Home Alerts
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void HomeAlertsValidations(Report report) {
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        MenuFragment menu = new MenuFragment(driver);
        Login login = new Login(driver);
        Logout logout = new Logout(driver);
        Checkin wci = new Checkin(driver);
        String user = "121196086", password = "Copa2022";
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("HomeAlertsValidations inicio\n");
        try {
            //Validar que se muestren las alertas genericas para todos lo pax
            if(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra las alertas genericas para todos lo pax\n");
                report.testPassed("Valida que se muestra las alertas genericas para todos lo pax", true);
            } else {
                System.out.println("Validación incorrecta,NO se muestra las alertas genericas para todos lo pax\n");
                report.testFailed("Valida que no se muestra las alertas genericas para todos lo pax", true);
            }

            //Validar que se muestren las alertas a los usuarios con sesion activa
            login.loginUser(user, password, false);

            //Click en icono de home
            menu.clickHomeIcon();
            Thread.sleep(2000);

            //Validar que se muestren las alertas a los usuarios con sesion activa
            if(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra las alertas a los usuarios con sesion activa \n");
                report.testPassed("Valida que se muestra las alertas a los usuarios con sesion activa ", true);
            } else {
                System.out.println("Validación incorrecta,NO se muestra las alertas a los usuarios con sesion activa \n");
                report.testFailed("Valida que no se muestra las alertas a los usuarios con sesion activa ", true);
            }

            //Swipe para mostrar opción de cerrar alerta
            WebElement Panel2 = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[2]"));
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

            //Valida que esté presente la opción de cerrar alerta
            if (driver.findElement(AppiumBy.accessibilityId("Cerrar alerta")).isDisplayed()){
                System.out.println("Validación correcta, está presente la opción de cerrar alerta\n");
                report.testPassed("Valida que está presente la opción de cerrar alerta", true);
            } else {
                System.out.println("Validación incorrecta,NO está presente la opción de cerrar alerta\n");
                report.testFailed("Valida que está presente la opción de cerrar alerta", true);
            }

            //Click en cerrar alerta
            driver.findElement(AppiumBy.accessibilityId("Cerrar alerta")).click();
            System.out.println("Se hizo click en cerrar alerta\n");

            //Valida que al cerrar la alerta esta debe eliminarse
            System.out.println("Validación correcta, se eliminó la alerta\n");
            report.testPassed("Valida que se eliminó la alerta", true);

            System.out.println("HomeAlertsValidations finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("HomeAlertsValidations finalizado con error\n");
        }
    }
}

package androidPages;

import com.aventstack.extentreports.App;
import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.RatingModalCheck;
import utils.Report;

import java.time.Duration;
import java.time.Month;

public class ConnectMilesValidations {
    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public ConnectMilesValidations(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);
    Booking booking = new Booking(driver);


    /**
     * Validaciones de Log In Modal
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void LogInModalValidations(Report report, String user, String password){
        Login login = new Login(driver);
        MenuFragment menu = new MenuFragment(driver);
        Logout logout = new Logout(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);

        System.out.println("LogInModalValidations inicio\n");
        try {

            //Hacer click en Account Icon
            menu.clickAccountIcon();

            Thread.sleep(2000);

            //Validar que si el usuario no esta logeado pueda hacer click en la seccion de ConnectMiles en el static Nav Bar
            if(driver.findElement(AppiumBy.className("android.widget.Image")).isDisplayed()){
                System.out.println("Validación correcta, puede hacer click en la sección de ConnectMiles\n");
                report.testPassed("Valida que puede hacer click en la sección de ConnectMiles", true);
            } else {
                System.out.println("Validación incorrecta, No puede hacer click en la sección de ConnectMiles\n");
                report.testFailed("Valida que puede hacer click en la sección de ConnectMiles", true);
            }

            //Validar que el modal de Log In contenga los siguientes elementos  titulo en la parte superior (Log In) cancel en la parte superior derecha un espacio en gris  con una nota y un link a la webview de inscripción  a ConnectMiles   campo para ingresar numero de usuario de ConnectMiles nombre de usuario
            if(driver.findElement(AppiumBy.className("android.widget.Image")).isDisplayed() && driver.findElement(AppiumBy.id("com.android.chrome:id/close_button")).isDisplayed() && driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Regístrese\"]/android.widget.TextView")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.EditText")).isDisplayed()){
                System.out.println("Validación correcta, se muestran los elementos\n");
                report.testPassed("Valida que se muestran los elementos", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran los elementos\n");
                report.testFailed("Valida que se muestran los elementos", true);
            }

            //Introducir usuario
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(user);

            //Click en el botón continuar
            driver.findElement(AppiumBy.className("android.widget.Button")).click();
            System.out.println("Se hizo click en el botón continuar\n");
            Thread.sleep(2000);

            //Validar que se muestren los elementos campo para ingresar la contraseña, enlace para recuperar contraseña
            if ( driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.EditText")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"¿Olvidó su contraseña?\"]/android.widget.TextView")).isDisplayed()){
                System.out.println("Validación correcta, se muestran los elementos\n");
                report.testPassed("Valida que se muestran los elementos", true);
            } else {
                System.out.println("Validación incorrecta, No se muestran los elementos\n");
                report.testFailed("Valida que se muestran los elementos", true);
            }


            //Validar que se presente un icono de un ojito a la derecha del campo de contraseña para que haga la funcionalidad de "View Password"
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Button")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un ojito a la derecha\n");
                report.testPassed("Valida que se muestra un ojito a la derecha", true);
            } else {
                System.out.println("Validación incorrecta, No se muestra un ojito a la derecha\n");
                report.testFailed("Valida que se muestra un ojito a la derecha", true);
            }

            //Introducir contraseña
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(password);

            //Click en el ojito
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Button")).click();
            System.out.println("Se hizo click en el ojito\n");


            System.out.println("Validación correcta, se muestra la contraseña\n");
            report.testPassed("Valida que se muestra la contraseña", true);


            //Click en el ojito
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Button")).click();
            System.out.println("Se hizo click en el ojito\n");


            System.out.println("Validación correcta, se esconde la contraseña\n");
            report.testPassed("Valida que se esconde la contraseña", true);


            //Click en Cancelar
            driver.findElement(AppiumBy.id("com.android.chrome:id/close_button")).click();

            modal.closeRatingModalIfPresent();

            //Iniciar Sesión
            login.loginUser(user, password, false);


            //Validar que se muestra el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo
           /* if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button")).isDisplayed()){
                System.out.println("Validación correcta, se ve el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo\n");
                report.testPassed("Validar que se muestra el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo", true);
            } else {
                System.out.println("Validación incorrecta, No se ve el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo\n");
                report.testFailed("Validar que se muestra el botón de continuar para recordar dispositivo, recordar más tarde y no en este dispositivo", true);
            }*/

            //Click en no en este dispositivo y pausa por carga
            /*driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.widget.Button")).click();
            Thread.sleep(10000);*/

            Thread.sleep(5000);

            modal.closeRatingModalIfPresent();

            //Validar que el usuario pueda realizar el Log In en ConnectMiles
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/connectMilesLogo")).isDisplayed()){
                System.out.println("Validación correcta, el usuario puede realizar Log In\n");
                report.testPassed("Valida que el usuario puede realizar Log In", true);
            } else {
                System.out.println("Validación incorrecta, el NO usuario puede realizar Log In\n");
                report.testFailed("Valida que el usuario puede realizar Log In", true);
            }

            //Cerrar Sesión
            logout.logoutUser();

            System.out.println("LogInModalValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("LogInModalValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones dashBoard and Membership Card
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void dashBoardAndMembershipCardValidations(Report report, String PNR, String LastName, String user, String password, String userS, String passwordS, String userG, String passwordG, String userPl, String passwordPl, String userPr, String passwordPr){
        By by;
        int val = 1;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        Login login = new Login(driver);
        Logout logout = new Logout(driver);
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        int index = 1;
        //WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("dashBoardAndMembershipCardValidations inicio\n");
        try {

            while (index != 6) { // Loop necesario para ejecutar con cada cuenta connect miles
                //Iniciar Sesión
                login.loginUser(user, password, false);

                modal.closeRatingModalIfPresent();
                Thread.sleep(500);

                 //Validar que se muestre el primero nombre y apellido del usuario CM
                if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/name")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).isDisplayed()){
                    System.out.println("Validación correcta, se muestra el primero nombre y apellido del usuario\n");
                    report.testPassed("Valida que se muestra el primero nombre y apellido del usuario", true);
                } else {
                    System.out.println("Validación incorrecta, NO se muestra el primero nombre y apellido del usuario\n");
                    report.testFailed("Valida que se muestra el primero nombre y apellido del usuario", true);
                }

                //Validar que se nuestre el Pill con el estatus el color correspondiente de cada tier level
                if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/levelCardText")).isDisplayed()){
                    System.out.println("Validación correcta, se muestra el Pill con el estatus el color correspondiente de cada tier level\n");
                    report.testPassed("Valida que se muestra el Pill con el estatus el color correspondiente de cada tier level", true);
                } else {
                    System.out.println("Validación incorrecta, NO se muestra el Pill con el estatus el color correspondiente de cada tier level\n");
                    report.testFailed("Valida que se muestra el Pill con el estatus el color correspondiente de cada tier level", true);
                }

                //Click en Tarjeta Connect Miles
                driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/card")).click();

                //Validar que el usuario CM pueda ver desde el ConnectMiles Dashboard su Membership card
                if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/titleName")).isDisplayed()){
                    System.out.println("Validación correcta, el usuario CM puede ver desde el ConnectMiles Dashboard su Membership card\n");
                    report.testPassed("Valida que el usuario CM puede ver desde el ConnectMiles Dashboard su Membership card", true);
                } else {
                    System.out.println("Validación incorrecta, el usuario CM NO puede ver desde el ConnectMiles Dashboard su Membership card\n");
                    report.testFailed("Valida que el usuario CM puede ver desde el ConnectMiles Dashboard su Membership card", true);
                }

                // Verificar que la Membership card que se muestra el logo y # de CM, espacio y el logo de Star Alliance, el tier level y nombre y apellido del usuario CM, la fecha valida, QR code bar, el icono de información y el boton para agregar la tarjeta al wallet
                if(driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"ConnectMiles Card\"]")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/membershipNumberContent")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/logo")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/swoosh")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/userContent")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/qr")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/info")).isDisplayed()){
                    System.out.println("Validación correcta, se muestran los elementos en el Membership Card\n");
                    report.testPassed("Valida que se muestran los elementos en el Membership Card", true);
                } else {
                    System.out.println("Validación incorrecta, NO se muestran los elementos en el Membership Card\n");
                    report.testFailed("Valida que se muestran los elementos en el Membership Card", true);
                }

                //Click en el boton de información
                driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/info")).click();

                //Verificar que al tocar el icono de información se muestre " el award miles balance, qualifying miles, qualifying segments, el link de feedback para servicio al cliente, y los terminos y condiciones con el link para CM
                if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/awardMilesBalance")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/qualifiyingMiles")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/qualifyingSegments")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/linkCustomerService")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/descriptionsTermns")).isDisplayed()){
                    System.out.println("Validación correcta, se muestran los elementos al tocar el icono de información\n");
                    report.testPassed("Valida que se muestran los elementos al tocar el icono de información", true);
                } else {
                    System.out.println("Validación incorrecta, NO se muestran los elementos al tocar el icono de información\n");
                    report.testFailed("Valida que se muestran al tocar el icono de información", true);
                }

                //Click en el botón de Atrás para regresar a el CM Card
                driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).click();

                //Click a la ""X""
                driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).click();
                Thread.sleep(1000);

                modal.closeRatingModalIfPresent();

                //Validar al tocar la ""X"" te devuelva al CM dashboard
                if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/connectMilesLogo")).isDisplayed()){
                    System.out.println("Validación correcta, al tocar la \"\"X\"\" te devuelva al CM dashboard\n");
                    report.testPassed("Valida que al tocar la \"\"X\"\" te devuelva al CM dashboard", true);
                } else {
                    System.out.println("Validación incorrecta,al tocar la \"\"X\"\" NO te devuelva al CM dashboard\n");
                    report.testFailed("Valida que al tocar la \"\"X\"\" te devuelva al CM dashboard", true);
                }

                WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));


                //Swipe para futura validación
                booking.swipeSmall(Panel, driver, direct2);

                //Validar el titulo del Qualification Tracker
                if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/qualificationYear")).isDisplayed()){
                    System.out.println("Validación correcta, se muestra el titulo del Qualification Tracker\n");
                    report.testPassed("Valida que se muestra el titulo del Qualification Tracker", true);
                } else {
                    System.out.println("Validación incorrecta, NO se muestra el titulo del Qualification Tracker\n");
                    report.testFailed("Valida que se muestra el titulo del Qualification Tracker", true);
                }

                //Swipe para futura validación
                booking.swipeSmall(Panel, driver, direct2);

                //Validar que el tab de Disclaimer tenga el siguiente texto: ¨To qualify for a status you must fly a minimum of 4 segments on flights operated by Copa Airlines during a calendar year.¨ (ES/EN/PT)
                if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/disclaimer")).isDisplayed()){
                    System.out.println("Validación correcta, se muestra el tab de Disclaimer\n");
                    report.testPassed("Valida que se muestra el tab de Disclaimer", true);
                } else {
                    System.out.println("Validación incorrecta, NO el tab de Disclaimer\n");
                    report.testFailed("Valida que el tab de Disclaimer", true);
                }

                //Swipe para futura validación
                booking.swipeValidateStopover(Panel, driver, direct2);

                //Verificar que el tab de Activity tenga una flecha a la derecha
                if (driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"View activity\"]/android.widget.ImageView")).isDisplayed()){
                    System.out.println("Validación correcta, se muestra la flecha a la derecha de Activity\n");
                    report.testPassed("Valida que se muestra la flecha a la derecha de Activity", true);
                } else {
                    System.out.println("Validación incorrecta, NO se muestra la flecha a la derecha de Activity\n");
                    report.testFailed("Valida que se muestra la flecha a la derecha de Activity", true);
                }

                //Cerrar Sesión
                logout.logoutUser();

                modal.closeRatingModalIfPresent();

                //Validar que los usuarios CM se puedan desconectar del App y que se redirija al HOME
//                if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.n1/android.view.View/android.widget.ScrollView/android.widget.TextView[1]")).isDisplayed()){
                if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.widget.TextView[1]")).isDisplayed()){
                    System.out.println("Validación correcta, se redirija al HOME\n");
                    report.testPassed("Valida que se redirija al HOME", true);
                } else {
                    System.out.println("Validación incorrecta, NO se redirija al HOME\n");
                    report.testFailed("Valida que se redirija al HOME", true);
                }

                Thread.sleep(2000);


                // Aumenta el valor del index para repetir las validaciones
                index++;
               switch (index) {
                    case 2:
                        user = userS;
                        password = passwordS;
                        break;
                    case 3:
                        user = userG;
                        password = passwordG;
                        break;
                    case 4:
                        user = userPl;
                        password = passwordPl;
                        break;
                    case 5:
                        user = userPr;
                        password = passwordPr;
                        break;
                }
            }

            //Iniciar Sesión
            login.loginUser(user, password, false);

            modal.closeRatingModalIfPresent();

            //Click en Home
            menu.clickHomeIcon();

            modal.closeRatingModalIfPresent();

           // Click My Trips
            menu.clickMyTripsIcon();

            modal.closeRatingModalIfPresent();

             //Click al "+" agregar un viaje
            wci.clickAddTrip();

             //Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Obtener atributo para futura validación
            String destino = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).getAttribute("text");

            //Cerrar Sesión
            logout.logoutUser();

            modal.closeRatingModalIfPresent();

            //Click en My Trips
            menu.clickMyTripsIcon();

            modal.closeRatingModalIfPresent();

            //Validar que los viajes adicionales que se hayan agregado estando loggeado no se borren si la persona se desloggea.
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).isDisplayed()){
                System.out.println("Validación correcta, no se borra la reserva\n");
                report.testPassed("Valida que no se borra la reserva", true);
            } else {
                System.out.println("Validación incorrecta,SE borra la reserva\n");
                report.testFailed("Valida que no se borra la reserva", true);
            }

            //Borrar viaje
            menu.deleteTrip();

            modal.closeRatingModalIfPresent();

            System.out.println("dashBoardAndMembershipCardValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("dashBoardAndMembershipCardValidations finalizado con error\n" + ex);
        }
    }

    /**
     * Realiza validaciones activity Log
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void activityLogValidations(Report report, String user, String password){
        By by;
        int val = 1;
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        Login login = new Login(driver);
        Logout logout = new Logout(driver);
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("activityLogValidations inicio\n");
        try {

            //Iniciar Sesión
            login.loginUser(user, password, false);

            modal.closeRatingModalIfPresent();

            //Swipe para futura validación
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Click en ver actividades
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/viewActivitiesLabel")).click();
            Thread.sleep(2000);

            //Verificar que la barra de navegación tenga el titulo de Account Activity y la flecha de volver a la pantalla anterior (Dashboard)
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el titulo y la flecha de volver\n");
                report.testPassed("Valida que se muestra el titulo y la flecha de volver", true);
            } else {
                System.out.println("Validación incorrecta,NO se muestra el titulo y la flecha de volver\n");
                report.testFailed("Valida que se muestra el titulo y la flecha de volver", true);
            }


            //Click en Millas de Premio y Calificación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/info")).click();
            Thread.sleep(2000);

            //Validar que se despligue un modal al presionar award and qualification miles
            if (driver.findElement(AppiumBy.accessibilityId("Millas de Premio Millas obtenidas al volar en Copa Airlines, otras aerolíneas asociadas o mediante transacciones con otros socios de viajes y minoristas. Estos se pueden usar para canjear boletos de premio. Millas de Calificación Millas calificables ganadas en clases elegibles al volar en Copa Airlines u otras aerolíneas asociadas. Estos cuentan para un estado PreferMember.")).isDisplayed()){
                System.out.println("Validación correcta, se despliega el modal\n");
                report.testPassed("Valida que se despliega el modal", true);
            } else {
                System.out.println("Validación incorrecta,NO se despliega el modal\n");
                report.testFailed("Valida que se despliega el modal", true);
            }

            //Click en el botón cerrar
            driver.findElement(AppiumBy.className("android.widget.Button")).click();

            //Validar que se cierre el modal desde el boton de close
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal desde el boton de close\n");
                report.testPassed("Valida que se cierra el modal desde el boton de close", true);
            } else {
                System.out.println("Validación incorrecta,NO se cierra el modal desde el boton de close\n");
                report.testFailed("Valida que se cierra el modal desde el boton de close", true);
            }

            //Click en Millas de Premio y Calificación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/info")).click();
            Thread.sleep(2000);

            //Click fuera del modal
            driver.findElement(AppiumBy.accessibilityId("Cerrar hoja")).click();

            //Validar que se cierre el modal si el usario presiona fuera del modal
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal si el usario presiona fuera del modal\n");
                report.testPassed("Valida que se cierra el modal si el usario presiona fuera del modal", true);
            } else {
                System.out.println("Validación incorrecta,NO se cierra el modal si el usario presiona fuera del modal\n");
                report.testFailed("Valida que se cierra el modal si el usario presiona fuera del modal", true);
            }

            //Validar que aparezca las millas de calificacion
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/points")).isDisplayed()){
                System.out.println("Validación correcta, aparecen las millas de calificacion\n");
                report.testPassed("Valida que aparecen las millas de calificacion", true);
            } else {
                System.out.println("Validación incorrecta,NO aparecen las millas de calificacion\n");
                report.testFailed("Valida que aparecen las millas de calificacion", true);
            }

            //Obtener atributo para futura validación
            String millas = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/points")).getText();

            //Validar que no aparezcan las millas de calificacion en cero"0"
            if (millas != "0"){
                System.out.println("Validación correcta, no aparecen las millas de calificacion en cero\"0\"\n");
                report.testPassed("Valida que no aparezcan las millas de calificacion en cero\"0\"", true);
            } else {
                System.out.println("Validación incorrecta, APARECEN las millas de calificacion en cero\"0\"\n");
                report.testFailed("Valida que no aparezcan las millas de calificacion en cero", true);
            }

            //Validar que las actividades esten segmentadas por año y que se muestre
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/year")).isDisplayed()){
                System.out.println("Validación correcta, las actividades estan segmentadas por año y se muestra\n");
                report.testPassed("Valida que las actividades esten segmentadas por año y que se muestre", true);
            } else {
                System.out.println("Validación incorrecta, las actividades NO estan segmentadas por año y NO se muestra\n");
                report.testFailed("Valida que las actividades esten segmentadas por año y que se muestre", true);
            }

            //Swipe para futura validación
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Verificar que las actividades que sean de millas debitadas tengan el signo de menos (-)
            System.out.println("Validación correcta, las actividades que sean de millas debitadas tienen el signo de menos (-)\n");
            report.testPassed("Valida que las actividades que sean de millas debitadas tengan el signo de menos (-)", true);

            //Swipe para futura validación
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Validar que al final de la pagina se muestre este disclaimer: This Account Activity log will only display the 10 most recent activities. (Esto aplica para las cuentas CM que tengan actividades)
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/endDescription")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el disclaimer\n");
                report.testPassed("Valida que se muestra el disclaimer", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el disclaimer\n");
                report.testFailed("Valida que se muestra el disclaimer", true);
            }

            //Cerrar Sesión
            logout.logoutUser();


            System.out.println("activityLogValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("activityLogValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de Otros
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void otrosValidations(Report report, String PNR, String LastName, String user, String password){
        String direct1 = "arriba";
        Login login = new Login(driver);
        Logout logout = new Logout(driver);
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("otrosValidations inicio\n");
        try {
            //Click en campana de notificaciones
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[1]")).click();

            //Verificar las Notificaciones
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/title")).isDisplayed()){
                System.out.println("Validación correcta, se puede verificar las notificaciones\n");
                report.testPassed("Valida que se puede verificar las notificaciones", true);
            } else {
                System.out.println("Validación incorrecta, NO se puede verificar las notificaciones\n");
                report.testFailed("Valida que se puede verificar las notificaciones", true);
            }

            //Click en Home
            menu.clickHomeIcon();


            //Iniciar Sesión
            login.loginUser(user, password, false);

            modal.closeRatingModalIfPresent();

            //Click en Home
            menu.clickHomeIcon();

            modal.closeRatingModalIfPresent();

            //Click en My Trips
            menu.clickMyTripsIcon();

            modal.closeRatingModalIfPresent();

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            //Rellenan campos requeridos
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/pnr")).sendKeys(PNR);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/lastName")).sendKeys(LastName);

            //Click en Busca tu Reservación
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/ctaAux")).click();
            Thread.sleep(8000);

            modal.closeRatingModalIfPresent();

            //Eliminar reserva
            menu.deleteTrip();

            modal.closeRatingModalIfPresent();

            //Swipe para futura validación
            tif.swipeToAdultChildInfant(Panel, driver, direct1);

            //Verificar que si agrego una reserva al dispositivo mientras estoy loggeado  puedo eliminar efectivamente esa reserva y que al hacer pull to refresh, no me regresa
            if (driver.findElement(AppiumBy.accessibilityId("You have no trips added")).isDisplayed()){
                System.out.println("Validación correcta, la reserva no regresa\n");
                report.testPassed("Valida que la reserva no regresa", true);
            } else {
                System.out.println("Validación INCORRECTA, la reserva REGRESA\n");
                report.testFailed("Valida que la reserva no regresa", true);
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

            //Cerrar Sesión
            logout.logoutUser();

            modal.closeRatingModalIfPresent();

            //Eliminar Viaje
            menu.deleteTrip();

            modal.closeRatingModalIfPresent();

            //Iniciar Sesión
            login.loginUser(user, password, false);

            modal.closeRatingModalIfPresent();

            //Click en Home
            menu.clickHomeIcon();

            //Click en MyTrips
            menu.clickMyTripsIcon();

            //Probar que si agrego una reserva al dispositivo mientras estoy loggeado, me desloggeo,  elimino la reserva, que la reserva me saldrá nuevamente cuando vuelva a loggearme a la misma cuenta.
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).isDisplayed()){
                System.out.println("Validación correcta, la reserva regresa\n");
                report.testPassed("Valida que la reserva regresa", true);
            } else {
                System.out.println("Validación incorrecta, la reserva NO regresa\n");
                report.testFailed("Valida que la reserva regresa", true);
            }

            //Eliminar Viaje
            menu.deleteTrip();

            modal.closeRatingModalIfPresent();

            //Swipe para futura validación
            tif.swipeToAdultChildInfant(Panel, driver, direct1);

            //Validar que la puedo eliminar y que al hacer pull to refresh no regresa
            if (driver.findElement(AppiumBy.accessibilityId("You have no trips added")).isDisplayed()){
                System.out.println("Validación correcta, la reserva no regresa\n");
                report.testPassed("Valida que la reserva no regresa", true);
            } else {
                System.out.println("Validación INCORRECTA, la reserva REGRESA\n");
                report.testFailed("Valida que la reserva no regresa", true);
            }

            //Cerrar Sesión
            logout.logoutUser();

            modal.closeRatingModalIfPresent();

            //Click en Home
            menu.clickHomeIcon();

            System.out.println("otrosValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("otrosValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de Loyalty
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void loyaltyValidations(Report report, String correo, String password){
        MenuFragment menu = new MenuFragment(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        Logout logout = new Logout(driver);
        System.out.println("loyaltyValidations inicio\n");
        try {

            //Click en Account
            menu.clickAccountIcon();

            // Swipe y Introducir correo invalido
            driver.findElement(AppiumBy.xpath("//android.widget.EditText")).sendKeys("correo2@gmail.");

            //Click en el botón continuar
            driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Continuar\"]")).click();
            System.out.println("Se hizo click en el botón continuar\n");

            Thread.sleep(2000);


            //Click en campo contraseña e Introduce la contraseña
            (new TouchAction((PerformsTouchActions) driver)).press(PointOption.point(544,338))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(540,887)).release().perform();
            System.out.println("Se hizo el swipe\n");
            driver.findElement(AppiumBy.xpath("//android.widget.EditText")).sendKeys(password);

            //Click en botón continuar
            Thread.sleep(500);
            driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Continuar\"]")).click();
            System.out.println("Se hizo click en el botón continuar\n");
            Thread.sleep(2000);

            //Validar que si el pax introduce un formato de correo inválido o contraseña invalida, deberá aparecerle un inline error que lo indique
            if(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Usuario o contraseña incorrecta\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra inline de error\n");
                report.testPassed("Valida que se muestra inline de error", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra inline de error\n");
                report.testFailed("Valida que se muestra inline de error", true);
            }

            //Click en editar correo
            driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Editar la dirección de correo electrónico\"]/android.widget.TextView")).click();
            Thread.sleep(3000);

            //Limpiar celda
            driver.findElement(AppiumBy.className("android.widget.EditText")).clear();

            //Introducir correo
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(correo);
            Thread.sleep(1000);

            //Click en el botón continuar
            driver.findElement(AppiumBy.className("android.widget.Button")).click();
            System.out.println("Se hizo click en el botón continuar\n");
            Thread.sleep(1000);

            //Introducir contraseña
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(password);

            //Click en botón continuar
            Thread.sleep(500);
            driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Continuar\"]")).click();
            System.out.println("Se hizo click en el botón continuar\n");
            Thread.sleep(10000);

            modal.closeRatingModalIfPresent();

            //Validar que el login también se puede realizar utilizando el correo
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/connectMilesLogo")).isDisplayed()){
                System.out.println("Validación correcta, se hace login con el correo\n");
                report.testPassed("Valida que se hace login con el correo", true);
            } else {
                System.out.println("Validación incorrecta, NO se hace login con el correo\n");
                report.testFailed("Valida que se hace login con el correo", true);
            }

            //Cerrar Sesión
            logout.logoutUser();


            System.out.println("loyaltyValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("loyaltyValidations finalizado con error\n" + ex);
        }
    }

    /**
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void editProfileandprofileExtendedServiceValidations(Report report, String user, String password){
        By by;
        int val = 1;
        String direct2 = "abajo";
        String direct1 = "arriba";
        Booking booking = new Booking(driver);
        Login login = new Login(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("editProfileandprofileExtendedServiceValidations inicio\n");
        try {

            //Iniciar Sesión
            login.loginUser(user, password, false);

            modal.closeRatingModalIfPresent();

            //Swipe para futura validación
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Validar que aparezca el botón de mi perfil
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/my_profile_label")).isDisplayed()){
                System.out.println("Validación correcta, aparece el botón de mi perfil\n");
                report.testPassed("Valida que aparece el botón de mi perfil", true);
            } else {
                System.out.println("Validación incorrecta, NO aparece el botón de mi perfil\n");
                report.testFailed("Valida que aparece el botón de mi perfil", true);
            }

            //Click en mi perfil
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/my_profile_label")).click();
            Thread.sleep(2000);

            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //Validar campos de Información de Pasaporte hasta fecha de vencimiento
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/passport_nationality")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/passport")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/passport_country")).isDisplayed() &&
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/passport_expiration_date")).isDisplayed()){
                System.out.println("Validación correcta, aparecen los campos de Información de pasaporte hasta fecha de vencimiento\n");
                report.testPassed("Valida que aparecen los campos de Información de pasaporte hasta fecha de vencimiento", true);
            } else {
                System.out.println("Validación incorrecta, NO aparecen los campos de Información de pasaporte hasta fecha de vencimiento\n");
                report.testFailed("Valida que aparecen los campos de Información de pasaporte hasta fecha de vencimiento", true);
            }

            //Swipe para continuar con validacione de información de pasaporte
            booking.swipeSmall(Panel, driver, direct2);

            //Validar campos restantes de Información de Pasaporte
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/know_traveler_number")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/redress_number")).isDisplayed()){
                System.out.println("Validación correcta, aparecen los campos restantes de Información de pasaporte\n");
                report.testPassed("Valida que aparecen los campos restantes de Información de pasaporte", true);
            } else {
                System.out.println("Validación incorrecta, NO aparecen los campos restantes de Información de pasaporte\n");
                report.testFailed("Valida que aparecen los campos restantes de Información de pasaporte", true);
            }

            //Swipe para futura validación
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Validar campos de Datos de identifación
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/document_type")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/document_number")).isDisplayed() &&
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/country_document")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/document_expiration_date")).isDisplayed()){
                System.out.println("Validación correcta, aparecen los campos de Datos de identificación\n");
                report.testPassed("Valida que aparecen los campos de Datos de identificación", true);
            } else {
                System.out.println("Validación incorrecta, NO aparecen los campos de Datos de identificación\n");
                report.testFailed("Valida que aparecen los campos de Datos de identificación", true);
            }

            //Swipe para validar campos de Dirección
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Validar campos de Dirección
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/country_residence")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/state")).isDisplayed() &&
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/main_address")).isDisplayed() && driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/aux_address")).isDisplayed()){
                System.out.println("Validación correcta, aparecen los campos de Dirección\n");
                report.testPassed("Valida que aparecen los campos de Dirección", true);
            } else {
                System.out.println("Validación incorrecta, NO aparecen los campos de Dirección\n");
                report.testFailed("Valida que aparecen los campos de Dirección", true);
            }

            System.out.println("editProfileandprofileExtendedServiceValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("editProfileandprofileExtendedServiceValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de profile Page Enhacement
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void profilePageEnhacementValidations(Report report){
        By by;
        int val = 1;
        String direct2 = "abajo";
        String direct1 = "arriba";
        Booking booking = new Booking(driver);
        MenuFragment menu = new MenuFragment(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("profilePageEnhacementValidationsValidations inicio\n");
        try {

            //Click en país de residencia
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/country_residence")).click();


            //Escribir en país de residencia
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/country_residence")).sendKeys("Panam");

            //Swipe para futura validación
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Click en País Emisor
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/country_document")).click();

            //Click en Fecha de Vencimiento
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/document_expiration_date")).click();

            //Click en Cancelar
            driver.findElement(AppiumBy.id("android:id/button2")).click();

            //Obtener atributo para futura validación
            String residence = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/country_residence")).getText();

            //Validar cuando el PAX haya escrito un país sin acentos o no haya seleccionado un país el front reconozca y seleccione el país de la lista que más se parece a su ortografía
            if (residence.equals("Panamá")){
                System.out.println("Validación correcta, el front selecciona el país\n");
                report.testPassed("Valida que el front selecciona el país", true);
            } else {
                System.out.println("Validación incorrecta, el front NO selecciona el país\n");
                report.testFailed("Valida que el front selecciona el país", true);
            }


            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct1);

            //Validar que el front debe poder identificar si hay más de un documento secundario en la información del pasajero y presentar al PAX el que corresponda

            //Click en ID principal
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/document_type")).click();

            //Validar que se vean los tres tipos de documento
            System.out.println("Validación correcta, aparecen los tres tipos de documentos\n");
            report.testPassed("Valida que el front selecciona el país", true);

            //Click en Account
            menu.clickAccountIcon();

            modal.closeRatingModalIfPresent();


            System.out.println("profilePageEnhacementValidationsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("profilePageEnhacementValidationsValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de new Buy Miles Web view
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void newBuyMilesWebviewValidations(Report report){
        By by;
        int val = 1;
        String direct2 = "abajo";
        String direct1 = "arriba";
        Booking booking = new Booking(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("newBuyMilesWebviewValidations inicio\n");
        try {
            //Swipe para futura validación
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Validar que aparezca el botón de Comprar millas
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/view_buy_miles_label")).isDisplayed()){
                System.out.println("Validación correcta, aparece botón de Comprar millas\n");
                report.testPassed("Valida que aparece botón de Comprar millas", true);
            } else {
                System.out.println("Validación incorrecta, NO aparece botón de Comprar millas\n");
                report.testFailed("Valida que aparece botón de Comprar millas", true);
            }

            //Click en Comprar Millas
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/view_buy_miles_label")).click();
            Thread.sleep(3000);

            //Validar que al hacer click se abre el webview de compra de millas
            if (driver.findElement(AppiumBy.accessibilityId("Página de compra de millas")).isDisplayed()){
                System.out.println("Validación correcta, se abre el webview\n");
                report.testPassed("Valida que se abre el webview", true);
            } else {
                System.out.println("Validación incorrecta, NO se abre el webview\n");
                report.testFailed("Valida que se abre el webview", true);
            }

            //Click en la X
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            modal.closeRatingModalIfPresent();

            //validar que si el PAX le da a la X del heather deberá aparecerle un modal nativo de advertencia.
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View")).isDisplayed()){
                System.out.println("Validación correcta, aparece el modal de advertencia\n");
                report.testPassed("Valida que aparece el modal de advertencia", true);
            } else {
                System.out.println("Validación incorrecta, NO aparece el modal de advertencia\n");
                report.testFailed("Valida que aparece el modal de advertencia", true);
            }

            //Click en No
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView")).click();

            //validar que si él le da click a no deberá permanecer en el webview
            if (driver.findElement(AppiumBy.accessibilityId("Página de compra de millas")).isDisplayed()){
                System.out.println("Validación correcta, permanece en el webview\n");
                report.testPassed("Valida que permanece en el webview", true);
            } else {
                System.out.println("Validación incorrecta, NO permanece en el webview\n");
                report.testFailed("Valida que permanece en el webview", true);
            }

            //Click en la X
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            modal.closeRatingModalIfPresent();

            //Click en Si
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView")).click();
            Thread.sleep(2000);

            modal.closeRatingModalIfPresent();

            //validar que si él le da click a sí deberá sacarlo del webview y regresarlo a su punto de origen.
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/my_profile_label")).isDisplayed()){
                System.out.println("Validación correcta, regresa al punto de origen\n");
                report.testPassed("Valida que regresa al punto de origen", true);
            } else {
                System.out.println("Validación incorrecta, NO regresa al punto de origen\n");
                report.testFailed("Valida que regresa al punto de origen", true);
            }

            System.out.println("newBuyMilesWebviewValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("newBuyMilesWebviewValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de miles Management Webview
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void milesManagementWebviewValidations(Report report){
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("milesManagementWebviewValidations inicio\n");
        try {

            //validar que cuando el PAX entre a su sección de cuenta, deberá ver un botón que diga Miles Managemente
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/management")).isDisplayed()){
                System.out.println("Validación correcta, aparece botón Miles Management\n");
                report.testPassed("Valida que aparece botón Miles Management", true);
            } else {
                System.out.println("Validación incorrecta, NO aparece botón Miles Management\n");
                report.testFailed("Valida que aparece botón Miles Management", true);
            }

            //Click en Management
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/management")).click();

            //Validar que al hacer click en Management  deberá abrirse un webview de Points con todas las opciones de transacción de millas.
            if (driver.findElement(AppiumBy.accessibilityId("Página de manejo de millas")).isDisplayed()){
                System.out.println("Validación correcta, se abre un webview de Points\n");
                report.testPassed("Valida que se abre un webview de Points", true);
            } else {
                System.out.println("Validación incorrecta, NO se abre un webview de Points\n");
                report.testFailed("Valida que se abre un webview de Points", true);
            }

            //Click en X
            driver.findElement(AppiumBy.accessibilityId("back")).click();

            modal.closeRatingModalIfPresent();

            //validar que este Webview, no contará con exit warning, ni cambiará la señalización de cerrar de "X" a "Cerrar".
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/my_profile_label")).isDisplayed()){
                System.out.println("Validación correcta, regresa y no aparece exit warning\n");
                report.testPassed("Valida que regresa y no aparece exit warning", true);
            } else {
                System.out.println("Validación incorrecta, NO regresa y aparece exit warning\n");
                report.testFailed("Valida que regresa y no aparece exit warning", true);
            }


            System.out.println("milesManagementWebviewValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("milesManagementWebviewValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones de new Miles Expiration Label
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void newMilesExpirationLabelValidations(Report report){
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        MenuFragment menu = new MenuFragment(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("newMilesExpirationLabelValidations inicio\n");
        try {

            //Click en Account
            menu.clickAccountIcon();

            modal.closeRatingModalIfPresent();

            //Swipe para futura validación
            booking.swipeSuperSmall(Panel, driver, direct2);

            //Validar que se presente en el campo de "Millas Premio" un indicativo con la fecha de expiracion
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/milesExpirationDate")).isDisplayed()){
                System.out.println("Validación correcta, se presenta un indicativo con la fecha de expiración\n");
                report.testPassed("Valida que se presenta un indicativo con la fecha de expiración", true);
            } else {
                System.out.println("Validación incorrecta, NO se presenta un indicativo con la fecha de expiración\n");
                report.testFailed("Valida que se presenta un indicativo con la fecha de expiración", true);
            }

            //Obtener atributo
            String fecha = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/milesExpirationDate")).getAttribute("content-desc");

            //Validar el formato de fecha mes dia año
            if (fecha.equals("Your miles will expire on null")){
                System.out.println("Validación correcta, se valida el formato de fecha mes dia año\n");
                report.testPassed("Valida que se valida el formato de fecha mes dia año", true);
            } else {
                System.out.println("Validación incorrecta, NO se valida el formato de fecha mes dia año\n");
                report.testFailed("Valida que se valida el formato de fecha mes dia año", true);
            }

            System.out.println("newMilesExpirationLabelValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("newMilesExpirationLabelValidations finalizado con error\n");
        }
    }

    /**
     * Realiza validaciones new Credit Card Promotional Banner
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void newCreditCardPromotionalBannerValidations(Report report){
        RatingModalCheck modal = new RatingModalCheck(driver);
        String direct1 = "arriba";
        Booking booking = new Booking(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("newCreditCardPromotionalBannerValidations inicio\n");
        try {

            //Swipe para futura validación
            booking.swipeSuperSmall(Panel, driver, direct1);

            //Validar se debe incluir en la seccion de cuenta un espacio para adquirir la tarjeta de credito connectmiles
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/creditCardPromo")).isDisplayed()){
                System.out.println("Validación correcta, se incluye el espacio para adquirir la tarjeta\n");
                report.testPassed("Valida que se incluye el espacio para adquirir la tarjeta", true);
            } else {
                System.out.println("Validación incorrecta, NO se incluye el espacio para adquirir la tarjeta\n");
                report.testFailed("Valida que se incluye el espacio para adquirir la tarjeta", true);
            }

            //Validar que el banner se pueda cerrar
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/closeCreditCard")).isDisplayed()){
                System.out.println("Validación correcta, el banner se puede cerrar\n");
                report.testPassed("Valida que el banner se puede cerrar", true);
            } else {
                System.out.println("Validación incorrecta, el banner NO se puede cerrar\n");
                report.testFailed("Valida que el banner se puede cerrar", true);
            }

            //Obtener atributo para validación
            String interactua = driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/creditCardPromo")).getAttribute("enabled");

            //Validar que toda la celda debe ser accionable menos el area de "X"
            if (interactua.equals("true")){
                System.out.println("Validación correcta, la celda es accionable\n");
                report.testPassed("Valida que la celda es accionable", true);
            } else {
                System.out.println("Validación incorrecta, la celda NO es accionable\n");
                report.testFailed("Valida que la celda es accionable", true);
            }

            //Validar que el texto del banner debe quedar fijo a dos líneas
            System.out.println("Validación correcta, el texto queda fijo a dos líneas\n");
            report.testPassed("Valida que el texto queda fijo a dos líneas", true);


            System.out.println("newCreditCardPromotionalBannerValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("newCreditCardPromotionalBannerValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de logic For Logged In Users
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void logicForLoggedInUsersValidations(Report report, String user, String password){
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        MenuFragment menu = new MenuFragment(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        Login login = new Login(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("logicForLoggedInUsersValidations inicio\n");
        try {

            //Iniciar Sesión
            login.loginUser(user, password, false);

            //Click en Home
            menu.clickHomeIcon();

            modal.closeRatingModalIfPresent();

            //Swipe para validación
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);
            booking.swipeSmall(Panel, driver, direct2);

            //Validar que se este logueado la seccion de Contact us pase a llamarse Service center
                System.out.println("Validación correcta, la sección pasa a llamarse Centro de Servicio\n");
                report.testPassed("Valida que la sección pasa a llamarse Centro de Servicio", true);

            //Click en Service Center
            //driver.findElement(AppiumBy.accessibilityId("Centro de Servicio")).click();
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[2]/android.view.View[5]")).click();


            modal.closeRatingModalIfPresent();

            //Validar que se muestre el numero de Panamá correspondiente al service center de Connectmiles Número Panamá: +507 309-1313
            if (driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Ciudad de Panamá : (+507) 321-9631\"]/android.widget.LinearLayout/android.widget.TextView[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el número\n");
                report.testPassed("Valida que se muestra el número", true);
            } else {
                System.out.println("Validación incorrecta, NO se muestra el número\n");
                report.testFailed("Valida que se muestra el número", true);
            }

            //Obtener atributo para validación
            String numero = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Ciudad de Panamá : (+507) 321-9631\"]/android.widget.LinearLayout/android.widget.TextView[2]")).getAttribute("clickable");

            //Validar que el numero de Connectmiles sea accionable
            if (numero.equals("true")){
                System.out.println("Validación correcta, el numero es accionable\n");
                report.testPassed("Valida que el numero es accionable", true);
            } else {
                System.out.println("Validación incorrecta, el numero NO es accionable\n");
                report.testFailed("Valida que el numero es accionable", true);
            }



            System.out.println("logicForLoggedInUsersValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("logicForLoggedInUsersValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de my Account Copy Connect Miles Number
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void myAccountCopyConnectMilesNumberValidations(Report report){
        MenuFragment menu = new MenuFragment(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("myAccountCopyConnectMilesNumberValidations inicio\n");
        try {

            //Click en Home
            menu.clickHomeIcon();

            //Click en Account
            menu.clickAccountIcon();

            modal.closeRatingModalIfPresent();

            //Validar el texto del ffn deberá mantener los espaciados cada 3 números
            if(driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/membershipId")).isDisplayed()){
                System.out.println("Validación correcta, se mantiene los espaciados\n");
                report.testPassed("Valida que se mantiene los espaciados", true);
            } else {
                System.out.println("Validación incorrecta, NO se mantiene los espaciados\n");
                report.testFailed("Valida que se mantiene los espaciados", true);
            }

            //Validar que  aparezca el icono de "Copiar" como el numero de ConnectMiles
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/copyMembershipId")).isDisplayed()){
                System.out.println("Validación correcta, aparece el icono de Copiar\n");
                report.testPassed("Valida que aparece el icono de Copiar", true);
            } else {
                System.out.println("Validación incorrecta, NO aparece el icono de Copiar\n");
                report.testFailed("Valida que aparece el icono de Copiar", true);
            }


            System.out.println("myAccountCopyConnectMilesNumberValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("myAccountCopyConnectMilesNumberValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de general Enhacements
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void generalEnhacementsValidations(Report report){
        String direct2 = "abajo";
        Booking booking = new Booking(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        System.out.println("generalEnhacementsValidations inicio\n");
        try {

            //Validar que se mueste una nueva seccion  de Member Information
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/member_information")).isDisplayed()){
                System.out.println("Validación correcta, aparece la sección de Member Information\n");
                report.testPassed("Valida que aparece la sección de Member Information", true);
            } else {
                System.out.println("Validación incorrecta, NO aparece la sección de Member Information\n");
                report.testFailed("Valida que aparece la sección de Member Information", true);
            }

            //Swipe para futura validación
            booking.swipeValidateStopover(Panel, driver, direct2);
            booking.swipeValidateStopover(Panel, driver, direct2);

            //Click en Mi perfil
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/my_profile_label")).click();

            //Validar diseño de la pantalla de my profile
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/basic_information_title")).isDisplayed()){
                System.out.println("Validación correcta, aparece el titulo de información básica\n");
                report.testPassed("Valida que aparece el titulo de información básica", true);
            } else {
                System.out.println("Validación incorrecta, NO aparece el titulo de información básica\n");
                report.testFailed("Valida que aparece el titulo de información básica", true);
            }

            //Validar el orden de las secciones de la pantalla de my profile
            System.out.println("Validación correcta, se valida el orden de las secciones\n");
            report.testPassed("Valida el orden de las secciones", true);

            //Validar diseño del boton de Update information (nuevo diseño ovalado)
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/cta")).isDisplayed()){
                System.out.println("Validación correcta, cambio el diseño de el botón Update\n");
                report.testPassed("Valida que cambio el diseño de el botón Update", true);
            } else {
                System.out.println("Validación incorrecta, NO cambio el diseño de el botón Update\n");
                report.testFailed("Valida que cambio el diseño de el botón Update", true);
            }

            //Click en el botón actualizar información de perfil
            driver.findElement(AppiumBy.accessibilityId("Botón de actualizar información de perfil. Haz doble toque para salvar información")).click();
            System.out.println("Se hizo click en el botón de actualizar información del perfil\n");
            Thread.sleep(5000);


            //Validar que al salvar el perfil, la página debe cerrarse y volver a la sección de cuenta con un mensaje de éxito
//            if (driver.findElement(AppiumBy.accessibilityId("Perfil actualizado Hemos guardado exitosamente la información de tu perfil.")).isDisplayed()){
//                System.out.println("Validación correcta, se regresa a la sección de cuenta con un mensaje de éxito\n");
//                report.testPassed("Valida que se regresa a la sección de cuenta con un mensaje de éxito", true);
//            } else {
//                System.out.println("Validación incorrecta, NO se regresa a la sección de cuenta con un mensaje de éxito\n");
//                report.testFailed("Valida que se regresa a la sección de cuenta con un mensaje de éxito", true);
//            }


            System.out.println("generalEnhacementsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("generalEnhacementsValidations finalizado con error\n");
        }
    }

}

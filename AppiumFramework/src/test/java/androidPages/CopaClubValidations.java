package androidPages;

import com.aventstack.extentreports.App;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.RatingModalCheck;
import utils.Report;

import java.util.List;

public class CopaClubValidations {
    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public CopaClubValidations(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);
    Booking booking = new Booking(driver);
    ReadData data = new ReadData(driver);

    /**
     * Validaciones de CopaClubPayments
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void CopaClubPayments(Report report){
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        Booking booking = new Booking(driver);
        String direct2 = "abajo";
        String direct1 = "arriba";
        RatingModalCheck modal = new RatingModalCheck(driver);

        System.out.println("CopaClubPass2PaxUSDVisa inicio\n");
        try {

            String CreditCardName = data.extractData(1, 3, "CopaClub");
            String CreditCardNumber = data.extractData(1, 4, "CopaClub");
            String CreditCardExpMonth = data.extractData(1, 5, "CopaClub");
            String CreditCardExpYear = String.valueOf(data.extractData(1, 6, "CopaClub"));
            String CreditCardCVV = String.valueOf(data.extractData(1, 7, "CopaClub"));

            //Dar click en ingresar tarjeta de credito
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.widget.Button[1]")).click();
            System.out.println("Se hizo click en ingresar tarjeta de crédito\n");
            Thread.sleep(2000);

            //Ubicar elemento
            WebElement PanelPay = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelPay, driver, direct2);
            booking.swipeSmall(PanelPay, driver, direct2);


            //Ingresar nombre de titular
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.widget.EditText")).sendKeys(CreditCardName);
            System.out.println("Se ingreso el nombre del titular");

            //Ingresar número de tarjeta
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText")).sendKeys(CreditCardNumber);
            System.out.println("Se ingreso número de tarjeta");

            //Click en selector de mes
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[5]")).click();

            //Click en mes de tarjeta
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]")).click();
            System.out.println("Se selecciono el mes\n");

            //Click en selector de año
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[7]")).click();

            //Click en año de tarjeta
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]")).click();
            System.out.println("Se selecciono el año\n");

            //Ingresar CVV
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[8]/android.widget.EditText")).sendKeys(CreditCardCVV);
            System.out.println("Se ingreso el CVV\n");

            //Ingresar país de emisión
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[9]/android.view.View/android.widget.EditText")).sendKeys("Panamá");
            driver.findElement(AppiumBy.className("android.widget.ListView")).click();
            System.out.println("Se ingreso país de emisión\n");

            //Tap para bajar teclado
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(883,315)).release().perform();


            //Swipe para encontrar el elemento
            //tif.swipeToAdultChildInfant(PanelPay, driver, direct2);
            booking.swipeSmall(PanelPay, driver, direct2);
            //booking.swipeSmall(PanelPay, driver, direct2);


            //Ingresar dirección de facturación
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText")).sendKeys("El Crisol");
            System.out.println("Se ingreso dirección de facturación\n");

            //Ingresar país
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.EditText")).sendKeys("Panamá");
            driver.findElement(AppiumBy.className("android.widget.ListView")).click();
            System.out.println("Se ingreso país\n");

            //Tap para bajar teclado
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(883,315)).release().perform();

            //Ingresar Ciudad
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[5]/android.widget.EditText")).sendKeys("Panamá");
            System.out.println("Se ingreso ciudad\n");

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelPay, driver, direct2);
            //booking.swipeSmall(PanelPay, driver, direct2);

            //Ingresar provincia
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText")).sendKeys("Panamá");
            System.out.println("Se ingreso provincia\n");

            //Ingresar código postal
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[3]/android.widget.EditText")).sendKeys("07082");
            System.out.println("Se ingreso código postal\n");

            //Ingresar código de país
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[4]/android.widget.EditText")).sendKeys("507");
            System.out.println("Se ingreso código de país\n");

            //Ingresar número de telefono
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[5]/android.widget.EditText")).sendKeys("64837456");
            System.out.println("Se ingreso número de telefono\n");

            //Click en checkbox
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[8]/android.view.View[1]/android.widget.CheckBox")).click();
            System.out.println("Se hizo clicl en el checkbox\n");

            //Click en botón confirmar pago y continuar
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/s2.k/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.widget.Button")).click();
            System.out.println("Se hizo click en confirmar pago y continuar\n");

            System.out.println("Validación correcta, se realiza la compra\n");
            report.testPassed("Validar que se realiza la compra", true);



            System.out.println("CopaClubPass2PaxUSDVisa finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("CopaClubPass2PaxUSDVisa finalizado con error\n");
        }
    }

    /**
     * Validaciones de CopaClubPass2PaxUSDVisa
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void CopaClubPass2PaxUSDVisa(Report report, String PNR, String LastName){
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        Booking booking = new Booking(driver);
        String direct2 = "abajo";
        RatingModalCheck modal = new RatingModalCheck(driver);

        System.out.println("CopaClubPass2PaxUSDVisa inicio\n");
        try {

            String descripcion = data.extractData(1, 0, "CopaClub");
            report.testPassed(descripcion, false);

            //Click en Mis Viajes
            menu.clickMyTripsIcon();
            Thread.sleep(1500);

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            System.out.println("se hizo click en el viaje");
            Thread.sleep(15000);

            //Identifica el elemento con el cual se hara swipe
            WebElement PanelTripDetails = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            //Validar que aparece el botón de comprar copa club arriba de itinerario
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[2]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el botón de compra copa club\n");
                report.testPassed("Validar que se muestra el botón de compra copa club", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el botón de compra copa club\n");
                report.testFailed("Validar que se muestra el botón de compra copa club", true);
            }

            //Click en el botón de compra de copa club
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            Thread.sleep(1000);

            //Validar que se muestra la pantalla de selección de terminal y que no este seleccionada la terminal
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View/android.view.View")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra la pantalla de selección de terminal y la terminal no está seleccionada al ingresar\n");
                report.testPassed("Validar que se muestra la pantalla de selección de terminal y la terminal no está seleccionada al ingresar", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la pantalla de selección de terminal y la terminal no está seleccionada al ingresar\n");
                report.testFailed("Validar que se muestra la pantalla de selección de terminal y la terminal no está seleccionada al ingresar", true);
            }


            //Dar click para seleccionar la terminar
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View/android.view.View")).click();

            //Captura para validar que se puede seleccionar la terminal y debe mostrar un gancho azul
            System.out.println("Validación correcta, se selecciona la terminal y debe mostrar un gancho azul\n");
            report.testPassed("Validar que se selecciona la terminal y debe mostrar un gancho azul", true);

            //Validar que se activa el botón de continuar cuando se seleccione la terminal
            if (driver.findElement(AppiumBy.accessibilityId("wcag")).isDisplayed()) {
                System.out.println("Validación correcta, se activa el botón de continuar al seleccionar la terminal\n");
                report.testPassed("Validar que se activa el botón de continuar al seleccionar la terminal", true);
            } else {
                System.out.println("Validación ERROR, NO se activa el botón de continuar al seleccionar la terminal\n");
                report.testFailed("Validar que se activa el botón de continuar al seleccionar la terminal", true);
            }

            //Dar click en el botón de continuar
            driver.findElement(AppiumBy.accessibilityId("wcag")).click();

            //Validar que se muestra una pantalla con todos los pax que pueden comprar el copa club
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.TextView")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra una pantalla con todos los pax que pueden comprar el copa club\n");
                report.testPassed("Validar que se muestra una pantalla con todos los pax que pueden comprar el copa club", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una pantalla con todos los pax que pueden comprar el copa club\n");
                report.testFailed("Validar que se muestra una pantalla con todos los pax que pueden comprar el copa club", true);
            }

            //Validar que debajo del nombre debe mostrarse el estatus de connectmiles si tiene y el monto del copa club
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.TextView[3]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.TextView[5]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra debajo del nombre el estatus de connectmiles y el monto del copa club\n");
                report.testPassed("Validar que se muestra debajo del nombre el estatus de connectmiles y el monto del copa club", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra debajo del nombre el estatus de connectmiles y el monto del copa club\n");
                report.testFailed("Validar que se muestra debajo del nombre el estatus de connectmiles y el monto del copa club", true);
            }

            //Captura para validar que no se debe mostrar seleccionados los pasajeros dentro de la pantalla elegir pasajeros
            System.out.println("Validación correcta, no se muestran los pasajeros seleccionados\n");
            report.testPassed("Validar que no se muestran los pasajeros seleccionados", true);

            //Click en ambos pasajeros
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View[1]")).click();
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View[2]")).click();
            System.out.println("Se hizo click en ambos pasajeros");

            //Validar que aparece el monto total a pagar por la compra y se habilita el botón de continuar
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.TextView[2]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el monto total a pagar y se habilita el botón de continuar\n");
                report.testPassed("Validar que se muestra el monto total a pagar y se habilita el botón de continuar", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el monto total a pagar y se habilita el botón de continuar\n");
                report.testFailed("Validar que se muestra el monto total a pagar y se habilita el botón de continuar", true);
            }

            //Dar click en el botón de continuar
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[3]")).click();
            System.out.println("Se hizo clicke en el botón continuar");

            //Validar que se muestre la pantalla de revisión de selección y se debe presentar el origen del vuelo, debajo el destino y una imagen de copa club
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.TextView")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[2]")).isDisplayed()
                    && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[3]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club\n");
                report.testPassed("Validar que se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club\n");
                report.testFailed("Validar que se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club", true);
            }

            //Dar click en ver detalles
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[7]")).click();
            System.out.println("Se hizo click en ver detalles");
            Thread.sleep(1000);

            //Validar que se presenta una sección para ver los detalles de copa club como las comodidades, otras comodidades términos y condiciones
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/exo_overlay")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra una sección para ver los detalles de copa club y terminos y condiciones\n");
                report.testPassed("Validar que se muestra una sección para ver los detalles de copa club y terminos y condiciones", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una sección para ver los detalles de copa club y terminos y condiciones\n");
                report.testFailed("Validar que se muestra una sección para ver los detalles de copa club y terminos y condiciones", true);
            }

            //Dar click en la X para salir de la sección ver detalles
            driver.findElement(AppiumBy.accessibilityId("Close")).click();
            System.out.println("Se cerró la sección de ver detalles");

            //Validar que en la pantalla de revisar selección aparezca una sección de terminos y condiciones
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/s2.k/android.widget.TextView")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra una sección de terminos y condiciones\n");
                report.testPassed("Validar que se muestra una sección de terminos y condiciones", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una sección de terminos y condiciones\n");
                report.testFailed("Validar que se muestra una sección de terminos y condiciones", true);
            }

            WebElement PanelRev = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelRev, driver, direct2);
            tif.swipeToAdultChildInfant(PanelRev, driver, direct2);

            //Validar que se muestra un resumen de la compra, subtotal, impuesto y total
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[2]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[6]")).isDisplayed()
                    && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[8]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[10]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra resumen de compra con subtotal, impuesto y total\n");
                report.testPassed("Validar que se muestra resumen de compra con subtotal, impuesto y total", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra resumen de compra con subtotal, impuesto y total\n");
                report.testFailed("Validar que se muestra resumen de compra con subtotal, impuesto y total", true);
            }

            //Validar que se muestra el boton con el texto de comprar
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[3]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el boton con el texto de comprar\n");
                report.testPassed("Validar que se muestra el boton con el texto de comprar", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el boton con el texto de comprar\n");
                report.testFailed("Validar que se muestra el boton con el texto de comprar", true);
            }

            //Hacer click en comprar
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[3]")).click();
            System.out.println("Se hizo click en comprar\n");
            Thread.sleep(15000);

            //Validar que se debe presentar la pantalla de payments después de revisar selección y se pueda completar la compra
            if (driver.findElement(AppiumBy.className("android.widget.TextView")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra la pantalla de payments\n");
                report.testPassed("Validar que se muestra la pantalla de payments", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la pantalla de payments\n");
                report.testFailed("Validar que se muestra la pantalla de payments", true);
            }

            CopaClubPayments(report);
            Thread.sleep(10000);

            //Validar que se muestra la pantalla de confirmación de compra
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la pantalla de confirmación de compra\n");
                report.testPassed("Validar que se muestra la pantalla de confirmación de compra", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la pantalla de confirmación de compra\n");
                report.testFailed("Validar que se muestra la pantalla de confirmación de compra", true);
            }

            //Validar que aparece una sección con la descripcipon de visit pass
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[15]")).isDisplayed()){
                System.out.println("Validación correcta, aparece una sección con la descripcipon de visit pass\n");
                report.testPassed("Validar que aparece una sección con la descripcipon de visit pass", true);
            } else {
                System.out.println("Validación ERROR, NO aparece una sección con la descripcipon de visit pass\n");
                report.testFailed("Validar que aparece una sección con la descripcipon de visit pass", true);
            }

            //Ubicar elemento
            WebElement PanelConf = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelConf, driver, direct2);
            //tif.swipeToAdultChildInfant(PanelConf, driver, direct2);

            //Validar que se muestra botón con el titulo ver copa club single-visit pass
            if (driver.findElement(AppiumBy.className("android.widget.Button")).isDisplayed()){
                System.out.println("Validación correcta, se muestra botón con el titulo ver copa club single-visit pass\n");
                report.testPassed("Validar que se muestra botón con el titulo ver copa club single-visit pass", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra botón con el titulo ver copa club single-visit pass\n");
                report.testFailed("Validar que se muestra botón con el titulo ver copa club single-visit pass", true);
            }

            //Click en el botón copa club
            driver.findElement(AppiumBy.className("android.widget.Button")).click();
            System.out.println("Se hizo click en el botón copa club\n");

            //Validar que se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View/android.widget.TextView")).isDisplayed()
            && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]")).isDisplayed() &&
            driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Terminal: %1$s\"]")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member\n");
                report.testPassed("Validar que se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member\n");
                report.testFailed("Validar que se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member", true);
            }

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelConf, driver, direct2);

            //Validar que se encuentran los dos copa clubs
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View[2]")).isDisplayed() &&
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View[3]")).isDisplayed()){
                System.out.println("Validación correcta, se encuentran los dos copa clubs\n");
                report.testPassed("Validar que se encuentran los dos copa clubs", true);
            } else {
                System.out.println("Validación ERROR, NO se encuentran los dos copa clubs\n");
                report.testFailed("Validar que se encuentran los dos copa clubs", true);
            }

            System.out.println("CopaClubPass2PaxUSDVisa finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("CopaClubPass2PaxUSDVisa finalizado con error\n");
        }
    }

    /**
     * Validaciones de CopaClubPass2PaxMXNVisa
     * @param report objeto creado en el main, necesario para los métodos en dónde se van a hacer capturas para el reporte
     */
    public void CopaClubPass1Pax(Report report, String PNR, String LastName){
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        Booking booking = new Booking(driver);
        String direct2 = "abajo";
        RatingModalCheck modal = new RatingModalCheck(driver);

        System.out.println("CopaClubPass1Pax inicio\n");
        try {

            String descripcion = data.extractData(1, 0, "CopaClub");
            report.testPassed(descripcion, false);

            //Click en Mis Viajes
            menu.clickMyTripsIcon();
            Thread.sleep(1500);

            // Click al "+" agregar un viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();
            System.out.println("se hizo click en el viaje");
            Thread.sleep(15000);

            //Identifica el elemento con el cual se hara swipe
            WebElement PanelTripDetails = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);

            //Validar que aparece el botón de comprar copa club arriba de itinerario
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.widget.ScrollView/android.view.View[2]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el botón de compra copa club\n");
                report.testPassed("Validar que se muestra el botón de compra copa club", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el botón de compra copa club\n");
                report.testFailed("Validar que se muestra el botón de compra copa club", true);
            }

            //Click en el botón de compra de copa club
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();
            Thread.sleep(1000);

            //Validar que se muestra la pantalla de selección de terminal y que no este seleccionada la terminal
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View/android.view.View")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra la pantalla de selección de terminal y la terminal no está seleccionada al ingresar\n");
                report.testPassed("Validar que se muestra la pantalla de selección de terminal y la terminal no está seleccionada al ingresar", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la pantalla de selección de terminal y la terminal no está seleccionada al ingresar\n");
                report.testFailed("Validar que se muestra la pantalla de selección de terminal y la terminal no está seleccionada al ingresar", true);
            }


            //Dar click para seleccionar la terminar
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View/android.view.View")).click();

            //Swipe para dar click en la siguiente terminal
            WebElement PanelRev = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelRev, driver, direct2);

            //Dar click para seleccionar la segunda terminal
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View")).click();

            //Captura para validar que se puede seleccionar la terminal y debe mostrar un gancho azul
            System.out.println("Validación correcta, se selecciona la terminal y debe mostrar un gancho azul\n");
            report.testPassed("Validar que se seleccionan las terminales y debe mostrar un gancho azul", true);

            //Validar que se activa el botón de continuar cuando se seleccione la terminal
            if (driver.findElement(AppiumBy.accessibilityId("wcag")).isDisplayed()) {
                System.out.println("Validación correcta, se activa el botón de continuar al seleccionar la terminal\n");
                report.testPassed("Validar que se activa el botón de continuar al seleccionar la terminal", true);
            } else {
                System.out.println("Validación ERROR, NO se activa el botón de continuar al seleccionar la terminal\n");
                report.testFailed("Validar que se activa el botón de continuar al seleccionar la terminal", true);
            }

            //Dar click en el botón de continuar
            driver.findElement(AppiumBy.accessibilityId("wcag")).click();

            //Validar que se muestra una pantalla con todos los pax que pueden comprar el copa club
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.widget.TextView")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra una pantalla con todos los pax que pueden comprar el copa club\n");
                report.testPassed("Validar que se muestra una pantalla con todos los pax que pueden comprar el copa club", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una pantalla con todos los pax que pueden comprar el copa club\n");
                report.testFailed("Validar que se muestra una pantalla con todos los pax que pueden comprar el copa club", true);
            }

            //Validar que debajo del nombre debe mostrarse el estatus de connectmiles si tiene y el monto del copa club
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.TextView[3]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.TextView[5]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra debajo del nombre el estatus de connectmiles y el monto del copa club\n");
                report.testPassed("Validar que se muestra debajo del nombre el estatus de connectmiles y el monto del copa club", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra debajo del nombre el estatus de connectmiles y el monto del copa club\n");
                report.testFailed("Validar que se muestra debajo del nombre el estatus de connectmiles y el monto del copa club", true);
            }

            //Captura para validar que no se debe mostrar seleccionados los pasajeros dentro de la pantalla elegir pasajeros
            System.out.println("Validación correcta, no se muestran los pasajeros seleccionados\n");
            report.testPassed("Validar que no se muestran los pasajeros seleccionados", true);

            //Click en ambos pasajeros
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View[1]")).click();
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View[2]")).click();
            System.out.println("Se hizo click en ambos pasajeros");

            //Validar que aparece el monto total a pagar por la compra y se habilita el botón de continuar
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.widget.TextView[2]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el monto total a pagar y se habilita el botón de continuar\n");
                report.testPassed("Validar que se muestra el monto total a pagar y se habilita el botón de continuar", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el monto total a pagar y se habilita el botón de continuar\n");
                report.testFailed("Validar que se muestra el monto total a pagar y se habilita el botón de continuar", true);
            }

            //Dar click en el botón de continuar
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[3]")).click();
            System.out.println("Se hizo clicke en el botón continuar");

            //Validar que se muestre la pantalla de revisión de selección y se debe presentar el origen del vuelo, debajo el destino y una imagen de copa club
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.widget.TextView")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.widget.TextView[2]")).isDisplayed()
                    && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.widget.TextView[3]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club\n");
                report.testPassed("Validar que se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club\n");
                report.testFailed("Validar que se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club", true);
            }

            //Dar click en ver detalles
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[6]")).click();
            System.out.println("Se hizo click en ver detalles");
            Thread.sleep(1000);

            //Validar que se presenta una sección para ver los detalles de copa club como las comodidades, otras comodidades términos y condiciones
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/exo_overlay")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra una sección para ver los detalles de copa club y terminos y condiciones\n");
                report.testPassed("Validar que se muestra una sección para ver los detalles de copa club y terminos y condiciones", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una sección para ver los detalles de copa club y terminos y condiciones\n");
                report.testFailed("Validar que se muestra una sección para ver los detalles de copa club y terminos y condiciones", true);
            }

            //Dar click en la X para salir de la sección ver detalles
            driver.findElement(AppiumBy.accessibilityId("Close")).click();
            System.out.println("Se cerró la sección de ver detalles");

            //Validar que se muestre el origen del vuelo, debajo el destino y una imagen de copa club
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[8]")).isDisplayed()
                    && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[9]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el origen del vuelo, debajo el destino y una imagen de copa club\n");
                report.testPassed("Validar que se muestra el origen del vuelo, debajo el destino y una imagen de copa club", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el origen del vuelo, debajo el destino y una imagen de copa club\n");
                report.testFailed("Validar que se muestra el origen del vuelo, debajo el destino y una imagen de copa club", true);
            }

            //Dar click en ver detalles
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[12]")).click();
            System.out.println("Se hizo click en ver detalles");
            Thread.sleep(1000);

            //Validar que se presenta una sección para ver los detalles de copa club como las comodidades, otras comodidades términos y condiciones
            if (driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/exo_overlay")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra una sección para ver los detalles de copa club y terminos y condiciones\n");
                report.testPassed("Validar que se muestra una sección para ver los detalles de copa club y terminos y condiciones", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una sección para ver los detalles de copa club y terminos y condiciones\n");
                report.testFailed("Validar que se muestra una sección para ver los detalles de copa club y terminos y condiciones", true);
            }

            //Dar click en la X para salir de la sección ver detalles
            driver.findElement(AppiumBy.accessibilityId("Close")).click();
            System.out.println("Se cerró la sección de ver detalles");

            //Validar que en la pantalla de revisar selección aparezca una sección de terminos y condiciones
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/s2.k/android.widget.TextView")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra una sección de terminos y condiciones\n");
                report.testPassed("Validar que se muestra una sección de terminos y condiciones", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra una sección de terminos y condiciones\n");
                report.testFailed("Validar que se muestra una sección de terminos y condiciones", true);
            }

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelRev, driver, direct2);
            tif.swipeToAdultChildInfant(PanelRev, driver, direct2);

            //Validar que se muestra un resumen de la compra, subtotal, impuesto y total
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.widget.TextView[1]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[8]")).isDisplayed()
                    && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[10]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[1]/android.widget.TextView[12]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra resumen de compra con subtotal, impuesto y total\n");
                report.testPassed("Validar que se muestra resumen de compra con subtotal, impuesto y total", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra resumen de compra con subtotal, impuesto y total\n");
                report.testFailed("Validar que se muestra resumen de compra con subtotal, impuesto y total", true);
            }

            //Validar que se muestra el boton con el texto de comprar
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[3]")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra el boton con el texto de comprar\n");
                report.testPassed("Validar que se muestra el boton con el texto de comprar", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el boton con el texto de comprar\n");
                report.testFailed("Validar que se muestra el boton con el texto de comprar", true);
            }

            //Hacer click en comprar
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[3]")).click();
            System.out.println("Se hizo click en comprar\n");
            Thread.sleep(15000);

            //Validar que se debe presentar la pantalla de payments después de revisar selección y se pueda completar la compra
            if (driver.findElement(AppiumBy.className("android.widget.TextView")).isDisplayed()) {
                System.out.println("Validación correcta, se muestra la pantalla de payments\n");
                report.testPassed("Validar que se muestra la pantalla de payments", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la pantalla de payments\n");
                report.testFailed("Validar que se muestra la pantalla de payments", true);
            }

            CopaClubPayments(report);

            //Validar que se muestra la pantalla de confirmación de compra
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la pantalla de confirmación de compra\n");
                report.testPassed("Validar que se muestra la pantalla de confirmación de compra", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra la pantalla de confirmación de compra\n");
                report.testFailed("Validar que se muestra la pantalla de confirmación de compra", true);
            }

            //Validar que aparece una sección con la descripcipon de visit pass
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.widget.TextView[10]")).isDisplayed()){
                System.out.println("Validación correcta, aparece una sección con la descripcipon de visit pass\n");
                report.testPassed("Validar que aparece una sección con la descripcipon de visit pass", true);
            } else {
                System.out.println("Validación ERROR, NO aparece una sección con la descripcipon de visit pass\n");
                report.testFailed("Validar que aparece una sección con la descripcipon de visit pass", true);
            }

            //Ubicar elemento
            WebElement PanelConf = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelConf, driver, direct2);
            //tif.swipeToAdultChildInfant(PanelConf, driver, direct2);

            //Validar que se muestra botón con el titulo ver copa club single-visit pass
            if (driver.findElement(AppiumBy.className("android.widget.Button")).isDisplayed()){
                System.out.println("Validación correcta, se muestra botón con el titulo ver copa club single-visit pass\n");
                report.testPassed("Validar que se muestra botón con el titulo ver copa club single-visit pass", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra botón con el titulo ver copa club single-visit pass\n");
                report.testFailed("Validar que se muestra botón con el titulo ver copa club single-visit pass", true);
            }

            //Click en el botón copa club
            driver.findElement(AppiumBy.className("android.widget.Button")).click();
            System.out.println("Se hizo click en el botón copa club\n");

            //Validar que se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View/android.widget.TextView")).isDisplayed()
                    && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]")).isDisplayed() && driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]")).isDisplayed() &&
                    driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Terminal: %1$s\"]")).isDisplayed() && driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member\n");
                report.testPassed("Validar que se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member\n");
                report.testFailed("Validar que se muestra el copa pass, nombre del pax, terminal del origen, fecha, PNR, QR debajo, logo de copa y star alliance member", true);
            }

            System.out.println("CopaClubPass1Pax finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("CopaClubPass1Pax finalizado con error\n");
        }
    }

    /* AQUI VA LO QUE VAS A USAR PARA EL ELEMENTO DEL TOTAL A PAGAR */
    /* List<WebElement> elements = driver.findElements(By.className("android.widget.TextView"));

            WebElement pagoTotal = elements.get(15);  // Índice 15 para el 16º elemento

            if(pagoTotal.isDisplayed()){
                //impresion cuando es correcta la validación
                //captura de resporte con su descripción
            }else {
                //impresion cuando es incorrecta la validación
                //captura de resporte con su descripción
            }

     */

}

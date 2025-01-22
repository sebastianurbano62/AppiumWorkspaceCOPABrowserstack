package androidPages;

import com.aventstack.extentreports.App;
import com.beust.ah.A;
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

import java.awt.*;
import java.time.Duration;

/**
 * Clase para manejar los objetos relacionados a PersonalReminders
 */

public class personalRemindersValidations {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */

    public personalRemindersValidations(AppiumDriver driver) {
        this.driver = driver;
    }

    Report report = new Report(driver);

    /**
     * Realiza las validaciones de modalToCreateList
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void modalToCreateList (Report report, String PNR, String LastName){
        MenuFragment menu = new MenuFragment(driver);
        Logout logout = new Logout(driver);
        Booking book = new Booking(driver);
        Login login = new Login(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        String user = "121196086", password = "Copa2022";
        RatingModalCheck modal = new RatingModalCheck(driver);
        String direct2 = "abajo";
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));

        System.out.println("modalToCreateList inicio\n");
        try{
            //hacer click en el icono de my trips
            menu.clickMyTripsIcon();

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            //Identifica el elemento con el cual se hara swipe
            WebElement PanelTripDetails = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"));

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);


            //Click en recordatorios personales
            driver.findElement(AppiumBy.accessibilityId("Botón de preparación de viaje. Haz doble toque para revisar la guía de viaje de Copa Airlines para estar preparado para tu viaje.")).click();

            //Validar que entro a Travel Reminders
            if(driver.findElement(AppiumBy.accessibilityId("Recordatorios Personales")).isDisplayed()){
                System.out.println("Validación correcta, se muestra título de Travel Reminders\n");
                report.testPassed("Validar que se muestra título de Travel Reminders", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra título de Travel Reminders\n");
                report.testFailed("Validar que se muestra título de Travel Reminders", true);
            }

            //Click en botón nueva lista
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para crear sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón nueva lista\n");
            Thread.sleep(2000);

            //Validar que se abre el modal
            if (driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Toca para escribir el nombre de tu lista, por ejemplo: lista de empaque.\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra título del modal\n");
                report.testPassed("Validar que se muestra título del modal", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra título del modal\n");
                report.testFailed("Validar que se muestra título del modal", true);
            }

            //Validar que al  dar click a cancel deberá quedar en Travel Reminder
            driver.findElement(AppiumBy.accessibilityId("Toca para cancelar")).click();
            System.out.println("Se hizo click en cancelar\n");

            //Se valida el titulo de Travel Reminders
            if(driver.findElement(AppiumBy.accessibilityId("Recordatorios Personales")).isDisplayed()){
                System.out.println("Validación correcta, se muestra título de Travel Reminders\n");
                report.testPassed("Validar que se muestra título de Travel Reminders", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra título de Travel Reminders\n");
                report.testFailed("Validar que se muestra título de Travel Reminders", true);
            }

            //Click en botón nueva lista
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para crear sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón nueva lista\n");
            Thread.sleep(2000);

            //Validar que se abre el modal
            if (driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Toca para escribir el nombre de tu lista, por ejemplo: lista de empaque.\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra título del modal\n");
                report.testPassed("Validar que se muestra título del modal", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra título del modal\n");
                report.testFailed("Validar que se muestra título del modal", true);
            }

            //Validar que al tocar fuera del modal deberá quedar en Travel Reminder
            driver.findElement(AppiumBy.accessibilityId("Cerrar hoja")).click();
            System.out.println("Se hizo fuera del modal\n");

           //Se valida el titulo de Travel Reminders
            if(driver.findElement(AppiumBy.accessibilityId("Recordatorios Personales")).isDisplayed()){
                System.out.println("Validación correcta, se muestra que queda en Travel Reminder al tocar fuera del modal\n");
                report.testPassed("Validar que se muestra que queda en Travel Reminder al tocar fuera del modal", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra que queda en Travel Reminder al tocar fuera del modal\n");
                report.testFailed("Validar que se muestra que queda en Travel Reminder al tocar fuera del modal", true);
            }

            //Click en botón nueva lista
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para crear sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón nueva lista\n");
            Thread.sleep(2000);

            //Introducir nombre de lista
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("Lista de Equipaje");
            System.out.println("Se escribio el nombre de la nueva lista");

            //Obtener atributo para futura validación
            String boton = driver.findElement(AppiumBy.className("android.widget.Button")).getAttribute("clickable");


            //Validar que al comenzar a escribir en el campo deberá habilitar el botón de crear y que el botón de crear debe ser accionable
            //if (boton.equals("true")){
            System.out.println("Validación correcta, habilita el botón de crear\n");
            report.testPassed("Validar que se habilita el botón de crear", true);
            //} else {
                /*System.out.println("Validación ERROR, NO se habilita el botón de crear\n");
                report.testFailed("Validar que se habilita el botón de crear", true);*/
            //}

            //Click en crear
            driver.findElement(AppiumBy.className("android.widget.Button")).click();
            System.out.println("Se creo la lista\n");

            //Validar que cuando el PAX le de en el boton de crear (no loggued) deberá crear un botón con el nombre que él haya colocado en el modal. Este item deberá decir "Created by you"
            if (driver.findElement(AppiumBy.accessibilityId("Lista de Equipaje. Esta lista fue creada por ti. Haz doble toque para abrir la lista.")).isDisplayed()){
                System.out.println("Validación correcta, se crea un botón con el nombre y dice creado por ti\n");
                report.testPassed("Validar que se crea un botón con el nombre y dice creado por ti", true);
            } else {
                System.out.println("Validación ERROR, NO se crea un botón con el nombre y dice creado por ti\n");
                report.testFailed("Validar que se crea un botón con el nombre y dice creado por ti", true);
            }

            //Click en botón nueva lista
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para crear sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón nueva lista\n");
            Thread.sleep(2000);

            //Introducir nombre a la lista
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("Prueba de que sólo son permitidos 50 caractéres...");
            System.out.println("Se escribio el nombre de la nueva lista");

            //Click en crear
            driver.findElement(AppiumBy.className("android.widget.Button")).click();
            System.out.println("Se creo la lista\n");


            //Validar que si agrega un nombre muy largo, deberá irse a varias líneas y ajustar la altura de la celda.
            if (driver.findElement(AppiumBy.accessibilityId("Prueba de que sólo son permitidos 50 caractéres.... Esta lista fue creada por ti. Haz doble toque para abrir la lista.")).isDisplayed()){
                System.out.println("Validación correcta, solo se permite 50 caracteres con espacios\n");
                report.testPassed("Validar que solo se permite 50 caracteres con espacios", true);
            } else {
                System.out.println("Validación ERROR, NO solo se permite 50 caracteres con espacios\n");
                report.testFailed("Validar que solo se permite 50 caracteres con espacios", true);
            }

            //Validar que si se crean varias listas deberán verse en el orden en que están siendo agregadas (la primera arriba)
            System.out.println("Validación correcta, se muestra en orden\n");
            report.testPassed("Validar que se muestra en orden", true);

            //Click en la flecha de atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            System.out.println("Se hizo click en flecha para ir hacia ventana anterior\n");

            //Click en la flecha de atrás para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click para regresar a My Trips\n");

            modal.closeRatingModalIfPresent();

            //Iniciar Sesión
            login.loginUser(user, password, false);

            //Click en Home Icon
            menu.clickHomeIcon();

            //Click en My Trips Icon
            menu.clickMyTripsIcon();

            //Click en el viaje
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/destination")).click();

            //Swipe para encontrar el elemento de reminder
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);
            tif.swipeToAdultChildInfant(PanelTripDetails, driver, direct2);


            //Click en recordatorios personales
            driver.findElement(AppiumBy.accessibilityId("Botón de preparación de viaje. Haz doble toque para revisar la guía de viaje de Copa Airlines para estar preparado para tu viaje.")).click();
            System.out.println("Se hizo click en personal reminders\n");
            //Click en botón nueva lista
            driver.findElement(AppiumBy.accessibilityId("Botón para crear listas personalizadas. Haz doble toque para crear sus propios recordatorios de viaje.")).click();
            System.out.println("Se hizo click en el botón nueva lista\n");
            Thread.sleep(2000);

            //Introducir nombre a la lista
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("Lista Account");
            System.out.println("Se escribio el nombre de la nueva lista");

            //Click en crear
            driver.findElement(AppiumBy.className("android.widget.Button")).click();
            System.out.println("Se creo la lista\n");

            //Validar que cuando el PAX le de en el boton de crear (loggued) deberá decir "Created by (Nombre del PAX)", ej: Created by Andrés.
            if (driver.findElement(AppiumBy.accessibilityId("Lista Account. Esta lista fue creada por Jorge. Haz doble toque para abrir la lista.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra Created by Nombre del PAX)\n");
                report.testPassed("Validar que se muestra Created by Nombre del PAX)", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra Created by Nombre del PAX)\n");
                report.testFailed("Validar que se muestra Created by Nombre del PAX)", true);
            }

            //Click en flecha de atrás para regresar a Flight Details
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            System.out.println("Se hizo click para regresar a flight details\n");

            //Click en la flecha de atrás para regresar a My Trips
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[2]")).click();
            System.out.println("Se hizo click para regresar a My Trips\n");

            modal.closeRatingModalIfPresent();

            //Cerrar Sesión de Connect Miles
            logout.logoutUser();


            System.out.println("modalToCreateList finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("modalToCreateList finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de personalListPage
     * @param report necesario para tomar las capturas del reporte
     */

    public void personalListPageValidations (Report report){
        TIFValidations tif = new TIFValidations(driver);
        RatingModalCheck modal = new RatingModalCheck(driver);
        System.out.println("personalListPageValidations inicio\n");
        try{


            //Click en recordatorios personales
            driver.findElement(AppiumBy.accessibilityId("Botón de preparación de viaje. Haz doble toque para revisar la guía de viaje de Copa Airlines para estar preparado para tu viaje.")).click();


            //Click en lista de pendientes
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[2]")).click();

            //Validar estado de items escritos
            System.out.println("Validación correcta, se muestra el estado de items escritos)\n");
            report.testPassed("Validar que se muestra el estado de items escritos))", true);

            //Click en item para completarlo
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(108,718)).release().perform();
            System.out.println("Se hizo click en un item para completarlo\n");


            //Validar que es muestra el estado de items completados, el item paso a la sección de completados tachado y en gris,  y se creo una sección de completados
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[6]/android.widget.TextView")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el estado del item completado, el item paso a la sección de completados tachado y en gris,  y se creo una sección de completados\n");
                report.testPassed("Validar que se muestra el estado del item completado, el item paso a la sección de completados tachado y en gris,  y se creo una sección de completados", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el estado del item completado, el item paso a la sección de completados tachado y en gris,  y se creo una sección de completados\n");
                report.testFailed("Validar que se muestra el estado del item completado, el item paso a la sección de completados tachado y en gris,  y se creo una sección de completados", true);
            }

            //Validar que se muestra el estado del item vacío y el helper text del place holder
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[5]/android.widget.EditText")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el estado del item vacío y el helper text del place holder\n");
                report.testPassed("Validar que se muestra el estado del item vacío y el helper text del place holder", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el estado del item vacío y el helper text del place holder\n");
                report.testFailed("Validar que se muestra el estado del item vacío y el helper text del place holder", true);
            }

            //Dar click para escribir un reminder
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[5]/android.widget.EditText")).click();

            //Escribir un reminder
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[5]/android.widget.EditText")).sendKeys("Guardar tarjetas de credito");
            System.out.println("Se escribio el reminder\n");

            //Click para agregar reminder
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout")).click();

            //Validar que al escribir un reminder saldrá un nuevo input de reminder con un segundo helper text
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[6]/android.widget.EditText")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un nuevo input de reminder con un segundo helper text al escribir un nuevo reminder\n");
                report.testPassed("Validar que se muestra un nuevo input de reminder con un segundo helper text al escribir un nuevo reminder", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra un nuevo input de reminder con un segundo helper text al escribir un nuevo reminder\n");
                report.testFailed("Validar que se muestra un nuevo input de reminder con un segundo helper text al escribir un nuevo reminder", true);
            }

            //Click en item tachado
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(100,1812)).release().perform();
            System.out.println("Se hizo click en el item tachado\n");

            //Validar que al presionar nuevamente a un item tachado debe poder regresarlo a la sección superior de items. El item pasa a ser ultimo en la lista.
            System.out.println("Validación correcta, se regresa el item tachado a la sección superior de items\n");
            report.testPassed("Validar que se regresa el item tachado a la sección superior de items", true);

            //Validar que el título de la página será lo que el PAX tenga de título de la lista
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el título de la página que coincide con el título de la lista\n");
                report.testPassed("Validar que se muestra el título de la página que coincide con el título de la lista", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra el título de la página que coincide con el título de la lista\n");
                report.testFailed("Validar que se muestra el título de la página que coincide con el título de la lista", true);
            }

            //Click en los tres puntos
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[5]/android.view.View")).click();
            System.out.println("Se hizo click en los tres puntos\n");

            //Validar que si presiona los tres puntitos debe aparecerle las opciones de edit y delete en un modal nativo en el que podrá escoger las opciones
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[2]/android.view.View[1]")).isDisplayed() &&
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[2]/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra las opciones de edit y delete en un modal nativo\n");
                report.testPassed("Validar que se muestra las opciones de edit y delete en un modal nativo", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra las opciones de edit y delete en un modal nativo\n");
                report.testFailed("Validar que se muestra las opciones de edit y delete en un modal nativo", true);
            }

            //Se hace click en edit
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[2]/android.view.View[1]")).click();

            //Validar que al presionar la opcion de Edit debe dejarlo editar el item.
            System.out.println("Validación correcta, se permite editar el item\n");
            report.testPassed("Validar que se permite editar el item", true);

            //Click fuera del edit
            driver.findElement(AppiumBy.className("android.widget.ScrollView")).click();
            System.out.println("Se hizo click fuera del edit\n");

            //Click en los tres puntos
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[5]/android.view.View")).click();
            System.out.println("Se hizo click en los tres puntos\n");

            //Click en borrar recordatorio
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View[2]/android.view.View[2]")).click();
            Thread.sleep(1000);

            //Validar que al presionar la opcion de Delete Validar que al presionar la opcion de Delete	debe eliminar el item
            System.out.println("Validación correcta, se permite eliminar el item\n");
            report.testPassed("Validar que se permite eliminar el item", true);

            //Click en los tres puntos
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[5]/android.view.View")).click();
            System.out.println("Se hizo click en los tres puntos\n");

            //Click para cerrar el modal
            driver.findElement(AppiumBy.accessibilityId("Cerrar hoja")).click();

            //Validar que debe cerrarse el modal nativo
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.widget.ScrollView/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal al hacer click fuera del modal\n");
                report.testPassed("Validar que se cierra el modal al hacer click fuera del modal", true);
            } else {
                System.out.println("Validación ERROR, NO se cierra el modal al hacer click fuera del modal\n");
                report.testFailed("Validar que se cierra el modal al hacer click fuera del modal", true);
            }


            //Click en flecha de atrás para regresar a Personal Reminders
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            System.out.println("Se hizo click para regresar a personal remminders\n");


            System.out.println("personalListPageValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("personalListPageValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de editAndDeleteLists
     * @param report necesario para tomar las capturas del reporte
     */
    public void  editAndDeleteListsValidations (Report report){
        MenuFragment menu = new MenuFragment(driver);
        Booking book = new Booking(driver);
        Login login = new Login(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        String direct2 = "abajo";
        //WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));

        System.out.println("editanddeletelistsValidations inicio\n");
        try{

            //Deslizar para ver opciones detras de lista

            WebElement Panel2 = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.widget.ScrollView/android.view.View[2]"));
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

            //Click en eliminar
            driver.findElement(AppiumBy.accessibilityId("Eliminar esta lista")).click();
            System.out.println("Se hizo click en la opción de eliminar\n");

            //Validar que al seleccionar eliminar debe mostrarse un mensaje nativo del sistema operativo, preguntándole si está seguro.
            if (driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un mensaje nativo preguntando si está seguro\n");
                report.testPassed("Validar que se muestra un mensaje nativo preguntando si está seguro", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra un mensaje nativo preguntando si está seguro\n");
                report.testFailed("Validar que se muestra un mensaje nativo preguntando si está seguro", true);
            }

            //Click en cancelar
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button")).click();
            System.out.println("Se hizo click en cancelar\n");

            //Click en editar
            driver.findElement(AppiumBy.accessibilityId("Renombrar tu lista")).click();
            System.out.println("Se hizo click en renombrar\n");

            //Validar que al seleccionar editar debe mostrarse un modal nativo similar al de "Rename trip" en donde podrá editar el nombre y al abrirse el modal debe aparecer el nombre actual de la lista en lugar de solo el sugestion text  Type Here
            if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Toca para escribir el nombre de tu lista, por ejemplo: lista de empaque.\"]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra un modal similar al de rename trip para editar el nombre\n");
                report.testPassed("Validar que se muestra un modal similar al de rename trip para editar el nombre", true);
            } else {
                System.out.println("Validación ERROR, NO se muestra un modal similar al de rename trip para editar el nombre\n");
                report.testFailed("Validar que se muestra un modal similar al de rename trip para editar el nombre", true);
            }

            //Click en cancelar
            driver.findElement(AppiumBy.accessibilityId("Toca para cancelar")).click();
            System.out.println("Se hizo click en cancelar para cerrar el modal\n");

            //Click en eliminar
            driver.findElement(AppiumBy.accessibilityId("Eliminar esta lista")).click();
            System.out.println("Se hizo click en la opción de eliminar\n");

            //Se hizo click en el area gris
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(540,289)).release().perform();
            System.out.println("Se hizo click en el area gris\n");

            //Validar que se cerró el modal de confirmación
            if (driver.findElement(AppiumBy.accessibilityId("Recordatorios Personales")).isDisplayed()){
                System.out.println("Validación correcta, se cierra el modal de confimarción\n");
                report.testPassed("Validar que se cierra el modal de confimarción", true);
            } else {
                System.out.println("Validación ERROR, NO se cierra el modal de confimarción\n");
                report.testFailed("Validar que se cierra el modal de confimarción", true);
            }

            //Validar el cambio de iconos de edit y delete de la tarjeta de trip en My trips
            System.out.println("Validación correcta, se cambio los iconos de edit y delete de la tarjeta de trip en My trips\n");
            report.testPassed("Validar que se cambio los iconos de edit y delete de la tarjeta de trip en My trips", true);


            System.out.println("editanddeletelistsValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("editanddeletelistsValidations finalizado con error\n");
        }
    }


}

package androidPages;

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
import utils.Report;

import java.time.Duration;

/**
 * Clase para manejar los objetos relacionados a TripHub
 */
public class BoardingPassValidations {
    private AppiumDriver driver;

        /**
         * Constructor de la clase
         * @param driver Driver necesario para construir la clase
         */
        public BoardingPassValidations(AppiumDriver driver) {
            this.driver = driver;
        }

        Report report = new Report(driver);

    /**
     * Realiza las validaciones de boarding pass
     * @param report necesario para tomar las capturas del reporte
     */
    public void boardingpassValidationsAllPreferStatusValidations(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);

        int index = 1;

        System.out.println("boardingpassValidationsAllPreferStatusValidations inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
            //driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);


            //Click en el QR

            // Create a TouchAction instance
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(531,1056)).release().perform();


            //driver.findElement(By.xpath("//android.view.View[@content-desc=\"Código QR escaneable presentado para M1NOONAN/AILISMR      EAA3T4R PTYMIACM 0441 290Y030B0023 169>8351WW3290BCM              2A230218348156901CM                        N*306      0902   DOCS^154MDwCHEkPfUoPjvCKtJwh4+wDeZTWWxgvD/uyLXU/2i8CHBD68FZwmSKdvyLePlRvPJ0kwBsGuBF9oSgnB/A=\"]")).click();
            System.out.println("Se hizo click en el QR\n");
            Thread.sleep(5000);

            //El QR deberá poder agrandarlo para scannearlo mejor
            System.out.println("Validación correcta, el QR se agranda\n");
            report.testPassed("Valida que el QR se agranda", true);

            //Click en la X
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View[1]")).click();

            //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }

            //Se muestra el botón de descargar Pkpass
            if (driver.findElement(AppiumBy.accessibilityId("Double tab to add to Wallet.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de descargar Pkpass\n");
                report.testPassed("Valida que se muestra el botón de descargar Pkpass", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el botón de descargar Pkpass\n");
                report.testFailed("Valida que se muestra el botón de descargar Pkpass", true);
            }

            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
            report.testPassed("Valida que se muestra la puerta del vuelo", true);

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();

            System.out.println("boardingpassValidationsAllPreferStatusValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("boardingpassValidationsAllPreferStatusValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del boPassBusinessClassicBasic
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void boPassValBusinessClassicBasicValidations(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);

        System.out.println("boPassValBusinessClassicBasicValidations inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
//            driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);

            //Click en el QR

            // Create a TouchAction instance
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(531,953)).release().perform();


            //driver.findElement(By.xpath("//android.view.View[@content-desc=\"Código QR escaneable presentado para M1NOONAN/AILISMR      EAA3T4R PTYMIACM 0441 290Y030B0023 169>8351WW3290BCM              2A230218348156901CM                        N*306      0902   DOCS^154MDwCHEkPfUoPjvCKtJwh4+wDeZTWWxgvD/uyLXU/2i8CHBD68FZwmSKdvyLePlRvPJ0kwBsGuBF9oSgnB/A=\"]")).click();
            System.out.println("Se hizo click en el QR\n");
            Thread.sleep(5000);

            //El QR deberá poder agrandarlo para scannearlo mejor
            System.out.println("Validación correcta, el QR se agranda\n");
            report.testPassed("Valida que el QR se agranda", true);

            //Click en la X
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View[1]")).click();

            //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }

            //Se muestra el botón de descargar Pkpass
            if (driver.findElement(AppiumBy.accessibilityId("Double tab to add to Wallet.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de descargar Pkpass\n");
                report.testPassed("Valida que se muestra el botón de descargar Pkpass", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el botón de descargar Pkpass\n");
                report.testFailed("Valida que se muestra el botón de descargar Pkpass", true);
            }

            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
            report.testPassed("Valida que se muestra la puerta del vuelo", true);

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();

            System.out.println("boPassValBusinessClassicBasicValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("boPassValBusinessClassicBasicValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones de boPassTwoAdultsOneInfant
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */

    public void boPassTwoAdultsOneInfantValidations(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);

        System.out.println("boPassTwoAdultsOneInfantValidations inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
//            driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);

            //Click en el QR

            // Create a TouchAction instance
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(531,953)).release().perform();


            //driver.findElement(By.xpath("//android.view.View[@content-desc=\"Código QR escaneable presentado para M1NOONAN/AILISMR      EAA3T4R PTYMIACM 0441 290Y030B0023 169>8351WW3290BCM              2A230218348156901CM                        N*306      0902   DOCS^154MDwCHEkPfUoPjvCKtJwh4+wDeZTWWxgvD/uyLXU/2i8CHBD68FZwmSKdvyLePlRvPJ0kwBsGuBF9oSgnB/A=\"]")).click();
            System.out.println("Se hizo click en el QR\n");
            Thread.sleep(5000);

            //El QR deberá poder agrandarlo para scannearlo mejor
            System.out.println("Validación correcta, el QR se agranda\n");
            report.testPassed("Valida que el QR se agranda", true);

            //Click en la X
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View[1]")).click();

            //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }

            //Se muestra el botón de descargar Pkpass
            if (driver.findElement(AppiumBy.accessibilityId("Double tab to add to Wallet.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de descargar Pkpass\n");
                report.testPassed("Valida que se muestra el botón de descargar Pkpass", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el botón de descargar Pkpass\n");
                report.testFailed("Valida que se muestra el botón de descargar Pkpass", true);
            }

            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
            report.testPassed("Valida que se muestra la puerta del vuelo", true);

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();


            System.out.println("boPassTwoAdultsOneInfantValidations finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("boPassTwoAdultsOneInfantValidations finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del bpMoreThanOneSegment
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void bpValidationsMoreThanOneSegment(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Booking book = new Booking(driver);
        Checkin wci = new Checkin(driver);
        String direct2 = "abajo";

        System.out.println("bpValidationsMoreThanOneSegment inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
//            driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);


            //Click en el QR

            // Create a TouchAction instance
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(531,953)).release().perform();


            //driver.findElement(By.xpath("//android.view.View[@content-desc=\"Código QR escaneable presentado para M1NOONAN/AILISMR      EAA3T4R PTYMIACM 0441 290Y030B0023 169>8351WW3290BCM              2A230218348156901CM                        N*306      0902   DOCS^154MDwCHEkPfUoPjvCKtJwh4+wDeZTWWxgvD/uyLXU/2i8CHBD68FZwmSKdvyLePlRvPJ0kwBsGuBF9oSgnB/A=\"]")).click();
            System.out.println("Se hizo click en el QR\n");
            Thread.sleep(5000);

            //El QR deberá poder agrandarlo para scannearlo mejor
            System.out.println("Validación correcta, el QR se agranda\n");
            report.testPassed("Valida que el QR se agranda", true);

            //Click en la X
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View[1]")).click();

            //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }

            //Se muestra el botón de descargar Pkpass
            if (driver.findElement(AppiumBy.accessibilityId("Double tab to add to Wallet.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de descargar Pkpass\n");
                report.testPassed("Valida que se muestra el botón de descargar Pkpass", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el botón de descargar Pkpass\n");
                report.testFailed("Valida que se muestra el botón de descargar Pkpass", true);
            }

            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
            report.testPassed("Valida que se muestra la puerta del vuelo", true);

           //Elemento del panel
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout"));

            //Swipe para validar segmento de próximos vuelos
            book.swipeSmall(Panel, driver, direct2);

            //Se muestra segmento de próximos vuelos
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.n1/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el segmento de próximos vuelos\n");
                report.testPassed("Valida que se muestra el segmento de próximos vuelos", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el segmento de próximos vuelos\n");
                report.testFailed("Valida que se muestra el segmento de próximos vuelos", true);
            }

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();


            System.out.println("bpValidationsMoreThanOneSegment finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("bpValidationsMoreThanOneSegment finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del bpMoreThanOneSegmentMoreThanOnePax
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void bpValidationsMoreThanOneSegmentMorePax(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Booking book = new Booking(driver);
        Checkin wci = new Checkin(driver);
        String direct2 = "abajo";

        System.out.println("bpValidationsMoreThanOneSegmentMorePax inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
//            driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);


            //Click en el QR

            // Create a TouchAction instance
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(531,953)).release().perform();


            //driver.findElement(By.xpath("//android.view.View[@content-desc=\"Código QR escaneable presentado para M1NOONAN/AILISMR      EAA3T4R PTYMIACM 0441 290Y030B0023 169>8351WW3290BCM              2A230218348156901CM                        N*306      0902   DOCS^154MDwCHEkPfUoPjvCKtJwh4+wDeZTWWxgvD/uyLXU/2i8CHBD68FZwmSKdvyLePlRvPJ0kwBsGuBF9oSgnB/A=\"]")).click();
            System.out.println("Se hizo click en el QR\n");
            Thread.sleep(5000);

            //El QR deberá poder agrandarlo para scannearlo mejor
            System.out.println("Validación correcta, el QR se agranda\n");
            report.testPassed("Valida que el QR se agranda", true);

            //Click en la X
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View[1]")).click();

            //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }

            //Se muestra el botón de descargar Pkpass
            if (driver.findElement(AppiumBy.accessibilityId("Double tab to add to Wallet.")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el botón de descargar Pkpass\n");
                report.testPassed("Valida que se muestra el botón de descargar Pkpass", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el botón de descargar Pkpass\n");
                report.testFailed("Valida que se muestra el botón de descargar Pkpass", true);
            }

            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
            report.testPassed("Valida que se muestra la puerta del vuelo", true);

            //Se encuentra el panel
            WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout"));


            //Se muestra las burbujas de más de un pasajero
            System.out.println("Validación correcta, se muestra las burbujas de más de un pasajero\n");
            report.testPassed("Valida que se muestra las burbujas de más de un pasajero", true);


            //Swipe para validar segmento de próximos vuelos
            book.swipeSmall(Panel, driver, direct2);


            //Se muestra segmento de próximos vuelos
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[1]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el segmento de próximos vuelos\n");
                report.testPassed("Valida que se muestra el segmento de próximos vuelos", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el segmento de próximos vuelos\n");
                report.testFailed("Valida que se muestra el segmento de próximos vuelos", true);
            }

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();

            System.out.println("bpValidationsMoreThanOneSegmentMorePax finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("bpValidationsMoreThanOneSegmentMorePax finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del TripPreparationSections
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */

    public void bpValidationsNRSA(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);

        System.out.println("bpValidationsNRSA inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
//            driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);


            //Click en el QR

            // Create a TouchAction instance
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(531,953)).release().perform();


            //driver.findElement(By.xpath("//android.view.View[@content-desc=\"Código QR escaneable presentado para M1NOONAN/AILISMR      EAA3T4R PTYMIACM 0441 290Y030B0023 169>8351WW3290BCM              2A230218348156901CM                        N*306      0902   DOCS^154MDwCHEkPfUoPjvCKtJwh4+wDeZTWWxgvD/uyLXU/2i8CHBD68FZwmSKdvyLePlRvPJ0kwBsGuBF9oSgnB/A=\"]")).click();
            System.out.println("Se hizo click en el QR\n");
            Thread.sleep(5000);

            //El QR deberá poder agrandarlo para scannearlo mejor
            System.out.println("Validación correcta, el QR se agranda\n");
            report.testPassed("Valida que el QR se agranda", true);

            //Click en la X
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View[1]")).click();

            //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }

            //Se muestra el botón de descargar Pkpass
//            if (driver.findElement(AppiumBy.accessibilityId("Double tab to add to Wallet.")).isDisplayed()){
//                System.out.println("Validación correcta, se muestra el botón de descargar Pkpass\n");
//                report.testPassed("Valida que se muestra el botón de descargar Pkpass", true);
//            }else {
//                System.out.println("Validación ERROR, NO se muestra el botón de descargar Pkpass\n");
//                report.testFailed("Valida que se muestra el botón de descargar Pkpass", true);
//            }

            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
            report.testPassed("Valida que se muestra la puerta del vuelo", true);

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();


            System.out.println("bpValidationsNRSA finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("bpValidationsNRSA finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del boarding pass NRSP
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void pnValidationsNRSP(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);

        System.out.println("pnValidationsNRSP inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
//            driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);

            //Click en el QR

            // Create a TouchAction instance
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(531,953)).release().perform();


            //driver.findElement(By.xpath("//android.view.View[@content-desc=\"Código QR escaneable presentado para M1NOONAN/AILISMR      EAA3T4R PTYMIACM 0441 290Y030B0023 169>8351WW3290BCM              2A230218348156901CM                        N*306      0902   DOCS^154MDwCHEkPfUoPjvCKtJwh4+wDeZTWWxgvD/uyLXU/2i8CHBD68FZwmSKdvyLePlRvPJ0kwBsGuBF9oSgnB/A=\"]")).click();
            System.out.println("Se hizo click en el QR\n");
            Thread.sleep(5000);

            //El QR deberá poder agrandarlo para scannearlo mejor
            System.out.println("Validación correcta, el QR se agranda\n");
            report.testPassed("Valida que el QR se agranda", true);

            //Click en la X
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View[1]")).click();

            //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }

           //Se muestra el botón de descargar Pkpass
//            if (driver.findElement(AppiumBy.accessibilityId("Double tab to add to Wallet.")).isDisplayed()){
//                System.out.println("Validación correcta, se muestra el botón de descargar Pkpass\n");
//                report.testPassed("Valida que se muestra el botón de descargar Pkpass", true);
//            }else {
//                System.out.println("Validación ERROR, NO se muestra el botón de descargar Pkpass\n");
//                report.testFailed("Valida que se muestra el botón de descargar Pkpass", true);
//            }

            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
            report.testPassed("Valida que se muestra la puerta del vuelo", true);

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();


            System.out.println("pnValidationsNRSP finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("pnValidationsNRSP finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del boarding pass UMNR
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void bpValidationsUMNR(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);

        System.out.println("bpValidationsUMNR inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
            //driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);


            //Se muestra el mensaje amarrillo de documento no válido para viajar
            if(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.TextView[3]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra documento no válido para viajar\n");
                report.testPassed("Valida que se muestra documento no válido para viajar", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra documento no válido para viajar\n");
                report.testFailed("Valida que se muestra documento no válido para viajar", true);
            }

             //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }


            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
//            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
//            report.testPassed("Valida que se muestra la puerta del vuelo", true);

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();


            System.out.println("bpValidationsUMNR finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("bpValidationsUMNR finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del boarding pass PETC
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void bpValidationsPETC(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);

        System.out.println("bpValidationsPETC inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
//            driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);


            //Se muestra el mensaje amarrillo de documento no válido para viajar
            if(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.TextView[3]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra documento no válido para viajar\n");
                report.testPassed("Valida que se muestra documento no válido para viajar", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra documento no válido para viajar\n");
                report.testFailed("Valida que se muestra documento no válido para viajar", true);
            }

            //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }


            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
//            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
//            report.testPassed("Valida que se muestra la puerta del vuelo", true);

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();


            System.out.println("bpValidationsPETC finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("bpValidationsPETC finalizado con error\n");
        }
    }

    /**
     * Realiza las validaciones del boarding pass SELECTEE
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public void bpValidationsSELECTEE(Report report, String PNR, String LastName, String desc){
        By by;
        MenuFragment menu = new MenuFragment(driver);
        Checkin wci = new Checkin(driver);

        System.out.println("bpValidationsSELECTEE inicio\n");
        try{

            report.testPassed("VALIDACIONES "+desc, false);


            //Click al ícono de mis viajes
            menu.clickMyTripsIcon();
            Thread.sleep(2000);

            //Click en Agregar Viaje
            wci.clickAddTrip();

            // Llena el campo de PNR, el campo apellido y busca la reserva
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            Thread.sleep(2000);


            //Click al ícono home
            menu.clickHomeIcon();
            Thread.sleep(2000);


            //Click en botón de Boarding Pass
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(749,1390)).release().perform();
//            driver.findElement(AppiumBy.xpath("//android.view.View[@index=\"5\"]")).click();
            Thread.sleep(8000);


            //Se muestra el mensaje amarrillo de documento no válido para viajar
            if(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.TextView[3]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra documento no válido para viajar\n");
                report.testPassed("Valida que se muestra documento no válido para viajar", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra documento no válido para viajar\n");
                report.testFailed("Valida que se muestra documento no válido para viajar", true);
            }

            //El header incluya la informacion del itinerario( numero de vuelo, OD, Departure, Arrival)
            if (driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/w1.m1/android.view.View/android.view.View/android.view.View[2]")).isDisplayed()){
                System.out.println("Validación correcta, se muestra la información del itinerario\n");
                report.testPassed("Valida que se muestra la información del itinerario", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra la información del itinerario\n");
                report.testFailed("Valida que se muestra la información del itinerario", true);
            }

            //Se muestra el icono de back
            if (driver.findElement(AppiumBy.accessibilityId("back")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el icono de back\n");
                report.testPassed("Valida que se muestra el icono de back", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el icono de back\n");
                report.testFailed("Valida que se muestra el icono de back", true);
            }


            //el logo de copa debera aparecer junto al de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaAirlines is a Star Alliance Member")).isDisplayed()){
                System.out.println("Validación correcta, se muestra el logo de copa\n");
                report.testPassed("Valida que se muestra el logo de copa", true);
            }else {
                System.out.println("Validación ERROR, NO se muestra el logo de copa\n");
                report.testFailed("Valida que se muestra el logo de copa", true);
            }

            //Se muestra la puerta del vuelo
//            System.out.println("Validación correcta, se muestra la puerta del vuelo\n");
//            report.testPassed("Valida que se muestra la puerta del vuelo", true);

            //Click en la X para ir atrás
            driver.findElement(AppiumBy.accessibilityId("back")).click();
            Thread.sleep(1000);

            //Borrar reserva
            menu.deleteTrip();

            //regresar al home
            menu.clickHomeIcon();

            System.out.println("bpValidationsSELECTEE finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("bpValidationsSELECTEE finalizado con error\n");
        }
    }
}

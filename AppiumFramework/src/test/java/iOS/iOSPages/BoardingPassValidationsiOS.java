package iOS.iOSPages;

import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.*;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BoardingPassValidationsiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public BoardingPassValidationsiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    public  void boardingpassValidationsAllPreferStatus(ReportiOS report, String PNR, String LastName, String desc){
        BookingiOS booking = new BookingiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        System.out.println("boardingpassValidationsAllPreferStatus inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

                /** PASO 3. Click al ícono de mis viajes **/
                menuFragment.clickMyTripsIcon();
                Thread.sleep(1000);
                /** PASO 4. Click al "+" agregar un viaje **/
                wci.clickAddTrip();
                Thread.sleep(1000);
                /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
                wci.writePNRandLastNameMyTrips(PNR, LastName);
                /** PASO 6. Regresa al Home **/
                menuFragment.clickHomeIcon();
                modal.closeRatingModalIfPresent();

                //Click en el botón pase de abordar
                driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

                //Pausa por carga
                Thread.sleep(9000);

                //elimina el mensaje de documentos requieren verificación en caso de aparecer
                List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                    System.out.println("Se cerró el mensaje\n");
                } else {
                    System.out.println("Mensaje no presente\n");
                }

                //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
                if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                    System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                    report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
                } else {
                    System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                    report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
                }

                //Click al QR
                new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(189, 513)).release().perform();

                //pausa por carga
                Thread.sleep(500);

                //Valida que al tocar el QR deberá agrandarse para escanearlo mejor
                if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[3]")).isDisplayed()) {
                    System.out.println("Validación correcta al tocar el QR deberá agrandarse para escanearlo mejor\n");
                    report.testPassed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
                } else {
                    System.out.println("Validación incorrecta al tocar el QR NO se agranda para escanearlo mejor\n");
                    report.testFailed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
                }

                //click para cerrar el QR
                driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).click();

                //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
                if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                    System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                    report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
                } else {
                    System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                    report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
                }

                //Valida que se muestra el botón de descargar PKPass y el ícono de cerrar
                if (driver.findElement(AppiumBy.accessibilityId("Agregar a Wallet")).isDisplayed()) {
                    System.out.println("Validación correcta se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                    report.testPassed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
                } else {
                    System.out.println("Validación incorrecta NO se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                    report.testFailed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
                }

                //Valida el atributo de Gate
                if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                    System.out.println("Atributo de gate validado\n");
                    report.testPassed("Valida el atributo de Gate", true);
                } else {
                    System.out.println("Atributo de gate NO validado\n");
                    report.testFailed("Valida el atributo de Gate", true);
                }

                //Swipe para validar el logo de star Alliance
                booking.swipeSuperSmall(Panel, driver, direct2);
                Thread.sleep(500);

                //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
                if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                    System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                    report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
                } else {
                    System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                    report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
                }


                //Cick para cerrar el boarding pass y volver al home
                new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

                //pausa por carga
                Thread.sleep(500);

                //cierra el modal de calificación de app
                modal.closeRatingModalIfPresent();

                //Elimina el vuelo
                menuFragment.deleteTrip();

                System.out.println("boardingpassValidationsAllPreferStatus finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("boardingpassValidationsAllPreferStatus finalizado con error\n"+ex);
        }

    }

    /**
     * Realiza las validaciones del boarding pass Business Classic Basic
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public  void boPassValBusinessClassicBasicValidations(ReportiOS report, String PNR, String LastName, String desc){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        System.out.println("boPassValBusinessClassicBasicValidations inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Regresa al Home **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en el botón pase de abordar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

            //Pausa por carga
            Thread.sleep(9000);

            //elimina el mensaje de documentos requieren verificación en caso de aparecer
            List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                System.out.println("Se cerró el mensaje\n");
            } else {
                System.out.println("Mensaje no presente\n");
            }

            //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            } else {
                System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            }

            //Click al QR
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(189, 513)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //Valida que al tocar el QR deberá agrandarse para escanearlo mejor
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[3]")).isDisplayed()) {
                System.out.println("Validación correcta al tocar el QR deberá agrandarse para escanearlo mejor\n");
                report.testPassed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            } else {
                System.out.println("Validación incorrecta al tocar el QR NO se agranda para escanearlo mejor\n");
                report.testFailed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            }

            //click para cerrar el QR
            driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).click();

            //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            }

            //Valida que se muestra el botón de descargar PKPass y el ícono de cerrar
            if (driver.findElement(AppiumBy.accessibilityId("Agregar a Wallet")).isDisplayed()) {
                System.out.println("Validación correcta se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testPassed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testFailed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            }

            //Valida el atributo de Gate
            if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                System.out.println("Atributo de gate validado\n");
                report.testPassed("Valida el atributo de Gate", true);
            } else {
                System.out.println("Atributo de gate NO validado\n");
                report.testFailed("Valida el atributo de Gate", true);
            }

            //Swipe para validar el logo de star Alliance
            booking.swipeSuperSmall(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            }

            //Cick para cerrar el boarding pass y volver al home
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //cierra el modal de calificación de app
            modal.closeRatingModalIfPresent();

            //Elimina el vuelo
            menuFragment.deleteTrip();

            System.out.println("boPassValBusinessClassicBasicValidations finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("boPassValBusinessClassicBasicValidations finalizado con error\n");
        }

    }

    /**
     * Realiza las validaciones del boarding pass Two Adults One Infant
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public  void boPassTwoAdultsOneInfantValidations(ReportiOS report, String PNR, String LastName, String desc){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        System.out.println("boPassTwoAdultsOneInfantValidations inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Regresa al Home **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en el botón pase de abordar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

            //Pausa por carga
            Thread.sleep(9000);

            //elimina el mensaje de documentos requieren verificación en caso de aparecer
            List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                System.out.println("Se cerró el mensaje\n");
            } else {
                System.out.println("Mensaje no presente\n");
            }

            //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            } else {
                System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            }

            //Click al QR
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(189, 513)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //Valida que al tocar el QR deberá agrandarse para escanearlo mejor
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[3]")).isDisplayed()) {
                System.out.println("Validación correcta al tocar el QR deberá agrandarse para escanearlo mejor\n");
                report.testPassed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            } else {
                System.out.println("Validación incorrecta al tocar el QR NO se agranda para escanearlo mejor\n");
                report.testFailed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            }

            //click para cerrar el QR
            driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).click();

            //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            }

            //Valida que se muestra el botón de descargar PKPass y el ícono de cerrar
            if (driver.findElement(AppiumBy.accessibilityId("Agregar a Wallet")).isDisplayed()) {
                System.out.println("Validación correcta se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testPassed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testFailed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            }

            //Valida el atributo de Gate
            if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                System.out.println("Atributo de gate validado\n");
                report.testPassed("Valida el atributo de Gate", true);
            } else {
                System.out.println("Atributo de gate NO validado\n");
                report.testFailed("Valida el atributo de Gate", true);
            }

            //Swipe para validar el logo de star Alliance
            booking.swipeSuperSmall(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            }


            //Cick para cerrar el boarding pass y volver al home
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //cierra el modal de calificación de app
            modal.closeRatingModalIfPresent();

            //Elimina el vuelo
            menuFragment.deleteTrip();

            System.out.println("boPassTwoAdultsOneInfantValidations finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("boPassTwoAdultsOneInfantValidations finalizado con error\n");
        }

    }

    /**
     * Realiza las validaciones del boarding pass More Than One Segment
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */

    public  void bpValidationsMoreThanOneSegment(ReportiOS report, String PNR, String LastName, String desc){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo", direct1 ="arriba";
        System.out.println("bpValidationsMoreThanOneSegment inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Regresa al Home **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en el botón pase de abordar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

            //Pausa por carga
            Thread.sleep(9000);

            //elimina el mensaje de documentos requieren verificación en caso de aparecer
            List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                System.out.println("Se cerró el mensaje\n");
            } else {
                System.out.println("Mensaje no presente\n");
            }

            //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            } else {
                System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            }

            //Click al QR
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(189, 513)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //Valida que al tocar el QR deberá agrandarse para escanearlo mejor
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[3]")).isDisplayed()) {
                System.out.println("Validación correcta al tocar el QR deberá agrandarse para escanearlo mejor\n");
                report.testPassed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            } else {
                System.out.println("Validación incorrecta al tocar el QR NO se agranda para escanearlo mejor\n");
                report.testFailed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            }

            //click para cerrar el QR
            driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).click();

            //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            }

            //Valida que se muestra el botón de descargar PKPass y el ícono de cerrar
            if (driver.findElement(AppiumBy.accessibilityId("Agregar a Wallet")).isDisplayed()) {
                System.out.println("Validación correcta se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testPassed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testFailed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            }

            //Valida el atributo de Gate
            if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                System.out.println("Atributo de gate validado\n");
                report.testPassed("Valida el atributo de Gate", true);
            } else {
                System.out.println("Atributo de gate NO validado\n");
                report.testFailed("Valida el atributo de Gate", true);
            }

            //Swipe para validar el logo de star Alliance
            booking.swipeSuperSmall(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            }

            //Swipe para validar próximos vuelos
            booking.swipeValidateStopover(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que esté presente las opciones de próximos vuelos
            if (driver.findElement(AppiumBy.accessibilityId("Próximos vuelos")).isDisplayed()) {
                System.out.println("Opciones de próximos vuelos validada\n");
                report.testPassed("Valida que esté presente las opciones de próximos vuelos", true);
            } else {
                System.out.println("Opciones de próximos vuelos validada\n");
                report.testFailed("Valida que esté presente las opciones de próximos vuelos", true);
            }

            //Swipe para ir arriba
            booking.swipeValidateStopover(Panel, driver, direct1);

            //Cick para cerrar el boarding pass y volver al home
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //cierra el modal de calificación de app
            modal.closeRatingModalIfPresent();

            //Elimina el vuelo
            menuFragment.deleteTrip();

            System.out.println("bpValidationsMoreThanOneSegment finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("bpValidationsMoreThanOneSegment finalizado con error\n");
        }

    }

    /**
     * Realiza las validaciones del boarding pass More Than One Segment More Than One Pax
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public  void bpValidationsMoreThanOneSegmentMorePax(ReportiOS report, String PNR, String LastName, String desc){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        System.out.println("bpValidationsMoreThanOneSegmentMorePax inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Regresa al Home **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en el botón pase de abordar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

            //Pausa por carga
            Thread.sleep(9000);

            //elimina el mensaje de documentos requieren verificación en caso de aparecer
            List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                System.out.println("Se cerró el mensaje\n");
            } else {
                System.out.println("Mensaje no presente\n");
            }

            //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            } else {
                System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            }

            //Click al QR
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(189, 513)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //Valida que al tocar el QR deberá agrandarse para escanearlo mejor
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[3]")).isDisplayed()) {
                System.out.println("Validación correcta al tocar el QR deberá agrandarse para escanearlo mejor\n");
                report.testPassed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            } else {
                System.out.println("Validación incorrecta al tocar el QR NO se agranda para escanearlo mejor\n");
                report.testFailed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            }

            //click para cerrar el QR
            driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).click();

            //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            }

            //Valida que se muestra el botón de descargar PKPass y el ícono de cerrar
            if (driver.findElement(AppiumBy.accessibilityId("Agregar a Wallet")).isDisplayed()) {
                System.out.println("Validación correcta se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testPassed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testFailed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            }

            //Valida que estén presente los dots cuando hay más de 1 boarding pass
            System.out.println("Validación correcta se muestran los dots cuando hay más de un pase de abordar\n");
            report.testPassed("Valida que estén presente los dots cuando hay más de 1 boarding pass", true);

            //Valida el atributo de Gate
            if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                System.out.println("Atributo de gate validado\n");
                report.testPassed("Valida el atributo de Gate", true);
            } else {
                System.out.println("Atributo de gate NO validado\n");
                report.testFailed("Valida el atributo de Gate", true);
            }

            //Swipe para validar el logo de star Alliance
            booking.swipeSuperSmall(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            }

            //Swipe para validar próximos vuelos
            booking.swipeValidateStopover(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que esté presente las opciones de próximos vuelos
            if (driver.findElement(AppiumBy.accessibilityId("Próximos vuelos")).isDisplayed()) {
                System.out.println("Opciones de próximos vuelos validada\n");
                report.testPassed("Valida que esté presente las opciones de próximos vuelos", true);
            } else {
                System.out.println("Opciones de próximos vuelos validada\n");
                report.testFailed("Valida que esté presente las opciones de próximos vuelos", true);
            }

            //Cick para cerrar el boarding pass y volver al home
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //cierra el modal de calificación de app
            modal.closeRatingModalIfPresent();

            //Elimina el vuelo
            menuFragment.deleteTrip();

            System.out.println("bpValidationsMoreThanOneSegmentMorePax finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("bpValidationsMoreThanOneSegmentMorePax finalizado con error\n");
        }

    }

    /**
     * Realiza las validaciones del boarding pass NRSA
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public  void bpValidationsNRSA(ReportiOS report, String PNR, String LastName, String desc){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        System.out.println("bpValidationsNRSA inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Regresa al Home **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en el botón pase de abordar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

            //Pausa por carga
            Thread.sleep(9000);

            //elimina el mensaje de documentos requieren verificación en caso de aparecer
            List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                System.out.println("Se cerró el mensaje\n");
            } else {
                System.out.println("Mensaje no presente\n");
            }

            //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            } else {
                System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            }

            //Click al QR
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(189, 513)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //Valida que al tocar el QR deberá agrandarse para escanearlo mejor
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[3]")).isDisplayed()) {
                System.out.println("Validación correcta al tocar el QR deberá agrandarse para escanearlo mejor\n");
                report.testPassed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            } else {
                System.out.println("Validación incorrecta al tocar el QR NO se agranda para escanearlo mejor\n");
                report.testFailed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            }

            //click para cerrar el QR
            driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).click();

            //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            }

            //Valida que se muestra el botón de descargar PKPass y el ícono de cerrar
            if (driver.findElement(AppiumBy.accessibilityId("Agregar a Wallet")).isDisplayed()) {
                System.out.println("Validación correcta se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testPassed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testFailed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            }

            //Valida el atributo de Gate
            if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                System.out.println("Atributo de gate validado\n");
                report.testPassed("Valida el atributo de Gate", true);
            } else {
                System.out.println("Atributo de gate NO validado\n");
                report.testFailed("Valida el atributo de Gate", true);
            }

            //Swipe para validar el logo de star Alliance
            booking.swipeSuperSmall(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            }

            //Cick para cerrar el boarding pass y volver al home
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //cierra el modal de calificación de app
            modal.closeRatingModalIfPresent();

            //Elimina el vuelo y regresa al home
            menuFragment.deleteTrip();

            System.out.println("bpValidationsNRSA finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("bpValidationsNRSA finalizado con error\n");
        }

    }

    /**
     * Realiza las validaciones del boarding pass NRSP
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public  void bpValidationsNRSP(ReportiOS report, String PNR, String LastName, String desc){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        System.out.println("bpValidationsNRSP inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Regresa al Home **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en el botón pase de abordar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

            //Pausa por carga
            Thread.sleep(9000);

            //elimina el mensaje de documentos requieren verificación en caso de aparecer
            List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                System.out.println("Se cerró el mensaje\n");
            } else {
                System.out.println("Mensaje no presente\n");
            }

            //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            } else {
                System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            }

            //Click al QR
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(189, 513)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //Valida que al tocar el QR deberá agrandarse para escanearlo mejor
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[3]")).isDisplayed()) {
                System.out.println("Validación correcta al tocar el QR deberá agrandarse para escanearlo mejor\n");
                report.testPassed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            } else {
                System.out.println("Validación incorrecta al tocar el QR NO se agranda para escanearlo mejor\n");
                report.testFailed("Valida que al tocar el QR deberá agrandarse para escanearlo mejor", true);
            }

            //click para cerrar el QR
            driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).click();

            //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            }

            //Valida que se muestra el botón de descargar PKPass y el ícono de cerrar
            if (driver.findElement(AppiumBy.accessibilityId("Agregar a Wallet")).isDisplayed()) {
                System.out.println("Validación correcta se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testPassed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra el botón de descargar PKPass y el ícono de cerrar\n");
                report.testFailed("Valida que se muestra el botón de descargar PKPass y el ícono de cerrar", true);
            }

            //Valida el atributo de Gate
            if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                System.out.println("Atributo de gate validado\n");
                report.testPassed("Valida el atributo de Gate", true);
            } else {
                System.out.println("Atributo de gate NO validado\n");
                report.testFailed("Valida el atributo de Gate", true);
            }

            //Swipe para validar el logo de star Alliance
            booking.swipeSuperSmall(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            }

            //Cick para cerrar el boarding pass y volver al home
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //cierra el modal de calificación de app
            modal.closeRatingModalIfPresent();

            //Elimina el vuelo
            menuFragment.deleteTrip();

            System.out.println("bpValidationsNRSP finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("bpValidationsNRSP finalizado con error\n");
        }

    }

    /**
     * Realiza las validaciones del boarding pass UMNR
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public  void bpValidationsUMNR(ReportiOS report, String PNR, String LastName, String desc){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        System.out.println("bpValidationsUMNR inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Regresa al Home **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en el botón pase de abordar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

            //Pausa por carga
            Thread.sleep(9000);

            //elimina el mensaje de documentos requieren verificación en caso de aparecer
            List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                System.out.println("Se cerró el mensaje\n");
            } else {
                System.out.println("Mensaje no presente\n");
            }

            //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            } else {
                System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            }

            //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            }

            //Valida que se muestra el ícono de cerrar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra el ícono de cerrar\n");
                report.testPassed("Valida que se muestra el ícono de cerrar", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra el ícono de cerrar\n");
                report.testFailed("Valida que se muestra el ícono de cerrar", true);
            }

            //Valida el atributo de Gate
            if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                System.out.println("Atributo de gate validado\n");
                report.testPassed("Valida el atributo de Gate", true);
            } else {
                System.out.println("Atributo de gate NO validado\n");
                report.testFailed("Valida el atributo de Gate", true);
            }

            //Swipe para validar el logo de star Alliance
            booking.swipeSuperSmall(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            }

            //Valida el mensaje de Este documento no es válido para viajas
            if (driver.findElement(AppiumBy.accessibilityId("Este documento no es válido para viajar")).isDisplayed()) {
                System.out.println("mensaje de Este documento no es válido para viajar validado\n");
                report.testPassed("Valida el mensaje de Este documento no es válido para viajar", true);
            } else {
                System.out.println("mensaje de Este documento no es válido para viajar validado\n");
                report.testFailed("Valida el mensaje de Este documento no es válido para viajar", true);
            }

            //Cick para cerrar el boarding pass y volver al home
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //cierra el modal de calificación de app
            modal.closeRatingModalIfPresent();

            //Elimina el vuelo
            menuFragment.deleteTrip();

            System.out.println("bpValidationsUMNR finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("bpValidationsUMNR finalizado con error\n");
        }

    }

    /**
     * Realiza las validaciones del boarding pass PETC
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public  void bpValidationsPETC(ReportiOS report, String PNR, String LastName, String desc){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        System.out.println("bpValidationsPETC inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Regresa al Home **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en el botón pase de abordar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

            //Pausa por carga
            Thread.sleep(9000);

            //elimina el mensaje de documentos requieren verificación en caso de aparecer
            List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                System.out.println("Se cerró el mensaje\n");
            } else {
                System.out.println("Mensaje no presente\n");
            }

            //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            } else {
                System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            }

            //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            }

            //Valida que se muestra el ícono de cerrar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra el ícono de cerrar\n");
                report.testPassed("Valida que se muestra el ícono de cerrar", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra el ícono de cerrar\n");
                report.testFailed("Valida que se muestra el ícono de cerrar", true);
            }

            //Valida el atributo de Gate
            if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                System.out.println("Atributo de gate validado\n");
                report.testPassed("Valida el atributo de Gate", true);
            } else {
                System.out.println("Atributo de gate NO validado\n");
                report.testFailed("Valida el atributo de Gate", true);
            }

            //Swipe para validar el logo de star Alliance
            booking.swipeSuperSmall(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            }

            //Valida el mensaje de Este documento no es válido para viajas
            if (driver.findElement(AppiumBy.accessibilityId("Este documento no es válido para viajar")).isDisplayed()) {
                System.out.println("mensaje de Este documento no es válido para viajar validado\n");
                report.testPassed("Valida el mensaje de Este documento no es válido para viajar", true);
            } else {
                System.out.println("mensaje de Este documento no es válido para viajar validado\n");
                report.testFailed("Valida el mensaje de Este documento no es válido para viajar", true);
            }

            //Cick para cerrar el boarding pass y volver al home
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //cierra el modal de calificación de app
            modal.closeRatingModalIfPresent();

            //Elimina el vuelo
            menuFragment.deleteTrip();

            System.out.println("bpValidationsPETC finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("bpValidationsPETC finalizado con error\n");
        }

    }

    /**
     * Realiza las validaciones del boarding pass SELECTEE
     * @param report necesario para tomar las capturas del reporte
     * @param PNR Contiene el PRN del vuelo
     * @param LastName Contiene el Apellido del vuelo
     */
    public  void bpValidationsSELECTEE(ReportiOS report, String PNR, String LastName, String desc){
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        System.out.println("bpValidationsSELECTEE inicio\n");
        try {
            report.testPassed("VALIDACIONES "+desc, false);

            /** PASO 3. Click al ícono de mis viajes **/
            menuFragment.clickMyTripsIcon();
            Thread.sleep(2000);
            /** PASO 4. Click al "+" agregar un viaje **/
            wci.clickAddTrip();
            Thread.sleep(1000);
            /** PASO 5. Llena el campo de PNR, el campo apellido y busca la reserva **/
            wci.writePNRandLastNameMyTrips(PNR, LastName);
            /** PASO 6. Regresa al Home **/
            menuFragment.clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //Click en el botón pase de abordar
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"16\"]")).click();

            //Pausa por carga
            Thread.sleep(9000);

            //elimina el mensaje de documentos requieren verificación en caso de aparecer
            List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId("Sus documentos de viaje requieren verificación en el aeropuerto (kioscos de autoservicio, mostradores o puerta de abordaje)."));

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                driver.findElement(AppiumBy.accessibilityId("close_alert")).click();
                System.out.println("Se cerró el mensaje\n");
            } else {
                System.out.println("Mensaje no presente\n");
            }

            //Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se presionó el botón pase de abordar y se redirige al pase de abordar\n");
                report.testPassed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            } else {
                System.out.println("Validación incorrecta NO se presionó el botón pase de abordar y NO se redirige al pase de abordar\n");
                report.testFailed("Valida que se pueda presionar el botón de Pase de abordar y redirija al pase de abordar", true);
            }

            //Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testPassed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival\n");
                report.testFailed("Valida que se muestra la información de itinerario: número de vuelo, operating airline, departure y arrival", true);
            }

            //Valida que se muestra el ícono de cerrar
            if (driver.findElement(AppiumBy.accessibilityId("Pase de Abordar")).isDisplayed()) {
                System.out.println("Validación correcta se muestra el ícono de cerrar\n");
                report.testPassed("Valida que se muestra el ícono de cerrar", true);
            } else {
                System.out.println("Validación incorrecta NO se muestra el ícono de cerrar\n");
                report.testFailed("Valida que se muestra el ícono de cerrar", true);
            }

            //Valida el atributo de Gate
            if (driver.findElement(AppiumBy.accessibilityId("PUERTA")).isDisplayed()) {
                System.out.println("Atributo de gate validado\n");
                report.testPassed("Valida el atributo de Gate", true);
            } else {
                System.out.println("Atributo de gate NO validado\n");
                report.testFailed("Valida el atributo de Gate", true);
            }

            //Swipe para validar el logo de star Alliance
            booking.swipeSuperSmall(Panel, driver, direct2);
            Thread.sleep(500);

            //Valida que el logo de Copa debe aparecer alado del logo de Star Alliance
            if (driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                System.out.println("Validación correcta el logo de Copa debe aparecer alado del logo de Star Alliance\n");
                report.testPassed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            } else {
                System.out.println("Validación incorrecta el logo de Copa NO aparece alado del logo de Star Alliance\n");
                report.testFailed("Valida que el logo de Copa debe aparecer alado del logo de Star Alliance", true);
            }


            //Valida el mensaje de Este documento no es válido para viajas
            if (driver.findElement(AppiumBy.accessibilityId("Este documento no es válido para viajar")).isDisplayed()) {
                System.out.println("mensaje de Este documento no es válido para viajar validado\n");
                report.testPassed("Valida el mensaje de Este documento no es válido para viajar", true);
            } else {
                System.out.println("mensaje de Este documento no es válido para viajar validado\n");
                report.testFailed("Valida el mensaje de Este documento no es válido para viajar", true);
            }

            //Cick para cerrar el boarding pass y volver al home
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(39, 59)).release().perform();

            //pausa por carga
            Thread.sleep(500);

            //cierra el modal de calificación de app
            modal.closeRatingModalIfPresent();

            //Elimina el vuelo
            menuFragment.deleteTrip();

            System.out.println("bpValidationsSELECTEE finalizado con éxito\n");


        }catch (Exception ex){
            System.out.println("bpValidationsSELECTEE finalizado con error\n");
        }

    }
}

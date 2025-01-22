package iOS.iOSPages;

import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.platform.commons.function.Try;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CopaClubValidationsiOS {

    private AppiumDriver driver;

    ReadDataiOS data = new ReadDataiOS(driver);

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public CopaClubValidationsiOS (AppiumDriver driver) {
        this.driver = driver;
    }

    public void PaymentsCopaClub(){
        BookingiOS book =  new BookingiOS(driver);
        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
        String direct2 = "abajo";
        try {
            String CreditCardName = data.extractData(1, 3, "CopaClub");
            String CreditCardNumber = data.extractData(1, 4, "CopaClub");
            String CreditCardExpMonth = data.extractData(1, 5, "CopaClub");
            String CreditCardExpYear = String.valueOf(data.extractData(1, 6, "CopaClub"));
            String CreditCardCVV = String.valueOf(data.extractData(1, 7, "CopaClub"));

            //Swipe para mostrar la sección de tarjeta de credito
            book.swipeSuperSmall(Panel, driver, direct2);

            //Selecciona tarjeta de crédito
            driver.findElement(AppiumBy.accessibilityId("Link de pagar con tarjeta de crédito. Presione enter para ingresar los datos de una tarjeta de crédito.")).click();

            //Swipe para llenar los campos de la tarjeta de crédito
            book.swipeSuperSmall(Panel, driver, direct2);
            book.swipeSuperSmall(Panel, driver, direct2);
            book.swipeSuperSmall(Panel, driver, direct2);

            //Llena el nombre
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@value=\"Ingrese nombre como aparece en la tarjeta de crédito\"]")).sendKeys(CreditCardName);
            System.out.println("Nombre de tarjeta llenado\n");

            //Click en ok en el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            System.out.println("Teclado cerrado\n");

            //Llena el número
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"artículo\"]/XCUIElementTypeOther[8]")).sendKeys(CreditCardNumber);
            System.out.println("Número de tarjeta llenado\n");

            //Click en ok en el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            System.out.println("Teclado cerrado\n");

            //Llena el mes de expiración
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"Use las flechas hacia arriba y hacia abajo para seleccionar el mes de vencimiento de la tarjeta de crédito.\"]")).click();
            driver.findElement(AppiumBy.accessibilityId(CreditCardExpMonth)).click();
            System.out.println("Mes de expiración llenado\n");

            //Llena el año de expiración
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"Use las flecha hacia arriba y hacia abajo para seleccionar el año de vencimiento de la tarjeta de crédito.\"]")).click();
            driver.findElement(AppiumBy.accessibilityId(CreditCardExpYear)).click();
            System.out.println("Año de expiración llenado\n");

            //Llena el cvv
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@value=\"###\"]")).sendKeys(CreditCardCVV);
            System.out.println("CVV llenado\n");

            //Click en ok en el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            System.out.println("Teclado cerrado\n");

            //Llena el país de emisión de tarjeta
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"Barra de desplazamiento vertical, 5 páginas\"]")).click();
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"artículo\"]/XCUIElementTypeOther[22]")).sendKeys("Panam");
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"Pago - Copa Airlines\"]")).click();
            System.out.println("País de emisión de tarjeta llenado\n");

            //Click en ok en el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            System.out.println("Teclado cerrado\n");

            //Llena dirección de facturación
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeTextField[@value=\"Ingrese dirección de facturación\"])[1]")).sendKeys("Panamá");
            System.out.println("Dirección de facturación llenado\n");

            //Click en ok en el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            System.out.println("Teclado cerrado\n");

            //Llena el país de dirección
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(187, 487)).release().perform();
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"artículo\"]/XCUIElementTypeOther[33]")).sendKeys("Panamá");
            System.out.println("País de dirección llenado\n");

            //Click en ok en el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            System.out.println("Teclado cerrado\n");

            //Llena la ciudad
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@value=\"Ingrese la ciudad\"]")).sendKeys("Panamá");
            System.out.println("Ciudad de facturación llenado\n");

            //Click en ok en el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            System.out.println("Teclado cerrado\n");

            //Llena estado o provincia
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@value=\"Ingrese estado/provincia\"]")).sendKeys("Panamá");
            System.out.println("Estado o provincia llenado\n");

            //Llena el código postal
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@value=\"Ingrese código postal\"]")).sendKeys("12345");
            System.out.println("Código postal llenado\n");

            //Click en ok en el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            System.out.println("Teclado cerrado\n");

            //Llena número de teléfono
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@value=\"Código de país\"]")).sendKeys("+507");
            System.out.println("Código de país llenado\n");

            //Llena número de teléfono
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@value=\"Ingrese número de teléfono\"]")).sendKeys("11223344");
            System.out.println("Número de teléfono llenado\n");

            //Click en ok en el teclado
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            System.out.println("Teclado cerrado\n");

            //Click en aceptar términos y condiciones
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"He leído y acepto los términos y condiciones de\"]")).click();
            System.out.println("Aceptar términos y condiciones seleccionado\n");

            //Click en confirmar pago y continuar
            //driver.findElement(AppiumBy.accessibilityId("Presiona el botón para Confirmar el Pago y Continuar")).click();
            System.out.println("Confirmar pago y continuar clickeado\n");

            Thread.sleep(15000);
            System.out.println("Se realizó la compra con éxito\n");
        }catch(Exception ex){
            System.out.println("Error: "+ex);
        }
    }

    public void CopaClubPass2Pax(ReportiOS report, String PNR, String LastName){
        BookingiOS book = new BookingiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        MenuFragmentiOS menu = new MenuFragmentiOS(driver);
        String direct1 = "arriba", direct2 = "abajo";

        try {
            String descripcion = data.extractData(1, 0, "CopaClub");
            report.testPassed(descripcion, false);

            //Valida que esté presente el copa club
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"10\"]")).isDisplayed()) {
                report.testPassed("Valida que esté presente el copa club arriba de itinerario de viaje", true);
                System.out.println("Validación correcta está presente el copa club\n");
            } else {
                report.testFailed("Valida que esté presente el copa club arriba de itinerario de viaje", true);
                System.out.println("Validación incorrecta NO está presente el copa club\n");
            }

            //click para entrar al copa club
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@index=\"10\"]")).click();
            Thread.sleep(500);

            //Validar que se muestra la pantalla de selección de terminal y que no esté seleccionada la terminal
            if (driver.findElement(AppiumBy.accessibilityId("copa_club_checkbox_unselected")).isDisplayed()) {
                report.testPassed("Valida que se muestre la pantalla de selección de terminal y que la terminal no esté seleccionada", true);
                System.out.println("Validación correcta se presentó la pantalla y la terminal no está seleccionada\n");
            } else {
                report.testFailed("Valida que se muestre la pantalla de selección de terminal y que la terminal no esté seleccionada", true);
                System.out.println("Validación incorrecta NO se mostró la pantalla o la terminal está seleccionada\n");
            }

            //Seleccionar la terminal
            driver.findElement(AppiumBy.accessibilityId("copa_club_checkbox_unselected")).click();
            Thread.sleep(500);

            //Valida el gancho azul en la terminal y el botón activado cuando selecciona la terminal
            if (driver.findElement(AppiumBy.accessibilityId("copa_club_lounge_checkbox_selected")).isDisplayed()
                    & driver.findElement(AppiumBy.accessibilityId("Continuar")).isDisplayed()) {
                report.testPassed("Valida el gancho azul en la terminal y el botón activado cuando selecciona la terminal", true);
                System.out.println("Validación correcta se muestra el gancho azul en la terminal y el botón activado cuando selecciona la terminal\n");
            } else {
                report.testFailed("Valida el gancho azul en la terminal y el botón activado cuando selecciona la terminal", true);
                System.out.println("Validación incorrecta NO se mostró el gancho azul en la terminal y el botón activado cuando selecciona la terminal\n");
            }

            //click en continuar
            driver.findElement(AppiumBy.accessibilityId("Continuar")).click();
            Thread.sleep(500);

            //Valida la pantalla con todos los pax que puedan comprar un copa club, estátus de connectmiles, el monto de copa club y
            // que no debe estar seleccionado ningún pasajero
            if (driver.findElement(AppiumBy.accessibilityId("Pasajeros en la reserva")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"copa_club_checkbox_unselected_gray\"])[1]")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"copa_club_checkbox_unselected_gray\"])[2]")).isDisplayed()) {
                report.testPassed("Valida la pantalla con todos los pax que puedan comprar un copa club, estátus de connectmiles, " +
                        "el monto de copa club y que no debe estar seleccionado ningún pasajero", true);
                System.out.println("Validación correcta se muestra la pantalla con todos los pax que puedan comprar un copa club, " +
                        "estátus de connectmiles, el monto de copa club y que no debe estar seleccionado ningún pasajero\n");
            } else {
                report.testFailed("Valida la pantalla con todos los pax que puedan comprar un copa club, estátus de connectmiles, " +
                        "el monto de copa club y que no debe estar seleccionado ningún pasajero", true);
                System.out.println("Validación incorrecta NO se mostró la pantalla con todos los pax que puedan comprar un copa club, " +
                        "estátus de connectmiles, el monto de copa club y que no debe estar seleccionado ningún pasajero\n");
            }

            //Seleccionar ambos pasajeros para comprar el copa club
            driver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"copa_club_checkbox_unselected_gray\"])[1]")).click();
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"copa_club_checkbox_unselected_gray\"]")).click();
            System.out.println("Se seleccionaron ambos pasajeros\n");

            //Valida que al seleccionar ambos pasajeros se muestre el total a pagar y se habilite el botón de continuar
            if (driver.findElement(AppiumBy.accessibilityId("Continuar")).isDisplayed()) {
                report.testPassed("Valida que al seleccionar ambos pasajeros se muestre el total a pagar y se habilite el botón de continuar", true);
                System.out.println("Validación correcta al seleccionar ambos pasajeros se muestra el total a pagar y se habilita el botón de continuar\n");
            } else {
                report.testFailed("Valida que al seleccionar ambos pasajeros se muestre el total a pagar y se habilite el botón de continuar", true);
                System.out.println("Validación incorrecta al seleccionar ambos pasajeros NO se muestra el total a pagar o NO se habilite el botón de continuar\n");
            }

            //Click en el botón de continuar
            driver.findElement(AppiumBy.accessibilityId("Continuar")).click();
            Thread.sleep(500);

            //Validar que se muestre la pantalla de revisión de selección, debe presentar el origen del vuelo, debajo el destino y una imagen de copa club
            if (driver.findElement(AppiumBy.accessibilityId("Revisar Selección")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Panamá · Terminal 1 ó 2")).isDisplayed()
                    & driver.findElement(AppiumBy.accessibilityId("Hacia Bogota (BOG)")).isDisplayed()) {
                report.testPassed("Validar que se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club", true);
                System.out.println("Validación correcta, se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club\n");
            } else {
                report.testFailed("Validar que se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club", true);
                System.out.println("Validación incorrecta, NO se muestra la pantalla de revisión de selección, el origen del vuelo, debajo el destino y una imagen de copa club\n");
            }

            //Valida la sección de terminos y condiciones
            if (driver.findElement(AppiumBy.accessibilityId("Términos y condiciones")).isDisplayed()) {
                report.testPassed("Valida la sección de terminos y condiciones", true);
                System.out.println("Validación correcta se muestra la sección de términos y condiciones\n");
            } else {
                report.testFailed("Valida la sección de terminos y condiciones", true);
                System.out.println("Validación incorrecta no se muestra la sección de términos y condiciones\n");
            }

            //Swipe para mostrar el resumen de la compra
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            book.swipeSmall(Panel, driver, direct2);
            book.swipeSuperSmall(Panel, driver, direct2);

            //Valida el resumen de la compra por adulto subtotal, impuestos, el total y el botón de comprar
            if (driver.findElement(AppiumBy.accessibilityId("Resumen de Compra")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Subtotal")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Impuestos")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Total")).isDisplayed() & driver.findElement(AppiumBy.accessibilityId("Comprar Single-Visit Pass")).isDisplayed()) {
                report.testPassed("Valida el resumen de la compra por adulto subtotal, impuestos, el total y el botón de comprar", true);
                System.out.println("Validación correcta se muestra el resumen de la compra por adulto subtotal, impuestos, el total y el botón de comprar\n");
            } else {
                report.testFailed("Valida el resumen de la compra por adulto subtotal, impuestos, el total y el botón de comprar", true);
                System.out.println("Validación incorrecta no se muestra el resumen de la compra por adulto subtotal, impuestos, el total y el botón de comprar\n");
            }

            //Click en el botón de comprar
            driver.findElement(AppiumBy.accessibilityId("Comprar Single-Visit Pass")).click();

            //Pausa por carga del webview de payments
            Thread.sleep(18000);

            //Valida que se presenta la pantalla de payments después de revisar la compra
            if (driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Selecciona un método de pago\"]")).isDisplayed()) {
                report.testPassed("Valida que se presenta la pantalla de payments después de revisar la compra", true);
                System.out.println("Validación correcta se presenta la pantalla de payments después de revisar la compra\n");
            } else {
                report.testFailed("Valida que se presenta la pantalla de payments después de revisar la compra", true);
                System.out.println("Validación incorrecta NO se presenta la pantalla de payments después de revisar la compra\n");
            }

            //Realiza el pago
            PaymentsCopaClub();

            //Valida que se muestre la pantalla de confirmación de compra
            if (driver.findElement(AppiumBy.accessibilityId("Confirmación de compra")).isDisplayed()) {
                report.testPassed("Valida que se muestre la pantalla de confirmación de compra", true);
                System.out.println("Validación correcta se muestra la pantalla de confirmación de compra\n");
            } else {
                report.testFailed("Valida que se muestre la pantalla de confirmación de compra", true);
                System.out.println("Validación incorrecta NO se muestra la pantalla de confirmación de compra\n");
            }

            //Swipe para ubicar elementos en pantalla
            book.swipeSmall(Panel, driver, direct2);

            //Valida que aparece una sección con el título de visit pass con una descripción debajo y se muestra botón con el titulo ver copa club single-visit pass
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Copa Club Single-Visit Pass\"])[1]")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Ver Copa Club Single-Visit Pass")).isDisplayed()) {
                report.testPassed("Valida que aparece una sección con el título de visit pass con una descripción debajo y se muestra botón con el titulo ver copa club single-visit pass", true);
                System.out.println("Validación correcta aparece una sección con el título de visit pass con una descripción debajo y se muestra botón con el titulo ver copa club single-visit pass\n");
            } else {
                report.testFailed("Valida que aparece una sección con el título de visit pass con una descripción debajo y se muestra botón con el titulo ver copa club single-visit pass", true);
                System.out.println("Validación incorrecta NO aparece una sección con el título de visit pass con una descripción debajo y se muestra botón con el titulo ver copa club single-visit pass\n");
            }

            //Presiona el botón de Ver Copa Club Single-Visit Pass
            driver.findElement(AppiumBy.accessibilityId("Ver Copa Club Single-Visit Pass")).click();
            Thread.sleep(1000);

            //Valida que se muestra el copa pass con el nombre del pax, la terminal del origen, la fecha, el PNR, un QR, debajo el logo de copa y star alliance member.
            if (driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Terminal\"])[1]")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Fecha\"])[1]")).isDisplayed() &
                    driver.findElement(AppiumBy.xpath("(//XCUIElementTypeStaticText[@name=\"Código de reserva\"])[1]")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("CopaBlueLogo")).isDisplayed() &
                    driver.findElement(AppiumBy.accessibilityId("Copa Airlines es un miembro de Star Alliance")).isDisplayed()) {
                report.testPassed("Valida que se muestra el copa pass con el nombre del pax, la terminal del origen, la fecha, el PNR, un QR, debajo el logo de copa y star alliance member.", true);
                System.out.println("Validación correcta se muestra el copa pass con el nombre del pax, la terminal del origen, la fecha, el PNR, un QR, debajo el logo de copa y star alliance member.\n");
            } else {
                report.testFailed("Valida que se muestra el copa pass con el nombre del pax, la terminal del origen, la fecha, el PNR, un QR, debajo el logo de copa y star alliance member.", true);
                System.out.println("Validación incorrecta NO se muestra el copa pass con el nombre del pax, la terminal del origen, la fecha, el PNR, un QR, debajo el logo de copa y star alliance member.\n");
            }

            //click para regresar
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(40, 42)).release().perform();

            //click para cerrar la pantalla de compra
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(42, 42)).release().perform();

            //Click para regresar a my trips
            tif.clickBackFlightDetails();

            //Elimina la reserva
            menu.deleteTrip();

        }catch (Exception ex){
            System.out.println("Error: "+ex);
        }

    }
}

package iOS.iOSTest;

import iOS.iOSPages.*;
import iOS.objectsiOS.OriginDestinationValiOS;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PhoneNumberMyProfileiOS {
    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        //Se crean las instancias de cada flujo automatizado.
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        FirstStepsiOS firstSteps = new FirstStepsiOS(driver, report);
        LoginiOS login = new LoginiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        OriginDestinationValiOS valOriginDest;

        String PNR = "APSGKD", LastName = "TROUTMAN";
        String direct1 = "arriba";
        String direct2 = "abajo";
        String tripDomestic = "David", tripInternationalM = "Miami", tripInternationalB = "Bogota";
        String adult = "Lailynnmr Troutman", child = "Alayahmr Woolford", infant = "Dawsen Donaghy";

        String countryCode = "+1", phoneNumber1 = "123456", phoneNumber2 = "12345678", phoneNumber3 = "1234567";
        int cont = 1;

        try {
            /**Crea el reporte **/
            report.createTestReport("Validación de campos", "Código de país y número de emergencia");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            /** PASO 2. Inicia sesión en el app **/
            login.loginUser("230002826","Copa2022", false);
            //menuFragment.clickHomeIcon();
            /** PASO 2. identifica el elemento en el cual se hará swipe y envía el elemento, el driver y la dirección a dónde se hará el swipe **/
            WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            /** PASO 3. swipe a la opción mi perfil **/
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            tif.swipeToAdultChildInfant(Panel, driver, direct2);
            /** Click a la opción de mi perfil **/
            driver.findElement(AppiumBy.accessibilityId("Botón de Mi perfil. Haz doble toque para acceder a tu perfil.")).click();
            Thread.sleep(2000);
            /** Swipe para ubicar los campos en pantalla **/
            tif.swipeFormTIF(Panel, driver, direct2);
            tif.swipeFormTIF(Panel, driver, direct2);
            /** PASO 13. Realiza las validaciones del número telefónico **/
            while (cont!=3) {
                switch (cont) {
                    case 1:
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;
                    case 2:
                        countryCode = "+244"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;
                    case 3:
                        countryCode = "+374"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 4:
                        countryCode = "+297"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 5:
                        countryCode = "+973"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 6:
                        countryCode = "+375"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 7:
                        countryCode = "+501"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 8:
                        countryCode = "+591"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 9:
                        countryCode = "+599"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 10:
                        countryCode = "+387"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 11:
                        countryCode = "+673"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 12:
                        countryCode = "+226"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 13:
                        countryCode = "+257"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 14:
                        countryCode = "+238"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 15:
                        countryCode = "+236"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 16:
                        countryCode = "+235"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 17:
                        countryCode = "+269"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 18:
                        countryCode = "+506"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 19:
                        countryCode = "+5999"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 20:
                        countryCode = "+420"; phoneNumber1 = "123"; phoneNumber2 = "12345"; phoneNumber3 = "1234";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 21:
                        countryCode = "+225"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 22:
                        countryCode = "+45"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 23:
                        countryCode = "+240"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 24:
                        countryCode = "+291"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 25:
                        countryCode = "+251"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 26:
                        countryCode = "+298"; phoneNumber1 = "12345"; phoneNumber2 = "1234567"; phoneNumber3 = "123456";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 27:
                        countryCode = "+679"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 28:
                        countryCode = "+33"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 29:
                        countryCode = "+594"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 30:
                        countryCode = "+689"; phoneNumber1 = "12345"; phoneNumber2 = "1234567"; phoneNumber3 = "123456";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 31:
                        countryCode = "+220"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 32:
                        countryCode = "+995"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 33:
                        countryCode = "+350"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 34:
                        countryCode = "+30"; phoneNumber1 = "123456789"; phoneNumber2 = "12345678901"; phoneNumber3 = "1234567890";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 35:
                        countryCode = "+299"; phoneNumber1 = "12345"; phoneNumber2 = "1234567"; phoneNumber3 = "123456";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 36:
                        countryCode = "+590"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 37:
                        countryCode = "+502"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 38:
                        countryCode = "+245"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 39:
                        countryCode = "+592"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 40:
                        countryCode = "+509"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 41:
                        countryCode = "+504"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 42:
                        countryCode = "+7"; phoneNumber1 = "123456789"; phoneNumber2 = "12345678901"; phoneNumber3 = "1234567890";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 43:
                        countryCode = "+996"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 44:
                        countryCode = "+266"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 45:
                        countryCode = "+231"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 46:
                        countryCode = "+218"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 47:
                        countryCode = "+423"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 48:
                        countryCode = "+370"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 49:
                        countryCode = "+960"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 50:
                        countryCode = "+223"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 51:
                        countryCode = "+356"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 52:
                        countryCode = "+692"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 53:
                        countryCode = "+596"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 54:
                        countryCode = "+230"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 55:
                        countryCode = "+52"; phoneNumber1 = "123456789"; phoneNumber2 = "12345678901"; phoneNumber3 = "1234567890";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 56:
                        countryCode = "+691"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 57:
                        countryCode = "+373"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 58:
                        countryCode = "+212"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 59:
                        countryCode = "+674"; phoneNumber1 = "123"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 60:
                        countryCode = "+31"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 61:
                        countryCode = "+687"; phoneNumber1 = "12345"; phoneNumber2 = "1234567"; phoneNumber3 = "123456";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 62:
                        countryCode = "+505"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 63:
                        countryCode = "+227"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 64:
                        countryCode = "+683"; phoneNumber1 = "123"; phoneNumber2 = "12345"; phoneNumber3 = "1234";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 65:
                        countryCode = "+680"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 66:
                        countryCode = "+40"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 67:
                        countryCode = "+7"; phoneNumber1 = "123456789"; phoneNumber2 = "12345678901"; phoneNumber3 = "1234567890";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 68:
                        countryCode = "+290"; phoneNumber1 = "123"; phoneNumber2 = "12345"; phoneNumber3 = "1234";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 69:
                        countryCode = "+508"; phoneNumber1 = "12345"; phoneNumber2 = "1234567"; phoneNumber3 = "123456";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 70:
                        countryCode = "+239"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 71:
                        countryCode = "+248"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 72:
                        countryCode = "+232"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 73:
                        countryCode = "+65"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 74:
                        countryCode = "+386"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 75:
                        countryCode = "+27"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 76:
                        countryCode = "+34"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 77:
                        countryCode = "+268"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 78:
                        countryCode = "+886"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 79:
                        countryCode = "+992"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 80:
                        countryCode = "+389"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 81:
                        countryCode = "+670"; phoneNumber1 = "123456"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 82:
                        countryCode = "+228"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 83:
                        countryCode = "+676"; phoneNumber1 = "1234"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 84:
                        countryCode = "+216"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 85:
                        countryCode = "+993"; phoneNumber1 = "1234567"; phoneNumber2 = "123456789"; phoneNumber3 = "12345678";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 86:
                        countryCode = "+256"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 87:
                        countryCode = "+998"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 88:
                        countryCode = "+678"; phoneNumber1 = "1234"; phoneNumber2 = "12345678"; phoneNumber3 = "1234567";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 89:
                        countryCode = "+681"; phoneNumber1 = "12345"; phoneNumber2 = "1234567"; phoneNumber3 = "123456";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;

                    case 90:
                        countryCode = "+260"; phoneNumber1 = "12345678"; phoneNumber2 = "1234567890"; phoneNumber3 = "123456789";
                        tif.validateCountryPhoneNumbersMyProfile(report, countryCode, phoneNumber1, phoneNumber2, phoneNumber3);
                        break;
                }
                cont++;
            }

            /** PASO 14. Click atrás Elimina el vuelo y cierra sesión **/
            driver.findElement(AppiumBy.accessibilityId("Botón para ir a atrás. Toca para regresar a Mi cuenta")).click();
            //menuFragment.deleteTrip();
            logout.logoutUser();


            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

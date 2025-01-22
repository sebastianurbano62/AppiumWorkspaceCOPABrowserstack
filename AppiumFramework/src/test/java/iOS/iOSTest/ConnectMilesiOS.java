package iOS.iOSTest;

import dataSet.DataBase;
import iOS.iOSPages.*;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ConnectMilesiOS {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        MenuFragmentiOS menuFragment = new MenuFragmentiOS(driver);
        BookingiOS booking = new BookingiOS(driver);
        LoginiOS login = new LoginiOS(driver);
        LogoutiOS logout = new LogoutiOS(driver);
        TIFValidationsiOS tif = new TIFValidationsiOS(driver);
        CheckiniOS wci = new CheckiniOS(driver);
        ConnectMilesValidationsiOS connect = new ConnectMilesValidationsiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);

        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

        String direct1 = "arriba";
        String direct2 = "abajo";
        Double user = data.extractUserCM(1, 1, "ConnectMiles");
        String password = data.extractData(1, 2, "ConnectMiles");
        String accountName = data.extractData(1, 3, "ConnectMiles"),
                pill = data.extractData(1, 4, "ConnectMiles"), tierCMCard = data.extractData(1, 5, "ConnectMiles");
        String PNR = data.extractData(1, 6, "ConnectMiles"), LastName = data.extractData(1, 7, "ConnectMiles");

        int index = 1;

        try {

            /**Crea el reporte **/
            report.createTestReport("Connectmiles Validaciones", "Realiza las validaciones del Módulo ConnectMiles");
            /** PASO 1. Se invoca la clase **/
            //firstSteps.skipFirstSteps();
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. Realiza las validaciones **/
            connect.LogInModalValidations(report, String.format("%.0f", user));
            while(index<6) { // Loop necesario para ejecutar con cada cuenta connect miles
                /** PASO 3. Realiza las validaciones  **/
                connect.dashBoardAndMembershipCardValidations(report, accountName, String.format("%.0f", user), password, pill, tierCMCard);
                index++;
                switch (index) {
                    case 2:
                        user = data.extractUserCM(2, 1, "ConnectMiles");
                        password = data.extractData(2, 2, "ConnectMiles");
                        accountName = data.extractData(2, 3, "ConnectMiles");
                        pill = data.extractData(2, 4, "ConnectMiles");
                        tierCMCard = data.extractData(2, 5, "ConnectMiles");
                        login.loginUser(String.format("%.0f", user), password, false);
                        break;
                    case 3:
                        user = data.extractUserCM(3, 1, "ConnectMiles");
                        password = data.extractData(3, 2, "ConnectMiles");
                        accountName = data.extractData(3, 3, "ConnectMiles");
                        pill = data.extractData(3, 4, "ConnectMiles");
                        tierCMCard = data.extractData(3, 5, "ConnectMiles");
                        login.loginUser(String.format("%.0f", user), password, false);
                        break;
                    case 4:
                        user = data.extractUserCM(4, 1, "ConnectMiles");
                        password = data.extractData(4, 2, "ConnectMiles");
                        accountName = data.extractData(4, 3, "ConnectMiles");
                        pill = data.extractData(4, 4, "ConnectMiles");
                        tierCMCard = data.extractData(4, 5, "ConnectMiles");
                        login.loginUser(String.format("%.0f", user), password, false);
                        break;
                    case 5:
                        user = data.extractUserCM(5, 1, "ConnectMiles");
                        password = data.extractData(5, 2, "ConnectMiles");
                        accountName = data.extractData(5, 3, "ConnectMiles");
                        pill = data.extractData(5, 4, "ConnectMiles");
                        tierCMCard = data.extractData(5, 5, "ConnectMiles");
                        login.loginUser(String.format("%.0f", user), password, false);
                        break;
                }
            }
            /** PASO 3. Realiza login **/
            user = data.extractUserCM(1, 1, "ConnectMiles"); password = data.extractData(1, 2, "ConnectMiles");
            login.loginUser(String.format("%.0f", user), password, false);
            /** PASO 4. Realiza las validaciones **/
            connect.activityLogValidations(report); //hay validaciones en el método comentadas
            /** PASO 5. Regresa al Home **/
            menuFragment.clickHomeIcon();
            /** PASO 6. Realiza las validaciones **/
            connect.otrosValidations(report, PNR, LastName, String.format("%.0f", user));
            /** PASO 7. Cierra la sesión **/
            logout.logoutUser();
            /** PASO 8. Realiza las validaciones **/
            connect.loyaltyLoginEnhancements(report, String.format("%.0f", user));
            /** PASO 9. Realiza las validaciones **/
            connect.editProfileDeleteAccountProfileExtendedServiceProfilePageErrorEnhancementServiceIntegrationsValidations(report);
            /** PASO 10. Realiza las validaciones **/
            connect.buyMilesAndPointOfEntryValidations(report);
            /** PASO 11. Realiza las validaciones **/
            connect.milesManagementWebviewValdiations(report);
            /** PASO 12. Realiza las validaciones **/
            connect.newMilesExpirationLabelValdiations(report);
            /** PASO 13. Realiza las validaciones **/
            connect.newCreditCardPromotionalBannerValidations(report); //corregir validación del texto a dos líneas
            /** PASO 14. Realiza las validaciones **/
            connect.logicForLoggedInUsersValidations(report);
            /** PASO 15. Realiza las validaciones **/
            connect.copyConnectMilesNumberValidations(report);
            /** PASO 16. Realiza las validaciones **/
            connect.generalEnhancementsValidations(report);
            /** Cierra sesión y termina las validaciones **/
            logout.logoutUser();

            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

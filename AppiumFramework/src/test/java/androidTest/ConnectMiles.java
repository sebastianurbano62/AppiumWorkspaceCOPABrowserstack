package androidTest;

import androidPages.*;
import dataSet.DataBase;
import io.appium.java_client.AppiumDriver;
import utils.Driver;
import utils.RatingModalCheck;
import utils.Report;
public class ConnectMiles {
    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        //WebDriver proxyDriver = DriverFactoryAndroid.createDriverWithConditionCheck(driver);

        DataBase db = new DataBase();

        RatingModalCheck modal = new RatingModalCheck(driver);

        //Se crean las instancias de cada flujo automatizado.
        ConnectMilesValidations connect = new ConnectMilesValidations(driver);
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        ReadData data = new ReadData(driver);

        String PNR = data.extractData(1, 6, "ConnectMiles");
        String LastName = data.extractData(1, 7, "ConnectMiles");
        double user = data.extractUserCM(1, 1, "ConnectMiles");
        String password = data.extractData(1, 2, "ConnectMiles");

        double userS = data.extractUserCM(2, 1, "ConnectMiles");
        String passwordS = data.extractData(2, 2, "ConnectMiles");

        double userG = data.extractUserCM(3, 1, "ConnectMiles");
        String passwordG = data.extractData(3, 2, "ConnectMiles");

        double  userPl = data.extractUserCM(4, 1, "ConnectMiles");
        String passwordPl = data.extractData(4, 2, "ConnectMiles");

        double userPr = data.extractUserCM(5, 1, "ConnectMiles");
        String passwordPr = data.extractData(5, 2, "ConnectMiles");

        String correo = data.extractData(1, 8, "ConnectMiles");

        try {

            /**Crea el reporte **/
            report.createTestReport("Validaciones", "Realiza las validaciones del Módulo ConnectMiles");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            Thread.sleep(1000);
            /** PASO 2. Realiza las validacionesLogInModal **/
            modal.closeRatingModalIfPresent();
            Thread.sleep(5000);
            //connect.LogInModalValidations(report, String.format("%.0f", user), password);
            /** PASO 3. Realiza las validaciones de Dashboard **/
            //connect.dashBoardAndMembershipCardValidations(report, PNR, LastName, String.format("%.0f", user), password, String.format("%.0f", userS), passwordS, String.format("%.0f", userG), passwordG, String.format("%.0f", userPl), passwordPl, String.format("%.0f", userPr), passwordPr);
          /** PASO 4. Realiza las validaciones de Activity Log**/
            //connect.activityLogValidations(report, String.format("%.0f", user), password);
            /** PASO 5. Realiza validaciones de Otros**/
            //connect.otrosValidations(report, PNR, LastName, String.format("%.0f", user), password);
            /** PASO 6. Realiza validaciones de Loyalty**/
            //connect.loyaltyValidations(report, correo, password);
            /** PASO 7. Realiza validaciones de edit Profile y Profile extend**/
            //connect.editProfileandprofileExtendedServiceValidations(report, String.format("%.0f", user), password);
           /** PASO 8. Realiza validaciones de profile Page Enhacement**/
            //connect.profilePageEnhacementValidations(report);
            /** PASO 9. Realiza validaciones de new Buy Miles Webview**/
            //connect.newBuyMilesWebviewValidations(report);
            /** PASO 10. Realiza validaciones de miles Management Webview**/
            //connect.milesManagementWebviewValidations(report);
            /** PASO 11. Realiza validaciones de new miles expiration label**/
            //connect.newMilesExpirationLabelValidations(report);
            /** PASO 12. Realiza validaciones de new credit card promotional banner**/
            //connect.newCreditCardPromotionalBannerValidations(report);
            /** PASO 13. Realiza validaciones de logic for logged in users**/
            connect.logicForLoggedInUsersValidations(report, String.format("%.0f", user), password);
            /** PASO 14. Realiza validaciones de My Account Copy Connect Miles Number**/
               //connect.myAccountCopyConnectMilesNumberValidations(report);
            /** PASO 15. Realiza validaciones de General Enhacements**/
            //connect.generalEnhacementsValidations(report);
            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

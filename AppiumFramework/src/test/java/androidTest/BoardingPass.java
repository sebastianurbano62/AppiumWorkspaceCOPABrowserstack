package androidTest;

import androidPages.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import objects.OriginDestinationVal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;
import utils.GeneratedUtils;
import utils.RatingModalCheck;
import utils.Report;

public class BoardingPass {
    public static void main(String[] args) throws Throwable {
        // Se crea la instancia para el driver de Appium
        AppiumDriver driver = Driver.getAndroidDriver();

        RatingModalCheck modal = new RatingModalCheck(driver);

        // Se crean las instancias de cada flujo automatizado.
        Report report = new Report(driver);
        FirstSteps firstSteps = new FirstSteps(driver, report);

        MenuFragment menuFragment = new MenuFragment(driver);
        Booking booking = new Booking(driver);
        Checkin wci = new Checkin(driver);
        TIFValidations tif = new TIFValidations(driver);
        Login login = new Login(driver);
        Logout logout = new Logout(driver);
        BoardingPassValidations board = new BoardingPassValidations(driver);

        ReadData data = new ReadData(driver);

        //Descripciónes para el reporte
        String desc = data.extractData(1,0, "boardingPass");

        //reservas creadas con los tier Member, Silver, Gold, Platinum y Presidential
        String memberPNR = data.extractData(1, 1, "BoardingPass"),  memberLN = data.extractData(1, 2, "BoardingPass"),
                silverPNR = data.extractData(2, 1, "BoardingPass"), silverLN = data.extractData(2, 2, "BoardingPass"),
                goldPNR = data.extractData(3, 1, "BoardingPass"), goldLN = data.extractData(3, 2, "BoardingPass");

        String platinumPNR = data.extractData(4, 1, "BoardingPass"), platinumLN = data.extractData(4, 2, "BoardingPass"),
                presidentialPNR = data.extractData(5, 1, "BoardingPass"), presidentialLN = data.extractData(5, 2, "BoardingPass");
        //vuelos con las clases Business, Classic y Basic
        String businessPNR = data.extractData(7, 1, "BoardingPass"), businessLN = data.extractData(7, 2, "BoardingPass"),
                classicPNR = data.extractData(8, 1, "BoardingPass"), classicLN = data.extractData(8, 2, "BoardingPass"),
                basicPNR = data.extractData(9, 1, "BoardingPass"), basicLN = data.extractData(9, 2, "BoardingPass");
        //vuelo con dos adultos y un infante
        String adtifnPNR = data.extractData(11, 1, "BoardingPass"), adtifnLN = data.extractData(11, 2, "BoardingPass");
        //vuelo con 2 segementos o más en ventana de checkin y 1 pasajero (Multiciudad)
        String onepaxPNR = data.extractData(13, 1, "BoardingPass"), onepaxLN = data.extractData(13, 2, "BoardingPass");
        //vuelo con 2 segementos o más en ventana de checkin y más de 1 pasajero (Multiciudad)
        String twopaxPNR = data.extractData(15, 1, "BoardingPass"), twopaxLN = data.extractData(15, 2, "BoardingPass");
        //Reservas NRSA, NRSP, UMNR, PETC Y SELECTEE
        String nrsaPNR = data.extractData(17, 1, "BoardingPass"), nrsaLN = data.extractData(17, 2, "BoardingPass"),
                nrspPNR = data.extractData(18, 1, "BoardingPass"), nrspLN = data.extractData(18, 2, "BoardingPass"),
                umnrPNR = data.extractData(19, 1, "BoardingPass"), umnrLN = data.extractData(19, 2, "BoardingPass");

        String petcPNR = data.extractData(20, 1, "BoardingPass"), petcLN = data.extractData(20, 2, "BoardingPass"),
                selecteePNR = data.extractData(21, 1, "BoardingPass"), selecteeLN = data.extractData(21, 2, "BoardingPass");

        String direct1 = "arriba";
        String direct2 = "abajo";

        try {
            /** Crea el reporte **/
            report.createTestReport("Validaciones", "Realiza las validaciones del Módulo BoardingPass");
            /** PASO 1. Se invoca la clase **/
            firstSteps.skipFirstSteps();
            modal.closeRatingModalIfPresent();
            //Thread.sleep(1000);
            /** PASO 2. Realiza Validaciones Boarding Pass **/
//            board.boardingpassValidationsAllPreferStatusValidations(report, memberPNR, memberLN, desc);
//            desc = data.extractData(2,0, "boardingPass");
//            board.boardingpassValidationsAllPreferStatusValidations(report, silverPNR, silverLN, desc);
//            desc = data.extractData(3,0, "boardingPass");
//            board.boardingpassValidationsAllPreferStatusValidations(report, goldPNR, goldLN, desc);
//            desc = data.extractData(4,0, "boardingPass");
//            board.boardingpassValidationsAllPreferStatusValidations(report, platinumPNR, platinumLN, desc);
//            desc = data.extractData(5,0, "boardingPass");
//            board.boardingpassValidationsAllPreferStatusValidations(report, presidentialPNR, presidentialLN, desc);
            /** PASO 3. Realiza Validaciones Boarding Pass Basic Classic y Business **/
//             desc = data.extractData(6,0, "boardingPass");
//           board.boPassValBusinessClassicBasicValidations(report, businessPNR, businessLN, desc);
//            desc = data.extractData(7,0, "boardingPass");
//           board.boPassValBusinessClassicBasicValidations(report, classicPNR, classicLN, desc);
            desc = data.extractData(8,0, "boardingPass");
//           board.boPassValBusinessClassicBasicValidations(report, basicPNR, basicLN, desc);
            /** PASO 4. Realiza Validaciones boPassTwoAdultsOneInfant **/
            desc = data.extractData(9,0, "boardingPass");
            //board.boPassTwoAdultsOneInfantValidations(report, adtifnPNR, adtifnLN, desc);
            /** PASO 5. Realiza Validaciones bpValidationsMoreThanOneSegment **/
            desc = data.extractData(10,0, "boardingPass");
            //board.bpValidationsMoreThanOneSegment(report, onepaxPNR, onepaxLN, desc);
            /** PASO 6. Realiza Validaciones bpValidationsMoreThanOneSegmentMorePax **/
            desc = data.extractData(11,0, "boardingPass");
            board.bpValidationsMoreThanOneSegmentMorePax(report, twopaxPNR, twopaxLN, desc);
            /** PASO 7. Realiza Validaciones bpValidationsNRSA **/
//            desc = data.extractData(12,0, "boardingPass");
//            board.bpValidationsNRSA(report, nrsaPNR, nrsaLN, desc);
            /** PASO 8. Realiza Validaciones pnValidationsNRSP **/
//            desc = data.extractData(13,0, "boardingPass");
//            board.pnValidationsNRSP(report, nrspPNR, nrspLN, desc);
            /** PASO 9. Realiza Validaciones bpValidationsUMNR **/
//            desc = data.extractData(14,0, "boardingPass");
//            board.bpValidationsUMNR(report, umnrPNR, umnrLN, desc);
            /** PASO 10. Realiza Validaciones bpValidationsPETC **/
//            desc = data.extractData(15,0, "boardingPass");
//            board.bpValidationsPETC(report, petcPNR, petcLN, desc);
            /** PASO 11. Realiza Validaciones bpValidationsSELECTEE **/
//            desc = data.extractData(16,0, "boardingPass");
//            board.bpValidationsSELECTEE(report, selecteePNR, selecteeLN, desc);

            report.cerrar();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
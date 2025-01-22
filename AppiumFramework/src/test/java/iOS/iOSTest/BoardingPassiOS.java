package iOS.iOSTest;

import dataSet.DataBase;
import iOS.iOSPages.*;
import iOS.utilsiOS.DriveriOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import iOS.utilsiOS.ReportiOS;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BoardingPassiOS {

    public static void main(String[] args) throws Exception {
        //Se crea la instancia para el driver de Appium
        AppiumDriver driver = DriveriOS.getiOSDriver();

        DataBase db = new DataBase();

        //Se crean las instancias de cada flujo automatizado.
        BookingiOS booking = new BookingiOS(driver);
        BoardingPassValidationsiOS bp = new BoardingPassValidationsiOS(driver);
        ReportiOS report = new ReportiOS(driver);
        ReadDataiOS data = new ReadDataiOS(driver);
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);

        WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));

        String direct1 = "arriba";
        String direct2 = "abajo";
        //Descripciónes para el reporte
        String desc = data.extractData(1,0, "boardingPass");
        //reservas creadas con los tier Member, Silver, Gold, Platinum y Presidential
        String memberPNR = data.extractData(1, 1, "boardingPass"), memberLN = data.extractData(1, 2, "boardingPass"),
                silverPNR = data.extractData(2, 1, "boardingPass"), silverLN = data.extractData(2, 2, "boardingPass"),
                goldPNR = data.extractData(3, 1, "boardingPass"), goldLN = data.extractData(3, 2, "boardingPass"),
                platinumPNR = data.extractData(4, 1, "boardingPass"), platinumLN = data.extractData(4, 2, "boardingPass"),
                presidentialPNR = data.extractData(5, 1, "boardingPass"), presidentialLN = data.extractData(5, 2, "boardingPass");
        //vuelos con las clases Business, Classic y Basic
        String businessPNR = data.extractData(7, 1, "boardingPass"), businessLN = data.extractData(7, 2, "boardingPass"),
                classicPNR = data.extractData(8, 1, "boardingPass"), classicLN = data.extractData(8, 2, "boardingPass"),
                basicPNR = data.extractData(9, 1, "boardingPass"), basicLN = data.extractData(9, 2, "boardingPass");
        //vuelo con dos adultos y un infante
        String adtifnPNR = data.extractData(11, 1, "boardingPass"), adtifnLN = data.extractData(11, 2, "boardingPass");
        //vuelo con 2 segementos o más en ventana de checkin y 1 pasajero (Multiciudad)
        String onepaxPNR = data.extractData(13, 1, "boardingPass"), onepaxLN = data.extractData(13, 2, "boardingPass");
        //vuelo con 2 segementos o más en ventana de checkin y más de 1 pasajero (Multiciudad)
        String twopaxPNR = data.extractData(15, 1, "boardingPass"), twopaxLN = data.extractData(15, 2, "boardingPass");
        //Reservas NRSA, NRSP, UMNR, PETC Y SELECTEE
        String nrsaPNR = data.extractData(17, 1, "boardingPass"), nrsaLN = data.extractData(17, 2, "boardingPass"),
                nrspPNR = data.extractData(18, 1, "boardingPass"), nrspLN = data.extractData(18, 2, "boardingPass"),
                umnrPNR = data.extractData(19, 1, "boardingPass"), umnrLN = data.extractData(19, 2, "boardingPass"),
                petcPNR = data.extractData(20, 1, "boardingPass"), petcLN = data.extractData(20, 2, "boardingPass"),
                selecteePNR = data.extractData(21, 1, "boardingPass"), selecteeLN = data.extractData(21, 2, "boardingPass");

        try {

            /**Crea el reporte **/
            report.createTestReport("Boarding Pass Validaciones", "Realiza las validaciones del Módulo BoardingPass");
            /** PASO 1. Se invoca la clase **/
            Thread.sleep(6000);
            modal.closeRatingModalIfPresent();
            /** PASO 2. Realiza las valdiaciones del Boarding Pass con las reservas en cada tier connectmiles **/
            bp.boardingpassValidationsAllPreferStatus(report, memberPNR, memberLN, desc);
            desc = data.extractData(2,0, "boardingPass");
            bp.boardingpassValidationsAllPreferStatus(report, silverPNR, silverLN, desc);
            desc = data.extractData(3,0, "boardingPass");
            bp.boardingpassValidationsAllPreferStatus(report, goldPNR, goldLN, desc);
            desc = data.extractData(4,0, "boardingPass");
            bp.boardingpassValidationsAllPreferStatus(report, platinumPNR, platinumLN, desc);
            desc = data.extractData(5,0, "boardingPass");
            bp.boardingpassValidationsAllPreferStatus(report, presidentialPNR, presidentialLN, desc);
            /** PASO 3. Realiza las valdiaciones del Boarding Pass con las reservas en cada clase **/
            desc = data.extractData(7,0, "boardingPass");
            bp.boPassValBusinessClassicBasicValidations(report, businessPNR, businessLN, desc);
            desc = data.extractData(8,0, "boardingPass");
            bp.boPassValBusinessClassicBasicValidations(report, classicPNR, classicLN, desc);
            desc = data.extractData(9,0, "boardingPass");
            bp.boPassValBusinessClassicBasicValidations(report, basicPNR, basicLN, desc);
            /** PASO 4. Realiza las valdiaciones del Boarding Pass con una reserva de 2 adultos y 1 infante **/
            //desc = data.extractData(11,0, "boardingPass");
            //bp.boPassTwoAdultsOneInfantValidations(report, adtifnPNR, adtifnLN, desc);
            /** PASO 5. Realiza las valdiaciones del Boarding Pass con 2 o más segmentos en ventana de checkin y 1 pasajero **/
            desc = data.extractData(13,0, "boardingPass");
            bp.bpValidationsMoreThanOneSegment(report, onepaxPNR, onepaxLN, desc);
            /** PASO 6. Realiza las valdiaciones del Boarding Pass con 2 o más segmentos en ventana de checkin y más de 1 pasajero **/
            desc = data.extractData(15,0, "boardingPass");
            bp.bpValidationsMoreThanOneSegmentMorePax(report, twopaxPNR, twopaxLN, desc);
            /** PASO 7. Realiza las valdiaciones del Boarding Pass para reservas NRSA **/
            desc = data.extractData(17,0, "boardingPass");
            bp.bpValidationsNRSA(report, nrsaPNR, nrsaLN, desc);
            /** PASO 7. Realiza las valdiaciones del Boarding Pass para reservas NRSP **/
            desc = data.extractData(18,0, "boardingPass");
            bp.bpValidationsNRSP(report, nrspPNR, nrspLN, desc);
            /** PASO 7. Realiza las valdiaciones del Boarding Pass para reservas UMNR **/
            desc = data.extractData(19,0, "boardingPass");
            bp.bpValidationsUMNR(report, umnrPNR, umnrLN, desc);
            /** PASO 7. Realiza las valdiaciones del Boarding Pass para reservas PETC **/
            //desc = data.extractData(20,0, "boardingPass");
            //bp.bpValidationsPETC(report, petcPNR, petcLN, desc);
            /** PASO 7. Realiza las valdiaciones del Boarding Pass para reservas SELECTEE **/
            desc = data.extractData(21,0, "boardingPass");
            bp.bpValidationsSELECTEE(report, selecteePNR, selecteeLN, desc);

            report.cerrar();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

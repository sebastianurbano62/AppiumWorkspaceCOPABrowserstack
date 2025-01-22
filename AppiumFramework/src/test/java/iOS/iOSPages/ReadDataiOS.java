package iOS.iOSPages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.GeneratedUtils;
import utils.Parameters;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class ReadDataiOS {

    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public ReadDataiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Realiza el login en el app
     * @throws Exception
     */
    public String extractData(Integer row, Integer cell, String sheet) throws Exception {
        String data = "";

        System.out.println("extractData inicio\n");
        try {

            // Ruta del archivo Excel
            String filePath = "/Users/hdqebsqa01/Documents/Regresión Data.xlsx/";

            //Create an object of FileInputStream class to read excel file
            FileInputStream fis = new FileInputStream(filePath);

            //Create object of XSSFWorkbook class
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            //nombre de la hoja
            XSSFSheet sheet1 = wb.getSheet(sheet);

            data = sheet1.getRow(row).getCell(cell).getStringCellValue();

            System.out.println("extractData finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("extractData: Error\n");
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public double extractUserCM(Integer row, Integer cell, String sheet) throws Exception {
        double user = 0;

        System.out.println("extract user ConnectMiles inicio\n");
        try {

            // Ruta del archivo Excel
            String filePath = "/Users/hdqebsqa01/Documents/Regresión Data.xlsx/";

            //Create an object of FileInputStream class to read excel file
            FileInputStream fis = new FileInputStream(filePath);

            //Create object of XSSFWorkbook class
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            //nombre de la hoja
            XSSFSheet sheet1 = wb.getSheet(sheet);

            user = sheet1.getRow(row).getCell(cell).getNumericCellValue();


            System.out.println("extract users ConnectMiles finalizado con éxito\n");
        } catch (Exception ex) {
            System.out.println("extract users ConnectMiles: Error\n");
            System.out.println(ex.getMessage());
        }
        return  user;
    }
}

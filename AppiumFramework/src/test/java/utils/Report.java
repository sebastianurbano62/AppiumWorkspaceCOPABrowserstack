package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Report {
    private ExtentTest test;
    private AppiumDriver driver;
    ExtentReports extent = new ExtentReports();

    /***
     * Constructor
     * @param driver
     */
    public  Report(AppiumDriver driver) {
        this.driver = driver;
    }

    /***
     * Se invoca para cerrar el reporte
     */
    public void cerrar() {
        extent.flush();
    }

    /**
     * Para iniciar la creación del reporte
     * @param testName Nombre del escenario o prueba
     * @param testDescription Descripción de la prueba
     * @throws Throwable
     */
    public void createTestReport(String testName, String testDescription) throws Throwable {
        Files files = new Files();
        String reportPath = files.createFolder() + "/" + testName + "_" + getformatDate() + ".html";
        System.out.println("ruta de archivo creado: " + reportPath);
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
        extent.attachReporter(htmlReporter);
        String header = reportHeader(testName,testDescription);
        test = extent.createTest(header);
    }

    /**
     * Cuando se cumple el resultado esperado en la prueba
     * @param escenario
     * @param takeScreenShoot
     */
    public void testPassed(String escenario,boolean takeScreenShoot){
        if (takeScreenShoot) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            String base64String = "data:image/png;base64," + Base64.getEncoder().encodeToString(data);
            test.log(Status.PASS, escenario, MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
        }
        else{
            test.pass(escenario);
        }
    }

    /**
     * Cuando no se cumple el resultado esperado en la prueba
     * @param escenario
     * @param takeScreenShoot
     */
    public void testFailed(String escenario,boolean takeScreenShoot){
        if (takeScreenShoot) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            String base64String = "data:image/png;base64," + Base64.getEncoder().encodeToString(data);
            test.log(Status.FAIL, escenario, MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
        }
        else{
            test.fail(escenario);
        }
    }

    private String getformatDate(){
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String fechaFormateada = formatoFecha.format(fechaActual);
        return fechaFormateada;
    }

    private String reportHeader(String escenario, String description) throws Throwable {

        String brakeline = "<br>";
        //String testID = ""Integer.toString(dto.getTestCase())"";
        String sistemaOperativo = "Android";
        String testDescription = description;
        //String testParameter = dto.getTestParameter();
        //String result_ID = counter + "_" + testID + "_" + className;

        String testHeader = "";
        testHeader += "Escenario: " + "<b>" + escenario + "</b>" + brakeline;
        testHeader += "Descripción: " + "<b>" + description + "</b>" + brakeline;
        testHeader += "SO: " + "<b>" + sistemaOperativo + "</b>" + brakeline;

        return testHeader;
    }

}

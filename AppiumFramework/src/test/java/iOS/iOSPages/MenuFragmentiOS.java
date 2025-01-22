package iOS.iOSPages;

import iOS.iOSPages.TIFValidationsiOS;
import iOS.utilsiOS.RatingModalCheckiOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import iOS.utilsiOS.GeneratedUtilsiOS;
import iOS.utilsiOS.ParametersiOS;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Clase para manejar los eventos del menú para las opciones Booking, Home y Account
 */
public class MenuFragmentiOS {
    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public MenuFragmentiOS(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Clic en el menú Booking
     */
    public void clickBookingIcon(){
        try{
            By by;
            driver.manage().timeouts().implicitlyWait(ParametersiOS.waiteTime, TimeUnit.MILLISECONDS);
            //    Clic booking.
            //driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Booking\"]")); DESCOMENTAR CUANDO TENGAMOS OTRA VEZ NEW HOME
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[1]")).click();

             System.out.println("clickBookingIcon finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("clickBookingIcon error");
        }

    }

    /**
     * Clic en el menú Check-in
     */
    public void clickCheckinIcon(){ //COMENTAR CLASE CUANDO YA TENGAMOS DE NUEVO EL NEW HOME
        try{
            //    Clic booking.
            GeneratedUtilsiOS.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("cd_home_check_in")).click();

            System.out.println("clickCheckinIcon finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("clickCheckinIcon error");
        }

    }

    /**
     * Clic en el menú Home
     */
    public void clickHomeIcon(){
        try{
            //    Clic al ícono de home.
            //driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Home\"]")); DESCOMENTAR CUANDO TENGAMOS OTRA VEZ NEW HOME
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[2]")).click();

            System.out.println("clickHomeIcon finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("clickHomeIcon error");
        }

    }

    /**
     * Clic en el menú Account
     */
    public void clickAccountIcon(){
        try{
            //    Clic al ícono de cuenta - Account.
            //driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Account\"]")); DESCOMENTAR CUANDO TENGAMOS OTRA VEZ NEW HOME
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeTabBar[@name=\"Barra de pestañas\"]/XCUIElementTypeButton[3]")).click();

            System.out.println("clickAccountIcon finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("clickAccountIcon  error");
        }

    }

    /**
     * Click en el ícono de add trip en el home
     */
    public void clickAddTripHome(){
        try{
            driver.findElement(AppiumBy.accessibilityId("cd_home_add_trip")).click();
        }
        catch(Exception e){
            System.out.println("clickAddTripHome  error "+ e);
        }
    }

    /**
     *  Click en el ícono de mis viajes
     */
    public void clickMyTripsIcon(){
        try{
            //    Clic al ícono de cuenta - Account.
            //driver.findElement(AppiumBy.accessibilityId("My Trips")).click(); DESCOMENTAR CUANDO TENGAMOS OTRA VEZ NEW HOME
            driver.findElement(AppiumBy.accessibilityId("cd_home_my_trips")).click(); // COMENTAR CON EL NUEVO HOME

            System.out.println("clickMyTripsIcon finalizado con éxito\n");
        }
        catch(Exception ex){
            System.out.println("clickMyTripsIcon  error");

        }

    }

    /**
     * Elimina el primer vuelo
     */
    public void deleteTrip(){
        String direct2 = "abajo";
        RatingModalCheckiOS modal = new RatingModalCheckiOS(driver);
        try{
            // DESCOMENTAR CUANDO YA TENGAMOS EL NEW HOME
            // clickMyTripsIcon();

            // COMENTAR ESTE BLOQUE ABAJO CUANDO TENGAMOS DE NUEVO EL NEW HOME
            //Valida si se muestra el modal de calificación y lo quita
            clickHomeIcon();
            modal.closeRatingModalIfPresent();

            //WebElement Panel = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]"));
            //tif.swipeFormTIF(Panel, driver, direct2);
            clickMyTripsIcon();
            // COMENTAR ESTE BLOQUE ARRIBA CUANDO TENGAMOS DE NUEVO EL NEW HOME

            Thread.sleep(1000);

            WebElement Panel2 = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"Copa Airlines\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell"));
            Dimension dimension = Panel2.getSize();

            int Anchor = Panel2.getSize().getWidth()/2;

            Double ScreenWidthStart = dimension.getWidth() * 0.9;
            int scrollStart = ScreenWidthStart.intValue();

            Double ScreenWidthEnd = dimension.getWidth() * 0.25;
            int scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            Thread.sleep(2000);
            new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(320,232)).release().perform();
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Eliminar Viaje\"]")).click();

            /*System.out.println("Pausa realizada");
            by = By.xpath("//XCUIElementTypeStaticText[@name='Eliminar']");
            driver.findElement(by).click();
            Thread.sleep(500);*/


            System.out.println("deleteTrip finalizado con éxito\n");
            Thread.sleep(1000);
        }catch(Exception ex){
            System.out.println("deleteTrip Error");
        }
    }
}

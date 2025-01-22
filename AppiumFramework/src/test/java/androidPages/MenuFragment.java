package androidPages;

import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.WebElement;

import utils.RatingModalCheck;

import java.time.Duration;


/**
 * Clase para manejar los eventos del menú para las opciones Booking, Home y Account
 */
public class MenuFragment {
    private AppiumDriver driver;

    /**
     * Constructor de la clase
     * @param driver Driver necesario para construir la clase
     */
    public MenuFragment(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Clic en el menú Booking
     */
    public void clickBookingIcon(){
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{
            By by;
            //    Clic booking.
            //driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            Thread.sleep(500);
            by = AppiumBy.id("com.copaair.copaAirlines.dev:id/bookingPanelFragment");
            driver.findElement(by).click(); //(click en el icono de booking, home nuevo)
            //driver.findElement(AppiumBy.accessibilityId("Reserva")).click();

            //Aqui va llamado de evidencia para el reporte
             System.out.println("clickBookingIcon finalizado con éxito");

            modal.closeRatingModalIfPresent();
        }
        catch(Exception ex){
            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickBookingIcon error");
        }

    }

    /**
     * Clic en el menú Check-in
     */
    public void clickCheckinIcon(){
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{

            //    Clic booking.
            //driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);

            Thread.sleep(500);
            driver.findElement(AppiumBy.accessibilityId("Open the Check-in flow.")).click();

            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickCheckinIcon finalizado con éxito");

            modal.closeRatingModalIfPresent();
        }
        catch(Exception ex){
            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickCheckinIcon error");
        }

    }

    /**
     * Clic en el menú Home
     */
    public void clickHomeIcon(){
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{
            By by;
            //    Clic al ícono de home.
            //driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);

            Thread.sleep(500);

            by = AppiumBy.accessibilityId("Inicio");
            driver.findElement(by).click(); //(boton home, home nuevo)

            //driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/homeFragment")).click();

            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickHomeIcon finalizado con éxito");

            modal.closeRatingModalIfPresent();
        }
        catch(Exception ex){
            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickHomeIcon error");
        }

    }

    /**
     * Clic en el menú Account
     */
    public void clickAccountIcon(){
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{
            //modal.closeRatingModalIfPresent();

            //driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            //    Clic al ícono de cuenta - Account.
            Thread.sleep(500);
            driver.findElement(AppiumBy.id("com.copaair.copaAirlines.dev:id/accountFragment")).click();//(botón Account, nuevo home)

            //driver.findElement(AppiumBy.accessibilityId("Cuenta")).click();

            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickAccountIcon finalizado con éxito");
            Thread.sleep(500);

            //modal.closeRatingModalIfPresent();
        }
        catch(Exception ex){
            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickAccountIcon  error");

        }

    }

    /**
     *  Click en el ícono de mis viajes
     */
    public void clickMyTripsIcon(){
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{
            //driver.manage().timeouts().implicitlyWait(Parameters.waiteTime, TimeUnit.MILLISECONDS);
            By by;
            //    Clic al ícono de cuenta - Account.
            Thread.sleep(500);
            //by = AppiumBy.id("com.copaair.copaAirlines.dev:id/myTrips");
            by = AppiumBy.xpath("(//android.view.View[@content-desc=\"Open My Trips to access all trips\"])[1]");
            driver.findElement(by).click();//(boton mis viajes, nuevo home)

            //driver.findElement(AppiumBy.accessibilityId("Abre Mis Viajes para acceder a todos los viajes.")).click();

            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickMyTripsIcon finalizado con éxito");

            modal.closeRatingModalIfPresent();
        }
        catch(Exception ex){
            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickMyTripsIcon  error");
        }

    }

    /**
     * Elimina el primer vuelo
     */
    public void deleteTrip(){
        RatingModalCheck modal = new RatingModalCheck(driver);
        try{
            By by;
            clickHomeIcon();
            modal.closeRatingModalIfPresent();
            clickMyTripsIcon();
            modal.closeRatingModalIfPresent();
            Thread.sleep(1000);

            WebElement Panel2 = driver.findElement(By.id("com.copaair.copaAirlines.dev:id/listTrips"));
            Dimension dimension = Panel2.getSize();

            int Anchor = Panel2.getSize().getWidth()/2;

            Double ScreenWidthStart = dimension.getWidth() * 0.9;
            int scrollStart = ScreenWidthStart.intValue();

            Double ScreenWidthEnd = dimension.getWidth() * 0.4;
            int scrollEnd = ScreenWidthEnd.intValue();

            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(scrollStart, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(scrollEnd, Anchor))
                    .release().perform();

            by = By.id("com.copaair.copaAirlines.dev:id/remove");
            driver.findElement(by).click();
            Thread.sleep(500);
            by = By.id("android:id/button1");
            driver.findElement(by).click();

            System.out.println("deleteTrip finalizado con éxito");
            Thread.sleep(1000);
        }catch(Exception ex){
            System.out.println("deleteTrip Error");
        }
    }

    public void ClickAddTripHome(){
        RatingModalCheck modal = new RatingModalCheck(driver);
        WebElement Panel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));
        Booking booking = new Booking(driver);
        String direct2 = "abajo";

        try{
            booking.swipeSmall(Panel, driver, direct2);

            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/w1.m1/android.view.View/android.widget.ScrollView/android.view.View[4]/android.view.View[1]")).click();

            //driver.findElement(AppiumBy.accessibilityId("Agrega un Viaje")).click();

            //Aqui va llamado de evidencia para el reporte
            System.out.println("clickAddTripHome finalizado con éxito");

            System.out.println("ClickAddTripHome finalizado con éxito");
            Thread.sleep(1000);
        }catch(Exception ex){
            System.out.println("ClickAddTripHome Error");
        }
    }

}

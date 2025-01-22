package iOS.utilsiOS;

/**
 * Parámetros Globales
 */
public class ParametersiOS {
     public static int waiteTime = 15000;
    // ****** CAPABILITIES PARAMETERS  ******
    //Id del dispositivo conectado en el puerto USB - se obtiene con el comando "adb devices"
    //public static String deviceId="M4VDU18106003755";
    public static String deviceId="c01e0685cae99a658476ec4c3c583439e29b7a8d";
    //ID del paquete en el APP
    public static String appPackage = "com.copaair.copaAirlines.dev";
    //Main del app
    public static String appActivity  = "com.copaair.copaAirlines.presentationLayer.entry.EntryActivity";
    public static String appiumServerURL = "http://0.0.0.0:4723/";

}

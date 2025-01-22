package utils;

import java.util.concurrent.TimeUnit;

public class GeneratedUtils {

    public static void sleep(int milliseconds) throws Exception {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        }
        catch (Exception e) {
            throw new Exception("Pause between steps was interrupted", e);
        }
    }
}

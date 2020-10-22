package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommUtils {
    public CommUtils() {
    }

    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];

            int n;
            while((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }

            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return buffer;
    }

    public static void waitMinute(int min) throws InterruptedException {
        TimeUnit.MINUTES.sleep((long)min);
    }

    public static void waitSecond(int sec) throws InterruptedException {
        TimeUnit.SECONDS.sleep((long)sec);
    }

    public static void waitHour(int hour) throws InterruptedException {
        TimeUnit.HOURS.sleep((long)hour);
    }

    public void waitDay(int day) throws InterruptedException {
        TimeUnit.DAYS.sleep((long)day);
    }
}

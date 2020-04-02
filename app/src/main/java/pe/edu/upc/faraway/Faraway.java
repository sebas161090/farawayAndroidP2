package pe.edu.upc.faraway;

import android.app.Application;
import android.os.SystemClock;

public class Faraway extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(1500);
    }
}


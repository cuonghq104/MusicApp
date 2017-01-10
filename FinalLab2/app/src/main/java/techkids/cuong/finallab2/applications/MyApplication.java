package techkids.cuong.finallab2.applications;

import android.app.Application;
import android.widget.Toast;

import techkids.cuong.finallab2.databases.DbContext;

/**
 * Created by Cuong on 1/8/2017.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbContext.init(this);
    }
}

package techkids.cuong.finallab2_remake.applications;

import android.app.Application;

import techkids.cuong.finallab2_remake.databases.context.DbContext;

/**
 * Created by Cuong on 1/30/2017.
 */

public class MusicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbContext.init(this);
    }
}

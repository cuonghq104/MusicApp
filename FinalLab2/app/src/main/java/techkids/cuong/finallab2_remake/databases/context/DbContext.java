package techkids.cuong.finallab2_remake.databases.context;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmResults;
import techkids.cuong.finallab2_remake.databases.realmmodels.MusicGenre;

/**
 * Created by Cuong on 1/30/2017.
 */

public class DbContext {

    private Realm realm;

    private static DbContext instances;

    public static DbContext getInstances() {
        return instances;
    }

    public DbContext() {
        realm = Realm.getDefaultInstance();
    }

    public static void init(Context context) {
        Realm.init(context);
        instances = new DbContext();
    }

    public void insert(MusicGenre genre) {
        realm.beginTransaction();
        realm.copyToRealm(genre);
        realm.commitTransaction();
    }

    public RealmResults<MusicGenre> getAllGenres() {
        return realm.where(MusicGenre.class).findAll();
    }

    public RealmResults<MusicGenre> getFavoriteGenres() {
        return realm.where(MusicGenre.class).equalTo("favorite", true).findAll();
    }

    public void removeAll() {

        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();

    }

    public void changeFavorite(MusicGenre genre) {

        realm.beginTransaction();
        genre.changeFavorite();
        realm.commitTransaction();

    }
}

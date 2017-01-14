package techkids.cuong.finallab2.databases;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import techkids.cuong.finallab2.models.Genre;
import techkids.cuong.finallab2.models.ListGenre;

/**
 * Created by Cuong on 1/8/2017.
 */
public class DbContext {

    private Realm realm;

    public void insert(Genre genre) {
        realm.beginTransaction();
        realm.copyToRealm(genre);
        realm.commitTransaction();
    }

    public void changeFavorite(Genre genre) {
        realm.beginTransaction();
        genre.changeFavorite();
        realm.commitTransaction();
    }

    public RealmResults<Genre> getAllGenre() {
        return realm.where(Genre.class).findAll();
    }

    public void removeAllGenre() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    private static DbContext instance;

    public static DbContext getInstance() {
        return instance;
    }

    public DbContext() {
        realm = Realm.getDefaultInstance();
    }

    public static void init(Context context) {
        Realm.init(context);
        instance = new DbContext();
    }
}

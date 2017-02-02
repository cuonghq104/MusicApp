package techkids.cuong.finallab2_remake.databases.realmmodels;

import io.realm.RealmObject;

/**
 * Created by Cuong on 1/30/2017.
 */

public class MusicGenre extends RealmObject{

    private String id;

    private String name;

    private boolean favorite;

    public static MusicGenre create(String id, String name) {

        MusicGenre genre = new MusicGenre();
        genre.id = id;
        genre.name = name;
        genre.favorite = false;
        return genre;

    }
    public String getId() {
        return id;
    }

    public void changeFavorite() {
        favorite = !favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getName() {
        return name;
    }
}

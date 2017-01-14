package techkids.cuong.finallab2.models;

import io.realm.RealmObject;

/**
 * Created by Cuong on 1/8/2017.
 */
public class Genre extends RealmObject{

    private String id;

    private String name;

    private boolean favorite;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Genre create(String id, String name) {
        Genre genre = new Genre();
        genre.id = id;
        genre.name = name;
        genre.favorite = false;
        return genre;
    }

    public void changeFavorite() {
        if (favorite) {
            favorite = false;
        } else {
            favorite = true;
        }
    }

    public boolean isFavorite() {
        return favorite;
    }
}

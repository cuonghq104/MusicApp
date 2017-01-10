package techkids.cuong.finallab2.models;

import io.realm.RealmObject;

/**
 * Created by Cuong on 1/8/2017.
 */
public class Genre extends RealmObject{

    private String id;

    private String name;

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
        return genre;
    }
}

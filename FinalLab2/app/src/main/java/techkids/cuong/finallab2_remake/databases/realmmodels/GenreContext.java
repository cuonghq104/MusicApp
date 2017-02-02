package techkids.cuong.finallab2_remake.databases.realmmodels;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Cuong on 1/30/2017.
 */

public class GenreContext {

    public static final String POSITION = "position";

    public static RealmList<MusicGenre> genres = new RealmList<>();

    public static List<MusicGenre> genresFavorite = new ArrayList<>();

}

package techkids.cuong.finallab2.models;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by Cuong on 1/8/2017.
 */
public class ListGenre {

    public static final String POSITION = "position";

    private static List<Genre> list = new RealmList<>();

    public static void add(Genre musicGenre) {
        list.add(musicGenre);
    }

    public static List<Genre> getList() {
        return list;
    }

    public static void setList(List<Genre> list) {
        ListGenre.list = list;
    }
}

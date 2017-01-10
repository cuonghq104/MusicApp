package techkids.cuong.finallab2.networks.models;

/**
 * Created by Cuong on 1/10/2017.
 */
public class SongImage {

    private String label;

    public SongImage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "SongImage{" +
                "label='" + label + '\'' +
                '}';
    }
}

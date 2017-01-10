package techkids.cuong.finallab2.networks.models;

/**
 * Created by Cuong on 1/10/2017.
 */
public class SongName {

    private String label;

    public SongName(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "SongName{" +
                "label='" + label + '\'' +
                '}';
    }
}

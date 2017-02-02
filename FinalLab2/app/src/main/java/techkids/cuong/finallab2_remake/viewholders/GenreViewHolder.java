package techkids.cuong.finallab2_remake.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.finallab2_remake.R;
import techkids.cuong.finallab2_remake.databases.realmmodels.GenreContext;
import techkids.cuong.finallab2_remake.databases.realmmodels.MusicGenre;
import techkids.cuong.finallab2_remake.events.TopSongEvent;

/**
 * Created by Cuong on 1/8/2017.
 */
public class GenreViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_genre_ts)
    ImageView ivGenre;

    @BindView(R.id.tv_genre)
    TextView tvGenre;

    private int position;

    public GenreViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position) {

        this.position = position;

        MusicGenre genre = GenreContext.genres.get(position);

        String id = genre.getId();

        String uri = String.format("genre_2x_%s", id);

        int imageResource = ivGenre.getContext().getResources().getIdentifier(uri, "drawable", ivGenre.getContext().getPackageName());

        ivGenre.setImageResource(imageResource);

        tvGenre.setText(genre.getName().toUpperCase());
    }

    @OnClick(R.id.iv_genre_ts)
    public void goToDetail() {
        EventBus.getDefault().post(new TopSongEvent(position, GenreContext.genres.get(position).getId()));
    }
}

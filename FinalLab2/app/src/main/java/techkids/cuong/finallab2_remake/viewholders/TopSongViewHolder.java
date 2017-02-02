package techkids.cuong.finallab2_remake.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.finallab2_remake.R;
import techkids.cuong.finallab2_remake.events.PlayMusicEvent;
import techkids.cuong.finallab2_remake.models.SongContext;
import techkids.cuong.finallab2_remake.networks.jsonmodels.Song;
import techkids.cuong.finallab2_remake.transforms.CircleTransform;

/**
 * Created by Cuong on 1/10/2017.
 */
public class TopSongViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.iv_top_song)
    ImageView ivTopSong;

    @BindView(R.id.tv_top_song_artist)
    TextView tvArtist;

    @BindView(R.id.tv_top_song_name)
    TextView tvName;

    @BindView(R.id.rl_play)
    RelativeLayout rlPlay;

    int position;
    public TopSongViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position) {
        this.position = position;

        Song song = SongContext.songs.get(position);

        tvArtist.setText(song.getArtist().getLabel());

        tvName.setText(song.getName().getName());

        Picasso.with(ivTopSong.getContext()).load(song.getSongImages()[2].getUrl()).transform(new CircleTransform()).into(ivTopSong);
    }

    @OnClick(R.id.rl_play)
    public void onPlayEvent() {
        EventBus.getDefault().post(new PlayMusicEvent(true, position));
    }
}

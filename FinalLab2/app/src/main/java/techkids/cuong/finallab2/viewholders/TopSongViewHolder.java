package techkids.cuong.finallab2.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.finallab2.R;
import techkids.cuong.finallab2.models.SongList;
import techkids.cuong.finallab2.networks.models.Song;
import techkids.cuong.finallab2.transforms.CircleTransform;

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

    int position;
    public TopSongViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position) {
        this.position = position;
        Song song = SongList.list.get(position);

        tvArtist.setText(song.getSongArtist().getLabel());

        tvName.setText(song.getSongName().getLabel());

        Picasso.with(ivTopSong.getContext()).load(song.getSongImage()[2].getLabel()).transform(new CircleTransform()).into(ivTopSong);
    }
}

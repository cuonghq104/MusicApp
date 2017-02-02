package techkids.cuong.finallab2_remake.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techkids.cuong.finallab2_remake.R;
import techkids.cuong.finallab2_remake.models.SongContext;
import techkids.cuong.finallab2_remake.viewholders.TopSongViewHolder;


/**
 * Created by Cuong on 1/10/2017.
 */
public class TopSongAdapter extends RecyclerView.Adapter<TopSongViewHolder> {

    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_top_song, parent, false);

        TopSongViewHolder viewHolder = new TopSongViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return SongContext.songs.size();
    }
}

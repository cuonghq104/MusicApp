package techkids.cuong.finallab2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techkids.cuong.finallab2.R;
import techkids.cuong.finallab2.models.ListGenre;
import techkids.cuong.finallab2.viewholders.GenreViewHolder;

/**
 * Created by Cuong on 1/8/2017.
 */
public class GenreAdapter extends RecyclerView.Adapter<GenreViewHolder> {

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_genre, parent, false);

        GenreViewHolder viewHolder = new GenreViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ListGenre.getList().size();
    }
}

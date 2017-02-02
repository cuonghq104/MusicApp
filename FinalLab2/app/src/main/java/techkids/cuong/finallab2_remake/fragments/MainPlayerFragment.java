package techkids.cuong.finallab2_remake.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.finallab2_remake.R;
import techkids.cuong.finallab2_remake.events.CurrentSongEvent;
import techkids.cuong.finallab2_remake.events.MusicPauseEvent;
import techkids.cuong.finallab2_remake.events.MusicPlayingEvent;
import techkids.cuong.finallab2_remake.events.NextSongEvent;
import techkids.cuong.finallab2_remake.events.PauseEvent;
import techkids.cuong.finallab2_remake.events.PlayMusicEvent;
import techkids.cuong.finallab2_remake.events.PreviousSongEvent;
import techkids.cuong.finallab2_remake.networks.jsonmodels.Song;
import techkids.cuong.finallab2_remake.viewcompounds.SquareImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPlayerFragment extends Fragment {

    @BindView(R.id.iv_song_image)
    SquareImageView ivSongImage;

    @BindView(R.id.sb_player)
    SeekBar sbPlayer;

    private Song song;

    public MainPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_player, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    private void setupUI() {
        sbPlayer.setPadding(0, 0, 0, 0);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky =  true)
    public void onPlayMusicEvent(CurrentSongEvent event) {
        this.song = event.getSong();
        Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
        Picasso.with(getContext()).load(song.getSongImages()[2].getUrl()).into(ivSongImage);
        sbPlayer.setMax(event.getDuration());
    }

    @Subscribe
    public void onMusicPlayingEvent(MusicPlayingEvent event) {
        sbPlayer.setProgress(event.getCurrent());
    }

    @BindView(R.id.bt_play)
    CircleButton btPlay;

    @OnClick(R.id.bt_play)
    public void onPausePressed() {
        EventBus.getDefault().post(new PauseEvent());
    }

    @Subscribe
    public void onPauseEvent(MusicPauseEvent event) {
        if (event.isPause()) {
            btPlay.setImageResource(R.drawable.ic_pause_main);
        } else {
            btPlay.setImageResource(R.drawable.play_main);
        }
    }

    @OnClick(R.id.bt_fast_forward)
    public void onFastForwardPressed() {
        EventBus.getDefault().post(new NextSongEvent());
    }

    @OnClick(R.id.bt_rewind)
    public void onRewindPressed() {
        EventBus.getDefault().post(new PreviousSongEvent());

    }
}

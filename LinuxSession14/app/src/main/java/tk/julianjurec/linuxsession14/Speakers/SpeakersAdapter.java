package tk.julianjurec.linuxsession14.Speakers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 30.04.17.
 */

class SpeakersAdapter extends RecyclerView.Adapter<SpeakersAdapter.Holder> {
    private List<Speaker> speakers;
    private Context context;
    private SpeakersPresenter presenter;


    SpeakersAdapter(Context context, SpeakersPresenter presenter, List<Speaker> speakers) {
        this.context = context;
        this.presenter = presenter;
        this.speakers = speakers;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speakers, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Speaker speaker = speakers.get(position);
        Lecture lecture = Lecture.find(Lecture.class, "speaker_id = ?", String.valueOf(speaker.getId())).get(0);
        holder.name.setText(speaker.getName());
        if (speaker.getImgUrl() != null && !speaker.getImgUrl().isEmpty())
            Picasso.with(context)
                    .load(speaker.getImgUrl())
                    .placeholder(R.drawable.unknown)
                    .into(holder.img);
        holder.card.setOnClickListener(v -> {
            presenter.showSpeakerDialog(speaker, lecture);
        });
    }

    @Override
    public int getItemCount() {
        return speakers.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.speaker_item_img)
        ImageView img;
        @BindView(R.id.speaker_item_name)
        TextView name;
        @BindView(R.id.speaker_item_card)
        CardView card;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

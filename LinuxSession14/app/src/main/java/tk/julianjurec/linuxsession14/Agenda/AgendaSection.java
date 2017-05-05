package tk.julianjurec.linuxsession14.Agenda;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;
import com.varunest.sparkbutton.SparkButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import retrofit2.http.HEAD;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 30.04.17.
 */

class AgendaSection extends StatelessSection {

    private static final Map<Integer, String> days;

    static {
        days = new HashMap<>(5);
        days.put(1, "Sobota");
        days.put(2, "Niedziela");
    }

    private String header;

    private RecyclerView recyclerView;
    private Context context;
    private List<Lecture> lectures;
    private AgendaPresenter presenter;

    AgendaSection(RecyclerView recyclerView, Context context, List<Lecture> lectures, String header, AgendaPresenter presenter) {
        super(R.layout.header_agenda, R.layout.item_agenda);
        this.recyclerView = recyclerView;
        this.context = context;
        this.lectures = lectures;
        this.presenter = presenter;
        setHasHeader(true);
        this.header=header;
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ((HeaderHolder)holder).title.setText(header);
    }


    @Override
    public int getContentItemsTotal() {
        return lectures.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new Holder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder h, int position) {
        Holder holder = (Holder) h;

        final Lecture lecture = lectures.get(position);
        final Speaker speaker = Speaker.findById(Speaker.class, lecture.getSpeakerId());

        holder.foldedDay.setText(days.get(lecture.getDay()));
        holder.foldedStart.setText(lecture.getStartTime());
        holder.foldedEnd.setText(lecture.getEndTime());
        holder.foldedTitle.setText(lecture.getTitle());
        holder.foldedSpeakerName.setText(speaker.getName());
        if (speaker.getImgUrl() != null && !speaker.getImgUrl().isEmpty())
            Picasso.with(context)
                    .load(speaker.getImgUrl())
                    .noFade()
                    .placeholder(R.drawable.unknown)
                    .into(holder.foldedImg);

        holder.start.setText(lecture.getStartTime());
        holder.end.setText(lecture.getEndTime());
        holder.title.setText(lecture.getTitle());
        holder.description.setText(lecture.getDescription());
        holder.share.setOnTouchListener((v, e) -> {
            if (e.getAction() == MotionEvent.ACTION_UP)
                new Handler().postDelayed(() -> {
                    holder.share.setChecked(false);
                    presenter.share(lecture);
                }, 500);
            return false;
        });

        holder.recyclerView = recyclerView;
        holder.position = position;
        holder.speakerName.setText(speaker.getName());
        if (speaker.getImgUrl() != null && !speaker.getImgUrl().isEmpty())
            Picasso.with(context)
                    .load(speaker.getImgUrl())
                    .noFade()
                    .placeholder(R.drawable.unknown)
                    .into(holder.img);

        holder.descriptionScroll.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    recyclerView.requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                    recyclerView.requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return false;
        });
    }
    static class HeaderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.agenda_header_title) TextView title;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.agenda_folding_cell)
        FoldingCell cell;

        @BindView(R.id.agenda_item_btn_share)
        SparkButton share;
        @BindView(R.id.agenda_item_description)
        TextView description;
        @BindView(R.id.agenda_item_speaker_name)
        TextView speakerName;
        @BindView(R.id.agenda_item_start)
        TextView start;
        @BindView(R.id.agenda_item_end)
        TextView end;
        @BindView(R.id.agenda_item_title)
        TextView title;

        @BindView(R.id.agenda_item_description_scroll)
        ScrollView descriptionScroll;
        @BindView(R.id.agenda_item_speaker_img)
        ImageView img;

        @BindView(R.id.agenda_item_folded_day)
        TextView foldedDay;
        @BindView(R.id.agenda_item_folded_start)
        TextView foldedStart;
        @BindView(R.id.agenda_item_folded_end)
        TextView foldedEnd;
        @BindView(R.id.agenda_item_folded_title)
        TextView foldedTitle;
        @BindView(R.id.agenda_item_folded_speaker_name)
        TextView foldedSpeakerName;
        @BindView(R.id.agenda_item_folded_img)
        ImageView foldedImg;
        private RecyclerView recyclerView;
        private int position;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.agenda_folding_cell)
        void toggleFold() {
            if (!cell.isUnfolded())
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView,null,getAdapterPosition());
                }, 750);
            cell.toggle(false);
        }
    }
}

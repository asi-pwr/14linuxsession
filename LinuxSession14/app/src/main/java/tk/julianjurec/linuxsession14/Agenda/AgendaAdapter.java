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

import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 30.04.17.
 */

class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.Holder> {

    private RecyclerView recyclerView;
    private Context context;

    AgendaAdapter(RecyclerView recyclerView, Context context) {
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda, parent, false);
        return new Holder(inflatedView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.recyclerView = recyclerView;
        holder.position = position;
        Picasso.with(context)
                .load("https://julianjurec.interc.co.uk/wp-content/uploads/2017/02/rsz_1jurec.png")
                .placeholder(R.drawable.unknown)
                .into(holder.img);
        Picasso.with(context)
                .load("https://julianjurec.interc.co.uk/wp-content/uploads/2017/02/rsz_1jurec.png")
                .placeholder(R.drawable.unknown)
                .into(holder.foldedImg);

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

    @Override
    public int getItemCount() {
        return 30;
    }


    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.agenda_folding_cell)
        FoldingCell cell;
        @BindView(R.id.agenda_item_description_scroll)
        ScrollView descriptionScroll;
        @BindView(R.id.agenda_item_speaker_img)
        ImageView img;
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
                    recyclerView.smoothScrollToPosition(position);
                }, 750);
            cell.toggle(false);
        }
    }
}

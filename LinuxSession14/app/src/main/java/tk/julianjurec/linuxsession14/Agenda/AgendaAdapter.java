package tk.julianjurec.linuxsession14.Agenda;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.ramotion.foldingcell.FoldingCell;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 30.04.17.
 */

class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.Holder> {

    private RecyclerView recyclerView;

    AgendaAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
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
        private RecyclerView recyclerView;
        private int position;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.agenda_folding_cell)
        void toggleFold(){
            cell.toggle(false);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                recyclerView.smoothScrollToPosition(position);
            }, 750);
        }
    }
}

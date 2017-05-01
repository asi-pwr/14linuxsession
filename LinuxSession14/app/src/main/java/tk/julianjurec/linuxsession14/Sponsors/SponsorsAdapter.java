package tk.julianjurec.linuxsession14.Sponsors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 01.05.17.
 */

class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.Holder> {

    private RecyclerView recyclerView;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sponsors, parent, false);
        return new Holder(inflatedView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 30;
    }


    static class Holder extends RecyclerView.ViewHolder {
        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

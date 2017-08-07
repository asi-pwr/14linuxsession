package tk.julianjurec.linuxsession14.Sponsors;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import tk.julianjurec.linuxsession14.Model.Sponsor;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 01.05.17.
 */

class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.Holder> {
    private static final Map<String, String> categories;
    static {
        categories = new HashMap<>(5);
        categories.put("patron", "Patron");
        categories.put("sponsor", "Współpraca");
        categories.put("mecenat", "Sponsor");
    }

    private List<Sponsor> sponsors;
    private Context context;

    public SponsorsAdapter(Context context, List<Sponsor> sponsors) {
        this.sponsors = sponsors;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sponsors, parent, false);
        return new Holder(inflatedView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Sponsor sponsor = sponsors.get(position);
        holder.name.setText(sponsor.getName());
        holder.category.setText(categories.get(sponsor.getCategory()));
        Picasso.with(context)
                .load(sponsor.getImgUrl())
                .placeholder(R.drawable.unknown)
                .into(holder.logoImg);
        holder.card.setOnClickListener(v -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(sponsor.getLink())));
        });

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return sponsors.size();
    }


    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.sponsors_item_logo_img) ImageView logoImg;
        @BindView(R.id.sponsors_item_name) TextView name;
        @BindView(R.id.sponsors_item_category) TextView category;
        @BindView(R.id.sponsors_item_card) CardView card;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

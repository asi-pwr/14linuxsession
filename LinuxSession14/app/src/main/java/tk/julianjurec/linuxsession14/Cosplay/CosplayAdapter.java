package tk.julianjurec.linuxsession14.Cosplay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import tk.julianjurec.linuxsession14.Model.Sponsor;
import tk.julianjurec.linuxsession14.R;

class CosplayAdapter extends RecyclerView.Adapter<CosplayAdapter.Holder> {

    private List<Sponsor> sponsors;
    private Context context;

    public CosplayAdapter(Context context, List<Sponsor> sponsors) {
        this.sponsors = sponsors;
        this.context = context;
    }

    @Override
    public CosplayAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cosplay, parent, false);
        return new CosplayAdapter.Holder(inflatedView);
    }

    @Override
    public void onBindViewHolder(CosplayAdapter.Holder holder, int position) {
        Sponsor sponsor = sponsors.get(position);
        holder.name.setText(sponsor.getName());
        Picasso.with(context)
                .load(sponsor.getImgUrl())
                .placeholder(R.drawable.unknown)
                .resize(720, 720)
                .onlyScaleDown()
                .centerInside()
                .into(holder.logoImg);
        holder.logoImg.setOnClickListener(v -> {
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
        @BindView(R.id.cosplay_item_image)
        ImageView logoImg;
        @BindView(R.id.cosplay_item_name)
        TextView name;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
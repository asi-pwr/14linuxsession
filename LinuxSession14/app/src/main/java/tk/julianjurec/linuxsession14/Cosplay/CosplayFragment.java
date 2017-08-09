package tk.julianjurec.linuxsession14.Cosplay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import tk.julianjurec.linuxsession14.Model.Sponsor;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */


public class CosplayFragment extends Fragment implements CosplayContract.View {

    @Inject
    public CosplayPresenter presenter;

    @BindView(R.id.cosplay_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.cosplay_inflater)
    CardView recyclerCard;

    private LinearLayoutManager linearLayoutManager;
    private CosplayAdapter adapter;

    public CosplayFragment() {
        //required empty public constructor
    }

    public static CosplayFragment newInstance() {

        Bundle args = new Bundle();

        CosplayFragment fragment = new CosplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCosplayComponent.builder().cosplayModule(new CosplayModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cosplay, container, false);
        ButterKnife.bind(this, root);

        linearLayoutManager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recyclerView.setLayoutManager(linearLayoutManager);

        loadRecyclerView();

        return root;
    }

    @Override
    public void setPresenter( CosplayPresenter presenter) { this.presenter = presenter; }

    @Override
    public void onResume() {
        super.onResume();
        try {
            presenter.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRecyclerView() {
        List<Sponsor> sponsors = Sponsor.find(Sponsor.class, "category='cosplay'");

        if(sponsors.isEmpty()){
            recyclerCard.setVisibility(View.GONE);
        }
        else {
            adapter = new CosplayAdapter(getContext(), sponsors);
            recyclerView.setAdapter(adapter);
        }
    }

    private void lunchMap(){
        Uri gmmIntentUri = Uri.parse("geo:51.107874,17.050815?q=51.107874,17.050815(Impart)");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @OnClick(R.id.mapInfo)
    public void onMapInfoClick(View view){
        lunchMap();
    }

    @OnClick(R.id.mapButton)
    public void onMapButtonClick(View view){
        lunchMap();
    }

}

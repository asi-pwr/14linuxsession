package tk.julianjurec.linuxsession14.Sponsors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tk.julianjurec.linuxsession14.Model.Sponsor;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */


public class SponsorsFragment extends Fragment implements SponsorsContract.View {

    @Inject
    public SponsorsPresenter presenter;

    @BindView(R.id.sponsors_recycler_view) RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SponsorsAdapter adapter;
    private static int type = -1;


    public SponsorsFragment() {
        //required empty public constructor
    }

    public static tk.julianjurec.linuxsession14.Sponsors.SponsorsFragment newInstance(int type) {
        SponsorsFragment.type = type;

        Bundle args = new Bundle();

        tk.julianjurec.linuxsession14.Sponsors.SponsorsFragment fragment = new tk.julianjurec.linuxsession14.Sponsors.SponsorsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSponsorsComponent.builder().sponsorsModule(new SponsorsModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sponsors, container, false);
        ButterKnife.bind(this, root);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);
        recyclerView.setLayoutManager(layoutManager);
        return root;
    }

    @Override
    public void setPresenter( SponsorsPresenter presenter) { this.presenter = presenter; }

    @Override
    public void onResume() {
        super.onResume();
        try {
            presenter.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSponsorsFetched(List<Sponsor> sponsors) {
        List<Sponsor> filteredList = new ArrayList<>();

        for(Sponsor sponsor : sponsors){
            if(type == 0 && sponsor.getCategory().equals("sponsor")){
                filteredList.add(sponsor);
            }
            else if(type == 1 && sponsor.getCategory().equals("patron")){
                filteredList.add(sponsor);
            }
        }

        adapter = new SponsorsAdapter(getContext(), filteredList);
        recyclerView.setAdapter(adapter);
    }

}


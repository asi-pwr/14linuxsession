package tk.julianjurec.linuxsession14.Media;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tk.julianjurec.linuxsession14.Model.Media;
import tk.julianjurec.linuxsession14.R;

import tk.julianjurec.linuxsession14.Media.MediaAdapter;
import tk.julianjurec.linuxsession14.Media.MediaContract;
import tk.julianjurec.linuxsession14.Media.MediaModule;
import tk.julianjurec.linuxsession14.Media.MediaPresenter;


/**
 * Created by sp0rk on 22.03.17.
 */


public class MediaFragment extends Fragment implements MediaContract.View {

    @Inject
    public MediaPresenter presenter;

    @BindView(R.id.sponsors_recycler_view) RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MediaAdapter adapter;


    public MediaFragment() {
        //required empty public constructor
    }

    public static MediaFragment newInstance() {

        Bundle args = new Bundle();

        MediaFragment fragment = new MediaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMediaComponent.builder().mediaModule(new MediaModule(this)).build().inject(this);
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
    public void setPresenter( MediaPresenter presenter) { this.presenter = presenter; }

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
    public void onSponsorsFetched(List<Media> medias) {
        adapter = new MediaAdapter(getContext(), medias);
        recyclerView.setAdapter(adapter);
    }

}


package tk.julianjurec.linuxsession14.Speakers;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import tk.julianjurec.linuxsession14.Speakers.SpeakersContract;
import tk.julianjurec.linuxsession14.Speakers.SpeakersModule;
import tk.julianjurec.linuxsession14.Speakers.SpeakersPresenter;
import tk.julianjurec.linuxsession14.Speakers.DaggerSpeakersComponent;
import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */


public class SpeakersFragment extends Fragment implements SpeakersContract.View {

    @Inject
    public SpeakersPresenter presenter;

    @BindView(R.id.speakers_recycler_view)
    RecyclerView recyclerView;

    private GridLayoutManager gridLayoutManager;
    private SpeakersAdapter adapter;

    public SpeakersFragment() {
        //required empty public constructor
    }

    public static SpeakersFragment newInstance() {

        Bundle args = new Bundle();

        tk.julianjurec.linuxsession14.Speakers.SpeakersFragment fragment = new tk.julianjurec.linuxsession14.Speakers.SpeakersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSpeakersComponent.builder().speakersModule(new SpeakersModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_speakers, container, false);
        ButterKnife.bind(this, root);
        gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new SpeakersAdapter(getContext(), presenter);
        recyclerView.setAdapter(adapter);
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.INTERNET},
                1);
        return root;
    }

    @Override
    public void setPresenter( SpeakersPresenter presenter) { this.presenter = presenter; }

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
    public void showToast(String test) {
        Toast.makeText(getContext(), test, Toast.LENGTH_SHORT).show();
    }
}

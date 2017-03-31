package tk.julianjurec.linuxsession14.Speakers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

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

    public SpeakersFragment() {
        //required empty public constructor
    }

    public static tk.julianjurec.linuxsession14.Speakers.SpeakersFragment newInstance() {

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

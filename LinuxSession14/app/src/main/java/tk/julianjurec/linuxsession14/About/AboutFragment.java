package tk.julianjurec.linuxsession14.About;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.About.AboutContract;
import tk.julianjurec.linuxsession14.About.AboutModule;
import tk.julianjurec.linuxsession14.About.AboutPresenter;
import tk.julianjurec.linuxsession14.About.DaggerAboutComponent;
import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AboutFragment extends Fragment implements AboutContract.View {

    @Inject
    public AboutPresenter presenter;

    public AboutFragment() {
        //required empty public constructor
    }

    public static tk.julianjurec.linuxsession14.About.AboutFragment newInstance() {

        Bundle args = new Bundle();

        tk.julianjurec.linuxsession14.About.AboutFragment fragment = new tk.julianjurec.linuxsession14.About.AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAboutComponent.builder().aboutModule(new AboutModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        return root;
    }

    @Override
    public void setPresenter( AboutPresenter presenter) { this.presenter = presenter; }

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

package tk.julianjurec.linuxsession14.MiddleParty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyContract;
import tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyModule;
import tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyPresenter;
import tk.julianjurec.linuxsession14.MiddleParty.DaggerMiddlePartyComponent;
import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */


public class MiddlePartyFragment extends Fragment implements MiddlePartyContract.View {

    @Inject
    public MiddlePartyPresenter presenter;

    public MiddlePartyFragment() {
        //required empty public constructor
    }

    public static tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyFragment newInstance() {

        Bundle args = new Bundle();

        tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyFragment fragment = new tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMiddlePartyComponent.builder().middlePartyModule(new MiddlePartyModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_middle_party, container, false);
        return root;
    }

    @Override
    public void setPresenter( MiddlePartyPresenter presenter) { this.presenter = presenter; }

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

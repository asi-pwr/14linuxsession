package tk.julianjurec.linuxsession14.MiddleParty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */

public class MiddlePartyFragment extends Fragment implements BaseFragment  {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_middle_party, container, false);
        return root;
    }

    public static MiddlePartyFragment newInstance() {

        Bundle args = new Bundle();

        MiddlePartyFragment fragment = new MiddlePartyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(Object presenter) {

    }
}

package tk.julianjurec.linuxsession14.Sponsors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */

public class SponsorsFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sponsors, container, false);
        return root;
    }

    public static SponsorsFragment newInstance() {

        Bundle args = new Bundle();

        SponsorsFragment fragment = new SponsorsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

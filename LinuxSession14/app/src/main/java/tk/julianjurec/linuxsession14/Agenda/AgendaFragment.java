package tk.julianjurec.linuxsession14.Agenda;

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

public class AgendaFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agenda, container, false);
        return root;
    }

    public static AgendaFragment newInstance() {

        Bundle args = new Bundle();

        AgendaFragment fragment = new AgendaFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

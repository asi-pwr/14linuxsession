package tk.julianjurec.linuxsession14.Agenda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AgendaFragment extends Fragment implements AgendaContract.View {

    public AgendaContract.Presenter presenter;

    public AgendaFragment() {
        //required empty public constructor
    }

    public static AgendaFragment newInstance() {

        Bundle args = new Bundle();

        AgendaFragment fragment = new AgendaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAgendaComponent.builder().agendaModule(new AgendaModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agenda, container, false);
        return root;
    }

    @Override
    public void setPresenter( AgendaContract.Presenter presenter) { this.presenter = presenter; }

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

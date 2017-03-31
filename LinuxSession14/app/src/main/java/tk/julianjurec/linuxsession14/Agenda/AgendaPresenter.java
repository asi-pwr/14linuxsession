package tk.julianjurec.linuxsession14.Agenda;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.Base.BasePresenter;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AgendaPresenter implements AgendaContract.Presenter {
    private AgendaFragment view;

    @Inject
    public AgendaPresenter(AgendaFragment view){
        this.view = view;
    }

    @Override
    public void start() {
        view.showToast("Agenda");
    }
}

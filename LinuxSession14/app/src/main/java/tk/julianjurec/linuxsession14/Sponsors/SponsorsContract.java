package tk.julianjurec.linuxsession14.Sponsors;

import tk.julianjurec.linuxsession14.Agenda.AgendaPresenter;
import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;

/**
 * Created by sp0rk on 22.03.17.
 */

public interface SponsorsContract {
    interface View extends BaseFragment<SponsorsPresenter> {
        void showToast(String test);
    }

    interface Presenter extends BasePresenter {

    }
}

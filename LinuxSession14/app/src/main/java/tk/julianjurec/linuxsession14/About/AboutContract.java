package tk.julianjurec.linuxsession14.About;

import tk.julianjurec.linuxsession14.Agenda.AgendaPresenter;
import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;

/**
 * Created by sp0rk on 22.03.17.
 */

public interface AboutContract {
    interface View extends BaseFragment<AboutPresenter> {
        void showToast(String test);
    }

    interface Presenter extends BasePresenter {

    }
}

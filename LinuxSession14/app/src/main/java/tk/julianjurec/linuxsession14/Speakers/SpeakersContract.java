package tk.julianjurec.linuxsession14.Speakers;

import tk.julianjurec.linuxsession14.Agenda.AgendaPresenter;
import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;

/**
 * Created by sp0rk on 22.03.17.
 */

public interface SpeakersContract {
    interface View extends BaseFragment<SpeakersPresenter> {
    }

    interface Presenter extends BasePresenter {
        void showSpeakerDialog();
    }
}

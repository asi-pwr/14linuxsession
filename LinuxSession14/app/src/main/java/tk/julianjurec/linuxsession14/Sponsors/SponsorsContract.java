package tk.julianjurec.linuxsession14.Sponsors;

import java.util.List;

import tk.julianjurec.linuxsession14.Agenda.AgendaPresenter;
import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;
import tk.julianjurec.linuxsession14.Model.Sponsor;

/**
 * Created by sp0rk on 22.03.17.
 */

public interface SponsorsContract {
    interface View extends BaseFragment<SponsorsPresenter> {

        void onSponsorsFetched(List<Sponsor> sponsors);

        void onSponsorsFetchFailure(Throwable throwable);
    }

    interface Presenter extends BasePresenter {

    }
}

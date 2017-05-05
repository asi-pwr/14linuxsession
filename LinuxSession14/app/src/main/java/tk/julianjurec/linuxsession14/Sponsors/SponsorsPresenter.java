package tk.julianjurec.linuxsession14.Sponsors;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.Model.Sponsor;

/**
 * Created by sp0rk on 22.03.17.
 */

public class SponsorsPresenter implements SponsorsContract.Presenter {
    private SponsorsFragment view;

    @Inject
    public SponsorsPresenter(SponsorsFragment view){
        this.view = view;
    }

    @Override
    public void start() {
        fetchSponsors();
    }

    private void fetchSponsors() {
        view.onSponsorsFetched(Sponsor.listAll(Sponsor.class));
    }

}

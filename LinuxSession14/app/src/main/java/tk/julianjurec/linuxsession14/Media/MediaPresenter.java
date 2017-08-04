package tk.julianjurec.linuxsession14.Media;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.Model.Media;
import tk.julianjurec.linuxsession14.Model.Sponsor;
import tk.julianjurec.linuxsession14.Sponsors.SponsorsContract;
import tk.julianjurec.linuxsession14.Sponsors.SponsorsFragment;

/**
 * Created by sp0rk on 22.03.17.
 */

public class MediaPresenter implements MediaContract.Presenter {
    private MediaFragment view;

    @Inject
    public MediaPresenter(MediaFragment view){
        this.view = view;
    }

    @Override
    public void start() {
        fetchSponsors();
    }

    private void fetchSponsors() {
        view.onSponsorsFetched(Sponsor.listAll(Media.class));
    }

}

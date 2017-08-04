package tk.julianjurec.linuxsession14.Media;

import java.util.List;

import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;
import tk.julianjurec.linuxsession14.Model.Media;
import tk.julianjurec.linuxsession14.Media.MediaPresenter;

/**
 * Created by sp0rk on 22.03.17.
 */

public interface MediaContract {
    interface View extends BaseFragment<MediaPresenter> {

        void onSponsorsFetched(List<Media> medias);

    }

    interface Presenter extends BasePresenter {

    }
}

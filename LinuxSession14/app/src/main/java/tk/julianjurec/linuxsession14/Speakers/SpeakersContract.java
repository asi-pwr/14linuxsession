package tk.julianjurec.linuxsession14.Speakers;

import java.util.List;

import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.Speaker;

/**
 * Created by sp0rk on 22.03.17.
 */

public interface SpeakersContract {
    interface View extends BaseFragment<SpeakersPresenter> {
        void onSpeakersFetchFailed(Throwable throwable);

        void onSpeakersFetched(List<Speaker> speakers);
    }

    interface Presenter extends BasePresenter {
        void showSpeakerDialog(Speaker speaker, Lecture lecture);

        void showLecture(Lecture lecture);
    }
}

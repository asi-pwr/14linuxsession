package tk.julianjurec.linuxsession14.Speakers;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.Base.MainActivity;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.Network.Api;

/**
 * Created by sp0rk on 22.03.17.
 */

public class SpeakersPresenter implements SpeakersContract.Presenter {
    private SpeakersFragment view;

    @Inject
    public SpeakersPresenter(SpeakersFragment view){
        this.view = view;
    }

    @Override
    public void start() {
        fetchSpeakers();
    }

    private void fetchSpeakers() {
        view.onSpeakersFetched(Speaker.listAll(Speaker.class));
    }

    @Override
    public void showSpeakerDialog(Speaker speaker, Lecture lecture) {

        SpeakersDialogFragment.newInstance(speaker, lecture).show(view.getChildFragmentManager(), "SpeakerDialog");
    }

    @Override
    public void showLecture(Lecture lecture) {
        if (lecture != null){

        }
    }
}

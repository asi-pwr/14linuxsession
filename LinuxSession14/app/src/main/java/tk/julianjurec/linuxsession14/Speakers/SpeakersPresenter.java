package tk.julianjurec.linuxsession14.Speakers;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.Speakers.SpeakersContract;
import tk.julianjurec.linuxsession14.Speakers.SpeakersFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;

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
    }

    @Override
    public void showSpeakerDialog() {
        SpeakersDialogFragment.newInstance().show(view.getFragmentManager(), "SpeakerDialog");
    }
}

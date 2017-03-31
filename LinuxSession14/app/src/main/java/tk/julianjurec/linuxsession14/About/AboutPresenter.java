package tk.julianjurec.linuxsession14.About;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.About.AboutContract;
import tk.julianjurec.linuxsession14.About.AboutFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AboutPresenter implements AboutContract.Presenter {
    private AboutFragment view;

    @Inject
    public AboutPresenter(AboutFragment view){
        this.view = view;
    }

    @Override
    public void start() {
        view.showToast("About");
    }
}


package tk.julianjurec.linuxsession14.Cosplay;

import javax.inject.Inject;

/**
 * Created by sp0rk on 22.03.17.
 */

public class CosplayPresenter implements CosplayContract.Presenter {

    private CosplayFragment view;

    @Inject
    public CosplayPresenter(CosplayFragment view){
        this.view = view;
    }

    @Override
    public void start() {

    }
}

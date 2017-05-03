package tk.julianjurec.linuxsession14.MiddleParty;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyContract;
import tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;

/**
 * Created by sp0rk on 22.03.17.
 */

public class MiddlePartyPresenter implements MiddlePartyContract.Presenter {
    private MiddlePartyFragment view;

    @Inject
    public MiddlePartyPresenter(MiddlePartyFragment view){
        this.view = view;
    }

    @Override
    public void start() {

    }
}

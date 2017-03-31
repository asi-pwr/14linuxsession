package tk.julianjurec.linuxsession14.MiddleParty;

import dagger.Module;
import dagger.Provides;
import tk.julianjurec.linuxsession14.About.AboutFragment;

/**
 * Created by sp0rk on 31.03.17.
 */

@Module
public class MiddlePartyModule {
    private final MiddlePartyFragment view;

    public MiddlePartyModule(MiddlePartyFragment view) {
        this.view = view;
    }

    @Provides
    MiddlePartyFragment provideMiddlePartyView() {
        return view;
    }
}
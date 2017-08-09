package tk.julianjurec.linuxsession14.Cosplay;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sp0rk on 31.03.17.
 */

@Module
public class CosplayModule {
    private final CosplayFragment view;

    public CosplayModule(CosplayFragment view) {
        this.view = view;
    }

    @Provides
    CosplayFragment provideMiddlePartyView() {
        return view;
    }
}
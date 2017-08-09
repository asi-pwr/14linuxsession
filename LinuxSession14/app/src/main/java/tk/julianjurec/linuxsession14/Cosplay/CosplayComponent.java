package tk.julianjurec.linuxsession14.Cosplay;

import dagger.Component;

/**
 * Created by sp0rk on 31.03.17.
 */

@Component(modules = CosplayModule.class)
public interface CosplayComponent {
    void inject(CosplayFragment middlePartyFragment);
}
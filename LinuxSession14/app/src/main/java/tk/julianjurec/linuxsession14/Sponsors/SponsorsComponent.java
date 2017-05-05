package tk.julianjurec.linuxsession14.Sponsors;

import dagger.Component;

/**
 * Created by sp0rk on 31.03.17.
 */

@Component(modules = SponsorsModule.class)
public interface SponsorsComponent {
    void inject(SponsorsFragment sponsorsFragment);
}
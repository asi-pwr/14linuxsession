package tk.julianjurec.linuxsession14.Network;

import javax.inject.Singleton;

import dagger.Component;
import tk.julianjurec.linuxsession14.Base.MainActivity;
import tk.julianjurec.linuxsession14.Base.SplashActivity;

/**
 * Created by sp0rk on 04.05.17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {
    void inject(MainActivity activity);
    void inject(SplashActivity splashActivity);
}
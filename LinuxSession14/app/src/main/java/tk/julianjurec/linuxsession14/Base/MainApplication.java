package tk.julianjurec.linuxsession14.Base;

import android.app.Application;

import tk.julianjurec.linuxsession14.Network.ApplicationModule;
import tk.julianjurec.linuxsession14.Network.DaggerNetworkComponent;
import tk.julianjurec.linuxsession14.Network.NetworkComponent;
import tk.julianjurec.linuxsession14.Network.NetworkModule;


/**
 * Created by sp0rk on 29.03.17.
 */

public class MainApplication extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("http://tramwaj.asi.wroclaw.pl:6937/"))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

}

package tk.julianjurec.linuxsession14.Base;

import android.app.Application;

import com.orm.SugarApp;

import tk.julianjurec.linuxsession14.Network.ApplicationModule;
import tk.julianjurec.linuxsession14.Network.DaggerNetworkComponent;
import tk.julianjurec.linuxsession14.Network.NetworkComponent;
import tk.julianjurec.linuxsession14.Network.NetworkModule;


/**
 * Created by sp0rk on 29.03.17.
 */

public class MainApplication extends SugarApp {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("http://138.197.25.138:6937/"))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

}

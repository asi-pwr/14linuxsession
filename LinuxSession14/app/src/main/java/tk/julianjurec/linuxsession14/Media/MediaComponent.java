package tk.julianjurec.linuxsession14.Media;

import dagger.Component;
import tk.julianjurec.linuxsession14.Media.MediaFragment;
import tk.julianjurec.linuxsession14.Media.MediaModule;

/**
 * Created by sp0rk on 31.03.17.
 */

@Component(modules = MediaModule.class)
public interface MediaComponent {
    void inject(MediaFragment mediaFragment);
}
package tk.julianjurec.linuxsession14.Media;

import dagger.Module;
import dagger.Provides;
import tk.julianjurec.linuxsession14.Media.MediaFragment;

/**
 * Created by sp0rk on 31.03.17.
 */

@Module
public class MediaModule {
    private final MediaFragment view;

    public MediaModule(MediaFragment view) {
        this.view = view;
    }

    @Provides
    MediaFragment provideSponsorsView() {
        return view;
    }
}
package tk.julianjurec.linuxsession14.Sponsors;

import dagger.Module;
import dagger.Provides;
import tk.julianjurec.linuxsession14.Speakers.SpeakersFragment;

/**
 * Created by sp0rk on 31.03.17.
 */

@Module
public class SponsorsModule {
    private final SponsorsFragment view;

    public SponsorsModule(SponsorsFragment view) {
        this.view = view;
    }

    @Provides
    SponsorsFragment provideSponsorsView() {
        return view;
    }
}
package tk.julianjurec.linuxsession14.Speakers;

import dagger.Module;
import dagger.Provides;
import tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyFragment;

/**
 * Created by sp0rk on 31.03.17.
 */

@Module
public class SpeakersModule {
    private final SpeakersFragment view;

    public SpeakersModule(SpeakersFragment view) {
        this.view = view;
    }

    @Provides
    SpeakersFragment provideSpeakersView() {
        return view;
    }
}
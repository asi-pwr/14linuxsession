package tk.julianjurec.linuxsession14.Speakers;

import dagger.Component;

/**
 * Created by sp0rk on 31.03.17.
 */

@Component(modules = SpeakersModule.class)
public interface SpeakersComponent {
    void inject(SpeakersFragment speakersFragment);
}
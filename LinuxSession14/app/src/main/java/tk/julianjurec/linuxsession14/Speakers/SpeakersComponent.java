package tk.julianjurec.linuxsession14.Speakers;

import dagger.Component;
import tk.julianjurec.linuxsession14.Agenda.AgendaFragment;
import tk.julianjurec.linuxsession14.Agenda.AgendaModule;

/**
 * Created by sp0rk on 31.03.17.
 */

@Component(modules = SpeakersModule.class)
public interface SpeakersComponent {
    void inject(SpeakersFragment speakersFragment);
}
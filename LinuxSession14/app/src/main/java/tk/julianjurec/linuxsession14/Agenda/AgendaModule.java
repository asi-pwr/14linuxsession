package tk.julianjurec.linuxsession14.Agenda;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sp0rk on 29.03.17.
 */

@Module
public class AgendaModule {
    private final AgendaFragment view;

    public AgendaModule(AgendaFragment view) {
        this.view = view;
    }

    @Provides
    AgendaFragment provideAgendaView() {
        return view;
    }
}

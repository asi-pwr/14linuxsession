package tk.julianjurec.linuxsession14.Agenda;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sp0rk on 29.03.17.
 */

@Module
public class AgendaModule {
    private final AgendaContract.View view;

    public AgendaModule(AgendaContract.View view) {
        this.view = view;
    }

    @Provides AgendaContract.View provideAgendaView() {
        return view;
    }
}

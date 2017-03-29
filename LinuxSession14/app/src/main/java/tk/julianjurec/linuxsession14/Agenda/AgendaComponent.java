package tk.julianjurec.linuxsession14.Agenda;

import dagger.Component;

/**
 * Created by sp0rk on 29.03.17.
 */

@Component(modules = {AgendaModule.class})
public interface AgendaComponent {
    void inject(AgendaFragment agendaFragment);
}

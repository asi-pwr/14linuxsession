package tk.julianjurec.linuxsession14.MiddleParty;

import dagger.Component;
import tk.julianjurec.linuxsession14.Agenda.AgendaFragment;
import tk.julianjurec.linuxsession14.Agenda.AgendaModule;

/**
 * Created by sp0rk on 31.03.17.
 */

@Component(modules = MiddlePartyModule.class)
public interface MiddlePartyComponent {
    void inject(MiddlePartyFragment middlePartyFragment);
}
package tk.julianjurec.linuxsession14.About;

import dagger.Component;
import tk.julianjurec.linuxsession14.Agenda.AgendaFragment;
import tk.julianjurec.linuxsession14.Agenda.AgendaModule;

/**
 * Created by sp0rk on 31.03.17.
 */

@Component(modules = AboutModule.class)
public interface AboutComponent {
    void inject(AboutFragment AboutFragment);
}

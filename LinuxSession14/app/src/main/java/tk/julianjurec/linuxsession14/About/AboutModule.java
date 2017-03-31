package tk.julianjurec.linuxsession14.About;

import dagger.Module;
import dagger.Provides;
import tk.julianjurec.linuxsession14.Agenda.AgendaFragment;

/**
 * Created by sp0rk on 31.03.17.
 */

@Module
public class AboutModule {
    private final AboutFragment view;

    public AboutModule(AboutFragment view) {
        this.view = view;
    }

    @Provides
    AboutFragment provideAboutView() {
        return view;
    }
}
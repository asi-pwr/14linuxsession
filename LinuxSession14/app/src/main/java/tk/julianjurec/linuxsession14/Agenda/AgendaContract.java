package tk.julianjurec.linuxsession14.Agenda;

import java.util.List;

import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;
import tk.julianjurec.linuxsession14.Model.Lecture;

/**
 * Created by sp0rk on 22.03.17.
 */

public interface AgendaContract {
    interface View extends BaseFragment<AgendaPresenter> {

        void onLectureFetchFailed(Throwable throwable);

        void onLecturesFetched(List<Lecture> lectures);
    }

    interface Presenter extends BasePresenter {

        boolean toggleFavourite(Lecture lecture);

        boolean share(Lecture lecture);

        Presenter onlyFavourites();
    }
}

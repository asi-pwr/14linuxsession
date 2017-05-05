package tk.julianjurec.linuxsession14.Agenda;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.Base.MainActivity;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Network.Api;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AgendaPresenter implements AgendaContract.Presenter {
    private Api api;
    private AgendaFragment view;
    private boolean onlyFavourites = false;

    @Inject
    public AgendaPresenter(AgendaFragment view) {
        this.view = view;
        api = ((MainActivity) view.getActivity()).getRetrofit().create(Api.class);
    }

    @Override
    public void start() {
        fetchLectures();
    }

    private void fetchLectures() {
        List<Lecture> lectures = Lecture.listAll(Lecture.class);
        List<Lecture> favs = new ArrayList<>();
        if (onlyFavourites) {
            for (Lecture lecture : lectures) {
                if (lecture.getFav())
                    favs.add(lecture);
            }
            view.onLecturesFetched(favs);
        } else
            view.onLecturesFetched(lectures);

    }

    @Override
    public boolean toggleFavourite(Lecture lecture) {
        lecture.setFav(!lecture.getFav());
        lecture.save();
        return false;
    }

    @Override
    public boolean share(Lecture lecture) {
        return false;
    }

    @Override
    public AgendaContract.Presenter onlyFavourites() {
        onlyFavourites = true;
        return this;
    }
}

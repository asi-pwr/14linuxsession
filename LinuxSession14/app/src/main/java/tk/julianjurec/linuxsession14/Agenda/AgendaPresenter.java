package tk.julianjurec.linuxsession14.Agenda;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tk.julianjurec.linuxsession14.Base.BasePresenter;
import tk.julianjurec.linuxsession14.Base.MainActivity;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.LecturesResponse;
import tk.julianjurec.linuxsession14.Network.Api;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AgendaPresenter implements AgendaContract.Presenter {
    private Api api;
    private AgendaFragment view;

    @Inject
    public AgendaPresenter(AgendaFragment view){
        this.view = view;
        api = ((MainActivity) view.getActivity()).getRetrofit().create(Api.class);
    }

    @Override
    public void start() {
        fetchLectures();
    }

    private void fetchLectures() {
        view.onLecturesFetched(Lecture.listAll(Lecture.class));
    }

    @Override
    public boolean toggleFavourite(Lecture lecture) {
        return false;
    }

    @Override
    public boolean share(Lecture lecture) {
        return false;
    }
}

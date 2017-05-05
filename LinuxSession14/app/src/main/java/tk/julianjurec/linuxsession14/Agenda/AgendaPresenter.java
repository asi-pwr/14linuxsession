package tk.julianjurec.linuxsession14.Agenda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.yarolegovich.lovelydialog.LovelyChoiceDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.Base.MainActivity;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Network.Api;
import tk.julianjurec.linuxsession14.R;

import static tk.julianjurec.linuxsession14.R.color.session_light;
import static tk.julianjurec.linuxsession14.R.color.white;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AgendaPresenter implements AgendaContract.Presenter {
    private Api api;
    private AgendaFragment view;

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
        view.onLecturesFetched(Lecture.listAll(Lecture.class));
    }

    @Override
    public boolean share(Lecture lecture) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = "Jestem na Sesji Linuksowej!";
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, lecture.getTitle());
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        if (intent.resolveActivity(view.getContext().getPackageManager()) != null) {
            view.getContext().startActivity(intent);
        } else {
            Toast.makeText(view.getContext(), R.string.error_no_share_app, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}

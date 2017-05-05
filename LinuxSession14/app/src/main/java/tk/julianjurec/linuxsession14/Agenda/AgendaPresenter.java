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
        String[] items = new String[]{"LinkedIn", "Twitter", "Inne"};
        new LovelyChoiceDialog(view.getContext())
                .setTopColorRes(session_light)
                .setIcon(R.drawable.share)
                .setIconTintColor(white)
                .setTopTitle(R.string.lecture_share)
                .setTopTitleColor(white)
                .setItems(items, (position, item) -> {
                    shareChosen(item, lecture.getTitle());
                })
                .setMessage(R.string.lecture_share_msg)
                .setCancelable(true)
                .show();
        return false;
    }

    private void shareChosen(String item, String title){
        Intent intent = prepareIntent(item, title);
        if (intent.resolveActivity(view.getContext().getPackageManager()) != null) {
            view.getContext().startActivity(intent);
        } else {
            Toast.makeText(view.getContext(), R.string.error_no_share_app, Toast.LENGTH_SHORT).show();
        }
    }

    private Intent prepareIntent(String choice, String title){
        Intent intent;
        switch (choice){
            case "LinkedIn":
                String url = "http://www.twitter.com/intent/tweet?url=http://14.sesja.linuksowa.pl/pl&text=" + title;
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                break;
            case "Twitter":
                intent = new Intent(Intent.ACTION_SEND);
                intent.setClassName("com.linkedin.android",
                        "com.linkedin.android.home.UpdateStatusActivity");
                intent.setType("text/*");
                intent.putExtra(Intent.EXTRA_TEXT, "Jestem na Sesji Linuksowej!\nZapraszam na prelekcję: " + title);
                break;
            case "Inne":
            default:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "Jestem na Sesji Linuksowej!";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                intent.putExtra(Intent.EXTRA_TEXT, "Zapraszam na prelekcję: " + title);
                break;
        }
        return intent;
    }

}

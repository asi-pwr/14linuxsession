package tk.julianjurec.linuxsession14.Agenda;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

import com.yarolegovich.lovelydialog.LovelyChoiceDialog;

import java.util.List;

import javax.inject.Inject;

import tk.julianjurec.linuxsession14.Base.MainActivity;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Network.Api;
import tk.julianjurec.linuxsession14.R;

import static tk.julianjurec.linuxsession14.R.color.session_light;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AgendaPresenter implements AgendaContract.Presenter {
    private AgendaFragment view;

    @Inject
    public AgendaPresenter(AgendaFragment view) {
        this.view = view;
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
        int white = view.getContext().getResources().getColor(R.color.white);
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
            case "Twitter":
                String url = "http://www.twitter.com/intent/tweet?url=http://niucon.pl//pl&text=" + title;
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                break;
            case "LinkedIn":
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Jestem na Niuconie!\nZapraszam na prelekcję: " + title);

                List<ResolveInfo> matches2 = view.getContext().getPackageManager()
                        .queryIntentActivities(intent, 0);

                for (ResolveInfo info : matches2) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith(
                            "com.linkedin")) {
                        intent.setPackage(info.activityInfo.packageName);
                        break;
                    }
                }
                break;
            case "Inne":
            default:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "Jestem na Niuconie!";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                intent.putExtra(Intent.EXTRA_TEXT, "Zapraszam na prelekcję: " + title);
                break;
        }
        return intent;
    }

}

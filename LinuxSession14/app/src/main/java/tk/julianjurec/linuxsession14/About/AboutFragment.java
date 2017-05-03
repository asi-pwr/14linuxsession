package tk.julianjurec.linuxsession14.About;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AboutFragment extends Fragment implements AboutContract.View {

    @Inject
    public AboutPresenter presenter;

    @BindColor(R.color.session_gold) int sessionGold;
    @BindColor(R.color.session_light) int sessionLight;
    @BindColor(R.color.session_dark) int sessionDark;
    @BindColor(R.color.white) int white;

    private static final TreeMap<String, String> contact_emails;
    static {
        contact_emails = new TreeMap<>();
        contact_emails.put("Organizator", "kontakt@asi.pwr.wroc.pl");
        contact_emails.put("Twórca Aplikacji", "julian.jurec@gmail.com");
    }

    public AboutFragment() {
        //required empty public constructor
    }

    public static tk.julianjurec.linuxsession14.About.AboutFragment newInstance() {

        Bundle args = new Bundle();

        tk.julianjurec.linuxsession14.About.AboutFragment fragment = new tk.julianjurec.linuxsession14.About.AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAboutComponent.builder().aboutModule(new AboutModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setPresenter( AboutPresenter presenter) { this.presenter = presenter; }

    @Override
    public void onResume() {
        super.onResume();
        try {
            presenter.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.about_card_looks, R.id.about_card_target, R.id.about_card_what, R.id.about_card_questions})
    public void onCardClicked(CardView v){
        switch (v.getId()){
            case R.id.about_card_looks:
                new LovelyInfoDialog(getContext())
                        .setTopColor(sessionLight)
                        .setConfirmButtonColor(sessionGold)
                        .setIcon(R.drawable.how_it_looks)
                        .setTopTitle(R.string.about_looks)
                        .setTopTitleColor(white)
                        .setMessage(R.string.about_looks_details)
                        .setConfirmButtonText("Ok")
                        .setCancelable(true)
                        .show();
                break;
            case R.id.about_card_what:
                new LovelyInfoDialog(getContext())
                        .setTopColor(sessionLight)
                        .setConfirmButtonColor(sessionGold)
                        .setIcon(R.drawable.what_is_it)
                        .setIconTintColor(white)
                        .setTopTitle(R.string.about_what)
                        .setTopTitleColor(white)
                        .setMessage(R.string.about_what_details)
                        .setConfirmButtonText("Ok")
                        .setCancelable(true)
                        .show();
                break;
            case R.id.about_card_target:
                new LovelyInfoDialog(getContext())
                        .setTopColor(sessionLight)
                        .setConfirmButtonColor(sessionGold)
                        .setIcon(R.drawable.target)
                        .setTopTitle(R.string.about_target)
                        .setIconTintColor(white)
                        .setTopTitleColor(white)
                        .setMessage(R.string.about_target_details)
                        .setConfirmButtonText("Ok")
                        .setCancelable(true)
                        .show();
                break;
            case R.id.about_card_questions:
                List<String> items = new ArrayList<>(contact_emails.keySet());
                new LovelyChoiceDialog(getContext())
                        .setTopColor(sessionLight)
                        .setIcon(R.drawable.ic_mail_outline_black_72dp)
                        .setIconTintColor(white)
                        .setTopTitle(R.string.about_questions)
                        .setTopTitleColor(white)
                        .setItems(items, (position, item) -> {
                            System.out.println(item + position + contact_emails.get(item));
                            Intent intent = new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("mailto:" + contact_emails.get(item)));
                            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_email_subject));
                            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                                startActivity(intent);
                            } else {
                                Toast.makeText(getContext(), R.string.error_no_email_app, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setMessage(R.string.about_questions_details)
                        .setCancelable(true)
                        .show();
                break;
        }
    }

}

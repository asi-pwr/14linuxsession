package tk.julianjurec.linuxsession14.About;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
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
    @BindColor(R.color.session_text_dark) int sessionLight;
    @BindColor(R.color.session_dark) int sessionDark;
    @BindColor(R.color.white) int white;

    private static final TreeMap<String, String> contact_emails;
    static {
        contact_emails = new TreeMap<>();
        contact_emails.put("Cosplay", "cosplay@niucon.pl");
        contact_emails.put("Media, patronaty", "media@niucon.pl");
        contact_emails.put("Program", "program@niucon.pl");
        contact_emails.put("Sponsorzy", "sponsor@niucon.pl");
        contact_emails.put("Wystawcy", "wystawcy@niucon.pl");
        contact_emails.put("Inne sprawy", "info@niucon.pl");
        contact_emails.put("Twórca Aplikacji", "jak_dor@wp.pl");
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

    @OnClick({R.id.about_card_what, R.id.about_card_questions})
    public void onCardClicked(CardView v){
        switch (v.getId()){
            case R.id.about_card_what:
                Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.about_dialog);
                dialog.show();

                TextView info = (TextView) dialog.findViewById(R.id.about_dialog_content);
                info.setMovementMethod(LinkMovementMethod.getInstance());
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

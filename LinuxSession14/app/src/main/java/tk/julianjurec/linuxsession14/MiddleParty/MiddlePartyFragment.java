package tk.julianjurec.linuxsession14.MiddleParty;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jaouan.revealator.Revealator;
import com.varunest.sparkbutton.SparkButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */


public class MiddlePartyFragment extends Fragment implements MiddlePartyContract.View {


    private static final int SET_ALARM_PERMISSION = 1;
    @Inject
    public MiddlePartyPresenter presenter;
    @BindView(R.id.middle_party_what_fullscreen) FrameLayout whatRevealed;
    @BindView(R.id.middle_party_where_fullscreen) FrameLayout whereRevealed;
    @BindView(R.id.middle_party_why_fullscreen) FrameLayout whyRevealed;
    @BindView(R.id.middle_party_when_fullscreen) FrameLayout whenRevealed;

    @BindView(R.id.middle_party_card_what) CardView whatCard;
    @BindView(R.id.middle_party_card_when) CardView whenCard;
    @BindView(R.id.middle_party_card_where) CardView whereCard;
    @BindView(R.id.middle_party_card_why) CardView whyCard;


    public MiddlePartyFragment() {
        //required empty public constructor
    }

    public static tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyFragment newInstance() {

        Bundle args = new Bundle();

        tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyFragment fragment = new tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMiddlePartyComponent.builder().middlePartyModule(new MiddlePartyModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_middle_party, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setPresenter( MiddlePartyPresenter presenter) { this.presenter = presenter; }

    @Override
    public void onResume() {
        super.onResume();
        try {
            presenter.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.middle_party_card_what, R.id.middle_party_card_when, R.id.middle_party_card_where, R.id.middle_party_card_why})
    public void onCardClicked(CardView v){

        for (CardView cardView : cards()) {
            cardView.setClickable(false);
        }
        v.bringToFront();

        View revealed;
        switch (v.getId()){
            case R.id.middle_party_card_what:
                revealed = whatRevealed;
                break;
            case R.id.middle_party_card_when:
                revealed = whenRevealed;
                break;
            case R.id.middle_party_card_where:
                revealed = whereRevealed;
                break;
            case R.id.middle_party_card_why:
            default:
                revealed = whyRevealed;
                break;

        }
        Revealator.reveal(revealed)
                .from(v)
                .withCurvedTranslation()
                .start();
    }

    @OnClick({R.id.middle_party_what_close, R.id.middle_party_where_close, R.id.middle_party_when_close, R.id.middle_party_why_close})
    public void onCardClosed(View v){

        for (CardView cardView : cards()) {
            cardView.setClickable(true);
        }
        View unrevealed;
        View card;
        switch (v.getId()){
            case R.id.middle_party_what_close:
                unrevealed = whatRevealed;
                card = whatCard;
                break;
            case R.id.middle_party_when_close:
                unrevealed = whenRevealed;
                card = whenCard;
                break;
            case R.id.middle_party_where_close:
                unrevealed = whereRevealed;
                card = whereCard;
                break;
            case R.id.middle_party_why_close:
            default:
                unrevealed = whyRevealed;
                card = whyCard;
                break;

        }
        Revealator.unreveal(unrevealed)
                .to(card)
                .withCurvedTranslation()
                .start();
    }

    @OnClick(R.id.middle_party_where_directions)
    public void onDirectionsClick(){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=Wrocław,%20pub%20Kontynuacja"));
        startActivity(intent);
    }

    private CardView[] cards(){
        return new CardView[]{whenCard, whereCard, whyCard, whatCard};
    }

}

package tk.julianjurec.linuxsession14.Speakers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 01.05.17.
 */

public class SpeakersDialogFragment extends DialogFragment {

    public static SpeakersDialogFragment newInstance() {
        Bundle args = new Bundle();
        SpeakersDialogFragment fragment = new SpeakersDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SpeakersDialogFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.SpeakerDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.speaker_dialog, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.dialog_speaker_outside)
    public void dismissOnClickOutside(){
        dismiss();
    }
}

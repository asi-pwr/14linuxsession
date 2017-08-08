package tk.julianjurec.linuxsession14.Speakers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.Room;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.R;

import static android.view.View.GONE;

/**
 * Created by sp0rk on 01.05.17.
 */

public class SpeakersDialogFragment extends DialogFragment {
    private Speaker speaker;
    private List<Lecture> lecture;

    @BindView(R.id.dialog_speaker_img)
    ImageView img;
    @BindView(R.id.dialog_speaker_name)
    TextView name;
    @BindView(R.id.dialog_speaker_description)
    TextView description;
    //@BindView(R.id.dialog_speaker_lecture_name)
    //TextView lectureName;
    //@BindView(R.id.dialog_speaker_lecture_time)
    //TextView lectureTime;
    @BindView(R.id.dialog_speaker_lecture_card)
    CardView lectureCard;
    private SpeakersPresenter presenter;

    public static SpeakersDialogFragment newInstance(Speaker speaker, List<Lecture> lecture) {
        Bundle args = new Bundle();
        args.putSerializable("speaker", speaker);
        args.putSerializable("lecture", (Serializable)lecture);
        SpeakersDialogFragment fragment = new SpeakersDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SpeakersDialogFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        speaker = (Speaker) getArguments().getSerializable("speaker");

        lecture = (List<Lecture>) getArguments().getSerializable("lecture");

        presenter = ((SpeakersFragment)getParentFragment()).getPresenter();
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.SpeakerDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_speaker, container, false);
        ButterKnife.bind(this, v);
        onBindView(v);
        return v;
    }

    private void onBindView(View dialogView) {
        name.setText(speaker.getName());
        description.setText(speaker.getDescription());

        if (!lecture.isEmpty()) {

            List<Room> rooms = Room.listAll(Room.class);

            LinearLayout layout = (LinearLayout)
                    dialogView.findViewById(R.id.dialog_speaker_lecture_inflater);

            for(Lecture LectureItem : lecture) {
                View view = getDialog().getLayoutInflater().
                        inflate(R.layout.dialog_speaker_lecture_item, layout, false);

                TextView lectureName = (TextView) view.findViewById(R.id.dialog_speaker_lecture_name);
                TextView lectureTime = (TextView) view.findViewById(R.id.dialog_speaker_lecture_time);

                lectureName.setText(LectureItem.getTitle());

                int roomNum = LectureItem.getRoomId() - 1;
                String roomStr;

                if(roomNum == 19) {
                    roomStr = "Wyróżnione";
                }
                else if (roomNum == 20){
                    roomStr = "nie działa :<";
                }
                else {
                    roomStr = rooms.get(roomNum).getDescription();
                    if(roomStr.isEmpty()){
                        roomStr = rooms.get(roomNum).getName();
                    }
                }

                if(!roomStr.isEmpty()){
                    roomStr = " (" + roomStr + ")";
                }

                String dayStr;

                if (LectureItem.getDay() == 1) {
                    dayStr = "Piątek, ";
                } else if (LectureItem.getDay() == 2) {
                    dayStr = "Sobota, ";
                } else {
                    dayStr = "Niedziela, ";
                }

                lectureTime.setText(dayStr + LectureItem.getStartTime() + "-"
                        + LectureItem.getEndTime() + roomStr);

                layout.addView(view);
            }

        } else {
            lectureCard.setVisibility(GONE);
            ScrollView scrollView =
                    (ScrollView) dialogView.findViewById(R.id.dialog_speaker_lecture_inflater_scroll);
            scrollView.setVisibility(GONE);
        }

        if (speaker.getImgUrl() != null && !speaker.getImgUrl().isEmpty()) {
            Picasso.with(getContext())
                    .load(speaker.getImgUrl())
                    .resize(600, 600)
                    .onlyScaleDown()
                    .centerInside()
                    .placeholder(R.drawable.unknown)
                    .into(img);
        }

    }

    @OnClick(R.id.dialog_speaker_outside)
    public void dismissOnClickOutside() {
        dismiss();
    }


    @OnClick(R.id.dialog_speaker_lecture_card)
    public void launchLecture(){
        if (presenter != null) {
            //presenter.showLecture(lecture.get(0));
        }
    }

}

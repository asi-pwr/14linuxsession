package tk.julianjurec.linuxsession14.Agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.Room;
import tk.julianjurec.linuxsession14.Model.RoomResponse;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AgendaFragment extends Fragment implements AgendaContract.View {

    @Inject
    public AgendaPresenter presenter;

    @BindView(R.id.agenda_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.agenda_spinner)
    Spinner agendaSpinner;

    private LinearLayoutManager linearLayoutManager;
    private SectionedRecyclerViewAdapter adapter;
    private List<Lecture> lectures;
    private ArrayAdapter<String> agendaRoomsAdapter;
    private static int chosenIndex = 20;


    public AgendaFragment() {
        //required empty public constructor
    }

    public static AgendaFragment newInstance() {

        Bundle args = new Bundle();
        AgendaFragment fragment = new AgendaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAgendaComponent.builder().agendaModule(new AgendaModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agenda, container, false);
        ButterKnife.bind(this, root);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0,0);

        loadSpinnerItems();
        initSpinner();

        return root;
    }

    @Override
    public void setPresenter( AgendaPresenter presenter) { this.presenter = presenter; }

    @Override
    public void onResume() {
        super.onResume();
        try {
            presenter.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLectureFetchFailed(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLecturesFetched(List<Lecture> lectures) {
        adapter = new SectionedRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        this.lectures = lectures;
        loadContent(chosenIndex);
    }

    private void loadContent(int room){
        ArrayList<Lecture> day1 = new ArrayList<>();
        ArrayList<Lecture> day2 = new ArrayList<>();
        ArrayList<Lecture> day3 = new ArrayList<>();

        for (Lecture lecture : lectures) {
            if(lecture.getRoomId() == null) {
                break;
            }

            if(lecture.getRoomId() == room){
                if (lecture.getDay() == 1) {
                    day1.add(lecture);
                } else if (lecture.getDay() == 2) {
                    day2.add(lecture);
                } else {
                    day3.add(lecture);
                }
            }
        }

        if(!day1.isEmpty()) {
            adapter.addSection(new AgendaSection(recyclerView, getContext(), day1, "Piątek", presenter));
        }
        if(!day2.isEmpty()) {
            adapter.addSection(new AgendaSection(recyclerView, getContext(), day2, "Sobota", presenter));
        }
        if(!day3.isEmpty()) {
            adapter.addSection(new AgendaSection(recyclerView, getContext(), day3, "Niedziela", presenter));
        }

    }

    private void discardContent(){
        adapter.removeAllSections();
        recyclerView.clearFocus();
        recyclerView.removeAllViews();
    }

    private void loadSpinnerItems(){
        List<Room> rooms = Room.listAll(Room.class);

        ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add("Wyróżnione");

        for(Room room : rooms){
            if(!room.getDescription().isEmpty() && room.getId() != 20) {
                spinnerList.add(room.getName() + " (" + room.getDescription() + ")");
            }
            else{
                if (room.getId() != 21 && room.getId() != 20) {
                    spinnerList.add(room.getName());
                }
            }
        }

        agendaRoomsAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, spinnerList);
        agendaRoomsAdapter.setDropDownViewResource(R.layout.spinner_item);
    }

    private void initSpinner(){
        agendaSpinner.setAdapter(agendaRoomsAdapter);
        if(chosenIndex == 20) {
            agendaSpinner.setSelection(0);
        }
        else {
            agendaSpinner.setSelection(chosenIndex);
        }

        agendaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private int init = 0;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(++init > 1) {
                    discardContent();
                    if(position == 0){
                        loadContent(20);
                        chosenIndex = 20;
                    }
                    else {
                        loadContent(position);
                        chosenIndex = position;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

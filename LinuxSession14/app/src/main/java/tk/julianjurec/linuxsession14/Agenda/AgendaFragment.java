package tk.julianjurec.linuxsession14.Agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 22.03.17.
 */

public class AgendaFragment extends Fragment implements AgendaContract.View {

    @Inject
    public AgendaPresenter presenter;

    @BindView(R.id.agenda_recycler_view)
    RecyclerView recyclerView;


    private LinearLayoutManager linearLayoutManager;
    private SectionedRecyclerViewAdapter adapter;


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
        ArrayList<Lecture> day1 = new ArrayList<>();
        ArrayList<Lecture> day2 = new ArrayList<>();

        for (Lecture lecture : lectures) {
            if (lecture.getDay()==1) {
                day1.add(lecture);
            } else
                day2.add(lecture);
        }
        adapter.addSection(new AgendaSection(recyclerView, getContext(), day1, "Sobota", presenter));
        adapter.addSection(new AgendaSection(recyclerView, getContext(), day2, "Niedziela", presenter));
        recyclerView.setAdapter(adapter);
    }
}

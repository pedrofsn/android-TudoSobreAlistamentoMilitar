package alistamento.militar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import alistamento.militar.R;
import alistamento.militar.adapter.AdapterDuvida;
import alistamento.militar.domain.Searchable;
import alistamento.militar.model.Duvida;
import alistamento.militar.task.CarregarDuvidasJSONAsyncTask;

/**
 * Created by pedrofsn on 11/01/2015.
 */
public class FragmentPerguntas extends Fragment implements Searchable {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perguntas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new CarregarDuvidasJSONAsyncTask(getActivity(), this).execute();
    }

    @Override
    public void onLoading(boolean status) {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onLoaded(Duvida[] result) {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        if (result != null && result.length >= 1) {
            recyclerView.setAdapter(new AdapterDuvida(getActivity(), result));
        }
    }
}

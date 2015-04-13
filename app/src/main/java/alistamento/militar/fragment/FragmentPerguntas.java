package alistamento.militar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import alistamento.militar.R;
import alistamento.militar.adapter.AdapterDuvida;
import alistamento.militar.domain.Searchable;
import alistamento.militar.model.Duvida;
import alistamento.militar.task.CarregarDuvidasJSONAsyncTask;

/**
 * Created by pedrofsn on 11/01/2015.
 */
public class FragmentPerguntas extends Fragment implements Searchable, SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MenuItem searchItem;
    private SearchView searchView;

    private List<Duvida> listDuvidasCarregadas;
    private List<Duvida> resultadoPesquisa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        searchItem = menu.findItem(R.id.pesquisar);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint(Html.fromHtml("<font color = #cccccc>" + getString(R.string.pesquisar) + "</font>"));
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onLoading(boolean status) {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onLoaded(List<Duvida> listDuvidasCarregadas) {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        if (listDuvidasCarregadas != null && listDuvidasCarregadas.size() >= 1) {
            this.listDuvidasCarregadas = listDuvidasCarregadas;
            setContent(listDuvidasCarregadas);
        }
    }

    private void setContent(final List<Duvida> listDuvidasCarregadas) {
        recyclerView.setAdapter(new AdapterDuvida(getActivity(), listDuvidasCarregadas));
    }

    @Override
    public boolean onQueryTextChange(String textoDigitado) {
        if (textoDigitado != null && textoDigitado.length() == 0) {
            setContent(listDuvidasCarregadas);
            MenuItemCompat.collapseActionView(searchItem);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String textoPesquisado) {
        realizaBusca(textoPesquisado);
        return true;
    }

    private void realizaBusca(String textoPesquisado) {
        resultadoPesquisa = new ArrayList<>();
        if (listDuvidasCarregadas != null) {
            for (Duvida duvida : listDuvidasCarregadas) {
                if (duvida.getPergunta().toLowerCase().contains(textoPesquisado.toLowerCase())) {
                    resultadoPesquisa.add(duvida);
                }
            }

            setContent(resultadoPesquisa);
        }
    }

}

package alistamento.militar.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alistamento.militar.R;
import alistamento.militar.dialog.SimpleDialog;
import alistamento.militar.model.Duvida;

/**
 * Created by pedrofsn on 11/01/2015.
 */
public class AdapterDuvida extends RecyclerView.Adapter<AdapterDuvida.ViewHolder> {

    public static Duvida duvidaSelecionada;
    private Duvida[] listDuvidas;
    private Context context;

    public AdapterDuvida(Context context, Duvida[] result) {
        this.context = context;
        this.listDuvidas = result;
    }

    @Override
    public AdapterDuvida.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_duvida, parent, false));
        holder.textView.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        duvidaSelecionada = listDuvidas[position];

        holder.textView.setText(duvidaSelecionada.getPergunta());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialog.show(((FragmentActivity) context));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDuvidas.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
        }
    }
}

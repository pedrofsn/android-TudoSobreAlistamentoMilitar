package alistamento.militar.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import alistamento.militar.R;
import alistamento.militar.dialog.SimpleDialog;
import alistamento.militar.model.Duvida;

/**
 * Created by pedrofsn on 11/01/2015.
 */
public class AdapterDuvida extends RecyclerView.Adapter<AdapterDuvida.ViewHolder> {

    public static Duvida duvida;
    private List<Duvida> listDuvidas;
    private Context context;

    public AdapterDuvida(Context context, List<Duvida> result) {
        this.listDuvidas = result;
        this.context = context;
    }

    @Override
    public AdapterDuvida.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_duvida, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(listDuvidas.get(position).getPergunta());
    }

    @Override
    public int getItemCount() {
        return listDuvidas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //O código da biblioteca exige que a variável seja static
            AdapterDuvida.duvida = listDuvidas.get(getAdapterPosition());
            SimpleDialog.show((FragmentActivity) context);
        }
    }
}

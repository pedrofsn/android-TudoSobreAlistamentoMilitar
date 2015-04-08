package alistamento.militar.domain;

import alistamento.militar.models.Duvida;

/**
 * Created by pedrofsn on 10/01/2015.
 */
public interface Searchable {

    public void onLoading(boolean status);

    public void onLoaded(Duvida[] result);
}

package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class ListaFavoritos extends AppStateAdapter{

    public ListaFavoritos(AppData dados) {
        super(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Lista_Favoritos;
    }
}

package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class ListaQuartosPessoal extends AppStateAdapter{
    public ListaQuartosPessoal(AppData dados) {
        super(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Lista_Quartos_Pessoal;
    }
}

package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class EditarAnuncio  extends AppStateAdapter{

    public EditarAnuncio(AppData dados) {
        super(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Editar_anuncio;
    }
}

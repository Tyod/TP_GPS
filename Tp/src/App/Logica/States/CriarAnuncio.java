package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class CriarAnuncio extends AppStateAdapter{

    public CriarAnuncio(AppData dados) {
        super(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Criar_Anuncio;
    }
}

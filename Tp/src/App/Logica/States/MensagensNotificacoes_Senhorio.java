package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class MensagensNotificacoes_Senhorio extends AppStateAdapter{

    public MensagensNotificacoes_Senhorio(AppData dados) {
        super(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.MensagensNotificacoes_Senhorio;
    }
}

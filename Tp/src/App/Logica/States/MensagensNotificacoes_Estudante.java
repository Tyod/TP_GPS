package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class MensagensNotificacoes_Estudante extends AppStateAdapter{

    public MensagensNotificacoes_Estudante(AppData dados) {
        super(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Mensagens_Notificacoes_Estudante;
    }
}

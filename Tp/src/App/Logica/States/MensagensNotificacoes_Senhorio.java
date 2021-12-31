package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class MensagensNotificacoes_Senhorio extends AppStateAdapter{

    public MensagensNotificacoes_Senhorio(AppData dados) { super(dados); }

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    public IAppState geraVistaListaQuartosPessoal(){ return new ListaQuartosPessoal(dados); }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.MensagensNotificacoes_Senhorio;
    }
}

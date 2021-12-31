package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class MensagensNotificacoes_Estudante extends AppStateAdapter{

    public MensagensNotificacoes_Estudante(AppData dados) {
        super(dados);
    }

    public IAppState geraVistaListaQuartosPublicados(){ return new ListaQuartosPublicados(dados); }

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    public IAppState geraVistaFavoritos() {
        return new ListaFavoritos(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Mensagens_Notificacoes_Estudante;
    }
}

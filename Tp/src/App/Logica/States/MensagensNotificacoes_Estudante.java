package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;
import App.Logica.Data.Mensagem;
import App.Logica.Data.TipoUtilzadores;

import java.util.ArrayList;

public class MensagensNotificacoes_Estudante extends AppStateAdapter{

    public MensagensNotificacoes_Estudante(AppData dados) {
        super(dados);
    }

    public ArrayList<Mensagem> getListaMensagens() { return dados.getListaMensagens(); }

    public void adicionaMensagem(TipoUtilzadores utilizador, String msg) { dados.adicionaMensagem(utilizador,msg); }

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

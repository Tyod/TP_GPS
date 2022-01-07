package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;
import App.Logica.Data.Mensagem;
import App.Logica.Data.Quarto;
import App.Logica.Data.TipoUtilzadores;

import java.util.ArrayList;

public class MensagensNotificacoes_Senhorio extends AppStateAdapter{

    public MensagensNotificacoes_Senhorio(AppData dados) { super(dados); }

    public ArrayList<Mensagem> getListaMensagens() { return dados.getListaMensagens(); }

    public void adicionaMensagem(TipoUtilzadores utilizador, String msg) { dados.adicionaMensagem(utilizador,msg); }

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    public IAppState geraVistaListaQuartosPessoal(){ return new ListaQuartosPessoal(dados); }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.MensagensNotificacoes_Senhorio;
    }
}

package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;
import App.Logica.Data.Quarto;

import java.util.ArrayList;

public class ListaQuartosPessoal extends AppStateAdapter{
    public ListaQuartosPessoal(AppData dados) {
        super(dados);
    }

    public ArrayList<Quarto> getListaQuartosPessoal(){ return dados.getListaQuartosPessoal(); }

    public void removeQuartoPessoal(int id, boolean flag) {
        dados.removeQuartoPessoal(id);
        dados.removeQuartoPendente(id);

        if(flag) {
            dados.removeQuartoPublicado(id);
            dados.removeQuartoFavorito(id);
        }
    }

    public void adcionaQuartoPublico(int id) {
        dados.guardaListaPessoal("ListaQuartosPessoal.txt");
        dados.adicionaQuartoPublicado(dados.getQuartoFromListaQuartosPessoal(id));
    }

    public IAppState geraVistaMensagensSenhorio(){
        return new MensagensNotificacoes_Senhorio(dados);
    }

    public IAppState geraVistaCriarAnuncio(){ return new CriarAnuncio(dados);}

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    public IAppState geraVistaEditarAnuncio(){
        return new EditarAnuncio(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Lista_Quartos_Pessoal;
    }
}

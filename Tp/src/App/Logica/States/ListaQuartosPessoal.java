package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class ListaQuartosPessoal extends AppStateAdapter{
    public ListaQuartosPessoal(AppData dados) {
        super(dados);
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

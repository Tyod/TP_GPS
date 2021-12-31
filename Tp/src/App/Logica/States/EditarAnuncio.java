package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class EditarAnuncio  extends AppStateAdapter{

    public EditarAnuncio(AppData dados) {
        super(dados);
    }

    public IAppState geraVistaListaQuartosPessoal(){ return new ListaQuartosPessoal(dados); }

    public IAppState geraVistaMensagensSenhorio(){ return new MensagensNotificacoes_Senhorio(dados); }

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Editar_anuncio;
    }
}

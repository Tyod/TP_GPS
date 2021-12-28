package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class EscolheVista extends AppStateAdapter{

    public EscolheVista(AppData dados) {
        super(dados);
    }

    public IAppState geraVistaListaQuartosPublicados(){
        return new ListaQuartosPublicados(dados);
    }

    public IAppState geraVistaListaQuartosPendentes(){
        return new ListaQuartosPendentes(dados);
    }

    public IAppState geraVistaListaQuartosPessoal(){
        return new ListaQuartosPessoal(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Escolhe_Vista;
    }
}

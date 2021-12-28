package App.Logica.States;

import App.Logica.AppSituation;

public interface IAppState {
    AppSituation getSituacaoAtual();
    IAppState geraVistaListaQuartosPublicados();
    IAppState geraVistaListaQuartosPendentes();
    IAppState geraVistaListaQuartosPessoal();
}

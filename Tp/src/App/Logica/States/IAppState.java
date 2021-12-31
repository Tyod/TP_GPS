package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.Quarto;

import java.util.ArrayList;

public interface IAppState {
    AppSituation getSituacaoAtual();
    IAppState geraVistaListaQuartosPublicados();
    IAppState geraVistaListaQuartosPendentes();
    IAppState geraVistaListaQuartosPessoal();
    IAppState geraVistaEscolheVista();
    IAppState geraVistaMensagensEstudante();
    IAppState geraVistaMensagensSenhorio();
    IAppState geraVistaFavoritos();

    IAppState geraVistaCriarAnuncio();
    IAppState geraVistaEditarAnuncio();

    ArrayList<Quarto> getListaQuartosPublicados();
}

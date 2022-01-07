package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.DisponibilidadeQuarto;
import App.Logica.Data.Mensagem;
import App.Logica.Data.Quarto;
import App.Logica.Data.TipoUtilzadores;

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
    ArrayList<Quarto> getListaQuartosPessoal();
    ArrayList<Quarto> getListaQuartosPendentes();
    ArrayList<Quarto> getListaQuartosFavoritos();

    ArrayList<Mensagem> getListaMensagens();
    void adicionaMensagem(TipoUtilzadores utilizador, String msg);

    void adicionaQuartoPessoal(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, String imagem);
    void adcionaQuartoPublico(int id);
    void removeQuartoPessoal(int id, boolean flag);
    boolean removeQuartoPendente(int id);
}

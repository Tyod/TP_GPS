package App.Logica;

import App.Logica.Data.*;
import App.Logica.States.EscolheVista;
import App.Logica.States.IAppState;

import java.util.ArrayList;
import java.util.List;

public class App {
    private IAppState estado;
    private AppData dados;
    private static final String filePublicados = "ListaQuartosPublicados.txt";
    private static final String filePendentes = "ListaQuartosPendentes.txt";
    private static final String filePessoal = "ListaQuartosPessoal.txt";
    private static final String fileFavoritos = "ListaFavoritos.txt";
    private List<PropsID> eventos = new ArrayList<>();

    public App(){
        dados = new AppData();
        estado = new EscolheVista(dados);
    }

    private boolean defineEstado(IAppState prox){
        IAppState anterior = estado;
        estado = prox;
        if(anterior!=prox){
            eventos.add(new PropsID("prop_estado"));
            return true;
        }
        return false;
    }

    public AppSituation getSituacaoAtual() {
        return estado.getSituacaoAtual();
    }


    public List<PropsID> geraVistaListaQuartosPublicados() {
        eventos.clear();
        defineEstado(estado.geraVistaListaQuartosPublicados());
        return eventos;
    }

    public List<PropsID> geraVistaListaQuartosPendentes() {
        eventos.clear();
        defineEstado(estado.geraVistaListaQuartosPendentes());
        return eventos;
    }

    public List<PropsID> geraVistaListaQuartosPessoal() {
        eventos.clear();
        defineEstado(estado.geraVistaListaQuartosPessoal());
        return eventos;
    }

    public List<PropsID> geraVistaEscolheVista() {
        eventos.clear();
        defineEstado(estado.geraVistaEscolheVista());
        return eventos;
    }

    public List<PropsID> geraVistaFavoritos() {
        eventos.clear();
        defineEstado(estado.geraVistaFavoritos());
        return eventos;
    }

    public List<PropsID> geraVistaMensagensEstudante() {
        eventos.clear();
        defineEstado(estado.geraVistaMensagensEstudante());
        return eventos;
    }

    public List<PropsID> geraVistaMensagensSenhorio() {
        eventos.clear();
        defineEstado(estado.geraVistaMensagensSenhorio());
        return eventos;
    }

    public List<PropsID> geraVistaEditarAnuncio() {
        eventos.clear();
        defineEstado(estado.geraVistaEditarAnuncio());
        return eventos;
    }

    public List<PropsID> geraVistaCriarAnuncio() {
        eventos.clear();
        defineEstado(estado.geraVistaCriarAnuncio());
        return eventos;
    }


    //GET Lists
    public ArrayList<Quarto> getListaQuartosPublicados() {
        return estado.getListaQuartosPublicados();
    }
    public ArrayList<Quarto> getListaQuartosPessoal() { return estado.getListaQuartosPessoal(); }
    public ArrayList<Quarto> getListaQuartosFavoritos() { return estado.getListaQuartosFavoritos(); }
    public ArrayList<Quarto> getListaQuartosPendentes() { return estado.getListaQuartosPendentes();}
    public ArrayList<Mensagem> getListaMensagens() { return estado.getListaMensagens(); }

    //Adicona Mensagens
    public void adicionaMensagem(TipoUtilzadores estudante, String text) { estado.adicionaMensagem(estudante, text); }

    public void adicionaQuartoPessoal(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, String imagem) {
        estado.adicionaQuartoPessoal(disponibilidade, preco, localizacao, servicos, despesas, contacto, imagem);
    }
    public void removeQuartoPessoal(int id, Boolean flag) {
        estado.removeQuartoPessoal(id, flag);
    }

    public void removeQuartoPendente(int id) {
        estado.removeQuartoPendente(id);
    }

    public void adcionaQuartoPublico(int id) {
        estado.adcionaQuartoPublico(id);
    }
}

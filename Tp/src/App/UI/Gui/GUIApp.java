package App.UI.Gui;

import App.Logica.AppObs;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class GUIApp {
    private GUICriarAnuncio vistaCriarAnuncio;
    private GUIEditarAnuncio vistaEditarAnuncio;
    private GUIEscolheVista vistaEscolheVista;
    private GUIListaFavoritos vistaListaFavoritos;
    private GUIListaQuartosPendentes vistaListaQuartosPendentes;
    private GUIListaQuartosPessoal vistaListaQuartosPessoal;
    private GUIListaQuartosPublicados vistaListaQuartosPublicados;
    private GUIMensagensNotificacoes_Estudante vistaMensagensNotificacoes_Estudante;
    private GUIMensagensNotificacoes_Senhorio vistaMensagensNotificacoes_Senhorio;

    private StackPane rootPane;

    private AppObs appObs;


    public GUIApp(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarListeners();
    }


    private void criarComponentes() {
        rootPane = new StackPane();
        vistaCriarAnuncio = new GUICriarAnuncio(appObs);
        vistaEditarAnuncio = new GUIEditarAnuncio(appObs);
        vistaEscolheVista = new GUIEscolheVista(appObs);
        vistaListaFavoritos = new GUIListaFavoritos(appObs);
        vistaListaQuartosPendentes = new GUIListaQuartosPendentes(appObs);
        vistaListaQuartosPessoal = new GUIListaQuartosPessoal(appObs);
        vistaListaQuartosPublicados = new GUIListaQuartosPublicados(appObs);
        vistaMensagensNotificacoes_Estudante = new GUIMensagensNotificacoes_Estudante(appObs);
        vistaMensagensNotificacoes_Senhorio = new GUIMensagensNotificacoes_Senhorio(appObs);
    }

    private void disporVista() {
        vistaEscolheVista.setVisible(true);
        vistaCriarAnuncio.setVisible(false);
        vistaEditarAnuncio.setVisible(false);
        vistaListaFavoritos.setVisible(false);
        vistaListaQuartosPendentes.setVisible(false);
        vistaListaQuartosPessoal.setVisible(false);
        vistaListaQuartosPublicados.setVisible(false);
        vistaMensagensNotificacoes_Estudante.setVisible(false);
        vistaMensagensNotificacoes_Senhorio.setVisible(false);
        rootPane.getChildren().addAll(vistaEscolheVista, vistaCriarAnuncio, vistaEditarAnuncio, vistaListaFavoritos, vistaListaQuartosPendentes, vistaListaQuartosPessoal, vistaListaQuartosPublicados, vistaMensagensNotificacoes_Estudante, vistaMensagensNotificacoes_Senhorio);
    }

    private void registarListeners() {
    }


    public Parent obtemRootPane(){
        return rootPane;
    }
}

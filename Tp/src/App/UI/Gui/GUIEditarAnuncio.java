package App.UI.Gui;

import App.Logica.AppObs;
import App.Logica.AppSituation;
import App.Logica.PropsID;
import javafx.scene.layout.BorderPane;

public class GUIEditarAnuncio extends BorderPane {
    AppObs appObs;

    public GUIEditarAnuncio(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> { this.setVisible(appObs.getSituacao() == AppSituation.Editar_anuncio);});
    }

    private void criarComponentes() {
    }

    private void disporVista() {
    }

    private void registarLiscteners() {
    }
}

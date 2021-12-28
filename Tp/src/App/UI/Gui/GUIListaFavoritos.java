package App.UI.Gui;

import App.Logica.AppObs;
import App.Logica.AppSituation;
import App.Logica.PropsID;
import javafx.scene.layout.BorderPane;

public class GUIListaFavoritos extends BorderPane {
    AppObs appObs;

    public GUIListaFavoritos(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> { this.setVisible(appObs.getSituacao() == AppSituation.Lista_Favoritos);});
    }

    private void criarComponentes() {
    }

    private void disporVista() {
    }

    private void registarLiscteners() {
    }
}

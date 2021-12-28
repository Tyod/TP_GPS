package App.UI.Gui;

import App.Logica.AppObs;
import App.Logica.AppSituation;
import App.Logica.PropsID;
import javafx.scene.layout.BorderPane;

public class GUICriarAnuncio extends BorderPane {

    private AppObs appObs;

    public GUICriarAnuncio(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> { this.setVisible(appObs.getSituacao() == AppSituation.Criar_Anuncio);});
    }

    private void criarComponentes() {
    }

    private void disporVista() {
    }

    private void registarLiscteners() {
    }
}

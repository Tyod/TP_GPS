package App.UI.Gui;

import App.Logica.AppObs;
import App.Logica.AppSituation;
import App.Logica.PropsID;
import App.UI.Resources.CSSManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GUIListaQuartosPublicados extends BorderPane {
    AppObs appObs;

    Label lbTitulo;
    VBox painel;

    public GUIListaQuartosPublicados(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        CSSManager.setCSS(this, "mystyles.css");
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> { this.setVisible(appObs.getSituacao() == AppSituation.Lista_Quartos_Publicados);});
    }

    private void criarComponentes() {
        lbTitulo = new Label("Estudante");
        painel = new VBox();
        lbTitulo.setId("labelTitulo");
    }

    private void disporVista() {
        painel.setAlignment(Pos.CENTER);
        painel.getChildren().addAll(lbTitulo);
        setCenter(painel);
    }

    private void registarLiscteners() {
    }
}

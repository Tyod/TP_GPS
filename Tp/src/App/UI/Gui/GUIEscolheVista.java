package App.UI.Gui;

import App.Logica.AppObs;
import App.Logica.AppSituation;
import App.Logica.PropsID;
import App.Logica.States.IAppState;
import App.UI.Resources.CSSManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class GUIEscolheVista extends BorderPane {

    private AppObs appObs;

    private Label lbTitulo;
    private Button btnEstudante;
    private Button btnSenhorio;
    private Button btnCamara;
    private VBox painel;


    public GUIEscolheVista(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        CSSManager.setCSS(this, "mystyles.css");
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> { this.setVisible(appObs.getSituacao() == AppSituation.Escolhe_Vista);});
    }

    private void criarComponentes() {
        lbTitulo = new Label("Room4You");
        btnEstudante = new Button("Estudante");
        btnSenhorio = new Button("Senhorio");
        btnCamara = new Button("Câmara");
        painel = new VBox();
        this.setId("PaginaInicial");
        lbTitulo.setId("labelTitulo");
        btnEstudante.setId("btnEstudante");
        btnSenhorio.setId("btnSenhorio");
        btnCamara.setId("btnCamara");
    }

    private void disporVista() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(btnEstudante, 0 ,0 );
        grid.add(btnSenhorio, 1,0);
        grid.add(btnCamara, 2 ,0);
        grid.setHgap(10);
        lbTitulo.setPadding(new Insets(0, 0, 30, 0));

        painel.setAlignment(Pos.CENTER);
        painel.getChildren().addAll(lbTitulo, grid);
        setCenter(painel);
    }

    private void registarLiscteners() {
        btnEstudante.setOnAction((e)->{
            appObs.geraVistaListaQuartosPublicados();
        });

        btnSenhorio.setOnAction((e)->{
            appObs.geraVistaListaQuartosPessoal();
        });

        btnCamara.setOnAction((e)->{
            appObs.geraVistaListaQuartosPendentes();
        });


    }
}

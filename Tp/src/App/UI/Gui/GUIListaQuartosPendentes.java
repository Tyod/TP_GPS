package App.UI.Gui;

import App.Logica.AppObs;
import App.Logica.AppSituation;
import App.Logica.Data.DisponibilidadeQuarto;
import App.Logica.Data.Quarto;
import App.Logica.PropsID;
import App.UI.Resources.CSSManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class GUIListaQuartosPendentes extends BorderPane {
    AppObs appObs;

    //Items Cabeçalho
    Label lbNomeApp;
    Button btnSair;
    HBox cabecalho;
    ScrollPane listaAnuncios;

    //Items Subcabeçalho
    Label lbTitulo;
    Button btnPT;
    Button btnENG;
    HBox subCabecalho;

    //Items de finalização de pagina
    Line linha;
    Label lbMsgFimDePagina;
    HBox contentorLinha;

    //Painel Principal
    VBox painel;


    public GUIListaQuartosPendentes(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        CSSManager.setCSS(this, "mystyles.css");
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> { this.setVisible(appObs.getSituacao() == AppSituation.Lista_Quartos_Pendentes);

                    if(appObs.getSituacao() == AppSituation.Lista_Quartos_Pendentes){
                        painel.getChildren().clear();
                        repoemVista();
                    }

                });
    }

    private void repoemVista() {
        painel.getChildren().addAll(cabecalho,subCabecalho);

        //Anuncios
        ArrayList<Quarto> listTemp = appObs.getListaQuartosPendentes();
        for (Quarto temp : appObs.getListaQuartosPendentes()) {
            GridPane anuncio = new GridPane();
            anuncio.setMaxWidth(950);
            ImageView imageView = new ImageView(temp.getImagem());
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            anuncio.add(new Label("Estado: " + temp.getDisponiblidade()),1,0);
            anuncio.add(new Label("Preço: " + temp.getPreco()),1,1);
            anuncio.add(new Label("Serviços: " + temp.getServicos()), 1,2);
            anuncio.add(new Label("Localização: " + temp.getLocalizacao()),1 ,3);
            anuncio.add(new Label("Despesas: " + temp.getDespesas()),1, 4);
            anuncio.add(new Label("Contactos: " + temp.getContacto()),1,5);
            anuncio.setVgap(8);
            anuncio.setPadding(new Insets(0,250,0,20));
            Button btnAprovar = new Button("✔");
            btnAprovar.setPadding(new Insets(7.5,7.8,7.5,7.8));
            Button btnReprovar = new Button("✖");
            btnReprovar.setPadding(new Insets(7.5,7.5,7.5,7.5));
            VBox painelBtn = new VBox();
            painelBtn.getChildren().addAll(btnAprovar, btnReprovar);
            HBox realAnuncio = new HBox();
            realAnuncio.setStyle("-fx-border-width: 3px;  -fx-border-radius: 18 18 18 18; -fx-border-style: solid;");
            realAnuncio.getChildren().addAll(imageView, anuncio, painelBtn);
            realAnuncio.setMaxWidth(800);
            realAnuncio.setPadding(new Insets(20, 20, 20, 20));
            painel.getChildren().add(realAnuncio);
            btnAprovar.setOnAction((e)->{
                temp.setAprovacao(true);
                appObs.removeQuartoPendente(temp.getId());
                painel.getChildren().remove(realAnuncio);
            });
            btnReprovar.setOnAction((e)->{
                temp.setAprovacao(false);
                appObs.removeQuartoPendente(temp.getId());
                painel.getChildren().remove(realAnuncio);
            });
        }

        painel.getChildren().add(contentorLinha);
        painel.getChildren().add(lbMsgFimDePagina);

        painel.setSpacing(20);
        painel.setAlignment(Pos.CENTER);
        listaAnuncios.setContent(painel);
        setTop(listaAnuncios);
    }

    private void criarComponentes() {
        lbNomeApp = new Label("Room4You");
        lbTitulo = new Label("Lista de Quartos Pendentes");
        btnPT = new Button("PT");
        btnENG = new Button("ENG");
        btnSair = new Button("Sair");
        painel = new VBox();
        cabecalho = new HBox();
        subCabecalho = new HBox();
        listaAnuncios = new ScrollPane();

        linha = new Line();
        lbMsgFimDePagina = new Label("Não há mais anuncios pendentes a mostrar...");
        contentorLinha = new HBox();

        lbTitulo.setId("labelSubTitulo");
        lbNomeApp.setId("labelNomeApp");
        cabecalho.setId("cabecalhoCamara");
    }

    private void disporVista() {
        btnPT.setStyle("-fx-background-color: #FF0000;");
        btnENG.setStyle("-fx-background-color: #FFFFFF;");


        listaAnuncios.setPannable(true);
        listaAnuncios.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        listaAnuncios.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        listaAnuncios.setMaxHeight(650);

        //Header
        GridPane header = new GridPane();
        header.add(btnSair, 8,0);
        header.setHgap(5);
        lbNomeApp.setPadding(new Insets(0, 812, 0 ,3));
        cabecalho.getChildren().addAll(lbNomeApp, header);
        cabecalho.setPadding(new Insets(15, 0,15,0));
        header.setAlignment(Pos.TOP_CENTER);

        //SubHeader
        lbTitulo.setPadding(new Insets(0,490, 0,3));
        GridPane subheader = new GridPane();
        subheader.add(btnPT, 0,0);
        subheader.add(btnENG, 1,0);
        subheader.setPadding(new Insets(2,0,0,0));
        subCabecalho.setPadding(new Insets(10, 0,20,0));
        subCabecalho.getChildren().addAll(lbTitulo, subheader);

        painel.getChildren().addAll(cabecalho,subCabecalho);




        //Items de finalização de pagina
        linha.setStartX(50);
        linha.setEndX(950);
        linha.setStrokeWidth(6);
        contentorLinha.getChildren().add(linha);
        contentorLinha.setAlignment(Pos.CENTER);
        painel.getChildren().add(contentorLinha);
        lbMsgFimDePagina.setAlignment(Pos.CENTER);
        lbMsgFimDePagina.setStyle("-fx-font-weight:bold;");
        lbMsgFimDePagina.setPadding(new Insets(0,5,20,5));
        painel.getChildren().add(lbMsgFimDePagina);


        //ColocaPainelPrincipal
        painel.setSpacing(20);
        painel.setAlignment(Pos.CENTER);
        listaAnuncios.setContent(painel);
        setTop(listaAnuncios);
    }

    private void registarLiscteners() {
        btnSair.setOnAction((e)->{
            appObs.geraVistaEscolheVista();
        });

        btnPT.setOnAction((e)->{
            btnPT.setStyle("-fx-background-color: #FF0000;");
            btnENG.setStyle("-fx-background-color: #FFFFFF;");

            lbNomeApp.setPadding(new Insets(0, 813, 0 ,3));
            btnSair.setText("Sair");

            lbTitulo.setText("Lista de Quartos Pendentes");
            lbTitulo.setPadding(new Insets(0,480, 0,3));

            lbMsgFimDePagina.setText("Não há mais anuncios pendentes a mostrar...");
        });

        btnENG.setOnAction((e)->{
            btnENG.setStyle("-fx-background-color: #0004F5");
            btnPT.setStyle("-fx-background-color: #FFFFFF;");

            lbNomeApp.setPadding(new Insets(0, 813, 0 ,3));
            btnSair.setText("Exit");

            lbTitulo.setText("List of Pending Rooms");
            lbTitulo.setPadding(new Insets(0,560, 0,3));

            lbMsgFimDePagina.setText("There are no more pending ads to show...");
        });
    }
}

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

public class GUIListaQuartosPessoal extends BorderPane {
    AppObs appObs;

    //Items Cabeçalho
    Label lbNomeApp;
    Button btnPesquisarQuartos;
    Button btnMensagensNotificacoes;
    Button btnSair;
    HBox cabecalho;
    ScrollPane listaAnuncios;

    //Items Subcabeçalho
    Label lbTitulo;
    Button btnPT;
    Button btnENG;
    HBox subCabecalho;

    //Items Adicionar
    Line linha;
    Button btnCriarAnuncio;
    HBox painelAdcionarAnuncio;

    //Painel Principal
    VBox painel;

    public GUIListaQuartosPessoal(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        CSSManager.setCSS(this, "mystyles.css");
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> {
                    this.setVisible(appObs.getSituacao() == AppSituation.Lista_Quartos_Pessoal);

                    if(appObs.getSituacao() == AppSituation.Lista_Quartos_Pessoal){
                        painel.getChildren().clear();
                        repoemVista();
                    }
                });
    }

    private void repoemVista() {
        painel.getChildren().addAll(cabecalho,subCabecalho);
        //Anuncios
        ArrayList<Quarto> listTemp = appObs.getListaQuartosPessoal();
        for (Quarto temp : appObs.getListaQuartosPessoal()) {
            GridPane anuncio = new GridPane();
            anuncio.setMaxWidth(950);
            ImageView imageView = new ImageView(temp.getImagem());
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            anuncio.add(new Label("Estado: " + temp.getDisponiblidade()),1,0);
            anuncio.add(new Label("Preço: " + temp.getPreco()),1,1);
            anuncio.add(new Label("Serviços: " + temp.getServicos()), 1,2);
            anuncio.add(new Label("Localização: " + temp.getLocalizacao()),1 ,3);
            anuncio.add(new Label("Despesas: " + temp.getDespesas()),1, 4);
            anuncio.add(new Label("Contactos: " + temp.getContacto()),1,5);
            anuncio.add(new Label("Aprovação: " + temp.getAprovacao()), 1,6);
            anuncio.add(new Label("Publicado: " + temp.getPublicado()), 1,7);
            anuncio.setVgap(8);
            anuncio.setPadding(new Insets(0,250,0,20));
            Button btnEditar = new Button("✏");
            btnEditar.setPadding(new Insets(4.8,9.5,4.8,9.5));
            btnEditar.setOnAction((e)->{
                System.out.println(temp.getId());
                appObs.setTempQuarto(temp.getId());
                appObs.geraVistaEditarAnuncio();
            });
            Button btnApagar = new Button("\uD83D\uDDD1");
            btnApagar.setPadding(new Insets(4.8,11,4.8,11));
            Button btnPublicar = new Button("\uD83D\uDCE4");
            btnPublicar.setPadding(new Insets(4.8,11,4.8,11));
            VBox painelBtn = new VBox();
            painelBtn.getChildren().addAll(btnEditar, btnApagar,btnPublicar);
            HBox realAnuncio = new HBox();
            realAnuncio.setStyle("-fx-border-width: 3px;  -fx-border-radius: 18 18 18 18; -fx-border-style: solid;");
            realAnuncio.getChildren().addAll(imageView, anuncio, painelBtn);
            realAnuncio.setMaxWidth(800);
            realAnuncio.setPadding(new Insets(20, 20, 20, 20));
            painel.getChildren().add(realAnuncio);
            btnApagar.setOnAction((e)->{
                painel.getChildren().remove(realAnuncio);
                appObs.removeQuartoPessoal(temp.getId(), temp.getPublicado());
            });
            btnPublicar.setOnAction((e)->{
                if(temp.getAprovacao() && !temp.getPublicado()) {
                    appObs.adcionaQuartoPublico(temp.getId());
                    temp.setPublicado(true);
                }
            });
        }


        painel.getChildren().add(painelAdcionarAnuncio);

        listaAnuncios.setContent(painel);
        setTop(listaAnuncios);
    }

    private void criarComponentes() {
        lbNomeApp = new Label("Room4You");
        lbTitulo = new Label("Lista de Quartos Publicados");
        btnPesquisarQuartos = new Button("Consultar Quartos");
        btnMensagensNotificacoes = new Button("Mensagens");
        btnPT = new Button("PT");
        btnENG = new Button("ENG");
        btnSair = new Button("Sair");
        painel = new VBox();
        cabecalho = new HBox();
        subCabecalho = new HBox();
        listaAnuncios = new ScrollPane();

        linha = new Line();
        btnCriarAnuncio = new Button("➕");
        painelAdcionarAnuncio = new HBox();

        lbTitulo.setId("labelSubTitulo");
        lbNomeApp.setId("labelNomeApp");
        cabecalho.setId("cabecalhoSenhorio");
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
        header.add(btnPesquisarQuartos, 2,0);
        header.add(btnMensagensNotificacoes, 3,0);
        header.add(btnSair, 8,0);
        header.setHgap(5);
        lbNomeApp.setPadding(new Insets(0, 620, 0 ,3));
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



        //Items Adicionar
        linha.setStartX(50);
        linha.setEndX(950);
        linha.setStrokeWidth(6);
        painelAdcionarAnuncio.getChildren().addAll(linha, btnCriarAnuncio);
        painelAdcionarAnuncio.setAlignment(Pos.CENTER);
        painelAdcionarAnuncio.setSpacing(10);
        painelAdcionarAnuncio.setPadding(new Insets(10,10,20,10));
        painel.getChildren().add(painelAdcionarAnuncio);

        //ColocaPainelPrincipal
        painel.setSpacing(20);
        painel.setAlignment(Pos.CENTER);
        listaAnuncios.setContent(painel);
        setTop(listaAnuncios);
    }

    private void registarLiscteners() {
        btnPT.setOnAction((e)->{
            btnPT.setStyle("-fx-background-color: #FF0000;");
            btnENG.setStyle("-fx-background-color: #FFFFFF;");

            lbNomeApp.setPadding(new Insets(0, 624, 0 ,3));
            btnMensagensNotificacoes.setText("Mensagens");
            btnPesquisarQuartos.setText("Consultar Quartos");
            btnSair.setText("Sair");

            lbTitulo.setText("Lista de Quartos Publicados");
            lbTitulo.setPadding(new Insets(0,480, 0,3));
        });

        btnENG.setOnAction((e)->{
            btnENG.setStyle("-fx-background-color: #0004F5");
            btnPT.setStyle("-fx-background-color: #FFFFFF;");

            lbNomeApp.setPadding(new Insets(0, 650, 0 ,3));
            btnMensagensNotificacoes.setText("Messages");
            btnPesquisarQuartos.setText("Consult rooms");
            btnSair.setText("Exit");

            lbTitulo.setText("List of Published Rooms");
            lbTitulo.setPadding(new Insets(0,540, 0,3));
        });

        btnSair.setOnAction((e)->{
            appObs.geraVistaEscolheVista();
        });

        btnMensagensNotificacoes.setOnAction((e)->{
            appObs.geraVistaMensagensSenhorio();
        });

        btnCriarAnuncio.setOnAction((e)->{
            appObs.geraVistaCriarAnuncio();
        });
    }
}

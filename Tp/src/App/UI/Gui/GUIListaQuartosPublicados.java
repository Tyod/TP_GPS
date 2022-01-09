package App.UI.Gui;

import App.Logica.AppObs;
import App.Logica.AppSituation;
import App.Logica.Data.DisponibilidadeQuarto;
import App.Logica.Data.Quarto;
import App.Logica.PropsID;
import App.UI.Resources.CSSManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;


import java.util.ArrayList;


public class GUIListaQuartosPublicados extends BorderPane {
    AppObs appObs;


    //Items Cabeçalho
    Label lbNomeApp;
    Button btnPesquisarQuartos;
    Button btnListaFavoritos;
    Button btnMensagensNotificacoes;
    Button btnSair;
    HBox cabecalho;
    GridPane header;
    GridPane filtros;
    ScrollPane listaAnuncios;

    //Items Subcabeçalho
    Label lbTitulo;
    Button btnPT;
    Button btnENG;
    HBox subCabecalho;
    GridPane subheader;

    //Items Filtros
    Label lbTituloFiltro;
    Label lbEstado;
    ComboBox opcoesDisponibilidade;
    Label lbPreco;
    TextField tfPreco;
    Label lbDespesas;
    Button btnDespesas;
    Label lbServicos;
    CheckBox chWifi;
    CheckBox chTvCabo;
    CheckBox chLimpeza;
    Label lbLocalizacao;
    TextField tfLocalizacao;
    Button btnFiltrar;

    //Items de finalização de pagina
    Line linha;
    Label lbMsgFimDePagina;
    HBox contentorLinha;

    //Painel Principal
    VBox painel;

    public GUIListaQuartosPublicados(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        CSSManager.setCSS(this, "mystyles.css");
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> { this.setVisible(appObs.getSituacao() == AppSituation.Lista_Quartos_Publicados);

                    if(appObs.getSituacao() == AppSituation.Lista_Quartos_Publicados) {
                        painel.getChildren().clear();
                        repoemVista();
                    }

                });
    }

    private void repoemVista() {
        painel.getChildren().addAll(cabecalho,subCabecalho,filtros);

        //Anuncios
        ArrayList<Quarto> listTemp = appObs.getListaQuartosPublicados();
        for (Quarto temp : appObs.getListaQuartosPublicados()) {
            GridPane anuncio = new GridPane();
            anuncio.setMaxWidth(950);
            ImageView imageView = new ImageView(temp.getImagem());
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            anuncio.add(new Label("Estado: " + temp.getDisponiblidade()),1,0);
            anuncio.add(new Label("Preço: " + temp.getPreco()),1,1);
            anuncio.add(new Label("Serviços: " + temp.getServicos()), 1,2);
            anuncio.add(new Label("Localização: " + temp.getLocalizacao()),1 ,3);
            anuncio.add(new Label("Notas: " + temp.getDespesas()),1, 4);
            anuncio.add(new Label("Contactos: " + temp.getContacto()),1,5);
            anuncio.setVgap(8);
            anuncio.setPadding(new Insets(0,250,0,20));
            Button btnFav = new Button("⭐");
            btnFav.setOnAction((e)->{
                if(!temp.getFavorito())
                appObs.adicionaQuartoFavorito(temp.getId());
                temp.setFavorito(true);
                appObs.geraVistaFavoritos();
            });
            Button btnMsg = new Button("✉");
            btnMsg.setOnAction((e)->{
                appObs.geraVistaMensagensEstudante();
            });
            btnFav.setPadding(new Insets(7.5,7.8,7.5,7.8));
            btnMsg.setPadding(new Insets(7.5,7.5,7.5,7.5));
            VBox painelBtn = new VBox();
            painelBtn.getChildren().addAll(btnFav, btnMsg);
            HBox realAnuncio = new HBox();
            realAnuncio.setStyle("-fx-border-width: 3px;  -fx-border-radius: 18 18 18 18; -fx-border-style: solid;");
            realAnuncio.getChildren().addAll(imageView, anuncio, painelBtn);
            realAnuncio.setMaxWidth(800);
            realAnuncio.setPadding(new Insets(20, 20, 20, 20));
            painel.getChildren().add(realAnuncio);
        }

        painel.getChildren().addAll(contentorLinha, lbMsgFimDePagina);

        painel.setSpacing(20);
        painel.setAlignment(Pos.CENTER);
        listaAnuncios.setContent(painel);
        setTop(listaAnuncios);
    }

    private void criarComponentes() {
        lbNomeApp = new Label("Room4You");
        lbTitulo = new Label("Lista de Quartos Publicados");
        btnPesquisarQuartos = new Button("Pesquisar Quartos");
        btnListaFavoritos = new Button("Lista de Favoritos");
        btnMensagensNotificacoes = new Button("Mensagens");
        btnPT = new Button("PT");
        btnENG = new Button("ENG");
        btnSair = new Button("Sair");
        painel = new VBox();
        cabecalho = new HBox();
        subCabecalho = new HBox();
        header = new GridPane();
        subheader = new GridPane();
        filtros = new GridPane();
        listaAnuncios = new ScrollPane();

        //Cena dos filtros
        lbTituloFiltro = new Label("Filtros de pesquisa:");
        lbEstado = new Label("Estado: ");
        opcoesDisponibilidade = new ComboBox();
        opcoesDisponibilidade.getItems().add("Disponivél");
        opcoesDisponibilidade.getItems().add("Indisponivél");
        opcoesDisponibilidade.getItems().add("Brevemente Disponivel");
        lbPreco = new Label("Preço: ");
        tfPreco = new TextField("Valor max");
        lbDespesas = new Label("Despesas: ");
        btnDespesas = new Button("Não Incluídas");
        lbServicos = new Label("Servicos: ");
        chWifi = new CheckBox("WI-FI");
        chTvCabo = new CheckBox("TvCabo");
        chLimpeza = new CheckBox("Limpeza");
        lbLocalizacao = new Label("Localização: ");
        tfLocalizacao = new TextField("Inserir localização...");
        btnFiltrar = new Button("Pesquisar \uD83D\uDD0D");


        linha = new Line();
        lbMsgFimDePagina = new Label("Não há mais anuncios publicados a mostrar...");
        contentorLinha = new HBox();

        lbTitulo.setId("labelSubTitulo");
        lbNomeApp.setId("labelNomeApp");
        cabecalho.setId("cabecalhoEstudante");
    }

    private void disporVista() {

        btnPT.setStyle("-fx-background-color: #FF0000;");
        btnENG.setStyle("-fx-background-color: #FFFFFF;");


        listaAnuncios.setPannable(true);
        listaAnuncios.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        listaAnuncios.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        listaAnuncios.setMaxHeight(650);

        //Header
        header.add(btnPesquisarQuartos, 2,0);
        header.add(btnListaFavoritos, 3, 0);
        header.add(btnMensagensNotificacoes, 4,0);
        header.add(btnSair, 8,0);
        header.setHgap(5);
        lbNomeApp.setPadding(new Insets(0, 515, 0 ,3));
        cabecalho.getChildren().addAll(lbNomeApp, header);
        cabecalho.setPadding(new Insets(15, 0,15,0));
        header.setAlignment(Pos.TOP_CENTER);

        //SubHeader
        lbTitulo.setPadding(new Insets(0,490, 0,3));
        subheader.add(btnPT, 0,0);
        subheader.add(btnENG, 1,0);
        subheader.setPadding(new Insets(2,0,0,0));
        subCabecalho.setPadding(new Insets(10, 0,20,0));
        subCabecalho.getChildren().addAll(lbTitulo, subheader);

        //Filtros
        filtros.setMaxWidth(850);
        lbTituloFiltro.setStyle("-fx-font-weight:bold; -fx-font: 14 arial;");
        lbTituloFiltro.setPadding(new Insets(0,0,25,0));
        filtros.add(lbTituloFiltro, 0,0);
        lbEstado.setStyle("-fx-font-weight:bold;");
        lbEstado.setPadding(new Insets(0, 0,0,40));
        filtros.add(lbEstado, 0,1);
        filtros.add(opcoesDisponibilidade, 1,1);
        lbPreco.setStyle("-fx-font-weight:bold;");
        lbPreco.setPadding(new Insets(0, 0,0,40));
        filtros.add(lbPreco, 0,2);
        filtros.add(tfPreco, 1, 2);
        lbDespesas.setStyle("-fx-font-weight:bold;");
        lbDespesas.setPadding(new Insets(0, 0,0,40));
        filtros.add(lbDespesas, 0, 3);
        filtros.add(btnDespesas, 1, 3);
        lbServicos.setStyle("-fx-font-weight:bold;");
        lbServicos.setPadding(new Insets(0, 0, 0, 120));
        filtros.add(lbServicos, 2, 0);
        chWifi.setPadding(new Insets(0,120,0,120));
        filtros.add(chWifi, 2, 1);
        chTvCabo.setPadding(new Insets(0,0,0,120));
        filtros.add(chTvCabo, 2, 2);
        chLimpeza.setPadding(new Insets(0,0,0,120));
        filtros.add(chLimpeza, 2, 3);
        lbLocalizacao.setStyle("-fx-font-weight:bold;");
        filtros.add(lbLocalizacao, 3,0);
        filtros.add(tfLocalizacao, 3,1);
        filtros.add(btnFiltrar,  4, 3);
        filtros.setStyle("-fx-border-width: 3px;  -fx-border-radius: 18 18 18 18; -fx-border-style: dashed;");
        filtros.setPadding(new Insets(10,10,10,10));
        filtros.setVgap(5);

        painel.getChildren().addAll(cabecalho,subCabecalho,filtros);

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

        btnListaFavoritos.setOnAction((e)->{
            appObs.geraVistaFavoritos();
        });

        btnMensagensNotificacoes.setOnAction((e)->{
            appObs.geraVistaMensagensEstudante();
        });

        btnPT.setOnAction((e)->{
            btnPT.setStyle("-fx-background-color: #FF0000;");
            btnENG.setStyle("-fx-background-color: #FFFFFF;");
        });

        btnENG.setOnAction((e)->{
            btnENG.setStyle("-fx-background-color: #0004F5");
            btnPT.setStyle("-fx-background-color: #FFFFFF;");
        });
    }
}

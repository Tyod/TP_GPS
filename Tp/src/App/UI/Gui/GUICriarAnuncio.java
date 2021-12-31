package App.UI.Gui;

import App.Logica.AppObs;
import App.Logica.AppSituation;
import App.Logica.Data.DisponibilidadeQuarto;
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

public class GUICriarAnuncio extends BorderPane {

    private AppObs appObs;

    //Items Cabeçalho
    Label lbNomeApp;
    Button btnPesquisarQuartos;
    Button btnMensagensNotificacoes;
    Button btnSair;
    HBox cabecalho;

    //Items Subcabeçalho
    Label lbTitulo;
    Button btnPT;
    Button btnENG;
    HBox subCabecalho;

    //Zona de imagem do anuncio
    ImageView imageView; Button btnNovaImagem;
    Label lbImagem;
    VBox painelImagem;

    //Zona de detalhes do anuncio
    Label lbDetalhes;
    Label lbEstado; ComboBox cbEstado;
    Label lbPreco; TextField tfPreco;
    Label lbServicos; CheckBox chWifi; CheckBox chTvcabo; CheckBox chLimpeza;
    Label lbLocalizacao; TextField tfLocalizacao;
    Label lbNotas; TextField tfNotas;
    Label lbContactos; TextField tfContactos;

    //Painel Botoes criacao anuncio
    Button btnCriar;
    Button btnCancelar;
    VBox painelButoes;

    //Zona de Criação do anuncio
    Line linha;
    HBox painelCriacao;

    //Painel Principal
    VBox painel;

    public GUICriarAnuncio(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        CSSManager.setCSS(this, "mystyles.css");
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> { this.setVisible(appObs.getSituacao() == AppSituation.Criar_Anuncio);});
    }

    private void criarComponentes() {
        lbNomeApp = new Label("Room4You");
        lbTitulo = new Label("Criar Anuncio");
        btnPesquisarQuartos = new Button("Consultar Quartos");
        btnMensagensNotificacoes = new Button("Mensagens");
        btnPT = new Button("PT");
        btnENG = new Button("ENG");
        btnSair = new Button("Sair");
        painel = new VBox();
        cabecalho = new HBox();
        subCabecalho = new HBox();

        imageView = new ImageView("C:\\Users\\AndreSilva\\OneDrive - ISEC\\Universidade\\5 - 3º Ano_1º Semestre\\GPS\\TP_GPS\\Tp\\src\\App\\UI\\Resources\\Images\\DefaultImage.png");
        lbImagem = new Label("Imagem:");
        btnNovaImagem = new Button("Selecionar Imagem");
        lbDetalhes = new Label("Detalhes:");
        lbEstado = new Label("Estado: ");
        cbEstado = new ComboBox();
        cbEstado.getItems().add(DisponibilidadeQuarto.disponivel);
        cbEstado.getItems().add(DisponibilidadeQuarto.indisponivel);
        cbEstado.getItems().add(DisponibilidadeQuarto.brevemente_disponivel);
        lbPreco = new Label("Preço: ");
        tfPreco = new TextField("Inserir preço...");
        lbServicos = new Label("Serviços: ");
        chWifi = new CheckBox("WI-FI");
        chTvcabo = new CheckBox("TVCabo");
        chLimpeza = new CheckBox("Limpeza");
        lbLocalizacao = new Label("Localização: ");
        tfLocalizacao = new TextField("Inserir localização...");
        lbNotas = new Label("Notas: ");
        tfNotas = new TextField("Inserir notas...");
        lbContactos = new Label("Contactos: ");
        tfContactos = new TextField("Inserir contacto...");
        painelImagem = new VBox();
        btnCriar = new Button("✔");
        btnCancelar = new Button("✖");
        painelButoes = new VBox();
        painelCriacao = new HBox();

        linha = new Line();

        lbTitulo.setId("labelSubTitulo");
        lbNomeApp.setId("labelNomeApp");
        cabecalho.setId("cabecalhoSenhorio");
    }

    private void disporVista() {
        btnPT.setStyle("-fx-background-color: #FF0000;");
        btnENG.setStyle("-fx-background-color: #FFFFFF;");

        ScrollPane listaAnuncios = new ScrollPane();
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
        lbNomeApp.setPadding(new Insets(0, 624, 0 ,3));
        cabecalho.getChildren().addAll(lbNomeApp, header);
        cabecalho.setPadding(new Insets(15, 0,15,0));
        header.setAlignment(Pos.TOP_CENTER);

        //SubHeader
        lbTitulo.setPadding(new Insets(0,705, 0,3));
        GridPane subheader = new GridPane();
        subheader.add(btnPT, 0,0);
        subheader.add(btnENG, 1,0);
        subheader.setPadding(new Insets(2,0,0,0));
        subCabecalho.setPadding(new Insets(10, 0,20,0));
        subCabecalho.getChildren().addAll(lbTitulo, subheader);

        //Zona de imagem do anuncio
        imageView.setFitHeight(315);
        imageView.setFitWidth(315);
        lbImagem.setStyle("-fx-font: 24 arial; -fx-font-weight:bold;");
        painelImagem.setSpacing(10);
        btnNovaImagem.setAlignment(Pos.CENTER);
        btnNovaImagem.setPadding(new Insets(5,107,5,107));
        painelImagem.getChildren().addAll(lbImagem, imageView, btnNovaImagem);



        //Zona de detalhes do anuncio
        GridPane zonaDeDetalhes = new GridPane();
        lbDetalhes.setStyle("-fx-font: 24 arial; -fx-font-weight:bold;");
        zonaDeDetalhes.add(lbDetalhes, 0, 0);
        lbEstado.setStyle("-fx-font-weight:bold;");
        zonaDeDetalhes.add(lbEstado, 0,1);
        zonaDeDetalhes.add(cbEstado, 1,1);
        lbPreco.setStyle("-fx-font-weight:bold;");
        zonaDeDetalhes.add(lbPreco, 0,2);
        zonaDeDetalhes.add(tfPreco,1,2);
        lbServicos.setStyle("-fx-font-weight:bold;");
        zonaDeDetalhes.add(lbServicos, 0,3);
        zonaDeDetalhes.add(chWifi, 1,3);
        zonaDeDetalhes.add(chTvcabo, 2,3);
        zonaDeDetalhes.add(chLimpeza, 3,3);
        lbLocalizacao.setStyle("-fx-font-weight:bold;");
        zonaDeDetalhes.add(lbLocalizacao, 0,4);
        zonaDeDetalhes.add(tfLocalizacao, 1,4);
        lbNotas.setStyle("-fx-font-weight:bold;");
        zonaDeDetalhes.add(lbNotas, 0,5);
        zonaDeDetalhes.add(tfNotas,1,5);
        lbContactos.setStyle("-fx-font-weight:bold;");
        zonaDeDetalhes.add(lbContactos, 0,6);
        zonaDeDetalhes.add(tfContactos,1,6);
        zonaDeDetalhes.setVgap(10);
        zonaDeDetalhes.setAlignment(Pos.CENTER);

        //Painel Botoes criacao anuncio
        btnCriar.setPadding(new Insets(7.5,7.5,7.5,7.5));
        btnCancelar.setPadding(new Insets(7.5,7.5,7.5,7.5));
        painelButoes.setSpacing(2);
        painelButoes.getChildren().addAll(btnCriar, btnCancelar);


        //Zona de Criação do anuncio
        linha.setStartY(600);
        linha.setEndY(300);
        linha.setStrokeWidth(6);
        painelCriacao.getChildren().addAll(painelImagem, linha, zonaDeDetalhes,painelButoes);
        painelCriacao.setSpacing(30);
        painelCriacao.setAlignment(Pos.CENTER);
        painelCriacao.setStyle("-fx-border-width: 3px;  -fx-border-radius: 18 18 18 18; -fx-border-style: solid;");
        painelCriacao.setMaxWidth(950);
        painelCriacao.setPadding(new Insets(40,40,40,40));

        painel.getChildren().addAll(cabecalho,subCabecalho,painelCriacao);

        //ColocaPainelPrincipal
        painel.setSpacing(20);
        painel.setAlignment(Pos.CENTER);
        listaAnuncios.setContent(painel);
        setTop(listaAnuncios);
    }

    private void registarLiscteners() {

        btnPesquisarQuartos.setOnAction((e)->{
            appObs.geraVistaListaQuartosPessoal();
        });

        btnMensagensNotificacoes.setOnAction((e)->{
            appObs.geraVistaMensagensSenhorio();
        });

        btnSair.setOnAction((e)->{
            appObs.geraVistaEscolheVista();
        });

        btnCriar.setOnAction((e)->{
            appObs.geraVistaListaQuartosPessoal();
        });

        btnCancelar.setOnAction((e)->{
            appObs.geraVistaListaQuartosPessoal();
        });

        btnPT.setOnAction((e)->{
            btnPT.setStyle("-fx-background-color: #FF0000;");
            btnENG.setStyle("-fx-background-color: #FFFFFF;");
        });

        btnENG.setOnAction((e)->{
            btnENG.setStyle("-fx-background-color: #0004F5");
            btnPT.setStyle("-fx-background-color: #FFFFFF;");
        });

        btnNovaImagem.setOnAction((e)->{
            //POR COMPLETAR
        });
    }
}

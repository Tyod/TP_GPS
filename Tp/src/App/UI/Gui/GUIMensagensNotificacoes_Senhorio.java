package App.UI.Gui;

import App.Logica.AppObs;
import App.Logica.AppSituation;
import App.Logica.Data.Mensagem;
import App.Logica.Data.TipoUtilzadores;
import App.Logica.PropsID;
import App.UI.Resources.CSSManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GUIMensagensNotificacoes_Senhorio extends BorderPane {
    AppObs appObs;

    int ContadorLinha = 0;

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

    //Zona de Mensagens
    GridPane zonaMsg;
    HBox painelDeMensagens;
    TextField tfInputMensagem;
    Button btnEnviaMsg;

    //Painel Principal
    VBox painel;

    public GUIMensagensNotificacoes_Senhorio(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        CSSManager.setCSS(this, "mystyles.css");
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> {
                    this.setVisible(appObs.getSituacao() == AppSituation.MensagensNotificacoes_Senhorio);

                    if(appObs.getSituacao() == AppSituation.MensagensNotificacoes_Senhorio){
                        painel.getChildren().clear();
                        zonaMsg.getChildren().clear();
                        repoemVista();
                    }
                });
    }

    private void repoemVista() {

        //Zona de Mensagens
        for(Mensagem temp : appObs.getListaMensagens()){
            System.out.println(temp.getMensagem());
            ImageView imageView;
            Label estudante = new Label();
            estudante.setStyle("-fx-font: 16 arial;");
            Label senhorio = new Label();
            estudante.setStyle("-fx-font: 16 arial;");

            if(temp.getAutor() == TipoUtilzadores.Estudante)
                imageView = new ImageView("App/UI/Resources/Images/AvatarEstudante.jpg");
            else
                imageView = new ImageView("App/UI/Resources/Images/AvatarSenhorio.jpg");

            imageView.setFitWidth(30);
            imageView.setFitHeight(30);

            if(temp.getAutor() == TipoUtilzadores.Estudante){
                estudante.setText(temp.getMensagem());
                zonaMsg.add(imageView, 0, ContadorLinha);
                zonaMsg.add(estudante, 1, ContadorLinha);
                ContadorLinha++;
            }else{
                senhorio.setText(temp.getMensagem());
                zonaMsg.add(imageView, 2,ContadorLinha);
                zonaMsg.add(senhorio,1,ContadorLinha);
                ContadorLinha++;
            }
        }

        painel.getChildren().addAll(cabecalho,subCabecalho,zonaMsg,painelDeMensagens);
    }

    private void criarComponentes() {
        lbNomeApp = new Label("Room4You");
        lbTitulo = new Label("Mensagens Senhorio");
        btnPesquisarQuartos = new Button("Consultar Quartos");
        btnMensagensNotificacoes = new Button("Mensagens");
        btnPT = new Button("PT");
        btnENG = new Button("ENG");
        btnSair = new Button("Sair");
        painel = new VBox();
        cabecalho = new HBox();
        subCabecalho = new HBox();
        listaAnuncios = new ScrollPane();

        zonaMsg = new GridPane();
        painelDeMensagens = new HBox();
        tfInputMensagem = new TextField("Escreva aqui...");
        btnEnviaMsg = new Button("✉");

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
        lbTitulo.setPadding(new Insets(0,595, 0,3));
        GridPane subheader = new GridPane();
        subheader.add(btnPT, 0,0);
        subheader.add(btnENG, 1,0);
        subheader.setPadding(new Insets(2,0,0,0));
        subCabecalho.setPadding(new Insets(10, 0,20,0));
        subCabecalho.getChildren().addAll(lbTitulo, subheader);

        zonaMsg.setStyle("-fx-border-width: 3px;  -fx-border-radius: 18 18 18 18; -fx-border-style: solid;");
        zonaMsg.setAlignment(Pos.CENTER);
        zonaMsg.setVgap(5);
        zonaMsg.setMaxWidth(900);

        //Painel de Escrita
        tfInputMensagem.setMinWidth(820);
        painelDeMensagens.setSpacing(10);
        painelDeMensagens.getChildren().addAll(tfInputMensagem, btnEnviaMsg);
        painelDeMensagens.setAlignment(Pos.CENTER);
        painel.setPadding(new Insets(0,0,20,0));


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

        btnPesquisarQuartos.setOnAction((e)->{
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

        btnEnviaMsg.setOnAction((e)->{
            ImageView imageView2;
            imageView2 = new ImageView("App/UI/Resources/Images/AvatarSenhorio.jpg");
            imageView2.setFitWidth(30);
            imageView2.setFitHeight(30);
            Label mensagem = new Label(tfInputMensagem.getText());
            mensagem.setStyle("-fx-font: 12 arial;");
            zonaMsg.add(imageView2, 2, ContadorLinha);
            zonaMsg.add(mensagem, 1, ContadorLinha);
            appObs.adicionaMensagem(TipoUtilzadores.Senhorio, tfInputMensagem.getText());
            ContadorLinha++;
        });
    }
}

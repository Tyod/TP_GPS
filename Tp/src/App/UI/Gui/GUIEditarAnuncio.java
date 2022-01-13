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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;

import java.io.File;

public class GUIEditarAnuncio extends BorderPane {
    private AppObs appObs;

    Boolean despesas  = false;

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
    Label lbDespesas; Button btnDespesas;
    Label lbContactos; TextField tfContactos;

    //Painel Botoes criacao anuncio
    Button btnGuardar;
    Button btnCancelar;
    Button btnInfo;
    VBox painelButoes;

    //Zona de Criação do anuncio
    Line linha;
    HBox painelCriacao;

    //Painel Principal
    VBox painel;

    public GUIEditarAnuncio(AppObs g){
        appObs = g;
        criarComponentes();
        disporVista();
        registarLiscteners();
        CSSManager.setCSS(this, "mystyles.css");
        appObs.registaPropertyChangeListener(
                new PropsID("prop_estado"),
                (e) -> {
                    this.setVisible(appObs.getSituacao() == AppSituation.Editar_anuncio);

                    if(appObs.getSituacao() == AppSituation.Editar_anuncio){
                        repoemVista();
                    }
                });
    }

    private void repoemVista() {
        cbEstado.setValue(appObs.getTempQuarto().getDisponiblidade());
        tfPreco.setText(Integer.toString(appObs.getTempQuarto().getPreco()));
        if(appObs.getTempQuarto().getServicos().equals("Limpeza // TV Cabo // WI-FI"))
            chLimpeza.setSelected(true);chTvcabo.setSelected(true);chWifi.setSelected(true);
        if(appObs.getTempQuarto().getServicos().equals("Limpeza // WI-FI"))
            chLimpeza.setSelected(true);chWifi.setSelected(true);
        if(appObs.getTempQuarto().getServicos().equals("Limpeza // TV Cabo"))
            chLimpeza.setSelected(true);chTvcabo.setSelected(true);
        if(appObs.getTempQuarto().getServicos().equals("TV Cabo // WI-FI"))
            chTvcabo.setSelected(true);chWifi.setSelected(true);
        if(appObs.getTempQuarto().getServicos().equals("Limpeza"))
            chLimpeza.setSelected(true);
        if(appObs.getTempQuarto().getServicos().equals("TV Cabo"))
            chTvcabo.setSelected(true);
        if(appObs.getTempQuarto().getServicos().equals("WI-FI"))
            chWifi.setSelected(true);
        tfLocalizacao.setText(appObs.getTempQuarto().getLocalizacao());
        if(appObs.getTempQuarto().getDespesas()) {
            btnDespesas.setText("Incluídas");
            btnDespesas.setStyle("-fx-background-color: #00FF00");
            despesas = true;
        }
        else  {
            btnDespesas.setText("Não Incluídas");
            btnDespesas.setStyle("-fx-background-color: #ff0000");
            despesas = false;
        }
        tfContactos.setText(Long.toString(appObs.getTempQuarto().getContacto()));
        imageView.setImage(new Image(appObs.getTempQuarto().getImagem()));
    }

    private void criarComponentes() {
        lbNomeApp = new Label("Room4You");
        lbTitulo = new Label("Editar Anuncio");
        btnPesquisarQuartos = new Button("Consultar Quartos");
        btnMensagensNotificacoes = new Button("Mensagens");
        btnPT = new Button("PT");
        btnENG = new Button("ENG");
        btnSair = new Button("Sair");
        painel = new VBox();
        cabecalho = new HBox();
        subCabecalho = new HBox();

        imageView = new ImageView("App/UI/Resources/Images/DefaultImage.png");
        lbImagem = new Label("Imagem:");
        btnNovaImagem = new Button("Alerar Imagem");
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
        lbDespesas = new Label("Despesas: ");
        btnDespesas = new Button("Não Incluídas");
        btnDespesas.setStyle("-fx-background-color: #ff0000");
        lbContactos = new Label("Contactos: ");
        tfContactos = new TextField("Inserir contacto...");
        painelImagem = new VBox();
        btnGuardar = new Button("✔");
        btnCancelar = new Button("✖");
        btnInfo= new Button("❓");
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
        lbTitulo.setPadding(new Insets(0,695, 0,3));
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
        btnNovaImagem.setPadding(new Insets(5,118,5,118));
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
        lbDespesas.setStyle("-fx-font-weight:bold;");
        zonaDeDetalhes.add(lbDespesas, 0,5);
        zonaDeDetalhes.add(btnDespesas,1,5);
        lbContactos.setStyle("-fx-font-weight:bold;");
        zonaDeDetalhes.add(lbContactos, 0,6);
        zonaDeDetalhes.add(tfContactos,1,6);
        zonaDeDetalhes.setVgap(10);
        zonaDeDetalhes.setAlignment(Pos.CENTER);

        //Painel Botoes criacao anuncio
        btnGuardar.setPadding(new Insets(7.5,7.5,7.5,7.5));
        btnCancelar.setPadding(new Insets(7.5,7.5,7.5,7.5));
        btnInfo.setPadding(new Insets(7.5,7.5,7.5,7.5));
        btnInfo.setTooltip(new Tooltip("Estado: Escolher um estado\nPreço: [0-9]+\nLocalização: [a-z][A-Z]\nContacto: [0-9]+ (9 digitos)"));
        painelButoes.setSpacing(2);
        painelButoes.getChildren().addAll(btnGuardar, btnCancelar, btnInfo);


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

    private Boolean validaCampos() {
        System.out.println(cbEstado.getValue() != null);
        System.out.println(tfPreco.getText().matches("^-?\\d+$"));
        System.out.println(tfLocalizacao.getText().matches("^[a-zA-Z]+$"));
        System.out.println(tfContactos.getText().matches("^-?\\d+$"));
        System.out.println(tfContactos.getText().length() == 9);
        return (cbEstado.getValue() != null && tfPreco.getText().matches("^-?\\d+$") && tfLocalizacao.getText().matches("^[a-zA-Z]+$") && tfContactos.getText().matches("^-?\\d+$") && tfContactos.getText().length() == 9);
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

        btnGuardar.setOnAction((e)->{
            if(validaCampos()){
                appObs.getTempQuarto().setDisponiblidade((DisponibilidadeQuarto) cbEstado.getValue());
                appObs.getTempQuarto().setPreco(Integer.parseInt(tfPreco.getText()));

                if(chLimpeza.isSelected() && chTvcabo.isSelected() && chWifi.isSelected())
                    appObs.getTempQuarto().setServicos("Limpeza // TV Cabo // WI-FI");
                if(chLimpeza.isSelected() && chWifi.isSelected() && !chTvcabo.isSelected())
                    appObs.getTempQuarto().setServicos("Limpeza // WI-FI");
                if(chLimpeza.isSelected() && chTvcabo.isSelected() && !chWifi.isSelected())
                    appObs.getTempQuarto().setServicos("Limpeza // TV Cabo");
                if(chTvcabo.isSelected() && chWifi.isSelected() && !chLimpeza.isSelected())
                    appObs.getTempQuarto().setServicos("TV Cabo // WI-FI");
                if(chLimpeza.isSelected() && !chWifi.isSelected() && !chTvcabo.isSelected())
                    appObs.getTempQuarto().setServicos("Limpeza");
                if(chTvcabo.isSelected() && !chWifi.isSelected() && !chLimpeza.isSelected())
                    appObs.getTempQuarto().setServicos("TV Cabo");
                if(chWifi.isSelected() && !chLimpeza.isSelected() && !chTvcabo.isSelected())
                    appObs.getTempQuarto().setServicos("WI-FI");
                if(!chWifi.isSelected() && !chLimpeza.isSelected() && !chTvcabo.isSelected())
                    appObs.getTempQuarto().setServicos("Não possui serviços");

                appObs.getTempQuarto().setDespesas(despesas);
                appObs.getTempQuarto().setLocalizacao(tfLocalizacao.getText());
                appObs.getTempQuarto().setContacto(Integer.parseInt(tfContactos.getText()));

                appObs.atualizaEdicaoListaQuartosPublicos(appObs.getTempQuarto());
                appObs.atualizaEdicaoListaQuartosFavoritos(appObs.getTempQuarto());
                appObs.atualizaEdicaoListaQuartosPendentes(appObs.getTempQuarto());

                appObs.geraVistaListaQuartosPessoal();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Campos Inválidos");
                alert.setHeaderText("Alguns dos campos inseridos não estão corretamente preenchidos");
                alert.showAndWait();
            }
        });

        btnCancelar.setOnAction((e)->{
            appObs.geraVistaListaQuartosPessoal();
        });

        btnDespesas.setOnAction((e)->{
            if(!despesas) {
                btnDespesas.setText("Incluídas");
                btnDespesas.setStyle("-fx-background-color: #00FF00");
                despesas = true;
            }
            else  {
                btnDespesas.setText("Não Incluídas");
                btnDespesas.setStyle("-fx-background-color: #ff0000");
                despesas = false;
            }
        });

        btnPT.setOnAction((e)->{
            btnPT.setStyle("-fx-background-color: #FF0000;");
            btnENG.setStyle("-fx-background-color: #FFFFFF;");

            lbNomeApp.setPadding(new Insets(0, 624, 0 ,3));
            btnMensagensNotificacoes.setText("Mensagens");
            btnPesquisarQuartos.setText("Consultar Quartos");
            btnSair.setText("Sair");

            lbTitulo.setText("Criar Anuncio");
            lbTitulo.setPadding(new Insets(0,705, 0,3));
            lbImagem.setText("Imagem:");
            lbDetalhes.setText("Detalhes:");
            lbEstado.setText("Estado:");
            lbPreco.setText("Preço:");
            tfPreco.setText("Inserir preço...");
            lbServicos.setText("Serviços:");
            chLimpeza.setText("Limpeza");
            chTvcabo.setText("TvCabo");
            lbLocalizacao.setText("Localização:");
            tfLocalizacao.setText("Inserir localização...");
            lbDespesas.setText("Despesas:");
            lbContactos.setText("Contactos:");
            tfContactos.setText("Inserir contacto...");
            btnNovaImagem.setText("Alterar Imagem");
            btnNovaImagem.setPadding(new Insets(5,107,5,107));
            btnInfo.setTooltip(new Tooltip("Estado: Escolher um estado\nPreço: [0-9]+\nLocalização: [a-z][A-Z]\nContacto: [0-9]+ (9 digitos)"));
            btnNovaImagem.setPadding(new Insets(5,117,5,117));
        });

        btnENG.setOnAction((e)->{
            btnENG.setStyle("-fx-background-color: #0004F5");
            btnPT.setStyle("-fx-background-color: #FFFFFF;");

            lbNomeApp.setPadding(new Insets(0, 650, 0 ,3));
            btnMensagensNotificacoes.setText("Messages");
            btnPesquisarQuartos.setText("Consult rooms");
            btnSair.setText("Exit");

            lbTitulo.setText("Create Ad");
            lbTitulo.setPadding(new Insets(0,760, 0,3));
            lbImagem.setText("Image:");
            lbDetalhes.setText("Details:");
            lbEstado.setText("State:");
            lbPreco.setText("Price:");
            tfPreco.setText("Insert price...");
            lbServicos.setText("Services:");
            chLimpeza.setText("Cleaning");
            chTvcabo.setText("Cable TV");
            lbLocalizacao.setText("Location:");
            tfLocalizacao.setText("Insert location...");
            lbDespesas.setText("Expenses: ");
            lbContactos.setText("Contact:");
            tfContactos.setText("Insert contact...");
            btnNovaImagem.setText("Change Image");
            btnNovaImagem.setPadding(new Insets(5,125,5,125));
            btnInfo.setTooltip(new Tooltip("State: Pick a state\nPrice: [0-9]+\nLocation: [a-z][A-Z]\nContact: [0-9]+ (9 digits)"));
            btnNovaImagem.setPadding(new Insets(5,119,5,119));
        });

        btnNovaImagem.setOnAction((e)->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Upload File Path");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
            );


            File file = fileChooser.showOpenDialog(this.getScene().getWindow());

            if (file != null) {
                imageView.setImage(new Image("file:///" + file.getPath()));
            } else  {
                System.out.println("error");
            }
        });
    }
}

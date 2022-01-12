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

    Boolean despesas  = false;

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
    Button btnInfo;

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

    private Boolean validaCampos() {
        return (opcoesDisponibilidade.getValue() != null && tfPreco.getText().matches("^-?\\d+$") && tfLocalizacao.getText().matches("^[a-zA-Z]+$"));
    }

    private void filtraVista(ArrayList<Quarto> tempLista){

        if(validaCampos()) {
            painel.getChildren().clear();
            painel.getChildren().addAll(cabecalho, subCabecalho, filtros);

            //Anuncios
            ArrayList<Quarto> listTemp = appObs.getListaQuartosPublicados();
            for (Quarto temp : tempLista) {
                GridPane anuncio = new GridPane();
                anuncio.setMaxWidth(950);
                ImageView imageView = new ImageView(temp.getImagem());
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                anuncio.add(new Label("Estado: " + temp.getDisponiblidade()), 1, 0);
                anuncio.add(new Label("Preço: " + temp.getPreco()), 1, 1);
                anuncio.add(new Label("Serviços: " + temp.getServicos()), 1, 2);
                anuncio.add(new Label("Localização: " + temp.getLocalizacao()), 1, 3);
                anuncio.add(new Label("Despesas: " + temp.getDespesas()), 1, 4);
                anuncio.add(new Label("Contactos: " + temp.getContacto()), 1, 5);
                anuncio.setVgap(8);
                anuncio.setPadding(new Insets(0, 250, 0, 20));
                Button btnFav = new Button("⭐");
                btnFav.setOnAction((e) -> {
                    if (!temp.getFavorito())
                        appObs.adicionaQuartoFavorito(temp.getId());
                    temp.setFavorito(true);
                    appObs.geraVistaFavoritos();
                });
                Button btnMsg = new Button("✉");
                btnMsg.setOnAction((e) -> {
                    appObs.geraVistaMensagensEstudante();
                });
                btnFav.setPadding(new Insets(7.5, 7.8, 7.5, 7.8));
                btnMsg.setPadding(new Insets(7.5, 7.5, 7.5, 7.5));
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
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Inválidos//Invalid Fields");
            alert.setHeaderText("Alguns dos campos inseridos não estão corretamente preenchidos\nSome of the fields entered are not filled in correctly");
            alert.showAndWait();
        }

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
            anuncio.add(new Label("Despesas: " + temp.getDespesas()),1, 4);
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
        opcoesDisponibilidade.getItems().add(DisponibilidadeQuarto.disponivel);
        opcoesDisponibilidade.getItems().add(DisponibilidadeQuarto.indisponivel);
        opcoesDisponibilidade.getItems().add(DisponibilidadeQuarto.brevemente_disponivel);
        lbPreco = new Label("Preço: ");
        tfPreco = new TextField("Valor max");
        lbDespesas = new Label("Despesas: ");
        btnDespesas = new Button("Não Incluídas");
        btnDespesas.setStyle("-fx-background-color: #ff0000");
        lbServicos = new Label("Servicos: ");
        chWifi = new CheckBox("WI-FI");
        chTvCabo = new CheckBox("TvCabo");
        chLimpeza = new CheckBox("Limpeza");
        lbLocalizacao = new Label("Localização: ");
        tfLocalizacao = new TextField("Inserir localização...");
        btnFiltrar = new Button("Pesquisar \uD83D\uDD0D");
        btnInfo= new Button("❓");


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
        filtros.setMaxWidth(870);
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
        btnFiltrar.setPadding(new Insets(3.5,3.5,3.5,3.5));
        filtros.add(btnFiltrar,  4, 3);
        btnInfo.setPadding(new Insets(4,4,4,4));
        btnInfo.setTooltip(new Tooltip("Estado: Escolher um estado\nPreço: [0-9]+\nLocalização: [a-z][A-Z]"));
        filtros.add(btnInfo,5,3);
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

        btnFiltrar.setOnAction((e)->{
            ArrayList<Quarto> tempLista = new ArrayList<>();
            for(Quarto temp : appObs.getListaQuartosPublicados()) {
                if (tfPreco.getText().equals("Valor max") || tfPreco.getText().equals("Max value")) {
                    if(tfLocalizacao.getText().equals("Inserir localização...") || tfLocalizacao.getText().equals("Insert location...")) {
                        if (temp.getDisponiblidade() == opcoesDisponibilidade.getValue() && temp.getDespesas().equals(despesas)) {
                            if (chLimpeza.isSelected() && chWifi.isSelected() && chTvCabo.isSelected() && temp.getServicos().equals("Limpeza // TV Cabo // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && chWifi.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Limpeza // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && chTvCabo.isSelected() && !chWifi.isSelected() && temp.getServicos().equals("Limpeza // TV Cabo"))
                                tempLista.add(temp);
                            if (chTvCabo.isSelected() && chWifi.isSelected() && !chLimpeza.isSelected() && temp.getServicos().equals("TV Cabo // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && !chWifi.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Limpeza"))
                                tempLista.add(temp);
                            if (chTvCabo.isSelected() && !chWifi.isSelected() && !chLimpeza.isSelected() && temp.getServicos().equals("TV Cabo"))
                                tempLista.add(temp);
                            if (chWifi.isSelected() && !chLimpeza.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("WI-FI"))
                                tempLista.add(temp);
                            if (!chWifi.isSelected() && !chLimpeza.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Não possui serviços"))
                                tempLista.add(temp);
                        }
                    }else {
                        if (temp.getDisponiblidade() == opcoesDisponibilidade.getValue() && temp.getDespesas().equals(despesas) && temp.getLocalizacao().equals(tfLocalizacao.getText())) {
                            if (chLimpeza.isSelected() && chWifi.isSelected() && chTvCabo.isSelected() && temp.getServicos().equals("Limpeza // TV Cabo // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && chWifi.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Limpeza // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && chTvCabo.isSelected() && !chWifi.isSelected() && temp.getServicos().equals("Limpeza // TV Cabo"))
                                tempLista.add(temp);
                            if (chTvCabo.isSelected() && chWifi.isSelected() && !chLimpeza.isSelected() && temp.getServicos().equals("TV Cabo // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && !chWifi.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Limpeza"))
                                tempLista.add(temp);
                            if (chTvCabo.isSelected() && !chWifi.isSelected() && !chLimpeza.isSelected() && temp.getServicos().equals("TV Cabo"))
                                tempLista.add(temp);
                            if (chWifi.isSelected() && !chLimpeza.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("WI-FI"))
                                tempLista.add(temp);
                            if (!chWifi.isSelected() && !chLimpeza.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Não possui serviços"))
                                tempLista.add(temp);
                        }
                    }
                } else {
                    if (!tfLocalizacao.getText().equals("Inserir localização...") || !tfLocalizacao.getText().equals("Insert location...")) {
                        if (temp.getDisponiblidade() == opcoesDisponibilidade.getValue() && temp.getPreco() < Integer.parseInt(tfPreco.getText()) && temp.getDespesas().equals(despesas) && temp.getLocalizacao().equals(tfLocalizacao.getText())) {
                            if (chLimpeza.isSelected() && chWifi.isSelected() && chTvCabo.isSelected() && temp.getServicos().equals("Limpeza // TV Cabo // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && chWifi.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Limpeza // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && chTvCabo.isSelected() && !chWifi.isSelected() && temp.getServicos().equals("Limpeza // TV Cabo"))
                                tempLista.add(temp);
                            if (chTvCabo.isSelected() && chWifi.isSelected() && !chLimpeza.isSelected() && temp.getServicos().equals("TV Cabo // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && !chWifi.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Limpeza"))
                                tempLista.add(temp);
                            if (chTvCabo.isSelected() && !chWifi.isSelected() && !chLimpeza.isSelected() && temp.getServicos().equals("TV Cabo"))
                                tempLista.add(temp);
                            if (chWifi.isSelected() && !chLimpeza.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("WI-FI"))
                                tempLista.add(temp);
                            if (!chWifi.isSelected() && !chLimpeza.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Não possui serviços"))
                                tempLista.add(temp);
                        }
                    }else{
                        if (temp.getDisponiblidade() == opcoesDisponibilidade.getValue() && temp.getPreco() < Integer.parseInt(tfPreco.getText()) && temp.getDespesas().equals(despesas)) {
                            if (chLimpeza.isSelected() && chWifi.isSelected() && chTvCabo.isSelected() && temp.getServicos().equals("Limpeza // TV Cabo // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && chWifi.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Limpeza // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && chTvCabo.isSelected() && !chWifi.isSelected() && temp.getServicos().equals("Limpeza // TV Cabo"))
                                tempLista.add(temp);
                            if (chTvCabo.isSelected() && chWifi.isSelected() && !chLimpeza.isSelected() && temp.getServicos().equals("TV Cabo // WI-FI"))
                                tempLista.add(temp);
                            if (chLimpeza.isSelected() && !chWifi.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Limpeza"))
                                tempLista.add(temp);
                            if (chTvCabo.isSelected() && !chWifi.isSelected() && !chLimpeza.isSelected() && temp.getServicos().equals("TV Cabo"))
                                tempLista.add(temp);
                            if (chWifi.isSelected() && !chLimpeza.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("WI-FI"))
                                tempLista.add(temp);
                            if (!chWifi.isSelected() && !chLimpeza.isSelected() && !chTvCabo.isSelected() && temp.getServicos().equals("Não possui serviços"))
                                tempLista.add(temp);
                        }
                    }
                }
            }
            filtraVista(tempLista);
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

            lbNomeApp.setPadding(new Insets(0, 514, 0 ,3));
            btnMensagensNotificacoes.setText("Mensagens");
            btnPesquisarQuartos.setText("Pesquisar Quartos");
            btnListaFavoritos.setText("Lista De Favoritos");
            btnSair.setText("Sair");

            lbTitulo.setText("Lista de quartos publicados");
            lbTitulo.setPadding(new Insets(0,490, 0,3));

            lbTituloFiltro.setText("Filtros de pesquisa:");
            lbEstado.setText("Estado:");
            lbPreco.setText("Preço:");
            tfPreco.setText("Valor max");
            lbDespesas.setText("Despesas:");
            lbServicos.setText("Serviços:");
            chTvCabo.setText("TvCabo");
            chLimpeza.setText("Limpeza");
            lbLocalizacao.setText("Localização:");
            tfLocalizacao.setText("Inserir localização...");
            btnFiltrar.setText("Pesquisar \uD83D\uDD0D");
            lbMsgFimDePagina.setText("Não há mais anuncios publicados a mostrar...");
            btnInfo.setTooltip(new Tooltip("Estado: Escolher um estado\nPreço: [0-9]+\nLocalização: [a-z][A-Z]"));
            filtros.setMaxWidth(870);
        });

        btnENG.setOnAction((e)->{
            btnENG.setStyle("-fx-background-color: #0004F5");
            btnPT.setStyle("-fx-background-color: #FFFFFF;");

            lbNomeApp.setPadding(new Insets(0, 567, 0 ,3));
            btnMensagensNotificacoes.setText("Messages");
            btnPesquisarQuartos.setText("Consult rooms");
            btnListaFavoritos.setText("Favorites List");
            btnSair.setText("Exit");

            lbTitulo.setText("List of published rooms");
            lbTitulo.setPadding(new Insets(0,555, 0,3));

            lbTituloFiltro.setText("Search filters:");
            lbEstado.setText("State:");
            lbPreco.setText("Price:");
            tfPreco.setText("Max value");
            lbDespesas.setText("Expenses: ");
            lbServicos.setText("Services:");
            chTvCabo.setText("Cable TV");
            chLimpeza.setText("Cleaning");
            lbLocalizacao.setText("Location:");
            tfLocalizacao.setText("Insert location...");
            btnFiltrar.setText("Search \uD83D\uDD0D");
            lbMsgFimDePagina.setText("There are no more published ads to show...");
            btnInfo.setTooltip(new Tooltip("State: Pick a state\nPrice: [0-9]+\nLocation: [a-z][A-Z]"));
            filtros.setMaxWidth(820);
        });
    }
}

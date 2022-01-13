package App.Logica.Data;

import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;

public class AppData {
    private Quarto tempQuarto;
    private  ArrayList<Quarto> listaQuartoPublicados;
    private  ArrayList<Quarto> listaQuartosPendentes;
    private  ArrayList<Quarto> listaQuartosPessoal;
    private  ArrayList<Quarto> listaFavoritos;

    private ArrayList<Mensagem> listaMensagens;

    public AppData(){
        listaQuartoPublicados = new ArrayList<>();
        listaQuartosPendentes = new ArrayList<>();
        listaQuartosPessoal = new ArrayList<>();
        listaFavoritos = new ArrayList<>();

        listaMensagens = new ArrayList<>();

        tempQuarto = null;

        //guardaListaPublicados("ListaQuartosPublicados.txt");
        //guardaListaPendentes("ListaQuartosPendentes.txt");
        //guardaListaPessoal("ListaQuartosPessoal.txt");
        //guardaListaFavoritos("ListaFavoritos.txt");
        //guardaMensagens("ListaMensagens.txt");

        carregaListaPublicados("ListaQuartosPublicados.txt");
        carregaListaPendentes("ListaQuartosPendentes.txt");
        carregaListaPessoal("ListaQuartosPessoal.txt");
        carregaListaFavoritos("ListaFavoritos.txt");

        carregaMensagens("ListaMensagens.txt");

        iniciaContadorQuartos();
    }

    private void iniciaContadorQuartos() {
        if(listaQuartosPessoal.size() == 0){
            Quarto.setContador(0);
        }
        else{
            int max=0;
            for(Quarto temp : listaQuartosPessoal){
                if(temp.getId() >= max)
                    max = temp.getId();
            }
            Quarto.setContador(max+1);
        }
    }

    public Quarto getTempQuarto() {
        return tempQuarto;
    }

    public void setTempQuarto(Quarto tempQuarto) {
        this.tempQuarto = tempQuarto;
    }

    public ArrayList<Quarto> getListaQuartosPendentes() {
        return listaQuartosPendentes;
    }

    public ArrayList<Quarto> getListaQuartosPessoal() {
        return listaQuartosPessoal;
    }
    public Quarto getLastQuartoFromListaQuartosPessoal(){
            return listaQuartosPessoal.get(listaQuartosPessoal.size()-1);
    }

    public Quarto getQuartoFromListaQuartosPessoal(int id){
        for(Quarto temp: listaQuartosPessoal)
            if(temp.getId() == id)
                return temp;

            return null;
    }

    public ArrayList<Quarto> getListaFavoritos() {
        return listaFavoritos;
    }

    public ArrayList<Quarto> getListaQuartoPublicados() {
        return listaQuartoPublicados;
    }

    public ArrayList<Mensagem> getListaMensagens() { return listaMensagens; }

    //ADICIONA ELEMENTOS ÀS LISTAS
    public void adicionaQuartoPublicado(Quarto quarto){
        listaQuartoPublicados.add(quarto);
        guardaListaPublicados("ListaQuartosPublicados.txt");
    }

    public void adicionaQuartoPendentes(Quarto quarto){
        listaQuartosPendentes.add(quarto);
        guardaListaPendentes("ListaQuartosPendentes.txt");
    }

    public void adicionaQuartoPessoal(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, Image imagem){
        listaQuartosPessoal.add(new Quarto(disponibilidade, preco, localizacao, servicos, despesas, contacto, imagem.getUrl()));
        guardaListaPessoal("ListaQuartosPessoal.txt");
    }

    public void adicionaQuartoFavorito(Quarto quarto){
        listaFavoritos.add(quarto);
        guardaListaFavoritos("ListaFavoritos.txt");
    }

    public void adicionaMensagem(TipoUtilzadores utilizador, String msg){
        listaMensagens.add(new Mensagem(utilizador, msg));
        guardaMensagens("ListaMensagens.txt");
    }


    //REMOVE ELEMENTOS DAS LISTAS
    public boolean removeQuartoPublicado(int id){
        for(Quarto temp : listaQuartoPublicados)
            if(temp.getId() == id) {
                listaQuartoPublicados.remove(temp);
                guardaListaPublicados("ListaQuartosPublicados.txt");
                return true;
            }

        return false;
    }


    public boolean removeQuartoPendente(int id){
        for(Quarto temp : listaQuartosPendentes)
            if(temp.getId() == id) {
                listaQuartosPendentes.remove(temp);
                guardaListaPendentes("ListaQuartosPendentes.txt");
                return true;
            }

        return false;
    }


    public boolean removeQuartoPessoal(int id){
        for(Quarto temp : listaQuartosPessoal)
            if(temp.getId() == id){
                listaQuartosPessoal.remove(temp);
                guardaListaPessoal("ListaQuartosPessoal.txt");
                return true;
            }

        return false;
    }


    public boolean removeQuartoFavorito(int id){
        for(Quarto temp : listaFavoritos)
            if(temp.getId() == id){
                listaFavoritos.remove(temp);
                guardaListaFavoritos("ListaFavoritos.txt");
                return true;
            }

        return false;
    }



    //CARREGAR DAS LISTAS ARMAZENADAS EM FICHEIROS
    public boolean carregaListaPublicados(String fileName){
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            listaQuartoPublicados = (ArrayList<Quarto>) objectIn.readObject();
            objectIn.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean carregaListaPendentes(String fileName){
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            listaQuartosPendentes = (ArrayList<Quarto>) objectIn.readObject();
            objectIn.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean carregaListaPessoal(String fileName){
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            listaQuartosPessoal = (ArrayList<Quarto>) objectIn.readObject();
            objectIn.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean carregaListaFavoritos(String fileName){
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            listaFavoritos = (ArrayList<Quarto>) objectIn.readObject();
            objectIn.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean carregaMensagens(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            listaMensagens = (ArrayList<Mensagem>) objectIn.readObject();
            objectIn.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



    //GUARDA LISTAS NOS FICHEIROS
    public boolean guardaListaPublicados(String fileName){
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(listaQuartoPublicados);
            objectOut.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardaListaPendentes(String fileName){
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(listaQuartosPendentes);
            objectOut.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardaListaPessoal(String fileName){
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(listaQuartosPessoal);
            objectOut.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardaListaFavoritos(String fileName){
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(listaFavoritos);
            objectOut.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardaMensagens(String fileName){
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(listaMensagens);
            objectOut.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void atualizaEdicaoListaQuartosPublicos(Quarto tempQuarto) {
        for(Quarto temp : listaQuartoPublicados){
            if(temp.getId() == tempQuarto.getId()){
                temp.setDisponiblidade(tempQuarto.getDisponiblidade());
                temp.setPreco(tempQuarto.getPreco());
                temp.setLocalizacao(tempQuarto.getLocalizacao());
                temp.setServicos(tempQuarto.getServicos());
                temp.setDespesas(tempQuarto.getDespesas());
                temp.setContacto(tempQuarto.getContacto());
                temp.setImagem(tempQuarto.getImagem());
                temp.setAprovacao(tempQuarto.getAprovacao());
                temp.setPublicado(tempQuarto.getPublicado());
                temp.setFavorito(tempQuarto.getFavorito());
            }
        }
    }

    public void atualizaEdicaoListaQuartosFavoritos(Quarto tempQuarto) {
        for(Quarto temp : listaFavoritos){
            if(temp.getId() == tempQuarto.getId()){
                temp.setDisponiblidade(tempQuarto.getDisponiblidade());
                temp.setPreco(tempQuarto.getPreco());
                temp.setLocalizacao(tempQuarto.getLocalizacao());
                temp.setServicos(tempQuarto.getServicos());
                temp.setDespesas(tempQuarto.getDespesas());
                temp.setContacto(tempQuarto.getContacto());
                temp.setImagem(tempQuarto.getImagem());
                temp.setAprovacao(tempQuarto.getAprovacao());
                temp.setPublicado(tempQuarto.getPublicado());
                temp.setFavorito(tempQuarto.getFavorito());
            }
        }
    }

    public void atualizaEdicaoListaQuartosPendentes(Quarto tempQuarto){
        for(Quarto temp : listaQuartosPendentes){
            if (temp.getId() == tempQuarto.getId()){
                temp.setDisponiblidade(tempQuarto.getDisponiblidade());
                temp.setPreco(tempQuarto.getPreco());
                temp.setLocalizacao(tempQuarto.getLocalizacao());
                temp.setServicos(tempQuarto.getServicos());
                temp.setDespesas(tempQuarto.getDespesas());
                temp.setContacto(tempQuarto.getContacto());
                temp.setImagem(tempQuarto.getImagem());
                temp.setAprovacao(tempQuarto.getAprovacao());
                temp.setPublicado(tempQuarto.getPublicado());
                temp.setFavorito(tempQuarto.getFavorito());
            }
        }
    }
}

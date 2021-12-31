package App.Logica.Data;

import java.io.*;
import java.util.ArrayList;

public class AppData implements Serializable {
    private  ArrayList<Quarto> listaQuartoPublicados;
    private  ArrayList<Quarto> listaQuartosPendentes;
    private  ArrayList<Quarto> listaQuartosPessoal;
    private  ArrayList<Quarto> listaFavoritos;

    public AppData(){
        listaQuartoPublicados = new ArrayList<>();
        listaQuartosPendentes = new ArrayList<>();
        listaQuartosPessoal = new ArrayList<>();
        listaFavoritos = new ArrayList<>();

        carregaListaPublicados("ListaQuartosPublicados.txt");
        carregaListaPendentes("ListaQuartosPendentes.txt");
        carregaListaPessoal("ListaQuartosPessoal.txt");
        carregaListaFavoritos("ListaFavoritos.txt");

        listaQuartoPublicados.clear();
        listaQuartoPublicados.add(new Quarto(DisponibilidadeQuarto.disponivel, 10, "Oliveira", "Mesa", true, 10, "C:\\Users\\AndreSilva\\OneDrive - ISEC\\Universidade\\5 - 3º Ano_1º Semestre\\GPS\\TP_GPS\\Tp\\src\\App\\UI\\Resources\\Images\\FundoInicial.png"));
    }

    public ArrayList<Quarto> getListaQuartoPublicados() {
        return listaQuartoPublicados;
    }

    //ADICIONA ELEMENTOS ÀS LISTAS
    public void adicionaQuartoPublicado(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, String imagem){
        listaQuartoPublicados.add(new Quarto(disponibilidade, preco, localizacao, servicos, despesas, contacto, imagem));
        guardaListaPublicados("ListaQuartosPublicados.txt");
    }

    public void adicionaQuartoPendentes(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, String imagem){
        listaQuartosPendentes.add(new Quarto(disponibilidade, preco, localizacao, servicos, despesas, contacto, imagem));
        guardaListaPendentes("ListaQuartosPendentes.txt");
    }

    public void adicionaQuartoPessoal(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, String imagem){
        listaQuartosPessoal.add(new Quarto(disponibilidade, preco, localizacao, servicos, despesas, contacto, imagem));
        guardaListaPessoal("ListaQuartosPessoal.txt");
    }

    public void adicionaQuartoFavorito(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, String imagem){
        listaFavoritos.add(new Quarto(disponibilidade, preco, localizacao, servicos, despesas, contacto, imagem));
        guardaListaFavoritos("ListaFavoritos.txt");
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
}

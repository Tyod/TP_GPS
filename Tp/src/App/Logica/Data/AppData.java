package App.Logica.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppData implements Serializable {
    private  List<Quarto> listaQuartoPublicados;
    private  List<Quarto> listaQuartosPendentes;
    private  List<Quarto> listaQuartosPessoal;
    private  List<Quarto> listaFavoritos;

    public AppData(){
        listaQuartoPublicados = new ArrayList<>();
        listaQuartosPendentes = new ArrayList<>();
        listaQuartosPessoal = new ArrayList<>();
        listaFavoritos = new ArrayList<>();

        carregaListaPublicados("ListaQuartosPublicados.txt");
        carregaListaPendentes("ListaQuartosPendentes.txt");
        carregaListaPessoal("ListaQuartosPessoal.txt");
        carregaListaFavoritos("ListaFavoritos.txt");
    }


    //ADICIONA ELEMENTOS ÀS LISTAS
    public void adicionaQuartoPublicado(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto){
        listaQuartoPublicados.add(new Quarto(disponibilidade, preco, localizacao, servicos, despesas, contacto));
        guardaListaPublicados("ListaQuartosPublicados.txt");
    }

    public void adicionaQuartoPendentes(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto){
        listaQuartosPendentes.add(new Quarto(disponibilidade, preco, localizacao, servicos, despesas, contacto));
        guardaListaPendentes("ListaQuartosPendentes.txt");
    }

    public void adicionaQuartoPessoal(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto){
        listaQuartosPessoal.add(new Quarto(disponibilidade, preco, localizacao, servicos, despesas, contacto));
        guardaListaPessoal("ListaQuartosPessoal.txt");
    }

    public void adicionaQuartoFavorito(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto){
        listaFavoritos.add(new Quarto(disponibilidade, preco, localizacao, servicos, despesas, contacto));
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
            listaQuartoPublicados = (List<Quarto>) objectIn.readObject();
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
            listaQuartosPendentes = (List<Quarto>) objectIn.readObject();
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
            listaQuartosPessoal = (List<Quarto>) objectIn.readObject();
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
            listaFavoritos = (List<Quarto>) objectIn.readObject();
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

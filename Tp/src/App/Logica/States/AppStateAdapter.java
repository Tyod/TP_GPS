package App.Logica.States;

import App.Logica.Data.AppData;
import App.Logica.Data.Quarto;

import java.util.ArrayList;

public abstract class AppStateAdapter  implements IAppState{
    protected AppData dados;
    protected AppStateAdapter(AppData dados){ this.dados = dados; }
    public IAppState geraVistaListaQuartosPublicados(){ return null; }
    public IAppState geraVistaListaQuartosPendentes(){ return null; }
    public IAppState geraVistaListaQuartosPessoal(){ return null; }
    public IAppState geraVistaMensagensEstudante() { return null; }
    public IAppState geraVistaMensagensSenhorio(){ return null; }
    public IAppState geraVistaEscolheVista(){ return null; }
    public IAppState geraVistaFavoritos(){ return null; }
    public IAppState geraVistaCriarAnuncio(){ return null; }
    public IAppState geraVistaEditarAnuncio(){ return null; }
    public ArrayList<Quarto> getListaQuartosPublicados(){ return null; }
}

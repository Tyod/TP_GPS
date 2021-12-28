package App.Logica.States;

import App.Logica.App;
import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public abstract class AppStateAdapter  implements IAppState{
    protected AppData dados;
    protected AppStateAdapter(AppData dados){ this.dados = dados; }
    public IAppState geraVistaListaQuartosPublicados(){ return null; }
    public IAppState geraVistaListaQuartosPendentes(){return null; }
    public IAppState geraVistaListaQuartosPessoal(){ return null; }
}

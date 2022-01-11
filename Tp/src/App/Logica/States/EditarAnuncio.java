package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;
import App.Logica.Data.Quarto;

public class EditarAnuncio  extends AppStateAdapter{

    public EditarAnuncio(AppData dados) {
        super(dados);
    }

    public IAppState geraVistaListaQuartosPessoal(){ return new ListaQuartosPessoal(dados); }

    public IAppState geraVistaMensagensSenhorio(){ return new MensagensNotificacoes_Senhorio(dados); }

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    public Quarto getTempQuarto(){ return dados.getTempQuarto(); }

    public void setTempQuarto(int id){
        dados.setTempQuarto(dados.getQuartoFromListaQuartosPessoal(id));
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Editar_anuncio;
    }
}

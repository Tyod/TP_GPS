package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;
import App.Logica.Data.DisponibilidadeQuarto;
import javafx.scene.image.Image;

public class CriarAnuncio extends AppStateAdapter{

    public CriarAnuncio(AppData dados) {
        super(dados);
    }

    public void adicionaQuartoPessoal(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, Image imagem){
        dados.adicionaQuartoPessoal(disponibilidade, preco, localizacao, servicos, despesas, contacto, imagem);
        dados.adicionaQuartoPendentes(dados.getLastQuartoFromListaQuartosPessoal());
    }

    public IAppState geraVistaListaQuartosPessoal(){ return new ListaQuartosPessoal(dados); }

    public IAppState geraVistaMensagensSenhorio(){ return new MensagensNotificacoes_Senhorio(dados); }

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Criar_Anuncio;
    }
}

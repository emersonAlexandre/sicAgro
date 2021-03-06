package br.edu.ifpb.sicAgro.beans.solicitacaoServicoMaquinas;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Manager bean responsável por gerenciar as páginas referentes a exibição de
 * detalhes de uma solicitação de serviço de veículo (máquina)
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class SolicitacaoServicoViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SolicitacaoServico solicitacaoServico;
	
	@Inject
	private SolicitacaoServicoService service;
	
	@Inject
	private PedidoSolicitacaoService pedidoSolicitacaoService;
	
	@Inject
	private VeiculoService veiculoService;

	/**
	 * É iniciando no início da renderização da pagina de solicitacaoView,
	 * responsável por obter uma solicitacao pelo contexto de aplicação.
	 */
	public void preRenderView() {
		solicitacaoServico = (SolicitacaoServico) JSFUtils.getParam("solicitacao");
	}
	
	/**
	 * método é chamado quando o uma solicitação é concluída na 
	 * pagina de visualização.
	 * 
	 * @throws SicAgroException
	 */
	public void completarSolicitacao() throws SicAgroException{
		if(solicitacaoServico.getTimeWorkeds() != null){
			this.veiculoService.setHorimetroVeiculo(solicitacaoServico.getVeiculo(), solicitacaoServico.getTimeWorkeds());
		}
		
		if(solicitacaoServico.getCompleted() && solicitacaoServico.getPedidoSolicitacao() != null){
			pedidoSolicitacaoService.completarPedidoSolicitacao(solicitacaoServico.getPedidoSolicitacao());
		}
		
		service.update(solicitacaoServico);
		MessageUtils.messageSucess("Solicitação finalizada com sucesso.");
	}
	/**
	 * Método para auxiliar no cancelamento de uma solicitação, i.e.,
	 * diante do cadastro de uma solicitação o usuário pode concluir e em seguida desistir
	 * de cde concluir uma solicitação, quando o mesmo desiste esse método é chamado.
	 */
	public void cancelConclusao(){
		solicitacaoServico.setCompleted(false);
		solicitacaoServico.setDateRealization(null);
		solicitacaoServico.setTimeWorkeds(null);
		service.update(solicitacaoServico);
	}

	public SolicitacaoServico getSolicitacaoServico() {
		return solicitacaoServico;
	}

	public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
		this.solicitacaoServico = solicitacaoServico;
	}
}

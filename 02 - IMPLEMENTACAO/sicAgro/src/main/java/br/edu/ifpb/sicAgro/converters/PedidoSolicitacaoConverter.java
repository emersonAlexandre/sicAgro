package br.edu.ifpb.sicAgro.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;

/**
 * Faces converter responsável por converter os atributos da entidade @PedidoSolicitacao para
 * string (visualização na pagina) e para objeto (gravar/recuperar/buscar). 
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
@FacesConverter(forClass = PedidoSolicitacao.class)
public class PedidoSolicitacaoConverter implements Converter {

	@Inject
	private PedidoSolicitacaoService pedidoSolicitacaoService;

	/**
	 * 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = Long.parseLong(value);
	      try {
	            return pedidoSolicitacaoService.findById(id);
	        } catch (NumberFormatException e) {
	            throw new ConverterException(new FacesMessage(String.format("%s é invalido para o pedido", id)), e);
	        }
	}

	/**
	 * 
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((PedidoSolicitacao) value).getId();
		return (id != null) ? id.toString() : null;
	}
}

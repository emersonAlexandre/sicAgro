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

import br.edu.ifpb.sicAgro.model.Endereco;
import br.edu.ifpb.sicAgro.services.EnderecoService;

/**
 * Faces converter responsável por converter os atributos da entidade @Veiculo para
 * string (visualização na pagina) e para objeto. 
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
@FacesConverter(forClass=Endereco.class)
public class EnderecoConverter implements Converter{

	@Inject
	private EnderecoService enderecoService;

	/**
	 * 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = null;
		Endereco endereco = null;
		try {
			id = Long.parseLong(value);
			 endereco = enderecoService.findById(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format(
					"%s é invalido para o endereço", id)), e);
		}
		return endereco;
	}

	/**
	 * 
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Endereco) value).getId();
		return (id != null) ? id.toString() : null;
	}

}

package br.edu.ifpb.sicAgro.services.impl;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.ProdutorDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.ContaService;
import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.util.jpa.Transactional;

public class ProdutorServiceImpl extends GenericServiceImpl<Produtor, Long> implements ProdutorService{

	
	private static final long serialVersionUID = -9171041213373059450L;
	
	@Inject
	private ContaService contaService;
	
	public ProdutorServiceImpl() {
		
	}

	@Inject
	public ProdutorServiceImpl(ProdutorDAO produtorDAO) {
		this.dao = produtorDAO;
	}
	
	@Override
	@Transactional
	public void add(Produtor entity) throws SicAgroExceptionHandler {
		if(isCPFExists(entity.getCpf())){
			throw new SicAgroExceptionHandler("Já existe um produtor cadastrado com este CPF:"+ entity.getCpf());
		}
		Conta conta = contaService.findByUserName(entity.getCpf());
		if(conta != null){
			throw new SicAgroExceptionHandler("Já existe um usuário cadastrado com este CPF:"+ entity.getCpf());
		}
		dao.add(entity);

	}

	@Override
	@Transactional
	public Produtor update(Produtor entity) {
		return dao.update(entity);
	}
	
	private boolean isCPFExists(String cpf){
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		return produtorDAO.isCPFExists(cpf);
	}

	@Override
	public Long getTotalProdutores() {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		return produtorDAO.getTotalProdutores();
	}

	@Override
	public Produtor findByCPF(String cpf) {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		return produtorDAO.findByCPF(cpf);
	}
}

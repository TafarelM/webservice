package com.tafarelmello.webservice.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tafarelmello.webservice.dao.TesteDAO;
import com.tafarelmello.webservice.model.Teste;

@Stateless
@Path("teste")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TesteService {

	@Inject
	TesteDAO testeDAO;
	
	public TesteService() {
	}

	//BUSCAR TODOS
	@GET
	public List<Teste> getTeste() {
		return testeDAO.getListOrdenada();
	}

	//BUSCAR
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Teste getTeste(@PathParam("id") Long id) {
		return testeDAO.encontrar(id);
	}

	//ADICIONAR
	@POST
	public Teste adicionar(Teste teste) {
		testeDAO.salvar(teste);
		return teste;
	}

	//EDITAR
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Teste atualizar(@PathParam("id") Long id, Teste testeAtualizado) {
		Teste teste = getTeste(id);
		teste.setNome(testeAtualizado.getNome());
		teste.setSobrenome(testeAtualizado.getSobrenome());

		testeDAO.atualizar(teste);

		return teste;
	}

	//EXCLUIR
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Teste excluir(@PathParam("id") Long id) {
		Teste teste = getTeste(id);
		testeDAO.remover(id);

		return teste;
	}

}

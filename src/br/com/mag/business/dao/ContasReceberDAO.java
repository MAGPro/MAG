package br.com.mag.business.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.mag.business.ContasReceber;

public class ContasReceberDAO extends GenericDAO <ContasReceber> {

public List<ContasReceber> ContasVenda(Integer vendaSelecionada){
		
		StringBuffer sql = new StringBuffer ("Select c from ContasReceber c where venda_codigoVenda = " + vendaSelecionada);
		Query query = getEntityManager().createQuery(sql.toString());
		List<ContasReceber> lista = query.getResultList();
		return lista;
	}
}

package br.com.mag.teste;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.mag.business.Cliente;
import br.com.mag.business.Endereco;
import br.com.mag.business.enumeration.TipoSituacaoCliente;
import br.com.mag.util.JPAUtil;

//import com.sun.security.ntlm.Client;

public class PopulaCliente {

	public static void main(String[] args) {

		EntityManager mn = new JPAUtil().getEntityManager();
		mn.getTransaction().begin();

		Cliente cliente = new Cliente();
		
		cliente.setNome("Ana Claudia Reis");
		cliente.setCpf("12245679896");
		cliente.setRg(10102109);
		// cliente.setDataNascimento(Calendar."2014");
		cliente.setSituacaoCliente(TipoSituacaoCliente.ADIPLENTE);

		
		mn.persist(cliente);
		mn.getTransaction().commit();
		mn.close();
	}

}

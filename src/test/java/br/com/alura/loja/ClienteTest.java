package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

public class ClienteTest {
	
	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://www.mocky.io"); // URI base de requisicoes
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		// System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185, 8 andar</rua>"));
	}
	
	@Test
	public void testaQueAConexaoComOServidorLocalFuncionaNoPathDeProjetos() {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8080"); // URI base de requisicoes
		String conteudo = target.path("/projetos").request().get(String.class);
		// System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("<nome>Minha loja</nome>"));
	}

}

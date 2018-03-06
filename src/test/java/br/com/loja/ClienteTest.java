package br.com.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.loja.model.Carrinho;
import br.com.loja.model.Produto;
import br.com.loja.model.Projeto;

public class ClienteTest {

	private HttpServer server;

	@Before // executa antes de cada teste
	public void iniciaServidor() {
		server = Servidor.inicializaServidor();
	}

	@After // executa apos cada teste
	public void paraServidor() {
		server.shutdown();
		System.out.println("Servidor parou");
	}

	@Test
	public void testaQueAConexaoComOServidorLocalFuncionaNoPathDeProjetos() {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8080"); // URI base de requisicoes
		String conteudo = target.path("/projetos/1").request().get(String.class);
		// System.out.println(conteudo);
		// Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		Projeto projeto = new Gson().fromJson(conteudo, Projeto.class);
		Assert.assertEquals("Minha loja", projeto.getNome());
	}

	@Test
	public void testaVerboPost() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("Sao Paulo");
		String xml = carrinho.toXML();
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		Response response = target.path("/carrinhos").request().post(entity);
		Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
	}

}

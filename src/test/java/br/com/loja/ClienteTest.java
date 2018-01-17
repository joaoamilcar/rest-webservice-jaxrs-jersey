package br.com.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

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
		System.out.println("Server parou");
	}
	
	@Test
	public void testaQueAConexaoComOServidorLocalFuncionaNoPathDeProjetos() {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8080"); // URI base de requisicoes
		String conteudo = target.path("/projetos/1").request().get(String.class);
		// System.out.println(conteudo);
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		Assert.assertEquals("Minha loja", projeto.getNome());
	}

}

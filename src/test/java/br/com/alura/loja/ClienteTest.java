package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

public class ClienteTest {
	
	private HttpServer server;
	
	@Before // run before each test
	public void startServer() {
		server = Servidor.initializeServer();
	}
	
	@After // run after each test
	public void killServer() {
		server.stop();
		System.out.println("Server stopped");
	}
	
	/*@Test
	public void testaQueAConexaoComOServidorLocalFuncionaNoPathDeProjetos() {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8080"); // URI base de requisicoes
		String conteudo = target.path("/projetos").request().get(String.class);
		// System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("<nome>Minha loja</nome>"));
	}*/
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8080"); // URI base de requisicoes
		String conteudo = target.path("/carrinhos").request().get(String.class);
		
		// System.out.println(conteudo);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}

}

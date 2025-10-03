package br.com.rhsc.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class PropertiesApplication {
	
	public static Properties loadProperties() throws IOException {
		
		try{
			FileInputStream fs = new FileInputStream("src/main/resources/application.properties");
			System.out.println(fs);
			Properties props = new Properties();
			props.load(fs);
			return props;
			
		} catch(Exception e) {
			throw new IOException("Erro ao ler o arquivo de configuração do banco: "+e.getMessage());
		}
		
	}
	
	public static void consumirApi(String cep) {
		Client client = ClientBuilder.newClient();
		
		String url = String.format("https://viacep.com.br/ws/%s/json", cep);
		
		System.out.println("url: "+url);
		
		WebTarget target = client.target(url);
		
		Response response = target
				.request(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Basic ...")
//				.post(null);
				.get();
		
		System.out.println(response.readEntity(String.class));
		
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			// faça algo
		}
	}
}

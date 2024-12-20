package br.com.coldigogeladeiras.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.coldigogeladeiras.bd.Conexao;
import br.com.coldigogeladeiras.jdbc.JDBCMarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;


@Path("marca")
public class MarcaRest extends UtilRest {
	
	@GET
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(){
		
		//System.out.println("passei aqui ");
		try {
			List<Marca> listaMarcas = new ArrayList<Marca>();
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			listaMarcas = jdbcMarca.buscar();
			
			//List<Marca> teste = listaMarcas;
		/*
			for(int i=0; i<listaMarcas.size(); i++) {
				
			
				System.out.println(listaMarcas.get(i));
			}
			*/
			/*
			for(Marca list : listaMarcas) {
				
				System.out.println(list);
			}
		*/
			/*
			for(Marca list : listaMarcas) {
				
				System.out.println(list.getId() + list.getNome());
			}
		
			*/
			//System.out.println(listaMarcas);
			conec.fecharConexao();
			return this.buildResponse(listaMarcas);
		}catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}

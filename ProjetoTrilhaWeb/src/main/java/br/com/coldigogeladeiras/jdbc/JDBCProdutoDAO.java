package br.com.coldigogeladeiras.jdbc;

//import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;


import com.google.gson.JsonObject;


import br.com.coldigogeladeiras.jdbcinterface.ProdutoDAO;
import br.com.coldigogeladeiras.modelo.Produto;

import java.sql.PreparedStatement;
//import java.sql.SQLException;


public class JDBCProdutoDAO implements ProdutoDAO {
	
	private Connection conexao;
	
	public JDBCProdutoDAO(Connection conexao) {
		this.conexao = conexao;
		
	}
	
	public boolean inserir(Produto produto) {
		
		String comando = "INSERT INTO produtos "
				+"(id, categoria, modelo, capacidade, valor, marcas_id)"
				+"VALUES (?,?,?,?,?,?)";
		
		PreparedStatement p;
		
		try {
			
			//Prepara o comando para execução no BD em que nos conecatamos
			
			p = this.conexao.prepareStatement(comando);
			
			//Substitui no comando os "?" pelos valores do produto
			
			p.setInt(1, produto.getId());
			p.setString(2, produto.getCategoria());
			p.setString(3, produto.getModelo());
			p.setInt(4, produto.getCapacidade());
			p.setFloat(5, produto.getValor());
			p.setInt(6, produto.getMarcaId());
			
			// Executa o comando no BD
			p.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public List<JsonObject> buscarPorNome(String nome){
		
		//System.out.println("Estou realmente aqui dentro !");
		//Inicia criação do comando SQL de busca
		String comando = "SELECT produtos.*, marcas.nome as marca FROM produtos "
				+ "INNER JOIN marcas ON produtos.marcas_id = marcas.id ";
		// Se o nome não estiver vazio..
		if(!nome.equals("")) {
			//concatena no comando o WHERE buscando no nome do produto
			//o texto da variavel nome 
			comando += " WHERE modelo LIKE '%" + nome + "%' ";
			
		}
		
		//Finaliza o comando ordenando alfabeticamente por categoria , marca e depois modelo.
		
		comando += " ORDER BY categoria ASC, marcas.nome ASC, modelo ASC";
		
		//System.out.println(comando);
		List<JsonObject> listaProdutos = new ArrayList<JsonObject>();
		JsonObject produto = null;
		
		try {
			
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				
				String categoria = rs.getString("categoria");
				
				String modelo = rs.getString("modelo");
				
				int capacidade = rs.getInt("capacidade");
				
				float valor = rs.getFloat("valor");
				
				String marcaNome = rs.getString("marca");
			/*
				
				
				System.out.println(id);
				System.out.println(categoria);
				System.out.println(modelo);
				System.out.println(capacidade);
				System.out.println(valor);
				System.out.println(marcaNome);
			*/
				if(categoria.equals("1")) {
					categoria = "Geladeira";
					
				}else if(categoria.equals("2")) {
					categoria = "Freezer";
				}
				
				produto = new JsonObject();
				produto.addProperty("id", id);
				produto.addProperty("modelo", modelo);
				produto.addProperty("categoria", categoria);
				produto.addProperty("capacidade", capacidade);
				produto.addProperty("valor", valor);
				produto.addProperty("marcaNome", marcaNome);
				
				listaProdutos.add(produto);
				
				//System.out.println(produto);
			}
			
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
		
		
		return listaProdutos;
	}
	
	public boolean deletar(int id) {
		
		String comando = "DELETE FROM produtos WHERE id = ?";
		PreparedStatement p;
		try {
			p= this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.execute();
			
		}catch (SQLException e ) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Produto buscarPorId(int id) {
		
		String comando = "SELECT * FROM produtos WHERE produtos.id = ?";
		Produto produto = new Produto();
		
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				int marcaId = rs.getInt("marcas_id");
				
				produto.setId(id);
				produto.setCategoria(categoria);
				produto.setMarcaId(marcaId);
				produto.setModelo(modelo);
				produto.setCapacidade(capacidade);
				produto.setValor(valor);
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return produto;
	}
	
	public boolean alterar(Produto produto) {
		
		String comando =  "UPDATE produtos "
				+ "SET categoria=?, modelo=?, capacidade=?, valor=?, marcas_id=?"
				+ "WHERE id=?";
		PreparedStatement p;
		
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, produto.getCategoria());
			p.setString(2, produto.getModelo());
			p.setInt(3, produto.getCapacidade());
			p.setFloat(4, produto.getValor());
			p.setInt(5, produto.getMarcaId());
			p.setInt(6, produto.getId());
			p.executeUpdate();
			
			System.out.println(p);
			
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

package br.ufc.quixada.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.entity.Funcionario;

public class FuncionarioJDBCDAO implements FuncionarioDAO {

	public FuncionarioJDBCDAO() { }
	
	public void save(Funcionario entity) {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String insert_sql = "insert into funcionario (cpf, matricula, nome, email, telefone) values (?, ?, ?, ?, ?)";
			String update_sql = "update funcionario set cpf = ?, matricula = ?, nome = ?, email = ?, telefone = ? where id = ?";
			PreparedStatement pst;

			if (entity.getId() == 0) {
				pst = con.prepareStatement(insert_sql);
			} else {
				pst = con.prepareStatement(update_sql);
				pst.setInt(6, entity.getId());
			}
			pst.setString(1, entity.getCpf());
			pst.setString(2, entity.getMatricula());
			pst.setString(3, entity.getNome());
			pst.setString(4, entity.getEmail());
			pst.setString(5, entity.getTelefone());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
	}

	public void delete(String cpf, String matricula) {
		Connection con = null;
		if(!cpf.equals("")){
			try {
				con = ConnectionFactory.getConnection();
				String sql = "delete from funcionario where cpf = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, cpf);
				pst.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("Operação não realizada com sucesso.", e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					throw new DAOException("Não foi possível fechar a conexão.",e);
				}
			}
		}else if(!matricula.equals("")){
			try {
				con = ConnectionFactory.getConnection();
				String sql = "delete from funcionario where matricula = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, matricula);
				pst.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("Operação não realizada com sucesso.", e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					throw new DAOException("Não foi possível fechar a conexão.",e);
				}
			}
		}
	}


	private Funcionario map(ResultSet rs) throws SQLException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(rs.getInt("id"));
		funcionario.setCpf(rs.getString("cpf"));
		funcionario.setMatricula(rs.getString("matricula"));
		funcionario.setNome(rs.getString("nome"));
		funcionario.setEmail(rs.getString("email"));
		funcionario.setTelefone(rs.getString("telefone"));
		return funcionario;
	}

	public List<Funcionario> listFuncionarios() {
		Connection con = null;
		List<Funcionario> result = null;
		try {
			con = ConnectionFactory.getConnection();
			PreparedStatement pst;
			String sql = "select id, cpf, matricula, nome, email, telefone from funcionario";
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			result = new ArrayList<Funcionario>();
			while (rs.next()) {
				Funcionario f = map(rs);
				result.add(f);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return result;
	}

	public Funcionario findFuncionario(String cpf, String matricula) {
		Connection con = null;
		Funcionario funcionario = null;
		if(!cpf.equals("")){
			try {
				con = ConnectionFactory.getConnection();
				PreparedStatement pst;
				String sql = "select id, cpf, matricula, nome, email, telefone from funcionario where upper(cpf) like ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, cpf);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					funcionario = map(rs);
				}
			} catch (SQLException e) {
				throw new DAOException("Operação não realizada com sucesso.", e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					throw new DAOException("Não foi possível fechar a conexão.",e);
				}
			}
		}else if(!matricula.equals("")){
			try {
				con = ConnectionFactory.getConnection();
				PreparedStatement pst;
				String sql = "select id, cpf, matricula, nome, email, telefone from funcionario where upper(matricula) like ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, matricula);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					funcionario = map(rs);
				}
			} catch (SQLException e) {
				throw new DAOException("Operação não realizada com sucesso.", e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					throw new DAOException("Não foi possível fechar a conexão.",e);
				}
			}
		}

		return funcionario;
	}


	public List<Funcionario> findList(String nome, String email) {
		Connection con = null;
		List<Funcionario> result = null;
		PreparedStatement pst;
		if(!nome.equals("")) {
			try {
				con = ConnectionFactory.getConnection();
				String sql = "select id, cpf, matricula, nome, email, telefone from funcionario where upper(nome) like ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, "%" + nome.toUpperCase() + "%");
				ResultSet rs = pst.executeQuery();
				result = new ArrayList<Funcionario>();
				while (rs.next()) {
					Funcionario funcionario = map(rs);
					result.add(funcionario);
				}
			} catch (SQLException e) {
				throw new DAOException("Operação não realizada com sucesso.", e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					throw new DAOException("Não foi possível fechar a conexão.", e);
				}
			}
		}else if(!email.equals("")) {
			try {
				con = ConnectionFactory.getConnection();
				String sql = "select id, cpf, matricula, nome, email, telefone from funcionario where upper(email) like ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, "%" + email.toUpperCase() + "%");
				ResultSet rs = pst.executeQuery();
				result = new ArrayList<Funcionario>();
				while (rs.next()) {
					Funcionario funcionario = map(rs);
					result.add(funcionario);
				}
			} catch (SQLException e) {
				throw new DAOException("Operação não realizada com sucesso.", e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					throw new DAOException("Não foi possível fechar a conexão.", e);
				}
			}
		}
		return result;
	}
	
}

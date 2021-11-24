package br.ufc.quixada.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.ufc.quixada.entity.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

//implementação que faz as consultas e alterações no banco

@Repository
@Primary
public class FuncionarioJDBCDAO implements FuncionarioDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public FuncionarioJDBCDAO() { }

	public void save(Funcionario entity) {
		String insert_sql = "insert into funcionario (cpf, matricula, nome, email, telefone) values (:cpf, :matricula, :nome, :email, :telefone)";
		String update_sql = "update funcionario set cpf = :cpf, matricula = :matricula, nome = :nome, email = :email, telefone = :telefone where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("cpf",  entity.getCpf())
				.addValue("matricula",  entity.getMatricula())
				.addValue("nome", entity.getNome())
				.addValue("email", entity.getEmail())
				.addValue("telefone", entity.getTelefone());
		if (entity.getId() == 0) {
			jdbcTemplate.update(insert_sql, params);
		} else {
			params.addValue("id", entity.getId());
			jdbcTemplate.update(update_sql, params);
		}

	}

	public void delete(String cpf, String matricula) {
		String sql = "";
		MapSqlParameterSource params = null;

		if(!cpf.equals("")){
			sql = "delete from funcionario where cpf = :cpf";
			params = new MapSqlParameterSource()
					.addValue("cpf",  cpf);
		}else if(!matricula.equals("")){
			sql = "delete from funcionario where matricula = :matricula";
			params = new MapSqlParameterSource()
					.addValue("matricula",  matricula);
		}
		jdbcTemplate.update(sql, params);

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
		String sql = "select id, cpf, matricula, nome, email, telefone from funcionario";
		return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs));
	}

	public Funcionario findFuncionario(String cpf, String matricula) {
		String sql = "";
		SqlParameterSource namedParameters = null;
		if(!cpf.equals("")){
			sql = "select id, cpf, matricula, nome, email, telefone from funcionario where cpf = :cpf_";
			namedParameters = new MapSqlParameterSource().addValue("cpf_", cpf);

		}else if(!matricula.equals("")) {
			sql = "select id, cpf, matricula, nome, email, telefone from funcionario where matricula = :matricula_";
			namedParameters = new MapSqlParameterSource().addValue("matricula_", matricula);
		}

		List<Funcionario> result = jdbcTemplate.query(sql, namedParameters, (rs, rowNum) -> map(rs));
		return result.isEmpty() ? null : result.get(0);
	}


	public List<Funcionario> findList(String nome, String email) {
		String sql = "";
		SqlParameterSource namedParameters = null;
		if(!nome.equals("")) {
			sql = "select id, cpf, matricula, nome, email, telefone from funcionario where upper(nome) like :nome_";
			namedParameters = new MapSqlParameterSource().addValue("nome_", "%" + nome.toUpperCase() + "%");

		}else if(!email.equals("")) {
			sql = "select id, cpf, matricula, nome, email, telefone from funcionario where upper(email) like :email_";
			namedParameters = new MapSqlParameterSource().addValue("email_", "%" + email.toUpperCase() + "%");

		}
		return jdbcTemplate.query(sql, namedParameters, (rs, rowNum) -> map(rs));
	}
	
}

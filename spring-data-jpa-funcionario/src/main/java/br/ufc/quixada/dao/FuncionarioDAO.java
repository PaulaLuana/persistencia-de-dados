package br.ufc.quixada.dao;

import br.ufc.quixada.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer> {

	public Funcionario findFirstByCpf(String cpf);

	@Query(name = "findMatricula")
	public Funcionario findMatricula(String matricula);

	@Query("select f from Funcionario f where f.nome like :nome%")
	public List<Funcionario> findName(String nome);

	@Query("select f from Funcionario f where f.email like :email")
	public Funcionario findEmail(String email);


}

package br.ufc.quixada.dao;

import br.ufc.quixada.entity.Aluno;
import br.ufc.quixada.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlunoDAO extends JpaRepository<Aluno, Integer> {

	@Query("select a from Aluno a where a.cpf = :cpf")
	public Aluno findCpf(String cpf);

	@Query("select a from Aluno a")
	public List<Aluno> findAll();

	//Consulta nomeada
	@Query(name = "findMatricula")
	public Aluno findMatricula(String matricula);

	@Query(name = "findEmail")
	public Aluno findEmail(String email);

	@Query("select a from Aluno a where a.nome like :nome%")
	public List<Aluno> findName(String nome);

	@Query("select d.alunos from Disciplina d where d.id = :id")
	public List<Aluno> cursantes(int id);

	@Query("select aluno from Aluno aluno where aluno.data_nasc > :data_nasc")
	public List<Aluno> comparaData(String data_nasc);



}

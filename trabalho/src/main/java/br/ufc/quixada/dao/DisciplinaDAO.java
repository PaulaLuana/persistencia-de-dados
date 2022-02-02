package br.ufc.quixada.dao;

import br.ufc.quixada.entity.Aluno;
import br.ufc.quixada.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Integer> {

    @Query("select d from Disciplina d where d.codigo = :codigo")
    public Disciplina findCodigo(String codigo);

//    @Query("select d from Disciplina d join fetch d.alunos where d.id = :id")
//    public List<Disciplina> disciplinasCursadas(int id);

    //Consulta nomeada
    @Query(name = "listDisciplinas")
    public List<Disciplina> listDisciplinas();

    //@Query("select p from Disciplina p where p.id in (select d.disciplina_id from Disciplina_alunos d where d.alunos_id = :id)")
    @Query("select a.disciplinas from Aluno a where a.id = :id")
    public List<Disciplina> disciplinasCursadas(int id);


    //@Query("select d from Disciplina d join fetch d.alunos where d.id = :id")
    //public List<Disciplina> disciplinasCursadas(int id);


}

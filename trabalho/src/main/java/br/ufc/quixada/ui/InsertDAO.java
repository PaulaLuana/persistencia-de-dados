package br.ufc.quixada.ui;

import br.ufc.quixada.dao.AlunoDAO;
import br.ufc.quixada.dao.DisciplinaDAO;
import br.ufc.quixada.entity.Aluno;
import br.ufc.quixada.entity.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/*3. Crie uma classe para adicionar via DAO pelo menos 6 alunos
com suas respectivas disciplinas cursadas no banco de dados.
Pelo menos 50% dos alunos devem ter disciplinas cursadas.
 */

@ComponentScan("br.ufc.quixada")
public class InsertDAO implements CommandLineRunner {
    @Autowired
    private DisciplinaDAO disciplinaDAO;
    @Autowired
    private AlunoDAO alunoDAO;

    public void run(String[] args) {
        List<Aluno> alunos = new ArrayList<>();
        List<Disciplina> disciplinas = new ArrayList<>();

        Aluno aluno1 = new Aluno();
        aluno1.setCpf("000");
        aluno1.setNome("Paula");
        aluno1.setMatricula("3389");
        aluno1.setEmail("paula@gmail.com");
        aluno1.setData_nasc(aluno1.formataData("29/08/1999"));
        alunoDAO.save(aluno1);
        alunos.add(aluno1);

        Aluno aluno2 = new Aluno();
        aluno2.setCpf("001");
        aluno2.setNome("Paulo");
        aluno2.setMatricula("3380");
        aluno2.setEmail("paulo@gmail.com");
        aluno2.setData_nasc(aluno2.formataData("13/07/1998"));
        alunoDAO.save(aluno2);
        alunos.add(aluno2);


        Aluno aluno3 = new Aluno();
        aluno3.setCpf("002");
        aluno3.setNome("Maria");
        aluno3.setMatricula("4389");
        aluno3.setEmail("mariazinha@gmail.com");
        aluno3.setData_nasc(aluno3.formataData("02/02/1996"));
        alunoDAO.save(aluno3);
        alunos.add(aluno3);

        Aluno aluno4 = new Aluno();
        aluno4.setCpf("003");
        aluno4.setNome("Jose");
        aluno4.setMatricula("3989");
        aluno4.setEmail("ze@gmail.com");
        aluno4.setData_nasc(aluno4.formataData("30/03/1997"));
        alunoDAO.save(aluno4);
        alunos.add(aluno4);


        Aluno aluno5 = new Aluno();
        aluno5.setCpf("6783");
        aluno5.setNome("Fernanda");
        aluno5.setMatricula("8539");
        aluno5.setEmail("nandinha@gmail.com");
        aluno5.setData_nasc(aluno5.formataData("01/01/2001"));
        alunoDAO.save(aluno5);
        alunos.add(aluno5);


        Aluno aluno6 = new Aluno();
        aluno6.setCpf("89410");
        aluno6.setNome("Carlos");
        aluno6.setMatricula("78400");
        aluno6.setEmail("carlim@gmail.com");
        aluno6.setData_nasc(aluno6.formataData("18/05/1996"));
        alunoDAO.save(aluno6);
        alunos.add(aluno6);


        Aluno aluno7 = new Aluno();
        aluno7.setCpf("9654");
        aluno7.setNome("Jasmine");
        aluno7.setMatricula("446339");
        aluno7.setEmail("jasmin@gmail.com");
        aluno7.setData_nasc(aluno7.formataData("20/05/1996"));
        alunoDAO.save(aluno7);
        alunos.add(aluno7);

        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo("QXD00");
        disciplina.setNome("Fundamentos de programação");
        disciplina.setAlunos(alunos);
        disciplinas.add(disciplina);

        Disciplina disciplina1 = new Disciplina();
        disciplina1.setCodigo("QXD01");
        disciplina1.setNome("Arquitetura de Computadores");
        disciplina1.setAlunos(alunos);
        disciplinas.add(disciplina1);


        Disciplina disciplina2 = new Disciplina();
        disciplina2.setCodigo("QXD03");
        disciplina2.setNome("Lógica");
        disciplina2.setAlunos(alunos);
        disciplinas.add(disciplina2);

        disciplinaDAO.save(disciplina);
        disciplinaDAO.save(disciplina1);
        disciplinaDAO.save(disciplina2);


        aluno1.setDisciplinas(disciplinas);
        aluno2.setDisciplinas(disciplinas);
        aluno3.setDisciplinas(disciplinas);
        aluno4.setDisciplinas(disciplinas);
        aluno5.setDisciplinas(disciplinas);
        aluno6.setDisciplinas(disciplinas);
        aluno7.setDisciplinas(disciplinas);


        alunoDAO.save(aluno1);
        alunoDAO.save(aluno2);
        alunoDAO.save(aluno3);
        alunoDAO.save(aluno4);
        alunoDAO.save(aluno5);
        alunoDAO.save(aluno6);
        alunoDAO.save(aluno7);




    }
}

package br.ufc.quixada.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufc.quixada.dao.AlunoDAO;
import br.ufc.quixada.dao.DisciplinaDAO;
import br.ufc.quixada.entity.Aluno;
import br.ufc.quixada.entity.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("br.ufc.quixada")
@EntityScan("br.ufc.quixada.entity")
@EnableJpaRepositories("br.ufc.quixada.dao")
public class Crud implements CommandLineRunner {
    @Autowired
    private DisciplinaDAO disciplinaDAO;
    @Autowired
    private AlunoDAO alunoDAO;

    public static void main(String[] args) {
        //SpringApplication.run(Principal.class, args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Crud.class);
        builder.headless(false).run(args);
    }

    public static char menuPrincipal(){
        String menu = "Escolha uma opção:\n" +
                "1 - Inserir\n" +
                "2 - Deletar\n" +
                "3 - Alterar\n" +
                "4 - Consultar\n" +
                "5 - Sair";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }

    public static char menuInserir(){
        String menu = "Escolha uma opção:\n" +
                "1 - Inserir aluno\n" +
                "2 - Inserir disciplina\n" +
                "3 - Adicionar disciplinas já cadastradas a um aluno já cadastrado\n" +
                "4 - Voltar";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }
    public static char menuDeletar(){
        String menu = "Escolha uma opção:\n" +
                "1 - Deletar aluno por matrícula\n" +
                "2 - Deletar disciplina por código\n" +
                "3 - Voltar";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }

    public static char menuAlterar(){
        String menu = "Escolha uma opção:\n" +
                "1 - Alterar aluno\n" +
                "2 - Alterar disciplina\n" +
                "3 - Voltar";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }

    public static char menuConsulta(){
        String menu = "Escolha uma opção de consulta:\n" +
                "1 - Consultar alunos\n" +
                "2 - Consultar disciplinas\n" +
                "3 - Consultar por disciplinas cursadas por (nome de aluno)\n" +
                "4 - Consultar alunos por (código da disciplina) \n" +
                "5 - Consultar a quantidade de disciplinas cursadas por (todos os alunos)\n" +
                "6 - Consultar nome e email do aluno pela (matricula).\n" +
                "7 - Dada uma data, mostrar somente os alunos que nasceram depois dela.\n"+
                "8 - Voltar";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }

    public static void obterAluno(AlunoDAO database, Aluno aluno) {
        String cpf = JOptionPane.showInputDialog("CPF", aluno.getCpf());
        if(!cpf.equals(aluno.getCpf())){//se o cpf que o funcionario inseriu foi diferente
            if(database.findCpf(cpf) != null){//verifica se o novo cpf existe no sistema
                JOptionPane.showMessageDialog(null, "Erro: cpf já existe");
                return;
            }
        }
        String matricula = JOptionPane.showInputDialog("Matricula", aluno.getMatricula());
        if(!matricula.equals(aluno.getMatricula())){//se a matrícula que o funcionario inseriu foi diferente
            if(database.findMatricula(matricula) != null){//verifica se a nova matrícula existe no sistema
                JOptionPane.showMessageDialog(null, "Erro: matrícula já existe");
                return;
            }
        }
        String nome = JOptionPane.showInputDialog("Nome", aluno.getNome());
        String email = JOptionPane.showInputDialog("Email", aluno.getEmail());
        if(!email.equals(aluno.getEmail())){//se a matrícula que o funcionario inseriu foi diferente
            if(database.findEmail(email) != null){//verifica se o novo email existe no sistema
                JOptionPane.showMessageDialog(null, "Erro: email já existe");
                return;
            }
        }
        String data_nasc = JOptionPane.showInputDialog("Data de nascimento", aluno.getData_nasc());
        aluno.setCpf(cpf);
        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setData_nasc(aluno.formataData(data_nasc));
        database.save(aluno);
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso.");
    }

    public static void obterDisciplina(DisciplinaDAO database, Disciplina disciplina) {
        String codigo = JOptionPane.showInputDialog("Código da disciplina", disciplina.getCodigo());
        if(!codigo.equals(disciplina.getCodigo())){
            if(database.findCodigo(codigo) != null){
                JOptionPane.showMessageDialog(null, "Erro: código já existe");
                return;
            }
        }
        String nome = JOptionPane.showInputDialog("Nome da disciplina", disciplina.getNome());
        disciplina.setCodigo(codigo);
        disciplina.setNome(nome);
        database.save(disciplina);
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso.");
    }


    public static void listaAlunos(List<Aluno> alunos) {
        StringBuilder listagem = new StringBuilder();
        for(Aluno a : alunos) {
            listagem.append(a).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum aluno encontrado" : listagem);
    }

    public static void listaDisciplinas(List<Disciplina> disciplinas) {
        StringBuilder listagem = new StringBuilder();
        for(Disciplina d : disciplinas) {
            listagem.append(d).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma disciplina encontrada" : listagem);
    }

    /*public static void listaFuncionario(Funcionario f) {
        JOptionPane.showMessageDialog(null, f == null ? "Nenhum funcionário encontrado" : f);
    }*/

    public Date formataData(String data){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data_ = null;
        try {
            data_ = formato.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data_;
    }

    @Override
    public void run(String... args) throws Exception {
        char opcao = '0', opcaoInserir, opcaoDeletar, opcaoAlterar, opcaoConsulta;
        String campo;
        Aluno aluno;
        Disciplina disciplina;
        List<Aluno> alunos;
        List<Disciplina> disciplinas;

        while (opcao != '5'){
            opcao = menuPrincipal();
            switch (opcao){
                case '1'://Inserir
                    opcaoInserir = menuInserir();
                    if(opcaoInserir == '1'){//inserir aluno
                        aluno = new Aluno();
                        obterAluno(alunoDAO, aluno);
                    }else if(opcaoInserir == '2'){//inserir disciplina
                        disciplina = new Disciplina();
                        obterDisciplina(disciplinaDAO, disciplina);
                    }else if(opcaoInserir == '3'){ //Adicionar disciplinas já cadastradas a um aluno já cadastrado
                        campo = JOptionPane.showInputDialog("Matrícula do aluno");
                        aluno = alunoDAO.findMatricula(campo);
                        campo = JOptionPane.showInputDialog("Código da disciplina");
                        disciplina = disciplinaDAO.findCodigo(campo);
                        if(aluno != null && disciplina != null){
                            if(alunoDAO.findMatricula(aluno.getMatricula()).getDisciplinas().contains(disciplina)){
                                JOptionPane.showMessageDialog(null, "Aluno já cursa essa disciplina!");
                            }else{
                                disciplinas = aluno.getDisciplinas();
                                disciplinas.add(disciplina);
                               // aluno.setDisciplinas(disciplinas);
                                alunoDAO.save(aluno);
                                JOptionPane.showMessageDialog(null, "Disciplina adicionada");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Os campos estão incorretos!");
                        }
                    }else if(opcaoInserir == '4'){
                        //voltar
                    }else{
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }
                    break;
                case '2'://Deletar
                    opcaoDeletar = menuDeletar();
                    if(opcaoDeletar == '1'){
                        //Deleta aluno por matrícula
                        campo = JOptionPane.showInputDialog("Matrícula");
                        aluno = alunoDAO.findMatricula(campo);
                        if (aluno != null) {
                            alunoDAO.delete(aluno);
                            JOptionPane.showMessageDialog(null, "Aluno deletado do sistema.");

                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao deletar: a matrícula inserida não existe no sistema");
                        }
                    }else if(opcaoDeletar == '2'){
                        //Deleta disciplina por código
                        campo = JOptionPane.showInputDialog("Código");
                        disciplina = disciplinaDAO.findCodigo(campo);
                        if (disciplina != null) {
                            disciplinaDAO.delete(disciplina);
                            JOptionPane.showMessageDialog(null, "Disciplina deletada do sistema.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao deletar: a disciplina inserida não existe no sistema");
                        }

                    }else if(opcaoDeletar == '3'){
                        //voltar
                    }else{
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }
                    break;
                case '3'://Alterar
                    opcaoAlterar = menuAlterar();
                    if(opcaoAlterar == '1'){
                        //Altera por matricula
                        campo = JOptionPane.showInputDialog("Digite a matrícula do aluno a ser alterado");
                        aluno = alunoDAO.findMatricula(campo);
                        if(aluno == null){
                            JOptionPane.showMessageDialog(null, "Matrícula não encontrada no sistema.");
                        }else{
                            obterAluno(alunoDAO, aluno);
                        }
                    }else if(opcaoAlterar == '2'){
                        //Altera por código
                        campo = JOptionPane.showInputDialog("Digite o código da disciplina a ser alterada");
                        disciplina = disciplinaDAO.findCodigo(campo);
                        if(disciplina == null){
                            JOptionPane.showMessageDialog(null, "Código não encontrado no sistema.");
                        }else{
                            obterDisciplina(disciplinaDAO, disciplina);
                        }
                    }else if(opcaoAlterar == '3'){
                        //voltar
                    }else{
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }
                    break;
                case '4'://Consulta
                    opcaoConsulta = menuConsulta();
                    if(opcaoConsulta == '1'){
                        //Mostra todos os alunos
                        listaAlunos(alunoDAO.findAll());

                    }else if(opcaoConsulta == '2'){
                        //Mostra todos as disciplinas
                        listaDisciplinas(disciplinaDAO.findAll());

                    }else if(opcaoConsulta == '3'){
                        //3 - Consultar por disciplinas cursadas por (nome de aluno)
                        campo = JOptionPane.showInputDialog("Nome");
                        alunos = alunoDAO.findName(campo);
                        if(alunos != null){
                            for(Aluno a : alunos){
                                JOptionPane.showMessageDialog(null, "Aluno(a) " + a.getNome() + " cursa as seguintes disciplinas\n"
                                        + disciplinaDAO.disciplinasCursadas(a.getId()));
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Esse nome não existe no sistema");

                        }
                    }else if(opcaoConsulta == '4'){
                        // 4 - Consultar alunos por (código da disciplina)
                        campo = JOptionPane.showInputDialog("Código da disciplina");
                        disciplina = disciplinaDAO.findCodigo(campo);
                        if(disciplina != null){
                            JOptionPane.showMessageDialog(null, alunoDAO.cursantes(disciplina.getId()));
                        }else{
                            JOptionPane.showMessageDialog(null, "Essa disciplina não existe no sistema");

                        }

                    }else if(opcaoConsulta == '5'){
                        // 5 - Consultar a quantidade de disciplinas cursadas por (todos os alunos)
                        String text = "";
                        for(Aluno a : alunoDAO.findAll()){
                            text += "Aluno(a) " + a.getNome() + " cursa " + a.getDisciplinas().size() + " disciplinas.\n";
                        }

                        JOptionPane.showMessageDialog(null, text);

                    }else if(opcaoConsulta == '6'){
                        // 6 - Consultar nome e email do aluno pela (matricula).
                        campo = JOptionPane.showInputDialog("Matrícula");
                        aluno = alunoDAO.findMatricula(campo);
                        if(aluno != null){
                            JOptionPane.showMessageDialog(null, "Nome: " + aluno.getNome() + ", email: " + aluno.getEmail());
                        }else{
                            JOptionPane.showMessageDialog(null, "Matrícula não existe no sistema");
                        }
                    }else if(opcaoConsulta == '7'){ //Dada uma data, mostrar somente os alunos que nasceram depois dela.
                        campo = JOptionPane.showInputDialog("Data");
                        JOptionPane.showMessageDialog(null, alunoDAO.comparaData(campo).toString());
                    }else if(opcaoConsulta == '8'){
                        //volta
                    }else{
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }
                    break;
                case '5':     // Sair
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida");
                    break;
            }
        }


    }

}

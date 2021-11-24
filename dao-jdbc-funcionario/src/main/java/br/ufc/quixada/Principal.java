package br.ufc.quixada;

import br.ufc.quixada.dao.FuncionarioDAO;
import br.ufc.quixada.dao.FuncionarioJDBCDAO;
import br.ufc.quixada.entity.Funcionario;

import javax.swing.*;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        char opcao = '0', opcaoDeletar, opcaoAlterar, opcaoConsulta;
        String campo;
        Funcionario f;
        List<Funcionario> funcionarios;
        FuncionarioDAO databaseFunc = new FuncionarioJDBCDAO();

        while (opcao != '5'){
            opcao = menuPrincipal();
            switch (opcao){
                case '1'://Inserir
                    f = new Funcionario();
                    obterFuncionario(databaseFunc, f);
                    break;
                case '2'://Deletar
                    opcaoDeletar = menuDeletar();
                    if(opcaoDeletar == '1'){
                        //Deleta por cpf
                        campo = JOptionPane.showInputDialog("CPF");
                        f = databaseFunc.findFuncionario(campo, "");
                        if (f != null) {
                            databaseFunc.delete(f.getCpf(),"");
                            JOptionPane.showMessageDialog(null, "Funcionário deletado do sistema.");

                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao deletar: o cpf inserido não existe no sistema");
                        }
                    }else if(opcaoDeletar == '2'){
                    //Deleta por matricula
                        campo = JOptionPane.showInputDialog("Matrícula");
                        f = databaseFunc.findFuncionario("", campo);
                        if (f != null) {
                            databaseFunc.delete("", f.getMatricula());
                            JOptionPane.showMessageDialog(null, "Funcionário deletado do sistema.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao deletar: a matrícula inserida não existe no sistema");
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
                        //Altera por cpf
                        campo = JOptionPane.showInputDialog("Digite o CPF do funcionário a ser alterado");
                        f = databaseFunc.findFuncionario(campo, "");
                        if(f == null){
                            JOptionPane.showMessageDialog(null, "CPF não encontrado no sistema.");
                        }else{
                            obterFuncionario(databaseFunc, f);
                        }
                    }else if(opcaoAlterar == '2'){
                        //Altera por matricula
                        campo = JOptionPane.showInputDialog("Digite a matrícula do funcionário a ser alterado");
                        f = databaseFunc.findFuncionario("", campo);
                        if(f == null){
                            JOptionPane.showMessageDialog(null, "Matrícula não encontrada no sistema.");
                        }else{
                            obterFuncionario(databaseFunc, f);
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
                        //Mostra todos os funcionários
                        listaFuncionarios(databaseFunc.listFuncionarios());

                    }else if(opcaoConsulta == '2'){
                        //Consulta por cpf
                        campo = JOptionPane.showInputDialog("CPF");
                        f = databaseFunc.findFuncionario(campo,"");
                        listaFuncionario(f);
                    }else if(opcaoConsulta == '3'){
                        //Consulta por matricula
                        campo = JOptionPane.showInputDialog("Matrícula");
                        f = databaseFunc.findFuncionario("", campo);
                        listaFuncionario(f);
                    }else if(opcaoConsulta == '4'){
                        //Consulta por nome
                        campo = JOptionPane.showInputDialog("Nome");
                        funcionarios = databaseFunc.findList(campo, "");
                        listaFuncionarios(funcionarios);
                    }else if(opcaoConsulta == '5'){
                        //voltar
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

    public static char menuPrincipal(){
        String menu = "Escolha uma opção:\n" +
                "1 - Inserir\n" +
                "2 - Deletar\n" +
                "3 - Alterar\n" +
                "4 - Consultar funcionários\n" +
                "5 - Sair";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }

    public static char menuDeletar(){
        String menu = "Escolha uma opção:\n" +
                "1 - Deletar por cpf\n" +
                "2 - Deletar por matrícula\n" +
                "3 - Voltar";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }

    public static char menuAlterar(){
        String menu = "Escolha uma opção:\n" +
                "1 - Alterar por cpf\n" +
                "2 - Alterar por matrícula\n" +
                "3 - Voltar";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }

    public static char menuConsulta(){
        String menu = "Escolha uma opção de consulta:\n" +
                "1 - Listar funcionários\n" +
                "2 - Consultar por cpf\n" +
                "3 - Consultar por matrícula\n" +
                "4 - Consultar por nome\n" +
                "5 - Voltar";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }

    public static void obterFuncionario(FuncionarioDAO database, Funcionario funcionario) {
        String cpf = JOptionPane.showInputDialog("CPF", funcionario.getCpf());
        if(!cpf.equals(funcionario.getCpf())){//se o cpf que o funcionario inseriu foi diferente
            if(database.findFuncionario(cpf, "") != null){//verifica se o novo cpf existe no sistema
                JOptionPane.showMessageDialog(null, "Erro: cpf já existe");
                return;
            }
        }
        String matricula = JOptionPane.showInputDialog("Matricula", funcionario.getMatricula());
        if(!matricula.equals(funcionario.getMatricula())){//se a matrícula que o funcionario inseriu foi diferente
            if(database.findFuncionario("", matricula) != null){//verifica se a nova matrícula existe no sistema
                JOptionPane.showMessageDialog(null, "Erro: matrícula já existe");
                return;
            }
        }
        String nome = JOptionPane.showInputDialog("Nome", funcionario.getNome());
        String email = JOptionPane.showInputDialog("Email", funcionario.getEmail());
        String telefone = JOptionPane.showInputDialog("Telefone", funcionario.getTelefone());
        funcionario.setCpf(cpf);
        funcionario.setMatricula(matricula);
        funcionario.setNome(nome);
        funcionario.setEmail(email);
        funcionario.setTelefone(telefone);
        database.save(funcionario);
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso.");
    }


    public static void listaFuncionarios(List<Funcionario> funcionarios) {
        StringBuilder listagem = new StringBuilder();
        for(Funcionario f : funcionarios) {
            listagem.append(f).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum funcionário encontrado" : listagem);
    }

    public static void listaFuncionario(Funcionario f) {
        JOptionPane.showMessageDialog(null, f == null ? "Nenhum funcionário encontrado" : f);
    }

}

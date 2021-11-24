package br.ufc.quixada.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.entity.Funcionario;

public class FuncionarioListDAO implements FuncionarioDAO {

	private List<Funcionario> funcionarios;
	
	private static int idProximo = 1;
	
	
	public FuncionarioListDAO() {
		this.funcionarios = new ArrayList<Funcionario>();
	}
	
	public void save(Funcionario entity) {
		// Inserir um cliente se o id do objeto for 0.
		if (entity.getId() == 0) {
			entity.setId(idProximo++);
			funcionarios.add(entity);
		// Alterar um cliente se o id não for 0.
		} else {
			int posicaoNaLista = findIndex(entity.getId());
			funcionarios.set(posicaoNaLista, entity);
		}
	}

	public void delete(String cpf, String matricula) {
		if(!cpf.equals("")){
			funcionarios.remove(findFuncionario(cpf, ""));
		}else if(!matricula.equals("")){
			funcionarios.remove(findFuncionario("",matricula));
		}
	}


	private int findIndex(int id) {
		for (int i=0; i < funcionarios.size(); i++) {
			if (funcionarios.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public List<Funcionario> listFuncionarios() {
		return this.funcionarios;
	}

	public Funcionario findFuncionario(String cpf, String matricula) {
		if(!cpf.equals("")){
			for (Funcionario f : this.funcionarios) {
				if (f.getCpf().equals(cpf)) {
					return f;
				}
			}
		}else if(!matricula.equals("")){
			for (Funcionario f : this.funcionarios) {
				if (f.getMatricula().equals(matricula)) {
					return f;
				}
			}
		}

		return null;
	}


	public List<Funcionario> findList(String nome, String email) {
		List<Funcionario> resultado = new ArrayList<Funcionario>();
		if(!nome.equals("")){
			for (Funcionario cl : this.funcionarios) {
				if (cl.getNome().toUpperCase().contains(nome.toUpperCase())) {
					resultado.add(cl);
				}
			}
		}else if(!email.equals("")){
			for (Funcionario cl : this.funcionarios) {
				if (cl.getNome().toUpperCase().contains(email.toUpperCase())) {
					resultado.add(cl);
				}
			}
		}

		return resultado;
	}
}

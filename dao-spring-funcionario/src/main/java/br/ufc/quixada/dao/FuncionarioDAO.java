package br.ufc.quixada.dao;

import java.util.List;

import br.ufc.quixada.entity.Funcionario;

public interface FuncionarioDAO {
	
	public void save(Funcionario entity);
	
	public void delete(String cpf, String matricula);

	public List<Funcionario> listFuncionarios();
	
	public Funcionario findFuncionario(String cpf, String matricula);
	
	public List<Funcionario> findList(String nome, String email);
}

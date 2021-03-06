package br.ufc.quixada.entity;

public class Funcionario {
	private int id;
	private String cpf;
	private String matricula;
	private String nome;
	private String email;
	private String telefone;

	public Funcionario() {}

	public Funcionario(int id, String cpf, String matricula, String nome, String email, String telefone) {
		this.id = id;
		this.cpf = cpf;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Funcionário(a) " + id + ": " + nome + ", cpf=" + cpf + ", matricula=" + matricula
				+ ", email=" + email + ", telefone=" + telefone;

	}
}

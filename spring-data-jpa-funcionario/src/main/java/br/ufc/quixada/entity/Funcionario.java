package br.ufc.quixada.entity;

import lombok.*;

import javax.persistence.*;

@NamedQueries({
		@NamedQuery(name = "findMatricula", query = "select f from Funcionario f where f.matricula = :matricula")
})

@Entity
@Table(name = "funcionario")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull @Getter @Setter private int id;

	@NonNull @Getter @Setter private String cpf;
	@NonNull @Getter @Setter private String matricula;
	@NonNull @Getter @Setter private String nome;
	@NonNull @Getter @Setter private String email;
	@Getter @Setter private String telefone;
}

package br.ufc.quixada.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
		@NamedQuery(name = "listDisciplinas", query = "select d from Disciplina d")
})

@Entity
@Table(name = "disciplina")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "alunos")
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@ManyToMany(fetch = FetchType.EAGER)
	@Getter @Setter private List<Aluno> alunos;

	@NonNull @Getter @Setter private String codigo;

	@NonNull @Getter @Setter private String nome;



}

package br.ufc.quixada.entity;

import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@NamedQueries({
		@NamedQuery(name = "findMatricula", query = "select a from Aluno a where a.matricula = :matricula"),
		@NamedQuery(name = "findEmail", query = "select a from Aluno a where a.email = :email")
})

@Entity
@Table(name = "aluno")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "disciplinas")
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@NonNull @Getter @Setter private String cpf;
	@NonNull @Getter @Setter private String matricula;
	@Getter @Setter private String nome;
	@NonNull @Getter @Setter private String email;
	@NonNull @Getter @Setter private Date data_nasc;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "DISCIPLINA_ALUNOS", joinColumns = {@JoinColumn(name = "ALUNOS_ID")},
	inverseJoinColumns = {@JoinColumn(name = "DISCIPLINA_ID")})
	@Getter @Setter private List<Disciplina> disciplinas;

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
}

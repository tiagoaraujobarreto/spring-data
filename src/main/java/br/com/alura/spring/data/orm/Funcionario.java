package br.com.alura.spring.data.orm;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private Float salario;
	private LocalDate dataDeContratacao = LocalDate.now();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public LocalDate getDataDeContratacao() {
		return dataDeContratacao;
	}

	public void setDataDeContratacao(LocalDate dataDeContratacao) {
		this.dataDeContratacao = dataDeContratacao;
	}

	@Override
	public String toString() {
		return "Funcionario [id= " + id + ", nome= " + nome + ", cpf = " + cpf + ", salario = " + salario
				+ ", dataDeContratacao = " + dataDeContratacao + "]";
	}

}

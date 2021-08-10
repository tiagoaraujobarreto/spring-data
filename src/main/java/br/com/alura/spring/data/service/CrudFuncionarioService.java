package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private final FuncionarioRepository funcionarioRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual ação você deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner scanner) {
		System.out.println("Nome do funcionario");
		String fNome = scanner.next();
		System.out.println("Insira o CPF");
		String fCpf = scanner.next();
		System.out.println("Salário bruto");
		Float fSalario = scanner.nextFloat();

		Funcionario funcionario = new Funcionario();

		funcionario.setNome(fNome);
		funcionario.setCpf(fCpf);
		funcionario.setSalario(fSalario);

		funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Informe o id");
		int id = scanner.nextInt();
		System.out.println("Nome do funcionario");
		String fNome = scanner.next();
		System.out.println("Insira o CPF");
		String fCpf = scanner.next();
		System.out.println("Salário bruto");
		Float fSalario = scanner.nextFloat();

		Funcionario funcionario = new Funcionario();

		funcionario.setId(id);
		funcionario.setNome(fNome);
		funcionario.setCpf(fCpf);
		funcionario.setSalario(fSalario);
		funcionarioRepository.save(funcionario);

		System.out.println("Atualizado");
	}

	private void visualizar() {
		Iterable<Funcionario> cargos = funcionarioRepository.findAll();
		cargos.forEach(funcionario -> System.out.println(funcionario));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Informe o id");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Registro deletado");
	}

}

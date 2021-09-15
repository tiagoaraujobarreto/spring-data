package br.com.alura.spring.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;

	private final CargoRepository cargoRepository;
	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
	private final FuncionarioRepository funcionarioRepository;

	public CrudFuncionarioService(CargoRepository cargoRepository,
			UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository, FuncionarioRepository funcionarioRepository) {
		this.cargoRepository = cargoRepository;
		this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
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
				visualizar(scanner);
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
		String nome = scanner.next();

		System.out.println("Insira o CPF");
		String cpf = scanner.next();

		System.out.println("Salário bruto");
		Double salario = scanner.nextDouble();

		System.out.println("Informe o ID do cargo");
		Integer cargoID = scanner.nextInt();

		List<UnidadeDeTrabalho> unidades = unidade(scanner);

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		Optional<Cargo> cargo = cargoRepository.findById(cargoID);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeDeTrabalhos(unidades);

		funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}

	private List<UnidadeDeTrabalho> unidade(Scanner scanner) {
		Boolean isTrue = true;
		List<UnidadeDeTrabalho> unidades = new ArrayList<>();

		while (isTrue) {
			System.out.println("Insira o ID da unidade, 'Para sair digite 0'");
			Integer unidadeId = scanner.nextInt();

			if (unidadeId != 0) {
				Optional<UnidadeDeTrabalho> unidade = unidadeDeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}
		return unidades;
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Informe o id do funcionário");
		int id = scanner.nextInt();

		System.out.println("Nome do funcionario");
		String nome = scanner.next();

		System.out.println("Insira o CPF");
		String cpf = scanner.next();

		System.out.println("Salário bruto");
		Double salario = scanner.nextDouble();

		System.out.println("Insarira o ID do cargo");
		Integer cargoID = scanner.nextInt();

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		Optional<Cargo> cargo = cargoRepository.findById(cargoID);
		funcionario.setCargo(cargo.get());

		funcionarioRepository.save(funcionario);
		System.out.println("Atualizado");
	}

	private void visualizar(Scanner scanner) {
		System.out.println("Qual página você deseja visualizar");
		Integer page = scanner.nextInt();

		Pageable pageable = PageRequest.of(page, 5, Sort.unsorted());
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

		System.out.println(funcionarios);
		System.out.println("Página atual " + funcionarios.getNumber());
		System.out.println("Total de elementos " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Informe o id");
		int id = scanner.nextInt();

		funcionarioRepository.deleteById(id);
		System.out.println("Registro deletado");
	}

}

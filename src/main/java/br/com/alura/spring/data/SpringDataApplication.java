package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudFuncionarioService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudFuncionarioService funcionarioService;

	private Boolean system = true;

	public SpringDataApplication(CrudFuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual ação você quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");

			int action = scanner.nextInt();

			if (action == 1) {

				funcionarioService.inicial(scanner);
			} else {
				system = false;
			}
		}

	}

}

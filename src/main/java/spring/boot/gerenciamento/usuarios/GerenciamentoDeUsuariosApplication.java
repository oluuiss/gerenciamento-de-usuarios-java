package spring.boot.gerenciamento.usuarios;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GerenciamentoDeUsuariosApplication {
    private static Scanner sc = new Scanner(System.in);

    // menu
    private static void opcao(){
        while (true) {
            System.out.println("\n==    Selecione a opção desejada    ==");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Todos usuários");
            System.out.println("3 - Pesquisar por CPF");
            System.out.println("4 - Remover usuário");
            System.out.println("0 - Sair");
            System.out.print("Opção desejada: ");
            int opcoes = Integer.parseInt(sc.nextLine());

            switch (opcoes) {
                case 0:
                    System.out.println("\nPrograma encerrado com sucesso.\n\n\n");
                    return;

                case 1:
                    cadastrarUsuario();
                    break;

                case 2:
                    pesquisarTodosUsuarios();
                    break;

                case 3:
                    pesquisarPorCPF();
                    break;

                case 4:
                    removerUsuario();
                    break;
            }
        }
    }

// cadastrar usuário
    private static void cadastrarUsuario(){
        // ========================= Dados pessoais =========================
        System.out.println("\n=== Opção desejada: Cadastrar Usuário ===");
        System.out.println("===           Dados pessoais          ===");
        System.out.print("Nome: "); 
        String nome = sc.nextLine(); // Nome
        ValidarString(nome);
        Long cpf = null;
        while (cpf == null) {
            System.out.print("Cadastro Pessoa Fisica (CPF - Apenas números): ");
            String cpfInput = sc.nextLine();
            if (validarCPF(cpfInput)) {
                cpf = Long.parseLong(cpfInput);
            } else {
                System.err.println("CPF deve conter exatamente 11 dígitos numéricos. Tente novamente.");
            }
        }
        System.out.print("Dia de nascimento: ");
        int dia = sc.nextInt(); // Dia
        System.out.print("Mês de nascimento: ");
        int mes = sc.nextInt(); // Mês
        System.out.print("Ano de nascimento: ");
        int ano = sc.nextInt(); // Ano
        buffer();
        System.out.print("Genero: "); 
        String genero = sc.nextLine(); // Genero
        System.out.print("Etnia / Raça: ");
        String etnia = sc.nextLine(); // Etnia
        System.out.print("Escolaridade: ");
        String escolaridade = sc.nextLine(); // Escolaridade

        Usuarios usuarios = new Usuarios(nome, cpf, dia, mes, ano, genero, etnia, escolaridade);
        UsuariosDAO UsuariosDAO = new UsuariosDAO();
        try {
            UsuariosDAO.save(usuarios);
            System.out.println("\n===   Usuário cadastrado com sucesso   ===\n\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("\n===   Erro ao cadastrar usuário   ===\n\n");
        }
        
        
       
    }

    // pesquisa de todos
    private static void pesquisarTodosUsuarios() {
        System.out.println("\n=== Opção desejada: Pesquisar Todos Usuários ===");
    
        UsuariosDAO usuarioDAO = new UsuariosDAO();
    
        try {
            List<Usuarios> usuarios = usuarioDAO.buscarTodosUsuarios();
            
            // verifica se a lista está vazia em vez de nula
            if (usuarios != null && !usuarios.isEmpty()) {
                System.out.println("Lista de usuários:");
                
                for (Usuarios usuario : usuarios) {
                    // adicione verificação nula para cada usuário
                    if (usuario != null) {
                        System.out.println("Nome: " + usuario.nome() + ", CPF: " + usuario.cpf());
                    }
                }
                System.out.println("Usuários cadastrados: "+ usuarios.size());
                System.out.println("\n===   Usuários encontrados com sucesso   ===");
            } else {
                System.err.println("\n===   Nenhum usuário encontrado   ===");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\n===   Erro ao pesquisar usuário   ===\n\n");
        }
    }

    // pesquisa por CPF
    private static void pesquisarPorCPF() {
        System.out.println("\n=== Opção desejada: Pesquisar por CPF ===");
        System.out.print("Digite o CPF (apenas números): ");
        String cpfInput = sc.nextLine();

        if (!validarCPF(cpfInput)) {
            System.err.println("\nCPF inválido. Deve conter exatamente 11 dígitos numéricos.");
            return;
        }

        Long cpf = Long.parseLong(cpfInput);
        UsuariosDAO usuarioDAO = new UsuariosDAO();

        try {
            Usuarios usuario = usuarioDAO.buscarPorCPF(cpf);

            if (usuario != null) {
                System.out.println("\n=== Usuário encontrado ===");
                System.out.println("Nome: " + usuario.nome());
                System.out.println("CPF: " + usuario.cpf());
                System.out.println("Data de Nascimento: " + usuario.dia() + "/" + usuario.mes() + "/" + usuario.ano());
                System.out.println("Gênero: " + usuario.genero());
                System.out.println("Etnia/Raça: " + usuario.etnia());
                System.out.println("Escolaridade: " + usuario.escolaridade());
                System.out.println("\n===   Pesquisa concluída com sucesso   ===");
            } else {
                System.err.println("\n===   Nenhum usuário encontrado com este CPF   ===");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\n===   Erro ao pesquisar usuário   ===\n\n");
        }
    }

    // remover usuário
    private static void removerUsuario() {
        System.out.println("\n=== Opção desejada: Remover Usuário ===");
        System.out.print("Digite o CPF do usuário a ser removido (apenas números): ");
        String cpfInput = sc.nextLine();

        if (!validarCPF(cpfInput)) {
            System.err.println("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
            return;
        }

        Long cpf = Long.parseLong(cpfInput);
        UsuariosDAO usuarioDAO = new UsuariosDAO();

        try {
            // Verifica se o usuário existe antes de remover
            Usuarios usuario = usuarioDAO.buscarPorCPF(cpf);
            if (usuario == null) {
                System.err.println("\n===   Nenhum usuário encontrado com este CPF   ===");
                return;
            }

            // Confirmação antes de remover
            System.out.println("\nUsuário encontrado:");
            System.out.println("Nome: " + usuario.nome());
            System.out.println("CPF: " + usuario.cpf());
            System.out.print("\nTem certeza que deseja remover este usuário? (S/N): ");
            String confirmacao = sc.nextLine().toUpperCase();

            if (confirmacao.equals("S")) {
                boolean removido = usuarioDAO.removerUsuario(cpf);
                if (removido) {
                    System.out.println("\n===   Usuário removido com sucesso   ===");
                } else {
                    System.err.println("\n===   Falha ao remover usuário   ===");
                }
            } else {
                System.out.println("\nOperação cancelada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\n===   Erro ao remover usuário   ===\n\n");
        }
    }

    // em caso de string após INT
    private static void buffer(){
        String limparBuffer = sc.nextLine();
    }

    // validação de nome (apenas letras e espaços)
    private static void ValidarString(String validarString) {
        if (!validarString.matches("^[A-Za-zÀ-ú]+( [A-Za-zÀ-ú]+)*$")) {
            System.err.println("Digite apenas letras com um espaço entre os nomes. Ex: 'Luis Porto'");
            System.out.print("Nome: ");
            String novoNome = sc.nextLine();
            ValidarString(novoNome); // chama recursivamente até ser válido
        }
    }
    // validação de CPF (11 dígitos)
    private static boolean validarCPF(String cpf) {
        return cpf.matches("^\\d{11}$"); // verifica se tem exatamente 11 dígitos
    }

    public static void main(String[] args) {
        System.out.println("===   Sistema de Gerenciamento de Usuários   ===\n\n");
        opcao();
    }
}
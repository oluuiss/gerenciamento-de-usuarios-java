package spring.boot.gerenciamento.usuarios;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GerenciamentoDeUsuariosApplication {
    private static Scanner sc = new Scanner(System.in);

    // Menu com opções para usuário
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
            }
        }
    }

// Cadastro de usuários
    private static void cadastrarUsuario(){
        // ========================= Dados pessoais =========================
        System.out.println("\n=== Opção desejada: Cadastrar Usuário ===");
        System.out.println("===           Dados pessoais          ===");
        System.out.print("Nome: "); 
        String nome = sc.nextLine(); // Nome
        ValidarString(nome);
        System.out.print("Cadastro Pessoa Fisica (CPF (Apenas números)): "); 
        Long cpf = sc.nextLong(); // CPF
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

    // Em caso de string após INT
    private static void buffer(){
        String limparBuffer = sc.nextLine();
    }

    // Validação de string
    private static void ValidarString(String validarString) {
        if (!validarString.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
           /* for(){
                System.err.println("Erro: Digite apenas letras com um espaço entre os nomes. Ex: 'Luis Gustavo'");
                System.out.println("Nome: ");
                String nome = sc.nextLine();
               // cadastrarUsuario();
            }*/
        }
    }
/* 
    private static void ValidarCPF(int validarCPF) {

        if (validarCPF.matches("^\\d{11}$")) {

        }
    }
*/
    public static void main(String[] args) {
        System.out.println("===   Sistema de Gerenciamento de Usuários   ===\n\n");
        opcao();
    }
}
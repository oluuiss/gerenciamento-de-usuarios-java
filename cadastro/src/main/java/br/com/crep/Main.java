package br.com.crep;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    // Menu com opções para usuário
    private static void opcao(){
        while (true) {
            System.out.println("\n=== Selecione a opção desejada ===");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Remover Usuário");
            System.out.println("3 - Pesquisar Usuário");
            System.out.println("0 - Sair");
            System.out.print("Opção desejada: ");
            int opcoes = Integer.parseInt(sc.nextLine());

            switch (opcoes) {
                case 0:
                    System.out.println("\nPrograma encerrado com sucesso.\n\n\n");
                    return;

                case 1:
                    cadastrarUsuario();
            }
        }
    }

    private static void cadastrarUsuario(){
        // ========================= Dados pessoais =========================
        System.out.println("\n=== Opção desejada: Cadastrar Usuário ===");
        System.out.println("===           Dados pessoais          ===");
        System.out.print("Nome: "); 
        String nome = sc.nextLine(); // Nome
        System.out.print("Cadastro Pessoa Fisica (CPF): "); 
        String cpf = sc.nextLine(); // CPF
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


    /*
    private static void pesquisarUsuario(){
        Usuarios Usuarios = new Usuarios();
        try {
            List<DadosPessoais> dp = Usuarios.pesquisar();

            if(dp != null){
                System.out.println("Lista de usuários:");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("\n===   Erro ao pesquisar usuário   ===\n\n");
        }
    }
    */

    // Em caso de string após INT
    private static void buffer(){
        String limparBuffer = sc.nextLine();
    }

    public static void main(String[] args) {
        opcao();
    }
}
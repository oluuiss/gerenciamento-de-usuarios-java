package spring.boot.gerenciamento.usuarios;


public record Usuarios(Long id, String nome, String cpf, int dia, int mes, int ano, String genero, String etnia, String escolaridade) {

    public Usuarios(String nome, String cpf, int dia, int mes, int ano, String genero, String etnia, String escolaridade) {
        this(null, nome, cpf, dia, mes, ano, genero, etnia, escolaridade);
    }
    
}



package br.com.alura.domain;

public class Abrigo {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;

    public Abrigo(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Abrigo() {
    }

    public String getTelefone() {
        return telefone;
    }

    public Pet[] getPets() {
        return pets;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return """
                "id":%s,"nome":"%s","telefone":"%s","email":"%s"
                """.formatted(this.id, this.nome, this.telefone, this.email);
    }
}

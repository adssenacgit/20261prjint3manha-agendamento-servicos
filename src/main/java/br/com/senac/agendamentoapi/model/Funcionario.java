package br.com.senac.agendamentoapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Integer id;

    @Column(name = "funcionario_nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "funcionario_email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "funcionario_senha_hash", nullable = false, length = 64)
    private String senhaHash;

    @Column(name = "funcionario_telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "funcionario_status", nullable = false)
    private Integer status;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}

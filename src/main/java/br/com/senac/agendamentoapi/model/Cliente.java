package br.com.senac.agendamentoapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Integer id;

    @Column(name = "cliente_nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "cliente_telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "cliente_email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "cliente_data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "cliente_status", nullable = false)
    private Integer status;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}

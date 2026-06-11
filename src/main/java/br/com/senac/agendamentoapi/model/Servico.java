package br.com.senac.agendamentoapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_id")
    private Integer id;

    @Column(name = "servico_nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "servico_descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "servico_valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "servico_duracao_minutos", nullable = false)
    private Integer duracaoMinutos;

    @Column(name = "servico_status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
}

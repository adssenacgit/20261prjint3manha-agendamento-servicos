package br.com.senac.agendamentoapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "horario_disponivel")
public class HorarioDisponivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horario_disponivel_id")
    private Integer id;

    @Column(name = "horario_disponivel_data", nullable = false)
    private LocalDate data;

    @Column(name = "horario_disponivel_inicio", nullable = false)
    private LocalTime inicio;

    @Column(name = "horario_disponivel_fim", nullable = false)
    private LocalTime fim;

    @Enumerated(EnumType.STRING)
    @Column(name = "horario_disponivel_situacao", nullable = false, length = 20)
    private HorarioDisponivelSituacao situacao;

    @Column(name = "horario_disponivel_status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getInicio() { return inicio; }
    public void setInicio(LocalTime inicio) { this.inicio = inicio; }
    public LocalTime getFim() { return fim; }
    public void setFim(LocalTime fim) { this.fim = fim; }
    public HorarioDisponivelSituacao getSituacao() { return situacao; }
    public void setSituacao(HorarioDisponivelSituacao situacao) { this.situacao = situacao; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
}

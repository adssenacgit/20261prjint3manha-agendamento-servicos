package br.com.senac.agendamentoapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agendamento_id")
    private Integer id;

    @Column(name = "agendamento_data", nullable = false)
    private LocalDate data;

    @Column(name = "agendamento_horario_inicio", nullable = false)
    private LocalTime horarioInicio;

    @Column(name = "agendamento_horario_fim", nullable = false)
    private LocalTime horarioFim;

    @Column(name = "agendamento_observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "agendamento_situacao", nullable = false, length = 20)
    private AgendamentoSituacao situacao;

    @Column(name = "agendamento_status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "horario_disponivel_id", nullable = false)
    private HorarioDisponivel horarioDisponivel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(LocalTime horarioInicio) { this.horarioInicio = horarioInicio; }
    public LocalTime getHorarioFim() { return horarioFim; }
    public void setHorarioFim(LocalTime horarioFim) { this.horarioFim = horarioFim; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public AgendamentoSituacao getSituacao() { return situacao; }
    public void setSituacao(AgendamentoSituacao situacao) { this.situacao = situacao; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }
    public HorarioDisponivel getHorarioDisponivel() { return horarioDisponivel; }
    public void setHorarioDisponivel(HorarioDisponivel horarioDisponivel) { this.horarioDisponivel = horarioDisponivel; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}

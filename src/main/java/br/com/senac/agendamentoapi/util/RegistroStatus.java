package br.com.senac.agendamentoapi.util;

import br.com.senac.agendamentoapi.exception.BusinessException;

public final class RegistroStatus {

    public static final int EXCLUIDO = -1;
    public static final int INATIVO = 0;
    public static final int ATIVO = 1;

    private RegistroStatus() {
    }

    public static int normalizar(Integer status) {
        if (status == null) {
            return ATIVO;
        }
        validar(status);
        return status;
    }

    public static void validar(Integer status) {
        if (status == null) {
            return;
        }
        if (status != EXCLUIDO && status != INATIVO && status != ATIVO) {
            throw new BusinessException("Status inválido. Use -1 para apagado, 0 para inativo ou 1 para ativo.");
        }
    }
}

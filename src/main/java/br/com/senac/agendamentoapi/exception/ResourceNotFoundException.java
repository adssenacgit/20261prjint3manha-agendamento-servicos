package br.com.senac.agendamentoapi.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Integer id) {
        super(resource + " não encontrado(a) com id " + id + ".");
    }
}

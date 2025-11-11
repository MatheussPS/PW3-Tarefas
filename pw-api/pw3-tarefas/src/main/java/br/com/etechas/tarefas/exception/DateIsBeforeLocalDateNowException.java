package br.com.etechas.tarefas.exception;

public class DateIsBeforeLocalDateNowException extends RuntimeException {
    public DateIsBeforeLocalDateNowException() {
        super("Data não pode ser menor que a data de hoje");
    }
}

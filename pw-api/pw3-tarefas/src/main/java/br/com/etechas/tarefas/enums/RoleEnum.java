package br.com.etechas.tarefas.enums;

public enum RoleEnum {
    ADMIN,
    USER;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}

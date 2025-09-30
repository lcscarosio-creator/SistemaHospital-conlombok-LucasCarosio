package Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor(force = true)
@Getter
@ToString(onlyExplicitlyIncluded = true)
@SuperBuilder
public class Matricula implements Serializable {

    @NonNull
    @ToString.Include
    private final String numero;

    // Constructor personalizado con validación
    public Matricula(String numero) {
        this.numero = validarMatricula(numero);
    }

    private String validarMatricula(String numero) {
        Objects.requireNonNull(numero, "El número de matrícula no puede ser nulo");
        if (!numero.matches("MP-\\d{4,6}")) {
            throw new IllegalArgumentException("Formato de matrícula inválido. Debe ser como MP-12345");
        }
        return numero;
    }
}

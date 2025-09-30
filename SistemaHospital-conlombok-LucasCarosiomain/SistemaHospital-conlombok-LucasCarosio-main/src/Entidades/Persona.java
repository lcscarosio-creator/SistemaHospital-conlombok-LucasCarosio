package Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@ToString(onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor(force = true) // necesario para SuperBuilder
public abstract class Persona implements Serializable {

    @NonNull
    @ToString.Include
    protected final String nombre;

    @NonNull
    @ToString.Include
    protected final String apellido;

    @NonNull
    @ToString.Include
    protected final String dni;

    @NonNull
    @ToString.Include
    protected final LocalDate fechaNacimiento;

    @NonNull
    @ToString.Include
    protected final TipoSangre tipoSangre;

    // Constructor personalizado con validaciones
    public Persona(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre) {
        this.nombre = validarString(nombre, "El nombre no puede ser nulo ni vacío");
        this.apellido = validarString(apellido, "El apellido no puede ser nulo ni vacío");
        this.dni = validarDni(dni);
        this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento, "La fecha de nacimiento no puede ser nula");
        this.tipoSangre = Objects.requireNonNull(tipoSangre, "El tipo de sangre no puede ser nulo");
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

    private String validarString(String valor, String mensajeError) {
        Objects.requireNonNull(valor, mensajeError);
        if (valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor;
    }

    private String validarDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo");
        if (!dni.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("El DNI debe tener 7 u 8 dígitos");
        }
        return dni;
    }
}

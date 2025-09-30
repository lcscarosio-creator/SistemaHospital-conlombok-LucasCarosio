package Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@ToString(onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor(force = true)
public class Medico extends Persona implements Serializable {

    @NonNull
    @ToString.Include
    private final Matricula matricula;

    @NonNull
    @ToString.Include
    private final EspecialidadMedica especialidad;

    @ToString.Include
    private Departamento departamento;

    private final List<Cita> citas = new ArrayList<>();

    // Constructor personalizado
    public Medico(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                  TipoSangre tipoSangre, String numeroMatricula, EspecialidadMedica especialidad) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.matricula = new Matricula(numeroMatricula);
        this.especialidad = Objects.requireNonNull(especialidad, "La especialidad no puede ser nula");
    }

    public void setDepartamento(Departamento departamento) {
        if (this.departamento != departamento) {
            this.departamento = departamento;
        }
    }

    public void addCita(Cita cita) {
        if (cita != null) {
            this.citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(new ArrayList<>(citas));
    }
}

package Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@ToString(onlyExplicitlyIncluded = true)
@SuperBuilder
public class HistoriaClinica implements Serializable {

    @NonNull
    @ToString.Include
    private final String numeroHistoria;

    @NonNull
    @ToString.Exclude // Excluir para evitar recursión infinita
    private final Paciente paciente;

    @NonNull
    @ToString.Include
    private final LocalDateTime fechaCreacion;

    @ToString.Include
    private final List<String> diagnosticos = new ArrayList<>();

    @ToString.Include
    private final List<String> tratamientos = new ArrayList<>();

    @ToString.Include
    private final List<String> alergias = new ArrayList<>();

    // Constructor personalizado que inicializa numeroHistoria y fecha
    public HistoriaClinica(Paciente paciente) {
        this.paciente = paciente;
        this.fechaCreacion = LocalDateTime.now();
        this.numeroHistoria = generarNumeroHistoria();
    }

    private String generarNumeroHistoria() {
        return "HC-" + paciente.getDni() + "-" + fechaCreacion.getYear();
    }

    public void agregarDiagnostico(String diagnostico) {
        if (diagnostico != null && !diagnostico.trim().isEmpty()) diagnosticos.add(diagnostico);
    }

    public void agregarTratamiento(String tratamiento) {
        if (tratamiento != null && !tratamiento.trim().isEmpty()) tratamientos.add(tratamiento);
    }

    public void agregarAlergia(String alergia) {
        if (alergia != null && !alergia.trim().isEmpty()) alergias.add(alergia);
    }

    public List<String> getDiagnosticos() {
        return Collections.unmodifiableList(diagnosticos);
    }

    public List<String> getTratamientos() {
        return Collections.unmodifiableList(tratamientos);
    }

    public List<String> getAlergias() {
        return Collections.unmodifiableList(alergias);
    }
}

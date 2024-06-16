package med.voll.vollapirest.domain.medico;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Especialidad {
    ORTOPEDIA("ortopedia"),
    CARDIOLOGIA("cardiologia"),
    GINECOLOGIA("ginecologia"),
    PEDIATRIA("pediatria");

    private final String minusculas;

    Especialidad(String minusculas) {
        this.minusculas = minusculas;
    }

    @JsonValue //Esto es para que Jackson serialice un objeto a json utilizando los valores en minúscula
    public String getMinusculas() { //Si lo queres ver funcionar, mira lo que devuelva un DatosRespuestaMedico
        return minusculas;
    }

    @JsonCreator ////Esto es para que Jackson deserialice un json a objeto verificando los valores en minúscula
    public static Especialidad fromJson(String valor) {
        for (Especialidad especialidad : Especialidad.values()) {
            if (especialidad.minusculas.equalsIgnoreCase(valor)) {
                return especialidad;
            }
        }
        throw new IllegalArgumentException("Especialidad desconocida para: " + valor);
    }
}

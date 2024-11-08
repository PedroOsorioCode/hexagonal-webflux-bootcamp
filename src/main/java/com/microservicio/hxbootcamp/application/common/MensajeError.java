package com.microservicio.hxbootcamp.application.common;

public enum MensajeError {

    DATOS_OBLIGATORIOS("El campo %s es obligatorio"),
    NOMBRE_DUPLICADO("Nombre duplicado"),
    TECNOLOGIA_INCOMPLETAS("La capacidad %s debe de tener mínimo 3 tecnologías asociadas"),
    LONGITUD_PERMITIDA("Longitud permitida para %s es de %s"),
    TECNOLOGIA_NO_PERMITIDAS("La capacidad %s debe de tener máximo 20 tecnologías asociadas"),
    TECNOLOGIA_REPETIDAS("La capacidad %s contiene tecnologías repetidas."),
    COLUMNA_ORDENAMIENTO_INCORRECTO("La columba de ordenamiento es incorrecta"),
    METODO_ORDENAMIENTO_INCORRECTO("El metodo de ordenamiento es incorrecto, solo se permite asc o desc");

    private final String mensaje;

    MensajeError(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String formato(Object... args) {
        return String.format(mensaje, args);
    }
}

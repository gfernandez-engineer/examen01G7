package com.examenG7.examen.utils;

public final class Constantes {

    public enum EstadoPersona {
        ACTIVO("ACTIVO"),
        INACTIVO("INACTIVO");

        private final String descripcion;

        EstadoPersona(String descripcion){
            this.descripcion = descripcion;
        }
        public String getDescripcion() {
            return descripcion;
        }
    }
    public enum EstadoPedido{
        PENDIENTE("PENDIENTE"),
        PROCESO("PROCESO"),
        CONFIRMADO("CONFIRMADO"),
        ELIMINADO("ELIMINADO");

        private final String descripcion;

        EstadoPedido(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

    };






}

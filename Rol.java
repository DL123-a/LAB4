public enum Rol {
    ANTROPOLOGO("Antropólogo"),
    INVESTIGADOR("Investigador"),
    ANALISTA("Analista"),
    ESTUDIANTE("Estudiante");

    private final String label;
    Rol(String l) { this.label = l; }
    @Override public String toString() { return label; }
}

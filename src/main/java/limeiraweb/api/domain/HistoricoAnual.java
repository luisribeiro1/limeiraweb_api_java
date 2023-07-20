package limeiraweb.api.domain;

public record HistoricoAnual(String ano,
                             double temperaturaMinima,
                             double temperaturaMaxima,
                             double uraMinima,
                             double uraMaxima,
                             double chuva) {
}

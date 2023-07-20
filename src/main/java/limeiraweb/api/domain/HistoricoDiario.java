package limeiraweb.api.domain;

import java.time.LocalDateTime;

public record HistoricoDiario(LocalDateTime data,
                              double temperaturaMinima,
                              double temperaturaMaxima,
                              double uraMinima,
                              double uraMaxima,
                              double chuva) {

}

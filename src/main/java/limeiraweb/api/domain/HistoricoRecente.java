package limeiraweb.api.domain;

import java.time.LocalDateTime;

public record HistoricoRecente(LocalDateTime data,
                               double temperatura,
                               int ura,
                               double chuva
) {
}

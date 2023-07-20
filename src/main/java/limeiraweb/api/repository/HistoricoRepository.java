package limeiraweb.api.repository;

import limeiraweb.api.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    @Query("""
        SELECT NEW limeiraweb.api.domain.HistoricoRecente(h.dataHora as data,
        h.temperatura as temperatura,
        h.ura as ura,
        h.chuva as chuva)
        FROM Historico h
        ORDER BY h.dataHora DESC
        LIMIT 144
        """)
    List<HistoricoRecente> listarHistoricoPorHora();

    @Query("""
            SELECT NEW limeiraweb.api.domain.HistoricoDiario(h.dataHora as data,
                MIN(h.temperatura) as temperaturaMinima,
                MAX(h.temperatura) as temperaturaMaxima,
                MIN(h.ura) as uraMinima,
                MAX(h.ura) as uraMaxima,
                SUM(h.chuva) as chuva)
            FROM Historico h
            GROUP BY DATE(h.dataHora)
            ORDER BY h.dataHora desc
            """)
    List<HistoricoDiario> listarHistoricoDiario();

    @Query("""
            SELECT NEW limeiraweb.api.domain.HistoricoMensal(concat(month(h.dataHora),'/',year(h.dataHora)) as data,
                MIN(h.temperatura) as temperaturaMinima,
                MAX(h.temperatura) as temperaturaMaxima,
                MIN(h.ura) as uraMinima,
                MAX(h.ura) as uraMaxima,
                SUM(h.chuva) as chuva)
            FROM Historico h
            GROUP BY concat(month(h.dataHora),'/',year(h.dataHora))
            ORDER BY h.dataHora desc
            """)
    List<HistoricoMensal> listarHistoricoMensal();

    @Query("""
            SELECT NEW limeiraweb.api.domain.HistoricoAnual(concat(year(h.dataHora),'') as ano,
                MIN(h.temperatura) as temperaturaMinima,
                MAX(h.temperatura) as temperaturaMaxima,
                MIN(h.ura) as uraMinima,
                MAX(h.ura) as uraMaxima,
                SUM(h.chuva) as chuva)
            FROM Historico h
            GROUP BY concat(year(h.dataHora),'')
            ORDER BY h.dataHora desc
            """)
    List<HistoricoAnual> listarHistoricoAnual();


}

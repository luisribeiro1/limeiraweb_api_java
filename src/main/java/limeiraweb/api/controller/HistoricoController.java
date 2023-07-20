package limeiraweb.api.controller;

import limeiraweb.api.domain.HistoricoAnual;
import limeiraweb.api.domain.HistoricoDiario;
import limeiraweb.api.domain.HistoricoMensal;
import limeiraweb.api.domain.HistoricoRecente;
import limeiraweb.api.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;

    @GetMapping("/porhora")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<HistoricoRecente> historicoPorHora(){
        return historicoRepository.listarHistoricoPorHora();
    }

    @GetMapping("/pordia")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<HistoricoDiario> historicoPorDia(){
        return historicoRepository.listarHistoricoDiario();
    }

    @GetMapping("/pormes")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<HistoricoMensal> historicoPorMes(){
        return historicoRepository.listarHistoricoMensal();
    }

    @GetMapping("/porano")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<HistoricoAnual> historicoPorAno(){
        return historicoRepository.listarHistoricoAnual();
    }
}

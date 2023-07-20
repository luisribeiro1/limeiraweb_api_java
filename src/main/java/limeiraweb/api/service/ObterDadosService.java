package limeiraweb.api.service;

import limeiraweb.api.domain.Historico;
import limeiraweb.api.exception.FormatoDeDataInvalidoException;
import limeiraweb.api.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

@Service
//@EnableScheduling
public class ObterDadosService {

    @Autowired
    private HistoricoRepository historicoRepository;

    // 120.000 dois minutos
    //@Scheduled(fixedDelay = 600000)
    public void atualizaHistorico() throws FormatoDeDataInvalidoException{

        String caminho = "https://www3.ft.unicamp.br/meteorologia/downld08.txt";

        try {
            URL url = new URL(caminho);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String linha;
            int contador = 1;

            while ((linha = br.readLine()) != null) {
                if(contador>3){

                    if (linha.length() >= 120) {
                        String data = linha.substring(0, 8);
                        String hora = linha.substring(10, 15);
                        String temperaturaString = linha.substring(18, 22);
                        String uraString = linha.substring(40,42);
                        String chuvaString = linha.substring(110, 116);


                        LocalDateTime dataHora = ValidaDataHora.converterStringParaLocalDateTime(data,hora);


                        Double temperatura = Double.parseDouble(temperaturaString);
                        int ura = Integer.parseInt(uraString);
                        Double chuva = Double.parseDouble(chuvaString);

                        Historico historico = new Historico();
                        historico.setDataHora(dataHora);
                        historico.setTemperatura(temperatura);
                        historico.setUra(ura);
                        historico.setChuva(chuva);
                        historicoRepository.save(historico);

                    }

                }

                contador++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

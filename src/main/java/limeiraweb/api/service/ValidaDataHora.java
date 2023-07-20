package limeiraweb.api.service;

import limeiraweb.api.exception.FormatoDeDataInvalidoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ValidaDataHora {


    public static LocalDateTime converterStringParaLocalDateTime(String dataString, String horaString) throws FormatoDeDataInvalidoException {

        String dataHoraString = dataString + " " + padronizarHora(horaString);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        try {
            return LocalDateTime.parse(dataHoraString, formatter);
        }catch (Exception ex){
            throw new FormatoDeDataInvalidoException("A data informada é inválida");
        }


    }

    private static String padronizarHora(String horaString){

        if(horaString.trim().length()==4){
            horaString = "0"+horaString.trim();
        }
        return horaString;
    }

}

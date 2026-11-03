package com.marczc.practica5controladorrest.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping()
public class indexController {
    @Value("${app.centro}")
    String centro;
    

    @GetMapping("/centro")
    public String getCentro() {
        return centro;
    }

    @GetMapping("/saludoX")
    public String saludo(
            @RequestParam int numVeces,
            @RequestParam (name= "developer", required = false, defaultValue = "Antonio Calabuig") String developer
    ){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int hora = ahora.getHour();
        String saludo;
        if (hora <= 12) {
            saludo = "Buenos días";
        }else if(hora < 20){
            saludo = "Buenas tardes";
        }else {
            saludo = "Buenas noches";
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder resultado = new StringBuilder("<b>"+saludo+"</b><br>Hoy es " + ahora.format(formato) + "<br><ol>");
        resultado.append(sb.repeat( "<li>Saludos desde el centro " + centro + " de parte de " + developer + "<br></li>",numVeces));
        resultado.append("</ol>");
        return resultado.toString();
    }
}

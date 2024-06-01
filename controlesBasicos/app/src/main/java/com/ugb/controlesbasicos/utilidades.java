package com.ugb.controlesbasicos;

import java.util.Base64;

public class utilidades {
    static String urlConsulta = "http://192.168.0.23:5984/turismosv/_design/turismosv/_view/turismosv";
    static String urlMto = "http://192.168.0.23:5984/turismosv/";
    static String user = "admin";
    static String passwd = "admin";
    static String credencialesCodificadas = Base64.getEncoder().encodeToString((user +":"+ passwd).getBytes());
    public String generarIdUnico(){
        return java.util.UUID.randomUUID().toString();
    }
}
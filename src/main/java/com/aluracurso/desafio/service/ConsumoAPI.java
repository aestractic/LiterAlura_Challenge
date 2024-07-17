package com.aluracurso.desafio.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ConsumoAPI {
    public String obtenerDatos(String urlString) {
        StringBuilder resultado = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                resultado.append(linea);
            }
            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }
}


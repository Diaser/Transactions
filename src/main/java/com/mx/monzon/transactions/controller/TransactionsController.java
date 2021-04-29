package com.mx.monzon.transactions.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.monzon.transactions.To.Transaction;
import org.json.JSONException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@RestController
public class TransactionsController {

    @RequestMapping("/tata")
    public String getHelloWorld() {
        return "Hola Api Rest Java";
    }

    @RequestMapping("/tata/transactions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Transaction>> getTransactions(){
        List<Transaction> lista = getList();
        return new ResponseEntity<> (lista, HttpStatus.OK);
        // 400 HttpStatus.BAD_REQUEST
        // 422 HttpStatus.UNPROCESSABLE_ENTITY;
    }

    private List<Transaction> getList(){
        List<Transaction> list = new ArrayList<>();

        for (int i = 1; i <= 3; i++){

            Transaction transaction = new Transaction();
            transaction.setId(i);
            transaction.setDescription("Pago " + i);
            transaction.setAmount(56.58f + i);
            transaction.setStatus("Inactive");
            list.add(transaction);
        }
        return list;
    }

    // Convierte excel a listado de consumidores
    @PostMapping("/pruebaExcel") // Mapeo de api
    public ResponseEntity<String> convertirPerfilExcel2(
            @RequestHeader("idCompania") String idCompania,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String resultPrueba =
                "Id Compañia" + idCompania +
                "Tamaño: " +
                file.getSize() + " Bytes: " +
                file.getBytes() + " Nombre de archivo: " +
                file.getName();
        // Entidad de respuesta
        return new ResponseEntity<>(resultPrueba, HttpStatus.OK);
    }

    @PostMapping("/prueba") // Mapeo de api
    public ResponseEntity<String> convertirPerfilExcel1(
            @RequestParam("file") MultipartFile file
    ) throws IOException, JSONException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://localhost:8080/tata", String.class);

        return response;
    }
}

package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.input.UsuarioDTOInput;
import org.example.service.UsuarioService;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class UsuarioController {
    private final UsuarioService usuarioService = new UsuarioService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UsuarioController() {
        get("/usuarios", (request, response) -> {
            response.type("application/json");
            response.status(200);
            String json = objectMapper.writeValueAsString(usuarioService.listarUsuarios());
            return json;
        });

        get("/usuarios/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            int id = Integer.parseInt(idParam);
            String json = objectMapper.writeValueAsString(usuarioService.obterUsuario(id));
            response.status(200);
            return json;
        });

        delete("/usuarios/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            int id = Integer.parseInt(idParam);
            usuarioService.excluirUsuario(id);
            response.status(200);
            return "Usuário excluído com sucesso";
        });

        post("/usuarios", (request, response) -> {
            UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(request.body(), UsuarioDTOInput.class);
            usuarioService.inserirUsuario(usuarioDTOInput);
            response.type("application/json");
            response.status(201);
            return "Usuário inserido com sucesso";
        });

        put("/usuarios", (request, response) -> {
            UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(request.body(), UsuarioDTOInput.class);
            usuarioService.alterarUsuario(usuarioDTOInput);
            response.type("application/json");
            response.status(200);
            return "Usuário alterado com sucesso";
        });
    }
}

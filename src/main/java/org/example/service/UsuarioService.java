package org.example.service;

import org.example.dao.GenericDao;
import org.example.dto.input.UsuarioDTOInput;
import org.example.dto.output.UsuarioDTOOutput;
import org.example.model.Usuario;
import org.modelmapper.ModelMapper;

import java.util.Collection;

public class UsuarioService {
    private final GenericDao<Usuario> usuarioDAO = new GenericDao<Usuario>(Usuario.class);
    private final ModelMapper modelMapper = new ModelMapper();

    public Collection<UsuarioDTOOutput> listarUsuarios() {
        usuarioDAO.begin();
        Iterable<Usuario> listaUsuarios = usuarioDAO.findAll();
        usuarioDAO.end();
        Collection<UsuarioDTOOutput> listaUsuarioDTOOutputs = modelMapper.map(listaUsuarios, Collection.class);
        return listaUsuarioDTOOutputs;
    }

    public UsuarioDTOOutput obterUsuario(int id) {
        usuarioDAO.begin();
        UsuarioDTOOutput usuarioDTOOutput = modelMapper.map(usuarioDAO.findById(id), UsuarioDTOOutput.class);
        usuarioDAO.end();
        return usuarioDTOOutput;
    }

    public void inserirUsuario(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        usuarioDAO.begin();
        usuarioDAO.create(usuario);
        usuarioDAO.end();
    }

    public void alterarUsuario(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        usuarioDAO.begin();
        usuarioDAO.update(usuario);
        usuarioDAO.end();
    }

    public void excluirUsuario(int id) {
        usuarioDAO.begin();
        usuarioDAO.delete(id);
        usuarioDAO.end();
    }
}

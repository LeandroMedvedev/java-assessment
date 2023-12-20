import org.example.dto.input.UsuarioDTOInput;
import org.example.service.UsuarioService;
import org.junit.Assert;
import org.junit.Test;

public class ServiceTest {
    @Test
    public void inserctionTest() {
        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();

        usuarioDTOInput.setNome("Kate Austen");
        usuarioDTOInput.setSenha("l0stS3rie-2004");
        usuarioService.inserirUsuario(usuarioDTOInput);

        Assert.assertEquals(1, usuarioService.listarUsuarios().size());
    }
}

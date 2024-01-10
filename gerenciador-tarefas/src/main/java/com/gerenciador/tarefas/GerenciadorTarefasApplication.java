package com.gerenciador.tarefas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.permissoes.PermissaoEnum;
import com.gerenciador.tarefas.service.UsuarioService;

@SpringBootApplication
public class GerenciadorTarefasApplication implements CommandLineRunner{


	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario();
		usuario.setUsername("admin");
		usuario.setPassword("123456");

		List<Role> roles = new ArrayList<>();
		
		Role roleAdmin = new Role();
		roleAdmin.setNome(PermissaoEnum.ADMINISTRADOR);

		Role roleUsuario = new Role();
		roleUsuario.setNome(PermissaoEnum.USUARIO);

		Role roleTR = new Role();
		roleTR.setNome(PermissaoEnum.TRANSFERENCIA);

		roles.add(roleAdmin);
		roles.add(roleUsuario);
		roles.add(roleTR);

		usuario.setRoles(roles);

		usuarioService.salvarUsuario(usuario);


		usuario = new Usuario();
		usuario.setUsername("caio");
		usuario.setPassword("123456");

		roles = new ArrayList<>();

		roleUsuario = new Role();
		roleUsuario.setNome(PermissaoEnum.USUARIO);

		roleTR = new Role();
		roleTR.setNome(PermissaoEnum.TRANSFERENCIA);

		roles.add(roleUsuario);
		roles.add(roleTR);

		usuario.setRoles(roles);

		usuarioService.salvarUsuario(usuario);
	}

}

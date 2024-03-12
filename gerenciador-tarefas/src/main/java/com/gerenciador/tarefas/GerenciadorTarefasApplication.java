package com.gerenciador.tarefas;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gerenciador.tarefas.entity.Entregador;
import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.entity.Transferencia;
import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.permissoes.LojasEnum;
import com.gerenciador.tarefas.permissoes.PermissaoEnum;
import com.gerenciador.tarefas.permissoes.StatusTransferenciaEnum;
import com.gerenciador.tarefas.service.EntregadorService;
import com.gerenciador.tarefas.service.TransferenciaService;
import com.gerenciador.tarefas.service.UsuarioService;

@SpringBootApplication
public class GerenciadorTarefasApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EntregadorService entregadorService;

	@Autowired
	private TransferenciaService transferenciaService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LocalDateTime localDateTime = LocalDateTime.now();
        
        // Converte para ZonedDateTime usando o fuso horário padrão
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        
        // Ajusta para o fuso horário do Brasil
        ZonedDateTime brasilTime = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
        
        // Obtém a hora no fuso horário do Brasil
        LocalDateTime horaBrasil = brasilTime.toLocalDateTime();
        
        System.out.println("Hora no Brasil: " + horaBrasil);
		System.out.println("modificado 12/3 12:14");

		// // CADASTRANDO USUARIOS

		// USUARIO 1
		Usuario usuario = new Usuario();
		usuario.setUsername("admin");
		usuario.setPassword("123456");
		usuario.setLojaDeRegistro(LojasEnum.TODAS);

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

		try {
			usuarioService.salvarUsuario(usuario);
		} catch (Exception e) {
			System.out.println("Ocorreu uma exceção: " + e.getMessage());
		}
		

		// // USUARIO 2
		// usuario = new Usuario();
		// usuario.setUsername("caio");
		// usuario.setPassword("123456");
		// usuario.setLojaDeRegistro(LojasEnum.LOJA_1);

		// roles = new ArrayList<>();

		// roleUsuario = new Role();
		// roleUsuario.setNome(PermissaoEnum.USUARIO);

		// roleTR = new Role();
		// roleTR.setNome(PermissaoEnum.TRANSFERENCIA);

		// roles.add(roleUsuario);
		// roles.add(roleTR);

		// usuario.setRoles(roles);

		// usuarioService.salvarUsuario(usuario);

		// // USUARIO 3
		// usuario = new Usuario();
		// usuario.setUsername("lorriane");
		// usuario.setPassword("123456");
		// usuario.setLojaDeRegistro(LojasEnum.LOJA_2);

		// roles = new ArrayList<>();

		// roleUsuario = new Role();
		// roleUsuario.setNome(PermissaoEnum.USUARIO);

		// roleTR = new Role();
		// roleTR.setNome(PermissaoEnum.TRANSFERENCIA);

		// roles.add(roleUsuario);
		// roles.add(roleTR);

		// usuario.setRoles(roles);

		// usuarioService.salvarUsuario(usuario);

		// // CADASTRANDO ENTREGADORES

		// // ENTREGADOR 1
		// Entregador entregador = new Entregador();
		// entregador.setNomeCompleto("caio novaes de lima");
		// entregador.setCpf("2123165461321");
		// entregador.setAtivo(true);

		// entregadorService.salvarEntregador(entregador);

		// // ENTREGADOR 2
		// entregador = new Entregador();
		// entregador.setNomeCompleto("roberto lopes de lima");
		// entregador.setCpf("4513214654321");
		// entregador.setAtivo(true);

		// entregadorService.salvarEntregador(entregador);

		// // CADASTRANDO TRANSFERENCIA

		// // TRANSFERENCIA 1

		// Transferencia transferencia = new Transferencia();

		// transferencia.setNumeroNota("512154");
		// transferencia.setLojaRemetente("LOJA_2");
		// transferencia.setLojaDestinatario("LOJA_1");
		// transferencia.setStatus(StatusTransferenciaEnum.ENVIADA);
		// transferencia.setEntregador(entregador);
		// transferencia.setUsuario(usuario);

		// transferenciaService.salvarTransferencia(transferencia);

	}

}

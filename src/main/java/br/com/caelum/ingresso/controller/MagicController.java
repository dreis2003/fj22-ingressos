package br.com.caelum.ingresso.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.caelum.ingresso.dao.PermissaoDao;
import br.com.caelum.ingresso.dao.UsuarioDao;
import br.com.caelum.ingresso.model.Permissao;
import br.com.caelum.ingresso.model.Usuario;

@Controller
public class MagicController {
	
	@Autowired
	private UsuarioDao usuarioDAO;
	
	@Autowired
	private PermissaoDao permissaoDAO;
	
	@Transactional
	@GetMapping("/magic/akfhasdhfasdhflkjahueifelhiufhaselfdjkfa")
	public String salvaUsuarioAdmin() {
		
		Permissao permissao = new Permissao();
		permissao.setNome("ROLE_ADMIN");
		
		permissaoDAO.salva(permissao);
		
		Usuario user = new Usuario();
		user.setEmail("dreis2003@hotmail.com");
		
		Set<Permissao> permissoes = new HashSet<>();
		permissoes.add(permissao);
		user.setPermissoes(permissoes);
		BCryptPasswordEncoder encr = new BCryptPasswordEncoder();
		
		String senha = encr.encode("123456");
		user.setPassword(senha);
		
		usuarioDAO.salva(user);
		
		return "login";
	}

}

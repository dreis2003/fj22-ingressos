package br.com.caelum.ingresso.model.descontos;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.enums.TipoDeIngresso;

public class DescontoTest {
	
	private Sala sala;
	private Filme filme;
	private Sessao sessao;
	private Lugar lugar;
	private Ingresso ingresso;
	
	@Before
	public void init() {
		this.sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		this.filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		this.sessao = new Sessao(LocalTime.now(), filme, sala);
		this.lugar = new Lugar("A",1);
	}

	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos() {
		
		this.ingresso = new Ingresso(sessao, TipoDeIngresso.BANCO, lugar);
		BigDecimal precoEsperado = new BigDecimal("22.75");
		assertEquals(precoEsperado, ingresso.getPreco());
		
	}

	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressoDeEstudante() {
		
		this.ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);
		BigDecimal precoEsperado = new BigDecimal("16.25");
		assertEquals(precoEsperado, ingresso.getPreco());
		
	}

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		
		this.ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);
		BigDecimal precoEsperado = new BigDecimal("32.5");
		assertEquals(precoEsperado, ingresso.getPreco());
	}

}

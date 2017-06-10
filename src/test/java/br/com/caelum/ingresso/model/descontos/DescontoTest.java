package br.com.caelum.ingresso.model.descontos;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTest {
	
	private Sala sala;
	private Filme filme;
	private Sessao sessao;
	
	@Before
	public void init() {
		this.sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		this.filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		sessao = new Sessao(LocalTime.now(), filme, sala);
	}

	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos() {
		
		Ingresso ingresso = new Ingresso(sessao, new DescontoDeTrintaPorCentoParaBancos());
		BigDecimal precoEsperado = new BigDecimal("22.75");
		assertEquals(precoEsperado, ingresso.getPreco());
		
	}

	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressoDeEstudante() {
		
		Ingresso ingresso = new Ingresso(sessao, new DescontoEstudante());
		BigDecimal precoEsperado = new BigDecimal("16.25");
		assertEquals(precoEsperado, ingresso.getPreco());
		
	}

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
		BigDecimal precoEsperado = new BigDecimal("32.5");
		assertEquals(precoEsperado, ingresso.getPreco());
	}

}

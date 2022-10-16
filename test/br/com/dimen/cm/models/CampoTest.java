package br.com.dimen.cm.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.dimen.cm.exception.ExplosionException;

public class CampoTest {

	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	
	@Test
	void testeVizinhoDistancia1Direita() {
		Campo vizinho = new Campo(3,4);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	
	@Test
	void testeVizinhoDistanciaEmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	@Test
	void testeVizinhoDistanciaEmBaixo() {
		Campo vizinho = new Campo(4,3);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	
	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2,2);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	
	@Test
	void testeNaoVizinhoDistancia() {
		Campo vizinho = new Campo(1,1);
		boolean result = campo.adicionarVizinho(vizinho);
		assertFalse(result);
	}
	
	@Test
	void testeValorPadraoAtributoMarcacao() {
		
		assertFalse(campo.isMarcado());
	}
	
    @Test
	void testeAltenarMarcacao() {
	    campo.altenarMarcacao();
		assertTrue(campo.isMarcado());
	}
    
    @Test
   	void testeAltenarMarcacaoDuasChamadas() {
   	    campo.altenarMarcacao();
   	    campo.altenarMarcacao();
   		assertFalse(campo.isMarcado());
   	}
    
    @Test
   	void testeAbrirNaoMinadoNaoMarcado() {
   	   assertTrue(campo.abrir());
   	}
    
    @Test
   	void testeAbrirNaoMinadoMarcado() {
   	    campo.altenarMarcacao();
   	    assertFalse(campo.abrir());
   	}
    
    @Test
   	void testeAbrirMinadoMarcado() {
   	    campo.altenarMarcacao();
   	    campo.minar();
   	    assertFalse(campo.abrir());
   	}
    
    @Test
   	void testeAbrirMinadoNaoMarcado() {
        campo.minar();
   	    assertThrows(ExplosionException.class, ()-> {
   	    	assertFalse(campo.abrir());
   	    });
        
   	}
    
    @Test
    void testAbrirComVizinho1() {
    	
    	
    	Campo campo11 = new Campo(1,1);
    	Campo campo22 = new Campo(2,2);
    	campo22.adicionarVizinho(campo11);
    	
    	campo.adicionarVizinho(campo22);
        campo.abrir();
    	
    	assertTrue(campo22.isAberto() && campo11.isAberto());
    }
    
    @Test
    void testAbrirComVizinho2() {
    	
    	
    	Campo campo11 = new Campo(1,1);
    	Campo campo12 = new Campo(1,1);
    	campo12.minar();
    	
    	Campo campo22 = new Campo(2,2);
    	campo22.adicionarVizinho(campo11);
    	campo22.adicionarVizinho(campo12);
    	
    	campo.adicionarVizinho(campo22);
        campo.abrir();
    	
    	assertTrue(campo22.isAberto() && campo11.isFechado());
    }
}

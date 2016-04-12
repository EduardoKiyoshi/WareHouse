/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.model.SimpleModel;
import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.model.WiredCube;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.gl.util.ShaderFactory;
import static java.lang.Math.cos;
import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 *
 * @author Hikari Kyuubi
 * 
 * Classe responsável por desenhar no canvas
 */
public class Scene02 implements GLEventListener { 
    
    private Shader shader; // Configuração de comportamento da GPU
    private SimpleModel cube; // Diretivas de desenho do modelo
    private SimpleModel sphere; // Diretivas de desenho do modelo
    
    private Matrix4 modelMatrix;
    private float delta;
    private float inc;
    private float delta2;
    public Scene02() {
        // Define Shader a ser usado (como ele funcionará)
        shader = ShaderFactory.getInstance(
                                 ShaderFactory.ShaderType.MODEL_MATRIX_SHADER);
        cube = new WiredCube(); // Define objeto a ser desenhado
        sphere = new Sphere();
        modelMatrix = new Matrix4();
        delta = 0f;
        delta2 = 0f;
        inc = 0.01f;
    }
    
    @Override // Configura a inicialização
    public void init(GLAutoDrawable glad) {
        GL3 gl = glad.getGL().getGL3(); // Contexto de desenho
        
        // "Limpar" buffer com determinada cor (R, G, B, alfa)
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); 
    
        shader.init(gl); // Manda shader para placa de vídeo
        shader.bind(); // Faz com que a GPU funcione de acordo com o shader 
        
        cube.init(gl, shader); // Associa o objeto à GPU e ao shader
        sphere.init(gl, shader);
        
        // Associa matriz, e define um nome referente a ele (dentro do shader)
        modelMatrix.init(gl, shader.getUniformLocation("u_modelMatrix"));
    }
    
    @Override // Chamado pelo animator
    public void display(GLAutoDrawable glad) {
        GL3 gl = glad.getGL().getGL3(); // Contexto de desenho
        
        // A cada atualização, limpa de acordo com a cor do buffer
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        // Cabeça 
        delta2 += 1;
        
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(0, 0.55f, 0);
        modelMatrix.scale(0.15f, 0.15f, 0.15f);
        
        modelMatrix.bind(); // Associa matriz à placa
        cube.bind(); // Seleciona objeto a ser ativado
        cube.draw(); // Desenha objeto ativo (deve ser o mesmo)
        
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(0.04f, 0.58f, 0);
        modelMatrix.scale(0.05f, 0.05f, 0.05f);
        
        modelMatrix.bind(); // Associa matriz à placa
        cube.bind(); // Seleciona objeto a ser ativado
        cube.draw(); // Desenha objeto ativo (deve ser o mesmo)
        
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(-0.04f, 0.58f, 0);
        modelMatrix.scale(0.05f, 0.05f, 0.05f);
        
        modelMatrix.bind(); // Associa matriz à placa
        cube.bind(); // Seleciona objeto a ser ativado
        cube.draw(); // Desenha objeto ativo (deve ser o mesmo)
        
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(0f, 0.52f, 0);
        modelMatrix.scale(0.05f, 0.05f, 0.05f);
        
        modelMatrix.bind(); // Associa matriz à placa
        cube.bind(); // Seleciona objeto a ser ativado
        cube.draw(); // Desenha objeto ativo (deve ser o mesmo)
        
        // Quadrado corpo 1
        modelMatrix.loadIdentity(); // Limpa matriz para matriz de identidade
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        
        modelMatrix.translate(0, 0.2f, 0);
        modelMatrix.scale(0.5f, 0.5f, 0.5f);
        
        modelMatrix.bind(); // Associa matriz à placa
        cube.bind(); // Seleciona objeto a ser ativado
        cube.draw(); // Desenha objeto ativo (deve ser o mesmo)
        
        
        // Quadrado corpo 2
        modelMatrix.loadIdentity(); // Limpa matriz para matriz de identidade
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(0, -0.2f, 0);
        modelMatrix.scale(0.3f, 0.3f, 0.3f);
       
        modelMatrix.bind(); // Associa matriz à placa
        cube.bind(); // Seleciona objeto a ser ativado
        cube.draw(); // Desenha objeto ativo (deve ser o mesmo)
        
        // Braço direito 1
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(0.35f, 0.3f, 0);
        modelMatrix.scale(0.10f, 0.10f, 0.10f);
       
        modelMatrix.bind(); // Associa matriz à placa
        sphere.bind();
        sphere.draw();
        
        // Braço direito 2
        
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(0.55f, 0.3f, 0);
        modelMatrix.scale(0.10f, 0.10f, 0.10f);
       
        modelMatrix.bind(); // Associa matriz à placa
        sphere.bind();
        sphere.draw();
        
        // Braço direito 3
        if (delta >= 0 || delta <= -0.4f) inc *= -1;
        delta += inc;
        
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        
        modelMatrix.translate(0, delta, 0);
        modelMatrix.translate(0.55f, 0.5f, 0);
        modelMatrix.scale(0.10f, 0.10f, 0.10f);
       
        modelMatrix.bind(); // Associa matriz à placa
        sphere.bind();
        sphere.draw();
        
        // Braço Esquerdo 1
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(-0.35f, 0.3f, 0);
        modelMatrix.scale(0.10f, 0.10f, 0.10f);
       
        modelMatrix.bind(); // Associa matriz à placa
        sphere.bind();
        sphere.draw();
        
        // Braço Esquerdo 2
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(-0.55f, 0.3f, 0);
        modelMatrix.scale(0.10f, 0.10f, 0.10f);
       
        modelMatrix.bind(); // Associa matriz à placa
        sphere.bind();
        sphere.draw();
        
        // Braço Esquerdo 3
        modelMatrix.loadIdentity();
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(0, -delta, 0);
        modelMatrix.translate(-0.55f, 0.1f, 0);
        modelMatrix.scale(0.10f, 0.10f, 0.10f);
       
        modelMatrix.bind(); // Associa matriz à placa
        sphere.bind();
        sphere.draw();
                
        // Perna Esquerda
        modelMatrix.loadIdentity(); // Limpa matriz para matriz de identidade
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(-0.1f, -0.55f, 0);
        modelMatrix.scale(0.15f, 0.45f, 0.2f);
       
        modelMatrix.bind(); // Associa matriz à placa
        cube.bind(); // Seleciona objeto a ser ativado
        cube.draw(); // Desenha objeto ativo (deve ser o mesmo)
       
        // Perna Direita
        modelMatrix.loadIdentity(); // Limpa matriz para matriz de identidade
        modelMatrix.rotate(delta2, 0, 1, 0);
        modelMatrix.rotate(10, 1, 1, 0);        
        modelMatrix.translate(0.1f, -0.55f, 0);
        modelMatrix.scale(0.15f, 0.45f, 0.2f);
       
        modelMatrix.bind(); // Associa matriz à placa
        cube.bind(); // Seleciona objeto a ser ativado
        cube.draw(); // Desenha objeto ativo (deve ser o mesmo)
       
    }
    
    @Override
    public void dispose(GLAutoDrawable glad) {
        
    }


    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {}
}

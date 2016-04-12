/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;
import br.usp.icmc.vicg.gl.core.Light;
import br.usp.icmc.vicg.gl.core.Material;
import br.usp.icmc.vicg.gl.jwavefront.JWavefrontObject;
import br.usp.icmc.vicg.gl.matrix.Matrix4;
import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.gl.util.ShaderFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JUNIOR
 */
public class Scene03 extends KeyAdapter implements GLEventListener{
    
  private final Shader shader; // Gerenciador dos shaders
  private final Matrix4 modelMatrix;
  private final Matrix4 projectionMatrix;
  private final Matrix4 viewMatrix;
  private final JWavefrontObject caja;
  private final JWavefrontObject pared;
  private final Light light;
  private float alpha;
  private float beta;
  private float delta;
  private int height;
  private int width;
  private int LARGO = 47;
  private int ANCHO = 28;
  private int ALTURA = 3;
  private int c = 1;
  private String[][] local;
   public Scene03() 
   {
        // Carrega os shaders
        shader = ShaderFactory.getInstance(ShaderFactory.ShaderType.COMPLETE_SHADER);
        modelMatrix = new Matrix4();
        projectionMatrix = new Matrix4();
        viewMatrix = new Matrix4();

        caja = new JWavefrontObject(new File("./warehouse/caja.obj"));
        pared = new JWavefrontObject(new File("./warehouse/pared.obj"));
        light = new Light();
        alpha = 0;
        beta = 0;
        delta = 5;
        this.local = new String[][]{
                            {
                                "============================",
                                "=||||||||||||||||||||||||||=",
                                "=|........................|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|..  ....  ....  ....00..|=",
                                "=|..  ....  ....  ....00..|=",
                                "=|..  ....  ....  ....00..|=",
                                "=|..  ....  ....  ....00..|=",
                                "=|..  ....  ....  ....00..|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|........................|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|........................|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|........................|=",
                                "=|.|||||||||.|.||||||||||||=",
                                "=|.|========.|.|============",
                                "=|...........|.||||||||||||=",
                                "=||||||||||=.|.||||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||222222||=",
                                "=||||||||||=.|.|=||222222||=",
                                "=||||||||||=.|.|=||222222||=",
                                "=||||||||||=.|.|=||222222||=",
                                "=||||||||||=.|.|=||222222||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=...|=||||||||||=",
                                "=||||||||||=||||=||||||||||=",
                                "=||||||||||======||||||||||="
                            },
                            {
                                "============================",
                                "=||||||||||||||||||||||||||=",
                                "=|........................|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|..  ....  ....  ....0 ..|=",
                                "=|..  ....  ....  ....0 ..|=",
                                "=|..  ....  ....  ....00..|=",
                                "=|..  ....  ....  ....00..|=",
                                "=|..  ....  ....  ....00..|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|........................|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|........................|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|........................|=",
                                "=|.|||||||||||.||||||||||||=",
                                "=|.|========||.|============",
                                "=|...........|.||||||||||||=",
                                "=||||||||||=.|.||||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=...|=||||||||||=",
                                "=||||||||||=||||=||||||||||=",
                                "=||||||||||======||||||||||="
                            },
                            {
                                "============================",
                                "=||||||||||||||||||||||||||=",
                                "=|........................|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|........................|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|........................|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|..  ....  ....  ....  ..|=",
                                "=|.||||..||||..||||..||||.|=",
                                "=|........................|=",
                                "=|.|||||||||||.||||||||||||=",
                                "=|.|========||.|============",
                                "=|...........|.||||||||||||=",
                                "=||||||||||=.|.||||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=.|.|=||||||||||=",
                                "=||||||||||=...|=||||||||||=",
                                "=||||||||||=||||=||||||||||=",
                                "=||||||||||======||||||||||="
                            }
                        };
  }
 public void guardarMedidas(int x,int y)
 {
     this.width=x;
     this.height=y;
 }
 @Override
 public void init(GLAutoDrawable drawable) {
    // Get pipeline
    GL3 gl = drawable.getGL().getGL3();

    // Print OpenGL version
    System.out.println("OpenGL Version: " + gl.glGetString(GL.GL_VERSION) + "\n");

    gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    gl.glClearDepth(1.0f);

    gl.glEnable(GL.GL_DEPTH_TEST);
    gl.glEnable(GL.GL_CULL_FACE);

    //inicializa os shaders
    shader.init(gl);

    //ativa os shaders
    shader.bind();

    //inicializa a matrix Model and Projection
    modelMatrix.init(gl, shader.getUniformLocation("u_modelMatrix"));
    projectionMatrix.init(gl, shader.getUniformLocation("u_projectionMatrix"));
    viewMatrix.init(gl, shader.getUniformLocation("u_viewMatrix"));

    try {
      //init the model
      caja.init(gl, shader);
      caja.unitize();
      caja.dump();
    } catch (IOException ex) {
      Logger.getLogger(Scene03.class.getName()).log(Level.SEVERE, null, ex);
    }

    //init the light
    light.setPosition(new float[]{14.0f, 15.0f, 20.0f, 0.0f});
    light.setAmbientColor(new float[]{1.0f, 1.0f, 1.0f, 1.0f });
    light.setDiffuseColor(new float[]{1.0f, 1.0f, 1.0f, 1.0f });
    light.setSpecularColor(new float[]{1.0f, 1.0f, 1.0f, 1.0f });
    light.init(gl, shader);
    
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    // Recupera o pipeline
    GL3 gl = drawable.getGL().getGL3();

    // Limpa o frame buffer com a cor definida
    gl.glClear(GL3.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);
    
    /*// vista general
    gl.glViewport(0, height/2, width/2, height/2);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glFrustum(-ar, ar, -1.0, 1.0, 2.0, 600.0);
    mirarA({14, 10, 50}, {14, 0, 35});
    visualizar();*/
    
    projectionMatrix.loadIdentity();
    projectionMatrix.ortho(
            -delta, delta, 
            -delta, delta, 
            -2 * delta, 2 * delta);
    projectionMatrix.bind();

    modelMatrix.loadIdentity();
    modelMatrix.rotate(beta, 0, 1.0f, 0);
    modelMatrix.rotate(alpha, 1.0f, 0, 0);
    modelMatrix.bind();

    viewMatrix.loadIdentity();
    viewMatrix.lookAt(
            1, 1, 1, 
            0, 0, 0, 
            0, 1, 0);
    viewMatrix.bind();

    light.bind();
    caja.draw();
    
    // Força execução das operações declaradas
    gl.glFlush();
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
    caja.dispose();
  }
  public KeyListener escucha() {
     KeyListener listener= new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
	    }        
            @Override
            public void keyPressed(KeyEvent e) {
              switch (e.getKeyChar()) {
                case '+'://faz zoom-in
                  delta = delta * 0.809f;
                  break;
                case '-'://faz zoom-out
                  delta = delta * 1.1f;
                  break;
                case 'w'://gira sobre o eixo-x
                  alpha = alpha - 5;
                  break;
                case 's'://gira sobre o eixo-x
                  alpha = alpha + 5;
                  break;
                case 'a'://gira sobre o eixo-y
                  beta = beta - 5;
                  break;
                case 'd'://gira sobre o eixo-y
                  beta = beta + 5;
                  break;
                default:
                       System.out.println("naniiiiii!!!!");
                       break;
              }
            }
	    @Override
	    public void keyReleased(KeyEvent e) {
		System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
	    }            
     };
     return listener;
  }

}

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
  private final JWavefrontObject base;
  private final JWavefrontObject estante;  
  private final Light light;
  private float alpha;
  private float beta;
  private float delta;
  private float omega;
  private int height;
  private int width;
  private int LADO = 10;
  private int LARGO = 47;
  private int ANCHO = 28;
  private int ALTURA = 3;
  private String[][] local;
  
  //posicao Inicial do carrinho
  private int heroPositionX = 14;
  private int heroPositionY = 24;
  private StringBuilder[][] novoLocal;
  
   public Scene03() 
   {
        // Carrega os shaders
        shader = ShaderFactory.getInstance(ShaderFactory.ShaderType.COMPLETE_SHADER);
        modelMatrix = new Matrix4();
        projectionMatrix = new Matrix4();
        viewMatrix = new Matrix4();

        caja = new JWavefrontObject(new File("./warehouse/caja.obj"));
        pared = new JWavefrontObject(new File("./warehouse/pared.obj"));
        base = new JWavefrontObject(new File("./warehouse/base.obj"));
        estante = new JWavefrontObject(new File("./warehouse/estante.obj"));
        light = new Light();
        alpha = 0;
        beta = 0;
        omega=0;
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
        //Inicializa StringBuilder
        novoLocal = new StringBuilder[local.length][local[0].length];              
        for(int i = 0; i < local.length;i++){
            for(int j = 0; j < local[i].length;j++){
                novoLocal[i][j] = new  StringBuilder( local[i][j] );
            }
        }   
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
      //init the model caja
      caja.init(gl, shader);
      caja.unitize();
      caja.dump();
      
      //init the model pared
      pared.init(gl, shader);
      pared.unitize();
      pared.dump();
      
      //init the model cuadro
      base.init(gl, shader);
      base.unitize();
      base.dump();
      
      //init the model estante
      estante.init(gl, shader);
      estante.unitize();
      estante.dump();
      
    } catch (IOException ex) {
      Logger.getLogger(Scene03.class.getName()).log(Level.SEVERE, null, ex);
    }

    //init the light
    light.setPosition(new float[]{14.0f, 15.0f, 20.0f, 0.0f});
    light.setAmbientColor(new float[]{1.0f, 1.0f, 1.0f, 1.0f });
    light.setDiffuseColor(new float[]{1.0f, 1.0f, 1.0f, 1.0f });
    light.setSpecularColor(new float[]{1.0f, 1.0f, 1.0f, 1.0f });
    light.init(gl, shader);
    
    
    //Coloca heroi na posicao inicial
    novoLocal[0][heroPositionY].replace(heroPositionX,heroPositionX+1, "h");
    setPositionX(heroPositionX);
    setPositionY(heroPositionY);
  }
 
  public void setPositionX(int x){
      this.heroPositionX += x;
  }
  public int getPositionX(){
      return this.heroPositionX;
  }
  
  public void setPositionY(int y){
      this.heroPositionY += y;
  }
  public int getPositionY(){
      return this.heroPositionY;
  }
 public class Punto
 {
    float x;
    float y;
    float z;
    Punto(float xo,float yo,float zo)
    {
        x=xo;
        y=yo;
        z=zo;
    }
 };
 public void guardarMedidas(int x,int y)
 {
     this.width=x;
     this.height=y;
 }
  void mirarA (Punto p, Punto pos)
  {   
      viewMatrix.lookAt(p.x, p.y, p.z, pos.x, pos.y, pos.z, 0, 1, 0);
  }
    public void drawCaja (float  x, float y, float z)
    {
        modelMatrix.push();
            modelMatrix.translate(x, y, z);
            modelMatrix.bind();
            caja.draw();
        modelMatrix.pop();
    }   
    public void drawEstante (int x, int y, int z)
    {
        if (y == 0)
        {
            modelMatrix.push();
                modelMatrix.translate(x, y, z);
                modelMatrix.bind();
                base.draw();
            modelMatrix.pop();
        }
        if (x == 5 || x == 11 || x == 17 || x == 23)
        {
            modelMatrix.push();
                modelMatrix.translate(x, y, z);
                modelMatrix.rotate(180, 0, 1, 0);
                modelMatrix.bind();
                estante.draw();
            modelMatrix.pop();
        }
        else
        {
            modelMatrix.push();
                modelMatrix.translate(x, y, z);
                modelMatrix.bind();
                estante.draw();
            modelMatrix.pop();;
        }
    }
    public void drawCuadro (int x, int z, boolean qr)
    {

    }
    public void drawPared (int x, int y, int z)
    {
        modelMatrix.push();
            modelMatrix.translate(x, y, z);
            modelMatrix.bind();
            pared.draw();
        modelMatrix.pop();
    }
  public void cargarLocal ()
  {
        //System.out.println(local[1][2].charAt(5));
        int i, j, k;
        for (k = 0 ; k < ALTURA ; k++)
            for (i = 0 ; i < LARGO ; i++)
                for (j = 0 ; j < ANCHO ; j++)
                    if (local[k][i].charAt(j) == '0' || local[k][i].charAt(j) == '2') {
                        drawCaja(j, k, i);;
                        if (i < 30)
                        drawEstante(j, k, i);;
                    }
                    else if (local[k][i].charAt(j)== ' ' || local[k][i].charAt(j) == '1' || local[k][i].charAt(j) == '2')
                        drawEstante(j, k, i);
                    else if (local[k][i].charAt(j) == '=')
                            {
                                    drawPared(j, k, i);;
                            }
   }

  @Override
  public void display(GLAutoDrawable drawable) {
    // Recupera o pipeline
    GL3 gl = drawable.getGL().getGL3();    
    // Limpa o frame buffer com a cor definida
    gl.glClear(GL3.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);

    // vista general
    
    //Proyeccion Ortonormal
    /*projectionMatrix.loadIdentity();
    projectionMatrix.ortho(
            -delta, delta, 
            -delta, delta, 
            -2 * delta, 2 * delta);
    projectionMatrix.bind();*/

    //Proyeccion Perspectiva
    projectionMatrix.loadIdentity();
    projectionMatrix.perspective(60.0f+delta,width/height, 2, 600);
    projectionMatrix.bind();
    
    viewMatrix.loadIdentity();
    mirarA(new Punto(14+alpha,12+beta,48+omega),new Punto(14+alpha,2+beta,33+omega));
    viewMatrix.bind();
   
    // Fuente de luz
    light.bind();
    
    //Dibujo de escena Almacen
    modelMatrix.loadIdentity();
    cargarLocal();
    /*
    modelMatrix.rotate(beta, 0, 1.0f, 0);
    modelMatrix.rotate(alpha, 1.0f, 0, 0);
    modelMatrix.bind();
    caja.draw();*/
    
    // Força execução das operações declaradas
    gl.glFlush();
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
    caja.dispose();
    pared.dispose();
    base.dispose();
    estante.dispose();
  }
  public KeyListener escucha() {
     KeyListener listener= new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
	    }        
            @Override
            public void keyPressed(KeyEvent e) {
              char a_char;
              int positionY = getPositionY();
              int positionX = getPositionX();
              if (e.getKeyCode() == KeyEvent.VK_UP) {                  
                    if(positionY > 0){
                        a_char = local[0][positionY - 1].charAt(positionX);
                        if(a_char == '.' ){
                                novoLocal[0][positionY].replace(positionX,positionX+1, ".");
                                novoLocal[0][positionY - 1].replace(positionX,positionX+1, "h");
                                setPositionY(-1);
                        }
                    }
              }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if(positionY < novoLocal[0].length){
                        a_char = novoLocal[0][positionY + 1].charAt(positionX);
                        if(a_char == '.' ){
                            novoLocal[0][positionY].replace(positionX,positionX+1, ".");
                            novoLocal[0][positionY+1].replace(positionX,positionX+1, "h");
                            setPositionY(1);
                        }
                    }  
              }
              if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if(positionX > 0){
                        a_char = novoLocal[0][positionY].charAt(positionX - 1);
                        if(a_char == '.' ){
                            novoLocal[0][positionY].replace(positionX,positionX+1, ".");
                            novoLocal[0][positionY].replace(positionX-1,positionX, "h");
                            setPositionX(-1);
                        }
                    }                    
              }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    if(positionX < novoLocal[0][positionY].length()){
                        a_char = novoLocal[0][positionY].charAt(positionX + 1);
                        if(a_char == '.' ){
                            novoLocal[0][positionY].replace(positionX,positionX+1, ".");
                            novoLocal[0][positionY].replace(positionX+1,positionX + 2, "h");
                            setPositionX(1);
                        }
                    }
              }  
              switch (e.getKeyChar()) {
                case '-'://faz zoom-in
                  if(delta<50)
                    delta = delta + 4.0f;
                  break;
                case '+'://faz zoom-out
                  if(delta>-60)
                    delta = delta - 4.0f;
                  break;
                case 'w'://
                  omega = omega - 1;
                  break;
                case 's'://
                  omega = omega + 1;
                  break;
                case 'a'://
                  alpha = alpha - 1;
                  break;
                case 'd'://
                  alpha = alpha + 1;
                  break;
                case 'z'://
                  beta = beta + 1;
                  break;
                case 'x'://
                  beta = beta - 1;
                  break;
                /*case '8'://
                  omega = omega - 1;
                  break;
                case '2'://
                  omega = omega + 1;
                  break;
                case '4'://
                  alpha = alpha - 1;
                  break;
                case '6'://
                  alpha = alpha + 1;  */      
                case '5'://
                  alpha=0;
                  beta=0;
                  omega=0;
                default:
                       System.out.println("naniiiiii!!!!");
                       break;
              }
            }
	    @Override
	    public void keyReleased(KeyEvent e) {
		System.out.println("Alfa: "+alpha+" Beta: "+beta+" Omega: "+omega);
	    }            
     };
     return listener;
  }

}

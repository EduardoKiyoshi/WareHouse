/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;
import com.jogamp.opengl.util.AnimatorBase;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

/**
 *
 * @author Hikari Kyuubi
 */
public class Main 
{
    
    public static void main(String args[]) 
    {
        // Get GL3 profile (to work with OpenGL 4.0)
        GLProfile profile = GLProfile.get(GLProfile.GL3);

        // Configurations
        GLCapabilities glcaps = new GLCapabilities(profile);
        glcaps.setDoubleBuffered(true);
        glcaps.setHardwareAccelerated(true);

        // Create canvas
        GLCanvas glCanvas = new GLCanvas(glcaps);

        // Add listener to panel
        Scene03 listener = new Scene03();
        glCanvas.addGLEventListener(listener);
        Frame frame = new Frame("WareHose");
        frame.setSize(1180, 680);
        frame.add(glCanvas);
        listener.guardarMedidas(frame.getWidth(),frame.getHeight());
        frame.addKeyListener(listener);
        final AnimatorBase animator = new FPSAnimator(glCanvas, 60);
        frame.setFocusable(true);
        frame.addKeyListener(listener.escucha());
        frame.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            new Thread(new Runnable() {
              @Override
              public void run() {
                animator.stop();
                System.exit(0);
              }
            }).start();
          }
        });
        frame.setVisible(true);
        animator.start();
    }
}
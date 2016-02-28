import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

@SuppressWarnings("deprecation")
public class main {
	
	 /** The fonts to draw to the screen */
    private TrueTypeFont font;
    private TrueTypeFont font2;
     
    /** Boolean flag on whether AntiAliasing is enabled or not */
    private boolean antiAlias = true;
	
	int mouse_x;
	int mouse_y;
	boolean leftButtonDown;

	 /** time at last frame */
	long lastFrame;
    
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	public void loadFont(){
		
		 // load a default java font
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, antiAlias);
		
		 // load font from file
       
	}
	
	 public void updateFPS() {
	        if (getTime() - lastFPS > 1000) {
	            Display.setTitle("FPS: " + fps);
	            fps = 0;
	             lastFPS += 1000;
	          }
	          fps++;
	     }
	    public int getDelta() {
	        long time = getTime();
	        int delta = (int) (time - lastFrame);
	        lastFrame = time;
	      
	        return delta;
	    }
	     
	    /**
	     * Get the accurate system time
	     * 
	     * @return The system time in milliseconds
	     */
	    public long getTime() {
	        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	    }
	    
	    public static float find_angle(float x_1, float y_1, float x_2, float y_2){
	        float tan_1;
	        float tan_2;

	        tan_1=y_1-y_2;
	        tan_2=x_1-x_2;

	        return (float)Math.atan2(tan_1,tan_2);
	    }

	    
	    public void update(int delta) {
	       
	    	mouse_x = Mouse.getX(); // will return the X coordinate on the Display.
	    	mouse_y = Mouse.getY(); // will return the Y coordinate on the Display.
	    	leftButtonDown = Mouse.isButtonDown(0);
	        
	         
	        updateFPS(); // update FPS Counter
	    }
	     
	    
		
	    @SuppressWarnings("deprecation")
		public void start() {
	    	
	        try {
	        Display.setDisplayMode(new DisplayMode(900,500));
	        Display.create();
	    } catch (LWJGLException e) {
	        e.printStackTrace();
	        System.exit(0);
	    }
	        
	        lastFPS = getTime();

	        
	        // init OpenGL
	        GL11.glMatrixMode(GL11.GL_PROJECTION);
	        GL11.glLoadIdentity();
	        GL11.glOrtho(0, 900, 0, 500, 1, -1);
	        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	        
	        
	        while (!Display.isCloseRequested()) {
	        	int delta = getDelta();
	        	
	            // Clear the screen and depth buffer
	            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
	            
	            Color.white.bind();
	            //font.drawString(100, 100, "NICE LOOKING FONTS!", Color.yellow);

	            update(delta);
	            
	            Display.update();
	            
	            Display.sync(60);
	            
	        }
	        Display.destroy();
	        
	    }

	public static void main(String[] args) {
		main MyMain = new main();
		MyMain.start();
		// TODO Auto-generated method stub
		
	}

}

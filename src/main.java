import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.Font;
import java.io.InputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

@SuppressWarnings("deprecation")
public class main {
	
	boolean exit_store;
	
	float final_total;
	
	float peanuts_amount;
	int book_amount;
	int movie_amount;
	
	int width = 800;
	int height = 600;
	
	int click_delay;
	
	private Texture background;
	
	 /** The fonts to draw to the screen */
    private TrueTypeFont font2;
     
    /** Boolean flag on whether AntiAliasing is enabled or not */
    private boolean antiAlias = true;
	
	int mouse_x;
	int mouse_y;
	boolean leftButtonDown;
	boolean rightButtonDown;

	 /** time at last frame */
	long lastFrame;
    
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	item peanuts;
	item book;
	item movie;
	
	
	public boolean location_clicked(int min_x,int max_x,int min_y,int max_y){
	    if(mouse_x>min_x && mouse_x<max_x && mouse_y>min_y && mouse_y<max_y && leftButtonDown)
	        return true;
	    else return false;
	}
	
	
	public void loadImage(){
		try{
		
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("background.png"));
			
			peanuts = new item(405,377,0.1,TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("peanuts.png")));
			book = new item(510,395,1,TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("book.png")));
			movie = new item(575,387,1,TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("movie.png")));
			

		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFont(){
		
		 // load font from file
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("OpenSans-Regular.ttf");
             
            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(24f); // set font size
            font2 = new TrueTypeFont(awtFont2, antiAlias);
             
        } catch (Exception e) {
            e.printStackTrace();
        }
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
	    
	    public void update(int delta) {
	       
	    	mouse_x = Mouse.getX(); // will return the X coordinate on the Display.
	    	mouse_y = Mouse.getY(); // will return the Y coordinate on the Display.

	    	mouse_y=600-mouse_y;
	    	
	    	leftButtonDown = Mouse.isButtonDown(0);
	    	rightButtonDown = Mouse.isButtonDown(1);
	    	
	    	
	    	if(location_clicked(160,300,380,600))
	    		exit_store=true;
	    

	    	final_total = ((0.5f*peanuts_amount)+(1.8f*peanuts_amount))+((1.03f*book_amount)+(book_amount*9))+((0.05f*movie_amount)+(movie_amount*14.99f));
	    		
	    	
	    	click_delay++;	
	    		
	    	
	    
	         
	        updateFPS(); // update FPS Counter
	    }
	     
	    public void drawTexture(Texture newTexture,int x, int y){
	    	 newTexture.bind(); // or GL11.glBind(texture.getTextureID());
	            
	    		GL11.glBegin(GL11.GL_QUADS);
	    			GL11.glTexCoord2f(0,0);
	    			GL11.glVertex2f(x,y);
	    			GL11.glTexCoord2f(1,0);
	    			GL11.glVertex2f(x+newTexture.getTextureWidth(),y);
	    			GL11.glTexCoord2f(1,1);
	    			GL11.glVertex2f(x+newTexture.getTextureWidth(),y+newTexture.getTextureHeight());
	    			GL11.glTexCoord2f(0,1);
	    			GL11.glVertex2f(x,y+newTexture.getTextureHeight());
	    		GL11.glEnd();
	    }
		
	    @SuppressWarnings("deprecation")
		public void start() {
	    	
	    	
	        try {
	        Display.setDisplayMode(new DisplayMode(width,height));
	        Display.create();
	    } catch (LWJGLException e) {
	        e.printStackTrace();
	        System.exit(0);
	    }
	        
	        lastFPS = getTime();


			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glShadeModel(GL11.GL_SMOOTH);        
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_LIGHTING);                    
	 
			GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
	        GL11.glClearDepth(1);                                       
	 
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	 
	        GL11.glViewport(0,0,width,height);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			 
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, width, height, 0, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
	        
	        loadFont();
	        loadImage();
			
	        
	        while (!Display.isCloseRequested() && !exit_store) {
	        	int delta = getDelta();
	        	
	        	
	            // Clear the screen and depth buffer
	            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
	            
	            Color.white.bind();
	            
	            
	           
	    		drawTexture(background,0,0);
	    		peanuts.drawTexture();
	    		book.drawTexture();
	    		movie.drawTexture();
	    		
	    		font2.drawString(210,520, "Exit", Color.black);
	    		font2.drawString(150,320, "Welcome to Danny's Assorted Goods Market!", Color.black);
	    		font2.drawString(400,470, "Peanuts    Book     Movie", Color.black);
	    		font2.drawString(400,495, "$1.80/lb   $9.00    $14.99", Color.black);
	    		font2.drawString(360,520, "Qty: " + String.format("%.1f",peanuts_amount) + " lb", Color.black);
	    		font2.drawString(520,520, book_amount + "", Color.black);
	    		font2.drawString(604,520, movie_amount + "", Color.black);
	    		font2.drawString(360,545, "Total: $" + String.format("%.2f",final_total), Color.red);
	    		
	    		
	            
	    		update(delta);
	    		
	    		peanuts.setMouseValues(mouse_x, mouse_y, leftButtonDown, rightButtonDown);
	    		peanuts_amount+=peanuts.update();
	    		if(peanuts_amount<0.0)peanuts_amount=0.0f;
	    		
	    		book.setMouseValues(mouse_x, mouse_y, leftButtonDown, rightButtonDown);
	    		book_amount+=book.update();
	    		if(book_amount<0.0)book_amount=0;
	    		
	    		movie.setMouseValues(mouse_x, mouse_y, leftButtonDown, rightButtonDown);
	    		movie_amount+=movie.update();
	    		if(movie_amount<0.0)movie_amount=0;
	    		
	    		
	            
	            
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

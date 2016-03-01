import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class item {
	
	int x;
	int y;
	int mouse_x;
	int mouse_y;
	boolean leftButtonDown;
	boolean rightButtonDown;
	
	private Texture texture;
	
	public void setMouseValues(int newMouseX, int newMouseY, boolean newLeftButtonDown, boolean newRightButtonDown){
		mouse_x = newMouseX;
		mouse_y = newMouseY;
		leftButtonDown = newLeftButtonDown;
		rightButtonDown = newRightButtonDown;
		
		//MY NAME ISDANNYIM POROOFUSSINULL PROWGRAMMAR
	}
	
	public void item(int newX, int newY,Texture newTexture){
		x = newX;
		y = newY;
		texture = newTexture;
	}
	
	public boolean location_clicked(int min_x,int max_x,int min_y,int max_y){
	    if(mouse_x>min_x && mouse_x<max_x && mouse_y>min_y && mouse_y<max_y && leftButtonDown)
	        return true;
	    else return false;
	}
	
	public void update(){

	}
	
	public void draw(){
	
	   texture.bind(); // or GL11.glBind(texture.getTextureID());
	            
	   GL11.glBegin(GL11.GL_QUADS);
	   		GL11.glTexCoord2f(0,0);
	    	GL11.glVertex2f(x,y);
	    	GL11.glTexCoord2f(1,0);
	    	GL11.glVertex2f(x+texture.getTextureWidth(),y);
	    	GL11.glTexCoord2f(1,1);
	    	GL11.glVertex2f(x+texture.getTextureWidth(),y+texture.getTextureHeight());
	    	GL11.glTexCoord2f(0,1);
	    	GL11.glVertex2f(x,y+texture.getTextureHeight());
	    GL11.glEnd();
	 }	
		
}


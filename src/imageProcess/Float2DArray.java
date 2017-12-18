package imageProcess;

import java.util.Arrays;

public class Float2DArray {

	protected float[]data;
	protected int width;
	protected int height;
	
	public Float2DArray(int width,int height){
		this.width=width;
		this.height=height;
		data=new float[width*height];//变成一维数组
	}
	
	public float getValue(int x,int y){
		int index=y*width+x;
		return data[index];
	}
	
	public float getZero(int x, int y)//是否溢出，溢出 返回0，不溢出 返回getValue
    {
        if (x >= width)
            return 0;

        if (y >= height)
            return 0;

        if (x < 0)
            return 0;

        if (y < 0)
            return 0;

        return getValue(x,y);
    }
	
	public void setValue(int x,int y,float value){
		int index=y*width+x;
		data[index]=value;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void reset(){
		Arrays.fill(data, 0f);
	}
	
}

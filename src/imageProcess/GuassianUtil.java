package imageProcess;

public class GuassianUtil {

	//ws rs xs
	public static void downsample(Float2DArray src,Float2DArray dst){
		int ws=2*src.width;//宽度
		int rs=0;
		for(int r=0;r<dst.data.length;r+=dst.width){
			//降维，缩小
			int xs=0;
			for(int x=0;x<dst.width;++x){
				dst.data[r+x]=src.data[rs+xs];
				xs+=2;
			}
			rs+=ws;//下一行
		}
	}
	
	/**
	 * return a integer that is flipped in the range [0 ... mod - 1]
	 * 
	 * @param a
	 *            the value to be flipped
	 * @param range
	 *            the size of the range
	 * @return a flipped in range like a ping pong ball
	 */
	//反转？返回反转值
	public static final int flipInRange(int a, int mod) {
		int p = 2 * mod;
		if (a < 0)
			a = p + a % p;
		if (a >= p)
			a = a % p;
		if (a >= mod)
			a = mod - a % mod - 1;
		return a;
	}
	
	
	/**
	 * convolve an image with a horizontal and a vertical kernel simple
	 * straightforward, not optimized---replace this with a trusted better
	 * version soon
	 * 
	 * @param input
	 *            the input image
	 * @param h
	 *            horizontal kernel都是一维数组?水平卷积核
	 * @param v
	 *            vertical kernel都是一维数组？垂直卷积核
	 * 
	 * @return convolved image
	 */
	public static Float2DArray convolveSeparable(Float2DArray input, float[] h,
			float[] v){
		Float2DArray output = new Float2DArray(input.width, input.height);
		Float2DArray temp = new Float2DArray(input.width, input.height);
		
		int hl=h.length/2;
		int vl=v.length/2;
		
		//不太明白是干什么
		int xl=input.width-h.length+1;//宽-卷积长+1
		int yl=input.height-v.length+1;//高-卷积长+1
		
		
		// create lookup tables for coordinates outside the image range
		//为图像范围之外的坐标创建查找表
		int[] xb=new int[h.length+hl-1];//数组[水平卷积长+卷积一半长-1]
		int[] xa=new int[h.length+hl-1];
		for(int i=0;i<xb.length;++i){
			xb[i]=flipInRange(i-hl,input.width);//a=i-卷积一半
			xa[i]=flipInRange(i+xl,input.width);//a=i+宽-卷积长+1
		}
 		
		int[] yb = new int[v.length + vl - 1];//数组[垂直卷积长+卷积一半长-1]
		int[] ya = new int[v.length + vl - 1];
		for (int i = 0; i < yb.length; ++i) {
			yb[i] = input.width * flipInRange(i - vl, input.height);
			ya[i] = input.width * flipInRange(i + yl, input.height);
		}
		
		//???
		xl+=hl;//宽-卷积长+1+卷积一半
		yl+=vl;
		
		//horizontal convolution per row
		//每行水平卷积
		int rl=input.height*input.width;
		for (int r=0;r<rl;r+=input.width){//行
			for(int x=hl; x<xl; ++x){//hl=卷积一半，xl=宽-卷积长+1+卷积一半=宽-卷积一半+1
				int c=x-hl;//c 循环了几次，列？
				float val=0;
				for(int xk=0;xk<h.length;++xk){
					val+=h[xk]*input.data[r+c+xk];
				}
				temp.data[r+x]=val;
			}
			for (int x = 0; x < hl; ++x) {
				float valb = 0;
				float vala = 0;
				for (int xk = 0; xk < h.length; ++xk) {
					valb += h[xk] * input.data[r + xb[x + xk]];
					vala += h[xk] * input.data[r + xa[x + xk]];
				}
				temp.data[r + x] = valb;
				temp.data[r + x + xl] = vala;
			}
		}
		
		// vertical convolution per column
		//每列垂直卷积
		rl = yl * temp.width;
		int vlc = vl * temp.width;
		for (int x = 0; x < temp.width; ++x) {
			for (int r = vlc; r < rl; r += temp.width) {
				float val = 0;
				int c = r - vlc;
				int rk = 0;
				for (int yk = 0; yk < v.length; ++yk) {
					val += v[yk] * temp.data[c + rk + x];
					rk += temp.width;
				}
				output.data[r + x] = val;
			}
			for (int y = 0; y < vl; ++y) {
				int r = y * temp.width;
				float valb = 0;
				float vala = 0;
				for (int yk = 0; yk < v.length; ++yk) {
					valb += h[yk] * temp.data[yb[y + yk] + x];
					vala += h[yk] * temp.data[ya[y + yk] + x];
				}
				output.data[r + x] = valb;
				output.data[r + rl + x] = vala;
			}
		}
		
		
		return output;
	}
	
	public static Float2DArray blurWith1DGaussian(Float2DArray input,
			float sigma){
		Float2DArray output = new Float2DArray(input.width, input.height);
		
		float avg,kernelsum=0;
		float[] kernel=creatGaussianLernel1D(sigma,true);
		int filterSize=kernel.length;
		
		//get kernel sum
		for(double value:kernel)
			kernelsum+=value;
		
		
		
		
		return output;
	}
	
	
	
	public static float[]creatGaussianLernel1D(float sigma,boolean normalize){
		int size=3;
		float[] gaussianKernel;
		
		if(sigma<=0){
			gaussianKernel=new float[3];
			gaussianKernel[1]=1;
		}else{
			size=Math.max(3, (int) (2 * (int) (3 * sigma + 0.5) + 1));
			System.out.println("Gaussian 1D windows size: "+size);
			float two_sq_sigma=2*sigma*sigma;//二维？
			gaussianKernel=new float[size];
			
			for(int x = size / 2; x >= 0; --x) {
				float val=(float) Math.exp(-(float)(x * x)/two_sq_sigma);
				
				gaussianKernel[size/2-x]=val;//高斯卷积 对称
				gaussianKernel[size/2+x]=val;
				
				
				if(normalize){
					float sum=0;
					for(float value:gaussianKernel)
						sum+=value;//累积卷积值
					
					for(int i=0;i<gaussianKernel.length;i++)
						gaussianKernel[i]/=sum;//化为百分比小数  总和为1
				}
				
				return gaussianKernel;
			}
			
			
		}
		
		
		return gaussianKernel;
	}
	
	
}

package imageProcess;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;

public class ImageRead {
	
	
	
	
	
	public String Imageopen(){
		JFileChooser chooser = new JFileChooser(); //创建选择文件对象
		chooser.setDialogTitle("请选择文件");//设置标题
		chooser.setMultiSelectionEnabled(true);  //设置只能选择文件
		FileNameExtensionFilter filter = new FileNameExtensionFilter("tif", "tif");//定义可选择文件类型
		chooser.setFileFilter(filter); //设置可选择文件类型
		chooser.showOpenDialog(null); //打开选择文件对话框,null可设置为你当前的窗口JFrame或Frame
		File file = chooser.getSelectedFile(); //file为用户选择的图片文件
		String filename=file.getAbsolutePath();
		System.out.println(filename);//获得图片绝对路径
		
		return filename;
	}
	
	//opencv
	public void read(String filename){
		Mat image=Imgcodecs.imread(filename);
		
		int depth=image.depth();
		System.out.println(String.format("%s",depth));
	}
	
	
	
	

}

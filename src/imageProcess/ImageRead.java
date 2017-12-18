package imageProcess;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;

public class ImageRead {
	
	
	
	
	
	public String Imageopen(){
		JFileChooser chooser = new JFileChooser(); //����ѡ���ļ�����
		chooser.setDialogTitle("��ѡ���ļ�");//���ñ���
		chooser.setMultiSelectionEnabled(true);  //����ֻ��ѡ���ļ�
		FileNameExtensionFilter filter = new FileNameExtensionFilter("tif", "tif");//�����ѡ���ļ�����
		chooser.setFileFilter(filter); //���ÿ�ѡ���ļ�����
		chooser.showOpenDialog(null); //��ѡ���ļ��Ի���,null������Ϊ�㵱ǰ�Ĵ���JFrame��Frame
		File file = chooser.getSelectedFile(); //fileΪ�û�ѡ���ͼƬ�ļ�
		String filename=file.getAbsolutePath();
		System.out.println(filename);//���ͼƬ����·��
		
		return filename;
	}
	
	//opencv
	public void read(String filename){
		Mat image=Imgcodecs.imread(filename);
		
		int depth=image.depth();
		System.out.println(String.format("%s",depth));
	}
	
	
	
	

}

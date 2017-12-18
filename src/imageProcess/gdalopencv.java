package imageProcess;

import org.opencv.core.Mat;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.imgcodecs.Imgcodecs;  

import org.gdal.gdal.Band;
import org.gdal.gdal.Dataset; 
import org.gdal.gdal.Driver; 
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconst;
import org.gdal.gdalconst.gdalconstConstants; 

public class gdalopencv {
	public static void main(String[] args) {
//		ImageRead ir=new ImageRead();		
//		String filename_tif=ir.Imageopen();
		String filename_tif="D:\\pinjietupian\\c1.tif";
		
		//使用OenCV for Java
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		//注册GDAL
		gdal.AllRegister();
		Dataset hDataset=gdal.Open(filename_tif,gdalconstConstants.GA_ReadOnly);
		//判断是否非空
        if(hDataset==null||hDataset.GetRasterCount()==0){
                return;
        }
//		Driver hDriver=hDataset.GetDriver();
		int iXSize=hDataset.GetRasterXSize();
		int iYSize=hDataset.getRasterYSize();
		
		int XBuffer=iXSize;
		int YBuffer=iYSize;
		int bandCount=hDataset.GetRasterCount();
		
		int[] dataArrayR=new int[iXSize*iYSize];
		int[] dataArrayG=new int[iXSize*iYSize];
		int[] dataArrayB=new int[iXSize*iYSize];
		
		int[] bandR=new int[]{1};
		int[] bandG=new int[]{2};
		int[] bandB=new int[]{3};
		
		int dataType=hDataset.GetRasterBand(1).GetRasterDataType();
		
//		hDataset.ReadRaster(0, 0, iXSize, iYSize, iXSize, iYSize, dataType, dataArrayR, bandR, 0);
//		hDataset.ReadRaster(0, 0, iXSize, iYSize, iXSize, iYSize, dataType, dataArrayG, bandG, 0);
//		hDataset.ReadRaster(0, 0, iXSize, iYSize, iXSize, iYSize, dataType, dataArrayB, bandB, 0);
		Band band1=hDataset.GetRasterBand(1);
		Band band2=hDataset.GetRasterBand(2);
		Band band3=hDataset.GetRasterBand(3);
		band1.ReadRaster(0, 0, iXSize, iYSize,dataArrayR);
		band2.ReadRaster(0, 0, iXSize, iYSize,dataArrayG);
		band3.ReadRaster(0, 0, iXSize, iYSize,dataArrayB);
		
		//GDAL to Mat
		Mat originalImage=new Mat(iYSize,iXSize,CvType.CV_8UC3);
		
		for(int j=0;j<iYSize;j++){
			for(int i=0;i<iXSize;i++){
				originalImage.put(j, i, new int[]{dataArrayB[j*iXSize+i],
						dataArrayG[j*iXSize+i],dataArrayR[j*iXSize+i]});
				}
			}
		Imgcodecs.imwrite("D:\\pinjietupian\\5.jpg",originalImage);
		
		System.out.println("finish");
		hDataset.delete();
	}	
}


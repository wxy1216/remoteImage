package imageProcess;

import org.opencv.core.Mat;
import org.opencv.core.CvType;

import org.gdal.gdal.Band;
import org.gdal.gdal.Dataset; 
import org.gdal.gdal.Driver; 
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconst;
import org.gdal.gdalconst.gdalconstConstants; 


public class ImageMain {
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		ImageRead ir=new ImageRead();
//		
//		String filename_tif=ir.Imageopen();
//		//String filename_tif="D:\\pinjietupian\\c1.tif";
//		gdal.AllRegister();
//		Dataset hDataset=gdal.Open(filename_tif,gdalconstConstants.GA_ReadOnly);
//		if (hDataset == null) 
//		{ 
//		System.err.println("GDALOpen failed - "+ gdal.GetLastErrorNo()); 
//		System.err.println(gdal.GetLastErrorMsg()); 
//		System.exit(1); 
//		} 
//		
//		Driver hDriver=hDataset.GetDriver();
//		System.out.println("Driver: " + hDriver.getShortName() + "/" + hDriver.getLongName()); 
//		int iXSize=hDataset.GetRasterXSize();
//		int iYSize=hDataset.getRasterYSize();
//		System.out.println("Size is"+iXSize+","+iYSize);
//		
//		Band band=hDataset.GetRasterBand(1);
//
////		byte[] byteArray=new byte[iXSize*iYSize];
////		byte[] byteArray2=new byte[iXSize*iYSize];
////		band.ReadRaster(0,0, iXSize, iYSize, gdalconst.GDT_Byte,byteArray);
//		
//		//读取图像
//		int [] intArray=new int [iXSize*iYSize];
//		band.ReadRaster(0, 0, iXSize, iYSize,intArray);
//		
//		//化为0-255
//		double[] doubleArray=new double[iXSize*iYSize];
//		double [] minmax=new double[2];
//		band.ComputeRasterMinMax(minmax);// 最小值minmax[0]，最大值minmax[1]
//		double temp;
//		double dif=minmax[1]-minmax[0];
//		for(int j=0;j<iYSize;j++){
//			for(int i=0;i<iXSize;i++){
//				temp=(double) intArray[j*iXSize+i]-minmax[0];
//				doubleArray[j*iXSize+i]=temp/dif*255;
//			}
//		}
//		
////		//字节类型读出来都是-1？？？
////		for(int j=0;j<iYSize;j++){
////			for(int i=0;i<iXSize;i++){
////				byte bVal=byteArray[j*iXSize+i]; //ranging from -128 to 127
////				int nVal=((int)bVal) & 0xff; //remapped to range from 0 to 255
////				//int nVal2;
////				//doubleArray[j*iXSize+i]=(double) nVal;
////				//intArray[j*iXSize+i]=nVal;
////				byte bVal2=(byte) ((nVal>127)? nVal-256:nVal);
////				byteArray2[j*iXSize+i]=bVal2;
////			}
////		}
//		 
//
//		//写图像
//		Dataset hDataset1=hDriver.Create("D:\\pinjietupian\\c3.tif", iXSize, iYSize,gdalconst.GDT_Byte);
//		//Dataset hDataset2=hDriver.CreateCopy("D:\\pinjietupian\\c4.tif", hDataset);
//				
//		Band band2=hDataset1.GetRasterBand(1);
//		band2.WriteRaster(0, 0, iXSize, iYSize, doubleArray);
//		band2.ReadRaster(0, 0, iXSize, iYSize,doubleArray);
////		for(int i=1200;i<1500;i++){
////			System.out.println(doubleArray[i]);
////		}
//				
//		hDataset.delete();
//		hDataset1.delete();
//		//hDataset2.delete();
//
//	}
//
//}

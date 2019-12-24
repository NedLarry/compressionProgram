/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_zipit;

import java.awt.Desktop;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 *
 * @author Chinedu
 */
public class CompressionSystem {
    
    
    /*
    This method for compressing text documents
    */
    
    public  void compressTest (File source, File destination) throws IOException{
        
        byte [] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);
        GZIPOutputStream gzip = new GZIPOutputStream(fos);
        
        int read;
        
        while((read = fis.read(buffer)) != -1){
            gzip.write(buffer,0, read);
        }
        gzip.finish();
        gzip.close();
        fis.close();
        fos.close();
        
    }
    
    
    /*
    This method for decompressing text documents
    */
    public  void decompressTest(File source, File destination) throws IOException{
        
        byte [] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(source);
        GZIPInputStream gzis = new GZIPInputStream(fis);
        FileOutputStream fos = new FileOutputStream(destination);
        
        
        int read;
        while((read = gzis.read(buffer)) != -1){
            fos.write(buffer,0, read);
        }
        
        gzis.close();
        fis.close();
        fos.close();
        
    }
    
    
    /*
    reduce the quality of images, reducing space
    */
    
    public  void compressJPEG (File originalImage, File compressedImage, float compressionQuality) throws IOException
    {
        RenderedImage image = ImageIO.read(originalImage);
        ImageWriter jpegWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam jpegWriteParam = jpegWriter.getDefaultWriteParam();
        jpegWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpegWriteParam.setCompressionQuality(compressionQuality);
        
        try (ImageOutputStream output = ImageIO.createImageOutputStream(compressedImage)){
            
            jpegWriter.setOutput(output);
            IIOImage outputImage = new IIOImage(image, null, null);
            jpegWriter.write(null, outputImage, jpegWriteParam);
        }
        jpegWriter.dispose();
    }
    
    
    public  void openTargetLocation(String targetLocation) throws IOException
    {
        Desktop desktop = Desktop.getDesktop();
        
        File targetDirectory = new File(targetLocation);
        
        desktop.open(targetDirectory);
        
        
        
    }
    
    
    public void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut)throws IOException
    {
        
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
        
    }
    
    
    public File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
         
        String destDirPath = destinationDir.getParent();
        String destFilePath = destFile.getCanonicalPath();
         
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
         
        return destFile;
    }
    
    
    
    public void uncompressFile(String zipFilePath, String destinationDirectory)
    {
        File dir = new File(destinationDirectory);
        // create output directory if it doesn't exist
        if(!dir.exists()) 
            dir.mkdirs();
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                
                String fileName = ze.getName();
                File newFile = new File(destinationDirectory + File.separator + fileName);
                System.out.println("Unzipping to "+ newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}

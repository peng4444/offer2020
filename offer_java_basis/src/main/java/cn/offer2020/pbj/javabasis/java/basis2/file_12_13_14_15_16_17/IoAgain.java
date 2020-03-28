package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
利用ByteArrayOutputStream缓存InputStream，以便InputStream的重复使用
具体实现：先读取inputStream中的数据 然后写入 ByteArrayOutputStream中 下次可以重复从ByteArrayOutputStream中读取

缺点：要缓存这个InputStream内存压力可能是比较大的。如果第一次读取只是需要判断该文件流的类型，文件编码等用的话，往往不需要所有的InputStream的数据，或者说只需要前n个字节的话，
这样一来缓存整个InputStream实际上是一种浪费
*/
public class IoAgain {

  private ByteArrayOutputStream byteArrayOutputStream=null;

  public IoAgain(InputStream inputStream) throws IOException {
     if(inputStream==null){
         return;
     }
     byteArrayOutputStream=new ByteArrayOutputStream();
     byte[] buffer=new byte[1024];
     int len;
     //先将流读取出来 然后再写入到ByteArrayOutputStream中
     while ((len=inputStream.read(buffer))>-1){
         byteArrayOutputStream.write(buffer,0,len);
     }
     byteArrayOutputStream.flush();
  }

  public ByteArrayInputStream getInputeStream() throws IOException {
      return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  }

  public static void main(String[] args) throws IOException {
     InputStream is=new BufferedInputStream(new FileInputStream("d://a.txt"));
     IoAgain again=new IoAgain(is);
     ByteArrayInputStream byteArrayOutputStream=again.getInputeStream();
     InputStreamReader isr=new InputStreamReader(byteArrayOutputStream);
     BufferedReader br=new BufferedReader(isr);
     String str=null;
     while ((str=br.readLine())!=null){
         System.out.print(str+"\n");
     }
  }
}

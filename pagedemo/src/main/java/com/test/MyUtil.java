package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
 
public final class MyUtil {
 
    private MyUtil() {
        throw new AssertionError();
    }
 
    public static void fileCopy(String source, String target) throws IOException {
        try (InputStream in = new FileInputStream(source)) {
            try (OutputStream out = new FileOutputStream(target)) {
                byte[] buffer = new byte[4096];
                int bytesToRead;
                while((bytesToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
            }
        }
    }
 
    public static void fileCopyNIO(String source, String target) throws IOException {
        try (FileInputStream in = new FileInputStream(source)) {
            try (FileOutputStream out = new FileOutputStream(target)) {
                FileChannel inChannel = in.getChannel();
                FileChannel outChannel = out.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                while(inChannel.read(buffer) != -1) {
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
            }
        }
    }
    
    /**
     * 统计给定文件中给定字符串的出现次数
     * 
     * @param filename  文件名
     * @param word 字符串
     * @return 字符串在文件中出现的次数
     */
    public static int countWordInFile(String filename, String word) {
        int counter = 0;
        try (FileReader fr = new FileReader(filename)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    int index = -1;
                    while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return counter;
    }
    
    public static void showDirectory(File f) {
    	_walkDirectory(f, 0);
    }
    
    private static void _walkDirectory(File f, int level) {
    	if(f.isDirectory()) {
    		for(File temp : f.listFiles()) {
    			_walkDirectory(temp, level + 1);
    		}
    	} else {
    		for(int i = 0; i < level - 1; i++) {
    			System.out.print("\t");
    		}
    		System.out.println(f.getName());
    	}
    }
    
    public static void main(String[] args) throws IOException {
//    	showDirectory(new File("C:\\Users\\jianglan.LONG\\Desktop\\统计返佣及订单小工具(优化版)"));
    	testNIo("C:\\Users\\jianglan.LONG\\Desktop\\统计返佣及订单小工具(优化版)");
	}

	private static void testNIo(String ph) throws IOException {
		Path initPath = Paths.get(ph);
        Files.walkFileTree(initPath, new SimpleFileVisitor<Path>() {
 
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) 
                    throws IOException {
                System.out.println(file.getFileName().toString());
                return FileVisitResult.CONTINUE;
            }

        });
	}
}

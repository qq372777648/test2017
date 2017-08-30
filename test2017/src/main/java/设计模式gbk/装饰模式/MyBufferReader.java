package װ��ģʽ;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyBufferReader {
	private FileReader  fillReader;
	public MyBufferReader(FileReader  fillReader){
		this.fillReader = fillReader;
	}
	public String readeAll() throws IOException {
		StringBuffer sb = new StringBuffer();
		int r = fillReader.read();//Reads a single character. �����ַ���asc��
		while (r!=-1) {
			System.out.println((char)r);
			sb.append((char)r);
			r = fillReader.read();//�������¶�
		}
		return sb.toString();
	}
	public void close() throws IOException {
		fillReader.close();
	}
	
	
	public static void main(String[] args) {
		try {
			FileReader fileReader=new FileReader("C://xxx.txt");
			System.out.println(new MyBufferReader(fileReader).readeAll());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

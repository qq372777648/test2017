package org.zdp.jxls;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jxls.transformer.XLSTransformer;

/**
 * Excel������.
 */
public class ExcelUtil {
	/**
	 * ����ģ������Excel�ļ�.
	 * @param templateFileName ģ���ļ�.
	 * @param list ģ���д�ŵ�����.
	 * @param resultFileName ���ɵ��ļ�.
	 */
	public void createExcel(String templateFileName, List<?> list, String resultFileName){
		try { 
			//����XLSTransformer����
			XLSTransformer transformer = new XLSTransformer();
			
			//��ȡjava��Ŀ������·��
			URL url = this.getClass().getClassLoader().getResource("");
			
			//�õ�ģ���ļ�·��
			String srcFilePath = url.getPath() + templateFileName;
			Map<String,Object> map = new HashMap<String,Object>(); 
			map.put("list", list);
			String destFilePath = url.getPath() + resultFileName;
			
			//����Excel�ļ�
			transformer.transformXLS(srcFilePath, map, destFilePath);
		} catch (Exception e) {
			throw new RuntimeException("error happens...", e);
		}
	}
}

package cn.edu.ustb.tr.xml;

import org.dom4j.*;
import java.io.*;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;


/**
 * ��xpathʵ��һ������XML�ļ���ͨ�÷����Թ��Ժ����Ҫ
 * @author phantom
 *
 */
public class FrameXpath {
	private String path;
	public Document document;
	/**
	 * ����Ĺ��췽�����������xml�ļ���·��
	 * @param path ·��
	 */
	public FrameXpath(String path){
		this.path = path;
		this.load();
	}
	/**
	 * �÷�����Ҫ������XML�ĵ�������һ��document��
	 */
	public void load(){
		File file = new File(path);
		if (file.exists()){
			//����һ��XML��ȡ�Ķ���
			SAXReader sr = new SAXReader();
			try {
				//����ȡ�Ķ�����뵽document����
				document = sr.read(file);
				System.out.println("read file success");
			} catch (DocumentException e) {
				// TODO: handle exception
				System.out.println("the file is wrong :"+ path);
			}
		}
		else{
			System.out.println("the file is not exist");
		}
	}
	/**
	 * ���ָ��·���µĽڵ�
	 * @param elementPath �ڵ�λ��
	 * @return ������Ҫ�Ľڵ�
	 */
	public Element getElement(String elementPath){
		return (Element)document.selectSingleNode(elementPath);
	}
	/**
	 * ���ָ��Ϊ֮��������Ԫ��
	 * @param elementPath ָ����·��
	 * @return �����������Ԫ���б�
	 */
	@SuppressWarnings("unchecked")//���߱������������ڡ�unchecked����ɵľ�����Ϣ
	public List<Element> getElementList(String elementPath){
		return document.selectNodes(elementPath);
	}
	/**
	 * ��ĳһ��Ԫ�������е���Ԫ�ز�������Ϣ���뵽һ��map��
	 * @param element ���ڵ�
	 * @return ���ص����ӽڵ��map
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getChildrenInfo(Element element){
		Map<String,String> map = new HashMap<String, String>();
		//��ĳһ���ڵ��µ������ӽڵ����Ϣ���뵽map��
		List<Element> list = element.elements();
		for (Element ele : list){
			map.put(ele.getName(), ele.getText());
		}
		return map;
	}
	/**
	 * �ж�һ��Ԫ���Ƿ����
	 * @param elementPath
	 * @return
	 */
	public boolean isExist(String elementPath){
		boolean flag = false;
		Element element = this.getElement(elementPath);
		if (element != null){
			flag = true;
		}
		return flag;
	}
	/**
	 * ���ָ��Ԫ�ص�ֵ
	 * @param elementPath
	 * @return
	 */
	public String getElementText(String elementPath){
		Element element = this.getElement(elementPath);
		if (element != null){
			return element.getText().trim();
		}
		else{
			return null;
		}
	}
	
	public List<Element> getElementList(Element element){
		List<Element> list = element.elements();
		return list ;
	}
	
	public void removeNode(String xpath) throws IOException{
		Element e = getElement(xpath);
		e.getParent().remove(e);
		writerXml("schame_table_1.xml");
		
	}
	public void writerXml(String name) throws IOException{
		XMLWriter writer = new XMLWriter(new FileWriter(new File(name)));
		writer.write(document);
		writer.close();
	}

	
	
	//����G:\\eclipse\\javacode\\RestfulService\\schame_table_1.xml
	public static void main(String[] args) throws IOException{
		FrameXpath fxpath = new FrameXpath("G:\\eclipse\\javacode\\TableRestful\\schame_tables_1.xml");
//		Element e = fxpath.getElement("//table[@id=3]");
//		fxpath.removeNode("//table[@id=3]");
////		System.out.println(e.attributeValue("name"));
//		List<Element> tables = fxpath.getElementList("//table");
//		System.out.println(tables.get(0).attributeValue("name"));
//		List<Element> rows = fxpath.getElementList("/schame/table[1]/row");
//		System.out.println(rows.size());
//		List<Element> column = fxpath.getElementList("/schame/table[1]/row[1]/column");
//		System.out.println(column.size());
		Element e = fxpath.getElement("//table[@id=3]");
		System.out.println(e.attributeValue("name"));
//		e.addAttribute("name", "test_name_3");
//		System.out.println(e.attributeValue("name"));
//		Element eroot = fxpath.document.getRootElement();
//		System.out.println(eroot.getName());
//		Element table = eroot.addElement("table");
//		table.addAttribute("name", "name_post");
//		table.addAttribute("id", "300");
//		fxpath.writerXml("schame_tables_1.xml");
	}

	
}


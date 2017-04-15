package cn.edu.ustb.tr.xml;

import org.dom4j.*;
import java.io.*;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;


/**
 * 有xpath实现一个解析XML文件的通用方法以供以后的需要
 * @author phantom
 *
 */
public class FrameXpath {
	private String path;
	public Document document;
	/**
	 * 该类的构造方法传入解析的xml文件的路径
	 * @param path 路径
	 */
	public FrameXpath(String path){
		this.path = path;
		this.load();
	}
	/**
	 * 该方法将要解析的XML文档解析成一个document树
	 */
	public void load(){
		File file = new File(path);
		if (file.exists()){
			//构建一个XML读取的对象
			SAXReader sr = new SAXReader();
			try {
				//将读取的对象放入到document树中
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
	 * 获得指定路径下的节点
	 * @param elementPath 节点位置
	 * @return 返回需要的节点
	 */
	public Element getElement(String elementPath){
		return (Element)document.selectSingleNode(elementPath);
	}
	/**
	 * 获得指定为之下所有子元素
	 * @param elementPath 指定的路径
	 * @return 返回所求的子元素列表
	 */
	@SuppressWarnings("unchecked")//告诉编译器忽略由于“unchecked”造成的警告信息
	public List<Element> getElementList(String elementPath){
		return document.selectNodes(elementPath);
	}
	/**
	 * 求某一个元素下所有的子元素并将其信息存入到一个map中
	 * @param element 父节点
	 * @return 返回的是子节点的map
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getChildrenInfo(Element element){
		Map<String,String> map = new HashMap<String, String>();
		//将某一个节点下的所有子节点的信息存入到map中
		List<Element> list = element.elements();
		for (Element ele : list){
			map.put(ele.getName(), ele.getText());
		}
		return map;
	}
	/**
	 * 判断一个元素是否存在
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
	 * 获得指定元素的值
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

	
	
	//测试G:\\eclipse\\javacode\\RestfulService\\schame_table_1.xml
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


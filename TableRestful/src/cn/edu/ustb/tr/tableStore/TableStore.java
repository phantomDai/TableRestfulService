package cn.edu.ustb.tr.tableStore;
import java.util.Map;

import org.bouncycastle.crypto.modes.gcm.Tables1kGCMExponentiator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import cn.edu.ustb.tr.bean.*;
import cn.edu.ustb.tr.xml.*;

/**
 * 单例模式保证只有一个对象
 * @author phantom
 *
 */
public class TableStore {
	private  Map<String,Table> store ;
	private  TableStore instance = null ;
	
	
	public TableStore(){
		store = new HashMap<String,Table>();
		initOneTable();
	}
	//从xml文件中读入数据初始化store
	private  void initOneTable(){
		CreateTable createTable = new CreateTable();
		this.store = createTable.getMapFromXML();
	}
	public  Map<String,Table> getStore(){
		
		return this.store ;
	}
	
	public  void deleteStore(String id) throws IOException{
		
		CreateTable createTable = new CreateTable();
		this.store.remove(id);
		createTable.createXML(this.store);
	}
	
	
	public  void updateXML(String id,String name) throws IOException {
		FrameXpath fxpath = new FrameXpath("G:\\eclipse\\javacode\\TableRestful\\schame_tables_1.xml");
		Element e = fxpath.getElement("//table[@id=" + id + "]");
		e.addAttribute("name", name);
		fxpath.writerXml("schame_tables_1.xml");
	}
	public void newOneTable(String id,String name) throws IOException{
		FrameXpath fxpath = new FrameXpath("G:\\eclipse\\javacode\\TableRestful\\schame_tables_1.xml");
		Element eroot = fxpath.document.getRootElement();
		System.out.println(eroot.getName());
		Element table = eroot.addElement("table");
		table.addAttribute("name", name);
		table.addAttribute("id", id);
		fxpath.writerXml("schame_tables_1.xml");
	}
	
	
	private void writerXML(Document document,String name) throws IOException{
		XMLWriter writer = new XMLWriter(new FileWriter(new File(name)));
		writer.write(document);
		writer.close();
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
//		System.out.println(TableStore.getStore().get("1").getRows().get(1).getName());
//		TableStore.getStore().remove("300");
//		System.out.println(TableStore.getStore().size());
//		System.out.println(TableStore.getStore().size());
//		deleteStore("299");
		TableStore tableStore = new TableStore();
//		tableStore.deleteStore("296");
		System.out.println(tableStore.store.size());
		
		
		
	}
	
}

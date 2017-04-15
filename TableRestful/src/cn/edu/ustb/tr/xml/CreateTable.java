package cn.edu.ustb.tr.xml;
import org.dom4j.*;
import org.dom4j.io.XMLWriter;

import java.io.*;
import cn.edu.ustb.tr.bean.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * ����ʵ�ִ���һ��������xml������ݽṹΪ��table������row��row������column
 * @author phantom
 *
 */
public class CreateTable {
	
	





	public CreateTable(){}
	//����һ��column
	private Column implementOneColumn(String name,String i,String text){
		Column c = new Column(name, i, text);
		return c ;
	}
	//����һ��Row
	private Row implementOneRow(String name,String id,List<Column> column){
		Row r = new Row(name, id, column) ;
		return r ;
	}
	//����һ��table
	private Table impleementOneTable(String name,String id,List<Row> row){
		Table t = new Table(name, id, row) ;
		return t ;
	}
	
	//ʵ����һ��Row


	private Row generateRow(String name,String id,int numOfColumn){
		
		List<Column> column = new ArrayList<Column>();
		Random random = new Random();
		Column c = null;
		for (int i = 0; i < numOfColumn; i++) {
			c = implementOneColumn(id, String.valueOf(i+1), String.valueOf(random.nextInt(3000)));
			column.add(c);
		}
		Row r = new Row(name, id, column);
		return r ; 
		
	}
	
	//ʵ����һ��table
	private Table generateTable(String name,String id,int numOfRow){
		List<Row> rlist = new ArrayList<Row>();
		for (int i = 0; i < numOfRow; i++) {
			Row row = generateRow(id, String.valueOf(i+1), numOfRow) ;
			rlist.add(row);
		}
		Table t = new Table(name, id, rlist);
		return t ;
	}

	//����һ��tableList
	public Map<String,Table> getTableMap(){
		Map<String,Table> store = new HashMap<String,Table>() ;
		Table t = null ;
		Random random = new Random();
		for (int i = 0; i < 300;i++){
			
			t = generateTable("name_" + String.valueOf(i+1), String.valueOf(i+1), random.nextInt(8)+1);
			store.put(String.valueOf(i+1), t);
		}
		return store ;
	}
	
	
	//�����ɵ�Map��ӡ��һ��xml��
	public void createXML() throws IOException{
		Element root = DocumentHelper.createElement("schame");
		Document document = DocumentHelper.createDocument(root);
		//����ڵ�֮�������ӽڵ��Լ�����
		Map<String,Table> store = new HashMap<String,Table>() ;
		store = getTableMap();
		System.out.println();
		int numOfTables = store.size();
		for (int i = 1; i < numOfTables+1; i++){
			Element table = root.addElement("table");
			table.addAttribute("name",store.get(String.valueOf(i)).getName());
			table.addAttribute("id", store.get(String.valueOf(i)).getId());
			int numOfRow = store.get(String.valueOf(i)).getRows().size();
			for (int j = 0; j < numOfRow; j++){
				Element row = table.addElement("row");
				row.addAttribute("name", store.get(String.valueOf(i)).getRows().get(j).getName());
				row.addAttribute("id", store.get(String.valueOf(i)).getRows().get(j).getId());
				//���ĳһ���µ�����
				int numOfColumn = store.get(String.valueOf(i)).getRows().get(j).getColumn().size();
				for (int co = 0; co < numOfColumn; co++){
					Element column = row.addElement("column");
					column.addAttribute("name", store.get(String.valueOf(i)).getRows().get(j).getColumn().get(co).getName());
					column.addAttribute("id", store.get(String.valueOf(i)).getRows().get(j).getColumn().get(co).getId());
					column.setText(store.get(String.valueOf(i)).getRows().get(j).getColumn().get(co).getText());
				}
			}
		}
		writerXML(document,"schame_tables_1.xml");
	}
	
	//�����ɵ�Map��ӡ��һ��xml��
		public void createXML(Map<String,Table> store) throws IOException{
			Element root = DocumentHelper.createElement("schame");
			Document document = DocumentHelper.createDocument(root);
			//����ڵ�֮�������ӽڵ��Լ�����

			int numOfTables = store.size();
			for (int i = 1; i < numOfTables+1; i++){
				if(store.get(String.valueOf(i)) != null){
					
				Element table = root.addElement("table");
				table.addAttribute("name",store.get(String.valueOf(i)).getName());
				table.addAttribute("id", store.get(String.valueOf(i)).getId());
				int numOfRow = store.get(String.valueOf(i)).getRows().size();
				for (int j = 0; j < numOfRow; j++){
					Element row = table.addElement("row");
					row.addAttribute("name", store.get(String.valueOf(i)).getRows().get(j).getName());
					row.addAttribute("id", store.get(String.valueOf(i)).getRows().get(j).getId());
					//���ĳһ���µ�����
					int numOfColumn = store.get(String.valueOf(i)).getRows().get(j).getColumn().size();
					for (int co = 0; co < numOfColumn; co++){
						Element column = row.addElement("column");
						column.addAttribute("name", store.get(String.valueOf(i)).getRows().get(j).getColumn().get(co).getName());
						column.addAttribute("id", store.get(String.valueOf(i)).getRows().get(j).getColumn().get(co).getId());
						column.setText(store.get(String.valueOf(i)).getRows().get(j).getColumn().get(co).getText());
					}
				}
			  }
			}
			writerXML(document,"schame_tables_1.xml");
		}
	
	
	
	
	private void writerXML(Document document,String name) throws IOException{
		XMLWriter writer = new XMLWriter(new FileWriter(new File(name)));
		writer.write(document);
		writer.close();
	}
	/**
	 * ����XML�ļ����õ�һ��tables
	 * @return tables
	 */
	public Map<String,Table> getMapFromXML(){
		Map<String,Table> tables = new HashMap<String,Table>();
		FrameXpath frameXpath = new FrameXpath("G:\\eclipse\\javacode\\TableRestful\\schame_tables_1.xml");
		//������е�table
		List<Element> e_tables = frameXpath.getElementList("//table");
		List<Table> table = new ArrayList<Table>();
		int numOfTables = e_tables.size();
		for (int i = 0 ; i < numOfTables ; i++){
			//��ȡÿһ��TableԪ�ص�rows
			List<Element> rows = frameXpath.getElementList("/schame/table[" + String.valueOf(i+1) + "]/row");
			List<Row> row = new ArrayList<Row>();
			//���row�ĸ���
			int numOfRows = rows.size();
			//���ÿһ��row�µ�column
			for(int j = 0 ; j < numOfRows ; j++){
				List<Column> col = new ArrayList<Column>();
				List<Element> columns =  frameXpath.getElementList("/schame/table[" + String.valueOf(i+1) + "]/row[" + String.valueOf(j+1) + "]/column");
				for(int co = 0 ; co < numOfRows ; co++){
					Column c = new Column(columns.get(co).attributeValue("name"), columns.get(co).attributeValue("id"), columns.get(co).getText());
					col.add(c);
				}
				
				Row ro = new Row(rows.get(j).attributeValue("name"), rows.get(j).attributeValue("id"), col);
				row.add(ro);
			}
			Table ta = new Table(e_tables.get(i).attributeValue("name"), e_tables.get(i).attributeValue("id"), row);
			tables.put(String.valueOf(i+1), ta);
		}
		return tables ;
	}
	
	public void updataXML(String id,String name) throws IOException{
		Map<String,Table> tables = new HashMap<String, Table>();
		tables = getMapFromXML();
		
		createXML(tables);
		
	}
	
	
	
	
	
	//����map������
		public static void main(String[] args) throws IOException {
			List<Row> row = new ArrayList<Row>();
			Table table = new Table("name_1", "1", row);
			CreateTable createTable = new CreateTable();
			
		}
	
}

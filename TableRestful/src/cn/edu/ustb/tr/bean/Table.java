package cn.edu.ustb.tr.bean;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

import java.util.List;

@XmlRootElement
public class Table {
	
	private String name ;
	private String id ;
	private List<Row> rows ;
	public Table(){}
	public Table(String name,String id,List<Row> rows){
		setId(id);
		setName(name);
		setRows(rows);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement(value="row")
	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	
}

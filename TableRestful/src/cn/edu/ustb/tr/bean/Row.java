package cn.edu.ustb.tr.bean;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

import java.util.List;
@XmlRootElement
public class Row {
	//行有自己的名字、id、column
	private String name ;	
	private String id ;
	private List<Column> column ; 
	
	
	
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
	@XmlElement(value="column")
	public List<Column> getColumn() {
		return column;
	}
	public void setColumn(List<Column> column) {
		this.column = column;
	}

	public Row(){}
	public Row(String name,String id,List<Column> column){
		setColumn(column);
		setId(id);
		setName(name);
	}
}

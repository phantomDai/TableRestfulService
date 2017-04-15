package cn.edu.ustb.tr.client;

import javax.print.attribute.standard.Media;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
import cn.edu.ustb.tr.bean.*;

public class TableClient {
	
	/**
	 * 该方法可以实现客户端调用所有的tables
	 */
	public static void getTables(WebResource r){
		//获取从Web资源返回的内容
		String xmlres = r.accept(MediaType.APPLICATION_XML).get(String.class);
		System.out.println(xmlres);
		//获得返回的一些信息
		ClientResponse response = r.get(ClientResponse.class);
		System.out.println(response.getStatus());
		System.out.println(response.getHeaders().get("Content-Type"));
		String entity = response.getEntity(String.class);
		System.out.println(entity);
		//获得Table的实例
		GenericType<List<Table>> genericType = new GenericType<List<Table>>(){};
		List<Table> tables = r.accept(MediaType.APPLICATION_XML).get(genericType);
		Table table = tables.get(0);
		System.out.println(table.getName());
	}
	/**
	 * 获得一个table的实例
	 * @param r
	 * @param id
	 */
	public static void getTable(WebResource r,String id){
		GenericType<JAXBElement<Table>> generic = new GenericType<JAXBElement<Table>>(){};
		JAXBElement<Table> jaxbtable = r.path(id).accept(MediaType.APPLICATION_XML).get(generic);
		
		Table table = jaxbtable.getValue();
		System.out.println("the name of the table is :"+ table.getName());
	}
	
	public static void postForm(WebResource r, String id,String name){
		Form form = new Form();
//		form.add("id", id);
		form.add("name", name);
		ClientResponse response = r.path(id).type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
//		System.out.println(response.getEntity(String.class));
	}
//	public static void putOneTable(WebResource r, Table t){
//		ClientResponse response = r.path(t.getId()).accept(MediaType.APPLICATION_XML).put(ClientResponse.class,t);
//		System.out.println(response.getStatus());
//	}
	public static void putOneTable(WebResource r, String id,String name){
		Form form = new Form();
//		form.add("id", id);
		form.add("name", name);
		ClientResponse response = r.path(id).type(MediaType.APPLICATION_FORM_URLENCODED).put(ClientResponse.class,form);
		System.out.println(response.getStatus());
		
		
		
		
	}
	
	
	
	
	
	public static void deleteOneTable(WebResource r,String id){
		ClientResponse response = r.path(id).delete(ClientResponse.class);
		System.out.println(response.getStatus());
	}
	
	public static void main(String[] args) {
		//创建一个客户端
		Client c = Client.create();
		//创建一个Web资源

		WebResource r = c.resource("http://127.0.0.1:8086/schame/tables/");
		//进行POST测试
//		String id = "500" ;
//		String name = "name_POST_500" ;
//		postForm(r, id, name);
		//进行PUT测试
//		List<Row> row = new ArrayList<Row>();
//		Table table = new Table("name_3", "3", row);
//		putOneTable(r, "3","name_put3");
		
		//进行 delete测试
		String delete_id = "1" ;
		deleteOneTable(r, delete_id);
//		postForm(r, "300", "post_test");
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}

package cn.edu.ustb.tr.restful;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.sun.jersey.multipart.FormDataParam;
import com.sun.research.ws.wadl.Request;

import cn.edu.ustb.tr.bean.*;
import cn.edu.ustb.tr.tableStore.*;
import cn.edu.ustb.tr.xml.CreateTable;
import cn.edu.ustb.tr.bean.*;

import java.io.IOException;
import java.util.*;
/**
 * 该类是服务类
 * @author phantom
 *
 */
@WebService(targetNamespace="http://www.ustb.edu.cn/sei/ws")
@Path(value="/schame")
@Produces(MediaType.APPLICATION_XML)
public class TableService {
	
	@GET
	@Path(value="/tables/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Table getTable(@PathParam(value="id")String id){
		TableStore tableStore = new TableStore();
		Table table = tableStore.getStore().get(id);
		return table ;
	}
	
	
	
	@GET
	@Path(value="/tables")
	@Produces(MediaType.APPLICATION_XML)
    public List<Table> getTables(){
		List<Table> tables = new ArrayList<Table>();
		TableStore tableStore = new TableStore();
		for(int i = 0; i < tableStore.getStore().size();i++){
			Table table = tableStore.getStore().get(String.valueOf(i+1));
			tables.add(table);
		}
		return tables;
	}
	

	
	
	
	@POST
	@Path(value="/tables/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_XML)
	public void newOneTable(@PathParam("id") String id,
            @FormDataParam("name") String name
			) throws IOException{
		TableStore tableStore = new TableStore();
		tableStore.newOneTable(id, name);
	}
	
	
	
	
	@PUT
	@Path(value="/tables/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	public Response putTable(@PathParam(value="id") String id,
                             @FormDataParam("name") String name
			) throws IOException{

		return putAndGetResponse(id,name);
	}
	private Response putAndGetResponse(String id,String name) throws IOException{
		TableStore tableStore = new TableStore();
		if(tableStore.getStore().containsKey(id)){
			tableStore.updateXML(id,name);
			return Response.ok().build();
		}else{
			return Response.status(Status.BAD_REQUEST).build();
		}
		
	}
	
	@DELETE
	@Path(value="/tables/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response deleteTable(@PathParam(value="id")String id) throws IOException{
		TableStore tableStore = new TableStore();
		if (tableStore.getStore().containsKey(id)){
			tableStore.deleteStore(id);
		return Response.ok().build();
		}else{
			return Response.status(Status.BAD_REQUEST).build();
		}
	}	
}

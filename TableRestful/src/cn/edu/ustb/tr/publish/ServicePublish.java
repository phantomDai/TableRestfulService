package cn.edu.ustb.tr.publish;

import cn.edu.ustb.tr.restful.*;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

/**
 * �����������
 * @author phantom
 *
 */
public class ServicePublish {
	public static void main(String[] args){
	JAXRSServerFactoryBean rs = new JAXRSServerFactoryBean() ;
	TableService ss = new TableService() ;
	rs.setServiceBean(ss) ;
	rs.setAddress("http://127.0.0.1:8086/") ;
	rs.create() ;
	
	}
}

package org.dbs.gateway.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request_trace")
public class RequestTrace implements Serializable {
	private static final long serialVersionUID = -4874261633452751056L;
	private Integer id;
	private String url;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	

	public RequestTrace(String url) {
		super();
		this.url = url;
	}



	public RequestTrace(Integer id, String url) {
		super();
		this.id = id;
		this.url = url;
	}



	public RequestTrace() {
		super();
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	@Override
	public String toString() {
		return "["+ id + "\t" + url + "]";
	}
	
	

}
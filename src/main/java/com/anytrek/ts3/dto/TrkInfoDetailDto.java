package com.anytrek.ts3.dto;

import javax.persistence.Column;

import com.anytrek.ts3.model.TrkInfo;
import com.fasterxml.jackson.annotation.JsonView;


/**
 * trkInfo扩展    增加一些关联表属性
 * @author John
 * date 2018 M10 8
 */
public class TrkInfoDetailDto extends TrkInfo{
    /** 版本号 */
    private static final long serialVersionUID = 4034635723711775295L;

    @Column(name = "model_name")
    @JsonView(View.Summary.class) 
    private String modelName;
    

    @Column(name = "org_name")
    @JsonView(View.Summary.class) 
    private String orgName;
    
    @Column(name = "batch_name")
    @JsonView(View.Summary.class) 
    private String batchName;
    
    //固件版本名称
    @Column(name = "file_name")
    @JsonView(View.Summary.class) 
    private String fileName;
    
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	} 
    
    
    
}

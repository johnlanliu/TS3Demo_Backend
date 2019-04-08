package com.anytrek.ts3.dto;


/**
 *  用于标记返回的json格式包含哪些内容
 * 1.Controller的方法标记用于返回的View 即在方法处增加注解： @JsonView(View.Summary.class)  
 * 2.Model 标记需要转换为json格式的字段  即在字段处增加注解：@JsonView(View.Summary.class) 
 * 
 * @author John
 * date 2018 M10 5
 */
public class View {
	public interface Summary {}
	public interface SummaryWithDetail extends Summary {}
	public interface SummaryWithTrkInfo extends Summary {}
	public interface SummaryWithTrkStatus extends Summary {}
	public interface SummaryWithTrkAll extends SummaryWithTrkInfo,SummaryWithTrkStatus {}
}

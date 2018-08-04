package blog.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;
//登录action，操作后结果处理页面
public class Result {
	//code 状态码：成功：000000，失败：999999
	private String code;
	//错误信息
	private String message;
	//返回的数据(链式结构，这里就不使用泛型T) data:{"data","123"}
	private Map<String,Object> data=new HashMap<String,Object>();
	
	public static Result success(){
		Result result=new Result();
		result.setCode("000000");
		result.setMessage("操作成功");
		return result;
	}
	
	public static Result error(String string){
		Result result=new Result();
		result.setCode("999999");
		if(StringUtils.isEmpty(string))
			result.setMessage("操作失败");
		else
			result.setMessage(string);
		return result;
	}
	
	//往data中填写信息，这项，文件上传用到了，返回上传后文件的地址
	public Result add(String key, Object value) {
		this.getData().put(key, value);
		return this;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
}

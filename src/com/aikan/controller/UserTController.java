package com.aikan.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aikan.entity.ManagerT;
import com.aikan.entity.UserT;
import com.aikan.service.ManagerTService;
import com.aikan.service.UserTService;
import com.aikan.util.MyMD5Util;

@Controller
//���������ǰ�����@RequestMapping�����Ը�����ع���ÿ������@RequestMapping
@RequestMapping("/manager/userT")
public class UserTController {
 
	//�ֶ���ָ����ʵ������󵽴˳�Ա�ֶα�����
	@Resource(name="userTServiceImpl")
	private UserTService userTService;
	
	//ͷ���ϴ���Ĭ��·��������Ŀ��Ŀ¼��ʼ
	private String savePath="/images/icon";

	@RequestMapping("/tolist.action")
	public String toList(){
		return "backgroud/user/list";
	}	
	
	@RequestMapping("/dologout.action")
	public String doLogout(HttpSession session){
		//ע������
		session.removeAttribute("userT");
		// ֻҪ���������ǰ׺(redirect:��forward:)����Ѱ��ҳ��Ĺ��̾Ͳ���ʹ��ԭ�������ø�ʽ
		// ����ֱ��ȥѰ��ǰ׺�������ʵ��ҳ��(��������.action������)
		// ���е�һ��/������ǵ�ǰ��Ŀ�ĸ�Ŀ¼��SpringMVC�����Ǵ������
		return "redirect:/index.jsp";
	}
	/**
	 * �û�ע��
	 * @param userT
	 * @param file
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/doregisterajax.action")
	@ResponseBody
	public Map doRegisterAjax(@ModelAttribute("userT") UserT userT, @RequestParam("imgFile") MultipartFile file,
			HttpSession session,HttpServletRequest request){

		ServletContext application=request.getSession().getServletContext();
		String headerDir=application.getInitParameter("headerDir");
		if(headerDir!=null&&!"".equals(headerDir)){
			//�����ⲿ���ã�����ͷ���ϴ�·��
			this.savePath=headerDir;
		}
		
		//�ҵ��ϴ�·���ڷ������ϵ�ʵ��·��
		String realPath=application.getRealPath(savePath);
		File saveDir=new File(realPath);
		if(!saveDir.exists()){
			//������·�������ڣ��򴴽���
			saveDir.mkdirs();
		}
		
		//���ϴ����ļ�������
		String fileName=System.nanoTime()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		boolean isUploadOK=false;
		File targetFile=new File(saveDir,fileName);
		try{
		//���ϴ����ļ����浽������ָ����Ŀ�ĵ�
			file.transferTo(targetFile);
            //�ϴ��ɹ�
			isUploadOK=true;
			
		}catch(IllegalStateException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		if(!isUploadOK){
			//�ϴ�ʧ��
			//return "index";
			Map data=new HashMap<>();
			data.put("result", -2);
			return data;
		}
		//�����ϴ�ͷ�����Ŀ���·����user����

		userT.setUserImg(savePath+"/"+fileName);
		
		//�û��������
		String password=userT.getUserPassword();
		String epass=MyMD5Util.encode(password);
		userT.setUserPassword(epass);
		
		int result=userTService.save(userT);
		if(result!=1){
			//���ʧ�ܣ�ɾ��ͷ��
			targetFile.delete();
		}else{
			session.setAttribute("userT", userT);
		}
		
		Map data=new HashMap<>();
		data.put("result", result);
		return data;
	}
	/**
	 * ���û���¼
	 * @param userName
	 * @param userPassword
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/dologinajax.action")
	@ResponseBody
	public Map doLoginAjax(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword,
			HttpSession session,HttpServletResponse response){
		//��½����
		UserT userT=userTService.login(userName, userPassword);
		UserT userT2=(UserT) session.getAttribute("userT");
		boolean result=false;
		if(userT!=null&& userT2==null){
			//��½�ɹ�
			//�ض�����ɹ�ҳ��
			//�����û���¼��Ϣ�����ڻỰ�У��Ա���ҳ��֮�䶼����ʹ��
			session.setAttribute("userT",userT);
			result=true;
			System.out.println("------------------UserTController.doLoginAjax");
		}
		Map data=new HashMap<>();
		data.put("result",result);
		return data;
	}
}


package com.lunwen.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lunwen.bean.*;
import com.lunwen.Dao.*;
import com.google.gson.Gson;
import com.lunwen.DBUtil.*;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.lunwen.bean.*;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ShowDao sd = new ShowDao();
        List<lunwen> list = sd.select();
        int n = 0;
        int[] array = new int[10000000];
		String[] sarray = new String[10000000];
		StringBuffer sb = new StringBuffer();
		HashMap<String, Integer> has = new HashMap<String,Integer>();//�򿪹�ϣ���ַ����ʹ���key�����ʣ������ʹ���value�����ʳ��ֵĴ�����
		int r=0;
        for(lunwen l : list) {
//        	System.out.println(l.getAbs());
        	n++;
        	
    		Pattern p=Pattern.compile("[\\pP��������]");//���Ӷ�Ӧ�ı��

            Matcher m=p.matcher(l.getTitle());//������ShowDaao��ĳ�abstract�ȴ���ȡ����getTitle�ĳ�getAbs

            String first=m.replaceAll(" "); //��Ӣ�ı������滻�ɿո�

//            System.out.println("��Ӣ�ı������滻�ɿո����ַ�����" + first);

            p=Pattern.compile(" {2,}");//ȥ������ո�

            m=p.matcher(first);

            String second=m.replaceAll(" ");

//            System.out.println("ȥ������ո����ַ���" + second);//secondΪ����������ַ���
    		String[] sList = second.split(" |,|\\.");//ʹ��split�������ַ���s���ո񡢶��ź;��ָ���������ַ�����sList��
    		for(int i=0; i<sList.length; i++){//����sList����
    		if(!has.containsKey(sList[i])){//���û��������ʣ��ͽ����ʴ���key�valueֵΪ1��containsKey���� �жϼ������Ƿ����ָ����key
    		has.put(sList[i], 1);
    		}else{//����Ѿ����ڣ��ͽ�valueֵ��1����get������ȡkey��Ӧ��valueֵ
    		has.put(sList[i], has.get(sList[i])+1);
    		}
    		}
    		
        
	}
      //ʹ����ǿforѭ��������ͨ��Entry���Ϸ��ʣ����Է���key�����Ӧ��Valueֵ
		for(Entry<String, Integer> entry:has.entrySet()){
			sarray[r] = entry.getKey();
			array[r] = entry.getValue();
			r++;
//		System.out.println(entry.getKey()+":"+entry.getValue());	
    }
		int p;
		int[] y=new int[50];
		int[] array1 = new int[10000000];
		for(int c=0;c<array.length;c++)
		{array1[c]=array[c];
//		System.out.println(array[c]+" "+sarray[c]); 
		}
		for(int t=0;t<y.length;t++) {
			int max=0;
		for(p=0;p<array1.length;p++){
			if(array1[p]>max){
				max=array1[p];
				y[t]=p;
			}
			array1[y[t]]=0;
		}
		}
		List<word> list1 = new ArrayList<word>();
		for(int t=0;t<y.length;t++) {
			word word=new word();
			word.setName(sarray[y[t]]);
			word.setValue(array[y[t]]);
			list1.add(word);
//		System.out.println(array[y[t]]+" "+sarray[y[t]]);
		}
		System.out.println(n);		
//		request.setAttribute("list1", list1);
		Gson gson = new Gson();
		String json = gson.toJson(list1);
		response.getWriter().write(json);
//        request.getRequestDispatcher("ciyun.jsp").forward(request, response);
        }
        
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

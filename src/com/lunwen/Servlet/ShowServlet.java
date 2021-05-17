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
		HashMap<String, Integer> has = new HashMap<String,Integer>();//打开哈希表，字符类型储存key（单词），整型储存value（单词出现的次数）
		int r=0;
        for(lunwen l : list) {
//        	System.out.println(l.getAbs());
        	n++;
        	
    		Pattern p=Pattern.compile("[\\pP‘’“”]");//增加对应的标点

            Matcher m=p.matcher(l.getTitle());//这里若ShowDaao里改成abstract热词提取，则getTitle改成getAbs

            String first=m.replaceAll(" "); //把英文标点符号替换成空格

//            System.out.println("把英文标点符号替换成空格后的字符串：" + first);

            p=Pattern.compile(" {2,}");//去除多余空格

            m=p.matcher(first);

            String second=m.replaceAll(" ");

//            System.out.println("去掉多余空格后的字符串" + second);//second为最终输出的字符串
    		String[] sList = second.split(" |,|\\.");//使用split方法将字符串s按空格、逗号和句点分割开，并存在字符数组sList中
    		for(int i=0; i<sList.length; i++){//遍历sList数组
    		if(!has.containsKey(sList[i])){//如果没有这个单词，就将单词存入key里，value值为1；containsKey方法 判断集合中是否包含指定的key
    		has.put(sList[i], 1);
    		}else{//如果已经存在，就将value值加1；用get方法获取key对应的value值
    		has.put(sList[i], has.get(sList[i])+1);
    		}
    		}
    		
        
	}
      //使用增强for循环遍历，通过Entry集合访问，可以访问key及其对应的Value值
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

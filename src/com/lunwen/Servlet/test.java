package com.lunwen.Servlet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class test {
	public static String StatList(String s) {
		StringBuffer sb = new StringBuffer();
		HashMap<String, Integer> has = new HashMap<String,Integer>();//打开哈希表，字符类型储存key（单词），整型储存value（单词出现的次数）
		
		Pattern p=Pattern.compile("[.,\"\\?!:']");//增加对应的标点

        Matcher m=p.matcher(s);

        String first=m.replaceAll(" "); //把英文标点符号替换成空格

        System.out.println("把英文标点符号替换成空格后的字符串：" + first);

        p=Pattern.compile(" {2,}");//去除多余空格

        m=p.matcher(first);

        String second=m.replaceAll(" ");

        System.out.println("去掉多余空格后的字符串" + second);//second为最终输出的字符串

		
		String[] sList = second.split(" |,|\\.|\\ s +");//使用split方法将字符串s按空格、逗号和句点分割开，并存在字符数组sList中
		for(int i=0; i<sList.length; i++){//遍历sList数组
		if(!has.containsKey(sList[i])){//如果没有这个单词，就将单词存入key里，value值为1；containsKey方法 判断集合中是否包含指定的key
		has.put(sList[i], 1);
		}else{//如果已经存在，就将value值加1；用get方法获取key对应的value值
		has.put(sList[i], has.get(sList[i])+1);
		}
		}
		int[] array = new int[10000000];
		String[] sarray = new String[10000000];
		int i=0;
		//使用增强for循环遍历，通过Entry集合访问，可以访问key及其对应的Value值
		for(Entry<String, Integer> entry:has.entrySet()){
			sarray[i] = entry.getKey();
			array[i] = entry.getValue();
			i++;
		System.out.println(entry.getKey()+":"+entry.getValue());
		}
		int max=0;
		int q=0;
		int[] y=new int[2];
		int[] array1 = new int[10000000];
		for(int c=0;c<array.length;c++)
		{array1[c]=array[c];}
		for(int t=0;t<y.length;t++) {
		for(q=0;q<array1.length;q++){
			if(array[q]>max){
				max=array1[q];
				y[t]=q;
			}
			array1[y[t]]=0;
		}
		}
		for(int t=0;t<y.length;t++) {
		System.out.println(array[y[t]]+" "+sarray[y[t]]);
		}
		
		
		    return sb.toString();
		    }
	public static void main(String[] args) {
		System.out.println(StatList("kkkkk.ssss.pppp,lllll.lll  ssss.ssss.pppp"));
		
	}
		
}

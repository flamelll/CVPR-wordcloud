package com.lunwen.Servlet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class test {
	public static String StatList(String s) {
		StringBuffer sb = new StringBuffer();
		HashMap<String, Integer> has = new HashMap<String,Integer>();//�򿪹�ϣ���ַ����ʹ���key�����ʣ������ʹ���value�����ʳ��ֵĴ�����
		
		Pattern p=Pattern.compile("[.,\"\\?!:']");//���Ӷ�Ӧ�ı��

        Matcher m=p.matcher(s);

        String first=m.replaceAll(" "); //��Ӣ�ı������滻�ɿո�

        System.out.println("��Ӣ�ı������滻�ɿո����ַ�����" + first);

        p=Pattern.compile(" {2,}");//ȥ������ո�

        m=p.matcher(first);

        String second=m.replaceAll(" ");

        System.out.println("ȥ������ո����ַ���" + second);//secondΪ����������ַ���

		
		String[] sList = second.split(" |,|\\.|\\ s +");//ʹ��split�������ַ���s���ո񡢶��ź;��ָ���������ַ�����sList��
		for(int i=0; i<sList.length; i++){//����sList����
		if(!has.containsKey(sList[i])){//���û��������ʣ��ͽ����ʴ���key�valueֵΪ1��containsKey���� �жϼ������Ƿ����ָ����key
		has.put(sList[i], 1);
		}else{//����Ѿ����ڣ��ͽ�valueֵ��1����get������ȡkey��Ӧ��valueֵ
		has.put(sList[i], has.get(sList[i])+1);
		}
		}
		int[] array = new int[10000000];
		String[] sarray = new String[10000000];
		int i=0;
		//ʹ����ǿforѭ��������ͨ��Entry���Ϸ��ʣ����Է���key�����Ӧ��Valueֵ
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

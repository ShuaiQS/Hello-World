package tools;

public class KMP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KMP().getNext("ABCABABCAB");
//		System.out.println(new KMP().bf("ABCDEFGASDFA", "AS"));
//		System.out.println(new KMP().kmpSerach("ABCDEFGASDFA", "AS"));
	}
	
	/**
	 * @暴力破解法
	 * @param ts 主串
	 * @param ps 模式串
	 * @return 如果找到，返回在主串中第一个字符出现的下标，否则为-1
	 */
	public  int bf(String ts, String ps) {
	    char[] t = ts.toCharArray();
	    char[] p = ps.toCharArray();
	    int i = 0; // 主串的位置
	    int j = 0; // 模式串的位置
	    while (i < t.length && j < p.length) {
	       if (t[i] == p[j]) { // 当两个字符相同，就比较下一个
	           i++;
	           j++;
	       } else {
	           i = i - j + 1; // 一旦不匹配，i后退
	           j = 0; // j归0
	       }
	    }
	    if (j == p.length) {
	       return i - j;
	    } else {
	       return -1;
	    }
	}
	
	/**
	 * @function 递推求next数组
	 * @param ps 模式串
	 */
	public int[] getNext(String ps) {
		char[] p = ps.toCharArray();
		int next[]=new int[p.length];
		next[0]=-1;
		int k=-1;
		int j=0;
		while(j<p.length-1) {
//			此刻，k即next[j-1],且p[k]表示前缀，p[j]表示后缀
//			k==-1表示未找到k前缀与k后缀相等，首次分析可先忽略
			if(k==-1||p[j]==p[k]) {
				++k;
				++j;
				next[j]=k;
			}else 
//				p[j]与p[k]失配，则继续递归前缀索引p[next[k]]
			{
				k=next[k];
			}
		}
		for(int i:next) {
			System.out.print(i+" ");
		}
		return next;
	}
	
	/**
	 * @function KMP算法
	 * @param ts 主串
	 * @param ps 模式串
	 */
	public int kmpSerach(String ts,String ps) {
		int i=0;
		int j=0;
		char s[] = ts.toCharArray();
		char p[] = ps.toCharArray();
		int next[]=getNext(ps);
		while(i<ts.length()&&j<ps.length()) {
//			如果j=-1，或者当前字符匹配成功（即s[i]==p[j]），令i++,j++
			if(j==-1||s[i]==p[j]) {
				i++;
				j++;
			}else {
//			如果j!=-1,且当前字符匹配失败（即s[i]!=p[i]），则令i不变，j=next[j]
				j = next[j];
			}
		}
		if(j==ps.length()) {
			return i-j;
		}
		else {
			return -1;
		}
	}
	
	/**
	 * @function 递推求next数组
	 * @param ps 模式串
	 * @exception 文本串匹配到i，模式串匹配到j，此刻，若text[i]≠pattern[j]，即失配的情况：
 			若next[j]=k，说明模式串应该从j滑动到k位置；
 			若此时满足pattern[j]==pattern[k]，因为text[i]≠pattern[j]，所以， text[i] ≠pattern[k]
 				即i和k没有匹配，应该继续滑动到next[k]。
 				换句话说：在原始的next数组中，若next[j]=k并且pattern[j]==pattern[k]，next[j]可以直接等于next[k]。
	 */
	public int[] getNewNext(String ps) {
		char[] p = ps.toCharArray();
		int next[]=new int[p.length];
		next[0]=-1;
		int k=-1;
		int j=0;
		while(j<p.length-1) {
//			此刻，k即next[j-1],且p[k]表示前缀，p[j]表示后缀
//			k==-1表示未找到k前缀与k后缀相等，首次分析可先忽略
			if(k==-1||p[j]==p[k]) {
				++k;
				++j;
				if(p[j]==p[k])
					next[j]=next[k];
				else 
					next[j]=k;
			}else 
//				p[j]与p[k]失配，则继续递归前缀索引p[next[k]]
			{
				k=next[k];
			}
		}
		for(int i:next) {
			System.out.print(i+" ");
		}
		return next;
	}
	

}

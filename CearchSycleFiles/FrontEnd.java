import java.io.*;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.DoubleWritable;

public class FrontEnd extends javax.swing.JFrame 
{
	private static javax.swing.JButton jButton1;
    private static javax.swing.JList jList1;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextField jTextField1;
	public static Scanner sc = new Scanner(System.in);
	public static String path = "";
	public static String addresses = "";
	public static int N = 0;
	public static double damping = 0;
	public static String files = "";
	public static String [] PAGES ;
	public static String [] LOCATIONS ;
	public static double s = 0.85, t = 0.15;
	public static double jump = 0;
	public static boolean [] ML ;
	public static boolean [] TL ;
	public static String [] title = new String[5];
	public static String [] desc = new String[5];
	public static String [] string = new String[5];
	public static int xx = 0;
	public static String user = "";

static class CearchSycle
{
	
	public static void remover() throws Exception
	{
		{
			File obj = new File("/home/"+user+"/CearchSycleFiles/indeces1");
			if(obj.exists())
			{
				File [] f = obj.listFiles();
				for(File c : f)
				{
					c.delete();
				}
				obj.delete();
			}
		}
		{
			File obj = new File("/home/"+user+"/CearchSycleFiles/indeces2");
			if(obj.exists())
			{
				File [] f = obj.listFiles();
				for(File c : f)
				{
					c.delete();
				}
				obj.delete();
			}
		}
		{
			String alpha = "qwertyuioplkjhgfdsazxcvbnm";
			for(int i=0;i<26;i++)
			{
				PrintWriter obj = new PrintWriter("/home/"+user+"/CearchSycleFiles/index/"+alpha.charAt(i)+".txt");
				obj.print("");
				obj.close();
			}
			{
				PrintWriter obj = new PrintWriter("/home/"+user+"/CearchSycleFiles/index/else.txt");
				obj.print("");
				obj.close();
			}
			{
				PrintWriter obj = new PrintWriter("/home/"+user+"/CearchSycleFiles/index/num.txt");
				obj.print("");
				obj.close();
			}
		}
	}

	public static void fetch(String x) throws Exception
	{
		File file = new File("/home/"+user+"/CearchSycleFiles/index/"+x.charAt(0)+".txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String l;
		while((l = br.readLine()) != null)
		{
			try
			{
				String [] arr = l.split("	");
				if(arr[0].equals(x))
				{	
					String [] arr1 = arr[1].split(",");
					if(arr1.length>0)
					{
						for(int i=0;i<arr1.length;i++)
						{
							String [] arr2 = arr1[i].split(" ");
							int n = Integer.parseInt(arr2[0]);
							TL[n] = true;
						}
						break;
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		for(int i=0;i<N;i++)
		{
			ML[i] = ML[i] | TL[i];
			if(ML[i])
				System.out.println(i);
		}
		/*
		boolean sym = false;
		for(int i=0;i<N;i++)
		{
			sym = sym | ML[i];
		}
		//Be More Sensitive
		if(sym)
		{
			for(int i=0;i<N;i++)
			{
				ML[i] = ML[i] & TL[i];
			}
		}
		else
		{
			for(int i=0;i<N;i++)
			{
				ML[i] = ML[i] | TL[i];
			}			
		}*/
		br.close();
		fr.close();
	}

	public static void frontEnd(String x) throws Exception
	{
			for(int i=0;i<N;i++)
			{
				ML[i]=false;
				TL[i]=false;
			}
			for(int i=0;i<5;i++)
			{
				title[i]=null;
				desc[i]=null;
			}
			String Q = x;
			Q = Q.toLowerCase();
			Q = word_separator(Q);
			if(Q!=null)
			{
				String [] q = Q.split(" ");
				for(int i=0;i<q.length;i++)
				{
					if(screening(q[i]) && screening2(q[i]))
					{
						fetch(q[i]);
					}
				}
				int j = 0;
				for(int i=0;i<5;i++)
				{
					for(;j<N;j++)
					{
						if(ML[j])
						{
							title[i] = PAGES[j];
							File file = new File(LOCATIONS[j]);
							FileReader fr = new FileReader(file);
							BufferedReader br = new BufferedReader(fr);
							desc[i] = "";
							for(int k=0;k<3;k++)
							{
								desc[i] += br.readLine();
							}
							br.close();
							fr.close();
							j++;
							break;
						}
					}
				}
				boolean su = false;
				for(int i=0;i<5;i++)
				{
					if(desc[i] != null && title[i] != null)
					{
						//System.out.println(title[i]+"\n"+desc[i]+"\n\n");
						su = true;
					}
				}
				if(!su)
				{
					System.out.println("NO RESULTS FOUND!");
				}
			}
			else
			{
				System.out.println("NO RESULT FOUND!");
			}
	}
	
	public static void initialisers() throws Exception
	{
		try
		{
			Process p = Runtime.getRuntime().exec("whoami");
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			user = br.readLine();
			br.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		PAGES = new String[N];
		LOCATIONS = new String[N];
		jump = t/((double)N);
		ML = new boolean[N];
		TL = new boolean[N];
	}
	
	public static void indexing() throws Exception
	{
		jTextField1.setText("WAIT!");
		remover();
		Index1.Iaction();
		Index2.IAction();
		String alpha = "qwertyuioplkjhgfdsazxcvbnm";
		String nulpha = "0123456789";
		File file = new File("/home/"+user+"/CearchSycleFiles/indeces2/part-r-00000");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null)
		{
			boolean sym = false;
			for(int i=0;i<26;i++)
			{
				if(line.charAt(0) == alpha.charAt(i))
				{
					PrintWriter obj = new PrintWriter(new FileOutputStream(new File("/home/"+user+"/CearchSycleFiles/index/"+alpha.charAt(i)+".txt"),true));
					obj.println(line);
					obj.close();
					sym = true;
					break;
				}
			}
			if(!sym)
			{
				for(int i=0;i<10;i++)
				{
					if(line.charAt(0)==nulpha.charAt(i))
					{
						PrintWriter obj = new PrintWriter(new FileOutputStream(new File("/home/"+user+"/CearchSycleFiles/index/num.txt"),true));
						obj.println(line);
						obj.close();
						sym = true;
						break;
					}
				}
				if(!sym)
				{
					PrintWriter obj = new PrintWriter(new FileOutputStream(new File("/home/"+user+"/CearchSycleFiles/index/else.txt"),true));
					obj.println(line);
					obj.close();
				}
			}
		}
		br.close();
		fr.close();
	}
	
	public static void pathBuilder(String addr) 
	{
		try
		{
		File obj = new File(addr);
		String [] contents = obj.list();
		for(String c : contents)
		{
			if(!c.equals("CearchSycleFiles"))
			{
				if(!(new File(addr + "/" + c).isHidden()) && new File(addr + "/" + c).isFile())
				{
					if((c.length()>4)&&(c.substring(c.length()-4,c.length()).equals( ".txt")))
					{
						if(path.length() == 0)
							path = addr + "/*.txt";
						else
							path = path + "," + addr + "/*.txt";
						break;
					}
				}
			}
		}
		for(String c : contents)
		{
			if(!c.equals("CearchSycleFiles"))
			{
				if(new File(addr + "/" + c).isDirectory() && !(new File(addr + "/" + c).isHidden()))
				{
					pathBuilder(addr + "/" + c);
				}
				else
				{
					if(!(new File(addr + "/" + c)).isHidden())
					{
						if(c.substring(c.length()-4,c.length()).equals( ".txt"))
						{
							N++;
							if(files.length() == 0)
							{
								files = c;
								addresses = addr + "/" + c;
							}
							else
							{
								files = files + "	" + c;
								addresses = addresses + "	" + addr + "/" + c;
							}
						}
					}
				}
			}
		}
		}
		catch(Exception e)
		{
		}
	}
	
	public static void rankAssignment() throws Exception
	{
		String [] arr = files.split("	");
		String [] arr1 = addresses.split("	");
		for(int i=0;i<N;i++)
		{
			PAGES[i] = arr[i];
			LOCATIONS[i] = arr1[i];
		}
	}
	
	public static boolean screening(String x) throws Exception
	{
		File file = new File("/home/"+user+"/CearchSycleFiles/stopword.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String word;
		while((word = br.readLine()) != null)
		{
			if(word.equals(x))
			{
				br.close();
				fr.close();
				return false;
			}
		}
		br.close();
		fr.close();
		return true;
	}
	
	public static boolean screening2(String x)
	{
		boolean sym = true;
		for(int i =0 ;i<x.length();i++)
		{
			if(x.charAt(i)=='0'||x.charAt(i)=='1'||x.charAt(i)=='2'||x.charAt(i)=='3'||x.charAt(i)=='4'||x.charAt(i)=='5'||x.charAt(i)=='6'||x.charAt(i)=='7'||x.charAt(i)=='8'||x.charAt(i)=='9')
			{
				sym = false;
			}
			else
			{
				sym = true;
			}
		}
		return sym;
	}
	
	public static String word_separator(String x)
	{
		boolean sym = false;
		String y=x;
		for(int i=0;i<y.length();i++)
		{
			if(y.charAt(i)=='.'||y.charAt(i)==','||y.charAt(i)=='?'||y.charAt(i)=='!'||y.charAt(i)=='-'||y.charAt(i)=='('||y.charAt(i)==')'||y.charAt(i)=='#'||y.charAt(i)=='"'||y.charAt(i)=='\''||y.charAt(i)==':'||y.charAt(i)==';'||y.charAt(i)=='~'||y.charAt(i)=='`'||y.charAt(i)=='@'||y.charAt(i)=='$'||y.charAt(i)=='%'||y.charAt(i)=='%'||y.charAt(i)=='^'||y.charAt(i)=='/'||y.charAt(i)=='*'||y.charAt(i)=='['||y.charAt(i)==']'||y.charAt(i)=='{'||y.charAt(i)=='}'||y.charAt(i)=='_'||y.charAt(i)=='+'||y.charAt(i)=='='||y.charAt(i)=='|')
				
			{
				if(i==0)
				{
					y=y.substring(1,y.length());
				}
				else
				{
					if(i==y.length()-1)
						y=y.substring(0,y.length()-1);
					else
					{
						if(y.charAt(i-1) != ' ')
							y=y.substring(0,i)+" "+y.substring(i+1,y.length());
						else
						{
							y=y.substring(0,i)+y.substring(i+1,y.length());
							i=i-1;
						}
					}
				}
			}
			else
			{
				if(y.charAt(i)==' '||y.charAt(i)=='	'||y.charAt(i)=='\n')
				{
					
				}
				else
				{
					sym = true;
				}
			}
		}
		if(sym)
		{
			if(y.length()>1)
				return y;
			else
				return null;
		}
		else
			return null;
	}
	
	public static class Dangling
	{
		static int l = 0;
		public static class DmyMapper extends Mapper<Object, Text, Text, DoubleWritable>
		{
			public void map(Object key, Text value, Context context) throws IOException, InterruptedException
			{
				try
				{
					String line = value.toString();
					double PR = 0;
					if(line.length() <= l)
					{
						File file = new File("/home/"+user+"/CearchSycle/pageranks.txt");
						FileReader fr = new FileReader(file);
						BufferedReader br = new BufferedReader(fr);
						String l1;
						while((l1 = br.readLine()) != null)
						{
							String [] spp = l1.split(" ");
							if(spp[0].equals(line))
							{
								PR = Double.parseDouble(spp[1]);
								PR = PR*s;
								damping = damping + PR;
							}
						}
						br.close();
						fr.close();
					}
					context.write(new Text("1"),new DoubleWritable(PR));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		public static class DmyReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable>
		{
			public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException
			{
				double p = 0;
				for(DoubleWritable d : values)
				{
					p += d.get();
				}
				context.write(key,new DoubleWritable(p));	
			}
		}
		public static void Daction() throws Exception
		{
			int tmp = N;
			while(tmp != 0)
			{
				l++;
				tmp = tmp/10;
			}
			Configuration conf = new Configuration();
			Job job = new Job(conf,"DanglingPage");
			job.setJarByClass(Dangling.class);
			job.setMapperClass(DmyMapper.class);
			job.setCombinerClass(DmyReducer.class);
			job.setReducerClass(DmyReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(DoubleWritable.class);
			FileInputFormat.addInputPath(job,new Path("/home/"+user+"/CearchSycleFiles/WebGraph.txt"));
			FileOutputFormat.setOutputPath(job, new Path("/home/"+user+"/CearchSycleFiles/dangling"));
			job.waitForCompletion(true);		
		}
	}
	
	public static class PageRank
	{
		public static class PmyMapper extends Mapper<Object, Text, Text, DoubleWritable>
		{
			public void map(Object key, Text value, Context context) throws IOException, InterruptedException
			{
				try
				{
					File file = new File("/home/"+user+"/CearchSycleFiles/PageRanks.txt");
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line = value.toString();
					String [] arr = line.split(" ");
					String line1;
					double PR = 0;
					while((line1 = br.readLine()) != null)
					{
						String [] spp = line1.split("	");
						if(spp[0].equals(arr[0]))
						{
							PR = Double.parseDouble(spp[1]);
							break;
						}
					}
					br.close();
					fr.close();
					if(arr.length == 2)
					{
						String [] links = arr[1].split(",");
						for(int i=0;i<links.length;i++)
						{
							double p=0;
							p=s*(PR/((double)links.length));
							context.write(new Text(links[i]), new DoubleWritable(p));
						}
					}
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
			}
		}
		public static class PmyReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable>
		{
			public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException
			{
				try
				{
					double pp = jump;
					for(DoubleWritable x : values)
					{
						pp += x.get();
					}
					context.write(key,new DoubleWritable(pp));
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
			}
		}
		public static int comparison() throws Exception
		{
			int x=1;
			File file = new File("/home/"+user+"/workspace/GothamReconing/input/demo_pageranks.txt");
			File file1 = new File("/home/"+user+"/workspace/GothamReconing/input/Temp.txt");
			FileReader fr = new FileReader(file);
			FileReader fr1 = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			BufferedReader br1 = new BufferedReader(fr1);
			String line,line1;
			while((line=br.readLine())!=null)
			{
				line1=br1.readLine();
				String [] arr = line.split(" ");
				String [] arr1 = line1.split(" ");
				double diff;
				diff = Double.parseDouble(arr[1]) - Double.parseDouble(arr1[1]);
				if((diff>=0 && diff>0.1) || (diff<0 && diff<-0.1))
				{
					x=1;
					break;
				}
				x=0;
			}
			br.close();
			br1.close();
			fr.close();
			fr1.close();
			return x;
		}
		public static void transfer() throws Exception
		{
			File file = new File("/home/"+user+"/workspace/GothamReconing/input/pageranks.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br =new BufferedReader(fr);
			PrintWriter obj = new PrintWriter("/home/"+user+"/workspace/GothamReconing/input/Temp.txt");
			String line;
			while((line=br.readLine())!=null)
			{
				obj.println(line);
			}
			obj.close();
			br.close();
			fr.close();
		}
		public static void update() throws Exception
		{
			File file1 = new File("/home/"+user+"/FakeWeb/PageRanks.txt");
			FileReader fr1 = new FileReader(file1);
			BufferedReader br1 = new BufferedReader(fr1);
			String text="",line,line1;
			while((line1=br1.readLine())!=null)
			{
				String [] arr1 = line1.split("	");
				File file = new File("/home/"+user+"/CearchSycleFiles/PRreduction/part-r-00000");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while((line=br.readLine())!=null)
				{
					String [] arr = line.split("	");
					if(arr1[0].equals(arr[0]))
					{
						text=text+arr1[0]+"	"+arr[1]+"\n";
						break;
					}
				}
				br.close();
				fr.close();
			}
			br1.close();
			fr1.close();
			PrintWriter obj = new PrintWriter("/home/"+user+"/CearchSycleFiles/PageRanks.txt");
			for(int i=0;i<text.length()-1;i++)
			{
				if(text.charAt(i)=='\n')
					obj.println();
				else
					obj.print(text.charAt(i));
			}
			obj.close();
		}
		public static void Paction() throws Exception
		{
			Configuration conf = new Configuration();
			Job job=new Job(conf,"PageRank");
			job.setJarByClass(PageRank.class);
			job.setMapperClass(PmyMapper.class);
			job.setCombinerClass(PmyReducer.class);
			job.setReducerClass(PmyReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(DoubleWritable.class);
			FileInputFormat.addInputPath(job,new Path("/home/"+user+"/CearchSycleFiles/WebGraph.txt"));
			FileOutputFormat.setOutputPath(job,new Path("/home/"+user+"/CearchSycleFiles/PRreduction"));
			job.waitForCompletion(true);	
			update();
		}
	}
	
	public static class Index1
	{
		public static class ImyMapper extends Mapper<Object, Text, Text, IntWritable>
		{
			String word;
			private final static IntWritable one = new IntWritable(1);
			public void map(Object key, Text value, Context context) throws IOException, InterruptedException
			{
				try
				{
					if(xx == 0)
					{
						String [] arr4;
						String [] arr3 = files.split("	");
						for(String a : arr3)
						{
							arr4 = word_separator(a).split(" ");
							int j=0;
							for(j=0;j<N;j++)
							{
								if(PAGES[j].equals(a))
								{
									break;
								}
							}
							for(String b : arr4)
							{
								word=b+"	"+Integer.toString(j);
								context.write(new Text(word), one);
							}
						}
					}
					xx = 1;
					StringTokenizer itr = new StringTokenizer(value.toString());
					FileSplit fileSplit = (FileSplit)context.getInputSplit();
					String filename = fileSplit.getPath().getName();
					while(itr.hasMoreTokens())
					{
						String s = itr.nextToken();
						s = s.toLowerCase();
						if(screening(s) && screening2(s))
						{
							String ss = word_separator(s);
							if(ss != null)
							{
								int sss=0;
								for(int i =0;i<ss.length();i++)
								{
									if(ss.charAt(i)==' ')
									{	
										sss=1;
										break;
									}
								}
								if(sss==1)
								{
									String arr[] =ss.split(" ");
									for(int i=0;i<arr.length;i++)
									{
										if(screening(arr[i]) && screening(arr[i]))
										{
											if(filename.charAt(filename.length()-1) != '~')
											{
												int j=0;
												for(j=0;j<N;j++)
												{
													if(PAGES[j].equals(filename))
													{
														break;
													}
												}
												word=arr[i]+"	"+Integer.toString(j);
												context.write(new Text(word), one);
											}
										}
									}
								}
								else
								{
									s=ss;
									if(screening(s) && screening(s))
									{
										if(filename.charAt(filename.length()-1) != '~')
										{
											int j=0;
											for(j=0;j<N;j++)
											{
												if(PAGES[j].equals(filename))
												{
													break;
												}
											}
											word=s+"	"+Integer.toString(j);
											context.write(new Text(word), one);
										}
									}
								}
							}
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		public static class ImyReducer extends Reducer<Text, IntWritable, Text, IntWritable>
		{
			public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
			{
				int i = 0;
				for(IntWritable s : values)
				{
					i += s.get();
				}
				context.write(key, new IntWritable(i));
			}
		}
		public static void Iaction() throws Exception
		{
			Configuration conf = new Configuration();
			Job job = new Job(conf, "MappedReduction");
			job.setJarByClass(Index1.class);
			job.setMapperClass(ImyMapper.class);
			job.setCombinerClass(ImyReducer.class);
			job.setReducerClass(ImyReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			FileInputFormat.setInputPaths(job, path);
			FileOutputFormat.setOutputPath(job, new Path("/home/"+user+"/CearchSycleFiles/indeces1"));
			job.waitForCompletion(true);
		}
	}
	
	public static class Index2
	{
		public static class IMyMapper extends Mapper<Object, Text, Text, Text>
		{
			public void map(Object key, Text value, Context context) throws IOException, InterruptedException
			{
				String line = value.toString();
				String [] arr = line.split("	");
				if(arr.length == 3)
				{
					String rem = arr[1]+" "+arr[2];
					context.write(new Text(arr[0]), new Text(rem));
				}
			}
		}
		public static class IMyReducer extends Reducer<Text, Text, Text, Text>
		{
			public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
			{
				String rem = "";
				for(Text s : values)
				{
					if(rem.length() == 0)
						rem = s.toString();
					else
						rem = rem + "," + s.toString();
				}
				context.write(key, new Text(rem));
			}
		}
		public static void IAction() throws Exception
		{
			Configuration conf = new Configuration();
			Job job = new Job(conf, "MappedReduction");
			job.setJarByClass(Index2.class);
			job.setMapperClass(IMyMapper.class);
			job.setCombinerClass(IMyReducer.class);
			job.setReducerClass(IMyReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileInputFormat.addInputPaths(job, "/home/"+user+"/CearchSycleFiles/indeces1/part-r-00000");
			FileOutputFormat.setOutputPath(job, new Path("/home/"+user+"/CearchSycleFiles/indeces2"));
			job.waitForCompletion(true);
		}
	}
	
	/*public static void main(String [] args) throws Exception
	{
		pathBuilder("/home/bane");
		initialisers();
		rankAssignment();
		//indexing();
		frontEnd();
		pathBuilder("/home/bane");
		initialisers();
		rankAssignment();
		indexing();
		frontEnd();
	}*/
}
	
    
    public FrontEnd() 
    {
    	try
    	{
	        initComponents();
	        CearchSycle.pathBuilder("/home/"+user);
	        CearchSycle.initialisers();
	        CearchSycle.rankAssignment();
	        //CearchSycle.indexing();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    private void initComponents() throws Exception
    {
    	jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("ENTER YOUR QUERY!");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jTextField1ActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jButton1.setText("REFRESH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton1ActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
        	
            String[] strings = { "", "", "", "", "" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
            
        });
        jList1.setFixedCellHeight(200);
        jList1.setFixedCellWidth(100);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
					jList1MousePressed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) throws Exception
    {
        String x;
        x = jTextField1.getText();
        CearchSycle.frontEnd(x);
        for(int i=0;i<5;i++)
        {
        	if(title[i] != null)
        	{
        		string[i]="<html><p><b><font size=30 color='green'>"+title[i]+"</font></b></p><p><font size=5 color='red'>"+desc[i]+"</font></p></html>";
        	}
        	if(title[i] == null)
        	{
        		string[i] = null;
        	}
        }
        jList1.setModel(new javax.swing.AbstractListModel() {
        String [] strings = string;
        public int getSize() { return strings.length; }
        public Object getElementAt(int i) { return strings[i]; }
        });
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws Exception
    {
       CearchSycle.pathBuilder("/home/bane");
       CearchSycle.initialisers();
       CearchSycle.rankAssignment();
       CearchSycle.indexing();
    }

    private void jList1MousePressed(java.awt.event.MouseEvent evt) throws Exception
    {
    	String command="gedit ";
    	int n = jList1.getSelectedIndex();
    	if(title[n] != null)
    	{	
	    	for(int i=0;i<N;i++)
	    	{
	    		if(PAGES[i].equals(title[n]))
	    		{
	    			for(int j=LOCATIONS[i].length()-1;j>=0;j--)
	    			{
	    				if(LOCATIONS[i].charAt(j)=='/')
	    				{
	    					command += LOCATIONS[i].substring(0,j+1) + "\"" + PAGES[i] + "\"";
	    					break;
	    				}
	    			}
	    			break;
	    		}
	    	}
	    	PrintWriter pr = new PrintWriter("/home/"+user+"/CearchSycleFiles/opener.sh");
			pr.print(command);
			pr.close();
	    	try
	    	{
	    		Process process = Runtime.getRuntime().exec("chmod 777 "+"/home/"+user+"/CearchSycleFiles/opener.sh");
	    		Process process2 = Runtime.getRuntime().exec("sh "+"/home/"+user+"/CearchSycleFiles/opener.sh");
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
    	}
    }
   
    public static void main(String args[]) 
    {
       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrontEnd().setVisible(true);
            }
        });
    }

}
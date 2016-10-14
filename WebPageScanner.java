import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;


public class WebPageScanner {
        
        
	String website;
	Model tree;
	String v;
	Model finalTree;
	Model prefinalTree;
	
	public WebPageScanner(String s)
	{
		website = s;
		tree = new Model();
		finalTree = new Model();
		prefinalTree = new Model();
	}

    public void chop() {
       	try
        {
        
        	URL site = new URL(website);
        	URLConnection siteConnection = site.openConnection();
        	siteConnection.connect();  	
        	Scanner sc = new Scanner(siteConnection.getInputStream());
			while(sc.hasNext())
			{		
    			int worth = 1;
    			String s = sc.nextLine();
    			v = "";
    			Scanner seeeeee = new Scanner(s);
    			if(seeeeee.hasNext())
    			{
    				v = seeeeee.next();
    			}
    			if(!(v.equals("")))
    			{
    				if(v.equals("<title"))
    					worth = 10;
    				if(v.equals("<h1>"))
  				  		worth = 10;
    				if(v.equals("<h2>"))
    					worth = 8;
    				if(v.equals("<h3>"))
    					worth = 6;
    				if(v.equals("<h4>"))
    					worth = 4;
    				if(v.equals("<h5>"))
    					worth = 2;
   					if(v.equals("<h6>"))
    					worth = 1;
    				if(v.equals("<p>"))
    					worth = 1;
    				if(v.contains("<a"))
    					worth = 5;
   					if(v.equals("<li>"))
    					worth = 1;
    			}
    			s = s.toLowerCase();
    			s = s.replaceAll("<[^>]*>"," ");			
    			s = s.replaceAll("[^\\w\\s]"," ");
    			s = s.replaceAll("[\\d]"," ");
    			s = s.replaceAll(" [a-z] ", " ");
				s = s.replaceAll(" the ", " ");
				s = s.replaceAll(" the ", " ");
				s = s.replaceAll(" i ", " ");
				s = s.replaceAll(" amp ", " ");
				s = s.replaceAll(" be ", " ");
				s = s.replaceAll(" to ", " ");
				s = s.replaceAll(" and ", " ");
				s = s.replaceAll(" a ", " ");
				s = s.replaceAll(" an ", " ");
				s = s.replaceAll(" is ", " ");
				s = s.replaceAll(" but ", " ");
				s = s.replaceAll(" of ", " ");
				s = s.replaceAll(" it ", " ");
				s = s.replaceAll(" quot ", " ");
				s = s.replaceAll(" I ", " ");
				s = s.replaceAll(" I ", " ");
				s = s.replaceAll(" was ", " ");
				s = s.replaceAll("will", " ");
				s = s.replaceAll("with", " ");
				s = s.replaceAll(" you ", " ");
				s = s.replaceAll(" your ", " ");
				s = s.replaceAll(" this ", " ");
				s = s.replaceAll(" or ", " ");
				s = s.replaceAll(" on ", " ");
				s = s.replaceAll(" if ", " ");
				s = s.replaceAll(" in ", " ");
				s = s.replaceAll(" do ", " ");
				s = s.replaceAll(" by ", " ");
				s = s.replaceAll(" are ", " ");
				s = s.replaceAll(" i ", " ");
				s = s.replaceAll(" we ", " ");
				s = s.replaceAll(" us ", " ");
				s = s.replaceAll(" they ", " ");
				s = s.replaceAll(" re ", " ");
				s = s.replaceAll(" d ", " ");
				s = s.replaceAll(" t ", " ");
				s = s.replaceAll(" don ", " ");
				s = s.replaceAll(" he ", " ");
				s = s.replaceAll(" she ", " ");
				s = s.replaceAll(" her ", " ");
				s = s.replaceAll(" his ", " ");
				seeeeee = new Scanner(s);
			while(seeeeee.hasNext())
			{
				String pull = seeeeee.next();
				if(pull.indexOf("_") == -1)
				{	
					if(tree.getVal(pull) == 0)
					{
						tree.add(pull, worth);
					}
					else{
						tree.add(pull, tree.getVal(pull)+worth);
					}
				}
			}
					
		}	
		for(Map.Entry<String,Integer> entry : tree.entrySet())
		{
			String key = entry.getKey();
			Integer value = entry.getValue();
  			if(value > 2)
  			{
  				prefinalTree.add(key, value);
  			}
		}
		while(finalTree.getSize() <= 50)
		{
			if(prefinalTree.getSize() > 0)
			{
				int maxcount = Integer.MIN_VALUE;
				Model x = new Model();
				for(Map.Entry<String,Integer> entry : prefinalTree.entrySet())
				{
					String key = entry.getKey();
					Integer value = entry.getValue();
  					if(value > maxcount)
  					{
  						maxcount = value;
  					}
				}
				for(Map.Entry<String,Integer> entry : prefinalTree.entrySet())
				{
					if(finalTree.getSize() < 50)
					{
						String key = entry.getKey();
					    Integer value = entry.getValue();
					    if(value == maxcount)
					    {
					    	finalTree.add(key,value);
					    }
				  	  else{
				  	  	x.add(key,value);
				  	  }
					}
				}
				prefinalTree = x;
			}
			else{
				break;
			}	
		}

   	}
   		catch (IOException e)
   		{
   			System.out.println("Oops!");
   		}
    }
    public Map getModel()
    {
    	return finalTree.getMap();
    }
	public int getSize()
	{
		return finalTree.getSize();
	}

}


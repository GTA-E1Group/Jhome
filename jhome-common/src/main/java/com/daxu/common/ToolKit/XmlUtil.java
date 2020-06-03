package com.daxu.common.ToolKit;

public class XmlUtil {

/**
 * @author Administrator
 *
 */
public class XMLtEST {
   
   /* public void xmlToMap() throws DocumentException{
        Map<String,Object> map = new HashMap<String,Object>();
        
        //创建读取xml的对象
        SAXReader reader = new SAXReader();
        //指定读取的文件
        org.dom4j.Document doc = reader.read(new File("pers.xml"));
        
        //读取根节点
        Element root = doc.getRootElement();
        
        //进行循环遍历，添加到集合
        //获取迭代器，迭代元素
        for(Iterator iterator = root.elementIterator();iterator.hasNext();){
            
            //迭代根节点中的所有子元素
            Element e = (Element) iterator.next();
            //System.out.println(e.getName());
            
            //获取该元素下的所有子元素，并放入list集合中
            List list = e.elements();
            //System.out.println(list.size());
            
            //判断该节点下是否还有子节点
            if(list.size()>0){
                //如果该节点下还有子节点，则调用方法，继续进行解析
                map.put(e.getName(),returnMap(e));
            }else {
                map.put(e.getName(),e.getText());
            }
        }
        //遍历出map集合中的元素
        Set<String> keySet = map.keySet();
        for(String s : keySet){
            System.out.println(s+"  :  "+map.get(s));
        }
    }*/
    
    
    


    /*
    //如果节点下还有节点，则调用该方法，继续进行解析
    public static Map returnMap(Element e){
        
        Map map = new HashMap();
        //将该节点下所有子元素读进来，并放在list集合中
        List list = e.elements();
        
        //如果还有子元素，则继续进行解析
        if(list.size()>0){
            for(int i = 0;i<list.size();i++){
                //循环获取集合中的元素
                Element ele = (Element) list.get(i);
                List listMapList = new ArrayList();
                
                //如果该元素下，仍有子节点，则用递归进行进一步解析
                if(ele.elements().size()>0){
                    //调用自身会返回一个Map集合
                    Map m = returnMap(ele);
                    //判断map中是否已经有该元素,!=null,则代表该key已经存在
                    if(map.get(ele.getName()) != null){
                        //如果已经存在，获取该对象
                        Object obj = map.get(ele.getName());
                        //判断该元素是不是一个list集合，如果不是一个list集合，则直接添加到listMapList集合中
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){
                            listMapList.add(obj);
                            listMapList.add(m);//将返回的map集合也放进集合中
                        }//如果该元素是一个list集合，则将该元素赋值给listMapList
                          if(obj.getClass().getName().equals("java.util.ArrayList")){  
                              listMapList = (List) obj;  
                              listMapList.add(m);  
                            }
                          //将该元素添加到map集合中去
                          map.put(ele.getName(),listMapList);
                    }else{    //如果该元素还未存在map集合中，则直接添加到map中
                        map.put(ele.getName(),m);
                        
                    }
                }else{    //如果该节点下，没有子节元素了，
                    if(map.get(ele.getName()) !=null){
                        Object obj = map.get(ele.getName());  
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){  
                            listMapList.add(obj);  
                            listMapList.add(ele.getText());  
                        }
                          if(obj.getClass().getName().equals("java.util.ArrayList")){  
                              listMapList = (List) obj;  
                              listMapList.add(ele.getText());  
                            }  
                          map.put(ele.getName(), listMapList);
                    }else{
                           map.put(ele.getName(), ele.getText());
                    }
                }
            }
        }else{
            map.put(e.getName(), e.getText());  
            
        }
        return map;
    }*/
    
}

}

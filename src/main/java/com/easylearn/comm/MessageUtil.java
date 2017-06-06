package com.easylearn.comm;

import com.easylearn.modules.baseservice.beans.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuisama on 2017/5/27.
 */
public class MessageUtil {
    protected static Logger logger = Logger.getLogger(MessageUtil.class.getClass());

    /**
     * 转发至客服
     */
    public static final String RESP_MESSAGE_TYPE_SERVER = "transfer_customer_service";

    /**
     * 返回消息类型 ： 文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型 ：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型 ：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    /**
     * 请求消息类型 ：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    /**
     * 请求消息类型 ：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 请求消息类型 ：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    /**
     * 请求消息类型 ：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    /**
     * 请求消息类型 ：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /**
     * 事件类型 ：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型 ：scan(扫描)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";
    /**
     * 事件类型 ：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /**
     * 事件类型 ：click(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 事件类型：LOCATION 获取用户地理位置
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    public static Map<String, String> userLocation = new HashMap<String, String>();

    /**
     * 解析微信发来的请求
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception{
        //将解析结果存储在hashmap中
        Map<String, String> map = new HashMap<String, String>();
        //从request中取得输入流
        InputStream inputStream = request.getInputStream();

        //读取输入流
        SAXReader reader = new SAXReader();

        Document document = reader.read(inputStream);

        //得到xml的根元素
        Element root = document.getRootElement();
        //得到跟元素的所有子节点

        List<Element> elementList = root.elements();
        //遍历所有子节点
        for (Element e : elementList) {
            System.out.println("数据："+e.getName()+":"+e.getText());
            map.put(e.getName(), e.getText());
        }

        //释放资源
        inputStream.close();
        inputStream = null;

        return map;


    }

    /**
     * 文本消息对象转成xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 音乐消息转换成xml
     * @param musicMessage
     * @return
     */
    public static String musicMessageToXml(MusicMessage musicMessage){
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息转换成xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 客服消息转换成xml
     * @param cusMsg
     * @return
     */
    public static String cusMsgToXml(CustomerServiceMsg cusMsg){
        xstream.alias("xml", cusMsg.getClass());
        return xstream.toXML(cusMsg);
    }


    /**
     * 扩展xstream，使其支持CDATA块
     */
    public static XStream xstream = new XStream(new XppDriver(){

        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }

    });

    /**
     * <pre>
     * 任务：
     * 描述：将object转为xml格式 ,返回的xml根目录为<xml>
     * 作者：屈剑飞
     * 时间：2015年1月16日下午1:13:47
     * @param obj 任意要转为的xml的object对象
     * @return 返回String类型的xml
     * returnType：String
     * </pre>
     */
    public static String ObjectToXml(Object obj){


        //创建一个document对象，根节点为xml
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("xml");

        //得到obj类名的全路径
        String path = obj.getClass().toString();
        String classPackagePath =path.split(" ")[1];

        Class clazz = null;
        try {
            clazz = Class.forName(classPackagePath);
        } catch (ClassNotFoundException e1) {
            logger.error(classPackagePath+"找不到或不存在");
        }

        Field[] fields = clazz.getDeclaredFields();//obj类的所有属性
        String fieldName = "";//属性名
        String upfiledName = "";//
        Object value = "";//属性的值
        String getMethodName = "";//get方法名
        Method getMethod = null;//该属性的get方法

        for (Field field : fields) {//遍历属性

            fieldName = field.getName();
            upfiledName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            getMethodName = "get"+upfiledName;
            try {

                getMethod = clazz.getMethod(getMethodName);
                value = getMethod.invoke(obj);

            } catch (NoSuchMethodException e) {
                logger.error(classPackagePath+" 中没有找到 "+getMethodName+" 方法 "+e.getMessage());
            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                logger.error(e.getMessage());
            }
            Element fieldEle = root.addElement(fieldName);//将属性名设为xml的节点名
            if(value !=null && !value.toString().equals("null")){//如果属性值不为空，将属性值设为节点的值
                fieldEle.setText(value.toString());
            }
        }
        return root.asXML();
    }


}

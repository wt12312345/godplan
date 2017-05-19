package com.wt.base.webService;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WebService {

	/**
	 * 接口返回信息
	 * 
	 * @param map
	 *            参数 值
	 * @param SOAPAction
	 *            xml规范中的SOAPAction地址
	 * @param asmx
	 *            对接地址
	 * @param nameSpace
	 *            命名空间
	 * @param methods
	 *            要对接的方法
	 * @return 返回xml
	 * @throws RemoteException
	 */
	public static String dockingService(Map<String, String> map,
			String SOAPAction, String asmx, String nameSpace, String methods)
			throws RemoteException {
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = new Options();
		// SOAPAction地址
		options.setAction(SOAPAction);
		// 对接地址
		EndpointReference targetEPR = new EndpointReference(asmx);
		options.setTo(targetEPR);
		// 设置超时时间
		options.setTimeOutInMilliSeconds(6000000000L);
		// 传输协议
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		serviceClient.setOptions(options);
		OMFactory fac = OMAbstractFactory.getOMFactory();
		// 命名空间
		OMNamespace omNs = fac.createOMNamespace(nameSpace, "");
		// 要对接的方法
		OMElement method = fac.createOMElement(methods, omNs);
		// 赋值
		for (String key : map.keySet()) {
			OMElement wordKey = fac.createOMElement(key, omNs);
			wordKey.setText(map.get(key));
			method.addChild(wordKey);
		}
		method.build();
		OMElement result = serviceClient.sendReceive(method);
		String str = "";
		String xmlStr = result.toString();
		if (xmlStr.contains("&lt;")) {
			str = xmlStr.replaceAll("&lt;", "<");
		} else {
			str = xmlStr;
		}
		return str;
	}

	/**
	 * 单一解析xml
	 * 
	 * @param xml
	 * @param targetUrl
	 *            节点 /GetStockResponse/GetStockResult/Message
	 * @return
	 */
	public static Map<String, Object> parsingXml(String xml, String targetUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			xml = xml.replace("&gt;", ">");
			//System.out.println(xml);
			StringReader sr = new StringReader(xml);
			InputSource is = new InputSource(sr);
			Document doc = builder.parse(is);
			XPathFactory xFactory = XPathFactory.newInstance();
			XPath xpath = xFactory.newXPath();
			// 获取xml根节点
			XPathExpression expr = xpath.compile(targetUrl);
			NodeList phones = (NodeList) expr.evaluate(doc,
					XPathConstants.NODESET);
			for (int i = 0; i < phones.getLength(); i++) {
				Node type = phones.item(i);
				XPathExpression expr1 = xpath.compile("./*");
				NodeList list = (NodeList) expr1.evaluate(type,
						XPathConstants.NODESET);
				for (int j = 0; j < list.getLength(); j++) {
					Element e1 = (Element) list.item(j);
					map.put(e1.getNodeName(), e1.getTextContent());
					// System.out.println(e1.getNodeName() + " = "+
					// e1.getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		 System.out.println(map);
		return map;
	}

	/**
	 * 复杂解析xml
	 * 
	 * @param xml
	 * @param targetUrl
	 * @return
	 */
	public static List<Map<String, Object>> readStringXml(String xml,
			String targetUrl) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringReader sr = new StringReader(xml);
			InputSource is = new InputSource(sr);
			Document doc = builder.parse(is);
			XPathFactory xFactory = XPathFactory.newInstance();
			XPath xpath = xFactory.newXPath();
			// 获取xml根节点
			XPathExpression expr = xpath.compile(targetUrl);
			NodeList phones = (NodeList) expr.evaluate(doc,
					XPathConstants.NODESET);
			for (int i = 0; i < phones.getLength(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Node type = phones.item(i);
				XPathExpression expr1 = xpath.compile("./*");
				NodeList list = (NodeList) expr1.evaluate(type,
						XPathConstants.NODESET);
				for (int j = 0; j < list.getLength(); j++) {
					Element e1 = (Element) list.item(j);
					map.put(e1.getNodeName(), e1.getTextContent());
					// System.out.println(e1.getNodeName() + " = "+
					// e1.getTextContent());
				}
				listMap.add(map);
			}
		} catch (ParserConfigurationException e) {
			listMap = new ArrayList<Map<String, Object>>();
		} catch (SAXException e) {
			listMap = new ArrayList<Map<String, Object>>();
		} catch (IOException e) {
			listMap = new ArrayList<Map<String, Object>>();
		} catch (XPathExpressionException e) {
			listMap = new ArrayList<Map<String, Object>>();
		}
		return listMap;
	}

}

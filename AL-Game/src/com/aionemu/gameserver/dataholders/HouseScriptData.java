package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.housing.LBox;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lboxes")
public class HouseScriptData
{
	private static final Logger log = LoggerFactory.getLogger(HouseScriptData.class);
	private static Marshaller marshaller;
	
	@XmlElement(name = "lbox", required = true)
	protected List<LBox> scriptData;
	
	@XmlTransient
	private final Map<Integer, LBox> defaultTemplates;
	
	public HouseScriptData() {
		defaultTemplates = new HashMap<Integer, LBox>();
	}
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (LBox template: scriptData) {
			defaultTemplates.put(template.getId(), template);
		}
		scriptData.clear();
		scriptData = null;
	}
	
	public String createScript(int scriptId, int position, int iconId) {
		LBox template = defaultTemplates.get(scriptId);
		LBox result = (LBox) template.clone();
		result.setId(position);
		result.setIcon(iconId);
		HouseScriptData fragment = new HouseScriptData();
		fragment.scriptData = new ArrayList<LBox>();
		fragment.scriptData.add(result);
		Writer writer = new StringWriter();
		try {
			marshaller.marshal(fragment, writer);
		} catch (JAXBException e) {
		}
		return XmlFormatter.format(writer.toString());
	}
	
	public int size() {
		return defaultTemplates.size();
	}
	
	static {
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema schema = null;
		JAXBContext jc = null;
		try {
			schema = sf.newSchema(new File("./data/static_data/housing/scripts.xsd"));
			jc = JAXBContext.newInstance(new Class[] { HouseScriptData.class });
			marshaller = jc.createMarshaller();
			marshaller.setSchema(schema);
			marshaller.setProperty("jaxb.encoding", "UTF-8");
		} catch (Exception e) {
			log.error("Could not instantiate HouseScriptData : \n" + e);
		}
	}
	
	public static class XmlFormatter {
		private static final Logger log = LoggerFactory.getLogger(XmlFormatter.class);
		private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		private static DocumentBuilder db;
		@SuppressWarnings("restriction")
		public static String format(String unformattedXml) {
			try {
				Document document = parseXmlFile(unformattedXml);
				OutputFormat format = new OutputFormat(document);
				format.setIndenting(true);
				format.setIndent(2);
				format.setEncoding("UTF-8");
				Writer out = new StringWriter();
				XMLSerializer serializer = new XMLSerializer(out, format);
				serializer.serialize(document);
				return out.toString();
			} catch (IOException e) {
			}
			return null;
		}
		
		private static Document parseXmlFile(String in) {
			try {
				InputSource is = new InputSource(new StringReader(in));
				return db.parse(is);
			} catch (SAXException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		static {
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				log.error("Could not instantiate XmlFormatter : \n" + e);
			}
		}
	}
}
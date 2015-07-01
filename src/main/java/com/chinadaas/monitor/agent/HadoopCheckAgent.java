package com.chinadaas.monitor.agent;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.chinadaas.monitor.common.CheckResult;
import com.chinadaas.util.HttpUtil;

/**
 * projectName: chinadaas-data<br>
 * desc: Hadoop服务校验代理<br>
 * date: 2015年3月31日 上午10:50:17<br>
 * @author 开发者真实姓名[Andy]
 */
public class HadoopCheckAgent extends CheckAgent {

	private static final String QRY_NAMENODEINFO_NAME = "Hadoop:service=NameNode,name=NameNodeInfo";

	@Override
	public CheckResult check() {
		CheckResult cr = new CheckResult();

		try {
			JSONObject json = HttpUtil.getJSON(String.format("%s/jmx?qry=%s", meta.getUrl(), QRY_NAMENODEINFO_NAME));
			if (json.containsKey("beans") ) {
				JSONArray array = (JSONArray) json.get("beans");
				if (array.size() > 0) {
					JSONObject obj = (JSONObject) array.get(0);
					JSONParser parser = new JSONParser();
					JSONObject liveNodes = (JSONObject) parser.parse(obj.get("LiveNodes").toString());
					JSONObject deadNodes = (JSONObject) parser.parse(obj.get("DeadNodes").toString());
					if (deadNodes.size() == 0 && liveNodes.size() > 0) {
						cr.success();
					} else {
						String dead = StringUtils.join(deadNodes.keySet(), ",");
						cr.warning(String.format("deadNodes: %s.", dead));
					}
				} else {
					cr.danger("Hadoop not running");
				}
			} else {
				cr.danger("Hadoop not running");
			}
		} catch (Exception e) {
			cr.danger("Some errors occurs : " + e.getMessage());
		}

		return cr;
	}

}


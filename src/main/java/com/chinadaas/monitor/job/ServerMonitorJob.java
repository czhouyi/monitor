package com.chinadaas.monitor.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chinadaas.domain.dao.IServerMetaDao;
import com.chinadaas.domain.entity.ServerMeta;
import com.chinadaas.monitor.agent.CheckAgent;
import com.chinadaas.monitor.common.CheckResult;
import com.chinadaas.util.StringUtil;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务检查任务<br>
 * date: 2015年3月31日 下午1:32:31<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
@Component
public class ServerMonitorJob {

	private static Logger logger = LoggerFactory.getLogger(ServerMonitorJob.class);

	@Autowired
	private IServerMetaDao serverMetaDao;

	public void setServerMetaDao(IServerMetaDao serverMetaDao) {
		this.serverMetaDao = serverMetaDao;
	}

	@Scheduled(cron="0 0/5 *  * * ? ")
	public void run() {
		List<ServerMeta> metas = serverMetaDao.getActiveList();
		for (ServerMeta meta : metas) {
			String className = String.format("com.chinadaas.monitor.agent.%sCheckAgent", StringUtil.upperFirst(meta.getType().name()));
			CheckAgent ca = null;
			try {
				ca = (CheckAgent) Class.forName(className).newInstance();
			} catch (Exception e) {
				logger.error(e.getMessage());
				continue;
			}
			ca.setMeta(meta);
			CheckResult cr = ca.check();
			serverMetaDao.update("insert into T_MONITOR_LOG values (SEQ_MONITOR_LOG.NEXTVAL, ?, sysdate, ?, ?)", 
					new Object[]{meta.getId(), cr.getStatus().name(), cr.getInfo()});
			logger.info("服务[{}]检测结果为{}", meta.getName(), cr.getStatus().name());
		}
	}

}

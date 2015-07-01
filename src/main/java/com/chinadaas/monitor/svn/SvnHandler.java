//package com.chinadaas.monitor.svn;
//
//import java.util.Collection;
//
//import org.tmatesoft.svn.core.SVNException;
//import org.tmatesoft.svn.core.SVNURL;
//import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
//import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
//import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
//import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
//import org.tmatesoft.svn.core.io.SVNFileRevision;
//import org.tmatesoft.svn.core.io.SVNRepository;
//import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
//import org.tmatesoft.svn.core.wc.SVNWCUtil;
//
///**
// * projectName: chinadaas-data<br>
// * copyright: Copyright (c) 2015<br>
// *
// * desc: TODO<br>
// * date: 2015年4月15日 上午9:57:30<br>
// * @author 开发者真实姓名[Andy]
// */
//public class SvnHandler {
//	
//	private static String url = "http://192.168.11.11:81/chinadaas";
//	private static String name = "yichuanzhou";
//	private static String password = "yichuanzhou";
//	static SVNRepository repository = null;
//	
//	public SvnHandler() {
//		if (repository == null) {
//			init();
//		}
//	}
//
//	private void setup() {
//		DAVRepositoryFactory.setup();
//		SVNRepositoryFactoryImpl.setup();
//		FSRepositoryFactory.setup();
//	}
//	
//	private void init() {
//		setup();
//		
//    	SVNURL repositoryURL = null;
//    	try {
//    		repositoryURL = SVNURL.parseURIEncoded(url);
//    		repository = SVNRepositoryFactory.create(repositoryURL);
//    	} catch (SVNException e) {
//    		System.err.println("创建版本库实例时失败，版本库的URL是 '" + url + "': " + e.getMessage());
//    		System.exit(1);
//    	}
//    	ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
//    	repository.setAuthenticationManager(authManager);
//	}
//	
//	public long getLatestRevision() throws SVNException {
//		return repository.getLatestRevision();
//	}
//	
//	public Collection<SVNFileRevision> getAllFileRevisions(String path) throws SVNException {
//		return repository.getFileRevisions(path, null, 0, getLatestRevision());
//	}
//	
//	public Collection<SVNFileRevision> getAllFileRevisions(String path, long f, long t) throws SVNException {
//		return repository.getFileRevisions(path, null, f, t);
//	}
//	
//	public Collection getLog(String path, long from, long to) throws SVNException {
//		path = path.replace(url, "");
//		Collection collection = repository.log(new String[]{path}, null, from, to, true, true);
//		
//		return collection;
//	}
//	
//	public void close() throws SVNException {
//		repository.closeSession();
//	}
//	
//	public static void main(String[] args) throws SVNException {
//		SvnHandler sh = new SvnHandler();
//		
//		sh.getLog("/09DEV/09Project/trunk/gsinfo", 6223, 6233);
//	}
//}
//

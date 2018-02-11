package thirdpartyJar.solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrSelectTest {
	private final static String URL = "http://localhost:8983/solr/jcg";// 注意当多core时，要指定一个core，不然会报错
	private final static HttpSolrServer server = new HttpSolrServer(URL);

	public static void main(String[] args) throws Exception {
		select();
//		select2();
//		color();
	}

	/**
	 * 返回filed
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void select() throws SolrServerException, IOException {
//		name:solr AND content:能不能
		HttpSolrServer server = new HttpSolrServer(URL);
		// 定义查询字符串
		SolrQuery query = new SolrQuery("name:我想了   OR content:我想了");
		String s[] = query.getFilterQueries();
		// 实现分页的查询
		query.setStart(0);
		query.setRows(3);
		QueryResponse res = server.query(query);
		// 查询出来的结果都保存在SolrDocumentList中
		SolrDocumentList sdl = res.getResults();
		System.out.println("总数：" + sdl.getNumFound());
		for (SolrDocument sd : sdl) {
			System.out.println(sd.get("id") + "#" + sd.get("name") + "#" + sd.get("content"));
		}
	}

	/**
	 * 返回bean
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void select2() throws SolrServerException, IOException {
		HttpSolrServer server = new HttpSolrServer(URL);
		// 相当于QueryParser
		SolrQuery query = new SolrQuery("*:*");
		query.setStart(2);
		query.setRows(10);
		QueryResponse res = server.query(query);
		// 可以直接查询相应的bean对象，但是不是很常用
		// 使用这种方式无法获取总数量
		List<PeopleBean> list = res.getBeans(PeopleBean.class);
		System.out.println("当前总数：" + list.size());
		for (PeopleBean msg : list) {
			System.out.println(msg.getId() + "#" + msg.getName() + "#" + msg.getContent());
		}
	}

	/**
	 * 高亮显示
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void color() throws SolrServerException, IOException {
		HttpSolrServer server = new HttpSolrServer(URL);
		SolrQuery query = new SolrQuery("name:solr OR content:solr"); // 高亮字符:基于
		query.setHighlight(true).setHighlightSimplePre("<span class='red'>").setHighlightSimplePost("</span>")
				.setStart(0).setRows(10);
		// hl.fl表示高亮的field，也就是高亮的区域
		query.setParam("hl.fl", "name", "content"); // 显示高亮的字段
		QueryResponse res = server.query(query);

		SolrDocumentList sdl = res.getResults();
		System.out.println("总数：" + sdl.getNumFound());
		for (SolrDocument sd : sdl) {
			// System.out.println(sd.get("id")+"#"+sd.get("msg_title")+"#"+sd.get("msg_content"));
			String id = (String) sd.get("id");
			// 在solr这里对需要加高亮的字段必须要在索引中的store=true才行
			System.out.println(id + "#" + res.getHighlighting().get(id).get("name")+ "#" + res.getHighlighting().get(id).get("content"));
			;

		}
	}

}

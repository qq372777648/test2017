package thirdpartyJar.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
/*
 * 使用Lucene来操作索引库
 */
public class LuceneDao {

	/*
	 * 增删改索引都是通过indexWriter对象完成
	 *
	 */

	public static void main(String[] args) throws Exception {
//		addIndex2();
		 find2();
		 System.out.println("______________");
		 find2();
	}

	/*
	 * 建立索引
	 */
	public static void addIndex() throws IOException {
		Document document = new Document();
		StringField idfield = new StringField("id", "1252345544", Store.YES);
		TextField goodNamefield = new TextField("goodName", "蛋清白贝", Store.YES);
		document.add(idfield);
		document.add(goodNamefield);
		
//		Document document2 = new Document();
//		document2.add( new StringField("id", "3445334", Store.YES));
//		document2.add(new TextField("goodName", "酱肉焖鸡", Store.YES));
//		
//		Document document3 = new Document();
//		document3.add( new StringField("id", "62425922334", Store.YES));
//		document3.add(new TextField("goodName", "榴莲班戟", Store.YES));

		IndexWriter indexWriter = LuceneUtils.getIndexWriterOfSP(null);
		indexWriter.addDocument(document);
//		indexWriter.addDocument(document2);
//		indexWriter.addDocument(document3);
		// indexWriter.forceMerge(10);//合并cfs文件。比如设定1，就是自动合并成一个索引cfs文件
		indexWriter.close();
	}
	
	public static void find() throws IOException, ParseException {
		IndexSearcher searcher=LuceneUtils.getIndexSearcherOfSP();
		
		QueryParser parser =new QueryParser(Version.LUCENE_44, "goodName", LuceneUtils.StandardAnalyzer());
		Query query=parser.parse("蛋");
		TopDocs topDocs=searcher.search(query, 5);
		if(topDocs!=null){
			System.out.println("符合条件数量"+topDocs.totalHits);
			for (int i = 0; i < topDocs.scoreDocs.length; i++) {
				Document document =searcher.doc(topDocs.scoreDocs[i].doc);
				System.out.println(topDocs.scoreDocs[i].score+"--"+document.get("goodName"));
			}
		}
		
	}
	
	/*
	 * 建立索引
	 */
	public static void addIndex2() throws IOException {
		Document document = new Document();
		document.add( new StringField("id", "634532334", Store.YES));
		document.add(new TextField("goodName", "忘年追求", Store.YES));
		document.add(new TextField("desc", "芝士酥皮，牛油果，奶香，蜂蜜。。。。这感觉如梦", Store.YES));
		IndexWriter indexWriter = LuceneUtils.getIndexWriterOfSP(LuceneUtils.IKAnalyzer());
		indexWriter.addDocument(document);
		// indexWriter.forceMerge(10);//合并cfs文件。比如设定1，就是自动合并成一个索引cfs文件
		indexWriter.close();
	}
	
	private static ScoreDoc after;
	public static void find2() throws IOException, ParseException {
		String []fields={"goodName","desc"};
		IndexSearcher searcher=LuceneUtils.getIndexSearcherOfSP();
		
		MultiFieldQueryParser parser =new MultiFieldQueryParser(Version.LUCENE_44 , fields, LuceneUtils.IKAnalyzer());
		Query query=parser.parse("芝士");
		TopDocs topDocs=null;
		if(after!=null)
			topDocs=searcher.searchAfter(after, query, 2) ;//(query, 50);
		else
			topDocs=searcher.search(query, 50);
		if(topDocs!=null){
			System.out.println("符合条件数量"+topDocs.totalHits);
			for (int i = 0; i < topDocs.scoreDocs.length; i++) {
				if(i==1)
					after=topDocs.scoreDocs[i];
				Document document =searcher.doc(topDocs.scoreDocs[i].doc);
				System.out.println(topDocs.scoreDocs[i].score+"--"+document.get("goodName")+"--"+document.get("desc"));
			}
		}
		
	}

	// /*
	// * 删除索引，根据字段对应的值进行删除
	// * */
	// public void delIndex(String fieldName,String fieldValue) throws
	// IOException {
	//
	// IndexWriter indexWriter = LuceneUtils.getIndexWriterOfSP();
	// //term!!!
	// Term term = new Term(fieldName,fieldValue);
	// //根据字段对应的值删除索引
	// indexWriter.deleteDocuments(term);
	//
	// indexWriter.close();
	// }
	//
	// /*
	// * 先删除符合条件的记录，再创建一个符合条件的记录
	// * */
	// public void updateIndex(String fieldName,String fieldValue,GoodDetails
	// goodDetails) throws IOException {
	//
	// IndexWriter indexWriter = LuceneUtils.getIndexWriterOfSP();
	//
	// Term term = new Term(fieldName,fieldValue);
	//
	// Document document = GoodDetailsUtils.GoodDetailsToDocument(goodDetails);
	//
	// /*
	// * 1.设置更新的条件
	// * 2.设置更新的内容的对象
	// * */
	// indexWriter.updateDocument(term, document);
	//
	// indexWriter.close();
	// }
	//
//	/*
//	 * 分页：每页10条
//	 */
//	public void findIndex(String keywords, int start, int rows) throws Exception {
//
//		Directory directory = FSDirectory.open(new File(Constant.INDEXURL_ALL));// 索引创建在硬盘上。
//		IndexSearcher indexSearcher = LuceneUtils.getIndexSearcherOfSP();
//
//		/** 同义词处理 */
//		String result = SynonymAnalyzerUtil
//				.displayTokens(SynonymAnalyzerUtil.convertSynonym(SynonymAnalyzerUtil.analyzeChinese(keywords, true)));
//		// Analyzer analyzer4 = new IKAnalyzer(false);// 普通简陋语意分词
//		// String result = keywords;
//		// 需要根据哪几个字段进行检索...
//		String fields[] = { "goodName" };
//
//		// 查询分析程序（查询解析）
//		QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(), fields,
//				LuceneUtils.getAnalyzer());
//
//		// 不同的规则构造不同的子类...
//		// title:keywords content:keywords
//		Query query = queryParser.parse(result);
//
//		// 这里检索的是索引目录,会把整个索引目录都读取一遍
//		// 根据query查询，返回前N条
//		TopDocs topDocs = indexSearcher.search(query, start + rows);
//
//		System.out.println("总记录数=" + topDocs.totalHits);
//
//		ScoreDoc scoreDoc[] = topDocs.scoreDocs;
//
//		/** 添加设置文字高亮begin */
//		// htmly页面高亮显示的格式化，默认是<b></b>即加粗
//		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
//		Scorer scorer = new QueryScorer(query);
//		Highlighter highlighter = new Highlighter(formatter, scorer);
//
//		// 设置文字摘要（高亮的部分），此时摘要大小为10
//		// int fragmentSize = 10;
//		Fragmenter fragmenter = new SimpleFragmenter();
//		highlighter.setTextFragmenter(fragmenter);
//
//		/** 添加设置文字高亮end */
//		List<GoodDetails> goodDetailslist = new ArrayList<GoodDetails>();
//		// 防止数组溢出
//		int endResult = Math.min(scoreDoc.length, start + rows);
//		GoodDetails goodDetails = null;
//
//		for (int i = start; i < endResult; i++) {
//			goodDetails = new GoodDetails();
//			// docID lucene的索引库里面有很多的document，lucene为每个document定义了一个编号，唯一标识，自增长
//			int docID = scoreDoc[i].doc;
//			System.out.println("标识docID=" + docID);
//			Document document = indexSearcher.doc(docID);
//			/** 获取文字高亮的信息begin */
//			System.out.println("==========================");
//			TokenStream tokenStream = LuceneUtils.getAnalyzer().tokenStream("goodName",
//					new StringReader(document.get("goodName")));
//			String goodName = highlighter.getBestFragment(tokenStream, document.get("goodName"));
//			System.out.println("goodName=" + goodName);
//			System.out.println("==========================");
//			/** 获取文字高亮的信息end */
//
//			// 备注：document.get("id")的返回值是String
//			goodDetails.setGoodId((document.get("id")));
//			goodDetails.setGoodName(goodName);
//			goodDetailslist.add(goodDetails);
//		}
//		return goodDetailslist;
//	}

}

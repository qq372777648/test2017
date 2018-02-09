package thirdpartyJar.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
public class LuceneUtils {
    private static Directory directory = null;


    private static IndexWriterConfig indexWriterConfig = null;

    private static Version matchVersion = null;

    private static Analyzer analyzer = null;
    
    private static IndexWriter  indexWriter=null;

    private static Directory ramDirectory = null;


    static {
        try {
        	//lucene index 存储路径
            directory = FSDirectory.open(new File("D:\\LuceneData\\test1"));
            
            matchVersion = Version.LUCENE_44;
            //分词器
            analyzer = new StandardAnalyzer(matchVersion);
            indexWriterConfig = new IndexWriterConfig(matchVersion, analyzer);
            
            if(indexWriter.isLocked(directory)){
            	indexWriter.unlock(directory);
            }
            
//            indexWriter=new IndexWriter(directory, indexWriterConfig);
            
            // 创建内存索引库
            ramDirectory = new RAMDirectory(directory, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 返回用于操作索引的对象
     * */
    public static IndexWriter getIndexWriterOfSP(Analyzer  analyzer) throws IOException {
    	if(analyzer==null||analyzer instanceof StandardAnalyzer){
    		return new IndexWriter(directory, indexWriterConfig);
    	}
        return new IndexWriter(directory, new IndexWriterConfig(matchVersion, analyzer));
    }


    /*
     * 返回用于读取索引的对象
     * */
    public static IndexSearcher getIndexSearcherOfSP() throws IOException {
    	
//        IndexReader indexReader = DirectoryReader.open(ramDirectory);
    	IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        return indexSearcher;
    }

    /*
     * 获取lucene当前的版本
     * */
    public static Version getMatchVersion() {
        return matchVersion;
    }

    /*
     * 获取经典分词器
     * */
    public static Analyzer StandardAnalyzer() {
        return new StandardAnalyzer(matchVersion);
    }
    /*
     * 获取空格分词器
     * */
    public static Analyzer WhitespaceAnalyzer() {
        return new WhitespaceAnalyzer(matchVersion);
    }
    public static Analyzer SimpleAnalyzer() {
        return new SimpleAnalyzer(matchVersion);
    }
    public static Analyzer CJKAnalyzer() {
        return new CJKAnalyzer(matchVersion);
    }
    public static Analyzer KeywordAnalyzer() {
        return new KeywordAnalyzer();
    }
    public static Analyzer StopAnalyzer() {
        return new StopAnalyzer(matchVersion);
    }
    /**
     * 第三方分词器（比较好用）
     * @return
     */
    public static Analyzer IKAnalyzer() {
        return new IKAnalyzer();
    }
    
    public static void testAnalyzer(Analyzer analyzer,String str){
    	try {
			StringReader reader = new StringReader(str);
			TokenStream tokenStream = analyzer.tokenStream("", reader);
			tokenStream.reset();
			CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
			System.out.println("分词技术" + analyzer.getClass());
			while (tokenStream.incrementToken()) {
				System.out.print(charTermAttribute.toString() + "|");
			} 
			System.out.println();
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    
    public static void main(String[] args) {
    	String str="hello lucene自己选的路不要后悔世界是你内心的，镜 像";
    	testAnalyzer(StandardAnalyzer(),str);
    	testAnalyzer(WhitespaceAnalyzer(),str);
    	testAnalyzer(SimpleAnalyzer(),str);
    	testAnalyzer(CJKAnalyzer(),str);
    	testAnalyzer(KeywordAnalyzer(),str);
    	testAnalyzer(StopAnalyzer(),str);
    	testAnalyzer(IKAnalyzer(),str);
	}

}
